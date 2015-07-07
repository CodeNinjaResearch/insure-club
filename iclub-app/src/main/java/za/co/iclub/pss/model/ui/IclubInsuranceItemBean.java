package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubInsuranceItemBean {
	
	private String iiId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubInsuranceItemType;
	private String iitLongDesc;
	private String iiQuoteId;
	private String iiItemId;
	private Date iiCrtdDt;
	private Double iiInsureValue;
	private Double iiActualValue;
	
	public String getIiId() {
		return iiId;
	}
	
	public void setIiId(String iiId) {
		this.iiId = iiId;
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
	
	public String getIiQuoteId() {
		return iiQuoteId;
	}
	
	public void setIiQuoteId(String iiQuoteId) {
		this.iiQuoteId = iiQuoteId;
	}
	
	public String getIiItemId() {
		return iiItemId;
	}
	
	public void setIiItemId(String iiItemId) {
		this.iiItemId = iiItemId;
	}
	
	public Date getIiCrtdDt() {
		return iiCrtdDt;
	}
	
	public void setIiCrtdDt(Date iiCrtdDt) {
		this.iiCrtdDt = iiCrtdDt;
	}
	
	public Double getIiInsureValue() {
		return iiInsureValue;
	}
	
	public void setIiInsureValue(Double iiInsureValue) {
		this.iiInsureValue = iiInsureValue;
	}
	
	public Double getIiActualValue() {
		return iiActualValue;
	}
	
	public void setIiActualValue(Double iiActualValue) {
		this.iiActualValue = iiActualValue;
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
