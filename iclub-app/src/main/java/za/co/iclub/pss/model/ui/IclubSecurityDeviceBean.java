package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubSecurityDeviceBean {

	private String sdId;
	private Long iclubInsuranceItemType;
	private String iitLongDesc;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubTrackerMaster;
	private String tmRegNum;
	private String sdItemId;
	private String sdSerNum;
	private String sdContractNum;
	private Date sdCrtdDt;

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

	public Date getSdCrtdDt() {
		return sdCrtdDt;
	}

	public void setSdCrtdDt(Date sdCrtdDt) {
		this.sdCrtdDt = sdCrtdDt;
	}

	public String getIitLongDesc() {
		return iitLongDesc;
	}

	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

	public String getTmRegNum() {
		return tmRegNum;
	}

	public void setTmRegNum(String tmRegNum) {
		this.tmRegNum = tmRegNum;
	}

}
