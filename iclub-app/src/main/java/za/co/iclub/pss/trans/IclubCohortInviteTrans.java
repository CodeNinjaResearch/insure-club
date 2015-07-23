package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortInviteBean;
import za.co.iclub.pss.model.ws.IclubCohortInviteModel;
import za.co.iclub.pss.orm.bean.IclubCohortInvite;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCohortInviteTrans {
	
	public static IclubCohortInviteBean fromWStoUI(IclubCohortInviteModel model) {
		
		IclubCohortInviteBean bean = new IclubCohortInviteBean();
		
		bean.setCiId(model.getCiId());
		bean.setIclubCohort(model.getIclubCohort());
		bean.setCEmail(model.getCEmail());
		bean.setCiCrtdDt(model.getCiCrtdDt());
		bean.setIclubNotificationType(model.getIclubNotificationType());
		bean.setNtLongDesc(model.getNtLongDesc());
		bean.setCiInviteAcceptYn(model.getCiInviteAcceptYn());
		bean.setCiInviteUri(model.getCiInviteUri());
		bean.setCiInviteFName(model.getCiInviteFName());
		bean.setCiInviteLName(model.getCiInviteLName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setCiInviteSendStatus(model.getCiInviteSendStatus());
		
		return bean;
	}
	
	public static IclubCohortInviteModel fromUItoWS(IclubCohortInviteBean bean) {
		
		IclubCohortInviteModel model = new IclubCohortInviteModel();
		
		model.setCiId(bean.getCiId());
		model.setIclubCohort(bean.getIclubCohort());
		model.setCEmail(bean.getCEmail());
		model.setCiCrtdDt(bean.getCiCrtdDt());
		model.setIclubNotificationType(bean.getIclubNotificationType());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setCiInviteAcceptYn(bean.getCiInviteAcceptYn());
		model.setCiInviteUri(bean.getCiInviteUri());
		model.setCiInviteFName(bean.getCiInviteFName());
		model.setCiInviteLName(bean.getCiInviteLName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setCiInviteSendStatus(bean.getCiInviteSendStatus());
		
		return model;
	}
	
	public static IclubCohortInviteModel fromORMtoWS(IclubCohortInvite bean) {
		
		IclubCohortInviteModel model = new IclubCohortInviteModel();
		
		model.setCiId(bean.getCiId());
		model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
		model.setCEmail(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCEmail() : null);
		model.setCiCrtdDt(bean.getCiCrtdDt());
		model.setIclubNotificationType((bean.getIclubNotificationType()) != null ? bean.getIclubNotificationType().getNtId() : null);
		model.setNtLongDesc((bean.getIclubNotificationType()) != null ? bean.getIclubNotificationType().getNtLongDesc() : null);
		model.setCiInviteAcceptYn(bean.getCiInviteAcceptYn());
		model.setCiInviteUri(bean.getCiInviteUri());
		model.setCiInviteFName(bean.getCiInviteFName());
		model.setCiInviteLName(bean.getCiInviteLName());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		model.setCiInviteSendStatus(bean.getCiInviteSendStatus());
		
		return model;
	}
	
	public static IclubCohortInvite fromWStoORM(IclubCohortInviteModel model, IclubNotificationTypeDAO iclubNotificationTypeDAO, IclubCohortDAO iclubCohortDAO, IclubPersonDAO iclubPersonDAO) {
		
		IclubCohortInvite bean = new IclubCohortInvite();
		
		bean.setCiId(model.getCiId());
		bean.setIclubCohort(model.getIclubCohort() != null ? iclubCohortDAO.findById(model.getIclubCohort()) : null);
		bean.setIclubNotificationType(model.getIclubNotificationType() != null ? iclubNotificationTypeDAO.findById(model.getIclubNotificationType()) : null);
		bean.setCiCrtdDt(model.getCiCrtdDt());
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setCiInviteAcceptYn(model.getCiInviteAcceptYn());
		bean.setCiInviteUri(model.getCiInviteUri());
		bean.setCiInviteFName(model.getCiInviteFName());
		bean.setCiInviteLName(model.getCiInviteLName());
		bean.setCiInviteSendStatus(model.getCiInviteSendStatus());
		
		return bean;
	}
	
}
