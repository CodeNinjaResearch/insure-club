package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubVehUsageTypeModel")
public class IclubVehUsageTypeModel {

	private Long vutId;
	private String vutLongDesc;
	private String vutShortDesc;
	private String vutStatus;
	private String[] iclubVehicles;

	public Long getVutId() {
		return vutId;
	}

	public void setVutId(Long vutId) {
		this.vutId = vutId;
	}

	public String getVutLongDesc() {
		return vutLongDesc;
	}

	public void setVutLongDesc(String vutLongDesc) {
		this.vutLongDesc = vutLongDesc;
	}

	public String getVutShortDesc() {
		return vutShortDesc;
	}

	public void setVutShortDesc(String vutShortDesc) {
		this.vutShortDesc = vutShortDesc;
	}

	public String getVutStatus() {
		return vutStatus;
	}

	public void setVutStatus(String vutStatus) {
		this.vutStatus = vutStatus;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}
