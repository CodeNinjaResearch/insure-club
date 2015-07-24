package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;
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
import za.co.iclub.pss.web.bean.IclubAccountBean;
import za.co.iclub.pss.web.bean.IclubAccountTypeBean;
import za.co.iclub.pss.web.bean.IclubBankMasterBean;
import za.co.iclub.pss.web.bean.IclubBarTypeBean;
import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubCountryCodeBean;
import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubEntityTypeBean;
import za.co.iclub.pss.web.bean.IclubExtrasBean;
import za.co.iclub.pss.web.bean.IclubFieldBean;
import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubOccupationBean;
import za.co.iclub.pss.web.bean.IclubOccupiedStatusBean;
import za.co.iclub.pss.web.bean.IclubOwnerTypeBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPropUsageTypeBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPropertyItemBean;
import za.co.iclub.pss.web.bean.IclubPropertyTypeBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.bean.IclubRateEngineBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.bean.IclubRoofTypeBean;
import za.co.iclub.pss.web.bean.IclubSecurityDeviceBean;
import za.co.iclub.pss.web.bean.IclubVehSecTypeBean;
import za.co.iclub.pss.web.bean.IclubVehUsageTypeBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.bean.IclubVehicleMasterBean;
import za.co.iclub.pss.web.bean.IclubWallTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.IclubAccountModel;
import za.co.iclub.pss.ws.model.IclubAccountTypeModel;
import za.co.iclub.pss.ws.model.IclubBankMasterModel;
import za.co.iclub.pss.ws.model.IclubBarTypeModel;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubConfigModel;
import za.co.iclub.pss.ws.model.IclubCountryCodeModel;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubEntityTypeModel;
import za.co.iclub.pss.ws.model.IclubExtrasModel;
import za.co.iclub.pss.ws.model.IclubFieldModel;
import za.co.iclub.pss.ws.model.IclubFullQuoteRequest;
import za.co.iclub.pss.ws.model.IclubFullQuoteResponse;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubOccupationModel;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
import za.co.iclub.pss.ws.model.IclubOwnerTypeModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPropUsageTypeModel;
import za.co.iclub.pss.ws.model.IclubPropertyItemModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubPropertyTypeModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.IclubRateEngineModel;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
import za.co.iclub.pss.ws.model.IclubSecurityDeviceModel;
import za.co.iclub.pss.ws.model.IclubVehSecTypeModel;
import za.co.iclub.pss.ws.model.IclubVehUsageTypeModel;
import za.co.iclub.pss.ws.model.IclubVehicleMasterModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.IclubWallTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubFullQuoteController")
@SessionScoped
public class IclubFullQuoteController implements Serializable {

	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubFullQuoteController.class);
	private static final String PUR_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropUsageTypeService/";
	private static final String VEHU_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehUsageTypeService/";
	private static final String RE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateEngineService/";
	private static final String PER_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private static final String VM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleMasterService/";
	private static final String V_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String D_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDriverService/";
	private static final String LIC_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLicenseCodeService/";
	private static final String WT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubWallTypeService/";
	private static final String ROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRoofTypeService/";
	private static final String RAT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateTypeService/";
	private static final String MS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubIdTypeService/";
	private static final String SM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehSecTypeService/";
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String SD_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityDeviceService/";
	private static final String EXTS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityDeviceService/";
	private static final String BT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBarTypeService/";
	private static final String PROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyTypeService/";
	private static final String OCCS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupiedStatusService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String PROI_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyItemService/";
	private static final String CT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";
	private static final String CLM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private static final String FQUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubFullQuoteService/";
	private static final String ACC_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String OWNT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOwnerTypeService/";
	private static final String ACCT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountTypeService/";
	private static final String BNKM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBankMasterService/";
	private static final String OCN_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupationService/";
	private static final String CCDE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCountryCodeService/";
	private static final String CONF_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubConfigService/";
	private static final String FD_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubFieldService/";
	private static final String ET_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubEntityTypeService/";
	private List<IclubVehSecTypeBean> securityMasterBeans;

	private List<IclubOccupationBean> occupationBeans;

	private List<IclubCountryCodeBean> countryCodeBeans;

	private List<IclubBankMasterBean> bankMasterBeans;

	private List<IclubAccountTypeBean> accountTypeBeans;

	private List<IclubOwnerTypeBean> ownerTypeBeans;

	private List<IclubAccessTypeBean> accessTypeBeans;

	private List<IclubSecurityDeviceBean> securityDeviceBeans;

	private List<IclubCoverTypeBean> coverTypeBeans;

	private List<IclubClaimStatusBean> claimStatusBeans;

	private List<IclubOccupiedStatusBean> occupiedStatusBeans;

	private List<IclubBarTypeBean> barTypeBeans;

	private List<String> bankNames;

	private Map<String, String> debitDates;

	private Map<String, String> debitMonths;

	private String vehCoverType;

	private String bankName;

	private Double genPremium;

	private boolean termsAndConditionFlag;

	private String quoteId;

	private MapModel draggableModelPer;
	private Marker markerPer;
	private String centerGeoMapPer = "36.890257,30.707417";

	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "36.890257,30.707417";

	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "36.890257,30.707417";

	private MapModel draggableModelVehDd;
	private Marker markerVehDd;
	private String centerGeoMapVehDd = "36.890257,30.707417";

	private List<IclubPropertyTypeBean> propertyTypeBeans;

	private IclubExtrasBean extrasBean;

	private List<String> vmMakes;

	private IclubVehicleMasterBean vehicleMasterBean;

	private IclubPersonBean personBean;

	private IclubQuoteBean quoteBean;

	private IclubPropertyBean propertyBean;

	private List<IclubMaritialStatusBean> maritialStatusBeans;

	private List<IclubIdTypeBean> idTypeBeans;

	private List<IclubVehicleMasterBean> vBeans;

	private List<IclubVehUsageTypeBean> vehUsageTypeBeans;

	private List<IclubPropUsageTypeBean> pPropUsageTypeBeans;

	private List<IclubLicenseCodeBean> licenseCodeBeans;

	private List<IclubWallTypeBean> wallTypeBeans;

	private List<IclubRoofTypeBean> roofTypeBeans;

	private IclubDriverBean driverBean;

	private List<String> years;

	private String sessionUserId;

	private IclubVehicleBean vehicleBean;

	private IclubClaimBean claimBean;

	private IclubAccountBean accountBean;

	private String vmMake;

	private String debitDate;

	private String debitMonth;

	private List<IclubInsuranceItemBean> vehicleIItemBeans;

	// private IclubInsuranceItemBean propertyIItemBean;
	private List<IclubPropertyItemBean> propertyItemBeans;
	private IclubPropertyItemBean propertyItemBean;

	private IclubPolicyBean policyBean;

	private ResourceBundle labelBundle;

	private boolean showVehAddPanel;
	private boolean showVehModPanel;
	private boolean showProAddPanel;
	private boolean showProModPanel;
	private String vehAddress;
	private String proAddress;
	private List<IclubVehicleBean> vehicleBeans;
	private List<IclubPropertyBean> propertyBeans;
	private Map<String, List<IclubPropertyItemBean>> propertyItemBeansMap;

	private Map<String, Integer> noOfCompYrs;
	private Map<String, Integer> noClaimYrs;
	private boolean showProItemAddPanel;
	private List<IclubVehicleMasterBean> vehicleMasters;

	@PostConstruct
	public void init() {
		draggableModelPer = new DefaultMapModel();
		draggableModelPro = new DefaultMapModel();
		draggableModelVeh = new DefaultMapModel();

	}

	public void showVehAddPanel() {
		showVehAddPanel = true;
		showVehModPanel = false;
		vehicleBean = new IclubVehicleBean();
	}

	public void clearVehForm() {
		showVehAddPanel = false;
		showVehModPanel = false;
		vehAddress = "";
		draggableModelVeh = new DefaultMapModel();
		vehicleBean = new IclubVehicleBean();
		vBeans = new ArrayList<IclubVehicleMasterBean>();
	}

	public void showVehModPanel() {
		showVehAddPanel = false;
		showVehModPanel = true;
		setVmMakeAndMode();
		draggableModelVeh = new DefaultMapModel();
		if (vehicleBean != null && vehicleBean.getVDdLat() != null && vehicleBean.getVDdLong() != null) {
			centerGeoMapVeh = vehicleBean.getVDdLat() + "," + vehicleBean.getVDdLong();
			LatLng coord = new LatLng(vehicleBean.getVDdLat(), vehicleBean.getVDdLong());
			Marker marker = new Marker(coord, "");
			marker.setDraggable(true);
			draggableModelVeh.addOverlay(marker);
		}
	}

	public void showProAddPanel() {
		showProAddPanel = true;
		showProModPanel = false;
		propertyBean = new IclubPropertyBean();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
	}

	public void clearProForm() {
		showProAddPanel = false;
		showProModPanel = false;
		proAddress = "";
		draggableModelPro = new DefaultMapModel();
		propertyBean = new IclubPropertyBean();
	}

	public void showProItemAddPanel() {
		showProItemAddPanel = true;
		propertyItemBean = new IclubPropertyItemBean();
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

	public void showProModPanel() {
		showProAddPanel = false;
		showProModPanel = true;
		draggableModelPro = new DefaultMapModel();

		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		try {
			propertyItemBeans = getPropertyItemBeansMap().get(propertyBean.getPId());
			// for (IclubPropertyItemBean propertyItemBean : propertyItemBeans)
			// {
			// propertyBean.setPContentCost(propertyBean.getPContentCost() !=
			// null ? propertyBean.getPContentCost() +
			// propertyItemBean.getPiValue() : propertyItemBean.getPiValue() !=
			// null ? propertyItemBean.getPiValue() : 0.0);
			// }
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

	public void addIclubPropertyItem() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubPropertyItem");
		try {

			if (validateProItemForm(true)) {
				propertyItemBean.setPiId(UUID.randomUUID().toString());
				propertyBean.setPContentCost(propertyBean.getPContentCost() != null ? propertyBean.getPContentCost() + propertyItemBean.getPiValue() : propertyItemBean.getPiValue() != null ? propertyItemBean.getPiValue() : 0.0);
				propertyItemBean.setModFlag(true);
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

	public void clearProItemForm() {
		showProItemAddPanel = false;
		propertyItemBean = new IclubPropertyItemBean();
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

	public void modIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubProperty");
		try {
			if (validateProForm(false)) {
				IclubPropertyModel model = new IclubPropertyModel();

				model.setPId(propertyBean.getPId());
				model.setPCrtdDt(new Date(System.currentTimeMillis()));
				model.setPEstValue(propertyBean.getPEstValue());
				model.setPSecGatesYn(propertyBean.getPSecGatesYn());
				model.setPNorobberyYn(propertyBean.getPNorobberyYn());
				model.setPCompYn(propertyBean.getPCompYn());
				model.setPRentFurYn(propertyBean.getPRentFurYn());
				model.setPNoclaimYrs(propertyBean.getPNoclaimYrs());
				model.setPPostalCd(propertyBean.getPPostalCd());
				model.setPLong(propertyBean.getPLong());
				model.setPLat(propertyBean.getPLat());
				model.setPAddress(propertyBean.getPAddress());
				model.setPRegNum(propertyBean.getPRegNum());
				model.setIclubCoverType(propertyBean.getIclubCoverType());
				model.setIclubPropUsageType(propertyBean.getIclubPropUsageType());
				model.setIclubOccupiedStatus(propertyBean.getIclubOccupiedStatus());
				model.setIclubPropertyType(propertyBean.getIclubPropertyType());
				model.setIclubWallType(propertyBean.getIclubWallType());
				model.setIclubAccessType(propertyBean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(propertyBean.getIclubBarType());
				model.setPThatchType(propertyBean.getPThatchType());
				model.setIclubRoofType(propertyBean.getIclubRoofType());

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

	public boolean validateProForm(boolean flag) {
		boolean ret = true;
		if (propertyBean.getPRegNum() == null || propertyBean.getPRegNum().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Premium Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPAddress() == null || propertyBean.getPAddress().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Address Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPLat() == null || propertyBean.getPLong() == null) {
			IclubWebHelper.addMessage("Please Select Location", FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (propertyBean.getPPostalCd() == null) {
			IclubWebHelper.addMessage(("Postel Code Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getIclubCoverType() == null) {
			IclubWebHelper.addMessage(("Please Select Cover Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getIclubPropUsageType() == null) {
			IclubWebHelper.addMessage(("Please Select PropUsage Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPNoclaimYrs() == null) {
			IclubWebHelper.addMessage(("Noclaim Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
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
		if (propertyBean.getIclubBarType() == null) {
			IclubWebHelper.addMessage(("Please Select Bar Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getIclubAccessType() == null) {
			IclubWebHelper.addMessage(("Please Select Access Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPRentFurYn() == null || propertyBean.getPRentFurYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("RentFur Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPCompYn() == null || propertyBean.getPCompYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Comp Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPSecGatesYn() == null || propertyBean.getPSecGatesYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Sec Gates Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (propertyBean.getPEstValue() == null) {
			IclubWebHelper.addMessage(("Est value Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (propertyBean.getPNorobberyYn() == null || propertyBean.getPNorobberyYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("No Robbery Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}

	public void addIclubVehicle() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubVehicle");
		try {
			if (validateVehForm(true)) {
				IclubVehicleModel model = new IclubVehicleModel();

				// vehicleBean.setVId(UUID.randomUUID().toString());
				vehicleBean.setVCrtdDt(new Date(System.currentTimeMillis()));
				vehicleBean.setIclubPerson(getSessionUserId());
				model.setVId(vehicleBean.getVId());
				model.setVOwner(vehicleBean.getVOwner());
				model.setVGearLockYn(vehicleBean.getVGearLockYn());
				model.setVImmYn(vehicleBean.getVImmYn());
				model.setVConcessReason(vehicleBean.getVConcessReason());
				model.setVConcessPrct(vehicleBean.getVConcessPrct());
				model.setVInsuredValue(vehicleBean.getVInsuredValue());
				model.setVYear(vehicleBean.getVYear());
				model.setVDdLong(vehicleBean.getVDdLong());
				model.setVDdLat(vehicleBean.getVDdLat());
				model.setVDdArea(vehicleBean.getVDdArea());
				model.setVOnLong(vehicleBean.getVOnLong());
				model.setVOnLat(vehicleBean.getVOnLat());
				model.setVOnArea(vehicleBean.getVOnArea());
				model.setVCompYrs(vehicleBean.getVCompYrs());
				model.setVOdometer(vehicleBean.getVOdometer());
				model.setVCrtdDt(vehicleBean.getVCrtdDt());
				model.setVRegNum(vehicleBean.getVRegNum());
				model.setVEngineNr(vehicleBean.getVEngineNr());
				model.setVVin(vehicleBean.getVVin());
				model.setVNoclaimYrs(vehicleBean.getVNoclaimYrs());
				model.setIclubVehicleMaster(vehicleBean.getIclubVehicleMaster());
				model.setIclubVehUsageType(vehicleBean.getIclubVehUsageType());
				model.setIclubVehSecType(vehicleBean.getIclubVehSecType());
				model.setIclubPerson(vehicleBean.getIclubPerson());
				model.setIclubDriver(vehicleBean.getIclubDriver());
				model.setIclubSecurityDevice(vehicleBean.getIclubSecurityDevice());
				model.setIclubAccessTypeByVDdAccessTypeId(vehicleBean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(vehicleBean.getIclubAccessTypeByVOnAccessTypeId());

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

				IclubVehicleModel model = new IclubVehicleModel();

				model.setVId(vehicleBean.getVId());
				vehicleBean.setVCrtdDt(new Date(System.currentTimeMillis()));
				vehicleBean.setIclubPerson(getSessionUserId());
				model.setVOwner(vehicleBean.getVOwner());
				model.setVGearLockYn(vehicleBean.getVGearLockYn());
				model.setVImmYn(vehicleBean.getVImmYn());
				model.setVConcessReason(vehicleBean.getVConcessReason());
				model.setVConcessPrct(vehicleBean.getVConcessPrct());
				model.setVInsuredValue(vehicleBean.getVInsuredValue());
				model.setVYear(vehicleBean.getVYear());
				model.setVDdLong(vehicleBean.getVDdLong());
				model.setVDdLat(vehicleBean.getVDdLat());
				model.setVDdArea(vehicleBean.getVDdArea());
				model.setVOnLong(vehicleBean.getVOnLong());
				model.setVOnLat(vehicleBean.getVOnLat());
				model.setVOnArea(vehicleBean.getVOnArea());
				model.setVCompYrs(vehicleBean.getVCompYrs());
				model.setVOdometer(vehicleBean.getVOdometer());
				model.setVCrtdDt(vehicleBean.getVCrtdDt());
				model.setVRegNum(vehicleBean.getVRegNum());
				model.setVEngineNr(vehicleBean.getVEngineNr());
				model.setVVin(vehicleBean.getVVin());
				model.setVNoclaimYrs(vehicleBean.getVNoclaimYrs());
				model.setIclubVehicleMaster(vehicleBean.getIclubVehicleMaster());
				model.setIclubVehUsageType(vehicleBean.getIclubVehUsageType());
				model.setIclubVehSecType(vehicleBean.getIclubVehSecType());
				model.setIclubPerson(vehicleBean.getIclubPerson());
				model.setIclubDriver(vehicleBean.getIclubDriver());
				model.setIclubSecurityDevice(vehicleBean.getIclubSecurityDevice());
				model.setIclubAccessTypeByVDdAccessTypeId(vehicleBean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(vehicleBean.getIclubAccessTypeByVOnAccessTypeId());

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

		if (vehicleBean.getVOdometer() == null) {
			IclubWebHelper.addMessage(("OdoMeter Cannot be empty"), FacesMessage.SEVERITY_ERROR);
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
		if (vehicleBean.getIclubAccessTypeByVDdAccessTypeId() == null) {
			IclubWebHelper.addMessage(("Please Select Dd AccessType"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVYear() == null) {
			IclubWebHelper.addMessage(("Year Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (vehicleBean.getVImmYn() == null || vehicleBean.getVImmYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Imn Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVGearLockYn() == null || vehicleBean.getVGearLockYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gear Lock Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getIclubVehSecType() == null) {
			IclubWebHelper.addMessage(("Please Select Security Master"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getIclubVehUsageType() == null) {
			IclubWebHelper.addMessage(("Please Select Vehicle Usage Type"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVNoclaimYrs() == null) {
			IclubWebHelper.addMessage(("No Claim Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (vehicleBean.getVCompYrs() == null) {
			IclubWebHelper.addMessage(("Comp Years Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVVin() == null || vehicleBean.getVVin().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Vin Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVEngineNr() == null || vehicleBean.getVEngineNr().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("EngineNr Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVRegNum() == null || vehicleBean.getVRegNum().toString().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("RegNum Cannot be empty"), FacesMessage.SEVERITY_ERROR);
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

	public MapModel getDraggableModelVehDd() {
		return draggableModelVehDd;
	}

	public String getCenterGeoMapVehDd() {
		return centerGeoMapVehDd;
	}

	public void onMarkerDragPer(MarkerDragEvent event) {
		markerPer = event.getMarker();
		personBean.setPLat(markerPer.getLatlng().getLat());
		personBean.setPLong(markerPer.getLatlng().getLng());
		personBean.setPAddress(markerPer.getTitle());
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
		personBean.setPLat(markerPer.getLatlng().getLat());
		personBean.setPLong(markerPer.getLatlng().getLng());
		personBean.setPAddress(markerPer.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPer.getTitle()));
	}

	public void onMarkerDragPro(MarkerDragEvent event) {
		markerPro = event.getMarker();
		propertyBean.setPLat(markerPro.getLatlng().getLat());
		propertyBean.setPLong(markerPro.getLatlng().getLng());
		propertyBean.setPAddress(markerPro.getTitle());

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
		propertyBean.setPLat(markerPro.getLatlng().getLat());
		propertyBean.setPLong(markerPro.getLatlng().getLng());
		propertyBean.setPAddress(markerPro.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}

	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		vehicleBean.setVOnLat(markerVeh.getLatlng().getLat());
		vehicleBean.setVOnLong(markerVeh.getLatlng().getLng());

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
		vehicleBean.setVOnLat(markerVeh.getLatlng().getLat());
		vehicleBean.setVOnLong(markerVeh.getLatlng().getLng());
		vehicleBean.setVOnArea(markerVeh.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVeh.getTitle()));
	}

	public void onMarkerDragVehDd(MarkerDragEvent event) {
		markerVehDd = event.getMarker();
		vehicleBean.setVDdLat(markerVehDd.getLatlng().getLat());
		vehicleBean.setVDdLong(markerVehDd.getLatlng().getLng());
		vehicleBean.setVDdArea(markerVehDd.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVehDd.getLatlng().getLat() + ", Lng:" + markerVehDd.getLatlng().getLng()));
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
		markerVehDd = (Marker) event.getOverlay();
		vehicleBean.setVDdLat(markerVehDd.getLatlng().getLat());
		vehicleBean.setVDdLong(markerVehDd.getLatlng().getLng());
		vehicleBean.setVDdArea(markerVehDd.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVehDd.getTitle()));
	}

	public void bankNameValueChangeListener() {
		if (bankName != null) {
			loadBankMasterBeans(bankName);
		} else {
			if (bankMasterBeans != null) {
				bankMasterBeans.clear();
			}

		}
	}

	public void loadBankMasterBeans(String bankName) {
		WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "get/bankName/" + bankName);
		Collection<? extends IclubBankMasterModel> models = new ArrayList<IclubBankMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
		client.close();
		bankMasterBeans = new ArrayList<IclubBankMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubBankMasterModel model : models) {

				IclubBankMasterBean bean = new IclubBankMasterBean();

				bean.setBmId(model.getBmId());
				bean.setBmBankName(model.getBmBankName());
				bean.setBmBankCode(model.getBmBankCode());
				bean.setBmBranchName(model.getBmBranchName());
				bean.setBmBranchCode(model.getBmBranchCode());
				bean.setBmBranchAddress(model.getBmBranchAddress());
				bean.setBmBranchLat(model.getBmBranchLat());
				bean.setBmBranchLong(model.getBmBranchLong());
				bean.setBmCrtdDt(model.getBmCrtdDt());
				bean.setIclubPerson(model.getIclubPerson());
				if (model.getIclubAccounts() != null && model.getIclubAccounts().length > 0) {

					String[] accounts = new String[model.getIclubAccounts().length];

					int i = 0;
					for (String account : model.getIclubAccounts()) {
						accounts[i] = account;
					}
					bean.setIclubAccounts(accounts);
				}

				bankMasterBeans.add(bean);
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

	public void vmModelValueChangeListener() {
		if (vehicleBean != null && vehicleBean.getIclubVehicleMaster() != null) {

			loadYears(vehicleBean.getIclubVehicleMaster().toString());

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

	public String saveFullQuoteDetails() {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: saveQuickQuoteDetails");
		try {
			if (validateForm(true)) {
				IclubFullQuoteRequest request = new IclubFullQuoteRequest();
				WebClient client = IclubWebHelper.createCustomClient(FQUT_BASE_URL + "createQuote/");
				IclubPersonModel personModel = addPerson(personBean);
				genPremium = quoteBean.getQGenPremium();
				List<IclubPropertyModel> propertyModels = addPropertiy(propertyBeans, quoteBean);
				IclubDriverModel driverModel = addDriver(driverBean, personBean, quoteBean);
				IclubQuoteModel quoteModel = addQuote(quoteBean, personBean);
				List<IclubVehicleModel> vehicleModels = addVehicle(vehicleBeans, driverModel, quoteModel);
				List<IclubPropertyItemModel> propertyItemModels = getPropertyItemModels(propertyItemBeansMap);
				request.setIclubDriverModel(driverModel);
				request.setIclubPersonModel(personModel);
				request.setIclubVehicleModels(vehicleModels);
				request.setIclubQuoteModel(quoteModel);
				request.setIclubPropertyModels(propertyModels);
				request.setIclubPropertyItemModels(propertyItemModels);
				IclubFullQuoteResponse response = client.accept(MediaType.APPLICATION_JSON).post(request, IclubFullQuoteResponse.class);
				genPremium = response.getGeneratedPremium();
				IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);

				return "fqs";
			} else {
				IclubWebHelper.addMessage("Fail :: ", FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			e.printStackTrace();
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}

	public String registerActionListener() {

		try {
			IclubQuoteModel quoteModel = addQuote(quoteBean, personBean);
			IclubAccountModel accountModel = addAccount(accountBean, personBean, quoteModel);
			IclubPolicyModel policyModel = addPolicy(policyBean, quoteModel, accountModel);
			IclubClaimModel claimModel = addClaim(getClaimBean(), policyModel);
			IclubFullQuoteRequest request = new IclubFullQuoteRequest();
			request.setIclubAccountModel(accountModel);
			request.setIclubClaimModel(claimModel);
			request.setIclubPolicyModel(policyModel);
			request.setIclubQuoteModel(quoteModel);
			WebClient client = IclubWebHelper.createCustomClient(FQUT_BASE_URL + "createPolicy/");
			IclubFullQuoteResponse response = client.accept(MediaType.APPLICATION_JSON).post(request, IclubFullQuoteResponse.class);
			genPremium = response.getGeneratedPremium();
			IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);
			clearForm();
			return "pdash";
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Load Person Fail ::" + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}

	public void clearForm() {
		claimBean = new IclubClaimBean();
		quoteBean = new IclubQuoteBean();
		personBean = new IclubPersonBean();
		vehicleBean = new IclubVehicleBean();
		propertyBean = new IclubPropertyBean();
		accountBean = new IclubAccountBean();
		driverBean = new IclubDriverBean();
		debitDate = "";
		debitMonth = "";
	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		/*
		 * if (personBean.getPFName() == null ||
		 * personBean.getPFName().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage
		 * (getLabelBundle().getString("First Name Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (personBean.getPLName() == null ||
		 * personBean.getPLName().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Last Name Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (personBean.getPMobile() == null ||
		 * personBean.getPMobile().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Mobile Number Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (personBean.getPGender() == null ||
		 * personBean.getPGender().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Gender Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (personBean.getPIdNum() == null ||
		 * personBean.getPIdNum().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Id Number Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (personBean.getIclubIdType() == null) {
		 * IclubWebHelper.addMessage(("Please Select ID Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (personBean.getPIsPensioner() == null ||
		 * personBean.getPIsPensioner().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Please Select Pensioner"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (personBean.getPDob() == null) {
		 * IclubWebHelper.addMessage(("Please Select DOB"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } else if
		 * (IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()) <=
		 * 18) { IclubWebHelper.addMessage(("You must be over 18 years"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 */

		/*
		 * if (propertyBean.getPRegNum() == null ||
		 * propertyBean.getPRegNum().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Premium Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPAddress() == null ||
		 * propertyBean.getPAddress().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Address Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPLat() == null || propertyBean.getPLong() == null) {
		 * IclubWebHelper.addMessage("Please Select Location",
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (propertyBean.getPPostalCd() == null) {
		 * IclubWebHelper.addMessage(("Postel Code Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubCoverType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Cover Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubPropUsageType() == null) {
		 * IclubWebHelper.addMessage(("Please Select PropUsage Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPNoclaimYrs() == null) {
		 * IclubWebHelper.addMessage(("Noclaim Years Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (propertyBean.getIclubWallType() == null) {
		 * IclubWebHelper.addMessage(("Please Select WallType"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubRoofType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Roof Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubThatchType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Thatch Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubBarType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Bar Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getIclubAccessType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Access Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPRentFurYn() == null ||
		 * propertyBean.getPRentFurYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("RentFur Yn Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPCompYn() == null ||
		 * propertyBean.getPCompYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Comp Yn Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPSecGatesYn() == null ||
		 * propertyBean.getPSecGatesYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Sec Gates Yn Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (propertyBean.getPEstValue() == null) {
		 * IclubWebHelper.addMessage(("Est value Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (propertyBean.getPNorobberyYn() == null ||
		 * propertyBean.getPNorobberyYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("No Robbery Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 */
		/*
		 * if (vehicleBean.getIclubVehicleMaster() == null) {
		 * IclubWebHelper.addMessage(("Please Select Vm Make and Model"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (vehicleBean.getVOdometer() == null) {
		 * IclubWebHelper.addMessage(("Odo Meter Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVOnArea() == null ||
		 * vehicleBean.getVOnArea().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("On Area Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVOnLat() == null || vehicleBean.getVOnLong() == null)
		 * { IclubWebHelper.addMessage(("Please Select Location"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getIclubAccessTypeByVOnAccessTypeId() == null) {
		 * IclubWebHelper.addMessage(("Please Select On AccessType"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getIclubAccessTypeByVDdAccessTypeId() == null) {
		 * IclubWebHelper.addMessage(("Please Select Dd AccessType"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVYear() == null) {
		 * IclubWebHelper.addMessage(("Year Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getIclubDriver() == null) {
		 * IclubWebHelper.addMessage(("Please Select Driver"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVImmYn() == null ||
		 * vehicleBean.getVImmYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Imn Yn Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVGearLockYn() == null ||
		 * vehicleBean.getVGearLockYn().trim().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Gear Lock Yn Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getIclubVehSecType() == null) {
		 * IclubWebHelper.addMessage(("Please Select Security Master"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getIclubPropUsageType() == null) {
		 * IclubWebHelper.addMessage(("Please Select PropUsage Type"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVNoclaimYrs() == null) {
		 * IclubWebHelper.addMessage(("No Claim Years Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 * 
		 * if (vehicleBean.getVCompYrs() == null) {
		 * IclubWebHelper.addMessage(("Comp Years Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVVin() == null ||
		 * vehicleBean.getVVin().toString().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("Vin Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVEngineNr() == null ||
		 * vehicleBean.getVEngineNr().toString().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("EngineNr Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; } if
		 * (vehicleBean.getVRegNum() == null ||
		 * vehicleBean.getVRegNum().toString().equalsIgnoreCase("")) {
		 * IclubWebHelper.addMessage(("RegNum Cannot be empty"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 */

		return ret;
	}

	public List<IclubPropertyItemModel> getPropertyItemModels(Map<String, List<IclubPropertyItemBean>> propertyItemModelMap) {
		List<IclubPropertyItemModel> propertyItemModels = new ArrayList<IclubPropertyItemModel>();

		if (propertyItemModelMap != null && propertyItemModelMap.size() > 0) {
			for (String propId : propertyItemModelMap.keySet()) {

				for (IclubPropertyItemBean bean : propertyItemModelMap.get(propId)) {
					IclubPropertyItemModel model = new IclubPropertyItemModel();
					model.setIclubProperty(propId);
					model.setPiId(bean.getPiId());
					model.setPiValue(bean.getPiValue());
					model.setPiDescripton(bean.getPiDescripton());
					model.setModFlag(bean.isModFlag());
					propertyItemModels.add(model);
				}
			}
		}

		return propertyItemModels;

	}

	public IclubPersonModel addPerson(IclubPersonBean personBean) {

		IclubPersonModel model = new IclubPersonModel();
		model.setPId(personBean.getPId());
		model.setPCrtdDt(personBean.getPCrtdDt());
		model.setPDob(personBean.getPDob());
		model.setPEmail(personBean.getPEmail());
		model.setPFName(personBean.getPFName());
		model.setPIdNum(personBean.getPIdNum());
		model.setPLName(personBean.getPLName());
		model.setPMobile(personBean.getPMobile());
		model.setPAddress(personBean.getPAddress());
		model.setPAge(IclubWebHelper.calculateYearDiff(personBean.getPDob().getTime()));
		model.setPContactPref(personBean.getPContactPref());
		model.setPGender(personBean.getPGender());
		model.setPContactPref(personBean.getPContactPref());
		model.setPIdExpiryDt(personBean.getPIdExpiryDt());
		model.setPInitials(personBean.getPInitials());
		model.setPIsPensioner(personBean.getPIsPensioner());
		model.setPIdIssueCntry(personBean.getPIdIssueCntry() != null ? personBean.getPIdIssueCntry().longValue() : null);
		model.setPLat(personBean.getPLat());
		model.setPLong(personBean.getPLong());
		model.setPOccupation(personBean.getPOccupation());
		model.setPTitle(personBean.getPTitle());
		model.setPZipCd(personBean.getPZipCd());
		model.setIclubIdType(personBean.getIclubIdType());
		model.setIclubPerson(getSessionUserId());
		model.setIclubMaritialStatus(personBean.getIclubMaritialStatus());

		return model;
	}

	public List<IclubVehicleModel> addVehicle(List<IclubVehicleBean> beans, IclubDriverModel driverModel, IclubQuoteModel quoteModel) {

		List<IclubVehicleModel> vehicleModels = new ArrayList<IclubVehicleModel>();
		if (beans != null && beans.size() > 0) {

			for (IclubVehicleBean bean : beans) {
				IclubVehicleModel model = new IclubVehicleModel();

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
				model.setVCompYrs(bean.getVCompYrs());
				model.setVDdArea(bean.getVDdArea());
				model.setVOnLong(bean.getVOnLong());
				model.setVOnLat(bean.getVOnLat());
				model.setVOnArea(bean.getVOnArea());
				model.setVOdometer(bean.getVOdometer());
				model.setVCrtdDt(new Date(System.currentTimeMillis()));
				model.setVRegNum(bean.getVRegNum());
				model.setVEngineNr(bean.getVEngineNr());
				model.setVVin(bean.getVVin());
				model.setVNoclaimYrs(bean.getVNoclaimYrs());
				model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
				model.setIclubVehUsageType(bean.getIclubVehUsageType());
				model.setIclubVehSecType(bean.getIclubVehSecType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubDriver(driverModel.getDId());
				model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
				model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());

				quoteId = quoteModel.getQId();
				vehicleModels.add(model);

			}

		}

		return vehicleModels;

	}

	public IclubDriverModel addDriver(IclubDriverBean bean, IclubPersonBean personModel, IclubQuoteBean quoteModel) {

		IclubDriverModel model = new IclubDriverModel();

		model.setDId(bean.getDId());

		model.setDDob(bean.getDDob());
		model.setDIssueDt(new Date(bean.getDIssueDt().getTime()));
		model.setDLicenseNum(bean.getDLicenseNum());
		model.setDName(bean.getDName());
		model.setDCrtdDt(new Date(System.currentTimeMillis()));
		model.setIclubAccessTypeByDAccessTypeId(bean.getIclubAccessTypeByDAccessTypeId());
		model.setIclubLicenseCode(bean.getIclubLicenseCode());
		model.setIclubMaritialStatus(personModel.getIclubMaritialStatus());
		model.setIclubPersonByDPersonId(personModel.getPId());
		model.setIclubPersonByDCrtdBy(getSessionUserId());

		return model;

	}

	public List<IclubPropertyModel> addPropertiy(List<IclubPropertyBean> beans, IclubQuoteBean quoteModel) {

		List<IclubPropertyModel> propertyModels = new ArrayList<IclubPropertyModel>();

		if (beans != null && beans.size() > 0) {

			for (IclubPropertyBean bean : beans) {
				IclubPropertyModel model = new IclubPropertyModel();
				if (propertyBean != null && propertyBean.getPId() != null) {
					model.setPId(bean.getPId());
					model.setUpdateFlag(true);
				} else {
					model.setPId(UUID.randomUUID().toString());
				}

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
				model.setIclubPropUsageType(bean.getIclubPropUsageType());
				model.setIclubOccupiedStatus(bean.getIclubOccupiedStatus());
				model.setIclubPropertyType(bean.getIclubPropertyType());
				model.setIclubWallType(bean.getIclubWallType());
				model.setIclubAccessType(bean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(bean.getIclubBarType());
				model.setPThatchType(bean.getPThatchType());
				model.setIclubRoofType(bean.getIclubRoofType());
				propertyModels.add(model);
			}

		}

		return propertyModels;

	}

	public ResponseModel addExtras(IclubExtrasBean bean) {
		WebClient client = IclubWebHelper.createCustomClient(EXTS_BASE_URL + "add");

		IclubExtrasModel model = new IclubExtrasModel();
		model.setEId(bean.getEId());
		model.setECrtdDt(new Date(System.currentTimeMillis()));
		model.setEDesc(bean.getEDesc());
		model.setEStatus(bean.getEStatus());
		model.setIclubPerson(getSessionUserId());
		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public IclubClaimModel addClaim(IclubClaimBean bean, IclubPolicyModel policyModel) {
		IclubClaimModel model = new IclubClaimModel();

		model.setCId(bean.getCId());
		model.setCCrtdDt(new Date(System.currentTimeMillis()));
		model.setCValue(bean.getCValue());
		model.setCNumItems(bean.getCNumItems());
		model.setCNumber(getCnumber());
		model.setIclubPolicy(policyModel.getPId());
		model.setIclubClaimStatus(bean.getIclubClaimStatus());
		model.setIclubPerson(getSessionUserId());

		return model;
	}

	public Long getCnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));

	}

	public IclubPolicyModel addPolicy(IclubPolicyBean bean, IclubQuoteModel quoteModel, IclubAccountModel accountModel) {

		IclubPolicyModel model = new IclubPolicyModel();
		model.setPId(UUID.randomUUID().toString());
		model.setPProrataPrm(0.0d);
		model.setPPremium(getGenPremium());
		model.setPNumber(quoteModel.getQNumber());
		model.setPDebitDt(debitDate != null && debitMonth != null ? Integer.parseInt(debitDate + debitMonth) : null);
		model.setPCrtdDt((new Date(System.currentTimeMillis())).toString());
		model.setIclubAccount(accountModel.getAId());
		model.setIclubQuote(quoteModel.getQId());
		model.setIclubPolicyStatus(1l);
		model.setIclubPerson(getSessionUserId());

		return model;

	}

	public IclubAccountModel addAccount(IclubAccountBean bean, IclubPersonBean personModel, IclubQuoteModel quoteModel) {
		IclubAccountModel model = new IclubAccountModel();

		model.setAId(UUID.randomUUID().toString());
		model.setAAccNum(bean.getAAccNum());
		model.setACrtdDt(new Date(System.currentTimeMillis()));
		model.setAOwnerId(personModel.getPId());
		model.setIclubBankMaster(bean.getIclubBankMaster());
		model.setIclubAccountType(bean.getIclubAccountType());
		model.setIclubOwnerType(bean.getIclubOwnerType());
		model.setIclubPerson(getSessionUserId());
		model.setAStatus(bean.getAStatus());

		return model;
	}

	public IclubQuoteModel addQuote(IclubQuoteBean bean, IclubPersonBean personModel) {

		IclubQuoteModel model = new IclubQuoteModel();
		model.setQId(bean.getQId());
		model.setQCrtdDt(new Date(System.currentTimeMillis()));
		model.setQIsMatched("N");
		model.setQPrevPremium(0.0d);
		model.setQValidUntil(new Date(System.currentTimeMillis() + (3 * 24 * 3600)));
		model.setQMobile(personModel.getPMobile());
		model.setQEmail(personModel.getPEmail());
		model.setQGenPremium(getGenPremium());
		model.setQNumItems(2);
		model.setQGenDt(new Date(System.currentTimeMillis()));
		model.setQClaimYn(bean.getQClaimYn());
		model.setQNumber(bean.getQNumber());
		model.setIclubPersonByQCrtdBy(getSessionUserId());
		model.setIclubProductType(1l);
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setIclubCoverType(vehCoverType != null ? new Long(vehCoverType) : null);
		model.setIclubQuoteStatus(2l);
		model.setIclubPersonByQPersonId(personModel.getPId());

		return model;

	}

	public Long getQnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));

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
					String[] properties = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String iclubProperty : model.getIclubVehicles()) {
						properties[i] = iclubProperty;
						i++;
					}
					bean.setIclubVehicles(properties);
				}

				vehUsageTypeBeans.add(bean);
			}
		}
		return vehUsageTypeBeans;
	}

	public void setVehUsageTypeBeans(List<IclubVehUsageTypeBean> vehUsageTypeBeans) {
		this.vehUsageTypeBeans = vehUsageTypeBeans;
	}

	public IclubPersonBean getPersonBean() {
		if (personBean == null) {
			personBean = new IclubPersonBean();
		}
		if (IclubWebHelper.getObjectIntoSession("fullquote") != null) {
			quoteBean = (IclubQuoteBean) IclubWebHelper.getObjectIntoSession("fullquote");

			IclubWebHelper.addObjectIntoSession("fullquote", null);
			driverBean = new IclubDriverBean();
			vehicleBeans = new ArrayList<IclubVehicleBean>();
			extrasBean = new IclubExtrasBean();
			accountBean = new IclubAccountBean();
			propertyBeans = new ArrayList<IclubPropertyBean>();
			claimBean = new IclubClaimBean();
			policyBean = new IclubPolicyBean();
			vehicleIItemBeans = new ArrayList<IclubInsuranceItemBean>();
			if (quoteBean != null && quoteBean.getIclubPersonByQPersonId() != null && !quoteBean.getIclubPersonByQPersonId().trim().equalsIgnoreCase("")) {
				WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + quoteBean.getIclubPersonByQPersonId());

				IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));

				personBean.setPId(model.getPId());
				personBean.setPCrtdDt(model.getPCrtdDt());
				personBean.setPDob(model.getPDob());
				personBean.setPEmail(model.getPEmail());
				personBean.setPFName(model.getPFName());
				personBean.setPIdNum(model.getPIdNum());
				personBean.setPLName(model.getPLName());
				personBean.setPMobile(model.getPMobile());
				personBean.setPAge(model.getPAge());
				personBean.setPAddress(model.getPAddress());
				personBean.setPContactPref(model.getPContactPref());
				personBean.setPGender(model.getPGender());
				personBean.setPContactPref(model.getPContactPref());
				personBean.setPIdExpiryDt(model.getPIdExpiryDt());
				personBean.setPInitials(model.getPInitials());
				personBean.setPIsPensioner(model.getPIsPensioner());
				personBean.setPIdIssueCntry(model.getPIdIssueCntry());
				personBean.setPIdIssueDt(model.getPIdIssueDt());
				personBean.setPLat(model.getPLat());
				personBean.setPLong(model.getPLong());
				personBean.setPOccupation(model.getPOccupation());
				personBean.setPTitle(model.getPTitle());
				personBean.setPZipCd(model.getPZipCd());
				personBean.setIclubIdType(model.getIclubIdType());
				personBean.setIclubPerson(model.getIclubPerson());
				personBean.setIclubMaritialStatus(model.getIclubMaritialStatus());
				if (personBean.getPLat() != null && personBean.getPLong() != null) {
					centerGeoMapPer = personBean.getPLat() + "," + personBean.getPLong();
					LatLng coord = new LatLng(personBean.getPLat(), personBean.getPLong());
					Marker marker = new Marker(coord, "");
					marker.setDraggable(true);
					draggableModelPer.addOverlay(marker);

				}

				client.close();
				vehicleIItemBeans = setInsuranceItemDetails(quoteBean.getQId(), 1l);

				setVehicleDetails(vehicleIItemBeans);

				setPropertyDetails();
				setPolicyDetails();
			}

			IclubWebHelper.addObjectIntoSession("fullquote", null);
		}
		return personBean;
	}

	public void setPropertyDetails() {

		propertyBeans = new ArrayList<IclubPropertyBean>();
		List<IclubInsuranceItemBean> propertyIItemBeans = setInsuranceItemDetails(quoteBean.getQId(), 2l);
		if (propertyIItemBeans != null && propertyIItemBeans.size() > 0) {

			for (IclubInsuranceItemBean propertyIItemBean : propertyIItemBeans) {
				WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + propertyIItemBean.getIiItemId());
				IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));
				client.close();
				if (model != null && model.getPId() != null) {
					IclubPropertyBean propertyBean = new IclubPropertyBean();
					propertyBean.setPId(model.getPId());
					propertyBean.setPCrtdDt(model.getPCrtdDt());
					propertyBean.setPEstValue(model.getPEstValue());
					propertyBean.setPSecGatesYn(model.getPSecGatesYn());
					propertyBean.setPNorobberyYn(model.getPNorobberyYn());
					propertyBean.setPCompYn(model.getPCompYn());
					propertyBean.setPRentFurYn(model.getPRentFurYn());
					propertyBean.setPNoclaimYrs(model.getPNoclaimYrs());
					propertyBean.setPPostalCd(model.getPPostalCd());
					propertyBean.setPLong(model.getPLong());
					propertyBean.setPLat(model.getPLat());
					propertyBean.setPAddress(model.getPAddress());
					propertyBean.setPRegNum(model.getPRegNum());
					propertyBean.setIclubCoverType(model.getIclubCoverType());
					propertyBean.setIclubPropUsageType(model.getIclubPropUsageType());
					propertyBean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
					propertyBean.setIclubPropertyType(model.getIclubPropertyType());
					propertyBean.setIclubWallType(model.getIclubWallType());
					propertyBean.setIclubAccessType(model.getIclubAccessType());
					propertyBean.setIclubPerson(model.getIclubPerson());
					propertyBean.setIclubBarType(model.getIclubBarType());
					propertyBean.setPThatchType(model.getPThatchType());
					propertyBean.setIclubRoofType(model.getIclubRoofType());
					propertyBean.setPReplacementCost(model.getPReplacementCost());
					propertyBean.setPContentCost(model.getPContentCost());
					setIclubPropertyItems(model.getPId());
					propertyBeans.add(propertyBean);

				}

			}
		}
	}

	public void setIclubPropertyItems(String propertyId) {

		WebClient client = IclubWebHelper.createCustomClient(PROI_BASE_URL + "listByProperty/" + propertyId);

		Collection<? extends IclubPropertyItemModel> models = new ArrayList<IclubPropertyItemModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyItemModel.class));
		client.close();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropertyItemModel model : models) {
				IclubPropertyItemBean bean = new IclubPropertyItemBean();
				bean.setIclubProperty(model.getIclubProperty());
				bean.setPiId(model.getPiId());
				bean.setPiValue(model.getPiValue());
				bean.setPiDescripton(model.getPiDescripton());
				bean.setPiCrtdDate(model.getPiCrtdDate());
				propertyItemBeans.add(bean);
			}
			getPropertyItemBeansMap().put(propertyId, propertyItemBeans);
		}

	}

	public void setPolicyDetails() {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "getByQuoteId/" + quoteBean.getQId());

		IclubPolicyModel model = (IclubPolicyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class));

		client.close();
		if (model != null && model.getPId() != null) {
			policyBean = new IclubPolicyBean();
			policyBean.setPId(model.getPId());
			policyBean.setPProrataPrm(model.getPProrataPrm());
			policyBean.setPPremium(model.getPPremium());
			policyBean.setPNumber(model.getPNumber());
			policyBean.setPDebitDt(model.getPDebitDt());
			policyBean.setPCrtdDt(model.getPCrtdDt());
			policyBean.setIclubAccount(model.getIclubAccount());
			policyBean.setIclubQuote(model.getIclubQuote());
			policyBean.setIclubPolicyStatus(model.getIclubPolicyStatus());
			policyBean.setIclubPerson(model.getIclubPerson());
			policyBean.setIclubPolicyStatus(model.getIclubPolicyStatus());

			if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
				String[] claims = new String[model.getIclubClaims().length];
				int i = 0;
				for (String claim : model.getIclubClaims()) {
					claims[i] = claim;
					i++;
				}
				policyBean.setIclubClaims(claims);
			}

			if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
				String[] payments = new String[model.getIclubPayments().length];
				int i = 0;
				for (String payment : model.getIclubPayments()) {
					payments[i] = payment;
					i++;
				}
				policyBean.setIclubClaims(payments);
			}

			setClaimDetails();
			setAccountDetails();
		}

	}

	public void setClaimDetails() {

		WebClient client = IclubWebHelper.createCustomClient(CLM_BASE_URL + "getByPolicyId/" + policyBean.getPId());

		IclubClaimModel model = (IclubClaimModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubClaimModel.class));
		client.close();

		if (model != null && model.getCId() != null) {
			claimBean = new IclubClaimBean();

			claimBean.setCId(model.getCId());
			claimBean.setCCrtdDt(model.getCCrtdDt());
			claimBean.setCValue(model.getCValue());
			claimBean.setCNumItems(model.getCNumItems());
			claimBean.setCNumber(model.getCNumber());
			claimBean.setIclubPolicy(model.getIclubPolicy());
			claimBean.setIclubClaimStatus(model.getIclubClaimStatus());
			claimBean.setIclubPerson(model.getIclubPerson());

			if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
				String[] iclubCohortClaims = model.getIclubCohortClaims();

				claimBean.setIclubCohortClaims(iclubCohortClaims);
			}

			if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
				String[] payments = new String[model.getIclubPayments().length];
				int i = 0;
				for (String payment : model.getIclubPayments()) {
					payments[i] = payment;
					i++;
				}

				claimBean.setIclubPayments(payments);
			}

			if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
				String[] claimItems = new String[model.getIclubClaimItems().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItems()) {
					claimItems[i] = claimItem;
					i++;
				}
				claimBean.setIclubClaimItems(claimItems);
			}

		}
	}

	public void setAccountDetails() {
		WebClient client = IclubWebHelper.createCustomClient(ACC_BASE_URL + "get/" + policyBean.getIclubAccount());

		IclubAccountModel model = (IclubAccountModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubAccountModel.class));

		client.close();
		if (model != null && model.getAId() != null) {

			accountBean = new IclubAccountBean();
			accountBean.setAId(model.getAId());

			accountBean.setAAccNum(model.getAAccNum());
			accountBean.setACrtdDt(model.getACrtdDt());
			accountBean.setAOwnerId(model.getAOwnerId());
			accountBean.setIclubBankMaster(model.getIclubBankMaster());
			accountBean.setIclubAccountType(model.getIclubAccountType());
			accountBean.setIclubOwnerType(model.getIclubOwnerType());
			accountBean.setIclubPerson(model.getIclubPerson());
			accountBean.setAStatus(model.getAStatus());

			if (model.getIclubPolicies() != null && model.getIclubPolicies().length > 0) {
				String[] policies = new String[model.getIclubPolicies().length];
				int i = 0;
				for (String policy : model.getIclubPolicies()) {

					policies[i] = policy;
					i++;
				}
				accountBean.setIclubPolicies(policies);
			}

			if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
				String[] payments = new String[model.getIclubPayments().length];
				int i = 0;
				for (String payment : model.getIclubPayments()) {

					payments[i] = payment;
					i++;
				}
				accountBean.setIclubPayments(payments);
			}

			client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "get/" + accountBean.getIclubBankMaster());
			IclubBankMasterModel bankMastermodel = (IclubBankMasterModel) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
			client.close();

			bankName = bankMastermodel.getBmBankName();
			loadBankMasterBeans(bankName);
		}
	}

	public void setDriverDetails(String driverId) {
		WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "get/" + driverId);

		IclubDriverModel model = (IclubDriverModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubDriverModel.class));
		driverBean = new IclubDriverBean();

		driverBean.setDId(model.getDId());
		driverBean.setDDob(model.getDDob());
		driverBean.setDIssueDt(model.getDIssueDt());
		driverBean.setDLicenseNum(model.getDLicenseNum());
		driverBean.setDName(model.getDName());
		driverBean.setDCrtdDt(model.getDCrtdDt());
		driverBean.setIclubAccessTypeByDAccessTypeId(model.getIclubAccessTypeByDAccessTypeId());
		driverBean.setIclubLicenseCode(model.getIclubLicenseCode());
		driverBean.setIclubMaritialStatus(model.getIclubMaritialStatus());
		driverBean.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId());
		driverBean.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy());

		client.close();

	}

	public void setVehicleDetails(List<IclubInsuranceItemBean> vehilcItemBeans) {

		vehicleBeans = new ArrayList<IclubVehicleBean>();
		if (vehilcItemBeans != null) {
			boolean flag = false;
			for (IclubInsuranceItemBean itemBean : vehilcItemBeans) {
				try {
					WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "get/" + itemBean.getIiItemId());

					IclubVehicleModel model = (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleModel.class));
					client.close();

					IclubVehicleBean vehicleBean = new IclubVehicleBean();

					vehicleBean.setVId(model.getVId());
					vehicleBean.setVOwner(model.getVOwner());
					vehicleBean.setVGearLockYn(model.getVGearLockYn());
					vehicleBean.setVImmYn(model.getVImmYn());
					vehicleBean.setVConcessReason(model.getVConcessReason());
					vehicleBean.setVConcessPrct(model.getVConcessPrct());
					vehicleBean.setVInsuredValue(model.getVInsuredValue());
					vehicleBean.setVYear(model.getVYear());
					vehicleBean.setVDdLong(model.getVDdLong());
					vehicleBean.setVDdLat(model.getVDdLat());
					vehicleBean.setVDdArea(model.getVDdArea());
					vehicleBean.setVOnLong(model.getVOnLong());
					vehicleBean.setVOnLat(model.getVOnLat());
					vehicleBean.setVOnArea(model.getVOnArea());
					vehicleBean.setVOdometer(model.getVOdometer());
					vehicleBean.setVCrtdDt(model.getVCrtdDt());
					vehicleBean.setVRegNum(model.getVRegNum());
					vehicleBean.setVEngineNr(model.getVEngineNr());
					vehicleBean.setVVin(model.getVVin());
					vehicleBean.setVNoclaimYrs(model.getVNoclaimYrs());
					vehicleBean.setIclubVehicleMaster(model.getIclubVehicleMaster());
					vehicleBean.setIclubVehUsageType(model.getIclubVehUsageType());
					vehicleBean.setIclubVehSecType(model.getIclubVehSecType());
					vehicleBean.setIclubPerson(model.getIclubPerson());
					vehicleBean.setIclubDriver(model.getIclubDriver());
					vehicleBean.setIclubSecurityDevice(model.getIclubSecurityDevice());
					vehicleBean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
					vehicleBean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());

					vehicleBeans.add(vehicleBean);
					if (!flag) {

						setDriverDetails(vehicleBean.getIclubDriver());
						flag = true;
					}
				} catch (Exception e) {
					LOGGER.error(e, e);
					IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				}

			}
		}

	}

	public void setVmMakeAndMode() {
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "get/" + vehicleBean.getIclubVehicleMaster());

		IclubVehicleMasterModel model = (IclubVehicleMasterModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleMasterModel.class));
		client.close();
		if (model != null && model.getVmId() != null) {
			vmMake = model.getVmMake();
			loadVmModels(vmMake);
			loadYears(model.getVmId().toString());
		}

	}

	public List<IclubInsuranceItemBean> setInsuranceItemDetails(String quoteId, Long itemTypeId) {

		List<IclubInsuranceItemBean> beans = new ArrayList<IclubInsuranceItemBean>();
		try {
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "listByQuoteIdAndItemTypeId/" + quoteId + "/" + itemTypeId);

			Collection<? extends IclubInsuranceItemModel> models = new ArrayList<IclubInsuranceItemModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
			client.close();

			if (models != null && models.size() > 0) {
				for (IclubInsuranceItemModel model : models) {
					IclubInsuranceItemBean bean = new IclubInsuranceItemBean();
					bean.setIiId(model.getIiId());
					bean.setIiItemId(model.getIiItemId());
					bean.setIiQuoteId(model.getIiQuoteId());
					bean.setIiCrtdDt(model.getIiCrtdDt());
					bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
					bean.setIclubPerson(model.getIclubPerson());

					if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
						String[] claimItems = new String[model.getIclubClaimItems().length];
						int i = 0;
						for (String claimItem : model.getIclubClaimItems()) {
							claimItems[i] = claimItem;
							i++;
						}
						bean.setIclubClaimItems(claimItems);
					}
					beans.add(bean);
				}
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);

		}

		return beans;
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

				IclubLicenseCodeBean bean = new IclubLicenseCodeBean();

				bean.setLcId(model.getLcId());
				bean.setLcCrtdDt(model.getLcCrtdDt());
				bean.setLcDesc(model.getLcDesc());
				bean.setLcStatus(model.getLcStatus());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setLcCategory(model.getLcCategory());
				if (model.getIclubDrivers() != null && model.getIclubDrivers().length > 0) {
					String[] drivers = new String[model.getIclubDrivers().length];
					int i = 0;
					for (String iclubMessage : model.getIclubDrivers()) {
						drivers[i] = iclubMessage;
						i++;
					}
					bean.setIclubDrivers(drivers);
				}

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
				IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
				bean.setMsId(model.getMsId());
				bean.setMsLongDesc(model.getMsLongDesc());
				bean.setMsShortDesc(model.getMsShortDesc());
				bean.setMsStatus(model.getMsStatus());
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
				IclubIdTypeBean bean = new IclubIdTypeBean();
				bean.setItId(model.getItId());
				bean.setItLongDesc(model.getItLongDesc());
				bean.setItShortDesc(model.getItShortDesc());
				bean.setItStatus(model.getItStatus());
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

	public List<IclubVehSecTypeBean> getVehSecTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "list");
		Collection<? extends IclubVehSecTypeModel> models = new ArrayList<IclubVehSecTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehSecTypeModel.class));
		client.close();
		securityMasterBeans = new ArrayList<IclubVehSecTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubVehSecTypeModel model : models) {

				IclubVehSecTypeBean bean = new IclubVehSecTypeBean();

				bean.setVstId(model.getVstId());
				bean.setVstLongDesc(model.getVstLongDesc());
				bean.setVstShortDesc(model.getVstShortDesc());

				bean.setVstStatus(model.getVstStatus());

				securityMasterBeans.add(bean);
			}
		}
		return securityMasterBeans;
	}

	public void setVehSecTypeBeans(List<IclubVehSecTypeBean> securityMasterBeans) {
		this.securityMasterBeans = securityMasterBeans;
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

	public List<IclubSecurityDeviceBean> getSecurityDeviceBeans() {
		WebClient client = IclubWebHelper.createCustomClient(SD_BASE_URL + "list");
		Collection<? extends IclubSecurityDeviceModel> models = new ArrayList<IclubSecurityDeviceModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityDeviceModel.class));
		client.close();
		securityDeviceBeans = new ArrayList<IclubSecurityDeviceBean>();
		if (models != null && models.size() > 0) {
			for (IclubSecurityDeviceModel model : models) {

				IclubSecurityDeviceBean bean = new IclubSecurityDeviceBean();
				bean.setSdId(model.getSdId());
				bean.setSdItemId(model.getSdItemId());
				bean.setSdContractNum(model.getSdContractNum());
				bean.setSdSerNum(model.getSdSerNum());
				bean.setSdCrtdDt(model.getSdCrtdDt());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				bean.setIclubTrackerMaster(model.getIclubTrackerMaster());
				bean.setIclubPerson(model.getIclubPerson());
				if (model.getIclubVehicles() != null && model.getIclubVehicles().length > 0) {
					String[] vehicales = new String[model.getIclubVehicles().length];
					int i = 0;
					for (String vehicle : model.getIclubVehicles()) {
						vehicales[i] = vehicle;

						i++;
					}
					bean.setIclubVehicles(vehicales);
				}
			}
		}
		return securityDeviceBeans;
	}

	public void setSecurityDeviceBeans(List<IclubSecurityDeviceBean> securityDeviceBeans) {
		this.securityDeviceBeans = securityDeviceBeans;
	}

	public IclubExtrasBean getExtrasBean() {
		if (extrasBean == null) {
			extrasBean = new IclubExtrasBean();
		}
		return extrasBean;
	}

	public void setExtrasBean(IclubExtrasBean extrasBean) {
		this.extrasBean = extrasBean;
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

	public IclubClaimBean getClaimBean() {
		if (claimBean == null) {
			claimBean = new IclubClaimBean();
		}
		return claimBean;
	}

	public void setClaimBean(IclubClaimBean claimBean) {
		this.claimBean = claimBean;
	}

	public List<IclubClaimStatusBean> getClaimStatusBeans() {

		WebClient client = IclubWebHelper.createCustomClient(CS_BASE_URL + "list");
		Collection<? extends IclubClaimStatusModel> models = new ArrayList<IclubClaimStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimStatusModel.class));
		client.close();
		claimStatusBeans = new ArrayList<IclubClaimStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimStatusModel model : models) {
				IclubClaimStatusBean bean = new IclubClaimStatusBean();
				bean.setCsId(model.getCsId());
				bean.setCsLongDesc(model.getCsLongDesc());
				bean.setCsShortDesc(model.getCsShortDesc());
				bean.setCsStatus(model.getCsStatus());
				claimStatusBeans.add(bean);
			}
		}
		return claimStatusBeans;
	}

	public void setClaimStatusBeans(List<IclubClaimStatusBean> claimStatusBeans) {
		this.claimStatusBeans = claimStatusBeans;
	}

	public IclubAccountBean getAccountBean() {
		if (accountBean == null) {
			accountBean = new IclubAccountBean();
		}
		return accountBean;
	}

	public void setAccountBean(IclubAccountBean accountBean) {
		this.accountBean = accountBean;
	}

	public List<IclubBankMasterBean> getBankMasterBeans() {

		if (bankMasterBeans == null) {
			bankMasterBeans = new ArrayList<IclubBankMasterBean>();
		}
		return bankMasterBeans;
	}

	public void setBankMasterBeans(List<IclubBankMasterBean> bankMasterBeans) {
		this.bankMasterBeans = bankMasterBeans;
	}

	public List<IclubAccountTypeBean> getAccountTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(ACCT_BASE_URL + "list");
		Collection<? extends IclubAccountTypeModel> models = new ArrayList<IclubAccountTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccountTypeModel.class));
		client.close();
		accountTypeBeans = new ArrayList<IclubAccountTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubAccountTypeModel model : models) {
				IclubAccountTypeBean bean = new IclubAccountTypeBean();
				bean.setAtId(model.getAtId());
				bean.setAtLongDesc(model.getAtLongDesc());
				bean.setAtShortDesc(model.getAtShortDesc());
				bean.setAtStatus(model.getAtStatus());
				accountTypeBeans.add(bean);
			}
		}
		return accountTypeBeans;
	}

	public void setAccountTypeBeans(List<IclubAccountTypeBean> accountTypeBeans) {
		this.accountTypeBeans = accountTypeBeans;
	}

	public List<IclubOwnerTypeBean> getOwnerTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(OWNT_BASE_URL + "list");
		Collection<? extends IclubOwnerTypeModel> models = new ArrayList<IclubOwnerTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOwnerTypeModel.class));
		client.close();
		ownerTypeBeans = new ArrayList<IclubOwnerTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubOwnerTypeModel model : models) {
				IclubOwnerTypeBean bean = new IclubOwnerTypeBean();
				bean.setOtId(model.getOtId());
				bean.setOtLongDesc(model.getOtLongDesc());
				bean.setOtShortDesc(model.getOtShortDesc());
				bean.setOtStatus(model.getOtStatus());

				if (model.getIclubAccounts() != null && model.getIclubAccounts().length > 0) {

					bean.setIclubAccounts(model.getIclubAccounts());
				}

				ownerTypeBeans.add(bean);
			}
		}
		return ownerTypeBeans;
	}

	public void setOwnerTypeBeans(List<IclubOwnerTypeBean> ownerTypeBeans) {
		this.ownerTypeBeans = ownerTypeBeans;
	}

	public String getDebitDate() {
		return debitDate;
	}

	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}

	public String getDebitMonth() {
		return debitMonth;
	}

	public void setDebitMonth(String debitMonth) {
		this.debitMonth = debitMonth;
	}

	public IclubQuoteBean getQuoteBean() {
		return quoteBean;
	}

	public void setQuoteBean(IclubQuoteBean quoteBean) {
		this.quoteBean = quoteBean;
	}

	/*
	 * public IclubInsuranceItemBean getVehicleIItemBean() { return
	 * vehicleIItemBean; }
	 * 
	 * public void setVehicleIItemBean(IclubInsuranceItemBean vehicleIItemBean)
	 * { this.vehicleIItemBean = vehicleIItemBean; }
	 */

	// public IclubInsuranceItemBean getPropertyIItemBean() {
	// return propertyIItemBean;
	// }
	//
	// public void setPropertyIItemBean(IclubInsuranceItemBean
	// propertyIItemBean) {
	// this.propertyIItemBean = propertyIItemBean;
	// }

	public IclubPolicyBean getPolicyBean() {
		return policyBean;
	}

	public void setPolicyBean(IclubPolicyBean policyBean) {
		this.policyBean = policyBean;
	}

	public List<IclubOccupationBean> getOccupationBeans() {
		WebClient client = IclubWebHelper.createCustomClient(OCN_BASE_URL + "list");
		Collection<? extends IclubOccupationModel> models = new ArrayList<IclubOccupationModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupationModel.class));
		client.close();
		occupationBeans = new ArrayList<IclubOccupationBean>();
		if (models != null && models.size() > 0) {
			for (IclubOccupationModel model : models) {

				IclubOccupationBean bean = new IclubOccupationBean();

				bean.setOId(model.getOId());
				bean.setODesc(model.getODesc());
				bean.setOCrtdDt(model.getOCrtdDt());
				bean.setOStatus(model.getOStatus());
				bean.setIclubPerson(model.getIclubPerson());

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
				IclubCountryCodeBean bean = new IclubCountryCodeBean();
				bean.setCcId(model.getCcId().intValue());
				bean.setCcIsoId(model.getCcIsoId());
				bean.setCcShortId(model.getCcShortId());
				bean.setCcCrtdDt(model.getCcCrtdDt());
				bean.setCcName(model.getCcName());
				bean.setIclubPerson(model.getIclubPerson());
				countryCodeBeans.add(bean);
			}
		}
		return countryCodeBeans;
	}

	public void setCountryCodeBeans(List<IclubCountryCodeBean> countryCodeBeans) {
		this.countryCodeBeans = countryCodeBeans;
	}

	public List<IclubPropUsageTypeBean> getpPropUsageTypeBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/list");
		Collection<? extends IclubPropUsageTypeModel> models = new ArrayList<IclubPropUsageTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropUsageTypeModel.class));
		client.close();
		pPropUsageTypeBeans = new ArrayList<IclubPropUsageTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropUsageTypeModel model : models) {
				IclubPropUsageTypeBean bean = new IclubPropUsageTypeBean();

				bean.setPutId(model.getPutId());
				bean.setPutLongDesc(model.getPutLongDesc());
				bean.setPutShortDesc(model.getPutShortDesc());
				bean.setPutStatus(model.getPutStatus());

				if (model.getIclubProperties() != null && model.getIclubProperties().length > 0) {
					String[] properties = new String[model.getIclubProperties().length];
					int i = 0;
					for (String iclubProperty : model.getIclubProperties()) {
						properties[i] = iclubProperty;
						i++;
					}
					bean.setIclubProperties(properties);
				}

				pPropUsageTypeBeans.add(bean);
			}
		}
		return pPropUsageTypeBeans;
	}

	public void setpPropUsageTypeBeans(List<IclubPropUsageTypeBean> pPropUsageTypeBeans) {
		this.pPropUsageTypeBeans = pPropUsageTypeBeans;
	}

	public ResourceBundle getLabelBundle() {

		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}

	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}

	@SuppressWarnings("unchecked")
	public List<String> getBankNames() {
		WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "list/banknames");
		Collection<? extends String> models = new ArrayList<String>(client.accept(MediaType.APPLICATION_JSON).getCollection(String.class));
		client.close();
		bankNames = (List<String>) models;
		return bankNames;
	}

	public void setBankNames(List<String> bankNames) {
		this.bankNames = bankNames;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@SuppressWarnings("unused")
	public Double getUpdatePremium(String quoteId, String quoteType, IclubVehicleBean vehicleBean, IclubPropertyBean propertyBean) {
		List<IclubFieldBean> fieldBeans = getIclubFieldBeans();
		IclubQuoteBean quoteBean = getQuoteDetailsById(quoteId);
		Double baseValue = getBasePremium();
		Double premium = quoteBean.getQGenPremium();
		for (IclubFieldBean fieldBean : fieldBeans) {
			if (fieldBean.getFRate() != null && fieldBean.getFStatus().equalsIgnoreCase("Y")) {

				IclubEntityTypeBean entityType = getEntityType(fieldBean.getIclubEntityType());
				String tableName = entityType.getEtTblNm();
				String fieldName = fieldBean.getFName();
				List<IclubRateTypeBean> rateTypeBeans = getRateTypeBeanByFieldId(fieldBean.getFId(), quoteType);
				if (tableName != null && rateTypeBeans != null && rateTypeBeans.size() > 0) {

					String fieldValue = null;
					if (tableName.equalsIgnoreCase("iclub_vehicle") && rateTypeBeans != null && rateTypeBeans.size() > 0 && rateTypeBeans.get(0).getRtType().equalsIgnoreCase("G")) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId(), "G");

					} else if (tableName.equalsIgnoreCase("iclub_vehicle")) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId(), null);

					} else if (tableName.equalsIgnoreCase("iclub_property") && propertyBean != null && propertyBean.getPId() != null) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, propertyBean.getPId(), null);
					} else if (tableName.equalsIgnoreCase("iclub_person")) {

						IclubPersonBean personBean = getIclubPersonBean(quoteBean.getIclubPersonByQPersonId());

						fieldValue = getFieldValueFromDB(fieldName, tableName, personBean.getPId(), null);

					} else if (tableName.equalsIgnoreCase("iclub_driver")) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, driverBean.getDId(), null);

					} else if (tableName.equalsIgnoreCase("iclub_quote")) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, quoteId, null);

					}

					for (IclubRateTypeBean rateTypeBean : rateTypeBeans) {
						if (rateTypeBean.getRtType().equalsIgnoreCase("G")) {
							String fieldValues[] = fieldValue.split("@");
							IclubGeoLocBean geoLocBean = new IclubGeoLocBean();
							// getGeoLocBean(new
							// Double(fieldValues[0]),
							// new
							// Double(fieldValues[1]));
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

	public List<IclubRateEngineBean> getRateEnginesByRateType(Long rateType) {

		WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/rateType/" + rateType);
		Collection<? extends IclubRateEngineModel> models = new ArrayList<IclubRateEngineModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubRateEngineModel.class));
		client.close();
		List<IclubRateEngineBean> beans = new ArrayList<IclubRateEngineBean>();
		if (models != null && models.size() > 0) {
			for (IclubRateEngineModel model : models) {

				IclubRateEngineBean bean = new IclubRateEngineBean();
				bean.setReId(model.getReId());
				bean.setReRate(model.getReRate());
				bean.setReCrtdDt(model.getReCrtdDt());
				bean.setReStatus(model.getReStatus());
				bean.setReMaxValue(model.getReMaxValue());
				bean.setReBaseValue(model.getReBaseValue());
				bean.setIclubRateType(model.getIclubRateType());
				bean.setIclubPerson(model.getIclubPerson());
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
			vehicleBean = new IclubVehicleBean();

			vehicleBean.setVId(model.getVId());
			vehicleBean.setVOwner(model.getVOwner());
			vehicleBean.setVGearLockYn(model.getVGearLockYn());
			vehicleBean.setVImmYn(model.getVImmYn());
			vehicleBean.setVConcessReason(model.getVConcessReason());
			vehicleBean.setVConcessPrct(model.getVConcessPrct());
			vehicleBean.setVInsuredValue(model.getVInsuredValue());
			vehicleBean.setVYear(model.getVYear());
			vehicleBean.setVDdLong(model.getVDdLong());
			vehicleBean.setVDdLat(model.getVDdLat());
			vehicleBean.setVDdArea(model.getVDdArea());
			vehicleBean.setVOnLong(model.getVOnLong());
			vehicleBean.setVOnLat(model.getVOnLat());
			vehicleBean.setVOnArea(model.getVOnArea());
			vehicleBean.setVOdometer(model.getVOdometer());
			vehicleBean.setVCrtdDt(model.getVCrtdDt());
			vehicleBean.setVRegNum(model.getVRegNum());
			vehicleBean.setVEngineNr(model.getVEngineNr());
			vehicleBean.setVVin(model.getVVin());
			vehicleBean.setVNoclaimYrs(model.getVNoclaimYrs());
			vehicleBean.setIclubVehicleMaster(model.getIclubVehicleMaster());
			vehicleBean.setIclubVehUsageType(model.getIclubVehUsageType());
			vehicleBean.setIclubVehSecType(model.getIclubVehSecType());
			vehicleBean.setIclubPerson(model.getIclubPerson());
			vehicleBean.setIclubDriver(model.getIclubDriver());
			vehicleBean.setIclubSecurityDevice(model.getIclubSecurityDevice());
			vehicleBean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
			vehicleBean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());

		}

		client.close();
		return vehicleBean;
	}

	public IclubPropertyBean getPropertyDetails(String propertyId) {
		WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + propertyId);
		IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));
		IclubPropertyBean propertyBean = new IclubPropertyBean();
		if (model != null && model.getPId() != null) {
			propertyBean = new IclubPropertyBean();
			propertyBean.setPId(model.getPId());
			propertyBean.setPCrtdDt(model.getPCrtdDt());
			propertyBean.setPEstValue(model.getPEstValue());
			propertyBean.setPSecGatesYn(model.getPSecGatesYn());
			propertyBean.setPNorobberyYn(model.getPNorobberyYn());
			propertyBean.setPCompYn(model.getPCompYn());
			propertyBean.setPRentFurYn(model.getPRentFurYn());
			propertyBean.setPNoclaimYrs(model.getPNoclaimYrs());
			propertyBean.setPPostalCd(model.getPPostalCd());
			propertyBean.setPLong(model.getPLong());
			propertyBean.setPLat(model.getPLat());
			propertyBean.setPAddress(model.getPAddress());
			propertyBean.setPRegNum(model.getPRegNum());
			propertyBean.setIclubCoverType(model.getIclubCoverType());
			propertyBean.setIclubPropUsageType(model.getIclubPropUsageType());
			propertyBean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
			propertyBean.setIclubPropertyType(model.getIclubPropertyType());
			propertyBean.setIclubWallType(model.getIclubWallType());
			propertyBean.setIclubAccessType(model.getIclubAccessType());
			propertyBean.setIclubPerson(model.getIclubPerson());
			propertyBean.setIclubBarType(model.getIclubBarType());
			propertyBean.setPThatchType(model.getPThatchType());
			propertyBean.setIclubRoofType(model.getIclubRoofType());

		}
		client.close();
		return propertyBean;
	}

	public IclubEntityTypeBean getEntityType(Long entityId) {
		WebClient client = IclubWebHelper.createCustomClient(ET_BASE_URL + "get/" + entityId);
		IclubEntityTypeModel model = (IclubEntityTypeModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubEntityTypeModel.class));
		client.close();

		IclubEntityTypeBean bean = new IclubEntityTypeBean();
		bean.setEtId(model.getEtId());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setEtShortDesc(model.getEtShortDesc());
		bean.setEtStatus(model.getEtStatus());
		bean.setEtTblNm(model.getEtTblNm());
		if (model.getIclubDocuments() != null && model.getIclubDocuments().length > 0) {
			String[] documents = new String[model.getIclubDocuments().length];
			int i = 0;
			for (String iclubDocument : model.getIclubDocuments()) {
				documents[i] = iclubDocument;
				i++;
			}
			bean.setIclubDocuments(documents);
		}
		return bean;

	}

	public IclubQuoteBean getQuoteDetailsById(String quoteId) {
		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);
		IclubQuoteModel model = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));

		IclubQuoteBean bean = new IclubQuoteBean();

		bean.setQId(model.getQId());
		bean.setQCrtdDt(model.getQCrtdDt());
		bean.setQIsMatched(model.getQIsMatched());
		bean.setQPrevPremium(model.getQPrevPremium());
		bean.setQValidUntil(model.getQValidUntil());
		bean.setQMobile(model.getQMobile());
		bean.setQEmail(model.getQEmail());
		bean.setQGenPremium(model.getQGenPremium());
		bean.setQNumItems(model.getQNumItems());
		bean.setQGenDt(model.getQGenDt());
		bean.setQNumber(model.getQNumber());
		bean.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy());
		bean.setIclubProductType(model.getIclubProductType());
		bean.setIclubProductType(model.getIclubProductType());
		bean.setIclubInsurerMaster(model.getIclubInsurerMaster());
		bean.setIclubCoverType(model.getIclubCoverType());
		bean.setIclubQuoteStatus(model.getIclubQuoteStatus());
		bean.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId());

		if (model.getIclubPolicies() != null && model.getIclubPolicies().length > 0) {
			String[] policies = new String[model.getIclubPolicies().length];
			int i = 0;
			for (String policy : model.getIclubPolicies()) {
				policies[i] = policy;
				i++;
			}
		}
		client.close();

		return bean;

	}

	public IclubPersonBean getIclubPersonBean(String personId) {

		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + personId);

		IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
		IclubPersonBean personBean = new IclubPersonBean();
		personBean.setPId(model.getPId());
		personBean.setPCrtdDt(model.getPCrtdDt());
		personBean.setPDob(model.getPDob());
		personBean.setPEmail(model.getPEmail());
		personBean.setPFName(model.getPFName());
		personBean.setPIdNum(model.getPIdNum());
		personBean.setPLName(model.getPLName());
		personBean.setPMobile(model.getPMobile());
		personBean.setPAddress(model.getPAddress());
		personBean.setPContactPref(model.getPContactPref());
		personBean.setPAge(model.getPAge());
		personBean.setPGender(model.getPGender());
		personBean.setPContactPref(model.getPContactPref());
		personBean.setPIdExpiryDt(model.getPIdExpiryDt());
		personBean.setPInitials(model.getPInitials());
		personBean.setPIsPensioner(model.getPIsPensioner());
		personBean.setPIdIssueCntry(model.getPIdIssueCntry());
		personBean.setPLat(model.getPLat());
		personBean.setPLong(model.getPLong());
		personBean.setPOccupation(model.getPOccupation());
		personBean.setPTitle(model.getPTitle());
		personBean.setPZipCd(model.getPZipCd());
		personBean.setIclubIdType(model.getIclubIdType());
		personBean.setIclubPerson(model.getIclubPerson());
		personBean.setIclubMaritialStatus(model.getIclubMaritialStatus());
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

				IclubRateTypeBean bean = new IclubRateTypeBean();
				bean.setRtId(model.getRtId());
				bean.setRtLongDesc(model.getRtLongDesc());
				bean.setRtShortDesc(model.getRtShortDesc());
				bean.setRtStatus(model.getRtStatus());
				bean.setRtQuoteType(model.getRtQuoteType());
				bean.setIclubField(model.getIclubField());
				bean.setIclubEntityType(model.getIclubEntityType());
				bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
				bean.setIclubPerson(model.getIclubPerson());
				bean.setRtCrtdDt(model.getRtCrtdDt());
				bean.setRtType(model.getRtType());
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

				IclubFieldBean bean = new IclubFieldBean();
				bean.setFId(model.getFId());
				bean.setFName(model.getFName());
				bean.setFDesc(model.getFDesc());
				bean.setFStatus(model.getFStatus());
				bean.setFLTblName(model.getFLTblName());
				bean.setFRate(model.getFRate());
				bean.setIclubEntityType(model.getIclubEntityType());
				if (model.getIclubRateTypes() != null && model.getIclubRateTypes().length > 0) {
					Long[] rateTypes = new Long[model.getIclubRateTypes().length];
					int i = 0;
					for (Long rateType : model.getIclubRateTypes()) {
						rateTypes[i] = rateType;
						i++;
					}

					bean.setIclubRateTypes(rateTypes);
					beans.add(bean);
				}
			}

		}
		return beans;
	}

	public Double getBasePremium() {
		WebClient client = IclubWebHelper.createCustomClient(CONF_BASE_URL + "/getByKey/base.premium");

		IclubConfigModel model = (IclubConfigModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubConfigModel.class));
		client.close();
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

	public boolean isTermsAndConditionFlag() {
		return termsAndConditionFlag;
	}

	public void setTermsAndConditionFlag(boolean termsAndConditionFlag) {
		this.termsAndConditionFlag = termsAndConditionFlag;
	}

	public String getVehCoverType() {
		return vehCoverType;
	}

	public void setVehCoverType(String vehCoverType) {
		this.vehCoverType = vehCoverType;
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

	public List<IclubVehicleBean> getVehicleBeans() {
		if (vehicleBeans == null) {
			vehicleBeans = new ArrayList<IclubVehicleBean>();
		}
		return vehicleBeans;
	}

	public void setVehicleBeans(List<IclubVehicleBean> vehicleBeans) {
		this.vehicleBeans = vehicleBeans;
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

	public List<IclubInsuranceItemBean> getVehicleIItemBeans() {
		if (vehicleIItemBeans == null) {
			vehicleIItemBeans = new ArrayList<IclubInsuranceItemBean>();
		}
		return vehicleIItemBeans;
	}

	public void setVehicleIItemBeans(List<IclubInsuranceItemBean> vehicleIItemBeans) {
		this.vehicleIItemBeans = vehicleIItemBeans;
	}

	public Map<String, Integer> getNoOfCompYrs() {

		if (noOfCompYrs == null) {
			noOfCompYrs = new HashMap<String, Integer>();

			noOfCompYrs.put("I never had insurance", 1);
			noOfCompYrs.put("Less than a year", 2);
			noOfCompYrs.put("Between 1 and 2 years", 3);
			noOfCompYrs.put("Between 2 and 3 years", 4);
			noOfCompYrs.put("Between 3 and 4 years", 5);
			noOfCompYrs.put("Between 4 and 5 years", 6);
			noOfCompYrs.put("Between 5 and 6 years", 7);
			noOfCompYrs.put("Between 6 and 7 years", 8);
			noOfCompYrs.put("More than 7 years", 9);
		}
		return noOfCompYrs;
	}

	public void setNoOfCompYrs(Map<String, Integer> noOfCompYrs) {
		this.noOfCompYrs = noOfCompYrs;
	}

	public Map<String, Integer> getNoClaimYrs() {

		if (noClaimYrs == null) {
			noClaimYrs = new HashMap<String, Integer>();

			noClaimYrs.put("Less than a year", 1);
			noClaimYrs.put("Between 1 and 2 years", 2);
			noClaimYrs.put("Between 2 and 3 years", 3);
			noClaimYrs.put("Between 3 and 4 years", 4);
			noClaimYrs.put("Between 4 and 5 years", 5);
			noClaimYrs.put("Between 5 and 6 years", 6);
			noClaimYrs.put("Between 6 and 7 years", 7);
			noClaimYrs.put("More than 7 years", 8);
			noClaimYrs.put("Never claimed", 9);
		}
		return noClaimYrs;
	}

	public void setNoClaimYrs(Map<String, Integer> noClaimYrs) {
		this.noClaimYrs = noClaimYrs;
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

	public Map<String, String> getDebitDates() {
		if (debitDates == null) {
			debitDates = new LinkedHashMap<String, String>();

			for (int i = 1; i <= 31; i++) {

				if (i < 10) {
					debitDates.put("0" + i, "0" + i);
				} else {
					debitDates.put("" + i, "" + i);
				}
			}

		}

		return debitDates;
	}

	public void setDebitDates(Map<String, String> debitDates) {
		this.debitDates = debitDates;
	}

	public Map<String, String> getDebitMonths() {
		if (debitMonths == null) {
			debitMonths = new HashMap<String, String>();
			debitMonths.put("Jan", "01");
			debitMonths.put("Feb", "02");
			debitMonths.put("Mar", "03");
			debitMonths.put("Apr", "04");
			debitMonths.put("May", "05");
			debitMonths.put("Jun", "06");
			debitMonths.put("Jul", "07");
			debitMonths.put("Aug", "08");
			debitMonths.put("Sep", "09");
			debitMonths.put("Oct", "10");
			debitMonths.put("Nov", "11");
			debitMonths.put("Dec", "12");

		}
		return debitMonths;
	}

	public void setDebitMonths(Map<String, String> debitMonths) {
		this.debitMonths = debitMonths;
	}

	public List<IclubVehicleMasterBean> getVehicleMasters() {

		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "list");
		Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
		client.close();
		vehicleMasters = new ArrayList<IclubVehicleMasterBean>();
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
				vehicleMasters.add(bean);
			}
		}

		return vehicleMasters;
	}

	public void setVehicleMasters(List<IclubVehicleMasterBean> vehicleMasters) {
		this.vehicleMasters = vehicleMasters;
	}
}
