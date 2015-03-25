package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

public class IclubInsuranceItemModel {

	private String iiId;
	private String iclubPerson;
	private Long iclubInsuranceItemType;
	private String iiQuoteId;
	private String iiItemId;
	private Timestamp iiCrtdDt;
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

	public Timestamp getIiCrtdDt() {
		return iiCrtdDt;
	}

	public void setIiCrtdDt(Timestamp iiCrtdDt) {
		this.iiCrtdDt = iiCrtdDt;
	}

	public String[] getIclubClaimItems() {
		return iclubClaimItems;
	}

	public void setIclubClaimItems(String[] iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
	}

}
