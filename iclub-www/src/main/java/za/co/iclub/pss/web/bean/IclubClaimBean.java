package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubClaimBean {

	private String CId;
	private String iclubPerson;
	private Long iclubClaimStatus;
	private String iclubPolicy;
	private Long CNumber;
	private Integer CNumItems;
	private Long CValue;
	private Timestamp CCrtdDt;
	private String[] iclubPayments;
	private String iclubClaimItems;

	public String getCId() {
		return CId;
	}

	public void setCId(String cId) {
		CId = cId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubClaimStatus() {
		return iclubClaimStatus;
	}

	public void setIclubClaimStatus(Long iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	public String getIclubPolicy() {
		return iclubPolicy;
	}

	public void setIclubPolicy(String iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
	}

	public Long getCNumber() {
		return CNumber;
	}

	public void setCNumber(Long cNumber) {
		CNumber = cNumber;
	}

	public Integer getCNumItems() {
		return CNumItems;
	}

	public void setCNumItems(Integer cNumItems) {
		CNumItems = cNumItems;
	}

	public Long getCValue() {
		return CValue;
	}

	public void setCValue(Long cValue) {
		CValue = cValue;
	}

	public Timestamp getCCrtdDt() {
		return CCrtdDt;
	}

	public void setCCrtdDt(Timestamp cCrtdDt) {
		CCrtdDt = cCrtdDt;
	}

	public String[] getIclubPayments() {
		return iclubPayments;
	}

	public void setIclubPayments(String[] iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

	public String getIclubClaimItems() {
		return iclubClaimItems;
	}

	public void setIclubClaimItems(String iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
	}

}
