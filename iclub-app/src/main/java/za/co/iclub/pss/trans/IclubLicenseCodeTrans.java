package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubLicenseCodeBean;
import za.co.iclub.pss.model.ws.IclubLicenseCodeModel;
import za.co.iclub.pss.orm.bean.IclubLicenseCode;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubLicenseCodeTrans {

	public static IclubLicenseCodeBean fromWStoUI(IclubLicenseCodeModel model) {

		IclubLicenseCodeBean bean = new IclubLicenseCodeBean();

		bean.setLcId(model.getLcId());
		bean.setLcCrtdDt(model.getLcCrtdDt());
		bean.setLcDesc(model.getLcDesc());
		bean.setLcStatus(model.getLcStatus());
		bean.setLcCategory(model.getLcCategory());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubLicenseCodeModel fromUItoWS(IclubLicenseCodeBean bean) {

		IclubLicenseCodeModel model = new IclubLicenseCodeModel();

		model.setLcId(bean.getLcId());
		model.setLcCrtdDt(bean.getLcCrtdDt());
		model.setLcDesc(bean.getLcDesc());
		model.setLcStatus(bean.getLcStatus());
		model.setLcCategory(bean.getLcCategory());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubLicenseCodeModel fromORMtoWS(IclubLicenseCode bean) {

		IclubLicenseCodeModel model = new IclubLicenseCodeModel();

		model.setLcId(bean.getLcId());
		model.setLcCrtdDt(bean.getLcCrtdDt());
		model.setLcDesc(bean.getLcDesc());
		model.setLcStatus(bean.getLcStatus());
		model.setLcCategory(bean.getLcCategory());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubLicenseCode fromWStoORM(IclubLicenseCodeModel model, IclubPersonDAO iclubPersonDAO) {

		IclubLicenseCode bean = new IclubLicenseCode();

		bean.setLcId(model.getLcId());
		bean.setLcDesc(model.getLcDesc());
		bean.setLcCrtdDt(model.getLcCrtdDt());
		bean.setLcStatus(model.getLcStatus());
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setLcCategory(model.getLcCategory());

		return bean;
	}

}
