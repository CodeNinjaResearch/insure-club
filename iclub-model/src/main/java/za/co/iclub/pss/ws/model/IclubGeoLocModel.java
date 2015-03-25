package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubGeoLocModel")
public class IclubGeoLocModel {

	private Long glId;
	private String iclubPerson;
	private String glAddress;
	private Long glLat;
	private Long glLong;
	private Long glRate;
	private Timestamp glCrtdDt;
	private String glKey; 

	public Long getGlId() {
		return glId;
	}

	public void setGlId(Long glId) {
		this.glId = glId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getGlAddress() {
		return glAddress;
	}

	public void setGlAddress(String glAddress) {
		this.glAddress = glAddress;
	}

	public Long getGlLat() {
		return glLat;
	}

	public void setGlLat(Long glLat) {
		this.glLat = glLat;
	}

	public Long getGlLong() {
		return glLong;
	}

	public void setGlLong(Long glLong) {
		this.glLong = glLong;
	}

	public Long getGlRate() {
		return glRate;
	}

	public void setGlRate(Long glRate) {
		this.glRate = glRate;
	}

	public Timestamp getGlCrtdDt() {
		return glCrtdDt;
	}

	public void setGlCrtdDt(Timestamp glCrtdDt) {
		this.glCrtdDt = glCrtdDt;
	}

	public String getGlKey() {
		return glKey;
	}

	public void setGlKey(String glKey) {
		this.glKey = glKey;
	}

}
