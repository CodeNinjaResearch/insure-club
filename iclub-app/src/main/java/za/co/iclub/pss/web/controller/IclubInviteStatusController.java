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

import za.co.iclub.pss.model.ui.IclubInviteStatusBean;
import za.co.iclub.pss.model.ws.IclubInviteStatusModel;
import za.co.iclub.pss.trans.IclubInviteStatusTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubInviteStatusController")
@SessionScoped
public class IclubInviteStatusController implements Serializable {
	
	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubInviteStatusController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInviteStatusService/";
	private List<IclubInviteStatusBean> beans;
	private IclubInviteStatusBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	
	public void addIclubInviteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubInviteStatus");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubInviteStatusModel model = IclubInviteStatusTrans.fromUItoWS(bean);
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubInviteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubInviteStatus");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubInviteStatusModel model = IclubInviteStatusTrans.fromUItoWS(bean);
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubInviteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubInviteStatus");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getIsId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Invite Status " + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubInviteStatusBean();
	}
	
	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubInviteStatusBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		if (bean.getIsShortDesc() != null && !bean.getIsShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getIsShortDesc().trim() + "/" + ((bean.getIsId() == null) ? -999l : bean.getIsId()));
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
		
		if (bean.getIsLongDesc() == null || bean.getIsLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getIsStatus() == null || bean.getIsStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public List<IclubInviteStatusBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubInviteStatusModel> models = new ArrayList<IclubInviteStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInviteStatusModel.class));
		client.close();
		beans = new ArrayList<IclubInviteStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubInviteStatusModel model : models) {
				IclubInviteStatusBean bean = IclubInviteStatusTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubInviteStatusBean> beans) {
		this.beans = beans;
	}
	
	public IclubInviteStatusBean getBean() {
		if (bean == null)
			bean = new IclubInviteStatusBean();
		return bean;
	}
	
	public void setBean(IclubInviteStatusBean bean) {
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
