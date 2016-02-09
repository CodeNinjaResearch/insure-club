package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortCriteriaModel")
public class IclubCohortCriteriaModel {

	private String ccId;
	private String iclubCohortInvite;
	private Long ccAge;
	private String ccGender;
	private String ccClaimLastTwYrs;
	private String ccClaimLastYr;
	private String ccMaritalStatus;
	private String ccInsuredValue;

	public String getCcId() {
		return ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	public String getIclubCohortInvite() {
		return iclubCohortInvite;
	}

	public void setIclubCohortInvite(String iclubCohortInvite) {
		this.iclubCohortInvite = iclubCohortInvite;
	}

	public Long getCcAge() {
		return ccAge;
	}

	public void setCcAge(Long ccAge) {
		this.ccAge = ccAge;
	}

	public String getCcGender() {
		return ccGender;
	}

	public void setCcGender(String ccGender) {
		this.ccGender = ccGender;
	}

	public String getCcClaimLastTwYrs() {
		return ccClaimLastTwYrs;
	}

	public void setCcClaimLastTwYrs(String ccClaimLastTwYrs) {
		this.ccClaimLastTwYrs = ccClaimLastTwYrs;
	}

	public String getCcClaimLastYr() {
		return ccClaimLastYr;
	}

	public void setCcClaimLastYr(String ccClaimLastYr) {
		this.ccClaimLastYr = ccClaimLastYr;
	}

	public String getCcMaritalStatus() {
		return ccMaritalStatus;
	}

	public void setCcMaritalStatus(String ccMaritalStatus) {
		this.ccMaritalStatus = ccMaritalStatus;
	}

	public String getCcInsuredValue() {
		return ccInsuredValue;
	}

	public void setCcInsuredValue(String ccInsuredValue) {
		this.ccInsuredValue = ccInsuredValue;
	}
}
