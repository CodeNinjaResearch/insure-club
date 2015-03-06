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
 * IclubProperty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_property")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_property where p_crtd_by=:id", name = "getPropertyByUser", resultClass = IclubProperty.class) })
public class IclubProperty implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2541992080633137713L;
	private String PId;
	private IclubCoverType iclubCoverType;
	private IclubPurposeType iclubPurposeType;
	private IclubOccupiedStatus iclubOccupiedStatus;
	private IclubPropertyType iclubPropertyType;
	private IclubWallType iclubWallType;
	private IclubAccessType iclubAccessType;
	private IclubPerson iclubPerson;
	private IclubBarType iclubBarType;
	private IclubThatchType iclubThatchType;
	private IclubRoofType iclubRoofType;
	private String PRegNum;
	private String PAddress;
	private Long PLat;
	private Long PLong;
	private Integer PPostalCd;
	private Integer PNoclaimYrs;
	private String PRentFurYn;
	private String PCompYn;
	private String PNorobberyYn;
	private String PSecGatesYn;
	private Long PEstValue;
	private Timestamp PCrtdDt;

	// Constructors

	/** default constructor */
	public IclubProperty() {
	}

	/** minimal constructor */
	public IclubProperty(String PId) {
		this.PId = PId;
	}

	/** full constructor */
	public IclubProperty(String PId, IclubCoverType iclubCoverType, IclubPurposeType iclubPurposeType, IclubOccupiedStatus iclubOccupiedStatus, IclubPropertyType iclubPropertyType, IclubWallType iclubWallType, IclubAccessType iclubAccessType, IclubPerson iclubPerson, IclubBarType iclubBarType, IclubThatchType iclubThatchType, IclubRoofType iclubRoofType, String PRegNum, String PAddress, Long PLat, Long PLong, Integer PPostalCd, Integer PNoclaimYrs, String PRentFurYn, String PCompYn, String PNorobberyYn, String PSecGatesYn, Long PEstValue, Timestamp PCrtdDt) {
		this.PId = PId;
		this.iclubCoverType = iclubCoverType;
		this.iclubPurposeType = iclubPurposeType;
		this.iclubOccupiedStatus = iclubOccupiedStatus;
		this.iclubPropertyType = iclubPropertyType;
		this.iclubWallType = iclubWallType;
		this.iclubAccessType = iclubAccessType;
		this.iclubPerson = iclubPerson;
		this.iclubBarType = iclubBarType;
		this.iclubThatchType = iclubThatchType;
		this.iclubRoofType = iclubRoofType;
		this.PRegNum = PRegNum;
		this.PAddress = PAddress;
		this.PLat = PLat;
		this.PLong = PLong;
		this.PPostalCd = PPostalCd;
		this.PNoclaimYrs = PNoclaimYrs;
		this.PRentFurYn = PRentFurYn;
		this.PCompYn = PCompYn;
		this.PNorobberyYn = PNorobberyYn;
		this.PSecGatesYn = PSecGatesYn;
		this.PEstValue = PEstValue;
		this.PCrtdDt = PCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "p_id", unique = true, nullable = false, length = 36)
	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_cover_type_id")
	public IclubCoverType getIclubCoverType() {
		return this.iclubCoverType;
	}

	public void setIclubCoverType(IclubCoverType iclubCoverType) {
		this.iclubCoverType = iclubCoverType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_purpose_type_id")
	public IclubPurposeType getIclubPurposeType() {
		return this.iclubPurposeType;
	}

	public void setIclubPurposeType(IclubPurposeType iclubPurposeType) {
		this.iclubPurposeType = iclubPurposeType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_occupied_status_id")
	public IclubOccupiedStatus getIclubOccupiedStatus() {
		return this.iclubOccupiedStatus;
	}

	public void setIclubOccupiedStatus(IclubOccupiedStatus iclubOccupiedStatus) {
		this.iclubOccupiedStatus = iclubOccupiedStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_type_id")
	public IclubPropertyType getIclubPropertyType() {
		return this.iclubPropertyType;
	}

	public void setIclubPropertyType(IclubPropertyType iclubPropertyType) {
		this.iclubPropertyType = iclubPropertyType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_wall_type_id")
	public IclubWallType getIclubWallType() {
		return this.iclubWallType;
	}

	public void setIclubWallType(IclubWallType iclubWallType) {
		this.iclubWallType = iclubWallType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_access_type_id")
	public IclubAccessType getIclubAccessType() {
		return this.iclubAccessType;
	}

	public void setIclubAccessType(IclubAccessType iclubAccessType) {
		this.iclubAccessType = iclubAccessType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_bar_type_id")
	public IclubBarType getIclubBarType() {
		return this.iclubBarType;
	}

	public void setIclubBarType(IclubBarType iclubBarType) {
		this.iclubBarType = iclubBarType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_thatch_type_id")
	public IclubThatchType getIclubThatchType() {
		return this.iclubThatchType;
	}

	public void setIclubThatchType(IclubThatchType iclubThatchType) {
		this.iclubThatchType = iclubThatchType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_roof_type_id")
	public IclubRoofType getIclubRoofType() {
		return this.iclubRoofType;
	}

	public void setIclubRoofType(IclubRoofType iclubRoofType) {
		this.iclubRoofType = iclubRoofType;
	}

	@Column(name = "p_reg_num", length = 50)
	public String getPRegNum() {
		return this.PRegNum;
	}

	public void setPRegNum(String PRegNum) {
		this.PRegNum = PRegNum;
	}

	@Column(name = "p_address", length = 999)
	public String getPAddress() {
		return this.PAddress;
	}

	public void setPAddress(String PAddress) {
		this.PAddress = PAddress;
	}

	@Column(name = "p_lat", precision = 10, scale = 7)
	public Long getPLat() {
		return this.PLat;
	}

	public void setPLat(Long PLat) {
		this.PLat = PLat;
	}

	@Column(name = "p_long", precision = 10, scale = 7)
	public Long getPLong() {
		return this.PLong;
	}

	public void setPLong(Long PLong) {
		this.PLong = PLong;
	}

	@Column(name = "p_postal_cd")
	public Integer getPPostalCd() {
		return this.PPostalCd;
	}

	public void setPPostalCd(Integer PPostalCd) {
		this.PPostalCd = PPostalCd;
	}

	@Column(name = "p_noclaim_yrs")
	public Integer getPNoclaimYrs() {
		return this.PNoclaimYrs;
	}

	public void setPNoclaimYrs(Integer PNoclaimYrs) {
		this.PNoclaimYrs = PNoclaimYrs;
	}

	@Column(name = "p_rent_fur_yn", length = 1)
	public String getPRentFurYn() {
		return this.PRentFurYn;
	}

	public void setPRentFurYn(String PRentFurYn) {
		this.PRentFurYn = PRentFurYn;
	}

	@Column(name = "p_comp_yn", length = 1)
	public String getPCompYn() {
		return this.PCompYn;
	}

	public void setPCompYn(String PCompYn) {
		this.PCompYn = PCompYn;
	}

	@Column(name = "p_norobbery_yn", length = 1)
	public String getPNorobberyYn() {
		return this.PNorobberyYn;
	}

	public void setPNorobberyYn(String PNorobberyYn) {
		this.PNorobberyYn = PNorobberyYn;
	}

	@Column(name = "p_sec_gates_yn", length = 1)
	public String getPSecGatesYn() {
		return this.PSecGatesYn;
	}

	public void setPSecGatesYn(String PSecGatesYn) {
		this.PSecGatesYn = PSecGatesYn;
	}

	@Column(name = "p_est_value", precision = 15, scale = 5)
	public Long getPEstValue() {
		return this.PEstValue;
	}

	public void setPEstValue(Long PEstValue) {
		this.PEstValue = PEstValue;
	}

	@Column(name = "p_crtd_dt", length = 19)
	public Timestamp getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(Timestamp PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

}