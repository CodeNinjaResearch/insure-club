package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropUsageTypeModel")
public class IclubPropUsageTypeModel {

	private Long putId;
	private String putLongDesc;
	private String putShortDesc;
	private String putStatus;

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

}
