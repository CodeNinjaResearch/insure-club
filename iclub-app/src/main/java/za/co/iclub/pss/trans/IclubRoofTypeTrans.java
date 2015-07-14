package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubRoofTypeBean;
import za.co.iclub.pss.model.ws.IclubRoofTypeModel;
import za.co.iclub.pss.orm.bean.IclubRoofType;

public class IclubRoofTypeTrans {
	
	public static IclubRoofTypeBean fromWStoUI(IclubRoofTypeModel model) {
		IclubRoofTypeBean bean = new IclubRoofTypeBean();
		bean.setRtId(model.getRtId());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setRtShortDesc(model.getRtShortDesc());
		bean.setRtStatus(model.getRtStatus());
		return bean;
	}
	
	public static IclubRoofTypeModel fromUItoWS(IclubRoofTypeBean bean) {
		IclubRoofTypeModel model = new IclubRoofTypeModel();
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}
	
	public static IclubRoofTypeModel fromORMtoWS(IclubRoofType bean) {
		IclubRoofTypeModel model = new IclubRoofTypeModel();
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		return model;
	}
	
	public static IclubRoofType fromWStoORM(IclubRoofTypeModel model) {
		IclubRoofType acctype = new IclubRoofType();
		
		acctype.setRtId(model.getRtId());
		acctype.setRtLongDesc(model.getRtLongDesc());
		acctype.setRtShortDesc(model.getRtShortDesc());
		acctype.setRtStatus(model.getRtStatus());
		
		return acctype;
	}
}
