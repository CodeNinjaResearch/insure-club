package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
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
import org.json.JSONObject;

import za.co.iclub.pss.model.ui.IclubCohortBean;
import za.co.iclub.pss.model.ui.IclubCohortInviteBean;
import za.co.iclub.pss.model.ui.IclubCohortSummaryBean;
import za.co.iclub.pss.model.ui.IclubCohortTypeBean;
import za.co.iclub.pss.model.ui.IclubNotificationTypeBean;
import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ui.yahoo.YahooContactBean;
import za.co.iclub.pss.model.ui.yahoo.YahooFieldBean;
import za.co.iclub.pss.model.ws.IclubCohortInviteModel;
import za.co.iclub.pss.model.ws.IclubCohortModel;
import za.co.iclub.pss.model.ws.IclubCohortSummaryModel;
import za.co.iclub.pss.model.ws.IclubCohortTypeModel;
import za.co.iclub.pss.model.ws.IclubNotificationTypeModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.trans.IclubCohortInviteTrans;
import za.co.iclub.pss.trans.IclubCohortTrans;
import za.co.iclub.pss.trans.IclubCohortTypeTrans;
import za.co.iclub.pss.trans.IclubNotificationTypeTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	private boolean inviteFromFbApp;
	
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
				IclubCohortBean bean = IclubCohortTrans.fromWStoUI(model);
				
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
						this.bean = bean;
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
					IclubCohortModel model = IclubCohortTrans.fromUItoWS(bean);
					
					model.setCId(UUID.randomUUID().toString());
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
						
						IclubCohortInviteModel model = IclubCohortInviteTrans.fromUItoWS(bean);
						
						model.setIclubCohort(this.bean.getCId());
						model.setCiCrtdDt(new Timestamp(System.currentTimeMillis()));
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
				bean.setCId(UUID.randomUUID().toString());
				IclubCohortModel model = IclubCohortTrans.fromUItoWS(bean);
				
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
				IclubCohortModel model = IclubCohortTrans.fromUItoWS(bean);
				
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
				
				/*
				 * FacebookClient facebookClient = new
				 * DefaultFacebookClient(access_token, Version.VERSION_2_3);
				 * Connection<User> myFriends =
				 * facebookClient.fetchConnection("me/friends", User.class); if
				 * (myFriends != null && myFriends.getData() != null) { User
				 * user = myFriends.getData().get(0); System.out.println(user);
				 * }
				 */
				
				String redirectUrl = "https://www.facebook.com/dialog/apprequests?app_id=" + BUNDLE.getString("fb.client_id") + "&redirect_uri=" + BUNDLE.getString("fb.app_redirect_uri") + "&message=" + BUNDLE.getString("fb.message");
				try {
					IclubWebHelper.addObjectIntoSession("access_token", access_token);
					IclubWebHelper.addObjectIntoSession("fromSocial", fromSocial);
					IclubWebHelper.addObjectIntoSession("guid", guid);
					FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} else if (fromSocial != null && fromSocial.equalsIgnoreCase("fbapp")) {
				boolean flag = true;
				int i = 0;
				while (flag) {
					HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
					
					String userId = request.getParameter("to[" + i + "]");
					System.out.println(userId + "========");
					if (userId != null && !userId.trim().equalsIgnoreCase("")) {
						String profileUrl = "https://graph.facebook.com/" + userId + "?access_token=" + access_token;
						URL u = new URL(profileUrl);
						URLConnection c = u.openConnection();
						BufferedReader in = new BufferedReader(new InputStreamReader(c.getInputStream()));
						String inputLine;
						StringBuffer b = new StringBuffer();
						while ((inputLine = in.readLine()) != null)
							b.append(inputLine + "\n");
						in.close();
						String graph = b.toString();
						JSONObject json = new JSONObject(graph);
						
						IclubCohortInviteBean bean = new IclubCohortInviteBean();
						bean.setCiId(UUID.randomUUID().toString());
						bean.setCiInviteFName(json.getString("first_name"));
						bean.setCiInviteLName(json.getString("last_name"));
						bean.setCiInviteAcceptYn("Y");
						bean.setIclubNotificationType(3l);
						bean.setCiInviteUri(userId);
						if (bean.getCiInviteUri() != null && !bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
							cohortsInviteBeanMap.put(bean.getCiInviteUri(), bean);
						}
						System.out.println(json.getString("first_name") + "------Json------" + json);
					} else {
						flag = false;
					}
					i++;
					
				}
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
				@SuppressWarnings("deprecation")
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
					
					if (fromSocial != null && fromSocial.equalsIgnoreCase("fbapp")) {
						inviteFromFbApp = true;
						List<IclubCohortInviteModel> models = new ArrayList<IclubCohortInviteModel>();
						client = IclubWebHelper.createCustomClient(CI_BASE_URL + "addList");
						for (IclubCohortInviteBean bean : cohortsInviteBeans) {
							
							IclubCohortInviteModel model = IclubCohortInviteTrans.fromUItoWS(bean);
							
							model.setIclubCohort(this.bean.getCId());
							model.setCiCrtdDt(new Timestamp(System.currentTimeMillis()));
							models.add(model);
							
						}
						client.accept(MediaType.APPLICATION_JSON).postCollection(models, IclubCohortInviteModel.class, ResponseModel.class);
						client.close();
					}
					
				} else {
					inviteFromFbApp = true;
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
				
				IclubCohortBean bean = IclubCohortTrans.fromWStoUI(model);
				
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
				
				IclubPersonBean bean = IclubPersonTrans.fromWStoUI(model);
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
				
				IclubCohortTypeBean bean = IclubCohortTypeTrans.fromWStoUI(model);
				
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
				
				IclubNotificationTypeBean bean = IclubNotificationTypeTrans.fromWStoUI(model);
				
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
			
		} else if (request.getParameter("from") != null && request.getParameter("from").toString().equalsIgnoreCase("fbapp")) {
			String access_token = IclubWebHelper.getObjectIntoSession("access_token") != null ? IclubWebHelper.getObjectIntoSession("access_token").toString() : null;
			String guid = IclubWebHelper.getObjectIntoSession("guid") != null ? IclubWebHelper.getObjectIntoSession("guid").toString() : null;
			setIclubCohortInvite(access_token, "fbapp", guid);
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
	
	public boolean isInviteFromFbApp() {
		return inviteFromFbApp;
	}
	
	public void setInviteFromFbApp(boolean inviteFromFbApp) {
		this.inviteFromFbApp = inviteFromFbApp;
	}
	
}
