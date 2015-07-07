package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBoundaryTypeBean;
import za.co.iclub.pss.model.ws.IclubBoundaryTypeModel;
import za.co.iclub.pss.orm.bean.IclubBoundaryType;

public class IclubBoundaryTypeTrans {
	
	public static IclubBoundaryTypeBean fromWStoUI(IclubBoundaryTypeModel model) {
		IclubBoundaryTypeBean bean = new IclubBoundaryTypeBean();
		bean.setBtId(model.getBtId().longValue());
		bean.setBtLongDesc(model.getBtLongDesc());
		bean.setBtShortDesc(model.getBtShortDesc());
		bean.setBtStatus(model.getBtStatus());
		return bean;
	}
	
	public static IclubBoundaryTypeModel fromUItoWS(IclubBoundaryTypeBean bean) {
		IclubBoundaryTypeModel model = new IclubBoundaryTypeModel();
		model.setBtId(bean.getBtId().longValue());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public static IclubBoundaryTypeModel fromORMtoWS(IclubBoundaryType bean) {
		IclubBoundaryTypeModel model = new IclubBoundaryTypeModel();
		model.setBtId(bean.getBtId().longValue());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public static IclubBoundaryType fromWStoORM(IclubBoundaryTypeModel model) {
		IclubBoundaryType acctype = new IclubBoundaryType();
		
		acctype.setBtId(model.getBtId());
		acctype.setBtLongDesc(model.getBtLongDesc());
		acctype.setBtShortDesc(model.getBtShortDesc());
		acctype.setBtStatus(model.getBtStatus());
		
		return acctype;
	}
}
