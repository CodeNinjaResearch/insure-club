package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPurposeTypeModel")
public class IclubPurposeTypeModel {

	private Long ptId;
	private Long iclubInsuranceItemType;
	private String iiLongDesc;
	private String iclubPerson;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Date ptCrtdDt;

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

	public String getIiLongDesc() {
		return iiLongDesc;
	}

	public void setIiLongDesc(String iiLongDesc) {
		this.iiLongDesc = iiLongDesc;
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

	public Date getPtCrtdDt() {
		return ptCrtdDt;
	}

	public void setPtCrtdDt(Date ptCrtdDt) {
		this.ptCrtdDt = ptCrtdDt;
	}

}
