package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPersonModel")
public class IclubPersonModel {

	private String PId;
	private Long iclubMaritialStatus;
	private String iclubPerson;
	private Long iclubIdType;
	private String PTitle;
	private String PInitials;
	private String PFName;
	private String PLName;
	private String PMobile;
	private String PEmail;
	private String PContactPref;
	private String PGender;
	private String PIdNum;
	private Long PIdIssueCntry;
	private Date PIdIssueDt;
	private Date PIdExpiryDt;
	private Long POccupation;
	private Date PDob;
	private String PIsPensioner;
	private String PAddress;
	private Double PLat;
	private Double PLong;
	private Integer PZipCd;
	private Date PCrtdDt;
	private String[] iclubMessageBoards;
	private String[] iclubPersons;
	private String[] iclubProperties;
	private Long[] iclubInsurerMasters;
	private Long[] iclubLicenseCodes;
	private String[] iclubAccounts;
	private String[] iclubVehicles;
	private Long[] iclubRateTypes;
	private Long[] iclubOccupations;
	private String[] iclubPolicies;
	private String[] iclubPayments;
	private String[] iclubSecurityDevices;
	private String[] iclubMbComments;
	private String[] iclubQuotesForQCrtdBy;
	private String[] iclubDocuments;
	private Long[] iclubGeoLocs;
	private String[] iclubRateEngines;
	private String[] iclubQuotesForQPersonId;
	private Long[] iclubTrackerMasters;
	private Long[] iclubCoverTypes;
	private Integer[] iclubCountryCodes;
	private Long[] iclubBankMasters;
	private String[] iclubNotifs;
	private Long[] iclubVehicleMasters;
	private String[] iclubDriversForDCrtdBy;
	private Long[] iclubPurposeTypes;
	private String[] iclubDriversForDPersonId;
	private String[] iclubInsuranceItems;
	private String[] iclubLoginsForLPersonId;
	private String[] iclubLoginsForLCrtdBy;
	private String[] iclubMessages;
	private String[] iclubSecurityMasters;
	private Long[] iclubExtrases;
	private String[] iclubSupplMasters;
	private Long[] iclubConfigs;
	private String[] iclubClaims;
	private String[] iclubEvents;

	public String getPId() {
		return PId;
	}

	public void setPId(String pId) {
		PId = pId;
	}

	public Long getIclubMaritialStatus() {
		return iclubMaritialStatus;
	}

