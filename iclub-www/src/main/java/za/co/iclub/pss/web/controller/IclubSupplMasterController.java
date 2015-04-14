package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
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

import za.co.iclub.pss.web.bean.IclubSupplMasterBean;
import za.co.iclub.pss.web.bean.IclubSupplierTypeBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubSupplMasterModel;
import za.co.iclub.pss.ws.model.IclubSupplierTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubSupplMasterController")
@SessionScoped
public class IclubSupplMasterController implements Serializable {

	private static final long serialVersionUID = 2776100159112333991L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubSupplMasterController.class);
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplMasterService/";
	private static final String ST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplierTypeService/";
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
		viewParam = 1l;
	}

	public void showCreate() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: showCreate");
		bean = new IclubSupplMasterBean();
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

	public List<IclubSupplMasterBean> getDashBoardBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "/get/user/" + getSessionUserId());
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		dashBoardBeans = new ArrayList<IclubSupplMasterBean>();
		for (IclubSupplMasterModel model : models) {
			IclubSupplMasterBean bean = new IclubSupplMasterBean();

			bean.setSmId(model.getSmId());
			bean.setSmCrtdDt(model.getSmCrtdDt());
			bean.setIclubSupplierType(model.getIclubSupplierType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setSmRating(model.getSmRating());
			bean.setSrActionDt(model.getSrActionDt());
			bean.setSmLong(model.getSmLong());
			bean.setSmCrLimit(model.getSmCrLimit());
			bean.setSmAddress(model.getSmAddress());
			bean.setSmRegNum(model.getSmRegNum());
			bean.setSmTradeName(model.getSmTradeName());
			bean.setSmLat(model.getSmLat());
			bean.setSmName(model.getSmName());

			if (model.getIclubClaimItemsForCiAssesorId() != null && model.getIclubClaimItemsForCiAssesorId().length > 0) {
				String[] claimItemsForCiAssesorIds = new String[model.getIclubClaimItemsForCiAssesorId().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItemsForCiAssesorId()) {
					claimItemsForCiAssesorIds[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItemsForCiAssesorId(claimItemsForCiAssesorIds);
			}

			if (model.getIclubClaimItemsForCiHandlerId() != null && model.getIclubClaimItemsForCiHandlerId().length > 0) {
				String[] claimItemsForCiHandlerIds = new String[model.getIclubClaimItemsForCiHandlerId().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItemsForCiHandlerId()) {
					claimItemsForCiHandlerIds[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItemsForCiHandlerId(claimItemsForCiHandlerIds);
			}

			dashBoardBeans.add(bean);
		}
		return dashBoardBeans;
	}

	public void setDashBoardBeans(List<IclubSupplMasterBean> dashBoardBeans) {
		this.dashBoardBeans = dashBoardBeans;
	}

	public void clearForm() {
		showCreateCont = false;
		showEditCont = false;
		bean = new IclubSupplMasterBean();
	}

	public void addIclubSupplMaster() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubSupplMaster");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubSupplMasterModel model = new IclubSupplMasterModel();

				model.setSmId(UUID.randomUUID().toString());
				model.setSmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubSupplierType(bean.getIclubSupplierType());
				model.setSmRating(bean.getSmRating());
				model.setSrActionDt(bean.getSrActionDt());
				model.setSmLong(bean.getSmLong());
				model.setSmCrLimit(bean.getSmCrLimit());
				model.setSmAddress(bean.getSmAddress());
				model.setSmRegNum(bean.getSmRegNum());
				model.setSmTradeName(bean.getSmTradeName());
				model.setSmLat(bean.getSmLat());
				model.setSmName(bean.getSmName());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {

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
				IclubSupplMasterModel model = new IclubSupplMasterModel();

				model.setSmId(bean.getSmId());
				model.setSmCrtdDt(new Timestamp(System.currentTimeMillis()));
				model.setIclubSupplierType(bean.getIclubSupplierType());
				model.setSmRating(bean.getSmRating());
				model.setSrActionDt(bean.getSrActionDt());
				model.setSmLong(bean.getSmLong());
				model.setSmCrLimit(bean.getSmCrLimit());
				model.setSmAddress(bean.getSmAddress());
				model.setSmRegNum(bean.getSmRegNum());
				model.setSmTradeName(bean.getSmTradeName());
				model.setSmLat(bean.getSmLat());
				model.setSmName(bean.getSmName());
				model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
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
		sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		if (sessionUserId == null)
			sessionUserId = "1";
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
		for (IclubSupplMasterModel model : models) {

			IclubSupplMasterBean bean = new IclubSupplMasterBean();

			bean.setSmId(model.getSmId());
			bean.setSmCrtdDt(model.getSmCrtdDt());
			bean.setIclubSupplierType(model.getIclubSupplierType());
			bean.setIclubPerson(model.getIclubPerson());
			bean.setSmRating(model.getSmRating());
			bean.setSrActionDt(model.getSrActionDt());
			bean.setSmLong(model.getSmLong());
			bean.setSmCrLimit(model.getSmCrLimit());
			bean.setSmAddress(model.getSmAddress());
			bean.setSmRegNum(model.getSmRegNum());
			bean.setSmTradeName(model.getSmTradeName());

			bean.setSmLat(model.getSmLat());
			bean.setSmName(model.getSmName());

			if (model.getIclubClaimItemsForCiAssesorId() != null && model.getIclubClaimItemsForCiAssesorId().length > 0) {
				String[] claimItemsForCiAssesorIds = new String[model.getIclubClaimItemsForCiAssesorId().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItemsForCiAssesorId()) {
					claimItemsForCiAssesorIds[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItemsForCiAssesorId(claimItemsForCiAssesorIds);
			}

			if (model.getIclubClaimItemsForCiHandlerId() != null && model.getIclubClaimItemsForCiHandlerId().length > 0) {
				String[] claimItemsForCiHandlerIds = new String[model.getIclubClaimItemsForCiHandlerId().length];
				int i = 0;
				for (String claimItem : model.getIclubClaimItemsForCiHandlerId()) {
					claimItemsForCiHandlerIds[i] = claimItem;
					i++;
				}
				bean.setIclubClaimItemsForCiHandlerId(claimItemsForCiHandlerIds);
			}

			beans.add(bean);
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
		for (IclubSupplierTypeModel model : models) {
			IclubSupplierTypeBean bean = new IclubSupplierTypeBean();
			bean.setStId(model.getStId());
			bean.setStLongDesc(model.getStLongDesc());
			bean.setStShortDesc(model.getStShortDesc());
			bean.setStStatus(model.getStStatus());
			supplierTypeBeans.add(bean);
		}
		return supplierTypeBeans;
	}

	public void setSupplierTypeBeans(List<IclubSupplierTypeBean> supplierTypeBeans) {
		this.supplierTypeBeans = supplierTypeBeans;
	}

}
