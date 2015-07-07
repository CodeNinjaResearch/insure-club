package za.co.iclub.pss.trans;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubDriverModel")
public class IclubDriverTrans {
	
	private String DId;
	private Long iclubAccessTypeAByDAccessTypeId;
	private String atALongDesc;
	private Long iclubLicenseCode;
	private String iclubPersonByDCrtdBy;
	private Long iclubMaritialStatus;
	private String msLongDesc;
	private String iclubPersonByDPersonId;
	private String DName;
	private String DLicenseNum;
	private Date DIssueDt;
	private Date DDob;
	private Date DCrtdDt;
	private Long iclubAccessTypeBByDAccessStatusId;
	private String atBLongDesc;
	private Integer DIssueYears;
	
	public String getDId() {
		return DId;
	}
	
	public void setDId(String dId) {
		DId = dId;
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
	
	public Date getDIssueDt() {
		return DIssueDt;
	}
	
	public void setDIssueDt(Date dIssueDt) {
		DIssueDt = dIssueDt;
	}
	
	public Date getDDob() {
		return DDob;
	}
	
	public void setDDob(Date dDob) {
		DDob = dDob;
	}
	
	public Date getDCrtdDt() {
		return DCrtdDt;
	}
	
	public void setDCrtdDt(Date dCrtdDt) {
		DCrtdDt = dCrtdDt;
	}
	
	public Integer getDIssueYears() {
		return DIssueYears;
	}
	
	public void setDIssueYears(Integer dIssueYears) {
		DIssueYears = dIssueYears;
	}
	
	public String getMsLongDesc() {
		return msLongDesc;
	}
	
	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}
	
	public String getAtALongDesc() {
		return atALongDesc;
	}
	
	public void setAtALongDesc(String atALongDesc) {
		this.atALongDesc = atALongDesc;
	}
	
	public String getAtBLongDesc() {
		return atBLongDesc;
	}
	
	public void setAtBLongDesc(String atBLongDesc) {
		this.atBLongDesc = atBLongDesc;
	}
	
	public Long getIclubAccessTypeAByDAccessTypeId() {
		return iclubAccessTypeAByDAccessTypeId;
	}
	
	public void setIclubAccessTypeAByDAccessTypeId(Long iclubAccessTypeAByDAccessTypeId) {
		this.iclubAccessTypeAByDAccessTypeId = iclubAccessTypeAByDAccessTypeId;
	}
	
	public Long getIclubAccessTypeBByDAccessStatusId() {
		return iclubAccessTypeBByDAccessStatusId;
	}
	
	public void setIclubAccessTypeBByDAccessStatusId(Long iclubAccessTypeBByDAccessStatusId) {
		this.iclubAccessTypeBByDAccessStatusId = iclubAccessTypeBByDAccessStatusId;
	}
}
