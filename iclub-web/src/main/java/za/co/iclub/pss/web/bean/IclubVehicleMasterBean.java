package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubVehicleMasterBean {

	private Long vmId;
	private String iclubPerson;
	private String vmMake;
	private String vmModel;
	private Double vmOrigRate;
	private Double vmMrktRate;
	private Double vmRetRate;
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

	public Double getVmOrigRate() {
		return vmOrigRate;
	}

	public void setVmOrigRate(Double vmOrigRate) {
		this.vmOrigRate = vmOrigRate;
	}

	public Double getVmMrktRate() {
		return vmMrktRate;
	}

	public void setVmMrktRate(Double vmMrktRate) {
		this.vmMrktRate = vmMrktRate;
	}

	public Double getVmRetRate() {
		return vmRetRate;
	}

	public void setVmRetRate(Double vmRetRate) {
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
