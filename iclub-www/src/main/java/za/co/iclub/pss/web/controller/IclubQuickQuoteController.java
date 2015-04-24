package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import za.co.iclub.pss.web.bean.IclubCountryCodeBean;
import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubEntityTypeBean;
import za.co.iclub.pss.web.bean.IclubFieldBean;
import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubLoginBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubOccupationBean;
import za.co.iclub.pss.web.bean.IclubOccupiedStatusBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPropertyTypeBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.bean.IclubRateEngineBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.bean.IclubRoofTypeBean;
import za.co.iclub.pss.web.bean.IclubSecurityMasterBean;
import za.co.iclub.pss.web.bean.IclubSecurityQuestionBean;
import za.co.iclub.pss.web.bean.IclubThatchTypeBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.bean.IclubVehicleMasterBean;
import za.co.iclub.pss.web.bean.IclubWallTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubAccessTypeModel;
import za.co.iclub.pss.ws.model.IclubBarTypeModel;
import za.co.iclub.pss.ws.model.IclubConfigModel;
import za.co.iclub.pss.ws.model.IclubCountryCodeModel;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubEntityTypeModel;
import za.co.iclub.pss.ws.model.IclubFieldModel;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubOccupationModel;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubPropertyTypeModel;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.IclubRateEngineModel;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
import za.co.iclub.pss.ws.model.IclubSecurityMasterModel;
import za.co.iclub.pss.ws.model.IclubSecurityQuestionModel;
import za.co.iclub.pss.ws.model.IclubThatchTypeModel;
import za.co.iclub.pss.ws.model.IclubVehicleMasterModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.IclubWallTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubQuickQuoteController")
@SessionScoped
public class IclubQuickQuoteController implements Serializable {

