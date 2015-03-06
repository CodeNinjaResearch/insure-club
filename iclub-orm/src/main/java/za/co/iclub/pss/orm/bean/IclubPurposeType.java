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
 * IclubPurposeType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_purpose_type")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_purpose_type where pt_crtd_by=:id", name = "getPurposeTypeByUser", resultClass = IclubPurposeType.class) })
public class IclubPurposeType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8543478655259701168L;
	private Long ptId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Timestamp ptCrtdDt;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubPurposeType() {
	}

	/** minimal constructor */
	public IclubPurposeType(Long ptId) {
		this.ptId = ptId;
	}

	/** full constructor */
	public IclubPurposeType(Long ptId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPerson, String ptShortDesc, String ptLongDesc, String ptStatus, Timestamp ptCrtdDt, Set<IclubProperty> iclubProperties, Set<IclubVehicle> iclubVehicles) {
		this.ptId = ptId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.ptShortDesc = ptShortDesc;
		this.ptLongDesc = ptLongDesc;
		this.ptStatus = ptStatus;
		this.ptCrtdDt = ptCrtdDt;
		this.iclubProperties = iclubProperties;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "pt_id", unique = true, nullable = false)
	public Long getPtId() {
		return this.ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pt_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pt_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "pt_short_desc", length = 4)
	public String getPtShortDesc() {
		return this.ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	@Column(name = "pt_long_desc", length = 450)
	public String getPtLongDesc() {
		return this.ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	@Column(name = "pt_status", length = 1)
	public String getPtStatus() {
		return this.ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

	@Column(name = "pt_crtd_dt", length = 19)
	public Timestamp getPtCrtdDt() {
		return this.ptCrtdDt;
	}

	public void setPtCrtdDt(Timestamp ptCrtdDt) {
		this.ptCrtdDt = ptCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPurposeType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPurposeType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}