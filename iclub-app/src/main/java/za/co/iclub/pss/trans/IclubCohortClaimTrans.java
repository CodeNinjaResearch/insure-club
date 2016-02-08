package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortClaimBean;
import za.co.iclub.pss.model.ws.IclubCohortClaimModel;
import za.co.iclub.pss.orm.bean.IclubCohortClaim;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCohortClaimTrans {

	public static IclubCohortClaimBean fromWStoUI(IclubCohortClaimModel model) {
		IclubCohortClaimBean bean = new IclubCohortClaimBean();

		model.setCcId(model.getCcId());
		model.setIclubCohort(model.getIclubCohort());
		model.setCEmail(model.getCEmail());
		model.setCcCrtdDt(model.getCcCrtdDt());
		model.setIclubClaim(model.getIclubClaim());
		model.setCNumber(model.getCNumber());
		model.setCValue(model.getCValue());
		model.setCcClaimAmt(model.getCcClaimAmt());
		model.setIclubPerson(model.getIclubPerson());
		model.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubCohortClaimModel fromUItoWS(IclubCohortClaimBean bean) {
		IclubCohortClaimModel model = new IclubCohortClaimModel();

		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		return model;
	}

	public static IclubCohortClaimModel fromORMtoWS(IclubCohortClaim bean) {

		IclubCohortClaimModel model = new IclubCohortClaimModel();

		model.setCcId(bean.getCcId());
		model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
		model.setCEmail(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCEmail() : null);
		model.setCcCrtdDt(bean.getCcCrtdDt());
		model.setIclubClaim(bean.getIclubClaim() != null ? bean.getIclubClaim().getCId() : null);
		model.setCNumber(bean.getIclubClaim() != null ? bean.getIclubClaim().getCNumber() : null);
		model.setCValue(bean.getIclubClaim() != null ? bean.getIclubClaim().getCValue() : null);
		model.setCcClaimAmt(bean.getCcClaimAmt());
		model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson()).getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubCohortClaim fromWStoORM(IclubCohortClaimModel model, IclubClaimDAO iclubClaimDAO, IclubCohortDAO iclubCohortDAO, IclubPersonDAO iclubPersonDAO) {

		IclubCohortClaim bean = new IclubCohortClaim();

		bean.setCcId(model.getCcId());
		bean.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
		bean.setIclubClaim(iclubClaimDAO.findById(model.getIclubClaim()));
		bean.setCcCrtdDt(model.getCcCrtdDt());
		bean.setCcClaimAmt(model.getCcClaimAmt());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

		return bean;
	}
}
