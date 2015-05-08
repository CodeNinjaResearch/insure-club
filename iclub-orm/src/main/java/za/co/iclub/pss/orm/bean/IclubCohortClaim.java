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
 * IclubCohortClaim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_claim", catalog = "iclubdb")
public class IclubCohortClaim implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4583675205269567576L;
	private String ccId;
	private IclubCohort iclubCohort;
	private IclubPerson iclubPerson;
	private IclubClaim iclubClaim;
	private Double ccClaimAmt;
	private Date ccCrtdDt;

	// Constructors

	/** default constructor */
	public IclubCohortClaim() {
	}

	/** minimal constructor */
	public IclubCohortClaim(String ccId) {
		this.ccId = ccId;
	}

	/** full constructor */
	public IclubCohortClaim(String ccId, IclubCohort iclubCohort, IclubPerson iclubPerson, IclubClaim iclubClaim, Double ccClaimAmt, Date ccCrtdDt) {
		this.ccId = ccId;
		this.iclubCohort = iclubCohort;
		this.iclubPerson = iclubPerson;
		this.iclubClaim = iclubClaim;
		this.ccClaimAmt = ccClaimAmt;
		this.ccCrtdDt = ccCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "cc_id", unique = true, nullable = false, length = 36)
	public String getCcId() {
		return this.ccId;
	}

	public void setCcId(String ccId) {
		this.ccId = ccId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CC_COHORT_ID")
	public IclubCohort getIclubCohort() {
		return this.iclubCohort;
	}

	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cc_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cc_claim_id")
	public IclubClaim getIclubClaim() {
		return this.iclubClaim;
	}

	public void setIclubClaim(IclubClaim iclubClaim) {
		this.iclubClaim = iclubClaim;
	}

	@Column(name = "cc_claim_amt", precision = 20, scale = 5)
	public Double getCcClaimAmt() {
		return this.ccClaimAmt;
	}

	public void setCcClaimAmt(Double ccClaimAmt) {
		this.ccClaimAmt = ccClaimAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "cc_crtd_dt", length = 10)
	public Date getCcCrtdDt() {
		return this.ccCrtdDt;
	}

	public void setCcCrtdDt(Date ccCrtdDt) {
		this.ccCrtdDt = ccCrtdDt;
	}

}