package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubVehicleTypeModel")
public class IclubVehicleTypeModel {

	private Long vtId;
	private String vtShortDesc;
	private String vtLongDesc;
	private String vtStatus;

	public Long getVtId() {
		return vtId;
	}

	public void setVtId(Long vtId) {
		this.vtId = vtId;
	}

	public String getVtShortDesc() {
		return vtShortDesc;
	}

	public void setVtShortDesc(String vtShortDesc) {
		this.vtShortDesc = vtShortDesc;
	}

	public String getVtLongDesc() {
		return vtLongDesc;
	}

	public void setVtLongDesc(String vtLongDesc) {
		this.vtLongDesc = vtLongDesc;
	}

	public String getVtStatus() {
		return vtStatus;
	}

	public void setVtStatus(String vtStatus) {
		this.vtStatus = vtStatus;
	}

}
