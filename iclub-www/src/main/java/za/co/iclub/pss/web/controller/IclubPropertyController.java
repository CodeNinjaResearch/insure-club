package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.PostConstruct;
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

import za.co.iclub.pss.web.bean.IclubAccessTypeBean;
import za.co.iclub.pss.web.bean.IclubBarTypeBean;
import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.bean.IclubOccupiedStatusBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPropertyTypeBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubRoofTypeBean;
import za.co.iclub.pss.web.bean.IclubThatchTypeBean;
import za.co.iclub.pss.web.bean.IclubWallTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.IclubBarTypeModel;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubPropertyTypeModel;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
import za.co.iclub.pss.ws.model.IclubThatchTypeModel;
import za.co.iclub.pss.ws.model.IclubWallTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubPropertyController")
@SessionScoped
public class IclubPropertyController implements Serializable {
	
	private static final long serialVersionUID = 1399848586779525616L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubPropertyController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String PUR_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPurposeTypeService/";
	private static final String BT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBarTypeService/";
	private static final String GL_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubGeoLocService/";
	private static final String TT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubThatchTypeService/";
	private static final String ROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRoofTypeService/";
	private static final String PROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyTypeService/";
	private static final String WT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubWallTypeService/";
	private static final String OCCS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupiedStatusService/";
	private static final String CT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";
	
