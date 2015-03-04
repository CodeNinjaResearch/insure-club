package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubWallTypeModel")
public class IclubWallTypeModel {

	private Long wtId;
	private String wtShortDesc;
	private String wtLongDesc;
	private String wtStatus;

	public Long getWtId() {
		return wtId;
	}

	public void setWtId(Long wtId) {
		this.wtId = wtId;
	}

	public String getWtShortDesc() {
		return wtShortDesc;
	}

	public void setWtShortDesc(String wtShortDesc) {
		this.wtShortDesc = wtShortDesc;
	}

	public String getWtLongDesc() {
		return wtLongDesc;
	}

	public void setWtLongDesc(String wtLongDesc) {
		this.wtLongDesc = wtLongDesc;
	}

	public String getWtStatus() {
		return wtStatus;
	}

	public void setWtStatus(String wtStatus) {
		this.wtStatus = wtStatus;
	}
}
