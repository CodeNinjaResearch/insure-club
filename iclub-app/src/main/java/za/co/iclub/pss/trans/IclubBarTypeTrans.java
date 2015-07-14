package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBarTypeBean;
import za.co.iclub.pss.model.ws.IclubBarTypeModel;
import za.co.iclub.pss.orm.bean.IclubBarType;

public class IclubBarTypeTrans {
	
	public static IclubBarTypeBean fromWStoUI(IclubBarTypeModel model) {
		IclubBarTypeBean bean = new IclubBarTypeBean();
		bean.setBtId(model.getBtId());
		bean.setBtLongDesc(model.getBtLongDesc());
		bean.setBtShortDesc(model.getBtShortDesc());
		bean.setBtStatus(model.getBtStatus());
		return bean;
	}
	
	public static IclubBarTypeModel fromUItoWS(IclubBarTypeBean bean) {
		IclubBarTypeModel model = new IclubBarTypeModel();
		model.setBtId(bean.getBtId());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public static IclubBarTypeModel fromORMtoWS(IclubBarType bean) {
		IclubBarTypeModel model = new IclubBarTypeModel();
		model.setBtId(bean.getBtId());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public static IclubBarType fromWStoORM(IclubBarTypeModel model) {
		IclubBarType acctype = new IclubBarType();
		
		acctype.setBtId(model.getBtId());
		acctype.setBtLongDesc(model.getBtLongDesc());
		acctype.setBtShortDesc(model.getBtShortDesc());
		acctype.setBtStatus(model.getBtStatus());
		
		return acctype;
	}
}
