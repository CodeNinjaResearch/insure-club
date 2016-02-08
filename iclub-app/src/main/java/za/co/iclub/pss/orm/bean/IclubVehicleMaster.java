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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubVehicleMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_vehicle_master", catalog = "iclubdb")
public class IclubVehicleMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4659999932796595343L;
	private Long vmId;
	private IclubPerson iclubPerson;
	private String vmMake;
	private String vmModel;
	private Double vmOrigRate;
	private Double vmMrktRate;
	private Double vmRetRate;
	private Double vmRatePrct;
	private Date vmProdDt;
	private Date vmCrtdDt;
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
	public IclubVehicleMaster(Long vmId, IclubPerson iclubPerson, String vmMake, String vmModel, Double vmOrigRate, Double vmMrktRate, Double vmRetRate, Double vmRatePrct, Date vmProdDt, Date vmCrtdDt, Set<IclubVehicle> iclubVehicles) {
		this.vmId = vmId;
		this.iclubPerson = iclubPerson;
		this.vmMake = vmMake;
		this.vmModel = vmModel;
		this.vmOrigRate = vmOrigRate;
		this.vmMrktRate = vmMrktRate;
		this.vmRetRate = vmRetRate;
		this.vmRatePrct = vmRatePrct;
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

	@Column(name = "vm_make", length = 250)
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
	public Double getVmOrigRate() {
		return this.vmOrigRate;
	}

	public void setVmOrigRate(Double vmOrigRate) {
		this.vmOrigRate = vmOrigRate;
	}

	@Column(name = "vm_mrkt_rate", precision = 15, scale = 5)
	public Double getVmMrktRate() {
		return this.vmMrktRate;
	}

	public void setVmMrktRate(Double vmMrktRate) {
		this.vmMrktRate = vmMrktRate;
	}

	@Column(name = "vm_ret_rate", precision = 15, scale = 5)
	public Double getVmRetRate() {
		return this.vmRetRate;
	}

	public void setVmRetRate(Double vmRetRate) {
		this.vmRetRate = vmRetRate;
	}

	@Column(name = "vm_rate_prct", precision = 4)
	public Double getVmRatePrct() {
		return this.vmRatePrct;
	}

	public void setVmRatePrct(Double vmRatePrct) {
		this.vmRatePrct = vmRatePrct;
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
	public Date getVmCrtdDt() {
		return this.vmCrtdDt;
	}

	public void setVmCrtdDt(Date vmCrtdDt) {
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