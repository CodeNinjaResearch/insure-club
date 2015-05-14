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

import za.co.iclub.pss.web.bean.IclubPropUsageTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubPropUsageTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubPropUsageTypeController")
@SessionScoped
public class IclubPropUsageController implements Serializable {
	
	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubPropUsageController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropUsageTypeService/";
	private List<IclubPropUsageTypeBean> beans;
	private IclubPropUsageTypeBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	
	public void addIclubPropUsageType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubPropUsageType");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();
				
				model.setPutLongDesc(bean.getPutLongDesc());
				model.setPutShortDesc(bean.getPutShortDesc());
				model.setPutStatus(bean.getPutStatus());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubPropUsageType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubPropUsageType");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubPropUsageTypeModel model = new IclubPropUsageTypeModel();
				model.setPutId(bean.getPutId());
				model.setPutLongDesc(bean.getPutLongDesc());
				model.setPutShortDesc(bean.getPutShortDesc());
				model.setPutStatus(bean.getPutStatus());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubPropUsageType() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPropUsageType");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getPutId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("propusage") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubPropUsageTypeBean();
	}
	
	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubPropUsageTypeBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		if (bean.getPutShortDesc() != null && !bean.getPutShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getPutShortDesc().trim() + "/" + ((bean.getPutId() == null) ? -999l : bean.getPutId()));
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
		
		if (bean.getPutLongDesc() == null || bean.getPutLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPutStatus() == null || bean.getPutStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public List<IclubPropUsageTypeBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubPropUsageTypeModel> models = new ArrayList<IclubPropUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropUsageTypeModel.class));
		client.close();
		beans = new ArrayList<IclubPropUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropUsageTypeModel model : models) {
				IclubPropUsageTypeBean bean = new IclubPropUsageTypeBean();
				bean.setPutId(model.getPutId());
				bean.setPutLongDesc(model.getPutLongDesc());
				bean.setPutShortDesc(model.getPutShortDesc());
				bean.setPutStatus(model.getPutStatus());
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubPropUsageTypeBean> beans) {
		this.beans = beans;
	}
	
	public IclubPropUsageTypeBean getBean() {
		if (bean == null)
			bean = new IclubPropUsageTypeBean();
		return bean;
	}
	
	public void setBean(IclubPropUsageTypeBean bean) {
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
