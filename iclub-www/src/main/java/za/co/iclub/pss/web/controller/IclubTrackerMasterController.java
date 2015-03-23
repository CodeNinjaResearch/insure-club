package za.co.iclub.pss.web.controller;

import java.io.Serializable;
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

import za.co.iclub.pss.web.bean.IclubTrackerMasterBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubTrackerMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubTrackerMasterController")
@SessionScoped
public class IclubTrackerMasterController implements Serializable {

	private static final long serialVersionUID = 1399848586779525616L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubTrackerMasterController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubTrackerMasterService/";
	private List<IclubTrackerMasterBean> beans;
	private List<IclubTrackerMasterBean> dashBoardBeans;
	private IclubTrackerMasterBean bean;
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
		bean = new IclubTrackerMasterBean();
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

	public List<IclubTrackerMasterBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubTrackerMasterModel> models = new ArrayList<IclubTrackerMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubTrackerMasterModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubTrackerMasterBean>();
		for (IclubTrackerMasterModel model : models) {
			IclubTrackerMasterBean bean = new IclubTrackerMasterBean();

			bean.setTmId(model.getTmId());
			bean.setTmName(model.getTmName());
			bean.setTmLat(model.getTmLat());
			bean.setTmLocation(model.getTmLocation());
			bean.setTmLong(model.getTmLong());
			bean.setTmTradeName(model.getTmTradeName());
			bean.setTmRegNum(model.getTmRegNum());
			bean.setTmCrtdDt(model.getTmCrtdDt());
			bean.setIclubPerson(model.getIclubPerson());

			if (model.getIclubSecurityDevices() != null && model.getIclubSecurityDevices().length > 0) {
				String[] securityDevices = new String[model.getIclubSecurityDevices().length];
				int i = 0;
				for (String securityDevice : model.getIclubSecurityDevices()) {
					securityDevices[i] = securityDevice;
					i++;
				}
				bean.setIclubSecurityDevices(securityDevices);
			}

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubTrackerMasterBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubTrackerMasterBean();
	}

	public void addIclubTrackerMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubTrackerMaster");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubTrackerMasterModel model = new IclubTrackerMasterModel();

				model.setTmName(bean.getTmName());
				model.setTmLat(bean.getTmLat());
				model.setTmLocation(bean.getTmLocation());
				model.setTmLong(bean.getTmLong());
				model.setTmTradeName(bean.getTmTradeName());
				model.setTmRegNum(bean.getTmRegNum());
				model.setTmCrtdDt(bean.getTmCrtdDt());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubTrackerMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubTrackerMaster");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubTrackerMasterModel model = new IclubTrackerMasterModel();

				model.setTmId(bean.getTmId());
				model.setTmName(bean.getTmName());
				model.setTmLat(bean.getTmLat());
				model.setTmLocation(bean.getTmLocation());
				model.setTmLong(bean.getTmLong());
				model.setTmTradeName(bean.getTmTradeName());
				model.setTmRegNum(bean.getTmRegNum());
				model.setTmCrtdDt(bean.getTmCrtdDt());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubTrackerMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubTrackerMaster");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getTmId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("trackermaster") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubTrackerMasterBean getBean() {
		if (bean == null)
			bean = new IclubTrackerMasterBean();
		return bean;
	}

	public void setBean(IclubTrackerMasterBean bean) {
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

	public List<IclubTrackerMasterBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubTrackerMasterModel> models = new ArrayList<IclubTrackerMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubTrackerMasterModel.class));
		client.close();
		beans = new ArrayList<IclubTrackerMasterBean>();
		for (IclubTrackerMasterModel model : models) {

			IclubTrackerMasterBean bean = new IclubTrackerMasterBean();

			bean.setTmId(model.getTmId());
			bean.setTmName(model.getTmName());
			bean.setTmLat(model.getTmLat());
			bean.setTmLocation(model.getTmLocation());
			bean.setTmLong(model.getTmLong());
			bean.setTmTradeName(model.getTmTradeName());
			bean.setTmRegNum(model.getTmRegNum());
			bean.setTmCrtdDt(model.getTmCrtdDt());
			bean.setIclubPerson(model.getIclubPerson());

			if (model.getIclubSecurityDevices() != null && model.getIclubSecurityDevices().length > 0) {
				String[] securityDevices = new String[model.getIclubSecurityDevices().length];
				int i = 0;
				for (String securityDevice : model.getIclubSecurityDevices()) {
					securityDevices[i] = securityDevice;
					i++;
				}
				bean.setIclubSecurityDevices(securityDevices);
			}

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubTrackerMasterBean> beans) {
		this.beans = beans;
	}
}
