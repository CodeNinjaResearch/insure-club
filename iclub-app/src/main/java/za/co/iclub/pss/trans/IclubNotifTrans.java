package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubNotifBean;
import za.co.iclub.pss.model.ws.IclubNotifModel;
import za.co.iclub.pss.orm.bean.IclubNotif;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubNotifTrans {
	
	public static IclubNotifBean fromWStoUI(IclubNotifModel model) {
		
		IclubNotifBean bean = new IclubNotifBean();
		
		bean.setNId(model.getNId());
		bean.setNTitle(model.getNTitle());
		bean.setNBody(model.getNBody());
		bean.setNFromAddr(model.getNFromAddr());
		bean.setNToList(model.getNToList());
		bean.setNCrtdDt(model.getNCrtdDt());
		bean.setNtLongDesc(model.getNtLongDesc());
		bean.setIclubNotificationType(model.getIclubNotificationType());
		bean.setNStatus(model.getNStatus());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubNotifModel fromUItoWS(IclubNotifBean bean) {
		
		IclubNotifModel model = new IclubNotifModel();
		
		model.setNId(bean.getNId());
		model.setNTitle(bean.getNTitle());
		model.setNBody(bean.getNBody());
		model.setNFromAddr(bean.getNFromAddr());
		model.setNToList(bean.getNToList());
		model.setNCrtdDt(bean.getNCrtdDt());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setIclubNotificationType(bean.getIclubNotificationType());
		model.setNStatus(bean.getNStatus());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubNotifModel fromORMtoWS(IclubNotif bean) {
		
		IclubNotifModel model = new IclubNotifModel();
		
		model.setNId(bean.getNId());
		model.setNTitle(bean.getNTitle());
		model.setNBody(bean.getNBody());
		model.setNFromAddr(bean.getNFromAddr());
		model.setNToList(bean.getNToList());
		model.setNCrtdDt(bean.getNCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setIclubNotificationType(bean.getIclubNotificationType() != null ? bean.getIclubNotificationType().getNtId() : null);
		model.setNtLongDesc(bean.getIclubNotificationType() != null ? bean.getIclubNotificationType().getNtLongDesc() : null);
		model.setNStatus(bean.getNStatus());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubNotif fromWStoORM(IclubNotifModel model, IclubPersonDAO iclubPersonDAO, IclubNotificationTypeDAO iclubNotificationTypeDAO) {
		
		IclubNotif bean = new IclubNotif();
		
		bean.setNId(model.getNId());
		bean.setNTitle(model.getNTitle());
		bean.setNBody(model.getNBody());
		bean.setNFromAddr(model.getNFromAddr());
		bean.setNToList(model.getNToList());
		bean.setNCrtdDt(model.getNCrtdDt());
		bean.setIclubNotificationType(iclubNotificationTypeDAO.findById(model.getIclubNotificationType()));
		bean.setNStatus(model.getNStatus());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
		
		return bean;
	}
}
