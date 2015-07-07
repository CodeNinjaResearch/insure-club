package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubProductTypeBean;
import za.co.iclub.pss.model.ws.IclubProductTypeModel;
import za.co.iclub.pss.orm.bean.IclubProductType;

public class IclubProductTypeTrans {
	
	public IclubProductTypeBean fromWStoUI(IclubProductTypeModel model) {
		IclubProductTypeBean bean = new IclubProductTypeBean();
		bean.setPtId(model.getPtId().longValue());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setPtShortDesc(model.getPtShortDesc());
		bean.setPtStatus(model.getPtStatus());
		return bean;
	}
	
	public IclubProductTypeModel fromUItoWS(IclubProductTypeBean bean) {
		IclubProductTypeModel model = new IclubProductTypeModel();
		model.setPtId(bean.getPtId().longValue());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public IclubProductTypeModel fromORMtoWS(IclubProductType bean) {
		IclubProductTypeModel model = new IclubProductTypeModel();
		model.setPtId(bean.getPtId().longValue());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setPtShortDesc(bean.getPtShortDesc());
		model.setPtStatus(bean.getPtStatus());
		return model;
	}
	
	public IclubProductType fromWStoORM(IclubProductTypeModel model) {
		IclubProductType acctype = new IclubProductType();
		
		acctype.setPtId(model.getPtId());
		acctype.setPtLongDesc(model.getPtLongDesc());
		acctype.setPtShortDesc(model.getPtShortDesc());
		acctype.setPtStatus(model.getPtStatus());
		
		return acctype;
	}
}
