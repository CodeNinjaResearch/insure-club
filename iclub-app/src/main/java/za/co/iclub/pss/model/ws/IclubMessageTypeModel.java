package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubMessageTypeModel")
public class IclubMessageTypeModel {

	private Long mtId;
	private String mtShortDesc;
	private String mtLongDesc;
	private String mtStatus;

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

}
