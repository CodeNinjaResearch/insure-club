package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubCoverTypeBean {
	
	private Long ctId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubInsuranceItemType;
	private String iitLongDesc;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	private Date ctCrtdDt;
	
	public Long getCtId() {
		return ctId;
	}
	
	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}
	
	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}
	
	public String getCtShortDesc() {
		return ctShortDesc;
	}
	
	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}
	
	public String getCtLongDesc() {
		return ctLongDesc;
	}
	
	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}
	
	public String getCtStatus() {
		return ctStatus;
	}
	
	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}
	
	public Date getCtCrtdDt() {
		return ctCrtdDt;
	}
	
	public void setCtCrtdDt(Date ctCrtdDt) {
		this.ctCrtdDt = ctCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getIitLongDesc() {
		return iitLongDesc;
	}
	
	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}
	
}
