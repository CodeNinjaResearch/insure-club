package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubVehicleMasterBean;
import za.co.iclub.pss.model.ws.IclubVehicleMasterModel;
import za.co.iclub.pss.orm.bean.IclubVehicleMaster;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubVehicleMasterTrans {
	
	public static IclubVehicleMasterBean fromWStoUI(IclubVehicleMasterModel model) {
		
		IclubVehicleMasterBean bean = new IclubVehicleMasterBean();
		
		bean.setVmId(model.getVmId());
		bean.setVmMake(model.getVmMake());
		bean.setVmModel(model.getVmModel());
		bean.setVmMrktRate(model.getVmMrktRate());
		bean.setVmOrigRate(model.getVmOrigRate());
		bean.setVmRetRate(model.getVmRetRate());
		bean.setVmProdDt(model.getVmProdDt());
		bean.setVmCrtdDt(model.getVmCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		return bean;
	}
	
	public static IclubVehicleMasterModel fromUItoWS(IclubVehicleMasterBean bean) {
		
		IclubVehicleMasterModel model = new IclubVehicleMasterModel();
		
		model.setVmId(bean.getVmId());
		model.setVmMake(bean.getVmMake());
		model.setVmModel(bean.getVmModel());
		model.setVmMrktRate(bean.getVmMrktRate());
		model.setVmOrigRate(bean.getVmOrigRate());
		model.setVmRetRate(bean.getVmRetRate());
		model.setVmProdDt(bean.getVmProdDt());
		model.setVmCrtdDt(bean.getVmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubVehicleMasterModel fromORMtoWS(IclubVehicleMaster bean) {
		IclubVehicleMasterModel model = new IclubVehicleMasterModel();
		
		model.setVmId(bean.getVmId());
		model.setVmMake(bean.getVmMake());
		model.setVmModel(bean.getVmModel());
		model.setVmMrktRate(bean.getVmMrktRate());
		model.setVmOrigRate(bean.getVmOrigRate());
		model.setVmRetRate(bean.getVmRetRate());
		model.setVmProdDt(bean.getVmProdDt());
		model.setVmCrtdDt(bean.getVmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		return model;
	}
	
	public static IclubVehicleMaster fromWStoORM(IclubVehicleMasterModel model, IclubPersonDAO iclubPersonDAO) {
		
		IclubVehicleMaster bean = new IclubVehicleMaster();
		
		bean.setVmId(model.getVmId());
		bean.setVmMake(model.getVmMake());
		bean.setVmModel(model.getVmModel());
		bean.setVmMrktRate(model.getVmMrktRate());
		bean.setVmOrigRate(model.getVmOrigRate());
		bean.setVmRetRate(model.getVmRetRate());
		bean.setVmProdDt(model.getVmProdDt());
		bean.setVmCrtdDt(model.getVmCrtdDt());
		bean.setVmRatePrct(model.getVmRatePrct());
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
}
