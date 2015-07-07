package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubPaymentBean {
	
	private String PId;
	private String iclubPolicy;
	private Long PNumber;
	private Double PPremium;
	private String iclubPerson;
	private String PFNameAndLName;
	private String iclubClaim;
	private Long CNumber;
	private Double CValue;
	private String iclubAccount;
	private String AAccNum;
	private Long iclubPaymentStatus;
	private String psLongDesc;
	private Double PValue;
	private String PDrCrInd;
	private Date PGenDt;
	private Date PCrtdDt;
	
	public String getPId() {
		return PId;
	}
	
	public void setPId(String pId) {
		PId = pId;
	}
	
	public String getIclubPolicy() {
		return iclubPolicy;
	}
	
	public void setIclubPolicy(String iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
	}
	
	public Long getPNumber() {
		return PNumber;
	}
	
	public void setPNumber(Long pNumber) {
		PNumber = pNumber;
	}
	
	public Double getPPremium() {
		return PPremium;
	}
	
	public void setPPremium(Double pPremium) {
		PPremium = pPremium;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getIclubClaim() {
		return iclubClaim;
	}
	
	public void setIclubClaim(String iclubClaim) {
		this.iclubClaim = iclubClaim;
	}
	
	public Long getCNumber() {
		return CNumber;
	}
	
	public void setCNumber(Long cNumber) {
		CNumber = cNumber;
	}
	
	public Double getCValue() {
		return CValue;
	}
	
	public void setCValue(Double cValue) {
		CValue = cValue;
	}
	
	public String getIclubAccount() {
		return iclubAccount;
	}
	
	public void setIclubAccount(String iclubAccount) {
		this.iclubAccount = iclubAccount;
	}
	
	public String getAAccNum() {
		return AAccNum;
	}
	
	public void setAAccNum(String aAccNum) {
		AAccNum = aAccNum;
	}
	
	public Long getIclubPaymentStatus() {
		return iclubPaymentStatus;
	}
	
	public void setIclubPaymentStatus(Long iclubPaymentStatus) {
		this.iclubPaymentStatus = iclubPaymentStatus;
	}
	
	public String getPsLongDesc() {
		return psLongDesc;
	}
	
	public void setPsLongDesc(String psLongDesc) {
		this.psLongDesc = psLongDesc;
	}
	
	public Double getPValue() {
		return PValue;
	}
	
	public void setPValue(Double pValue) {
		PValue = pValue;
	}
	
	public String getPDrCrInd() {
		return PDrCrInd;
	}
	
	public void setPDrCrInd(String pDrCrInd) {
		PDrCrInd = pDrCrInd;
	}
	
	public Date getPGenDt() {
		return PGenDt;
	}
	
	public void setPGenDt(Date pGenDt) {
		PGenDt = pGenDt;
	}
	
	public Date getPCrtdDt() {
		return PCrtdDt;
	}
	
	public void setPCrtdDt(Date pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}
	
}
