package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import za.co.iclub.pss.model.ui.IclubCountryCodeBean;
import za.co.iclub.pss.model.ui.IclubIdTypeBean;
import za.co.iclub.pss.model.ui.IclubLoginBean;
import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ui.IclubOccupationBean;
import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ui.IclubSecurityQuestionBean;
import za.co.iclub.pss.model.ui.IclubSupplMasterBean;
import za.co.iclub.pss.model.ui.IclubSupplierTypeBean;
import za.co.iclub.pss.model.ws.IclubCountryCodeModel;
import za.co.iclub.pss.model.ws.IclubIdTypeModel;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.model.ws.IclubOccupationModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.model.ws.IclubSecurityQuestionModel;
import za.co.iclub.pss.model.ws.IclubSupplMasterModel;
import za.co.iclub.pss.model.ws.IclubSupplPersonModel;
import za.co.iclub.pss.model.ws.IclubSupplierTypeModel;
import za.co.iclub.pss.trans.IclubCountryCodeTrans;
import za.co.iclub.pss.trans.IclubIdTypeTrans;
import za.co.iclub.pss.trans.IclubLoginTrans;
import za.co.iclub.pss.trans.IclubMaritialStatusTrans;
import za.co.iclub.pss.trans.IclubOccupationTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.trans.IclubSecurityQuestionTrans;
import za.co.iclub.pss.trans.IclubSupplMasterTrans;
import za.co.iclub.pss.trans.IclubSupplierTypeTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubSupplMasterController")
@SessionScoped
public class IclubSupplMasterController implements Serializable {
	
	private static final long serialVersionUID = 2776100159112333991L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubSupplMasterController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplMasterService/";
	private static final String SP_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplPersonService/";
	private static final String ST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplierTypeService/";
	private static final String LOG_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLoginService/";
	private static final String PER_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private static final String MS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubIdTypeService/";
	private static final String SECQ_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityQuestionService/";
	private static final String OCN_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupationService/";
	private static final String CCDE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCountryCodeService/";
	private List<IclubSupplMasterBean> beans;
	private List<IclubSupplierTypeBean> supplierTypeBeans;
	private List<IclubSupplMasterBean> dashBoardBeans;
	private IclubSupplMasterBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	
	private MapModel draggableModelPer;
	private Marker markerPer;
	private String centerGeoMapPer = "36.890257,30.707417";
	private IclubLoginBean loginBean;
	private IclubPersonBean personBean;
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	private List<IclubIdTypeBean> idTypeBeans;
	private List<IclubPersonBean> personBeans;
	private Map<String, IclubLoginBean> loginBeans;
	private List<IclubOccupationBean> occupationBeans;
	private List<IclubCountryCodeBean> countryCodeBeans;
	private List<IclubSecurityQuestionBean> securityQuestionBeans;
	private boolean showPerAddPanel;
	private boolean showPerModPanel;
	private Integer activeIndex;
	
	public void showPerAddPanel() {
		showPerAddPanel = true;
		showPerModPanel = false;
		personBean = new IclubPersonBean();
	}
	
	public void clearPerForm() {
		showPerAddPanel = false;
		showPerModPanel = false;
		loginBean = new IclubLoginBean();
		personBean = new IclubPersonBean();
	}
	
	public void showPerModPanel() {
		showPerAddPanel = false;
		showPerModPanel = true;
		loginBean = getLoginBeans().get(personBean.getPId());
	}
	
	public MapModel getDraggableModelPer() {
		return draggableModelPer;
	}
	
	public String getCenterGeoMapPer() {
		return centerGeoMapPer;
	}
	
