package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubInsurerMasterBean;
import za.co.iclub.pss.model.ws.IclubInsurerMasterModel;
import za.co.iclub.pss.orm.bean.IclubInsurerMaster;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubInsurerMasterTrans {

	public static IclubInsurerMasterBean fromWStoUI(IclubInsurerMasterModel model) {

		IclubInsurerMasterBean bean = new IclubInsurerMasterBean();

		bean.setImId(model.getImId());
		bean.setImName(model.getImName());
		bean.setImLat(model.getImLat());
		bean.setImLong(model.getImLong());
		bean.setImTradeName(model.getImTradeName());
		bean.setImRegNum(model.getImRegNum());
		bean.setImLocation(model.getImLocation());
		bean.setImCrtdDt(model.getImCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubInsurerMasterModel fromUItoWS(IclubInsurerMasterBean bean) {

		IclubInsurerMasterModel model = new IclubInsurerMasterModel();

		model.setImId(bean.getImId());
		model.setImName(bean.getImName());
		model.setImLat(bean.getImLat());
		model.setImLong(bean.getImLong());
		model.setImTradeName(bean.getImTradeName());
		model.setImRegNum(bean.getImRegNum());
		model.setImLocation(bean.getImLocation());
		model.setImCrtdDt(bean.getImCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubInsurerMasterModel fromORMtoWS(IclubInsurerMaster bean) {

		IclubInsurerMasterModel model = new IclubInsurerMasterModel();

		model.setImId(bean.getImId());
		model.setImName(bean.getImName());
		model.setImLat(bean.getImLat());
		model.setImLong(bean.getImLong());
		model.setImTradeName(bean.getImTradeName());
		model.setImRegNum(bean.getImRegNum());
		model.setImLocation(bean.getImLocation());
		model.setImCrtdDt(bean.getImCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubInsurerMaster fromWStoORM(IclubInsurerMasterModel model, IclubPersonDAO iclubPersonDAO) {
		IclubInsurerMaster bean = new IclubInsurerMaster();

		bean.setImId(model.getImId());
		bean.setImName(model.getImName());
		bean.setImLat(model.getImLat());
		bean.setImLong(model.getImLong());
		bean.setImTradeName(model.getImTradeName());
		bean.setImRegNum(model.getImRegNum());
		bean.setImLocation(model.getImLocation());
		bean.setImCrtdDt(model.getImCrtdDt());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

		return bean;
	}

}
