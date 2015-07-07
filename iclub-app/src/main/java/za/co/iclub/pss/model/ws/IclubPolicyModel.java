package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPolicyModel")
public class IclubPolicyModel {
	
	private String PId;
	private Long iclubPolicyStatus;
	private String psLongDesc;
	private String iclubQuote;
	private Long QNumber;
	private String iclubPerson;
	private String PFNameAndLName;
	private String iclubAccount;
	private String AAccNum;
	private Long PNumber;
	private Double PPremium;
	private Double PProrataPrm;
	private Integer PDebitDt;
	private String PCrtdDt;
	
	public String getPId() {
		return PId;
	}
	
	public void setPId(String pId) {
		PId = pId;
	}
	
	public Long getIclubPolicyStatus() {
		return iclubPolicyStatus;
	}
	
	public void setIclubPolicyStatus(Long iclubPolicyStatus) {
		this.iclubPolicyStatus = iclubPolicyStatus;
	}
	
	public String getIclubQuote() {
		return iclubQuote;
	}
	
	public void setIclubQuote(String iclubQuote) {
		this.iclubQuote = iclubQuote;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getIclubAccount() {
		return iclubAccount;
	}
	
	public void setIclubAccount(String iclubAccount) {
		this.iclubAccount = iclubAccount;
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
	
	public Double getPProrataPrm() {
		return PProrataPrm;
	}
	
	public void setPProrataPrm(Double pProrataPrm) {
		PProrataPrm = pProrataPrm;
	}
	
	public Integer getPDebitDt() {
		return PDebitDt;
	}
	
	public void setPDebitDt(Integer pDebitDt) {
		PDebitDt = pDebitDt;
	}
	
	public String getPCrtdDt() {
		return PCrtdDt;
	}
	
	public void setPCrtdDt(String pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}
	
	public String getPsLongDesc() {
		return psLongDesc;
	}
	
	public void setPsLongDesc(String psLongDesc) {
		this.psLongDesc = psLongDesc;
	}
	
	public Long getQNumber() {
		return QNumber;
	}
	
	public void setQNumber(Long qNumber) {
		QNumber = qNumber;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getAAccNum() {
		return AAccNum;
	}
	
	public void setAAccNum(String aAccNum) {
		AAccNum = aAccNum;
	}
	
}
