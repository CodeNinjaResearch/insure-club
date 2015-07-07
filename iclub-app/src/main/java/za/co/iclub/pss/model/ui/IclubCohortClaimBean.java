package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubCohortClaimBean {
	
	private String ccId;
	private String iclubCohort;
	private String CEmail;
	private String iclubPerson;
	private String PFNameAndLName;
	private String iclubClaim;
	private Long CNumber;
	private Double CValue;
	private Double ccClaimAmt;
	private Date ccCrtdDt;
	
	public String getCcId() {
		return ccId;
	}
	
	public void setCcId(String ccId) {
		this.ccId = ccId;
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
	
	public String getIclubClaim() {
		return iclubClaim;
	}
	
	public void setIclubClaim(String iclubClaim) {
		this.iclubClaim = iclubClaim;
	}
	
	public Long getCNumber() {
		return CNumber;
	}
	
	public void setCNumber(Long cNumber) {
		CNumber = cNumber;
	}
	
	public Double getCValue() {
		return CValue;
	}
	
	public void setCValue(Double cValue) {
		CValue = cValue;
	}
	
	public Double getCcClaimAmt() {
		return ccClaimAmt;
	}
	
	public void setCcClaimAmt(Double ccClaimAmt) {
		this.ccClaimAmt = ccClaimAmt;
	}
	
	public Date getCcCrtdDt() {
		return ccCrtdDt;
	}
	
	public void setCcCrtdDt(Date ccCrtdDt) {
		this.ccCrtdDt = ccCrtdDt;
	}
	
}
