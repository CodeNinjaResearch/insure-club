package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubAccountTypeModel")
public class IclubAccountTypeModel {

	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	private String[] iclubAccounts;

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

	public String[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(String[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}
}
