package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubDriverBean;
import za.co.iclub.pss.model.ws.IclubDriverModel;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubLicenseCodeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubDriverTrans {
	
	public static IclubDriverBean fromWStoUI(IclubDriverModel model) {
		
		IclubDriverBean bean = new IclubDriverBean();
		
		bean.setDId(model.getDId());
		bean.setDDob(model.getDDob());
		bean.setDIssueDt(model.getDIssueDt());
		bean.setDLicenseNum(model.getDLicenseNum());
		bean.setDName(model.getDName());
		bean.setDCrtdDt(model.getDCrtdDt());
		bean.setDIssueYears(model.getDIssueYears());
		bean.setIclubAccessTypeBByDAccessStatusId(model.getIclubAccessTypeBByDAccessStatusId());
		bean.setAtALongDesc(model.getAtALongDesc());
		bean.setIclubAccessTypeAByDAccessTypeId(model.getIclubAccessTypeAByDAccessTypeId());
		bean.setAtBLongDesc(model.getAtBLongDesc());
		bean.setIclubLicenseCode(model.getIclubLicenseCode());
		bean.setLcDesc(model.getLcDesc());
		bean.setIclubMaritialStatus(model.getIclubMaritialStatus());
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setIclubPersonBByDPersonId(model.getIclubPersonBByDPersonId());
		bean.setIclubPersonAByDCrtdBy(model.getIclubPersonAByDCrtdBy());
		bean.setPBFNameAndLName(model.getPBFNameAndLName());
		bean.setPAFNameAndLName(model.getPAFNameAndLName());
		
		return bean;
	}
	
	public static IclubDriverModel fromUItoWS(IclubDriverBean bean) {
		
		IclubDriverModel model = new IclubDriverModel();
		
		model.setDId(bean.getDId());
		model.setDDob(bean.getDDob());
		model.setDIssueDt(bean.getDIssueDt());
		model.setDLicenseNum(bean.getDLicenseNum());
		model.setDName(bean.getDName());
		model.setDCrtdDt(bean.getDCrtdDt());
		model.setDIssueYears(bean.getDIssueYears());
		model.setIclubAccessTypeBByDAccessStatusId(bean.getIclubAccessTypeBByDAccessStatusId());
		model.setAtALongDesc(bean.getAtALongDesc());
		model.setIclubAccessTypeAByDAccessTypeId(bean.getIclubAccessTypeAByDAccessTypeId());
		model.setAtBLongDesc(bean.getAtBLongDesc());
		model.setIclubLicenseCode(bean.getIclubLicenseCode());
		model.setLcDesc(bean.getLcDesc());
		model.setIclubMaritialStatus(bean.getIclubMaritialStatus());
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setIclubPersonBByDPersonId(bean.getIclubPersonBByDPersonId());
		model.setIclubPersonAByDCrtdBy(bean.getIclubPersonAByDCrtdBy());
		model.setPBFNameAndLName(bean.getPBFNameAndLName());
		model.setPAFNameAndLName(bean.getPAFNameAndLName());
		
		return model;
	}
	
	public static IclubDriverModel fromORMtoWS(IclubDriver bean) {
		
		IclubDriverModel model = new IclubDriverModel();
		
		model.setDId(bean.getDId());
		model.setDDob(bean.getDDob());
		model.setDIssueDt(bean.getDIssueDt());
		model.setDLicenseNum(bean.getDLicenseNum());
		model.setDName(bean.getDName());
		model.setDCrtdDt(bean.getDCrtdDt());
		model.setDIssueYears(bean.getDIssueYears());
		model.setIclubAccessTypeBByDAccessStatusId(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
		model.setAtALongDesc(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtLongDesc() : null);
		model.setIclubAccessTypeBByDAccessStatusId(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtId() : null);
		model.setAtBLongDesc(bean.getIclubAccessTypeByDAccessStatusId() != null ? bean.getIclubAccessTypeByDAccessStatusId().getAtLongDesc() : null);
		model.setIclubLicenseCode(bean.getIclubLicenseCode() != null ? (bean.getIclubLicenseCode().getLcId()) : null);
		model.setLcDesc(bean.getIclubLicenseCode() != null ? (bean.getIclubLicenseCode().getLcDesc()) : null);
		model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);
		model.setMsLongDesc(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsLongDesc()) : null);
		model.setIclubPersonBByDPersonId(bean.getIclubPersonByDPersonId() != null ? (bean.getIclubPersonByDPersonId().getPId()) : null);
		model.setIclubPersonAByDCrtdBy(bean.getIclubPersonByDCrtdBy() != null ? (bean.getIclubPersonByDCrtdBy().getPId()) : null);
		model.setPBFNameAndLName(bean.getIclubPersonByDPersonId() != null ? bean.getIclubPersonByDPersonId().getPFName() + " " + bean.getIclubPersonByDPersonId().getPLName() != null ? bean.getIclubPersonByDPersonId().getPLName() : "" : "");
		model.setPAFNameAndLName(bean.getIclubPersonByDCrtdBy() != null ? bean.getIclubPersonByDCrtdBy().getPFName() + " " + bean.getIclubPersonByDCrtdBy().getPLName() != null ? bean.getIclubPersonByDCrtdBy().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubDriver fromWStoORM(IclubDriverModel model, IclubAccessTypeDAO iclubAccessTypeDAO, IclubLicenseCodeDAO iclubLicenseCodeDAO, IclubMaritialStatusDAO iclubMaritialStatusDAO, IclubPersonDAO iclubPersonDAO) {
		
		IclubDriver bean = new IclubDriver();
		
		bean.setDId(model.getDId());
		bean.setDDob(model.getDDob());
		bean.setDIssueDt(model.getDIssueDt());
		bean.setDLicenseNum(model.getDLicenseNum());
		bean.setDName(model.getDName());
		bean.setDCrtdDt(model.getDCrtdDt());
		bean.setDIssueYears(model.getDIssueYears());
		bean.setIclubAccessTypeByDAccessTypeId(model.getIclubAccessTypeAByDAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeAByDAccessTypeId()) : null);
		bean.setIclubAccessTypeByDAccessStatusId(model.getIclubAccessTypeBByDAccessStatusId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeAByDAccessTypeId()) : null);
		bean.setIclubLicenseCode(model.getIclubLicenseCode() != null ? iclubLicenseCodeDAO.findById(model.getIclubLicenseCode()) : null);
		bean.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
		bean.setIclubPersonByDPersonId(model.getIclubPersonBByDPersonId() != null && !model.getIclubPersonBByDPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonBByDPersonId()) : null);
		bean.setIclubPersonByDCrtdBy(model.getIclubPersonAByDCrtdBy() != null && !model.getIclubPersonAByDCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonAByDCrtdBy()) : null);
		
		return bean;
	}
}
