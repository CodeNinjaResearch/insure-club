package za.co.iclub.pss.trans;

import org.springframework.beans.factory.annotation.Autowired;

import za.co.iclub.pss.model.ui.IclubConfigBean;
import za.co.iclub.pss.model.ws.IclubConfigModel;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubConfigTrans {
	
	public static IclubConfigBean fromWStoUI(IclubConfigModel model) {
		
		IclubConfigBean bean = new IclubConfigBean();
		
		bean.setCId(model.getCId());
		bean.setCKey(model.getCKey());
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setCValue(model.getCValue());
		bean.setCStatus(model.getCStatus());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubConfigModel fromUItoWS(IclubConfigBean bean) {
		
		IclubConfigModel model = new IclubConfigModel();
		
		model.setCId(bean.getCId());
		model.setCKey(bean.getCKey());
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setCValue(bean.getCValue());
		model.setCStatus(bean.getCStatus());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubConfigModel fromORMtoWS(IclubConfig bean) {
		
		IclubConfigModel model = new IclubConfigModel();
		
		model.setCId(bean.getCId());
		model.setCKey(bean.getCKey());
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setCValue(bean.getCValue());
		model.setCStatus(bean.getCStatus());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubConfig fromWStoORM(IclubConfigModel model, IclubPersonDAO iclubPersonDAO) {
		
		IclubConfig bean = new IclubConfig();
		bean.setCId(model.getCId());
		bean.setCKey(model.getCKey());
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setCValue(model.getCValue());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
		bean.setCStatus(model.getCStatus());
		return bean;
	}
	
}
