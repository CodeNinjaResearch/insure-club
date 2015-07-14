package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropUsageTypeBean;
import za.co.iclub.pss.model.ws.IclubPropUsageTypeModel;
import za.co.iclub.pss.orm.bean.IclubPropUsageType;

public class IclubPropUsageTypeTrans {
	
	public static IclubPropUsageTypeBean fromWStoUI(IclubPropUsageTypeModel model) {
		IclubPropUsageTypeBean bean = new IclubPropUsageTypeBean();
		bean.setPutId(model.getPutId());
		bean.setPutLongDesc(model.getPutLongDesc());
		bean.setPutShortDesc(model.getPutShortDesc());
		bean.setPutStatus(model.getPutStatus());
		return bean;
	}
	
	public static IclubPropUsageTypeModel fromUItoWS(IclubPropUsageTypeBean bean) {
		IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();
		model.setPutId(bean.getPutId());
		model.setPutLongDesc(bean.getPutLongDesc());
		model.setPutShortDesc(bean.getPutShortDesc());
		model.setPutStatus(bean.getPutStatus());
		return model;
	}
	
	public static IclubPropUsageTypeModel fromORMtoWS(IclubPropUsageType bean) {
		IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();
		model.setPutId(bean.getPutId());
		model.setPutLongDesc(bean.getPutLongDesc());
		model.setPutShortDesc(bean.getPutShortDesc());
		model.setPutStatus(bean.getPutStatus());
		return model;
	}
	
	public static IclubPropUsageType fromWStoORM(IclubPropUsageTypeModel model) {
		IclubPropUsageType acctype = new IclubPropUsageType();
		
		acctype.setPutId(model.getPutId());
		acctype.setPutLongDesc(model.getPutLongDesc());
		acctype.setPutShortDesc(model.getPutShortDesc());
		acctype.setPutStatus(model.getPutStatus());
		
		return acctype;
	}
}
