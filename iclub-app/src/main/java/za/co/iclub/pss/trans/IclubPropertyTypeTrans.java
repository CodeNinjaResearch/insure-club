package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropertyTypeBean;
import za.co.iclub.pss.model.ws.IclubPropertyTypeModel;
import za.co.iclub.pss.orm.bean.IclubPropertyType;

public class IclubPropertyTypeTrans {

	public static IclubPropertyTypeBean fromWStoUI(IclubPropertyTypeModel model) {
		IclubPropertyTypeBean bean = new IclubPropertyTypeBean();
		bean.setPtId(model.getPtId());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setPtShortDesc(model.getPtShortDesc());
		bean.setPtStatus(model.getPtStatus());
		return bean;
	}

	public static IclubPropertyTypeModel fromUItoWS(IclubPropertyTypeBean bean) {
		IclubPropertyTypeModel model = new IclubPropertyTypeModel();
		model.setPtId(bean.getPtId());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}

	public static IclubPropertyTypeModel fromORMtoWS(IclubPropertyType bean) {
		IclubPropertyTypeModel model = new IclubPropertyTypeModel();
		model.setPtId(bean.getPtId());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}

	public static IclubPropertyType fromWStoORM(IclubPropertyTypeModel model) {
		IclubPropertyType acctype = new IclubPropertyType();

		acctype.setPtId(model.getPtId());
		acctype.setPtLongDesc(model.getPtLongDesc());
		acctype.setPtShortDesc(model.getPtShortDesc());
		acctype.setPtStatus(model.getPtStatus());

		return acctype;
	}
}
