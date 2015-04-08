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

import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubMaritialStatusController")
@SessionScoped
public class IclubMaritialStatusController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubMaritialStatusController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private List<IclubMaritialStatusBean> beans;
	private IclubMaritialStatusBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubMaritialStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubMaritialStatus");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubMaritialStatusModel model = new IclubMaritialStatusModel();

				model.setMsLongDesc(bean.getMsLongDesc());
				model.setMsShortDesc(bean.getMsShortDesc());
				model.setMsStatus(bean.getMsStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubMaritialStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubMaritialStatus");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubMaritialStatusModel model = new IclubMaritialStatusModel();
				model.setMsId(bean.getMsId());
				model.setMsLongDesc(bean.getMsLongDesc());
				model.setMsShortDesc(bean.getMsShortDesc());
				model.setMsStatus(bean.getMsStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubMaritialStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubMaritialStatus");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getMsId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("maritialstatus") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubMaritialStatusBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubMaritialStatusBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getMsShortDesc() != null && !bean.getMsShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getMsShortDesc().trim() + "/" + ((bean.getMsId() == null) ? -999l : bean.getMsId()));
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

		if (bean.getMsLongDesc() == null || bean.getMsLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getMsStatus() == null || bean.getMsStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubMaritialStatusBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubMaritialStatusModel> models = new ArrayList<IclubMaritialStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMaritialStatusModel.class));
		client.close();
		beans = new ArrayList<IclubMaritialStatusBean>();
		for (IclubMaritialStatusModel model : models) {
			IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
			bean.setMsId(model.getMsId());
			bean.setMsLongDesc(model.getMsLongDesc());
			bean.setMsShortDesc(model.getMsShortDesc());
			bean.setMsStatus(model.getMsStatus());
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubMaritialStatusBean> beans) {
		this.beans = beans;
	}

	public IclubMaritialStatusBean getBean() {
		if (bean == null)
			bean = new IclubMaritialStatusBean();
		return bean;
	}

	public void setBean(IclubMaritialStatusBean bean) {
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
