package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubGeoLocModel")
public class IclubGeoLocModel {

	private Long glId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String glProvince;
	private String glSuburb;
	private String glAddress;
	private Double glLat;
	private Double glLong;
	private Date glCrtdDt;
	private Double glRate;

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

	public String getGlProvince() {
		return glProvince;
	}

	public void setGlProvince(String glProvince) {
		this.glProvince = glProvince;
	}

	public String getGlSuburb() {
		return glSuburb;
	}

	public void setGlSuburb(String glSuburb) {
		this.glSuburb = glSuburb;
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

	public Date getGlCrtdDt() {
		return glCrtdDt;
	}

	public void setGlCrtdDt(Date glCrtdDt) {
		this.glCrtdDt = glCrtdDt;
	}

	public Double getGlRate() {
		return glRate;
	}

	public void setGlRate(Double glRate) {
		this.glRate = glRate;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
