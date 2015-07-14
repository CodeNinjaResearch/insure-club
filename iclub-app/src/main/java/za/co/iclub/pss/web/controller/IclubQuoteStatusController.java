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

import za.co.iclub.pss.model.ui.IclubQuoteStatusBean;
import za.co.iclub.pss.model.ws.IclubQuoteStatusModel;
import za.co.iclub.pss.trans.IclubQuoteStatusTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubQuoteStatusController")
@SessionScoped
public class IclubQuoteStatusController implements Serializable {
	
	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubQuoteStatusController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-app/services/iclub/IclubQuoteStatusService/";
	private List<IclubQuoteStatusBean> beans;
	private IclubQuoteStatusBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	
	public void addIclubQuoteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubQuoteStatus");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubQuoteStatusModel model = IclubQuoteStatusTrans.fromUItoWS(bean);
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubQuoteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubQuoteStatus");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubQuoteStatusModel model = IclubQuoteStatusTrans.fromUItoWS(bean);
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubQuoteStatus() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubQuoteStatus");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getQsId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("quotestatus") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubQuoteStatusBean();
	}
	
	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubQuoteStatusBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		if (bean.getQsShortDesc() != null && !bean.getQsShortDesc().trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "validate/sd/" + bean.getQsShortDesc().trim() + "/" + ((bean.getQsId() == null) ? -999l : bean.getQsId()));
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
		
		if (bean.getQsLongDesc() == null || bean.getQsLongDesc().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.longdesc.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getQsStatus() == null || bean.getQsStatus().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("val.select.valid"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public List<IclubQuoteStatusBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubQuoteStatusModel> models = new ArrayList<IclubQuoteStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubQuoteStatusModel.class));
		client.close();
		beans = new ArrayList<IclubQuoteStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubQuoteStatusModel model : models) {
				IclubQuoteStatusBean bean = IclubQuoteStatusTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubQuoteStatusBean> beans) {
		this.beans = beans;
	}
	
	public IclubQuoteStatusBean getBean() {
		if (bean == null)
			bean = new IclubQuoteStatusBean();
		return bean;
	}
	
	public void setBean(IclubQuoteStatusBean bean) {
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
