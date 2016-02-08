package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubClaimItemBean {

	private String ciId;
	private String iclubSupplMasterByCiHandlerId;
	private String smARegNum;
	private String iclubInsuranceItem;
	private String iiQuoteId;
	private String iiItemId;
	private String iclubSupplMasterByCiAssesorId;
	private String smBRegNum;
	private String iclubClaim;
	private Long CNumber;
	private Double CValue;
	private Long iclubClaimStatus;
	private String csLongDesc;
	private Double ciValue;
	private String ciCrtdBy;
	private Date ciCrtdDt;

	public String getCiId() {
		return ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	public String getIclubSupplMasterByCiHandlerId() {
		return iclubSupplMasterByCiHandlerId;
	}

	public void setIclubSupplMasterByCiHandlerId(String iclubSupplMasterByCiHandlerId) {
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
	}

	public String getSmARegNum() {
		return smARegNum;
	}

	public void setSmARegNum(String smARegNum) {
		this.smARegNum = smARegNum;
	}

	public String getIclubInsuranceItem() {
		return iclubInsuranceItem;
	}

	public void setIclubInsuranceItem(String iclubInsuranceItem) {
		this.iclubInsuranceItem = iclubInsuranceItem;
	}

	public String getIclubSupplMasterByCiAssesorId() {
		return iclubSupplMasterByCiAssesorId;
	}

	public void setIclubSupplMasterByCiAssesorId(String iclubSupplMasterByCiAssesorId) {
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
	}

	public String getSmBRegNum() {
		return smBRegNum;
	}

	public void setSmBRegNum(String smBRegNum) {
		this.smBRegNum = smBRegNum;
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

	public Long getIclubClaimStatus() {
		return iclubClaimStatus;
	}

	public void setIclubClaimStatus(Long iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	public String getCsLongDesc() {
		return csLongDesc;
	}

	public void setCsLongDesc(String csLongDesc) {
		this.csLongDesc = csLongDesc;
	}

	public Double getCiValue() {
		return ciValue;
	}

	public void setCiValue(Double ciValue) {
		this.ciValue = ciValue;
	}

	public String getCiCrtdBy() {
		return ciCrtdBy;
	}

	public void setCiCrtdBy(String ciCrtdBy) {
		this.ciCrtdBy = ciCrtdBy;
	}

	public Date getCiCrtdDt() {
		return ciCrtdDt;
	}

	public void setCiCrtdDt(Date ciCrtdDt) {
		this.ciCrtdDt = ciCrtdDt;
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

}
