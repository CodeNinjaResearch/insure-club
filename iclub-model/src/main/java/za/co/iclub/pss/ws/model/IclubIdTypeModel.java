package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubIdTypeModel")
public class IclubIdTypeModel {
	
	private Long itId;
	private String itShortDesc;
	private String itLongDesc;
	private String itStatus;
	
	public Long getItId() {
		return itId;
	}
	public void setItId(Long itId) {
		this.itId = itId;
	}
	public String getItShortDesc() {
		return itShortDesc;
	}
	public void setItShortDesc(String itShortDesc) {
		this.itShortDesc = itShortDesc;
	}
	public String getItLongDesc() {
		return itLongDesc;
	}
	public void setItLongDesc(String itLongDesc) {
		this.itLongDesc = itLongDesc;
	}
	public String getItStatus() {
		return itStatus;
	}
	public void setItStatus(String itStatus) {
		this.itStatus = itStatus;
	}

}
