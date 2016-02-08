package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.model.ws.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.orm.bean.IclubInsuranceItemType;

public class IclubInsuranceItemTypeTrans {

	public static IclubInsuranceItemTypeBean fromWStoUI(IclubInsuranceItemTypeModel model) {
		IclubInsuranceItemTypeBean bean = new IclubInsuranceItemTypeBean();
		bean.setIitId(model.getIitId());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setIitShortDesc(model.getIitShortDesc());
		bean.setIitStatus(model.getIitStatus());
		return bean;
	}

	public static IclubInsuranceItemTypeModel fromUItoWS(IclubInsuranceItemTypeBean bean) {
		IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();
		model.setIitId(bean.getIitId());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIitShortDesc(bean.getIitShortDesc());
		model.setIitStatus(bean.getIitStatus());
		return model;
	}

	public static IclubInsuranceItemTypeModel fromORMtoWS(IclubInsuranceItemType bean) {
		IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();
		model.setIitId(bean.getIitId());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIitShortDesc(bean.getIitShortDesc());
		model.setIitStatus(bean.getIitStatus());
		return model;
	}

	public static IclubInsuranceItemType fromWStoORM(IclubInsuranceItemTypeModel model) {
		IclubInsuranceItemType acctype = new IclubInsuranceItemType();

		acctype.setIitId(model.getIitId());
		acctype.setIitLongDesc(model.getIitLongDesc());
		acctype.setIitShortDesc(model.getIitShortDesc());
		acctype.setIitStatus(model.getIitStatus());

		return acctype;
	}
}
