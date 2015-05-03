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

import za.co.iclub.pss.web.bean.IclubVehicleTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubVehicleTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubVehicleTypeController")
@SessionScoped
public class IclubVehicleTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubVehicleTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleTypeService/";
	private List<IclubVehicleTypeBean> beans;
	private IclubVehicleTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubVehicleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubVehicleType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubVehicleTypeModel model = new IclubVehicleTypeModel();

				model.setVtLongDesc(bean.getVtLongDesc());
				model.setVtShortDesc(bean.getVtShortDesc());
				model.setVtStatus(bean.getVtStatus());

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

	public void modIclubVehicleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubVehicleType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubVehicleTypeModel model = new IclubVehicleTypeModel();
				model.setVtId(bean.getVtId());
				model.setVtLongDesc(bean.getVtLongDesc());
				model.setVtShortDesc(bean.getVtShortDesc());
				model.setVtStatus(bean.getVtStatus());

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

	public void delIclubVehicleType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubVehicleType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getVtId());
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
		bean = new IclubVehicleTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubVehicleTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getVtShortDesc() != null && !bean.getVtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getVtShortDesc().trim() + "/" + ((bean.getVtId() == null) ? -999l : bean.getVtId()));
			ResponseModel message = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
			client.close();
			if (message.getStatusCode() != 0) {
				IclubWebHelper.addMessage(message.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
		} else {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.shortdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getVtLongDesc() == null || bean.getVtLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getVtStatus() == null || bean.getVtStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubVehicleTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubVehicleTypeModel> models = new ArrayList<IclubVehicleTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleTypeModel.class));
		client.close();
		beans = new ArrayList<IclubVehicleTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehicleTypeModel model : models) {
				IclubVehicleTypeBean bean = new IclubVehicleTypeBean();
				bean.setVtId(model.getVtId());
				bean.setVtLongDesc(model.getVtLongDesc());
				bean.setVtShortDesc(model.getVtShortDesc());
				bean.setVtStatus(model.getVtStatus());
				beans.add(bean);
			}
		}
		return beans;
	}

	public void setBeans(List<IclubVehicleTypeBean> beans) {
		this.beans = beans;
	}

	public IclubVehicleTypeBean getBean() {
		if (bean == null)
			bean = new IclubVehicleTypeBean();
		return bean;
	}

	public void setBean(IclubVehicleTypeBean bean) {
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
