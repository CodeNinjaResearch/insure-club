package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubNotifModel")
public class IclubNotifModel {
	
	private String NId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubNotificationType;
	private String ntLongDesc;
	private String NTitle;
	private String NBody;
	private String NFromAddr;
	private String NToList;
	private String NStatus;
	private Date NCrtdDt;
	
	public String getNId() {
		return NId;
	}
	
	public void setNId(String nId) {
		NId = nId;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
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
	
	public Date getNCrtdDt() {
		return NCrtdDt;
	}
	
	public void setNCrtdDt(Date nCrtdDt) {
		NCrtdDt = nCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getNtLongDesc() {
		return ntLongDesc;
	}
	
	public void setNtLongDesc(String ntLongDesc) {
		this.ntLongDesc = ntLongDesc;
	}
	
}
