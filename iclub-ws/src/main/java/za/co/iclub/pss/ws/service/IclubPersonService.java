package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubAccount;
import za.co.iclub.pss.orm.bean.IclubBankMaster;
import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.bean.IclubCountryCode;
import za.co.iclub.pss.orm.bean.IclubCoverType;
import za.co.iclub.pss.orm.bean.IclubDocument;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubEvent;
import za.co.iclub.pss.orm.bean.IclubExtras;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.bean.IclubInsurerMaster;
import za.co.iclub.pss.orm.bean.IclubLicenseCode;
import za.co.iclub.pss.orm.bean.IclubLogin;
import za.co.iclub.pss.orm.bean.IclubMbComment;
import za.co.iclub.pss.orm.bean.IclubMessage;
import za.co.iclub.pss.orm.bean.IclubMessageBoard;
import za.co.iclub.pss.orm.bean.IclubNotif;
import za.co.iclub.pss.orm.bean.IclubOccupation;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubPurposeType;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.bean.IclubSecurityDevice;
import za.co.iclub.pss.orm.bean.IclubSecurityMaster;
import za.co.iclub.pss.orm.bean.IclubSupplMaster;
import za.co.iclub.pss.orm.bean.IclubTrackerMaster;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.bean.IclubVehicleMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path("/IclubPersonService")
public class IclubPersonService {
	protected static final Logger LOGGER = Logger.getLogger(IclubPersonService.class);
	private IclubPersonDAO iclubPersonDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubIdTypeDAO iclubIdTypeDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;

