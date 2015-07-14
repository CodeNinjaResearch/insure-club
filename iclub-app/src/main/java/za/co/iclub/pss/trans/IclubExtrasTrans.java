package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubExtrasBean;
import za.co.iclub.pss.model.ws.IclubExtrasModel;
import za.co.iclub.pss.orm.bean.IclubExtras;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubExtrasTrans {
	
	public static IclubExtrasBean fromWStoUI(IclubExtrasModel model) {
		IclubExtrasBean bean = new IclubExtrasBean();
		bean.setEId(model.getEId());
		bean.setEDesc(model.getEDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setECrtdDt(model.getECrtdDt());
		bean.setEStatus(model.getEStatus());
		return bean;
	}
	
	public static IclubExtrasModel fromUItoWS(IclubExtrasBean bean) {
		IclubExtrasModel model = new IclubExtrasModel();
		model.setEId(bean.getEId());
		model.setEDesc(bean.getEDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setECrtdDt(bean.getECrtdDt());
		model.setEStatus(bean.getEStatus());
		return model;
	}
	
	public static IclubExtrasModel fromORMtoWS(IclubExtras bean) {
		IclubExtrasModel model = new IclubExtrasModel();
		model.setEId(bean.getEId());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() : null);
		model.setEDesc(bean.getEDesc());
		model.setECrtdDt(bean.getECrtdDt());
		model.setEStatus(bean.getEStatus());
		return model;
	}
	
	public static IclubExtras fromWStoORM(IclubExtrasModel model, IclubPersonDAO iclubPersonDAO) {
		
		IclubExtras acctype = new IclubExtras();
		
		acctype.setEId(model.getEId());
		acctype.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		acctype.setEDesc(model.getEDesc());
		acctype.setECrtdDt(model.getECrtdDt());
		acctype.setEStatus(model.getEStatus());
		
		return acctype;
	}
	
}
