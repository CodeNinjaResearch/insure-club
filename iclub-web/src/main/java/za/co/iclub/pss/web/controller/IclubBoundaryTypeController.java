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

import za.co.iclub.pss.web.bean.IclubBoundaryTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubBoundaryTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubBoundaryTypeController")
@SessionScoped
public class IclubBoundaryTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubBoundaryTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBoundaryTypeService/";
	private List<IclubBoundaryTypeBean> beans;
	private IclubBoundaryTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubBoundaryType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubBoundaryType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubBoundaryTypeModel model = new IclubBoundaryTypeModel();

				model.setBtLongDesc(bean.getBtLongDesc());
				model.setBtShortDesc(bean.getBtShortDesc());
				model.setBtStatus(bean.getBtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubBoundaryType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubBoundaryType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubBoundaryTypeModel model = new IclubBoundaryTypeModel();
				model.setBtId(bean.getBtId());
				model.setBtLongDesc(bean.getBtLongDesc());
				model.setBtShortDesc(bean.getBtShortDesc());
				model.setBtStatus(bean.getBtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubBoundaryType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubBoundaryType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getBtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("boundarytype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubBoundaryTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubBoundaryTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getBtShortDesc() != null && !bean.getBtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getBtShortDesc().trim() + "/" + ((bean.getBtId() == null) ? -999l : bean.getBtId()));
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		}

		if (bean.getBtStatus().equalsIgnoreCase("-1")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubBoundaryTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubBoundaryTypeModel> models = new ArrayList<IclubBoundaryTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBoundaryTypeModel.class));
		client.close();
		beans = new ArrayList<IclubBoundaryTypeBean>();
		for (IclubBoundaryTypeModel model : models) {
			IclubBoundaryTypeBean bean = new IclubBoundaryTypeBean();
			bean.setBtId(model.getBtId());
			bean.setBtLongDesc(model.getBtLongDesc());
			bean.setBtShortDesc(model.getBtShortDesc());
			bean.setBtStatus(model.getBtStatus());
			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubBoundaryTypeBean> beans) {
		this.beans = beans;
	}

	public IclubBoundaryTypeBean getBean() {
		if (bean == null)
			bean = new IclubBoundaryTypeBean();
		return bean;
	}

	public void setBean(IclubBoundaryTypeBean bean) {
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
