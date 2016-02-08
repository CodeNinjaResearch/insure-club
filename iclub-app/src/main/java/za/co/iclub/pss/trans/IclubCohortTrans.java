package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortBean;
import za.co.iclub.pss.model.ws.IclubCohortModel;
import za.co.iclub.pss.orm.bean.IclubCohort;
import za.co.iclub.pss.orm.dao.IclubCohortTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCohortTrans {

	public static IclubCohortBean fromWStoUI(IclubCohortModel model) {
		IclubCohortBean bean = new IclubCohortBean();

		bean.setCId(model.getCId());
		bean.setCName(model.getCName());
		bean.setCEmail(model.getCEmail());
		bean.setCInitDt(model.getCInitDt());
		bean.setCFinalizeDt(model.getCFinalizeDt());
		bean.setCTotalContrib(model.getCTotalContrib());
		bean.setCCollectedContrib(model.getCCollectedContrib());
		bean.setCCurMemberCnt(model.getCCurMemberCnt());
		bean.setIclubCohortType(model.getIclubCohortType());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId());
		bean.setPAFNameAndLName(model.getPAFNameAndLName());
		bean.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy());
		bean.setPBFNameAndLName(model.getPBFNameAndLName());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setIclubPersonByAdminId(model.getIclubPersonByAdminId());
		bean.setPCFNameAndLName(model.getPCFNameAndLName());
		bean.setNoOfActiveMembers(model.getNoOfActiveMembers());

		return bean;
	}

	public static IclubCohortModel fromUItoWS(IclubCohortBean bean) {
		IclubCohortModel model = new IclubCohortModel();

		model.setCId(bean.getCId());
		model.setCName(bean.getCName());
		model.setCEmail(bean.getCEmail());
		model.setCInitDt(bean.getCInitDt());
		model.setCFinalizeDt(bean.getCFinalizeDt());
		model.setCTotalContrib(bean.getCTotalContrib());
		model.setCCollectedContrib(bean.getCCollectedContrib());
		model.setCCurMemberCnt(bean.getCCurMemberCnt());
		model.setIclubCohortType(bean.getIclubCohortType());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId());
		model.setPAFNameAndLName(bean.getPAFNameAndLName());
		model.setIclubPersonByCCrtdBy(bean.getIclubPersonByCCrtdBy());
		model.setPBFNameAndLName(bean.getPBFNameAndLName());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIclubPersonByAdminId(bean.getIclubPersonByAdminId());
		model.setPCFNameAndLName(bean.getPCFNameAndLName());
		model.setNoOfActiveMembers(bean.getNoOfActiveMembers());

		return model;
	}

	public static IclubCohortModel fromORMtoWS(IclubCohort bean) {
		IclubCohortModel model = new IclubCohortModel();

		model.setCId(bean.getCId());
		model.setCName(bean.getCName());
		model.setCEmail(bean.getCEmail());
		model.setCInitDt(bean.getCInitDt());
		model.setCFinalizeDt(bean.getCFinalizeDt());
		model.setCTotalContrib(bean.getCTotalContrib());
		model.setCCollectedContrib(bean.getCCollectedContrib());
		model.setCCurMemberCnt(bean.getCCurMemberCnt());
		model.setIclubCohortType(bean.getIclubCohortType() != null ? (bean.getIclubCohortType()).getCtId() : null);
		model.setCtLongDesc(bean.getIclubCohortType() != null ? (bean.getIclubCohortType()).getCtLongDesc() : null);
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId() != null ? (bean.getIclubPersonByCPrimaryUserId()).getPId() : null);
		model.setPAFNameAndLName(bean.getIclubPersonByCPrimaryUserId() != null ? (bean.getIclubPersonByCPrimaryUserId()).getPFName() + " " + bean.getIclubPersonByCPrimaryUserId().getPLName() : null);
		model.setIclubPersonByCCrtdBy(bean.getIclubPersonByCCrtdBy() != null ? (bean.getIclubPersonByCCrtdBy()).getPId() : null);
		model.setPBFNameAndLName(bean.getIclubPersonByCCrtdBy() != null ? (bean.getIclubPersonByCCrtdBy()).getPFName() + " " + bean.getIclubPersonByCCrtdBy().getPLName() : null);
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
		model.setIitLongDesc(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitLongDesc() : null);
		model.setIclubPersonByAdminId(bean.getIclubPersonByCAdminId() != null ? bean.getIclubPersonByCAdminId().getPId() : null);
		model.setPCFNameAndLName(bean.getIclubPersonByCAdminId() != null ? bean.getIclubPersonByCAdminId().getPFName() + " " + bean.getIclubPersonByCAdminId().getPLName() : null);
		model.setNoOfActiveMembers(bean.getIclubPersons() != null ? (bean.getIclubPersons().size()) : 0l);

		return model;
	}

	public static IclubCohort fromWStoORM(IclubCohortModel model, IclubPersonDAO iclubPersonDAO, IclubCohortTypeDAO iclubCohortTypeDAO, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {

		IclubCohort bean = new IclubCohort();

		bean.setCId(model.getCId());
		bean.setCName(model.getCName());
		bean.setCEmail(model.getCEmail());
		bean.setCInitDt(model.getCInitDt());
		bean.setCFinalizeDt(model.getCFinalizeDt());
		bean.setCTotalContrib(model.getCTotalContrib());
		bean.setCCollectedContrib(model.getCCollectedContrib());
		bean.setCCurMemberCnt(model.getCCurMemberCnt());
		bean.setIclubCohortType(model.getIclubCohortType() != null ? iclubCohortTypeDAO.findById(model.getIclubCohortType()) : null);
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId() != null ? iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()) : null);
		bean.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()) : null);
		bean.setIclubPersonByCAdminId(model.getIclubPersonByAdminId() != null ? iclubPersonDAO.findById(model.getIclubPersonByAdminId()) : null);

		return bean;
	}
}
