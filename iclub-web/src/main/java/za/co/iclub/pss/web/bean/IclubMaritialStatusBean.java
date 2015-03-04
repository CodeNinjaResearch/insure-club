package za.co.iclub.pss.web.bean;

public class IclubMaritialStatusBean {

	private Long msId;
	private String msShortDesc;
	private String msLongDesc;
	private String msStatus;

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getMsShortDesc() {
		return msShortDesc;
	}

	public void setMsShortDesc(String msShortDesc) {
		this.msShortDesc = msShortDesc;
	}

	public String getMsLongDesc() {
		return msLongDesc;
	}

	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}

	public String getMsStatus() {
		return msStatus;
	}

	public void setMsStatus(String msStatus) {
		this.msStatus = msStatus;
	}

}
