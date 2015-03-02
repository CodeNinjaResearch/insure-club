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

import za.co.iclub.pss.orm.bean.IclubPaymentType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubPaymentTypeDAO;
import za.co.iclub.pss.ws.model.IclubPaymentTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPaymentTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPaymentTypeModelService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPaymentTypeModelService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPaymentTypeDAO iclubPaymentTypeDAO; 

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPaymentTypeModel model) {
		try {
			IclubPaymentType iPt = new IclubPaymentType();

			iPt.setPtId(iclubCommonDAO.getNextId(IclubPaymentType.class));
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPaymentTypeDAO.save(iPt);

			LOGGER.info("Save Success with ID :: " + iPt.getPtId());

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
	public ResponseModel mod(IclubPaymentTypeModel model) {
		try {
			IclubPaymentType iPt = new IclubPaymentType();

			iPt.setPtId(model.getPtId());
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPaymentTypeDAO.merge(iPt);

			LOGGER.info("Merge Success with ID :: " + model.getPtId());

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
			iclubPaymentTypeDAO.delete(iclubPaymentTypeDAO.findById(id));
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
	public <T extends IclubPaymentTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPaymentTypeDAO.findAll();

			for (Object object : batmod) {
				IclubPaymentType iPt = (IclubPaymentType) object;

				IclubPaymentTypeModel model = new IclubPaymentTypeModel();

				model.setPtId(iPt.getPtId());
				model.setPtLongDesc(iPt.getPtLongDesc());
				model.setPtShortDesc(iPt.getPtShortDesc());
				model.setPtStatus(iPt.getPtStatus());

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
	public IclubPaymentTypeModel getById(@PathParam("id") Long id) {
		IclubPaymentTypeModel model = new IclubPaymentTypeModel();
		try {
			IclubPaymentType bean = iclubPaymentTypeDAO.findById(id);

			model.setPtId(bean.getPtId());
			model.setPtLongDesc(bean.getPtLongDesc());
			model.setPtShortDesc(bean.getPtShortDesc());
			model.setPtStatus(bean.getPtStatus());

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
			List data = iclubPaymentTypeDAO.getPaymentTypeBySD(val, id);
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


	 

	public IclubPaymentTypeDAO getIclubPaymentTypeDAO() {
		return iclubPaymentTypeDAO;
	}

	public void setIclubPaymentTypeDAO(IclubPaymentTypeDAO iclubPaymentTypeDAO) {
		this.iclubPaymentTypeDAO = iclubPaymentTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
