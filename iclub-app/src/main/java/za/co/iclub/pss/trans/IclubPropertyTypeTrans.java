package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropertyTypeBean;
import za.co.iclub.pss.model.ws.IclubPropertyTypeModel;
import za.co.iclub.pss.orm.bean.IclubPropertyType;

public class IclubPropertyTypeTrans {
	
	public IclubPropertyTypeBean fromWStoUI(IclubPropertyTypeModel model) {
		IclubPropertyTypeBean bean = new IclubPropertyTypeBean();
		bean.setPtId(model.getPtId().longValue());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setPtShortDesc(model.getPtShortDesc());
		bean.setPtStatus(model.getPtStatus());
		return bean;
	}
	
	public IclubPropertyTypeModel fromUItoWS(IclubPropertyTypeBean bean) {
		IclubPropertyTypeModel model = new IclubPropertyTypeModel();
		model.setPtId(bean.getPtId().longValue());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public IclubPropertyTypeModel fromORMtoWS(IclubPropertyType bean) {
		IclubPropertyTypeModel model = new IclubPropertyTypeModel();
		model.setPtId(bean.getPtId().longValue());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public IclubPropertyType fromWStoORM(IclubPropertyTypeModel model) {
		IclubPropertyType acctype = new IclubPropertyType();
		
		acctype.setPtId(model.getPtId());
		acctype.setPtLongDesc(model.getPtLongDesc());
		acctype.setPtShortDesc(model.getPtShortDesc());
		acctype.setPtStatus(model.getPtStatus());
		
		return acctype;
	}
}
