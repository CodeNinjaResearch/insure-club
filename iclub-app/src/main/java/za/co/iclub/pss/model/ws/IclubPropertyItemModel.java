package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropertyItemModel")
public class IclubPropertyItemModel {

	private String piId;
	private String iclubProperty;
	private String PRegNum;
	private String iclubPerson;
	private String PFNameAndLName;
	private String piDescripton;
	private Double piValue;
	private Date piCrtdDate;
	private boolean modFlag;

	public String getPiId() {
		return piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public String getIclubProperty() {
		return iclubProperty;
	}

	public void setIclubProperty(String iclubProperty) {
		this.iclubProperty = iclubProperty;
	}

	public String getPRegNum() {
		return PRegNum;
	}

	public void setPRegNum(String pRegNum) {
		PRegNum = pRegNum;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

	public String getPiDescripton() {
		return piDescripton;
	}

	public void setPiDescripton(String piDescripton) {
		this.piDescripton = piDescripton;
	}

	public Double getPiValue() {
		return piValue;
	}

	public void setPiValue(Double piValue) {
		this.piValue = piValue;
	}

	public Date getPiCrtdDate() {
		return piCrtdDate;
	}

	public void setPiCrtdDate(Date piCrtdDate) {
		this.piCrtdDate = piCrtdDate;
	}

	public boolean isModFlag() {
		return modFlag;
	}

	public void setModFlag(boolean modFlag) {
		this.modFlag = modFlag;
	}

}
