package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubAccountBean;
import za.co.iclub.pss.web.bean.IclubAccountTypeBean;
import za.co.iclub.pss.web.bean.IclubBankMasterBean;
import za.co.iclub.pss.web.bean.IclubOwnerTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccountModel;
import za.co.iclub.pss.ws.model.IclubAccountTypeModel;
import za.co.iclub.pss.ws.model.IclubBankMasterModel;
import za.co.iclub.pss.ws.model.IclubOwnerTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubAccountController")
@SessionScoped
public class IclubAccountsController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubAccountsController.class);
	private static final String ACCT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountTypeService/";
	private static final String OWNT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOwnerTypeService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountService/";
	private static final String BNKM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBankMasterService/";
	private List<IclubAccountBean> beans;
	private List<IclubAccountTypeBean> accountTypeBeans;
	private List<IclubOwnerTypeBean> ownerTypeBeans;
	private List<IclubBankMasterBean> bankMasterBeans;
	private List<String> bankNames;
	private String bankName;
	private IclubAccountBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	private String sessionUserId;

	public void addIclubAccount() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubAccount");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubAccountModel model = new IclubAccountModel();

				model.setAId(UUID.randomUUID().toString());
				model.setAAccNum(bean.getAAccNum());
				model.setACrtdDt(bean.getACrtdDt());
				model.setAOwnerId(bean.getAOwnerId());
				model.setIclubBankMaster(bean.getIclubBankMaster());
				model.setIclubAccountType(bean.getIclubAccountType());
				model.setIclubOwnerType(bean.getIclubOwnerType());
				model.setIclubPerson(bean.getIclubPerson());
				model.setAStatus(bean.getAStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubAccount() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubAccount");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubAccountModel model = new IclubAccountModel();
				model.setAId(bean.getAId());
				model.setAAccNum(bean.getAAccNum());
				model.setACrtdDt(bean.getACrtdDt());
				model.setAOwnerId(bean.getAOwnerId());
				model.setIclubBankMaster(bean.getIclubBankMaster());
				model.setIclubAccountType(bean.getIclubAccountType());
				model.setIclubOwnerType(bean.getIclubOwnerType());
				model.setIclubPerson(getSessionUserId());
				model.setAStatus(bean.getAStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubAccount() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubAccount");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getAId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("account") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void bankNameValueChangeListener() {
		if (bankName != null) {
			loadBankMasterBeans(bankName);
		} else {
			if (bankMasterBeans != null) {
				bankMasterBeans.clear();
			}

		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubAccountBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubAccountBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
		try {

			WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "get/" + bean.getIclubBankMaster());
			IclubBankMasterModel model = (IclubBankMasterModel) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
			client.close();

			bankName = model.getBmBankName();
			loadBankMasterBeans(bankName);

		} catch (Exception e) {
		}

	}

	public void loadBankMasterBeans(String bankName) {
		WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "get/bankName/" + bankName);
		Collection<? extends IclubBankMasterModel> models = new ArrayList<IclubBankMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
		client.close();
		bankMasterBeans = new ArrayList<IclubBankMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubBankMasterModel model : models) {

				IclubBankMasterBean bean = new IclubBankMasterBean();

				bean.setBmId(model.getBmId());
				bean.setBmBankName(model.getBmBankName());
				bean.setBmBankCode(model.getBmBankCode());
				bean.setBmBranchName(model.getBmBranchName());
				bean.setBmBranchCode(model.getBmBranchCode());
				bean.setBmBranchAddress(model.getBmBranchAddress());
				bean.setBmBranchLat(model.getBmBranchLat());
				bean.setBmBranchLong(model.getBmBranchLong());
				bean.setBmCrtdDt(model.getBmCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());
				if (model.getIclubAccounts() != null && model.getIclubAccounts().length > 0) {

					String[] accounts = new String[model.getIclubAccounts().length];

					int i = 0;
					for (String account : model.getIclubAccounts()) {
						accounts[i] = account;
					}
					bean.setIclubAccounts(accounts);
				}

				bankMasterBeans.add(bean);
			}
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getAStatus() == null || bean.getAStatus().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getAAccNum() == null || bean.getAAccNum().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Account Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubAccountType() == null) {
			IclubWebHelper.addMessage(("Please select Account Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubBankMaster() == null) {
			IclubWebHelper.addMessage(("Please select Branch Name"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubAccountBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubAccountModel> models = new ArrayList<IclubAccountModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccountModel.class));
		client.close();
		beans = new ArrayList<IclubAccountBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccountModel model : models) {
				IclubAccountBean bean = new IclubAccountBean();
				bean.setAId(model.getAId());
				bean.setAAccNum(model.getAAccNum());
				bean.setACrtdDt(model.getACrtdDt());
				bean.setAOwnerId(model.getAOwnerId());
				bean.setIclubBankMaster(model.getIclubBankMaster());
				bean.setIclubAccountType(model.getIclubAccountType());
				bean.setIclubOwnerType(model.getIclubOwnerType());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setAStatus(model.getAStatus());

				if (model.getIclubPolicies() != null && model.getIclubPolicies().length > 0) {
					String[] policies = new String[model.getIclubPolicies().length];
					int i = 0;
					for (String policy : model.getIclubPolicies()) {

						policies[i] = policy;
						i++;
					}
					bean.setIclubPolicies(policies);
				}

				if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
					String[] payments = new String[model.getIclubPayments().length];
					int i = 0;
					for (String payment : model.getIclubPayments()) {

						payments[i] = payment;
						i++;
					}
					bean.setIclubPayments(payments);
				}
				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubAccountBean> beans) {
		this.beans = beans;
	}

	public IclubAccountBean getBean() {
		if (bean == null)
			bean = new IclubAccountBean();
		return bean;
	}

	public void setBean(IclubAccountBean bean) {
		this.bean = bean;
	}

	public boolean isShowAddPanel() {
		return showAddPanel;
	}

	public void setShowAddPanel(boolean showAddPanel) {
		this.showAddPanel = showAddPanel;
	}

	public boolean isShowModPanel() {
		return showModPanel;
	}

	public void setShowModPanel(boolean showModPanel) {
		this.showModPanel = showModPanel;
	}

	public ResourceBundle getLabelBundle() {

		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	public List<IclubAccountTypeBean> getAccountTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(ACCT_BASE_URL + "list");
		Collection<? extends IclubAccountTypeModel> models = new ArrayList<IclubAccountTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccountTypeModel.class));
		client.close();
		accountTypeBeans = new ArrayList<IclubAccountTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccountTypeModel model : models) {
				IclubAccountTypeBean bean = new IclubAccountTypeBean();
				bean.setAtId(model.getAtId());
				bean.setAtLongDesc(model.getAtLongDesc());
				bean.setAtShortDesc(model.getAtShortDesc());
				bean.setAtStatus(model.getAtStatus());
				accountTypeBeans.add(bean);
			}
		}
		return accountTypeBeans;
	}

	public void setAccountTypeBeans(List<IclubAccountTypeBean> accountTypeBeans) {
		this.accountTypeBeans = accountTypeBeans;
	}

	public List<IclubOwnerTypeBean> getOwnerTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(OWNT_BASE_URL + "list");
		Collection<? extends IclubOwnerTypeModel> models = new ArrayList<IclubOwnerTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOwnerTypeModel.class));
		client.close();
		ownerTypeBeans = new ArrayList<IclubOwnerTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubOwnerTypeModel model : models) {
				IclubOwnerTypeBean bean = new IclubOwnerTypeBean();
				bean.setOtId(model.getOtId());
				bean.setOtLongDesc(model.getOtLongDesc());
				bean.setOtShortDesc(model.getOtShortDesc());
				bean.setOtStatus(model.getOtStatus());

				if (model.getIclubAccounts() != null && model.getIclubAccounts().length > 0) {

					bean.setIclubAccounts(model.getIclubAccounts());
				}

				ownerTypeBeans.add(bean);
			}
		}
		return ownerTypeBeans;
	}

	public void setOwnerTypeBeans(List<IclubOwnerTypeBean> ownerTypeBeans) {
		this.ownerTypeBeans = ownerTypeBeans;
	}

	public List<IclubBankMasterBean> getBankMasterBeans() {

		if (bankMasterBeans == null) {
			bankMasterBeans = new ArrayList<IclubBankMasterBean>();
		}
		return bankMasterBeans;
	}

	public void setBankMasterBeans(List<IclubBankMasterBean> bankMasterBeans) {
		this.bankMasterBeans = bankMasterBeans;
	}

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

	@SuppressWarnings("unchecked")
	public List<String> getBankNames() {
		WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "list/banknames");
		Collection<? extends String> models = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));
		client.close();
		bankNames = (List<String>) models;
		return bankNames;
	}

	public void setBankNames(List<String> bankNames) {
		this.bankNames = bankNames;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
