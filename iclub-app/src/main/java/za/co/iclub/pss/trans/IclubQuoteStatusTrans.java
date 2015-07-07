package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubQuoteStatusBean;
import za.co.iclub.pss.model.ws.IclubQuoteStatusModel;
import za.co.iclub.pss.orm.bean.IclubQuoteStatus;

public class IclubQuoteStatusTrans {
	
	public static IclubQuoteStatusBean fromWStoUI(IclubQuoteStatusModel model) {
		IclubQuoteStatusBean bean = new IclubQuoteStatusBean();
		bean.setQsId(model.getQsId().longValue());
		bean.setQsLongDesc(model.getQsLongDesc());
		bean.setQsShortDesc(model.getQsShortDesc());
		bean.setQsStatus(model.getQsStatus());
		return bean;
	}
	
	public static IclubQuoteStatusModel fromUItoWS(IclubQuoteStatusBean bean) {
		IclubQuoteStatusModel model = new IclubQuoteStatusModel();
		model.setQsId(bean.getQsId().longValue());
		model.setQsLongDesc(bean.getQsLongDesc());
		model.setQsShortDesc(bean.getQsShortDesc());
		model.setQsStatus(bean.getQsStatus());
		return model;
	}
	
	public static IclubQuoteStatusModel fromORMtoWS(IclubQuoteStatus bean) {
		IclubQuoteStatusModel model = new IclubQuoteStatusModel();
		model.setQsId(bean.getQsId().longValue());
		model.setQsLongDesc(bean.getQsLongDesc());
		model.setQsShortDesc(bean.getQsShortDesc());
		model.setQsStatus(bean.getQsStatus());
		return model;
	}
	
	public static IclubQuoteStatus fromWStoORM(IclubQuoteStatusModel model) {
		IclubQuoteStatus acctype = new IclubQuoteStatus();
		
		acctype.setQsId(model.getQsId());
		acctype.setQsLongDesc(model.getQsLongDesc());
		acctype.setQsShortDesc(model.getQsShortDesc());
		acctype.setQsStatus(model.getQsStatus());
		
		return acctype;
	}
}
