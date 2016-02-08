package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPaymentStatusBean;
import za.co.iclub.pss.model.ws.IclubPaymentStatusModel;
import za.co.iclub.pss.orm.bean.IclubPaymentStatus;

public class IclubPaymentStatusTrans {

	public static IclubPaymentStatusBean fromWStoUI(IclubPaymentStatusModel model) {
		IclubPaymentStatusBean bean = new IclubPaymentStatusBean();
		bean.setPsId(model.getPsId());
		bean.setPsLongDesc(model.getPsLongDesc());
		bean.setPsShortDesc(model.getPsShortDesc());
		bean.setPsStatus(model.getPsStatus());
		return bean;
	}

	public static IclubPaymentStatusModel fromUItoWS(IclubPaymentStatusBean bean) {
		IclubPaymentStatusModel model = new IclubPaymentStatusModel();
		model.setPsId(bean.getPsId());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}

	public static IclubPaymentStatusModel fromORMtoWS(IclubPaymentStatus bean) {
		IclubPaymentStatusModel model = new IclubPaymentStatusModel();
		model.setPsId(bean.getPsId());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setPsShortDesc(bean.getPsShortDesc());
		model.setPsStatus(bean.getPsStatus());
		return model;
	}

	public static IclubPaymentStatus fromWStoORM(IclubPaymentStatusModel model) {
		IclubPaymentStatus acctype = new IclubPaymentStatus();

		acctype.setPsId(model.getPsId());
		acctype.setPsLongDesc(model.getPsLongDesc());
		acctype.setPsShortDesc(model.getPsShortDesc());
		acctype.setPsStatus(model.getPsStatus());

		return acctype;
	}
}
