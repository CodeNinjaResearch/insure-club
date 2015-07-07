package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubBarTypeModel")
public class IclubBarTypeModel {
	
	private Long btId;
	private String btShortDesc;
	private String btLongDesc;
	private String btStatus;
	
	public Long getBtId() {
		return btId;
	}
	
	public void setBtId(Long btId) {
		this.btId = btId;
	}
	
	public String getBtShortDesc() {
		return btShortDesc;
	}
	
	public void setBtShortDesc(String btShortDesc) {
		this.btShortDesc = btShortDesc;
	}
	
	public String getBtLongDesc() {
		return btLongDesc;
	}
	
	public void setBtLongDesc(String btLongDesc) {
		this.btLongDesc = btLongDesc;
	}
	
	public String getBtStatus() {
		return btStatus;
	}
	
	public void setBtStatus(String btStatus) {
		this.btStatus = btStatus;
	}
	
}
