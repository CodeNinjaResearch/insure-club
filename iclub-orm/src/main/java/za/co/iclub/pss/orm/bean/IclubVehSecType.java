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
	private static final long serialVersionUID = -4661676321734086985L;
	private Long vstId;
	private String vstShortDesc;
	private String vstLongDesc;
	private String vstStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehSecType() {
	}

	/** minimal constructor */
	public IclubVehSecType(Long vstId) {
		this.vstId = vstId;
	}

	/** full constructor */
	public IclubVehSecType(Long vstId, String vstShortDesc, String vstLongDesc, String vstStatus, Set<IclubVehicle> iclubVehicles) {
		this.vstId = vstId;
		this.vstShortDesc = vstShortDesc;
		this.vstLongDesc = vstLongDesc;
		this.vstStatus = vstStatus;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vst_id", unique = true, nullable = false)
	public Long getVstId() {
		return this.vstId;
	}

	public void setVstId(Long vstId) {
		this.vstId = vstId;
	}

	@Column(name = "vst_short_desc", length = 4)
	public String getVstShortDesc() {
		return this.vstShortDesc;
	}

	public void setVstShortDesc(String vstShortDesc) {
		this.vstShortDesc = vstShortDesc;
	}

	@Column(name = "vst_long_desc", length = 500)
	public String getVstLongDesc() {
		return this.vstLongDesc;
	}

	public void setVstLongDesc(String vstLongDesc) {
		this.vstLongDesc = vstLongDesc;
	}

	@Column(name = "vst_status", length = 1)
	public String getVstStatus() {
		return this.vstStatus;
	}

	public void setVstStatus(String vstStatus) {
		this.vstStatus = vstStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehSecType")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}