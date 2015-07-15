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

import za.co.iclub.pss.model.ui.IclubClaimBean;
import za.co.iclub.pss.model.ui.IclubClaimStatusBean;
import za.co.iclub.pss.model.ui.IclubCohortSummaryBean;
import za.co.iclub.pss.model.ui.IclubPaymentBean;
import za.co.iclub.pss.model.ui.IclubPaymentStatusBean;
import za.co.iclub.pss.model.ui.IclubPolicyBean;
import za.co.iclub.pss.model.ui.IclubPolicyStatusBean;
import za.co.iclub.pss.model.ui.IclubQuoteBean;
import za.co.iclub.pss.model.ui.IclubUserDashboardBean;
import za.co.iclub.pss.model.ws.IclubClaimModel;
import za.co.iclub.pss.model.ws.IclubClaimStatusModel;
import za.co.iclub.pss.model.ws.IclubCohortSummaryModel;
import za.co.iclub.pss.model.ws.IclubPaymentModel;
import za.co.iclub.pss.model.ws.IclubPaymentStatusModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.model.ws.IclubPolicyModel;
import za.co.iclub.pss.model.ws.IclubPolicyStatusModel;
import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.trans.IclubClaimStatusTrans;
import za.co.iclub.pss.trans.IclubClaimTrans;
import za.co.iclub.pss.trans.IclubPaymentStatusTrans;
import za.co.iclub.pss.trans.IclubPaymentTrans;
import za.co.iclub.pss.trans.IclubPolicyTrans;
import za.co.iclub.pss.trans.IclubQuoteTrans;
import za.co.iclub.pss.util.IclubWebHelper;

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
	private static final String PCY_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPolicyService/";
	private static final String PMT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPaymentService/";
	private static final String PCY_STS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPolicyStatusService/";
	private static final String PMT_STS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPaymentStatusService/";
	private static final String QUT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubQuoteService/";
	private static final String CLM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubClaimStatusService/";
	private static final String CH_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortService/";
	private static final String P_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	private static final String U_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubUserDashboardService/";
	private List<IclubQuoteBean> quoteBeans;
	private List<IclubPolicyBean> policyBeans;
	private List<IclubPolicyStatusBean> policyStatusBeans;
	private List<IclubPaymentStatusBean> paymentStatusBeans;
	private List<IclubClaimBean> claimBeans;
	private List<IclubPaymentBean> paymentBeans;
	private List<IclubPaymentBean> claimPaymentBeans;
	private String sessionUserId;
	private List<IclubClaimStatusBean> claimStatusBeans;
	private IclubCohortSummaryBean cohortSummaryBean;
	private IclubCohortSummaryBean cohortSummaryUserBean;
	private boolean cohortSummaryFlag;
	private IclubUserDashboardBean userDashboardBean;
	
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
				IclubQuoteBean bean = IclubQuoteTrans.fromWStoUI(model);
				
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
					
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(model);
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
				IclubClaimBean bean = IclubClaimTrans.fromWStoUI(model);
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
				IclubPaymentBean bean = IclubPaymentTrans.fromWStoUI(model);
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
				IclubClaimStatusBean bean = IclubClaimStatusTrans.fromWStoUI(model);
				
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
				IclubPaymentBean bean = IclubPaymentTrans.fromWStoUI(model);
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
					
					IclubPaymentStatusBean bean = IclubPaymentStatusTrans.fromWStoUI(model);
					
					paymentStatusBeans.add(bean);
				}
				
			}
		}
		return paymentStatusBeans;
	}
	
	public void setPaymentStatusBeans(List<IclubPaymentStatusBean> paymentStatusBeans) {
		this.paymentStatusBeans = paymentStatusBeans;
	}
	
	public IclubCohortSummaryBean getCohortSummaryBean() {
		if (cohortSummaryBean == null) {
			cohortSummaryBean = new IclubCohortSummaryBean();
		}
		return cohortSummaryBean;
	}
	
	public void setCohortSummaryBean(IclubCohortSummaryBean cohortSummaryBean) {
		this.cohortSummaryBean = cohortSummaryBean;
	}
	
	public IclubCohortSummaryBean getCohortSummaryUserBean() {
		if (cohortSummaryUserBean == null) {
			cohortSummaryUserBean = new IclubCohortSummaryBean();
			
		}
		cohortSummaryFlag = false;
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null && !IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString().trim().equalsIgnoreCase("")) {
			String personId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
			WebClient personClient = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + personId);
			IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
			WebClient client = IclubWebHelper.createCustomClient(CH_BASE_URL + "getCohortSummaryById/" + personModel.getIclubCohort());
			IclubCohortSummaryModel model = (IclubCohortSummaryModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubCohortSummaryModel.class));
			client.close();
			WebClient userClient = IclubWebHelper.createCustomClient(CH_BASE_URL + "getCohortSummaryById/" + getSessionUserId());
			IclubCohortSummaryModel userModel = (IclubCohortSummaryModel) (userClient.accept(MediaType.APPLICATION_JSON).get(IclubCohortSummaryModel.class));
			cohortSummaryBean = new IclubCohortSummaryBean();
			cohortSummaryUserBean = new IclubCohortSummaryBean();
			
			if (model != null) {
				cohortSummaryFlag = true;
				cohortSummaryBean.setClaimSinceI(model.getClaimSinceI() != null ? model.getClaimSinceI() : 0.0);
				cohortSummaryBean.setClaimsInYear(model.getClaimsInYear() != null ? model.getClaimsInYear() : 0.0);
				cohortSummaryBean.setPremiumForYear(model.getPremiumForYear() != null ? model.getPremiumForYear() : 0.0);
				cohortSummaryBean.setPremiumPaidInYear(model.getPremiumPaidInYear() != null ? model.getPremiumPaidInYear() : 0.0);
				cohortSummaryBean.setPrimumSinceI(model.getPrimumSinceI() != null ? model.getPrimumSinceI() : 0.0);
			}
			if (userModel != null) {
				cohortSummaryFlag = true;
				cohortSummaryUserBean.setClaimSinceI(model.getClaimSinceI() != null ? model.getClaimSinceI() : 0.0);
				cohortSummaryUserBean.setClaimsInYear(model.getClaimsInYear() != null ? model.getClaimsInYear() : 0.0);
				cohortSummaryUserBean.setPremiumForYear(model.getPremiumForYear() != null ? model.getPremiumForYear() : 0.0);
				cohortSummaryUserBean.setPremiumPaidInYear(model.getPremiumPaidInYear() != null ? model.getPremiumPaidInYear() : 0.0);
				cohortSummaryUserBean.setPrimumSinceI(model.getPrimumSinceI() != null ? model.getPrimumSinceI() : 0.0);
			}
			
		}
		return cohortSummaryUserBean;
	}
	
	public void setCohortSummaryUserBean(IclubCohortSummaryBean cohortSummaryUserBean) {
		this.cohortSummaryUserBean = cohortSummaryUserBean;
	}
	
	public boolean isCohortSummaryFlag() {
		return cohortSummaryFlag;
	}
	
	public void setCohortSummaryFlag(boolean cohortSummaryFlag) {
		this.cohortSummaryFlag = cohortSummaryFlag;
	}
	
	public IclubUserDashboardBean getUserDashboardBean() {
		
		if (userDashboardBean == null) {
			userDashboardBean = new IclubUserDashboardBean();
		}
		
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null && !IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString().trim().equalsIgnoreCase("")) {
			String personId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
			WebClient client = IclubWebHelper.createCustomClient(U_BASE_URL + "get/" + personId);
			client.accept(MediaType.APPLICATION_JSON).get();
		}
		return userDashboardBean;
	}
	
	public void setUserDashboardBean(IclubUserDashboardBean userDashboardBean) {
		this.userDashboardBean = userDashboardBean;
	}
	
}
