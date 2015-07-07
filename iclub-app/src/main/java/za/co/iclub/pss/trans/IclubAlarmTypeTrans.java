package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubAlarmTypeBean;
import za.co.iclub.pss.model.ws.IclubAlarmTypeModel;
import za.co.iclub.pss.orm.bean.IclubAlarmType;

public class IclubAlarmTypeTrans {
	
	public static IclubAlarmTypeBean fromWStoUI(IclubAlarmTypeModel model) {
		IclubAlarmTypeBean bean = new IclubAlarmTypeBean();
		bean.setAtId(model.getAtId().longValue());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setAtShortDesc(model.getAtShortDesc());
		bean.setAtStatus(model.getAtStatus());
		return bean;
	}
	
	public static IclubAlarmTypeModel fromUItoWS(IclubAlarmTypeBean bean) {
		IclubAlarmTypeModel model = new IclubAlarmTypeModel();
		model.setAtId(bean.getAtId().longValue());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAlarmTypeModel fromORMtoWS(IclubAlarmType bean) {
		IclubAlarmTypeModel model = new IclubAlarmTypeModel();
		model.setAtId(bean.getAtId().longValue());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setAtShortDesc(bean.getAtShortDesc());
		model.setAtStatus(bean.getAtStatus());
		return model;
	}
	
	public static IclubAlarmType fromWStoORM(IclubAlarmTypeModel model) {
		IclubAlarmType acctype = new IclubAlarmType();
		
		acctype.setAtId(model.getAtId());
		acctype.setAtLongDesc(model.getAtLongDesc());
		acctype.setAtShortDesc(model.getAtShortDesc());
		acctype.setAtStatus(model.getAtStatus());
		
		return acctype;
	}
}
