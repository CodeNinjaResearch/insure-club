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
 * IclubTrackerMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_tracker_master", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "tm_name"))
public class IclubTrackerMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3932954844072561271L;
	private Long tmId;
	private IclubPerson iclubPerson;
	private String tmName;
	private String tmTradeName;
	private String tmLocation;
	private Long tmLat;
	private Long tmLong;
	private String tmRegNum;
	private Timestamp tmCrtdDt;

	// Constructors

	/** default constructor */
	public IclubTrackerMaster() {
	}

	/** minimal constructor */
	public IclubTrackerMaster(Long tmId) {
		this.tmId = tmId;
	}

	/** full constructor */
	public IclubTrackerMaster(Long tmId, IclubPerson iclubPerson,
			String tmName, String tmTradeName, String tmLocation, Long tmLat,
			Long tmLong, String tmRegNum, Timestamp tmCrtdDt) {
		this.tmId = tmId;
		this.iclubPerson = iclubPerson;
		this.tmName = tmName;
		this.tmTradeName = tmTradeName;
		this.tmLocation = tmLocation;
		this.tmLat = tmLat;
		this.tmLong = tmLong;
		this.tmRegNum = tmRegNum;
		this.tmCrtdDt = tmCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "tm_id", unique = true, nullable = false)
	public Long getTmId() {
		return this.tmId;
	}

	public void setTmId(Long tmId) {
		this.tmId = tmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "tm_name", unique = true, length = 250)
	public String getTmName() {
		return this.tmName;
	}

	public void setTmName(String tmName) {
		this.tmName = tmName;
	}

	@Column(name = "tm_trade_name", length = 999)
	public String getTmTradeName() {
		return this.tmTradeName;
	}

	public void setTmTradeName(String tmTradeName) {
		this.tmTradeName = tmTradeName;
	}

	@Column(name = "tm_location", length = 999)
	public String getTmLocation() {
		return this.tmLocation;
	}

	public void setTmLocation(String tmLocation) {
		this.tmLocation = tmLocation;
	}

	@Column(name = "tm_lat", precision = 10, scale = 0)
	public Long getTmLat() {
		return this.tmLat;
	}

	public void setTmLat(Long tmLat) {
		this.tmLat = tmLat;
	}

	@Column(name = "tm_long", precision = 10, scale = 0)
	public Long getTmLong() {
		return this.tmLong;
	}

	public void setTmLong(Long tmLong) {
		this.tmLong = tmLong;
	}

	@Column(name = "tm_reg_num", length = 25)
	public String getTmRegNum() {
		return this.tmRegNum;
	}

	public void setTmRegNum(String tmRegNum) {
		this.tmRegNum = tmRegNum;
	}

	@Column(name = "tm_crtd_dt", length = 19)
	public Timestamp getTmCrtdDt() {
		return this.tmCrtdDt;
	}

	public void setTmCrtdDt(Timestamp tmCrtdDt) {
		this.tmCrtdDt = tmCrtdDt;
	}

}