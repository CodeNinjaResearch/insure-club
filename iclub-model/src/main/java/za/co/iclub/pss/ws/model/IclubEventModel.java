package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubEventModel")
public class IclubEventModel {

	private String EId;
	private Long iclubEventType;
	private String iclubPerson;
	private String EDesc;
	private Date ECrtdDt;

	public String getEId() {
		return EId;
	}

	public void setEId(String eId) {
		EId = eId;
	}

	public Long getIclubEventType() {
		return iclubEventType;
	}

	public void setIclubEventType(Long iclubEventType) {
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

	public Date getECrtdDt() {
		return ECrtdDt;
	}

	public void setECrtdDt(Date eCrtdDt) {
		ECrtdDt = eCrtdDt;
	}

}
