package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubInsurerMasterModel")
public class IclubInsurerMasterModel {
	
	private Long imId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String imName;
	private String imTradeName;
	private String imLocation;
	private Double imLat;
	private Double imLong;
	private String imRegNum;
	private Date imCrtdDt;
	
	public Long getImId() {
		return imId;
	}
	
	public void setImId(Long imId) {
		this.imId = imId;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getImName() {
		return imName;
	}
	
	public void setImName(String imName) {
		this.imName = imName;
	}
	
	public String getImTradeName() {
		return imTradeName;
	}
	
	public void setImTradeName(String imTradeName) {
		this.imTradeName = imTradeName;
	}
	
	public String getImLocation() {
		return imLocation;
	}
	
	public void setImLocation(String imLocation) {
		this.imLocation = imLocation;
	}
	
	public Double getImLat() {
		return imLat;
	}
	
	public void setImLat(Double imLat) {
		this.imLat = imLat;
	}
	
	public Double getImLong() {
		return imLong;
	}
	
	public void setImLong(Double imLong) {
		this.imLong = imLong;
	}
	
	public String getImRegNum() {
		return imRegNum;
	}
	
	public void setImRegNum(String imRegNum) {
		this.imRegNum = imRegNum;
	}
	
	public Date getImCrtdDt() {
		return imCrtdDt;
	}
	
	public void setImCrtdDt(Date imCrtdDt) {
		this.imCrtdDt = imCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
}
