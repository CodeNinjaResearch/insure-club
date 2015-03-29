package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubGeoLocBean {

	private Long glId;
	private String iclubPerson;
	private String glAddress;
	private Double glLat;
	private Double glLong;
	private Double glRate;
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

	public Double getGlLat() {
		return glLat;
	}

	public void setGlLat(Double glLat) {
		this.glLat = glLat;
	}

	public Double getGlLong() {
		return glLong;
	}

	public void setGlLong(Double glLong) {
		this.glLong = glLong;
	}

	public Double getGlRate() {
		return glRate;
	}

	public void setGlRate(Double glRate) {
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
