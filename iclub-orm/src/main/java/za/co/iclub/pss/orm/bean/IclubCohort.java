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
 * IclubCohort entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort", catalog = "iclubdb")
public class IclubCohort implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8430671233412422699L;
	private String CId;
	private IclubPerson iclubPersonByCPrimaryUserId;
	private IclubPerson iclubPersonByCCrtdBy;
	private IclubCohortType iclubCohortType;
	private String CName;
	private String CEmail;
	private Date CInitDt;
	private Date CFinalizeDt;
	private Double CTotalContrib;
	private Double CCollectedContrib;
	private Integer CCurMemberCnt;
	private Date CCrtdDt;
	private Set<IclubCohortClaim> iclubCohortClaims = new HashSet<IclubCohortClaim>(0);
	private Set<IclubCohortInvite> iclubCohortInvites = new HashSet<IclubCohortInvite>(0);
	private Set<IclubCohortPerson> iclubCohortPersons = new HashSet<IclubCohortPerson>(0);

	// Constructors

	/** default constructor */
	public IclubCohort() {
	}

	/** minimal constructor */
	public IclubCohort(String CId) {
		this.CId = CId;
	}

	/** full constructor */
	public IclubCohort(String CId, IclubPerson iclubPersonByCPrimaryUserId, IclubPerson iclubPersonByCCrtdBy, IclubCohortType iclubCohortType, String CName, String CEmail, Date CInitDt, Date CFinalizeDt, Double CTotalContrib, Double CCollectedContrib, Integer CCurMemberCnt, Date CCrtdDt, Set<IclubCohortClaim> iclubCohortClaims, Set<IclubCohortInvite> iclubCohortInvites, Set<IclubCohortPerson> iclubCohortPersons) {
		this.CId = CId;
		this.iclubPersonByCPrimaryUserId = iclubPersonByCPrimaryUserId;
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
		this.iclubCohortType = iclubCohortType;
		this.CName = CName;
		this.CEmail = CEmail;
		this.CInitDt = CInitDt;
		this.CFinalizeDt = CFinalizeDt;
		this.CTotalContrib = CTotalContrib;
		this.CCollectedContrib = CCollectedContrib;
		this.CCurMemberCnt = CCurMemberCnt;
		this.CCrtdDt = CCrtdDt;
		this.iclubCohortClaims = iclubCohortClaims;
		this.iclubCohortInvites = iclubCohortInvites;
		this.iclubCohortPersons = iclubCohortPersons;
	}

	// Property accessors
	@Id
	@Column(name = "c_id", unique = true, nullable = false, length = 36)
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_primary_user_id")
	public IclubPerson getIclubPersonByCPrimaryUserId() {
		return this.iclubPersonByCPrimaryUserId;
	}

	public void setIclubPersonByCPrimaryUserId(IclubPerson iclubPersonByCPrimaryUserId) {
		this.iclubPersonByCPrimaryUserId = iclubPersonByCPrimaryUserId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_crtd_by")
	public IclubPerson getIclubPersonByCCrtdBy() {
		return this.iclubPersonByCCrtdBy;
	}

	public void setIclubPersonByCCrtdBy(IclubPerson iclubPersonByCCrtdBy) {
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_cohort_type_id")
	public IclubCohortType getIclubCohortType() {
		return this.iclubCohortType;
	}

	public void setIclubCohortType(IclubCohortType iclubCohortType) {
		this.iclubCohortType = iclubCohortType;
	}

	@Column(name = "c_name", length = 999)
	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	@Column(name = "c_email", length = 999)
	public String getCEmail() {
		return this.CEmail;
	}

	public void setCEmail(String CEmail) {
		this.CEmail = CEmail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_init_dt", length = 10)
	public Date getCInitDt() {
		return this.CInitDt;
	}

	public void setCInitDt(Date CInitDt) {
		this.CInitDt = CInitDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_finalize_dt", length = 10)
	public Date getCFinalizeDt() {
		return this.CFinalizeDt;
	}

	public void setCFinalizeDt(Date CFinalizeDt) {
		this.CFinalizeDt = CFinalizeDt;
	}

	@Column(name = "c_total_contrib", precision = 20, scale = 5)
	public Double getCTotalContrib() {
		return this.CTotalContrib;
	}

	public void setCTotalContrib(Double CTotalContrib) {
		this.CTotalContrib = CTotalContrib;
	}

	@Column(name = "c_collected_contrib", precision = 20, scale = 5)
	public Double getCCollectedContrib() {
		return this.CCollectedContrib;
	}

	public void setCCollectedContrib(Double CCollectedContrib) {
		this.CCollectedContrib = CCollectedContrib;
	}

	@Column(name = "c_cur_member_cnt")
	public Integer getCCurMemberCnt() {
		return this.CCurMemberCnt;
	}

	public void setCCurMemberCnt(Integer CCurMemberCnt) {
		this.CCurMemberCnt = CCurMemberCnt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "c_crtd_dt", length = 10)
	public Date getCCrtdDt() {
		return this.CCrtdDt;
	}

	public void setCCrtdDt(Date CCrtdDt) {
		this.CCrtdDt = CCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortClaim> getIclubCohortClaims() {
		return this.iclubCohortClaims;
	}

	public void setIclubCohortClaims(Set<IclubCohortClaim> iclubCohortClaims) {
		this.iclubCohortClaims = iclubCohortClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortInvite> getIclubCohortInvites() {
		return this.iclubCohortInvites;
	}

	public void setIclubCohortInvites(Set<IclubCohortInvite> iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohort")
	public Set<IclubCohortPerson> getIclubCohortPersons() {
		return this.iclubCohortPersons;
	}

	public void setIclubCohortPersons(Set<IclubCohortPerson> iclubCohortPersons) {
		this.iclubCohortPersons = iclubCohortPersons;
	}

}