package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCoverTypeModel")
public class IclubCoverTypeModel {

	private Long ctId;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	
	public Long getCtId() {
		return ctId;
	}
	public void setCtId(Long ctId) {
		this.ctId = ctId;
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
	
}
