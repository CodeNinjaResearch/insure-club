package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPaymentBean;
import za.co.iclub.pss.model.ws.IclubPaymentModel;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubPaymentStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;

public class IclubPaymentTrans {
	
	private IclubPolicyDAO iclubPolicyDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubClaimDAO iclubClaimDAO;
	private IclubAccountDAO iclubAccountDAO;
	private IclubPaymentStatusDAO iclubPaymentStatusDAO;
	
	public IclubPaymentBean fromWStoUI(IclubPaymentModel model) {
		
		IclubPaymentBean bean = new IclubPaymentBean();
		
		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPGenDt(model.getPGenDt());
		bean.setPDrCrInd(model.getPDrCrInd());
		bean.setPValue(model.getPValue());
		bean.setIclubPolicy(model.getIclubPolicy());
		bean.setIclubPaymentStatus(model.getIclubPaymentStatus());
		bean.setIclubAccount(model.getIclubAccount());
		bean.setIclubClaim(model.getIclubClaim());
		bean.setCNumber(model.getCNumber());
		bean.setCValue(model.getCValue());
		bean.setIclubPolicy(model.getIclubPolicy());
		bean.setPPremium(model.getPPremium());
		bean.setPNumber(model.getPNumber());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubPaymentModel fromUItoWS(IclubPaymentBean bean) {
		
		IclubPaymentModel model = new IclubPaymentModel();
		
		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPGenDt(bean.getPGenDt());
		model.setPDrCrInd(bean.getPDrCrInd());
		model.setPValue(bean.getPValue());
		model.setIclubPolicy(bean.getIclubPolicy());
		model.setIclubPaymentStatus(bean.getIclubPaymentStatus());
		model.setIclubAccount(bean.getIclubAccount());
		model.setIclubClaim(bean.getIclubClaim());
		model.setCNumber(bean.getCNumber());
		model.setCValue(bean.getCValue());
		model.setIclubPolicy(bean.getIclubPolicy());
		model.setPPremium(bean.getPPremium());
		model.setPNumber(bean.getPNumber());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubPaymentModel fromORMtoWS(IclubPayment bean) {
		
		IclubPaymentModel model = new IclubPaymentModel();
		
		model.setPId(bean.getPId());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setPGenDt(bean.getPGenDt());
		model.setPDrCrInd(bean.getPDrCrInd());
		model.setPValue(bean.getPValue());
		model.setIclubPolicy(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPId()) : null);
		model.setIclubPaymentStatus(bean.getIclubPaymentStatus() != null ? (bean.getIclubPaymentStatus().getPsId()) : null);
		model.setIclubAccount(bean.getIclubAccount() != null ? (bean.getIclubAccount().getAId()) : null);
		model.setIclubClaim(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCId()) : null);
		model.setCNumber(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCNumber()) : null);
		model.setCValue(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCValue()) : null);
		model.setIclubPolicy(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPId()) : null);
		model.setPPremium(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPPremium()) : null);
		model.setPNumber(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPNumber()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubPayment fromWStoORM(IclubPaymentModel model) {
		
		IclubPayment bean = new IclubPayment();
		
		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPGenDt(model.getPGenDt());
		bean.setPDrCrInd(model.getPDrCrInd());
		bean.setPValue(model.getPValue());
		bean.setIclubPolicy(model.getIclubPolicy() != null && !model.getIclubPolicy().trim().equalsIgnoreCase("") ? iclubPolicyDAO.findById(model.getIclubPolicy()) : null);
		bean.setIclubClaim(model.getIclubClaim() != null && !model.getIclubClaim().trim().equalsIgnoreCase("") ? iclubClaimDAO.findById(model.getIclubClaim()) : null);
		bean.setIclubAccount(model.getIclubAccount() != null && !model.getIclubAccount().trim().equalsIgnoreCase("") ? iclubAccountDAO.findById(model.getIclubAccount()) : null);
		bean.setIclubPaymentStatus(model.getIclubPaymentStatus() != null ? iclubPaymentStatusDAO.findById(model.getIclubPaymentStatus()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
	public IclubPolicyDAO getIclubPolicyDAO() {
		return iclubPolicyDAO;
	}
	
	public void setIclubPolicyDAO(IclubPolicyDAO iclubPolicyDAO) {
		this.iclubPolicyDAO = iclubPolicyDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubClaimDAO getIclubClaimDAO() {
		return iclubClaimDAO;
	}
	
	public void setIclubClaimDAO(IclubClaimDAO iclubClaimDAO) {
		this.iclubClaimDAO = iclubClaimDAO;
	}
	
	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}
	
	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}
	
	public IclubPaymentStatusDAO getIclubPaymentStatusDAO() {
		return iclubPaymentStatusDAO;
	}
	
	public void setIclubPaymentStatusDAO(IclubPaymentStatusDAO iclubPaymentStatusDAO) {
		this.iclubPaymentStatusDAO = iclubPaymentStatusDAO;
	}
}
