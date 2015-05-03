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

import za.co.iclub.pss.web.bean.IclubAccessTypeBean;
import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubDriverController")
@SessionScoped
public class IclubDriverController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubDriverController.class);
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDriverService/";
	private static final String LIC_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLicenseCodeService/";
	private static final String MS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private List<IclubDriverBean> beans;
	private IclubDriverBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubLicenseCodeBean> licenseCodeBeans;
	private List<IclubMaritialStatusBean> maritialStatusBeans;

	private ResourceBundle labelBundle;
	private String sessionUserId;

	public void addIclubDriver() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubDriver");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubDriverModel model = new IclubDriverModel();

				model.setDId(UUID.randomUUID().toString());
				model.setDDob(bean.getDDob());
				model.setDIssueDt(new Date(System.currentTimeMillis()));
				model.setDLicenseNum(bean.getDLicenseNum());
				model.setDName(bean.getDName());
				model.setDCrtdDt(bean.getDCrtdDt());
				model.setIclubAccessType(bean.getIclubAccessType());
				model.setIclubLicenseCode(bean.getIclubLicenseCode());
				model.setIclubMaritialStatus(bean.getIclubMaritialStatus());
				model.setIclubPersonByDPersonId(bean.getIclubPersonByDPersonId());
				model.setIclubPersonByDCrtdBy(bean.getIclubPersonByDCrtdBy());

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
				IclubDriverModel model = new IclubDriverModel();
				model.setDId(bean.getDId());
				model.setDDob(bean.getDDob());
				model.setDIssueDt(new Date(System.currentTimeMillis()));
				model.setDLicenseNum(bean.getDLicenseNum());
				model.setDName(bean.getDName());
				model.setDCrtdDt(bean.getDCrtdDt());
				model.setIclubAccessType(bean.getIclubAccessType());
				model.setIclubLicenseCode(bean.getIclubLicenseCode());
				model.setIclubMaritialStatus(bean.getIclubMaritialStatus());
				model.setIclubPersonByDPersonId(bean.getIclubPersonByDPersonId());
				model.setIclubPersonByDCrtdBy(bean.getIclubPersonByDCrtdBy());

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
		} else if (IclubWebHelper.calculateMyAge(bean.getDDob().getTime()) <= 18) {
			IclubWebHelper.addMessage(("You must be over 18 years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubMaritialStatus() == null) {
			IclubWebHelper.addMessage(getLabelBundle().getString("Please select Maritial Status"), FacesMessage.SEVERITY_ERROR);
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
				IclubDriverBean bean = new IclubDriverBean();
				bean.setDId(model.getDId());
				bean.setDDob(model.getDDob());
				bean.setDIssueDt(model.getDIssueDt());
				bean.setDLicenseNum(model.getDLicenseNum());
				bean.setDName(model.getDName());
				bean.setDCrtdDt(model.getDCrtdDt());
				bean.setIclubAccessType(model.getIclubAccessType());
				bean.setIclubLicenseCode(model.getIclubLicenseCode());
				bean.setIclubMaritialStatus(model.getIclubMaritialStatus());
				bean.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId());
				bean.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy());

				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicles = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String vehicle : model.getIclubVehicles()) {

						vehicles[i] = vehicle;
						i++;
					}
					bean.setIclubVehicles(vehicles);
				}
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
				IclubAccessTypeBean bean = new IclubAccessTypeBean();
				bean.setAtId(model.getAtId());
				bean.setAtLongDesc(model.getAtLongDesc());
				bean.setAtShortDesc(model.getAtShortDesc());
				bean.setAtStatus(model.getAtStatus());
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

				licenseCodeBeans.add(bean);
			}
		}

		return licenseCodeBeans;
	}

	public void setLicenseCodeBeans(List<IclubLicenseCodeBean> licenseCodeBeans) {
		this.licenseCodeBeans = licenseCodeBeans;
	}

	public List<IclubMaritialStatusBean> getMaritialStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(MS_BASE_URL + "list");
		Collection<? extends IclubMaritialStatusModel> models = new ArrayList<IclubMaritialStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMaritialStatusModel.class));
		client.close();
		maritialStatusBeans = new ArrayList<IclubMaritialStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubMaritialStatusModel model : models) {
				IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
				bean.setMsId(model.getMsId());
				bean.setMsLongDesc(model.getMsLongDesc());
				bean.setMsShortDesc(model.getMsShortDesc());
				bean.setMsStatus(model.getMsStatus());
				maritialStatusBeans.add(bean);
			}
		}
		return maritialStatusBeans;
	}

	public void setMaritialStatusBeans(List<IclubMaritialStatusBean> maritialStatusBeans) {
		this.maritialStatusBeans = maritialStatusBeans;
	}
}
