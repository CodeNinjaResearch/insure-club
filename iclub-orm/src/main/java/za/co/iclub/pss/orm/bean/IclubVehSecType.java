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
 * IclubVehSecType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_veh_sec_type", catalog = "iclubdb")
public class IclubVehSecType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1257326289662693934L;
	private Long vsId;
	private String vsShortDesc;
	private String vsLongDesc;
	private String vsStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehSecType() {
	}

	/** minimal constructor */
	public IclubVehSecType(Long vsId) {
		this.vsId = vsId;
	}

	/** full constructor */
	public IclubVehSecType(Long vsId, String vsShortDesc, String vsLongDesc, String vsStatus, Set<IclubVehicle> iclubVehicles) {
		this.vsId = vsId;
		this.vsShortDesc = vsShortDesc;
		this.vsLongDesc = vsLongDesc;
		this.vsStatus = vsStatus;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vs_id", unique = true, nullable = false)
	public Long getVsId() {
		return this.vsId;
	}

	public void setVsId(Long vsId) {
		this.vsId = vsId;
	}

	@Column(name = "vs_short_desc", length = 4)
	public String getVsShortDesc() {
		return this.vsShortDesc;
	}

	public void setVsShortDesc(String vsShortDesc) {
		this.vsShortDesc = vsShortDesc;
	}

	@Column(name = "vs_long_desc", length = 500)
	public String getVsLongDesc() {
		return this.vsLongDesc;
	}

	public void setVsLongDesc(String vsLongDesc) {
		this.vsLongDesc = vsLongDesc;
	}

	@Column(name = "vs_status", length = 1)
	public String getVsStatus() {
		return this.vsStatus;
	}

	public void setVsStatus(String vsStatus) {
		this.vsStatus = vsStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehSecType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}