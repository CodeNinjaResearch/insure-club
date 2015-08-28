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
 * IclubNotificationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_notification_type", catalog = "iclubdb")
public class IclubNotificationType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1284245551610280654L;
	private Long ntId;
	private String ntShortDesc;
	private String ntLongDesc;
	private String ntStatus;
	private Set<IclubCohortInvite> iclubCohortInvites = new HashSet<IclubCohortInvite>(
			0);
	private Set<IclubNotif> iclubNotifs = new HashSet<IclubNotif>(0);

	// Constructors

	/** default constructor */
	public IclubNotificationType() {
	}

	/** minimal constructor */
	public IclubNotificationType(Long ntId) {
		this.ntId = ntId;
	}

	/** full constructor */
	public IclubNotificationType(Long ntId, String ntShortDesc,
			String ntLongDesc, String ntStatus,
			Set<IclubCohortInvite> iclubCohortInvites,
			Set<IclubNotif> iclubNotifs) {
		this.ntId = ntId;
		this.ntShortDesc = ntShortDesc;
		this.ntLongDesc = ntLongDesc;
		this.ntStatus = ntStatus;
		this.iclubCohortInvites = iclubCohortInvites;
		this.iclubNotifs = iclubNotifs;
	}

	// Property accessors
	@Id
	@Column(name = "nt_id", unique = true, nullable = false)
	public Long getNtId() {
		return this.ntId;
	}

	public void setNtId(Long ntId) {
		this.ntId = ntId;
	}

	@Column(name = "nt_short_desc", length = 4)
	public String getNtShortDesc() {
		return this.ntShortDesc;
	}

	public void setNtShortDesc(String ntShortDesc) {
		this.ntShortDesc = ntShortDesc;
	}

	@Column(name = "nt_long_desc", length = 500)
	public String getNtLongDesc() {
		return this.ntLongDesc;
	}

	public void setNtLongDesc(String ntLongDesc) {
		this.ntLongDesc = ntLongDesc;
	}

	@Column(name = "nt_status", length = 1)
	public String getNtStatus() {
		return this.ntStatus;
	}

	public void setNtStatus(String ntStatus) {
		this.ntStatus = ntStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubNotificationType")
	public Set<IclubCohortInvite> getIclubCohortInvites() {
		return this.iclubCohortInvites;
	}

	public void setIclubCohortInvites(Set<IclubCohortInvite> iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubNotificationType")
	public Set<IclubNotif> getIclubNotifs() {
		return this.iclubNotifs;
	}

	public void setIclubNotifs(Set<IclubNotif> iclubNotifs) {
		this.iclubNotifs = iclubNotifs;
	}

}