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

import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubCoverTypeController")
@SessionScoped
public class IclubCoverTypeController implements Serializable {

	private static final long serialVersionUID = 2246191018654665794L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubCoverTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";
	private static final String IIT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemTypeService/";
	private List<IclubCoverTypeBean> beans;
	private List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans;
	private List<IclubCoverTypeBean> dashBoardBeans;
	private IclubCoverTypeBean bean;
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
		bean = new IclubCoverTypeBean();
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

	public List<IclubCoverTypeBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubCoverTypeModel> models = new ArrayList<IclubCoverTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCoverTypeModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubCoverTypeBean>();
		for (IclubCoverTypeModel model : models) {
			IclubCoverTypeBean bean = new IclubCoverTypeBean();

			bean.setCtId(model.getCtId());
			bean.setCtLongDesc(model.getCtLongDesc());
			bean.setCtShortDesc(model.getCtShortDesc());
			bean.setCtStatus(model.getCtStatus());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setCtCrtdDt(model.getCtCrtdDt());
			if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
				String[] iclubProperties = new String[model.getIclubProperties().length];
				int i = 0;
				for (String iclubProperty : bean.getIclubProperties()) {
					iclubProperties[i] = iclubProperty;
					i++;
				}
				bean.setIclubProperties(iclubProperties);
			}
			if (model.getIclubQuotes() != null && model.getIclubQuotes().length > 0) {
				String[] iclubQuotes = new String[model.getIclubQuotes().length];
				int i = 0;
				for (String iclubQuote : model.getIclubQuotes()) {
					iclubQuotes[i] = iclubQuote;
					i++;
				}
				bean.setIclubQuotes(iclubQuotes);
			}

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubCoverTypeBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubCoverTypeBean();
	}

	public void addIclubCoverType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCoverType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubCoverTypeModel model = new IclubCoverTypeModel();

				model.setCtLongDesc(bean.getCtLongDesc());
				model.setCtShortDesc(bean.getCtShortDesc());
				model.setCtStatus(bean.getCtStatus());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setCtCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubCoverType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubCoverType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubCoverTypeModel model = new IclubCoverTypeModel();

				model.setCtId(bean.getCtId());
				model.setCtLongDesc(bean.getCtLongDesc());
				model.setCtShortDesc(bean.getCtShortDesc());
				model.setCtStatus(bean.getCtStatus());
				model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
				model.setCtCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubCoverType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubCoverType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getCtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getIclubPerson() != null && bean.getIclubPerson().equals(-1)) {
			IclubWebHelper.addMessage("Please select a valid value for EntityStatus", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public IclubCoverTypeBean getBean() {
		if (bean == null)
			bean = new IclubCoverTypeBean();
		return bean;
	}

	public void setBean(IclubCoverTypeBean bean) {
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

	public List<IclubCoverTypeBean> getBeans() {

		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubCoverTypeModel> models = new ArrayList<IclubCoverTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCoverTypeModel.class));
		client.close();
		beans = new ArrayList<IclubCoverTypeBean>();
		for (IclubCoverTypeModel model : models) {

			IclubCoverTypeBean bean = new IclubCoverTypeBean();

			bean.setCtId(model.getCtId());
			bean.setCtLongDesc(model.getCtLongDesc());
			bean.setCtShortDesc(model.getCtShortDesc());
			bean.setCtStatus(model.getCtStatus());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setCtCrtdDt(model.getCtCrtdDt());
			if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
				String[] iclubProperties = new String[model.getIclubProperties().length];
				int i = 0;
				for (String iclubProperty : model.getIclubProperties()) {
					iclubProperties[i] = iclubProperty;
					i++;
				}
				bean.setIclubProperties(iclubProperties);
			}
			if (model.getIclubQuotes() != null && model.getIclubQuotes().length > 0) {
				String[] iclubQuotes = new String[model.getIclubQuotes().length];
				int i = 0;
				for (String iclubQuote : model.getIclubQuotes()) {
					iclubQuotes[i] = iclubQuote;
					i++;
				}
				bean.setIclubQuotes(iclubQuotes);
			}
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubCoverTypeBean> beans) {
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
