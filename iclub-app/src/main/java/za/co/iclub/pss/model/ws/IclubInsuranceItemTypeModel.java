package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubInsuranceItemTypeModel")
public class IclubInsuranceItemTypeModel {

	private Long iitId;
	private String iitShortDesc;
	private String iitLongDesc;
	private String iitStatus;

	public Long getIitId() {
		return iitId;
	}

	public void setIitId(Long iitId) {
		this.iitId = iitId;
	}

	public String getIitShortDesc() {
		return iitShortDesc;
	}

	public void setIitShortDesc(String iitShortDesc) {
		this.iitShortDesc = iitShortDesc;
	}

	public String getIitLongDesc() {
		return iitLongDesc;
	}

	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}

	public String getIitStatus() {
		return iitStatus;
	}

	public void setIitStatus(String iitStatus) {
		this.iitStatus = iitStatus;
	}

}
