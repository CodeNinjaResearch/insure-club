package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPersonModel")
public class IclubPersonModel {
	
	private String PId;
	private Long iclubMaritialStatus;
	private String msLongDesc;
	private String iclubPerson;
	private String PFNameAndLName;
	private String iclubCohort;
	private String CEmail;
	private Long iclubIdType;
	private String itLongDesc;
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
	
	public String getMsLongDesc() {
		return msLongDesc;
	}
	
	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getIclubCohort() {
		return iclubCohort;
	}
	
	public void setIclubCohort(String iclubCohort) {
		this.iclubCohort = iclubCohort;
	}
	
	public String getCEmail() {
		return CEmail;
	}
	
	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}
	
	public Long getIclubIdType() {
		return iclubIdType;
	}
	
	public void setIclubIdType(Long iclubIdType) {
		this.iclubIdType = iclubIdType;
	}
	
	public String getItLongDesc() {
		return itLongDesc;
	}
	
	public void setItLongDesc(String itLongDesc) {
		this.itLongDesc = itLongDesc;
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
	
	public Integer getPAge() {
		return PAge;
	}
	
	public void setPAge(Integer pAge) {
		PAge = pAge;
	}
	
	public Date getPCrtdDt() {
		return PCrtdDt;
	}
	
	public void setPCrtdDt(Date pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}
	
}
