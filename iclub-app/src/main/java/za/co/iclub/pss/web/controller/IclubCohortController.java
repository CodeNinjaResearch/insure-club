package za.co.iclub.pss.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
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
import za.co.iclub.pss.model.ui.IclubCohortCriteriaBean;
import za.co.iclub.pss.model.ui.IclubCohortInviteBean;
import za.co.iclub.pss.model.ui.IclubCohortSummaryBean;
import za.co.iclub.pss.model.ui.IclubCohortTypeBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.model.ui.IclubInviteStatusBean;
import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ui.IclubNotificationTypeBean;
import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ui.OutLookContactsBean;
import za.co.iclub.pss.model.ui.OutlookContactDataBean;
import za.co.iclub.pss.model.ui.yahoo.YahooContactBean;
import za.co.iclub.pss.model.ui.yahoo.YahooFieldBean;
import za.co.iclub.pss.model.ws.IclubCohortCriteriaModel;
import za.co.iclub.pss.model.ws.IclubCohortInviteModel;
import za.co.iclub.pss.model.ws.IclubCohortModel;
import za.co.iclub.pss.model.ws.IclubCohortSummaryModel;
import za.co.iclub.pss.model.ws.IclubCohortTypeModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.model.ws.IclubInviteStatusModel;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.model.ws.IclubNotificationTypeModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.trans.IclubCohortCriteriaTrans;
import za.co.iclub.pss.trans.IclubCohortInviteTrans;
import za.co.iclub.pss.trans.IclubCohortTrans;
import za.co.iclub.pss.trans.IclubCohortTypeTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTypeTrans;
import za.co.iclub.pss.trans.IclubInviteStatusTrans;
import za.co.iclub.pss.trans.IclubMaritialStatusTrans;
import za.co.iclub.pss.trans.IclubNotificationTypeTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

import com.google.gdata.client.Query;
import com.google.gdata.client.contacts.ContactsService;
import com.google.gdata.data.contacts.ContactEntry;
import com.google.gdata.data.contacts.ContactFeed;
import com.google.gdata.data.extensions.Email;
import com.google.gdata.data.extensions.PhoneNumber;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@ManagedBean(name = "iclubCohortController")
@SessionScoped
@SuppressWarnings({ "resource", "deprecation" })
public class IclubCohortController implements Serializable {
	
