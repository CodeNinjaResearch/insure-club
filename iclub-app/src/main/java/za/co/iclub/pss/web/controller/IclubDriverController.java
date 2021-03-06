package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import za.co.iclub.pss.model.ui.IclubAccessTypeBean;
import za.co.iclub.pss.model.ui.IclubDriverBean;
import za.co.iclub.pss.model.ui.IclubLicenseCodeBean;
import za.co.iclub.pss.model.ui.IclubMaritalStatusBean;
import za.co.iclub.pss.model.ws.IclubAccessTypeModel;
import za.co.iclub.pss.model.ws.IclubDriverModel;
import za.co.iclub.pss.model.ws.IclubLicenseCodeModel;
import za.co.iclub.pss.model.ws.IclubMaritalStatusModel;
import za.co.iclub.pss.trans.IclubAccessTypeTrans;
import za.co.iclub.pss.trans.IclubDriverTrans;
import za.co.iclub.pss.trans.IclubLicenseCodeTrans;
import za.co.iclub.pss.trans.IclubMaritalStatusTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubDriverController")
@SessionScoped
public class IclubDriverController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubDriverController.class);
	private static final String AEST_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubAccessTypeService/";
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubDriverService/";
	private static final String LIC_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLicenseCodeService/";
	private static final String MS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubMaritalStatusService/";
	private List<IclubDriverBean> beans;
	private IclubDriverBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubLicenseCodeBean> licenseCodeBeans;
	private List<IclubMaritalStatusBean> maritalStatusBeans;

	private ResourceBundle labelBundle;
	private String sessionUserId;

	public void addIclubDriver() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubDriver");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubDriverModel model = IclubDriverTrans.fromUItoWS(bean);

				model.setDId(UUID.randomUUID().toString());

				model.setDIssueDt(new Date(System.currentTimeMillis()));

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubDriver() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubDriver");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubDriverModel model = IclubDriverTrans.fromUItoWS(bean);

				model.setDIssueDt(new Date(System.currentTimeMillis()));

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubDriver() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubDriver");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getDId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicletype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubDriverBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubDriverBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getDLicenseNum() == null || bean.getDLicenseNum().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("License Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubLicenseCode() == null) {
			IclubWebHelper.addMessage(getLabelBundle().getString("Please select License Code"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getDIssueDt() == null) {
			IclubWebHelper.addMessage(getLabelBundle().getString("Issue Date Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getDDob() == null) {
			IclubWebHelper.addMessage(getLabelBundle().getString("DOB Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.calculateYearDiff(bean.getDDob().getTime()) <= 18) {
			IclubWebHelper.addMessage(("You must be over 18 years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubMaritalStatus() == null) {
			IclubWebHelper.addMessage(getLabelBundle().getString("Please select Marital Status"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getDIssueDt() == null) {
			IclubWebHelper.addMessage(("Please Select IssueDate"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.isCurrentDate(bean.getDIssueDt().getTime())) {
			IclubWebHelper.addMessage(("Issue Date less than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubDriverBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubDriverModel> models = new ArrayList<IclubDriverModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubDriverModel.class));
		client.close();
		beans = new ArrayList<IclubDriverBean>();
		if (models != null && models.size() > 0) {
			for (IclubDriverModel model : models) {
				IclubDriverBean bean = IclubDriverTrans.fromWStoUI(model);

				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubDriverBean> beans) {
		this.beans = beans;
	}

	public IclubDriverBean getBean() {
		if (bean == null)
			bean = new IclubDriverBean();
		return bean;
	}

	public void setBean(IclubDriverBean bean) {
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

	public List<IclubAccessTypeBean> getAccessTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(AEST_BASE_URL + "list");
		Collection<? extends IclubAccessTypeModel> models = new ArrayList<IclubAccessTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccessTypeModel.class));
		client.close();
		accessTypeBeans = new ArrayList<IclubAccessTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccessTypeModel model : models) {
				IclubAccessTypeBean bean = IclubAccessTypeTrans.fromWStoUI(model);
				accessTypeBeans.add(bean);
			}
		}
		return accessTypeBeans;
	}

	public void setAccessTypeBeans(List<IclubAccessTypeBean> accessTypeBeans) {
		this.accessTypeBeans = accessTypeBeans;
	}

	public List<IclubLicenseCodeBean> getLicenseCodeBeans() {

		licenseCodeBeans = new ArrayList<IclubLicenseCodeBean>();
		WebClient client = IclubWebHelper.createCustomClient(LIC_BASE_URL + "list");
		Collection<? extends IclubLicenseCodeModel> models = new ArrayList<IclubLicenseCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubLicenseCodeModel.class));
		client.close();

		if (models != null && models.size() > 0) {
			for (IclubLicenseCodeModel model : models) {

				IclubLicenseCodeBean bean = IclubLicenseCodeTrans.fromWStoUI(model);

				licenseCodeBeans.add(bean);
			}
		}

		return licenseCodeBeans;
	}

	public void setLicenseCodeBeans(List<IclubLicenseCodeBean> licenseCodeBeans) {
		this.licenseCodeBeans = licenseCodeBeans;
	}

	public List<IclubMaritalStatusBean> getMaritalStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(MS_BASE_URL + "list");
		Collection<? extends IclubMaritalStatusModel> models = new ArrayList<IclubMaritalStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMaritalStatusModel.class));
		client.close();
		maritalStatusBeans = new ArrayList<IclubMaritalStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubMaritalStatusModel model : models) {
				IclubMaritalStatusBean bean = IclubMaritalStatusTrans.fromWStoUI(model);

				maritalStatusBeans.add(bean);
			}
		}
		return maritalStatusBeans;
	}

	public void setMaritalStatusBeans(List<IclubMaritalStatusBean> maritalStatusBeans) {
		this.maritalStatusBeans = maritalStatusBeans;
	}
}
