package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubNotifModel")
public class IclubNotifModel {

	private String NId;
	private Long iclubPerson;
	private Long iclubNotificationType;
	private String NTitle;
	private String NBody;
	private String NFromAddr;
	private String NToList;
	private String NStatus;
	private Timestamp NCrtdDt;

	public String getNId() {
		return NId;
	}

	public void setNId(String nId) {
		NId = nId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubNotificationType() {
		return iclubNotificationType;
	}

	public void setIclubNotificationType(Long iclubNotificationType) {
		this.iclubNotificationType = iclubNotificationType;
	}

	public String getNTitle() {
		return NTitle;
	}

	public void setNTitle(String nTitle) {
		NTitle = nTitle;
	}

	public String getNBody() {
		return NBody;
	}

	public void setNBody(String nBody) {
		NBody = nBody;
	}

	public String getNFromAddr() {
		return NFromAddr;
	}

	public void setNFromAddr(String nFromAddr) {
		NFromAddr = nFromAddr;
	}

	public String getNToList() {
		return NToList;
	}

	public void setNToList(String nToList) {
		NToList = nToList;
	}

	public String getNStatus() {
		return NStatus;
	}

	public void setNStatus(String nStatus) {
		NStatus = nStatus;
	}

	public Timestamp getNCrtdDt() {
		return NCrtdDt;
	}

	public void setNCrtdDt(Timestamp nCrtdDt) {
		NCrtdDt = nCrtdDt;
	}

}
