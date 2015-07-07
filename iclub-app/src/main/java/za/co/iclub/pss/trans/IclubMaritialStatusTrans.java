package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.orm.bean.IclubMaritialStatus;

public class IclubMaritialStatusTrans {
	
	public IclubMaritialStatusBean fromWStoUI(IclubMaritialStatusModel model) {
		IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
		bean.setMsId(model.getMsId().longValue());
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setMsShortDesc(model.getMsShortDesc());
		bean.setMsStatus(model.getMsStatus());
		return bean;
	}
	
	public IclubMaritialStatusModel fromUItoWS(IclubMaritialStatusBean bean) {
		IclubMaritialStatusModel model = new IclubMaritialStatusModel();
		model.setMsId(bean.getMsId().longValue());
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}
	
	public IclubMaritialStatusModel fromORMtoWS(IclubMaritialStatus bean) {
		IclubMaritialStatusModel model = new IclubMaritialStatusModel();
		model.setMsId(bean.getMsId().longValue());
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setMsShortDesc(bean.getMsShortDesc());
		model.setMsStatus(bean.getMsStatus());
		return model;
	}
	
	public IclubMaritialStatus fromWStoORM(IclubMaritialStatusModel model) {
		IclubMaritialStatus acctype = new IclubMaritialStatus();
		
		acctype.setMsId(model.getMsId());
		acctype.setMsLongDesc(model.getMsLongDesc());
		acctype.setMsShortDesc(model.getMsShortDesc());
		acctype.setMsStatus(model.getMsStatus());
		
		return acctype;
	}
}
