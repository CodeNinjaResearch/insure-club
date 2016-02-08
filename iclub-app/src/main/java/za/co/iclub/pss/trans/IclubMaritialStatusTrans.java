package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.orm.bean.IclubMaritialStatus;

public class IclubMaritialStatusTrans {

	public static IclubMaritialStatusBean fromWStoUI(IclubMaritialStatusModel model) {
		IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
		bean.setMsId(model.getMsId() != null ? model.getMsId().longValue() : null);
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setMsShortDesc(model.getMsShortDesc());
		bean.setMsStatus(model.getMsStatus());
		return bean;
	}

	public static IclubMaritialStatusModel fromUItoWS(IclubMaritialStatusBean bean) {
		IclubMaritialStatusModel model = new IclubMaritialStatusModel();
		model.setMsId(bean.getMsId() != null ? bean.getMsId().longValue() : null);
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}

	public static IclubMaritialStatusModel fromORMtoWS(IclubMaritialStatus bean) {
		IclubMaritialStatusModel model = new IclubMaritialStatusModel();
		model.setMsId(bean.getMsId() != null ? bean.getMsId().longValue() : null);
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}

	public static IclubMaritialStatus fromWStoORM(IclubMaritialStatusModel model) {
		IclubMaritialStatus acctype = new IclubMaritialStatus();

		acctype.setMsId(model.getMsId());
		acctype.setMsLongDesc(model.getMsLongDesc());
		acctype.setMsShortDesc(model.getMsShortDesc());
		acctype.setMsStatus(model.getMsStatus());

		return acctype;
	}
}
