package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubGeoLoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_geo_loc")
public class IclubGeoLoc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 509313853940529348L;
	private Long glId;
	private IclubPerson iclubPerson;
	private String glAddress;
	private Long glLat;
	private Long glLong;
	private Long glRate;
	private Timestamp glCrtdDt;

	// Constructors

	/** default constructor */
	public IclubGeoLoc() {
	}

	/** minimal constructor */
	public IclubGeoLoc(Long glId) {
		this.glId = glId;
	}

	/** full constructor */
	public IclubGeoLoc(Long glId, IclubPerson iclubPerson, String glAddress, Long glLat, Long glLong, Long glRate, Timestamp glCrtdDt) {
		this.glId = glId;
		this.iclubPerson = iclubPerson;
		this.glAddress = glAddress;
		this.glLat = glLat;
		this.glLong = glLong;
		this.glRate = glRate;
		this.glCrtdDt = glCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "gl_id", unique = true, nullable = false)
	public Long getGlId() {
		return this.glId;
	}

	public void setGlId(Long glId) {
		this.glId = glId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gl_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "gl_address", length = 999)
	public String getGlAddress() {
		return this.glAddress;
	}

	public void setGlAddress(String glAddress) {
		this.glAddress = glAddress;
	}

	@Column(name = "gl_lat", precision = 10, scale = 7)
	public Long getGlLat() {
		return this.glLat;
	}

	public void setGlLat(Long glLat) {
		this.glLat = glLat;
	}

	@Column(name = "gl_long", precision = 10, scale = 7)
	public Long getGlLong() {
		return this.glLong;
	}

	public void setGlLong(Long glLong) {
		this.glLong = glLong;
	}

	@Column(name = "gl_rate", precision = 15, scale = 5)
	public Long getGlRate() {
		return this.glRate;
	}

	public void setGlRate(Long glRate) {
		this.glRate = glRate;
	}

	@Column(name = "gl_crtd_dt", length = 19)
	public Timestamp getGlCrtdDt() {
		return this.glCrtdDt;
	}

	public void setGlCrtdDt(Timestamp glCrtdDt) {
		this.glCrtdDt = glCrtdDt;
	}

}