package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubOwnerTypeBean;
import za.co.iclub.pss.model.ws.IclubOwnerTypeModel;
import za.co.iclub.pss.orm.bean.IclubOwnerType;

public class IclubOwnerTypeTrans {
	
	public static IclubOwnerTypeBean fromWStoUI(IclubOwnerTypeModel model) {
		IclubOwnerTypeBean bean = new IclubOwnerTypeBean();
		bean.setOtId(model.getOtId());
		bean.setOtLongDesc(model.getOtLongDesc());
		bean.setOtShortDesc(model.getOtShortDesc());
		bean.setOtStatus(model.getOtStatus());
		return bean;
	}
	
	public static IclubOwnerTypeModel fromUItoWS(IclubOwnerTypeBean bean) {
		IclubOwnerTypeModel model = new IclubOwnerTypeModel();
		model.setOtId(bean.getOtId());
		model.setOtLongDesc(bean.getOtLongDesc());
		model.setOtShortDesc(bean.getOtShortDesc());
		model.setOtStatus(bean.getOtStatus());
		return model;
	}
	
	public static IclubOwnerTypeModel fromORMtoWS(IclubOwnerType bean) {
		IclubOwnerTypeModel model = new IclubOwnerTypeModel();
		model.setOtId(bean.getOtId());
		model.setOtLongDesc(bean.getOtLongDesc());
		model.setOtShortDesc(bean.getOtShortDesc());
		model.setOtStatus(bean.getOtStatus());
		return model;
	}
	
	public static IclubOwnerType fromWStoORM(IclubOwnerTypeModel model) {
		IclubOwnerType acctype = new IclubOwnerType();
		
		acctype.setOtId(model.getOtId());
		acctype.setOtLongDesc(model.getOtLongDesc());
		acctype.setOtShortDesc(model.getOtShortDesc());
		acctype.setOtStatus(model.getOtStatus());
		
		return acctype;
	}
}
