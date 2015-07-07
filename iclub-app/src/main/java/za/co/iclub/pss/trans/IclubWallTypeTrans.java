package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubWallTypeBean;
import za.co.iclub.pss.model.ws.IclubWallTypeModel;
import za.co.iclub.pss.orm.bean.IclubWallType;

public class IclubWallTypeTrans {
	
	public IclubWallTypeBean fromWStoUI(IclubWallTypeModel model) {
		IclubWallTypeBean bean = new IclubWallTypeBean();
		bean.setWtId(model.getWtId().longValue());
		bean.setWtLongDesc(model.getWtLongDesc());
		bean.setWtShortDesc(model.getWtShortDesc());
		bean.setWtStatus(model.getWtStatus());
		return bean;
	}
	
	public IclubWallTypeModel fromUItoWS(IclubWallTypeBean bean) {
		IclubWallTypeModel model = new IclubWallTypeModel();
		model.setWtId(bean.getWtId().longValue());
		model.setWtLongDesc(bean.getWtLongDesc());
		model.setWtShortDesc(bean.getWtShortDesc());
		model.setWtStatus(bean.getWtStatus());
		return model;
	}
	
	public IclubWallTypeModel fromORMtoWS(IclubWallType bean) {
		IclubWallTypeModel model = new IclubWallTypeModel();
		model.setWtId(bean.getWtId().longValue());
		model.setWtLongDesc(bean.getWtLongDesc());
		model.setWtShortDesc(bean.getWtShortDesc());
		model.setWtStatus(bean.getWtStatus());
		return model;
	}
	
	public IclubWallType fromWStoORM(IclubWallTypeModel model) {
		IclubWallType acctype = new IclubWallType();
		
		acctype.setWtId(model.getWtId());
		acctype.setWtLongDesc(model.getWtLongDesc());
		acctype.setWtShortDesc(model.getWtShortDesc());
		acctype.setWtStatus(model.getWtStatus());
		
		return acctype;
	}
}
