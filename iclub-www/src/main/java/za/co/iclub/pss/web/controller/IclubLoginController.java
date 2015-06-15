package za.co.iclub.pss.web.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubLoginBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubLoginController")
@SessionScoped
public class IclubLoginController implements Serializable {
	
	private static final long serialVersionUID = 8092436101540887282L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	private static final ResourceBundle Y_BUNDLE = ResourceBundle.getBundle("yahoo-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubLoginController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLoginService/";
	private static final String U_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private List<IclubLoginBean> beans;
	private IclubLoginBean bean;
	private boolean showAddPanel;
	private boolean showModPanel;
	
	public String googleAction() {
		String redirectUrl = "https://accounts.google.com/o/oauth2/auth?scope=" + BUNDLE.getString("scope") + "&redirect_uri=" + BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + BUNDLE.getString("client_id") + "&approval_prompt=force";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getLoginRedirectURL() {
		
		String redirectUrl = "https://graph.facebook.com/oauth/authorize?client_id=" + BUNDLE.getString("fb.client_id") + "&display=page&redirect_uri=" + BUNDLE.getString("fb.redirect_uri") + "&scope=" + BUNDLE.getString("fb.perms2");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String yahooAction() {
		String redirectUrl = "https://api.login.yahoo.com/oauth2/request_auth?redirect_uri=" + Y_BUNDLE.getString("redirect_uri") + "&response_type=code&client_id=" + Y_BUNDLE.getString("client_id") + "&language=en-us";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String hotmailAction() {
		String redirectUrl = "https://login.live.com/oauth20_authorize.srf?scope=wl.signin%20wl.basic" + "&redirect_uri=http://localhost:8080/iclub-www/Oauth2callback" + "&response_type=token&client_id=000000004C15240C";
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		// client_id=CLIENT_ID&scope=SCOPES&response_type=RESPONSE_TYPE&redirect_uri=REDIRECT_URL
	}
	
	public String doIclubLogin() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: doIclubLogin");
		if (!validateLogin()) {
			try {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "person/login/" + bean.getLName() + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(bean.getLPasswd())));
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Person Logged-In successfully", FacesMessage.SEVERITY_INFO);
					WebClient loginClient = IclubWebHelper.createCustomClient(BASE_URL + "person/" + bean.getLName());
					IclubLoginModel model = loginClient.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class);
					loginClient.close();
					if (model != null && model.getLId() != null) {
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonByLPersonId());
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), bean.getLName());
						WebClient personClient = IclubWebHelper.createCustomClient(U_BASE_URL + "get/" + model.getIclubPersonByLPersonId());
						IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
						personClient.close();
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), 1l);
						
						return "home";
						
					} else {
						IclubWebHelper.addMessage("Person Profile Fetch Error - Contact Admin", FacesMessage.SEVERITY_ERROR);
						return "";
					}
				} else {
					IclubWebHelper.addMessage("Login error :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					return "";
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
				IclubWebHelper.addMessage("Login error :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				return "";
			}
		} else {
			return "";
		}
	}
	
	public void doIclubLogout() {
		IclubWebHelper.invalidateSession();
		FacesContext context = FacesContext.getCurrentInstance();
		NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
		navigationHandler.handleNavigation(context, null, "/templates/home.xhtml?faces-redirect=true");
	}
	
	public boolean validateLogin() {
		boolean ret = false;
		if (bean.getLName() == null || bean.getLName().trim().length() == 0) {
			IclubWebHelper.addMessage("Login Name Cannot be null", FacesMessage.SEVERITY_ERROR);
			ret = true;
		}
		if (bean.getLPasswd() == null || bean.getLPasswd().trim().length() == 0) {
			IclubWebHelper.addMessage("Password Cannot be null", FacesMessage.SEVERITY_ERROR);
			ret = true;
		}
		return ret;
	}
	
	public List<IclubLoginBean> getBeans() {
		return beans;
	}
	
	public void setBeans(List<IclubLoginBean> beans) {
		this.beans = beans;
	}
	
	public IclubLoginBean getBean() {
		if (bean == null)
			bean = new IclubLoginBean();
		return bean;
	}
	
	public void setBean(IclubLoginBean bean) {
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
	
}
