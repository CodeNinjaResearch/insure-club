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

import za.co.iclub.pss.orm.bean.IclubVehUsageType;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubVehUsageTypeDAO;
import za.co.iclub.pss.ws.model.IclubVehUsageTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubVehUsageTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubVehUsageTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubVehUsageTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubVehUsageTypeDAO iclubVehUsageTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubVehUsageTypeModel model) {
		try {
			IclubVehUsageType iCPt = new IclubVehUsageType();

			iCPt.setVuId(iclubCommonDAO.getNextId(IclubVehUsageType.class));
			iCPt.setVuLongDesc(model.getVuLongDesc());
			iCPt.setVuShortDesc(model.getVuShortDesc());
			iCPt.setVuStatus(model.getVuStatus());

			iclubVehUsageTypeDAO.save(iCPt);

			LOGGER.info("Save Success with ID :: " + iCPt.getVuId());

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
	public ResponseModel mod(IclubVehUsageTypeModel model) {
		try {
			IclubVehUsageType iCPt = new IclubVehUsageType();

			iCPt.setVuId(model.getVuId());
			iCPt.setVuLongDesc(model.getVuLongDesc());
			iCPt.setVuShortDesc(model.getVuShortDesc());
			iCPt.setVuStatus(model.getVuStatus());

			iclubVehUsageTypeDAO.merge(iCPt);

			LOGGER.info("Merge Success with ID :: " + model.getVuId());

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
			iclubVehUsageTypeDAO.delete(iclubVehUsageTypeDAO.findById(id));
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
	public <T extends IclubVehUsageTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubVehUsageTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehUsageType iCPt = (IclubVehUsageType) object;

					IclubVehUsageTypeModel model = new IclubVehUsageTypeModel();

					model.setVuId(iCPt.getVuId());
					model.setVuLongDesc(iCPt.getVuLongDesc());
					model.setVuShortDesc(iCPt.getVuShortDesc());
					model.setVuStatus(iCPt.getVuStatus());

					if (iCPt.getIclubVehicles() != null && iCPt.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iCPt.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle iclubVehicle : iCPt.getIclubVehicles()) {
							vehicles[i] = iclubVehicle.getVId();
							i++;
						}
						model.setIclubVehicles(vehicles);
					}

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
	public <T extends IclubVehUsageTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubVehUsageType.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubVehUsageType iCPt = (IclubVehUsageType) object;

					IclubVehUsageTypeModel model = new IclubVehUsageTypeModel();

					model.setVuId(iCPt.getVuId());
					model.setVuLongDesc(iCPt.getVuLongDesc());
					model.setVuShortDesc(iCPt.getVuShortDesc());
					model.setVuStatus(iCPt.getVuStatus());

					if (iCPt.getIclubVehicles() != null && iCPt.getIclubVehicles().size() > 0) {
						String[] vehicles = new String[iCPt.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle iclubVehicle : iCPt.getIclubVehicles()) {
							vehicles[i] = iclubVehicle.getVId();
							i++;
						}
						model.setIclubVehicles(vehicles);
					}

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
	public IclubVehUsageTypeModel getById(@PathParam("id") Long id) {
		IclubVehUsageTypeModel model = new IclubVehUsageTypeModel();
		try {
			IclubVehUsageType bean = iclubVehUsageTypeDAO.findById(id);

			model.setVuId(bean.getVuId());
			model.setVuLongDesc(bean.getVuLongDesc());
			model.setVuShortDesc(bean.getVuShortDesc());
			model.setVuStatus(bean.getVuStatus());

			if (bean.getIclubVehicles() != null && bean.getIclubVehicles().size() > 0) {
				String[] vehicles = new String[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubVehicle iclubVehicle : bean.getIclubVehicles()) {
					vehicles[i] = iclubVehicle.getVId();
					i++;
				}
				model.setIclubVehicles(vehicles);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubVehUsageTypeDAO getIclubVehUsageTypeDAO() {
		return iclubVehUsageTypeDAO;
	}

	public void setIclubVehUsageTypeDAO(IclubVehUsageTypeDAO iclubVehUsageTypeDAO) {
		this.iclubVehUsageTypeDAO = iclubVehUsageTypeDAO;
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

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