	private List<IclubPropertyBean> beans;
	private List<IclubPropertyBean> dashBoardBeans;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubPurposeTypeBean> pPurposeTypeBeans;
	private List<IclubBarTypeBean> barTypeBeans;
	private List<IclubThatchTypeBean> thatchTypeBeans;
	private List<IclubRoofTypeBean> roofTypeBeans;
	private List<IclubPropertyTypeBean> propertyTypeBeans;
	private List<IclubWallTypeBean> wallTypeBeans;
	private List<IclubOccupiedStatusBean> occupiedStatusBeans;
	private List<IclubCoverTypeBean> coverTypeBeans;
	private IclubPropertyBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private boolean showAddPanel;
	private boolean showModPanel;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "36.890257,30.707417";
	
	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		
		draggableModelPro = new DefaultMapModel();
		
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		
	}
	
	@PostConstruct
	public void init() {
		draggableModelPro = new DefaultMapModel();
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
		bean = new IclubPropertyBean();
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
	
	public void showAddPanel() {
		showAddPanel = true;
		showModPanel = false;
		bean = new IclubPropertyBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public MapModel getDraggableModelPro() {
		return draggableModelPro;
	}
	
	public String getCenterGeoMapPro() {
		return centerGeoMapPro;
	}
	
	public void onMarkerDragPro(MarkerDragEvent event) {
		markerPro = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerPro.getLatlng().getLat(), markerPro.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			bean.setPLat(geoBean.getGlLat());
			bean.setPLong(geoBean.getGlLong());
		} else {
			bean.setPLat(markerPro.getLatlng().getLat());
			bean.setPLong(markerPro.getLatlng().getLng());
		}
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerPro.getLatlng().getLat() + ", Lng:" + markerPro.getLatlng().getLng()));
	}
	
	public void onGeocodePro(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		draggableModelPro = new DefaultMapModel();
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapPro = center.getLat() + "," + center.getLng();
			
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				Marker marker = new Marker(result.getLatLng(), result.getAddress());
				marker.setDraggable(true);
				draggableModelPro.addOverlay(marker);
			}
		}
	}
	
	public void onMarkerSelectPro(OverlaySelectEvent event) {
		markerPro = (Marker) event.getOverlay();
		IclubGeoLocBean geoBean = getGeoLocBean(markerPro.getLatlng().getLat(), markerPro.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			bean.setPLat(geoBean.getGlLat());
			bean.setPLong(geoBean.getGlLong());
		} else {
			bean.setPLat(markerPro.getLatlng().getLat());
			bean.setPLong(markerPro.getLatlng().getLng());
		}
		bean.setPAddress(markerPro.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}
	
	public IclubGeoLocBean getGeoLocBean(Double geoLong, Double geoLat) {
		WebClient client = IclubWebHelper.createCustomClient(GL_BASE_URL + "get/" + geoLat + "/" + geoLong);
		IclubGeoLocModel model = (IclubGeoLocModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubGeoLocModel.class));
		client.close();
		IclubGeoLocBean bean = new IclubGeoLocBean();
		if (model != null) {
			bean.setGlProvince(model.getGlProvince());
			bean.setGlSuburb(model.getGlSuburb());
			bean.setGlId(model.getGlId());
			bean.setGlAddress(model.getGlAddress());
			bean.setGlLat(model.getGlLat());
			bean.setGlLong(model.getGlLong());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setGlRate(model.getGlRate());
			bean.setGlCrtdDt(model.getGlCrtdDt());
		}
		return bean;
		
	}
	
	public List<IclubPropertyBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubPropertyModel> models = new ArrayList<IclubPropertyModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubPropertyBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropertyModel model : models) {
				IclubPropertyBean bean = new IclubPropertyBean();
				
				bean.setPId(model.getPId());
				bean.setPCrtdDt(model.getPCrtdDt());
				bean.setPEstValue(model.getPEstValue());
				bean.setPSecGatesYn(model.getPSecGatesYn());
				bean.setPNorobberyYn(model.getPNorobberyYn());
				bean.setPCompYn(model.getPCompYn());
				bean.setPRentFurYn(model.getPRentFurYn());
				bean.setPNoclaimYrs(model.getPNoclaimYrs());
				bean.setPPostalCd(model.getPPostalCd());
				bean.setPLong(model.getPLong());
				bean.setPLat(model.getPLat());
				bean.setPAddress(model.getPAddress());
				bean.setPRegNum(model.getPRegNum());
				bean.setIclubCoverType(model.getIclubCoverType());
				bean.setIclubPurposeType(model.getIclubPurposeType());
				bean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
				bean.setIclubPropertyType(model.getIclubPropertyType());
				bean.setIclubWallType(model.getIclubWallType());
				bean.setIclubAccessType(model.getIclubAccessType());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setIclubBarType(model.getIclubBarType());
				bean.setIclubThatchType(model.getIclubThatchType());
				bean.setIclubRoofType(model.getIclubRoofType());
				
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void setDashBoardBeans(List<IclubPropertyBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubPropertyBean();
	}
	
	public void addIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubProperty");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubPropertyModel model = new IclubPropertyModel();
				
				bean.setPId(UUID.randomUUID().toString());
				model.setPId(bean.getPId());
				model.setPCrtdDt(new Date(System.currentTimeMillis()));
				model.setPEstValue(bean.getPEstValue());
				model.setPSecGatesYn(bean.getPSecGatesYn());
				model.setPNorobberyYn(bean.getPNorobberyYn());
				model.setPCompYn(bean.getPCompYn());
				model.setPRentFurYn(bean.getPRentFurYn());
				model.setPNoclaimYrs(bean.getPNoclaimYrs());
				model.setPPostalCd(bean.getPPostalCd());
				model.setPLong(bean.getPLong());
				model.setPLat(bean.getPLat());
				model.setPAddress(bean.getPAddress());
				model.setPRegNum(bean.getPRegNum());
				model.setIclubCoverType(bean.getIclubCoverType());
				model.setIclubPurposeType(bean.getIclubPurposeType());
				model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus());
				model.setIclubPropertyType(bean.getIclubPropertyType());
				model.setIclubWallType(bean.getIclubWallType());
				model.setIclubAccessType(bean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(bean.getIclubBarType());
				model.setIclubThatchType(bean.getIclubThatchType());
				model.setIclubRoofType(bean.getIclubRoofType());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					beans.add(bean);
					clearForm();
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubProperty");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubPropertyModel model = new IclubPropertyModel();
				
				model.setPId(bean.getPId());
				model.setPCrtdDt(new Date(System.currentTimeMillis()));
				model.setPEstValue(bean.getPEstValue());
				model.setPSecGatesYn(bean.getPSecGatesYn());
				model.setPNorobberyYn(bean.getPNorobberyYn());
				model.setPCompYn(bean.getPCompYn());
				model.setPRentFurYn(bean.getPRentFurYn());
				model.setPNoclaimYrs(bean.getPNoclaimYrs());
				model.setPPostalCd(bean.getPPostalCd());
				model.setPLong(bean.getPLong());
				model.setPLat(bean.getPLat());
				model.setPAddress(bean.getPAddress());
				model.setPRegNum(bean.getPRegNum());
				model.setIclubCoverType(bean.getIclubCoverType());
				model.setIclubPurposeType(bean.getIclubPurposeType());
				model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus());
				model.setIclubPropertyType(bean.getIclubPropertyType());
				model.setIclubWallType(bean.getIclubWallType());
				model.setIclubAccessType(bean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(bean.getIclubBarType());
				model.setIclubThatchType(bean.getIclubThatchType());
				model.setIclubRoofType(bean.getIclubRoofType());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					clearForm();
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubProperty");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getPId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getPRegNum() == null || bean.getPRegNum().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Premium Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPAddress() == null || bean.getPAddress().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Address Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPLat() == null || bean.getPLong() == null) {
			IclubWebHelper.addMessage("Please Select Location", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPPostalCd() == null) {
			IclubWebHelper.addMessage(("Postel Code Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubCoverType() == null) {
			IclubWebHelper.addMessage(("Please Select Cover Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubPurposeType() == null) {
			IclubWebHelper.addMessage(("Please Select Purpose Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPNoclaimYrs() == null) {
			IclubWebHelper.addMessage(("Noclaim Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getIclubWallType() == null) {
			IclubWebHelper.addMessage(("Please Select WallType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubRoofType() == null) {
			IclubWebHelper.addMessage(("Please Select Roof Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubThatchType() == null) {
			IclubWebHelper.addMessage(("Please Select Thatch Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubBarType() == null) {
			IclubWebHelper.addMessage(("Please Select Bar Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubAccessType() == null) {
			IclubWebHelper.addMessage(("Please Select Access Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPRentFurYn() == null || bean.getPRentFurYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("RentFur Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPCompYn() == null || bean.getPCompYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Comp Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPSecGatesYn() == null || bean.getPSecGatesYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Sec Gates Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPEstValue() == null) {
			IclubWebHelper.addMessage(("Est value Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPNorobberyYn() == null || bean.getPNorobberyYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("No Robbery Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret || true;
	}
	
	public IclubPropertyBean getBean() {
		if (bean == null)
			bean = new IclubPropertyBean();
		return bean;
	}
	
	public void setBean(IclubPropertyBean bean) {
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
	
	public List<IclubPropertyBean> getBeans() {
		
		if (beans == null) {
			beans = new ArrayList<IclubPropertyBean>();
		}
		
		return beans;
	}
	
	public void setBeans(List<IclubPropertyBean> beans) {
		this.beans = beans;
	}
	
	public List<IclubAccessTypeBean> getAccessTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(AEST_BASE_URL + "list");
		Collection<? extends IclubAccessTypeModel> models = new ArrayList<IclubAccessTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccessTypeModel.class));
		client.close();
		accessTypeBeans = new ArrayList<IclubAccessTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccessTypeModel model : models) {
				IclubAccessTypeBean bean = new IclubAccessTypeBean();
				bean.setAtId(model.getAtId());
				bean.setAtLongDesc(model.getAtLongDesc());
				bean.setAtShortDesc(model.getAtShortDesc());
				bean.setAtStatus(model.getAtStatus());
				accessTypeBeans.add(bean);
			}
		}
		return accessTypeBeans;
	}
	
	public void setAccessTypeBeans(List<IclubAccessTypeBean> accessTypeBeans) {
		this.accessTypeBeans = accessTypeBeans;
	}
	
	public List<IclubBarTypeBean> getBarTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(BT_BASE_URL + "list");
		Collection<? extends IclubBarTypeModel> models = new ArrayList<IclubBarTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBarTypeModel.class));
		client.close();
		barTypeBeans = new ArrayList<IclubBarTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubBarTypeModel model : models) {
				IclubBarTypeBean bean = new IclubBarTypeBean();
				bean.setBtId(model.getBtId());
				bean.setBtLongDesc(model.getBtLongDesc());
				bean.setBtShortDesc(model.getBtShortDesc());
				bean.setBtStatus(model.getBtStatus());
				barTypeBeans.add(bean);
			}
		}
		return barTypeBeans;
	}
	
	public void setBarTypeBeans(List<IclubBarTypeBean> barTypeBeans) {
		this.barTypeBeans = barTypeBeans;
	}
	
	public List<IclubThatchTypeBean> getThatchTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(TT_BASE_URL + "list");
		Collection<? extends IclubThatchTypeModel> models = new ArrayList<IclubThatchTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubThatchTypeModel.class));
		client.close();
		thatchTypeBeans = new ArrayList<IclubThatchTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubThatchTypeModel model : models) {
				IclubThatchTypeBean bean = new IclubThatchTypeBean();
				bean.setTtId(model.getTtId());
				bean.setTtLongDesc(model.getTtLongDesc());
				bean.setTtShortDesc(model.getTtShortDesc());
				bean.setTtStatus(model.getTtStatus());
				thatchTypeBeans.add(bean);
			}
		}
		return thatchTypeBeans;
	}
	
	public void setThatchTypeBeans(List<IclubThatchTypeBean> thatchTypeBeans) {
		this.thatchTypeBeans = thatchTypeBeans;
	}
	
	public List<IclubRoofTypeBean> getRoofTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(ROT_BASE_URL + "list");
		Collection<? extends IclubRoofTypeModel> models = new ArrayList<IclubRoofTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRoofTypeModel.class));
		client.close();
		roofTypeBeans = new ArrayList<IclubRoofTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRoofTypeModel model : models) {
				IclubRoofTypeBean bean = new IclubRoofTypeBean();
				bean.setRtId(model.getRtId());
				bean.setRtLongDesc(model.getRtLongDesc());
				bean.setRtShortDesc(model.getRtShortDesc());
				bean.setRtStatus(model.getRtStatus());
				roofTypeBeans.add(bean);
			}
		}
		return roofTypeBeans;
	}
	
	public void setRoofTypeBeans(List<IclubRoofTypeBean> roofTypeBeans) {
		this.roofTypeBeans = roofTypeBeans;
	}
	
	public List<IclubWallTypeBean> getWallTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(WT_BASE_URL + "list");
		Collection<? extends IclubWallTypeModel> models = new ArrayList<IclubWallTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubWallTypeModel.class));
		client.close();
		wallTypeBeans = new ArrayList<IclubWallTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubWallTypeModel model : models) {
				IclubWallTypeBean bean = new IclubWallTypeBean();
				bean.setWtId(model.getWtId());
				bean.setWtLongDesc(model.getWtLongDesc());
				bean.setWtShortDesc(model.getWtShortDesc());
				bean.setWtStatus(model.getWtStatus());
				wallTypeBeans.add(bean);
			}
		}
		return wallTypeBeans;
	}
	
	public void setWallTypeBeans(List<IclubWallTypeBean> wallTypeBeans) {
		this.wallTypeBeans = wallTypeBeans;
	}
	
	public List<IclubPropertyTypeBean> getPropertyTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PROT_BASE_URL + "list");
		Collection<? extends IclubPropertyTypeModel> models = new ArrayList<IclubPropertyTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyTypeModel.class));
		client.close();
		propertyTypeBeans = new ArrayList<IclubPropertyTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropertyTypeModel model : models) {
				IclubPropertyTypeBean bean = new IclubPropertyTypeBean();
				bean.setPtId(model.getPtId());
				bean.setPtLongDesc(model.getPtLongDesc());
				bean.setPtShortDesc(model.getPtShortDesc());
				bean.setPtStatus(model.getPtStatus());
				
				propertyTypeBeans.add(bean);
			}
		}
		return propertyTypeBeans;
	}
	
	public void setPropertyTypeBeans(List<IclubPropertyTypeBean> propertyTypeBeans) {
		this.propertyTypeBeans = propertyTypeBeans;
	}
	
	public List<IclubOccupiedStatusBean> getOccupiedStatusBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(OCCS_BASE_URL + "list");
		Collection<? extends IclubOccupiedStatusModel> models = new ArrayList<IclubOccupiedStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupiedStatusModel.class));
		client.close();
		occupiedStatusBeans = new ArrayList<IclubOccupiedStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubOccupiedStatusModel model : models) {
				IclubOccupiedStatusBean bean = new IclubOccupiedStatusBean();
				bean.setOsId(model.getOsId());
				bean.setOsLongDesc(model.getOsLongDesc());
				bean.setOsShortDesc(model.getOsShortDesc());
				bean.setOsStatus(model.getOsStatus());
				occupiedStatusBeans.add(bean);
			}
		}
		return occupiedStatusBeans;
	}
	
	public void setOccupiedStatusBeans(List<IclubOccupiedStatusBean> occupiedStatusBeans) {
		this.occupiedStatusBeans = occupiedStatusBeans;
	}
	
	public List<IclubPurposeTypeBean> getpPurposeTypeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/list/status/" + "2");
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		pPurposeTypeBeans = new ArrayList<IclubPurposeTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPurposeTypeModel model : models) {
				IclubPurposeTypeBean bean = new IclubPurposeTypeBean();
				
				bean.setPtId(model.getPtId());
				bean.setPtLongDesc(model.getPtLongDesc());
				bean.setPtShortDesc(model.getPtShortDesc());
				bean.setPtStatus(model.getPtStatus());
				bean.setPtCrtdDt(model.getPtCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				
				if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
					String[] properties = new String[model.getIclubProperties().length];
					int i = 0;
					for (String iclubProperty : model.getIclubProperties()) {
						properties[i] = iclubProperty;
						i++;
					}
					bean.setIclubProperties(properties);
				}
				
				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicles = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String iclubVehicle : model.getIclubVehicles()) {
						vehicles[i] = iclubVehicle;
						i++;
					}
					bean.setIclubVehicles(vehicles);
				}
				pPurposeTypeBeans.add(bean);
			}
		}
		return pPurposeTypeBeans;
	}
	
	public void setpPurposeTypeBeans(List<IclubPurposeTypeBean> pPurposeTypeBeans) {
		this.pPurposeTypeBeans = pPurposeTypeBeans;
	}
	
	public List<IclubCoverTypeBean> getCoverTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CT_BASE_URL + "list");
		Collection<? extends IclubCoverTypeModel> models = new ArrayList<IclubCoverTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCoverTypeModel.class));
		client.close();
		coverTypeBeans = new ArrayList<IclubCoverTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubCoverTypeModel model : models) {
				
				IclubCoverTypeBean bean = new IclubCoverTypeBean();
				
				bean.setCtId(model.getCtId());
				bean.setCtLongDesc(model.getCtLongDesc());
				bean.setCtShortDesc(model.getCtShortDesc());
				bean.setCtStatus(model.getCtStatus());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setCtCrtdDt(model.getCtCrtdDt());
				if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
					String[] iclubProperties = new String[model.getIclubProperties().length];
					int i = 0;
					for (String iclubProperty : model.getIclubProperties()) {
						iclubProperties[i] = iclubProperty;
						i++;
					}
					bean.setIclubProperties(iclubProperties);
				}
				if (model.getIclubQuotes() != null && model.getIclubQuotes().length > 0) {
					String[] iclubQuotes = new String[model.getIclubQuotes().length];
					int i = 0;
					for (String iclubQuote : model.getIclubQuotes()) {
						iclubQuotes[i] = iclubQuote;
						i++;
					}
					bean.setIclubQuotes(iclubQuotes);
				}
				coverTypeBeans.add(bean);
			}
		}
		return coverTypeBeans;
	}
	
	public void setCoverTypeBeans(List<IclubCoverTypeBean> coverTypeBeans) {
		this.coverTypeBeans = coverTypeBeans;
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
