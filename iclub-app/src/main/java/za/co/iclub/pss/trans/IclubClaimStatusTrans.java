package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubClaimStatusBean;
import za.co.iclub.pss.model.ws.IclubClaimStatusModel;
import za.co.iclub.pss.orm.bean.IclubClaimStatus;

public class IclubClaimStatusTrans {
	public static IclubClaimStatusBean fromWStoUI(IclubClaimStatusModel model) {
		IclubClaimStatusBean bean = new IclubClaimStatusBean();
		bean.setCsId(model.getCsId());
		bean.setCsLongDesc(model.getCsLongDesc());
		bean.setCsShortDesc(model.getCsShortDesc());
		bean.setCsStatus(model.getCsStatus());
		return bean;
	}

	public static IclubClaimStatusModel fromUItoWS(IclubClaimStatusBean bean) {
		IclubClaimStatusModel model = new IclubClaimStatusModel();
		model.setCsId(bean.getCsId());
		model.setCsLongDesc(bean.getCsLongDesc());
		model.setCsShortDesc(bean.getCsShortDesc());
		model.setCsStatus(bean.getCsStatus());
		return model;
	}

	public static IclubClaimStatusModel fromORMtoWS(IclubClaimStatus bean) {
		IclubClaimStatusModel model = new IclubClaimStatusModel();
		model.setCsId(bean.getCsId());
		model.setCsLongDesc(bean.getCsLongDesc());
		model.setCsShortDesc(bean.getCsShortDesc());
		model.setCsStatus(bean.getCsStatus());
		return model;
	}

	public static IclubClaimStatus fromWStoORM(IclubClaimStatusModel model) {
		IclubClaimStatus acctype = new IclubClaimStatus();

		acctype.setCsId(model.getCsId());
		acctype.setCsLongDesc(model.getCsLongDesc());
		acctype.setCsShortDesc(model.getCsShortDesc());
		acctype.setCsStatus(model.getCsStatus());

		return acctype;
	}
}
