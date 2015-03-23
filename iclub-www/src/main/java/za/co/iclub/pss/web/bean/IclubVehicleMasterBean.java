package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubVehicleMasterBean {

	private Long vmId;
	private String iclubPerson;
	private String vmMake;
	private String vmModel;
	private Long vmOrigRate;
	private Long vmMrktRate;
	private Long vmRetRate;
	private Date vmProdDt;
	private Date vmCrtdDt;
	private String[] iclubVehicles;

	public Long getVmId() {
		return vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getVmMake() {
		return vmMake;
	}

	public void setVmMake(String vmMake) {
		this.vmMake = vmMake;
	}

	public String getVmModel() {
		return vmModel;
	}

	public void setVmModel(String vmModel) {
		this.vmModel = vmModel;
	}

	public Long getVmOrigRate() {
		return vmOrigRate;
	}

	public void setVmOrigRate(Long vmOrigRate) {
		this.vmOrigRate = vmOrigRate;
	}

	public Long getVmMrktRate() {
		return vmMrktRate;
	}

	public void setVmMrktRate(Long vmMrktRate) {
		this.vmMrktRate = vmMrktRate;
	}

	public Long getVmRetRate() {
		return vmRetRate;
	}

	public void setVmRetRate(Long vmRetRate) {
		this.vmRetRate = vmRetRate;
	}

	public Date getVmProdDt() {
		return vmProdDt;
	}

	public void setVmProdDt(Date vmProdDt) {
		this.vmProdDt = vmProdDt;
	}

	public Date getVmCrtdDt() {
		return vmCrtdDt;
	}

	public void setVmCrtdDt(Date vmCrtdDt) {
		this.vmCrtdDt = vmCrtdDt;
	}

	public String[] getIclubVehicles() {
		return iclubVehicles;
	}

	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}
