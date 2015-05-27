package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
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
import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.bean.IclubVehSecTypeBean;
import za.co.iclub.pss.web.bean.IclubVehUsageTypeBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.bean.IclubVehicleMasterBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.IclubVehSecTypeModel;
import za.co.iclub.pss.ws.model.IclubVehUsageTypeModel;
import za.co.iclub.pss.ws.model.IclubVehicleMasterModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubVehicleController")
@SessionScoped
public class IclubVehicleController implements Serializable {
	
	private static final long serialVersionUID = 1399848586779525616L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubVehicleController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String SM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehSecTypeService/";
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String VM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleMasterService/";
	private static final String VEHU_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehUsageTypeService/";
	private static final String GL_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubGeoLocService/";
	
	private List<IclubVehicleBean> beans;
	private List<IclubVehicleBean> dashBoardBeans;
	private List<IclubVehSecTypeBean> vehSecTypeBeans;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubVehUsageTypeBean> vehUsageTypeBeans;
	private boolean showAddPanel;
	private boolean showModPanel;
	private String vmMake;
	private List<String> vmMakes;
	private List<IclubVehicleMasterBean> vBeans;
	private List<String> years;
	private IclubVehicleBean bean;
	private boolean showCreateCont;
	private boolean showViewCont;
	private boolean showEditCont;
	private Long viewParam;
	private String sessionUserId;
	private String userName;
	private ResourceBundle labelBundle;
	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "36.890257,30.707417";
	
	public void initializePage() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		
		draggableModelVeh = new DefaultMapModel();
		
