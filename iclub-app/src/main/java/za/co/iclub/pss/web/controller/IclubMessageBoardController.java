package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import za.co.iclub.pss.model.ws.IclubMessageBoardModel;
import za.co.iclub.pss.trans.IclubMbCommentTrans;
import za.co.iclub.pss.trans.IclubMessageBoardTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubMessageBoardController")
@SessionScoped
public class IclubMessageBoardController implements Serializable {
	
	private static final long serialVersionUID = 8245517153102756484L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubMessageBoardController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-app/services/iclub/IclubMessageBoardService/";
	private static final String MB_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-app/services/iclub/IclubMbCommentService/";
	private List<IclubMessageBoardBean> beans;
	private List<IclubMessageBoardBean> dashBoardBeans;
	private List<IclubMbCommentBean> mbCommentBeans;
	private IclubMessageBoardBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private boolean showSummaryCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	private IclubMessageBoardBean summeryBean;
	
	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		else if (viewParam != null && viewParam.longValue() == 3)
			showSummary();
		
	}
	
	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		viewParam = 1l;
	}
	
	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubMessageBoardBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		viewParam = 1l;
	}
	
	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		viewParam = 2l;
	}
	
	public void showSummary() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showSummary");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = false;
		showSummaryCont = true;
		viewParam = 3l;
	}
	
	public String summaryAction(IclubMessageBoardBean bean) {
		IclubWebHelper.addObjectIntoSession("messageBoardBean", bean);
		return "summary.xhtml?faces-redirect=true";
	}
	
	public List<IclubMessageBoardBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubMessageBoardModel> models = new ArrayList<IclubMessageBoardModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMessageBoardModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubMessageBoardBean>();
		if (models != null && models.size() > 0) {
			for (IclubMessageBoardModel model : models) {
				IclubMessageBoardBean bean = IclubMessageBoardTrans.fromWStoUI(model);
				
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void setDashBoardBeans(List<IclubMessageBoardBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubMessageBoardBean();
	}
	
	public String addIclubMessageBoard() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubMessageBoard");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubMessageBoardModel model = IclubMessageBoardTrans.fromUItoWS(bean);
				
				model.setMbId(UUID.randomUUID().toString());
				model.setMbCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPerson(getSessionUserId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					return null;
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return null;
		}
		return "messages.xhtml?faces-redirect=true";
	}
	
	public void modIclubMessageBoard() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubMessageBoard");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubMessageBoardModel model = new IclubMessageBoardModel();
				
				model.setMbId(bean.getMbId());
				model.setMbContent(bean.getMbContent());
				model.setMbContent(bean.getMbContent());
				model.setMbTag(bean.getMbTag());
				model.setMbTitle(bean.getMbTitle());
				model.setMbCrtdDt(bean.getMbCrtdDt());
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubMessageBoard() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubMessageBoard");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getMbId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("message") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getMbTag() == null || bean.getMbTag().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mb Tag Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getMbTitle() == null || bean.getMbTitle().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mb Title Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getMbContent() == null || bean.getMbContent().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mb Content Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public IclubMessageBoardBean getBean() {
		if (bean == null)
			bean = new IclubMessageBoardBean();
		return bean;
	}
	
	public void setBean(IclubMessageBoardBean bean) {
		this.bean = bean;
	}
	
	public boolean isShowCreateCont() {
		return showCreateCont;
	}
	
	public void setShowCreateCont(boolean showCreateCont) {
		this.showCreateCont = showCreateCont;
	}
	
	public boolean isShowViewCont() {
		return showViewCont;
	}
	
	public void setShowViewCont(boolean showViewCont) {
		this.showViewCont = showViewCont;
	}
	
	public boolean isShowEditCont() {
		return showEditCont;
	}
	
	public void setShowEditCont(boolean showEditCont) {
		this.showEditCont = showEditCont;
	}
	
	public Long getViewParam() {
		return viewParam;
	}
	
	public void setViewParam(Long viewParam) {
		this.viewParam = viewParam;
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
	
	public String getUserName() {
		userName = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.scname")).toString();
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public ResourceBundle getLabelBundle() {
		if (labelBundle == null) {
			labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		}
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	public List<IclubMessageBoardBean> getBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubMessageBoardModel> models = new ArrayList<IclubMessageBoardModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMessageBoardModel.class));
		client.close();
		beans = new ArrayList<IclubMessageBoardBean>();
		if (models != null && models.size() > 0) {
			for (IclubMessageBoardModel model : models) {
				
				IclubMessageBoardBean bean = IclubMessageBoardTrans.fromWStoUI(model);
				
				bean.setIclubPerson(getSessionUserId());
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubMessageBoardBean> beans) {
		this.beans = beans;
	}
	
	public boolean isShowSummaryCont() {
		return showSummaryCont;
	}
	
	public void setShowSummaryCont(boolean showSummaryCont) {
		this.showSummaryCont = showSummaryCont;
	}
	
	public IclubMessageBoardBean getSummeryBean() {
		
		if (IclubWebHelper.getObjectIntoSession("messageBoardBean") != null) {
			summeryBean = (IclubMessageBoardBean) IclubWebHelper.getObjectIntoSession("messageBoardBean");
			
		} else {
			summeryBean = new IclubMessageBoardBean();
		}
		return summeryBean;
	}
	
	public void setSummeryBean(IclubMessageBoardBean summeryBean) {
		this.summeryBean = summeryBean;
	}
	
	public List<IclubMbCommentBean> getMbCommentBeans() {
		WebClient client = IclubWebHelper.createCustomClient(MB_BASE_URL + "list");
		Collection<? extends IclubMbCommentModel> models = new ArrayList<IclubMbCommentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMbCommentModel.class));
		client.close();
		mbCommentBeans = new ArrayList<IclubMbCommentBean>();
		if (models != null && models.size() > 0) {
			for (IclubMbCommentModel model : models) {
				IclubMbCommentBean bean = IclubMbCommentTrans.fromWStoUI(model);
				
				mbCommentBeans.add(bean);
			}
		}
		return mbCommentBeans;
	}
	
	public void setMbCommentBeans(List<IclubMbCommentBean> mbCommentBeans) {
		this.mbCommentBeans = mbCommentBeans;
	}
	
}
