package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubNotificationTypeBean;
import za.co.iclub.pss.model.ws.IclubNotificationTypeModel;
import za.co.iclub.pss.orm.bean.IclubNotificationType;

public class IclubNotificationTypeTrans {

	public static IclubNotificationTypeBean fromWStoUI(IclubNotificationTypeModel model) {
		IclubNotificationTypeBean bean = new IclubNotificationTypeBean();
		bean.setNtId(model.getNtId());
		bean.setNtLongDesc(model.getNtLongDesc());
		bean.setNtShortDesc(model.getNtShortDesc());
		bean.setNtStatus(model.getNtStatus());
		return bean;
	}

	public static IclubNotificationTypeModel fromUItoWS(IclubNotificationTypeBean bean) {
		IclubNotificationTypeModel model = new IclubNotificationTypeModel();
		model.setNtId(bean.getNtId());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setNtShortDesc(bean.getNtShortDesc());
		model.setNtStatus(bean.getNtStatus());
		return model;
	}

	public static IclubNotificationTypeModel fromORMtoWS(IclubNotificationType bean) {
		IclubNotificationTypeModel model = new IclubNotificationTypeModel();
		model.setNtId(bean.getNtId());
		model.setNtLongDesc(bean.getNtLongDesc());
		model.setNtShortDesc(bean.getNtShortDesc());
		model.setNtStatus(bean.getNtStatus());
		return model;
	}

	public static IclubNotificationType fromWStoORM(IclubNotificationTypeModel model) {
		IclubNotificationType acctype = new IclubNotificationType();

		acctype.setNtId(model.getNtId());
		acctype.setNtLongDesc(model.getNtLongDesc());
		acctype.setNtShortDesc(model.getNtShortDesc());
		acctype.setNtStatus(model.getNtStatus());

		return acctype;
	}
}
