package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubInsuranceItemBean;
import za.co.iclub.pss.model.ws.IclubInsuranceItemModel;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubInsuranceItemTrans {

	public static IclubInsuranceItemBean fromWStoUI(IclubInsuranceItemModel model) {

		IclubInsuranceItemBean bean = new IclubInsuranceItemBean();

		bean.setIiId(model.getIiId());
		bean.setIiItemId(model.getIiItemId());
		bean.setIiQuoteId(model.getIiQuoteId());
		bean.setIiCrtdDt(model.getIiCrtdDt());
		bean.setIiInsureValue(model.getIiInsureValue());
		bean.setIiActualValue(model.getIiActualValue());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubInsuranceItemModel fromUItoWS(IclubInsuranceItemBean bean) {

		IclubInsuranceItemModel model = new IclubInsuranceItemModel();

		model.setIiId(bean.getIiId());
		model.setIiItemId(bean.getIiItemId());
		model.setIiQuoteId(bean.getIiQuoteId());
		model.setIiCrtdDt(bean.getIiCrtdDt());
		model.setIiInsureValue(bean.getIiInsureValue());
		model.setIiActualValue(bean.getIiActualValue());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubInsuranceItemModel fromORMtoWS(IclubInsuranceItem bean) {
		IclubInsuranceItemModel model = new IclubInsuranceItemModel();

		model.setIiId(bean.getIiId());
		model.setIiItemId(bean.getIiItemId());
		model.setIiQuoteId(bean.getIiQuoteId());
		model.setIiCrtdDt(bean.getIiCrtdDt());
		model.setIiInsureValue(bean.getIiInsureValue());
		model.setIiActualValue(bean.getIiActualValue());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitId()) : null);
		model.setIitLongDesc(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubInsuranceItem fromWStoORM(IclubInsuranceItemModel model, IclubPersonDAO iclubPersonDAO, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		IclubInsuranceItem bean = new IclubInsuranceItem();

		bean.setIiId(model.getIiId());
		bean.setIiItemId(model.getIiItemId());
		bean.setIiQuoteId(model.getIiQuoteId());
		bean.setIiCrtdDt(model.getIiCrtdDt());
		bean.setIiInsureValue(model.getIiInsureValue());
		bean.setIiActualValue(model.getIiActualValue());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		return bean;
	}

}
