package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSecurityQuestionBean;
import za.co.iclub.pss.model.ws.IclubSecurityQuestionModel;
import za.co.iclub.pss.orm.bean.IclubSecurityQuestion;

public class IclubSecurityQuestionTrans {
	public IclubSecurityQuestionBean fromWStoUI(IclubSecurityQuestionModel model) {
		IclubSecurityQuestionBean bean = new IclubSecurityQuestionBean();
		bean.setSqId(model.getSqId().longValue());
		bean.setSqLongDesc(model.getSqLongDesc());
		bean.setSqShortDesc(model.getSqShortDesc());
		bean.setSqStatus(model.getSqStatus());
		return bean;
	}
	
	public IclubSecurityQuestionModel fromUItoWS(IclubSecurityQuestionBean bean) {
		IclubSecurityQuestionModel model = new IclubSecurityQuestionModel();
		model.setSqId(bean.getSqId().longValue());
		model.setSqLongDesc(bean.getSqLongDesc());
		model.setSqShortDesc(bean.getSqShortDesc());
		model.setSqStatus(bean.getSqStatus());
		return model;
	}
	
	public IclubSecurityQuestionModel fromORMtoWS(IclubSecurityQuestion bean) {
		IclubSecurityQuestionModel model = new IclubSecurityQuestionModel();
		model.setSqId(bean.getSqId().longValue());
		model.setSqLongDesc(bean.getSqLongDesc());
		model.setSqShortDesc(bean.getSqShortDesc());
		model.setSqStatus(bean.getSqStatus());
		return model;
	}
	
	public IclubSecurityQuestion fromWStoORM(IclubSecurityQuestionModel model) {
		IclubSecurityQuestion acctype = new IclubSecurityQuestion();
		
		acctype.setSqId(model.getSqId());
		acctype.setSqLongDesc(model.getSqLongDesc());
		acctype.setSqShortDesc(model.getSqShortDesc());
		acctype.setSqStatus(model.getSqStatus());
		
		return acctype;
	}
}
