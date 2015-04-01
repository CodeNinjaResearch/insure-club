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
 * IclubRoleType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_role_type")
public class IclubRoleType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5402385345055521646L;
	private Long rtId;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;
	private Set<IclubLogin> iclubLogins = new HashSet<IclubLogin>(0);

	// Constructors

	/** default constructor */
	public IclubRoleType() {
	}

	/** minimal constructor */
	public IclubRoleType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRoleType(Long rtId, String rtShortDesc, String rtLongDesc, String rtStatus, Set<IclubLogin> iclubLogins) {
		this.rtId = rtId;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtStatus = rtStatus;
		this.iclubLogins = iclubLogins;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubRoleType")
	public Set<IclubLogin> getIclubLogins() {
		return this.iclubLogins;
	}

	public void setIclubLogins(Set<IclubLogin> iclubLogins) {
		this.iclubLogins = iclubLogins;
	}

}