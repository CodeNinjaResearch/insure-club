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
 * IclubAccessType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cohort_activity", catalog = "iclubdb")
public class IclubCohortActivity implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 833824063865850865L;
	private Long caId;
	private IclubCohort iclubCohort;
	private IclubInviteStatus iclubInviteCurrStatus;
	private IclubInviteStatus iclubInvitePrevStatus;
	private Date caChangeDate;
	
	// Constructors
	
	/** default constructor */
	public IclubCohortActivity() {
	}
	
	/** minimal constructor */
	public IclubCohortActivity(Long caId) {
		this.caId = caId;
	}
	
	// Property accessors
	
	@Id
	@Column(name = "ca_id", unique = true, nullable = false)
	public Long getCaId() {
		return caId;
	}
	
	public void setCaId(Long caId) {
		this.caId = caId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_cohort_id")
	public IclubCohort getIclubCohort() {
		return iclubCohort;
	}
	
	public void setIclubCohort(IclubCohort iclubCohort) {
		this.iclubCohort = iclubCohort;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ca_cohort_curr_status")
	public IclubInviteStatus getIclubInviteCurrStatus() {
		return iclubInviteCurrStatus;
	}
	
	public void setIclubInviteCurrStatus(IclubInviteStatus iclubInviteCurrStatus) {
		this.iclubInviteCurrStatus = iclubInviteCurrStatus;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ca_cohort_prev_status")
	public IclubInviteStatus getIclubInvitePrevStatus() {
		return iclubInvitePrevStatus;
	}
	
	public void setIclubInvitePrevStatus(IclubInviteStatus iclubInvitePrevStatus) {
		this.iclubInvitePrevStatus = iclubInvitePrevStatus;
	}
	
	@Column(name = "ca_change_date", length = 500)
	public Date getCaChangeDate() {
		return caChangeDate;
	}
	
	public void setCaChangeDate(Date caChangeDate) {
		this.caChangeDate = caChangeDate;
	}
	
}