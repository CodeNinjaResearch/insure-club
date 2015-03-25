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
 * IclubVehicle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_vehicle")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_vehicle where v_driver_id=:driverId", name = "getVehicleByDriverId", resultClass = IclubVehicle.class), })
public class IclubVehicle implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -9151297193348935652L;
	private String VId;
	private IclubPurposeType iclubPurposeType;
	private IclubSecurityMaster iclubSecurityMaster;
	private IclubAccessType iclubAccessTypeByVOnAccessTypeId;
	private IclubAccessType iclubAccessTypeByVDdAccessTypeId;
	private IclubSecurityDevice iclubSecurityDevice;
	private IclubVehicleMaster iclubVehicleMaster;
	private IclubPerson iclubPerson;
	private IclubDriver iclubDriver;
	private Long VOdometer;
	private String VOnArea;
	private Double VOnLat;
	private Double VOnLong;
	private String VDdArea;
	private Double VDdLat;
	private Double VDdLong;
	private Integer VYear;
	private Long VInsuredValue;
	private Long VConcessPrct;
	private String VConcessReason;
	private String VImmYn;
	private String VGearLockYn;
	private String VOwner;
	private Integer VNoclaimYrs;
	private Integer VCompYrs;
	private String VVin;
	private String VEngineNr;
	private String VRegNum;
	private Timestamp VCrtdDt;

	// Constructors

	/** default constructor */
	public IclubVehicle() {
	}

	/** minimal constructor */
	public IclubVehicle(String VId) {
		this.VId = VId;
	}

	/** full constructor */
	public IclubVehicle(String VId, IclubPurposeType iclubPurposeType, IclubSecurityMaster iclubSecurityMaster, IclubAccessType iclubAccessTypeByVOnAccessTypeId, IclubAccessType iclubAccessTypeByVDdAccessTypeId, IclubSecurityDevice iclubSecurityDevice, IclubVehicleMaster iclubVehicleMaster, IclubPerson iclubPerson, IclubDriver iclubDriver, Long VOdometer, String VOnArea, Double VOnLat, Double VOnLong, String VDdArea, Double VDdLat, Double VDdLong, Integer VYear, Long VInsuredValue, Long VConcessPrct, String VConcessReason, String VImmYn, String VGearLockYn, String VOwner, Integer VNoclaimYrs, Integer VCompYrs, String VVin, String VEngineNr, String VRegNum, Timestamp VCrtdDt) {
		this.VId = VId;
		this.iclubPurposeType = iclubPurposeType;
		this.iclubSecurityMaster = iclubSecurityMaster;
		this.iclubAccessTypeByVOnAccessTypeId = iclubAccessTypeByVOnAccessTypeId;
		this.iclubAccessTypeByVDdAccessTypeId = iclubAccessTypeByVDdAccessTypeId;
		this.iclubSecurityDevice = iclubSecurityDevice;
		this.iclubVehicleMaster = iclubVehicleMaster;
		this.iclubPerson = iclubPerson;
		this.iclubDriver = iclubDriver;
		this.VOdometer = VOdometer;
		this.VOnArea = VOnArea;
		this.VOnLat = VOnLat;
		this.VOnLong = VOnLong;
		this.VDdArea = VDdArea;
		this.VDdLat = VDdLat;
		this.VDdLong = VDdLong;
		this.VYear = VYear;
		this.VInsuredValue = VInsuredValue;
		this.VConcessPrct = VConcessPrct;
		this.VConcessReason = VConcessReason;
		this.VImmYn = VImmYn;
		this.VGearLockYn = VGearLockYn;
		this.VOwner = VOwner;
		this.VNoclaimYrs = VNoclaimYrs;
		this.VCompYrs = VCompYrs;
		this.VVin = VVin;
		this.VEngineNr = VEngineNr;
		this.VRegNum = VRegNum;
		this.VCrtdDt = VCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "v_id", unique = true, nullable = false, length = 36)
	public String getVId() {
		return this.VId;
	}

	public void setVId(String VId) {
		this.VId = VId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_purpose_type_id")
	public IclubPurposeType getIclubPurposeType() {
		return this.iclubPurposeType;
	}

	public void setIclubPurposeType(IclubPurposeType iclubPurposeType) {
		this.iclubPurposeType = iclubPurposeType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_sec_mas_id")
	public IclubSecurityMaster getIclubSecurityMaster() {
		return this.iclubSecurityMaster;
	}

	public void setIclubSecurityMaster(IclubSecurityMaster iclubSecurityMaster) {
		this.iclubSecurityMaster = iclubSecurityMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_on_access_type_id")
	public IclubAccessType getIclubAccessTypeByVOnAccessTypeId() {
		return this.iclubAccessTypeByVOnAccessTypeId;
	}

	public void setIclubAccessTypeByVOnAccessTypeId(IclubAccessType iclubAccessTypeByVOnAccessTypeId) {
		this.iclubAccessTypeByVOnAccessTypeId = iclubAccessTypeByVOnAccessTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_dd_access_type_id")
	public IclubAccessType getIclubAccessTypeByVDdAccessTypeId() {
		return this.iclubAccessTypeByVDdAccessTypeId;
	}

	public void setIclubAccessTypeByVDdAccessTypeId(IclubAccessType iclubAccessTypeByVDdAccessTypeId) {
		this.iclubAccessTypeByVDdAccessTypeId = iclubAccessTypeByVDdAccessTypeId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_sec_dev_id")
	public IclubSecurityDevice getIclubSecurityDevice() {
		return this.iclubSecurityDevice;
	}

	public void setIclubSecurityDevice(IclubSecurityDevice iclubSecurityDevice) {
		this.iclubSecurityDevice = iclubSecurityDevice;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_vm_id")
	public IclubVehicleMaster getIclubVehicleMaster() {
		return this.iclubVehicleMaster;
	}

	public void setIclubVehicleMaster(IclubVehicleMaster iclubVehicleMaster) {
		this.iclubVehicleMaster = iclubVehicleMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "v_driver_id")
	public IclubDriver getIclubDriver() {
		return this.iclubDriver;
	}

	public void setIclubDriver(IclubDriver iclubDriver) {
		this.iclubDriver = iclubDriver;
	}

	@Column(name = "v_odometer")
	public Long getVOdometer() {
		return this.VOdometer;
	}

	public void setVOdometer(Long VOdometer) {
		this.VOdometer = VOdometer;
	}

	@Column(name = "v_on_area", length = 999)
	public String getVOnArea() {
		return this.VOnArea;
	}

	public void setVOnArea(String VOnArea) {
		this.VOnArea = VOnArea;
	}

	@Column(name = "v_on_lat", precision = 10, scale = 7)
	public Double getVOnLat() {
		return this.VOnLat;
	}

	public void setVOnLat(Double VOnLat) {
		this.VOnLat = VOnLat;
	}

	@Column(name = "v_on_long", precision = 10, scale = 7)
	public Double getVOnLong() {
		return this.VOnLong;
	}

	public void setVOnLong(Double VOnLong) {
		this.VOnLong = VOnLong;
	}

	@Column(name = "v_dd_area", length = 999)
	public String getVDdArea() {
		return this.VDdArea;
	}

	public void setVDdArea(String VDdArea) {
		this.VDdArea = VDdArea;
	}

	@Column(name = "v_dd_lat", precision = 10, scale = 7)
	public Double getVDdLat() {
		return this.VDdLat;
	}

	public void setVDdLat(Double VDdLat) {
		this.VDdLat = VDdLat;
	}

	@Column(name = "v_dd_long", precision = 10, scale = 7)
	public Double getVDdLong() {
		return this.VDdLong;
	}

	public void setVDdLong(Double VDdLong) {
		this.VDdLong = VDdLong;
	}

	@Column(name = "v_year")
	public Integer getVYear() {
		return this.VYear;
	}

	public void setVYear(Integer VYear) {
		this.VYear = VYear;
	}

	@Column(name = "v_insured_value", precision = 15, scale = 5)
	public Long getVInsuredValue() {
		return this.VInsuredValue;
	}

	public void setVInsuredValue(Long VInsuredValue) {
		this.VInsuredValue = VInsuredValue;
	}

	@Column(name = "v_concess_prct", precision = 15, scale = 5)
	public Long getVConcessPrct() {
		return this.VConcessPrct;
	}

	public void setVConcessPrct(Long VConcessPrct) {
		this.VConcessPrct = VConcessPrct;
	}

	@Column(name = "v_concess_reason", length = 999)
	public String getVConcessReason() {
		return this.VConcessReason;
	}

	public void setVConcessReason(String VConcessReason) {
		this.VConcessReason = VConcessReason;
	}

	@Column(name = "v_imm_yn", length = 1)
	public String getVImmYn() {
		return this.VImmYn;
	}

	public void setVImmYn(String VImmYn) {
		this.VImmYn = VImmYn;
	}

	@Column(name = "v_gear_lock_yn", length = 1)
	public String getVGearLockYn() {
		return this.VGearLockYn;
	}

	public void setVGearLockYn(String VGearLockYn) {
		this.VGearLockYn = VGearLockYn;
	}

	@Column(name = "v_owner", length = 999)
	public String getVOwner() {
		return this.VOwner;
	}

	public void setVOwner(String VOwner) {
		this.VOwner = VOwner;
	}

	@Column(name = "v_noclaim_yrs")
	public Integer getVNoclaimYrs() {
		return this.VNoclaimYrs;
	}

	public void setVNoclaimYrs(Integer VNoclaimYrs) {
		this.VNoclaimYrs = VNoclaimYrs;
	}

	@Column(name = "v_comp_yrs")
	public Integer getVCompYrs() {
		return VCompYrs;
	}

	public void setVCompYrs(Integer vCompYrs) {
		VCompYrs = vCompYrs;
	}

	@Column(name = "v_vin", length = 50)
	public String getVVin() {
		return this.VVin;
	}

	public void setVVin(String VVin) {
		this.VVin = VVin;
	}

	@Column(name = "v_engine_nr", length = 50)
	public String getVEngineNr() {
		return this.VEngineNr;
	}

	public void setVEngineNr(String VEngineNr) {
		this.VEngineNr = VEngineNr;
	}

	@Column(name = "v_reg_num", length = 10)
	public String getVRegNum() {
		return this.VRegNum;
	}

	public void setVRegNum(String VRegNum) {
		this.VRegNum = VRegNum;
	}

	@Column(name = "v_crtd_dt", length = 19)
	public Timestamp getVCrtdDt() {
		return this.VCrtdDt;
	}

	public void setVCrtdDt(Timestamp VCrtdDt) {
		this.VCrtdDt = VCrtdDt;
	}

}