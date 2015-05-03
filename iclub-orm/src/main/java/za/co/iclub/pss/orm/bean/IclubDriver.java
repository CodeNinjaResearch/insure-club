package za.co.iclub.pss.orm.bean;

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

/**
 * IclubDriver entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_driver")
public class IclubDriver implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5690473405219597608L;
	private String DId;
	private IclubAccessType iclubAccessType;
	private IclubLicenseCode iclubLicenseCode;
	private IclubPerson iclubPersonByDCrtdBy;
	private IclubMaritialStatus iclubMaritialStatus;
	private IclubPerson iclubPersonByDPersonId;
	private String DName;
	private String DLicenseNum;
	private Date DIssueDt;
	private Date DDob;
	private Date DCrtdDt;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubDriver() {
	}

	/** minimal constructor */
	public IclubDriver(String DId) {
		this.DId = DId;
	}

	/** full constructor */
	public IclubDriver(String DId, IclubAccessType iclubAccessType, IclubLicenseCode iclubLicenseCode, IclubPerson iclubPersonByDCrtdBy, IclubMaritialStatus iclubMaritialStatus, IclubPerson iclubPersonByDPersonId, String DName, String DLicenseNum, Date DIssueDt, Date DDob, Date DCrtdDt, Set<IclubVehicle> iclubVehicles) {
		this.DId = DId;
		this.iclubAccessType = iclubAccessType;
		this.iclubLicenseCode = iclubLicenseCode;
		this.iclubPersonByDCrtdBy = iclubPersonByDCrtdBy;
		this.iclubMaritialStatus = iclubMaritialStatus;
		this.iclubPersonByDPersonId = iclubPersonByDPersonId;
		this.DName = DName;
		this.DLicenseNum = DLicenseNum;
		this.DIssueDt = DIssueDt;
		this.DDob = DDob;
		this.DCrtdDt = DCrtdDt;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "d_id", unique = true, nullable = false, length = 36)
	public String getDId() {
		return this.DId;
	}

	public void setDId(String DId) {
		this.DId = DId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_access_status_id")
	public IclubAccessType getIclubAccessType() {
		return this.iclubAccessType;
	}

	public void setIclubAccessType(IclubAccessType iclubAccessType) {
		this.iclubAccessType = iclubAccessType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_license_code")
	public IclubLicenseCode getIclubLicenseCode() {
		return this.iclubLicenseCode;
	}

	public void setIclubLicenseCode(IclubLicenseCode iclubLicenseCode) {
		this.iclubLicenseCode = iclubLicenseCode;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_crtd_by")
	public IclubPerson getIclubPersonByDCrtdBy() {
		return this.iclubPersonByDCrtdBy;
	}

	public void setIclubPersonByDCrtdBy(IclubPerson iclubPersonByDCrtdBy) {
		this.iclubPersonByDCrtdBy = iclubPersonByDCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_mar_status_id")
	public IclubMaritialStatus getIclubMaritialStatus() {
		return this.iclubMaritialStatus;
	}

	public void setIclubMaritialStatus(IclubMaritialStatus iclubMaritialStatus) {
		this.iclubMaritialStatus = iclubMaritialStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_person_id")
	public IclubPerson getIclubPersonByDPersonId() {
		return this.iclubPersonByDPersonId;
	}

	public void setIclubPersonByDPersonId(IclubPerson iclubPersonByDPersonId) {
		this.iclubPersonByDPersonId = iclubPersonByDPersonId;
	}

	@Column(name = "d_name", length = 999)
	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	@Column(name = "d_license_num", length = 50)
	public String getDLicenseNum() {
		return this.DLicenseNum;
	}

	public void setDLicenseNum(String DLicenseNum) {
		this.DLicenseNum = DLicenseNum;
	}

	@Column(name = "d_issue_dt", length = 19)
	public Date getDIssueDt() {
		return this.DIssueDt;
	}

	public void setDIssueDt(Date DIssueDt) {
		this.DIssueDt = DIssueDt;
	}

	@Column(name = "d_dob", length = 19)
	public Date getDDob() {
		return this.DDob;
	}

	public void setDDob(Date DDob) {
		this.DDob = DDob;
	}

	@Column(name = "d_crtd_dt", length = 19)
	public Date getDCrtdDt() {
		return this.DCrtdDt;
	}

	public void setDCrtdDt(Date DCrtdDt) {
		this.DCrtdDt = DCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubDriver")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}