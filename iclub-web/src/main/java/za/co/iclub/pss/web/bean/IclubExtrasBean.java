package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubExtrasBean {

	private Long EId;
	private String iclubPerson;
	private String EDesc;
	private String EStatus;
	private Timestamp ECrtdDt;

	public Long getEId() {
		return EId;
	}

	public void setEId(Long eId) {
		EId = eId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getEDesc() {
		return EDesc;
	}

	public void setEDesc(String eDesc) {
		EDesc = eDesc;
	}

	public String getEStatus() {
		return EStatus;
	}

	public void setEStatus(String eStatus) {
		EStatus = eStatus;
	}

	public Timestamp getECrtdDt() {
		return ECrtdDt;
	}

	public void setECrtdDt(Timestamp eCrtdDt) {
		ECrtdDt = eCrtdDt;
	}

}
