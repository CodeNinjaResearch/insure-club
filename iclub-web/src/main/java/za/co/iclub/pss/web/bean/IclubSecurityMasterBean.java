package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubSecurityMasterBean {

	private String smId;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private String smDesc;
	private String smStatus;
	private Timestamp smCrtdDt;

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getSmDesc() {
		return smDesc;
	}

	public void setSmDesc(String smDesc) {
		this.smDesc = smDesc;
	}

	public String getSmStatus() {
		return smStatus;
	}

	public void setSmStatus(String smStatus) {
		this.smStatus = smStatus;
	}

	public Timestamp getSmCrtdDt() {
		return smCrtdDt;
	}

	public void setSmCrtdDt(Timestamp smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

}
