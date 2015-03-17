package za.co.iclub.pss.web.bean;

import java.util.List;

public class IclubWallTypeBean {

	private Long wtId;
	private String wtShortDesc;
	private String wtLongDesc;
	private String wtStatus;
	private String[] iclubProperties;

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

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}
}
