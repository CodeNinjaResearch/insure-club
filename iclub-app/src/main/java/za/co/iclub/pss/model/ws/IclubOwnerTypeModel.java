package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubOwnerTypeModel")
public class IclubOwnerTypeModel {

	private Long otId;
	private String otShortDesc;
	private String otLongDesc;
	private String otStatus;

	public Long getOtId() {
		return otId;
	}

	public void setOtId(Long otId) {
		this.otId = otId;
	}

	public String getOtShortDesc() {
		return otShortDesc;
	}

	public void setOtShortDesc(String otShortDesc) {
		this.otShortDesc = otShortDesc;
	}

	public String getOtLongDesc() {
		return otLongDesc;
	}

	public void setOtLongDesc(String otLongDesc) {
		this.otLongDesc = otLongDesc;
	}

	public String getOtStatus() {
		return otStatus;
	}

	public void setOtStatus(String otStatus) {
		this.otStatus = otStatus;
	}

}
