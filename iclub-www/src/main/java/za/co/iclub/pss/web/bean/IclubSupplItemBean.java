package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubSupplItemBean {

	private String siId;
	private String iclubSupplMaster;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private Long iclubAssessmentType;
	private String siItemId;
	private Long siAssessNumber;
	private Date siCrtdDt;

	public String getSiId() {
		return siId;
	}

	public void setSiId(String siId) {
		this.siId = siId;
	}

	public String getIclubSupplMaster() {
		return iclubSupplMaster;
	}

	public void setIclubSupplMaster(String iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
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

	public Long getIclubAssessmentType() {
		return iclubAssessmentType;
	}

	public void setIclubAssessmentType(Long iclubAssessmentType) {
		this.iclubAssessmentType = iclubAssessmentType;
	}

	public String getSiItemId() {
		return siItemId;
	}

	public void setSiItemId(String siItemId) {
		this.siItemId = siItemId;
	}

	public Long getSiAssessNumber() {
		return siAssessNumber;
	}

	public void setSiAssessNumber(Long siAssessNumber) {
		this.siAssessNumber = siAssessNumber;
	}

	public Date getSiCrtdDt() {
		return siCrtdDt;
	}

	public void setSiCrtdDt(Date siCrtdDt) {
		this.siCrtdDt = siCrtdDt;
	}

}
