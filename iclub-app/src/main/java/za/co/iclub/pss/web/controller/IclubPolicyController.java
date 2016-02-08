package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import za.co.iclub.pss.model.ui.IclubDocumentBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemBean;
import za.co.iclub.pss.model.ui.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.model.ui.IclubPolicyBean;
import za.co.iclub.pss.model.ui.IclubPropertyBean;
import za.co.iclub.pss.model.ui.IclubPropertyItemBean;
import za.co.iclub.pss.model.ui.IclubSupplMasterBean;
import za.co.iclub.pss.model.ui.IclubVehicleBean;
import za.co.iclub.pss.model.ui.IclubVehicleMasterBean;
import za.co.iclub.pss.model.ws.IclubDocumentModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemModel;
import za.co.iclub.pss.model.ws.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.model.ws.IclubPolicyModel;
import za.co.iclub.pss.model.ws.IclubPropertyItemModel;
import za.co.iclub.pss.model.ws.IclubPropertyModel;
import za.co.iclub.pss.model.ws.IclubSupplItemModel;
import za.co.iclub.pss.model.ws.IclubSupplMasterModel;
import za.co.iclub.pss.model.ws.IclubVehicleMasterModel;
import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.trans.IclubDocumentTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTrans;
import za.co.iclub.pss.trans.IclubInsuranceItemTypeTrans;
import za.co.iclub.pss.trans.IclubPolicyTrans;
import za.co.iclub.pss.trans.IclubPropertyItemTrans;
import za.co.iclub.pss.trans.IclubPropertyTrans;
import za.co.iclub.pss.trans.IclubSupplMasterTrans;
import za.co.iclub.pss.trans.IclubVehicleMasterTrans;
import za.co.iclub.pss.trans.IclubVehicleTrans;
import za.co.iclub.pss.util.IclubWebHelper;
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
	private static final String SM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSupplMasterService/";
	private static final String SI_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubSupplItemService/";
	private static final String PCY_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemService/";
	private static final String V_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleService/";
	private static final String VM_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubVehicleMasterService/";
	private static final String PRO_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyService/";
	private static final String PROI_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubPropertyItemService/";
	private static final String D_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubDocumentService/";
	private static final String IIT_BASE_URL = BUNDLE.getString("ws.protocol") + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + BUNDLE.getString("ws.context") + "/iclub/IclubInsuranceItemTypeService/";
	private boolean viewPolicy;

	private List<IclubPolicyBean> beans;

	private List<IclubPolicyBean> paDashboardBeans;
	private List<IclubPolicyBean> acDashboardBeans;
	private List<IclubPolicyBean> allDashboardBeans;
	private List<IclubSupplMasterBean> dDSupplMasterBeans;
	private List<IclubSupplMasterBean> oNSupplMasterBeans;
	private List<IclubVehicleBean> vehicleBeans;
	private List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans;

	private ResourceBundle labelBundle;

	private boolean iItemFalg;

	private List<IclubInsuranceItemBean> iItemBeans;

	private IclubPolicyBean bean;

	private IclubVehicleBean vehicleBean;

	private IclubSupplMasterBean supplMasterBean;

	private boolean vehhicleFlag;

	private boolean propertyFlag;

	private boolean paVehicleFlag;

	private IclubPropertyBean propertyBean;

	private String sessionUserId;

	private List<IclubDocumentBean> docs;

	private List<String> docIds;

	private StreamedContent file;

	private List<IclubVehicleMasterBean> vBeans;

	private Map<String, String> years;

	private MapModel draggableModelPer;
	private Marker markerPer;
	private String centerGeoMapPer = "-28.4792905,24.6722915";

	private MapModel draggableModelPro;
	private Marker markerPro;
	private String centerGeoMapPro = "-28.4792905,24.6722915";

	private MapModel draggableModelVeh;
	private Marker markerVeh;
	private String centerGeoMapVeh = "-28.4792905,24.6722915";
	private ArrayList<IclubPropertyItemBean> propertyItemBeans;

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
		{
			propertyBean.setPLat(markerPro.getLatlng().getLat());
			propertyBean.setPLong(markerPro.getLatlng().getLng());
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + markerPer.getLatlng().getLat() + ", Lng:" + markerPer.getLatlng().getLng()));
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
		{
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
		propertyBean.setPAddress(markerPro.getTitle());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", markerPro.getTitle()));
	}

	public void onMarkerDragVeh(MarkerDragEvent event) {
		markerVeh = event.getMarker();
		{
			vehicleBean.setVDdLat(markerVeh.getLatlng().getLat());
			vehicleBean.setVDdLong(markerVeh.getLatlng().getLng());
		}
		vehicleBean.setVDdArea(markerVeh.getTitle());
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
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(model);
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
				IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("del.success"), FacesMessage.SEVERITY_INFO);
				clearForm();
			} else {
				IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("del.service.error"), FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("del.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
				IclubPolicyModel model = IclubPolicyTrans.fromUItoWS(bean);
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
					IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				} else {

					IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					return null;

				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);

			IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
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
		propertyFlag = false;
		vehhicleFlag = false;
		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
		bean = policyBean;
		IclubWebHelper.addObjectIntoSession("policyBean", policyBean);
		for (IclubInsuranceItemModel model : models) {
			IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);
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
				vehicleBean = IclubVehicleTrans.fromWStoUI(model);

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
				propertyBean = IclubPropertyTrans.fromWStoUI(model);
				setIclubPropertyItems(propertyBean.getPId());
			}
			client.close();
		} else {
			vehhicleFlag = false;
			propertyFlag = false;

		}
	}

	public void setIclubPropertyItems(String propertyId) {

		WebClient client = IclubWebHelper.createCustomClient(PROI_BASE_URL + "listByProperty/" + propertyId);

		Collection<? extends IclubPropertyItemModel> models = new ArrayList<IclubPropertyItemModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPropertyItemModel.class));
		client.close();
		propertyItemBeans = new ArrayList<IclubPropertyItemBean>();
		if (models != null && models.size() > 0) {
			for (IclubPropertyItemModel model : models) {

				IclubPropertyItemBean bean = IclubPropertyItemTrans.fromWStoUI(model);

				propertyItemBeans.add(bean);
			}

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
		// getDocIds().add(docId);
		try {
			IclubDocumentModel model = new IclubDocumentModel();
			model.setIclubPerson(getSessionUserId());
			model.setDCrtdDt(new Date(System.currentTimeMillis()));
			model.setDId(docId);
			model.setDName(fue.getFile().getFileName());
			model.setDContent(fue.getFile().getContentType());
			model.setDSize(fue.getFile().getSize());
			model.setDEntityId(bean.getPId().toString());
			model.setIclubEntityType(1l);
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
					IclubDocumentBean bean = IclubDocumentTrans.fromWStoUI(model);

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
			dDSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVDdLong(), vehicleBean.getVDdLat());
			oNSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVOnLong(), vehicleBean.getVOnLat());

			return "assignSupplier.xhtml?faces-redirect=true";
		}
		return null;
	}

	public void assignSupplAction() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubSupplItem");
		try {
			if (supplMasterBean != null) {
				WebClient client = IclubWebHelper.createCustomClient(SI_BASE_URL + "add");

				IclubSupplItemModel model = new IclubSupplItemModel();
				model.setSiId(UUID.randomUUID().toString());
				model.setIclubPerson(getSessionUserId());
				model.setSiCrtdDt(new Date(System.currentTimeMillis()));
				model.setSiAssessNumber(IclubWebHelper.getRandomNumber());
				model.setIclubInsuranceItemType(1l);
				model.setSiItemId(vehicleBean.getVId());
				model.setIclubAssessmentType(1l);
				model.setIclubSupplMaster(supplMasterBean.getSmId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/" + bean.getPId());
					IclubPolicyModel pModel = client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class);
					client.close();

					pModel.setPCrtdDt(new Date(System.currentTimeMillis()).toString());
					pModel.setIclubPolicyStatus(4l);
					pModel.setIclubPerson(getSessionUserId());
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "mod");
					response = client.accept(MediaType.APPLICATION_JSON).put(pModel, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0) {
						IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
					} else {

						IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);

					}
				} else {

					IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);

				}
			} else {

				IclubWebHelper.addMessage(getLabelBundle().getString("roletype") + " " + getLabelBundle().getString("mod.error"), FacesMessage.SEVERITY_ERROR);

			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("policy") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}

	public List<IclubSupplMasterBean> getSupplMasterBeans(Double smLong, Double smLat) {

		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "getByLongAndLat/" + smLat + "/" + smLong);
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		ArrayList<IclubSupplMasterBean> supplMasterBeans = new ArrayList<IclubSupplMasterBean>();
		if (models != null && models.size() > 0) {
			for (IclubSupplMasterModel model : models) {

				IclubSupplMasterBean bean = IclubSupplMasterTrans.fromWStoUI(model);

				supplMasterBeans.add(bean);
			}
		}
		return supplMasterBeans;
	}

	public String viewVehicleAction() {

		if (bean != null) {
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
					IclubInsuranceItemBean bean = IclubInsuranceItemTrans.fromWStoUI(model);

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

					IclubVehicleBean vehicleBean = IclubVehicleTrans.fromWStoUI(model);

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
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(model);

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
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(model);

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
					IclubPolicyBean bean = IclubPolicyTrans.fromWStoUI(model);

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

	public IclubSupplMasterBean getSupplMasterBean() {
		if (supplMasterBean == null) {
			supplMasterBean = new IclubSupplMasterBean();
		}
		return supplMasterBean;
	}

	public void setSupplMasterBean(IclubSupplMasterBean supplMasterBean) {
		this.supplMasterBean = supplMasterBean;
	}

	public List<IclubVehicleMasterBean> getvBeans() {

		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "list");
		Collection<? extends IclubVehicleMasterModel> models = new ArrayList<IclubVehicleMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubVehicleMasterModel.class));
		client.close();
		vBeans = new ArrayList<IclubVehicleMasterBean>();
		years = new HashMap<String, String>();
		if (models != null && models.size() > 0) {

			for (IclubVehicleMasterModel model : models) {

				IclubVehicleMasterBean bean = IclubVehicleMasterTrans.fromWStoUI(model);

				if (model != null && model.getVmId() != null && model.getVmId().toString().equalsIgnoreCase(vehicleBean.getIclubVehicleMaster().toString())) {
					Calendar now = Calendar.getInstance();
					int currentYear = now.get(Calendar.YEAR);
					now.setTimeInMillis(model.getVmProdDt().getTime());
					int prodYear = now.get(Calendar.YEAR);
					for (int i = prodYear; i <= currentYear; i++) {
						years.put(i + "", model.getVmId().toString());
					}

				}
				vBeans.add(bean);
			}
		}
		return vBeans;
	}

	public void setvBeans(List<IclubVehicleMasterBean> vBeans) {
		this.vBeans = vBeans;
	}

	public Map<String, String> getYears() {
		return years;
	}

	public void setYears(HashMap<String, String> years) {
		this.years = years;
	}

	public ArrayList<IclubPropertyItemBean> getPropertyItemBeans() {
		return propertyItemBeans;
	}

	public void setPropertyItemBeans(ArrayList<IclubPropertyItemBean> propertyItemBeans) {
		this.propertyItemBeans = propertyItemBeans;
	}

	public List<IclubInsuranceItemTypeBean> getInsuranceItemTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		insuranceItemTypeBeans = new ArrayList<IclubInsuranceItemTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemTypeModel model : models) {
				IclubInsuranceItemTypeBean bean = IclubInsuranceItemTypeTrans.fromWStoUI(model);

				insuranceItemTypeBeans.add(bean);
			}
		}
		return insuranceItemTypeBeans;
	}

	public void setInsuranceItemTypeBeans(List<IclubInsuranceItemTypeBean> insuranceItemTypeBeans) {
		this.insuranceItemTypeBeans = insuranceItemTypeBeans;
	}

}
