package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubCountryCodeModel {

	private Integer ccId;
	private Long iclubPerson;
	private String ccShortId;
	private String ccIsoId;
	private String ccName;
	private Timestamp ccCrtdDt;

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getCcShortId() {
		return ccShortId;
	}

	public void setCcShortId(String ccShortId) {
		this.ccShortId = ccShortId;
	}

	public String getCcIsoId() {
		return ccIsoId;
	}

	public void setCcIsoId(String ccIsoId) {
		this.ccIsoId = ccIsoId;
	}

	public String getCcName() {
		return ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	public Timestamp getCcCrtdDt() {
		return ccCrtdDt;
	}

	public void setCcCrtdDt(Timestamp ccCrtdDt) {
		this.ccCrtdDt = ccCrtdDt;
	}

}
