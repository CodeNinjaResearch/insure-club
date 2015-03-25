package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubNotificationType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_notification_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getNotificationTypeBySD", query = "select * from iclub_notification_type where lower(nt_short_desc) = lower(:sd) and nt_id <> :id", resultClass = IclubNotificationType.class) })
public class IclubNotificationType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4410660226310612623L;
	private Long ntId;
	private String ntShortDesc;
	private String ntLongDesc;
	private String ntStatus;
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
	public IclubNotificationType(Long ntId, String ntShortDesc, String ntLongDesc, String ntStatus, Set<IclubNotif> iclubNotifs) {
		this.ntId = ntId;
		this.ntShortDesc = ntShortDesc;
		this.ntLongDesc = ntLongDesc;
		this.ntStatus = ntStatus;
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
	public Set<IclubNotif> getIclubNotifs() {
		return this.iclubNotifs;
	}

	public void setIclubNotifs(Set<IclubNotif> iclubNotifs) {
		this.iclubNotifs = iclubNotifs;
	}

}