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

import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubLicenseCodeController")
@SessionScoped
public class IclubLicenseCodeController implements Serializable {

	private static final long serialVersionUID = -8915296782572192096L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubLicenseCodeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLicenseCodeService/";
	private List<IclubLicenseCodeBean> beans;
	private List<IclubLicenseCodeBean> dashBoardBeans;
	private IclubLicenseCodeBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
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

	}

	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		viewParam = 1l;
	}

	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubLicenseCodeBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		viewParam = 1l;
	}

	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		viewParam = 2l;
	}

	public List<IclubLicenseCodeBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubLicenseCodeModel> models = new ArrayList<IclubLicenseCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubLicenseCodeModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubLicenseCodeBean>();
		for (IclubLicenseCodeModel model : models) {

			IclubLicenseCodeBean bean = new IclubLicenseCodeBean();

			bean.setLcId(model.getLcId());
			bean.setLcCrtdDt(model.getLcCrtdDt());
			bean.setLcDesc(model.getLcDesc());
			bean.setLcStatus(model.getLcStatus());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setLcCategory(model.getLcCategory());
			if (model.getIclubDrivers() != null && model.getIclubDrivers().length > 0) {
				String[] drivers = new String[model.getIclubDrivers().length];
				int i = 0;
				for (String iclubMessage : model.getIclubDrivers()) {
					drivers[i] = iclubMessage;
					i++;
				}
				bean.setIclubDrivers(drivers);
			}
			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubLicenseCodeBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubLicenseCodeBean();
	}

	public void addIclubLicenseCode() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubLicenseCode");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubLicenseCodeModel model = new IclubLicenseCodeModel();

				model.setLcCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setLcDesc(bean.getLcDesc());
				model.setLcStatus(bean.getLcStatus());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setLcCategory(bean.getLcCategory());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubLicenseCode() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubLicenseCode");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubLicenseCodeModel model = new IclubLicenseCodeModel();

				model.setLcId(bean.getLcId());
				model.setLcCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setLcDesc(bean.getLcDesc());
				model.setLcStatus(bean.getLcStatus());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setLcCategory(bean.getLcCategory());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubLicenseCode() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubLicenseCode");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getLcId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("licensecode") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubLicenseCodeBean getBean() {
		if (bean == null)
			bean = new IclubLicenseCodeBean();
		return bean;
	}

	public void setBean(IclubLicenseCodeBean bean) {
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

	public List<IclubLicenseCodeBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubLicenseCodeModel> models = new ArrayList<IclubLicenseCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubLicenseCodeModel.class));
		client.close();
		beans = new ArrayList<IclubLicenseCodeBean>();
		for (IclubLicenseCodeModel model : models) {

			IclubLicenseCodeBean bean = new IclubLicenseCodeBean();

			bean.setLcId(model.getLcId());
			bean.setLcCrtdDt(model.getLcCrtdDt());
			bean.setLcDesc(model.getLcDesc());
			bean.setLcStatus(model.getLcStatus());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setLcCategory(model.getLcCategory());
			if (model.getIclubDrivers() != null && model.getIclubDrivers().length > 0) {
				String[] drivers = new String[model.getIclubDrivers().length];
				int i = 0;
				for (String iclubMessage : model.getIclubDrivers()) {
					drivers[i] = iclubMessage;
					i++;
				}
				bean.setIclubDrivers(drivers);
			}

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubLicenseCodeBean> beans) {
		this.beans = beans;
	}

}
