package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubInsuranceItemBean {

	private String iiId;
	private String iclubPerson;
	private Long iclubInsuranceItemType;
	private String iiQuoteId;
	private String iiItemId;
	private Double iiInsureValue;
	private Double iiActualValue;
	private Date iiCrtdDt;
	private String[] iclubClaimItems;

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

	public String[] getIclubClaimItems() {
		return iclubClaimItems;
	}

	public void setIclubClaimItems(String[] iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
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

}
