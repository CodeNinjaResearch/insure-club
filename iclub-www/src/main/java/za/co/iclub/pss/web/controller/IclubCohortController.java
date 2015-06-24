package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import za.co.iclub.pss.web.bean.IclubCohortBean;
import za.co.iclub.pss.web.bean.IclubCohortInviteBean;
import za.co.iclub.pss.web.bean.IclubCohortSummaryBean;
import za.co.iclub.pss.web.bean.IclubCohortTypeBean;
import za.co.iclub.pss.web.bean.IclubNotificationTypeBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.yahoo.YahooContactBean;
import za.co.iclub.pss.web.bean.yahoo.YahooFieldBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubCohortInviteModel;
import za.co.iclub.pss.ws.model.IclubCohortModel;
import za.co.iclub.pss.ws.model.IclubCohortSummaryModel;
import za.co.iclub.pss.ws.model.IclubCohortTypeModel;
import za.co.iclub.pss.ws.model.IclubNotificationTypeModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

@ManagedBean(name = "iclubCohortController")
@SessionScoped
public class IclubCohortController implements Serializable {
	
	private static final long serialVersionUID = 8245517153102756484L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubCohortController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCohortService/";
	private static final String NFT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubNotificationTypeService/";
	private static final String CI_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCohortInviteService/";
	private static final String CHT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCohortTypeService/";
	private static final String P_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private List<IclubCohortBean> beans;
	private List<IclubCohortBean> dashBoardBeans;
	private List<IclubPersonBean> personBeans;
	private List<IclubCohortTypeBean> cohortTypeBeans;
	private List<IclubCohortBean> selectedBeans;
	private List<IclubCohortInviteBean> selectedInviteBeans;
	private List<IclubCohortInviteBean> cohortsInviteBeans;
	private List<IclubNotificationTypeBean> iclubNotificationTypeBeans;
	
