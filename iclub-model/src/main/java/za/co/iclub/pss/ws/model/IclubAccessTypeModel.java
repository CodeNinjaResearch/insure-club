package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubAccessTypeModel")
public class IclubAccessTypeModel {

	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	private String[] iclubVehiclesForVOnAccessTypeId;
	private String[] iclubVehiclesForVDdAccessTypeId;
	private String[] iclubProperties;
	private String[] iclubDriversForDAccessTypeId;
	private String[] iclubDriversForDAccessStatusId;

	public Long getAtId() {
		return atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	public String getAtShortDesc() {
		return atShortDesc;
	}

	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}

	public String getAtLongDesc() {
		return atLongDesc;
	}

	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}

	public String getAtStatus() {
		return atStatus;
	}

	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}

	public String[] getIclubVehiclesForVOnAccessTypeId() {
		return iclubVehiclesForVOnAccessTypeId;
	}

	public void setIclubVehiclesForVOnAccessTypeId(String[] iclubVehiclesForVOnAccessTypeId) {
		this.iclubVehiclesForVOnAccessTypeId = iclubVehiclesForVOnAccessTypeId;
	}

	public String[] getIclubVehiclesForVDdAccessTypeId() {
		return iclubVehiclesForVDdAccessTypeId;
	}

	public void setIclubVehiclesForVDdAccessTypeId(String[] iclubVehiclesForVDdAccessTypeId) {
		this.iclubVehiclesForVDdAccessTypeId = iclubVehiclesForVDdAccessTypeId;
	}

	public String[] getIclubProperties() {
		return iclubProperties;
	}

	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	public String[] getIclubDriversForDAccessTypeId() {
		return iclubDriversForDAccessTypeId;
	}

	public void setIclubDriversForDAccessTypeId(String[] iclubDriversForDAccessTypeId) {
		this.iclubDriversForDAccessTypeId = iclubDriversForDAccessTypeId;
	}

	public String[] getIclubDriversForDAccessStatusId() {
		return iclubDriversForDAccessStatusId;
	}

	public void setIclubDriversForDAccessStatusId(String[] iclubDriversForDAccessStatusId) {
		this.iclubDriversForDAccessStatusId = iclubDriversForDAccessStatusId;
	}

}
