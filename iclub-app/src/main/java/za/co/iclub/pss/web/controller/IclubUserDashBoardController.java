package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubPaymentBean;
import za.co.iclub.pss.web.bean.IclubPaymentStatusBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPolicyStatusBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubPaymentModel;
import za.co.iclub.pss.ws.model.IclubPaymentStatusModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPolicyStatusModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;

@SuppressWarnings("unchecked")
@ManagedBean(name = "iclubUserDashBoardController")
@SessionScoped
public class IclubUserDashBoardController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1365095132052535408L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubUserDashBoardController.class);
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String PMT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPaymentService/";
	private static final String PCY_STS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyStatusService/";
	private static final String PMT_STS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPaymentStatusService/";
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private static final String CLM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";
	private List<IclubQuoteBean> quoteBeans;
	private List<IclubPolicyBean> policyBeans;
	private List<IclubPolicyStatusBean> policyStatusBeans;
	private List<IclubPaymentStatusBean> paymentStatusBeans;
	private List<IclubClaimBean> claimBeans;
	private List<IclubPaymentBean> paymentBeans;
	private List<IclubPaymentBean> claimPaymentBeans;
	private String sessionUserId;
	private List<IclubClaimStatusBean> claimStatusBeans;
	
	public String getSessionUserId() {
		Object sessUsrId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));
		if (sessUsrId == null)
			sessionUserId = "1";
		else
			sessionUserId = sessUsrId.toString();
		return sessionUserId;
	}
	
	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}
	
	public List<IclubPolicyStatusBean> getPolicyStatusBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PCY_STS_BASE_URL + "list");
		
		List<IclubPolicyStatusModel> models = (ArrayList<IclubPolicyStatusModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyStatusModel.class));
		
		if (models != null && models.size() > 0) {
			policyStatusBeans = new ArrayList<IclubPolicyStatusBean>();
			for (IclubPolicyStatusModel model : models) {
				if (model != null && model.getPsId() != null) {
					
					IclubPolicyStatusBean bean = new IclubPolicyStatusBean();
					
					bean.setPsId(model.getPsId());
					bean.setPsLongDesc(model.getPsLongDesc());
					bean.setPsShortDesc(model.getPsShortDesc());
					bean.setPsStatus(model.getPsStatus());
					
					policyStatusBeans.add(bean);
				}
				
			}
		}
		return policyStatusBeans;
	}
	
	public String viewPolicies() {
		
		return "/pages/policy/dashboard.xhtml?face-redirect=true";
		
	}
	
	public String viewClaims() {
		
		return "/pages/claim/allDashboard.xhtml?face-redirect=true";
		
	}
	
	public String viewQuotes() {
		
		return "/pages/quote/vq.xhtml?face-redirect=true";
		
	}
	
	public void setPolicyStatusBeans(List<IclubPolicyStatusBean> policyStatusBeans) {
		this.policyStatusBeans = policyStatusBeans;
	}
	
	public List<IclubQuoteBean> getQuoteBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/userstatusId/" + getSessionUserId() + "/1");
		Collection<? extends IclubQuoteModel> models = new ArrayList<IclubQuoteModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteModel.class));
		client.close();
		
		quoteBeans = new ArrayList<IclubQuoteBean>();
		if (models != null && models.size() > 0) {
			for (IclubQuoteModel model : models) {
				IclubQuoteBean bean = new IclubQuoteBean();
				
				bean.setQId(model.getQId());
				bean.setQCrtdDt(model.getQCrtdDt());
				bean.setQIsMatched(model.getQIsMatched());
				bean.setQPrevPremium(model.getQPrevPremium());
				bean.setQValidUntil(model.getQValidUntil());
				bean.setQMobile(model.getQMobile());
				bean.setQEmail(model.getQEmail());
				bean.setQGenPremium(model.getQGenPremium());
				bean.setQNumItems(model.getQNumItems());
				bean.setQGenDt(model.getQGenDt());
				bean.setQNumber(model.getQNumber());
				bean.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy());
				bean.setIclubProductType(model.getIclubProductType());
				bean.setIclubProductType(model.getIclubProductType());
				bean.setIclubInsurerMaster(model.getIclubInsurerMaster());
				bean.setIclubCoverType(model.getIclubCoverType());
				bean.setIclubQuoteStatus(model.getIclubQuoteStatus());
				bean.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId());
				
				if (model.getIclubPolicies() != null && model.getIclubPolicies().length > 0) {
					String[] policies = new String[model.getIclubPolicies().length];
					int i = 0;
					for (String policy : model.getIclubPolicies()) {
						policies[i] = policy;
						i++;
					}
				}
				quoteBeans.add(bean);
			}
		}
		return quoteBeans;
	}
	
	public void setQuoteBeans(List<IclubQuoteBean> quoteBeans) {
		this.quoteBeans = quoteBeans;
	}
	
	public List<IclubPolicyBean> getPolicyBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/user/" + getSessionUserId());
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		if (models != null && models.size() > 0) {
			policyBeans = new ArrayList<IclubPolicyBean>();
			int i = 0;
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					i++;
					if (i == 6)
						break;
					policyBeans.add(bean);
				}
				
			}
		}
		return policyBeans;
	}
	
	public void setPolicyBeans(List<IclubPolicyBean> policyBeans) {
		this.policyBeans = policyBeans;
	}
	
	public List<IclubClaimBean> getClaimBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CLM_BASE_URL + "listOrderByCrtDt");
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		claimBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			int i = 0;
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = new IclubClaimBean();
				bean.setCId(model.getCId());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setCValue(model.getCValue());
				bean.setCNumItems(model.getCNumItems());
				bean.setCNumber(model.getCNumber());
				bean.setIclubPolicy(model.getIclubPolicy());
				bean.setIclubClaimStatus(model.getIclubClaimStatus());
				bean.setIclubPerson(model.getIclubPerson());
				i++;
				if (i == 6)
					break;
				claimBeans.add(bean);
			}
		}
		return claimBeans;
	}
	
	public void setClaimBeans(List<IclubClaimBean> claimBeans) {
		this.claimBeans = claimBeans;
	}
	
	public List<IclubPaymentBean> getPaymentBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PMT_BASE_URL + "list");
		Collection<? extends IclubPaymentModel> models = new ArrayList<IclubPaymentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPaymentModel.class));
		client.close();
		paymentBeans = new ArrayList<IclubPaymentBean>();
		if (models != null && models.size() > 0) {
			int i = 0;
			for (IclubPaymentModel model : models) {
				IclubPaymentBean bean = new IclubPaymentBean();
				model.setPId(bean.getPId());
				model.setPCrtdDt(bean.getPCrtdDt());
				model.setPGenDt(bean.getPGenDt());
				model.setPDrCrInd(bean.getPDrCrInd());
				model.setPValue(bean.getPValue());
				
				model.setIclubPolicy(bean.getIclubPolicy());
				model.setIclubPaymentStatus(bean.getIclubPaymentStatus());
				model.setIclubAccount(bean.getIclubAccount());
				model.setIclubClaim(bean.getIclubClaim());
				model.setIclubPerson(bean.getIclubPerson());
				model.setIclubPolicy(bean.getIclubPolicy());
				i++;
				if (i == 6)
					break;
				paymentBeans.add(bean);
			}
		}
		return paymentBeans;
	}
	
	public void setPaymentBeans(List<IclubPaymentBean> paymentBeans) {
		this.paymentBeans = paymentBeans;
	}
	
	public List<IclubClaimStatusBean> getClaimStatusBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CS_BASE_URL + "list");
		Collection<? extends IclubClaimStatusModel> models = new ArrayList<IclubClaimStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimStatusModel.class));
		client.close();
		claimStatusBeans = new ArrayList<IclubClaimStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimStatusModel model : models) {
				IclubClaimStatusBean bean = new IclubClaimStatusBean();
				bean.setCsId(model.getCsId());
				bean.setCsLongDesc(model.getCsLongDesc());
				bean.setCsShortDesc(model.getCsShortDesc());
				bean.setCsStatus(model.getCsStatus());
				claimStatusBeans.add(bean);
			}
		}
		return claimStatusBeans;
	}
	
	public void setClaimStatusBeans(List<IclubClaimStatusBean> claimStatusBeans) {
		this.claimStatusBeans = claimStatusBeans;
	}
	
	public List<IclubPaymentBean> getClaimPaymentBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PMT_BASE_URL + "list");
		Collection<? extends IclubPaymentModel> models = new ArrayList<IclubPaymentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPaymentModel.class));
		client.close();
		claimPaymentBeans = new ArrayList<IclubPaymentBean>();
		if (models != null && models.size() > 0) {
			int i = 0;
			for (IclubPaymentModel model : models) {
				IclubPaymentBean bean = new IclubPaymentBean();
				model.setPId(bean.getPId());
				model.setPCrtdDt(bean.getPCrtdDt());
				model.setPGenDt(bean.getPGenDt());
				model.setPDrCrInd(bean.getPDrCrInd());
				model.setPValue(bean.getPValue());
				
				model.setIclubPolicy(bean.getIclubPolicy());
				model.setIclubPaymentStatus(bean.getIclubPaymentStatus());
				model.setIclubAccount(bean.getIclubAccount());
				model.setIclubClaim(bean.getIclubClaim());
				model.setIclubPerson(bean.getIclubPerson());
				model.setIclubPolicy(bean.getIclubPolicy());
				i++;
				if (i == 6)
					break;
				claimPaymentBeans.add(bean);
			}
		}
		return claimPaymentBeans;
	}
	
	public void setClaimPaymentBeans(List<IclubPaymentBean> claimPaymentBeans) {
		this.claimPaymentBeans = claimPaymentBeans;
	}
	
	public List<IclubPaymentStatusBean> getPaymentStatusBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PMT_STS_BASE_URL + "list");
		
		List<IclubPaymentStatusModel> models = (ArrayList<IclubPaymentStatusModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPaymentStatusModel.class));
		
		if (models != null && models.size() > 0) {
			paymentStatusBeans = new ArrayList<IclubPaymentStatusBean>();
			for (IclubPaymentStatusModel model : models) {
				if (model != null && model.getPsId() != null) {
					
					IclubPaymentStatusBean bean = new IclubPaymentStatusBean();
					
					bean.setPsId(model.getPsId());
					bean.setPsLongDesc(model.getPsLongDesc());
					bean.setPsShortDesc(model.getPsShortDesc());
					bean.setPsStatus(model.getPsStatus());
					
					paymentStatusBeans.add(bean);
				}
				
			}
		}
		return paymentStatusBeans;
	}
	
	public void setPaymentStatusBeans(List<IclubPaymentStatusBean> paymentStatusBeans) {
		this.paymentStatusBeans = paymentStatusBeans;
	}
	
}
