package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubInsuranceItemTypeModel")
public class IclubInsuranceItemTypeModel {
	
	private Long iitId;
	private String iitShortDesc;
	private String iitLongDesc;
	private String iitStatus;
	private Long[] iclubRateTypes;
	private String[] iclubSecurityDevices;
	private Long[] iclubPurposeTypes;
	private String[] iclubRateEngines;
	private String[] iclubInsuranceItems;
	private String[] iclubInsuranceItems_1;
	private Long[] iclubCoverTypes;
	
	public Long getIitId() {
		return iitId;
	}
	
	public void setIitId(Long iitId) {
		this.iitId = iitId;
	}
	
	public String getIitShortDesc() {
		return iitShortDesc;
	}
	
	public void setIitShortDesc(String iitShortDesc) {
		this.iitShortDesc = iitShortDesc;
	}
	
	public String getIitLongDesc() {
		return iitLongDesc;
	}
	
	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}
	
	public String getIitStatus() {
		return iitStatus;
	}
	
	public void setIitStatus(String iitStatus) {
		this.iitStatus = iitStatus;
	}
	
	public Long[] getIclubRateTypes() {
		return iclubRateTypes;
	}
	
	public void setIclubRateTypes(Long[] iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}
	
	public String[] getIclubSecurityDevices() {
		return iclubSecurityDevices;
	}
	
	public void setIclubSecurityDevices(String[] iclubSecurityDevices) {
		this.iclubSecurityDevices = iclubSecurityDevices;
	}
	
	public Long[] getIclubPurposeTypes() {
		return iclubPurposeTypes;
	}
	
	public void setIclubPurposeTypes(Long[] iclubPurposeTypes) {
		this.iclubPurposeTypes = iclubPurposeTypes;
	}
	
	public String[] getIclubRateEngines() {
		return iclubRateEngines;
	}
	
	public void setIclubRateEngines(String[] iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}
	
	public String[] getIclubInsuranceItems() {
		return iclubInsuranceItems;
	}
	
	public void setIclubInsuranceItems(String[] iclubInsuranceItems) {
		this.iclubInsuranceItems = iclubInsuranceItems;
	}
	
	public String[] getIclubInsuranceItems_1() {
		return iclubInsuranceItems_1;
	}
	
	public void setIclubInsuranceItems_1(String[] iclubInsuranceItems_1) {
		this.iclubInsuranceItems_1 = iclubInsuranceItems_1;
	}
	
	public Long[] getIclubCoverTypes() {
		return iclubCoverTypes;
	}
	
	public void setIclubCoverTypes(Long[] iclubCoverTypes) {
		this.iclubCoverTypes = iclubCoverTypes;
	}
	
}
