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
import javax.persistence.UniqueConstraint;

/**
 * IclubLicenseCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_license_code", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "lc_category"))
public class IclubLicenseCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3453309689516690165L;
	private Long lcId;
	private IclubPerson iclubPerson;
	private String lcCategory;
	private String lcDesc;
	private String lcStatus;
	private Date lcCrtdDt;
	private Set<IclubDriver> iclubDrivers = new HashSet<IclubDriver>(0);

	// Constructors

	/** default constructor */
	public IclubLicenseCode() {
	}

	/** minimal constructor */
	public IclubLicenseCode(Long lcId) {
		this.lcId = lcId;
	}

	/** full constructor */
	public IclubLicenseCode(Long lcId, IclubPerson iclubPerson, String lcCategory, String lcDesc, String lcStatus, Date lcCrtdDt, Set<IclubDriver> iclubDrivers) {
		this.lcId = lcId;
		this.iclubPerson = iclubPerson;
		this.lcCategory = lcCategory;
		this.lcDesc = lcDesc;
		this.lcStatus = lcStatus;
		this.lcCrtdDt = lcCrtdDt;
		this.iclubDrivers = iclubDrivers;
	}

	// Property accessors
	@Id
	@Column(name = "lc_id", unique = true, nullable = false)
	public Long getLcId() {
		return this.lcId;
	}

	public void setLcId(Long lcId) {
		this.lcId = lcId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lc_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "lc_category", unique = true, length = 2)
	public String getLcCategory() {
		return this.lcCategory;
	}

	public void setLcCategory(String lcCategory) {
		this.lcCategory = lcCategory;
	}

	@Column(name = "lc_desc", length = 450)
	public String getLcDesc() {
		return this.lcDesc;
	}

	public void setLcDesc(String lcDesc) {
		this.lcDesc = lcDesc;
	}

	@Column(name = "lc_status", length = 1)
	public String getLcStatus() {
		return this.lcStatus;
	}

	public void setLcStatus(String lcStatus) {
		this.lcStatus = lcStatus;
	}

	@Column(name = "lc_crtd_dt", length = 19)
	public Date getLcCrtdDt() {
		return this.lcCrtdDt;
	}

	public void setLcCrtdDt(Date lcCrtdDt) {
		this.lcCrtdDt = lcCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubLicenseCode")
	public Set<IclubDriver> getIclubDrivers() {
		return this.iclubDrivers;
	}

	public void setIclubDrivers(Set<IclubDriver> iclubDrivers) {
		this.iclubDrivers = iclubDrivers;
	}

}