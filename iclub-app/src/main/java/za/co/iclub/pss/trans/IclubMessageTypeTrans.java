package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMessageTypeBean;
import za.co.iclub.pss.model.ws.IclubMessageTypeModel;
import za.co.iclub.pss.orm.bean.IclubMessageType;

public class IclubMessageTypeTrans {
	
	public static IclubMessageTypeBean fromWStoUI(IclubMessageTypeModel model) {
		IclubMessageTypeBean bean = new IclubMessageTypeBean();
		bean.setMtId(model.getMtId().longValue());
		bean.setMtLongDesc(model.getMtLongDesc());
		bean.setMtShortDesc(model.getMtShortDesc());
		bean.setMtStatus(model.getMtStatus());
		return bean;
	}
	
	public static IclubMessageTypeModel fromUItoWS(IclubMessageTypeBean bean) {
		IclubMessageTypeModel model = new IclubMessageTypeModel();
		model.setMtId(bean.getMtId().longValue());
		model.setMtLongDesc(bean.getMtLongDesc());
		model.setMtShortDesc(bean.getMtShortDesc());
		model.setMtStatus(bean.getMtStatus());
		return model;
	}
	
	public static IclubMessageTypeModel fromORMtoWS(IclubMessageType bean) {
		IclubMessageTypeModel model = new IclubMessageTypeModel();
		model.setMtId(bean.getMtId().longValue());
		model.setMtLongDesc(bean.getMtLongDesc());
		model.setMtShortDesc(bean.getMtShortDesc());
		model.setMtStatus(bean.getMtStatus());
		return model;
	}
	
	public static IclubMessageType fromWStoORM(IclubMessageTypeModel model) {
		IclubMessageType acctype = new IclubMessageType();
		
		acctype.setMtId(model.getMtId());
		acctype.setMtLongDesc(model.getMtLongDesc());
		acctype.setMtShortDesc(model.getMtShortDesc());
		acctype.setMtStatus(model.getMtStatus());
		
		return acctype;
	}
}
