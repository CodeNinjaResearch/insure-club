package za.co.iclub.pss.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_person", catalog = "iclubdb")
public class IclubPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6904314475598401673L;
	private String PId;
	private IclubMaritialStatus iclubMaritialStatus;
	private IclubPerson iclubPerson;
	private IclubCohort iclubCohort;
	private IclubCohortInvite iclubCohortInvite;
	private IclubIdType iclubIdType;
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
	private Integer PAge;
	private Date PCrtdDt;
	private Set<IclubSupplPerson> iclubSupplPersonsForSpPersonId = new HashSet<IclubSupplPerson>(0);
	private Set<IclubMbComment> iclubMbComments = new HashSet<IclubMbComment>(0);
	private Set<IclubSupplPerson> iclubSupplPersonsForSpCrtdBy = new HashSet<IclubSupplPerson>(0);
	private Set<IclubCohortInvite> iclubCohortInvites = new HashSet<IclubCohortInvite>(0);
	private Set<IclubNotif> iclubNotifs = new HashSet<IclubNotif>(0);
	private Set<IclubTrackerMaster> iclubTrackerMasters = new HashSet<IclubTrackerMaster>(0);
	private Set<IclubPayment> iclubPayments = new HashSet<IclubPayment>(0);
	private Set<IclubRateEngine> iclubRateEngines = new HashSet<IclubRateEngine>(0);
	private Set<IclubCountryCode> iclubCountryCodes = new HashSet<IclubCountryCode>(0);
	private Set<IclubLogin> iclubLoginsForLPersonId = new HashSet<IclubLogin>(0);
	private Set<IclubPolicy> iclubPolicies = new HashSet<IclubPolicy>(0);
	private Set<IclubLogin> iclubLoginsForLCrtdBy = new HashSet<IclubLogin>(0);
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);
	private Set<IclubCohortPerson> iclubCohortPersonsForCpPersonId = new HashSet<IclubCohortPerson>(0);
	private Set<IclubCohortPerson> iclubCohortPersonsForCpCrtdBy = new HashSet<IclubCohortPerson>(0);
	private Set<IclubVehicleMaster> iclubVehicleMasters = new HashSet<IclubVehicleMaster>(0);
	private Set<IclubGeoLoc> iclubGeoLocs = new HashSet<IclubGeoLoc>(0);
	private Set<IclubConfig> iclubConfigs = new HashSet<IclubConfig>(0);
	private Set<IclubOccupation> iclubOccupations = new HashSet<IclubOccupation>(0);
	private Set<IclubMessage> iclubMessages = new HashSet<IclubMessage>(0);
	private Set<IclubPropertyItem> iclubPropertyItems = new HashSet<IclubPropertyItem>(0);
	private Set<IclubInsurerMaster> iclubInsurerMasters = new HashSet<IclubInsurerMaster>(0);
	private Set<IclubSupplItem> iclubSupplItems = new HashSet<IclubSupplItem>(0);
	private Set<IclubLicenseCode> iclubLicenseCodes = new HashSet<IclubLicenseCode>(0);
	private Set<IclubCohort> iclubCohortsForCCrtdBy = new HashSet<IclubCohort>(0);
	private Set<IclubRateType> iclubRateTypes = new HashSet<IclubRateType>(0);
	private Set<IclubCohort> iclubCohortsForCAdminId = new HashSet<IclubCohort>(0);
	private Set<IclubQuote> iclubQuotesForQCrtdBy = new HashSet<IclubQuote>(0);
	private Set<IclubAccount> iclubAccounts = new HashSet<IclubAccount>(0);
	private Set<IclubCohort> iclubCohortsForCPrimaryUserId = new HashSet<IclubCohort>(0);
	private Set<IclubInsuranceItem> iclubInsuranceItems = new HashSet<IclubInsuranceItem>(0);
	private Set<IclubCoverType> iclubCoverTypes = new HashSet<IclubCoverType>(0);
	private Set<IclubPurposeType> iclubPurposeTypes = new HashSet<IclubPurposeType>(0);
	private Set<IclubEvent> iclubEvents = new HashSet<IclubEvent>(0);
	private Set<IclubSecurityDevice> iclubSecurityDevices = new HashSet<IclubSecurityDevice>(0);
	private Set<IclubClaim> iclubClaims = new HashSet<IclubClaim>(0);
	private Set<IclubSecurityMaster> iclubSecurityMasters = new HashSet<IclubSecurityMaster>(0);
	private Set<IclubCohortClaim> iclubCohortClaims = new HashSet<IclubCohortClaim>(0);
	private Set<IclubBankMaster> iclubBankMasters = new HashSet<IclubBankMaster>(0);
	private Set<IclubQuote> iclubQuotesForQPersonId = new HashSet<IclubQuote>(0);
	private Set<IclubDriver> iclubDriversForDCrtdBy = new HashSet<IclubDriver>(0);
	private Set<IclubMessageBoard> iclubMessageBoards = new HashSet<IclubMessageBoard>(0);
	private Set<IclubExtras> iclubExtrases = new HashSet<IclubExtras>(0);
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);
	private Set<IclubDocument> iclubDocuments = new HashSet<IclubDocument>(0);
	private Set<IclubPerson> iclubPersons = new HashSet<IclubPerson>(0);
	private Set<IclubSupplMaster> iclubSupplMasters = new HashSet<IclubSupplMaster>(0);
	private Set<IclubDriver> iclubDriversForDPersonId = new HashSet<IclubDriver>(0);

	// Constructors

	/** default constructor */
	public IclubPerson() {
	}

	/** minimal constructor */
	public IclubPerson(String PId) {
		this.PId = PId;
	}

	/** full constructor */
	public IclubPerson(String PId, IclubMaritialStatus iclubMaritialStatus, IclubPerson iclubPerson, IclubCohort iclubCohort, IclubCohortInvite iclubCohortInvite, IclubIdType iclubIdType, String PTitle, String PInitials, String PFName, String PLName, String PMobile, String PEmail, String PContactPref, String PGender, String PIdNum, Long PIdIssueCntry, Date PIdIssueDt, Date PIdExpiryDt, Long POccupation, Date PDob, String PIsPensioner, String PAddress, Double PLat, Double PLong, Integer PZipCd, Integer PAge, Date PCrtdDt, Set<IclubSupplPerson> iclubSupplPersonsForSpPersonId, Set<IclubMbComment> iclubMbComments, Set<IclubSupplPerson> iclubSupplPersonsForSpCrtdBy, Set<IclubCohortInvite> iclubCohortInvites, Set<IclubNotif> iclubNotifs, Set<IclubTrackerMaster> iclubTrackerMasters,
			Set<IclubPayment> iclubPayments, Set<IclubRateEngine> iclubRateEngines, Set<IclubCountryCode> iclubCountryCodes, Set<IclubLogin> iclubLoginsForLPersonId, Set<IclubPolicy> iclubPolicies, Set<IclubLogin> iclubLoginsForLCrtdBy, Set<IclubVehicle> iclubVehicles, Set<IclubCohortPerson> iclubCohortPersonsForCpPersonId, Set<IclubCohortPerson> iclubCohortPersonsForCpCrtdBy, Set<IclubVehicleMaster> iclubVehicleMasters, Set<IclubGeoLoc> iclubGeoLocs, Set<IclubConfig> iclubConfigs, Set<IclubOccupation> iclubOccupations, Set<IclubMessage> iclubMessages, Set<IclubPropertyItem> iclubPropertyItems, Set<IclubInsurerMaster> iclubInsurerMasters, Set<IclubSupplItem> iclubSupplItems, Set<IclubLicenseCode> iclubLicenseCodes, Set<IclubCohort> iclubCohortsForCCrtdBy,
			Set<IclubRateType> iclubRateTypes, Set<IclubCohort> iclubCohortsForCAdminId, Set<IclubQuote> iclubQuotesForQCrtdBy, Set<IclubAccount> iclubAccounts, Set<IclubCohort> iclubCohortsForCPrimaryUserId, Set<IclubInsuranceItem> iclubInsuranceItems, Set<IclubCoverType> iclubCoverTypes, Set<IclubPurposeType> iclubPurposeTypes, Set<IclubEvent> iclubEvents, Set<IclubSecurityDevice> iclubSecurityDevices, Set<IclubClaim> iclubClaims, Set<IclubSecurityMaster> iclubSecurityMasters, Set<IclubCohortClaim> iclubCohortClaims, Set<IclubBankMaster> iclubBankMasters, Set<IclubQuote> iclubQuotesForQPersonId, Set<IclubDriver> iclubDriversForDCrtdBy, Set<IclubMessageBoard> iclubMessageBoards, Set<IclubExtras> iclubExtrases, Set<IclubProperty> iclubProperties, Set<IclubDocument> iclubDocuments,
			Set<IclubPerson> iclubPersons, Set<IclubSupplMaster> iclubSupplMasters, Set<IclubDriver> iclubDriversForDPersonId) {
		this.PId = PId;
		this.iclubMaritialStatus = iclubMaritialStatus;
		this.iclubPerson = iclubPerson;
		this.iclubCohort = iclubCohort;
		this.iclubCohortInvite = iclubCohortInvite;
		this.iclubIdType = iclubIdType;
		this.PTitle = PTitle;
		this.PInitials = PInitials;
		this.PFName = PFName;
		this.PLName = PLName;
		this.PMobile = PMobile;
		this.PEmail = PEmail;
		this.PContactPref = PContactPref;
		this.PGender = PGender;
		this.PIdNum = PIdNum;
		this.PIdIssueCntry = PIdIssueCntry;
		this.PIdIssueDt = PIdIssueDt;
		this.PIdExpiryDt = PIdExpiryDt;
		this.POccupation = POccupation;
		this.PDob = PDob;
		this.PIsPensioner = PIsPensioner;
		this.PAddress = PAddress;
		this.PLat = PLat;
		this.PLong = PLong;
		this.PZipCd = PZipCd;
		this.PAge = PAge;
		this.PCrtdDt = PCrtdDt;
		this.iclubSupplPersonsForSpPersonId = iclubSupplPersonsForSpPersonId;
		this.iclubMbComments = iclubMbComments;
		this.iclubSupplPersonsForSpCrtdBy = iclubSupplPersonsForSpCrtdBy;
		this.iclubCohortInvites = iclubCohortInvites;
		this.iclubNotifs = iclubNotifs;
		this.iclubTrackerMasters = iclubTrackerMasters;
		this.iclubPayments = iclubPayments;
		this.iclubRateEngines = iclubRateEngines;
		this.iclubCountryCodes = iclubCountryCodes;
		this.iclubLoginsForLPersonId = iclubLoginsForLPersonId;
		this.iclubPolicies = iclubPolicies;
		this.iclubLoginsForLCrtdBy = iclubLoginsForLCrtdBy;
		this.iclubVehicles = iclubVehicles;
		this.iclubCohortPersonsForCpPersonId = iclubCohortPersonsForCpPersonId;
		this.iclubCohortPersonsForCpCrtdBy = iclubCohortPersonsForCpCrtdBy;
		this.iclubVehicleMasters = iclubVehicleMasters;
		this.iclubGeoLocs = iclubGeoLocs;
		this.iclubConfigs = iclubConfigs;
		this.iclubOccupations = iclubOccupations;
		this.iclubMessages = iclubMessages;
		this.iclubPropertyItems = iclubPropertyItems;
		this.iclubInsurerMasters = iclubInsurerMasters;
		this.iclubSupplItems = iclubSupplItems;
		this.iclubLicenseCodes = iclubLicenseCodes;
		this.iclubCohortsForCCrtdBy = iclubCohortsForCCrtdBy;
		this.iclubRateTypes = iclubRateTypes;
		this.iclubCohortsForCAdminId = iclubCohortsForCAdminId;
		this.iclubQuotesForQCrtdBy = iclubQuotesForQCrtdBy;
		this.iclubAccounts = iclubAccounts;
		this.iclubCohortsForCPrimaryUserId = iclubCohortsForCPrimaryUserId;
		this.iclubInsuranceItems = iclubInsuranceItems;
		this.iclubCoverTypes = iclubCoverTypes;
		this.iclubPurposeTypes = iclubPurposeTypes;
		this.iclubEvents = iclubEvents;
		this.iclubSecurityDevices = iclubSecurityDevices;
		this.iclubClaims = iclubClaims;
		this.iclubSecurityMasters = iclubSecurityMasters;
		this.iclubCohortClaims = iclubCohortClaims;
		this.iclubBankMasters = iclubBankMasters;
		this.iclubQuotesForQPersonId = iclubQuotesForQPersonId;
		this.iclubDriversForDCrtdBy = iclubDriversForDCrtdBy;
		this.iclubMessageBoards = iclubMessageBoards;
		this.iclubExtrases = iclubExtrases;
		this.iclubProperties = iclubProperties;
		this.iclubDocuments = iclubDocuments;
		this.iclubPersons = iclubPersons;
		this.iclubSupplMasters = iclubSupplMasters;
		this.iclubDriversForDPersonId = iclubDriversForDPersonId;
	}

	// Property accessors
	@Id
	@Column(name = "p_id", unique = true, nullable = false, length = 36)
	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_mar_status")
	public IclubMaritialStatus getIclubMaritialStatus() {
		return this.iclubMaritialStatus;
	}

	public void setIclubMaritialStatus(IclubMaritialStatus iclubMaritialStatus) {
		this.iclubMaritialStatus = iclubMaritialStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_pri_cohort_id")
	public IclubCohort getIclubCohort() {
		return this.iclubCohort;
	}

	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_cohort_invite_id")
	public IclubCohortInvite getIclubCohortInvite() {
		return this.iclubCohortInvite;
	}

	public void setIclubCohortInvite(IclubCohortInvite iclubCohortInvite) {
		this.iclubCohortInvite = iclubCohortInvite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id_type")
	public IclubIdType getIclubIdType() {
		return this.iclubIdType;
	}

	public void setIclubIdType(IclubIdType iclubIdType) {
		this.iclubIdType = iclubIdType;
	}

	@Column(name = "p_title", length = 4)
	public String getPTitle() {
		return this.PTitle;
	}

	public void setPTitle(String PTitle) {
		this.PTitle = PTitle;
	}

	@Column(name = "p_initials", length = 5)
	public String getPInitials() {
		return this.PInitials;
	}

	public void setPInitials(String PInitials) {
		this.PInitials = PInitials;
	}

	@Column(name = "p_f_name", length = 450)
	public String getPFName() {
		return this.PFName;
	}

	public void setPFName(String PFName) {
		this.PFName = PFName;
	}

	@Column(name = "p_l_name", length = 450)
	public String getPLName() {
		return this.PLName;
	}

	public void setPLName(String PLName) {
		this.PLName = PLName;
	}

	@Column(name = "p_mobile", length = 13)
	public String getPMobile() {
		return this.PMobile;
	}

	public void setPMobile(String PMobile) {
		this.PMobile = PMobile;
	}

	@Column(name = "p_email", length = 999)
	public String getPEmail() {
		return this.PEmail;
	}

	public void setPEmail(String PEmail) {
		this.PEmail = PEmail;
	}

	@Column(name = "p_contact_pref", length = 1)
	public String getPContactPref() {
		return this.PContactPref;
	}

	public void setPContactPref(String PContactPref) {
		this.PContactPref = PContactPref;
	}

	@Column(name = "p_gender", length = 1)
	public String getPGender() {
		return this.PGender;
	}

	public void setPGender(String PGender) {
		this.PGender = PGender;
	}

	@Column(name = "p_id_num", length = 45)
	public String getPIdNum() {
		return this.PIdNum;
	}

	public void setPIdNum(String PIdNum) {
		this.PIdNum = PIdNum;
	}

	@Column(name = "p_id_issue_cntry")
	public Long getPIdIssueCntry() {
		return this.PIdIssueCntry;
	}

	public void setPIdIssueCntry(Long PIdIssueCntry) {
		this.PIdIssueCntry = PIdIssueCntry;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "p_id_issue_dt", length = 10)
	public Date getPIdIssueDt() {
		return this.PIdIssueDt;
	}

	public void setPIdIssueDt(Date PIdIssueDt) {
		this.PIdIssueDt = PIdIssueDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "p_id_expiry_dt", length = 10)
	public Date getPIdExpiryDt() {
		return this.PIdExpiryDt;
	}

	public void setPIdExpiryDt(Date PIdExpiryDt) {
		this.PIdExpiryDt = PIdExpiryDt;
	}

	@Column(name = "p_occupation")
	public Long getPOccupation() {
		return this.POccupation;
	}

	public void setPOccupation(Long POccupation) {
		this.POccupation = POccupation;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "p_dob", length = 10)
	public Date getPDob() {
		return this.PDob;
	}

	public void setPDob(Date PDob) {
		this.PDob = PDob;
	}

	@Column(name = "p_is_pensioner", length = 1)
	public String getPIsPensioner() {
		return this.PIsPensioner;
	}

	public void setPIsPensioner(String PIsPensioner) {
		this.PIsPensioner = PIsPensioner;
	}

	@Column(name = "p_address", length = 999)
	public String getPAddress() {
		return this.PAddress;
	}

	public void setPAddress(String PAddress) {
		this.PAddress = PAddress;
	}

	@Column(name = "p_lat", precision = 10, scale = 7)
	public Double getPLat() {
		return this.PLat;
	}

	public void setPLat(Double PLat) {
		this.PLat = PLat;
	}

	@Column(name = "p_long", precision = 10, scale = 7)
	public Double getPLong() {
		return this.PLong;
	}

	public void setPLong(Double PLong) {
		this.PLong = PLong;
	}

	@Column(name = "p_zip_cd")
	public Integer getPZipCd() {
		return this.PZipCd;
	}

	public void setPZipCd(Integer PZipCd) {
		this.PZipCd = PZipCd;
	}

	@Column(name = "p_age")
	public Integer getPAge() {
		return this.PAge;
	}

	public void setPAge(Integer PAge) {
		this.PAge = PAge;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "p_crtd_dt", length = 10)
	public Date getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(Date PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonBySpPersonId")
	public Set<IclubSupplPerson> getIclubSupplPersonsForSpPersonId() {
		return this.iclubSupplPersonsForSpPersonId;
	}

	public void setIclubSupplPersonsForSpPersonId(Set<IclubSupplPerson> iclubSupplPersonsForSpPersonId) {
		this.iclubSupplPersonsForSpPersonId = iclubSupplPersonsForSpPersonId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubMbComment> getIclubMbComments() {
		return this.iclubMbComments;
	}

	public void setIclubMbComments(Set<IclubMbComment> iclubMbComments) {
		this.iclubMbComments = iclubMbComments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonBySpCrtdBy")
	public Set<IclubSupplPerson> getIclubSupplPersonsForSpCrtdBy() {
		return this.iclubSupplPersonsForSpCrtdBy;
	}

	public void setIclubSupplPersonsForSpCrtdBy(Set<IclubSupplPerson> iclubSupplPersonsForSpCrtdBy) {
		this.iclubSupplPersonsForSpCrtdBy = iclubSupplPersonsForSpCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubCohortInvite> getIclubCohortInvites() {
		return this.iclubCohortInvites;
	}

	public void setIclubCohortInvites(Set<IclubCohortInvite> iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubNotif> getIclubNotifs() {
		return this.iclubNotifs;
	}

	public void setIclubNotifs(Set<IclubNotif> iclubNotifs) {
		this.iclubNotifs = iclubNotifs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubTrackerMaster> getIclubTrackerMasters() {
		return this.iclubTrackerMasters;
	}

	public void setIclubTrackerMasters(Set<IclubTrackerMaster> iclubTrackerMasters) {
		this.iclubTrackerMasters = iclubTrackerMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubPayment> getIclubPayments() {
		return this.iclubPayments;
	}

	public void setIclubPayments(Set<IclubPayment> iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubRateEngine> getIclubRateEngines() {
		return this.iclubRateEngines;
	}

	public void setIclubRateEngines(Set<IclubRateEngine> iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubCountryCode> getIclubCountryCodes() {
		return this.iclubCountryCodes;
	}

	public void setIclubCountryCodes(Set<IclubCountryCode> iclubCountryCodes) {
		this.iclubCountryCodes = iclubCountryCodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByLPersonId")
	public Set<IclubLogin> getIclubLoginsForLPersonId() {
		return this.iclubLoginsForLPersonId;
	}

	public void setIclubLoginsForLPersonId(Set<IclubLogin> iclubLoginsForLPersonId) {
		this.iclubLoginsForLPersonId = iclubLoginsForLPersonId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubPolicy> getIclubPolicies() {
		return this.iclubPolicies;
	}

	public void setIclubPolicies(Set<IclubPolicy> iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByLCrtdBy")
	public Set<IclubLogin> getIclubLoginsForLCrtdBy() {
		return this.iclubLoginsForLCrtdBy;
	}

	public void setIclubLoginsForLCrtdBy(Set<IclubLogin> iclubLoginsForLCrtdBy) {
		this.iclubLoginsForLCrtdBy = iclubLoginsForLCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByCpPersonId")
	public Set<IclubCohortPerson> getIclubCohortPersonsForCpPersonId() {
		return this.iclubCohortPersonsForCpPersonId;
	}

	public void setIclubCohortPersonsForCpPersonId(Set<IclubCohortPerson> iclubCohortPersonsForCpPersonId) {
		this.iclubCohortPersonsForCpPersonId = iclubCohortPersonsForCpPersonId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByCpCrtdBy")
	public Set<IclubCohortPerson> getIclubCohortPersonsForCpCrtdBy() {
		return this.iclubCohortPersonsForCpCrtdBy;
	}

	public void setIclubCohortPersonsForCpCrtdBy(Set<IclubCohortPerson> iclubCohortPersonsForCpCrtdBy) {
		this.iclubCohortPersonsForCpCrtdBy = iclubCohortPersonsForCpCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubVehicleMaster> getIclubVehicleMasters() {
		return this.iclubVehicleMasters;
	}

	public void setIclubVehicleMasters(Set<IclubVehicleMaster> iclubVehicleMasters) {
		this.iclubVehicleMasters = iclubVehicleMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubGeoLoc> getIclubGeoLocs() {
		return this.iclubGeoLocs;
	}

	public void setIclubGeoLocs(Set<IclubGeoLoc> iclubGeoLocs) {
		this.iclubGeoLocs = iclubGeoLocs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubConfig> getIclubConfigs() {
		return this.iclubConfigs;
	}

	public void setIclubConfigs(Set<IclubConfig> iclubConfigs) {
		this.iclubConfigs = iclubConfigs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubOccupation> getIclubOccupations() {
		return this.iclubOccupations;
	}

	public void setIclubOccupations(Set<IclubOccupation> iclubOccupations) {
		this.iclubOccupations = iclubOccupations;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubMessage> getIclubMessages() {
		return this.iclubMessages;
	}

	public void setIclubMessages(Set<IclubMessage> iclubMessages) {
		this.iclubMessages = iclubMessages;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubPropertyItem> getIclubPropertyItems() {
		return this.iclubPropertyItems;
	}

	public void setIclubPropertyItems(Set<IclubPropertyItem> iclubPropertyItems) {
		this.iclubPropertyItems = iclubPropertyItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubInsurerMaster> getIclubInsurerMasters() {
		return this.iclubInsurerMasters;
	}

	public void setIclubInsurerMasters(Set<IclubInsurerMaster> iclubInsurerMasters) {
		this.iclubInsurerMasters = iclubInsurerMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubSupplItem> getIclubSupplItems() {
		return this.iclubSupplItems;
	}

	public void setIclubSupplItems(Set<IclubSupplItem> iclubSupplItems) {
		this.iclubSupplItems = iclubSupplItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubLicenseCode> getIclubLicenseCodes() {
		return this.iclubLicenseCodes;
	}

	public void setIclubLicenseCodes(Set<IclubLicenseCode> iclubLicenseCodes) {
		this.iclubLicenseCodes = iclubLicenseCodes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByCCrtdBy")
	public Set<IclubCohort> getIclubCohortsForCCrtdBy() {
		return this.iclubCohortsForCCrtdBy;
	}

	public void setIclubCohortsForCCrtdBy(Set<IclubCohort> iclubCohortsForCCrtdBy) {
		this.iclubCohortsForCCrtdBy = iclubCohortsForCCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubRateType> getIclubRateTypes() {
		return this.iclubRateTypes;
	}

	public void setIclubRateTypes(Set<IclubRateType> iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByCAdminId")
	public Set<IclubCohort> getIclubCohortsForCAdminId() {
		return this.iclubCohortsForCAdminId;
	}

	public void setIclubCohortsForCAdminId(Set<IclubCohort> iclubCohortsForCAdminId) {
		this.iclubCohortsForCAdminId = iclubCohortsForCAdminId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByQCrtdBy")
	public Set<IclubQuote> getIclubQuotesForQCrtdBy() {
		return this.iclubQuotesForQCrtdBy;
	}

	public void setIclubQuotesForQCrtdBy(Set<IclubQuote> iclubQuotesForQCrtdBy) {
		this.iclubQuotesForQCrtdBy = iclubQuotesForQCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubAccount> getIclubAccounts() {
		return this.iclubAccounts;
	}

	public void setIclubAccounts(Set<IclubAccount> iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByCPrimaryUserId")
	public Set<IclubCohort> getIclubCohortsForCPrimaryUserId() {
		return this.iclubCohortsForCPrimaryUserId;
	}

	public void setIclubCohortsForCPrimaryUserId(Set<IclubCohort> iclubCohortsForCPrimaryUserId) {
		this.iclubCohortsForCPrimaryUserId = iclubCohortsForCPrimaryUserId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubInsuranceItem> getIclubInsuranceItems() {
		return this.iclubInsuranceItems;
	}

	public void setIclubInsuranceItems(Set<IclubInsuranceItem> iclubInsuranceItems) {
		this.iclubInsuranceItems = iclubInsuranceItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubCoverType> getIclubCoverTypes() {
		return this.iclubCoverTypes;
	}

	public void setIclubCoverTypes(Set<IclubCoverType> iclubCoverTypes) {
		this.iclubCoverTypes = iclubCoverTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubPurposeType> getIclubPurposeTypes() {
		return this.iclubPurposeTypes;
	}

	public void setIclubPurposeTypes(Set<IclubPurposeType> iclubPurposeTypes) {
		this.iclubPurposeTypes = iclubPurposeTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubEvent> getIclubEvents() {
		return this.iclubEvents;
	}

	public void setIclubEvents(Set<IclubEvent> iclubEvents) {
		this.iclubEvents = iclubEvents;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubSecurityDevice> getIclubSecurityDevices() {
		return this.iclubSecurityDevices;
	}

	public void setIclubSecurityDevices(Set<IclubSecurityDevice> iclubSecurityDevices) {
		this.iclubSecurityDevices = iclubSecurityDevices;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubClaim> getIclubClaims() {
		return this.iclubClaims;
	}

	public void setIclubClaims(Set<IclubClaim> iclubClaims) {
		this.iclubClaims = iclubClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubSecurityMaster> getIclubSecurityMasters() {
		return this.iclubSecurityMasters;
	}

	public void setIclubSecurityMasters(Set<IclubSecurityMaster> iclubSecurityMasters) {
		this.iclubSecurityMasters = iclubSecurityMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubCohortClaim> getIclubCohortClaims() {
		return this.iclubCohortClaims;
	}

	public void setIclubCohortClaims(Set<IclubCohortClaim> iclubCohortClaims) {
		this.iclubCohortClaims = iclubCohortClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubBankMaster> getIclubBankMasters() {
		return this.iclubBankMasters;
	}

	public void setIclubBankMasters(Set<IclubBankMaster> iclubBankMasters) {
		this.iclubBankMasters = iclubBankMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByQPersonId")
	public Set<IclubQuote> getIclubQuotesForQPersonId() {
		return this.iclubQuotesForQPersonId;
	}

	public void setIclubQuotesForQPersonId(Set<IclubQuote> iclubQuotesForQPersonId) {
		this.iclubQuotesForQPersonId = iclubQuotesForQPersonId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByDCrtdBy")
	public Set<IclubDriver> getIclubDriversForDCrtdBy() {
		return this.iclubDriversForDCrtdBy;
	}

	public void setIclubDriversForDCrtdBy(Set<IclubDriver> iclubDriversForDCrtdBy) {
		this.iclubDriversForDCrtdBy = iclubDriversForDCrtdBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubMessageBoard> getIclubMessageBoards() {
		return this.iclubMessageBoards;
	}

	public void setIclubMessageBoards(Set<IclubMessageBoard> iclubMessageBoards) {
		this.iclubMessageBoards = iclubMessageBoards;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubExtras> getIclubExtrases() {
		return this.iclubExtrases;
	}

	public void setIclubExtrases(Set<IclubExtras> iclubExtrases) {
		this.iclubExtrases = iclubExtrases;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubDocument> getIclubDocuments() {
		return this.iclubDocuments;
	}

	public void setIclubDocuments(Set<IclubDocument> iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubPerson> getIclubPersons() {
		return this.iclubPersons;
	}

	public void setIclubPersons(Set<IclubPerson> iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPerson")
	public Set<IclubSupplMaster> getIclubSupplMasters() {
		return this.iclubSupplMasters;
	}

	public void setIclubSupplMasters(Set<IclubSupplMaster> iclubSupplMasters) {
		this.iclubSupplMasters = iclubSupplMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPersonByDPersonId")
	public Set<IclubDriver> getIclubDriversForDPersonId() {
		return this.iclubDriversForDPersonId;
	}

	public void setIclubDriversForDPersonId(Set<IclubDriver> iclubDriversForDPersonId) {
		this.iclubDriversForDPersonId = iclubDriversForDPersonId;
	}

}