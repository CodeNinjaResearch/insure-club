package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubVehicleBean;
import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityDeviceDAO;
import za.co.iclub.pss.orm.dao.IclubVehSecTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleMasterDAO;

public class IclubVehicleTrans {
	public static IclubVehicleBean fromWStoUI(IclubVehicleModel model) {
		
		IclubVehicleBean bean = new IclubVehicleBean();
		
		bean.setVId(model.getVId());
		bean.setVOwner(model.getVOwner());
		bean.setVGearLockYn(model.getVGearLockYn());
		bean.setVImmYn(model.getVImmYn());
		bean.setVConcessReason(model.getVConcessReason());
		bean.setVConcessPrct(model.getVConcessPrct());
		bean.setVInsuredValue(model.getVInsuredValue());
		bean.setVYear(model.getVYear());
		bean.setVDdLong(model.getVDdLong());
		bean.setVDdLat(model.getVDdLat());
		bean.setVDdArea(model.getVDdArea());
		bean.setVOnLong(model.getVOnLong());
		bean.setVOnLat(model.getVOnLat());
		bean.setVOnArea(model.getVOnArea());
		bean.setVCompYrs(model.getVCompYrs());
		bean.setVOdometer(model.getVOdometer());
		bean.setVCrtdDt(model.getVCrtdDt());
		bean.setVRegNum(model.getVRegNum());
		bean.setVEngineNr(model.getVEngineNr());
		bean.setVVin(model.getVVin());
		bean.setVNoclaimYrs(model.getVNoclaimYrs());
		bean.setIclubCoverType(model.getIclubCoverType());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setIclubVehicleMaster(model.getIclubVehicleMaster());
		bean.setIclubVehUsageType(model.getIclubVehUsageType());
		bean.setVutLongDesc(model.getVutLongDesc());
		bean.setIclubVehSecType(model.getIclubVehSecType());
		bean.setVstLongDesc(model.getVstLongDesc());
		bean.setIclubDriver(model.getIclubDriver());
		bean.setDName(model.getDName());
		bean.setIclubSecurityDevice(model.getIclubSecurityDevice());
		bean.setSdSerNum(model.getSdSerNum());
		bean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
		bean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());
		bean.setAtDdLongDesc(model.getAtDdLongDesc());
		bean.setAtOnLongDesc(model.getAtOnLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubVehicleModel fromUItoWS(IclubVehicleBean bean) {
		
		IclubVehicleModel model = new IclubVehicleModel();
		
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
		model.setVCompYrs(bean.getVCompYrs());
		model.setVOdometer(bean.getVOdometer());
		model.setVCrtdDt(bean.getVCrtdDt());
		model.setVRegNum(bean.getVRegNum());
		model.setVEngineNr(bean.getVEngineNr());
		model.setVVin(bean.getVVin());
		model.setVNoclaimYrs(bean.getVNoclaimYrs());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
		model.setIclubVehUsageType(bean.getIclubVehUsageType());
		model.setVutLongDesc(bean.getVutLongDesc());
		model.setIclubVehSecType(bean.getIclubVehSecType());
		model.setVstLongDesc(bean.getVstLongDesc());
		model.setIclubDriver(bean.getIclubDriver());
		model.setDName(bean.getDName());
		model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
		model.setSdSerNum(bean.getSdSerNum());
		model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
		model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());
		model.setAtDdLongDesc(bean.getAtDdLongDesc());
		model.setAtOnLongDesc(bean.getAtOnLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubVehicleModel fromORMtoWS(IclubVehicle bean) {
		
		IclubVehicleModel model = new IclubVehicleModel();
		
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
		model.setVCompYrs(bean.getVCompYrs());
		model.setVOdometer(bean.getVOdometer());
		model.setVCrtdDt(bean.getVCrtdDt());
		model.setVRegNum(bean.getVRegNum());
		model.setVEngineNr(bean.getVEngineNr());
		model.setVVin(bean.getVVin());
		model.setVNoclaimYrs(bean.getVNoclaimYrs());
		model.setIclubCoverType(bean.getIclubCoverType() != null ? bean.getIclubCoverType().getCtId() : null);
		model.setCtLongDesc(bean.getIclubCoverType() != null ? bean.getIclubCoverType().getCtLongDesc() : null);
		model.setIclubVehicleMaster(bean.getIclubVehicleMaster() != null ? (bean.getIclubVehicleMaster().getVmId()) : null);
		model.setIclubVehUsageType(bean.getIclubVehUsageType() != null ? (bean.getIclubVehUsageType().getVutId()) : null);
		model.setVutLongDesc(bean.getIclubVehUsageType() != null ? (bean.getIclubVehUsageType().getVutLongDesc()) : null);
		model.setIclubVehSecType(bean.getIclubVehSecType() != null ? (bean.getIclubVehSecType().getVstId()) : null);
		model.setVstLongDesc(bean.getIclubVehSecType() != null ? (bean.getIclubVehSecType().getVstLongDesc()) : null);
		model.setIclubDriver(bean.getIclubDriver() != null ? (bean.getIclubDriver().getDId()) : null);
		model.setDName(bean.getIclubDriver() != null ? (bean.getIclubDriver().getDName()) : null);
		model.setIclubSecurityDevice(bean.getIclubSecurityDevice() != null ? (bean.getIclubSecurityDevice().getSdId()) : null);
		model.setSdSerNum(bean.getIclubSecurityDevice() != null ? (bean.getIclubSecurityDevice().getSdSerNum()) : null);
		model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId() != null ? (bean.getIclubAccessTypeByVDdAccessTypeId().getAtId()) : null);
		model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId() != null ? (bean.getIclubAccessTypeByVOnAccessTypeId().getAtId()) : null);
		model.setAtDdLongDesc(bean.getIclubAccessTypeByVDdAccessTypeId() != null ? (bean.getIclubAccessTypeByVDdAccessTypeId().getAtLongDesc()) : null);
		model.setAtOnLongDesc(bean.getIclubAccessTypeByVOnAccessTypeId() != null ? (bean.getIclubAccessTypeByVOnAccessTypeId().getAtLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubVehicle fromWStoORM(IclubVehicleModel model, IclubVehicleMasterDAO iclubVehicleMasterDAO, IclubAccessTypeDAO iclubAccessTypeDAO, IclubDriverDAO iclubDriverDAO, IclubSecurityDeviceDAO iclubSecurityDeviceDAO, IclubVehSecTypeDAO iclubVehSecTypeDAO, IclubPersonDAO iclubPersonDAO, IclubCoverTypeDAO iclubCoverTypeDAO, IclubVehUsageTypeDAO iclubVehUsageTypeDAO) {
		
		IclubVehicle bean = new IclubVehicle();
		
		bean.setVId(model.getVId());
		bean.setVOwner(model.getVOwner());
		bean.setVGearLockYn(model.getVGearLockYn());
		bean.setVImmYn(model.getVImmYn());
		bean.setVConcessReason(model.getVConcessReason());
		bean.setVConcessPrct(model.getVConcessPrct());
		bean.setVInsuredValue(model.getVInsuredValue());
		bean.setVYear(model.getVYear());
		bean.setVDdLong(model.getVDdLong());
		bean.setVCompYrs(model.getVCompYrs());
		bean.setVDdLat(model.getVDdLat());
		bean.setVDdArea(model.getVDdArea());
		bean.setVOnLong(model.getVOnLong());
		bean.setVOnLat(model.getVOnLat());
		bean.setVOnArea(model.getVOnArea());
		bean.setVOdometer(model.getVOdometer());
		bean.setVCrtdDt(model.getVCrtdDt());
		bean.setVRegNum(model.getVRegNum());
		bean.setVEngineNr(model.getVEngineNr());
		bean.setVVin(model.getVVin());
		bean.setVNoclaimYrs(model.getVNoclaimYrs());
		bean.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
		bean.setIclubVehUsageType(model.getIclubVehUsageType() != null ? iclubVehUsageTypeDAO.findById(model.getIclubVehUsageType()) : null);
		bean.setIclubVehSecType(model.getIclubVehSecType() != null ? iclubVehSecTypeDAO.findById(model.getIclubVehSecType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubDriver(model.getIclubDriver() != null ? iclubDriverDAO.findById(model.getIclubDriver()) : null);
		bean.setIclubSecurityDevice(model.getIclubSecurityDevice() != null ? iclubSecurityDeviceDAO.findById(model.getIclubSecurityDevice()) : null);
		bean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVDdAccessTypeId()) : null);
		bean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVOnAccessTypeId()) : null);
		bean.setIclubVehicleMaster(model.getIclubVehicleMaster() != null ? iclubVehicleMasterDAO.findById(model.getIclubVehicleMaster()) : null);
		
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
}
