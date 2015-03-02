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

import za.co.iclub.pss.orm.bean.IclubPurposeType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubPurposeTypeDAO;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPurposeTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPurposeTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPurposeTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPurposeTypeDAO iclubPurposeTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPurposeTypeModel model) {
		try {
			IclubPurposeType iPt = new IclubPurposeType();

			iPt.setPtId(iclubCommonDAO.getNextId(IclubPurposeType.class));
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPurposeTypeDAO.save(iPt);

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
	public ResponseModel mod(IclubPurposeTypeModel model) {
		try {
			IclubPurposeType iPt = new IclubPurposeType();

			iPt.setPtId(model.getPtId());
			iPt.setPtLongDesc(model.getPtLongDesc());
			iPt.setPtShortDesc(model.getPtShortDesc());
			iPt.setPtStatus(model.getPtStatus());

			iclubPurposeTypeDAO.merge(iPt);

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
			iclubPurposeTypeDAO.delete(iclubPurposeTypeDAO.findById(id));
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
	public <T extends IclubPurposeTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPurposeTypeDAO.findAll();

			for (Object object : batmod) {
				IclubPurposeType iPt = (IclubPurposeType) object;

				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

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
	public IclubPurposeTypeModel getById(@PathParam("id") Long id) {
		IclubPurposeTypeModel model = new IclubPurposeTypeModel();
		try {
			IclubPurposeType bean = iclubPurposeTypeDAO.findById(id);

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
			List data = iclubPurposeTypeDAO.getPurposeTypeBySD(val, id);
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

	public IclubPurposeTypeDAO getIclubPurposeTypeDAO() {
		return iclubPurposeTypeDAO;
	}

	public void setIclubPurposeTypeDAO(IclubPurposeTypeDAO iclubPurposeTypeDAO) {
		this.iclubPurposeTypeDAO = iclubPurposeTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
