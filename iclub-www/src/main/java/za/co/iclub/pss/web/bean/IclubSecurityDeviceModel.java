package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubSecurityDeviceModel {

	private String sdId;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private Long iclubTrackerMaster;
	private String sdItemId;
	private String sdSerNum;
	private String sdContractNum;
	private Timestamp sdCrtdDt;
	private String[] iclubVehicles;

	public String getSdId() {
		return sdId;
	}

	public void setSdId(String sdId) {
		this.sdId = sdId;
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

	public Long getIclubTrackerMaster() {
		return iclubTrackerMaster;
	}

	public void setIclubTrackerMaster(Long iclubTrackerMaster) {
		this.iclubTrackerMaster = iclubTrackerMaster;
	}

	public String getSdItemId() {
		return sdItemId;
	}

	public void setSdItemId(String sdItemId) {
		this.sdItemId = sdItemId;
	}

	public String getSdSerNum() {
		return sdSerNum;
	}

	public void setSdSerNum(String sdSerNum) {
		this.sdSerNum = sdSerNum;
	}

	public String getSdContractNum() {
		return sdContractNum;
	}

	public void setSdContractNum(String sdContractNum) {
		this.sdContractNum = sdContractNum;
	}

	public Timestamp getSdCrtdDt() {
		return sdCrtdDt;
	}

	public void setSdCrtdDt(Timestamp sdCrtdDt) {
		this.sdCrtdDt = sdCrtdDt;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}
