package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSupplMasterBean;
import za.co.iclub.pss.model.ws.IclubSupplMasterModel;
import za.co.iclub.pss.orm.bean.IclubSupplMaster;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplierTypeDAO;

public class IclubSupplMasterTrans {

	public static IclubSupplMasterBean fromWStoUI(IclubSupplMasterModel model) {

		IclubSupplMasterBean bean = new IclubSupplMasterBean();

		bean.setSmId(model.getSmId());
		bean.setSmCrtdDt(model.getSmCrtdDt());
		bean.setIclubSupplierType(model.getIclubSupplierType());
		bean.setStLongDesc(model.getStLongDesc());
		bean.setSmRating(model.getSmRating());
		bean.setSrActionDt(model.getSrActionDt());
		bean.setSmLong(model.getSmLong());
		bean.setSmCrLimit(model.getSmCrLimit());
		bean.setSmAddress(model.getSmAddress());
		bean.setSmRegNum(model.getSmRegNum());
		bean.setSmTradeName(model.getSmTradeName());
		bean.setSmLat(model.getSmLat());
		bean.setSmName(model.getSmName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		return bean;
	}

	public static IclubSupplMasterModel fromUItoWS(IclubSupplMasterBean bean) {

		IclubSupplMasterModel model = new IclubSupplMasterModel();

		model.setSmId(bean.getSmId());
		model.setSmCrtdDt(bean.getSmCrtdDt());
		model.setIclubSupplierType(bean.getIclubSupplierType());
		model.setStLongDesc(bean.getStLongDesc());
		model.setSmRating(bean.getSmRating());
		model.setSrActionDt(bean.getSrActionDt());
		model.setSmLong(bean.getSmLong());
		model.setSmCrLimit(bean.getSmCrLimit());
		model.setSmAddress(bean.getSmAddress());
		model.setSmRegNum(bean.getSmRegNum());
		model.setSmTradeName(bean.getSmTradeName());
		model.setSmLat(bean.getSmLat());
		model.setSmName(bean.getSmName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubSupplMasterModel fromORMtoWS(IclubSupplMaster bean) {

		IclubSupplMasterModel model = new IclubSupplMasterModel();

		model.setSmId(bean.getSmId());
		model.setSmCrtdDt(bean.getSmCrtdDt());
		model.setIclubSupplierType(bean.getIclubSupplierType() != null ? bean.getIclubSupplierType().getStId() : null);
		model.setStLongDesc(bean.getIclubSupplierType() != null ? bean.getIclubSupplierType().getStLongDesc() : null);
		model.setSmRating(bean.getSmRating());
		model.setSrActionDt(bean.getSrActionDt());
		model.setSmLong(bean.getSmLong());
		model.setSmCrLimit(bean.getSmCrLimit());
		model.setSmAddress(bean.getSmAddress());
		model.setSmRegNum(bean.getSmRegNum());
		model.setSmTradeName(bean.getSmTradeName());
		model.setSmLat(bean.getSmLat());
		model.setSmName(bean.getSmName());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubSupplMaster fromWStoORM(IclubSupplMasterModel model, IclubPersonDAO iclubPersonDAO, IclubSupplierTypeDAO iclubSupplierTypeDAO) {

		IclubSupplMaster bean = new IclubSupplMaster();

		bean.setSmId(model.getSmId());
		bean.setSmCrtdDt(model.getSmCrtdDt());
		bean.setSmRating(model.getSmRating());
		bean.setSrActionDt(model.getSrActionDt());
		bean.setSmLong(model.getSmLong());
		bean.setSmCrLimit(model.getSmCrLimit());
		bean.setSmAddress(model.getSmAddress());
		bean.setSmRegNum(model.getSmRegNum());
		bean.setSmTradeName(model.getSmTradeName());
		bean.setSmLat(model.getSmLat());
		bean.setSmName(model.getSmName());
		bean.setIclubSupplierType(model.getIclubSupplierType() != null ? iclubSupplierTypeDAO.findById(model.getIclubSupplierType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		return bean;
	}
}
