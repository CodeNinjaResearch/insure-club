package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubEventBean;
import za.co.iclub.pss.model.ws.IclubEventModel;
import za.co.iclub.pss.orm.bean.IclubEvent;
import za.co.iclub.pss.orm.dao.IclubEventTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubEventTrans {
	
	public static IclubEventBean fromWStoUI(IclubEventModel model) {
		
		IclubEventBean bean = new IclubEventBean();
		
		bean.setEId(model.getEId());
		bean.setECrtdDt(model.getECrtdDt());
		bean.setEDesc(model.getEDesc());
		bean.setIclubEventType(model.getIclubEventType());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		return bean;
	}
	
	public static IclubEventModel fromUItoWS(IclubEventBean bean) {
		
		IclubEventModel model = new IclubEventModel();
		
		model.setEId(bean.getEId());
		model.setECrtdDt(bean.getECrtdDt());
		model.setEDesc(bean.getEDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setIclubEventType(bean.getIclubEventType());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubEventModel fromORMtoWS(IclubEvent bean) {
		
		IclubEventModel model = new IclubEventModel();
		
		model.setEId(bean.getEId());
		model.setECrtdDt(bean.getECrtdDt());
		model.setEDesc(bean.getEDesc());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setIclubEventType(bean.getIclubEventType() != null ? bean.getIclubEventType().getEtId() : null);
		model.setEtLongDesc(bean.getIclubEventType() != null ? bean.getIclubEventType().getEtLongDesc() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubEvent fromWStoORM(IclubEventModel model, IclubPersonDAO iclubPersonDAO, IclubEventTypeDAO iclubEventTypeDAO) {
		
		IclubEvent bean = new IclubEvent();
		
		bean.setEId(model.getEId());
		bean.setECrtdDt(model.getECrtdDt());
		bean.setEDesc(model.getEDesc());
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubEventType(model.getIclubEventType() != null ? iclubEventTypeDAO.findById(model.getIclubEventType()) : null);
		
		return bean;
	}
	
}
