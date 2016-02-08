package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubEventBean {

	private String EId;
	private Long iclubEventType;
	private String etLongDesc;
	private String iclubPerson;
	private String PFNameAndLName;
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

	public String getEtLongDesc() {
		return etLongDesc;
	}

	public void setEtLongDesc(String etLongDesc) {
		this.etLongDesc = etLongDesc;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
