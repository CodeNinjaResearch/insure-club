package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubOccupiedStatusBean;
import za.co.iclub.pss.model.ws.IclubOccupiedStatusModel;
import za.co.iclub.pss.orm.bean.IclubOccupiedStatus;

public class IclubOccupiedStatusTrans {

	public static IclubOccupiedStatusBean fromWStoUI(IclubOccupiedStatusModel model) {
		IclubOccupiedStatusBean bean = new IclubOccupiedStatusBean();
		bean.setOsId(model.getOsId());
		bean.setOsLongDesc(model.getOsLongDesc());
		bean.setOsShortDesc(model.getOsShortDesc());
		bean.setOsStatus(model.getOsStatus());
		return bean;
	}

	public static IclubOccupiedStatusModel fromUItoWS(IclubOccupiedStatusBean bean) {
		IclubOccupiedStatusModel model = new IclubOccupiedStatusModel();
		model.setOsId(bean.getOsId());
		model.setOsLongDesc(bean.getOsLongDesc());
		model.setOsShortDesc(bean.getOsShortDesc());
		model.setOsStatus(bean.getOsStatus());
		return model;
	}

	public static IclubOccupiedStatusModel fromORMtoWS(IclubOccupiedStatus bean) {
		IclubOccupiedStatusModel model = new IclubOccupiedStatusModel();
		model.setOsId(bean.getOsId());
		model.setOsLongDesc(bean.getOsLongDesc());
		model.setOsShortDesc(bean.getOsShortDesc());
		model.setOsStatus(bean.getOsStatus());
		return model;
	}

	public static IclubOccupiedStatus fromWStoORM(IclubOccupiedStatusModel model) {
		IclubOccupiedStatus acctype = new IclubOccupiedStatus();

		acctype.setOsId(model.getOsId());
		acctype.setOsLongDesc(model.getOsLongDesc());
		acctype.setOsShortDesc(model.getOsShortDesc());
		acctype.setOsStatus(model.getOsStatus());

		return acctype;
	}
}
