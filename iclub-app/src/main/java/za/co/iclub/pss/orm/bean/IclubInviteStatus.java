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
 * IclubInviteStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_invite_status", catalog = "iclubdb")
public class IclubInviteStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4910269947491204519L;
	private Long isId;
	private String isLongDesc;
	private String isShortDesc;
	private String isStatus;
	private Set<IclubCohortInvite> iclubCohortInvites = new HashSet<IclubCohortInvite>(
			0);
	private Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortPrevStatus = new HashSet<IclubCohortActivity>(
			0);
	private Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortCurrStatus = new HashSet<IclubCohortActivity>(
			0);

	// Constructors

	/** default constructor */
	public IclubInviteStatus() {
	}

	/** minimal constructor */
	public IclubInviteStatus(Long isId) {
		this.isId = isId;
	}

	/** full constructor */
	public IclubInviteStatus(
			Long isId,
			String isLongDesc,
			String isShortDesc,
			String isStatus,
			Set<IclubCohortInvite> iclubCohortInvites,
			Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortPrevStatus,
			Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortCurrStatus) {
		this.isId = isId;
		this.isLongDesc = isLongDesc;
		this.isShortDesc = isShortDesc;
		this.isStatus = isStatus;
		this.iclubCohortInvites = iclubCohortInvites;
		this.iclubCohortActivitiesForCaCohortPrevStatus = iclubCohortActivitiesForCaCohortPrevStatus;
		this.iclubCohortActivitiesForCaCohortCurrStatus = iclubCohortActivitiesForCaCohortCurrStatus;
	}

	// Property accessors
	@Id
	@Column(name = "is_id", unique = true, nullable = false)
	public Long getIsId() {
		return this.isId;
	}

	public void setIsId(Long isId) {
		this.isId = isId;
	}

	@Column(name = "is_long_desc", length = 500)
	public String getIsLongDesc() {
		return this.isLongDesc;
	}

	public void setIsLongDesc(String isLongDesc) {
		this.isLongDesc = isLongDesc;
	}

	@Column(name = "is_short_desc", length = 4)
	public String getIsShortDesc() {
		return this.isShortDesc;
	}

	public void setIsShortDesc(String isShortDesc) {
		this.isShortDesc = isShortDesc;
	}

	@Column(name = "is_status", length = 1)
	public String getIsStatus() {
		return this.isStatus;
	}

	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInviteStatus")
	public Set<IclubCohortInvite> getIclubCohortInvites() {
		return this.iclubCohortInvites;
	}

	public void setIclubCohortInvites(Set<IclubCohortInvite> iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInviteStatusByCaCohortPrevStatus")
	public Set<IclubCohortActivity> getIclubCohortActivitiesForCaCohortPrevStatus() {
		return this.iclubCohortActivitiesForCaCohortPrevStatus;
	}

	public void setIclubCohortActivitiesForCaCohortPrevStatus(
			Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortPrevStatus) {
		this.iclubCohortActivitiesForCaCohortPrevStatus = iclubCohortActivitiesForCaCohortPrevStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInviteStatusByCaCohortCurrStatus")
	public Set<IclubCohortActivity> getIclubCohortActivitiesForCaCohortCurrStatus() {
		return this.iclubCohortActivitiesForCaCohortCurrStatus;
	}

	public void setIclubCohortActivitiesForCaCohortCurrStatus(
			Set<IclubCohortActivity> iclubCohortActivitiesForCaCohortCurrStatus) {
		this.iclubCohortActivitiesForCaCohortCurrStatus = iclubCohortActivitiesForCaCohortCurrStatus;
	}

}