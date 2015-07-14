package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPaymentTypeBean;
import za.co.iclub.pss.model.ws.IclubPaymentTypeModel;
import za.co.iclub.pss.orm.bean.IclubPaymentType;

public class IclubPaymentTypeTrans {
	
	public static IclubPaymentTypeBean fromWStoUI(IclubPaymentTypeModel model) {
		IclubPaymentTypeBean bean = new IclubPaymentTypeBean();
		bean.setPtId(model.getPtId());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setPtShortDesc(model.getPtShortDesc());
		bean.setPtStatus(model.getPtStatus());
		return bean;
	}
	
	public static IclubPaymentTypeModel fromUItoWS(IclubPaymentTypeBean bean) {
		IclubPaymentTypeModel model = new IclubPaymentTypeModel();
		model.setPtId(bean.getPtId());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public static IclubPaymentTypeModel fromORMtoWS(IclubPaymentType bean) {
		IclubPaymentTypeModel model = new IclubPaymentTypeModel();
		model.setPtId(bean.getPtId());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public static IclubPaymentType fromWStoORM(IclubPaymentTypeModel model) {
		IclubPaymentType acctype = new IclubPaymentType();
		
		acctype.setPtId(model.getPtId());
		acctype.setPtLongDesc(model.getPtLongDesc());
		acctype.setPtShortDesc(model.getPtShortDesc());
		acctype.setPtStatus(model.getPtStatus());
		
		return acctype;
	}
}
