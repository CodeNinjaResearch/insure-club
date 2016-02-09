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
 * IclubCohortType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_type", catalog = "iclubdb")
public class IclubCohortType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2120214218126100010L;
	private Long ctId;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	private Set<IclubCohort> iclubCohorts = new HashSet<IclubCohort>(0);

	// Constructors

	/** default constructor */
	public IclubCohortType() {
	}

	/** minimal constructor */
	public IclubCohortType(Long ctId) {
		this.ctId = ctId;
	}

	/** full constructor */
	public IclubCohortType(Long ctId, String ctShortDesc, String ctLongDesc, String ctStatus, Set<IclubCohort> iclubCohorts) {
		this.ctId = ctId;
		this.ctShortDesc = ctShortDesc;
		this.ctLongDesc = ctLongDesc;
		this.ctStatus = ctStatus;
		this.iclubCohorts = iclubCohorts;
	}

	// Property accessors
	@Id
	@Column(name = "ct_id", unique = true, nullable = false)
	public Long getCtId() {
		return this.ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	@Column(name = "ct_short_desc", length = 4)
	public String getCtShortDesc() {
		return this.ctShortDesc;
	}

	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}

	@Column(name = "ct_long_desc", length = 500)
	public String getCtLongDesc() {
		return this.ctLongDesc;
	}

	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}

	@Column(name = "ct_status", length = 1)
	public String getCtStatus() {
		return this.ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohortType")
	public Set<IclubCohort> getIclubCohorts() {
		return this.iclubCohorts;
	}

	public void setIclubCohorts(Set<IclubCohort> iclubCohorts) {
		this.iclubCohorts = iclubCohorts;
	}

}