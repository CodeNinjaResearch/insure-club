package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubEventBean {

	private String EId;
	private String iclubEventType;
	private String iclubPerson;
	private String EDesc;
	private Timestamp ECrtdDt;

	public String getEId() {
		return EId;
	}

	public void setEId(String eId) {
		EId = eId;
	}

	public String getIclubEventType() {
		return iclubEventType;
	}

	public void setIclubEventType(String iclubEventType) {
		this.iclubEventType = iclubEventType;
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

	public Timestamp getECrtdDt() {
		return ECrtdDt;
	}

	public void setECrtdDt(Timestamp eCrtdDt) {
		ECrtdDt = eCrtdDt;
	}

}
