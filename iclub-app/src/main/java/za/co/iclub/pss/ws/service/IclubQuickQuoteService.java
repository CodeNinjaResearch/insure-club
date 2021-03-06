package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.Date;
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

import za.co.iclub.pss.model.ws.IclubDriverModel;
import za.co.iclub.pss.model.ws.IclubPropertyItemModel;
import za.co.iclub.pss.model.ws.IclubPropertyModel;
import za.co.iclub.pss.model.ws.IclubQuickQuoteRequest;
import za.co.iclub.pss.model.ws.IclubQuickQuoteResponse;
import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.model.ws.IclubVehicleModel;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubEntityType;
import za.co.iclub.pss.orm.bean.IclubField;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubPropertyItem;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubAccessTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBarTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubDriverDAO;
import za.co.iclub.pss.orm.dao.IclubGeoLocDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubLicenseCodeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritalStatusDAO;
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
import za.co.iclub.pss.trans.IclubDriverTrans;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.trans.IclubPropertyItemTrans;
import za.co.iclub.pss.trans.IclubPropertyTrans;
import za.co.iclub.pss.trans.IclubQuoteTrans;
import za.co.iclub.pss.trans.IclubVehicleTrans;
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
	private IclubMaritalStatusDAO IclubMaritalStatusDAO;
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
	private IclubCohortDAO iclubCohortDAO;
	private IclubCohortInviteDAO iclubCohortInviteDAO;

	@POST
	@Path("/createQuote")
	@Transactional
	public IclubQuickQuoteResponse createQuote(IclubQuickQuoteRequest iclubQuickQuoteRequest) {

		Double generatedPremium = 0.0;
		IclubPerson iclubPerson = null;
		if (!iclubQuickQuoteRequest.isLoginFlag()) {
			iclubPerson = IclubPersonTrans.fromWStoORM(iclubQuickQuoteRequest.getIclubPersonModel(), iclubIdTypeDAO, iclubPersonDAO, IclubMaritalStatusDAO, iclubCohortDAO, iclubCohortInviteDAO);
			iclubPersonDAO.save(iclubPerson);
		} else {
			iclubPerson = IclubPersonTrans.fromWStoORM(iclubQuickQuoteRequest.getIclubPersonModel(), iclubIdTypeDAO, iclubPersonDAO, IclubMaritalStatusDAO, iclubCohortDAO, iclubCohortInviteDAO);
			iclubPersonDAO.merge(iclubPerson);
		}

		IclubQuote iclubQuote = IclubQuoteTrans.fromWStoORM(iclubQuickQuoteRequest.getIclubQuoteModel(), iclubQuoteStatusDAO, iclubCoverTypeDAO, iclubInsurerMasterDAO, iclubProductTypeDAO, iclubPersonDAO);
		iclubQuoteDAO.save(iclubQuote);
		String quoteNumber = iclubQuote.getQId();
		IclubDriverModel iclubDriverModel = iclubQuickQuoteRequest.getIclubDriverModel();
		IclubDriver iclubDriver = IclubDriverTrans.fromWStoORM(iclubDriverModel, iclubAccessTypeDAO, iclubLicenseCodeDAO, IclubMaritalStatusDAO, iclubPersonDAO);
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

				IclubProperty iCP = IclubPropertyTrans.fromWStoORM(model, iclubPersonDAO, iclubCoverTypeDAO, iclubPropUsageTypeDAO, iclubOccupiedStatusDAO, iclubPropertyTypeDAO, iclubWallTypeDAO, iclubAccessTypeDAO, iclubBarTypeDAO, iclubRoofTypeDAO);

				iclubProperties.add(iCP);
			}
		}

		return iclubProperties;
	}

	public List<IclubPropertyItem> getIclubPropertyItems(List<IclubPropertyItemModel> models) {
		List<IclubPropertyItem> iclubProeprtyItems = new ArrayList<IclubPropertyItem>();

		if (models != null && models.size() > 0) {
			for (IclubPropertyItemModel model : models) {
				IclubPropertyItem iTt = IclubPropertyItemTrans.fromWStoORM(model, iclubPersonDAO, iclubPropertyDAO);

				iclubProeprtyItems.add(iTt);
			}
		}

		return iclubProeprtyItems;
	}

	public List<IclubVehicle> getVehicleList(List<IclubVehicleModel> models, IclubQuote iclubQuote) {
		List<IclubVehicle> iclubVehicles = new ArrayList<IclubVehicle>();

		for (IclubVehicleModel model : models) {
			IclubVehicle iCV = IclubVehicleTrans.fromWStoORM(model, iclubVehicleMasterDAO, iclubAccessTypeDAO, iclubDriverDAO, iclubSecurityDeviceDAO, iclubVehSecTypeDAO, iclubPersonDAO, iclubCoverTypeDAO, iclubVehUsageTypeDAO);

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
				iCTt.setIiCrtdDt(new Date(System.currentTimeMillis()));
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
				iCTt.setIiCrtdDt(new Date(System.currentTimeMillis()));
				iCTt.setIclubInsuranceItemType(iclubInsuranceItemTypeDAO.findById(2l));
				iCTt.setIclubPerson(property.getIclubPerson() != null && !property.getIclubPerson().getPId().equalsIgnoreCase("") ? iclubPersonDAO.findById(property.getIclubPerson().getPId()) : null);
				iclubInsuranceItems.add(iCTt);
			}
		}

		return iclubInsuranceItems;
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
			IclubQuote iCQ = IclubQuoteTrans.fromWStoORM(model, iclubQuoteStatusDAO, iclubCoverTypeDAO, iclubInsurerMasterDAO, iclubProductTypeDAO, iclubPersonDAO);

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
			IclubQuote iCQ = IclubQuoteTrans.fromWStoORM(model, iclubQuoteStatusDAO, iclubCoverTypeDAO, iclubInsurerMasterDAO, iclubProductTypeDAO, iclubPersonDAO);

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
					IclubQuote bean = (IclubQuote) object;

					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);

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
					IclubQuote bean = (IclubQuote) object;

					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);

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
					IclubQuote bean = (IclubQuote) object;

					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);

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

			model = IclubQuoteTrans.fromORMtoWS(bean);

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

	public IclubMaritalStatusDAO getIclubMaritalStatusDAO() {
		return IclubMaritalStatusDAO;
	}

	public void setIclubMaritalStatusDAO(IclubMaritalStatusDAO IclubMaritalStatusDAO) {
		this.IclubMaritalStatusDAO = IclubMaritalStatusDAO;
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

	public IclubCohortDAO getIclubCohortDAO() {
		return iclubCohortDAO;
	}

	public void setIclubCohortDAO(IclubCohortDAO iclubCohortDAO) {
		this.iclubCohortDAO = iclubCohortDAO;
	}

	public IclubCohortInviteDAO getIclubCohortInviteDAO() {
		return iclubCohortInviteDAO;
	}

	public void setIclubCohortInviteDAO(IclubCohortInviteDAO iclubCohortInviteDAO) {
		this.iclubCohortInviteDAO = iclubCohortInviteDAO;
	}

}
