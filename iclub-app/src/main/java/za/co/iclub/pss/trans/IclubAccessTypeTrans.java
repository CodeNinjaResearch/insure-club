package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubAccessTypeBean;
import za.co.iclub.pss.model.ws.IclubAccessTypeModel;
import za.co.iclub.pss.orm.bean.IclubAccessType;

public class IclubAccessTypeTrans {

	public static IclubAccessTypeBean fromWStoUI(IclubAccessTypeModel model) {
		IclubAccessTypeBean bean = new IclubAccessTypeBean();
		bean.setAtId(model.getAtId());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setAtShortDesc(model.getAtShortDesc());
		bean.setAtStatus(model.getAtStatus());
		return bean;
	}

	public static IclubAccessTypeModel fromUItoWS(IclubAccessTypeBean bean) {
		IclubAccessTypeModel model = new IclubAccessTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}

	public static IclubAccessTypeModel fromORMtoWS(IclubAccessType bean) {
		IclubAccessTypeModel model = new IclubAccessTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}

	public static IclubAccessType fromWStoORM(IclubAccessTypeModel model) {
		IclubAccessType acctype = new IclubAccessType();

		acctype.setAtId(model.getAtId());
		acctype.setAtLongDesc(model.getAtLongDesc());
		acctype.setAtShortDesc(model.getAtShortDesc());
		acctype.setAtStatus(model.getAtStatus());

		return acctype;
	}

}
