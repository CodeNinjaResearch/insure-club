package za.co.iclub.pss.web.bean;

public class IclubMessageTypeBean {

	private Long mtId;
	private String mtShortDesc;
	private String mtLongDesc;
	private String mtStatus;
	private String[] iclubMessages;

	public Long getMtId() {
		return mtId;
	}

	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}

	public String getMtShortDesc() {
		return mtShortDesc;
	}

	public void setMtShortDesc(String mtShortDesc) {
		this.mtShortDesc = mtShortDesc;
	}

	public String getMtLongDesc() {
		return mtLongDesc;
	}

	public void setMtLongDesc(String mtLongDesc) {
		this.mtLongDesc = mtLongDesc;
	}

	public String getMtStatus() {
		return mtStatus;
	}

	public void setMtStatus(String mtStatus) {
		this.mtStatus = mtStatus;
	}

	public String[] getIclubMessages() {
		return iclubMessages;
	}

	public void setIclubMessages(String[] iclubMessages) {
		this.iclubMessages = iclubMessages;
	}

}
