package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPaymentModel")
public class IclubPaymentModel {

	private String PId;
	private String iclubPolicy;
	private String iclubPerson;
	private String iclubClaim;
	private String iclubAccount;
	private Long iclubPaymentStatus;
	private Double PValue;
	private String PDrCrInd;
	private Date PGenDt;
	private Date PCrtdDt;

	public String getPId() {
		return PId;
	}

	public void setPId(String pId) {
		PId = pId;
	}

	public String getIclubPolicy() {
		return iclubPolicy;
	}

	public void setIclubPolicy(String iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
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

	public String getIclubAccount() {
		return iclubAccount;
	}

	public void setIclubAccount(String iclubAccount) {
		this.iclubAccount = iclubAccount;
	}

	public Long getIclubPaymentStatus() {
		return iclubPaymentStatus;
	}

	public void setIclubPaymentStatus(Long iclubPaymentStatus) {
		this.iclubPaymentStatus = iclubPaymentStatus;
	}

	public Double getPValue() {
		return PValue;
	}

	public void setPValue(Double pValue) {
		PValue = pValue;
	}

	public String getPDrCrInd() {
		return PDrCrInd;
	}

	public void setPDrCrInd(String pDrCrInd) {
		PDrCrInd = pDrCrInd;
	}

	public Date getPGenDt() {
		return PGenDt;
	}

	public void setPGenDt(Date pGenDt) {
		PGenDt = pGenDt;
	}

	public Date getPCrtdDt() {
		return PCrtdDt;
	}

	public void setPCrtdDt(Date pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}

}
