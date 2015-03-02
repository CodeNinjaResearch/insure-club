package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPersonModel")
public class IclubPersonModel {

	private String PId;
	private Long iclubMaritialStatus;
	private Long iclubPerson;
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
	private Long PLat;
	private Long PLong;
	private Integer PZipCd;
	private Date PCrtdDt;
	private Long[] iclubPersons;
	private Long[] iclubSecurityMasters;
	private Long[] iclubGeoLocs;
	private Long[] iclubInsurerMasters;
	private Long[] iclubAccounts;
	private Long[] iclubTrackerMasters;
	private Long[] iclubConfigs;
	private Long[] iclubSupplMasters;
	private Long[] iclubBankMaster;
	private Long[] iclubCountryCodes;
	private Long[] iclubNotifs;
	private Long[] iclubVehicleMasters;
	private Long[] iclubLoginsForLPersonId;
	private Long[] iclubLoginsForLCrtdBy;

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

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
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

	public Long getPLat() {
		return PLat;
	}

	public void setPLat(Long pLat) {
		PLat = pLat;
	}

	public Long getPLong() {
		return PLong;
	}

	public void setPLong(Long pLong) {
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

	public Long[] getIclubPersons() {
		return iclubPersons;
	}

	public void setIclubPersons(Long[] iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

	public Long[] getIclubSecurityMasters() {
		return iclubSecurityMasters;
	}

	public void setIclubSecurityMasters(Long[] iclubSecurityMasters) {
		this.iclubSecurityMasters = iclubSecurityMasters;
	}

	public Long[] getIclubGeoLocs() {
		return iclubGeoLocs;
	}

	public void setIclubGeoLocs(Long[] iclubGeoLocs) {
		this.iclubGeoLocs = iclubGeoLocs;
	}

	public Long[] getIclubInsurerMasters() {
		return iclubInsurerMasters;
	}

	public void setIclubInsurerMasters(Long[] iclubInsurerMasters) {
		this.iclubInsurerMasters = iclubInsurerMasters;
	}

	public Long[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(Long[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

	public Long[] getIclubTrackerMasters() {
		return iclubTrackerMasters;
	}

	public void setIclubTrackerMasters(Long[] iclubTrackerMasters) {
		this.iclubTrackerMasters = iclubTrackerMasters;
	}

	public Long[] getIclubConfigs() {
		return iclubConfigs;
	}

	public void setIclubConfigs(Long[] iclubConfigs) {
		this.iclubConfigs = iclubConfigs;
	}

	public Long[] getIclubSupplMasters() {
		return iclubSupplMasters;
	}

	public void setIclubSupplMasters(Long[] iclubSupplMasters) {
		this.iclubSupplMasters = iclubSupplMasters;
	}

	public Long[] getIclubBankMaster() {
		return iclubBankMaster;
	}

	public void setIclubBankMaster(Long[] iclubBankMaster) {
		this.iclubBankMaster = iclubBankMaster;
	}

	public Long[] getIclubCountryCodes() {
		return iclubCountryCodes;
	}

	public void setIclubCountryCodes(Long[] iclubCountryCodes) {
		this.iclubCountryCodes = iclubCountryCodes;
	}

	public Long[] getIclubNotifs() {
		return iclubNotifs;
	}

	public void setIclubNotifs(Long[] iclubNotifs) {
		this.iclubNotifs = iclubNotifs;
	}

	public Long[] getIclubVehicleMasters() {
		return iclubVehicleMasters;
	}

	public void setIclubVehicleMasters(Long[] iclubVehicleMasters) {
		this.iclubVehicleMasters = iclubVehicleMasters;
	}

	public Long[] getIclubLoginsForLPersonId() {
		return iclubLoginsForLPersonId;
	}

	public void setIclubLoginsForLPersonId(Long[] iclubLoginsForLPersonId) {
		this.iclubLoginsForLPersonId = iclubLoginsForLPersonId;
	}

	public Long[] getIclubLoginsForLCrtdBy() {
		return iclubLoginsForLCrtdBy;
	}

	public void setIclubLoginsForLCrtdBy(Long[] iclubLoginsForLCrtdBy) {
		this.iclubLoginsForLCrtdBy = iclubLoginsForLCrtdBy;
	}

}
