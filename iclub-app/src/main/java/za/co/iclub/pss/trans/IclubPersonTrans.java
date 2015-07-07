package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubPersonTrans {
	
	private IclubIdTypeDAO iclubIdTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;
	
	public IclubPersonBean fromWStoUI(IclubPersonModel model) {
		
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
		bean.setPIdIssueCntry(model.getPIdIssueCntry());
		bean.setPIdIssueDt(model.getPIdIssueDt());
		bean.setPLat(model.getPLat());
		bean.setPLong(model.getPLong());
		bean.setPOccupation(model.getPOccupation());
		bean.setPTitle(model.getPTitle());
		bean.setPZipCd(model.getPZipCd());
		bean.setIclubIdType(model.getIclubIdType());
		bean.setItLongDesc(model.getItLongDesc());
		bean.setIclubMaritialStatus(model.getIclubMaritialStatus());
		bean.setMsLongDesc(model.getMsLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubPersonModel fromUItoWS(IclubPersonBean bean) {
		
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
		model.setIclubIdType(bean.getIclubIdType());
		model.setItLongDesc(bean.getItLongDesc());
		model.setIclubMaritialStatus(bean.getIclubMaritialStatus());
		model.setMsLongDesc(bean.getMsLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubPersonModel fromORMtoWS(IclubPerson bean) {
		
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
		model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);
		model.setMsLongDesc(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsLongDesc()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubPerson fromWStoORM(IclubPersonModel model) {
		
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
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
	public IclubIdTypeDAO getIclubIdTypeDAO() {
		return iclubIdTypeDAO;
	}
	
	public void setIclubIdTypeDAO(IclubIdTypeDAO iclubIdTypeDAO) {
		this.iclubIdTypeDAO = iclubIdTypeDAO;
	}
	
	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}
	
	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
}
