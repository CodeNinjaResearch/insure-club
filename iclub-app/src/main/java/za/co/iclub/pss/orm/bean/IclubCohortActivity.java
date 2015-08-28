package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubCohortActivity entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_activity", catalog = "iclubdb")
public class IclubCohortActivity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1659224143969661098L;
	private String caId;
	private IclubCohort iclubCohort;
	private IclubInviteStatus iclubInviteStatusByCaCohortCurrStatus;
	private IclubInviteStatus iclubInviteStatusByCaCohortPrevStatus;
	private Date caChangeDate;

	// Constructors

	/** default constructor */
	public IclubCohortActivity() {
	}

	/** minimal constructor */
	public IclubCohortActivity(String caId) {
		this.caId = caId;
	}

	/** full constructor */
	public IclubCohortActivity(String caId, IclubCohort iclubCohort,
			IclubInviteStatus iclubInviteStatusByCaCohortCurrStatus,
			IclubInviteStatus iclubInviteStatusByCaCohortPrevStatus,
			Date caChangeDate) {
		this.caId = caId;
		this.iclubCohort = iclubCohort;
		this.iclubInviteStatusByCaCohortCurrStatus = iclubInviteStatusByCaCohortCurrStatus;
		this.iclubInviteStatusByCaCohortPrevStatus = iclubInviteStatusByCaCohortPrevStatus;
		this.caChangeDate = caChangeDate;
	}

	// Property accessors
	@Id
	@Column(name = "ca_id", unique = true, nullable = false, length = 36)
	public String getCaId() {
		return this.caId;
	}

	public void setCaId(String caId) {
		this.caId = caId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ca_cohort_id")
	public IclubCohort getIclubCohort() {
		return this.iclubCohort;
	}

	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ca_cohort_curr_status")
	public IclubInviteStatus getIclubInviteStatusByCaCohortCurrStatus() {
		return this.iclubInviteStatusByCaCohortCurrStatus;
	}

	public void setIclubInviteStatusByCaCohortCurrStatus(
			IclubInviteStatus iclubInviteStatusByCaCohortCurrStatus) {
		this.iclubInviteStatusByCaCohortCurrStatus = iclubInviteStatusByCaCohortCurrStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ca_cohort_prev_status")
	public IclubInviteStatus getIclubInviteStatusByCaCohortPrevStatus() {
		return this.iclubInviteStatusByCaCohortPrevStatus;
	}

	public void setIclubInviteStatusByCaCohortPrevStatus(
			IclubInviteStatus iclubInviteStatusByCaCohortPrevStatus) {
		this.iclubInviteStatusByCaCohortPrevStatus = iclubInviteStatusByCaCohortPrevStatus;
	}

	@Column(name = "ca_change_date", length = 19)
	public Date getCaChangeDate() {
		return this.caChangeDate;
	}

	public void setCaChangeDate(Date caChangeDate) {
		this.caChangeDate = caChangeDate;
	}

}