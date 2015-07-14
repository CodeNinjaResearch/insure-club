package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.model.ui.IclubMbCommentBean;
import za.co.iclub.pss.model.ui.IclubMessageBoardBean;
import za.co.iclub.pss.model.ws.IclubMbCommentModel;
import za.co.iclub.pss.trans.IclubMbCommentTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubMbCommentController")
@SessionScoped
public class IclubMbCommentController implements Serializable {
	
	private static final long serialVersionUID = 6271776777151313314L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final Logger LOGGER = Logger.getLogger(IclubMbCommentController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubMbCommentService/";
	private List<IclubMbCommentBean> beans;
	private String sessionUserId;
	private IclubMbCommentBean bean;
	private IclubMessageBoardBean messageBoardBean;
	private boolean showAddPanel;
	private boolean showModPanel;
	private ResourceBundle labelBundle;
	
	public void addIclubMbComment() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubMbComment");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubMbCommentModel model = new IclubMbCommentModel();
				
				model.setMbcId(UUID.randomUUID().toString());
				model.setMbcCrtdDt(bean.getMbcCrtdDt());
				model.setMbcDesc(bean.getMbcDesc());
				model.setIclubMessageBoard(messageBoardBean != null ? messageBoardBean.getMbId() : null);
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubMbComment() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubMbComment");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				
				IclubMbCommentModel model = IclubMbCommentTrans.fromUItoWS(bean);
				
				model.setIclubMessageBoard(messageBoardBean != null ? messageBoardBean.getMbId() : null);
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					clearForm();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubMbComment() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubMbComment");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getMbcId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void clearForm() {
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubMbCommentBean();
	}
	
	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubMbCommentBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		return ret;
	}
	
	public List<IclubMbCommentBean> getBeans() {
		
		if (IclubWebHelper.getObjectIntoSession("messageBoardBean") != null) {
			messageBoardBean = (IclubMessageBoardBean) IclubWebHelper.getObjectIntoSession("messageBoardBean");
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/messageboard/" + messageBoardBean.getMbId());
			Collection<? extends IclubMbCommentModel> models = new ArrayList<IclubMbCommentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMbCommentModel.class));
			client.close();
			beans = new ArrayList<IclubMbCommentBean>();
			if (models != null && models.size() > 0) {
				for (IclubMbCommentModel model : models) {
					IclubMbCommentBean bean = IclubMbCommentTrans.fromWStoUI(model);
					
					beans.add(bean);
				}
			}
			
		}
		
		return beans;
	}
	
	public void setBeans(List<IclubMbCommentBean> beans) {
		this.beans = beans;
	}
	
	public IclubMbCommentBean getBean() {
		if (bean == null)
			bean = new IclubMbCommentBean();
		return bean;
	}
	
	public void setBean(IclubMbCommentBean bean) {
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
	
	public String getSessionUserId() {
		Object sessUsrId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id"));
		if (sessUsrId == null)
			sessionUserId = "1";
		else
			sessionUserId = sessUsrId.toString();
		return sessionUserId;
	}
	
	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}
	
	public IclubMessageBoardBean getMessageBoardBean() {
		return messageBoardBean;
	}
	
	public void setMessageBoardBean(IclubMessageBoardBean messageBoardBean) {
		this.messageBoardBean = messageBoardBean;
	}
}
