package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubClaimController")
@SessionScoped
public class IclubClaimController implements Serializable {

	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubClaimController.class);
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";

	private List<IclubQuoteBean> quoteBeans;

	private List<IclubClaimStatusBean> claimStatusBeans;

	private List<IclubPolicyBean> policyBeans;

	private List<IclubClaimBean> beans;

	private IclubClaimBean bean;

	private IclubPolicyBean policyBean;

	private boolean quoteFlag;

	private boolean iItemFalg;

	private boolean policyFlag;
	private List<IclubInsuranceItemBean> iItemBeans;

	private ResourceBundle labelBundle;

	private String sessionUserId;

	@SuppressWarnings("unchecked")
	public String quoteListener(IclubQuoteBean quoteBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + quoteBean.getQId());
		iItemFalg = false;
		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemModel model : models) {
				IclubInsuranceItemBean bean = new IclubInsuranceItemBean();

				bean.setIiId(model.getIiId());
				bean.setIiItemId(model.getIiItemId());
				bean.setIiQuoteId(model.getIiQuoteId());
				bean.setIiCrtdDt(model.getIiCrtdDt());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				bean.setIclubPerson(model.getIclubPerson());

				if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
					String[] claimItems = new String[model.getIclubClaimItems().length];
					int i = 0;
					for (String claimItem : model.getIclubClaimItems()) {
						claimItems[i] = claimItem;
						i++;
					}
					bean.setIclubClaimItems(claimItems);
				}
				iItemBeans.add(bean);

			}
			iItemFalg = true;
		}
		return null;
	}

	public String addIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubClaim");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubClaimModel model = new IclubClaimModel();
				model.setCId(UUID.randomUUID().toString());
				model.setCCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setCValue(bean.getCValue());
				model.setCNumItems(bean.getCNumItems());
				model.setCNumber(getCnumber());
				model.setIclubPolicy(policyBean.getPId());
				model.setIclubClaimStatus(bean.getIclubClaimStatus());
				model.setIclubPerson(getSessionUserId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					bean = new IclubClaimBean();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return "";
		}
		return "view.xhtml?faces-redirect=true";
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public Long getCnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));

	}

	public String getSessionUserId() {
		if (sessionUserId == null) {
			sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		}
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public List<IclubInsuranceItemBean> getiItemBeans() {
		return iItemBeans;
	}

	public void setiItemBeans(List<IclubInsuranceItemBean> iItemBeans) {
		this.iItemBeans = iItemBeans;
	}

	@SuppressWarnings("unchecked")
	public IclubPolicyBean getPolicyBean() {

		if (IclubWebHelper.getObjectIntoSession("policyBean") != null) {

			policyBean = (IclubPolicyBean) IclubWebHelper.getObjectIntoSession("policyBean");
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
			IclubWebHelper.addObjectIntoSession("policyBean", null);
			List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
			iItemBeans = new ArrayList<IclubInsuranceItemBean>();
			for (IclubInsuranceItemModel model : models) {
				IclubInsuranceItemBean bean = new IclubInsuranceItemBean();

				bean.setIiId(model.getIiId());
				bean.setIiItemId(model.getIiItemId());
				bean.setIiQuoteId(model.getIiQuoteId());
				bean.setIiCrtdDt(model.getIiCrtdDt());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				bean.setIclubPerson(model.getIclubPerson());

				if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
					String[] claimItems = new String[model.getIclubClaimItems().length];
					int i = 0;
					for (String claimItem : model.getIclubClaimItems()) {
						claimItems[i] = claimItem;
						i++;
					}
					bean.setIclubClaimItems(claimItems);
				}
				iItemBeans.add(bean);
			}
		}
		if (policyBean == null)
			policyBean = new IclubPolicyBean();
		return policyBean;
	}

	public void setPolicyBean(IclubPolicyBean policyBean) {
		this.policyBean = policyBean;
	}

	public IclubClaimBean getBean() {
		if (bean == null)
			bean = new IclubClaimBean();
		return bean;
	}

	public void setBean(IclubClaimBean bean) {
		this.bean = bean;
	}

	public ResourceBundle getLabelBundle() {

		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	@SuppressWarnings("unchecked")
	public String claimToPolicyListener(IclubClaimBean claimBean) {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/user/" + getSessionUserId());

		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));

		quoteFlag = false;
		policyFlag = false;
		if (models != null && models.size() > 0) {
			policyFlag = true;
			policyBeans = new ArrayList<IclubPolicyBean>();
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

					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}

					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					policyBeans.add(bean);
				}

			}
		}

		return "";
	}

	public String policyListener(IclubPolicyBean policyBean) {

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "/list");
		Collection<? extends IclubQuoteModel> models = new ArrayList<IclubQuoteModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteModel.class));
		client.close();

		quoteBeans = new ArrayList<IclubQuoteBean>();
		quoteFlag = true;
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

		return "";
	}

	public List<IclubClaimStatusBean> getClaimStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(CS_BASE_URL + "list");
		Collection<? extends IclubClaimStatusModel> models = new ArrayList<IclubClaimStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimStatusModel.class));
		client.close();
		claimStatusBeans = new ArrayList<IclubClaimStatusBean>();
		for (IclubClaimStatusModel model : models) {
			IclubClaimStatusBean bean = new IclubClaimStatusBean();
			bean.setCsId(model.getCsId());
			bean.setCsLongDesc(model.getCsLongDesc());
			bean.setCsShortDesc(model.getCsShortDesc());
			bean.setCsStatus(model.getCsStatus());
			claimStatusBeans.add(bean);
		}
		return claimStatusBeans;
	}

	public void setClaimStatusBeans(List<IclubClaimStatusBean> claimStatusBeans) {
		this.claimStatusBeans = claimStatusBeans;
	}

	public List<IclubClaimBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		beans = new ArrayList<IclubClaimBean>();
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

			if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
				String[] payments = new String[model.getIclubPayments().length];
				int i = 0;
				for (String payment : model.getIclubPayments()) {
					payments[i] = payment;
					i++;
				}

				bean.setIclubPayments(payments);
			}

			if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
				String[] claimItems = new String[model.getIclubClaimItems().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItems()) {
					claimItems[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItems(claimItems);
			}
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubClaimBean> beans) {
		this.beans = beans;
	}

	public List<IclubPolicyBean> getPolicyBeans() {

		return policyBeans;
	}

	public void setPolicyBeans(List<IclubPolicyBean> policyBeans) {
		this.policyBeans = policyBeans;
	}

	public List<IclubQuoteBean> getQuoteBeans() {

		return quoteBeans;
	}

	public void setQuoteBeans(List<IclubQuoteBean> quoteBeans) {
		this.quoteBeans = quoteBeans;
	}

	public boolean isPolicyFlag() {
		return policyFlag;
	}

	public void setPolicyFlag(boolean policyFlag) {
		this.policyFlag = policyFlag;
	}

	public boolean isQuoteFlag() {
		return quoteFlag;
	}

	public void setQuoteFlag(boolean quoteFlag) {
		this.quoteFlag = quoteFlag;
	}

	public boolean isiItemFalg() {
		return iItemFalg;
	}

	public void setiItemFalg(boolean iItemFalg) {
		this.iItemFalg = iItemFalg;
	}

}
