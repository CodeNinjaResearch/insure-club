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
 * IclubAccessType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_access_type", catalog = "iclubdb")
public class IclubAccessType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 752034910997465996L;
	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	private Set<IclubVehicle> iclubVehiclesForVOnAccessTypeId = new HashSet<IclubVehicle>(0);
	private Set<IclubVehicle> iclubVehiclesForVDdAccessTypeId = new HashSet<IclubVehicle>(0);
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);
	private Set<IclubDriver> iclubDriversForDAccessTypeId = new HashSet<IclubDriver>(0);
	private Set<IclubDriver> iclubDriversForDAccessStatusId = new HashSet<IclubDriver>(0);

	// Constructors

	/** default constructor */
	public IclubAccessType() {
	}

	/** minimal constructor */
	public IclubAccessType(Long atId) {
		this.atId = atId;
	}

	/** full constructor */
	public IclubAccessType(Long atId, String atShortDesc, String atLongDesc, String atStatus, Set<IclubVehicle> iclubVehiclesForVOnAccessTypeId, Set<IclubVehicle> iclubVehiclesForVDdAccessTypeId, Set<IclubProperty> iclubProperties, Set<IclubDriver> iclubDriversForDAccessTypeId, Set<IclubDriver> iclubDriversForDAccessStatusId) {
		this.atId = atId;
		this.atShortDesc = atShortDesc;
		this.atLongDesc = atLongDesc;
		this.atStatus = atStatus;
		this.iclubVehiclesForVOnAccessTypeId = iclubVehiclesForVOnAccessTypeId;
		this.iclubVehiclesForVDdAccessTypeId = iclubVehiclesForVDdAccessTypeId;
		this.iclubProperties = iclubProperties;
		this.iclubDriversForDAccessTypeId = iclubDriversForDAccessTypeId;
		this.iclubDriversForDAccessStatusId = iclubDriversForDAccessStatusId;
	}

	// Property accessors
	@Id
	@Column(name = "at_id", unique = true, nullable = false)
	public Long getAtId() {
		return this.atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	@Column(name = "at_short_desc", length = 4)
	public String getAtShortDesc() {
		return this.atShortDesc;
	}

	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}

	@Column(name = "at_long_desc", length = 500)
	public String getAtLongDesc() {
		return this.atLongDesc;
	}

	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}

	@Column(name = "at_status", length = 1)
	public String getAtStatus() {
		return this.atStatus;
	}

	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccessTypeByVOnAccessTypeId")
	public Set<IclubVehicle> getIclubVehiclesForVOnAccessTypeId() {
		return this.iclubVehiclesForVOnAccessTypeId;
	}

	public void setIclubVehiclesForVOnAccessTypeId(Set<IclubVehicle> iclubVehiclesForVOnAccessTypeId) {
		this.iclubVehiclesForVOnAccessTypeId = iclubVehiclesForVOnAccessTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccessTypeByVDdAccessTypeId")
	public Set<IclubVehicle> getIclubVehiclesForVDdAccessTypeId() {
		return this.iclubVehiclesForVDdAccessTypeId;
	}

	public void setIclubVehiclesForVDdAccessTypeId(Set<IclubVehicle> iclubVehiclesForVDdAccessTypeId) {
		this.iclubVehiclesForVDdAccessTypeId = iclubVehiclesForVDdAccessTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccessType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccessTypeByDAccessTypeId")
	public Set<IclubDriver> getIclubDriversForDAccessTypeId() {
		return this.iclubDriversForDAccessTypeId;
	}

	public void setIclubDriversForDAccessTypeId(Set<IclubDriver> iclubDriversForDAccessTypeId) {
		this.iclubDriversForDAccessTypeId = iclubDriversForDAccessTypeId;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccessTypeByDAccessStatusId")
	public Set<IclubDriver> getIclubDriversForDAccessStatusId() {
		return this.iclubDriversForDAccessStatusId;
	}

	public void setIclubDriversForDAccessStatusId(Set<IclubDriver> iclubDriversForDAccessStatusId) {
		this.iclubDriversForDAccessStatusId = iclubDriversForDAccessStatusId;
	}

}