package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortTypeBean;
import za.co.iclub.pss.model.ws.IclubCohortTypeModel;
import za.co.iclub.pss.orm.bean.IclubCohortType;

public class IclubCohortTypeTrans {
	
	public static IclubCohortTypeBean fromWStoUI(IclubCohortTypeModel model) {
		IclubCohortTypeBean bean = new IclubCohortTypeBean();
		bean.setCtId(model.getCtId());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setCtShortDesc(model.getCtShortDesc());
		bean.setCtStatus(model.getCtStatus());
		return bean;
	}
	
	public static IclubCohortTypeModel fromUItoWS(IclubCohortTypeBean bean) {
		IclubCohortTypeModel model = new IclubCohortTypeModel();
		model.setCtId(bean.getCtId());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setCtShortDesc(bean.getCtShortDesc());
		model.setCtStatus(bean.getCtStatus());
		return model;
	}
	
	public static IclubCohortTypeModel fromORMtoWS(IclubCohortType bean) {
		IclubCohortTypeModel model = new IclubCohortTypeModel();
		model.setCtId(bean.getCtId());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setCtShortDesc(bean.getCtShortDesc());
		model.setCtStatus(bean.getCtStatus());
		return model;
	}
	
	public static IclubCohortType fromWStoORM(IclubCohortTypeModel model) {
		IclubCohortType acctype = new IclubCohortType();
		
		acctype.setCtId(model.getCtId());
		acctype.setCtLongDesc(model.getCtLongDesc());
		acctype.setCtShortDesc(model.getCtShortDesc());
		acctype.setCtStatus(model.getCtStatus());
		
		return acctype;
	}
}
