package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPolicyBean;
import za.co.iclub.pss.model.ws.IclubPolicyModel;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyStatusDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;

public class IclubPolicyTrans {
	
	private IclubPersonDAO iclubPersonDAO;
	private IclubPolicyStatusDAO iclubPolicyStatusDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubAccountDAO iclubAccountDAO;
	
	public IclubPolicyBean fromWStoUI(IclubPolicyModel model) {
		
		IclubPolicyBean bean = new IclubPolicyBean();
		
		bean.setPId(model.getPId());
		bean.setPProrataPrm(model.getPProrataPrm());
		bean.setPPremium(model.getPPremium());
		bean.setPNumber(model.getPNumber());
		bean.setPDebitDt(model.getPDebitDt());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setIclubAccount(model.getIclubAccount());
		bean.setAAccNum(model.getAAccNum());
		bean.setIclubQuote(model.getIclubQuote());
		bean.setQNumber(model.getQNumber());
		bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
		bean.setPsLongDesc(model.getPsLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubPolicyModel fromUItoWS(IclubPolicyBean bean) {
		
		IclubPolicyModel model = new IclubPolicyModel();
		
		model.setPId(bean.getPId());
		model.setPProrataPrm(bean.getPProrataPrm());
		model.setPPremium(bean.getPPremium());
		model.setPNumber(bean.getPNumber());
		model.setPDebitDt(bean.getPDebitDt());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setIclubAccount(bean.getIclubAccount());
		model.setAAccNum(bean.getAAccNum());
		model.setIclubQuote(bean.getIclubQuote());
		model.setQNumber(bean.getQNumber());
		model.setIclubPolicyStatus(bean.getIclubPolicyStatus());
		model.setPsLongDesc(bean.getPsLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubPolicyModel fromORMtoWS(IclubPolicy bean) {
		
		IclubPolicyModel model = new IclubPolicyModel();
		
		model.setPId(bean.getPId());
		model.setPProrataPrm(bean.getPProrataPrm());
		model.setPPremium(bean.getPPremium());
		model.setPNumber(bean.getPNumber());
		model.setPDebitDt(bean.getPDebitDt());
		model.setPCrtdDt(bean.getPCrtdDt());
		model.setIclubAccount(bean.getIclubAccount() != null ? (bean.getIclubAccount().getAId()) : null);
		model.setAAccNum(bean.getIclubAccount() != null ? (bean.getIclubAccount().getAAccNum()) : null);
		model.setIclubQuote(bean.getIclubQuote() != null ? (bean.getIclubQuote().getQId()) : null);
		model.setQNumber(bean.getIclubQuote() != null ? (bean.getIclubQuote().getQNumber()) : null);
		model.setIclubPolicyStatus(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsId()) : null);
		model.setPsLongDesc(bean.getIclubPolicyStatus() != null ? (bean.getIclubPolicyStatus().getPsLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubPolicy fromWStoORM(IclubPolicyModel model) {
		
		IclubPolicy bean = new IclubPolicy();
		
		bean.setPId(model.getPId());
		bean.setPProrataPrm(model.getPProrataPrm());
		bean.setPPremium(model.getPPremium());
		bean.setPNumber(model.getPNumber());
		bean.setPDebitDt(model.getPDebitDt());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setIclubPolicyStatus(model.getIclubPolicyStatus() != null ? iclubPolicyStatusDAO.findById(model.getIclubPolicyStatus()) : null);
		bean.setIclubQuote(model.getIclubQuote() != null ? iclubQuoteDAO.findById(model.getIclubQuote()) : null);
		bean.setIclubAccount(model.getIclubAccount() != null ? iclubAccountDAO.findById(model.getIclubAccount()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubPolicyStatusDAO getIclubPolicyStatusDAO() {
		return iclubPolicyStatusDAO;
	}
	
	public void setIclubPolicyStatusDAO(IclubPolicyStatusDAO iclubPolicyStatusDAO) {
		this.iclubPolicyStatusDAO = iclubPolicyStatusDAO;
	}
	
	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}
	
	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
	}
	
	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}
	
	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}
	
}
