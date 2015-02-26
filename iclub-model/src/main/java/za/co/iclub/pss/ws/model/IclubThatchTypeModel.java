package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="IclubThatchTypeModel")
public class IclubThatchTypeModel {

	private Long ttId;
	private String ttShortDesc;
	private String ttLongDesc;
	private String ttStatus;
	
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
}
