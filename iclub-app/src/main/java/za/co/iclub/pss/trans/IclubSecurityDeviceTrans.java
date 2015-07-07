package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSecurityDeviceBean;
import za.co.iclub.pss.model.ws.IclubSecurityDeviceModel;
import za.co.iclub.pss.orm.bean.IclubSecurityDevice;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubTrackerMasterDAO;

public class IclubSecurityDeviceTrans {
	
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubTrackerMasterDAO iclubTrackerMasterDAO;
	
	public IclubSecurityDeviceBean fromWStoUI(IclubSecurityDeviceModel model) {
		
		IclubSecurityDeviceBean bean = new IclubSecurityDeviceBean();
		
		bean.setSdId(model.getSdId());
		bean.setSdItemId(model.getSdItemId());
		bean.setSdContractNum(model.getSdContractNum());
		bean.setSdSerNum(model.getSdSerNum());
		bean.setSdCrtdDt(model.getSdCrtdDt());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setIclubTrackerMaster(model.getIclubTrackerMaster());
		bean.setTmRegNum(model.getTmRegNum());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubSecurityDeviceModel fromUItoWS(IclubSecurityDeviceBean bean) {
		
		IclubSecurityDeviceModel model = new IclubSecurityDeviceModel();
		
		model.setSdId(bean.getSdId());
		model.setSdItemId(bean.getSdItemId());
		model.setSdContractNum(bean.getSdContractNum());
		model.setSdSerNum(bean.getSdSerNum());
		model.setSdCrtdDt(bean.getSdCrtdDt());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIclubTrackerMaster(bean.getIclubTrackerMaster());
		model.setTmRegNum(bean.getTmRegNum());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubSecurityDeviceModel fromORMtoWS(IclubSecurityDevice bean) {
		
		IclubSecurityDeviceModel model = new IclubSecurityDeviceModel();
		
		model.setSdId(bean.getSdId());
		model.setSdItemId(bean.getSdItemId());
		model.setSdContractNum(bean.getSdContractNum());
		model.setSdSerNum(bean.getSdSerNum());
		model.setSdCrtdDt(bean.getSdCrtdDt());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitId()) : null);
		model.setIitLongDesc(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitLongDesc()) : null);
		model.setIclubTrackerMaster(bean.getIclubTrackerMaster() != null ? (bean.getIclubTrackerMaster().getTmId()) : null);
		model.setTmRegNum(bean.getIclubTrackerMaster() != null ? (bean.getIclubTrackerMaster().getTmRegNum()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubSecurityDevice fromWStoORM(IclubSecurityDeviceModel model) {
		
		IclubSecurityDevice bean = new IclubSecurityDevice();
		
		bean.setSdId(model.getSdId());
		bean.setSdItemId(model.getSdItemId());
		bean.setSdContractNum(model.getSdContractNum());
		bean.setSdSerNum(model.getSdSerNum());
		bean.setSdCrtdDt(model.getSdCrtdDt());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setIclubTrackerMaster(model.getIclubTrackerMaster() != null ? iclubTrackerMasterDAO.findById(model.getIclubTrackerMaster()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
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
}
