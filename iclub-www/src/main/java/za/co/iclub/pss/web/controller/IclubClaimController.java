package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

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
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubDocumentBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemTypeBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPolicyStatusBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubSupplMasterBean;
import za.co.iclub.pss.web.bean.IclubVehicleBean;
import za.co.iclub.pss.web.util.IclubWebHelper;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubDocumentModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPolicyStatusModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubSupplItemModel;
import za.co.iclub.pss.ws.model.IclubSupplMasterModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@ManagedBean(name = "iclubClaimController")
@SessionScoped
public class IclubClaimController implements Serializable {
	
	private static final long serialVersionUID = -1299854691643272437L;
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("iclub-web");
	protected static final Logger LOGGER = Logger.getLogger(IclubClaimController.class);
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";
	private static final String V_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubVehicleService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String D_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubDocumentService/";
	private static final String IIT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemTypeService/";
	private static final String PS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyStatusService/";
	private static final String SM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplMasterService/";
	private static final String SI_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSupplMasterService/";
	
	private List<IclubClaimStatusBean> claimStatusBeans;
	
	private List<IclubInsuranceItemTypeBean> insuranceItemTypebeans;
	
	private List<IclubPolicyStatusBean> policyStatusBeans;
	
	private List<IclubPurposeTypeBean> purposeTypeBeans;
	
	private List<IclubPolicyBean> policyBeans;
	
	private List<IclubClaimBean> beans;
	private List<IclubClaimBean> acBeans;
	private List<IclubClaimBean> allBeans;
	private List<IclubClaimBean> paBeans;
	
	private IclubClaimBean bean;
	
	private IclubPolicyBean policyBean;
	
	private boolean iItemFalg;
	
	private boolean policyFlag;
	
	private List<IclubInsuranceItemBean> iItemBeans;
	
	private ResourceBundle labelBundle;
	
	private IclubVehicleBean vehicleBean;
	
	private boolean vehhicleFlag;
	
	private boolean propertyFlag;
	
	private IclubPropertyBean propertyBean;
	
	private String sessionUserId;
	private List<IclubDocumentBean> docs;
	
	private List<String> docIds;
	
	private StreamedContent file;
	
	private boolean viewClaimFlag;
	private boolean paVehicleFlag;
	private List<IclubClaimBean> acDashboardBeans;
	private List<IclubClaimBean> allDashboardBeans;
	private List<IclubSupplMasterBean> dDSupplMasterBeans;
	private List<IclubSupplMasterBean> oNSupplMasterBeans;
	private List<IclubVehicleBean> vehicleBeans;
	private IclubSupplMasterBean supplMasterBean;
	
	public void viewCalimAction() {
		viewClaimFlag = true;
	}
	
	public String showModPanel() {
		return "edit.xhtml?faces-redirect=true";
	}
	
