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

import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityDeviceDAO;
import za.co.iclub.pss.orm.dao.IclubVehSecTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleMasterDAO;
import za.co.iclub.pss.trans.IclubVehicleTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubVehicleService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubVehicleService {

	protected static final Logger LOGGER = Logger.getLogger(IclubVehicleService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubVehicleDAO iclubVehicleDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubVehUsageTypeDAO iclubVehUsageTypeDAO;
	private IclubVehSecTypeDAO iclubVehSecTypeDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubSecurityDeviceDAO iclubSecurityDeviceDAO;
	private IclubVehicleMasterDAO iclubVehicleMasterDAO;
	private IclubDriverDAO iclubDriverDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubVehicleModel model) {
		try {
			IclubVehicle iCV = IclubVehicleTrans.fromWStoORM(model, iclubVehicleMasterDAO, iclubAccessTypeDAO, iclubDriverDAO, iclubSecurityDeviceDAO, iclubVehSecTypeDAO, iclubPersonDAO, iclubCoverTypeDAO, iclubVehUsageTypeDAO);

			iclubVehicleDAO.save(iCV);

			LOGGER.info("Save Success with ID :: " + iCV.getVId());

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
	public ResponseModel mod(IclubVehicleModel model) {
		try {
			IclubVehicle iCV = IclubVehicleTrans.fromWStoORM(model, iclubVehicleMasterDAO, iclubAccessTypeDAO, iclubDriverDAO, iclubSecurityDeviceDAO, iclubVehSecTypeDAO, iclubPersonDAO, iclubCoverTypeDAO, iclubVehUsageTypeDAO);

			iclubVehicleDAO.merge(iCV);

			LOGGER.info("Merge Success with ID :: " + model.getVId());

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
	public Response del(@PathParam("id") String id) {
		try {
			iclubVehicleDAO.delete(iclubVehicleDAO.findById(id));
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
	public <T extends IclubVehicleModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubVehicleDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicle bean = (IclubVehicle) object;

					IclubVehicleModel model = IclubVehicleTrans.fromORMtoWS(bean);

					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/user/{user}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubVehicleModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubVehicle.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicle bean = (IclubVehicle) object;

					IclubVehicleModel model = IclubVehicleTrans.fromORMtoWS(bean);

					ret.add((T) model);
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
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubVehicleModel getById(@PathParam("id") String id) {
		IclubVehicleModel model = new IclubVehicleModel();
		try {
			IclubVehicle bean = iclubVehicleDAO.findById(id);

			model = IclubVehicleTrans.fromORMtoWS(bean);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/getByDriverId/{driverId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubVehicleModel> List<T> getVehicleByDriverAndQuote(@PathParam("driverId") String driverId) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByDriverId(driverId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehicle bean = (IclubVehicle) object;

					IclubVehicleModel model = IclubVehicleTrans.fromORMtoWS(bean);

					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	public IclubVehicleDAO getIclubVehicleDAO() {
		return iclubVehicleDAO;
	}

	public void setIclubVehicleDAO(IclubVehicleDAO iclubVehicleDAO) {
		this.iclubVehicleDAO = iclubVehicleDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubVehUsageTypeDAO getIclubVehUsageTypeDAO() {
		return iclubVehUsageTypeDAO;
	}

	public void setIclubVehUsageTypeDAO(IclubVehUsageTypeDAO iclubVehUsageTypeDAO) {
		this.iclubVehUsageTypeDAO = iclubVehUsageTypeDAO;
	}

	public IclubVehSecTypeDAO getIclubVehSecTypeDAO() {
		return iclubVehSecTypeDAO;
	}

	public void setIclubVehSecTypeDAO(IclubVehSecTypeDAO iclubVehSecTypeDAO) {
		this.iclubVehSecTypeDAO = iclubVehSecTypeDAO;
	}

	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}

	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
	}

	public IclubSecurityDeviceDAO getIclubSecurityDeviceDAO() {
		return iclubSecurityDeviceDAO;
	}

	public void setIclubSecurityDeviceDAO(IclubSecurityDeviceDAO iclubSecurityDeviceDAO) {
		this.iclubSecurityDeviceDAO = iclubSecurityDeviceDAO;
	}

	public IclubVehicleMasterDAO getIclubVehicleMasterDAO() {
		return iclubVehicleMasterDAO;
	}

	public void setIclubVehicleMasterDAO(IclubVehicleMasterDAO iclubVehicleMasterDAO) {
		this.iclubVehicleMasterDAO = iclubVehicleMasterDAO;
	}

	public IclubDriverDAO getIclubDriverDAO() {
		return iclubDriverDAO;
	}

	public void setIclubDriverDAO(IclubDriverDAO iclubDriverDAO) {
		this.iclubDriverDAO = iclubDriverDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}

	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}

}
