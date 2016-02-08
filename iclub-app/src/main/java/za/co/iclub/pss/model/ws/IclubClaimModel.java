package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubClaimModel")
public class IclubClaimModel {

	private String CId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubClaimStatus;
	private String csLongDesc;
	private String iclubPolicy;
	private Double PPremium;
	private Long PNumber;
	private Long CNumber;
	private Integer CNumItems;
	private Double CValue;
	private Date CCrtdDt;

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

	public Double getCValue() {
		return CValue;
	}

	public void setCValue(Double cValue) {
		CValue = cValue;
	}

	public Date getCCrtdDt() {
		return CCrtdDt;
	}

	public void setCCrtdDt(Date cCrtdDt) {
		CCrtdDt = cCrtdDt;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

	public String getCsLongDesc() {
		return csLongDesc;
	}

	public void setCsLongDesc(String csLongDesc) {
		this.csLongDesc = csLongDesc;
	}

	public Double getPPremium() {
		return PPremium;
	}

	public void setPPremium(Double pPremium) {
		PPremium = pPremium;
	}

	public Long getPNumber() {
		return PNumber;
	}

	public void setPNumber(Long pNumber) {
		PNumber = pNumber;
	}

}
