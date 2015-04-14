package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubBankMasterBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubBankMasterModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubBankMasterController")
@SessionScoped
public class IclubBankMasterController implements Serializable {

	private static final long serialVersionUID = 8245517153102756484L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubBankMasterController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBankMasterService/";
	private static final String P_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private List<IclubBankMasterBean> beans;
	private List<IclubBankMasterBean> dashBoardBeans;
	private List<IclubPersonBean> personBeans;
	private IclubBankMasterBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private boolean showSummaryCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;

	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		else if (viewParam != null && viewParam.longValue() == 3)
			showSummary();

	}

	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		showSummaryCont = false;
		viewParam = 1l;
	}

	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubBankMasterBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		showSummaryCont = false;
		viewParam = 1l;
	}

	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		showSummaryCont = false;
		viewParam = 2l;
	}

	public void showSummary() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showSummary");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = false;
		showSummaryCont = true;
		viewParam = 3l;
	}

	public List<IclubBankMasterBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubBankMasterModel> models = new ArrayList<IclubBankMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubBankMasterBean>();
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

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubBankMasterBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubBankMasterBean();
	}

	public void addIclubBankMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubBankMaster");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubBankMasterModel model = new IclubBankMasterModel();

				model.setBmBankName(bean.getBmBankName());
				model.setBmBankCode(bean.getBmBankCode());
				model.setBmBranchName(bean.getBmBranchName());
				model.setBmBranchCode(bean.getBmBranchCode());
				model.setBmBranchAddress(bean.getBmBranchAddress());
				model.setBmBranchLat(bean.getBmBranchLat());
				model.setBmBranchLong(bean.getBmBranchLong());
				model.setBmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubBankMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubBankMaster");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubBankMasterModel model = new IclubBankMasterModel();

				model.setBmId(bean.getBmId());
				model.setBmBankName(bean.getBmBankName());
				model.setBmBankCode(bean.getBmBankCode());
				model.setBmBranchName(bean.getBmBranchName());
				model.setBmBranchCode(bean.getBmBranchCode());
				model.setBmBranchAddress(bean.getBmBranchAddress());
				model.setBmBranchLat(bean.getBmBranchLat());
				model.setBmBranchLong(bean.getBmBranchLong());
				model.setBmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubBankMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubBankMaster");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getBmId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubBankMasterBean getBean() {
		if (bean == null)
			bean = new IclubBankMasterBean();
		return bean;
	}

	public void setBean(IclubBankMasterBean bean) {
		this.bean = bean;
	}

	public boolean isShowCreateCont() {
		return showCreateCont;
	}

	public void setShowCreateCont(boolean showCreateCont) {
		this.showCreateCont = showCreateCont;
	}

	public boolean isShowViewCont() {
		return showViewCont;
	}

	public void setShowViewCont(boolean showViewCont) {
		this.showViewCont = showViewCont;
	}

	public boolean isShowEditCont() {
		return showEditCont;
	}

	public void setShowEditCont(boolean showEditCont) {
		this.showEditCont = showEditCont;
	}

	public Long getViewParam() {
		return viewParam;
	}

	public void setViewParam(Long viewParam) {
		this.viewParam = viewParam;
	}

	public String getSessionUserId() {
		sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		if (sessionUserId == null)
			sessionUserId = "1";
		return sessionUserId;
	}

	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}

	public String getUserName() {
		userName = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.scname")).toString();
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ResourceBundle getLabelBundle() {
		if (labelBundle == null) {
			labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		}
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	public List<IclubBankMasterBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubBankMasterModel> models = new ArrayList<IclubBankMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
		client.close();
		beans = new ArrayList<IclubBankMasterBean>();
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

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubBankMasterBean> beans) {
		this.beans = beans;
	}

	public boolean isShowSummaryCont() {
		return showSummaryCont;
	}

	public void setShowSummaryCont(boolean showSummaryCont) {
		this.showSummaryCont = showSummaryCont;
	}

	public List<IclubPersonBean> getPersonBeans() {
		WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "list");
		Collection<? extends IclubPersonModel> models = new ArrayList<IclubPersonModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPersonModel.class));
		client.close();
		personBeans = new ArrayList<IclubPersonBean>();
		for (IclubPersonModel model : models) {

			IclubPersonBean bean = new IclubPersonBean();
			bean.setPId(model.getPId());
			bean.setPFName(model.getPFName());
			bean.setPLName(model.getPLName());
			personBeans.add(bean);
		}
		return personBeans;
	}

	public void setPersonBeans(List<IclubPersonBean> personBeans) {
		this.personBeans = personBeans;
	}

}
