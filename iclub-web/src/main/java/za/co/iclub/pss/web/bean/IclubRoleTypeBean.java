package za.co.iclub.pss.web.bean;

public class IclubRoleTypeBean {

	private Long rtId;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;
	private String[] iclubLogins;

	public Long getRtId() {
		return rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	public String getRtShortDesc() {
		return rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	public String getRtLongDesc() {
		return rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	public String getRtStatus() {
		return rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	public String[] getIclubLogins() {
		return iclubLogins;
	}

	public void setIclubLogins(String[] iclubLogins) {
		this.iclubLogins = iclubLogins;
	}

}
