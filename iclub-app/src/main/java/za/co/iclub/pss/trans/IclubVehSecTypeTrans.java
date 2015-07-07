package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubVehSecTypeBean;
import za.co.iclub.pss.model.ws.IclubVehSecTypeModel;
import za.co.iclub.pss.orm.bean.IclubVehSecType;

public class IclubVehSecTypeTrans {
	
	public static IclubVehSecTypeBean fromWStoUI(IclubVehSecTypeModel model) {
		IclubVehSecTypeBean bean = new IclubVehSecTypeBean();
		bean.setVstId(model.getVstId().longValue());
		bean.setVstLongDesc(model.getVstLongDesc());
		bean.setVstShortDesc(model.getVstShortDesc());
		bean.setVstStatus(model.getVstStatus());
		return bean;
	}
	
	public static IclubVehSecTypeModel fromUItoWS(IclubVehSecTypeBean bean) {
		IclubVehSecTypeModel model = new IclubVehSecTypeModel();
		model.setVstId(bean.getVstId().longValue());
		model.setVstLongDesc(bean.getVstLongDesc());
		model.setVstShortDesc(bean.getVstShortDesc());
		model.setVstStatus(bean.getVstStatus());
		return model;
	}
	
	public static IclubVehSecTypeModel fromORMtoWS(IclubVehSecType bean) {
		IclubVehSecTypeModel model = new IclubVehSecTypeModel();
		model.setVstId(bean.getVstId().longValue());
		model.setVstLongDesc(bean.getVstLongDesc());
		model.setVstShortDesc(bean.getVstShortDesc());
		model.setVstStatus(bean.getVstStatus());
		return model;
	}
	
	public static IclubVehSecType fromWStoORM(IclubVehSecTypeModel model) {
		IclubVehSecType acctype = new IclubVehSecType();
		
		acctype.setVstId(model.getVstId());
		acctype.setVstLongDesc(model.getVstLongDesc());
		acctype.setVstShortDesc(model.getVstShortDesc());
		acctype.setVstStatus(model.getVstStatus());
		
		return acctype;
	}
}
