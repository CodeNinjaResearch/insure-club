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
 * IclubCohortInvite entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_invite", catalog = "iclubdb")
public class IclubCohortInvite implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6022548714917586012L;
	private String ciId;
	private IclubCohort iclubCohort;
	private IclubPerson iclubPerson;
	private IclubInviteStatus iclubInviteStatus;
	private IclubNotificationType iclubNotificationType;
	private String ciInviteUri;
	private String ciInviteAcceptYn;
	private Date ciCrtdDt;
	private String ciInviteFName;
	private String ciInviteLName;
	private String ciInviteSentStatus;
	private Set<IclubPerson> iclubPersons = new HashSet<IclubPerson>(0);

	// Constructors

	/** default constructor */
	public IclubCohortInvite() {
	}

	/** minimal constructor */
	public IclubCohortInvite(String ciId) {
		this.ciId = ciId;
	}

	/** full constructor */
	public IclubCohortInvite(String ciId, IclubCohort iclubCohort,
			IclubPerson iclubPerson, IclubInviteStatus iclubInviteStatus,
			IclubNotificationType iclubNotificationType, String ciInviteUri,
			String ciInviteAcceptYn, Date ciCrtdDt, String ciInviteFName,
			String ciInviteLName, String ciInviteSentStatus,
			Set<IclubPerson> iclubPersons) {
		this.ciId = ciId;
		this.iclubCohort = iclubCohort;
		this.iclubPerson = iclubPerson;
		this.iclubInviteStatus = iclubInviteStatus;
		this.iclubNotificationType = iclubNotificationType;
		this.ciInviteUri = ciInviteUri;
		this.ciInviteAcceptYn = ciInviteAcceptYn;
		this.ciCrtdDt = ciCrtdDt;
		this.ciInviteFName = ciInviteFName;
		this.ciInviteLName = ciInviteLName;
		this.ciInviteSentStatus = ciInviteSentStatus;
		this.iclubPersons = iclubPersons;
	}

	// Property accessors
	@Id
	@Column(name = "ci_id", unique = true, nullable = false, length = 36)
	public String getCiId() {
		return this.ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_cohort_id")
	public IclubCohort getIclubCohort() {
		return this.iclubCohort;
	}

	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_invite_status_id")
	public IclubInviteStatus getIclubInviteStatus() {
		return this.iclubInviteStatus;
	}

	public void setIclubInviteStatus(IclubInviteStatus iclubInviteStatus) {
		this.iclubInviteStatus = iclubInviteStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_invite_type_id")
	public IclubNotificationType getIclubNotificationType() {
		return this.iclubNotificationType;
	}

	public void setIclubNotificationType(
			IclubNotificationType iclubNotificationType) {
		this.iclubNotificationType = iclubNotificationType;
	}

	@Column(name = "ci_invite_uri", length = 999)
	public String getCiInviteUri() {
		return this.ciInviteUri;
	}

	public void setCiInviteUri(String ciInviteUri) {
		this.ciInviteUri = ciInviteUri;
	}

	@Column(name = "ci_invite_accept_yn", length = 1)
	public String getCiInviteAcceptYn() {
		return this.ciInviteAcceptYn;
	}

	public void setCiInviteAcceptYn(String ciInviteAcceptYn) {
		this.ciInviteAcceptYn = ciInviteAcceptYn;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ci_crtd_dt", length = 10)
	public Date getCiCrtdDt() {
		return this.ciCrtdDt;
	}

	public void setCiCrtdDt(Date ciCrtdDt) {
		this.ciCrtdDt = ciCrtdDt;
	}

	@Column(name = "ci_invite_f_name", length = 999)
	public String getCiInviteFName() {
		return this.ciInviteFName;
	}

	public void setCiInviteFName(String ciInviteFName) {
		this.ciInviteFName = ciInviteFName;
	}

	@Column(name = "ci_invite_l_name", length = 999)
	public String getCiInviteLName() {
		return this.ciInviteLName;
	}

	public void setCiInviteLName(String ciInviteLName) {
		this.ciInviteLName = ciInviteLName;
	}

	@Column(name = "ci_invite_sent_status", length = 45)
	public String getCiInviteSentStatus() {
		return this.ciInviteSentStatus;
	}

	public void setCiInviteSentStatus(String ciInviteSentStatus) {
		this.ciInviteSentStatus = ciInviteSentStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCohortInvite")
	public Set<IclubPerson> getIclubPersons() {
		return this.iclubPersons;
	}

	public void setIclubPersons(Set<IclubPerson> iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

}