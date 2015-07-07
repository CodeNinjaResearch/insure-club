package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubSupplItemBean {
	
	private String siId;
	private String iclubSupplMaster;
	private String smRegNum;
	private Long iclubInsuranceItemType;
	private String iitLongDesc;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubAssessmentType;
	private String atLongDesc;
	private String siItemId;
	private Long siAssessNumber;
	private Date siCrtdDt;
	
	public String getSiId() {
		return siId;
	}
	
	public void setSiId(String siId) {
		this.siId = siId;
	}
	
	public String getIclubSupplMaster() {
		return iclubSupplMaster;
	}
	
	public void setIclubSupplMaster(String iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
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
	
	public Long getIclubAssessmentType() {
		return iclubAssessmentType;
	}
	
	public void setIclubAssessmentType(Long iclubAssessmentType) {
		this.iclubAssessmentType = iclubAssessmentType;
	}
	
	public String getSiItemId() {
		return siItemId;
	}
	
	public void setSiItemId(String siItemId) {
		this.siItemId = siItemId;
	}
	
	public Long getSiAssessNumber() {
		return siAssessNumber;
	}
	
	public void setSiAssessNumber(Long siAssessNumber) {
		this.siAssessNumber = siAssessNumber;
	}
	
	public Date getSiCrtdDt() {
		return siCrtdDt;
	}
	
	public void setSiCrtdDt(Date siCrtdDt) {
		this.siCrtdDt = siCrtdDt;
	}
	
	public String getSmRegNum() {
		return smRegNum;
	}
	
	public void setSmRegNum(String smRegNum) {
		this.smRegNum = smRegNum;
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
	
	public String getAtLongDesc() {
		return atLongDesc;
	}
	
	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}
	
}
