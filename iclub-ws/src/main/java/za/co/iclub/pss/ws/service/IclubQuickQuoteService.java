package za.co.iclub.pss.ws.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubEntityType;
import za.co.iclub.pss.orm.bean.IclubField;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubPropertyItem;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBarTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubGeoLocDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubLicenseCodeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubOccupiedStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPropUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyItemDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyTypeDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;
import za.co.iclub.pss.orm.dao.IclubRoofTypeDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityDeviceDAO;
import za.co.iclub.pss.orm.dao.IclubVehSecTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehUsageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleDAO;
import za.co.iclub.pss.orm.dao.IclubVehicleMasterDAO;
import za.co.iclub.pss.orm.dao.IclubWallTypeDAO;
import za.co.iclub.pss.ws.model.IclubDriverModel;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.IclubPropertyItemModel;
import za.co.iclub.pss.ws.model.IclubPropertyModel;
import za.co.iclub.pss.ws.model.IclubQuickQuoteRequest;
import za.co.iclub.pss.ws.model.IclubQuickQuoteResponse;
import za.co.iclub.pss.ws.model.IclubQuoteModel;
import za.co.iclub.pss.ws.model.IclubVehicleModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubQuickQuoteService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubQuickQuoteService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubQuickQuoteService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubQuoteStatusDAO iclubQuoteStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubIdTypeDAO iclubIdTypeDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;
	private IclubGeoLocDAO iclubGeoLocDAO;
	private IclubLicenseCodeDAO iclubLicenseCodeDAO;
	private IclubAccessTypeDAO iclubAccessTypeDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubDriverDAO iclubDriverDAO;
	private IclubSecurityDeviceDAO iclubSecurityDeviceDAO;
	private IclubVehicleMasterDAO iclubVehicleMasterDAO;
	private IclubVehSecTypeDAO iclubVehSecTypeDAO;
	private IclubVehUsageTypeDAO iclubVehUsageTypeDAO;
	private IclubRoofTypeDAO iclubRoofTypeDAO;
	private IclubBarTypeDAO iclubBarTypeDAO;
	private IclubWallTypeDAO iclubWallTypeDAO;
	private IclubPropertyTypeDAO iclubPropertyTypeDAO;
	private IclubOccupiedStatusDAO iclubOccupiedStatusDAO;
	private IclubPropUsageTypeDAO iclubPropUsageTypeDAO;
	private IclubPropertyDAO iclubPropertyDAO;
	private IclubVehicleDAO iclubVehicleDAO;
	private IclubPropertyItemDAO iclubPropertyItemDAO;
	private IclubInsuranceItemDAO iclubInsuranceItemDAO;
	
	@POST
	@Path("/createQuote")
	@Transactional
	public IclubQuickQuoteResponse createQuote(IclubQuickQuoteRequest iclubQuickQuoteRequest) {
		
		Double generatedPremium = 0.0;
		IclubPerson iclubPerson = null;
		if (iclubQuickQuoteRequest.isLoginFlag()) {
			iclubPerson = getIclubPerson(iclubQuickQuoteRequest);
			iclubPersonDAO.save(iclubPerson);
		} else {
			iclubPerson = iclubPersonDAO.findById(iclubQuickQuoteRequest.getIclubPersonModel().getPId());
		}
		
		IclubQuote iclubQuote = getIclubQuote(iclubQuickQuoteRequest);
		iclubQuoteDAO.save(iclubQuote);
		String quoteNumber = iclubQuote.getQId();
		IclubDriverModel iclubDriverModel = iclubQuickQuoteRequest.getIclubDriverModel();
		IclubDriver iclubDriver = getIclubDriver(iclubDriverModel);
		iclubDriverDAO.save(iclubDriver);
		List<IclubVehicleModel> vehicleModels = iclubQuickQuoteRequest.getIclubVehicleModels();
		
		List<IclubVehicle> iclubVehicles = getVehicleList(vehicleModels, iclubQuote);
		
		if (iclubVehicles.size() > 0) {
			for (IclubVehicle iclubVehicle : iclubVehicles) {
				iclubVehicleDAO.save(iclubVehicle);
			}
		}
		
		List<IclubPropertyModel> iclubPropertyModels = iclubQuickQuoteRequest.getIclubPropertyModels();
		List<IclubProperty> iclubProperties = getIclubPropertis(iclubPropertyModels);
		
		if (iclubProperties != null && iclubProperties.size() > 0) {
			for (IclubProperty iclubProperty : iclubProperties) {
				iclubPropertyDAO.save(iclubProperty);
			}
		}
		
		List<IclubPropertyItemModel> iclubPropertyItemModels = iclubQuickQuoteRequest.getIclubPropertyItemModels();
		List<IclubPropertyItem> iclubPropertyItems = getIclubPropertyItems(iclubPropertyItemModels);
		if (iclubPropertyItems != null && iclubPropertyItems.size() > 0) {
			for (IclubPropertyItem iclubPropertyItem : iclubPropertyItems) {
				iclubPropertyItemDAO.save(iclubPropertyItem);
			}
		}
		List<IclubInsuranceItem> insuranceItems = getIclubInsuranceItemList(iclubVehicles, iclubProperties, iclubQuote);
		if (insuranceItems != null && insuranceItems.size() > 0) {
			for (IclubInsuranceItem insuranceItem : insuranceItems) {
				iclubInsuranceItemDAO.save(insuranceItem);
			}
		}
		generatedPremium = getUpdatePremium(iclubQuote, "Q", iclubVehicles, iclubDriver, iclubProperties, iclubPerson);
		IclubQuickQuoteResponse response = new IclubQuickQuoteResponse();
		response.setGeneratedPremium(generatedPremium);
		response.setQuoteNumber(quoteNumber);
		
		return response;
	}
	
	public List<IclubProperty> getIclubPropertis(List<IclubPropertyModel> models) {
		
		List<IclubProperty> iclubProperties = new ArrayList<IclubProperty>();
		if (models != null && models.size() > 0) {
			
			for (IclubPropertyModel model : models) {
				IclubProperty iCP = new IclubProperty();
				
				iCP.setPId(model.getPId());
				iCP.setPCrtdDt(model.getPCrtdDt());
				iCP.setPEstValue(model.getPEstValue());
				iCP.setPSecGatesYn(model.getPSecGatesYn());
				iCP.setPNorobberyYn(model.getPNorobberyYn());
				iCP.setPCompYn(model.getPCompYn());
				iCP.setPRentFurYn(model.getPRentFurYn());
				iCP.setPNoclaimYrs(model.getPNoclaimYrs());
				iCP.setPPostalCd(model.getPPostalCd());
				iCP.setPLong(model.getPLong());
				iCP.setPLat(model.getPLat());
				iCP.setPAddress(model.getPAddress());
				iCP.setPRegNum(model.getPRegNum());
				iCP.setPReplacementCost(model.getPReplacementCost());
				iCP.setPContentCost(model.getPContentCost());
				iCP.setIclubRoofType(model.getIclubRoofType() != null ? iclubRoofTypeDAO.findById(model.getIclubRoofType()) : null);
				iCP.setPThatchType(model.getPThatchType());
				iCP.setIclubBarType(model.getIclubBarType() != null ? iclubBarTypeDAO.findById(model.getIclubBarType()) : null);
				iCP.setIclubAccessType(model.getIclubAccessType() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessType()) : null);
				iCP.setIclubWallType(model.getIclubWallType() != null ? iclubWallTypeDAO.findById(model.getIclubWallType()) : null);
				iCP.setIclubPropertyType(model.getIclubPropertyType() != null ? iclubPropertyTypeDAO.findById(model.getIclubPropertyType()) : null);
				iCP.setIclubOccupiedStatus(model.getIclubOccupiedStatus() != null ? iclubOccupiedStatusDAO.findById(model.getIclubOccupiedStatus()) : null);
				iCP.setIclubPropUsageType(model.getIclubPropUsageType() != null ? iclubPropUsageTypeDAO.findById(model.getIclubPropUsageType()) : null);
				iCP.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
				iCP.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
				iclubProperties.add(iCP);
			}
		}
		
		return iclubProperties;
	}
	
	public List<IclubPropertyItem> getIclubPropertyItems(List<IclubPropertyItemModel> models) {
		List<IclubPropertyItem> iclubProeprtyItems = new ArrayList<IclubPropertyItem>();
		
		if (models != null && models.size() > 0) {
			for (IclubPropertyItemModel model : models) {
				IclubPropertyItem iTt = new IclubPropertyItem();
				
				iTt.setPiId(model.getPiId());
				iTt.setPiCrtdDate(model.getPiCrtdDate());
				iTt.setPiDescripton(model.getPiDescripton());
				iTt.setPiValue(model.getPiValue());
				iTt.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
				iTt.setIclubProperty(model.getIclubProperty() != null ? iclubPropertyDAO.findById(model.getIclubProperty()) : null);
				iclubProeprtyItems.add(iTt);
			}
		}
		
		return iclubProeprtyItems;
	}
	
	public IclubDriver getIclubDriver(IclubDriverModel model) {
		IclubDriver iCt = new IclubDriver();
		
		iCt.setDId(model.getDId());
		iCt.setDDob(model.getDDob());
		iCt.setDIssueDt(model.getDIssueDt());
		iCt.setDLicenseNum(model.getDLicenseNum());
		iCt.setDName(model.getDName());
		iCt.setDCrtdDt(model.getDCrtdDt());
		iCt.setDIssueYears(model.getDIssueYears());
		iCt.setIclubAccessTypeByDAccessTypeId(model.getIclubAccessTypeByDAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessTypeId()) : null);
		iCt.setIclubAccessTypeByDAccessStatusId(model.getIclubAccessTypeByDAccessStatusId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByDAccessStatusId()) : null);
		iCt.setIclubLicenseCode(model.getIclubLicenseCode() != null ? iclubLicenseCodeDAO.findById(model.getIclubLicenseCode()) : null);
		iCt.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
		iCt.setIclubPersonByDPersonId(model.getIclubPersonByDPersonId() != null && !model.getIclubPersonByDPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDPersonId()) : null);
		iCt.setIclubPersonByDCrtdBy(model.getIclubPersonByDCrtdBy() != null && !model.getIclubPersonByDCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByDCrtdBy()) : null);
		return iCt;
		
	}
	
	public List<IclubVehicle> getVehicleList(List<IclubVehicleModel> models, IclubQuote iclubQuote) {
		List<IclubVehicle> iclubVehicles = new ArrayList<IclubVehicle>();
		
		for (IclubVehicleModel model : models) {
			IclubVehicle iCV = new IclubVehicle();
			
			iCV.setVId(model.getVId());
			iCV.setVOwner(model.getVOwner());
			iCV.setVGearLockYn(model.getVGearLockYn());
			iCV.setVImmYn(model.getVImmYn());
			iCV.setVConcessReason(model.getVConcessReason());
			iCV.setVConcessPrct(model.getVConcessPrct());
			iCV.setVInsuredValue(model.getVInsuredValue());
			iCV.setVYear(model.getVYear());
			iCV.setVDdLong(model.getVDdLong());
			iCV.setVCompYrs(model.getVCompYrs());
			iCV.setVDdLat(model.getVDdLat());
			iCV.setVDdArea(model.getVDdArea());
			iCV.setVOnLong(model.getVOnLong());
			iCV.setVOnLat(model.getVOnLat());
			iCV.setVOnArea(model.getVOnArea());
			iCV.setVOdometer(model.getVOdometer());
			iCV.setVCrtdDt(model.getVCrtdDt());
			iCV.setVRegNum(model.getVRegNum());
			iCV.setVEngineNr(model.getVEngineNr());
			iCV.setVVin(model.getVVin());
			iCV.setVNoclaimYrs(model.getVNoclaimYrs());
			iCV.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCV.setIclubVehUsageType(model.getIclubVehUsageType() != null ? iclubVehUsageTypeDAO.findById(model.getIclubVehUsageType()) : null);
			iCV.setIclubVehSecType(model.getIclubVehSecType() != null ? iclubVehSecTypeDAO.findById(model.getIclubVehSecType()) : null);
			iCV.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCV.setIclubDriver(model.getIclubDriver() != null ? iclubDriverDAO.findById(model.getIclubDriver()) : null);
			iCV.setIclubSecurityDevice(model.getIclubSecurityDevice() != null ? iclubSecurityDeviceDAO.findById(model.getIclubSecurityDevice()) : null);
			iCV.setIclubAccessTypeByVDdAccessTypeId(model.getIclubAccessTypeByVDdAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVDdAccessTypeId()) : null);
			iCV.setIclubAccessTypeByVOnAccessTypeId(model.getIclubAccessTypeByVOnAccessTypeId() != null ? iclubAccessTypeDAO.findById(model.getIclubAccessTypeByVOnAccessTypeId()) : null);
			iCV.setIclubVehicleMaster(model.getIclubVehicleMaster() != null ? iclubVehicleMasterDAO.findById(model.getIclubVehicleMaster()) : null);
			iclubVehicles.add(iCV);
		}
		return iclubVehicles;
	}
	
	public List<IclubInsuranceItem> getIclubInsuranceItemList(List<IclubVehicle> iclubVehicles, List<IclubProperty> iclubProperties, IclubQuote iclubQuote) {
		List<IclubInsuranceItem> iclubInsuranceItems = new ArrayList<IclubInsuranceItem>();
		
		if (iclubVehicles != null && iclubVehicles.size() > 0) {
			for (IclubVehicle vehicle : iclubVehicles) {
				IclubInsuranceItem iCTt = new IclubInsuranceItem();
				
				iCTt.setIiId(UUID.randomUUID().toString());
				iCTt.setIiItemId(vehicle.getVId());
				iCTt.setIiQuoteId(iclubQuote.getQId());
				iCTt.setIiCrtdDt(new Timestamp(System.currentTimeMillis()));
				iCTt.setIiInsureValue(vehicle.getVInsuredValue());
				iCTt.setIclubInsuranceItemType(iclubInsuranceItemTypeDAO.findById(1l));
				iCTt.setIclubPerson(vehicle.getIclubPerson() != null && !vehicle.getIclubPerson().getPId().equalsIgnoreCase("") ? iclubPersonDAO.findById(vehicle.getIclubPerson().getPId()) : null);
				iclubInsuranceItems.add(iCTt);
			}
		}
		if (iclubProperties != null && iclubProperties.size() > 0) {
			for (IclubProperty property : iclubProperties) {
				IclubInsuranceItem iCTt = new IclubInsuranceItem();
				
				iCTt.setIiId(UUID.randomUUID().toString());
				iCTt.setIiItemId(property.getPId());
				iCTt.setIiQuoteId(iclubQuote.getQId());
				iCTt.setIiCrtdDt(new Timestamp(System.currentTimeMillis()));
				iCTt.setIclubInsuranceItemType(iclubInsuranceItemTypeDAO.findById(2l));
				iCTt.setIclubPerson(property.getIclubPerson() != null && !property.getIclubPerson().getPId().equalsIgnoreCase("") ? iclubPersonDAO.findById(property.getIclubPerson().getPId()) : null);
				iclubInsuranceItems.add(iCTt);
			}
		}
		
		return iclubInsuranceItems;
	}
	
	public IclubPerson getIclubPerson(IclubQuickQuoteRequest iclubQuickQuoteRequest) {
		
		IclubPerson person = new IclubPerson();
		IclubPersonModel model = iclubQuickQuoteRequest.getIclubPersonModel();
		person.setPId(model.getPId());
		person.setPCrtdDt(model.getPCrtdDt());
		person.setPDob(model.getPDob());
		person.setPEmail(model.getPEmail());
		person.setPFName(model.getPFName());
		person.setPIdNum(model.getPIdNum());
		person.setPLName(model.getPLName());
		person.setPMobile(model.getPMobile());
		person.setPAddress(model.getPAddress());
		person.setPContactPref(model.getPContactPref());
		person.setPGender(model.getPGender());
		person.setPIdNum(model.getPIdNum());
		person.setPContactPref(model.getPContactPref());
		person.setPIdExpiryDt(model.getPIdExpiryDt());
		person.setPIdIssueDt(model.getPIdIssueDt());
		person.setPInitials(model.getPInitials());
		person.setPIsPensioner(model.getPIsPensioner());
		person.setPIdIssueCntry(model.getPIdIssueCntry());
		person.setPLat(model.getPLat());
		person.setPLong(model.getPLong());
		person.setPOccupation(model.getPOccupation());
		person.setPTitle(model.getPTitle());
		person.setPAge(model.getPAge());
		person.setPZipCd(model.getPZipCd());
		person.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
		person.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		person.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
		
		return person;
		
	}
	
	public IclubQuote getIclubQuote(IclubQuickQuoteRequest iclubQuickQuoteRequest) {
		
		IclubQuoteModel model = iclubQuickQuoteRequest.getIclubQuoteModel();
		IclubQuote iCQ = new IclubQuote();
		
		iCQ.setQId(model.getQId());
		iCQ.setQCrtdDt(model.getQCrtdDt());
		iCQ.setQIsMatched(model.getQIsMatched());
		iCQ.setQPrevPremium(model.getQPrevPremium());
		iCQ.setQValidUntil(model.getQValidUntil());
		iCQ.setQMobile(model.getQMobile());
		iCQ.setQEmail(model.getQEmail());
		iCQ.setQGenPremium(model.getQGenPremium());
		iCQ.setQNumItems(model.getQNumItems());
		iCQ.setQGenDt(model.getQGenDt());
		iCQ.setQNumber(model.getQNumber());
		iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
		iCQ.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
		iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
		iCQ.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
		iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() != null && !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
		iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);
		
		return iCQ;
		
	}
	
	public Double getUpdatePremium(IclubQuote quoteBean, String quoteType, List<IclubVehicle> vehicleBeans, IclubDriver driverBean, List<IclubProperty> propertyBeans, IclubPerson iclubPersonBean) {
		List fieldBeans = iclubNamedQueryDAO.getIclubFieldByFieldStatus("Y");
		IclubConfig configBean = iclubNamedQueryDAO.getIclubConfigByKey("base.premium");
		Double baseValue = new Double(configBean.getCValue());
		Double premium = baseValue;
		List<String> ids = new ArrayList<String>();
		
		for (IclubVehicle daf : vehicleBeans) {
			ids.add(daf.getVId());
		}
		// for (IclubProperty props : propertyBeans) {
		// ids.add(props.getPId());
		// }
		//
		if (vehicleBeans != null && vehicleBeans.size() > 0) {
			for (String vehProId : ids) {
				for (Object obj : fieldBeans) {
					IclubField fieldBean = (IclubField) obj;
					if (fieldBean.getFRate() != null && fieldBean.getFStatus().equalsIgnoreCase("Y")) {
						
						IclubEntityType entityType = fieldBean.getIclubEntityType();
						
						String tableName = entityType.getEtTblNm();
						String fieldName = fieldBean.getFName();
						if (tableName != null) {
							List rateTypeBeans = iclubNamedQueryDAO.findIclubRateTypeByQuoteTypeAndFieldId(fieldBean.getFId(), quoteType);
							
							String fieldValue = null;
							if (tableName.equalsIgnoreCase("iclub_vehicle") && rateTypeBeans != null && rateTypeBeans.size() > 0 && ((IclubRateType) rateTypeBeans.get(0)).getRtType().equalsIgnoreCase("G")) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, vehProId, "G");
								
							} else if (tableName.equalsIgnoreCase("iclub_vehicle")) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, vehProId, null);
								
							} else if (tableName.equalsIgnoreCase("iclub_property") && vehProId != null) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, vehProId, null);
							} else if (tableName.equalsIgnoreCase("iclub_person")) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, iclubPersonBean.getPId(), null);
								
							} else if (tableName.equalsIgnoreCase("iclub_driver")) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, driverBean.getDId(), null);
								
							} else if (tableName.equalsIgnoreCase("iclub_quote")) {
								
								fieldValue = getFieldValueFromDB(fieldName, tableName, quoteBean.getQId(), null);
								
							}
							
							if (rateTypeBeans != null && rateTypeBeans.size() > 0) {
								for (Object object : rateTypeBeans) {
									IclubRateType rateTypeBean = (IclubRateType) object;
									if (rateTypeBean.getRtType().equalsIgnoreCase("G")) {
										String fieldValues[] = fieldValue.split("@");
										Long glId = iclubNamedQueryDAO.getIclubGeoLocByLatAndLong(new Double(fieldValues[0].toString()), new Double(fieldValues[1].toString()));
										IclubGeoLoc geoLocBean = iclubGeoLocDAO.findById(glId);
										premium = premium + baseValue * (geoLocBean.getGlRate() / 100);
										
									} else {
										
										List rateEngineBeans = iclubNamedQueryDAO.findByRateType(rateTypeBean.getRtId().toString());
										
										for (Object reObj : rateEngineBeans) {
											IclubRateEngine rateEngineBean = (IclubRateEngine) reObj;
											
											if (fieldValue != null) {
												if ((rateTypeBean.getRtType().equalsIgnoreCase("F") && rateEngineBean.getReBaseValue().trim().equalsIgnoreCase(fieldValue.toString()) || (rateTypeBean.getRtType().trim().equalsIgnoreCase("R") && (Double.parseDouble(rateEngineBean.getReBaseValue().trim()) <= Double.parseDouble(fieldValue.toString()) && Double.parseDouble(rateEngineBean.getReMaxValue().trim()) >= Double.parseDouble(fieldValue.toString()))))) {
													
													premium = premium + baseValue * (rateEngineBean.getReRate() / 100);
													
												} else if (rateTypeBean.getRtType().equalsIgnoreCase("L")) {
													String lookupDetails = iclubCommonDAO.findAllLookValuesByTabelName(fieldBean.getFLTblName(), fieldValue.toString());
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
				}
			}
		}
		return premium;
	}
	
	public String getFieldValueFromDB(String fieldName, String tableName, String fieldId, String rateType) {
		String fieldValue = null;
		if (rateType != null && !rateType.trim().equalsIgnoreCase("") && rateType.trim().equalsIgnoreCase("G")) {
			Object[] batObj = iclubCommonDAO.getFieldValuesByFieldNameAndId(fieldName, tableName, fieldId);
			
			if (batObj != null) {
				fieldValue = batObj[0].toString() + "@" + batObj[1].toString();
			}
		} else {
			Object batObj = iclubCommonDAO.getFieldValueByFieldNameAndId(fieldName, tableName, fieldId);
			
			if (batObj != null) {
				fieldValue = batObj.toString();
			}
			
		}
		
		return fieldValue;
		
	}
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = new IclubQuote();
			
			iCQ.setQId(model.getQId());
			iCQ.setQCrtdDt(model.getQCrtdDt());
			iCQ.setQIsMatched(model.getQIsMatched());
			iCQ.setQPrevPremium(model.getQPrevPremium());
			iCQ.setQValidUntil(model.getQValidUntil());
			iCQ.setQMobile(model.getQMobile());
			iCQ.setQEmail(model.getQEmail());
			iCQ.setQGenPremium(model.getQGenPremium());
			iCQ.setQNumItems(model.getQNumItems());
			iCQ.setQGenDt(model.getQGenDt());
			iCQ.setQNumber(model.getQNumber());
			iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
			iCQ.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
			iCQ.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
			iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() != null && !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
			iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);
			
			iclubQuoteDAO.save(iCQ);
			
			LOGGER.info("Save Success with ID :: " + iCQ.getQId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = new IclubQuote();
			
			iCQ.setQId(model.getQId());
			iCQ.setQCrtdDt(model.getQCrtdDt());
			iCQ.setQIsMatched(model.getQIsMatched());
			iCQ.setQPrevPremium(model.getQPrevPremium());
			iCQ.setQValidUntil(model.getQValidUntil());
			iCQ.setQMobile(model.getQMobile());
			iCQ.setQEmail(model.getQEmail());
			iCQ.setQGenPremium(model.getQGenPremium());
			iCQ.setQNumItems(model.getQNumItems());
			iCQ.setQGenDt(model.getQGenDt());
			iCQ.setQNumber(model.getQNumber());
			iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
			iCQ.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
			iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
			iCQ.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
			iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() != null && !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
			iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);
			
			iclubQuoteDAO.merge(iCQ);
			
			LOGGER.info("Merge Success with ID :: " + model.getQId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@GET
	@Path("/del/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubQuoteDAO.delete(iclubQuoteDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubQuoteDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote iCQ = (IclubQuote) object;
					
					IclubQuoteModel model = new IclubQuoteModel();
					
					model.setQId(iCQ.getQId());
					model.setQCrtdDt(iCQ.getQCrtdDt());
					model.setQIsMatched(iCQ.getQIsMatched());
					model.setQPrevPremium(iCQ.getQPrevPremium());
					model.setQValidUntil(iCQ.getQValidUntil());
					model.setQMobile(iCQ.getQMobile());
					model.setQEmail(iCQ.getQEmail());
					model.setQGenPremium(iCQ.getQGenPremium());
					model.setQNumItems(iCQ.getQNumItems());
					model.setQGenDt(iCQ.getQGenDt());
					model.setQNumber(iCQ.getQNumber());
					model.setIclubPersonByQCrtdBy(iCQ.getIclubPersonByQCrtdBy() != null ? (iCQ.getIclubPersonByQCrtdBy().getPId()) : null);
					model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
					model.setIclubInsurerMaster(iCQ.getIclubInsurerMaster() != null ? (iCQ.getIclubInsurerMaster().getImId()) : null);
					model.setIclubCoverType(iCQ.getIclubCoverType() != null ? (iCQ.getIclubCoverType().getCtId()) : null);
					model.setIclubQuoteStatus(iCQ.getIclubQuoteStatus() != null ? (iCQ.getIclubQuoteStatus().getQsId()) : null);
					model.setIclubPersonByQPersonId(iCQ.getIclubPersonByQPersonId() != null ? (iCQ.getIclubPersonByQPersonId().getPId()) : null);
					
					if (iCQ.getIclubPolicies() != null && iCQ.getIclubPolicies().size() > 0) {
						String[] policies = new String[iCQ.getIclubPolicies().size()];
						int i = 0;
						for (IclubPolicy policy : iCQ.getIclubPolicies()) {
							policies[i] = policy.getPId();
							i++;
						}
					}
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/user/{user}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubQuote.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote iCQ = (IclubQuote) object;
					
					IclubQuoteModel model = new IclubQuoteModel();
					
					model.setQId(iCQ.getQId());
					model.setQCrtdDt(iCQ.getQCrtdDt());
					model.setQIsMatched(iCQ.getQIsMatched());
					model.setQPrevPremium(iCQ.getQPrevPremium());
					model.setQValidUntil(iCQ.getQValidUntil());
					model.setQMobile(iCQ.getQMobile());
					model.setQEmail(iCQ.getQEmail());
					model.setQGenPremium(iCQ.getQGenPremium());
					model.setQNumItems(iCQ.getQNumItems());
					model.setQGenDt(iCQ.getQGenDt());
					model.setQNumber(iCQ.getQNumber());
					model.setIclubPersonByQCrtdBy(iCQ.getIclubPersonByQCrtdBy() != null ? (iCQ.getIclubPersonByQCrtdBy().getPId()) : null);
					model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
					model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
					model.setIclubInsurerMaster(iCQ.getIclubInsurerMaster() != null ? (iCQ.getIclubInsurerMaster().getImId()) : null);
					model.setIclubCoverType(iCQ.getIclubCoverType() != null ? (iCQ.getIclubCoverType().getCtId()) : null);
					model.setIclubQuoteStatus(iCQ.getIclubQuoteStatus() != null ? (iCQ.getIclubQuoteStatus().getQsId()) : null);
					model.setIclubPersonByQPersonId(iCQ.getIclubPersonByQPersonId() != null ? (iCQ.getIclubPersonByQPersonId().getPId()) : null);
					
					if (iCQ.getIclubPolicies() != null && iCQ.getIclubPolicies().size() > 0) {
						String[] policies = new String[iCQ.getIclubPolicies().size()];
						int i = 0;
						for (IclubPolicy policy : iCQ.getIclubPolicies()) {
							policies[i] = policy.getPId();
							i++;
						}
					}
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/userstatusId/{user}/{statusId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> getByUserAndStatus(@PathParam("user") String user, @PathParam("statusId") Long statusId) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findIclubQuotesByUserAndStatusId(user, statusId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote iCQ = (IclubQuote) object;
					
					IclubQuoteModel model = new IclubQuoteModel();
					
					model.setQId(iCQ.getQId());
					model.setQCrtdDt(iCQ.getQCrtdDt());
					model.setQIsMatched(iCQ.getQIsMatched());
					model.setQPrevPremium(iCQ.getQPrevPremium());
					model.setQValidUntil(iCQ.getQValidUntil());
					model.setQMobile(iCQ.getQMobile());
					model.setQEmail(iCQ.getQEmail());
					model.setQGenPremium(iCQ.getQGenPremium());
					model.setQNumItems(iCQ.getQNumItems());
					model.setQGenDt(iCQ.getQGenDt());
					model.setQNumber(iCQ.getQNumber());
					model.setIclubPersonByQCrtdBy(iCQ.getIclubPersonByQCrtdBy() != null ? (iCQ.getIclubPersonByQCrtdBy().getPId()) : null);
					model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
					model.setIclubProductType(iCQ.getIclubProductType() != null ? (iCQ.getIclubProductType().getPtId()) : null);
					model.setIclubInsurerMaster(iCQ.getIclubInsurerMaster() != null ? (iCQ.getIclubInsurerMaster().getImId()) : null);
					model.setIclubCoverType(iCQ.getIclubCoverType() != null ? (iCQ.getIclubCoverType().getCtId()) : null);
					model.setIclubQuoteStatus(iCQ.getIclubQuoteStatus() != null ? (iCQ.getIclubQuoteStatus().getQsId()) : null);
					model.setIclubPersonByQPersonId(iCQ.getIclubPersonByQPersonId() != null ? (iCQ.getIclubPersonByQPersonId().getPId()) : null);
					
					if (iCQ.getIclubPolicies() != null && iCQ.getIclubPolicies().size() > 0) {
						String[] policies = new String[iCQ.getIclubPolicies().size()];
						int i = 0;
						for (IclubPolicy policy : iCQ.getIclubPolicies()) {
							policies[i] = policy.getPId();
							i++;
						}
					}
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubQuoteModel getById(@PathParam("id") String id) {
		IclubQuoteModel model = new IclubQuoteModel();
		try {
			IclubQuote bean = iclubQuoteDAO.findById(id);
			
			model.setQId(bean.getQId());
			model.setQCrtdDt(bean.getQCrtdDt());
			model.setQIsMatched(bean.getQIsMatched());
			model.setQPrevPremium(bean.getQPrevPremium());
			model.setQValidUntil(bean.getQValidUntil());
			model.setQMobile(bean.getQMobile());
			model.setQEmail(bean.getQEmail());
			model.setQGenPremium(bean.getQGenPremium());
			model.setQNumItems(bean.getQNumItems());
			model.setQGenDt(bean.getQGenDt());
			model.setQNumber(bean.getQNumber());
			model.setIclubPersonByQCrtdBy(bean.getIclubPersonByQCrtdBy() != null ? (bean.getIclubPersonByQCrtdBy().getPId()) : null);
			model.setIclubProductType(bean.getIclubProductType() != null ? (bean.getIclubProductType().getPtId()) : null);
			model.setIclubProductType(bean.getIclubProductType() != null ? (bean.getIclubProductType().getPtId()) : null);
			model.setIclubInsurerMaster(bean.getIclubInsurerMaster() != null ? (bean.getIclubInsurerMaster().getImId()) : null);
			model.setIclubCoverType(bean.getIclubCoverType() != null ? (bean.getIclubCoverType().getCtId()) : null);
			model.setIclubQuoteStatus(bean.getIclubQuoteStatus() != null ? (bean.getIclubQuoteStatus().getQsId()) : null);
			model.setIclubPersonByQPersonId(bean.getIclubPersonByQPersonId() != null ? (bean.getIclubPersonByQPersonId().getPId()) : null);
			
			if (bean.getIclubPolicies() != null && bean.getIclubPolicies().size() > 0) {
				String[] policies = new String[bean.getIclubPolicies().size()];
				int i = 0;
				for (IclubPolicy policy : bean.getIclubPolicies()) {
					policies[i] = policy.getPId();
					i++;
				}
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}
	
	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubProductTypeDAO getIclubProductTypeDAO() {
		return iclubProductTypeDAO;
	}
	
	public void setIclubProductTypeDAO(IclubProductTypeDAO iclubProductTypeDAO) {
		this.iclubProductTypeDAO = iclubProductTypeDAO;
	}
	
	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}
	
	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
	}
	
	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}
	
	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}
	
	public IclubQuoteStatusDAO getIclubQuoteStatusDAO() {
		return iclubQuoteStatusDAO;
	}
	
	public void setIclubQuoteStatusDAO(IclubQuoteStatusDAO iclubQuoteStatusDAO) {
		this.iclubQuoteStatusDAO = iclubQuoteStatusDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
	public IclubIdTypeDAO getIclubIdTypeDAO() {
		return iclubIdTypeDAO;
	}
	
	public void setIclubIdTypeDAO(IclubIdTypeDAO iclubIdTypeDAO) {
		this.iclubIdTypeDAO = iclubIdTypeDAO;
	}
	
	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}
	
	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}
	
	public IclubGeoLocDAO getIclubGeoLocDAO() {
		return iclubGeoLocDAO;
	}
	
	public void setIclubGeoLocDAO(IclubGeoLocDAO iclubGeoLocDAO) {
		this.iclubGeoLocDAO = iclubGeoLocDAO;
	}
	
	public IclubLicenseCodeDAO getIclubLicenseCodeDAO() {
		return iclubLicenseCodeDAO;
	}
	
	public void setIclubLicenseCodeDAO(IclubLicenseCodeDAO iclubLicenseCodeDAO) {
		this.iclubLicenseCodeDAO = iclubLicenseCodeDAO;
	}
	
	public IclubAccessTypeDAO getIclubAccessTypeDAO() {
		return iclubAccessTypeDAO;
	}
	
	public void setIclubAccessTypeDAO(IclubAccessTypeDAO iclubAccessTypeDAO) {
		this.iclubAccessTypeDAO = iclubAccessTypeDAO;
	}
	
	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}
	
	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}
	
	public IclubDriverDAO getIclubDriverDAO() {
		return iclubDriverDAO;
	}
	
	public void setIclubDriverDAO(IclubDriverDAO iclubDriverDAO) {
		this.iclubDriverDAO = iclubDriverDAO;
	}
	
	public IclubSecurityDeviceDAO getIclubSecurityDeviceDAO() {
		return iclubSecurityDeviceDAO;
	}
	
	public void setIclubSecurityDeviceDAO(IclubSecurityDeviceDAO iclubSecurityDeviceDAO) {
		this.iclubSecurityDeviceDAO = iclubSecurityDeviceDAO;
	}
	
	public IclubVehicleMasterDAO getIclubVehicleMasterDAO() {
		return iclubVehicleMasterDAO;
	}
	
	public void setIclubVehicleMasterDAO(IclubVehicleMasterDAO iclubVehicleMasterDAO) {
		this.iclubVehicleMasterDAO = iclubVehicleMasterDAO;
	}
	
	public IclubVehSecTypeDAO getIclubVehSecTypeDAO() {
		return iclubVehSecTypeDAO;
	}
	
	public void setIclubVehSecTypeDAO(IclubVehSecTypeDAO iclubVehSecTypeDAO) {
		this.iclubVehSecTypeDAO = iclubVehSecTypeDAO;
	}
	
	public IclubVehUsageTypeDAO getIclubVehUsageTypeDAO() {
		return iclubVehUsageTypeDAO;
	}
	
	public void setIclubVehUsageTypeDAO(IclubVehUsageTypeDAO iclubVehUsageTypeDAO) {
		this.iclubVehUsageTypeDAO = iclubVehUsageTypeDAO;
	}
	
	public IclubRoofTypeDAO getIclubRoofTypeDAO() {
		return iclubRoofTypeDAO;
	}
	
	public void setIclubRoofTypeDAO(IclubRoofTypeDAO iclubRoofTypeDAO) {
		this.iclubRoofTypeDAO = iclubRoofTypeDAO;
	}
	
	public IclubBarTypeDAO getIclubBarTypeDAO() {
		return iclubBarTypeDAO;
	}
	
	public void setIclubBarTypeDAO(IclubBarTypeDAO iclubBarTypeDAO) {
		this.iclubBarTypeDAO = iclubBarTypeDAO;
	}
	
	public IclubWallTypeDAO getIclubWallTypeDAO() {
		return iclubWallTypeDAO;
	}
	
	public void setIclubWallTypeDAO(IclubWallTypeDAO iclubWallTypeDAO) {
		this.iclubWallTypeDAO = iclubWallTypeDAO;
	}
	
	public IclubPropertyTypeDAO getIclubPropertyTypeDAO() {
		return iclubPropertyTypeDAO;
	}
	
	public void setIclubPropertyTypeDAO(IclubPropertyTypeDAO iclubPropertyTypeDAO) {
		this.iclubPropertyTypeDAO = iclubPropertyTypeDAO;
	}
	
	public IclubOccupiedStatusDAO getIclubOccupiedStatusDAO() {
		return iclubOccupiedStatusDAO;
	}
	
	public void setIclubOccupiedStatusDAO(IclubOccupiedStatusDAO iclubOccupiedStatusDAO) {
		this.iclubOccupiedStatusDAO = iclubOccupiedStatusDAO;
	}
	
	public IclubPropUsageTypeDAO getIclubPropUsageTypeDAO() {
		return iclubPropUsageTypeDAO;
	}
	
	public void setIclubPropUsageTypeDAO(IclubPropUsageTypeDAO iclubPropUsageTypeDAO) {
		this.iclubPropUsageTypeDAO = iclubPropUsageTypeDAO;
	}
	
	public IclubPropertyDAO getIclubPropertyDAO() {
		return iclubPropertyDAO;
	}
	
	public void setIclubPropertyDAO(IclubPropertyDAO iclubPropertyDAO) {
		this.iclubPropertyDAO = iclubPropertyDAO;
	}
	
	public IclubVehicleDAO getIclubVehicleDAO() {
		return iclubVehicleDAO;
	}
	
	public void setIclubVehicleDAO(IclubVehicleDAO iclubVehicleDAO) {
		this.iclubVehicleDAO = iclubVehicleDAO;
	}
	
	public IclubPropertyItemDAO getIclubPropertyItemDAO() {
		return iclubPropertyItemDAO;
	}
	
	public void setIclubPropertyItemDAO(IclubPropertyItemDAO iclubPropertyItemDAO) {
		this.iclubPropertyItemDAO = iclubPropertyItemDAO;
	}
	
	public IclubInsuranceItemDAO getIclubInsuranceItemDAO() {
		return iclubInsuranceItemDAO;
	}
	
	public void setIclubInsuranceItemDAO(IclubInsuranceItemDAO iclubInsuranceItemDAO) {
		this.iclubInsuranceItemDAO = iclubInsuranceItemDAO;
	}
	
}
