package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;
import java.util.Date;

public class IclubConfigBean {

	private Long CId;
	private String iclubPerson;
	private String CKey;
	private String CValue;
	private String CStatus;
	private Date CCrtdDt;

	public Long getCId() {
		return CId;
	}

	public void setCId(Long cId) {
		CId = cId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
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

	public Date getCCrtdDt() {
		return CCrtdDt;
	}

	public void setCCrtdDt(Date cCrtdDt) {
		CCrtdDt = cCrtdDt;
	}

}
