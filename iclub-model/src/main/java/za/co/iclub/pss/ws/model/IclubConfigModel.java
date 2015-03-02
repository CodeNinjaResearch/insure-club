package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubConfigModel")
public class IclubConfigModel {

	private Long CId;
	private Long iclubPerson;
	private String CKey;
	private String CValue;
	private String CStatus;
	private Timestamp CCrtdDt;

	public Long getCId() {
		return CId;
	}

	public void setCId(Long cId) {
		CId = cId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getCKey() {
		return CKey;
	}

	public void setCKey(String cKey) {
		CKey = cKey;
	}

	public String getCValue() {
		return CValue;
	}

	public void setCValue(String cValue) {
		CValue = cValue;
	}

	public String getCStatus() {
		return CStatus;
	}

	public void setCStatus(String cStatus) {
		CStatus = cStatus;
	}

	public Timestamp getCCrtdDt() {
		return CCrtdDt;
	}

	public void setCCrtdDt(Timestamp cCrtdDt) {
		CCrtdDt = cCrtdDt;
	}

}
