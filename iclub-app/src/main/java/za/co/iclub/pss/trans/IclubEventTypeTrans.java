package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubEventTypeBean;
import za.co.iclub.pss.model.ws.IclubEventTypeModel;
import za.co.iclub.pss.orm.bean.IclubEventType;

public class IclubEventTypeTrans {
	
	public static IclubEventTypeBean fromWStoUI(IclubEventTypeModel model) {
		IclubEventTypeBean bean = new IclubEventTypeBean();
		bean.setEtId(model.getEtId());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setEtShortDesc(model.getEtShortDesc());
		bean.setEtStatus(model.getEtStatus());
		return bean;
	}
	
	public static IclubEventTypeModel fromUItoWS(IclubEventTypeBean bean) {
		IclubEventTypeModel model = new IclubEventTypeModel();
		model.setEtId(bean.getEtId());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setEtShortDesc(bean.getEtShortDesc());
		model.setEtStatus(bean.getEtStatus());
		return model;
	}
	
	public static IclubEventTypeModel fromORMtoWS(IclubEventType bean) {
		IclubEventTypeModel model = new IclubEventTypeModel();
		model.setEtId(bean.getEtId());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setEtShortDesc(bean.getEtShortDesc());
		model.setEtStatus(bean.getEtStatus());
		return model;
	}
	
	public static IclubEventType fromWStoORM(IclubEventTypeModel model) {
		IclubEventType acctype = new IclubEventType();
		
		acctype.setEtId(model.getEtId());
		acctype.setEtLongDesc(model.getEtLongDesc());
		acctype.setEtShortDesc(model.getEtShortDesc());
		acctype.setEtStatus(model.getEtStatus());
		
		return acctype;
	}
}
