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

import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubCoverTypeController")
@SessionScoped
public class IclubCoverTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubCoverTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";
	private List<IclubCoverTypeBean> beans;
	private IclubCoverTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubCoverType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCoverType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubCoverTypeModel model = new IclubCoverTypeModel();

				model.setCtLongDesc(bean.getCtLongDesc());
				model.setCtShortDesc(bean.getCtShortDesc());
				model.setCtStatus(bean.getCtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
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

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
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
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("covertype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubCoverTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubCoverTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getCtShortDesc() != null && !bean.getCtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getCtShortDesc().trim() + "/" + ((bean.getCtId() == null) ? -999l : bean.getCtId()));
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		}

		if (bean.getCtStatus().equalsIgnoreCase("-1")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
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
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubCoverTypeBean> beans) {
		this.beans = beans;
	}

	public IclubCoverTypeBean getBean() {
		if (bean == null)
			bean = new IclubCoverTypeBean();
		return bean;
	}

	public void setBean(IclubCoverTypeBean bean) {
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
