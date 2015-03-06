package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubLicenseCodeBean {

	private Long lcId;
	private String iclubPerson;
	private String lcCategory;
	private String lcDesc;
	private String lcStatus;
	private Timestamp lcCrtdDt;
	private String[] iclubDrivers;

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

	public Timestamp getLcCrtdDt() {
		return lcCrtdDt;
	}

	public void setLcCrtdDt(Timestamp lcCrtdDt) {
		this.lcCrtdDt = lcCrtdDt;
	}

	public String[] getIclubDrivers() {
		return iclubDrivers;
	}

	public void setIclubDrivers(String[] iclubDrivers) {
		this.iclubDrivers = iclubDrivers;
	}

}
