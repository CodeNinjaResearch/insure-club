package za.co.iclub.pss.web.bean;

public class IclubVehUsageTypeBean {
	
	private Long vuId;
	private String vuShortDesc;
	private String vuLongDesc;
	private String vuStatus;
	private String[] iclubVehicles;
	
	public Long getVuId() {
		return vuId;
	}
	
	public void setVuId(Long vuId) {
		this.vuId = vuId;
	}
	
	public String getVuShortDesc() {
		return vuShortDesc;
	}
	
	public void setVuShortDesc(String vuShortDesc) {
		this.vuShortDesc = vuShortDesc;
	}
	
	public String getVuLongDesc() {
		return vuLongDesc;
	}
	
	public void setVuLongDesc(String vuLongDesc) {
		this.vuLongDesc = vuLongDesc;
	}
	
	public String getVuStatus() {
		return vuStatus;
	}
	
	public void setVuStatus(String vuStatus) {
		this.vuStatus = vuStatus;
	}
	
	public String[] getIclubVehicles() {
		return iclubVehicles;
	}
	
	public void setIclubVehicles(String[] iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}
	
}
