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

import za.co.iclub.pss.web.bean.IclubRoleTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubRoleTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubRoleTypeController")
@SessionScoped
public class IclubRoleTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubRoleTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRoleTypeService/";
	private List<IclubRoleTypeBean> beans;
	private IclubRoleTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubRoleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubRoleType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubRoleTypeModel model = new IclubRoleTypeModel();

				model.setRtLongDesc(bean.getRtLongDesc());
				model.setRtShortDesc(bean.getRtShortDesc());
				model.setRtStatus(bean.getRtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubRoleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubRoleType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubRoleTypeModel model = new IclubRoleTypeModel();
				model.setRtId(bean.getRtId());
				model.setRtLongDesc(bean.getRtLongDesc());
				model.setRtShortDesc(bean.getRtShortDesc());
				model.setRtStatus(bean.getRtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubRoleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubRoleType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getRtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubRoleTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubRoleTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getRtShortDesc() != null && !bean.getRtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getRtShortDesc().trim() + "/" + ((bean.getRtId() == null) ? -999l : bean.getRtId()));
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

		if (bean.getRtLongDesc() == null || bean.getRtLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getRtStatus() == null || bean.getRtStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubRoleTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubRoleTypeModel> models = new ArrayList<IclubRoleTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRoleTypeModel.class));
		client.close();
		beans = new ArrayList<IclubRoleTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRoleTypeModel model : models) {
				IclubRoleTypeBean bean = new IclubRoleTypeBean();
				bean.setRtId(model.getRtId());
				bean.setRtLongDesc(model.getRtLongDesc());
				bean.setRtShortDesc(model.getRtShortDesc());
				bean.setRtStatus(model.getRtStatus());

				// need to verify is required or not
				if (model.getIclubLogins() != null && model.getIclubLogins().length > 0) {

					bean.setIclubLogins(model.getIclubLogins());
				}

				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubRoleTypeBean> beans) {
		this.beans = beans;
	}

	public IclubRoleTypeBean getBean() {
		if (bean == null)
			bean = new IclubRoleTypeBean();
		return bean;
	}

	public void setBean(IclubRoleTypeBean bean) {
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
