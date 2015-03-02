package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubSupplMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_suppl_master", catalog = "iclubdb")
public class IclubSupplMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -235353527920294510L;
	private String smId;
	private IclubPerson iclubPerson;
	private IclubSupplierType iclubSupplierType;
	private String smName;
	private String smTradeName;
	private String smRegNum;
	private String smAddress;
	private Long smLat;
	private Long smLong;
	private Long smCrLimit;
	private Date srActionDt;
	private Integer smRating;
	private Timestamp smCrtdDt;

	// Constructors

	/** default constructor */
	public IclubSupplMaster() {
	}

	/** minimal constructor */
	public IclubSupplMaster(String smId) {
		this.smId = smId;
	}

	/** full constructor */
	public IclubSupplMaster(String smId, IclubPerson iclubPerson,
			IclubSupplierType iclubSupplierType, String smName,
			String smTradeName, String smRegNum, String smAddress, Long smLat,
			Long smLong, Long smCrLimit, Date srActionDt, Integer smRating,
			Timestamp smCrtdDt) {
		this.smId = smId;
		this.iclubPerson = iclubPerson;
		this.iclubSupplierType = iclubSupplierType;
		this.smName = smName;
		this.smTradeName = smTradeName;
		this.smRegNum = smRegNum;
		this.smAddress = smAddress;
		this.smLat = smLat;
		this.smLong = smLong;
		this.smCrLimit = smCrLimit;
		this.srActionDt = srActionDt;
		this.smRating = smRating;
		this.smCrtdDt = smCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "sm_id", unique = true, nullable = false, length = 36)
	public String getSmId() {
		return this.smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_type_id")
	public IclubSupplierType getIclubSupplierType() {
		return this.iclubSupplierType;
	}

	public void setIclubSupplierType(IclubSupplierType iclubSupplierType) {
		this.iclubSupplierType = iclubSupplierType;
	}

	@Column(name = "sm_name", length = 999)
	public String getSmName() {
		return this.smName;
	}

	public void setSmName(String smName) {
		this.smName = smName;
	}

	@Column(name = "sm_trade_name", length = 999)
	public String getSmTradeName() {
		return this.smTradeName;
	}

	public void setSmTradeName(String smTradeName) {
		this.smTradeName = smTradeName;
	}

	@Column(name = "sm_reg_num", length = 25)
	public String getSmRegNum() {
		return this.smRegNum;
	}

	public void setSmRegNum(String smRegNum) {
		this.smRegNum = smRegNum;
	}

	@Column(name = "sm_address", length = 999)
	public String getSmAddress() {
		return this.smAddress;
	}

	public void setSmAddress(String smAddress) {
		this.smAddress = smAddress;
	}

	@Column(name = "sm_lat", precision = 10, scale = 0)
	public Long getSmLat() {
		return this.smLat;
	}

	public void setSmLat(Long smLat) {
		this.smLat = smLat;
	}

	@Column(name = "sm_long", precision = 10, scale = 0)
	public Long getSmLong() {
		return this.smLong;
	}

	public void setSmLong(Long smLong) {
		this.smLong = smLong;
	}

	@Column(name = "sm_cr_limit", precision = 10, scale = 0)
	public Long getSmCrLimit() {
		return this.smCrLimit;
	}

	public void setSmCrLimit(Long smCrLimit) {
		this.smCrLimit = smCrLimit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sr_action_dt", length = 10)
	public Date getSrActionDt() {
		return this.srActionDt;
	}

	public void setSrActionDt(Date srActionDt) {
		this.srActionDt = srActionDt;
	}

	@Column(name = "sm_rating")
	public Integer getSmRating() {
		return this.smRating;
	}

	public void setSmRating(Integer smRating) {
		this.smRating = smRating;
	}

	@Column(name = "sm_crtd_dt", length = 19)
	public Timestamp getSmCrtdDt() {
		return this.smCrtdDt;
	}

	public void setSmCrtdDt(Timestamp smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

}