package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPurposeTypeModel")
public class IclubPurposeTypeModel {

	private Long ptId;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;

	public Long getPtId() {
		return ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}

	public String getPtShortDesc() {
		return ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	public String getPtLongDesc() {
		return ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	public String getPtStatus() {
		return ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

}
