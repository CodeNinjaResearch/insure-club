package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubSupplMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_suppl_master")
public class IclubSupplMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 178164329861311959L;
	private String smId;
	private IclubPerson iclubPerson;
	private IclubSupplierType iclubSupplierType;
	private String smName;
	private String smTradeName;
	private String smRegNum;
	private String smAddress;
	private Double smLat;
	private Double smLong;
	private Double smCrLimit;
	private Date srActionDt;
	private Integer smRating;
	private Timestamp smCrtdDt;
	private Set<IclubClaimItem> iclubClaimItemsForCiAssesorId = new HashSet<IclubClaimItem>(0);
	private Set<IclubClaimItem> iclubClaimItemsForCiHandlerId = new HashSet<IclubClaimItem>(0);

	// Constructors

	/** default constructor */
	public IclubSupplMaster() {
	}

	/** minimal constructor */
	public IclubSupplMaster(String smId) {
		this.smId = smId;
	}

	/** full constructor */
	public IclubSupplMaster(String smId, IclubPerson iclubPerson, IclubSupplierType iclubSupplierType, String smName, String smTradeName, String smRegNum, String smAddress, Double smLat, Double smLong, Double smCrLimit, Date srActionDt, Integer smRating, Timestamp smCrtdDt, Set<IclubClaimItem> iclubClaimItemsForCiAssesorId, Set<IclubClaimItem> iclubClaimItemsForCiHandlerId) {
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
		this.iclubClaimItemsForCiAssesorId = iclubClaimItemsForCiAssesorId;
		this.iclubClaimItemsForCiHandlerId = iclubClaimItemsForCiHandlerId;
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

	@Column(name = "sm_lat", precision = 10, scale = 7)
	public Double getSmLat() {
		return this.smLat;
	}

	public void setSmLat(Double smLat) {
		this.smLat = smLat;
	}

	@Column(name = "sm_long", precision = 10, scale = 7)
	public Double getSmLong() {
		return this.smLong;
	}

	public void setSmLong(Double smLong) {
		this.smLong = smLong;
	}

	@Column(name = "sm_cr_limit", precision = 15, scale = 5)
	public Double getSmCrLimit() {
		return this.smCrLimit;
	}

	public void setSmCrLimit(Double smCrLimit) {
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSupplMasterByCiAssesorId")
	public Set<IclubClaimItem> getIclubClaimItemsForCiAssesorId() {
		return this.iclubClaimItemsForCiAssesorId;
	}

	public void setIclubClaimItemsForCiAssesorId(Set<IclubClaimItem> iclubClaimItemsForCiAssesorId) {
		this.iclubClaimItemsForCiAssesorId = iclubClaimItemsForCiAssesorId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSupplMasterByCiHandlerId")
	public Set<IclubClaimItem> getIclubClaimItemsForCiHandlerId() {
		return this.iclubClaimItemsForCiHandlerId;
	}

	public void setIclubClaimItemsForCiHandlerId(Set<IclubClaimItem> iclubClaimItemsForCiHandlerId) {
		this.iclubClaimItemsForCiHandlerId = iclubClaimItemsForCiHandlerId;
	}

}