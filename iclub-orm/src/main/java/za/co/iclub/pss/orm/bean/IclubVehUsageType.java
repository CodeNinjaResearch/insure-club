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
@Table(name = "iclub_veh_usage_type")
public class IclubVehUsageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7114046638385079241L;
	private Long vuId;
	private String vuShortDesc;
	private String vuLongDesc;
	private String vuStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehUsageType() {
	}

	/** minimal constructor */
	public IclubVehUsageType(Long vuId) {
		this.vuId = vuId;
	}

	/** full constructor */
	public IclubVehUsageType(Long vuId, String vuShortDesc, String vuLongDesc, String vuStatus, Set<IclubVehicle> iclubVehicles) {
		this.vuId = vuId;
		this.vuShortDesc = vuShortDesc;
		this.vuLongDesc = vuLongDesc;
		this.vuStatus = vuStatus;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vu_id", unique = true, nullable = false)
	public Long getVuId() {
		return this.vuId;
	}

	public void setVuId(Long vuId) {
		this.vuId = vuId;
	}

	@Column(name = "vu_short_desc", length = 4)
	public String getVuShortDesc() {
		return this.vuShortDesc;
	}

	public void setVuShortDesc(String vuShortDesc) {
		this.vuShortDesc = vuShortDesc;
	}

	@Column(name = "vu_long_desc", length = 500)
	public String getVuLongDesc() {
		return this.vuLongDesc;
	}

	public void setVuLongDesc(String vuLongDesc) {
		this.vuLongDesc = vuLongDesc;
	}

	@Column(name = "vu_status", length = 1)
	public String getVuStatus() {
		return this.vuStatus;
	}

	public void setVuStatus(String vuStatus) {
		this.vuStatus = vuStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehUsageType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}