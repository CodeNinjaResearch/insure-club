package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IclubInsurerMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurer_master", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "im_name"))
public class IclubInsurerMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2765591419941938228L;
	private Long imId;
	private IclubPerson iclubPerson;
	private String imName;
	private String imTradeName;
	private String imLocation;
	private Long imLat;
	private Long imLong;
	private String imRegNum;
	private Timestamp imCrtdDt;

	// Constructors

	/** default constructor */
	public IclubInsurerMaster() {
	}

	/** minimal constructor */
	public IclubInsurerMaster(Long imId) {
		this.imId = imId;
	}

	/** full constructor */
	public IclubInsurerMaster(Long imId, IclubPerson iclubPerson,
			String imName, String imTradeName, String imLocation, Long imLat,
			Long imLong, String imRegNum, Timestamp imCrtdDt) {
		this.imId = imId;
		this.iclubPerson = iclubPerson;
		this.imName = imName;
		this.imTradeName = imTradeName;
		this.imLocation = imLocation;
		this.imLat = imLat;
		this.imLong = imLong;
		this.imRegNum = imRegNum;
		this.imCrtdDt = imCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "im_id", unique = true, nullable = false)
	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "im_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "im_name", unique = true, length = 250)
	public String getImName() {
		return this.imName;
	}

	public void setImName(String imName) {
		this.imName = imName;
	}

	@Column(name = "im_trade_name", length = 999)
	public String getImTradeName() {
		return this.imTradeName;
	}

	public void setImTradeName(String imTradeName) {
		this.imTradeName = imTradeName;
	}

	@Column(name = "im_location", length = 999)
	public String getImLocation() {
		return this.imLocation;
	}

	public void setImLocation(String imLocation) {
		this.imLocation = imLocation;
	}

	@Column(name = "im_lat", precision = 10, scale = 0)
	public Long getImLat() {
		return this.imLat;
	}

	public void setImLat(Long imLat) {
		this.imLat = imLat;
	}

	@Column(name = "im_long", precision = 10, scale = 0)
	public Long getImLong() {
		return this.imLong;
	}

	public void setImLong(Long imLong) {
		this.imLong = imLong;
	}

	@Column(name = "im_reg_num", length = 25)
	public String getImRegNum() {
		return this.imRegNum;
	}

	public void setImRegNum(String imRegNum) {
		this.imRegNum = imRegNum;
	}

	@Column(name = "im_crtd_dt", length = 19)
	public Timestamp getImCrtdDt() {
		return this.imCrtdDt;
	}

	public void setImCrtdDt(Timestamp imCrtdDt) {
		this.imCrtdDt = imCrtdDt;
	}

}