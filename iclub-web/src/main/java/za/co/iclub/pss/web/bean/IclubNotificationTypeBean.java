package za.co.iclub.pss.web.bean;

public class IclubNotificationTypeBean {

	private Long ntId;
	private String ntShortDesc;
	private String ntLongDesc;
	private String ntStatus;

	public Long getNtId() {
		return ntId;
	}

	public void setNtId(Long ntId) {
		this.ntId = ntId;
	}

	public String getNtShortDesc() {
		return ntShortDesc;
	}

	public void setNtShortDesc(String ntShortDesc) {
		this.ntShortDesc = ntShortDesc;
	}

	public String getNtLongDesc() {
		return ntLongDesc;
	}

	public void setNtLongDesc(String ntLongDesc) {
		this.ntLongDesc = ntLongDesc;
	}

	public String getNtStatus() {
		return ntStatus;
	}

	public void setNtStatus(String ntStatus) {
		this.ntStatus = ntStatus;
	}

}
