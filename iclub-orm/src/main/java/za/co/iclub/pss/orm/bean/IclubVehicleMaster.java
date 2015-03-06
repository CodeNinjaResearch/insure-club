package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * IclubVehicleMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_vehicle_master", uniqueConstraints = @UniqueConstraint(columnNames = "vm_make"))
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_vehicle_master where vm_crtd_by=:id", name = "getVehicleMasterByUser", resultClass = IclubVehicleMaster.class) })
public class IclubVehicleMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -991980648399076007L;
	private Long vmId;
	private IclubPerson iclubPerson;
	private String vmMake;
	private String vmModel;
	private Long vmOrigRate;
	private Long vmMrktRate;
	private Long vmRetRate;
	private Date vmProdDt;
	private Timestamp vmCrtdDt;
	private Set<IclubVehicle> iclubVehicles = new HashSet<IclubVehicle>(0);

	// Constructors

	/** default constructor */
	public IclubVehicleMaster() {
	}

	/** minimal constructor */
	public IclubVehicleMaster(Long vmId) {
		this.vmId = vmId;
	}

	/** full constructor */
	public IclubVehicleMaster(Long vmId, IclubPerson iclubPerson, String vmMake, String vmModel, Long vmOrigRate, Long vmMrktRate, Long vmRetRate, Date vmProdDt, Timestamp vmCrtdDt, Set<IclubVehicle> iclubVehicles) {
		this.vmId = vmId;
		this.iclubPerson = iclubPerson;
		this.vmMake = vmMake;
		this.vmModel = vmModel;
		this.vmOrigRate = vmOrigRate;
		this.vmMrktRate = vmMrktRate;
		this.vmRetRate = vmRetRate;
		this.vmProdDt = vmProdDt;
		this.vmCrtdDt = vmCrtdDt;
		this.iclubVehicles = iclubVehicles;
	}

	// Property accessors
	@Id
	@Column(name = "vm_id", unique = true, nullable = false)
	public Long getVmId() {
		return this.vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "vm_make", unique = true, length = 250)
	public String getVmMake() {
		return this.vmMake;
	}

	public void setVmMake(String vmMake) {
		this.vmMake = vmMake;
	}

	@Column(name = "vm_model", length = 999)
	public String getVmModel() {
		return this.vmModel;
	}

	public void setVmModel(String vmModel) {
		this.vmModel = vmModel;
	}

	@Column(name = "vm_orig_rate", precision = 15, scale = 5)
	public Long getVmOrigRate() {
		return this.vmOrigRate;
	}

	public void setVmOrigRate(Long vmOrigRate) {
		this.vmOrigRate = vmOrigRate;
	}

	@Column(name = "vm_mrkt_rate", precision = 15, scale = 5)
	public Long getVmMrktRate() {
		return this.vmMrktRate;
	}

	public void setVmMrktRate(Long vmMrktRate) {
		this.vmMrktRate = vmMrktRate;
	}

	@Column(name = "vm_ret_rate", precision = 15, scale = 5)
	public Long getVmRetRate() {
		return this.vmRetRate;
	}

	public void setVmRetRate(Long vmRetRate) {
		this.vmRetRate = vmRetRate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "vm_prod_dt", length = 10)
	public Date getVmProdDt() {
		return this.vmProdDt;
	}

	public void setVmProdDt(Date vmProdDt) {
		this.vmProdDt = vmProdDt;
	}

	@Column(name = "vm_crtd_dt", length = 19)
	public Timestamp getVmCrtdDt() {
		return this.vmCrtdDt;
	}

	public void setVmCrtdDt(Timestamp vmCrtdDt) {
		this.vmCrtdDt = vmCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubVehicleMaster")
	public Set<IclubVehicle> getIclubVehicles() {
		return this.iclubVehicles;
	}

	public void setIclubVehicles(Set<IclubVehicle> iclubVehicles) {
		this.iclubVehicles = iclubVehicles;
	}

}