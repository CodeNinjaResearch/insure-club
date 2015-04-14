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

import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubEntityTypeBean;
import za.co.iclub.pss.web.bean.IclubFieldBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.bean.IclubRateEngineBean;
import za.co.iclub.pss.web.bean.IclubRateTypeBean;
import za.co.iclub.pss.web.bean.IclubRoofTypeBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.bean.IclubVehicleMasterBean;
import za.co.iclub.pss.web.bean.IclubWallTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.IclubRateEngineModel;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
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

	private List<String> vmMakes;
	private IclubVehicleMasterBean vehicleMasterBean;
	private IclubPersonBean personBean;
	private IclubPropertyBean propertyBean;
	private List<IclubMaritialStatusBean> maritialStatusBeans;
	private List<IclubIdTypeBean> idTypeBeans;
	private List<IclubVehicleMasterBean> vBeans;
	private List<IclubPurposeTypeBean> pBeans;
	private List<IclubLicenseCodeBean> licenseCodeBeans;
	private List<IclubWallTypeBean> wallTypeBeans;
	private List<IclubRoofTypeBean> roofTypeBeans;
	private IclubDriverBean driverBean;
	private List<String> years;
	private String sessionUserId;
	private IclubVehicleBean vehicleBean;
	private String vmMake;
	private String claimYN;
	private ResourceBundle labelBundle;
	private boolean termsAndConditionFlag;

	private MapModel draggableModelPer;
	private Marker markerPer;
	private String centerGeoMapPer = "36.890257,30.707417";

	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "36.890257,30.707417";

	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "36.890257,30.707417";

	@PostConstruct
	public void init() {
		draggableModelPer = new DefaultMapModel();
		draggableModelPro = new DefaultMapModel();
		draggableModelVeh = new DefaultMapModel();

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

		personBean.setPLat(markerPer.getLatlng().getLat());
		personBean.setPLong(markerPer.getLatlng().getLng());
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
		personBean.setPLat(markerPer.getLatlng().getLat());
		personBean.setPLong(markerPer.getLatlng().getLng());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPer.getTitle()));
	}

	public void onMarkerDragPro(MarkerDragEvent event) {
		markerPro = event.getMarker();
		propertyBean.setPLat(markerPro.getLatlng().getLat());
		propertyBean.setPLong(markerPro.getLatlng().getLng());
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
		propertyBean.setPLat(markerPro.getLatlng().getLat());
		propertyBean.setPLong(markerPro.getLatlng().getLng());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}

	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		vehicleBean.setVDdLat(markerVeh.getLatlng().getLat());
		vehicleBean.setVDdLong(markerVeh.getLatlng().getLng());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerVeh.getLatlng().getLat() + ", Lng:" + markerVeh.getLatlng().getLng()));
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

		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "add");

		IclubPersonModel model = new IclubPersonModel();
		model.setPId(UUID.randomUUID().toString());
		model.setPCrtdDt(personBean.getPCrtdDt());
		model.setPDob(personBean.getPDob());
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

	public ResponseModel addVehicle(IclubVehicleBean bean, IclubDriverModel driverModel, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "add");

		IclubVehicleModel model = new IclubVehicleModel();

		model.setVId(UUID.randomUUID().toString());
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
		model.setVCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setVRegNum(bean.getVRegNum());
		model.setVEngineNr(bean.getVEngineNr());
		model.setVVin(bean.getVVin());
		model.setVNoclaimYrs(bean.getVNoclaimYrs());
		model.setIclubVehicleMaster(bean.getIclubVehicleMaster());
		model.setIclubPurposeType(bean.getIclubPurposeType());
		model.setIclubSecurityMaster(bean.getIclubSecurityMaster());
		model.setIclubPerson(getSessionUserId());
		model.setIclubDriver(driverModel.getDId());
		model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
		model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
		model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			addInsuranceItem(model.getVId(), quoteModel.getQId(), 1l, getSessionUserId());

			quoteModel.setQGenPremium(getUpdatePremium(quoteModel.getQId(), "Q"));
			client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "mod");

			response = client.accept(MediaType.APPLICATION_JSON).put(quoteModel, ResponseModel.class);
			client.close();
			if (response != null && response.getStatusCode() == 0) {
				IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);
			} else {
				IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
			}

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addPropertiy(IclubPropertyBean bean, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "add");

		IclubPropertyModel model = new IclubPropertyModel();

		model.setPId(UUID.randomUUID().toString());
		model.setPCrtdDt(new Timestamp(System.currentTimeMillis()));
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

		return response;

	}

	public ResponseModel addInsuranceItem(String itemId, String quoteId, Long iItemType, String userId) {
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

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "add");
		IclubQuoteModel model = new IclubQuoteModel();
		model.setQId(UUID.randomUUID().toString());
		model.setQCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setQIsMatched("N");
		model.setQPrevPremium(0.0d);
		model.setQValidUntil(new Timestamp(System.currentTimeMillis()));
		model.setQMobile(personModel.getPMobile());
		model.setQEmail(personModel.getPEmail());
		model.setQGenPremium(0.0d);
		model.setQNumItems(2);
		model.setQGenDt(new Timestamp(System.currentTimeMillis()));

		model.setQNumber(getQnumber());
		model.setIclubPersonByQCrtdBy(getSessionUserId());
		model.setIclubProductType(bean.getIclubProductType());
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setIclubQuoteStatus(bean.getIclubQuoteStatus());
		model.setIclubPersonByQPersonId(personModel.getPId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();
		if (response.getStatusCode() == 0) {

			addPropertiy(propertyBean, model);

			addDriver(driverBean, personModel, model);
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

			addVehicle(vehicleBean, model, quoteModel);

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
		if (vehicleBean.getIclubVehicleMaster() == null) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.ms.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (vmMake == null || vmMake.trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.vmmake.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}

		if (vehicleBean.getVYear() == null) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.vyear.empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (claimYN == null || claimYN.trim().equalsIgnoreCase("")) {

			IclubWebHelper.addMessage(getLabelBundle().getString("quote.val.claimyn.empty"), FacesMessage.SEVERITY_ERROR);
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

	public Double getUpdatePremium(String quoteId, String quoteType) {
		List<IclubFieldBean> fieldBeans = new ArrayList<IclubFieldBean>();
		IclubQuoteBean quoteBean = getQuoteDetailsById(quoteId);
		Long baseValue = 100l;
		Double premium = quoteBean.getQGenPremium();
		for (IclubFieldBean fieldBean : fieldBeans) {
			if (fieldBean.getFRate() != null && fieldBean.getFStatus().equalsIgnoreCase("Y")) {

				IclubEntityTypeBean entityType = getEntityType(fieldBean.getIclubEntityType());
				String tableName = entityType.getEtTblNm();
				String fieldName = fieldBean.getFName();
				if (tableName != null) {
					List<IclubRateTypeBean> rateTypeBeans = getRateTypeBeanByFieldId(fieldBean.getFId(), quoteType);
					Object obj = null;
					if (tableName.equalsIgnoreCase("iclub_vehicle")) {
						IclubInsuranceItemBean insuranceItemBean = setInsuranceItemDetails(quoteId, 1l);
						IclubVehicleBean vehicleBean = getVehicleDetails(insuranceItemBean.getIiItemId());
						obj = getFieldValueFromDB(fieldName, tableName, vehicleBean.getVId());

					}
					if (tableName.equalsIgnoreCase("iclub_property")) {
						IclubInsuranceItemBean insuranceItemBean = setInsuranceItemDetails(quoteId, 2l);
						IclubPropertyBean proeprtyBean = getPropertyDetails(insuranceItemBean.getIiItemId());
						obj = getFieldValueFromDB(fieldName, tableName, proeprtyBean.getPId());
					}
					if (tableName.equalsIgnoreCase("iclub_person")) {
						IclubPersonBean personBean = getIclubPersonBean(quoteBean.getIclubPersonByQPersonId());

						obj = getFieldValueFromDB(fieldName, tableName, personBean.getPId());

					}

					for (IclubRateTypeBean rateTypeBean : rateTypeBeans) {
						List<IclubRateEngineBean> rateEngineBeans = getRateEnginesByRateType(rateTypeBean.getRtId());
						for (IclubRateEngineBean rateEngineBean : rateEngineBeans) {
							if (obj instanceof Long) {
								Long fieldValue = (Long) obj;
								if ((rateTypeBean.getRtType().equalsIgnoreCase("F") && rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(fieldValue.toString()) || (rateTypeBean.getRtType().trim().equalsIgnoreCase("R") && (Double.parseDouble(rateEngineBean.getReBaseValue().trim()) <= Double.parseDouble(fieldValue.toString()) && Double.parseDouble(rateEngineBean.getReMaxValue().trim()) >= Double.parseDouble(fieldValue.toString()))))) {

									premium = premium + baseValue * rateEngineBean.getReRate();

								} else if (rateTypeBean.getRtType().equalsIgnoreCase("L")) {
									WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/lookupdetails/" + tableName + "/" + obj.toString());
									String lookupDetails = client.accept(MediaType.APPLICATION_JSON).get(String.class);
									if (rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(lookupDetails)) {
										premium = premium + baseValue * rateEngineBean.getReRate();
									}
								}

							} else if (obj instanceof Double) {
								Double fieldValue = (Double) obj;
								if ((rateTypeBean.getRtType().equalsIgnoreCase("F") && rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(fieldValue.toString()) || (rateTypeBean.getRtType().trim().equalsIgnoreCase("R") && (Double.parseDouble(rateEngineBean.getReBaseValue().trim()) <= Double.parseDouble(fieldValue.toString()) && Double.parseDouble(rateEngineBean.getReMaxValue().trim()) >= Double.parseDouble(fieldValue.toString()))))) {
									premium = premium + baseValue * rateEngineBean.getReRate();
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

		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "get/rateType/" + rateType);
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

	public Object getFieldValueFromDB(String fieldName, String tableName, String id) {

		WebClient client = IclubWebHelper.createCustomClient(RE_BASE_URL + "get/fieldValue/" + fieldName + "/" + tableName + "/" + id);

		Object obj = client.accept(MediaType.APPLICATION_JSON).get();

		return obj;

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

		return new IclubEntityTypeBean();

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

}
