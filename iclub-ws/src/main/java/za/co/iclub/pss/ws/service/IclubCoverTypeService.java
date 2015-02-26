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

import za.co.iclub.pss.orm.bean.IclubCoverType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCoverTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCoverTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubCoverTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO; 

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubCoverTypeModel model) {
		try {
			IclubCoverType iCt = new IclubCoverType();

			iCt.setCtId(iclubCommonDAO.getNextId(IclubCoverType.class));
			iCt.setCtLongDesc(model.getCtLongDesc());
			iCt.setCtShortDesc(model.getCtShortDesc());
			iCt.setCtStatus(model.getCtStatus());

			iclubCoverTypeDAO.save(iCt);

			LOGGER.info("Save Success with ID :: " + iCt.getCtId());

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
	public ResponseModel mod(IclubCoverTypeModel model) {
		try {
			IclubCoverType iCt = new IclubCoverType();

			iCt.setCtId(model.getCtId());
			iCt.setCtLongDesc(model.getCtLongDesc());
			iCt.setCtShortDesc(model.getCtShortDesc());
			iCt.setCtStatus(model.getCtStatus());

			iclubCoverTypeDAO.merge(iCt);

			LOGGER.info("Merge Success with ID :: " + model.getCtId());

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
			iclubCoverTypeDAO.delete(iclubCoverTypeDAO.findById(id));
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
	public <T extends IclubCoverTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCoverTypeDAO.findAll();

			for (Object object : batmod) {
				IclubCoverType iCt = (IclubCoverType) object;

				IclubCoverTypeModel model = new IclubCoverTypeModel();

				model.setCtId(iCt.getCtId());
				model.setCtLongDesc(iCt.getCtLongDesc());
				model.setCtShortDesc(iCt.getCtShortDesc());
				model.setCtStatus(iCt.getCtStatus());

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
	public IclubCoverTypeModel getById(@PathParam("id") Long id) {
		IclubCoverTypeModel model = new IclubCoverTypeModel();
		try {
			IclubCoverType bean = iclubCoverTypeDAO.findById(id);

			model.setCtId(bean.getCtId());
			model.setCtLongDesc(bean.getCtLongDesc());
			model.setCtShortDesc(bean.getCtShortDesc());
			model.setCtStatus(bean.getCtStatus());

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
			List data = iclubCoverTypeDAO.getCoverTypeBySD(val, id);
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


	 

	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}

	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
