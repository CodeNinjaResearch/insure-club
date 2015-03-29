package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubGeoLoc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_geo_loc")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_geo_loc where gl_crtd_by=:id", name = "getGeoLocByUser", resultClass = IclubGeoLoc.class) })
public class IclubGeoLoc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5619528734859414363L;
	private Long glId;
	private IclubPerson iclubPerson;
	private String glKey;
	private String glAddress;
	private Double glLat;
	private Double glLong;
	private Double glRate;
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
	public IclubGeoLoc(Long glId, IclubPerson iclubPerson, String glKey, String glAddress, Double glLat, Double glLong, Double glRate, Timestamp glCrtdDt) {
		this.glId = glId;
		this.iclubPerson = iclubPerson;
		this.glKey = glKey;
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

	@Column(name = "gl_key", length = 450)
	public String getGlKey() {
		return this.glKey;
	}

	public void setGlKey(String glKey) {
		this.glKey = glKey;
	}

	@Column(name = "gl_address", length = 999)
	public String getGlAddress() {
		return this.glAddress;
	}

	public void setGlAddress(String glAddress) {
		this.glAddress = glAddress;
	}

	@Column(name = "gl_lat", precision = 10, scale = 7)
	public Double getGlLat() {
		return this.glLat;
	}

	public void setGlLat(Double glLat) {
		this.glLat = glLat;
	}

	@Column(name = "gl_long", precision = 10, scale = 7)
	public Double getGlLong() {
		return this.glLong;
	}

	public void setGlLong(Double glLong) {
		this.glLong = glLong;
	}

	@Column(name = "gl_rate", precision = 15, scale = 5)
	public Double getGlRate() {
		return this.glRate;
	}

	public void setGlRate(Double glRate) {
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