	private static final long serialVersionUID = 8245517153102756484L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubCohortController.class);
	private static final String BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortService/";
	private static final String NFT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubNotificationTypeService/";
	private static final String MS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubMaritialStatusService/";
	private static final String CI_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortInviteService/";
	private static final String CC_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortCriteriaService/";
	private static final String CHT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCohortTypeService/";
	private static final String IIT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemTypeService/";
	private static final String P_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	private static final String IS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInviteStatusService/";
	private List<IclubCohortBean> beans;
	private List<IclubCohortBean> dashBoardBeans;
	private List<IclubPersonBean> personBeans;
	private List<IclubCohortTypeBean> cohortTypeBeans;
	private List<IclubCohortBean> selectedBeans;
	private List<IclubCohortInviteBean> selectedInviteBeans;
	private List<IclubCohortInviteBean> cohortsInviteBeans;
	private List<IclubCohortInviteBean> newCohortsInviteBeans;
	private List<IclubNotificationTypeBean> iclubNotificationTypeBeans;
	private List<IclubInsuranceItemTypeBean> iclubInsuranceItemTypeBeans;
	
	private IclubCohortBean bean;
	private IclubCohortCriteriaBean criteriaBean;
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
	private String newInviteKey;
	private String fromSocial;
	private String guid;
	private String cohortId;
	private boolean cohortSummaryFlag;
	private boolean inviteFromFbApp;
	private boolean newInvites;
	private List<IclubCohortInviteBean> adminCohortInviteBeans;
	private List<IclubCohortInviteBean> selecteAdminCohortInviteBeans;
	private List<IclubInviteStatusBean> inviteStatusBeans;
	private boolean deRegister;
	private IclubCohortInviteModel inviteModel;
	private IclubCohortInviteBean cohortInviteBean;
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	
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
	
	public void showCriteria() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCriteria");
		
		if (cohortInviteBean != null) {
			WebClient client = IclubWebHelper.createCustomClient(CC_BASE_URL + "get/" + cohortInviteBean.getCohortCriteriaId());
			
			IclubCohortCriteriaModel model = (IclubCohortCriteriaModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubCohortCriteriaModel.class));
			
			criteriaBean = IclubCohortCriteriaTrans.fromWStoUI(model);
			
			client.close();
		}
		
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
	
	public String criteriaAction() {
		
		if (validateForm(true)) {
			selectedBeans = getSelectedBeans();
			if (selectedBeans.size() == 0)
				selectedBeans.add(bean);
			return addIclubCohorts();
		}
		return "";
	}
	
	public String addIclubCohorts() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubCohort");
		try {
			List<IclubCohortModel> models = new ArrayList<IclubCohortModel>();
			if (!showCreateCont && validateForm(true)) {
				
				if (selectedBeans != null && selectedBeans.size() == 1 && !showCreateCont) {
					for (IclubCohortBean bean : selectedBeans) {
						this.bean = bean;
						
						WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + getSessionUserId());
						
						IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
						client.close();
						IclubCohortInviteModel inviteModel = getInviteModel();
						inviteModel.setCiInviteFName(model.getPFName());
						inviteModel.setCiInviteLName(model.getPLName());
						inviteModel.setCiInviteUri(model.getPEmail());
						inviteModel.setIclubCohort(this.bean.getCId());
						inviteModel.setCiCrtdDt(new Date(System.currentTimeMillis()));
						inviteModel.setCiInviteAcceptYn("Y");
						inviteModel.setIclubInviteStatus(2l);
						inviteModel.setCiInviteSentStatus("Y");
						inviteModel.setIclubNotificationType(3l);
						ResponseModel response = null;
						if (inviteModel.getCiId() == null) {
							inviteModel.setCiId(UUID.randomUUID().toString());
							inviteModel.setIclubPerson(getSessionUserId());
							client = IclubWebHelper.createCustomClient(CI_BASE_URL + "add");
							response = client.accept(MediaType.APPLICATION_JSON).post(inviteModel, ResponseModel.class);
							client.close();
							model.setIclubCohortInvite(inviteModel.getCiId());
							client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
							response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
							client.close();
							
						} else {
							
							try {
								client = IclubWebHelper.createCustomClient(CI_BASE_URL + "mod");
								response = client.accept(MediaType.APPLICATION_JSON).put(inviteModel, ResponseModel.class);
								client.close();
								model.setIclubCohortInvite(inviteModel.getCiId());
								client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
								response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
								client.close();
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						if (response.getStatusCode() == 0) {
							criteriaBean.setCcId(UUID.randomUUID().toString());
							criteriaBean.setIclubCohortInvite(inviteModel.getCiId());
							IclubCohortCriteriaModel criteriaModel = IclubCohortCriteriaTrans.fromUItoWS(criteriaBean);
							client = IclubWebHelper.createCustomClient(CC_BASE_URL + "add");
							response = client.accept(MediaType.APPLICATION_JSON).post(criteriaModel, ResponseModel.class);
							client.close();
							IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
							viewParam = 1l;
							showView();
							if (!deRegister) {
								return "cohortInvites.xhtml?faces-redirect=true";
							} else {
								deRegister = false;
								return "userDashboard";
							}
						} else {
							IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
						}
					}
				}
				
				else {
					IclubWebHelper.addMessage("Select one Row", FacesMessage.SEVERITY_INFO);
					return "";
				}
				
			} else if (showCreateCont) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				bean.setCId(UUID.randomUUID().toString());
				IclubCohortModel model = IclubCohortTrans.fromUItoWS(bean);
				
				model.setIclubPersonByCPrimaryUserId(getSessionUserId());
				model.setIclubPersonByCCrtdBy(getSessionUserId());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPersonByAdminId(getSessionUserId());
				models.add(model);
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + getSessionUserId());
					
					IclubPersonModel person = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
					
					viewParam = 1l;
					
					IclubCohortInviteModel inviteModel = new IclubCohortInviteModel();
					inviteModel.setCiInviteFName(person.getPFName());
					inviteModel.setCiInviteLName(person.getPLName());
					inviteModel.setCiInviteUri(person.getPEmail());
					inviteModel.setIclubCohort(this.bean.getCId());
					inviteModel.setCiCrtdDt(new Date(System.currentTimeMillis()));
					inviteModel.setIclubPerson(getSessionUserId());
					inviteModel.setCiInviteAcceptYn("Y");
					inviteModel.setCiInviteSentStatus("Y");
					inviteModel.setIclubInviteStatus(3l);
					inviteModel.setIclubNotificationType(3l);
					inviteModel.setCiId(UUID.randomUUID().toString());
					
					client = IclubWebHelper.createCustomClient(CI_BASE_URL + "add");
					response = client.accept(MediaType.APPLICATION_JSON).post(inviteModel, ResponseModel.class);
					client.close();
					try {
						client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
						person.setIclubCohort(bean.getCId());
						person.setIclubCohortInvite(inviteModel.getCiId());
						response = client.accept(MediaType.APPLICATION_JSON).put(person, ResponseModel.class);
						IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
						showView();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					if (!deRegister) {
						return "cohortInvites.xhtml?face-redirect=true";
					} else {
						deRegister = false;
						return "userDashboard";
					}
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("bankmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
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
			if (true) {
				
				List<IclubCohortInviteModel> models = new ArrayList<IclubCohortInviteModel>();
				if (selectedInviteBeans != null && selectedInviteBeans.size() > 0) {
					WebClient client = IclubWebHelper.createCustomClient(CI_BASE_URL + "addList");
					for (IclubCohortInviteBean bean : selectedInviteBeans) {
						
						IclubCohortInviteModel model = IclubCohortInviteTrans.fromUItoWS(bean);
						
						model.setIclubCohort(this.bean.getCId());
						model.setCiCrtdDt(new Date(System.currentTimeMillis()));
						model.setIclubPerson(getSessionUserId());
						model.setCiInviteAcceptYn("N");
						model.setIclubInviteStatus(1l);
						models.add(model);
						
					}
					ResponseModel response = client.accept(MediaType.APPLICATION_JSON).postCollection(models, IclubCohortInviteModel.class, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0) {
						IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
						viewParam = 1l;
						showView();
						if (newInvites) {
							return "userDashboard";
						} else {
							return "qq";
						}
						
					} else {
						IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					}
				} else {
					if (newInvites) {
						return "userDashboard";
					} else {
						return "qq";
					}
				}
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("cohortinvite") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "";
	}
	
	public String deRegisterCohort() {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: deRegisterCohort");
		try {
			WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + getSessionUserId());
			IclubPersonModel model = client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
			
			model.setIclubCohort(null);
			client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
			if (response.getStatusCode() == 0) {
				IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
				deRegister = true;
				return "/pages/admin/cohorts/allCohorts.xhtml?faces-redirect=true";
			} else {
				IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Cohort " + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
	
	public void rejectInvites() {
		updatecohort(4l, "deleted");
	}
	
	public void approveInvites() {
		updatecohort(3l, "approved");
	}
	
	public void updatecohort(Long status, String approveOrReject) {
		try {
			
			if (selecteAdminCohortInviteBeans != null && selecteAdminCohortInviteBeans.size() > 0) {
				List<IclubCohortInviteModel> models = new ArrayList<IclubCohortInviteModel>();
				for (IclubCohortInviteBean bean : selecteAdminCohortInviteBeans) {
					
					if (status == 3 && bean.getIclubInviteStatus() == 2) {
						WebClient client = IclubWebHelper.createCustomClient(P_BASE_URL + "get/" + bean.getIclubPerson());
						IclubPersonModel personModel = client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
						
						personModel.setIclubCohort(bean.getIclubCohort());
						client = IclubWebHelper.createCustomClient(P_BASE_URL + "mod");
						ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(personModel, ResponseModel.class);
						if (response.getStatusCode() == 0) {
							
						}
					}
					bean.setIclubInviteStatus(status);
					IclubCohortInviteModel model = IclubCohortInviteTrans.fromUItoWS(bean);
					models.add(model);
					
				}
				WebClient client = IclubWebHelper.createCustomClient(CI_BASE_URL + "modList");
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).postCollection(models, IclubCohortInviteModel.class, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("CohortInvite" + " " + approveOrReject + " Successfully", FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage("CohortInvite" + " " + approveOrReject + "error :: Web Service Error - Contact Admin", FacesMessage.SEVERITY_ERROR);
				}
			} else {
				IclubWebHelper.addMessage("Select atleast on record", FacesMessage.SEVERITY_INFO);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("CohortInvite" + " " + approveOrReject + "error :: Web Service Error - Contact Admin", FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void setIclubCohortInvite(String access_token, String fromSocial, String guid) {
		try {
			Map<String, IclubCohortInviteBean> cohortsInviteBeanMap = new HashMap<String, IclubCohortInviteBean>();
			LOGGER.info("Class :: " + this.getClass() + " :: Method :: setIclubCohortInvite");
			LOGGER.info(fromSocial + "=====FromSocial====");
			if (newInvites) {
				access_token = IclubWebHelper.getObjectIntoSession("key").toString();
			}
			if (fromSocial != null && fromSocial.equalsIgnoreCase("OUTLOOK")) {
				
				HttpClient client = new DefaultHttpClient();
				HttpGet outlookRequest = new HttpGet("https://apis.live.net/v5.0/me/contacts?access_token=" + access_token);
				HttpResponse response = client.execute(outlookRequest);
				String outputString = EntityUtils.toString(response.getEntity());
				
				OutlookContactDataBean data = new Gson().fromJson(outputString, OutlookContactDataBean.class);
				
				if (data != null && data.getData() != null && data.getData().size() > 0) {
					
					for (OutLookContactsBean contact : data.getData()) {
						IclubCohortInviteBean bean = new IclubCohortInviteBean();
						bean.setCiId(UUID.randomUUID().toString());
						bean.setCiInviteFName(contact.getFirst_name());
						bean.setCiInviteLName(contact.getLast_name());
						bean.setCiInviteAcceptYn("N");
						bean.setIclubNotificationType(3l);
						bean.setCiInviteUri(contact.getEmails() != null ? contact.getEmails().getPreferred() : contact.getPhones() != null ? contact.getPhones().getPreferred() : null);
						if (bean.getCiInviteUri() != null && !bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
							cohortsInviteBeanMap.put(bean.getCiInviteUri(), bean);
						}
					}
					
				}
			} else if (fromSocial != null && fromSocial.equalsIgnoreCase("FB")) {
				
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
						bean.setCiInviteAcceptYn("N");
						bean.setIclubNotificationType(3l);
						bean.setCiInviteUri(userId);
						if (bean.getCiInviteUri() != null && !bean.getCiInviteUri().trim().equalsIgnoreCase("")) {
							cohortsInviteBeanMap.put(bean.getCiInviteUri(), bean);
						}
					} else {
						flag = false;
					}
					i++;
					
				}
			} else if (fromSocial != null && fromSocial.equalsIgnoreCase("yahoo")) {
				HttpClient client = new DefaultHttpClient();
				String callUrl1 = "https://social.yahooapis.com/v1/user/me" + "/contacts?format=json";
				HttpGet httpGet = new HttpGet(callUrl1);
				httpGet.setHeader("Authorization", "Bearer " + access_token);
				httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
				HttpResponse response2 = client.execute(httpGet);
				String outputString = EntityUtils.toString(response2.getEntity());
				JsonObject jsonGet = (JsonObject) new JsonParser().parse(outputString);
				if (jsonGet.has("contacts")) {
					jsonGet = (JsonObject) new JsonParser().parse(jsonGet.get("contacts").toString());
					ObjectMapper mapper = new ObjectMapper();
					List<YahooContactBean> contactBeans = mapper.readValue(jsonGet.get("contact").toString(), TypeFactory.collectionType(List.class, YahooContactBean.class));
					
					if (contactBeans != null && contactBeans.size() > 0) {
						for (YahooContactBean contactBean : contactBeans) {
							IclubCohortInviteBean bean = new IclubCohortInviteBean();
							bean.setCiInviteAcceptYn("N");
							bean.setIclubNotificationType(3l);
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
				}
				
			} else {
				
				ContactsService myService = new ContactsService("iclub");
				myService.setHeader("Authorization", "Bearer " + access_token);
				URL feedUrl = new URL("https://www.google.com/m8/feeds/contacts/default/full?access_token=" + access_token);
				Query myQuery = new Query(feedUrl);
				myQuery.setStartIndex(1);
				myQuery.setMaxResults(500);
				ContactFeed resultFeed = myService.query(myQuery, ContactFeed.class);
				for (ContactEntry entry : resultFeed.getEntries()) {
					IclubCohortInviteBean bean = new IclubCohortInviteBean();
					bean.setCiId(UUID.randomUUID().toString());
					for (Email email : entry.getEmailAddresses()) {
						if (email.getAddress() != null) {
							bean.setCiInviteUri(email.getAddress());
							break;
						}
					}
					bean.setIclubNotificationType(3l);
					bean.setCiInviteAcceptYn("N");
					bean.setCiInviteFName(entry.getName() != null ? entry.getName().getFullName() != null ? entry.getName().getFullName().getValue() : null : null);
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
				
				if (newInvites) {
					client = IclubWebHelper.createCustomClient(CI_BASE_URL + "getInvitiesList");
					
					Collection<? extends String> notInvitedEmials = client.accept(MediaType.APPLICATION_JSON).postAndGetCollection(cohortsInviteBeanMap.keySet(), String.class, String.class);
					client.close();
					if (notInvitedEmials != null && notInvitedEmials.size() > 0) {
						for (String email : notInvitedEmials) {
							cohortsInviteBeanMap.remove(email);
						}
					}
					if (existingEmials != null && existingEmials.size() > 0) {
						for (String number : existingEmials) {
							cohortsInviteBeanMap.remove(number);
						}
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
							model.setCiCrtdDt(new Date(System.currentTimeMillis()));
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
		
		if (criteriaBean.getCcAge() == null) {
			IclubWebHelper.addMessage(("Age Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (criteriaBean.getCcGender() == null || criteriaBean.getCcGender().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Gender"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (criteriaBean.getCcClaimLastTwYrs() == null || criteriaBean.getCcClaimLastTwYrs().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Claims In Last Two Years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (criteriaBean.getCcClaimLastYr() == null || criteriaBean.getCcClaimLastYr().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Claims In Last Year"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
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
			if (IclubWebHelper.getObjectIntoSession("newInvites") != null) {
				newInvites = new Boolean(IclubWebHelper.getObjectIntoSession("newInvites").toString());
			}
			
			request.removeAttribute("key");
			request.removeAttribute("guid");
			request.removeAttribute("from");
			setIclubCohortInvite(key, fromSocial, guid);
			
			if ((IclubWebHelper.getObjectIntoSession("cohortInviteId") != null && !IclubWebHelper.getObjectIntoSession("cohortInviteId").toString().equalsIgnoreCase("null")) || IclubWebHelper.getObjectIntoSession("cohortId") != null) {
				try {
					
					WebClient client = null;
					ResponseModel responseModels = null;
					String cohortId = null;
					if (IclubWebHelper.getObjectIntoSession("cohortId") != null) {
						cohortId = IclubWebHelper.getObjectIntoSession("cohortId").toString();
					}
					if (cohortId == null || cohortId.trim().equalsIgnoreCase("")) {
						client = IclubWebHelper.createCustomClient(CI_BASE_URL + "get/" + IclubWebHelper.getObjectIntoSession("cohortInviteId").toString());
						inviteModel = client.get(IclubCohortInviteModel.class);
						inviteModel.setCiInviteAcceptYn("Y");
						try {
							client = IclubWebHelper.createCustomClient(CI_BASE_URL + "mod");
							responseModels = client.accept(MediaType.APPLICATION_JSON).put(inviteModel, ResponseModel.class);
							cohortId = inviteModel.getIclubCohort();
							IclubWebHelper.addObjectIntoSession("cohortInviteId", null);
						} catch (Exception e) {
							
							e.printStackTrace();
						}
					}
					
					if ((responseModels != null && responseModels.getStatusCode() == 0) || (cohortId != null && !cohortId.trim().equalsIgnoreCase(""))) {
						client = IclubWebHelper.createCustomClient(BASE_URL + "get/" + cohortId);
						IclubCohortModel model = client.get(IclubCohortModel.class);
						IclubCohortBean bean = IclubCohortTrans.fromWStoUI(model);
						selectedBeans = new ArrayList<IclubCohortBean>();
						selectedBeans.add(bean);
						NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
						navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/admin/cohorts/" + addIclubCohorts());
					}
				} catch (Exception e) {
					
				}
				
			}
			
		} else if (request.getParameter("from") != null && request.getParameter("from").toString().equalsIgnoreCase("fbapp")) {
			String access_token = IclubWebHelper.getObjectIntoSession("access_token") != null ? IclubWebHelper.getObjectIntoSession("access_token").toString() : null;
			String guid = IclubWebHelper.getObjectIntoSession("guid") != null ? IclubWebHelper.getObjectIntoSession("guid").toString() : null;
			setIclubCohortInvite(access_token, "fbapp", guid);
		}
		return key;
	}
	
	public String getNewInviteKey() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		
		if (request.getParameter("key") != null) {
			key = (String) request.getParameter("key");
			fromSocial = (String) request.getParameter("from");
			guid = (String) request.getParameter("guid");
			if (IclubWebHelper.getObjectIntoSession("newInvites") != null) {
				newInvites = new Boolean(IclubWebHelper.getObjectIntoSession("newInvites").toString());
			}
			
			request.removeAttribute("key");
			request.removeAttribute("guid");
			request.removeAttribute("from");
			setIclubCohortInvite(key, fromSocial, guid);
			
			if (IclubWebHelper.getObjectIntoSession("cohortInviteId") != null || IclubWebHelper.getObjectIntoSession("cohortId") != null) {
				try {
					
					WebClient client = null;
					ResponseModel responseModels = null;
					String cohortId = null;
					if (IclubWebHelper.getObjectIntoSession("cohortId") != null) {
						cohortId = IclubWebHelper.getObjectIntoSession("cohortId").toString();
					}
					if (cohortId == null || cohortId.trim().equalsIgnoreCase("")) {
						client = IclubWebHelper.createCustomClient(CI_BASE_URL + "get/" + IclubWebHelper.getObjectIntoSession("cohortInviteId").toString());
						inviteModel = client.get(IclubCohortInviteModel.class);
						inviteModel.setCiInviteAcceptYn("Y");
						try {
							client = IclubWebHelper.createCustomClient(CI_BASE_URL + "mod");
							responseModels = client.accept(MediaType.APPLICATION_JSON).put(inviteModel, ResponseModel.class);
							cohortId = inviteModel.getIclubCohort();
							IclubWebHelper.addObjectIntoSession("cohortInviteId", null);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if ((responseModels != null && responseModels.getStatusCode() == 0) || (cohortId != null && !cohortId.trim().equalsIgnoreCase(""))) {
						client = IclubWebHelper.createCustomClient(BASE_URL + "get/" + cohortId);
						IclubCohortModel model = client.get(IclubCohortModel.class);
						IclubCohortBean bean = IclubCohortTrans.fromWStoUI(model);
						this.bean = bean;
						selectedBeans = new ArrayList<IclubCohortBean>();
						selectedBeans.add(bean);
					}
				} catch (Exception e) {
					
				}
				
			}
			
		} else if (request.getParameter("from") != null && request.getParameter("from").toString().equalsIgnoreCase("fbapp")) {
			String access_token = IclubWebHelper.getObjectIntoSession("access_token") != null ? IclubWebHelper.getObjectIntoSession("access_token").toString() : null;
			String guid = IclubWebHelper.getObjectIntoSession("guid") != null ? IclubWebHelper.getObjectIntoSession("guid").toString() : null;
			setIclubCohortInvite(access_token, "fbapp", guid);
		}
		return newInviteKey;
	}
	
	public void setNewInviteKey(String newInviteKey) {
		this.newInviteKey = newInviteKey;
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
	
	public List<IclubCohortInviteBean> getNewCohortsInviteBeans() {
		return newCohortsInviteBeans;
	}
	
	public void setNewCohortsInviteBeans(List<IclubCohortInviteBean> newCohortsInviteBeans) {
		this.newCohortsInviteBeans = newCohortsInviteBeans;
	}
	
	public boolean isNewInvites() {
		return newInvites;
	}
	
	public void setNewInvites(boolean newInvites) {
		this.newInvites = newInvites;
	}
	
	public List<IclubInsuranceItemTypeBean> getIclubInsuranceItemTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		iclubInsuranceItemTypeBeans = new ArrayList<IclubInsuranceItemTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemTypeModel model : models) {
				
				IclubInsuranceItemTypeBean bean = IclubInsuranceItemTypeTrans.fromWStoUI(model);
				
				iclubInsuranceItemTypeBeans.add(bean);
			}
		}
		return iclubInsuranceItemTypeBeans;
	}
	
	public void setIclubInsuranceItemTypeBeans(List<IclubInsuranceItemTypeBean> iclubInsuranceItemTypeBeans) {
		this.iclubInsuranceItemTypeBeans = iclubInsuranceItemTypeBeans;
	}
	
	public List<IclubCohortInviteBean> getAdminCohortInviteBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CI_BASE_URL + "admin/list/" + getSessionUserId());
		Collection<? extends IclubCohortInviteModel> models = new ArrayList<IclubCohortInviteModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCohortInviteModel.class));
		client.close();
		adminCohortInviteBeans = new ArrayList<IclubCohortInviteBean>();
		if (models != null && models.size() > 0) {
			for (IclubCohortInviteModel model : models) {
				
				IclubCohortInviteBean bean = IclubCohortInviteTrans.fromWStoUI(model);
				
				adminCohortInviteBeans.add(bean);
			}
		}
		return adminCohortInviteBeans;
	}
	
	public void setAdminCohortInviteBeans(List<IclubCohortInviteBean> adminCohortInviteBeans) {
		this.adminCohortInviteBeans = adminCohortInviteBeans;
	}
	
	public List<IclubInviteStatusBean> getInviteStatusBeans() {
		WebClient client = IclubWebHelper.createCustomClient(IS_BASE_URL + "list");
		Collection<? extends IclubInviteStatusModel> models = new ArrayList<IclubInviteStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInviteStatusModel.class));
		client.close();
		inviteStatusBeans = new ArrayList<IclubInviteStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubInviteStatusModel model : models) {
				
				IclubInviteStatusBean bean = IclubInviteStatusTrans.fromWStoUI(model);
				
				inviteStatusBeans.add(bean);
			}
		}
		return inviteStatusBeans;
	}
	
	public void setInviteStatusBeans(List<IclubInviteStatusBean> inviteStatusBeans) {
		this.inviteStatusBeans = inviteStatusBeans;
	}
	
	public List<IclubCohortInviteBean> getSelecteAdminCohortInviteBeans() {
		if (selecteAdminCohortInviteBeans == null) {
			selecteAdminCohortInviteBeans = new ArrayList<IclubCohortInviteBean>();
		}
		return selecteAdminCohortInviteBeans;
	}
	
	public void setSelecteAdminCohortInviteBeans(List<IclubCohortInviteBean> selecteAdminCohortInviteBeans) {
		this.selecteAdminCohortInviteBeans = selecteAdminCohortInviteBeans;
	}
	
	public boolean isDeRegister() {
		return deRegister;
	}
	
	public void setDeRegister(boolean deRegister) {
		this.deRegister = deRegister;
	}
	
	public IclubCohortInviteModel getInviteModel() {
		if (inviteModel == null)
			inviteModel = new IclubCohortInviteModel();
		return inviteModel;
	}
	
	public void setInviteModel(IclubCohortInviteModel inviteModel) {
		this.inviteModel = inviteModel;
	}
	
	public IclubCohortCriteriaBean getCriteriaBean() {
		if (criteriaBean == null) {
			criteriaBean = new IclubCohortCriteriaBean();
		}
		return criteriaBean;
	}
	
	public void setCriteriaBean(IclubCohortCriteriaBean criteriaBean) {
		this.criteriaBean = criteriaBean;
	}
	
	public IclubCohortInviteBean getCohortInviteBean() {
		return cohortInviteBean;
	}
	
	public void setCohortInviteBean(IclubCohortInviteBean cohortInviteBean) {
		this.cohortInviteBean = cohortInviteBean;
	}
	
	public List<IclubMaritialStatusBean> getMaritialStatusBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(MS_BASE_URL + "list");
		Collection<? extends IclubMaritialStatusModel> models = new ArrayList<IclubMaritialStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubMaritialStatusModel.class));
		client.close();
		maritialStatusBeans = new ArrayList<IclubMaritialStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubMaritialStatusModel model : models) {
				IclubMaritialStatusBean bean = IclubMaritialStatusTrans.fromWStoUI(model);
				
				maritialStatusBeans.add(bean);
			}
		}
		return maritialStatusBeans;
	}
	
	public void setMaritialStatusBeans(List<IclubMaritialStatusBean> maritialStatusBeans) {
		this.maritialStatusBeans = maritialStatusBeans;
	}
	
}
