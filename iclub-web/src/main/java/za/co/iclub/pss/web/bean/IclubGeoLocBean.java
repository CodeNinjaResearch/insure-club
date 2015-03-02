package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubGeoLocBean {

	private Long glId;
	private Long iclubPerson;
	private String glAddress;
	private Long glLat;
	private Long glLong;
	private Long glRate;
	private Timestamp glCrtdDt;

	public Long getGlId() {
		return glId;
	}

	public void setGlId(Long glId) {
		this.glId = glId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
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

}
