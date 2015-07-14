package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubAccountTypeBean;
import za.co.iclub.pss.model.ws.IclubAccountTypeModel;
import za.co.iclub.pss.orm.bean.IclubAccountType;

public class IclubAccountTypeTrans {
	
	public static IclubAccountTypeBean fromWStoUI(IclubAccountTypeModel model) {
		IclubAccountTypeBean bean = new IclubAccountTypeBean();
		bean.setAtId(model.getAtId());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setAtShortDesc(model.getAtShortDesc());
		bean.setAtStatus(model.getAtStatus());
		return bean;
	}
	
	public static IclubAccountTypeModel fromUItoWS(IclubAccountTypeBean bean) {
		IclubAccountTypeModel model = new IclubAccountTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAccountTypeModel fromORMtoWS(IclubAccountType bean) {
		IclubAccountTypeModel model = new IclubAccountTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAccountType fromWStoORM(IclubAccountTypeModel model) {
		IclubAccountType acctype = new IclubAccountType();
		
		acctype.setAtId(model.getAtId());
		acctype.setAtLongDesc(model.getAtLongDesc());
		acctype.setAtShortDesc(model.getAtShortDesc());
		acctype.setAtStatus(model.getAtStatus());
		
		return acctype;
	}
}
