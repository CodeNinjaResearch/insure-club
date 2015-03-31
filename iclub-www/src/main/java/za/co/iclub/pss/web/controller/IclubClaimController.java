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
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubClaimController")
@SessionScoped
public class IclubClaimController implements Serializable {

	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubClaimController.class);
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";

	private List<IclubClaimStatusBean> claimStatusBeans;

	private List<IclubClaimBean> beans;

	private IclubClaimBean bean;

	private IclubPolicyBean policyBean;

	private List<IclubInsuranceItemBean> iItemBeans;

	private ResourceBundle labelBundle;

	private String sessionUserId;

	@SuppressWarnings("unchecked")
	public String policyListener(IclubPolicyBean policyBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());

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

		policyBean = (IclubPolicyBean) IclubWebHelper.getObjectIntoSession("policyBean");

		if (policyBean != null && policyBean.getPId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());

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

}
