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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubThatchType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubThatchTypeDAO;
import za.co.iclub.pss.ws.model.IclubThatchTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubThatchTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubThatchTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubThatchTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubThatchTypeDAO iclubThatchTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubThatchTypeModel model) {
		try {
			IclubThatchType iTt = new IclubThatchType();

			iTt.setTtId(iclubCommonDAO.getNextId(IclubThatchType.class));
			iTt.setTtLongDesc(model.getTtLongDesc());
			iTt.setTtShortDesc(model.getTtShortDesc());
			iTt.setTtStatus(model.getTtStatus());

			iclubThatchTypeDAO.save(iTt);

			LOGGER.info("Save Success with ID :: " + iTt.getTtId());

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
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubThatchTypeModel model) {
		try {
			IclubThatchType iTt = new IclubThatchType();

			iTt.setTtId(model.getTtId());
			iTt.setTtLongDesc(model.getTtLongDesc());
			iTt.setTtShortDesc(model.getTtShortDesc());
			iTt.setTtStatus(model.getTtStatus());

			iclubThatchTypeDAO.merge(iTt);

			LOGGER.info("Merge Success with ID :: " + model.getTtId());

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
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") Long id) {
		try {
			iclubThatchTypeDAO.delete(iclubThatchTypeDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubThatchTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubThatchTypeDAO.findAll();

			for (Object object : batmod) {
				IclubThatchType iTt = (IclubThatchType) object;

				IclubThatchTypeModel model = new IclubThatchTypeModel();

				model.setTtId(iTt.getTtId());
				model.setTtLongDesc(iTt.getTtLongDesc());
				model.setTtShortDesc(iTt.getTtShortDesc());
				model.setTtStatus(iTt.getTtStatus());

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubThatchTypeModel getById(@PathParam("id") Long id) {
		IclubThatchTypeModel model = new IclubThatchTypeModel();
		try {
			IclubThatchType bean = iclubThatchTypeDAO.findById(id);

			model.setTtId(bean.getTtId());
			model.setTtLongDesc(bean.getTtLongDesc());
			model.setTtShortDesc(bean.getTtShortDesc());
			model.setTtStatus(bean.getTtStatus());

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubThatchTypeDAO.getThatchTypeBySD(val, id);
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	public IclubThatchTypeDAO getIclubThatchTypeDAO() {
		return iclubThatchTypeDAO;
	}

	public void setIclubThatchTypeDAO(IclubThatchTypeDAO iclubThatchTypeDAO) {
		this.iclubThatchTypeDAO = iclubThatchTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
