package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubAccountBean {

	private String AId;
	private Long iclubAccountType;
	private Long iclubBankMaster;
	private Long iclubOwnerType;
	private String iclubPerson;
	private String AAccNum;
	private String AOwnerId;
	private String AStatus;
	private Date ACrtdDt;
	private String[] iclubPolicies;
	private String[] iclubPayments;

	public String getAId() {
		return AId;
	}

	public void setAId(String aId) {
		AId = aId;
	}

	public Long getIclubAccountType() {
		return iclubAccountType;
	}

	public void setIclubAccountType(Long iclubAccountType) {
		this.iclubAccountType = iclubAccountType;
	}

	public Long getIclubBankMaster() {
		return iclubBankMaster;
	}

	public void setIclubBankMaster(Long iclubBankMaster) {
		this.iclubBankMaster = iclubBankMaster;
	}

	public Long getIclubOwnerType() {
		return iclubOwnerType;
	}

	public void setIclubOwnerType(Long iclubOwnerType) {
		this.iclubOwnerType = iclubOwnerType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getAAccNum() {
		return AAccNum;
	}

	public void setAAccNum(String aAccNum) {
		AAccNum = aAccNum;
	}

	public String getAOwnerId() {
		return AOwnerId;
	}

	public void setAOwnerId(String aOwnerId) {
		AOwnerId = aOwnerId;
	}

	public String getAStatus() {
		return AStatus;
	}

	public void setAStatus(String aStatus) {
		AStatus = aStatus;
	}

	public Date getACrtdDt() {
		return ACrtdDt;
	}

	public void setACrtdDt(Date aCrtdDt) {
		ACrtdDt = aCrtdDt;
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

}
