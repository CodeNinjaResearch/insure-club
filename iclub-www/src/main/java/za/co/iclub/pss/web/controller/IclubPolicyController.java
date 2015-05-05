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
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.map.GeocodeEvent;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.GeocodeResult;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import za.co.iclub.pss.web.bean.IclubDocumentBean;
import za.co.iclub.pss.web.bean.IclubGeoLocBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubSupplMasterBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubDocumentModel;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubPolicyController")
@SessionScoped
public class IclubPolicyController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyController.class);
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String V_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String D_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDocumentService/";
	private static final String GL_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubGeoLocService/";
	private boolean viewPolicy;
	
	private List<IclubPolicyBean> beans;
	
	private List<IclubPolicyBean> paDashboardBeans;
	private List<IclubPolicyBean> acDashboardBeans;
	private List<IclubPolicyBean> allDashboardBeans;
	private List<IclubSupplMasterBean> dDSupplMasterBeans;
	private List<IclubSupplMasterBean> oNSupplMasterBeans;
	private List<IclubVehicleBean> vehicleBeans;
	
	private ResourceBundle labelBundle;
	
	private boolean iItemFalg;
	
	private List<IclubInsuranceItemBean> iItemBeans;
	
	private IclubPolicyBean bean;
	
	private IclubVehicleBean vehicleBean;
	
	private boolean vehhicleFlag;
	
	private boolean propertyFlag;
	
	private boolean paVehicleFlag;
	
	private IclubPropertyBean propertyBean;
	
	private String sessionUserId;
	
	private List<IclubDocumentBean> docs;
	
	private List<String> docIds;
	
	private StreamedContent file;
	
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
		IclubGeoLocBean geoBean = getGeoLocBean(markerPro.getLatlng().getLat(), markerPro.getLatlng().getLng());
		if (geoBean.getGlLat() != null && geoBean.getGlLong() != null) {
			propertyBean.setPLat(geoBean.getGlLat());
			propertyBean.setPLong(geoBean.getGlLong());
		} else {
			propertyBean.setPLat(markerPro.getLatlng().getLat());
			propertyBean.setPLong(markerPro.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerPer.getLatlng().getLat() + ", Lng:" + markerPer.getLatlng().getLng()));
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
	
	public void onGeocodePer(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		draggableModelPer = new DefaultMapModel();
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
		propertyBean.setPLat(markerPer.getLatlng().getLat());
		propertyBean.setPLong(markerPer.getLatlng().getLng());
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
			draggableModelPro = new DefaultMapModel();
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
	
	public void onGeocodeVeh(GeocodeEvent event) {
		List<GeocodeResult> results = event.getResults();
		
		if (results != null && !results.isEmpty()) {
			LatLng center = results.get(0).getLatLng();
			centerGeoMapVeh = center.getLat() + "," + center.getLng();
			draggableModelVeh = new DefaultMapModel();
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
	
	@SuppressWarnings("unchecked")
	public List<IclubPolicyBean> getBeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/user/" + getSessionUserId());
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		if (models != null && models.size() > 0) {
			beans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					
					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}
					
					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					beans.add(bean);
				}
				
			}
		}
		
		return beans;
	}
	
	public String delIclubPolicy() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubPolicy");
		try {
			WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "del/" + bean.getPId());
			Response response = client.accept(MediaType.APPLICATION_JSON).get();
			if (response.getStatus() == 200) {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		return "dashboard.xhtml?faces-redirect=true";
	}
	
	public String clearForm() {
		
		return "dashboard.xhtml?faces-redirect=true";
		
	}
	
	public void setBeans(List<IclubPolicyBean> beans) {
		this.beans = beans;
	}
	
	public String showModPanel() {
		
		return "edit.xhtml?faces-redirect=true";
		
	}
	
	public void viewPolicy() {
		viewPolicy = true;
	}
	
	public String modIclubPolicy() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubPolicy");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "mod");
				IclubPolicyModel model = new IclubPolicyModel();
				model.setPId(bean.getPId());
				model.setPProrataPrm(bean.getPProrataPrm());
				model.setPPremium(bean.getPPremium());
				model.setPNumber(bean.getPNumber());
				model.setPDebitDt(bean.getPDebitDt());
				model.setPCrtdDt(bean.getPCrtdDt());
				model.setIclubAccount(bean.getIclubAccount());
				model.setIclubQuote(bean.getIclubQuote());
				model.setIclubPolicyStatus(bean.getIclubPolicyStatus());
				model.setIclubPolicyStatus(bean.getIclubPolicyStatus());
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					for (String doc : getDocIds()) {
						IclubDocumentModel docModel = new IclubDocumentModel();
						docModel.setDId(doc);
						docModel.setDEntityId(model.getPId().toString());
						docModel.setIclubEntityType(1l);
						WebClient docClient = IclubWebHelper.createCustomClient(D_BASE_URL + "mod");
						ResponseModel res = docClient.accept(MediaType.APPLICATION_JSON).put(docModel, ResponseModel.class);
						if (res.getStatusCode() == 0)
							LOGGER.info("Doc Merge Successful :: " + doc);
					}
					docIds = null;
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("thatchtype") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
		
		return "dashboard.xhtml?faces-redirect=true";
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		if (bean.getPPremium() == null) {
			IclubWebHelper.addMessage(("Premium Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getPDebitDt() == null) {
			IclubWebHelper.addMessage(("Debit Date Cannot be empty"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		if (bean.getIclubAccount() == null || bean.getIclubAccount().trim().equalsIgnoreCase("")) {
			IclubWebHelper.addMessage(getLabelBundle().getString("Please select Account"), FacesMessage.SEVERITY_ERROR);
			ret = ret && false;
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	public String policyListener(IclubPolicyBean policyBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
		
		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
		IclubWebHelper.addObjectIntoSession("policyBean", policyBean);
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
			iItemFalg = true;
			iItemBeans.add(bean);
			
		}
		return null;
	}
	
	public String claimAction() {
		
		bean = new IclubPolicyBean();
		beans = new ArrayList<IclubPolicyBean>();
		vehhicleFlag = false;
		propertyFlag = false;
		iItemFalg = false;
		return "claim";
	}
	
	public void iItemListener(IclubInsuranceItemBean bean) {
		if (bean != null && bean.getIclubInsuranceItemType().compareTo(1l) == 0) {
			vehhicleFlag = true;
			propertyFlag = false;
			WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "get/" + bean.getIiItemId());
			
			IclubVehicleModel model = (IclubVehicleModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleModel.class));
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
				if (vehicleBean.getVDdLat() != null && vehicleBean.getVDdLong() != null) {
					centerGeoMapVeh = vehicleBean.getVDdLat() + "," + vehicleBean.getVDdLong();
					LatLng coord = new LatLng(vehicleBean.getVDdLat(), vehicleBean.getVDdLong());
					Marker marker = new Marker(coord, "");
					marker.setDraggable(true);
					draggableModelVeh = new DefaultMapModel();
					draggableModelVeh.addOverlay(marker);
					
				}
				client.close();
			}
		} else if (bean != null && bean.getIclubInsuranceItemType().compareTo(2l) == 0) {
			vehhicleFlag = false;
			propertyFlag = true;
			
			WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + bean.getIiItemId());
			IclubPropertyModel model = (IclubPropertyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPropertyModel.class));
			
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
		} else {
			vehhicleFlag = false;
			propertyFlag = false;
			
		}
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
	
	public List<IclubInsuranceItemBean> getiItemBeans() {
		return iItemBeans;
	}
	
	public void setiItemBeans(List<IclubInsuranceItemBean> iItemBeans) {
		this.iItemBeans = iItemBeans;
	}
	
	public boolean isiItemFalg() {
		return iItemFalg;
	}
	
	public void setiItemFalg(boolean iItemFalg) {
		this.iItemFalg = iItemFalg;
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
	
	public boolean isVehhicleFlag() {
		return vehhicleFlag;
	}
	
	public void setVehhicleFlag(boolean vehhicleFlag) {
		this.vehhicleFlag = vehhicleFlag;
	}
	
	public boolean isPropertyFlag() {
		return propertyFlag;
	}
	
	public void setPropertyFlag(boolean propertyFlag) {
		this.propertyFlag = propertyFlag;
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
	
	public IclubPolicyBean getBean() {
		if (bean == null) {
			bean = new IclubPolicyBean();
		}
		return bean;
	}
	
	public void setBean(IclubPolicyBean bean) {
		this.bean = bean;
	}
	
	public ResourceBundle getLabelBundle() {
		
		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	public void showDocumentUpload() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showDocumentUpload");
		if (getDocIds().size() != 0) {
			for (String doc : getDocIds()) {
				WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "del/" + doc);
				client.get();
				client.close();
			}
		}
		getDocIds().clear();
	}
	
	public void handleFileUpload(FileUploadEvent fue) {
		String docId = UUID.randomUUID().toString();
		getDocIds().add(docId);
		try {
			IclubDocumentModel model = new IclubDocumentModel();
			model.setIclubPerson(getSessionUserId());
			model.setDCrtdDt(new Date(System.currentTimeMillis()));
			model.setDId(docId);
			model.setDName(fue.getFile().getFileName());
			model.setDContent(fue.getFile().getContentType());
			model.setDSize(fue.getFile().getSize());
			
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "add");
			ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			client.close();
			
			if (response.getStatusCode() == 0) {
				ContentDisposition cd = new ContentDisposition("attachment;filename=" + fue.getFile().getFileName() + ";filetype=" + fue.getFile().getContentType());
				List<Attachment> attachments = new ArrayList<Attachment>();
				Attachment attachment = new Attachment(docId, fue.getFile().getInputstream(), cd);
				attachments.add(attachment);
				
				WebClient uploadClient = WebClient.create(D_BASE_URL + "upload");
				Response res = uploadClient.type("multipart/form-data").post(new MultipartBody(attachments));
				uploadClient.close();
				
				if (res.getStatus() == 200) {
					IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadedsuccessfully"), FacesMessage.SEVERITY_INFO);
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingfailed") + " :: " + (res.getHeaderString("status") != null ? res.getHeaderString("status") : res.getStatusInfo()), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void downloadDocument(String selDocId) {
		try {
			WebClient client = WebClient.create(D_BASE_URL + "download/" + selDocId);
			client.type("multipart/form-data").accept(MediaType.MULTIPART_FORM_DATA);
			List<Attachment> attachments = (List<Attachment>) client.getCollection(Attachment.class);
			file = new DefaultStreamedContent(attachments.get(0).getDataHandler().getInputStream(), attachments.get(0).getContentDisposition().getParameter("filetype"), attachments.get(0).getContentDisposition().getParameter("filename"));
			client.close();
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deleteDocument(String selDocId) {
		try {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "del/" + selDocId);
			client.get();
			client.close();
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("doucmentuploadingerror") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public List<IclubDocumentBean> getDocs() {
		if (bean != null && bean.getPId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "get/entity/" + bean.getPId() + "" + "/1");
			Collection<? extends IclubDocumentModel> models = new ArrayList<IclubDocumentModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubDocumentModel.class));
			client.close();
			docs = new ArrayList<IclubDocumentBean>();
			if (models != null && models.size() > 0) {
				for (IclubDocumentModel model : models) {
					IclubDocumentBean bean = new IclubDocumentBean();
					bean.setDId(model.getDId());
					bean.setDContent(model.getDContent());
					bean.setDEntityId(model.getDEntityId());
					bean.setDSize(model.getDSize());
					bean.setDMimeType(model.getDMimeType());
					bean.setDName(model.getDName());
					bean.setDCrtdDt(model.getDCrtdDt());
					bean.setIclubDocumentType(model.getIclubDocumentType());
					bean.setIclubEntityType(model.getIclubEntityType());
					bean.setIclubPerson(model.getIclubPerson());
					
					docs.add(bean);
				}
			}
		} else {
			docs = new ArrayList<IclubDocumentBean>();
		}
		return docs;
	}
	
	public void setDocs(List<IclubDocumentBean> docs) {
		this.docs = docs;
	}
	
	public StreamedContent getFile() {
		return file;
	}
	
	public void setFile(StreamedContent file) {
		this.file = file;
	}
	
	public List<String> getDocIds() {
		if (docIds == null) {
			docIds = new ArrayList<String>();
		}
		
		return docIds;
	}
	
	public void setDocIds(List<String> docIds) {
		this.docIds = docIds;
	}
	
	public boolean isViewPolicy() {
		return viewPolicy;
	}
	
	public String assignAction() {
		
		if (vehicleBean != null) {
			
		}
		return null;
	}
	
	public String viewVehicleAction() {
		
		if (bean != null) {
			// bean.get
			paVehicleFlag = true;
			List<IclubInsuranceItemBean> vehicleIItemBeans = getInsuranceItemDetails(bean.getIclubQuote(), 1l);
			setVehicleDetails(vehicleIItemBeans);
			
		}
		return null;
	}
	
	public List<IclubInsuranceItemBean> getInsuranceItemDetails(String quoteId, Long itemTypeId) {
		
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
	
	public void setVehicleDetails(List<IclubInsuranceItemBean> vehilcItemBeans) {
		
		vehicleBeans = new ArrayList<IclubVehicleBean>();
		if (vehilcItemBeans != null) {
			
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
					vehicleBean.setIclubPurposeType(model.getIclubPurposeType());
					vehicleBean.setIclubSecurityMaster(model.getIclubSecurityMaster());
					vehicleBean.setIclubPerson(model.getIclubPerson());
					vehicleBean.setIclubDriver(model.getIclubDriver());
					vehicleBean.setIclubSecurityDevice(model.getIclubSecurityDevice());
					vehicleBean.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId());
					vehicleBean.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId());
					
					vehicleBeans.add(vehicleBean);
					
				} catch (Exception e) {
					LOGGER.error(e, e);
					IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
				}
				
			}
		}
		
	}
	
	public String verifyAction() {
		return null;
	}
	
	public void setViewPolicy(boolean viewPolicy) {
		this.viewPolicy = viewPolicy;
	}
	
	@SuppressWarnings("unchecked")
	public List<IclubPolicyBean> getPaDashboardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/policyStauts/" + 2);
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		if (models != null && models.size() > 0) {
			paDashboardBeans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					
					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}
					
					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					paDashboardBeans.add(bean);
				}
				
			}
		}
		
		return paDashboardBeans;
	}
	
	public void setPaDashboardBeans(List<IclubPolicyBean> paDashboardBeans) {
		this.paDashboardBeans = paDashboardBeans;
	}
	
	@SuppressWarnings("unchecked")
	public List<IclubPolicyBean> getAcDashboardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "/get/policyStauts/" + 4);
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		if (models != null && models.size() > 0) {
			acDashboardBeans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					
					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}
					
					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					acDashboardBeans.add(bean);
				}
				
			}
		}
		
		return acDashboardBeans;
	}
	
	public void setAcDashboardBeans(List<IclubPolicyBean> acDashboardBeans) {
		this.acDashboardBeans = acDashboardBeans;
	}
	
	@SuppressWarnings("unchecked")
	public List<IclubPolicyBean> getAllDashboardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/createdate");
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		if (models != null && models.size() > 0) {
			allDashboardBeans = new ArrayList<IclubPolicyBean>();
			for (IclubPolicyModel model : models) {
				if (model != null && model.getPId() != null) {
					IclubPolicyBean bean = new IclubPolicyBean();
					bean.setPId(model.getPId());
					bean.setPProrataPrm(model.getPProrataPrm());
					bean.setPPremium(model.getPPremium());
					bean.setPNumber(model.getPNumber());
					bean.setPDebitDt(model.getPDebitDt());
					bean.setPCrtdDt(model.getPCrtdDt());
					bean.setIclubAccount(model.getIclubAccount());
					bean.setIclubQuote(model.getIclubQuote());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					bean.setIclubPerson(model.getIclubPerson());
					bean.setIclubPolicyStatus(model.getIclubPolicyStatus());
					
					if (model.getIclubClaims() != null && model.getIclubClaims().length > 0) {
						String[] claims = new String[model.getIclubClaims().length];
						int i = 0;
						for (String claim : model.getIclubClaims()) {
							claims[i] = claim;
							i++;
						}
						bean.setIclubClaims(claims);
					}
					
					if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
						String[] payments = new String[model.getIclubPayments().length];
						int i = 0;
						for (String payment : model.getIclubPayments()) {
							payments[i] = payment;
							i++;
						}
						bean.setIclubClaims(payments);
					}
					allDashboardBeans.add(bean);
				}
				
			}
		}
		return allDashboardBeans;
	}
	
	public void setAllDashboardBeans(List<IclubPolicyBean> allDashboardBeans) {
		this.allDashboardBeans = allDashboardBeans;
	}
	
	public List<IclubSupplMasterBean> getdDSupplMasterBeans() {
		if (dDSupplMasterBeans == null) {
			dDSupplMasterBeans = new ArrayList<IclubSupplMasterBean>();
		}
		return dDSupplMasterBeans;
	}
	
	public void setdDSupplMasterBeans(List<IclubSupplMasterBean> dDSupplMasterBeans) {
		this.dDSupplMasterBeans = dDSupplMasterBeans;
	}
	
	public List<IclubSupplMasterBean> getoNSupplMasterBeans() {
		if (oNSupplMasterBeans == null) {
			oNSupplMasterBeans = new ArrayList<IclubSupplMasterBean>();
		}
		return oNSupplMasterBeans;
	}
	
	public void setoNSupplMasterBeans(List<IclubSupplMasterBean> oNSupplMasterBeans) {
		this.oNSupplMasterBeans = oNSupplMasterBeans;
	}
	
	public boolean isPaVehicleFlag() {
		return paVehicleFlag;
	}
	
	public void setPaVehicleFlag(boolean paVehicleFlag) {
		this.paVehicleFlag = paVehicleFlag;
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
	
}
