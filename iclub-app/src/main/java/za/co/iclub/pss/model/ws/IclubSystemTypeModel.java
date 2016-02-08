package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubSystemTypeModel")
public class IclubSystemTypeModel {

	private Long stId;
	private String stShortDesc;
	private String stLongDesc;
	private String stStatus;

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

}
