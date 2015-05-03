package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubGeoLocController")
@SessionScoped
public class IclubGeoLocController implements Serializable {

	private static final long serialVersionUID = 8245517153102756484L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubGeoLocController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubGeoLocService/";
	private List<IclubGeoLocBean> beans;
	private List<IclubGeoLocBean> dashBoardBeans;
	private IclubGeoLocBean bean;
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
		viewParam = 1l;
	}

	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubGeoLocBean();
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

	public void showSummary() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showSummary");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = false;
		showSummaryCont = true;
		viewParam = 3l;
	}

	public List<IclubGeoLocBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubGeoLocModel> models = new ArrayList<IclubGeoLocModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubGeoLocModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubGeoLocBean>();
		if (models != null && models.size() > 0) {
			for (IclubGeoLocModel model : models) {
				IclubGeoLocBean bean = new IclubGeoLocBean();

				bean.setGlId(model.getGlId());
				bean.setGlAddress(model.getGlAddress());
				bean.setGlLat(model.getGlLat());
				bean.setGlLong(model.getGlLong());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setGlRate(model.getGlRate());
				bean.setGlCrtdDt(model.getGlCrtdDt());

				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubGeoLocBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubGeoLocBean();
	}

	public void addIclubGeoLoc() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubGeoLoc");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubGeoLocModel model = new IclubGeoLocModel();

				model.setGlAddress(bean.getGlAddress());
				model.setGlLat(bean.getGlLat());
				model.setGlLong(bean.getGlLong());
				model.setGlRate(bean.getGlRate());
				model.setGlCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubGeoLoc() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubGeoLoc");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubGeoLocModel model = new IclubGeoLocModel();

				model.setGlId(bean.getGlId());
				model.setGlAddress(bean.getGlAddress());
				model.setGlLat(bean.getGlLat());
				model.setGlLong(bean.getGlLong());
				model.setGlRate(bean.getGlRate());
				model.setGlCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubGeoLoc() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubGeoLoc");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getGlId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("geoloc") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		return ret;
	}

	public IclubGeoLocBean getBean() {
		if (bean == null)
			bean = new IclubGeoLocBean();
		return bean;
	}

	public void setBean(IclubGeoLocBean bean) {
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

	public List<IclubGeoLocBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubGeoLocModel> models = new ArrayList<IclubGeoLocModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubGeoLocModel.class));
		client.close();
		beans = new ArrayList<IclubGeoLocBean>();
		if (models != null && models.size() > 0) {
			for (IclubGeoLocModel model : models) {

				IclubGeoLocBean bean = new IclubGeoLocBean();

				bean.setGlId(model.getGlId());
				bean.setGlAddress(model.getGlAddress());
				bean.setGlLat(model.getGlLat());
				bean.setGlLong(model.getGlLong());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setGlRate(model.getGlRate());
				bean.setGlCrtdDt(model.getGlCrtdDt());

				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubGeoLocBean> beans) {
		this.beans = beans;
	}

	public boolean isShowSummaryCont() {
		return showSummaryCont;
	}

	public void setShowSummaryCont(boolean showSummaryCont) {
		this.showSummaryCont = showSummaryCont;
	}

}
