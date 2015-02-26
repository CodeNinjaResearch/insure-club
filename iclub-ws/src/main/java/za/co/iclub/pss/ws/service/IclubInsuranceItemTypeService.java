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

import za.co.iclub.pss.orm.bean.IclubInsuranceItemType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubInsuranceItemTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubInsuranceItemTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubInsuranceItemTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO; 

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubInsuranceItemTypeModel model) {
		try {
			IclubInsuranceItemType iIit = new IclubInsuranceItemType();

			iIit.setIitId(iclubCommonDAO.getNextId(IclubInsuranceItemType.class));
			iIit.setIitLongDesc(model.getIitLongDesc());
			iIit.setIitShortDesc(model.getIitShortDesc());
			iIit.setIitStatus(model.getIitStatus());

			iclubInsuranceItemTypeDAO.save(iIit);

			LOGGER.info("Save Success with ID :: " + iIit.getIitId());

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
	public ResponseModel mod(IclubInsuranceItemTypeModel model) {
		try {
			IclubInsuranceItemType iIit = new IclubInsuranceItemType();

			iIit.setIitId(model.getIitId());
			iIit.setIitLongDesc(model.getIitLongDesc());
			iIit.setIitShortDesc(model.getIitShortDesc());
			iIit.setIitStatus(model.getIitStatus());

			iclubInsuranceItemTypeDAO.merge(iIit);

			LOGGER.info("Merge Success with ID :: " + model.getIitId());

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
			iclubInsuranceItemTypeDAO.delete(iclubInsuranceItemTypeDAO.findById(id));
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
	public <T extends IclubInsuranceItemTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsuranceItemTypeDAO.findAll();

			for (Object object : batmod) {
				IclubInsuranceItemType iIit = (IclubInsuranceItemType) object;

				IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();

				model.setIitId(iIit.getIitId());
				model.setIitLongDesc(iIit.getIitLongDesc());
				model.setIitShortDesc(iIit.getIitShortDesc());
				model.setIitStatus(iIit.getIitStatus());

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
	public IclubInsuranceItemTypeModel getById(@PathParam("id") Long id) {
		IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();
		try {
			IclubInsuranceItemType bean = iclubInsuranceItemTypeDAO.findById(id);

			model.setIitId(bean.getIitId());
			model.setIitLongDesc(bean.getIitLongDesc());
			model.setIitShortDesc(bean.getIitShortDesc());
			model.setIitStatus(bean.getIitStatus());

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
			List data = iclubInsuranceItemTypeDAO.getInsuranceItemTypeBySD(val, id);
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


	 

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
