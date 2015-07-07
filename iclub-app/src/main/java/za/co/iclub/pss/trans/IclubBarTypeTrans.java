package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBarTypeBean;
import za.co.iclub.pss.model.ws.IclubBarTypeModel;
import za.co.iclub.pss.orm.bean.IclubBarType;

public class IclubBarTypeTrans {
	
	public IclubBarTypeBean fromWStoUI(IclubBarTypeModel model) {
		IclubBarTypeBean bean = new IclubBarTypeBean();
		bean.setBtId(model.getBtId().longValue());
		bean.setBtLongDesc(model.getBtLongDesc());
		bean.setBtShortDesc(model.getBtShortDesc());
		bean.setBtStatus(model.getBtStatus());
		return bean;
	}
	
	public IclubBarTypeModel fromUItoWS(IclubBarTypeBean bean) {
		IclubBarTypeModel model = new IclubBarTypeModel();
		model.setBtId(bean.getBtId().longValue());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public IclubBarTypeModel fromORMtoWS(IclubBarType bean) {
		IclubBarTypeModel model = new IclubBarTypeModel();
		model.setBtId(bean.getBtId().longValue());
		model.setBtLongDesc(bean.getBtLongDesc());
		model.setBtShortDesc(bean.getBtShortDesc());
		model.setBtStatus(bean.getBtStatus());
		return model;
	}
	
	public IclubBarType fromWStoORM(IclubBarTypeModel model) {
		IclubBarType acctype = new IclubBarType();
		
		acctype.setBtId(model.getBtId());
		acctype.setBtLongDesc(model.getBtLongDesc());
		acctype.setBtShortDesc(model.getBtShortDesc());
		acctype.setBtStatus(model.getBtStatus());
		
		return acctype;
	}
}
