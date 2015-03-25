package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubPaymentBean {

	private String PId;
	private String iclubPolicy;
	private String iclubPerson;
	private String iclubClaim;
	private String iclubAccount;
	private Long iclubPaymentStatus;
	private Long PValue;
	private String PDrCrInd;
	private Timestamp PGenDt;
	private Timestamp PCrtdDt;

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

	public Long getPValue() {
		return PValue;
	}

	public void setPValue(Long pValue) {
		PValue = pValue;
	}

	public String getPDrCrInd() {
		return PDrCrInd;
	}

	public void setPDrCrInd(String pDrCrInd) {
		PDrCrInd = pDrCrInd;
	}

	public Timestamp getPGenDt() {
		return PGenDt;
	}

	public void setPGenDt(Timestamp pGenDt) {
		PGenDt = pGenDt;
	}

	public Timestamp getPCrtdDt() {
		return PCrtdDt;
	}

	public void setPCrtdDt(Timestamp pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}

}
