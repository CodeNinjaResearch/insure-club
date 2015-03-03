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
 * IclubAccountType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_account_type", catalog = "iclubdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getAccountTypeBySD", query = "select * from iclub_account_type where lower(at_short_desc) = lower(:sd) and at_id <> :id", resultClass = IclubAccountType.class) })
public class IclubAccountType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6974495836946733505L;
	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	private Set<IclubAccount> iclubAccounts = new HashSet<IclubAccount>(0);

	// Constructors

	/** default constructor */
	public IclubAccountType() {
	}

	/** minimal constructor */
	public IclubAccountType(Long atId) {
		this.atId = atId;
	}

	/** full constructor */
	public IclubAccountType(Long atId, String atShortDesc, String atLongDesc, String atStatus, Set<IclubAccount> iclubAccounts) {
		this.atId = atId;
		this.atShortDesc = atShortDesc;
		this.atLongDesc = atLongDesc;
		this.atStatus = atStatus;
		this.iclubAccounts = iclubAccounts;
	}

	// Property accessors
	@Id
	@Column(name = "at_id", unique = true, nullable = false)
	public Long getAtId() {
		return this.atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	@Column(name = "at_short_desc", length = 4)
	public String getAtShortDesc() {
		return this.atShortDesc;
	}

	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}

	@Column(name = "at_long_desc", length = 500)
	public String getAtLongDesc() {
		return this.atLongDesc;
	}

	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}

	@Column(name = "at_status", length = 1)
	public String getAtStatus() {
		return this.atStatus;
	}

	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccountType")
	public Set<IclubAccount> getIclubAccounts() {
		return this.iclubAccounts;
	}

	public void setIclubAccounts(Set<IclubAccount> iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

}