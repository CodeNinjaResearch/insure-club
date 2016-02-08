package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSupplierTypeBean;
import za.co.iclub.pss.model.ws.IclubSupplierTypeModel;
import za.co.iclub.pss.orm.bean.IclubSupplierType;

public class IclubSupplierTypeTrans {

	public static IclubSupplierTypeBean fromWStoUI(IclubSupplierTypeModel model) {
		IclubSupplierTypeBean bean = new IclubSupplierTypeBean();
		bean.setStId(model.getStId());
		bean.setStLongDesc(model.getStLongDesc());
		bean.setStShortDesc(model.getStShortDesc());
		bean.setStStatus(model.getStStatus());
		return bean;
	}

	public static IclubSupplierTypeModel fromUItoWS(IclubSupplierTypeBean bean) {
		IclubSupplierTypeModel model = new IclubSupplierTypeModel();
		model.setStId(bean.getStId());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}

	public static IclubSupplierTypeModel fromORMtoWS(IclubSupplierType bean) {
		IclubSupplierTypeModel model = new IclubSupplierTypeModel();
		model.setStId(bean.getStId());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}

	public static IclubSupplierType fromWStoORM(IclubSupplierTypeModel model) {
		IclubSupplierType acctype = new IclubSupplierType();

		acctype.setStId(model.getStId());
		acctype.setStLongDesc(model.getStLongDesc());
		acctype.setStShortDesc(model.getStShortDesc());
		acctype.setStStatus(model.getStStatus());

		return acctype;
	}
}
