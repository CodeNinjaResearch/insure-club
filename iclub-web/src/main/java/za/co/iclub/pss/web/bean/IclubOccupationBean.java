package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubOccupationBean {

	private Long OId;
	private String iclubPerson;
	private String ODesc;
	private String OStatus;
	private Date OCrtdDt;

	public Long getOId() {
		return OId;
	}

	public void setOId(Long oId) {
		OId = oId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getODesc() {
		return ODesc;
	}

	public void setODesc(String oDesc) {
		ODesc = oDesc;
	}

	public String getOStatus() {
		return OStatus;
	}

	public void setOStatus(String oStatus) {
		OStatus = oStatus;
	}

	public Date getOCrtdDt() {
		return OCrtdDt;
	}

	public void setOCrtdDt(Date oCrtdDt) {
		OCrtdDt = oCrtdDt;
	}

}
