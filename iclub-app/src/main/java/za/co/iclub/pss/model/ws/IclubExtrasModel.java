package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubExtrasModel")
public class IclubExtrasModel {
	
	private Long EId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String EDesc;
	private String EStatus;
	private Date ECrtdDt;
	
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
	
	public Date getECrtdDt() {
		return ECrtdDt;
	}
	
	public void setECrtdDt(Date eCrtdDt) {
		ECrtdDt = eCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
}
