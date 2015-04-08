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

import za.co.iclub.pss.web.bean.IclubEventTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubEventTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubEventTypeController")
@SessionScoped
public class IclubEventTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubEventTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubEventTypeService/";
	private List<IclubEventTypeBean> beans;
	private IclubEventTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubEventType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubEventType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubEventTypeModel model = new IclubEventTypeModel();

				model.setEtLongDesc(bean.getEtLongDesc());
				model.setEtShortDesc(bean.getEtShortDesc());
				model.setEtStatus(bean.getEtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubEventType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubEventType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubEventTypeModel model = new IclubEventTypeModel();
				model.setEtId(bean.getEtId());
				model.setEtLongDesc(bean.getEtLongDesc());
				model.setEtShortDesc(bean.getEtShortDesc());
				model.setEtStatus(bean.getEtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubEventType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubEventType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getEtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("eventtype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubEventTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubEventTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getEtShortDesc() != null && !bean.getEtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getEtShortDesc().trim() + "/" + ((bean.getEtId() == null) ? -999l : bean.getEtId()));
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		}

		else {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.shortdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getEtLongDesc() == null || bean.getEtLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getEtStatus() == null || bean.getEtStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubEventTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubEventTypeModel> models = new ArrayList<IclubEventTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubEventTypeModel.class));
		client.close();
		beans = new ArrayList<IclubEventTypeBean>();
		for (IclubEventTypeModel model : models) {
			IclubEventTypeBean bean = new IclubEventTypeBean();
			bean.setEtId(model.getEtId());
			bean.setEtLongDesc(model.getEtLongDesc());
			bean.setEtShortDesc(model.getEtShortDesc());
			bean.setEtStatus(model.getEtStatus());
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubEventTypeBean> beans) {
		this.beans = beans;
	}

	public IclubEventTypeBean getBean() {
		if (bean == null)
			bean = new IclubEventTypeBean();
		return bean;
	}

	public void setBean(IclubEventTypeBean bean) {
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
}
