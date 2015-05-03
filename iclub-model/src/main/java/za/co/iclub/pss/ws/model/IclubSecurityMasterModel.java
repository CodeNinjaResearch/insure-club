package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubSecurityMasterModel")
public class IclubSecurityMasterModel {

	private String smId;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private String smDesc;
	private String smStatus;
	private Date smCrtdDt;
	private String[] iclubVehicles ;

	public String getSmId() {
		return smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getSmDesc() {
		return smDesc;
	}

	public void setSmDesc(String smDesc) {
		this.smDesc = smDesc;
	}

	public String getSmStatus() {
		return smStatus;
	}

	public void setSmStatus(String smStatus) {
		this.smStatus = smStatus;
	}

	public Date getSmCrtdDt() {
		return smCrtdDt;
	}

	public void setSmCrtdDt(Date smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}