	public String assignAction() {
		
		if (vehicleBean != null) {
			dDSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVDdLong(), vehicleBean.getVDdLat());
			oNSupplMasterBeans = getSupplMasterBeans(vehicleBean.getVOnLong(), vehicleBean.getVOnLat());
			
			return "assignSupplier.xhtml?faces-redirect=true";
		}
		return null;
	}
	
	public List<IclubSupplMasterBean> getSupplMasterBeans(Double smLong, Double smLat) {
		
		WebClient client = IclubWebHelper.createCustomClient(SM_BASE_URL + "getByLongAndLat/" + smLat + "/" + smLong);
		Collection<? extends IclubSupplMasterModel> models = new ArrayList<IclubSupplMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSupplMasterModel.class));
		client.close();
		ArrayList<IclubSupplMasterBean> supplMasterBeans = new ArrayList<IclubSupplMasterBean>();
		if (models != null && models.size() > 0) {
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
				
				supplMasterBeans.add(bean);
			}
		}
		return supplMasterBeans;
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
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/" + policyBean.getPId());
					IclubPolicyModel pModel = client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class);
					client.close();
					
					pModel.setPCrtdDt(new Date(System.currentTimeMillis()).toString());
					pModel.setIclubPolicyStatus(4l);
					pModel.setIclubPerson(getSessionUserId());
					client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "mod");
					response = client.accept(MediaType.APPLICATION_JSON).put(pModel, ResponseModel.class);
					client.close();
					if (response.getStatusCode() == 0)
						IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.success"), FacesMessage.SEVERITY_INFO);
				} else {
					
					IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
					
				}
				
			} else {
				
				IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error"), FacesMessage.SEVERITY_ERROR);
				
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("claim") + " " + getLabelBundle().getString("mod.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String viewVehicleAction() {
		
		if (bean != null) {
			// bean.get
			paVehicleFlag = true;
			IclubPolicyBean policyBean = new IclubPolicyBean();
			List<IclubInsuranceItemBean> vehicleIItemBeans = getInsuranceItemDetails(policyBean.getIclubQuote(), 1l);
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
	
	@SuppressWarnings("unchecked")
	public String policyListener(IclubPolicyBean policyBean) {
		WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
		iItemFalg = false;
		List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
		iItemBeans = new ArrayList<IclubInsuranceItemBean>();
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
				iItemBeans.add(bean);
				
			}
			iItemFalg = true;
		}
		return null;
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
	
	public String addIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: addIclubClaim");
		try {
			if (validateForm(true)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "add");
				IclubClaimModel model = new IclubClaimModel();
				model.setCId(UUID.randomUUID().toString());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				model.setCValue(bean.getCValue());
				model.setCNumItems(bean.getCNumItems());
				model.setCNumber(getCnumber());
				model.setIclubPolicy(policyBean.getPId());
				model.setIclubClaimStatus(bean.getIclubClaimStatus());
				model.setIclubPerson(getSessionUserId());
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.success"), FacesMessage.SEVERITY_INFO);
					bean = new IclubClaimBean();
				} else {
					IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage(getLabelBundle().getString("claimstatus") + " " + getLabelBundle().getString("add.error") + " :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
			return "";
		}
		return "view.xhtml?faces-redirect=true";
	}
	
	public String modIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: modIclubMbComment");
		try {
			if (validateForm(false)) {
				WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "mod");
				IclubClaimModel model = new IclubClaimModel();
				model.setCId(bean.getCId());
				model.setCCrtdDt(new Date(System.currentTimeMillis()));
				model.setCValue(bean.getCValue());
				model.setCNumItems(bean.getCNumItems());
				model.setCNumber(bean.getCNumber());
				model.setIclubPolicy(bean.getIclubPolicy());
				model.setIclubClaimStatus(bean.getIclubClaimStatus());
				model.setIclubPerson(getSessionUserId());
				
				ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
				client.close();
				if (response.getStatusCode() == 0) {
					for (String doc : getDocIds()) {
						IclubDocumentModel docModel = new IclubDocumentModel();
						docModel.setDId(doc);
						docModel.setDEntityId(model.getCId().toString());
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
		
		return "view.xhtml?faces-redirect=true";
	}
	
	public String delIclubClaim() {
		LOGGER.info("Class :: " + this.getClass() + " :: Method :: delIclubMbComment");
		try {
			WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "del/" + bean.getCId());
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
		return "view.xhtml?faces-redirect=true";
	}
	
	public String clearForm() {
		
		return "view.xhtml?faces-redirect=true";
		
	}
	
	public boolean validateForm(boolean flag) {
		boolean ret = true;
		
		return ret;
	}
	
	public Long getCnumber() {
		Random r = new Random();
		int Low = 1000000;
		int High = 9999999;
		int R = r.nextInt(High - Low) + Low;
		SimpleDateFormat formate = new SimpleDateFormat("YYYYMMDD");
		return Long.parseLong((formate.format(new Date()) + R));
		
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
	
	@SuppressWarnings("unchecked")
	public List<IclubInsuranceItemBean> getiItemBeans() {
		if (IclubWebHelper.getObjectIntoSession("policyBean") != null) {
			
			policyBean = (IclubPolicyBean) IclubWebHelper.getObjectIntoSession("policyBean");
			WebClient client = IclubWebHelper.createCustomClient(II_BASE_URL + "get/quoteId/" + policyBean.getIclubQuote());
			IclubWebHelper.addObjectIntoSession("policyBean", null);
			List<IclubInsuranceItemModel> models = (ArrayList<IclubInsuranceItemModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemModel.class));
			iItemBeans = new ArrayList<IclubInsuranceItemBean>();
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
				iItemBeans.add(bean);
			}
		}
		return iItemBeans;
	}
	
	public void setiItemBeans(List<IclubInsuranceItemBean> iItemBeans) {
		this.iItemBeans = iItemBeans;
	}
	
	public IclubPolicyBean getPolicyBean() {
		
		if (policyBean == null)
			policyBean = new IclubPolicyBean();
		return policyBean;
	}
	
	public void setPolicyBean(IclubPolicyBean policyBean) {
		this.policyBean = policyBean;
	}
	
	public IclubClaimBean getBean() {
		if (bean == null)
			bean = new IclubClaimBean();
		return bean;
	}
	
	public void setBean(IclubClaimBean bean) {
		this.bean = bean;
	}
	
	public ResourceBundle getLabelBundle() {
		
		labelBundle = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "labels");
		return labelBundle;
	}
	
	public void setLabelBundle(ResourceBundle labelBundle) {
		this.labelBundle = labelBundle;
	}
	
	@SuppressWarnings("unchecked")
	public String claimToPolicyListener(IclubClaimBean claimBean) {
		
		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "get/user/" + getSessionUserId());
		
		List<IclubPolicyModel> models = (ArrayList<IclubPolicyModel>) (client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyModel.class));
		
		policyFlag = false;
		if (models != null && models.size() > 0) {
			policyFlag = true;
			policyBeans = new ArrayList<IclubPolicyBean>();
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
					policyBeans.add(bean);
				}
				
			}
		}
		
		return "";
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
	
	public List<IclubClaimBean> getBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/user/" + getSessionUserId());
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		beans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = new IclubClaimBean();
				bean.setCId(model.getCId());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setCValue(model.getCValue());
				bean.setCNumItems(model.getCNumItems());
				bean.setCNumber(model.getCNumber());
				bean.setIclubPolicy(model.getIclubPolicy());
				bean.setIclubClaimStatus(model.getIclubClaimStatus());
				bean.setIclubPerson(model.getIclubPerson());
				
				if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = model.getIclubCohortClaims();
					
					bean.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
					String[] payments = new String[model.getIclubPayments().length];
					int i = 0;
					for (String payment : model.getIclubPayments()) {
						payments[i] = payment;
						i++;
					}
					
					bean.setIclubPayments(payments);
				}
				
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
		return beans;
	}
	
	public void setBeans(List<IclubClaimBean> beans) {
		this.beans = beans;
	}
	
	public List<IclubPolicyBean> getPolicyBeans() {
		
		return policyBeans;
	}
	
	public void setPolicyBeans(List<IclubPolicyBean> policyBeans) {
		this.policyBeans = policyBeans;
	}
	
	public boolean isPolicyFlag() {
		return policyFlag;
	}
	
	public void setPolicyFlag(boolean policyFlag) {
		this.policyFlag = policyFlag;
	}
	
	public boolean isiItemFalg() {
		return iItemFalg;
	}
	
	public void setiItemFalg(boolean iItemFalg) {
		this.iItemFalg = iItemFalg;
	}
	
	public IclubVehicleBean getVehicleBean() {
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
		return propertyBean;
	}
	
	public void setPropertyBean(IclubPropertyBean propertyBean) {
		this.propertyBean = propertyBean;
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
		if (bean != null && bean.getCId() != null) {
			WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "get/entity/" + bean.getCId() + "" + "/1");
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
	
	public List<IclubInsuranceItemTypeBean> getInsuranceItemTypebeans() {
		
		WebClient client = IclubWebHelper.createCustomClient(IIT_BASE_URL + "list");
		Collection<? extends IclubInsuranceItemTypeModel> models = new ArrayList<IclubInsuranceItemTypeModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubInsuranceItemTypeModel.class));
		client.close();
		insuranceItemTypebeans = new ArrayList<IclubInsuranceItemTypeBean>();
		if (models != null && models.size() > 0) {
			for (IclubInsuranceItemTypeModel model : models) {
				IclubInsuranceItemTypeBean bean = new IclubInsuranceItemTypeBean();
				bean.setIitId(model.getIitId());
				bean.setIitLongDesc(model.getIitLongDesc());
				bean.setIitShortDesc(model.getIitShortDesc());
				bean.setIitStatus(model.getIitStatus());
				insuranceItemTypebeans.add(bean);
			}
		}
		return insuranceItemTypebeans;
	}
	
	public void setInsuranceItemTypebeans(List<IclubInsuranceItemTypeBean> insuranceItemTypebeans) {
		this.insuranceItemTypebeans = insuranceItemTypebeans;
	}
	
	public List<IclubPolicyStatusBean> getPolicyStatusBeans() {
		WebClient client = IclubWebHelper.createCustomClient(PS_BASE_URL + "list");
		Collection<? extends IclubPolicyStatusModel> models = new ArrayList<IclubPolicyStatusModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubPolicyStatusModel.class));
		client.close();
		policyStatusBeans = new ArrayList<IclubPolicyStatusBean>();
		if (models != null && models.size() > 0) {
			for (IclubPolicyStatusModel model : models) {
				IclubPolicyStatusBean bean = new IclubPolicyStatusBean();
				bean.setPsId(model.getPsId());
				bean.setPsLongDesc(model.getPsLongDesc());
				bean.setPsShortDesc(model.getPsShortDesc());
				bean.setPsStatus(model.getPsStatus());
				policyStatusBeans.add(bean);
			}
		}
		return policyStatusBeans;
	}
	
	public void setPolicyStatusBeans(List<IclubPolicyStatusBean> policyStatusBeans) {
		this.policyStatusBeans = policyStatusBeans;
	}
	
	public List<IclubPurposeTypeBean> getPurposeTypeBeans() {
		return purposeTypeBeans;
	}
	
	public void setPurposeTypeBeans(List<IclubPurposeTypeBean> purposeTypeBeans) {
		this.purposeTypeBeans = purposeTypeBeans;
	}
	
	public boolean isViewClaimFlag() {
		return viewClaimFlag;
	}
	
	public void setViewClaimFlag(boolean viewClaimFlag) {
		this.viewClaimFlag = viewClaimFlag;
	}
	
	public List<IclubClaimBean> getAcBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/statusId/" + 4);
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		acBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = new IclubClaimBean();
				bean.setCId(model.getCId());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setCValue(model.getCValue());
				bean.setCNumItems(model.getCNumItems());
				bean.setCNumber(model.getCNumber());
				bean.setIclubPolicy(model.getIclubPolicy());
				bean.setIclubClaimStatus(model.getIclubClaimStatus());
				bean.setIclubPerson(model.getIclubPerson());
				
				if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = model.getIclubCohortClaims();
					
					bean.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
					String[] payments = new String[model.getIclubPayments().length];
					int i = 0;
					for (String payment : model.getIclubPayments()) {
						payments[i] = payment;
						i++;
					}
					
					bean.setIclubPayments(payments);
				}
				
				if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
					String[] claimItems = new String[model.getIclubClaimItems().length];
					int i = 0;
					for (String claimItem : model.getIclubClaimItems()) {
						claimItems[i] = claimItem;
						i++;
					}
					bean.setIclubClaimItems(claimItems);
				}
				acBeans.add(bean);
			}
		}
		return acBeans;
	}
	
	public void setAcBeans(List<IclubClaimBean> acBeans) {
		this.acBeans = acBeans;
	}
	
	public List<IclubClaimBean> getAllBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "listOrderByCrtDt");
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		allBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = new IclubClaimBean();
				bean.setCId(model.getCId());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setCValue(model.getCValue());
				bean.setCNumItems(model.getCNumItems());
				bean.setCNumber(model.getCNumber());
				bean.setIclubPolicy(model.getIclubPolicy());
				bean.setIclubClaimStatus(model.getIclubClaimStatus());
				bean.setIclubPerson(model.getIclubPerson());
				
				if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = model.getIclubCohortClaims();
					
					bean.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
					String[] payments = new String[model.getIclubPayments().length];
					int i = 0;
					for (String payment : model.getIclubPayments()) {
						payments[i] = payment;
						i++;
					}
					
					bean.setIclubPayments(payments);
				}
				
				if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
					String[] claimItems = new String[model.getIclubClaimItems().length];
					int i = 0;
					for (String claimItem : model.getIclubClaimItems()) {
						claimItems[i] = claimItem;
						i++;
					}
					bean.setIclubClaimItems(claimItems);
				}
				allBeans.add(bean);
			}
		}
		return allBeans;
	}
	
	public void setAllBeans(List<IclubClaimBean> allBeans) {
		this.allBeans = allBeans;
	}
	
	public List<IclubClaimBean> getPaBeans() {
		WebClient client = IclubWebHelper.createCustomClient(BASE_URL + "get/statusId" + 2);
		Collection<? extends IclubClaimModel> models = new ArrayList<IclubClaimModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubClaimModel.class));
		client.close();
		paBeans = new ArrayList<IclubClaimBean>();
		if (models != null && models.size() > 0) {
			for (IclubClaimModel model : models) {
				IclubClaimBean bean = new IclubClaimBean();
				bean.setCId(model.getCId());
				bean.setCCrtdDt(model.getCCrtdDt());
				bean.setCValue(model.getCValue());
				bean.setCNumItems(model.getCNumItems());
				bean.setCNumber(model.getCNumber());
				bean.setIclubPolicy(model.getIclubPolicy());
				bean.setIclubClaimStatus(model.getIclubClaimStatus());
				bean.setIclubPerson(model.getIclubPerson());
				
				if (model.getIclubCohortClaims() != null && model.getIclubCohortClaims().length > 0) {
					String[] iclubCohortClaims = model.getIclubCohortClaims();
					
					bean.setIclubCohortClaims(iclubCohortClaims);
				}
				
				if (model.getIclubPayments() != null && model.getIclubPayments().length > 0) {
					String[] payments = new String[model.getIclubPayments().length];
					int i = 0;
					for (String payment : model.getIclubPayments()) {
						payments[i] = payment;
						i++;
					}
					
					bean.setIclubPayments(payments);
				}
				
				if (model.getIclubClaimItems() != null && model.getIclubClaimItems().length > 0) {
					String[] claimItems = new String[model.getIclubClaimItems().length];
					int i = 0;
					for (String claimItem : model.getIclubClaimItems()) {
						claimItems[i] = claimItem;
						i++;
					}
					bean.setIclubClaimItems(claimItems);
				}
				paBeans.add(bean);
			}
		}
		return paBeans;
	}
	
	public void setPaBeans(List<IclubClaimBean> paBeans) {
		this.paBeans = paBeans;
	}
	
	public boolean isPaVehicleFlag() {
		return paVehicleFlag;
	}
	
	public void setPaVehicleFlag(boolean paVehicleFlag) {
		this.paVehicleFlag = paVehicleFlag;
	}
	
	public List<IclubClaimBean> getAcDashboardBeans() {
		return acDashboardBeans;
	}
	
	public void setAcDashboardBeans(List<IclubClaimBean> acDashboardBeans) {
		this.acDashboardBeans = acDashboardBeans;
	}
	
	public List<IclubClaimBean> getAllDashboardBeans() {
		return allDashboardBeans;
	}
	
	public void setAllDashboardBeans(List<IclubClaimBean> allDashboardBeans) {
		this.allDashboardBeans = allDashboardBeans;
	}
	
	public List<IclubSupplMasterBean> getdDSupplMasterBeans() {
		return dDSupplMasterBeans;
	}
	
	public void setdDSupplMasterBeans(List<IclubSupplMasterBean> dDSupplMasterBeans) {
		this.dDSupplMasterBeans = dDSupplMasterBeans;
	}
	
	public List<IclubSupplMasterBean> getoNSupplMasterBeans() {
		return oNSupplMasterBeans;
	}
	
	public void setoNSupplMasterBeans(List<IclubSupplMasterBean> oNSupplMasterBeans) {
		this.oNSupplMasterBeans = oNSupplMasterBeans;
	}
	
	public List<IclubVehicleBean> getVehicleBeans() {
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
	
}
