package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubIdTypeBean;
import za.co.iclub.pss.model.ws.IclubIdTypeModel;
import za.co.iclub.pss.orm.bean.IclubIdType;

public class IclubIdTypeTrans {
	
	public IclubIdTypeBean fromWStoUI(IclubIdTypeModel model) {
		IclubIdTypeBean bean = new IclubIdTypeBean();
		bean.setItId(model.getItId().longValue());
		bean.setItLongDesc(model.getItLongDesc());
		bean.setItShortDesc(model.getItShortDesc());
		bean.setItStatus(model.getItStatus());
		return bean;
	}
	
	public IclubIdTypeModel fromUItoWS(IclubIdTypeBean bean) {
		IclubIdTypeModel model = new IclubIdTypeModel();
		model.setItId(bean.getItId().longValue());
		model.setItLongDesc(bean.getItLongDesc());
		model.setItShortDesc(bean.getItShortDesc());
		model.setItStatus(bean.getItStatus());
		return model;
	}
	
	public IclubIdTypeModel fromORMtoWS(IclubIdType bean) {
		IclubIdTypeModel model = new IclubIdTypeModel();
		model.setItId(bean.getItId().longValue());
		model.setItLongDesc(bean.getItLongDesc());
		model.setItShortDesc(bean.getItShortDesc());
		model.setItStatus(bean.getItStatus());
		return model;
	}
	
	public IclubIdType fromWStoORM(IclubIdTypeModel model) {
		IclubIdType acctype = new IclubIdType();
		
		acctype.setItId(model.getItId());
		acctype.setItLongDesc(model.getItLongDesc());
		acctype.setItShortDesc(model.getItShortDesc());
		acctype.setItStatus(model.getItStatus());
		
		return acctype;
	}
}
