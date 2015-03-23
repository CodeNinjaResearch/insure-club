package za.co.iclub.pss.web.bean;

public class IclubSystemTypeBean {

	private Long stId;
	private String stShortDesc;
	private String stLongDesc;
	private String stStatus;
	private String[] iclubMessagesForMFromSysId;
	private String[] iclubMessagesForMToSysId;

	public Long getStId() {
		return stId;
	}

	public void setStId(Long stId) {
		this.stId = stId;
	}

	public String getStShortDesc() {
		return stShortDesc;
	}

	public void setStShortDesc(String stShortDesc) {
		this.stShortDesc = stShortDesc;
	}

	public String getStLongDesc() {
		return stLongDesc;
	}

	public void setStLongDesc(String stLongDesc) {
		this.stLongDesc = stLongDesc;
	}

	public String getStStatus() {
		return stStatus;
	}

	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}

	public String[] getIclubMessagesForMFromSysId() {
		return iclubMessagesForMFromSysId;
	}

	public void setIclubMessagesForMFromSysId(String[] iclubMessagesForMFromSysId) {
		this.iclubMessagesForMFromSysId = iclubMessagesForMFromSysId;
	}

	public String[] getIclubMessagesForMToSysId() {
		return iclubMessagesForMToSysId;
	}

	public void setIclubMessagesForMToSysId(String[] iclubMessagesForMToSysId) {
		this.iclubMessagesForMToSysId = iclubMessagesForMToSysId;
	}

}
