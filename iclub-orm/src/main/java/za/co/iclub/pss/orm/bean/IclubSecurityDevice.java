package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubSecurityDevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_security_device")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_security_device where sd_crtd_by=:id", name = "getSecurityDeviceByUser", resultClass = IclubSecurityDevice.class) })
public class IclubSecurityDevice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1697585544672829159L;
	private String sdId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private IclubTrackerMaster iclubTrackerMaster;
	private String sdItemId;
	private String sdSerNum;
	private String sdContractNum;
	private Timestamp sdCrtdDt;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubSecurityDevice() {
	}

	/** minimal constructor */
	public IclubSecurityDevice(String sdId) {
		this.sdId = sdId;
	}

	/** full constructor */
	public IclubSecurityDevice(String sdId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPerson, IclubTrackerMaster iclubTrackerMaster, String sdItemId, String sdSerNum, String sdContractNum, Timestamp sdCrtdDt, Set<IclubVehicle> iclubVehicles) {
		this.sdId = sdId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.iclubTrackerMaster = iclubTrackerMaster;
		this.sdItemId = sdItemId;
		this.sdSerNum = sdSerNum;
		this.sdContractNum = sdContractNum;
		this.sdCrtdDt = sdCrtdDt;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "sd_id", unique = true, nullable = false, length = 36)
	public String getSdId() {
		return this.sdId;
	}

	public void setSdId(String sdId) {
		this.sdId = sdId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sd_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sd_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sd_tracker_id")
	public IclubTrackerMaster getIclubTrackerMaster() {
		return this.iclubTrackerMaster;
	}

	public void setIclubTrackerMaster(IclubTrackerMaster iclubTrackerMaster) {
		this.iclubTrackerMaster = iclubTrackerMaster;
	}

	@Column(name = "sd_item_id", length = 36)
	public String getSdItemId() {
		return this.sdItemId;
	}

	public void setSdItemId(String sdItemId) {
		this.sdItemId = sdItemId;
	}

	@Column(name = "sd_ser_num", length = 50)
	public String getSdSerNum() {
		return this.sdSerNum;
	}

	public void setSdSerNum(String sdSerNum) {
		this.sdSerNum = sdSerNum;
	}

	@Column(name = "sd_contract_num", length = 50)
	public String getSdContractNum() {
		return this.sdContractNum;
	}

	public void setSdContractNum(String sdContractNum) {
		this.sdContractNum = sdContractNum;
	}

	@Column(name = "sd_crtd_dt", length = 19)
	public Timestamp getSdCrtdDt() {
		return this.sdCrtdDt;
	}

	public void setSdCrtdDt(Timestamp sdCrtdDt) {
		this.sdCrtdDt = sdCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSecurityDevice")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}