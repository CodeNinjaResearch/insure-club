package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubDriverModel {

	private String DId;
	private Long iclubAccessType;
	private Long iclubLicenseCode;
	private String iclubPersonByDCrtdBy;
	private Long iclubMaritialStatus;
	private String iclubPersonByDPersonId;
	private String DName;
	private String DLicenseNum;
	private Timestamp DIssueDt;
	private Timestamp DDob;
	private Timestamp DCrtdDt;
	private String[] iclubVehicles;

	public String getDId() {
		return DId;
	}

	public void setDId(String dId) {
		DId = dId;
	}

	public Long getIclubAccessType() {
		return iclubAccessType;
	}

	public void setIclubAccessType(Long iclubAccessType) {
		this.iclubAccessType = iclubAccessType;
	}

	public Long getIclubLicenseCode() {
		return iclubLicenseCode;
	}

	public void setIclubLicenseCode(Long iclubLicenseCode) {
		this.iclubLicenseCode = iclubLicenseCode;
	}

	public String getIclubPersonByDCrtdBy() {
		return iclubPersonByDCrtdBy;
	}

	public void setIclubPersonByDCrtdBy(String iclubPersonByDCrtdBy) {
		this.iclubPersonByDCrtdBy = iclubPersonByDCrtdBy;
	}

	public Long getIclubMaritialStatus() {
		return iclubMaritialStatus;
	}

	public void setIclubMaritialStatus(Long iclubMaritialStatus) {
		this.iclubMaritialStatus = iclubMaritialStatus;
	}

	public String getIclubPersonByDPersonId() {
		return iclubPersonByDPersonId;
	}

	public void setIclubPersonByDPersonId(String iclubPersonByDPersonId) {
		this.iclubPersonByDPersonId = iclubPersonByDPersonId;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public String getDLicenseNum() {
		return DLicenseNum;
	}

	public void setDLicenseNum(String dLicenseNum) {
		DLicenseNum = dLicenseNum;
	}

	public Timestamp getDIssueDt() {
		return DIssueDt;
	}

	public void setDIssueDt(Timestamp dIssueDt) {
		DIssueDt = dIssueDt;
	}

	public Timestamp getDDob() {
		return DDob;
	}

	public void setDDob(Timestamp dDob) {
		DDob = dDob;
	}

	public Timestamp getDCrtdDt() {
		return DCrtdDt;
	}

	public void setDCrtdDt(Timestamp dCrtdDt) {
		DCrtdDt = dCrtdDt;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}
}
