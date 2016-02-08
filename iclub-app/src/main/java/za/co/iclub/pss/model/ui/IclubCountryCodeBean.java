package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubCountryCodeBean {

	private Integer ccId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String ccShortId;
	private String ccIsoId;
	private String ccName;
	private Date ccCrtdDt;

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getCcShortId() {
		return ccShortId;
	}

	public void setCcShortId(String ccShortId) {
		this.ccShortId = ccShortId;
	}

	public String getCcIsoId() {
		return ccIsoId;
	}

	public void setCcIsoId(String ccIsoId) {
		this.ccIsoId = ccIsoId;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public Date getCcCrtdDt() {
		return ccCrtdDt;
	}

	public void setCcCrtdDt(Date ccCrtdDt) {
		this.ccCrtdDt = ccCrtdDt;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
