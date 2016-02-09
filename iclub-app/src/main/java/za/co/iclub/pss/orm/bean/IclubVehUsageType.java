package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubVehUsageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_veh_usage_type", catalog = "iclubdb")
public class IclubVehUsageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3475933410814982974L;
	private Long vutId;
	private String vutLongDesc;
	private String vutShortDesc;
	private String vutStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehUsageType() {
	}

	/** minimal constructor */
	public IclubVehUsageType(Long vutId) {
		this.vutId = vutId;
	}

	/** full constructor */
	public IclubVehUsageType(Long vutId, String vutLongDesc, String vutShortDesc, String vutStatus, Set<IclubVehicle> iclubVehicles) {
		this.vutId = vutId;
		this.vutLongDesc = vutLongDesc;
		this.vutShortDesc = vutShortDesc;
		this.vutStatus = vutStatus;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vut_id", unique = true, nullable = false)
	public Long getVutId() {
		return this.vutId;
	}

	public void setVutId(Long vutId) {
		this.vutId = vutId;
	}

	@Column(name = "vut_long_desc")
	public String getVutLongDesc() {
		return this.vutLongDesc;
	}

	public void setVutLongDesc(String vutLongDesc) {
		this.vutLongDesc = vutLongDesc;
	}

	@Column(name = "vut_short_desc", length = 4)
	public String getVutShortDesc() {
		return this.vutShortDesc;
	}

	public void setVutShortDesc(String vutShortDesc) {
		this.vutShortDesc = vutShortDesc;
	}

	@Column(name = "vut_status", length = 1)
	public String getVutStatus() {
		return this.vutStatus;
	}

	public void setVutStatus(String vutStatus) {
		this.vutStatus = vutStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehUsageType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}