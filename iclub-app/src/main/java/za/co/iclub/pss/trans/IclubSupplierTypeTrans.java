package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSupplierTypeBean;
import za.co.iclub.pss.model.ws.IclubSupplierTypeModel;
import za.co.iclub.pss.orm.bean.IclubSupplierType;

public class IclubSupplierTypeTrans {
	
	public IclubSupplierTypeBean fromWStoUI(IclubSupplierTypeModel model) {
		IclubSupplierTypeBean bean = new IclubSupplierTypeBean();
		bean.setStId(model.getStId().longValue());
		bean.setStLongDesc(model.getStLongDesc());
		bean.setStShortDesc(model.getStShortDesc());
		bean.setStStatus(model.getStStatus());
		return bean;
	}
	
	public IclubSupplierTypeModel fromUItoWS(IclubSupplierTypeBean bean) {
		IclubSupplierTypeModel model = new IclubSupplierTypeModel();
		model.setStId(bean.getStId().longValue());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}
	
	public IclubSupplierTypeModel fromORMtoWS(IclubSupplierType bean) {
		IclubSupplierTypeModel model = new IclubSupplierTypeModel();
		model.setStId(bean.getStId().longValue());
		model.setStLongDesc(bean.getStLongDesc());
		model.setStShortDesc(bean.getStShortDesc());
		model.setStStatus(bean.getStStatus());
		return model;
	}
	
	public IclubSupplierType fromWStoORM(IclubSupplierTypeModel model) {
		IclubSupplierType acctype = new IclubSupplierType();
		
		acctype.setStId(model.getStId());
		acctype.setStLongDesc(model.getStLongDesc());
		acctype.setStShortDesc(model.getStShortDesc());
		acctype.setStStatus(model.getStStatus());
		
		return acctype;
	}
}
