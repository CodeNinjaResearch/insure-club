package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
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

import za.co.iclub.pss.model.ui.IclubAccessTypeBean;
import za.co.iclub.pss.model.ui.IclubBarTypeBean;
import za.co.iclub.pss.model.ui.IclubCountryCodeBean;
import za.co.iclub.pss.model.ui.IclubCoverTypeBean;
import za.co.iclub.pss.model.ui.IclubDriverBean;
import za.co.iclub.pss.model.ui.IclubEntityTypeBean;
import za.co.iclub.pss.model.ui.IclubFieldBean;
import za.co.iclub.pss.model.ui.IclubGeoLocBean;
import za.co.iclub.pss.model.ui.IclubIdTypeBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemBean;
import za.co.iclub.pss.model.ui.IclubInsurerMasterBean;
import za.co.iclub.pss.model.ui.IclubLicenseCodeBean;
import za.co.iclub.pss.model.ui.IclubLoginBean;
import za.co.iclub.pss.model.ui.IclubMaritialStatusBean;
import za.co.iclub.pss.model.ui.IclubOccupationBean;
import za.co.iclub.pss.model.ui.IclubOccupiedStatusBean;
import za.co.iclub.pss.model.ui.IclubPersonBean;
import za.co.iclub.pss.model.ui.IclubPropUsageTypeBean;
import za.co.iclub.pss.model.ui.IclubPropertyBean;
import za.co.iclub.pss.model.ui.IclubPropertyItemBean;
import za.co.iclub.pss.model.ui.IclubPropertyTypeBean;
import za.co.iclub.pss.model.ui.IclubQuoteBean;
import za.co.iclub.pss.model.ui.IclubRateEngineBean;
import za.co.iclub.pss.model.ui.IclubRateTypeBean;
import za.co.iclub.pss.model.ui.IclubRoofTypeBean;
import za.co.iclub.pss.model.ui.IclubSecurityQuestionBean;
import za.co.iclub.pss.model.ui.IclubVehSecTypeBean;
import za.co.iclub.pss.model.ui.IclubVehUsageTypeBean;
import za.co.iclub.pss.model.ui.IclubVehicleBean;
import za.co.iclub.pss.model.ui.IclubVehicleMasterBean;
import za.co.iclub.pss.model.ui.IclubWallTypeBean;
import za.co.iclub.pss.model.ws.IclubAccessTypeModel;
import za.co.iclub.pss.model.ws.IclubBarTypeModel;
import za.co.iclub.pss.model.ws.IclubConfigModel;
import za.co.iclub.pss.model.ws.IclubCountryCodeModel;
import za.co.iclub.pss.model.ws.IclubCoverTypeModel;
import za.co.iclub.pss.model.ws.IclubDriverModel;
import za.co.iclub.pss.model.ws.IclubEntityTypeModel;
import za.co.iclub.pss.model.ws.IclubFieldModel;
import za.co.iclub.pss.model.ws.IclubIdTypeModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemModel;
import za.co.iclub.pss.model.ws.IclubInsurerMasterModel;
import za.co.iclub.pss.model.ws.IclubLicenseCodeModel;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.model.ws.IclubMaritialStatusModel;
import za.co.iclub.pss.model.ws.IclubOccupationModel;
import za.co.iclub.pss.model.ws.IclubOccupiedStatusModel;
import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.model.ws.IclubPropUsageTypeModel;
import za.co.iclub.pss.model.ws.IclubPropertyItemModel;
import za.co.iclub.pss.model.ws.IclubPropertyModel;
import za.co.iclub.pss.model.ws.IclubPropertyTypeModel;
import za.co.iclub.pss.model.ws.IclubQuickQuoteRequest;
import za.co.iclub.pss.model.ws.IclubQuickQuoteResponse;
import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.model.ws.IclubRateEngineModel;
import za.co.iclub.pss.model.ws.IclubRateTypeModel;
import za.co.iclub.pss.model.ws.IclubRoofTypeModel;
import za.co.iclub.pss.model.ws.IclubSecurityQuestionModel;
import za.co.iclub.pss.model.ws.IclubVehSecTypeModel;
import za.co.iclub.pss.model.ws.IclubVehUsageTypeModel;
import za.co.iclub.pss.model.ws.IclubVehicleMasterModel;
import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.model.ws.IclubWallTypeModel;
import za.co.iclub.pss.trans.IclubAccessTypeTrans;
import za.co.iclub.pss.trans.IclubBarTypeTrans;
import za.co.iclub.pss.trans.IclubCountryCodeTrans;
import za.co.iclub.pss.trans.IclubCoverTypeTrans;
import za.co.iclub.pss.trans.IclubDriverTrans;
import za.co.iclub.pss.trans.IclubEntityTypeTrans;
import za.co.iclub.pss.trans.IclubFieldTrans;
import za.co.iclub.pss.trans.IclubIdTypeTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTrans;
import za.co.iclub.pss.trans.IclubInsurerMasterTrans;
import za.co.iclub.pss.trans.IclubLicenseCodeTrans;
import za.co.iclub.pss.trans.IclubLoginTrans;
import za.co.iclub.pss.trans.IclubMaritialStatusTrans;
import za.co.iclub.pss.trans.IclubOccupationTrans;
import za.co.iclub.pss.trans.IclubOccupiedStatusTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.trans.IclubPropUsageTypeTrans;
import za.co.iclub.pss.trans.IclubPropertyItemTrans;
import za.co.iclub.pss.trans.IclubPropertyTrans;
import za.co.iclub.pss.trans.IclubPropertyTypeTrans;
import za.co.iclub.pss.trans.IclubQuoteTrans;
import za.co.iclub.pss.trans.IclubRateEngineTrans;
import za.co.iclub.pss.trans.IclubRateTypeTrans;
import za.co.iclub.pss.trans.IclubRoofTypeTrans;
import za.co.iclub.pss.trans.IclubSecurityQuestionTrans;
import za.co.iclub.pss.trans.IclubVehSecTypeTrans;
import za.co.iclub.pss.trans.IclubVehUsageTypeTrans;
import za.co.iclub.pss.trans.IclubVehicleMasterTrans;
import za.co.iclub.pss.trans.IclubVehicleTrans;
import za.co.iclub.pss.trans.IclubWallTypeTrans;
import za.co.iclub.pss.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubQuickQuoteController")
@SessionScoped
public class IclubQuickQuoteController implements Serializable {
	
	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubQuickQuoteController.class);
	private static final String PUR_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropUsageTypeService/";
	private static final String VEHU_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehUsageTypeService/";
	private static final String PER_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPersonService/";
	private static final String VM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleMasterService/";
	private static final String V_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleService/";
	private static final String D_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubDriverService/";
	private static final String LIC_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLicenseCodeService/";
	private static final String WT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubWallTypeService/";
	private static final String RT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubRoofTypeService/";
	private static final String MS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubIdTypeService/";
	private static final String QUT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubQuoteService/";
	private static final String QQUT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubQuickQuoteService/";
	private static final String PRO_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyService/";
	private static final String PRO_ITM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyItemService/";
	private static final String II_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemService/";
	private static final String RAT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubRateTypeService/";
	private static final String RE_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubRateEngineService/";
	private static final String FD_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubFieldService/";
	private static final String CONF_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubConfigService/";
	private static final String OCN_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubOccupationService/";
	private static final String CCDE_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCountryCodeService/";
	private static final String LOG_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubLoginService/";
	private static final String SECQ_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSecurityQuestionService/";
	private static final String ET_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubEntityTypeService/";
	private static final String SM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehSecTypeService/";
	private static final String AEST_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubAccessTypeService/";
	private static final String BT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubBarTypeService/";
	private static final String PROT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyTypeService/";
	private static final String OCCS_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubOccupiedStatusService/";
	private static final String CT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubCoverTypeService/";
	private static final String IM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsurerMasterService/";
	
	private List<String> vmMakes;
	private IclubVehicleMasterBean vehicleMasterBean;
	private IclubPersonBean personBean;
	private IclubPropertyBean propertyBean;
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	private List<IclubIdTypeBean> idTypeBeans;
	private List<IclubVehicleMasterBean> vBeans;
	private List<IclubVehUsageTypeBean> pBeans;
	private List<IclubLicenseCodeBean> licenseCodeBeans;
	private List<IclubPropUsageTypeBean> pPropUsageTypeBeans;
	private List<IclubBarTypeBean> barTypeBeans;
	private List<IclubRoofTypeBean> roofTypeBeans;
	private List<IclubPropertyTypeBean> propertyTypeBeans;
	private List<IclubWallTypeBean> wallTypeBeans;
	private List<IclubOccupiedStatusBean> occupiedStatusBeans;
	private List<IclubCoverTypeBean> coverTypeBeans;
	private List<IclubPropertyBean> propertyBeans;
	private IclubDriverBean driverBean;
	private List<String> years;
	private String sessionUserId;
	private IclubVehicleBean vehicleBean;
	private String vmMake;
	private String claimYN;
	private ResourceBundle labelBundle;
	private boolean termsAndConditionFlag;
	private Double genPremium = 0.0d;
	private String quoteId;
	private String vehAddress;
	private String proAddress;
	
	private MapModel draggableModelPer;
	private Marker markerPer;
	private String centerGeoMapPer = "-28.4792905,24.6722915";
	
	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "-28.4792905,24.6722915";
	
	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "-28.4792905,24.6722915";
	
	private MapModel draggableModelVehDd;
	private Marker markerVehDd;
	private String centerGeoMapVehDd = "-28.4792905,24.6722915";
	
	private IclubPersonBean bean;
	
	private List<IclubOccupationBean> occupationBeans;
	
	private List<IclubCountryCodeBean> countryCodeBeans;
	
	private List<IclubSecurityQuestionBean> securityQuestionBeans;
	
	private IclubLoginBean loginBean;
	
	private boolean profileTabFlag;
	
	private boolean updateLogin;
	private boolean showVehAddPanel;
	private boolean showVehModPanel;
	private boolean showProAddPanel;
	private boolean showProModPanel;
	private boolean showProItemAddPanel;
	private List<IclubVehicleBean> vehicleBeans;
	private List<IclubVehSecTypeBean> vehSecTypeBeans;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubVehUsageTypeBean> vehUsageTypeBeans;
	private List<IclubPropertyItemBean> propertyItemBeans;
	private IclubPropertyItemBean propertyItemBean;
	private Map<String, List<IclubPropertyItemBean>> propertyItemBeansMap;
	private IclubQuoteBean quoteBean;
	private List<IclubVehicleMasterBean> vehicleMasters;
	private List<IclubInsurerMasterBean> insurerMasterBeans;
	
	@PostConstruct
	public void init() {
		draggableModelPer = new DefaultMapModel();
		draggableModelPro = new DefaultMapModel();
		draggableModelVeh = new DefaultMapModel();
		draggableModelVehDd = new DefaultMapModel();
		
	}
	
	public void showVehAddPanel() {
		showVehAddPanel = true;
		showVehModPanel = false;
		vehicleBean = new IclubVehicleBean();
		draggableModelVehDd = new DefaultMapModel();
		draggableModelVeh = new DefaultMapModel();
	}
	
	public void clearVehForm() {
		showVehAddPanel = false;
		showVehModPanel = false;
		vehAddress = "";
		draggableModelVeh = new DefaultMapModel();
		draggableModelVehDd = new DefaultMapModel();
		vehicleBean = new IclubVehicleBean();
	}
	
	public void showVehModPanel() {
		showVehAddPanel = false;
		showVehModPanel = true;
		draggableModelVehDd = new DefaultMapModel();
		draggableModelVeh = new DefaultMapModel();
		if (vehicleBean != null && vehicleBean.getVDdLat() != null && vehicleBean.getVDdLong() != null) {
			centerGeoMapVehDd = vehicleBean.getVDdLat() + "," + vehicleBean.getVDdLong();
			LatLng coord = new LatLng(vehicleBean.getVDdLat(), vehicleBean.getVDdLong());
			Marker marker = new Marker(coord, "");
			marker.setDraggable(true);
			draggableModelVehDd.addOverlay(marker);
			
		}
		if (vehicleBean != null && vehicleBean.getVOnLat() != null && vehicleBean.getVOnLong() != null) {
			centerGeoMapVeh = vehicleBean.getVOnLat() + "," + vehicleBean.getVOnLong();
			LatLng coord = new LatLng(vehicleBean.getVOnLat(), vehicleBean.getVOnLong());
			Marker marker = new Marker(coord, "");
			marker.setDraggable(true);
			draggableModelVeh.addOverlay(marker);
			
		}
	}
	
	public void showProAddPanel() {
		showProAddPanel = true;
		showProModPanel = false;
		draggableModelPro = new DefaultMapModel();
		propertyBean = new IclubPropertyBean();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
	}
	
	public void showProItemAddPanel() {
		showProItemAddPanel = true;
		propertyItemBean = new IclubPropertyItemBean();
	}
	
	public void clearProForm() {
		showProAddPanel = false;
		showProModPanel = false;
		proAddress = "";
		draggableModelPro = new DefaultMapModel();
		propertyBean = new IclubPropertyBean();
	}
	
	public void clearProItemForm() {
		showProItemAddPanel = false;
		propertyItemBean = new IclubPropertyItemBean();
	}
	
	public void showProModPanel() {
		showProAddPanel = false;
		showProModPanel = true;
		draggableModelPro = new DefaultMapModel();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		try {
			propertyItemBeans = getPropertyItemBeansMap().get(propertyBean.getPId());
		} catch (Exception e) {
			
		}
		
		if (propertyBean != null && propertyBean.getPLat() != null && propertyBean.getPLong() != null) {
			centerGeoMapPro = propertyBean.getPLat() + "," + propertyBean.getPLong();
			LatLng coord = new LatLng(propertyBean.getPLat(), propertyBean.getPLong());
			Marker marker = new Marker(coord, "");
			marker.setDraggable(true);
			draggableModelPro.addOverlay(marker);
			
		}
	}
	
	public void addIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubProperty");
		try {
			if (validateProForm(true)) {
				
				propertyBean.setPId(UUID.randomUUID().toString());
				
				getPropertyItemBeansMap().put(propertyBean.getPId(), propertyItemBeans);
				propertyBeans.add(propertyBean);
				clearProForm();
				
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addIclubPropertyItem() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubPropertyItem");
		try {
			
			if (validateProItemForm(true)) {
				propertyItemBean.setPiId(UUID.randomUUID().toString());
				propertyBean.setPContentCost(propertyBean.getPContentCost() != null ? propertyBean.getPContentCost() + propertyItemBean.getPiValue() : propertyItemBean.getPiValue() != null ? propertyItemBean.getPiValue() : 0.0);
				propertyItemBeans.add(propertyItemBean);
				
				clearProItemForm();
				IclubWebHelper.addMessage(getLabelBundle().getString("propertyitem") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("propertyitem") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubPropertyItem() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPropertyItem");
		try {
			propertyBean.setPContentCost(propertyBean.getPContentCost() != null ? propertyBean.getPContentCost() - propertyItemBean.getPiValue() : propertyItemBean.getPiValue() != null ? -propertyItemBean.getPiValue() : 0.0);
			propertyItemBeans.remove(propertyItemBean);
			
			clearProItemForm();
			IclubWebHelper.addMessage(getLabelBundle().getString("propertyitem") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("propertyitem") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubProperty");
		try {
			if (validateProForm(false)) {
				
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				clearProForm();
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubProperty");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "del/" + propertyBean.getPId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				propertyBeans.remove(propertyBean);
				clearProForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("property") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateProItemForm(boolean flag) {
		boolean ret = true;
		if (propertyItemBean.getPiDescripton() == null || propertyItemBean.getPiDescripton().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Descripton Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyItemBean.getPiValue() == null) {
			IclubWebHelper.addMessage(("Descripton Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	public boolean validateProForm(boolean flag) {
		boolean ret = true;
		
		if (propertyBean.getPAddress() == null || propertyBean.getPAddress().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Address Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPLat() == null || propertyBean.getPLong() == null) {
			IclubWebHelper.addMessage("Please Select Location", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (propertyBean.getIclubWallType() == null) {
			IclubWebHelper.addMessage(("Please Select WallType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getIclubRoofType() == null) {
			IclubWebHelper.addMessage(("Please Select Roof Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public void addIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubVehicle");
		try {
			if (validateVehForm(true)) {
				
				vehicleBean.setVId(UUID.randomUUID().toString());
				vehicleBean.setVCrtdDt(new Date(System.currentTimeMillis()));
				vehicleBean.setIclubPerson(getSessionUserId());
				
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
				vehicleBeans.add(vehicleBean);
				
				clearVehForm();
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void modIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubVehicle");
		try {
			if (validateVehForm(false)) {
				
				vehicleBean.setVCrtdDt(new Date(System.currentTimeMillis()));
				vehicleBean.setIclubPerson(getSessionUserId());
				
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				clearVehForm();
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubVehicle");
		try {
			WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "del/" + vehicleBean.getVId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				vehicleBeans.remove(vehicleBean);
				clearVehForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("vehicle") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public boolean validateVehForm(boolean flag) {
		boolean ret = true;
		
		if (vehicleBean.getIclubVehicleMaster() == null) {
			IclubWebHelper.addMessage(("Please Select Make and Model"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (vehicleBean.getVOnArea() == null || vehicleBean.getVOnArea().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("On Area Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVOnLat() == null || vehicleBean.getVOnLong() == null) {
			IclubWebHelper.addMessage(("Please Select Location"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getIclubAccessTypeByVOnAccessTypeId() == null) {
			IclubWebHelper.addMessage(("Please Select On AccessType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (vehicleBean.getVYear() == null) {
			IclubWebHelper.addMessage(("Year Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (vehicleBean.getIclubVehUsageType() == null) {
			IclubWebHelper.addMessage(("Please Select VehUsage Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
	}
	
	public MapModel getDraggableModelPer() {
		return draggableModelPer;
	}
	
	public MapModel getDraggableModelVeh() {
		return draggableModelVeh;
	}
	
	public MapModel getDraggableModelVehDd() {
		return draggableModelVehDd;
	}
	
	public MapModel getDraggableModelPro() {
		return draggableModelPro;
	}
	
	public String getCenterGeoMapPro() {
		return centerGeoMapPro;
	}
	
	public String getCenterGeoMapPer() {
		return centerGeoMapPer;
	}
	
	public String getCenterGeoMapVeh() {
		return centerGeoMapVeh;
	}
	
	public String getCenterGeoMapVehDd() {
		return centerGeoMapVehDd;
	}
	
	public void onMarkerDragPer(MarkerDragEvent event) {
		markerPer = event.getMarker();
		
		IclubGeoLocBean geoBean = getGeoLocBean(markerPer.getLatlng().getLat(), markerPer.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			personBean.setPLat(geoBean.getGlLat());
			personBean.setPLong(geoBean.getGlLong());
		} else {
			personBean.setPLat(markerPer.getLatlng().getLat());
			personBean.setPLong(markerPer.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerPer.getLatlng().getLat() + ", Lng:" + markerPer.getLatlng().getLng()));
	}
	
	public void onGeocodePer(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		draggableModelPer = new DefaultMapModel();
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapPer = center.getLat() + "," + center.getLng();
			
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
		if (!IclubWebHelper.validateCountry(markerPer.getLatlng().getLat() + "," + markerPer.getLatlng().getLng())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", "Lat:" + markerPer.getLatlng().getLat() + ", Lng:" + markerPer.getLatlng().getLng() + " is not in range"));
			return;
		}
		IclubGeoLocBean geoBean = getGeoLocBean(markerPer.getLatlng().getLat(), markerPer.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			personBean.setPLat(geoBean.getGlLat());
			personBean.setPLong(geoBean.getGlLong());
		} else {
			personBean.setPLat(markerPer.getLatlng().getLat());
			personBean.setPLong(markerPer.getLatlng().getLng());
		}
		personBean.setPAddress(markerPer.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPer.getTitle()));
	}
	
	public void onMarkerDragPro(MarkerDragEvent event) {
		markerPro = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerPro.getLatlng().getLat(), markerPro.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			propertyBean.setPLat(geoBean.getGlLat());
			propertyBean.setPLong(geoBean.getGlLong());
		} else {
			propertyBean.setPLat(markerPro.getLatlng().getLat());
			propertyBean.setPLong(markerPro.getLatlng().getLng());
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
		
		if (!IclubWebHelper.validateCountry(markerPro.getLatlng().getLat() + "," + markerPro.getLatlng().getLng())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", "Lat:" + markerPro.getLatlng().getLat() + ", Lng:" + markerPro.getLatlng().getLng() + " is not in range"));
			return;
		}
		IclubGeoLocBean geoBean = getGeoLocBean(markerPro.getLatlng().getLat(), markerPro.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			propertyBean.setPLat(geoBean.getGlLat());
			propertyBean.setPLong(geoBean.getGlLong());
		} else {
			propertyBean.setPLat(markerPro.getLatlng().getLat());
			propertyBean.setPLong(markerPro.getLatlng().getLng());
		}
		propertyBean.setPAddress(markerPro.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}
	
	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVOnLat(geoBean.getGlLat());
			vehicleBean.setVOnLong(geoBean.getGlLong());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + geoBean.getGlLat() + ", Lng:" + geoBean.getGlLong()));
		} else {
			vehicleBean.setVOnLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVOnLong(markerVeh.getLatlng().getLng());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng()));
		}
		
	}
	
	public IclubGeoLocBean getGeoLocBean(Double geoLong, Double geoLat) {
		
		IclubGeoLocBean bean = new IclubGeoLocBean();
		
		return bean;
		
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
		if (!IclubWebHelper.validateCountry(markerVeh.getLatlng().getLat() + "," + markerVeh.getLatlng().getLng())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng() + " is not in range"));
			return;
		}
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVOnLat(geoBean.getGlLat());
			vehicleBean.setVOnLong(geoBean.getGlLong());
			
		} else {
			vehicleBean.setVOnLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVOnLong(markerVeh.getLatlng().getLng());
		}
		vehicleBean.setVOnArea(markerVeh.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVeh.getTitle()));
	}
	
	public void onMarkerDragVehDd(MarkerDragEvent event) {
		markerVehDd = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerVehDd.getLatlng().getLat(), markerVehDd.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVDdLat(geoBean.getGlLat());
			vehicleBean.setVDdLong(geoBean.getGlLong());
		} else {
			vehicleBean.setVDdLat(markerVehDd.getLatlng().getLat());
			vehicleBean.setVDdLong(markerVehDd.getLatlng().getLng());
		}
		vehicleBean.setVDdArea(markerVehDd.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng()));
	}
	
	public void onGeocodeVehDd(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		draggableModelVehDd = new DefaultMapModel();
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapVehDd = center.getLat() + "," + center.getLng();
			
			for (int i = 0; i < results.size(); i++) {
				GeocodeResult result = results.get(i);
				Marker marker = new Marker(result.getLatLng(), result.getAddress());
				marker.setDraggable(true);
				draggableModelVehDd.addOverlay(marker);
			}
		}
	}
	
	public void onMarkerSelectVehDd(OverlaySelectEvent event) {
		markerVeh = (Marker) event.getOverlay();
		if (!IclubWebHelper.validateCountry(markerVeh.getLatlng().getLat() + "," + markerVeh.getLatlng().getLng())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng() + " is not in range"));
			return;
		}
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVDdLat(geoBean.getGlLat());
			vehicleBean.setVDdLong(geoBean.getGlLong());
		} else {
			vehicleBean.setVDdLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVDdLong(markerVeh.getLatlng().getLng());
		}
		vehicleBean.setVDdArea(markerVeh.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVeh.getTitle()));
	}
	
	public void initializePage() {
		IclubWebHelper.addObjectIntoSession("page_key", "/pages/quote/qq.xhtml");
		
		if (IclubWebHelper.getObjectIntoSession("social_update_profile") != null) {
			NavigationHandler navigationHandler = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
			navigationHandler.handleNavigation(FacesContext.getCurrentInstance(), null, "/pages/user/profile.xhtml?faces-redirect=true");
			FacesContext.getCurrentInstance().responseComplete();
		}
	}
	
	public void vmMakeValueChangeListener() {
		if (vmMake != null) {
			
			WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "getByMake/" + vmMake);
			Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
			client.close();
			vBeans = new ArrayList<IclubVehicleMasterBean>();
			if (models != null && models.size() > 0) {
				for (IclubVehicleMasterModel model : models) {
					
					IclubVehicleMasterBean bean = IclubVehicleMasterTrans.fromWStoUI(model);
					
					vBeans.add(bean);
				}
			}
			
		} else {
			if (vBeans != null) {
				vBeans.clear();
			}
			if (years != null) {
				years.clear();
			}
		}
		LOGGER.info("Completed");
	}
	
	public void vmModelValueChangeListener() {
		if (vehicleBean.getIclubVehicleMaster() != null) {
			
			WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "get/" + vehicleBean.getIclubVehicleMaster());
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
			
		} else {
			if (years != null) {
				years.clear();
			}
		}
	}
	
	public String registerActionListener() {
		
		try {
			WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);
			
			IclubQuoteModel model = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));
			
			{
				loadPersonBean(model.getIclubPersonBByQPersonId());
				if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) == null || IclubWebHelper.getObjectIntoSession("googlelogin") != null) {
					clearForm();
					clearVehForm();
					clearProForm();
					return "register";
				} else {
					client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);
					
					IclubQuoteModel quoteModel = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));
					client.close();
					if (quoteModel != null) {
						
						quoteModel.setQGenPremium(genPremium);
						quoteModel.setIclubPersonAByQCrtdBy(getSessionUserId());
						quoteModel.setIclubPersonBByQPersonId(getSessionUserId());
						client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "mod");
						ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(quoteModel, ResponseModel.class);
						client.close();
						if (response != null && response.getStatusCode() == 0) {
							
							client = IclubWebHelper.createCustomClient(II_BASE_URL + "getByQuoteIdAndItemTypeId/" + quoteId + "/" + 1l);
							
							IclubInsuranceItemModel insurancemodel = (IclubInsuranceItemModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubInsuranceItemModel.class));
							client.close();
							if (insurancemodel != null) {
								insurancemodel.setIclubPerson(getSessionUserId());
								client = IclubWebHelper.createCustomClient(II_BASE_URL + "mod");
								response = client.accept(MediaType.APPLICATION_JSON).put(insurancemodel, ResponseModel.class);
								client.close();
							}
							
						}
					}
					
					clearForm();
					return "vq";
				}
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Load Person Fail ::" + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public void clearForm() {
		vehicleBean = new IclubVehicleBean();
		driverBean = new IclubDriverBean();
		personBean = new IclubPersonBean();
		quoteBean = new IclubQuoteBean();
		claimYN = "";
		vehicleBeans = new ArrayList<IclubVehicleBean>();
		propertyBeans = new ArrayList<IclubPropertyBean>();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		propertyItemBeansMap = new HashMap<String, List<IclubPropertyItemBean>>();
	}
	
	public String updatedPerson() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatedPerson");
		try {
			if (validatePersonForm(!true) && validateLoginForm(!updateLogin)) {
				WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "mod");
				
				IclubPersonModel model = IclubPersonTrans.fromUItoWS(bean);
				
				model.setPAge(IclubWebHelper.calculateYearDiff(bean.getPDob().getTime()));
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				
				if (response.getStatusCode() == 0) {
					response = updatePassword();
					client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);
					
					IclubQuoteModel quoteModel = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));
					quoteModel.setQGenPremium(genPremium);
					quoteModel.setIclubPersonAByQCrtdBy(model.getPId());
					quoteModel.setIclubPersonBByQPersonId(model.getPId());
					
					client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "mod");
					
					response = client.accept(MediaType.APPLICATION_JSON).put(quoteModel, ResponseModel.class);
					client.close();
					
					if (response != null && response.getStatusCode() == 0) {
						
						client = IclubWebHelper.createCustomClient(II_BASE_URL + "getByQuoteIdAndItemTypeId/" + quoteId + "/" + 1l);
						
						IclubInsuranceItemModel insurancemodel = (IclubInsuranceItemModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubInsuranceItemModel.class));
						insurancemodel.setIclubPerson(model.getPId());
						client = IclubWebHelper.createCustomClient(II_BASE_URL + "mod");
						response = client.accept(MediaType.APPLICATION_JSON).put(insurancemodel, ResponseModel.class);
						client.close();
						
					}
					
					if (response != null && response.getStatusCode() == 0) {
						
						IclubWebHelper.addMessage("Updated Successfully", FacesMessage.SEVERITY_INFO);
						if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) == null) {
							doIclubLogin(loginBean, bean);
						}
						bean = new IclubPersonBean();
						
						IclubWebHelper.addObjectIntoSession("googlelogin", null);
						return "vq";
					} else {
						IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					}
					
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			} else {
				IclubWebHelper.addMessage("Fail :: ", FacesMessage.SEVERITY_ERROR);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return "";
		
	}
	
	public void doIclubLogin(IclubLoginBean bean, IclubPersonBean personBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: doIclubLogin");
		if (bean != null && personBean != null) {
			try {
				WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/login/" + bean.getLName() + "/" + Base64.encodeBase64URLSafeString(DigestUtils.md5(bean.getLPasswd())));
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Person Logged-In successfully", FacesMessage.SEVERITY_INFO);
					WebClient loginClient = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/" + bean.getLName());
					IclubLoginModel model = loginClient.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class);
					loginClient.close();
					if (model != null && model.getLId() != null) {
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonBByLPersonId());
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), bean.getLName());
						WebClient personClient = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + model.getIclubPersonBByLPersonId());
						IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
						personClient.close();
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), model.getIclubRoleType());
						
					} else {
						IclubWebHelper.addMessage("Person Profile Fetch Error - Contact Admin", FacesMessage.SEVERITY_ERROR);
						
					}
				} else {
					IclubWebHelper.addMessage("Login error :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
				IclubWebHelper.addMessage("Login error :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				
			}
		} else {
			IclubWebHelper.addMessage("Login error :: ", FacesMessage.SEVERITY_ERROR);
			
		}
		
	}
	
	public ResponseModel updatePassword() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatePassword");
		try {
			if (validateLoginForm(!updateLogin)) {
				IclubLoginModel model = IclubLoginTrans.fromUItoWS(loginBean);
				WebClient client = null;
				
				if (loginBean.getLId() != null) {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "mod");
					model.setLId(loginBean.getLId());
					
				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
					model.setLId(UUID.randomUUID().toString());
				}
				model.setLCrtdDt(new Date(System.currentTimeMillis()));
				model.setLPasswd(Base64.encodeBase64URLSafeString(DigestUtils.md5(loginBean.getLPasswd())));
				model.setIclubPersonAByLCrtdBy(bean.getPId());
				model.setIclubPersonBByLPersonId(bean.getPId());
				model.setIclubRoleType(2l);
				
				ResponseModel response = null;
				if (updateLogin) {
					response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				} else {
					response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				}
				
				return response;
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return new ResponseModel();
	}
	
	public boolean validateLoginForm(boolean flag) {
		boolean ret = true;
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
		if (bean.getPFName() == null || bean.getPFName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("First Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPLName() == null || bean.getPLName().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Last Name Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPMobile() == null || bean.getPMobile().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Mobile Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPGender() == null || bean.getPGender().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gender Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (bean.getPIdNum() == null || bean.getPIdNum().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Id Number Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubIdType() == null) {
			IclubWebHelper.addMessage(("Please Select ID Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPIsPensioner() == null || bean.getPIsPensioner().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Please Select Pensioner"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPDob() == null) {
			IclubWebHelper.addMessage(("Please Select DOB"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.calculateYearDiff(bean.getPDob().getTime()) <= 18) {
			IclubWebHelper.addMessage(("You must be over 18 years"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPIdIssueDt() == null) {
			IclubWebHelper.addMessage(("Please Select IssueDate"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.isCurrentDate(bean.getPIdIssueDt().getTime())) {
			IclubWebHelper.addMessage(("Issue Date Should be less than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (bean.getPIdExpiryDt() != null && !IclubWebHelper.isCurrentDate(bean.getPIdExpiryDt().getTime())) {
			IclubWebHelper.addMessage(("Issue Date Should be greater than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (bean.getPIdExpiryDt() != null && !IclubWebHelper.isCurrentDate(bean.getPIdExpiryDt().getTime())) {
			IclubWebHelper.addMessage(("Expiry Date Should be greater than Current Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (((Long) bean.getPIdIssueDt().getTime()).compareTo(((Long) bean.getPIdExpiryDt().getTime())) >= 0) {
			IclubWebHelper.addMessage((" Id Issue Date Should be less than Expiry Date"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubIdType() == null) {
			IclubWebHelper.addMessage(("Please Select IdType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		else if (bean.getIclubIdType() == 4 || bean.getIclubIdType() == 1) {
			String idValidate = IclubWebHelper.validateId(bean.getPIdNum(), bean.getPGender());
			if (idValidate != null && !idValidate.trim().equalsIgnoreCase("")) {
				
				IclubWebHelper.addMessage(idValidate, FacesMessage.SEVERITY_ERROR);
				ret = ret && false;
			}
			
		}
		
		return ret;
	}
	
	public String saveQuickQuoteDetails() {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: saveQuickQuoteDetails");
		try {
			IclubQuickQuoteRequest request = new IclubQuickQuoteRequest();
			if (validateForm(true, IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) == null && IclubWebHelper.getObjectIntoSession("googlelogin") == null)) {
				if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null && IclubWebHelper.getObjectIntoSession("googlelogin") == null) {
					
					personBean = getIclubPersonBean(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
					request.setLoginFlag(true);
				} else if (IclubWebHelper.getObjectIntoSession("googlelogin") != null) {
					request.setLoginFlag(true);
				}
				
				IclubPersonModel personModel = insertIntoPerson(personBean);
				
				IclubQuoteModel quoteModel = addQuote(getQuoteBean(), personBean);
				request.setIclubQuoteModel(quoteModel);
				
				IclubDriverModel driverModel = addDriver(driverBean, personBean, quoteModel);
				List<IclubVehicleModel> vehicleModels = addVehicle(vehicleBeans, driverBean, quoteModel);
				
				List<IclubPropertyModel> propertyModels = addPropertiy(propertyBeans, quoteModel);
				List<IclubPropertyItemModel> propertyItemModels = getPropertyItemModels(propertyItemBeansMap);
				request.setIclubDriverModel(driverModel);
				request.setIclubVehicleModels(vehicleModels);
				request.setIclubPersonModel(personModel);
				request.setIclubPropertyModels(propertyModels);
				request.setIclubPropertyItemModels(propertyItemModels);
				WebClient client = IclubWebHelper.createCustomClient(QQUT_BASE_URL + "createQuote/");
				
				IclubQuickQuoteResponse response = client.accept(MediaType.APPLICATION_JSON).post(request, IclubQuickQuoteResponse.class);
				quoteId = response.getQuoteNumber();
				genPremium = response.getGeneratedPremium();
				
				IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);
				return "qqs.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public IclubPersonModel insertIntoPerson(IclubPersonBean personBean) throws Exception {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: insertIntoPerson");
		
		try {
			IclubPersonModel model = IclubPersonTrans.fromUItoWS(personBean);
			
			personBean.setPAge(personBean.getPDob() != null ? IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()) : null);
			personBean.setPId(personBean.getPId() != null ? personBean.getPId() : UUID.randomUUID().toString());
			
			return model;
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw e;
		}
		
	}
	
	public void deleteIclubPerson(IclubPersonBean personBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPerson");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "del/" + personBean.getPId());
			client.accept(MediaType.APPLICATION_JSON).get();
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubQuote(IclubQuoteBean quoteBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubQuote");
		try {
			WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "del/" + quoteBean.getQId());
			client.accept(MediaType.APPLICATION_JSON).get();
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubDriver(IclubDriverBean driverBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubDriver");
		try {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "del/" + driverBean.getDId());
			client.accept(MediaType.APPLICATION_JSON).get();
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubVehile(IclubVehicleBean vehicleBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubVehicle");
		try {
			WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "del/" + vehicleBean.getVId());
			client.accept(MediaType.APPLICATION_JSON).get();
			deleteIclubInsuranceItemBean(vehicleBean.getVId());
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubProperty(IclubPropertyBean propertyBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: deleteIclubProperty");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "del/" + propertyBean.getPId());
			client.accept(MediaType.APPLICATION_JSON).get();
			deleteIclubPropertyItem(propertyBean.getPId());
			deleteIclubInsuranceItemBean(propertyBean.getPId());
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubPropertyItem(String propertyBean) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: deleteIclubPropertyItem");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PRO_ITM_BASE_URL + "delByProperty/" + propertyBean);
			client.accept(MediaType.APPLICATION_JSON).get();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public void deleteIclubInsuranceItemBean(String iiItemId) {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubInsuranceItemBean");
		try {
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "delByVehilce/" + iiItemId);
			client.accept(MediaType.APPLICATION_JSON).get();
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}
	
	public List<IclubVehicleModel> addVehicle(List<IclubVehicleBean> beans, IclubDriverBean driverBean, IclubQuoteModel quoteModel) throws Exception {
		List<IclubVehicleModel> models = new ArrayList<IclubVehicleModel>();
		
		if (beans != null && beans.size() > 0) {
			try {
				
				for (IclubVehicleBean bean : beans) {
					
					IclubVehicleModel model = IclubVehicleTrans.fromUItoWS(bean);
					
					model.setIclubDriver(driverBean.getDId());
					
					models.add(model);
				}
				
				quoteId = quoteModel.getQId();
				
			} catch (Exception e) {
				LOGGER.error(e, e);
				throw e;
			}
			
		}
		
		return models;
		
	}
	
	public List<IclubPropertyItemModel> getPropertyItemModels(Map<String, List<IclubPropertyItemBean>> propertyItemModelMap) {
		List<IclubPropertyItemModel> propertyItemModels = new ArrayList<IclubPropertyItemModel>();
		
		if (propertyItemModelMap != null && propertyItemModelMap.size() > 0) {
			for (String propId : propertyItemModelMap.keySet()) {
				
				for (IclubPropertyItemBean bean : propertyItemModelMap.get(propId)) {
					IclubPropertyItemModel model = IclubPropertyItemTrans.fromUItoWS(bean);
					model.setIclubProperty(propId);
					propertyItemModels.add(model);
				}
			}
		}
		
		return propertyItemModels;
		
	}
	
	public List<IclubPropertyModel> addPropertiy(List<IclubPropertyBean> beans, IclubQuoteModel quoteModel) throws Exception {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addPropertiy");
		List<IclubPropertyModel> models = new ArrayList<IclubPropertyModel>();
		if (beans != null && beans.size() > 0) {
			try {
				
				for (IclubPropertyBean bean : beans) {
					
					IclubPropertyModel model = IclubPropertyTrans.fromUItoWS(bean);
					
					models.add(model);
				}
				
			} catch (Exception e) {
				
				throw e;
			}
			
		}
		
		return models;
		
	}
	
	public void addPropertiyItem(List<IclubPropertyItemBean> beans, IclubPropertyModel propertyModel) {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addPropertiy");
		
		if (beans != null && beans.size() > 0) {
			for (IclubPropertyItemBean bean : beans) {
				IclubPropertyItemModel model = IclubPropertyItemTrans.fromUItoWS(bean);
				WebClient client = IclubWebHelper.createCustomClient(PRO_ITM_BASE_URL + "add");
				model.setPiCrtdDate(new Date(System.currentTimeMillis()));
				model.setIclubPerson(personBean.getPId());
				model.setIclubProperty(propertyModel.getPId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
				
			}
		}
	}
	
	public void addInsuranceItem(String itemId, String quoteId, Long iItemType, String userId) throws Exception {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addInsuranceItem");
		
		try {
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "add");
			IclubInsuranceItemModel model = new IclubInsuranceItemModel();
			model.setIiId(UUID.randomUUID().toString());
			model.setIiItemId(itemId);
			model.setIiQuoteId(quoteId);
			model.setIiCrtdDt(new Date(System.currentTimeMillis()));
			model.setIclubInsuranceItemType(iItemType);
			model.setIclubPerson(userId);
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			client.close();
			
			if (response.getStatusCode() == 0) {
				
			} else {
				IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				throw new Exception();
				
			}
		} catch (Exception e) {
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			throw e;
		}
		
	}
	
	public IclubQuoteModel addQuote(IclubQuoteBean bean, IclubPersonBean personBean) throws Exception {
		
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addQuote");
		
		try {
			
			bean.setQId(UUID.randomUUID().toString());
			IclubQuoteModel model = IclubQuoteTrans.fromUItoWS(bean);
			model.setQId(bean.getQId());
			model.setQCrtdDt(new Date(System.currentTimeMillis()));
			model.setQIsMatched("N");
			model.setQPrevPremium(bean != null ? bean.getQPrevPremium() : 0.0d);
			model.setQValidUntil(new Date(new Date(System.currentTimeMillis() + (1000 * 24 * 3600 * 24)).getTime() + (1000 * 24 * 3600 * 6)));
			model.setQMobile(personBean.getPMobile());
			model.setQEmail(personBean.getPEmail());
			model.setQGenPremium(0.0d);
			model.setQNumItems(1);
			model.setQGenDt(new Date(System.currentTimeMillis()));
			model.setQNumber(getQnumber());
			model.setIclubPersonAByQCrtdBy(getSessionUserId());
			model.setIclubProductType(1l);
			model.setIclubQuoteStatus(1l);
			model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
			model.setIclubPersonBByQPersonId(personBean.getPId());
			return model;
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw e;
		}
		
	}
	
	public Long getQnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));
		
	}
	
	public IclubDriverModel addDriver(IclubDriverBean bean, IclubPersonBean personBean, IclubQuoteModel quoteModel) throws Exception {
		
		try {
			
			bean.setDId(UUID.randomUUID().toString());
			
			IclubDriverModel model = IclubDriverTrans.fromUItoWS(bean);
			
			model.setDIssueYears(IclubWebHelper.calculateYearDiff(bean.getDIssueDt().getTime()));
			model.setDCrtdDt(new Date(System.currentTimeMillis()));
			model.setIclubMaritialStatus(personBean.getIclubMaritialStatus());
			model.setIclubPersonBByDPersonId(personBean.getPId());
			model.setIclubPersonAByDCrtdBy(getSessionUserId());
			
			return model;
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw e;
		}
		
	}
	
	public boolean validateForm(boolean flag, boolean loginFlag) {
		boolean ret = true;
		
		if (vehicleBeans == null || vehicleBeans.size() == 0) {
			IclubWebHelper.addMessage("Add atleast one vehicle", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (loginFlag && (personBean.getPFName() == null || personBean.getPFName().trim().equalsIgnoreCase(""))) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pfname.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginFlag && (personBean.getPLName() == null || personBean.getPLName().trim().equalsIgnoreCase(""))) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.plname.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginFlag && (personBean.getPMobile() == null || personBean.getPMobile().trim().equalsIgnoreCase(""))) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pmobile.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginFlag && (personBean.getPEmail() == null || personBean.getPEmail().trim().equalsIgnoreCase(""))) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pemail.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginFlag && (personBean.getPGender() == null || personBean.getPGender().trim().equalsIgnoreCase(""))) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pgender.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (loginFlag && (personBean.getPDob() == null)) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pdob.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (driverBean.getDName() == null || driverBean.getDName().trim().equalsIgnoreCase("")) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.dname.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (driverBean.getIclubLicenseCode() == null) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.dlicensecode.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (driverBean.getDIssueDt() == null) {
			
			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.dissuedt.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		} else if (IclubWebHelper.isCurrentDate(driverBean.getDIssueDt().getTime())) {
			
			IclubWebHelper.addMessage("Issue Date Should be less than Current Date", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (quoteBean.getQPrevPremium() != 0 && quoteBean.getIclubInsurerMaster() == null) {
			
			IclubWebHelper.addMessage("Please select Previous Insurer", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		if (quoteBean.getIclubInsurerMaster() != null && (quoteBean.getQPrevPremium() == 0 || quoteBean.getQPrevPremium() == null)) {
			
			IclubWebHelper.addMessage("Please enter Current Premium", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		
		return ret;
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
	
	public List<IclubVehUsageTypeBean> getpBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/get/insurnceitemtype/" + "1");
		Collection<? extends IclubVehUsageTypeModel> models = new ArrayList<IclubVehUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehUsageTypeModel.class));
		client.close();
		vehUsageTypeBeans = new ArrayList<IclubVehUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehUsageTypeModel model : models) {
				IclubVehUsageTypeBean bean = IclubVehUsageTypeTrans.fromWStoUI(model);
				
				pBeans.add(bean);
				
			}
		}
		return pBeans;
	}
	
	public void setpBeans(List<IclubVehUsageTypeBean> pBeans) {
		this.pBeans = pBeans;
	}
	
	public IclubPersonBean getPersonBean() {
		
		if (personBean == null && IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null) {
			
			personBean = getIclubPersonBean(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		}
		if (personBean == null)
			personBean = new IclubPersonBean();
		return personBean;
	}
	
	public void setPersonBean(IclubPersonBean personBean) {
		this.personBean = personBean;
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
	
	public IclubDriverBean getDriverBean() {
		if (driverBean == null)
			driverBean = new IclubDriverBean();
		return driverBean;
	}
	
	public void setDriverBean(IclubDriverBean driverBean) {
		this.driverBean = driverBean;
	}
	
	public List<IclubLicenseCodeBean> getLicenseCodeBeans() {
		
		licenseCodeBeans = new ArrayList<IclubLicenseCodeBean>();
		WebClient client = IclubWebHelper.createCustomClient(LIC_BASE_URL + "list");
		Collection<? extends IclubLicenseCodeModel> models = new ArrayList<IclubLicenseCodeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubLicenseCodeModel.class));
		client.close();
		
		if (models != null && models.size() > 0) {
			for (IclubLicenseCodeModel model : models) {
				
				IclubLicenseCodeBean bean = IclubLicenseCodeTrans.fromWStoUI(model);
				
				licenseCodeBeans.add(bean);
			}
		}
		
		return licenseCodeBeans;
	}
	
	public void setLicenseCodeBeans(List<IclubLicenseCodeBean> licenseCodeBeans) {
		this.licenseCodeBeans = licenseCodeBeans;
	}
	
	public List<IclubWallTypeBean> getWallTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(WT_BASE_URL + "list");
		Collection<? extends IclubWallTypeModel> models = new ArrayList<IclubWallTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubWallTypeModel.class));
		client.close();
		wallTypeBeans = new ArrayList<IclubWallTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubWallTypeModel model : models) {
				
				IclubWallTypeBean bean = IclubWallTypeTrans.fromWStoUI(model);
				
				wallTypeBeans.add(bean);
			}
		}
		return wallTypeBeans;
	}
	
	public void setWallTypeBeans(List<IclubWallTypeBean> wallTypeBeans) {
		this.wallTypeBeans = wallTypeBeans;
	}
	
	public List<IclubRoofTypeBean> getRoofTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(RT_BASE_URL + "list");
		Collection<? extends IclubRoofTypeModel> models = new ArrayList<IclubRoofTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRoofTypeModel.class));
		client.close();
		roofTypeBeans = new ArrayList<IclubRoofTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRoofTypeModel model : models) {
				IclubRoofTypeBean bean = IclubRoofTypeTrans.fromWStoUI(model);
				
				roofTypeBeans.add(bean);
			}
		}
		return roofTypeBeans;
	}
	
	public void setRoofTypeBeans(List<IclubRoofTypeBean> roofTypeBeans) {
		this.roofTypeBeans = roofTypeBeans;
	}
	
	public IclubPropertyBean getPropertyBean() {
		if (propertyBean == null) {
			propertyBean = new IclubPropertyBean();
		}
		return propertyBean;
	}
	
	public void setPropertyBean(IclubPropertyBean propertyBean) {
		this.propertyBean = propertyBean;
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
	
	public IclubVehicleMasterBean getVehicleMasterBean() {
		if (vehicleMasterBean == null) {
			vehicleMasterBean = new IclubVehicleMasterBean();
		}
		return vehicleMasterBean;
	}
	
	public void setVehicleMasterBean(IclubVehicleMasterBean vehicleMasterBean) {
		this.vehicleMasterBean = vehicleMasterBean;
	}
	
	public String getVmMake() {
		return vmMake;
	}
	
	public void setVmMake(String vmMake) {
		this.vmMake = vmMake;
	}
	
	public IclubVehicleBean getVehicleBean() {
		if (vehicleBean == null) {
			vehicleBean = new IclubVehicleBean();
		}
		return vehicleBean;
	}
	
	public void setVehicleBean(IclubVehicleBean vehicleBean) {
		this.vehicleBean = vehicleBean;
	}
	
	public String getClaimYN() {
		return claimYN;
	}
	
	public void setClaimYN(String claimYN) {
		this.claimYN = claimYN;
	}
	
	public ResourceBundle getLabelBundle() {
		
		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	public boolean isTermsAndConditionFlag() {
		return termsAndConditionFlag;
	}
	
	public void setTermsAndConditionFlag(boolean termsAndConditionFlag) {
		this.termsAndConditionFlag = termsAndConditionFlag;
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
	
	public void loadLoginBean() {
		WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "personId/" + bean.getPId());
		
		IclubLoginModel model = (IclubLoginModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class));
		if (model != null && model.getLId() != null) {
			updateLogin = true;
			loginBean = IclubLoginTrans.fromWStoUI(model);
			
		} else {
			loginBean = new IclubLoginBean();
		}
	}
	
	public void loadPersonBean(String userId) {
		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + userId);
		
		IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
		bean = IclubPersonTrans.fromWStoUI(model);
		
		loadLoginBean();
		
	}
	
	public void setBean(IclubPersonBean bean) {
		this.bean = bean;
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
	
	public Double getUpdatePremium(String quoteId, String quoteType, IclubVehicleBean vehicleBean, IclubDriverBean driverBean, IclubPropertyBean propertyBean) {
		List<IclubFieldBean> fieldBeans = getIclubFieldBeans();
		IclubQuoteBean quoteBean = getQuoteDetailsById(quoteId);
		Double baseValue = getBasePremium();
		Double premium = baseValue;
		for (IclubFieldBean fieldBean : fieldBeans) {
			if (fieldBean.getFRate() != null && fieldBean.getFStatus().equalsIgnoreCase("Y")) {
				
				IclubEntityTypeBean entityType = getEntityType(fieldBean.getIclubEntityType());
				String tableName = entityType.getEtTblNm();
				String fieldName = fieldBean.getFName();
				if (tableName != null) {
					List<IclubRateTypeBean> rateTypeBeans = getRateTypeBeanByFieldId(fieldBean.getFId(), quoteType);
					String fieldValue = null;
					if (tableName.equalsIgnoreCase("iclub_vehicle") && rateTypeBeans != null && rateTypeBeans.size() > 0 && rateTypeBeans.get(0).getRtType().equalsIgnoreCase("G")) {
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId(), "G");
						
					} else if (tableName.equalsIgnoreCase("iclub_vehicle")) {
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId(), null);
						
					} else if (tableName.equalsIgnoreCase("iclub_property") && propertyBean != null && propertyBean.getPId() != null) {
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, propertyBean.getPId(), null);
					} else if (tableName.equalsIgnoreCase("iclub_person")) {
						IclubPersonBean personBean = getIclubPersonBean(quoteBean.getIclubPersonBByQPersonId());
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, personBean.getPId(), null);
						
					} else if (tableName.equalsIgnoreCase("iclub_driver")) {
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, driverBean.getDId(), null);
						
					} else if (tableName.equalsIgnoreCase("iclub_quote")) {
						
						fieldValue = getFieldValueFromDB(fieldName, tableName, quoteId, null);
						
					}
					
					for (IclubRateTypeBean rateTypeBean : rateTypeBeans) {
						if (rateTypeBean.getRtType().equalsIgnoreCase("G")) {
							String fieldValues[] = fieldValue.split("@");
							IclubGeoLocBean geoLocBean = getGeoLocBean(new Double(fieldValues[0]), new Double(fieldValues[1]));
							premium = premium + baseValue * (geoLocBean.getGlRate() / 100);
							
						} else {
							List<IclubRateEngineBean> rateEngineBeans = getRateEnginesByRateType(rateTypeBean.getRtId());
							for (IclubRateEngineBean rateEngineBean : rateEngineBeans) {
								if (fieldValue != null) {
									if ((rateTypeBean.getRtType().equalsIgnoreCase("F") && rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(fieldValue.toString()) || (rateTypeBean.getRtType().trim().equalsIgnoreCase("R") && (Double.parseDouble(rateEngineBean.getReBaseValue().trim()) <= Double.parseDouble(fieldValue.toString()) && Double.parseDouble(rateEngineBean.getReMaxValue().trim()) >= Double.parseDouble(fieldValue.toString()))))) {
										
										premium = premium + baseValue * (rateEngineBean.getReRate() / 100);
										
									} else if (rateTypeBean.getRtType().equalsIgnoreCase("L")) {
										WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/lookupdetails/" + fieldBean.getFLTblName() + "/" + fieldValue.toString());
										String lookupDetails = client.accept(MediaType.APPLICATION_JSON).get(String.class);
										if (rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(lookupDetails)) {
											premium = premium + baseValue * (rateEngineBean.getReRate() / 100);
										}
									}
									
								}
							}
						}
						
					}
					
				}
			}
		}
		return premium;
	}
	
	public IclubInsuranceItemBean setInsuranceItemDetails(String quoteId, Long itemTypeId) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "getByQuoteIdAndItemTypeId/" + quoteId + "/" + itemTypeId);
		
		IclubInsuranceItemModel model = (IclubInsuranceItemModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubInsuranceItemModel.class));
		IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);
		
		client.close();
		return bean;
	}
	
	public List<IclubRateEngineBean> getRateEnginesByRateType(Long rateType) {
		
		WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/rateType/" + rateType);
		Collection<? extends IclubRateEngineModel> models = new ArrayList<IclubRateEngineModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateEngineModel.class));
		client.close();
		List<IclubRateEngineBean> beans = new ArrayList<IclubRateEngineBean>();
		if (models != null && models.size() > 0) {
			for (IclubRateEngineModel model : models) {
				
				IclubRateEngineBean bean = IclubRateEngineTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
		}
		return beans;
	}
	
	public String getFieldValueFromDB(String fieldName, String tableName, String id, String rateType) {
		String fieldValue = null;
		if (rateType != null && !rateType.trim().equalsIgnoreCase("") && rateType.trim().equalsIgnoreCase("G")) {
			WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/fieldValues/" + fieldName + "/" + tableName + "/" + id);
			
			fieldValue = client.accept(MediaType.APPLICATION_JSON).get(String.class);
		} else {
			WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/fieldValue/" + fieldName + "/" + tableName + "/" + id);
			
			fieldValue = client.accept(MediaType.APPLICATION_JSON).get(String.class);
		}
		
		return fieldValue;
		
	}
	
	public IclubVehicleBean getVehicleDetails(String vehicleID) {
		WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "get/" + vehicleID);
		
		IclubVehicleModel model = (IclubVehicleModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleModel.class));
		IclubVehicleBean vehicleBean = new IclubVehicleBean();
		if (model != null && model.getVId() != null) {
			vehicleBean = IclubVehicleTrans.fromWStoUI(model);
			
		}
		
		client.close();
		return vehicleBean;
	}
	
	public IclubPropertyBean getPropertyDetails(String propertyId) {
		WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + propertyId);
		IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));
		IclubPropertyBean propertyBean = new IclubPropertyBean();
		if (model != null && model.getPId() != null) {
			propertyBean = IclubPropertyTrans.fromWStoUI(model);
			
		}
		client.close();
		return propertyBean;
	}
	
	public IclubEntityTypeBean getEntityType(Long entityId) {
		WebClient client = IclubWebHelper.createCustomClient(ET_BASE_URL + "get/" + entityId);
		IclubEntityTypeModel model = (IclubEntityTypeModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubEntityTypeModel.class));
		client.close();
		
		IclubEntityTypeBean bean = IclubEntityTypeTrans.fromWStoUI(model);
		
		return bean;
		
	}
	
	public IclubQuoteBean getQuoteDetailsById(String quoteId) {
		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);
		IclubQuoteModel model = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));
		
		IclubQuoteBean bean = IclubQuoteTrans.fromWStoUI(model);
		
		client.close();
		
		return bean;
		
	}
	
	public IclubPersonBean getIclubPersonBean(String personId) {
		
		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + personId);
		
		IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
		IclubPersonBean personBean = IclubPersonTrans.fromWStoUI(model);
		
		client.close();
		return personBean;
	}
	
	public List<IclubRateTypeBean> getRateTypeBeanByFieldId(Long fieldId, String quoteType) {
		
		WebClient client = IclubWebHelper.createCustomClient(RAT_BASE_URL + "getByFieldIdANdQuoteType/" + fieldId + "/" + quoteType);
		Collection<? extends IclubRateTypeModel> models = new ArrayList<IclubRateTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateTypeModel.class));
		client.close();
		List<IclubRateTypeBean> beans = new ArrayList<IclubRateTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubRateTypeModel model : models) {
				
				IclubRateTypeBean bean = IclubRateTypeTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
		}
		return beans;
		
	}
	
	public List<IclubFieldBean> getIclubFieldBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(FD_BASE_URL + "getByStatus/Y");
		Collection<? extends IclubFieldModel> models = new ArrayList<IclubFieldModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubFieldModel.class));
		client.close();
		List<IclubFieldBean> beans = new ArrayList<IclubFieldBean>();
		if (models != null && models.size() > 0) {
			for (IclubFieldModel model : models) {
				
				IclubFieldBean bean = IclubFieldTrans.fromWStoUI(model);
				
				beans.add(bean);
			}
			
		}
		return beans;
	}
	
	public Double getBasePremium() {
		WebClient client = IclubWebHelper.createCustomClient(CONF_BASE_URL + "/getByKey/base.premium");
		
		IclubConfigModel model = (IclubConfigModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubConfigModel.class));
		if (model != null) {
			return new Double(model.getCValue());
		} else {
			return 100d;
		}
	}
	
	public Double getGenPremium() {
		if (genPremium == null) {
			genPremium = 0d;
		}
		return genPremium;
	}
	
	public void setGenPremium(Double genPremium) {
		this.genPremium = genPremium;
	}
	
	public String getQuoteId() {
		return quoteId;
	}
	
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}
	
	public boolean isUpdateLogin() {
		return updateLogin;
	}
	
	public void setUpdateLogin(boolean updateLogin) {
		this.updateLogin = updateLogin;
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
	
	public IclubPersonBean getBean() {
		if (bean == null) {
			bean = new IclubPersonBean();
		}
		return bean;
	}
	
	public List<IclubVehicleBean> getVehicleBeans() {
		if (vehicleBeans == null) {
			vehicleBeans = new ArrayList<IclubVehicleBean>();
		}
		
		return vehicleBeans;
	}
	
	public void setVehicleBeans(List<IclubVehicleBean> vehicleBeans) {
		this.vehicleBeans = vehicleBeans;
	}
	
	public List<IclubVehSecTypeBean> getVehSecTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "list");
		Collection<? extends IclubVehSecTypeModel> models = new ArrayList<IclubVehSecTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehSecTypeModel.class));
		client.close();
		vehSecTypeBeans = new ArrayList<IclubVehSecTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehSecTypeModel model : models) {
				
				IclubVehSecTypeBean bean = IclubVehSecTypeTrans.fromWStoUI(model);
				
				vehSecTypeBeans.add(bean);
			}
		}
		return vehSecTypeBeans;
	}
	
	public void setVehSecTypeBeans(List<IclubVehSecTypeBean> vehSecTypeBeans) {
		this.vehSecTypeBeans = vehSecTypeBeans;
	}
	
	public List<IclubVehUsageTypeBean> getVehUsageTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(VEHU_BASE_URL + "/list");
		Collection<? extends IclubVehUsageTypeModel> models = new ArrayList<IclubVehUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehUsageTypeModel.class));
		client.close();
		vehUsageTypeBeans = new ArrayList<IclubVehUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehUsageTypeModel model : models) {
				IclubVehUsageTypeBean bean = IclubVehUsageTypeTrans.fromWStoUI(model);
				
				vehUsageTypeBeans.add(bean);
			}
		}
		return vehUsageTypeBeans;
	}
	
	public void setVehUsageTypeBeans(List<IclubVehUsageTypeBean> vehUsageTypeBeans) {
		this.vehUsageTypeBeans = vehUsageTypeBeans;
	}
	
	public List<IclubAccessTypeBean> getAccessTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(AEST_BASE_URL + "list");
		Collection<? extends IclubAccessTypeModel> models = new ArrayList<IclubAccessTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccessTypeModel.class));
		client.close();
		accessTypeBeans = new ArrayList<IclubAccessTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccessTypeModel model : models) {
				IclubAccessTypeBean bean = IclubAccessTypeTrans.fromWStoUI(model);
				
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
				IclubBarTypeBean bean = IclubBarTypeTrans.fromWStoUI(model);
				
				barTypeBeans.add(bean);
			}
		}
		return barTypeBeans;
	}
	
	public void setBarTypeBeans(List<IclubBarTypeBean> barTypeBeans) {
		this.barTypeBeans = barTypeBeans;
	}
	
	public List<IclubPropertyTypeBean> getPropertyTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PROT_BASE_URL + "list");
		Collection<? extends IclubPropertyTypeModel> models = new ArrayList<IclubPropertyTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyTypeModel.class));
		client.close();
		propertyTypeBeans = new ArrayList<IclubPropertyTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropertyTypeModel model : models) {
				IclubPropertyTypeBean bean = IclubPropertyTypeTrans.fromWStoUI(model);
				
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
				IclubOccupiedStatusBean bean = IclubOccupiedStatusTrans.fromWStoUI(model);
				
				occupiedStatusBeans.add(bean);
			}
		}
		return occupiedStatusBeans;
	}
	
	public void setOccupiedStatusBeans(List<IclubOccupiedStatusBean> occupiedStatusBeans) {
		this.occupiedStatusBeans = occupiedStatusBeans;
	}
	
	public List<IclubPropUsageTypeBean> getpPropUsageTypeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/list");
		Collection<? extends IclubPropUsageTypeModel> models = new ArrayList<IclubPropUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropUsageTypeModel.class));
		client.close();
		pPropUsageTypeBeans = new ArrayList<IclubPropUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropUsageTypeModel model : models) {
				IclubPropUsageTypeBean bean = IclubPropUsageTypeTrans.fromWStoUI(model);
				
				pPropUsageTypeBeans.add(bean);
			}
		}
		return pPropUsageTypeBeans;
	}
	
	public void setpPropUsageTypeBeans(List<IclubPropUsageTypeBean> pPropUsageTypeBeans) {
		this.pPropUsageTypeBeans = pPropUsageTypeBeans;
	}
	
	public List<IclubCoverTypeBean> getCoverTypeBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(CT_BASE_URL + "list");
		Collection<? extends IclubCoverTypeModel> models = new ArrayList<IclubCoverTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubCoverTypeModel.class));
		client.close();
		coverTypeBeans = new ArrayList<IclubCoverTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubCoverTypeModel model : models) {
				
				IclubCoverTypeBean bean = IclubCoverTypeTrans.fromWStoUI(model);
				
				coverTypeBeans.add(bean);
			}
		}
		return coverTypeBeans;
	}
	
	public void setCoverTypeBeans(List<IclubCoverTypeBean> coverTypeBeans) {
		this.coverTypeBeans = coverTypeBeans;
	}
	
	public List<IclubPropertyBean> getPropertyBeans() {
		if (propertyBeans == null) {
			propertyBeans = new ArrayList<IclubPropertyBean>();
		}
		return propertyBeans;
	}
	
	public void setPropertyBeans(List<IclubPropertyBean> propertyBeans) {
		this.propertyBeans = propertyBeans;
	}
	
	public boolean isShowVehAddPanel() {
		return showVehAddPanel;
	}
	
	public void setShowVehAddPanel(boolean showVehAddPanel) {
		this.showVehAddPanel = showVehAddPanel;
	}
	
	public boolean isShowVehModPanel() {
		return showVehModPanel;
	}
	
	public void setShowVehModPanel(boolean showVehModPanel) {
		this.showVehModPanel = showVehModPanel;
	}
	
	public boolean isShowProAddPanel() {
		return showProAddPanel;
	}
	
	public void setShowProAddPanel(boolean showProAddPanel) {
		this.showProAddPanel = showProAddPanel;
	}
	
	public boolean isShowProModPanel() {
		return showProModPanel;
	}
	
	public void setShowProModPanel(boolean showProModPanel) {
		this.showProModPanel = showProModPanel;
	}
	
	public String getVehAddress() {
		return vehAddress;
	}
	
	public void setVehAddress(String vehAddress) {
		this.vehAddress = vehAddress;
	}
	
	public String getProAddress() {
		return proAddress;
	}
	
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	
	public boolean isProfileTabFlag() {
		
		if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) != null && IclubWebHelper.getObjectIntoSession("googlelogin") == null) {
			profileTabFlag = false;
		} else {
			profileTabFlag = true;
		}
		return profileTabFlag;
	}
	
	public void setProfileTabFlag(boolean profileTabFlag) {
		this.profileTabFlag = profileTabFlag;
	}
	
	public List<IclubPropertyItemBean> getPropertyItemBeans() {
		if (propertyItemBeans == null) {
			propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		}
		return propertyItemBeans;
	}
	
	public void setPropertyItemBeans(List<IclubPropertyItemBean> propertyItemBeans) {
		this.propertyItemBeans = propertyItemBeans;
	}
	
	public IclubPropertyItemBean getPropertyItemBean() {
		if (propertyItemBean == null) {
			propertyItemBean = new IclubPropertyItemBean();
		}
		return propertyItemBean;
	}
	
	public void setPropertyItemBean(IclubPropertyItemBean propertyItemBean) {
		this.propertyItemBean = propertyItemBean;
	}
	
	public boolean isShowProItemAddPanel() {
		return showProItemAddPanel;
	}
	
	public void setShowProItemAddPanel(boolean showProItemAddPanel) {
		this.showProItemAddPanel = showProItemAddPanel;
	}
	
	public Map<String, List<IclubPropertyItemBean>> getPropertyItemBeansMap() {
		if (propertyItemBeansMap == null) {
			propertyItemBeansMap = new TreeMap<String, List<IclubPropertyItemBean>>();
		}
		return propertyItemBeansMap;
	}
	
	public void setPropertyItemBeansMap(Map<String, List<IclubPropertyItemBean>> propertyItemBeansMap) {
		this.propertyItemBeansMap = propertyItemBeansMap;
	}
	
	public IclubQuoteBean getQuoteBean() {
		if (quoteBean == null) {
			quoteBean = new IclubQuoteBean();
		}
		
		return quoteBean;
	}
	
	public void setQuoteBean(IclubQuoteBean quoteBean) {
		this.quoteBean = quoteBean;
	}
	
	public List<IclubVehicleMasterBean> getVehicleMasters() {
		
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "list");
		Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
		client.close();
		vehicleMasters = new ArrayList<IclubVehicleMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehicleMasterModel model : models) {
				IclubVehicleMasterBean bean = IclubVehicleMasterTrans.fromWStoUI(model);
				
				vehicleMasters.add(bean);
			}
		}
		
		return vehicleMasters;
	}
	
	public void setVehicleMasters(List<IclubVehicleMasterBean> vehicleMasters) {
		this.vehicleMasters = vehicleMasters;
	}
	
	public List<IclubInsurerMasterBean> getInsurerMasterBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(IM_BASE_URL + "list");
		Collection<? extends IclubInsurerMasterModel> models = new ArrayList<IclubInsurerMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsurerMasterModel.class));
		client.close();
		insurerMasterBeans = new ArrayList<IclubInsurerMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsurerMasterModel model : models) {
				IclubInsurerMasterBean bean = IclubInsurerMasterTrans.fromWStoUI(model);
				
				insurerMasterBeans.add(bean);
			}
		}
		
		return insurerMasterBeans;
	}
	
	public void setInsurerMasterBeans(List<IclubInsurerMasterBean> insurerMasterBean) {
		this.insurerMasterBeans = insurerMasterBean;
	}
	
}
