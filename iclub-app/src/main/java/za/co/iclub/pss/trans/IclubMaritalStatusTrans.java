package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMaritalStatusBean;
import za.co.iclub.pss.model.ws.IclubMaritalStatusModel;
import za.co.iclub.pss.orm.bean.IclubMaritalStatus;

public class IclubMaritalStatusTrans {

	public static IclubMaritalStatusBean fromWStoUI(IclubMaritalStatusModel model) {
		IclubMaritalStatusBean bean = new IclubMaritalStatusBean();
		bean.setMsId(model.getMsId() != null ? model.getMsId().longValue() : null);
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setMsShortDesc(model.getMsShortDesc());
		bean.setMsStatus(model.getMsStatus());
		return bean;
	}

	public static IclubMaritalStatusModel fromUItoWS(IclubMaritalStatusBean bean) {
		IclubMaritalStatusModel model = new IclubMaritalStatusModel();
		model.setMsId(bean.getMsId() != null ? bean.getMsId().longValue() : null);
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}

	public static IclubMaritalStatusModel fromORMtoWS(IclubMaritalStatus bean) {
		IclubMaritalStatusModel model = new IclubMaritalStatusModel();
		model.setMsId(bean.getMsId() != null ? bean.getMsId().longValue() : null);
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}

	public static IclubMaritalStatus fromWStoORM(IclubMaritalStatusModel model) {
		IclubMaritalStatus acctype = new IclubMaritalStatus();

		acctype.setMsId(model.getMsId());
		acctype.setMsLongDesc(model.getMsLongDesc());
		acctype.setMsShortDesc(model.getMsShortDesc());
		acctype.setMsStatus(model.getMsStatus());

		return acctype;
	}
}
