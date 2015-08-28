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
	private static final long serialVersionUID = 787252223420108941L;
	private Long vstId;
	private String vstLongDesc;
	private String vstShortDesc;
	private String vstStatus;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);
	private Set<IclubVehicle> iclubVehicles_1 = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehSecType() {
	}

	/** minimal constructor */
	public IclubVehSecType(Long vstId) {
		this.vstId = vstId;
	}

	/** full constructor */
	public IclubVehSecType(Long vstId, String vstLongDesc, String vstShortDesc,
			String vstStatus, Set<IclubVehicle> iclubVehicles,
			Set<IclubVehicle> iclubVehicles_1) {
		this.vstId = vstId;
		this.vstLongDesc = vstLongDesc;
		this.vstShortDesc = vstShortDesc;
		this.vstStatus = vstStatus;
		this.iclubVehicles = iclubVehicles;
		this.iclubVehicles_1 = iclubVehicles_1;
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

	@Column(name = "vst_long_desc")
	public String getVstLongDesc() {
		return this.vstLongDesc;
	}

	public void setVstLongDesc(String vstLongDesc) {
		this.vstLongDesc = vstLongDesc;
	}

	@Column(name = "vst_short_desc", length = 4)
	public String getVstShortDesc() {
		return this.vstShortDesc;
	}

	public void setVstShortDesc(String vstShortDesc) {
		this.vstShortDesc = vstShortDesc;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehSecType")
	public Set<IclubVehicle> getIclubVehicles_1() {
		return this.iclubVehicles_1;
	}

	public void setIclubVehicles_1(Set<IclubVehicle> iclubVehicles_1) {
		this.iclubVehicles_1 = iclubVehicles_1;
	}

}