	@POST
	@Path("/add")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPersonModel model) {
		try {
			IclubPerson person = new IclubPerson();

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
			person.setPInitials(model.getPInitials());
			person.setPIsPensioner(model.getPIsPensioner());
			person.setPIdIssueCntry(model.getPIdIssueCntry());
			person.setPLat(model.getPLat());
			person.setPLong(model.getPLong());
			person.setPOccupation(model.getPOccupation());
			person.setPTitle(model.getPTitle());
			person.setPZipCd(model.getPZipCd());
			person.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
			person.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			person.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
			iclubPersonDAO.save(person);

			LOGGER.info("Save Success with ID :: " + person.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/mod")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPersonModel model) {
		try {
			IclubPerson person = new IclubPerson();

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
			person.setPInitials(model.getPInitials());
			person.setPIsPensioner(model.getPIsPensioner());
			person.setPIdIssueCntry(model.getPIdIssueCntry());
			person.setPLat(model.getPLat());
			person.setPLong(model.getPLong());
			person.setPOccupation(model.getPOccupation());
			person.setPTitle(model.getPTitle());
			person.setPZipCd(model.getPZipCd());
			person.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
			person.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			person.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);

			iclubPersonDAO.merge(person);

			LOGGER.info("Merge Success with ID :: " + model.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@GET
	@Path("/del/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPersonDAO.delete(iclubPersonDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPersonDAO.findAll();

			for (Object object : batmod) {
				IclubPerson iPerson = (IclubPerson) object;

				IclubPersonModel model = new IclubPersonModel();
				model.setPId(iPerson.getPId());
				model.setPCrtdDt(iPerson.getPCrtdDt());
				model.setPDob(iPerson.getPDob());
				model.setPEmail(iPerson.getPEmail());
				model.setPFName(iPerson.getPFName());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPLName(iPerson.getPLName());
				model.setPMobile(iPerson.getPMobile());
				model.setPAddress(iPerson.getPAddress());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPGender(iPerson.getPGender());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPIdExpiryDt(iPerson.getPIdExpiryDt());
				model.setPInitials(iPerson.getPInitials());
				model.setPIsPensioner(iPerson.getPIsPensioner());
				model.setPIdIssueCntry(iPerson.getPIdIssueCntry());
				model.setPLat(iPerson.getPLat());
				model.setPLong(iPerson.getPLong());
				model.setPOccupation(iPerson.getPOccupation());
				model.setPTitle(iPerson.getPTitle());
				model.setPZipCd(iPerson.getPZipCd());
				model.setIclubIdType(iPerson.getIclubIdType() != null ? (iPerson.getIclubIdType().getItId()) : null);
				model.setIclubMaritialStatus(iPerson.getIclubMaritialStatus() != null ? (iPerson.getIclubMaritialStatus().getMsId()) : null);
				model.setIclubPerson(iPerson.getIclubPerson() != null ? iPerson.getIclubPerson().getPId() : null);

				if (iPerson.getIclubMessageBoards() != null && iPerson.getIclubMessageBoards().size() > 0) {
					String[] iclubMessageBoards = new String[iPerson.getIclubMessageBoards().size()];
					int i = 0;
					for (IclubMessageBoard iclubMessageBoard : iPerson.getIclubMessageBoards()) {
						iclubMessageBoards[i] = iclubMessageBoard.getMbId();
						i++;
					}
					model.setIclubMessageBoards(iclubMessageBoards);
				}
				if (iPerson.getIclubPersons() != null && iPerson.getIclubPersons().size() > 0) {
					String[] iclubPersons = new String[iPerson.getIclubPersons().size()];
					int i = 0;
					for (IclubPerson iclubPerson : iPerson.getIclubPersons()) {
						iclubPersons[i] = iclubPerson.getPId();
						i++;
					}
					model.setIclubPersons(iclubPersons);
				}
				if (iPerson.getIclubExtrases() != null && iPerson.getIclubExtrases().size() > 0) {
					Long[] iclubExtrases = new Long[iPerson.getIclubExtrases().size()];
					int i = 0;
					for (IclubExtras iclubExtras : iPerson.getIclubExtrases()) {
						iclubExtrases[i] = iclubExtras.getEId();
						i++;
					}
					model.setIclubExtrases(iclubExtrases);
				}
				if (iPerson.getIclubSecurityMasters() != null && iPerson.getIclubSecurityMasters().size() > 0) {
					String[] iclubSecurityMasters = new String[iPerson.getIclubSecurityMasters().size()];
					int i = 0;
					for (IclubSecurityMaster iclubSecurityMaster : iPerson.getIclubSecurityMasters()) {
						iclubSecurityMasters[i] = iclubSecurityMaster.getSmId();
						i++;
					}
					model.setIclubSecurityMasters(iclubSecurityMasters);
				}
				if (iPerson.getIclubProperties() != null && iPerson.getIclubProperties().size() > 0) {
					String[] iclubProperties = new String[iPerson.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iPerson.getIclubProperties()) {
						iclubProperties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(iclubProperties);
				}
				if (iPerson.getIclubInsurerMasters() != null && iPerson.getIclubInsurerMasters().size() > 0) {
					Long[] iclubInsurerMasters = new Long[iPerson.getIclubInsurerMasters().size()];
					int i = 0;
					for (IclubInsurerMaster iclubInsurerMaster : iPerson.getIclubInsurerMasters()) {
						iclubInsurerMasters[i] = iclubInsurerMaster.getImId();
						i++;
					}
					model.setIclubInsurerMasters(iclubInsurerMasters);
				}
				if (iPerson.getIclubLicenseCodes() != null && iPerson.getIclubLicenseCodes().size() > 0) {
					Long[] iclubLicenseCodes = new Long[iPerson.getIclubLicenseCodes().size()];
					int i = 0;
					for (IclubLicenseCode iclubLicenseCode : iPerson.getIclubLicenseCodes()) {
						iclubLicenseCodes[i] = iclubLicenseCode.getLcId();
						i++;
					}
					model.setIclubLicenseCodes(iclubLicenseCodes);
				}
				if (iPerson.getIclubAccounts() != null && iPerson.getIclubAccounts().size() > 0) {
					String[] iclubAccounts = new String[iPerson.getIclubAccounts().size()];
					int i = 0;
					for (IclubAccount iclubAccount : iPerson.getIclubAccounts()) {
						iclubAccounts[i] = iclubAccount.getAId();
						i++;
					}
					model.setIclubAccounts(iclubAccounts);
				}
				if (iPerson.getIclubVehicles() != null && iPerson.getIclubVehicles().size() > 0) {
					String[] iclubVehicles = new String[iPerson.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle iclubVehicle : iPerson.getIclubVehicles()) {
						iclubVehicles[i] = iclubVehicle.getVId();
						i++;
					}
					model.setIclubVehicles(iclubVehicles);
				}
				if (iPerson.getIclubSupplMasters() != null && iPerson.getIclubSupplMasters().size() > 0) {
					String[] iclubSupplMasters = new String[iPerson.getIclubSupplMasters().size()];
					int i = 0;
					for (IclubSupplMaster iclubSupplMaster : iPerson.getIclubSupplMasters()) {
						iclubSupplMasters[i] = iclubSupplMaster.getSmId();
						i++;
					}
					model.setIclubSupplMasters(iclubSupplMasters);
				}
				if (iPerson.getIclubConfigs() != null && iPerson.getIclubConfigs().size() > 0) {
					Long[] iclubConfigs = new Long[iPerson.getIclubConfigs().size()];
					int i = 0;
					for (IclubConfig iclubConfig : iPerson.getIclubConfigs()) {
						iclubConfigs[i] = iclubConfig.getCId();
						i++;
					}
					model.setIclubConfigs(iclubConfigs);
				}
				if (iPerson.getIclubClaims() != null && iPerson.getIclubClaims().size() > 0) {
					String[] iclubClaims = new String[iPerson.getIclubClaims().size()];
					int i = 0;
					for (IclubClaim iclubClaim : iPerson.getIclubClaims()) {
						iclubClaims[i] = iclubClaim.getCId();
						i++;
					}
					model.setIclubClaims(iclubClaims);
				}
				if (iPerson.getIclubRateTypes() != null && iPerson.getIclubRateTypes().size() > 0) {
					Long[] iclubRateTypes = new Long[iPerson.getIclubRateTypes().size()];
					int i = 0;
					for (IclubRateType iclubRateType : iPerson.getIclubRateTypes()) {
						iclubRateTypes[i] = iclubRateType.getRtId();
						i++;
					}
					model.setIclubRateTypes(iclubRateTypes);
				}
				if (iPerson.getIclubEvents() != null && iPerson.getIclubEvents().size() > 0) {
					String[] iclubEvents = new String[iPerson.getIclubEvents().size()];
					int i = 0;
					for (IclubEvent iclubEvent : iPerson.getIclubEvents()) {
						iclubEvents[i] = iclubEvent.getEId();
						i++;
					}
					model.setIclubEvents(iclubEvents);
				}
				if (iPerson.getIclubOccupations() != null && iPerson.getIclubOccupations().size() > 0) {
					Long[] iclubOccupations = new Long[iPerson.getIclubOccupations().size()];
					int i = 0;
					for (IclubOccupation iclubOccupation : iPerson.getIclubOccupations()) {
						iclubOccupations[i] = iclubOccupation.getOId();
						i++;
					}
					model.setIclubOccupations(iclubOccupations);
				}
				if (iPerson.getIclubPayments() != null && iPerson.getIclubPayments().size() > 0) {
					String[] iclubPayments = new String[iPerson.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment iclubPayment : iPerson.getIclubPayments()) {
						iclubPayments[i] = iclubPayment.getPId();
						i++;
					}
					model.setIclubPayments(iclubPayments);
				}
				if (iPerson.getIclubSecurityDevices() != null && iPerson.getIclubSecurityDevices().size() > 0) {
					String[] iclubSecurityDevices = new String[iPerson.getIclubSecurityDevices().size()];
					int i = 0;
					for (IclubSecurityDevice iclubSecurityDevice : iPerson.getIclubSecurityDevices()) {
						iclubSecurityDevices[i] = iclubSecurityDevice.getSdId();
						i++;
					}
					model.setIclubSecurityDevices(iclubSecurityDevices);
				}
				if (iPerson.getIclubPolicies() != null && iPerson.getIclubPolicies().size() > 0) {
					String[] iclubPolicies = new String[iPerson.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy iclubPolicy : iPerson.getIclubPolicies()) {
						iclubPolicies[i] = iclubPolicy.getPId();
						i++;
					}
					model.setIclubPolicies(iclubPolicies);
				}
				if (iPerson.getIclubMbComments() != null && iPerson.getIclubMbComments().size() > 0) {
					String[] iclubMbComments = new String[iPerson.getIclubMbComments().size()];
					int i = 0;
					for (IclubMbComment iclubMbComment : iPerson.getIclubMbComments()) {
						iclubMbComments[i] = iclubMbComment.getMbcId();
						i++;
					}
					model.setIclubMbComments(iclubMbComments);
				}
				if (iPerson.getIclubQuotesForQCrtdBy() != null && iPerson.getIclubQuotesForQCrtdBy().size() > 0) {
					String[] iclubQuotesForQCrtdBy = new String[iPerson.getIclubQuotesForQCrtdBy().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iPerson.getIclubQuotesForQCrtdBy()) {
						iclubQuotesForQCrtdBy[i] = iclubQuote.getQId();
						i++;
					}
					model.setIclubQuotesForQCrtdBy(iclubQuotesForQCrtdBy);
				}

				if (iPerson.getIclubDocuments() != null && iPerson.getIclubDocuments().size() > 0) {
					String[] iclubDocuments = new String[iPerson.getIclubDocuments().size()];
					int i = 0;
					for (IclubDocument iclubDocument : iPerson.getIclubDocuments()) {
						iclubDocuments[i] = iclubDocument.getDId();
						i++;
					}
					model.setIclubDocuments(iclubDocuments);
				}
				if (iPerson.getIclubRateEngines() != null && iPerson.getIclubRateEngines().size() > 0) {
					String[] iclubRateEngines = new String[iPerson.getIclubRateEngines().size()];
					int i = 0;
					for (IclubRateEngine iclubRateEngine : iPerson.getIclubRateEngines()) {
						iclubRateEngines[i] = iclubRateEngine.getReId();
						i++;
					}
					model.setIclubRateEngines(iclubRateEngines);
				}
				if (iPerson.getIclubGeoLocs() != null && iPerson.getIclubGeoLocs().size() > 0) {
					Long[] iclubGeoLocs = new Long[iPerson.getIclubGeoLocs().size()];
					int i = 0;
					for (IclubGeoLoc iclubGeoLoc : iPerson.getIclubGeoLocs()) {
						iclubGeoLocs[i] = iclubGeoLoc.getGlId();
						i++;
					}
					model.setIclubGeoLocs(iclubGeoLocs);
				}
				if (iPerson.getIclubQuotesForQPersonId() != null && iPerson.getIclubQuotesForQPersonId().size() > 0) {
					String[] iclubQuotesForQPersonId = new String[iPerson.getIclubQuotesForQPersonId().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iPerson.getIclubQuotesForQPersonId()) {
						iclubQuotesForQPersonId[i] = iclubQuote.getQId();
						i++;
					}
					model.setIclubQuotesForQPersonId(iclubQuotesForQPersonId);
				}
				if (iPerson.getIclubTrackerMasters() != null && iPerson.getIclubTrackerMasters().size() > 0) {
					Long[] iclubTrackerMasters = new Long[iPerson.getIclubTrackerMasters().size()];
					int i = 0;
					for (IclubTrackerMaster iclubTrackerMaster : iPerson.getIclubTrackerMasters()) {
						iclubTrackerMasters[i] = iclubTrackerMaster.getTmId();
						i++;
					}
					model.setIclubTrackerMasters(iclubTrackerMasters);
				}
				if (iPerson.getIclubCoverTypes() != null && iPerson.getIclubCoverTypes().size() > 0) {
					Long[] iclubCoverTypes = new Long[iPerson.getIclubVehicles().size()];
					int i = 0;
					for (IclubCoverType iclubCoverType : iPerson.getIclubCoverTypes()) {
						iclubCoverTypes[i] = iclubCoverType.getCtId();
						i++;
					}
					model.setIclubCoverTypes(iclubCoverTypes);
				}
				if (iPerson.getIclubCountryCodes() != null && iPerson.getIclubCountryCodes().size() > 0) {
					Integer[] iclubCountryCodes = new Integer[iPerson.getIclubCountryCodes().size()];
					int i = 0;
					for (IclubCountryCode iclubCountryCode : iPerson.getIclubCountryCodes()) {
						iclubCountryCodes[i] = iclubCountryCode.getCcId();
						i++;
					}
					model.setIclubCountryCodes(iclubCountryCodes);
				}
				if (iPerson.getIclubBankMasters() != null && iPerson.getIclubBankMasters().size() > 0) {
					Long[] iclubBankMasters = new Long[iPerson.getIclubBankMasters().size()];
					int i = 0;
					for (IclubBankMaster iclubBankMaster : iPerson.getIclubBankMasters()) {
						iclubBankMasters[i] = iclubBankMaster.getBmId();
						i++;
					}
					model.setIclubBankMasters(iclubBankMasters);
				}

				if (iPerson.getIclubNotifs() != null && iPerson.getIclubNotifs().size() > 0) {
					String[] iclubNotifs = new String[iPerson.getIclubNotifs().size()];
					int i = 0;
					for (IclubNotif iclubNotif : iPerson.getIclubNotifs()) {
						iclubNotifs[i] = iclubNotif.getNId();
						i++;
					}
					model.setIclubNotifs(iclubNotifs);
				}
				if (iPerson.getIclubVehicleMasters() != null && iPerson.getIclubVehicleMasters().size() > 0) {
					Long[] iclubVehicleMasters = new Long[iPerson.getIclubVehicleMasters().size()];
					int i = 0;
					for (IclubVehicleMaster iclubVehicleMaster : iPerson.getIclubVehicleMasters()) {
						iclubVehicleMasters[i] = iclubVehicleMaster.getVmId();
						i++;
					}
					model.setIclubVehicleMasters(iclubVehicleMasters);
				}
				if (iPerson.getIclubDriversForDCrtdBy() != null && iPerson.getIclubDriversForDCrtdBy().size() > 0) {
					String[] iclubDriversForDCrtdBy = new String[iPerson.getIclubDriversForDCrtdBy().size()];
					int i = 0;
					for (IclubDriver iclubDriver : iPerson.getIclubDriversForDCrtdBy()) {
						iclubDriversForDCrtdBy[i] = iclubDriver.getDId();
						i++;
					}
					model.setIclubDriversForDCrtdBy(iclubDriversForDCrtdBy);
				}
				if (iPerson.getIclubPurposeTypes() != null && iPerson.getIclubPurposeTypes().size() > 0) {
					Long[] purposeTypes = new Long[iPerson.getIclubPurposeTypes().size()];
					int i = 0;
					for (IclubPurposeType iclubPurposeType : iPerson.getIclubPurposeTypes()) {
						purposeTypes[i] = iclubPurposeType.getPtId();
						i++;
					}
					model.setIclubPurposeTypes(purposeTypes);
				}
				if (iPerson.getIclubDriversForDPersonId() != null && iPerson.getIclubDriversForDPersonId().size() > 0) {
					String[] driversForDPersonId = new String[iPerson.getIclubDriversForDPersonId().size()];
					int i = 0;
					for (IclubDriver iclubDriver : iPerson.getIclubDriversForDPersonId()) {
						driversForDPersonId[i] = iclubDriver.getDId();
						i++;
					}
					model.setIclubDriversForDPersonId(driversForDPersonId);
				}
				if (iPerson.getIclubInsuranceItems() != null && iPerson.getIclubInsuranceItems().size() > 0) {
					String[] insuranceItems = new String[iPerson.getIclubInsuranceItems().size()];
					int i = 0;
					for (IclubInsuranceItem iclubInsuranceItem : iPerson.getIclubInsuranceItems()) {
						insuranceItems[i] = iclubInsuranceItem.getIiId();
						i++;
					}
					model.setIclubInsuranceItems(insuranceItems);
				}
				if (iPerson.getIclubLoginsForLPersonId() != null && iPerson.getIclubLoginsForLPersonId().size() > 0) {
					String[] loginsForLPersonId = new String[iPerson.getIclubLoginsForLPersonId().size()];
					int i = 0;
					for (IclubLogin iclubLogin : iPerson.getIclubLoginsForLPersonId()) {
						loginsForLPersonId[i] = iclubLogin.getLId();
						i++;
					}
					model.setIclubLoginsForLPersonId(loginsForLPersonId);
				}
				if (iPerson.getIclubLoginsForLCrtdBy() != null && iPerson.getIclubLoginsForLCrtdBy().size() > 0) {
					String[] LoginsForLCrtdBy = new String[iPerson.getIclubLoginsForLCrtdBy().size()];
					int i = 0;
					for (IclubLogin iclubLogin : iPerson.getIclubLoginsForLCrtdBy()) {
						LoginsForLCrtdBy[i] = iclubLogin.getLId();
						i++;
					}
					model.setIclubLoginsForLCrtdBy(LoginsForLCrtdBy);
				}
				if (iPerson.getIclubMessages() != null && iPerson.getIclubMessages().size() > 0) {
					String[] messages = new String[iPerson.getIclubMessages().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iPerson.getIclubMessages()) {
						messages[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessages(messages);
				}
				ret.add((T) model);
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPersonDAO.findByUser(user);

			for (Object object : batmod) {
				IclubPerson iPerson = (IclubPerson) object;

				IclubPersonModel model = new IclubPersonModel();
				model.setPId(iPerson.getPId());
				model.setPCrtdDt(iPerson.getPCrtdDt());
				model.setPDob(iPerson.getPDob());
				model.setPEmail(iPerson.getPEmail());
				model.setPFName(iPerson.getPFName());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPLName(iPerson.getPLName());
				model.setPMobile(iPerson.getPMobile());
				model.setPAddress(iPerson.getPAddress());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPGender(iPerson.getPGender());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPIdExpiryDt(iPerson.getPIdExpiryDt());
				model.setPInitials(iPerson.getPInitials());
				model.setPIsPensioner(iPerson.getPIsPensioner());
				model.setPIdIssueCntry(iPerson.getPIdIssueCntry());
				model.setPLat(iPerson.getPLat());
				model.setPLong(iPerson.getPLong());
				model.setPOccupation(iPerson.getPOccupation());
				model.setPTitle(iPerson.getPTitle());
				model.setPZipCd(iPerson.getPZipCd());
				model.setIclubIdType(iPerson.getIclubIdType() != null ? (iPerson.getIclubIdType().getItId()) : null);
				model.setIclubPerson(iPerson.getIclubPerson() != null ? iPerson.getIclubPerson().getPId() : null);
				model.setIclubMaritialStatus(iPerson.getIclubMaritialStatus() != null ? (iPerson.getIclubMaritialStatus().getMsId()) : null);

				if (iPerson.getIclubMessageBoards() != null && iPerson.getIclubMessageBoards().size() > 0) {
					String[] iclubMessageBoards = new String[iPerson.getIclubMessageBoards().size()];
					int i = 0;
					for (IclubMessageBoard iclubMessageBoard : iPerson.getIclubMessageBoards()) {
						iclubMessageBoards[i] = iclubMessageBoard.getMbId();
						i++;
					}
					model.setIclubMessageBoards(iclubMessageBoards);
				}
				if (iPerson.getIclubPersons() != null && iPerson.getIclubPersons().size() > 0) {
					String[] iclubPersons = new String[iPerson.getIclubPersons().size()];
					int i = 0;
					for (IclubPerson iclubPerson : iPerson.getIclubPersons()) {
						iclubPersons[i] = iclubPerson.getPId();
						i++;
					}
					model.setIclubPersons(iclubPersons);
				}
				if (iPerson.getIclubExtrases() != null && iPerson.getIclubExtrases().size() > 0) {
					Long[] iclubExtrases = new Long[iPerson.getIclubExtrases().size()];
					int i = 0;
					for (IclubExtras iclubExtras : iPerson.getIclubExtrases()) {
						iclubExtrases[i] = iclubExtras.getEId();
						i++;
					}
					model.setIclubExtrases(iclubExtrases);
				}
				if (iPerson.getIclubSecurityMasters() != null && iPerson.getIclubSecurityMasters().size() > 0) {
					String[] iclubSecurityMasters = new String[iPerson.getIclubSecurityMasters().size()];
					int i = 0;
					for (IclubSecurityMaster iclubSecurityMaster : iPerson.getIclubSecurityMasters()) {
						iclubSecurityMasters[i] = iclubSecurityMaster.getSmId();
						i++;
					}
					model.setIclubSecurityMasters(iclubSecurityMasters);
				}
				if (iPerson.getIclubProperties() != null && iPerson.getIclubProperties().size() > 0) {
					String[] iclubProperties = new String[iPerson.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iPerson.getIclubProperties()) {
						iclubProperties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(iclubProperties);
				}
				if (iPerson.getIclubInsurerMasters() != null && iPerson.getIclubInsurerMasters().size() > 0) {
					Long[] iclubInsurerMasters = new Long[iPerson.getIclubInsurerMasters().size()];
					int i = 0;
					for (IclubInsurerMaster iclubInsurerMaster : iPerson.getIclubInsurerMasters()) {
						iclubInsurerMasters[i] = iclubInsurerMaster.getImId();
						i++;
					}
					model.setIclubInsurerMasters(iclubInsurerMasters);
				}
				if (iPerson.getIclubLicenseCodes() != null && iPerson.getIclubLicenseCodes().size() > 0) {
					Long[] iclubLicenseCodes = new Long[iPerson.getIclubLicenseCodes().size()];
					int i = 0;
					for (IclubLicenseCode iclubLicenseCode : iPerson.getIclubLicenseCodes()) {
						iclubLicenseCodes[i] = iclubLicenseCode.getLcId();
						i++;
					}
					model.setIclubLicenseCodes(iclubLicenseCodes);
				}
				if (iPerson.getIclubAccounts() != null && iPerson.getIclubAccounts().size() > 0) {
					String[] iclubAccounts = new String[iPerson.getIclubAccounts().size()];
					int i = 0;
					for (IclubAccount iclubAccount : iPerson.getIclubAccounts()) {
						iclubAccounts[i] = iclubAccount.getAId();
						i++;
					}
					model.setIclubAccounts(iclubAccounts);
				}
				if (iPerson.getIclubVehicles() != null && iPerson.getIclubVehicles().size() > 0) {
					String[] iclubVehicles = new String[iPerson.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle iclubVehicle : iPerson.getIclubVehicles()) {
						iclubVehicles[i] = iclubVehicle.getVId();
						i++;
					}
					model.setIclubVehicles(iclubVehicles);
				}
				if (iPerson.getIclubSupplMasters() != null && iPerson.getIclubSupplMasters().size() > 0) {
					String[] iclubSupplMasters = new String[iPerson.getIclubSupplMasters().size()];
					int i = 0;
					for (IclubSupplMaster iclubSupplMaster : iPerson.getIclubSupplMasters()) {
						iclubSupplMasters[i] = iclubSupplMaster.getSmId();
						i++;
					}
					model.setIclubSupplMasters(iclubSupplMasters);
				}
				if (iPerson.getIclubConfigs() != null && iPerson.getIclubConfigs().size() > 0) {
					Long[] iclubConfigs = new Long[iPerson.getIclubConfigs().size()];
					int i = 0;
					for (IclubConfig iclubConfig : iPerson.getIclubConfigs()) {
						iclubConfigs[i] = iclubConfig.getCId();
						i++;
					}
					model.setIclubConfigs(iclubConfigs);
				}
				if (iPerson.getIclubClaims() != null && iPerson.getIclubClaims().size() > 0) {
					String[] iclubClaims = new String[iPerson.getIclubClaims().size()];
					int i = 0;
					for (IclubClaim iclubClaim : iPerson.getIclubClaims()) {
						iclubClaims[i] = iclubClaim.getCId();
						i++;
					}
					model.setIclubClaims(iclubClaims);
				}
				if (iPerson.getIclubRateTypes() != null && iPerson.getIclubRateTypes().size() > 0) {
					Long[] iclubRateTypes = new Long[iPerson.getIclubRateTypes().size()];
					int i = 0;
					for (IclubRateType iclubRateType : iPerson.getIclubRateTypes()) {
						iclubRateTypes[i] = iclubRateType.getRtId();
						i++;
					}
					model.setIclubRateTypes(iclubRateTypes);
				}
				if (iPerson.getIclubEvents() != null && iPerson.getIclubEvents().size() > 0) {
					String[] iclubEvents = new String[iPerson.getIclubEvents().size()];
					int i = 0;
					for (IclubEvent iclubEvent : iPerson.getIclubEvents()) {
						iclubEvents[i] = iclubEvent.getEId();
						i++;
					}
					model.setIclubEvents(iclubEvents);
				}
				if (iPerson.getIclubOccupations() != null && iPerson.getIclubOccupations().size() > 0) {
					Long[] iclubOccupations = new Long[iPerson.getIclubOccupations().size()];
					int i = 0;
					for (IclubOccupation iclubOccupation : iPerson.getIclubOccupations()) {
						iclubOccupations[i] = iclubOccupation.getOId();
						i++;
					}
					model.setIclubOccupations(iclubOccupations);
				}
				if (iPerson.getIclubPayments() != null && iPerson.getIclubPayments().size() > 0) {
					String[] iclubPayments = new String[iPerson.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment iclubPayment : iPerson.getIclubPayments()) {
						iclubPayments[i] = iclubPayment.getPId();
						i++;
					}
					model.setIclubPayments(iclubPayments);
				}
				if (iPerson.getIclubSecurityDevices() != null && iPerson.getIclubSecurityDevices().size() > 0) {
					String[] iclubSecurityDevices = new String[iPerson.getIclubSecurityDevices().size()];
					int i = 0;
					for (IclubSecurityDevice iclubSecurityDevice : iPerson.getIclubSecurityDevices()) {
						iclubSecurityDevices[i] = iclubSecurityDevice.getSdId();
						i++;
					}
					model.setIclubSecurityDevices(iclubSecurityDevices);
				}
				if (iPerson.getIclubPolicies() != null && iPerson.getIclubPolicies().size() > 0) {
					String[] iclubPolicies = new String[iPerson.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy iclubPolicy : iPerson.getIclubPolicies()) {
						iclubPolicies[i] = iclubPolicy.getPId();
						i++;
					}
					model.setIclubPolicies(iclubPolicies);
				}
				if (iPerson.getIclubMbComments() != null && iPerson.getIclubMbComments().size() > 0) {
					String[] iclubMbComments = new String[iPerson.getIclubMbComments().size()];
					int i = 0;
					for (IclubMbComment iclubMbComment : iPerson.getIclubMbComments()) {
						iclubMbComments[i] = iclubMbComment.getMbcId();
						i++;
					}
					model.setIclubMbComments(iclubMbComments);
				}
				if (iPerson.getIclubQuotesForQCrtdBy() != null && iPerson.getIclubQuotesForQCrtdBy().size() > 0) {
					String[] iclubQuotesForQCrtdBy = new String[iPerson.getIclubQuotesForQCrtdBy().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iPerson.getIclubQuotesForQCrtdBy()) {
						iclubQuotesForQCrtdBy[i] = iclubQuote.getQId();
						i++;
					}
					model.setIclubQuotesForQCrtdBy(iclubQuotesForQCrtdBy);
				}

				if (iPerson.getIclubDocuments() != null && iPerson.getIclubDocuments().size() > 0) {
					String[] iclubDocuments = new String[iPerson.getIclubDocuments().size()];
					int i = 0;
					for (IclubDocument iclubDocument : iPerson.getIclubDocuments()) {
						iclubDocuments[i] = iclubDocument.getDId();
						i++;
					}
					model.setIclubDocuments(iclubDocuments);
				}
				if (iPerson.getIclubRateEngines() != null && iPerson.getIclubRateEngines().size() > 0) {
					String[] iclubRateEngines = new String[iPerson.getIclubRateEngines().size()];
					int i = 0;
					for (IclubRateEngine iclubRateEngine : iPerson.getIclubRateEngines()) {
						iclubRateEngines[i] = iclubRateEngine.getReId();
						i++;
					}
					model.setIclubRateEngines(iclubRateEngines);
				}
				if (iPerson.getIclubGeoLocs() != null && iPerson.getIclubGeoLocs().size() > 0) {
					Long[] iclubGeoLocs = new Long[iPerson.getIclubGeoLocs().size()];
					int i = 0;
					for (IclubGeoLoc iclubGeoLoc : iPerson.getIclubGeoLocs()) {
						iclubGeoLocs[i] = iclubGeoLoc.getGlId();
						i++;
					}
					model.setIclubGeoLocs(iclubGeoLocs);
				}
				if (iPerson.getIclubQuotesForQPersonId() != null && iPerson.getIclubQuotesForQPersonId().size() > 0) {
					String[] iclubQuotesForQPersonId = new String[iPerson.getIclubQuotesForQPersonId().size()];
					int i = 0;
					for (IclubQuote iclubQuote : iPerson.getIclubQuotesForQPersonId()) {
						iclubQuotesForQPersonId[i] = iclubQuote.getQId();
						i++;
					}
					model.setIclubQuotesForQPersonId(iclubQuotesForQPersonId);
				}
				if (iPerson.getIclubTrackerMasters() != null && iPerson.getIclubTrackerMasters().size() > 0) {
					Long[] iclubTrackerMasters = new Long[iPerson.getIclubTrackerMasters().size()];
					int i = 0;
					for (IclubTrackerMaster iclubTrackerMaster : iPerson.getIclubTrackerMasters()) {
						iclubTrackerMasters[i] = iclubTrackerMaster.getTmId();
						i++;
					}
					model.setIclubTrackerMasters(iclubTrackerMasters);
				}
				if (iPerson.getIclubCoverTypes() != null && iPerson.getIclubCoverTypes().size() > 0) {
					Long[] iclubCoverTypes = new Long[iPerson.getIclubVehicles().size()];
					int i = 0;
					for (IclubCoverType iclubCoverType : iPerson.getIclubCoverTypes()) {
						iclubCoverTypes[i] = iclubCoverType.getCtId();
						i++;
					}
					model.setIclubCoverTypes(iclubCoverTypes);
				}
				if (iPerson.getIclubCountryCodes() != null && iPerson.getIclubCountryCodes().size() > 0) {
					Integer[] iclubCountryCodes = new Integer[iPerson.getIclubCountryCodes().size()];
					int i = 0;
					for (IclubCountryCode iclubCountryCode : iPerson.getIclubCountryCodes()) {
						iclubCountryCodes[i] = iclubCountryCode.getCcId();
						i++;
					}
					model.setIclubCountryCodes(iclubCountryCodes);
				}
				if (iPerson.getIclubBankMasters() != null && iPerson.getIclubBankMasters().size() > 0) {
					Long[] iclubBankMasters = new Long[iPerson.getIclubBankMasters().size()];
					int i = 0;
					for (IclubBankMaster iclubBankMaster : iPerson.getIclubBankMasters()) {
						iclubBankMasters[i] = iclubBankMaster.getBmId();
						i++;
					}
					model.setIclubBankMasters(iclubBankMasters);
				}

				if (iPerson.getIclubNotifs() != null && iPerson.getIclubNotifs().size() > 0) {
					String[] iclubNotifs = new String[iPerson.getIclubNotifs().size()];
					int i = 0;
					for (IclubNotif iclubNotif : iPerson.getIclubNotifs()) {
						iclubNotifs[i] = iclubNotif.getNId();
						i++;
					}
					model.setIclubNotifs(iclubNotifs);
				}
				if (iPerson.getIclubVehicleMasters() != null && iPerson.getIclubVehicleMasters().size() > 0) {
					Long[] iclubVehicleMasters = new Long[iPerson.getIclubVehicleMasters().size()];
					int i = 0;
					for (IclubVehicleMaster iclubVehicleMaster : iPerson.getIclubVehicleMasters()) {
						iclubVehicleMasters[i] = iclubVehicleMaster.getVmId();
						i++;
					}
					model.setIclubVehicleMasters(iclubVehicleMasters);
				}
				if (iPerson.getIclubDriversForDCrtdBy() != null && iPerson.getIclubDriversForDCrtdBy().size() > 0) {
					String[] iclubDriversForDCrtdBy = new String[iPerson.getIclubDriversForDCrtdBy().size()];
					int i = 0;
					for (IclubDriver iclubDriver : iPerson.getIclubDriversForDCrtdBy()) {
						iclubDriversForDCrtdBy[i] = iclubDriver.getDId();
						i++;
					}
					model.setIclubDriversForDCrtdBy(iclubDriversForDCrtdBy);
				}
				if (iPerson.getIclubPurposeTypes() != null && iPerson.getIclubPurposeTypes().size() > 0) {
					Long[] purposeTypes = new Long[iPerson.getIclubPurposeTypes().size()];
					int i = 0;
					for (IclubPurposeType iclubPurposeType : iPerson.getIclubPurposeTypes()) {
						purposeTypes[i] = iclubPurposeType.getPtId();
						i++;
					}
					model.setIclubPurposeTypes(purposeTypes);
				}
				if (iPerson.getIclubDriversForDPersonId() != null && iPerson.getIclubDriversForDPersonId().size() > 0) {
					String[] driversForDPersonId = new String[iPerson.getIclubDriversForDPersonId().size()];
					int i = 0;
					for (IclubDriver iclubDriver : iPerson.getIclubDriversForDPersonId()) {
						driversForDPersonId[i] = iclubDriver.getDId();
						i++;
					}
					model.setIclubDriversForDPersonId(driversForDPersonId);
				}
				if (iPerson.getIclubInsuranceItems() != null && iPerson.getIclubInsuranceItems().size() > 0) {
					String[] insuranceItems = new String[iPerson.getIclubInsuranceItems().size()];
					int i = 0;
					for (IclubInsuranceItem iclubInsuranceItem : iPerson.getIclubInsuranceItems()) {
						insuranceItems[i] = iclubInsuranceItem.getIiId();
						i++;
					}
					model.setIclubInsuranceItems(insuranceItems);
				}
				if (iPerson.getIclubLoginsForLPersonId() != null && iPerson.getIclubLoginsForLPersonId().size() > 0) {
					String[] loginsForLPersonId = new String[iPerson.getIclubLoginsForLPersonId().size()];
					int i = 0;
					for (IclubLogin iclubLogin : iPerson.getIclubLoginsForLPersonId()) {
						loginsForLPersonId[i] = iclubLogin.getLId();
						i++;
					}
					model.setIclubLoginsForLPersonId(loginsForLPersonId);
				}
				if (iPerson.getIclubLoginsForLCrtdBy() != null && iPerson.getIclubLoginsForLCrtdBy().size() > 0) {
					String[] LoginsForLCrtdBy = new String[iPerson.getIclubLoginsForLCrtdBy().size()];
					int i = 0;
					for (IclubLogin iclubLogin : iPerson.getIclubLoginsForLCrtdBy()) {
						LoginsForLCrtdBy[i] = iclubLogin.getLId();
						i++;
					}
					model.setIclubLoginsForLCrtdBy(LoginsForLCrtdBy);
				}
				if (iPerson.getIclubMessages() != null && iPerson.getIclubMessages().size() > 0) {
					String[] messages = new String[iPerson.getIclubMessages().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iPerson.getIclubMessages()) {
						messages[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessages(messages);
				}

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPersonModel getById(@PathParam("id") String id) {
		IclubPersonModel model = new IclubPersonModel();
		try {
			IclubPerson bean = iclubPersonDAO.findById(id);

			model.setPId(bean.getPId());
			model.setPCrtdDt(bean.getPCrtdDt());
			model.setPDob(bean.getPDob());
			model.setPEmail(bean.getPEmail());
			model.setPFName(bean.getPFName());
			model.setPIdNum(bean.getPIdNum());
			model.setPLName(bean.getPLName());
			model.setPMobile(bean.getPMobile());
			model.setPAddress(bean.getPAddress());
			model.setPContactPref(bean.getPContactPref());
			model.setPGender(bean.getPGender());
			model.setPContactPref(bean.getPContactPref());
			model.setPIdExpiryDt(bean.getPIdExpiryDt());
			model.setPInitials(bean.getPInitials());
			model.setPIsPensioner(bean.getPIsPensioner());
			model.setPIdIssueCntry(bean.getPIdIssueCntry());
			model.setPLat(bean.getPLat());
			model.setPLong(bean.getPLong());
			model.setPOccupation(bean.getPOccupation());
			model.setPTitle(bean.getPTitle());
			model.setPZipCd(bean.getPZipCd());
			model.setIclubIdType(bean.getIclubIdType() != null ? (bean.getIclubIdType().getItId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);

			if (bean.getIclubMessageBoards() != null && bean.getIclubMessageBoards().size() > 0) {
				String[] iclubMessageBoards = new String[bean.getIclubMessageBoards().size()];
				int i = 0;
				for (IclubMessageBoard iclubMessageBoard : bean.getIclubMessageBoards()) {
					iclubMessageBoards[i] = iclubMessageBoard.getMbId();
					i++;
				}
				model.setIclubMessageBoards(iclubMessageBoards);
			}
			if (bean.getIclubPersons() != null && bean.getIclubPersons().size() > 0) {
				String[] iclubPersons = new String[bean.getIclubPersons().size()];
				int i = 0;
				for (IclubPerson iclubPerson : bean.getIclubPersons()) {
					iclubPersons[i] = iclubPerson.getPId();
					i++;
				}
				model.setIclubPersons(iclubPersons);
			}
			if (bean.getIclubExtrases() != null && bean.getIclubExtrases().size() > 0) {
				Long[] iclubExtrases = new Long[bean.getIclubExtrases().size()];
				int i = 0;
				for (IclubExtras iclubExtras : bean.getIclubExtrases()) {
					iclubExtrases[i] = iclubExtras.getEId();
					i++;
				}
				model.setIclubExtrases(iclubExtrases);
			}
			if (bean.getIclubSecurityMasters() != null && bean.getIclubSecurityMasters().size() > 0) {
				String[] iclubSecurityMasters = new String[bean.getIclubSecurityMasters().size()];
				int i = 0;
				for (IclubSecurityMaster iclubSecurityMaster : bean.getIclubSecurityMasters()) {
					iclubSecurityMasters[i] = iclubSecurityMaster.getSmId();
					i++;
				}
				model.setIclubSecurityMasters(iclubSecurityMasters);
			}
			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] iclubProperties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty iclubProperty : bean.getIclubProperties()) {
					iclubProperties[i] = iclubProperty.getPId();
					i++;
				}
				model.setIclubProperties(iclubProperties);
			}
			if (bean.getIclubInsurerMasters() != null && bean.getIclubInsurerMasters().size() > 0) {
				Long[] iclubInsurerMasters = new Long[bean.getIclubInsurerMasters().size()];
				int i = 0;
				for (IclubInsurerMaster iclubInsurerMaster : bean.getIclubInsurerMasters()) {
					iclubInsurerMasters[i] = iclubInsurerMaster.getImId();
					i++;
				}
				model.setIclubInsurerMasters(iclubInsurerMasters);
			}
			if (bean.getIclubLicenseCodes() != null && bean.getIclubLicenseCodes().size() > 0) {
				Long[] iclubLicenseCodes = new Long[bean.getIclubLicenseCodes().size()];
				int i = 0;
				for (IclubLicenseCode iclubLicenseCode : bean.getIclubLicenseCodes()) {
					iclubLicenseCodes[i] = iclubLicenseCode.getLcId();
					i++;
				}
				model.setIclubLicenseCodes(iclubLicenseCodes);
			}
			if (bean.getIclubAccounts() != null && bean.getIclubAccounts().size() > 0) {
				String[] iclubAccounts = new String[bean.getIclubAccounts().size()];
				int i = 0;
				for (IclubAccount iclubAccount : bean.getIclubAccounts()) {
					iclubAccounts[i] = iclubAccount.getAId();
					i++;
				}
				model.setIclubAccounts(iclubAccounts);
			}
			if (bean.getIclubVehicles() != null && bean.getIclubVehicles().size() > 0) {
				String[] iclubVehicles = new String[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubVehicle iclubVehicle : bean.getIclubVehicles()) {
					iclubVehicles[i] = iclubVehicle.getVId();
					i++;
				}
				model.setIclubVehicles(iclubVehicles);
			}
			if (bean.getIclubSupplMasters() != null && bean.getIclubSupplMasters().size() > 0) {
				String[] iclubSupplMasters = new String[bean.getIclubSupplMasters().size()];
				int i = 0;
				for (IclubSupplMaster iclubSupplMaster : bean.getIclubSupplMasters()) {
					iclubSupplMasters[i] = iclubSupplMaster.getSmId();
					i++;
				}
				model.setIclubSupplMasters(iclubSupplMasters);
			}
			if (bean.getIclubConfigs() != null && bean.getIclubConfigs().size() > 0) {
				Long[] iclubConfigs = new Long[bean.getIclubConfigs().size()];
				int i = 0;
				for (IclubConfig iclubConfig : bean.getIclubConfigs()) {
					iclubConfigs[i] = iclubConfig.getCId();
					i++;
				}
				model.setIclubConfigs(iclubConfigs);
			}
			if (bean.getIclubClaims() != null && bean.getIclubClaims().size() > 0) {
				String[] iclubClaims = new String[bean.getIclubClaims().size()];
				int i = 0;
				for (IclubClaim iclubClaim : bean.getIclubClaims()) {
					iclubClaims[i] = iclubClaim.getCId();
					i++;
				}
				model.setIclubClaims(iclubClaims);
			}
			if (bean.getIclubRateTypes() != null && bean.getIclubRateTypes().size() > 0) {
				Long[] iclubRateTypes = new Long[bean.getIclubRateTypes().size()];
				int i = 0;
				for (IclubRateType iclubRateType : bean.getIclubRateTypes()) {
					iclubRateTypes[i] = iclubRateType.getRtId();
					i++;
				}
				model.setIclubRateTypes(iclubRateTypes);
			}
			if (bean.getIclubEvents() != null && bean.getIclubEvents().size() > 0) {
				String[] iclubEvents = new String[bean.getIclubEvents().size()];
				int i = 0;
				for (IclubEvent iclubEvent : bean.getIclubEvents()) {
					iclubEvents[i] = iclubEvent.getEId();
					i++;
				}
				model.setIclubEvents(iclubEvents);
			}
			if (bean.getIclubOccupations() != null && bean.getIclubOccupations().size() > 0) {
				Long[] iclubOccupations = new Long[bean.getIclubOccupations().size()];
				int i = 0;
				for (IclubOccupation iclubOccupation : bean.getIclubOccupations()) {
					iclubOccupations[i] = iclubOccupation.getOId();
					i++;
				}
				model.setIclubOccupations(iclubOccupations);
			}
			if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
				String[] iclubPayments = new String[bean.getIclubPayments().size()];
				int i = 0;
				for (IclubPayment iclubPayment : bean.getIclubPayments()) {
					iclubPayments[i] = iclubPayment.getPId();
					i++;
				}
				model.setIclubPayments(iclubPayments);
			}
			if (bean.getIclubSecurityDevices() != null && bean.getIclubSecurityDevices().size() > 0) {
				String[] iclubSecurityDevices = new String[bean.getIclubSecurityDevices().size()];
				int i = 0;
				for (IclubSecurityDevice iclubSecurityDevice : bean.getIclubSecurityDevices()) {
					iclubSecurityDevices[i] = iclubSecurityDevice.getSdId();
					i++;
				}
				model.setIclubSecurityDevices(iclubSecurityDevices);
			}
			if (bean.getIclubPolicies() != null && bean.getIclubPolicies().size() > 0) {
				String[] iclubPolicies = new String[bean.getIclubPolicies().size()];
				int i = 0;
				for (IclubPolicy iclubPolicy : bean.getIclubPolicies()) {
					iclubPolicies[i] = iclubPolicy.getPId();
					i++;
				}
				model.setIclubPolicies(iclubPolicies);
			}
			if (bean.getIclubMbComments() != null && bean.getIclubMbComments().size() > 0) {
				String[] iclubMbComments = new String[bean.getIclubMbComments().size()];
				int i = 0;
				for (IclubMbComment iclubMbComment : bean.getIclubMbComments()) {
					iclubMbComments[i] = iclubMbComment.getMbcId();
					i++;
				}
				model.setIclubMbComments(iclubMbComments);
			}
			if (bean.getIclubQuotesForQCrtdBy() != null && bean.getIclubQuotesForQCrtdBy().size() > 0) {
				String[] iclubQuotesForQCrtdBy = new String[bean.getIclubQuotesForQCrtdBy().size()];
				int i = 0;
				for (IclubQuote iclubQuote : bean.getIclubQuotesForQCrtdBy()) {
					iclubQuotesForQCrtdBy[i] = iclubQuote.getQId();
					i++;
				}
				model.setIclubQuotesForQCrtdBy(iclubQuotesForQCrtdBy);
			}

			if (bean.getIclubDocuments() != null && bean.getIclubDocuments().size() > 0) {
				String[] iclubDocuments = new String[bean.getIclubDocuments().size()];
				int i = 0;
				for (IclubDocument iclubDocument : bean.getIclubDocuments()) {
					iclubDocuments[i] = iclubDocument.getDId();
					i++;
				}
				model.setIclubDocuments(iclubDocuments);
			}
			if (bean.getIclubRateEngines() != null && bean.getIclubRateEngines().size() > 0) {
				String[] iclubRateEngines = new String[bean.getIclubRateEngines().size()];
				int i = 0;
				for (IclubRateEngine iclubRateEngine : bean.getIclubRateEngines()) {
					iclubRateEngines[i] = iclubRateEngine.getReId();
					i++;
				}
				model.setIclubRateEngines(iclubRateEngines);
			}
			if (bean.getIclubGeoLocs() != null && bean.getIclubGeoLocs().size() > 0) {
				Long[] iclubGeoLocs = new Long[bean.getIclubGeoLocs().size()];
				int i = 0;
				for (IclubGeoLoc iclubGeoLoc : bean.getIclubGeoLocs()) {
					iclubGeoLocs[i] = iclubGeoLoc.getGlId();
					i++;
				}
				model.setIclubGeoLocs(iclubGeoLocs);
			}
			if (bean.getIclubQuotesForQPersonId() != null && bean.getIclubQuotesForQPersonId().size() > 0) {
				String[] iclubQuotesForQPersonId = new String[bean.getIclubQuotesForQPersonId().size()];
				int i = 0;
				for (IclubQuote iclubQuote : bean.getIclubQuotesForQPersonId()) {
					iclubQuotesForQPersonId[i] = iclubQuote.getQId();
					i++;
				}
				model.setIclubQuotesForQPersonId(iclubQuotesForQPersonId);
			}
			if (bean.getIclubTrackerMasters() != null && bean.getIclubTrackerMasters().size() > 0) {
				Long[] iclubTrackerMasters = new Long[bean.getIclubTrackerMasters().size()];
				int i = 0;
				for (IclubTrackerMaster iclubTrackerMaster : bean.getIclubTrackerMasters()) {
					iclubTrackerMasters[i] = iclubTrackerMaster.getTmId();
					i++;
				}
				model.setIclubTrackerMasters(iclubTrackerMasters);
			}
			if (bean.getIclubCoverTypes() != null && bean.getIclubCoverTypes().size() > 0) {
				Long[] iclubCoverTypes = new Long[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubCoverType iclubCoverType : bean.getIclubCoverTypes()) {
					iclubCoverTypes[i] = iclubCoverType.getCtId();
					i++;
				}
				model.setIclubCoverTypes(iclubCoverTypes);
			}
			if (bean.getIclubCountryCodes() != null && bean.getIclubCountryCodes().size() > 0) {
				Integer[] iclubCountryCodes = new Integer[bean.getIclubCountryCodes().size()];
				int i = 0;
				for (IclubCountryCode iclubCountryCode : bean.getIclubCountryCodes()) {
					iclubCountryCodes[i] = iclubCountryCode.getCcId();
					i++;
				}
				model.setIclubCountryCodes(iclubCountryCodes);
			}
			if (bean.getIclubBankMasters() != null && bean.getIclubBankMasters().size() > 0) {
				Long[] iclubBankMasters = new Long[bean.getIclubBankMasters().size()];
				int i = 0;
				for (IclubBankMaster iclubBankMaster : bean.getIclubBankMasters()) {
					iclubBankMasters[i] = iclubBankMaster.getBmId();
					i++;
				}
				model.setIclubBankMasters(iclubBankMasters);
			}

			if (bean.getIclubNotifs() != null && bean.getIclubNotifs().size() > 0) {
				String[] iclubNotifs = new String[bean.getIclubNotifs().size()];
				int i = 0;
				for (IclubNotif iclubNotif : bean.getIclubNotifs()) {
					iclubNotifs[i] = iclubNotif.getNId();
					i++;
				}
				model.setIclubNotifs(iclubNotifs);
			}
			if (bean.getIclubVehicleMasters() != null && bean.getIclubVehicleMasters().size() > 0) {
				Long[] iclubVehicleMasters = new Long[bean.getIclubVehicleMasters().size()];
				int i = 0;
				for (IclubVehicleMaster iclubVehicleMaster : bean.getIclubVehicleMasters()) {
					iclubVehicleMasters[i] = iclubVehicleMaster.getVmId();
					i++;
				}
				model.setIclubVehicleMasters(iclubVehicleMasters);
			}
			if (bean.getIclubDriversForDCrtdBy() != null && bean.getIclubDriversForDCrtdBy().size() > 0) {
				String[] iclubDriversForDCrtdBy = new String[bean.getIclubDriversForDCrtdBy().size()];
				int i = 0;
				for (IclubDriver iclubDriver : bean.getIclubDriversForDCrtdBy()) {
					iclubDriversForDCrtdBy[i] = iclubDriver.getDId();
					i++;
				}
				model.setIclubDriversForDCrtdBy(iclubDriversForDCrtdBy);
			}
			if (bean.getIclubPurposeTypes() != null && bean.getIclubPurposeTypes().size() > 0) {
				Long[] purposeTypes = new Long[bean.getIclubPurposeTypes().size()];
				int i = 0;
				for (IclubPurposeType iclubPurposeType : bean.getIclubPurposeTypes()) {
					purposeTypes[i] = iclubPurposeType.getPtId();
					i++;
				}
				model.setIclubPurposeTypes(purposeTypes);
			}
			if (bean.getIclubDriversForDPersonId() != null && bean.getIclubDriversForDPersonId().size() > 0) {
				String[] driversForDPersonId = new String[bean.getIclubDriversForDPersonId().size()];
				int i = 0;
				for (IclubDriver iclubDriver : bean.getIclubDriversForDPersonId()) {
					driversForDPersonId[i] = iclubDriver.getDId();
					i++;
				}
				model.setIclubDriversForDPersonId(driversForDPersonId);
			}
			if (bean.getIclubInsuranceItems() != null && bean.getIclubInsuranceItems().size() > 0) {
				String[] insuranceItems = new String[bean.getIclubInsuranceItems().size()];
				int i = 0;
				for (IclubInsuranceItem iclubInsuranceItem : bean.getIclubInsuranceItems()) {
					insuranceItems[i] = iclubInsuranceItem.getIiId();
					i++;
				}
				model.setIclubInsuranceItems(insuranceItems);
			}
			if (bean.getIclubLoginsForLPersonId() != null && bean.getIclubLoginsForLPersonId().size() > 0) {
				String[] loginsForLPersonId = new String[bean.getIclubLoginsForLPersonId().size()];
				int i = 0;
				for (IclubLogin iclubLogin : bean.getIclubLoginsForLPersonId()) {
					loginsForLPersonId[i] = iclubLogin.getLId();
					i++;
				}
				model.setIclubLoginsForLPersonId(loginsForLPersonId);
			}
			if (bean.getIclubLoginsForLCrtdBy() != null && bean.getIclubLoginsForLCrtdBy().size() > 0) {
				String[] LoginsForLCrtdBy = new String[bean.getIclubLoginsForLCrtdBy().size()];
				int i = 0;
				for (IclubLogin iclubLogin : bean.getIclubLoginsForLCrtdBy()) {
					LoginsForLCrtdBy[i] = iclubLogin.getLId();
					i++;
				}
				model.setIclubLoginsForLCrtdBy(LoginsForLCrtdBy);
			}
			if (bean.getIclubMessages() != null && bean.getIclubMessages().size() > 0) {
				String[] messages = new String[bean.getIclubMessages().size()];
				int i = 0;
				for (IclubMessage iclubMessage : bean.getIclubMessages()) {
					messages[i] = iclubMessage.getMId();
					i++;
				}
				model.setIclubMessages(messages);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
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

}
