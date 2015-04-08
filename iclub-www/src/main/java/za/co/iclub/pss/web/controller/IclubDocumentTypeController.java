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

import za.co.iclub.pss.web.bean.IclubDocumentTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubDocumentTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubDocumentTypeController")
@SessionScoped
public class IclubDocumentTypeController implements Serializable {

	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubDocumentTypeController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDocumentTypeService/";
	private List<IclubDocumentTypeBean> beans;
	private IclubDocumentTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;

	public void addIclubDocumentType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubDocumentType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubDocumentTypeModel model = new IclubDocumentTypeModel();

				model.setDtLongDesc(bean.getDtLongDesc());
				model.setDtShortDesc(bean.getDtShortDesc());
				model.setDtStatus(bean.getDtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void modIclubDocumentType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubDocumentType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubDocumentTypeModel model = new IclubDocumentTypeModel();
				model.setDtId(bean.getDtId());
				model.setDtLongDesc(bean.getDtLongDesc());
				model.setDtShortDesc(bean.getDtShortDesc());
				model.setDtStatus(bean.getDtStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void delIclubDocumentType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubDocumentType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getDtId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("documenttype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubDocumentTypeBean();
	}

	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubDocumentTypeBean();
	}

	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (bean.getDtShortDesc() != null && !bean.getDtShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getDtShortDesc().trim() + "/" + ((bean.getDtId() == null) ? -999l : bean.getDtId()));
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

		if (bean.getDtLongDesc() == null || bean.getDtLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (bean.getDtStatus() == null || bean.getDtStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		return ret;
	}

	public List<IclubDocumentTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubDocumentTypeModel> models = new ArrayList<IclubDocumentTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubDocumentTypeModel.class));
		client.close();
		beans = new ArrayList<IclubDocumentTypeBean>();
		for (IclubDocumentTypeModel model : models) {
			IclubDocumentTypeBean bean = new IclubDocumentTypeBean();
			bean.setDtId(model.getDtId());
			bean.setDtLongDesc(model.getDtLongDesc());
			bean.setDtShortDesc(model.getDtShortDesc());
			bean.setDtStatus(model.getDtStatus());

			beans.add(bean);
		}
		return beans;
	}

	public void setBeans(List<IclubDocumentTypeBean> beans) {
		this.beans = beans;
	}

	public IclubDocumentTypeBean getBean() {
		if (bean == null)
			bean = new IclubDocumentTypeBean();
		return bean;
	}

	public void setBean(IclubDocumentTypeBean bean) {
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
