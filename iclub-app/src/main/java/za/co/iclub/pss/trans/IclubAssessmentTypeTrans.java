package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubAssessmentTypeBean;
import za.co.iclub.pss.model.ws.IclubAssessmentTypeModel;
import za.co.iclub.pss.orm.bean.IclubAssessmentType;

public class IclubAssessmentTypeTrans {
	
	public static IclubAssessmentTypeBean fromWStoUI(IclubAssessmentTypeModel model) {
		IclubAssessmentTypeBean bean = new IclubAssessmentTypeBean();
		bean.setAtId(model.getAtId());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setAtShortDesc(model.getAtShortDesc());
		bean.setAtStatus(model.getAtStatus());
		return bean;
	}
	
	public static IclubAssessmentTypeModel fromUItoWS(IclubAssessmentTypeBean bean) {
		IclubAssessmentTypeModel model = new IclubAssessmentTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAssessmentTypeModel fromORMtoWS(IclubAssessmentType bean) {
		IclubAssessmentTypeModel model = new IclubAssessmentTypeModel();
		model.setAtId(bean.getAtId());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAssessmentType fromWStoORM(IclubAssessmentTypeModel model) {
		IclubAssessmentType acctype = new IclubAssessmentType();
		
		acctype.setAtId(model.getAtId());
		acctype.setAtLongDesc(model.getAtLongDesc());
		acctype.setAtShortDesc(model.getAtShortDesc());
		acctype.setAtStatus(model.getAtStatus());
		
		return acctype;
	}
}
