package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCoverTypeBean;
import za.co.iclub.pss.model.ws.IclubCoverTypeModel;
import za.co.iclub.pss.orm.bean.IclubCoverType;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCoverTypeTrans {

	public static IclubCoverTypeBean fromWStoUI(IclubCoverTypeModel model) {

		IclubCoverTypeBean bean = new IclubCoverTypeBean();

		bean.setCtId(model.getCtId());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setCtShortDesc(model.getCtShortDesc());
		bean.setCtStatus(model.getCtStatus());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setCtCrtdDt(model.getCtCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubCoverTypeModel fromUItoWS(IclubCoverTypeBean bean) {

		IclubCoverTypeModel model = new IclubCoverTypeModel();

		model.setCtId(bean.getCtId());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setCtShortDesc(bean.getCtShortDesc());
		model.setCtStatus(bean.getCtStatus());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setCtCrtdDt(bean.getCtCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubCoverTypeModel fromORMtoWS(IclubCoverType bean) {

		IclubCoverTypeModel model = new IclubCoverTypeModel();

		model.setCtId(bean.getCtId());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setCtShortDesc(bean.getCtShortDesc());
		model.setCtStatus(bean.getCtStatus());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
		model.setIitLongDesc(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitLongDesc() : null);
		model.setCtCrtdDt(bean.getCtCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubCoverType fromWStoORM(IclubCoverTypeModel model, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO, IclubPersonDAO iclubPersonDAO) {

		IclubCoverType bean = new IclubCoverType();

		bean.setCtId(bean.getCtId());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setCtShortDesc(model.getCtShortDesc());
		bean.setCtStatus(model.getCtStatus());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setCtCrtdDt(model.getCtCrtdDt());

		return bean;
	}
}
