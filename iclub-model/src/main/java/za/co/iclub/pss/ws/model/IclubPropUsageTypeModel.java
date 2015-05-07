package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropUsageTypeModel")
public class IclubPropUsageTypeModel {
	
	private Long puId;
	private String puShortDesc;
	private String puLongDesc;
	private String puStatus;
	private String[] iclubProperties;
	
	public Long getPuId() {
		return puId;
	}
	
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	
	public String getPuShortDesc() {
		return puShortDesc;
	}
	
	public void setPuShortDesc(String puShortDesc) {
		this.puShortDesc = puShortDesc;
	}
	
	public String getPuLongDesc() {
		return puLongDesc;
	}
	
	public void setPuLongDesc(String puLongDesc) {
		this.puLongDesc = puLongDesc;
	}
	
	public String getPuStatus() {
		return puStatus;
	}
	
	public void setPuStatus(String puStatus) {
		this.puStatus = puStatus;
	}
	
	public String[] getIclubProperties() {
		return iclubProperties;
	}
	
	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}
	
}
