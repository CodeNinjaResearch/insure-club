package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubPurposeTypeBean {

	private Long ptId;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Timestamp ptCrtdDt;
	private String[] iclubProperties;
	private String[] iclubVehicles;

	public Long getPtId() {
		return ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
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

	public String getPtShortDesc() {
		return ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	public String getPtLongDesc() {
		return ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	public String getPtStatus() {
		return ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

	public Timestamp getPtCrtdDt() {
		return ptCrtdDt;
	}

	public void setPtCrtdDt(Timestamp ptCrtdDt) {
		this.ptCrtdDt = ptCrtdDt;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}
