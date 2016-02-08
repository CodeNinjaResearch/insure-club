package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCountryCodeBean;
import za.co.iclub.pss.model.ws.IclubCountryCodeModel;
import za.co.iclub.pss.orm.bean.IclubCountryCode;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCountryCodeTrans {

	public static IclubCountryCodeBean fromWStoUI(IclubCountryCodeModel model) {

		IclubCountryCodeBean bean = new IclubCountryCodeBean();

		bean.setCcId(model.getCcId() != null ? model.getCcId().intValue() : null);
		bean.setCcIsoId(model.getCcIsoId());
		bean.setCcShortId(model.getCcShortId());
		bean.setCcCrtdDt(model.getCcCrtdDt());
		bean.setCcName(model.getCcName());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubCountryCodeModel fromUItoWS(IclubCountryCodeBean bean) {

		IclubCountryCodeModel model = new IclubCountryCodeModel();

		model.setCcId(bean.getCcId() != null ? bean.getCcId().intValue() : null);
		model.setCcIsoId(bean.getCcIsoId());
		model.setCcShortId(bean.getCcShortId());
		model.setCcCrtdDt(bean.getCcCrtdDt());
		model.setCcName(bean.getCcName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubCountryCodeModel fromORMtoWS(IclubCountryCode bean) {

		IclubCountryCodeModel model = new IclubCountryCodeModel();

		model.setCcId(bean.getCcId() != null ? bean.getCcId().intValue() : null);
		model.setCcIsoId(bean.getCcIsoId());
		model.setCcShortId(bean.getCcShortId());
		model.setCcCrtdDt(bean.getCcCrtdDt());
		model.setCcName(bean.getCcName());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubCountryCode fromWStoORM(IclubCountryCodeModel model, IclubPersonDAO iclubPersonDAO) {

		IclubCountryCode bean = new IclubCountryCode();

		bean.setCcId(model.getCcId());
		bean.setCcId(model.getCcId());
		bean.setCcIsoId(model.getCcIsoId());
		bean.setCcShortId(model.getCcShortId());
		bean.setCcCrtdDt(model.getCcCrtdDt());
		bean.setCcName(model.getCcName());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

		return bean;
	}

}
