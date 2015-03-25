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

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.log4j.Logger;

import za.co.iclub.pss.web.bean.IclubAccessTypeBean;
import za.co.iclub.pss.web.bean.IclubAccountBean;
import za.co.iclub.pss.web.bean.IclubAccountTypeBean;
import za.co.iclub.pss.web.bean.IclubBankMasterBean;
import za.co.iclub.pss.web.bean.IclubBarTypeBean;
import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubExtrasBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubInsuranceItemBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubOccupiedStatusBean;
import za.co.iclub.pss.web.bean.IclubOwnerTypeBean;
import za.co.iclub.pss.web.bean.IclubPersonBean;
import za.co.iclub.pss.web.bean.IclubPolicyBean;
import za.co.iclub.pss.web.bean.IclubPropertyBean;
import za.co.iclub.pss.web.bean.IclubPropertyTypeBean;
import za.co.iclub.pss.web.bean.IclubPurposeTypeBean;
import za.co.iclub.pss.web.bean.IclubQuoteBean;
import za.co.iclub.pss.web.bean.IclubRoofTypeBean;
import za.co.iclub.pss.web.bean.IclubSecurityDeviceBean;
import za.co.iclub.pss.web.bean.IclubSecurityMasterBean;
import za.co.iclub.pss.web.bean.IclubThatchTypeBean;
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
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubExtrasModel;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
import za.co.iclub.pss.ws.model.IclubOwnerTypeModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.IclubPolicyModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubPropertyTypeModel;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
import za.co.iclub.pss.ws.model.IclubSecurityDeviceModel;
import za.co.iclub.pss.ws.model.IclubSecurityMasterModel;
import za.co.iclub.pss.ws.model.IclubThatchTypeModel;
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
	private static final String SM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityMasterService/";
	private static final String AEST_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccessTypeService/";
	private static final String SD_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityDeviceService/";
	private static final String EXTS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubSecurityDeviceService/";
	private static final String TT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubThatchTypeService/";
	private static final String BT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBarTypeService/";
	private static final String PROT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyTypeService/";
	private static final String OCCS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOccupiedStatusService/";
	private static final String PRO_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPropertyService/";
	private static final String CT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubCoverTypeService/";
	private static final String CS_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimStatusService/";
	private static final String CLM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubClaimService/";
	private static final String PCY_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubPolicyService/";
	private static final String QUT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubQuoteService/";
	private static final String ACC_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountService/";
	private static final String II_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubInsuranceItemService/";
	private static final String OWNT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubOwnerTypeService/";
	private static final String ACCT_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubAccountTypeService/";
	private static final String BNKM_BASE_URL = "http://" + BUNDLE.getString("ws.host") + ":" + BUNDLE.getString("ws.port") + "/iclub-ws/iclub/IclubBankMasterService/";
	private List<IclubSecurityMasterBean> securityMasterBeans;

	private List<IclubBankMasterBean> bankMasterBeans;

	private List<IclubAccountTypeBean> accountTypeBeans;

	private List<IclubOwnerTypeBean> ownerTypeBeans;

	private List<IclubAccessTypeBean> accessTypeBeans;

	private List<IclubSecurityDeviceBean> securityDeviceBeans;

	private List<IclubCoverTypeBean> coverTypeBeans;

	private List<IclubClaimStatusBean> claimStatusBeans;

	private List<IclubThatchTypeBean> thatchTypeBeans;

	private List<IclubOccupiedStatusBean> occupiedStatusBeans;

	private List<IclubBarTypeBean> barTypeBeans;

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

	private List<IclubPurposeTypeBean> purposeTypeBeans;

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

	private IclubInsuranceItemBean vehicleIItemBean;

	private IclubInsuranceItemBean propertyIItemBean;

	private IclubPolicyBean policyBean;

	private String claimYN;

	public void initializePage() {

	}

	public void vmMakeValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			loadVmModels(valueChangeEvent.getNewValue().toString());

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
	}

	public void vmModelValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			loadYears(valueChangeEvent.getNewValue().toString());

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

	public void saveFullQuoteDetails() {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: saveQuickQuoteDetails");
		try {
			addPerson(personBean);
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

	}

	public ResponseModel addPerson(IclubPersonBean personBean) {

		WebClient client = IclubWebHelper.createCustomClient(PER_BASE_URL + "mod");

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

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			addQuote(quoteBean, model);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addVehicle(IclubVehicleBean bean, IclubDriverModel driverModel, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "mod");

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

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			// addInsuranceItem(model.getVId(), quoteModel.getQId(), 1l,
			// getSessionUserId());

			IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addDriver(IclubDriverBean bean, IclubPersonModel personModel, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "mod");

		IclubDriverModel model = new IclubDriverModel();

		model.setDId(bean.getDId());

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

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			response = addVehicle(vehicleBean, model, quoteModel);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addPropertiy(IclubPropertyBean bean, IclubQuoteModel quoteModel) {

		WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "mod");

		IclubPropertyModel model = new IclubPropertyModel();

		model.setPId(bean.getPId());
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

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {
			// addInsuranceItem(model.getPId(), quoteModel.getQId(), 2l,
			// getSessionUserId());
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

	public ResponseModel addExtras(IclubExtrasBean bean) {
		WebClient client = IclubWebHelper.createCustomClient(EXTS_BASE_URL + "add");

		IclubExtrasModel model = new IclubExtrasModel();
		model.setEId(bean.getEId());
		model.setECrtdDt(new Timestamp(System.currentTimeMillis()));
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

	public ResponseModel addClaim(IclubClaimBean bean, IclubPolicyModel policyModel) {
		IclubClaimModel model = new IclubClaimModel();

		WebClient client = null;
		if (bean != null && bean.getCId() != null) {
			client = IclubWebHelper.createCustomClient(CLM_BASE_URL + "mod");
			model.setCId(bean.getCId());
		} else {
			client = IclubWebHelper.createCustomClient(CLM_BASE_URL + "add");
			model.setCId(UUID.randomUUID().toString());
		}

		model.setCCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setCValue(bean.getCValue());
		model.setCNumItems(bean.getCNumItems());
		model.setCNumber(bean.getCNumber());
		model.setIclubPolicy(policyModel.getPId());
		model.setIclubClaimStatus(bean.getIclubClaimStatus());
		model.setIclubPerson(getSessionUserId());
		ResponseModel response = null;
		if (bean != null && bean.getCId() != null) {
			response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
			client.close();
		} else {
			response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
			client.close();
		}

		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addPolicy(IclubPolicyBean bean, IclubQuoteModel quoteModel, IclubAccountModel accountModel) {

		IclubPolicyModel model = new IclubPolicyModel();
		WebClient client = null;
		if (bean != null && bean.getPId() != null) {
			client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "mod");
			model.setPId(bean.getPId());
		} else {
			client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "add");
			model.setPId(UUID.randomUUID().toString());
			bean=new IclubPolicyBean();
		}

		model.setPProrataPrm(0l);
		model.setPPremium(0l);
		model.setPNumber(quoteModel.getQNumber());
		model.setPDebitDt(debitDate != null && debitMonth != null ? Integer.parseInt(debitDate + debitMonth) : null);
		model.setPCrtdDt((new Timestamp(System.currentTimeMillis())).toString());
		model.setIclubAccount(accountModel.getAId());
		model.setIclubQuote(quoteModel.getQId());
		model.setIclubPolicyStatus(bean.getIclubPolicyStatus());
		model.setIclubPerson(getSessionUserId());
		ResponseModel response = null;
		if (bean != null && bean.getPId() != null) {
			response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		} else {
			response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		}
		client.close();
		if (response.getStatusCode() == 0) {
			addClaim(claimBean, model);
		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addAccount(IclubAccountBean bean, IclubPersonModel personModel, IclubQuoteModel quoteModel) {
		IclubAccountModel model = new IclubAccountModel();
		WebClient client = null;
		if (bean != null && bean.getAId() != null) {
			client = IclubWebHelper.createCustomClient(ACC_BASE_URL + "mod");
			model.setAId(bean.getAId());
		} else {
			client = IclubWebHelper.createCustomClient(ACC_BASE_URL + "add");
			model.setAId(UUID.randomUUID().toString());
		}

		model.setAAccNum(bean.getAAccNum());
		model.setACrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setAOwnerId(personModel.getPId());
		model.setIclubBankMaster(bean.getIclubBankMaster());
		model.setIclubAccountType(bean.getIclubAccountType());
		model.setIclubOwnerType(bean.getIclubOwnerType());
		model.setIclubPerson(getSessionUserId());
		model.setAStatus(bean.getAStatus());
		ResponseModel response = null;
		if (bean != null && bean.getAId() != null) {
			  response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		}else{
			response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		}
		client.close();
		if (response.getStatusCode() == 0) {
			addPolicy(policyBean, quoteModel, model);
		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addQuote(IclubQuoteBean bean, IclubPersonModel personModel) {

		WebClient client = IclubWebHelper.createCustomClient(QUT_BASE_URL + "mod");
		IclubQuoteModel model = new IclubQuoteModel();
		model.setQId(bean.getQId());
		model.setQCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setQIsMatched("N");
		model.setQPrevPremium(0l);
		model.setQValidUntil(new Timestamp(System.currentTimeMillis()));
		model.setQMobile(personModel.getPMobile());
		model.setQEmail(personModel.getPEmail());
		model.setQGenPremium(0l);
		model.setQNumItems(2);
		model.setQGenDt(new Timestamp(System.currentTimeMillis()));

		model.setQNumber(quoteBean.getQNumber());
		model.setIclubPersonByQCrtdBy(getSessionUserId());
		model.setIclubProductType(bean.getIclubProductType());
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setIclubQuoteStatus(bean.getIclubQuoteStatus());
		model.setIclubPersonByQPersonId(personModel.getPId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).put(model, ResponseModel.class);
		client.close();
		if (response.getStatusCode() == 0) {

			addPropertiy(propertyBean, model);
			addAccount(accountBean, personModel, model);
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

	public List<IclubPurposeTypeBean> getPurposeTypeBeans() {

		WebClient client = IclubWebHelper.createCustomClient(PUR_BASE_URL + "/get/insurnceitemtype/" + "1");
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

	public IclubPersonBean getPersonBean() {
		if (personBean == null) {
			personBean = new IclubPersonBean();
		}
		if (IclubWebHelper.getObjectIntoSession("fullquote") != null) {
			quoteBean = (IclubQuoteBean) IclubWebHelper.getObjectIntoSession("fullquote");

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
				setDriverDetails();
				setPropertyDetails();
				setPolicyDetails();
			}

			IclubWebHelper.addObjectIntoSession("fullquote", null);
		}
		return personBean;
	}

	public void setPropertyDetails() {

		propertyIItemBean = setInsuranceItemDetails(quoteBean.getQId(), 2l);
		WebClient client = IclubWebHelper.createCustomClient(PRO_BASE_URL + "get/" + propertyIItemBean.getIiItemId());
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
	}

	public void setPolicyDetails() {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "getByQuoteId/" + quoteBean.getQId());

		IclubPolicyModel model = (IclubPolicyModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubPolicyModel.class));

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
		}
	}

	public void setDriverDetails() {
		WebClient client = IclubWebHelper.createCustomClient(D_BASE_URL + "getByPersonId/" + personBean.getPId());

		IclubDriverModel model = (IclubDriverModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubDriverModel.class));
		driverBean = new IclubDriverBean();

		driverBean.setDId(model.getDId());
		driverBean.setDDob(model.getDDob());
		driverBean.setDIssueDt(model.getDIssueDt());
		driverBean.setDLicenseNum(model.getDLicenseNum());
		driverBean.setDName(model.getDName());
		driverBean.setDCrtdDt(model.getDCrtdDt());
		driverBean.setIclubAccessType(model.getIclubAccessType());
		driverBean.setIclubLicenseCode(model.getIclubLicenseCode());
		driverBean.setIclubMaritialStatus(model.getIclubMaritialStatus());
		driverBean.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId());
		driverBean.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy());

		client.close();
		setVehicleDetails();
	}

	public void setVehicleDetails() {
		WebClient client = IclubWebHelper.createCustomClient(V_BASE_URL + "getByDriverId/" + driverBean.getDId());

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

			vehicleIItemBean = setInsuranceItemDetails(quoteBean.getQId(), 1l);
			setVmMakeAndMode();
		}

		client.close();
	}

	public void setVmMakeAndMode() {
		WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "get/" + vehicleBean.getIclubVehicleMaster());

		IclubVehicleMasterModel model = (IclubVehicleMasterModel) (client.accept(MediaType.APPLICATION_JSON).get(IclubVehicleMasterModel.class));
		if (model != null && model.getVmId() != null) {
			vmMake = model.getVmMake();
			loadVmModels(vmMake);
			loadYears(model.getVmId().toString());
		}

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

	public void setPersonBean(IclubPersonBean personBean) {
		this.personBean = personBean;
	}

	public String getSessionUserId() {
		if (sessionUserId == null) {
			sessionUserId = IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString();
		}
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

	public List<IclubSecurityDeviceBean> getSecurityDeviceBeans() {
		WebClient client = IclubWebHelper.createCustomClient(SD_BASE_URL + "list");
		Collection<? extends IclubSecurityDeviceModel> models = new ArrayList<IclubSecurityDeviceModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubSecurityDeviceModel.class));
		client.close();
		securityDeviceBeans = new ArrayList<IclubSecurityDeviceBean>();

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
		for (IclubClaimStatusModel model : models) {
			IclubClaimStatusBean bean = new IclubClaimStatusBean();
			bean.setCsId(model.getCsId());
			bean.setCsLongDesc(model.getCsLongDesc());
			bean.setCsShortDesc(model.getCsShortDesc());
			bean.setCsStatus(model.getCsStatus());
			claimStatusBeans.add(bean);
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

		WebClient client = IclubWebHelper.createCustomClient(BNKM_BASE_URL + "list");
		Collection<? extends IclubBankMasterModel> models = new ArrayList<IclubBankMasterModel>(client.accept(MediaType.APPLICATION_JSON).getCollection(IclubBankMasterModel.class));
		client.close();
		bankMasterBeans = new ArrayList<IclubBankMasterBean>();
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
		for (IclubAccountTypeModel model : models) {
			IclubAccountTypeBean bean = new IclubAccountTypeBean();
			bean.setAtId(model.getAtId());
			bean.setAtLongDesc(model.getAtLongDesc());
			bean.setAtShortDesc(model.getAtShortDesc());
			bean.setAtStatus(model.getAtStatus());
			accountTypeBeans.add(bean);
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

	public IclubInsuranceItemBean getVehicleIItemBean() {
		return vehicleIItemBean;
	}

	public void setVehicleIItemBean(IclubInsuranceItemBean vehicleIItemBean) {
		this.vehicleIItemBean = vehicleIItemBean;
	}

	public IclubInsuranceItemBean getPropertyIItemBean() {
		return propertyIItemBean;
	}

	public void setPropertyIItemBean(IclubInsuranceItemBean propertyIItemBean) {
		this.propertyIItemBean = propertyIItemBean;
	}

	public IclubPolicyBean getPolicyBean() {
		return policyBean;
	}

	public void setPolicyBean(IclubPolicyBean policyBean) {
		this.policyBean = policyBean;
	}

	public String getClaimYN() {
		return claimYN;
	}

	public void setClaimYN(String claimYN) {
		this.claimYN = claimYN;
	}

}