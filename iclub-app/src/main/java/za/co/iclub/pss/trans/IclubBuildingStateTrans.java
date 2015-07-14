package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBuildingStateBean;
import za.co.iclub.pss.model.ws.IclubBuildingStateModel;
import za.co.iclub.pss.orm.bean.IclubBuildingState;

public class IclubBuildingStateTrans {
	
	public static IclubBuildingStateBean fromWStoUI(IclubBuildingStateModel model) {
		IclubBuildingStateBean bean = new IclubBuildingStateBean();
		bean.setBsId(model.getBsId());
		bean.setBsLongDesc(model.getBsLongDesc());
		bean.setBsShortDesc(model.getBsShortDesc());
		bean.setBsStatus(model.getBsStatus());
		return bean;
	}
	
	public static IclubBuildingStateModel fromUItoWS(IclubBuildingStateBean bean) {
		IclubBuildingStateModel model = new IclubBuildingStateModel();
		model.setBsId(bean.getBsId());
		model.setBsLongDesc(bean.getBsLongDesc());
		model.setBsShortDesc(bean.getBsShortDesc());
		model.setBsStatus(bean.getBsStatus());
		return model;
	}
	
	public static IclubBuildingStateModel fromORMtoWS(IclubBuildingState bean) {
		IclubBuildingStateModel model = new IclubBuildingStateModel();
		model.setBsId(bean.getBsId());
		model.setBsLongDesc(bean.getBsLongDesc());
		model.setBsShortDesc(bean.getBsShortDesc());
		model.setBsStatus(bean.getBsStatus());
		return model;
	}
	
	public static IclubBuildingState fromWStoORM(IclubBuildingStateModel model) {
		IclubBuildingState acctype = new IclubBuildingState();
		
		acctype.setBsId(model.getBsId());
		acctype.setBsLongDesc(model.getBsLongDesc());
		acctype.setBsShortDesc(model.getBsShortDesc());
		acctype.setBsStatus(model.getBsStatus());
		
		return acctype;
	}
}
