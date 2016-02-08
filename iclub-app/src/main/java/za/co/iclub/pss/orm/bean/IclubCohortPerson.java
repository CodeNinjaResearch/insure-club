package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * IclubCohortPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_person", catalog = "iclubdb")
public class IclubCohortPerson implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2481929650414152375L;
	private String cpId;
	private IclubCohort iclubCohort;
	private IclubPerson iclubPersonByCpCrtdBy;
	private IclubPerson iclubPersonByCpPersonId;
	private Double cpContrib;
	private Date cpCrtdDt;

	// Constructors

	/** default constructor */
	public IclubCohortPerson() {
	}

	/** minimal constructor */
	public IclubCohortPerson(String cpId) {
		this.cpId = cpId;
	}

	/** full constructor */
	public IclubCohortPerson(String cpId, IclubCohort iclubCohort, IclubPerson iclubPersonByCpCrtdBy, IclubPerson iclubPersonByCpPersonId, Double cpContrib, Date cpCrtdDt) {
		this.cpId = cpId;
		this.iclubCohort = iclubCohort;
		this.iclubPersonByCpCrtdBy = iclubPersonByCpCrtdBy;
		this.iclubPersonByCpPersonId = iclubPersonByCpPersonId;
		this.cpContrib = cpContrib;
		this.cpCrtdDt = cpCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "cp_id", unique = true, nullable = false, length = 36)
	public String getCpId() {
		return this.cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_cohort_id")
	public IclubCohort getIclubCohort() {
		return this.iclubCohort;
	}

	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_crtd_by")
	public IclubPerson getIclubPersonByCpCrtdBy() {
		return this.iclubPersonByCpCrtdBy;
	}

	public void setIclubPersonByCpCrtdBy(IclubPerson iclubPersonByCpCrtdBy) {
		this.iclubPersonByCpCrtdBy = iclubPersonByCpCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cp_person_id")
	public IclubPerson getIclubPersonByCpPersonId() {
		return this.iclubPersonByCpPersonId;
	}

	public void setIclubPersonByCpPersonId(IclubPerson iclubPersonByCpPersonId) {
		this.iclubPersonByCpPersonId = iclubPersonByCpPersonId;
	}

	@Column(name = "cp_contrib", precision = 15, scale = 5)
	public Double getCpContrib() {
		return this.cpContrib;
	}

	public void setCpContrib(Double cpContrib) {
		this.cpContrib = cpContrib;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cp_crtd_dt", length = 10)
	public Date getCpCrtdDt() {
		return this.cpCrtdDt;
	}

	public void setCpCrtdDt(Date cpCrtdDt) {
		this.cpCrtdDt = cpCrtdDt;
	}

}