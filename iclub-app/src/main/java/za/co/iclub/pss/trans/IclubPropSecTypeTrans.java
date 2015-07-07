package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropSecTypeBean;
import za.co.iclub.pss.model.ws.IclubPropSecTypeModel;
import za.co.iclub.pss.orm.bean.IclubPropSecType;

public class IclubPropSecTypeTrans {
	
	public static IclubPropSecTypeBean fromWStoUI(IclubPropSecTypeModel model) {
		IclubPropSecTypeBean bean = new IclubPropSecTypeBean();
		bean.setPstId(model.getPstId().longValue());
		bean.setPstLongDesc(model.getPstLongDesc());
		bean.setPstShortDesc(model.getPstShortDesc());
		bean.setPstStatus(model.getPstStatus());
		return bean;
	}
	
	public static IclubPropSecTypeModel fromUItoWS(IclubPropSecTypeBean bean) {
		IclubPropSecTypeModel model = new IclubPropSecTypeModel();
		model.setPstId(bean.getPstId().longValue());
		model.setPstLongDesc(bean.getPstLongDesc());
		model.setPstShortDesc(bean.getPstShortDesc());
		model.setPstStatus(bean.getPstStatus());
		return model;
	}
	
	public static IclubPropSecTypeModel fromORMtoWS(IclubPropSecType bean) {
		IclubPropSecTypeModel model = new IclubPropSecTypeModel();
		model.setPstId(bean.getPstId().longValue());
		model.setPstLongDesc(bean.getPstLongDesc());
		model.setPstShortDesc(bean.getPstShortDesc());
		model.setPstStatus(bean.getPstStatus());
		return model;
	}
	
	public static IclubPropSecType fromWStoORM(IclubPropSecTypeModel model) {
		IclubPropSecType acctype = new IclubPropSecType();
		
		acctype.setPstId(model.getPstId());
		acctype.setPstLongDesc(model.getPstLongDesc());
		acctype.setPstShortDesc(model.getPstShortDesc());
		acctype.setPstStatus(model.getPstStatus());
		
		return acctype;
	}
}
