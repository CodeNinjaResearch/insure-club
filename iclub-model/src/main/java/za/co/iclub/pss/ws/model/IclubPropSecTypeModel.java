package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropSecTypeModel")
public class IclubPropSecTypeModel {
	
	private Long pstId;
	private String pstShortDesc;
	private String pstLongDesc;
	private String pstStatus;
	private String[] iclubProperties;
	
	public Long getPstId() {
		return pstId;
	}
	
	public void setPstId(Long pstId) {
		this.pstId = pstId;
	}
	
	public String getPstShortDesc() {
		return pstShortDesc;
	}
	
	public void setPstShortDesc(String pstShortDesc) {
		this.pstShortDesc = pstShortDesc;
	}
	
	public String getPstLongDesc() {
		return pstLongDesc;
	}
	
	public void setPstLongDesc(String pstLongDesc) {
		this.pstLongDesc = pstLongDesc;
	}
	
	public String getPstStatus() {
		return pstStatus;
	}
	
	public void setPstStatus(String pstStatus) {
		this.pstStatus = pstStatus;
	}
	
	public String[] getIclubProperties() {
		return iclubProperties;
	}
	
	public void setIclubProperties(String[] iclubProperties) {
		this.iclubProperties = iclubProperties;
	}
	
}