		if (viewParam == null || viewParam.longValue() == 1)
			showView();
		else if (viewParam != null && viewParam.longValue() == 2)
			showEdit();
		
	}
	
	@PostConstruct
	public void init() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: initializePage");
		
		draggableModelVeh = new DefaultMapModel();
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
		bean = new IclubVehicleBean();
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
		bean = new IclubVehicleBean();
	}
	
	public void showModPanel() {
		showAddPanel = false;
		showModPanel = true;
	}
	
	public MapModel getDraggableModelVeh() {
		return draggableModelVeh;
	}
	
	public String getCenterGeoMapVeh() {
		return centerGeoMapVeh;
	}
	
	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		bean.setVDdLat(geoBean.getGlLat());
		bean.setVDdLong(geoBean.getGlLong());
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng()));
	}
	
	public void onGeocodeVeh(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		draggableModelVeh = new DefaultMapModel();
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapVeh = center.getLat() + "," + center.getLng();
			
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				Marker marker = new Marker(result.getLatLng(), result.getAddress());
				marker.setDraggable(true);
				draggableModelVeh.addOverlay(marker);
			}
		}
	}
	
	public void onMarkerSelectVeh(OverlaySelectEvent event) {
		markerVeh = (Marker) event.getOverlay();
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		bean.setVDdLat(geoBean.getGlLat());
		bean.setVDdLong(geoBean.getGlLong());
		bean.setVDdArea(markerVeh.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVeh.getTitle()));
	}
	
	public IclubGeoLocBean getGeoLocBean(Double geoLong, Double geoLat) {
		WebClient client = IclubWebHelper.createCustomClient(GL_BASE_URL + "get/" + geoLat + "/" + geoLong);
		IclubGeoLocModel model = (IclubGeoLocModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubGeoLocModel.class));
		client.close();
		IclubGeoLocBean bean = new IclubGeoLocBean();
		if (model != null) {
			bean.setGlId(model.getGlId());
			bean.setGlProvince(model.getGlProvince());
			bean.setGlSuburb(model.getGlSuburb());
			bean.setGlAddress(model.getGlAddress());
			bean.setGlLat(model.getGlLat());
			bean.setGlLong(model.getGlLong());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setGlRate(model.getGlRate());
			bean.setGlCrtdDt(model.getGlCrtdDt());
		}
		return bean;
		
	}
	
	public List<IclubVehicleBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubVehicleModel> models = new ArrayList<IclubVehicleModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubVehicleBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehicleModel model : models) {
				IclubVehicleBean bean = new IclubVehicleBean();
				
				bean.setVId(model.getVId());
				bean.setVOwner(model.getVOwner());
				bean.setVGearLockYn(model.getVGearLockYn());
				bean.setVImmYn(model.getVImmYn());
				bean.setVConcessReason(model.getVConcessReason());
				bean.setVConcessPrct(model.getVConcessPrct());
				bean.setVInsuredValue(model.getVInsuredValue());
				bean.setVYear(model.getVYear());
				bean.setVDdLong(model.getVDdLong());
				bean.setVDdLat(model.getVDdLat());
				bean.setVDdArea(model.getVDdArea());
				bean.setVOnLong(model.getVOnLong());
				bean.setVOnLat(model.getVOnLat());
				bean.setVOnArea(model.getVOnArea());
				bean.setVCompYrs(model.getVCompYrs());
				bean.setVOdometer(model.getVOdometer());
				bean.setVRegNum(model.getVRegNum());
				bean.setVEngineNr(model.getVEngineNr());
				bean.setVVin(model.getVVin());
				bean.setVNoclaimYrs(model.getVNoclaimYrs());
				bean.setIclubVehicleMaster(model.getIclubVehicleMaster());
				bean.setIclubVehUsageType(model.getIclubVehUsageType());
				bean.setIclubVehSecType(model.getIclubVehSecType());
				bean.setIclubDriver(model.getIclubDriver());
				bean.setIclubCoverType(model.getIclubCoverType());
				bean.setIclubSecurityDevice(model.getIclubSecurityDevice());
				bean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
				bean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());
				bean.setVCrtdDt(model.getVCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());
				
				dashBoardBeans.add(bean);
			}
		}
		return dashBoardBeans;
	}
	
	public void vmModelValueChangeListener() {
		if (bean != null && bean.getIclubVehicleMaster() != null) {
			
			loadYears(bean.getIclubVehicleMaster().toString());
			
		} else {
			if (years != null) {
				years.clear();
			}
		}
	}
	
	public void loadYears(String vmId) {
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "get/" + vmId);
		IclubVehicleMasterModel model = (IclubVehicleMasterModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleMasterModel.class));
		client.close();
		years = new ArrayList<String>();
		if (model != null && model.getVmProdDt() != null) {
			Calendar now = Calendar.getInstance();
			int currentYear = now.get(Calendar.YEAR);
			now.setTimeInMillis(model.getVmProdDt().getTime());
			int prodYear = now.get(Calendar.YEAR);
			for (int i = prodYear; i <= currentYear; i++) {
				years.add(i + "");
			}
			
		}
	}
	
	public void vmMakeValueChangeListener() {
		if (vmMake != null) {
			
			loadVmModels(vmMake);
			
		} else {
			if (vBeans != null) {
				vBeans.clear();
			}
			if (years != null) {
				years.clear();
			}
		}
	}
	
	public void loadVmModels(String vmMake) {
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "getByMake/" + vmMake);
		Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
		client.close();
		vBeans = new ArrayList<IclubVehicleMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehicleMasterModel model : models) {
				IclubVehicleMasterBean bean = new IclubVehicleMasterBean();
				
				bean.setVmId(model.getVmId());
				
				bean.setVmMake(model.getVmMake());
				bean.setVmModel(model.getVmModel());
				bean.setVmMrktRate(model.getVmMrktRate());
				bean.setVmOrigRate(model.getVmOrigRate());
				bean.setVmRetRate(model.getVmRetRate());
				bean.setVmProdDt(model.getVmProdDt());
				bean.setVmCrtdDt(model.getVmCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setVmRatePrct(model.getVmRatePrct());
				
				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicles = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String vehicle : model.getIclubVehicles()) {
						vehicles[i] = vehicle;
						i++;
					}
					bean.setIclubVehicles(vehicles);
				}
				vBeans.add(bean);
			}
		}
	}
	
	public void setDashBoardBeans(List<IclubVehicleBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}
	
	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		showAddPanel = false;
		showModPanel = false;
		bean = new IclubVehicleBean();
	}
	
	public void addIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubVehicle");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubVehicleModel model = new IclubVehicleModel();
				
				bean.setVId(UUID.randomUUID().toString());
				bean.setVCrtdDt(new Date(System.currentTimeMillis()));
				bean.setIclubPerson(getSessionUserId());
				model.setVId(bean.getVId());
				model.setVOwner(bean.getVOwner());
				model.setVGearLockYn(bean.getVGearLockYn());
				model.setVImmYn(bean.getVImmYn());
				model.setVConcessReason(bean.getVConcessReason());
				model.setVConcessPrct(bean.getVConcessPrct());
				model.setVInsuredValue(bean.getVInsuredValue());
				model.setVYear(bean.getVYear());
				model.setVDdLong(bean.getVDdLong());
				model.setVDdLat(bean.getVDdLat());
				model.setVDdArea(bean.getVDdArea());
				model.setVOnLong(bean.getVOnLong());
				model.setVOnLat(bean.getVOnLat());
				model.setVOnArea(bean.getVOnArea());
				model.setVCompYrs(bean.getVCompYrs());
				model.setVOdometer(bean.getVOdometer());
				model.setVCrtdDt(bean.getVCrtdDt());
				model.setVRegNum(bean.getVRegNum());
				model.setVEngineNr(bean.getVEngineNr());
				model.setVVin(bean.getVVin());
				model.setVNoclaimYrs(bean.getVNoclaimYrs());
				model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
				model.setIclubVehUsageType(bean.getIclubVehUsageType());
				model.setIclubVehSecType(bean.getIclubVehSecType());
				model.setIclubPerson(bean.getIclubPerson());
				model.setIclubDriver(bean.getIclubDriver());
				model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
				model.setIclubCoverType(bean.getIclubCoverType());
				model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					beans.add(bean);
					clearForm();
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubVehicle");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubVehicleModel model = new IclubVehicleModel();
				
				model.setVId(bean.getVId());
				bean.setVCrtdDt(new Date(System.currentTimeMillis()));
				bean.setIclubPerson(getSessionUserId());
				model.setVOwner(bean.getVOwner());
				model.setVGearLockYn(bean.getVGearLockYn());
				model.setVImmYn(bean.getVImmYn());
				model.setVConcessReason(bean.getVConcessReason());
				model.setVConcessPrct(bean.getVConcessPrct());
				model.setVInsuredValue(bean.getVInsuredValue());
				model.setVYear(bean.getVYear());
				model.setVDdLong(bean.getVDdLong());
				model.setVDdLat(bean.getVDdLat());
				model.setVDdArea(bean.getVDdArea());
				model.setVOnLong(bean.getVOnLong());
				model.setVOnLat(bean.getVOnLat());
				model.setVOnArea(bean.getVOnArea());
				model.setVCompYrs(bean.getVCompYrs());
				model.setVOdometer(bean.getVOdometer());
				model.setVCrtdDt(bean.getVCrtdDt());
				model.setVRegNum(bean.getVRegNum());
				model.setVEngineNr(bean.getVEngineNr());
				model.setVVin(bean.getVVin());
				model.setVNoclaimYrs(bean.getVNoclaimYrs());
				model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
				model.setIclubVehUsageType(bean.getIclubVehUsageType());
				model.setIclubVehSecType(bean.getIclubVehSecType());
				model.setIclubPerson(bean.getIclubPerson());
				model.setIclubCoverType(bean.getIclubCoverType());
				model.setIclubDriver(bean.getIclubDriver());
				model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
				model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					viewParam = 1l;
					clearForm();
					showView();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubVehicle");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getVId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				viewParam = 1l;
				showView();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		if (bean.getIclubVehicleMaster() == null) {
			IclubWebHelper.addMessage(("Please Select Make and Model"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getVOdometer() == null) {
			IclubWebHelper.addMessage(("OdoMeter Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVOnArea() == null || bean.getVOnArea().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("On Area Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVOnLat() == null || bean.getVOnLong() == null) {
			IclubWebHelper.addMessage(("Please Select Location"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubAccessTypeByVOnAccessTypeId() == null) {
			IclubWebHelper.addMessage(("Please Select On AccessType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubAccessTypeByVDdAccessTypeId() == null) {
			IclubWebHelper.addMessage(("Please Select Dd AccessType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVYear() == null) {
			IclubWebHelper.addMessage(("Year Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		/*
		 * if (bean.getIclubDriver() == null) {
		 * IclubWebHelper.addMessage(("Please Select Driver"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 */
		if (bean.getVImmYn() == null || bean.getVImmYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Imn Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVGearLockYn() == null || bean.getVGearLockYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gear Lock Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubVehSecType() == null) {
			IclubWebHelper.addMessage(("Please Select Security Master"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubVehUsageType() == null) {
			IclubWebHelper.addMessage(("Please Select VehUsage Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVNoclaimYrs() == null) {
			IclubWebHelper.addMessage(("No Claim Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getVCompYrs() == null) {
			IclubWebHelper.addMessage(("Comp Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVVin() == null || bean.getVVin().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Vin Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVEngineNr() == null || bean.getVEngineNr().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("EngineNr Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getVRegNum() == null || bean.getVRegNum().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("RegNum Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public IclubVehicleBean getBean() {
		if (bean == null)
			bean = new IclubVehicleBean();
		return bean;
	}
	
	public void setBean(IclubVehicleBean bean) {
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
	
	public List<IclubVehicleBean> getBeans() {
		
		if (beans == null) {
			beans = new ArrayList<IclubVehicleBean>();
		}
		
		return beans;
	}
	
	public void setBeans(List<IclubVehicleBean> beans) {
		this.beans = beans;
	}
	
	public List<IclubVehSecTypeBean> getVehSecTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "list");
		Collection<? extends IclubVehSecTypeModel> models = new ArrayList<IclubVehSecTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehSecTypeModel.class));
		client.close();
		vehSecTypeBeans = new ArrayList<IclubVehSecTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehSecTypeModel model : models) {
				
				IclubVehSecTypeBean bean = new IclubVehSecTypeBean();
				
				bean.setVstId(model.getVstId());
				bean.setVstLongDesc(model.getVstLongDesc());
				bean.setVstShortDesc(model.getVstShortDesc());
				bean.setVstStatus(model.getVstStatus());
				
				vehSecTypeBeans.add(bean);
			}
		}
		return vehSecTypeBeans;
	}
	
	public void setVehSecTypeBeans(List<IclubVehSecTypeBean> vehSecTypeBeans) {
		this.vehSecTypeBeans = vehSecTypeBeans;
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
	
	public List<String> getVmMakes() {
		
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "/listAllMake");
		Collection<? extends String> models = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));
		client.close();
		
		vmMakes = new ArrayList<>(models);
		
		return vmMakes;
	}
	
	public void setVmMakes(List<String> vmMakes) {
		this.vmMakes = vmMakes;
	}
	
	public String getVmMake() {
		return vmMake;
	}
	
	public void setVmMake(String vmMake) {
		this.vmMake = vmMake;
	}
	
	public List<IclubVehicleMasterBean> getvBeans() {
		return vBeans;
	}
	
	public void setvBeans(List<IclubVehicleMasterBean> vBeans) {
		this.vBeans = vBeans;
	}
	
	public List<String> getYears() {
		return years;
	}
	
	public void setYears(List<String> years) {
		this.years = years;
	}
	
	public List<IclubVehUsageTypeBean> getVehUsageTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(VEHU_BASE_URL + "/list");
		Collection<? extends IclubVehUsageTypeModel> models = new ArrayList<IclubVehUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehUsageTypeModel.class));
		client.close();
		vehUsageTypeBeans = new ArrayList<IclubVehUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehUsageTypeModel model : models) {
				IclubVehUsageTypeBean bean = new IclubVehUsageTypeBean();
				
				bean.setVutId(model.getVutId());
				bean.setVutLongDesc(model.getVutLongDesc());
				bean.setVutShortDesc(model.getVutShortDesc());
				bean.setVutStatus(model.getVutStatus());
				
				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicles = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String iclubVehicle : model.getIclubVehicles()) {
						vehicles[i] = iclubVehicle;
						i++;
					}
					bean.setIclubVehicles(vehicles);
				}
				vehUsageTypeBeans.add(bean);
			}
		}
		return vehUsageTypeBeans;
	}
	
	public void setVehUsageTypeBeans(List<IclubVehUsageTypeBean> vehUsageTypeBeans) {
		this.vehUsageTypeBeans = vehUsageTypeBeans;
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
