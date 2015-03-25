package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPolicyModel")
public class IclubPolicyModel {

	private String PId;
	private Long iclubPolicyStatus;
	private String iclubQuote;
	private String iclubPerson;
	private String iclubAccount;
	private Long PNumber;
	private Long PPremium;
	private Long PProrataPrm;
	private Integer PDebitDt;
	private String PCrtdDt;
	private String[] iclubClaims;
	private String[] iclubPayments;

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

	public Long getPPremium() {
		return PPremium;
	}

	public void setPPremium(Long pPremium) {
		PPremium = pPremium;
	}

	public Long getPProrataPrm() {
		return PProrataPrm;
	}

	public void setPProrataPrm(Long pProrataPrm) {
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

	public String[] getIclubClaims() {
		return iclubClaims;
	}

	public void setIclubClaims(String[] iclubClaims) {
		this.iclubClaims = iclubClaims;
	}

	public String[] getIclubPayments() {
		return iclubPayments;
	}

	public void setIclubPayments(String[] iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

}
