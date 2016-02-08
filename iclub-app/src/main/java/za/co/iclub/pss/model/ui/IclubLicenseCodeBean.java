package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubLicenseCodeBean {

	private Long lcId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String lcCategory;
	private String lcDesc;
	private String lcStatus;
	private Date lcCrtdDt;

	public Long getLcId() {
		return lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getLcCategory() {
		return lcCategory;
	}

	public void setLcCategory(String lcCategory) {
		this.lcCategory = lcCategory;
	}

	public String getLcDesc() {
		return lcDesc;
	}

	public void setLcDesc(String lcDesc) {
		this.lcDesc = lcDesc;
	}

	public String getLcStatus() {
		return lcStatus;
	}

	public void setLcStatus(String lcStatus) {
		this.lcStatus = lcStatus;
	}

	public Date getLcCrtdDt() {
		return lcCrtdDt;
	}

	public void setLcCrtdDt(Date lcCrtdDt) {
		this.lcCrtdDt = lcCrtdDt;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
