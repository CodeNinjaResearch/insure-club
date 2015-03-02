package za.co.iclub.pss.web.bean;

public class IclubAccountTypeBean {

	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	private Long[] iclubAccounts;

	public Long getAtId() {
		return atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	public String getAtShortDesc() {
		return atShortDesc;
	}

	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}

	public String getAtLongDesc() {
		return atLongDesc;
	}

	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}

	public String getAtStatus() {
		return atStatus;
	}

	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}

	public Long[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(Long[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}
}
