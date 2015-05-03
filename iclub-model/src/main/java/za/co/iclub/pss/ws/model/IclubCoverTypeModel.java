package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCoverTypeModel")
public class IclubCoverTypeModel {

	private Long ctId;
	private String iclubPerson;
	private Long iclubInsuranceItemType;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	private Date ctCrtdDt;
	private String[] iclubProperties;
	private String[] iclubQuotes;

	public Long getCtId() {
		return ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getCtShortDesc() {
		return ctShortDesc;
	}

	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}

	public String getCtLongDesc() {
		return ctLongDesc;
	}

	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}

	public String getCtStatus() {
		return ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

	public Date getCtCrtdDt() {
		return ctCrtdDt;
	}

	public void setCtCrtdDt(Date ctCrtdDt) {
		this.ctCrtdDt = ctCrtdDt;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	public String[] getIclubQuotes() {
		return iclubQuotes;
	}

	public void setIclubQuotes(String[] iclubQuotes) {
		this.iclubQuotes = iclubQuotes;
	}

}
