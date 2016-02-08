package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubRoleTypeBean;
import za.co.iclub.pss.model.ws.IclubRoleTypeModel;
import za.co.iclub.pss.orm.bean.IclubRoleType;

public class IclubRoleTypeTrans {

	public static IclubRoleTypeBean fromWStoUI(IclubRoleTypeModel model) {
		IclubRoleTypeBean bean = new IclubRoleTypeBean();
		bean.setRtId(model.getRtId());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setRtShortDesc(model.getRtShortDesc());
		bean.setRtStatus(model.getRtStatus());
		return bean;
	}

	public static IclubRoleTypeModel fromUItoWS(IclubRoleTypeBean bean) {
		IclubRoleTypeModel model = new IclubRoleTypeModel();
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}

	public static IclubRoleTypeModel fromORMtoWS(IclubRoleType bean) {
		IclubRoleTypeModel model = new IclubRoleTypeModel();
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}

	public static IclubRoleType fromWStoORM(IclubRoleTypeModel model) {
		IclubRoleType acctype = new IclubRoleType();

		acctype.setRtId(model.getRtId());
		acctype.setRtLongDesc(model.getRtLongDesc());
		acctype.setRtShortDesc(model.getRtShortDesc());
		acctype.setRtStatus(model.getRtStatus());

		return acctype;
	}
}
