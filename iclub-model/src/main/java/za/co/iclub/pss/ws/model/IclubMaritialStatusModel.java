package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubMaritialStatusModel")
public class IclubMaritialStatusModel {

	private Long msId;
	private String msShortDesc;
	private String msLongDesc;
	private String msStatus;
	private String[] iclubPersons;

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public String getMsShortDesc() {
		return msShortDesc;
	}

	public void setMsShortDesc(String msShortDesc) {
		this.msShortDesc = msShortDesc;
	}

	public String getMsLongDesc() {
		return msLongDesc;
	}

	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}

	public String getMsStatus() {
		return msStatus;
	}

	public void setMsStatus(String msStatus) {
		this.msStatus = msStatus;
	}

	public String[] getIclubPersons() {
		return iclubPersons;
	}

	public void setIclubPersons(String[] iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

}
