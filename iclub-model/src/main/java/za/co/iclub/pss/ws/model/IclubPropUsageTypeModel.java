package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropUsageTypeModel")
public class IclubPropUsageTypeModel {

	private Long putId;
	private String putLongDesc;
	private String putShortDesc;
	private String putStatus;
	private String[] iclubProperties;

	public Long getPutId() {
		return putId;
	}

	public void setPutId(Long putId) {
		this.putId = putId;
	}

	public String getPutLongDesc() {
		return putLongDesc;
	}

	public void setPutLongDesc(String putLongDesc) {
		this.putLongDesc = putLongDesc;
	}

	public String getPutShortDesc() {
		return putShortDesc;
	}

	public void setPutShortDesc(String putShortDesc) {
		this.putShortDesc = putShortDesc;
	}

	public String getPutStatus() {
		return putStatus;
	}

	public void setPutStatus(String putStatus) {
		this.putStatus = putStatus;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}
