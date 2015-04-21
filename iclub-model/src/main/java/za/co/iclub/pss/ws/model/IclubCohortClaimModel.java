package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortClaimModel")
public class IclubCohortClaimModel {

	private String ccId;
	private String iclubCohort;
	private String iclubPerson;
	private String iclubClaim;
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

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getIclubClaim() {
		return iclubClaim;
	}

	public void setIclubClaim(String iclubClaim) {
		this.iclubClaim = iclubClaim;
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
