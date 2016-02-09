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
 * IclubOwnerType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_owner_type", catalog = "iclubdb")
public class IclubOwnerType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2167188022875393892L;
	private Long otId;
	private String otShortDesc;
	private String otLongDesc;
	private String otStatus;
	private Set<IclubAccount> iclubAccounts = new HashSet<IclubAccount>(0);

	// Constructors

	/** default constructor */
	public IclubOwnerType() {
	}

	/** minimal constructor */
	public IclubOwnerType(Long otId) {
		this.otId = otId;
	}

	/** full constructor */
	public IclubOwnerType(Long otId, String otShortDesc, String otLongDesc, String otStatus, Set<IclubAccount> iclubAccounts) {
		this.otId = otId;
		this.otShortDesc = otShortDesc;
		this.otLongDesc = otLongDesc;
		this.otStatus = otStatus;
		this.iclubAccounts = iclubAccounts;
	}

	// Property accessors
	@Id
	@Column(name = "ot_id", unique = true, nullable = false)
	public Long getOtId() {
		return this.otId;
	}

	public void setOtId(Long otId) {
		this.otId = otId;
	}

	@Column(name = "ot_short_desc", length = 4)
	public String getOtShortDesc() {
		return this.otShortDesc;
	}

	public void setOtShortDesc(String otShortDesc) {
		this.otShortDesc = otShortDesc;
	}

	@Column(name = "ot_long_desc", length = 500)
	public String getOtLongDesc() {
		return this.otLongDesc;
	}

	public void setOtLongDesc(String otLongDesc) {
		this.otLongDesc = otLongDesc;
	}

	@Column(name = "ot_status", length = 1)
	public String getOtStatus() {
		return this.otStatus;
	}

	public void setOtStatus(String otStatus) {
		this.otStatus = otStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubOwnerType")
	public Set<IclubAccount> getIclubAccounts() {
		return this.iclubAccounts;
	}

	public void setIclubAccounts(Set<IclubAccount> iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

}