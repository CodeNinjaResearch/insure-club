package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubInsurerMasterModel")
public class IclubInsurerMasterModel {

	private Long imId;
	private String iclubPerson;
	private String imName;
	private String imTradeName;
	private String imLocation;
	private Long imLat;
	private Long imLong;
	private String imRegNum;
	private Timestamp imCrtdDt;
	private String[] iclubQuotes;

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

	public Long getImLat() {
		return imLat;
	}

	public void setImLat(Long imLat) {
		this.imLat = imLat;
	}

	public Long getImLong() {
		return imLong;
	}

	public void setImLong(Long imLong) {
		this.imLong = imLong;
	}

	public String getImRegNum() {
		return imRegNum;
	}

	public void setImRegNum(String imRegNum) {
		this.imRegNum = imRegNum;
	}

	public Timestamp getImCrtdDt() {
		return imCrtdDt;
	}

	public void setImCrtdDt(Timestamp imCrtdDt) {
		this.imCrtdDt = imCrtdDt;
	}

	public String[] getIclubQuotes() {
		return iclubQuotes;
	}

	public void setIclubQuotes(String[] iclubQuotes) {
		this.iclubQuotes = iclubQuotes;
	}

}