	private static final long serialVersionUID = -6405843984156478759L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubQuickQuoteController.class);
	private static final String PUR_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPurposeTypeService/";
	private static final String PER_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPersonService/";
	private static final String VM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleMasterService/";
	private static final String V_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String D_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDriverService/";
	private static final String LIC_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLicenseCodeService/";
	private static final String WT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubWallTypeService/";
	private static final String RT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRoofTypeService/";
	private static final String MS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubMaritialStatusService/";
	private static final String IT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubIdTypeService/";
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String RAT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateTypeService/";
	private static final String RE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubRateEngineService/";
	private static final String FD_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubFieldService/";
	private static final String CONF_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubConfigService/";
	private static final String OCN_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupationService/";
	private static final String CCDE_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCountryCodeService/";
	private static final String LOG_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubLoginService/";
	private static final String SECQ_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityQuestionService/";
	private static final String ET_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubEntityTypeService/";
	private static final String SM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityMasterService/";
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String GL_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubGeoLocService/";
	private static final String BT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBarTypeService/";
	private static final String TT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubThatchTypeService/";
	private static final String PROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyTypeService/";
	private static final String OCCS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupiedStatusService/";
	private static final String CT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";

	private List<String> vmMakes;
	private IclubVehicleMasterBean vehicleMasterBean;
	private IclubPersonBean personBean;
	private IclubPropertyBean propertyBean;
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	private List<IclubIdTypeBean> idTypeBeans;
	private List<IclubVehicleMasterBean> vBeans;
	private List<IclubPurposeTypeBean> pBeans;
	private List<IclubLicenseCodeBean> licenseCodeBeans;
	private List<IclubPurposeTypeBean> pPurposeTypeBeans;
	private List<IclubBarTypeBean> barTypeBeans;
	private List<IclubThatchTypeBean> thatchTypeBeans;
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
	private String centerGeoMapPer = "36.890257,30.707417";

	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "36.890257,30.707417";

	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "36.890257,30.707417";

	private IclubPersonBean bean;

	private List<IclubOccupationBean> occupationBeans;

	private List<IclubCountryCodeBean> countryCodeBeans;

	private List<IclubSecurityQuestionBean> securityQuestionBeans;

	private IclubLoginBean loginBean;

	private boolean updateLogin;
	private boolean showVehAddPanel;
	private boolean showVehModPanel;
	private boolean showProAddPanel;
	private boolean showProModPanel;
	private List<IclubVehicleBean> vehicleBeans;
	private List<IclubSecurityMasterBean> securityMasterBeans;
	private List<IclubAccessTypeBean> accessTypeBeans;
	private List<IclubPurposeTypeBean> purposeTypeBeans;

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
		vehicleBean = new IclubVehicleBean();
	}

	public void showVehModPanel() {
		showVehAddPanel = false;
		showVehModPanel = true;
	}

	public void showProAddPanel() {
		showProAddPanel = true;
		showProModPanel = false;
		propertyBean = new IclubPropertyBean();
	}

	public void clearProForm() {
		showProAddPanel = false;
		showProModPanel = false;
		proAddress = "";
		propertyBean = new IclubPropertyBean();
	}

	public void showProModPanel() {
		showProAddPanel = false;
		showProModPanel = true;
	}

	public void addIclubProperty() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubProperty");
		try {
			if (validateProForm(true)) {
				IclubPropertyModel model = new IclubPropertyModel();

				propertyBean.setPId(UUID.randomUUID().toString());
				model.setPId(propertyBean.getPId());
				model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
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
				model.setIclubPurposeType(propertyBean.getIclubPurposeType());
				model.setIclubOccupiedStatus(propertyBean.getIclubOccupiedStatus());
				model.setIclubPropertyType(propertyBean.getIclubPropertyType());
				model.setIclubWallType(propertyBean.getIclubWallType());
				model.setIclubAccessType(propertyBean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(propertyBean.getIclubBarType());
				model.setIclubThatchType(propertyBean.getIclubThatchType());
				model.setIclubRoofType(propertyBean.getIclubRoofType());

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
			if (validateVehForm(false)) {
				IclubPropertyModel model = new IclubPropertyModel();

				model.setPId(propertyBean.getPId());
				model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
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
				model.setIclubPurposeType(propertyBean.getIclubPurposeType());
				model.setIclubOccupiedStatus(propertyBean.getIclubOccupiedStatus());
				model.setIclubPropertyType(propertyBean.getIclubPropertyType());
				model.setIclubWallType(propertyBean.getIclubWallType());
				model.setIclubAccessType(propertyBean.getIclubAccessType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubBarType(propertyBean.getIclubBarType());
				model.setIclubThatchType(propertyBean.getIclubThatchType());
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
		if (propertyBean.getIclubPurposeType() == null) {
			IclubWebHelper.addMessage(("Please Select Purpose Type"), FacesMessage.SEVERITY_ERROR);
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
		if (propertyBean.getIclubThatchType() == null) {
			IclubWebHelper.addMessage(("Please Select Thatch Type"), FacesMessage.SEVERITY_ERROR);
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

				vehicleBean.setVId(UUID.randomUUID().toString());
				vehicleBean.setVCrtdDt(new Timestamp(System.currentTimeMillis()));
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
				model.setIclubPurposeType(vehicleBean.getIclubPurposeType());
				model.setIclubSecurityMaster(vehicleBean.getIclubSecurityMaster());
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
				vehicleBean.setVCrtdDt(new Timestamp(System.currentTimeMillis()));
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
				model.setIclubPurposeType(vehicleBean.getIclubPurposeType());
				model.setIclubSecurityMaster(vehicleBean.getIclubSecurityMaster());
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
		/*
		 * if (bean.getIclubDriver() == null) {
		 * IclubWebHelper.addMessage(("Please Select Driver"),
		 * FacesMessage.SEVERITY_ERROR); ret = ret && false; }
		 */
		if (vehicleBean.getVImmYn() == null || vehicleBean.getVImmYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Imn Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getVGearLockYn() == null || vehicleBean.getVGearLockYn().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(("Gear Lock Yn Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getIclubSecurityMaster() == null) {
			IclubWebHelper.addMessage(("Please Select Security Master"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vehicleBean.getIclubPurposeType() == null) {
			IclubWebHelper.addMessage(("Please Select Purpose Type"), FacesMessage.SEVERITY_ERROR);
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
		IclubGeoLocBean geoBean = getGeoLocBean(markerPer.getLatlng().getLat(), markerPer.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			personBean.setPLat(geoBean.getGlLat());
			personBean.setPLong(geoBean.getGlLong());
		} else {
			personBean.setPLat(markerPer.getLatlng().getLat());
			personBean.setPLong(markerPer.getLatlng().getLng());
		}
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
			propertyBean.setPLat(geoBean.getGlLat());
			propertyBean.setPLong(geoBean.getGlLong());
		} else {
			propertyBean.setPLat(markerPro.getLatlng().getLat());
			propertyBean.setPLong(markerPro.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}

	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		IclubGeoLocBean geoBean = getGeoLocBean(markerVeh.getLatlng().getLat(), markerVeh.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVDdLat(geoBean.getGlLat());
			vehicleBean.setVDdLong(geoBean.getGlLong());
		} else {
			vehicleBean.setVDdLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVDdLong(markerVeh.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng()));
	}

	public IclubGeoLocBean getGeoLocBean(Double geoLong, Double geoLat) {
		WebClient client = IclubWebHelper.createCustomClient(GL_BASE_URL + "get/" + geoLat + "/" + geoLong);
		IclubGeoLocModel model = (IclubGeoLocModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubGeoLocModel.class));
		client.close();
		IclubGeoLocBean bean = new IclubGeoLocBean();
		if (model != null) {
			bean.setGlKey(model.getGlKey());
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

	public void onGeocodeVeh(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();

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
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			vehicleBean.setVDdLat(geoBean.getGlLat());
			vehicleBean.setVDdLong(geoBean.getGlLong());
		} else {
			vehicleBean.setVDdLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVDdLong(markerVeh.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerVeh.getTitle()));
	}

	public void initializePage() {
		IclubWebHelper.addObjectIntoSession("page_key", "/pages/quote/qq.xhtml");
	}

	public void vmMakeValueChangeListener() {
		if (vmMake != null) {

			WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "getByMake/" + vmMake);
			Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
			client.close();
			vBeans = new ArrayList<IclubVehicleMasterBean>();
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
				loadPersonBean(model.getIclubPersonByQPersonId());
				if (IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")) == null) {
					clearForm();
					clearVehForm();
					clearProForm();
					return "register";
				} else {
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
		claimYN = "";
	}

	public String updatedPerson() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: updatedPerson");
		try {
			if (validatePersonForm(!true) && validateLoginForm(!updateLogin)) {
				WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "mod");

				IclubPersonModel model = new IclubPersonModel();
				model.setPId(bean.getPId());
				model.setPCrtdDt(bean.getPCrtdDt());
				model.setPDob(bean.getPDob());
				model.setPEmail(bean.getPEmail());
				model.setPFName(bean.getPFName());
				model.setPIdNum(bean.getPIdNum());
				model.setPLName(bean.getPLName());
				model.setPAge(IclubWebHelper.calculateMyAge(bean.getPDob().getTime()));
				model.setPMobile(bean.getPMobile());
				model.setPAddress(bean.getPAddress());
				model.setPContactPref(bean.getPContactPref());
				model.setPGender(bean.getPGender());
				model.setPContactPref(bean.getPContactPref());
				model.setPIdExpiryDt(bean.getPIdExpiryDt());
				model.setPInitials(bean.getPInitials());
				model.setPIsPensioner(bean.getPIsPensioner());
				model.setPIdIssueCntry(bean.getPIdIssueCntry() != null ? bean.getPIdIssueCntry().longValue() : null);
				model.setPIdIssueDt(bean.getPIdIssueDt());
				model.setPLat(bean.getPLat());
				model.setPLong(bean.getPLong());
				model.setPOccupation(bean.getPOccupation());
				model.setPTitle(bean.getPTitle());
				model.setPZipCd(bean.getPZipCd());
				model.setIclubIdType(bean.getIclubIdType());
				model.setIclubPerson(getSessionUserId());
				model.setIclubMaritialStatus(bean.getIclubMaritialStatus());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();

				if (response.getStatusCode() == 0) {
					response = updatePassword();
					client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "get/" + quoteId);

					IclubQuoteModel quoteModel = (IclubQuoteModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubQuoteModel.class));
					quoteModel.setQGenPremium(genPremium);
					quoteModel.setIclubPersonByQCrtdBy(model.getPId());
					quoteModel.setIclubPersonByQPersonId(model.getPId());

					client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "mod");

					response = client.accept(MediaType.APPLICATION_JSON).put(quoteModel, ResponseModel.class);
					client.close();

					if (response != null && response.getStatusCode() == 0) {

						client = IclubWebHelper.createCustomClient(II_BASE_URL + "getByQuoteIdAndItemTypeId/" + quoteId + "/" + 1l);

						IclubInsuranceItemModel insurancemodel = (IclubInsuranceItemModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubInsuranceItemModel.class));
						model.setIclubPerson(model.getPId());
						client = IclubWebHelper.createCustomClient(II_BASE_URL + "mod");
						response = client.accept(MediaType.APPLICATION_JSON).put(insurancemodel, ResponseModel.class);
						client.close();

					}

					if (response != null && response.getStatusCode() == 0) {

						IclubWebHelper.addMessage("Updated Successfully", FacesMessage.SEVERITY_INFO);
						doIclubLogin(loginBean, bean);
						bean = new IclubPersonBean();
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
				WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/login/" + bean.getLName() + "/" + (bean.getLPasswd()));
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).get(ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage("Person Logged-In successfully", FacesMessage.SEVERITY_INFO);
					WebClient loginClient = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/" + bean.getLName());
					IclubLoginModel model = loginClient.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class);
					loginClient.close();
					if (model != null && model.getLId() != null) {
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.id"), model.getIclubPersonByLPersonId());
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.scname"), bean.getLName());
						WebClient personClient = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + model.getIclubPersonByLPersonId());
						IclubPersonModel personModel = personClient.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class);
						personClient.close();
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.user.name"), personModel.getPFName() + (personModel.getPLName() == null ? "" : personModel.getPLName() + " "));
						IclubWebHelper.addObjectIntoSession(BUNDLE.getString("logged.in.role.id"), 1l);

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
				IclubLoginModel model = new IclubLoginModel();
				WebClient client = null;

				if (loginBean.getLId() != null) {
					client = IclubWebHelper.createCustomClient(PER_BASE_URL + "mod");
					model.setLId(loginBean.getLId());

				} else {
					client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "add");
					model.setLId(UUID.randomUUID().toString());
				}
				model.setLCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setLLastDate(loginBean.getLLastDate());
				model.setLName(loginBean.getLName());
				model.setLPasswd((loginBean.getLPasswd()).toString());
				model.setLSecAns(loginBean.getLSecAns());
				model.setIclubPersonByLCrtdBy(bean.getPId());
				model.setIclubPersonByLPersonId(bean.getPId());
				model.setIclubRoleType(2l);
				model.setIclubSecurityQuestion(loginBean.getIclubSecurityQuestion());

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
		}
		return ret;
	}

	public String saveQuickQuoteDetails() {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: saveQuickQuoteDetails");
		try {
			if (validateForm(true)) {
				insertIntoPerson(personBean);
				return "qqs.xhtml?faces-redirect=true";
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}

	public ResponseModel insertIntoPerson(IclubPersonBean personBean) {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: insertIntoPerson");

		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "add");

		IclubPersonModel model = new IclubPersonModel();
		model.setPId(UUID.randomUUID().toString());
		model.setPCrtdDt(personBean.getPCrtdDt());
		model.setPDob(personBean.getPDob());
		model.setPAge(IclubWebHelper.calculateMyAge(personBean.getPDob().getTime()));
		model.setPEmail(personBean.getPEmail());
		model.setPFName(personBean.getPFName());
		model.setPIdNum(personBean.getPIdNum());
		model.setPLName(personBean.getPLName());
		model.setPMobile(personBean.getPMobile());
		model.setPAddress(personBean.getPAddress());
		model.setPContactPref(personBean.getPContactPref());
		model.setPGender(personBean.getPGender());
		model.setPContactPref(personBean.getPContactPref());
		model.setPIdExpiryDt(personBean.getPIdExpiryDt());
		model.setPIdIssueDt(personBean.getPIdIssueDt());
		model.setPInitials(personBean.getPInitials());
		model.setPIsPensioner(personBean.getPIsPensioner());
		model.setPIdIssueCntry(personBean.getPIdIssueCntry());
		model.setPLat(personBean.getPLat());
		model.setPLong(personBean.getPLong());
		model.setPOccupation(personBean.getPOccupation());
		model.setPTitle(personBean.getPTitle());
		model.setPZipCd(personBean.getPZipCd());
		model.setIclubIdType(personBean.getIclubIdType());
		model.setIclubPerson(getSessionUserId());
		model.setIclubMaritialStatus(personBean.getIclubMaritialStatus());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {
			addQuote(new IclubQuoteBean(), model);
		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addVehicle(List<IclubVehicleBean> beans, IclubDriverModel driverModel, IclubQuoteModel quoteModel) {

		if (beans != null && beans.size() > 0) {
			for (IclubVehicleBean bean : beans) {

				WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "add");

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
				model.setVDdArea(bean.getVDdArea());
				model.setVOnLong(bean.getVOnLong());
				model.setVOnLat(bean.getVOnLat());
				model.setVOnArea(bean.getVOnArea());
				model.setVOdometer(bean.getVOdometer());
				model.setVCrtdDt(bean.getVCrtdDt());
				model.setVRegNum(bean.getVRegNum());
				model.setVEngineNr(bean.getVEngineNr());
				model.setVVin(bean.getVVin());
				model.setVNoclaimYrs(bean.getVNoclaimYrs());
				model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
				model.setIclubPurposeType(bean.getIclubPurposeType());
				model.setIclubSecurityMaster(bean.getIclubSecurityMaster());
				model.setIclubPerson(bean.getIclubPerson());
				model.setIclubDriver(driverModel.getDId());
				model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
				model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
				model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					addInsuranceItem(model.getVId(), quoteModel.getQId(), 1l, getSessionUserId());
					genPremium += getUpdatePremium(quoteModel.getQId(), "Q", bean);
					quoteId = quoteModel.getQId();
					IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);

				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}

			}

		}

		return new ResponseModel();

	}

	public ResponseModel addPropertiy(List<IclubPropertyBean> beans, IclubQuoteModel quoteModel) {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addPropertiy");

		if (beans != null && beans.size() > 0) {
			for (IclubPropertyBean bean : beans) {
				WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "add");

				IclubPropertyModel model = new IclubPropertyModel();

				model.setPId(bean.getPId());
				model.setPCrtdDt(bean.getPCrtdDt());
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
					addInsuranceItem(model.getPId(), quoteModel.getQId(), 2l, getSessionUserId());
				} else {
					IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		}

		return new ResponseModel();

	}

	public ResponseModel addInsuranceItem(String itemId, String quoteId, Long iItemType, String userId) {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addInsuranceItem");

		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "add");
		IclubInsuranceItemModel model = new IclubInsuranceItemModel();
		model.setIiId(UUID.randomUUID().toString());
		model.setIiItemId(itemId);
		model.setIiQuoteId(quoteId);
		model.setIiCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setIclubInsuranceItemType(iItemType);
		model.setIclubPerson(userId);
		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addQuote(IclubQuoteBean bean, IclubPersonModel personModel) {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addQuote");

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "add");
		IclubQuoteModel model = new IclubQuoteModel();
		model.setQId(UUID.randomUUID().toString());
		model.setQCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setQIsMatched("N");
		model.setQPrevPremium(0.0d);
		model.setQValidUntil(new Timestamp(System.currentTimeMillis() + (3 * 24 * 3600)));
		model.setQMobile(personModel.getPMobile());
		model.setQEmail(personModel.getPEmail());
		model.setQGenPremium(0.0d);
		model.setQNumItems(1);
		model.setQGenDt(new Timestamp(System.currentTimeMillis()));

		model.setQNumber(getQnumber());
		model.setIclubPersonByQCrtdBy(getSessionUserId());
		model.setIclubProductType(1l);
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setIclubQuoteStatus(1l);
		model.setIclubPersonByQPersonId(personModel.getPId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();
		if (response.getStatusCode() == 0) {

			addDriver(driverBean, personModel, model);
			addPropertiy(propertyBeans, model);
		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public Long getQnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));

	}

	public ResponseModel addDriver(IclubDriverBean bean, IclubPersonModel personModel, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "add");

		IclubDriverModel model = new IclubDriverModel();

		model.setDId(UUID.randomUUID().toString());

		model.setDDob(bean.getDDob());
		model.setDIssueDt(new Timestamp(bean.getDIssueDt().getTime()));
		model.setDLicenseNum(bean.getDLicenseNum());
		model.setDName(bean.getDName());
		model.setDCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setIclubAccessType(bean.getIclubAccessType());
		model.setIclubLicenseCode(bean.getIclubLicenseCode());
		model.setIclubMaritialStatus(personModel.getIclubMaritialStatus());
		model.setIclubPersonByDPersonId(personModel.getPId());
		model.setIclubPersonByDCrtdBy(getSessionUserId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			addVehicle(vehicleBeans, model, quoteModel);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public boolean validateForm(boolean flag) {
		boolean ret = true;

		if (personBean.getPFName() == null || personBean.getPFName().trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pfname.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPLName() == null || personBean.getPLName().trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.plname.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPMobile() == null || personBean.getPMobile().trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pmobile.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPEmail() == null || personBean.getPEmail().trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pemail.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPGender() == null || personBean.getPGender().trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.pgender.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (personBean.getPDob() == null) {

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

	public List<IclubPurposeTypeBean> getpBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/get/insurnceitemtype/" + "1");
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		pBeans = new ArrayList<IclubPurposeTypeBean>();
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
			pBeans.add(bean);
		}
		return pBeans;
	}

	public void setpBeans(List<IclubPurposeTypeBean> pBeans) {
		this.pBeans = pBeans;
	}

	public IclubPersonBean getPersonBean() {

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
		for (IclubWallTypeModel model : models) {
			IclubWallTypeBean bean = new IclubWallTypeBean();
			bean.setWtId(model.getWtId());
			bean.setWtLongDesc(model.getWtLongDesc());
			bean.setWtShortDesc(model.getWtShortDesc());
			bean.setWtStatus(model.getWtStatus());
			wallTypeBeans.add(bean);
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
		for (IclubRoofTypeModel model : models) {
			IclubRoofTypeBean bean = new IclubRoofTypeBean();
			bean.setRtId(model.getRtId());
			bean.setRtLongDesc(model.getRtLongDesc());
			bean.setRtShortDesc(model.getRtShortDesc());
			bean.setRtStatus(model.getRtStatus());
			roofTypeBeans.add(bean);
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
		for (IclubMaritialStatusModel model : models) {
			IclubMaritialStatusBean bean = new IclubMaritialStatusBean();
			bean.setMsId(model.getMsId());
			bean.setMsLongDesc(model.getMsLongDesc());
			bean.setMsShortDesc(model.getMsShortDesc());
			bean.setMsStatus(model.getMsStatus());
			maritialStatusBeans.add(bean);
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
		for (IclubIdTypeModel model : models) {
			IclubIdTypeBean bean = new IclubIdTypeBean();
			bean.setItId(model.getItId());
			bean.setItLongDesc(model.getItLongDesc());
			bean.setItShortDesc(model.getItShortDesc());
			bean.setItStatus(model.getItStatus());
			idTypeBeans.add(bean);
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
		for (IclubSecurityQuestionModel model : models) {
			IclubSecurityQuestionBean bean = new IclubSecurityQuestionBean();
			bean.setSqId(model.getSqId());
			bean.setSqLongDesc(model.getSqLongDesc());
			bean.setSqShortDesc(model.getSqShortDesc());
			bean.setSqStatus(model.getSqStatus());

			securityQuestionBeans.add(bean);
		}
		return securityQuestionBeans;
	}

	public void setSecurityQuestionBeans(List<IclubSecurityQuestionBean> securityQuestionBeans) {
		this.securityQuestionBeans = securityQuestionBeans;
	}

	public void loadLoginBean() {
		WebClient client = IclubWebHelper.createCustomClient(LOG_BASE_URL + "person/" + bean.getPFName());

		IclubLoginModel model = (IclubLoginModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubLoginModel.class));
		if (model != null && model.getLId() != null) {
			updateLogin = true;
			loginBean = new IclubLoginBean();
			loginBean.setLId(model.getLId());
			loginBean.setLCrtdDt(model.getLCrtdDt());
			loginBean.setLLastDate(model.getLLastDate());
			loginBean.setLName(model.getLName());
			// loginBean.setLPasswd(model.getLPasswd());
			loginBean.setLSecAns(model.getLSecAns());
			loginBean.setLSecAns(model.getLSecAns());
			loginBean.setIclubPersonByLCrtdBy(model.getIclubPersonByLCrtdBy());
			loginBean.setIclubPersonByLPersonId(model.getIclubPersonByLPersonId());
			loginBean.setIclubRoleType(model.getIclubRoleType());
			loginBean.setIclubSecurityQuestion(model.getIclubSecurityQuestion());
		} else {
			loginBean = new IclubLoginBean();
		}
	}

	public void loadPersonBean(String userId) {
		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "get/" + userId);

		IclubPersonModel model = (IclubPersonModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPersonModel.class));
		bean = new IclubPersonBean();
		bean.setPId(model.getPId());
		bean.setPCrtdDt(model.getPCrtdDt());
		bean.setPDob(model.getPDob());
		bean.setPEmail(model.getPEmail());
		bean.setPFName(model.getPFName());
		bean.setPIdNum(model.getPIdNum());
		bean.setPLName(model.getPLName());
		bean.setPMobile(model.getPMobile());
		bean.setPAddress(model.getPAddress());
		bean.setPContactPref(model.getPContactPref());
		bean.setPGender(model.getPGender());
		bean.setPContactPref(model.getPContactPref());
		bean.setPIdExpiryDt(model.getPIdExpiryDt());
		bean.setPInitials(model.getPInitials());
		bean.setPIsPensioner(model.getPIsPensioner());
		bean.setPIdIssueCntry(model.getPIdIssueCntry());
		bean.setPLat(model.getPLat());
		bean.setPLong(model.getPLong());
		bean.setPOccupation(model.getPOccupation());
		bean.setPTitle(model.getPTitle());
		bean.setPZipCd(model.getPZipCd());
		bean.setIclubIdType(model.getIclubIdType());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setIclubMaritialStatus(model.getIclubMaritialStatus());

	}

	public void setBean(IclubPersonBean bean) {
		this.bean = bean;
	}

	public List<IclubOccupationBean> getOccupationBeans() {
		WebClient client = IclubWebHelper.createCustomClient(OCN_BASE_URL + "list");
		Collection<? extends IclubOccupationModel> models = new ArrayList<IclubOccupationModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubOccupationModel.class));
		client.close();
		occupationBeans = new ArrayList<IclubOccupationBean>();
		for (IclubOccupationModel model : models) {

			IclubOccupationBean bean = new IclubOccupationBean();

			bean.setOId(model.getOId());
			bean.setODesc(model.getODesc());
			bean.setOCrtdDt(model.getOCrtdDt());
			bean.setOStatus(model.getOStatus());
			bean.setIclubPerson(model.getIclubPerson());

			occupationBeans.add(bean);
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
		return countryCodeBeans;
	}

	public void setCountryCodeBeans(List<IclubCountryCodeBean> countryCodeBeans) {
		this.countryCodeBeans = countryCodeBeans;
	}

	public Double getUpdatePremium(String quoteId, String quoteType, IclubVehicleBean vehicleBean) {
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
					if (tableName.equalsIgnoreCase("iclub_vehicle")) {

						fieldValue = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId());

					}
					if (tableName.equalsIgnoreCase("iclub_property")) {
						IclubInsuranceItemBean insuranceItemBean = setInsuranceItemDetails(quoteId, 2l);
						IclubPropertyBean proeprtyBean = getPropertyDetails(insuranceItemBean.getIiItemId());
						fieldValue = getFieldValueFromDB(fieldName, tableName, proeprtyBean.getPId());
					}
					if (tableName.equalsIgnoreCase("iclub_person")) {
						IclubPersonBean personBean = getIclubPersonBean(quoteBean.getIclubPersonByQPersonId());

						fieldValue = getFieldValueFromDB(fieldName, tableName, personBean.getPId());

					}

					for (IclubRateTypeBean rateTypeBean : rateTypeBeans) {
						List<IclubRateEngineBean> rateEngineBeans = getRateEnginesByRateType(rateTypeBean.getRtId());
						for (IclubRateEngineBean rateEngineBean : rateEngineBeans) {
							if (fieldValue != null) {
								if ((rateTypeBean.getRtType().equalsIgnoreCase("F") && rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(fieldValue.toString()) || (rateTypeBean.getRtType().trim().equalsIgnoreCase("R") && (Double.parseDouble(rateEngineBean.getReBaseValue().trim()) <= Double.parseDouble(fieldValue.toString()) && Double.parseDouble(rateEngineBean.getReMaxValue().trim()) >= Double.parseDouble(fieldValue.toString()))))) {

									premium = premium + baseValue * (rateEngineBean.getReRate() / 100);

								} else if (rateTypeBean.getRtType().equalsIgnoreCase("L")) {
									WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/lookupdetails/" + tableName + "/" + fieldValue.toString());
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
		return premium;
	}

	public IclubInsuranceItemBean setInsuranceItemDetails(String quoteId, Long itemTypeId) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "getByQuoteIdAndItemTypeId/" + quoteId + "/" + itemTypeId);

		IclubInsuranceItemModel model = (IclubInsuranceItemModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubInsuranceItemModel.class));
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

	public String getFieldValueFromDB(String fieldName, String tableName, String id) {

		WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/fieldValue/" + fieldName + "/" + tableName + "/" + id);

		String fieldValue = client.accept(MediaType.APPLICATION_JSON).get(String.class);

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
			vehicleBean.setIclubPurposeType(model.getIclubPurposeType());
			vehicleBean.setIclubSecurityMaster(model.getIclubSecurityMaster());
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
			propertyBean.setIclubPurposeType(model.getIclubPurposeType());
			propertyBean.setIclubOccupiedStatus(model.getIclubOccupiedStatus());
			propertyBean.setIclubPropertyType(model.getIclubPropertyType());
			propertyBean.setIclubWallType(model.getIclubWallType());
			propertyBean.setIclubAccessType(model.getIclubAccessType());
			propertyBean.setIclubPerson(model.getIclubPerson());
			propertyBean.setIclubBarType(model.getIclubBarType());
			propertyBean.setIclubThatchType(model.getIclubThatchType());
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
			String[] documents = new String[bean.getIclubDocuments().length];
			int i = 0;
			for (String iclubDocument : bean.getIclubDocuments()) {
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

	public List<IclubSecurityMasterBean> getSecurityMasterBeans() {

		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "list");
		Collection<? extends IclubSecurityMasterModel> models = new ArrayList<IclubSecurityMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityMasterModel.class));
		client.close();
		securityMasterBeans = new ArrayList<IclubSecurityMasterBean>();
		for (IclubSecurityMasterModel model : models) {

			IclubSecurityMasterBean bean = new IclubSecurityMasterBean();

			bean.setSmId(model.getSmId());
			bean.setSmCrtdDt(model.getSmCrtdDt());
			bean.setSmDesc(model.getSmDesc());
			bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setSmStatus(model.getSmStatus());

			securityMasterBeans.add(bean);
		}
		return securityMasterBeans;
	}

	public void setSecurityMasterBeans(List<IclubSecurityMasterBean> securityMasterBeans) {
		this.securityMasterBeans = securityMasterBeans;
	}

	public List<IclubPurposeTypeBean> getPurposeTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/list/status/" + "1");
		Collection<? extends IclubPurposeTypeModel> models = new ArrayList<IclubPurposeTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPurposeTypeModel.class));
		client.close();
		purposeTypeBeans = new ArrayList<IclubPurposeTypeBean>();
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
			purposeTypeBeans.add(bean);
		}
		return purposeTypeBeans;
	}

	public void setPurposeTypeBeans(List<IclubPurposeTypeBean> purposeTypeBeans) {
		this.purposeTypeBeans = purposeTypeBeans;
	}

	public List<IclubAccessTypeBean> getAccessTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(AEST_BASE_URL + "list");
		Collection<? extends IclubAccessTypeModel> models = new ArrayList<IclubAccessTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubAccessTypeModel.class));
		client.close();
		accessTypeBeans = new ArrayList<IclubAccessTypeBean>();
		for (IclubAccessTypeModel model : models) {
			IclubAccessTypeBean bean = new IclubAccessTypeBean();
			bean.setAtId(model.getAtId());
			bean.setAtLongDesc(model.getAtLongDesc());
			bean.setAtShortDesc(model.getAtShortDesc());
			bean.setAtStatus(model.getAtStatus());
			accessTypeBeans.add(bean);
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
		for (IclubBarTypeModel model : models) {
			IclubBarTypeBean bean = new IclubBarTypeBean();
			bean.setBtId(model.getBtId());
			bean.setBtLongDesc(model.getBtLongDesc());
			bean.setBtShortDesc(model.getBtShortDesc());
			bean.setBtStatus(model.getBtStatus());
			barTypeBeans.add(bean);
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
		for (IclubThatchTypeModel model : models) {
			IclubThatchTypeBean bean = new IclubThatchTypeBean();
			bean.setTtId(model.getTtId());
			bean.setTtLongDesc(model.getTtLongDesc());
			bean.setTtShortDesc(model.getTtShortDesc());
			bean.setTtStatus(model.getTtStatus());
			thatchTypeBeans.add(bean);
		}
		return thatchTypeBeans;
	}

	public void setThatchTypeBeans(List<IclubThatchTypeBean> thatchTypeBeans) {
		this.thatchTypeBeans = thatchTypeBeans;
	}

	public List<IclubPropertyTypeBean> getPropertyTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PROT_BASE_URL + "list");
		Collection<? extends IclubPropertyTypeModel> models = new ArrayList<IclubPropertyTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyTypeModel.class));
		client.close();
		propertyTypeBeans = new ArrayList<IclubPropertyTypeBean>();
		for (IclubPropertyTypeModel model : models) {
			IclubPropertyTypeBean bean = new IclubPropertyTypeBean();
			bean.setPtId(model.getPtId());
			bean.setPtLongDesc(model.getPtLongDesc());
			bean.setPtShortDesc(model.getPtShortDesc());
			bean.setPtStatus(model.getPtStatus());

			propertyTypeBeans.add(bean);
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
		for (IclubOccupiedStatusModel model : models) {
			IclubOccupiedStatusBean bean = new IclubOccupiedStatusBean();
			bean.setOsId(model.getOsId());
			bean.setOsLongDesc(model.getOsLongDesc());
			bean.setOsShortDesc(model.getOsShortDesc());
			bean.setOsStatus(model.getOsStatus());
			occupiedStatusBeans.add(bean);
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

}