	public void onMarkerDragPer(MarkerDragEvent event) {
		markerPer = event.getMarker();
		
		bean.setSmLat(markerPer.getLatlng().getLat());
		bean.setSmLong(markerPer.getLatlng().getLng());
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerPer.getLatlng().getLat() + ", Lng:" + markerPer.getLatlng().getLng()));
	}
	
	public void onGeocodePer(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapPer = center.getLat() + "," + center.getLng();
			draggableModelPer = new DefaultMapModel();
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				Marker marker = new Marker(result.getLatLng(), result.getAddress());
				marker.setDraggable(true);
				draggableModelPer.addOverlay(marker);
			}
		}
	}
	
	public void onMarkerSelectPer(OverlaySelectEvent event) {
		markerPer = (Marker) event.getOverlay();
		bean.setSmLat(markerPer.getLatlng().getLat());
		bean.setSmLong(markerPer.getLatlng().getLng());
		bean.setSmAddress(markerPer.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPer.getTitle()));
	}
	
	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		draggableModelPer = new DefaultMapModel();
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		
	}
	
	public void showView() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showView");
		showCreateCont = false;
		showViewCont = true;
		showEditCont = false;
		activeIndex = 0;
		viewParam = 1l;
	}
	
	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubSupplMasterBean();
		draggableModelPer = new DefaultMapModel();
		personBean = new IclubPersonBean();
		loginBean = new IclubLoginBean();
		personBeans = new ArrayList<IclubPersonBean>();
		loginBeans = new TreeMap<String, IclubLoginBean>();
		showCreateCont = true;
		showViewCont = false;
		showEditCont = false;
		activeIndex = 0;
		viewParam = 1l;
	}
	
	public void showEdit() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showEdit");
		activeIndex = 0;
		showCreateCont = false;
		showViewCont = false;
		showEditCont = true;
		setIclubPersonsList();
		if (bean.getSmLat() != null && bean.getSmLat() != null) {
			centerGeoMapPer = bean.getSmLat() + "," + bean.getSmLong();
			LatLng coord = new LatLng(bean.getSmLat(), bean.getSmLong());
			Marker marker = new Marker(coord, "");
			marker.setDraggable(true);
			draggableModelPer.addOverlay(marker);
			
		} else {
			draggableModelPer = new DefaultMapModel();
			centerGeoMapPer = "36.890257,30.707417";
		}
		viewParam = 2l;
	}
	
	public void setIclubPersonsList() {
		
		WebClient client = IclubWebHelper.createCustomClient(SP_BASE_URL + "get/supplMaster/" + bean.getSmId());
		Collection<? extends IclubSupplPersonModel> models = new ArrayList<IclubSupplPersonModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplPersonModel.class));
		client.close();
		personBeans = new ArrayList<IclubPersonBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplPersonModel spModel : models) {
				
				client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + spModel.getIclubPersonBBySpPersonId());
				IclubPersonModel model = client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
				client.close();
				
				if (model != null && model.getPId() != null) {
					
					IclubPersonBean personBean = IclubPersonTrans.fromWStoUI(model);
					
					getIclubLoginBean(personBean.getPId());
					
					personBeans.add(personBean);
				}
			}
		}
		
	}
	
	public void getIclubLoginBean(String personId) {
		WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "personId/" + personId);
		
		IclubLoginModel model = (IclubLoginModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class));
		if (model != null && model.getLId() != null) {
			IclubLoginBean loginBean = IclubLoginTrans.fromWStoUI(model);
			
			getLoginBeans().put(personId, loginBean);
			
		}
		
	}
	
	public List<IclubSupplMasterBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubSupplMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplMasterModel model : models) {
				IclubSupplMasterBean bean = IclubSupplMasterTrans.fromWStoUI(model);
				
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void setDashBoardBeans(List<IclubSupplMasterBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		activeIndex = 0;
		bean = new IclubSupplMasterBean();
	}
	
	public void addIclubSupplMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubSupplMaster");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubSupplMasterModel model = IclubSupplMasterTrans.fromUItoWS(bean);
				bean.setSmId(UUID.randomUUID().toString());
				
				model.setSmId(bean.getSmId());
				model.setSmCrtdDt(new Date(System.currentTimeMillis()));
				
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					if (personBeans != null && personBeans.size() > 0) {
						
						for (IclubPersonBean bean : personBeans) {
							insertIntoPerson(bean);
						}
						
					}
					
					IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubSupplMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubSupplMaster");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubSupplMasterModel model = IclubSupplMasterTrans.fromUItoWS(bean);
				
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					if (personBeans != null && personBeans.size() > 0) {
						
						for (IclubPersonBean bean : personBeans) {
							insertIntoPerson(bean);
						}
					}
					IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubSupplMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubSupplMaster");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getSmId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addIclubPerson() {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubPerson");
		try {
			if (validatePersonForm(true) && validateLoginForm(true)) {
				
				personBean.setPId(UUID.randomUUID().toString());
				
				personBean.setPAge(IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()));
				personBean.setIclubPerson(getSessionUserId());
				
				getLoginBeans().put(personBean.getPId(), loginBean);
				personBeans.add(personBean);
				
				clearPerForm();
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void modIclubPerson() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubPerson");
		try {
			if (validateForm(false)) {
				
				personBean.setPAge(IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()));
				personBean.setIclubPerson(getSessionUserId());
				getLoginBeans().put(personBean.getPId(), loginBean);
				clearPerForm();
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("supplmaster") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public ResponseModel insertIntoPerson(IclubPersonBean personBean) {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: insertIntoPerson");
		WebClient client = null;
		boolean createOrUpdate = false;
		IclubPersonModel model = IclubPersonTrans.fromUItoWS(personBean);
		if (personBean.getPCrtdDt() != null) {
			client = IclubWebHelper.createCustomClient(PER_BASE_URL + "mod");
			model.setPCrtdDt(new Date(System.currentTimeMillis()));
		} else {
			client = IclubWebHelper.createCustomClient(PER_BASE_URL + "add");
			model.setPCrtdDt(new Date(System.currentTimeMillis()));
		}
		
		model.setPAge(IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()));
		
		model.setIclubPerson(getSessionUserId());
		
		ResponseModel response = null;
		if (personBean.getPCrtdDt() != null) {
			response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
			client.close();
		} else {
			response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			createOrUpdate = true;
			client.close();
		}
		
		if (response.getStatusCode() == 0) {
			insertIntoIclubLogin(getLoginBeans().get(model.getPId()), model.getPId(), createOrUpdate);
			if (createOrUpdate) {
				insertIntoIclubSupplMaster(model);
			}
			
		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}
		
		return response;
	}
	
	public ResponseModel insertIntoIclubLogin(IclubLoginBean loginBean, String personId, boolean createOrUpdate) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: insertIntoIclubLogin");
		try {
			if ((true)) {
				IclubLoginModel model = IclubLoginTrans.fromUItoWS(loginBean);
				WebClient client = null;
				if (createOrUpdate) {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
					model.setLId(UUID.randomUUID().toString());
				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "mod");
					model.setLId(loginBean.getLId());
				}
				
				model.setIclubPersonAByLCrtdBy(getSessionUserId());
				model.setIclubPersonBByLPersonId(personId);
				// need to change
				model.setIclubRoleType(4l);
				ResponseModel response = null;
				if (createOrUpdate) {
					response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				} else {
					response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				}
				
				client.close();
				return response;
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return new ResponseModel();
	}
	
	public ResponseModel insertIntoIclubSupplMaster(IclubPersonModel personModel) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: insertIntoIclubSupplMaster");
		try {
			
			if (personModel != null && personModel.getPId() != null) {
				
				WebClient client = IclubWebHelper.createCustomClient(SP_BASE_URL + "add");
				
				IclubSupplPersonModel model = new IclubSupplPersonModel();
				
				model.setSpId(UUID.randomUUID().toString());
				model.setIclubPersonABySpCrtdBy(getSessionUserId());
				model.setIclubPersonBBySpPersonId(personModel.getPId());
				model.setIclubSupplMaster(bean.getSmId());
				
				ResponseModel response = null;
				
				response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				
				return response;
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return new ResponseModel();
	}
	
	public void delIclubPerson() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPerson");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "del/" + personBean.getPId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				personBeans.remove(personBean);
				clearPerForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public ResponseModel addIclubLoign() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatePassword");
		try {
			if (validateLoginForm(true)) {
				IclubLoginModel model = IclubLoginTrans.fromUItoWS(loginBean);
				WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
				model.setLId(UUID.randomUUID().toString());
				
				model.setLCrtdDt(new Date(System.currentTimeMillis()));
				model.setIclubPersonAByLCrtdBy(personBean.getPId());
				model.setIclubPersonBByLPersonId(personBean.getPId());
				model.setIclubRoleType(2l);
				
				ResponseModel response = null;
				
				response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				
				return response;
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return new ResponseModel();
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getSmName() == null || bean.getSmName().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage("Sm Name Cannot be empty", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getSmRegNum() == null || bean.getSmRegNum().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Reg Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getSmLat() == null || bean.getSmLong() == null) {
			IclubWebHelper.addMessage(("Please select Map Location"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getSmAddress() == null || bean.getSmAddress().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Address Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getSmCrLimit() == null) {
			IclubWebHelper.addMessage(("Cr Limit Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getSrActionDt() == null) {
			IclubWebHelper.addMessage(("Action Date Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public boolean validateLoginForm(boolean flag) {
		boolean ret = true;
		if (loginBean.getLName() == null || loginBean.getLName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Login Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginBean.getLPasswd() == null || loginBean.getLPasswd().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Password Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginBean.getIclubSecurityQuestion() == null) {
			IclubWebHelper.addMessage(("Please select Security Question"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (loginBean.getLSecAns() == null || loginBean.getLSecAns().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Security Ans Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public boolean validatePersonForm(boolean flag) {
		boolean ret = true;
		if (personBean.getPFName() == null || personBean.getPFName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("First Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPLName() == null || personBean.getPLName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Last Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPMobile() == null || personBean.getPMobile().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mobile Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (personBean.getPGender() == null || personBean.getPGender().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gender Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (personBean.getPIdNum() == null || personBean.getPIdNum().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Id Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getIclubIdType() == null) {
			IclubWebHelper.addMessage(("Please Select ID Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPIsPensioner() == null || personBean.getPIsPensioner().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Pensioner"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPDob() == null) {
			IclubWebHelper.addMessage(("Please Select DOB"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()) <= 18) {
			IclubWebHelper.addMessage(("You must be over 18 years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPIdIssueDt() == null) {
			IclubWebHelper.addMessage(("Please Select IssueDate"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.isCurrentDate(personBean.getPIdIssueDt().getTime())) {
			IclubWebHelper.addMessage(("Issue Date less than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public IclubSupplMasterBean getBean() {
		if (bean == null)
			bean = new IclubSupplMasterBean();
		return bean;
	}
	
	public void setBean(IclubSupplMasterBean bean) {
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
	
	public List<IclubSupplMasterBean> getBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "list");
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		beans = new ArrayList<IclubSupplMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplMasterModel model : models) {
				
				IclubSupplMasterBean bean = IclubSupplMasterTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public void setBeans(List<IclubSupplMasterBean> beans) {
		this.beans = beans;
	}
	
	public List<IclubSupplierTypeBean> getSupplierTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(ST_BASE_URL + "list");
		Collection<? extends IclubSupplierTypeModel> models = new ArrayList<IclubSupplierTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplierTypeModel.class));
		client.close();
		supplierTypeBeans = new ArrayList<IclubSupplierTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplierTypeModel model : models) {
				IclubSupplierTypeBean bean = IclubSupplierTypeTrans.fromWStoUI(model);
				
				supplierTypeBeans.add(bean);
			}
		}
		return supplierTypeBeans;
	}
	
	public void setSupplierTypeBeans(List<IclubSupplierTypeBean> supplierTypeBeans) {
		this.supplierTypeBeans = supplierTypeBeans;
	}
	
	public IclubLoginBean getLoginBean() {
		if (loginBean == null) {
			loginBean = new IclubLoginBean();
		}
		return loginBean;
	}
	
	public void setLoginBean(IclubLoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	public IclubPersonBean getPersonBean() {
		if (personBean == null) {
			personBean = new IclubPersonBean();
		}
		return personBean;
	}
	
	public void setPersonBean(IclubPersonBean personBean) {
		this.personBean = personBean;
	}
	
	public boolean isShowPerAddPanel() {
		return showPerAddPanel;
	}
	
	public void setShowPerAddPanel(boolean showPerAddPanel) {
		this.showPerAddPanel = showPerAddPanel;
	}
	
	public boolean isShowPerModPanel() {
		return showPerModPanel;
	}
	
	public void setShowPerModPanel(boolean showPerModPanel) {
		this.showPerModPanel = showPerModPanel;
	}
	
	public List<IclubPersonBean> getPersonBeans() {
		if (personBeans == null) {
			personBeans = new ArrayList<IclubPersonBean>();
		}
		return personBeans;
	}
	
	public void setPersonBeans(List<IclubPersonBean> personBeans) {
		this.personBeans = personBeans;
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
	
	public List<IclubIdTypeBean> getIdTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(IT_BASE_URL + "list");
		Collection<? extends IclubIdTypeModel> models = new ArrayList<IclubIdTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubIdTypeModel.class));
		client.close();
		idTypeBeans = new ArrayList<IclubIdTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubIdTypeModel model : models) {
				IclubIdTypeBean bean = IclubIdTypeTrans.fromWStoUI(model);
				
				idTypeBeans.add(bean);
			}
		}
		return idTypeBeans;
	}
	
	public void setIdTypeBeans(List<IclubIdTypeBean> idTypeBeans) {
		this.idTypeBeans = idTypeBeans;
	}
	
	public List<IclubOccupationBean> getOccupationBeans() {
		WebClient client = IclubWebHelper.createCustomClient(OCN_BASE_URL + "list");
		Collection<? extends IclubOccupationModel> models = new ArrayList<IclubOccupationModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupationModel.class));
		client.close();
		occupationBeans = new ArrayList<IclubOccupationBean>();
		if (models != null && models.size() > 0) {
			for (IclubOccupationModel model : models) {
				
				IclubOccupationBean bean = IclubOccupationTrans.fromWStoUI(model);
				
				occupationBeans.add(bean);
			}
		}
		return occupationBeans;
	}
	
	public void setOccupationBeans(List<IclubOccupationBean> occupationBeans) {
		this.occupationBeans = occupationBeans;
	}
	
	public List<IclubCountryCodeBean> getCountryCodeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(CCDE_BASE_URL + "list");
		Collection<? extends IclubCountryCodeModel> models = new ArrayList<IclubCountryCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCountryCodeModel.class));
		client.close();
		countryCodeBeans = new ArrayList<IclubCountryCodeBean>();
		if (models != null && models.size() > 0) {
			for (IclubCountryCodeModel model : models) {
				IclubCountryCodeBean bean = IclubCountryCodeTrans.fromWStoUI(model);
				
				countryCodeBeans.add(bean);
			}
		}
		return countryCodeBeans;
	}
	
	public void setCountryCodeBeans(List<IclubCountryCodeBean> countryCodeBeans) {
		this.countryCodeBeans = countryCodeBeans;
	}
	
	public List<IclubSecurityQuestionBean> getSecurityQuestionBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(SECQ_BASE_URL + "list");
		Collection<? extends IclubSecurityQuestionModel> models = new ArrayList<IclubSecurityQuestionModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityQuestionModel.class));
		client.close();
		securityQuestionBeans = new ArrayList<IclubSecurityQuestionBean>();
		if (models != null && models.size() > 0) {
			for (IclubSecurityQuestionModel model : models) {
				IclubSecurityQuestionBean bean = IclubSecurityQuestionTrans.fromWStoUI(model);
				 
				
				securityQuestionBeans.add(bean);
			}
		}
		return securityQuestionBeans;
	}
	
	public void setSecurityQuestionBeans(List<IclubSecurityQuestionBean> securityQuestionBeans) {
		this.securityQuestionBeans = securityQuestionBeans;
	}
	
	public Map<String, IclubLoginBean> getLoginBeans() {
		
		if (loginBeans == null) {
			loginBeans = new TreeMap<String, IclubLoginBean>();
		}
		return loginBeans;
	}
	
	public void setLoginBeans(Map<String, IclubLoginBean> loginBeans) {
		this.loginBeans = loginBeans;
	}
	
	public Integer getActiveIndex() {
		if (activeIndex == null) {
			activeIndex = 0;
		}
		return activeIndex;
	}
	
	public void setActiveIndex(Integer activeIndex) {
		this.activeIndex = activeIndex;
	}
	
}
