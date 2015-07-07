package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubClaimBean;
import za.co.iclub.pss.model.ws.IclubClaimModel;
import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.dao.IclubClaimStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;

public class IclubClaimTrans {
	
	public static IclubClaimBean fromWStoUI(IclubClaimModel model) {
		IclubClaimBean bean = new IclubClaimBean();
		bean.setCId(model.getCId());
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setCValue(model.getCValue());
		bean.setCNumItems(model.getCNumItems());
		bean.setCNumber(model.getCNumber());
		bean.setIclubPolicy(model.getIclubPolicy());
		bean.setPPremium(model.getPPremium());
		bean.setPNumber(model.getPNumber());
		bean.setIclubClaimStatus(model.getIclubClaimStatus());
		bean.setCsLongDesc(model.getCsLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		return bean;
	}
	
	public static IclubClaimModel fromUItoWS(IclubClaimBean bean) {
		IclubClaimModel model = new IclubClaimModel();
		model.setCId(bean.getCId());
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setCValue(bean.getCValue());
		model.setCNumItems(bean.getCNumItems());
		model.setCNumber(bean.getCNumber());
		model.setIclubPolicy(bean.getIclubPolicy());
		model.setPPremium(bean.getPPremium());
		model.setPNumber(bean.getPNumber());
		model.setIclubClaimStatus(bean.getIclubClaimStatus());
		model.setCsLongDesc(bean.getCsLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		return model;
	}
	
	public static IclubClaimModel fromORMtoWS(IclubClaim bean) {
		IclubClaimModel model = new IclubClaimModel();
		
		model.setCId(bean.getCId());
		model.setCCrtdDt(bean.getCCrtdDt());
		model.setCValue(bean.getCValue());
		model.setCNumItems(bean.getCNumItems());
		model.setCNumber(bean.getCNumber());
		model.setIclubPolicy(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPId()) : null);
		model.setPPremium(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPPremium()) : null);
		model.setPNumber(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPNumber()) : null);
		model.setIclubClaimStatus(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsId()) : null);
		model.setCsLongDesc(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		return model;
	}
	
	public static IclubClaim fromWStoORM(IclubClaimModel model, IclubClaimStatusDAO iclubClaimStatusDAO, IclubPolicyDAO iclubPolicyDAO, IclubPersonDAO iclubPersonDAO) {
		
		IclubClaim bean = new IclubClaim();
		bean.setCId(model.getCId());
		bean.setCCrtdDt(model.getCCrtdDt());
		bean.setCValue(model.getCValue());
		bean.setCNumItems(model.getCNumItems());
		bean.setCNumber(model.getCNumber());
		bean.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);
		bean.setIclubPolicy(model.getIclubPolicy() != null ? iclubPolicyDAO.findById(model.getIclubPolicy()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
}
