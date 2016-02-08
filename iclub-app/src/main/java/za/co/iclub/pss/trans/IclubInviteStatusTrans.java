package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubInviteStatusBean;
import za.co.iclub.pss.model.ws.IclubInviteStatusModel;
import za.co.iclub.pss.orm.bean.IclubInviteStatus;

public class IclubInviteStatusTrans {

	public static IclubInviteStatusBean fromWStoUI(IclubInviteStatusModel model) {
		IclubInviteStatusBean bean = new IclubInviteStatusBean();
		bean.setIsId(model.getIsId());
		bean.setIsLongDesc(model.getIsLongDesc());
		bean.setIsShortDesc(model.getIsShortDesc());
		bean.setIsStatus(model.getIsStatus());
		return bean;
	}

	public static IclubInviteStatusModel fromUItoWS(IclubInviteStatusBean bean) {
		IclubInviteStatusModel model = new IclubInviteStatusModel();
		model.setIsId(bean.getIsId());
		model.setIsLongDesc(bean.getIsLongDesc());
		model.setIsShortDesc(bean.getIsShortDesc());
		model.setIsStatus(bean.getIsStatus());
		return model;
	}

	public static IclubInviteStatusModel fromORMtoWS(IclubInviteStatus bean) {
		IclubInviteStatusModel model = new IclubInviteStatusModel();
		model.setIsId(bean.getIsId());
		model.setIsLongDesc(bean.getIsLongDesc());
		model.setIsShortDesc(bean.getIsShortDesc());
		model.setIsStatus(bean.getIsStatus());
		return model;
	}

	public static IclubInviteStatus fromWStoORM(IclubInviteStatusModel model) {
		IclubInviteStatus inviteStatus = new IclubInviteStatus();

		inviteStatus.setIsId(model.getIsId());
		inviteStatus.setIsLongDesc(model.getIsLongDesc());
		inviteStatus.setIsShortDesc(model.getIsShortDesc());
		inviteStatus.setIsStatus(model.getIsStatus());

		return inviteStatus;
	}
}