	public void setIclubMaritialStatus(Long iclubMaritialStatus) {
		this.iclubMaritialStatus = iclubMaritialStatus;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubIdType() {
		return iclubIdType;
	}

	public void setIclubIdType(Long iclubIdType) {
		this.iclubIdType = iclubIdType;
	}

	public String getPTitle() {
		return PTitle;
	}

	public void setPTitle(String pTitle) {
		PTitle = pTitle;
	}

	public String getPInitials() {
		return PInitials;
	}

	public void setPInitials(String pInitials) {
		PInitials = pInitials;
	}

	public String getPFName() {
		return PFName;
	}

	public void setPFName(String pFName) {
		PFName = pFName;
	}

	public String getPLName() {
		return PLName;
	}

	public void setPLName(String pLName) {
		PLName = pLName;
	}

	public String getPMobile() {
		return PMobile;
	}

	public void setPMobile(String pMobile) {
		PMobile = pMobile;
	}

	public String getPEmail() {
		return PEmail;
	}

	public void setPEmail(String pEmail) {
		PEmail = pEmail;
	}

	public String getPContactPref() {
		return PContactPref;
	}

	public void setPContactPref(String pContactPref) {
		PContactPref = pContactPref;
	}

	public String getPGender() {
		return PGender;
	}

	public void setPGender(String pGender) {
		PGender = pGender;
	}

	public String getPIdNum() {
		return PIdNum;
	}

	public void setPIdNum(String pIdNum) {
		PIdNum = pIdNum;
	}

	public Long getPIdIssueCntry() {
		return PIdIssueCntry;
	}

	public void setPIdIssueCntry(Long pIdIssueCntry) {
		PIdIssueCntry = pIdIssueCntry;
	}

	public Date getPIdIssueDt() {
		return PIdIssueDt;
	}

	public void setPIdIssueDt(Date pIdIssueDt) {
		PIdIssueDt = pIdIssueDt;
	}

	public Date getPIdExpiryDt() {
		return PIdExpiryDt;
	}

	public void setPIdExpiryDt(Date pIdExpiryDt) {
		PIdExpiryDt = pIdExpiryDt;
	}

	public Long getPOccupation() {
		return POccupation;
	}

	public void setPOccupation(Long pOccupation) {
		POccupation = pOccupation;
	}

	public Date getPDob() {
		return PDob;
	}

	public void setPDob(Date pDob) {
		PDob = pDob;
	}

	public String getPIsPensioner() {
		return PIsPensioner;
	}

	public void setPIsPensioner(String pIsPensioner) {
		PIsPensioner = pIsPensioner;
	}

	public String getPAddress() {
		return PAddress;
	}

	public void setPAddress(String pAddress) {
		PAddress = pAddress;
	}

	public Double getPLat() {
		return PLat;
	}

	public void setPLat(Double pLat) {
		PLat = pLat;
	}

	public Double getPLong() {
		return PLong;
	}

	public void setPLong(Double pLong) {
		PLong = pLong;
	}

	public Integer getPZipCd() {
		return PZipCd;
	}

	public void setPZipCd(Integer pZipCd) {
		PZipCd = pZipCd;
	}

	public Date getPCrtdDt() {
		return PCrtdDt;
	}

	public void setPCrtdDt(Date pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}

	public String[] getIclubMessageBoards() {
		return iclubMessageBoards;
	}

	public void setIclubMessageBoards(String[] iclubMessageBoards) {
		this.iclubMessageBoards = iclubMessageBoards;
	}

	public String[] getIclubPersons() {
		return iclubPersons;
	}

	public void setIclubPersons(String[] iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	public Long[] getIclubInsurerMasters() {
		return iclubInsurerMasters;
	}

	public void setIclubInsurerMasters(Long[] iclubInsurerMasters) {
		this.iclubInsurerMasters = iclubInsurerMasters;
	}

	public Long[] getIclubLicenseCodes() {
		return iclubLicenseCodes;
	}

	public void setIclubLicenseCodes(Long[] iclubLicenseCodes) {
		this.iclubLicenseCodes = iclubLicenseCodes;
	}

	public String[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(String[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

	public Long[] getIclubRateTypes() {
		return iclubRateTypes;
	}

	public void setIclubRateTypes(Long[] iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

	public Long[] getIclubOccupations() {
		return iclubOccupations;
	}

	public void setIclubOccupations(Long[] iclubOccupations) {
		this.iclubOccupations = iclubOccupations;
	}

	public String[] getIclubPolicies() {
		return iclubPolicies;
	}

	public void setIclubPolicies(String[] iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

	public String[] getIclubPayments() {
		return iclubPayments;
	}

	public void setIclubPayments(String[] iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

	public String[] getIclubSecurityDevices() {
		return iclubSecurityDevices;
	}

	public void setIclubSecurityDevices(String[] iclubSecurityDevices) {
		this.iclubSecurityDevices = iclubSecurityDevices;
	}

	public String[] getIclubMbComments() {
		return iclubMbComments;
	}

	public void setIclubMbComments(String[] iclubMbComments) {
		this.iclubMbComments = iclubMbComments;
	}

	public String[] getIclubQuotesForQCrtdBy() {
		return iclubQuotesForQCrtdBy;
	}

	public void setIclubQuotesForQCrtdBy(String[] iclubQuotesForQCrtdBy) {
		this.iclubQuotesForQCrtdBy = iclubQuotesForQCrtdBy;
	}

	public String[] getIclubDocuments() {
		return iclubDocuments;
	}

	public void setIclubDocuments(String[] iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}

	public Long[] getIclubGeoLocs() {
		return iclubGeoLocs;
	}

	public void setIclubGeoLocs(Long[] iclubGeoLocs) {
		this.iclubGeoLocs = iclubGeoLocs;
	}

	public String[] getIclubRateEngines() {
		return iclubRateEngines;
	}

	public void setIclubRateEngines(String[] iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}

	public String[] getIclubQuotesForQPersonId() {
		return iclubQuotesForQPersonId;
	}

	public void setIclubQuotesForQPersonId(String[] iclubQuotesForQPersonId) {
		this.iclubQuotesForQPersonId = iclubQuotesForQPersonId;
	}

	public Long[] getIclubTrackerMasters() {
		return iclubTrackerMasters;
	}

	public void setIclubTrackerMasters(Long[] iclubTrackerMasters) {
		this.iclubTrackerMasters = iclubTrackerMasters;
	}

	public Long[] getIclubCoverTypes() {
		return iclubCoverTypes;
	}

	public void setIclubCoverTypes(Long[] iclubCoverTypes) {
		this.iclubCoverTypes = iclubCoverTypes;
	}

	public Integer[] getIclubCountryCodes() {
		return iclubCountryCodes;
	}

	public void setIclubCountryCodes(Integer[] iclubCountryCodes) {
		this.iclubCountryCodes = iclubCountryCodes;
	}

	public Long[] getIclubBankMasters() {
		return iclubBankMasters;
	}

	public void setIclubBankMasters(Long[] iclubBankMasters) {
		this.iclubBankMasters = iclubBankMasters;
	}

	public String[] getIclubNotifs() {
		return iclubNotifs;
	}

	public void setIclubNotifs(String[] iclubNotifs) {
		this.iclubNotifs = iclubNotifs;
	}

	public Long[] getIclubVehicleMasters() {
		return iclubVehicleMasters;
	}

	public void setIclubVehicleMasters(Long[] iclubVehicleMasters) {
		this.iclubVehicleMasters = iclubVehicleMasters;
	}

	public String[] getIclubDriversForDCrtdBy() {
		return iclubDriversForDCrtdBy;
	}

	public void setIclubDriversForDCrtdBy(String[] iclubDriversForDCrtdBy) {
		this.iclubDriversForDCrtdBy = iclubDriversForDCrtdBy;
	}

	public Long[] getIclubPurposeTypes() {
		return iclubPurposeTypes;
	}

	public void setIclubPurposeTypes(Long[] iclubPurposeTypes) {
		this.iclubPurposeTypes = iclubPurposeTypes;
	}

	public String[] getIclubDriversForDPersonId() {
		return iclubDriversForDPersonId;
	}

	public void setIclubDriversForDPersonId(String[] iclubDriversForDPersonId) {
		this.iclubDriversForDPersonId = iclubDriversForDPersonId;
	}

	public String[] getIclubInsuranceItems() {
		return iclubInsuranceItems;
	}

	public void setIclubInsuranceItems(String[] iclubInsuranceItems) {
		this.iclubInsuranceItems = iclubInsuranceItems;
	}

	public String[] getIclubLoginsForLPersonId() {
		return iclubLoginsForLPersonId;
	}

	public void setIclubLoginsForLPersonId(String[] iclubLoginsForLPersonId) {
		this.iclubLoginsForLPersonId = iclubLoginsForLPersonId;
	}

	public String[] getIclubLoginsForLCrtdBy() {
		return iclubLoginsForLCrtdBy;
	}

	public void setIclubLoginsForLCrtdBy(String[] iclubLoginsForLCrtdBy) {
		this.iclubLoginsForLCrtdBy = iclubLoginsForLCrtdBy;
	}

	public String[] getIclubMessages() {
		return iclubMessages;
	}

	public void setIclubMessages(String[] iclubMessages) {
		this.iclubMessages = iclubMessages;
	}

	public String[] getIclubSecurityMasters() {
		return iclubSecurityMasters;
	}

	public void setIclubSecurityMasters(String[] iclubSecurityMasters) {
		this.iclubSecurityMasters = iclubSecurityMasters;
	}

	public Long[] getIclubExtrases() {
		return iclubExtrases;
	}

	public void setIclubExtrases(Long[] iclubExtrases) {
		this.iclubExtrases = iclubExtrases;
	}

	public String[] getIclubSupplMasters() {
		return iclubSupplMasters;
	}

	public void setIclubSupplMasters(String[] iclubSupplMasters) {
		this.iclubSupplMasters = iclubSupplMasters;
	}

	public Long[] getIclubConfigs() {
		return iclubConfigs;
	}

	public void setIclubConfigs(Long[] iclubConfigs) {
		this.iclubConfigs = iclubConfigs;
	}

	public String[] getIclubClaims() {
		return iclubClaims;
	}

	public void setIclubClaims(String[] iclubClaims) {
		this.iclubClaims = iclubClaims;
	}

	public String[] getIclubEvents() {
		return iclubEvents;
	}

	public void setIclubEvents(String[] iclubEvents) {
		this.iclubEvents = iclubEvents;
	}

}
