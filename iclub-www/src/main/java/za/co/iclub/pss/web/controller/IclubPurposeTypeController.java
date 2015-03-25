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

import za.co.iclub.pss.web.bean.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubPurposeTypeController")
@SessionScoped
public class IclubPurposeTypeController implements Serializable {

	private static final long serialVersionUID = -8915296782572192096L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubPurposeTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPurposeTypeService/";
	private static final String IIT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemTypeService/";
	private List<IclubPurposeTypeBean> beans;
	private List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans;
	private List<IclubPurposeTypeBean> dashBoardBeans;
	private IclubPurposeTypeBean bean;
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
		bean = new IclubPurposeTypeBean();
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

	public List<IclubPurposeTypeBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubPurposeTypeBean>();
		for (IclubPurposeTypeModel model : models) {
			IclubPurposeTypeBean bean = new IclubPurposeTypeBean();

			bean.setPtId(model.getPtId());
			bean.setPtLongDesc(model.getPtLongDesc());
			bean.setPtShortDesc(model.getPtShortDesc());
			bean.setPtStatus(model.getPtStatus());
			bean.setPtCrtdDt(model.getPtCrtdDt());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());

			if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
				String[] properties = new String[model.getIclubProperties().length];
				int i = 0;
				for (String iclubProperty : model.getIclubProperties()) {
					properties[i] = iclubProperty;
					i++;
				}
				bean.setIclubProperties(properties);
			}

			if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
				String[] vehicles = new String[model.getIclubVehicles().length];
				int i = 0;
				for (String iclubVehicle : model.getIclubVehicles()) {
					vehicles[i] = iclubVehicle;
					i++;
				}
				bean.setIclubVehicles(vehicles);
			}

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubPurposeTypeBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubPurposeTypeBean();
	}

	public void addIclubPurposeType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubPurposeType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

				model.setPtLongDesc(bean.getPtLongDesc());
				model.setPtShortDesc(bean.getPtShortDesc());
				model.setPtStatus(bean.getPtStatus());
				model.setPtCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubPurposeType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubPurposeType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

				model.setPtId(bean.getPtId());
				model.setPtLongDesc(bean.getPtLongDesc());
				model.setPtShortDesc(bean.getPtShortDesc());
				model.setPtStatus(bean.getPtStatus());
				model.setPtCrtdDt(new Timestamp(bean.getPtCrtdDt().getTime()));
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubPurposeType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPurposeType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getPtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("purposetype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubPurposeTypeBean getBean() {
		if (bean == null)
			bean = new IclubPurposeTypeBean();
		return bean;
	}

	public void setBean(IclubPurposeTypeBean bean) {
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

	public List<IclubPurposeTypeBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		beans = new ArrayList<IclubPurposeTypeBean>();
		for (IclubPurposeTypeModel model : models) {

			IclubPurposeTypeBean bean = new IclubPurposeTypeBean();

			bean.setPtId(model.getPtId());
			bean.setPtLongDesc(model.getPtLongDesc());
			bean.setPtShortDesc(model.getPtShortDesc());
			bean.setPtStatus(model.getPtStatus());
			bean.setPtCrtdDt(model.getPtCrtdDt());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());

			if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
				String[] properties = new String[model.getIclubProperties().length];
				int i = 0;
				for (String iclubProperty : model.getIclubProperties()) {
					properties[i] = iclubProperty;
					i++;
				}
				bean.setIclubProperties(properties);
			}

			if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
				String[] vehicles = new String[model.getIclubVehicles().length];
				int i = 0;
				for (String iclubVehicle : model.getIclubVehicles()) {
					vehicles[i] = iclubVehicle;
					i++;
				}
				bean.setIclubVehicles(vehicles);
			}

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubPurposeTypeBean> beans) {
		this.beans = beans;
	}

	public List<IclubInsuranceItemTypeBean> getInsuranceItemTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		insuranceItemTypeBeans = new ArrayList<IclubInsuranceItemTypeBean>();
		for (IclubInsuranceItemTypeModel model : models) {
			IclubInsuranceItemTypeBean bean = new IclubInsuranceItemTypeBean();
			bean.setIitId(model.getIitId());
			bean.setIitLongDesc(model.getIitLongDesc());
			bean.setIitShortDesc(model.getIitShortDesc());
			bean.setIitStatus(model.getIitStatus());
			insuranceItemTypeBeans.add(bean);
		}
		return insuranceItemTypeBeans;
	}

	public void setInsuranceItemTypeBeans(List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans) {
		this.insuranceItemTypeBeans = insuranceItemTypeBeans;
	}

}
