package za.co.iclub.pss.orm.bean;

import java.util.Date;

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
@Table(name = "iclub_geo_loc", catalog = "iclubdb")
public class IclubGeoLoc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7975855546692116552L;
	private Long glId;
	private IclubPerson iclubPerson;
	private String glProvince;
	private String glSuburb;
	private String glAddress;
	private Double glLat;
	private Double glLong;
	private Date glCrtdDt;
	private Double glRate;

	// Constructors

	/** default constructor */
	public IclubGeoLoc() {
	}

	/** minimal constructor */
	public IclubGeoLoc(Long glId) {
		this.glId = glId;
	}

	/** full constructor */
	public IclubGeoLoc(Long glId, IclubPerson iclubPerson, String glProvince, String glSuburb, String glAddress, Double glLat, Double glLong, Date glCrtdDt, Double glRate) {
		this.glId = glId;
		this.iclubPerson = iclubPerson;
		this.glProvince = glProvince;
		this.glSuburb = glSuburb;
		this.glAddress = glAddress;
		this.glLat = glLat;
		this.glLong = glLong;
		this.glCrtdDt = glCrtdDt;
		this.glRate = glRate;
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

	@Column(name = "gl_province", length = 450)
	public String getGlProvince() {
		return this.glProvince;
	}

	public void setGlProvince(String glProvince) {
		this.glProvince = glProvince;
	}

	@Column(name = "gl_suburb", length = 999)
	public String getGlSuburb() {
		return this.glSuburb;
	}

	public void setGlSuburb(String glSuburb) {
		this.glSuburb = glSuburb;
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

	@Column(name = "gl_crtd_dt", length = 19)
	public Date getGlCrtdDt() {
		return this.glCrtdDt;
	}

	public void setGlCrtdDt(Date glCrtdDt) {
		this.glCrtdDt = glCrtdDt;
	}

	@Column(name = "gl_rate", precision = 22, scale = 0)
	public Double getGlRate() {
		return this.glRate;
	}

	public void setGlRate(Double glRate) {
		this.glRate = glRate;
	}

}