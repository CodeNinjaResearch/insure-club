package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubVehicleTypeBean;
import za.co.iclub.pss.model.ws.IclubVehicleTypeModel;
import za.co.iclub.pss.orm.bean.IclubVehicleType;

public class IclubVehicleTypeTrans {

	public static IclubVehicleTypeBean fromWStoUI(IclubVehicleTypeModel model) {
		IclubVehicleTypeBean bean = new IclubVehicleTypeBean();
		bean.setVtId(model.getVtId());
		bean.setVtLongDesc(model.getVtLongDesc());
		bean.setVtShortDesc(model.getVtShortDesc());
		bean.setVtStatus(model.getVtStatus());
		return bean;
	}

	public static IclubVehicleTypeModel fromUItoWS(IclubVehicleTypeBean bean) {
		IclubVehicleTypeModel model = new IclubVehicleTypeModel();
		model.setVtId(bean.getVtId());
		model.setVtLongDesc(bean.getVtLongDesc());
		model.setVtShortDesc(bean.getVtShortDesc());
		model.setVtStatus(bean.getVtStatus());
		return model;
	}

	public static IclubVehicleTypeModel fromORMtoWS(IclubVehicleType bean) {
		IclubVehicleTypeModel model = new IclubVehicleTypeModel();
		model.setVtId(bean.getVtId());
		model.setVtLongDesc(bean.getVtLongDesc());
		model.setVtShortDesc(bean.getVtShortDesc());
		model.setVtStatus(bean.getVtStatus());
		return model;
	}

	public static IclubVehicleType fromWStoORM(IclubVehicleTypeModel model) {
		IclubVehicleType acctype = new IclubVehicleType();

		acctype.setVtId(model.getVtId());
		acctype.setVtLongDesc(model.getVtLongDesc());
		acctype.setVtShortDesc(model.getVtShortDesc());
		acctype.setVtStatus(model.getVtStatus());

		return acctype;
	}
}
