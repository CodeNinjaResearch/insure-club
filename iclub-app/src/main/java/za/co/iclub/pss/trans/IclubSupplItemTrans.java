package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSupplItemBean;
import za.co.iclub.pss.model.ws.IclubSupplItemModel;
import za.co.iclub.pss.orm.bean.IclubSupplItem;
import za.co.iclub.pss.orm.dao.IclubAssessmentTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;

public class IclubSupplItemTrans {

	public static IclubSupplItemBean fromWStoUI(IclubSupplItemModel model) {

		IclubSupplItemBean bean = new IclubSupplItemBean();

		bean.setSiId(model.getSiId());
		bean.setIclubAssessmentType(model.getIclubAssessmentType());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setIitLongDesc(model.getIitLongDesc());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setSmRegNum(model.getSmRegNum());
		bean.setIclubSupplMaster(model.getIclubSupplMaster());
		bean.setSiCrtdDt(model.getSiCrtdDt());
		bean.setSiAssessNumber(model.getSiAssessNumber());
		bean.setSiItemId(model.getSiItemId());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubSupplItemModel fromUItoWS(IclubSupplItemBean bean) {

		IclubSupplItemModel model = new IclubSupplItemModel();

		model.setSiId(bean.getSiId());
		model.setIclubAssessmentType(bean.getIclubAssessmentType());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setIitLongDesc(bean.getIitLongDesc());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setSmRegNum(bean.getSmRegNum());
		model.setIclubSupplMaster(bean.getIclubSupplMaster());
		model.setSiCrtdDt(bean.getSiCrtdDt());
		model.setSiAssessNumber(bean.getSiAssessNumber());
		model.setSiItemId(bean.getSiItemId());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubSupplItemModel fromORMtoWS(IclubSupplItem bean) {

		IclubSupplItemModel model = new IclubSupplItemModel();

		model.setSiId(bean.getSiId());
		model.setIclubAssessmentType(bean.getIclubAssessmentType() != null ? bean.getIclubAssessmentType().getAtId() : null);
		model.setAtLongDesc(bean.getIclubAssessmentType() != null ? bean.getIclubAssessmentType().getAtLongDesc() : null);
		model.setIitLongDesc(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitLongDesc() : null);
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
		model.setSmRegNum(bean.getIclubSupplMaster() != null ? bean.getIclubSupplMaster().getSmRegNum() : null);
		model.setIclubSupplMaster(bean.getIclubSupplMaster() != null ? bean.getIclubSupplMaster().getSmId() : null);
		model.setSiCrtdDt(bean.getSiCrtdDt());
		model.setSiAssessNumber(bean.getSiAssessNumber());
		model.setSiItemId(bean.getSiItemId());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubSupplItem fromWStoORM(IclubSupplItemModel model, IclubAssessmentTypeDAO iclubAssessmentTypeDAO, IclubPersonDAO iclubPersonDAO, IclubSupplMasterDAO iclubSupplMasterDAO, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {

		IclubSupplItem bean = new IclubSupplItem();

		bean.setSiId(model.getSiId());
		bean.setIclubAssessmentType(model.getIclubAssessmentType() != null ? iclubAssessmentTypeDAO.findById(model.getIclubAssessmentType()) : null);
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setIclubSupplMaster(model.getIclubSupplMaster() != null ? iclubSupplMasterDAO.findById(model.getIclubSupplMaster()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setSiCrtdDt(model.getSiCrtdDt());
		bean.setSiAssessNumber(model.getSiAssessNumber());
		bean.setSiItemId(model.getSiItemId());

		return bean;
	}
}
