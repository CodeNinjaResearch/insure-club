package za.co.iclub.pss.web.bean;

public class IclubThatchTypeBean {

	private Long ttId;
	private String ttShortDesc;
	private String ttLongDesc;
	private String ttStatus;
	private String[] iclubProperties;

	public Long getTtId() {
		return ttId;
	}

	public void setTtId(Long ttId) {
		this.ttId = ttId;
	}

	public String getTtShortDesc() {
		return ttShortDesc;
	}

	public void setTtShortDesc(String ttShortDesc) {
		this.ttShortDesc = ttShortDesc;
	}

	public String getTtLongDesc() {
		return ttLongDesc;
	}

	public void setTtLongDesc(String ttLongDesc) {
		this.ttLongDesc = ttLongDesc;
	}

	public String getTtStatus() {
		return ttStatus;
	}

	public void setTtStatus(String ttStatus) {
		this.ttStatus = ttStatus;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}
}
