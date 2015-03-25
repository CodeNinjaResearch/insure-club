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

import za.co.iclub.pss.orm.bean.IclubVehicleType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleTypeDAO;
import za.co.iclub.pss.ws.model.IclubVehicleTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubVehicleTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubVehicleTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubVehicleTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubVehicleTypeDAO iclubVehicleTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubVehicleTypeModel model) {
		try {
			IclubVehicleType iTt = new IclubVehicleType();

			iTt.setVtId(iclubCommonDAO.getNextId(IclubVehicleType.class));
			iTt.setVtLongDesc(model.getVtLongDesc());
			iTt.setVtShortDesc(model.getVtShortDesc());
			iTt.setVtStatus(model.getVtStatus());

			iclubVehicleTypeDAO.save(iTt);

			LOGGER.info("Save Success with ID :: " + iTt.getVtId());

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
	public ResponseModel mod(IclubVehicleTypeModel model) {
		try {
			IclubVehicleType iTt = new IclubVehicleType();

			iTt.setVtId(model.getVtId());
			iTt.setVtLongDesc(model.getVtLongDesc());
			iTt.setVtShortDesc(model.getVtShortDesc());
			iTt.setVtStatus(model.getVtStatus());

			iclubVehicleTypeDAO.merge(iTt);

			LOGGER.info("Merge Success with ID :: " + model.getVtId());

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
			iclubVehicleTypeDAO.delete(iclubVehicleTypeDAO.findById(id));
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
	public <T extends IclubVehicleTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubVehicleTypeDAO.findAll();

			for (Object object : batmod) {
				IclubVehicleType iTt = (IclubVehicleType) object;

				IclubVehicleTypeModel model = new IclubVehicleTypeModel();

				model.setVtId(iTt.getVtId());
				model.setVtLongDesc(iTt.getVtLongDesc());
				model.setVtShortDesc(iTt.getVtShortDesc());
				model.setVtStatus(iTt.getVtStatus());

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
	public IclubVehicleTypeModel getById(@PathParam("id") Long id) {
		IclubVehicleTypeModel model = new IclubVehicleTypeModel();
		try {
			IclubVehicleType bean = iclubVehicleTypeDAO.findById(id);

			model.setVtId(bean.getVtId());
			model.setVtLongDesc(bean.getVtLongDesc());
			model.setVtShortDesc(bean.getVtShortDesc());
			model.setVtStatus(bean.getVtStatus());

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
			List data = iclubVehicleTypeDAO.getVehicleTypeBySD(val, id);
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

	public IclubVehicleTypeDAO getIclubVehicleTypeDAO() {
		return iclubVehicleTypeDAO;
	}

	public void setIclubVehicleTypeDAO(IclubVehicleTypeDAO iclubVehicleTypeDAO) {
		this.iclubVehicleTypeDAO = iclubVehicleTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
