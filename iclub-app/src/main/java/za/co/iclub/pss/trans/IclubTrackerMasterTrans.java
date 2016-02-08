package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubTrackerMasterBean;
import za.co.iclub.pss.model.ws.IclubTrackerMasterModel;
import za.co.iclub.pss.orm.bean.IclubTrackerMaster;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubTrackerMasterTrans {

	public static IclubTrackerMasterBean fromWStoUI(IclubTrackerMasterModel model) {
		IclubTrackerMasterBean bean = new IclubTrackerMasterBean();

		bean.setTmId(model.getTmId());
		bean.setTmName(model.getTmName());
		bean.setTmLat(model.getTmLat());
		bean.setTmLocation(model.getTmLocation());
		bean.setTmLong(model.getTmLong());
		bean.setTmTradeName(model.getTmTradeName());
		bean.setTmRegNum(model.getTmRegNum());
		bean.setTmCrtdDt(model.getTmCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubTrackerMasterModel fromUItoWS(IclubTrackerMasterBean bean) {
		IclubTrackerMasterModel model = new IclubTrackerMasterModel();

		model.setTmId(bean.getTmId());
		model.setTmName(bean.getTmName());
		model.setTmLat(bean.getTmLat());
		model.setTmLocation(bean.getTmLocation());
		model.setTmLong(bean.getTmLong());
		model.setTmTradeName(bean.getTmTradeName());
		model.setTmRegNum(bean.getTmRegNum());
		model.setTmCrtdDt(bean.getTmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		return model;
	}

	public static IclubTrackerMasterModel fromORMtoWS(IclubTrackerMaster bean) {

		IclubTrackerMasterModel model = new IclubTrackerMasterModel();

		model.setTmId(bean.getTmId());
		model.setTmName(bean.getTmName());
		model.setTmLat(bean.getTmLat());
		model.setTmLocation(bean.getTmLocation());
		model.setTmLong(bean.getTmLong());
		model.setTmTradeName(bean.getTmTradeName());
		model.setTmRegNum(bean.getTmRegNum());
		model.setTmCrtdDt(bean.getTmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		return model;
	}

	public static IclubTrackerMaster fromWStoORM(IclubTrackerMasterModel model, IclubPersonDAO iclubPersonDAO) {

		IclubTrackerMaster bean = new IclubTrackerMaster();

		bean.setTmId(model.getTmId());
		bean.setTmName(model.getTmName());
		bean.setTmLat(model.getTmLat());
		bean.setTmLocation(model.getTmLocation());
		bean.setTmLong(model.getTmLong());
		bean.setTmTradeName(model.getTmTradeName());
		bean.setTmRegNum(model.getTmRegNum());
		bean.setTmCrtdDt(model.getTmCrtdDt());
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		return bean;
	}

}
