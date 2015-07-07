package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSystemTypeBean;
import za.co.iclub.pss.model.ws.IclubSystemTypeModel;
import za.co.iclub.pss.orm.bean.IclubSystemType;

public class IclubSystemTypeTrans {
	
	public static IclubSystemTypeBean fromWStoUI(IclubSystemTypeModel model) {
		IclubSystemTypeBean bean = new IclubSystemTypeBean();
		bean.setStId(model.getStId().longValue());
		bean.setStLongDesc(model.getStLongDesc());
		bean.setStShortDesc(model.getStShortDesc());
		bean.setStStatus(model.getStStatus());
		return bean;
	}
	
	public static IclubSystemTypeModel fromUItoWS(IclubSystemTypeBean bean) {
		IclubSystemTypeModel model = new IclubSystemTypeModel();
		model.setStId(bean.getStId().longValue());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}
	
	public static IclubSystemTypeModel fromORMtoWS(IclubSystemType bean) {
		IclubSystemTypeModel model = new IclubSystemTypeModel();
		model.setStId(bean.getStId().longValue());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}
	
	public static IclubSystemType fromWStoORM(IclubSystemTypeModel model) {
		IclubSystemType acctype = new IclubSystemType();
		
		acctype.setStId(model.getStId());
		acctype.setStLongDesc(model.getStLongDesc());
		acctype.setStShortDesc(model.getStShortDesc());
		acctype.setStStatus(model.getStStatus());
		
		return acctype;
	}
}
