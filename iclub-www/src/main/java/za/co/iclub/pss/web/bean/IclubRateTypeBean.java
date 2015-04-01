package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubRateTypeBean {

	private Long rtId;
	private Long iclubInsuranceItemType;
	private Long iclubField;
	private String iclubPerson;
	private Long iclubEntityType;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;
	private String rtType;
	private String rtQuoteType;
	private Timestamp rtCrtdDt;
	private String[] iclubRateEngines;

	public Long getRtId() {
		return rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubEntityType() {
		return iclubEntityType;
	}

	public void setIclubEntityType(Long iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	public String getRtShortDesc() {
		return rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	public String getRtLongDesc() {
		return rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	public String getRtStatus() {
		return rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	public String getRtQuoteType() {
		return rtQuoteType;
	}

	public void setRtQuoteType(String rtQuoteType) {
		this.rtQuoteType = rtQuoteType;
	}

	public Timestamp getRtCrtdDt() {
		return rtCrtdDt;
	}

	public void setRtCrtdDt(Timestamp rtCrtdDt) {
		this.rtCrtdDt = rtCrtdDt;
	}

	public String[] getIclubRateEngines() {
		return iclubRateEngines;
	}

	public void setIclubRateEngines(String[] iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}

	public Long getIclubField() {
		return iclubField;
	}

	public void setIclubField(Long iclubField) {
		this.iclubField = iclubField;
	}
}
