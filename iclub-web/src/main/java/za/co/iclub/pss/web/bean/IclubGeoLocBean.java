package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubGeoLocBean {

	private Long glId;
	private String iclubPerson;
	private String glAddress;
	private Long glLat;
	private Long glLong;
	private Long glRate;
	private Date glCrtdDt;
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

	public Date getGlCrtdDt() {
		return glCrtdDt;
	}

	public void setGlCrtdDt(Date glCrtdDt) {
		this.glCrtdDt = glCrtdDt;
	}

	public String getGlKey() {
		return glKey;
	}

	public void setGlKey(String glKey) {
		this.glKey = glKey;
	}

}
