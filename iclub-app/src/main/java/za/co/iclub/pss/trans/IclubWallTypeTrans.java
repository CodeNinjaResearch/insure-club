package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubWallTypeBean;
import za.co.iclub.pss.model.ws.IclubWallTypeModel;
import za.co.iclub.pss.orm.bean.IclubWallType;

public class IclubWallTypeTrans {

	public static IclubWallTypeBean fromWStoUI(IclubWallTypeModel model) {
		IclubWallTypeBean bean = new IclubWallTypeBean();
		bean.setWtId(model.getWtId());
		bean.setWtLongDesc(model.getWtLongDesc());
		bean.setWtShortDesc(model.getWtShortDesc());
		bean.setWtStatus(model.getWtStatus());
		return bean;
	}

	public static IclubWallTypeModel fromUItoWS(IclubWallTypeBean bean) {
		IclubWallTypeModel model = new IclubWallTypeModel();
		model.setWtId(bean.getWtId());
		model.setWtLongDesc(bean.getWtLongDesc());
		model.setWtShortDesc(bean.getWtShortDesc());
		model.setWtStatus(bean.getWtStatus());
		return model;
	}

	public static IclubWallTypeModel fromORMtoWS(IclubWallType bean) {
		IclubWallTypeModel model = new IclubWallTypeModel();
		model.setWtId(bean.getWtId());
		model.setWtLongDesc(bean.getWtLongDesc());
		model.setWtShortDesc(bean.getWtShortDesc());
		model.setWtStatus(bean.getWtStatus());
		return model;
	}

	public static IclubWallType fromWStoORM(IclubWallTypeModel model) {
		IclubWallType acctype = new IclubWallType();

		acctype.setWtId(model.getWtId());
		acctype.setWtLongDesc(model.getWtLongDesc());
		acctype.setWtShortDesc(model.getWtShortDesc());
		acctype.setWtStatus(model.getWtStatus());

		return acctype;
	}
}
