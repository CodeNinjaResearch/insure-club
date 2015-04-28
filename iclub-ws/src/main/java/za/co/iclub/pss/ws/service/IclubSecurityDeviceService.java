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

import za.co.iclub.pss.orm.bean.IclubSecurityDevice;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityDeviceDAO;
import za.co.iclub.pss.orm.dao.IclubTrackerMasterDAO;
import za.co.iclub.pss.ws.model.IclubSecurityDeviceModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSecurityDeviceService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSecurityDeviceService {

	protected static final Logger LOGGER = Logger.getLogger(IclubSecurityDeviceService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSecurityDeviceDAO iclubSecurityDeviceDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubTrackerMasterDAO iclubTrackerMasterDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubSecurityDeviceModel model) {
		try {
			IclubSecurityDevice iCSd = new IclubSecurityDevice();

			iCSd.setSdId(model.getSdId());
			iCSd.setSdItemId(model.getSdItemId());
			iCSd.setSdContractNum(model.getSdContractNum());
			iCSd.setSdSerNum(model.getSdSerNum());
			iCSd.setSdCrtdDt(model.getSdCrtdDt());
			iCSd.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iCSd.setIclubTrackerMaster(model.getIclubTrackerMaster() != null ? iclubTrackerMasterDAO.findById(model.getIclubTrackerMaster()) : null);
			iCSd.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubSecurityDeviceDAO.save(iCSd);

			LOGGER.info("Save Success with ID :: " + iCSd.getSdId());

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
	public ResponseModel mod(IclubSecurityDeviceModel model) {
		try {
			IclubSecurityDevice iCSd = new IclubSecurityDevice();

			iCSd.setSdId(model.getSdId());
			iCSd.setSdItemId(model.getSdItemId());
			iCSd.setSdContractNum(model.getSdContractNum());
			iCSd.setSdSerNum(model.getSdSerNum());
			iCSd.setSdCrtdDt(model.getSdCrtdDt());
			iCSd.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iCSd.setIclubTrackerMaster(model.getIclubTrackerMaster() != null ? iclubTrackerMasterDAO.findById(model.getIclubTrackerMaster()) : null);
			iCSd.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubSecurityDeviceDAO.merge(iCSd);

			LOGGER.info("Merge Success with ID :: " + model.getSdId());

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
			iclubSecurityDeviceDAO.delete(iclubSecurityDeviceDAO.findById(id));
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
	public <T extends IclubSecurityDeviceModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSecurityDeviceDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSecurityDevice iCSd = (IclubSecurityDevice) object;

					IclubSecurityDeviceModel model = new IclubSecurityDeviceModel();
					model.setSdId(iCSd.getSdId());
					model.setSdItemId(iCSd.getSdItemId());
					model.setSdContractNum(iCSd.getSdContractNum());
					model.setSdSerNum(iCSd.getSdSerNum());
					model.setSdCrtdDt(iCSd.getSdCrtdDt());
					model.setIclubInsuranceItemType(iCSd.getIclubInsuranceItemType() != null ? (iCSd.getIclubInsuranceItemType().getIitId()) : null);
					model.setIclubTrackerMaster(iCSd.getIclubTrackerMaster() != null ? (iCSd.getIclubTrackerMaster().getTmId()) : null);
					model.setIclubPerson(iCSd.getIclubPerson() != null ? (iCSd.getIclubPerson().getPId()) : null);
					if (iCSd.getIclubVehicles() != null && iCSd.getIclubVehicles().size() > 0) {
						String[] vehicales = new String[iCSd.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iCSd.getIclubVehicles()) {
							vehicales[i] = vehicle.getVId();

							i++;
						}
						model.setIclubVehicles(vehicales);
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
	public <T extends IclubSecurityDeviceModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubSecurityDevice.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSecurityDevice iCSd = (IclubSecurityDevice) object;

					IclubSecurityDeviceModel model = new IclubSecurityDeviceModel();

					model.setSdId(iCSd.getSdId());
					model.setSdItemId(iCSd.getSdItemId());
					model.setSdContractNum(iCSd.getSdContractNum());
					model.setSdSerNum(iCSd.getSdSerNum());
					model.setSdCrtdDt(iCSd.getSdCrtdDt());
					model.setIclubInsuranceItemType(iCSd.getIclubInsuranceItemType() != null ? (iCSd.getIclubInsuranceItemType().getIitId()) : null);
					model.setIclubTrackerMaster(iCSd.getIclubTrackerMaster() != null ? (iCSd.getIclubTrackerMaster().getTmId()) : null);
					model.setIclubPerson(iCSd.getIclubPerson() != null ? (iCSd.getIclubPerson().getPId()) : null);
					if (iCSd.getIclubVehicles() != null && iCSd.getIclubVehicles().size() > 0) {
						String[] vehicales = new String[iCSd.getIclubVehicles().size()];
						int i = 0;
						for (IclubVehicle vehicle : iCSd.getIclubVehicles()) {
							vehicales[i] = vehicle.getVId();

							i++;
						}
						model.setIclubVehicles(vehicales);
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
	public IclubSecurityDeviceModel getById(@PathParam("id") String id) {
		IclubSecurityDeviceModel model = new IclubSecurityDeviceModel();
		try {
			IclubSecurityDevice bean = iclubSecurityDeviceDAO.findById(id);

			model.setSdId(bean.getSdId());
			model.setSdItemId(bean.getSdItemId());
			model.setSdContractNum(bean.getSdContractNum());
			model.setSdSerNum(bean.getSdSerNum());
			model.setSdCrtdDt(bean.getSdCrtdDt());
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitId()) : null);
			model.setIclubTrackerMaster(bean.getIclubTrackerMaster() != null ? (bean.getIclubTrackerMaster().getTmId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			if (bean.getIclubVehicles() != null && bean.getIclubVehicles().size() > 0) {
				String[] vehicales = new String[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubVehicle vehicle : bean.getIclubVehicles()) {
					vehicales[i] = vehicle.getVId();

					i++;
				}
				model.setIclubVehicles(vehicales);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubSecurityDeviceDAO getIclubSecurityDeviceDAO() {
		return iclubSecurityDeviceDAO;
	}

	public void setIclubSecurityDeviceDAO(IclubSecurityDeviceDAO iclubSecurityDeviceDAO) {
		this.iclubSecurityDeviceDAO = iclubSecurityDeviceDAO;
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

	public IclubTrackerMasterDAO getIclubTrackerMasterDAO() {
		return iclubTrackerMasterDAO;
	}

	public void setIclubTrackerMasterDAO(IclubTrackerMasterDAO iclubTrackerMasterDAO) {
		this.iclubTrackerMasterDAO = iclubTrackerMasterDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
