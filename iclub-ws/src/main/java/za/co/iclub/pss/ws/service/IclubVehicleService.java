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

import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPurposeTypeDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityDeviceDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityMasterDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleMasterDAO;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubVehicleService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubVehicleService {

	protected static final Logger LOGGER = Logger.getLogger(IclubVehicleService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubVehicleDAO iclubVehicleDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubPurposeTypeDAO iclubPurposeTypeDAO;
	private IclubSecurityMasterDAO iclubSecurityMasterDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubSecurityDeviceDAO iclubSecurityDeviceDAO;
	private IclubVehicleMasterDAO iclubVehicleMasterDAO;
	private IclubDriverDAO iclubDriverDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubVehicleModel model) {
		try {
			IclubVehicle iCV = new IclubVehicle();

			iCV.setVId(model.getVId());
			iCV.setVOwner(model.getVOwner());
			iCV.setVGearLockYn(model.getVGearLockYn());
			iCV.setVImmYn(model.getVImmYn());
			iCV.setVConcessReason(model.getVConcessReason());
			iCV.setVConcessPrct(model.getVConcessPrct());
			iCV.setVInsuredValue(model.getVInsuredValue());
			iCV.setVYear(model.getVYear());
			iCV.setVDdLong(model.getVDdLong());
			iCV.setVDdLat(model.getVDdLat());
			iCV.setVDdArea(model.getVDdArea());
			iCV.setVOnLong(model.getVOnLong());
			iCV.setVOnLat(model.getVOnLat());
			iCV.setVOnArea(model.getVOnArea());
			iCV.setVOdometer(model.getVOdometer());
			iCV.setVCrtdDt(model.getVCrtdDt());
			iCV.setVRegNum(model.getVRegNum());
			iCV.setVEngineNr(model.getVEngineNr());
			iCV.setVVin(model.getVVin());
			iCV.setVNoclaimYrs(model.getVNoclaimYrs());

			iCV.setIclubPurposeType(model.getIclubPurposeType()!=null ? iclubPurposeTypeDAO.findById(model.getIclubPurposeType()):null);
			iCV.setIclubSecurityMaster(model.getIclubSecurityMaster()!=null ?iclubSecurityMasterDAO.findById(model.getIclubSecurityMaster()):null);
			iCV.setIclubPerson(model.getIclubPerson()!=null  ? iclubPersonDAO.findById(model.getIclubPerson()):null);
			iCV.setIclubDriver(model.getIclubDriver()!=null ? iclubDriverDAO.findById(model.getIclubDriver()):null);
			iCV.setIclubSecurityDevice(model.getIclubSecurityDevice()!=null ? iclubSecurityDeviceDAO.findById(model.getIclubSecurityDevice()):null);
			iCV.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId()!=null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVDdAccessTypeId()):null);
			iCV.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId()!=null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVOnAccessTypeId()):null);
			iCV.setIclubVehicleMaster(model.getIclubVehicleMaster()!=null ?iclubVehicleMasterDAO.findById(model.getIclubVehicleMaster()):null);

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
			IclubVehicle iCV = new IclubVehicle();

			iCV.setVId(model.getVId());
			iCV.setVOwner(model.getVOwner());
			iCV.setVGearLockYn(model.getVGearLockYn());
			iCV.setVImmYn(model.getVImmYn());
			iCV.setVConcessReason(model.getVConcessReason());
			iCV.setVConcessPrct(model.getVConcessPrct());
			iCV.setVInsuredValue(model.getVInsuredValue());
			iCV.setVYear(model.getVYear());
			iCV.setVDdLong(model.getVDdLong());
			iCV.setVDdLat(model.getVDdLat());
			iCV.setVDdArea(model.getVDdArea());
			iCV.setVOnLong(model.getVOnLong());
			iCV.setVOnLat(model.getVOnLat());
			iCV.setVOnArea(model.getVOnArea());
			iCV.setVOdometer(model.getVOdometer());
			iCV.setVCrtdDt(model.getVCrtdDt());
			iCV.setVRegNum(model.getVRegNum());
			iCV.setVEngineNr(model.getVEngineNr());
			iCV.setVVin(model.getVVin());
			iCV.setVNoclaimYrs(model.getVNoclaimYrs());

			iCV.setIclubPurposeType(iclubPurposeTypeDAO.findById(model.getIclubPurposeType()));
			iCV.setIclubSecurityMaster(iclubSecurityMasterDAO.findById(model.getIclubSecurityMaster()));
			iCV.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCV.setIclubDriver(iclubDriverDAO.findById(model.getIclubDriver()));
			iCV.setIclubSecurityDevice(iclubSecurityDeviceDAO.findById(model.getIclubSecurityDevice()));
			iCV.setIclubAccessTypeByVDdAccessTypeId(iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVDdAccessTypeId()));
			iCV.setIclubAccessTypeByVOnAccessTypeId(iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVOnAccessTypeId()));
			iCV.setIclubVehicleMaster(iclubVehicleMasterDAO.findById(model.getIclubVehicleMaster()));

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

			for (Object object : batmod) {
				IclubVehicle iCV = (IclubVehicle) object;

				IclubVehicleModel model = new IclubVehicleModel();

				model.setVId(iCV.getVId());
				model.setVOwner(iCV.getVOwner());
				model.setVGearLockYn(iCV.getVGearLockYn());
				model.setVImmYn(iCV.getVImmYn());
				model.setVConcessReason(iCV.getVConcessReason());
				model.setVConcessPrct(iCV.getVConcessPrct());
				model.setVInsuredValue(iCV.getVInsuredValue());
				model.setVYear(iCV.getVYear());
				model.setVDdLong(iCV.getVDdLong());
				model.setVDdLat(iCV.getVDdLat());
				model.setVDdArea(iCV.getVDdArea());
				model.setVOnLong(iCV.getVOnLong());
				model.setVOnLat(iCV.getVOnLat());
				model.setVOnArea(iCV.getVOnArea());
				model.setVOdometer(iCV.getVOdometer());
				model.setVCrtdDt(iCV.getVCrtdDt());
				model.setVRegNum(iCV.getVRegNum());
				model.setVEngineNr(iCV.getVEngineNr());
				model.setVVin(iCV.getVVin());
				model.setVNoclaimYrs(iCV.getVNoclaimYrs());
				model.setIclubVehicleMaster(iCV.getIclubVehicleMaster() != null ? (iCV.getIclubVehicleMaster().getVmId()) : null);
				model.setIclubPurposeType(iCV.getIclubPurposeType() != null ? (iCV.getIclubPurposeType().getPtId()) : null);
				model.setIclubSecurityMaster(iCV.getIclubSecurityMaster() != null ? (iCV.getIclubSecurityMaster().getSmId()) : null);
				model.setIclubPerson(iCV.getIclubPerson() != null ? (iCV.getIclubPerson().getPId()) : null);
				model.setIclubDriver(iCV.getIclubDriver() != null ? (iCV.getIclubDriver().getDId()) : null);
				model.setIclubSecurityDevice(iCV.getIclubSecurityDevice() != null ? (iCV.getIclubSecurityDevice().getSdId()) : null);
				model.setIclubAccessTypeByVDdAccessTypeId(iCV.getIclubAccessTypeByVDdAccessTypeId() != null ? (iCV.getIclubAccessTypeByVDdAccessTypeId().getAtId()) : null);
				model.setIclubAccessTypeByVOnAccessTypeId(iCV.getIclubAccessTypeByVOnAccessTypeId() != null ? (iCV.getIclubAccessTypeByVOnAccessTypeId().getAtId()) : null);

				ret.add((T) model);
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
			List batmod = iclubVehicleDAO.findByUser(user);

			for (Object object : batmod) {
				IclubVehicle iCV = (IclubVehicle) object;

				IclubVehicleModel model = new IclubVehicleModel();

				model.setVId(iCV.getVId());
				model.setVOwner(iCV.getVOwner());
				model.setVGearLockYn(iCV.getVGearLockYn());
				model.setVImmYn(iCV.getVImmYn());
				model.setVConcessReason(iCV.getVConcessReason());
				model.setVConcessPrct(iCV.getVConcessPrct());
				model.setVInsuredValue(iCV.getVInsuredValue());
				model.setVYear(iCV.getVYear());
				model.setVDdLong(iCV.getVDdLong());
				model.setVDdLat(iCV.getVDdLat());
				model.setVDdArea(iCV.getVDdArea());
				model.setVOnLong(iCV.getVOnLong());
				model.setVOnLat(iCV.getVOnLat());
				model.setVOnArea(iCV.getVOnArea());
				model.setVOdometer(iCV.getVOdometer());
				model.setVCrtdDt(iCV.getVCrtdDt());
				model.setVRegNum(iCV.getVRegNum());
				model.setVEngineNr(iCV.getVEngineNr());
				model.setVVin(iCV.getVVin());
				model.setVNoclaimYrs(iCV.getVNoclaimYrs());
				model.setIclubPurposeType(iCV.getIclubPurposeType() != null ? (iCV.getIclubPurposeType().getPtId()) : null);
				model.setIclubSecurityMaster(iCV.getIclubSecurityMaster() != null ? (iCV.getIclubSecurityMaster().getSmId()) : null);
				model.setIclubPerson(iCV.getIclubPerson() != null ? (iCV.getIclubPerson().getPId()) : null);
				model.setIclubVehicleMaster(iCV.getIclubVehicleMaster() != null ? (iCV.getIclubVehicleMaster().getVmId()) : null);
				model.setIclubDriver(iCV.getIclubDriver() != null ? (iCV.getIclubDriver().getDId()) : null);
				model.setIclubSecurityDevice(iCV.getIclubSecurityDevice() != null ? (iCV.getIclubSecurityDevice().getSdId()) : null);
				model.setIclubAccessTypeByVDdAccessTypeId(iCV.getIclubAccessTypeByVDdAccessTypeId() != null ? (iCV.getIclubAccessTypeByVDdAccessTypeId().getAtId()) : null);
				model.setIclubAccessTypeByVOnAccessTypeId(iCV.getIclubAccessTypeByVOnAccessTypeId() != null ? (iCV.getIclubAccessTypeByVOnAccessTypeId().getAtId()) : null);

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
	public IclubVehicleModel getById(@PathParam("id") String id) {
		IclubVehicleModel model = new IclubVehicleModel();
		try {
			IclubVehicle bean = iclubVehicleDAO.findById(id);

			model.setVId(bean.getVId());
			model.setVOwner(bean.getVOwner());
			model.setVGearLockYn(bean.getVGearLockYn());
			model.setVImmYn(bean.getVImmYn());
			model.setVConcessReason(bean.getVConcessReason());
			model.setVConcessPrct(bean.getVConcessPrct());
			model.setVInsuredValue(bean.getVInsuredValue());
			model.setVYear(bean.getVYear());
			model.setVDdLong(bean.getVDdLong());
			model.setVDdLat(bean.getVDdLat());
			model.setVDdArea(bean.getVDdArea());
			model.setVOnLong(bean.getVOnLong());
			model.setVOnLat(bean.getVOnLat());
			model.setVOnArea(bean.getVOnArea());
			model.setVOdometer(bean.getVOdometer());
			model.setVCrtdDt(bean.getVCrtdDt());
			model.setVRegNum(bean.getVRegNum());
			model.setVEngineNr(bean.getVEngineNr());
			model.setVVin(bean.getVVin());
			model.setVNoclaimYrs(bean.getVNoclaimYrs());
			model.setIclubVehicleMaster(bean.getIclubVehicleMaster() != null ? (bean.getIclubVehicleMaster().getVmId()) : null);
			model.setIclubPurposeType(bean.getIclubPurposeType() != null ? (bean.getIclubPurposeType().getPtId()) : null);
			model.setIclubSecurityMaster(bean.getIclubSecurityMaster() != null ? (bean.getIclubSecurityMaster().getSmId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			model.setIclubDriver(bean.getIclubDriver() != null ? (bean.getIclubDriver().getDId()) : null);
			model.setIclubSecurityDevice(bean.getIclubSecurityDevice() != null ? (bean.getIclubSecurityDevice().getSdId()) : null);
			model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId() != null ? (bean.getIclubAccessTypeByVDdAccessTypeId().getAtId()) : null);
			model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId() != null ? (bean.getIclubAccessTypeByVOnAccessTypeId().getAtId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
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

	public IclubPurposeTypeDAO getIclubPurposeTypeDAO() {
		return iclubPurposeTypeDAO;
	}

	public void setIclubPurposeTypeDAO(IclubPurposeTypeDAO iclubPurposeTypeDAO) {
		this.iclubPurposeTypeDAO = iclubPurposeTypeDAO;
	}

	public IclubSecurityMasterDAO getIclubSecurityMasterDAO() {
		return iclubSecurityMasterDAO;
	}

	public void setIclubSecurityMasterDAO(IclubSecurityMasterDAO iclubSecurityMasterDAO) {
		this.iclubSecurityMasterDAO = iclubSecurityMasterDAO;
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

}
