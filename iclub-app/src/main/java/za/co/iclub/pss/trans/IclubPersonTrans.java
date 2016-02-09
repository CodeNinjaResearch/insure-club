package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritalStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubPersonTrans {

	public static IclubPersonBean fromWStoUI(IclubPersonModel model) {

		IclubPersonBean bean = new IclubPersonBean();

		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPDob(model.getPDob());
		bean.setPEmail(model.getPEmail());
		bean.setPFName(model.getPFName());
		bean.setPIdNum(model.getPIdNum());
		bean.setPLName(model.getPLName());
		bean.setPAge(model.getPAge());
		bean.setPMobile(model.getPMobile());
		bean.setPAddress(model.getPAddress());
		bean.setPContactPref(model.getPContactPref());
		bean.setPGender(model.getPGender());
		bean.setPContactPref(model.getPContactPref());
		bean.setPIdExpiryDt(model.getPIdExpiryDt());
		bean.setPInitials(model.getPInitials());
		bean.setPIsPensioner(model.getPIsPensioner());
		bean.setPIdIssueCntry(model.getPIdIssueCntry() != null ? model.getPIdIssueCntry().longValue() : null);
		bean.setPIdIssueDt(model.getPIdIssueDt());
		bean.setPLat(model.getPLat());
		bean.setPLong(model.getPLong());
		bean.setPOccupation(model.getPOccupation());
		bean.setPTitle(model.getPTitle());
		bean.setPZipCd(model.getPZipCd());
		bean.setIclubIdType(model.getIclubIdType());
		bean.setItLongDesc(model.getItLongDesc());
		bean.setIclubMaritalStatus(model.getIclubMaritalStatus());
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setIclubCohort(model.getIclubCohort());
		bean.setCEmail(model.getCEmail());
		bean.setIclubCohortInvite(model.getIclubCohortInvite());

		return bean;
	}

	public static IclubPersonModel fromUItoWS(IclubPersonBean bean) {

		IclubPersonModel model = new IclubPersonModel();

		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPDob(bean.getPDob());
		model.setPEmail(bean.getPEmail());
		model.setPFName(bean.getPFName());
		model.setPIdNum(bean.getPIdNum());
		model.setPLName(bean.getPLName());
		model.setPAge(bean.getPAge());
		model.setPMobile(bean.getPMobile());
		model.setPAddress(bean.getPAddress());
		model.setPContactPref(bean.getPContactPref());
		model.setPGender(bean.getPGender());
		model.setPContactPref(bean.getPContactPref());
		model.setPIdExpiryDt(bean.getPIdExpiryDt());
		model.setPInitials(bean.getPInitials());
		model.setPIsPensioner(bean.getPIsPensioner());
		model.setPIdIssueCntry(bean.getPIdIssueCntry() != null ? bean.getPIdIssueCntry().longValue() : null);
		model.setPIdIssueDt(bean.getPIdIssueDt());
		model.setPLat(bean.getPLat());
		model.setPLong(bean.getPLong());
		model.setPOccupation(bean.getPOccupation());
		model.setPTitle(bean.getPTitle());
		model.setPZipCd(bean.getPZipCd());
		model.setIclubIdType(bean.getIclubIdType());
		model.setItLongDesc(bean.getItLongDesc());
		model.setIclubMaritalStatus(bean.getIclubMaritalStatus());
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setIclubCohort(bean.getIclubCohort());
		model.setCEmail(bean.getCEmail());
		model.setIclubCohortInvite(bean.getIclubCohortInvite());

		return model;
	}

	public static IclubPersonModel fromORMtoWS(IclubPerson bean) {

		IclubPersonModel model = new IclubPersonModel();

		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPDob(bean.getPDob());
		model.setPEmail(bean.getPEmail());
		model.setPFName(bean.getPFName());
		model.setPIdNum(bean.getPIdNum());
		model.setPLName(bean.getPLName());
		model.setPAge(bean.getPAge());
		model.setPMobile(bean.getPMobile());
		model.setPAddress(bean.getPAddress());
		model.setPContactPref(bean.getPContactPref());
		model.setPGender(bean.getPGender());
		model.setPContactPref(bean.getPContactPref());
		model.setPIdExpiryDt(bean.getPIdExpiryDt());
		model.setPInitials(bean.getPInitials());
		model.setPIsPensioner(bean.getPIsPensioner());
		model.setPIdIssueCntry(bean.getPIdIssueCntry());
		model.setPIdIssueDt(bean.getPIdIssueDt());
		model.setPLat(bean.getPLat());
		model.setPLong(bean.getPLong());
		model.setPOccupation(bean.getPOccupation());
		model.setPTitle(bean.getPTitle());
		model.setPZipCd(bean.getPZipCd());
		model.setIclubIdType(bean.getIclubIdType() != null ? (bean.getIclubIdType().getItId()) : null);
		model.setItLongDesc(bean.getIclubIdType() != null ? (bean.getIclubIdType().getItLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setIclubCohort(bean.getIclubCohort() != null ? bean.getIclubCohort().getCId() : null);
		model.setCEmail(bean.getIclubCohort() != null ? bean.getIclubCohort().getCEmail() : null);
		model.setIclubMaritalStatus(bean.getIclubMaritalStatus() != null ? (bean.getIclubMaritalStatus().getMsId()) : null);
		model.setMsLongDesc(bean.getIclubMaritalStatus() != null ? (bean.getIclubMaritalStatus().getMsLongDesc()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		model.setIclubCohortInvite(bean.getIclubCohortInvite() != null ? bean.getIclubCohortInvite().getCiId() : null);

		return model;
	}

	public static IclubPerson fromWStoORM(IclubPersonModel model, IclubIdTypeDAO iclubIdTypeDAO, IclubPersonDAO iclubPersonDAO, IclubMaritalStatusDAO IclubMaritalStatusDAO, IclubCohortDAO iclubCohortDAO, IclubCohortInviteDAO iclubCohortInviteDAO) {

		IclubPerson bean = new IclubPerson();

		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPDob(model.getPDob());
		bean.setPEmail(model.getPEmail());
		bean.setPFName(model.getPFName());
		bean.setPIdNum(model.getPIdNum());
		bean.setPLName(model.getPLName());
		bean.setPMobile(model.getPMobile());
		bean.setPAddress(model.getPAddress());
		bean.setPContactPref(model.getPContactPref());
		bean.setPGender(model.getPGender());
		bean.setPIdNum(model.getPIdNum());
		bean.setPContactPref(model.getPContactPref());
		bean.setPIdExpiryDt(model.getPIdExpiryDt());
		bean.setPIdIssueDt(model.getPIdIssueDt());
		bean.setPInitials(model.getPInitials());
		bean.setPIsPensioner(model.getPIsPensioner());
		bean.setPIdIssueCntry(model.getPIdIssueCntry());
		bean.setPLat(model.getPLat());
		bean.setPLong(model.getPLong());
		bean.setPOccupation(model.getPOccupation());
		bean.setPTitle(model.getPTitle());
		bean.setPAge(model.getPAge());
		bean.setPZipCd(model.getPZipCd());
		bean.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
		bean.setIclubCohort(model.getIclubCohort() != null ? iclubCohortDAO.findById(model.getIclubCohort()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubMaritalStatus(model.getIclubMaritalStatus() != null ? IclubMaritalStatusDAO.findById(model.getIclubMaritalStatus()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubCohortInvite(model.getIclubCohortInvite() != null ? iclubCohortInviteDAO.findById(model.getIclubCohortInvite()) : null);
		return bean;
	}

}
