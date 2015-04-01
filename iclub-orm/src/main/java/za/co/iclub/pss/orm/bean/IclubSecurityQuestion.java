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
 * IclubSecurityQuestion entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_security_question")
public class IclubSecurityQuestion implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4564368744994956599L;
	private Long sqId;
	private String sqShortDesc;
	private String sqLongDesc;
	private String sqStatus;
	private Set<IclubLogin> iclubLogins = new HashSet<IclubLogin>(0);

	// Constructors

	/** default constructor */
	public IclubSecurityQuestion() {
	}

	/** minimal constructor */
	public IclubSecurityQuestion(Long sqId) {
		this.sqId = sqId;
	}

	/** full constructor */
	public IclubSecurityQuestion(Long sqId, String sqShortDesc, String sqLongDesc, String sqStatus, Set<IclubLogin> iclubLogins) {
		this.sqId = sqId;
		this.sqShortDesc = sqShortDesc;
		this.sqLongDesc = sqLongDesc;
		this.sqStatus = sqStatus;
		this.iclubLogins = iclubLogins;
	}

	// Property accessors
	@Id
	@Column(name = "sq_id", unique = true, nullable = false)
	public Long getSqId() {
		return this.sqId;
	}

	public void setSqId(Long sqId) {
		this.sqId = sqId;
	}

	@Column(name = "sq_short_desc", length = 4)
	public String getSqShortDesc() {
		return this.sqShortDesc;
	}

	public void setSqShortDesc(String sqShortDesc) {
		this.sqShortDesc = sqShortDesc;
	}

	@Column(name = "sq_long_desc", length = 500)
	public String getSqLongDesc() {
		return this.sqLongDesc;
	}

	public void setSqLongDesc(String sqLongDesc) {
		this.sqLongDesc = sqLongDesc;
	}

	@Column(name = "sq_status", length = 1)
	public String getSqStatus() {
		return this.sqStatus;
	}

	public void setSqStatus(String sqStatus) {
		this.sqStatus = sqStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSecurityQuestion")
	public Set<IclubLogin> getIclubLogins() {
		return this.iclubLogins;
	}

	public void setIclubLogins(Set<IclubLogin> iclubLogins) {
		this.iclubLogins = iclubLogins;
	}

}