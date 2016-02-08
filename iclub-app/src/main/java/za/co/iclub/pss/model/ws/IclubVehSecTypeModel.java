package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubVehSecTypeModel")
public class IclubVehSecTypeModel {

	private Long vstId;
	private String vstShortDesc;
	private String vstLongDesc;
	private String vstStatus;

	public Long getVstId() {
		return vstId;
	}

	public void setVstId(Long vstId) {
		this.vstId = vstId;
	}

	public String getVstShortDesc() {
		return vstShortDesc;
	}

	public void setVstShortDesc(String vstShortDesc) {
		this.vstShortDesc = vstShortDesc;
	}

	public String getVstLongDesc() {
		return vstLongDesc;
	}

	public void setVstLongDesc(String vstLongDesc) {
		this.vstLongDesc = vstLongDesc;
	}

	public String getVstStatus() {
		return vstStatus;
	}

	public void setVstStatus(String vstStatus) {
		this.vstStatus = vstStatus;
	}

}
