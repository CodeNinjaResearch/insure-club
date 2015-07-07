package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubAlarmTypeModel;
import za.co.iclub.pss.orm.bean.IclubAlarmType;
import za.co.iclub.pss.orm.dao.IclubAlarmTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAlarmTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAlarmTypeService {
	private static final Logger LOGGER = Logger.getLogger(IclubAlarmTypeService.class);
	private IclubAlarmTypeDAO iclubAlarmTypeDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubAlarmTypeModel model) {
		try {

			IclubAlarmType alamType = new IclubAlarmType();

			alamType.setAtId(iclubCommonDAO.getNextId(IclubAlarmType.class));
			alamType.setAtLongDesc(model.getAtLongDesc());
			alamType.setAtShortDesc(model.getAtShortDesc());
			alamType.setAtStatus(model.getAtStatus());

			iclubAlarmTypeDAO.save(alamType);

			LOGGER.info("Save Success with ID :: " + alamType.getAtId().longValue());

			ResponseModel message = new ResponseModel();

			message.setStatusCode(0);
			message.setStatusDesc("Success");

			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel mod(IclubAlarmTypeModel model) {
		try {
			IclubAlarmType alamType = new IclubAlarmType();

			alamType.setAtId(model.getAtId());
			alamType.setAtLongDesc(model.getAtLongDesc());
			alamType.setAtShortDesc(model.getAtShortDesc());
			alamType.setAtStatus(model.getAtStatus());

			iclubAlarmTypeDAO.merge(alamType);

			LOGGER.info("Save Success with ID :: " + model.getAtId().longValue());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@GET
	@Path("/del/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public Response del(@PathParam("id") Long id) {
		try {
			iclubAlarmTypeDAO.delete(iclubAlarmTypeDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubAlarmType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if (data != null && data.size() > 0) {
				message.setStatusCode(1);
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(0);
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(2);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubAlarmTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubAlarmTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubAlarmType iclubAlamtype = (IclubAlarmType) object;
					IclubAlarmTypeModel iCB = new IclubAlarmTypeModel();

					iCB.setAtId(iclubAlamtype.getAtId().longValue());
					iCB.setAtLongDesc(iclubAlamtype.getAtLongDesc());
					iCB.setAtShortDesc(iclubAlamtype.getAtShortDesc());
					iCB.setAtStatus(iclubAlamtype.getAtStatus());

					ret.add((T) iCB);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional
	public IclubAlarmTypeModel getById(@PathParam("id") Long id) {
		IclubAlarmTypeModel model = new IclubAlarmTypeModel();
		try {
			IclubAlarmType bean = iclubAlarmTypeDAO.findById(id);

			model.setAtId(bean.getAtId().longValue());
			model.setAtLongDesc(bean.getAtLongDesc());
			model.setAtShortDesc(bean.getAtShortDesc());
			model.setAtStatus(bean.getAtStatus());

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubAlarmTypeDAO getIclubAlarmTypeDAO() {
		return iclubAlarmTypeDAO;
	}

	public void setIclubAlarmTypeDAO(IclubAlarmTypeDAO iclubAlarmTypeDAO) {
		this.iclubAlarmTypeDAO = iclubAlarmTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