	private IclubCohortBean bean;
	private IclubCohortSummaryBean cohortSummaryBean;
	private IclubCohortSummaryBean cohortSummaryUserBean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private boolean showSummaryCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	private String key;
	private String fromSocial;
	private String guid;
	private String cohortId;
	private boolean cohortSummaryFlag;
	
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
		showSummaryCont = false;
		viewParam = 1l;
		selectedBeans = new ArrayList<IclubCohortBean>();
	}
	
	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubCohortBean();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		showSummaryCont = false;
		viewParam = 1l;
		selectedBeans = new ArrayList<IclubCohortBean>();
	}
	
	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		showSummaryCont = false;
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
	
	public String cohortSummaryAction() {
		cohortSummaryFlag = false;
		if (cohortId != null && !cohortId.trim().equalsIgnoreCase("")) {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "getCohortSummaryById/" + cohortId);
			IclubCohortSummaryModel model = (IclubCohortSummaryModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubCohortSummaryModel.class));
			client.close();
			WebClient userClient = IclubWebHelper.createCustomClient(BASE_URL + "getCohortSummaryById/" + getSessionUserId());
			IclubCohortSummaryModel userModel = (IclubCohortSummaryModel) (userClient.accept(MediaType.APPLICATION_JSON).get(IclubCohortSummaryModel.class));
			cohortSummaryBean = new IclubCohortSummaryBean();
			cohortSummaryUserBean = new IclubCohortSummaryBean();
			
			if (model != null) {
				cohortSummaryFlag = true;
				cohortSummaryBean.setClaimSinceI(model.getClaimSinceI() != null ? model.getClaimSinceI() : 0.0);
				cohortSummaryBean.setClaimsInYear(model.getClaimsInYear() != null ? model.getClaimsInYear() : 0.0);
				cohortSummaryBean.setPremiumForYear(model.getPremiumForYear() != null ? model.getPremiumForYear() : 0.0);
				cohortSummaryBean.setPremiumPaidInYear(model.getPremiumPaidInYear() != null ? model.getPremiumPaidInYear() : 0.0);
				cohortSummaryBean.setPrimumSinceI(model.getPrimumSinceI() != null ? model.getPrimumSinceI() : 0.0);
			}
			if (userModel != null) {
				cohortSummaryFlag = true;
				cohortSummaryUserBean.setClaimSinceI(model.getClaimSinceI() != null ? model.getClaimSinceI() : 0.0);
				cohortSummaryUserBean.setClaimsInYear(model.getClaimsInYear() != null ? model.getClaimsInYear() : 0.0);
				cohortSummaryUserBean.setPremiumForYear(model.getPremiumForYear() != null ? model.getPremiumForYear() : 0.0);
				cohortSummaryUserBean.setPremiumPaidInYear(model.getPremiumPaidInYear() != null ? model.getPremiumPaidInYear() : 0.0);
				cohortSummaryUserBean.setPrimumSinceI(model.getPrimumSinceI() != null ? model.getPrimumSinceI() : 0.0);
			}
			
		} else {
			IclubWebHelper.addMessage("Please Enter Cohort Id", FacesMessage.SEVERITY_INFO);
		}
		return null;
	}
	
	public List<IclubCohortBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubCohortModel> models = new ArrayList<IclubCohortModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCohortModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubCohortBean>();
		if (models != null && models.size() > 0) {
			for (IclubCohortModel model : models) {
				IclubCohortBean bean = new IclubCohortBean();
				
				model.setCId(bean.getCId());
				model.setCId(bean.getCId());
				model.setCName(bean.getCName());
				model.setCEmail(bean.getCEmail());
				model.setCInitDt(bean.getCInitDt());
				model.setCFinalizeDt(bean.getCFinalizeDt());
				model.setCTotalContrib(bean.getCTotalContrib());
				model.setCCollectedContrib(bean.getCCollectedContrib());
				model.setCCurMemberCnt(bean.getCCurMemberCnt());
				model.setIclubCohortType(bean.getIclubCohortType());
				model.setCCrtdDt(bean.getCCrtdDt());
				model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId());
				model.setIclubPersonByCCrtdBy(bean.getIclubPersonByCCrtdBy());
				if (bean.getIclubCohortClaims() != null && bean.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = bean.getIclubCohortClaims();
					model.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (bean.getIclubCohortPersons() != null && bean.getIclubCohortPersons().length > 0) {
					String[] iclubCohortPersons = bean.getIclubCohortPersons();
					
					model.setIclubCohortPersons(iclubCohortPersons);
				}
				
				if (bean.getIclubPersons() != null && bean.getIclubPersons().length > 0) {
					String[] iclubPersons = bean.getIclubPersons();
					
					model.setIclubPersons(iclubPersons);
				}
				
				if (bean.getIclubCohortInvites() != null && bean.getIclubCohortInvites().length > 0) {
					String[] iclubCohortInvites = bean.getIclubCohortInvites();
					
					model.setIclubCohortInvites(iclubCohortInvites);
				}
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void setDashBoardBeans(List<IclubCohortBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubCohortBean();
	}
	
	public String addIclubCohorts() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCohort");
		try {
			if (validateForm(true)) {
				
				List<IclubCohortModel> models = new ArrayList<IclubCohortModel>();
				if (selectedBeans != null && selectedBeans.size() == 1 && !showCreateCont) {
					for (IclubCohortBean bean : selectedBeans) {
						WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + getSessionUserId());
						
						IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
						
						client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
						model.setIclubCohort(bean.getCId());
						ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
						client.close();
						if (response.getStatusCode() == 0) {
							IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
							viewParam = 1l;
							showView();
							
							return "cohortInvites.xhtml?faces-redirect=true";
						} else {
							IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
						}
					}
				} else if (showCreateCont) {
					WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
					IclubCohortModel model = new IclubCohortModel();
					
					model.setCId(UUID.randomUUID().toString());
					model.setCName(bean.getCName());
					model.setCEmail(bean.getCEmail());
					model.setCInitDt(bean.getCInitDt());
					model.setCFinalizeDt(bean.getCFinalizeDt());
					model.setCTotalContrib(bean.getCTotalContrib());
					model.setCCollectedContrib(bean.getCCollectedContrib());
					model.setCCurMemberCnt(bean.getCCurMemberCnt());
					model.setIclubCohortType(bean.getIclubCohortType());
					model.setIclubPersonByCPrimaryUserId(getSessionUserId());
					model.setIclubPersonByCCrtdBy(getSessionUserId());
					model.setCCrtdDt(new Date(System.currentTimeMillis()));
					models.add(model);
					
					ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0) {
						
						client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + getSessionUserId());
						
						IclubPersonModel person = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
						
						client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
						person.setIclubCohort(bean.getCId());
						response = client.accept(MediaType.APPLICATION_JSON).put(person, ResponseModel.class);
						
						IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
						viewParam = 1l;
						showView();
						
						return "cohortInvites.xhtml?face-redirect=true";
					} else {
						IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					}
				}
				
				else {
					IclubWebHelper.addMessage("Select one Row", FacesMessage.SEVERITY_INFO);
					return "";
				}
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "";
	}
	
	public String addIclubCohortsInvites() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCohort");
		try {
			if (validateForm(true)) {
				
				List<IclubCohortInviteModel> models = new ArrayList<IclubCohortInviteModel>();
				if (selectedInviteBeans != null && selectedInviteBeans.size() > 0) {
					WebClient client = IclubWebHelper.createCustomClient(CI_BASE_URL + "addList");
					for (IclubCohortInviteBean bean : selectedInviteBeans) {
						
						IclubCohortInviteModel model = new IclubCohortInviteModel();
						
						model.setCiId(bean.getCiId());
						model.setIclubCohort(bean.getIclubCohort());
						model.setCiCrtdDt(new Timestamp(System.currentTimeMillis()));
						model.setIclubNotificationType(bean.getIclubNotificationType());
						model.setCiInviteAcceptYn(bean.getCiInviteAcceptYn());
						model.setCiInviteUri(bean.getCiInviteUri());
						model.setIclubPerson(getSessionUserId());
						models.add(model);
						
					}
					ResponseModel response = client.accept(MediaType.APPLICATION_JSON).postCollection(models, IclubCohortInviteModel.class, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0) {
						IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
						viewParam = 1l;
						showView();
						return "qq";
					} else {
						IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					}
				} else {
					IclubWebHelper.addMessage("Select one Row", FacesMessage.SEVERITY_INFO);
					return "";
				}
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "";
	}
	
	public void addIclubCohort() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCohort");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubCohortModel model = new IclubCohortModel();
				
				bean.setCId(UUID.randomUUID().toString());
				model.setCId(bean.getCId());
				model.setCName(bean.getCName());
				model.setCEmail(bean.getCEmail());
				model.setCInitDt(bean.getCInitDt());
				model.setCFinalizeDt(bean.getCFinalizeDt());
				model.setCTotalContrib(bean.getCTotalContrib());
				model.setCCollectedContrib(bean.getCCollectedContrib());
				model.setCCurMemberCnt(bean.getCCurMemberCnt());
				model.setIclubCohortType(bean.getIclubCohortType());
				model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubCohort() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubCohort");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubCohortModel model = new IclubCohortModel();
				
				model.setCId(bean.getCId());
				model.setCId(bean.getCId());
				model.setCName(bean.getCName());
				model.setCEmail(bean.getCEmail());
				model.setCInitDt(bean.getCInitDt());
				model.setCFinalizeDt(bean.getCFinalizeDt());
				model.setCTotalContrib(bean.getCTotalContrib());
				model.setCCollectedContrib(bean.getCCollectedContrib());
				model.setCCurMemberCnt(bean.getCCurMemberCnt());
				model.setIclubCohortType(bean.getIclubCohortType());
				model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubCohort() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubCohort");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getCId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void setIclubCohortInvite(String access_token, String fromSocial, String guid) {
		try {
			Map<String, IclubCohortInviteBean> cohortsInviteBeanMap = new HashMap<String, IclubCohortInviteBean>();
			LOGGER.info("Class :: " + this.getClass() + " :: Method :: setIclubCohortInvite");
			if (fromSocial != null && fromSocial.equalsIgnoreCase("FB")) {
				
				FacebookClient facebookClient = new DefaultFacebookClient(access_token, Version.VERSION_2_3);
				Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
				if (myFriends != null && myFriends.getData() != null) {
					User user = myFriends.getData().get(0);
					System.out.println(user);
				}
				
				/*
				 * String g =
				 * "https://graph.facebook.com/me/friends?access_token=" +
				 * access_token; URL u = new URL(g); URLConnection c =
				 * u.openConnection(); BufferedReader in = new
				 * BufferedReader(new InputStreamReader(c.getInputStream()));
				 * String inputLine; StringBuffer b = new StringBuffer(); while
				 * ((inputLine = in.readLine()) != null) b.append(inputLine +
				 * "\n"); in.close(); String graph = b.toString(); JSONObject
				 * json = new JSONObject(graph); String data =
				 * json.getString("data").replace("[", "").replace("]", "");
				 * String summary = json.getString("summary").replace("[",
				 * "").replace("]", ""); String paging =
				 * json.getString("paging"); JSONObject fbBean = new
				 * JSONObject(data); JSONObject fbSumBean = new
				 * JSONObject(summary); JSONObject fbPagBean = new
				 * JSONObject(paging); System.out.println("Js-----------" +
				 * json); // "paging":{"next":" if (fbBean != null &&
				 * fbBean.has("id")) { // g = "https://graph.facebook.com/" + //
				 * fbBean.getString("id") + "?access_token=" + access_token; //
				 * u = new URL(g); // c = u.openConnection(); // in = new
				 * BufferedReader(new // InputStreamReader(c.getInputStream()));
				 * // // b = new StringBuffer(); // while ((inputLine =
				 * in.readLine()) != null) // b.append(inputLine + "\n"); //
				 * in.close(); // graph = b.toString(); if (fbSumBean != null &&
				 * fbSumBean.has("total_count")) {
				 * 
				 * Integer totalCount = new
				 * Integer(fbSumBean.getString("total_count")); for (int i = 1;
				 * i < totalCount; i++) { g = fbPagBean.getString("next"); u =
				 * new URL(g); c = u.openConnection(); in = new
				 * BufferedReader(new InputStreamReader(c.getInputStream()));
				 * 
				 * b = new StringBuffer(); while ((inputLine = in.readLine()) !=
				 * null) b.append(inputLine + "\n"); in.close(); graph =
				 * b.toString(); json = new JSONObject(graph); data =
				 * json.getString("data").replace("[", "").replace("]", ""); if
				 * (data != null) fbBean = new JSONObject(data); paging =
				 * json.getString("paging"); fbPagBean = new JSONObject(paging);
				 * System.out.println(i + "--I-----------" + graph); } } }
				 */
			} else if (fromSocial != null && fromSocial.equalsIgnoreCase("yahoo")) {
				HttpClient client = new DefaultHttpClient();
				String callUrl1 = "https://social.yahooapis.com/v1/user/" + guid + "/contacts?format=json";
				HttpGet httpGet = new HttpGet(callUrl1);
				httpGet.setHeader("Authorization", "Bearer " + access_token);
				httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
				HttpResponse response2 = client.execute(httpGet);
				String outputString = EntityUtils.toString(response2.getEntity());
				JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
				jsonGet = (JsonObject) new JsonParser().parse(jsonGet.get("contacts").toString());
				System.out.println(jsonGet);
				ObjectMapper mapper = new ObjectMapper();
				List<YahooContactBean> contactBeans = mapper.readValue(jsonGet.get("contact").toString(), TypeFactory.collectionType(List.class, YahooContactBean.class));
				
				if (contactBeans != null && contactBeans.size() > 0) {
					for (YahooContactBean contactBean : contactBeans) {
						IclubCohortInviteBean bean = new IclubCohortInviteBean();
						bean.setCiId(UUID.randomUUID().toString());
						if (contactBean.getFields() != null && contactBean.getFields().size() > 0) {
							for (YahooFieldBean fBean : contactBean.getFields()) {
								if (fBean.getType() != null && fBean.getType().equalsIgnoreCase("email")) {
									bean.setCiInviteUri(fBean.getValue().toString());
									break;
								} else if (fBean.getType() != null && fBean.getType().equalsIgnoreCase("phone")) {
									bean.setCiInviteUri(fBean.getValue().toString());
								}
							}
						}
						if (bean.getCiInviteUri() != null && !bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
							cohortsInviteBeanMap.put(bean.getCiInviteUri(), bean);
						}
					}
					
				}
				
				System.out.println(contactBeans + "------outputString   :\n");
			} else {
				
				ContactsService myService = new ContactsService("iclub");
				URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full?access_token=" + access_token);
				ContactFeed resultFeed = myService.getFeed(feedUrl, ContactFeed.class);
				// Print the results
				
				for (ContactEntry entry : resultFeed.getEntries()) {
					IclubCohortInviteBean bean = new IclubCohortInviteBean();
					bean.setCiId(UUID.randomUUID().toString());
					
					for (Email email : entry.getEmailAddresses()) {
						System.out.print(" " + email.getAddress());
						if (email.getAddress() != null) {
							bean.setCiInviteUri(email.getAddress());
							break;
						}
					}
					
					if (bean.getCiInviteUri() == null || bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
						if (entry.hasName()) {
							if (entry.hasPhoneNumbers()) {
								for (PhoneNumber phnum : entry.getPhoneNumbers()) {
									bean.setCiInviteUri(phnum.getPhoneNumber());
									break;
								}
							}
							
						}
					}
					if (bean.getCiInviteUri() != null && !bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
						cohortsInviteBeanMap.put(bean.getCiInviteUri(), bean);
					}
				}
				
			}
			if (cohortsInviteBeanMap != null && cohortsInviteBeanMap.size() > 0) {
				
				WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "getMNumberList");
				
				Collection<? extends String> existingNumbers = client.accept(MediaType.APPLICATION_JSON).postAndGetCollection(cohortsInviteBeanMap.keySet(), String.class, String.class);
				client.close();
				
				if (existingNumbers != null && existingNumbers.size() > 0) {
					for (String number : existingNumbers) {
						cohortsInviteBeanMap.remove(number);
					}
				}
				
				client = IclubWebHelper.createCustomClient(P_BASE_URL + "getEmailsList");
				
				Collection<? extends String> existingEmials = client.accept(MediaType.APPLICATION_JSON).postAndGetCollection(cohortsInviteBeanMap.keySet(), String.class, String.class);
				client.close();
				if (existingEmials != null && existingEmials.size() > 0) {
					for (String email : existingEmials) {
						cohortsInviteBeanMap.remove(email);
					}
				}
				if (cohortsInviteBeanMap.size() > 0) {
					cohortsInviteBeans = new ArrayList<IclubCohortInviteBean>(cohortsInviteBeanMap.values());
				} else {
					cohortsInviteBeans = new ArrayList<IclubCohortInviteBean>();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		return ret;
	}
	
	public IclubCohortBean getBean() {
		if (bean == null)
			bean = new IclubCohortBean();
		return bean;
	}
	
	public void setBean(IclubCohortBean bean) {
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
	
	public List<IclubCohortBean> getBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubCohortModel> models = new ArrayList<IclubCohortModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCohortModel.class));
		client.close();
		beans = new ArrayList<IclubCohortBean>();
		if (models != null && models.size() > 0) {
			for (IclubCohortModel model : models) {
				
				IclubCohortBean bean = new IclubCohortBean();
				
				bean.setCId(model.getCId());
				bean.setCName(model.getCName());
				bean.setCEmail(model.getCEmail());
				bean.setCInitDt(model.getCInitDt());
				bean.setCFinalizeDt(model.getCFinalizeDt());
				bean.setCTotalContrib(model.getCTotalContrib());
				bean.setCCollectedContrib(model.getCCollectedContrib());
				bean.setCCurMemberCnt(model.getCCurMemberCnt());
				bean.setIclubCohortType(model.getIclubCohortType());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setIclubPersonByCPrimaryUserId(model.getIclubPersonByCPrimaryUserId());
				bean.setIclubPersonByCCrtdBy(model.getIclubPersonByCCrtdBy());
				if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = model.getIclubCohortClaims();
					bean.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (model.getIclubCohortPersons() != null && model.getIclubCohortPersons().length > 0) {
					String[] iclubCohortPersons = model.getIclubCohortPersons();
					bean.setIclubCohortPersons(iclubCohortPersons);
				}
				
				if (model.getIclubPersons() != null && model.getIclubPersons().length > 0) {
					String[] iclubPersons = model.getIclubPersons();
					bean.setIclubPersons(iclubPersons);
				}
				
				if (model.getIclubCohortInvites() != null && model.getIclubCohortInvites().length > 0) {
					String[] iclubCohortInvites = model.getIclubCohortInvites();
					bean.setIclubCohortInvites(iclubCohortInvites);
				}
				beans.add(bean);
				
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubCohortBean> beans) {
		this.beans = beans;
	}
	
	public boolean isShowSummaryCont() {
		return showSummaryCont;
	}
	
	public void setShowSummaryCont(boolean showSummaryCont) {
		this.showSummaryCont = showSummaryCont;
	}
	
	public List<IclubPersonBean> getPersonBeans() {
		WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "list");
		Collection<? extends IclubPersonModel> models = new ArrayList<IclubPersonModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPersonModel.class));
		client.close();
		personBeans = new ArrayList<IclubPersonBean>();
		if (models != null && models.size() > 0) {
			for (IclubPersonModel model : models) {
				
				IclubPersonBean bean = new IclubPersonBean();
				bean.setPId(model.getPId());
				bean.setPFName(model.getPFName());
				bean.setPLName(model.getPLName());
				personBeans.add(bean);
			}
		}
		return personBeans;
	}
	
	public void setPersonBeans(List<IclubPersonBean> personBeans) {
		this.personBeans = personBeans;
	}
	
	public List<IclubCohortTypeBean> getCohortTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CHT_BASE_URL + "list");
		Collection<? extends IclubCohortTypeModel> models = new ArrayList<IclubCohortTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCohortTypeModel.class));
		client.close();
		cohortTypeBeans = new ArrayList<IclubCohortTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubCohortTypeModel model : models) {
				
				IclubCohortTypeBean bean = new IclubCohortTypeBean();
				
				bean.setCtId(model.getCtId());
				bean.setCtLongDesc(model.getCtLongDesc());
				bean.setCtShortDesc(model.getCtShortDesc());
				bean.setCtStatus(model.getCtStatus());
				
				cohortTypeBeans.add(bean);
			}
		}
		return cohortTypeBeans;
	}
	
	public void setCohortTypeBeans(List<IclubCohortTypeBean> cohortTypeBeans) {
		this.cohortTypeBeans = cohortTypeBeans;
	}
	
	public List<IclubCohortBean> getSelectedBeans() {
		if (selectedBeans == null) {
			selectedBeans = new ArrayList<IclubCohortBean>();
		}
		return selectedBeans;
	}
	
	public void setSelectedBeans(List<IclubCohortBean> selectedBeans) {
		this.selectedBeans = selectedBeans;
	}
	
	public List<IclubCohortInviteBean> getCohortsInviteBeans() {
		if (cohortsInviteBeans == null) {
			cohortsInviteBeans = new ArrayList<IclubCohortInviteBean>();
		}
		
		return cohortsInviteBeans;
	}
	
	public void setCohortsInviteBeans(List<IclubCohortInviteBean> cohortsInviteBeans) {
		this.cohortsInviteBeans = cohortsInviteBeans;
	}
	
	public List<IclubCohortInviteBean> getSelectedInviteBeans() {
		if (selectedInviteBeans == null) {
			selectedInviteBeans = new ArrayList<IclubCohortInviteBean>();
		}
		return selectedInviteBeans;
	}
	
	public void setSelectedInviteBeans(List<IclubCohortInviteBean> selectedInviteBeans) {
		this.selectedInviteBeans = selectedInviteBeans;
	}
	
	public List<IclubNotificationTypeBean> getIclubNotificationTypeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(NFT_BASE_URL + "list");
		Collection<? extends IclubNotificationTypeModel> models = new ArrayList<IclubNotificationTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubNotificationTypeModel.class));
		client.close();
		iclubNotificationTypeBeans = new ArrayList<IclubNotificationTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubNotificationTypeModel model : models) {
				IclubNotificationTypeBean bean = new IclubNotificationTypeBean();
				model.setNtId(bean.getNtId());
				model.setNtLongDesc(bean.getNtLongDesc());
				model.setNtShortDesc(bean.getNtShortDesc());
				model.setNtStatus(bean.getNtStatus());
				bean.setIclubNotifs(model.getIclubNotifs());
				iclubNotificationTypeBeans.add(bean);
			}
		}
		return iclubNotificationTypeBeans;
	}
	
	public void setIclubNotificationTypeBeans(List<IclubNotificationTypeBean> iclubNotificationTypeBeans) {
		this.iclubNotificationTypeBeans = iclubNotificationTypeBeans;
	}
	
	public String getKey() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		if (request.getParameter("key") != null) {
			key = (String) request.getParameter("key");
			fromSocial = (String) request.getParameter("from");
			guid = (String) request.getParameter("guid");
			request.removeAttribute("key");
			request.removeAttribute("guid");
			request.removeAttribute("from");
			setIclubCohortInvite(key, fromSocial, guid);
			
		}
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public IclubCohortSummaryBean getCohortSummaryBean() {
		if (cohortSummaryBean == null) {
			cohortSummaryBean = new IclubCohortSummaryBean();
		}
		
		return cohortSummaryBean;
	}
	
	public void setCohortSummaryBean(IclubCohortSummaryBean cohortSummaryBean) {
		this.cohortSummaryBean = cohortSummaryBean;
	}
	
	public String getCohortId() {
		return cohortId;
	}
	
	public void setCohortId(String cohortId) {
		this.cohortId = cohortId;
	}
	
	public IclubCohortSummaryBean getCohortSummaryUserBean() {
		if (cohortSummaryUserBean == null) {
			cohortSummaryUserBean = new IclubCohortSummaryBean();
		}
		return cohortSummaryUserBean;
	}
	
	public void setCohortSummaryUserBean(IclubCohortSummaryBean cohortSummaryUserBean) {
		this.cohortSummaryUserBean = cohortSummaryUserBean;
	}
	
	public boolean isCohortSummaryFlag() {
		return cohortSummaryFlag;
	}
	
	public void setCohortSummaryFlag(boolean cohortSummaryFlag) {
		this.cohortSummaryFlag = cohortSummaryFlag;
	}
	
	public String getFromSocial() {
		return fromSocial;
	}
	
	public void setFromSocial(String fromSocial) {
		this.fromSocial = fromSocial;
	}
	
	public String getGuid() {
		return guid;
	}
	
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
}
