package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubCohortCriteria entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_criteria", catalog = "iclubdb")
public class IclubCohortCriteria implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6348839993192322137L;
	private String ccId;
	private IclubCohortInvite iclubCohortInvite;
	private Long ccAge;
	private String ccGender;
	private String ccClaimLastTwYrs;
	private String ccClaimLastYr;
	private String ccMaritalStatus;
	private String ccInsuredValue;

	// Constructors

	/** default constructor */
	public IclubCohortCriteria() {
	}

	/** minimal constructor */
	public IclubCohortCriteria(String ccId) {
		this.ccId = ccId;
	}

	/** full constructor */
	public IclubCohortCriteria(String ccId, IclubCohortInvite iclubCohortInvite, Long ccAge, String ccGender, String ccClaimLastTwYrs, String ccClaimLastYr) {
		this.ccId = ccId;
		this.iclubCohortInvite = iclubCohortInvite;
		this.ccAge = ccAge;
		this.ccGender = ccGender;
		this.ccClaimLastTwYrs = ccClaimLastTwYrs;
		this.ccClaimLastYr = ccClaimLastYr;
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
	@JoinColumn(name = "cc_cohort_invite_id")
	public IclubCohortInvite getIclubCohortInvite() {
		return this.iclubCohortInvite;
	}

	public void setIclubCohortInvite(IclubCohortInvite iclubCohortInvite) {
		this.iclubCohortInvite = iclubCohortInvite;
	}

	@Column(name = "cc_age")
	public Long getCcAge() {
		return this.ccAge;
	}

	public void setCcAge(Long ccAge) {
		this.ccAge = ccAge;
	}

	@Column(name = "cc_gender", length = 1)
	public String getCcGender() {
		return this.ccGender;
	}

	public void setCcGender(String ccGender) {
		this.ccGender = ccGender;
	}

	@Column(name = "cc_claim_last_tw_yrs")
	public String getCcClaimLastTwYrs() {
		return this.ccClaimLastTwYrs;
	}

	public void setCcClaimLastTwYrs(String ccClaimLastTwYrs) {
		this.ccClaimLastTwYrs = ccClaimLastTwYrs;
	}

	@Column(name = "cc_claim_last_yr")
	public String getCcClaimLastYr() {
		return this.ccClaimLastYr;
	}

	public void setCcClaimLastYr(String ccClaimLastYr) {
		this.ccClaimLastYr = ccClaimLastYr;
	}

	@Column(name = "cc_marital_status")
	public String getCcMaritalStatus() {
		return ccMaritalStatus;
	}

	public void setCcMaritalStatus(String ccMaritalStatus) {
		this.ccMaritalStatus = ccMaritalStatus;
	}

	@Column(name = "cc_insured_value")
	public String getCcInsuredValue() {
		return ccInsuredValue;
	}

	public void setCcInsuredValue(String ccInsuredValue) {
		this.ccInsuredValue = ccInsuredValue;
	}

}