package za.co.iclub.pss.web.controller;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
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
import za.co.iclub.pss.web.bean.IclubBarTypeBean;
import za.co.iclub.pss.web.bean.IclubClaimBean;
import za.co.iclub.pss.web.bean.IclubClaimStatusBean;
import za.co.iclub.pss.web.bean.IclubCoverTypeBean;
import za.co.iclub.pss.web.bean.IclubDriverBean;
import za.co.iclub.pss.web.bean.IclubExtrasBean;
import za.co.iclub.pss.web.bean.IclubIdTypeBean;
import za.co.iclub.pss.web.bean.IclubLicenseCodeBean;
import za.co.iclub.pss.web.bean.IclubMaritialStatusBean;
import za.co.iclub.pss.web.bean.IclubOccupiedStatusBean;
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
import za.co.iclub.pss.ws.model.IclubBarTypeModel;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.IclubCoverTypeModel;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubExtrasModel;
import za.co.iclub.pss.ws.model.IclubIdTypeModel;
import za.co.iclub.pss.ws.model.IclubLicenseCodeModel;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
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
	private List<IclubSecurityMasterBean> securityMasterBeans;

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

	public void initializePage() {

	}

	public void vmMakeValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "getByMake/" + valueChangeEvent.getNewValue().toString());
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
	}

	public void vmModelValueChangeListener(ValueChangeEvent valueChangeEvent) {
		if (valueChangeEvent != null && valueChangeEvent.getNewValue() != null && !valueChangeEvent.getNewValue().toString().trim().equalsIgnoreCase("-1")) {

			WebClient client = IclubWebHelper.createCustomClient(VM_BASE_URL + "get/" + valueChangeEvent.getNewValue().toString());
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

	public void saveQuickQuoteDetails() {

		LOGGER.info("Class :: " + this.getClass() + " :: Method :: saveQuickQuoteDetails");
		try {
			addPerson(personBean);
		} catch (Exception e) {
			LOGGER.error(e, e);
			IclubWebHelper.addMessage("Fail :: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

	}

	public ResponseModel addPerson(IclubPersonBean personBean) {

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
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		model.setIclubMaritialStatus(personBean.getIclubMaritialStatus());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			addDriver(driverBean, model);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addVehicle(IclubVehicleBean bean, IclubDriverModel driverModel) {

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
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		model.setIclubDriver(driverModel.getDId());
		model.setIclubSecurityDevice(bean.getIclubSecurityDevice());
		model.setIclubAccessTypeByVDdAccessTypeId(bean.getIclubAccessTypeByVDdAccessTypeId());
		model.setIclubAccessTypeByVOnAccessTypeId(bean.getIclubAccessTypeByVOnAccessTypeId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			IclubWebHelper.addMessage("Success", FacesMessage.SEVERITY_INFO);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addDriver(IclubDriverBean bean, IclubPersonModel personModel) {

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
		model.setIclubPersonByDCrtdBy(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

			response = addVehicle(vehicleBean, model);

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addPropertiy(IclubPropertyBean bean) {

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
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		model.setIclubBarType(bean.getIclubBarType());
		model.setIclubThatchType(bean.getIclubThatchType());
		model.setIclubRoofType(bean.getIclubRoofType());

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
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addClaim(IclubClaimBean bean) {
		WebClient client = IclubWebHelper.createCustomClient(CLM_BASE_URL + "add");
		IclubClaimModel model = new IclubClaimModel();
		model.setCId(UUID.randomUUID().toString());
		model.setCCrtdDt(new Timestamp(System.currentTimeMillis()));
		model.setCValue(bean.getCValue());
		model.setCNumItems(bean.getCNumItems());
		model.setCNumber(bean.getCNumber());
		model.setIclubPolicy(bean.getIclubPolicy());
		model.setIclubClaimStatus(bean.getIclubClaimStatus());
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();

		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;
	}

	public ResponseModel addPolicy(IclubPolicyBean bean, IclubQuoteModel quoteModel, IclubAccountModel accountModel) {

		WebClient client = IclubWebHelper.createCustomClient(PCY_BASE_URL + "add");

		IclubPolicyModel model = new IclubPolicyModel();

		model.setPId(UUID.randomUUID().toString());
		model.setPProrataPrm(0l);
		model.setPPremium(0l);
		model.setPNumber(quoteModel.getQNumber());
		model.setPDebitDt(bean.getPDebitDt());
		model.setPCrtdDt((new Timestamp(System.currentTimeMillis())).toString());
		model.setIclubAccount(accountModel.getAId());
		model.setIclubQuote(quoteModel.getQId());
		model.setIclubPolicyStatus(bean.getIclubPolicyStatus());
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();
		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

	}

	public ResponseModel addAccount(IclubAccountBean bean) {

		WebClient client = IclubWebHelper.createCustomClient(ACC_BASE_URL + "add");
		IclubAccountModel model = new IclubAccountModel();
		model.setAAccNum(bean.getAAccNum());
		model.setACrtdDt(bean.getACrtdDt());
		model.setAOwnerId(bean.getAOwnerId());
		model.setIclubBankMaster(bean.getIclubBankMaster());
		model.setIclubAccountType(bean.getIclubAccountType());
		model.setIclubOwnerType(bean.getIclubOwnerType());
		model.setIclubPerson(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		model.setAStatus(bean.getAStatus());
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
		model.setQPrevPremium(0l);
		model.setQValidUntil(new Timestamp(System.currentTimeMillis()));
		model.setQMobile(personModel.getPMobile());
		model.setQEmail(personModel.getPEmail());
		model.setQGenPremium(0l);
		model.setQNumItems(2);
		model.setQGenDt(new Timestamp(System.currentTimeMillis()));
		model.setQNumber(bean.getQNumber());
		model.setIclubPersonByQCrtdBy(IclubWebHelper.getObjectIntoSession(BUNDLE.getString("logged.in.user.id")).toString());
		model.setIclubProductType(bean.getIclubProductType());
		model.setIclubProductType(bean.getIclubProductType());
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setIclubQuoteStatus(bean.getIclubQuoteStatus());
		model.setIclubPersonByQPersonId(personModel.getPId());

		ResponseModel response = client.accept(MediaType.APPLICATION_JSON).post(model, ResponseModel.class);
		client.close();
		if (response.getStatusCode() == 0) {

		} else {
			IclubWebHelper.addMessage("Fail :: " + response.getStatusDesc(), FacesMessage.SEVERITY_ERROR);
		}

		return response;

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
		if (personBean == null)
			personBean = new IclubPersonBean();
		return personBean;
	}

	public void setPersonBean(IclubPersonBean personBean) {
		this.personBean = personBean;
	}

	public String getSessionUserId() {
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
				for (String iclubProperty : bean.getIclubProperties()) {
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

}
