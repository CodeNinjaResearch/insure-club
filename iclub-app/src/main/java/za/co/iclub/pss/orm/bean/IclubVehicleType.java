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
 * IclubVehicleType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_vehicle_type", catalog = "iclubdb")
public class IclubVehicleType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2504618251322597924L;
	private Long vtId;
	private String vtShortDesc;
	private String vtLongDesc;
	private String vtStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehicleType() {
	}

	/** minimal constructor */
	public IclubVehicleType(Long vtId) {
		this.vtId = vtId;
	}

	/** full constructor */
	public IclubVehicleType(Long vtId, String vtShortDesc, String vtLongDesc,
			String vtStatus, Set<IclubVehicle> iclubVehicles) {
		this.vtId = vtId;
		this.vtShortDesc = vtShortDesc;
		this.vtLongDesc = vtLongDesc;
		this.vtStatus = vtStatus;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vt_id", unique = true, nullable = false)
	public Long getVtId() {
		return this.vtId;
	}

	public void setVtId(Long vtId) {
		this.vtId = vtId;
	}

	@Column(name = "vt_short_desc", length = 4)
	public String getVtShortDesc() {
		return this.vtShortDesc;
	}

	public void setVtShortDesc(String vtShortDesc) {
		this.vtShortDesc = vtShortDesc;
	}

	@Column(name = "vt_long_desc", length = 500)
	public String getVtLongDesc() {
		return this.vtLongDesc;
	}

	public void setVtLongDesc(String vtLongDesc) {
		this.vtLongDesc = vtLongDesc;
	}

	@Column(name = "vt_status", length = 1)
	public String getVtStatus() {
		return this.vtStatus;
	}

	public void setVtStatus(String vtStatus) {
		this.vtStatus = vtStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehicleType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}