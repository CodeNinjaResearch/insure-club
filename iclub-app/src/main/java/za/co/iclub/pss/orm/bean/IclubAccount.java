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

/**
 * IclubAccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_account", catalog = "iclubdb")
public class IclubAccount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3406911225383084349L;
	private String AId;
	private IclubAccountType iclubAccountType;
	private IclubBankMaster iclubBankMaster;
	private IclubOwnerType iclubOwnerType;
	private IclubPerson iclubPerson;
	private String AAccNum;
	private String AOwnerId;
	private String AStatus;
	private Date ACrtdDt;
	private Set<IclubPolicy> iclubPolicies = new HashSet<IclubPolicy>(0);
	private Set<IclubPayment> iclubPayments = new HashSet<IclubPayment>(0);

	// Constructors

	/** default constructor */
	public IclubAccount() {
	}

	/** minimal constructor */
	public IclubAccount(String AId) {
		this.AId = AId;
	}

	/** full constructor */
	public IclubAccount(String AId, IclubAccountType iclubAccountType,
			IclubBankMaster iclubBankMaster, IclubOwnerType iclubOwnerType,
			IclubPerson iclubPerson, String AAccNum, String AOwnerId,
			String AStatus, Date ACrtdDt, Set<IclubPolicy> iclubPolicies,
			Set<IclubPayment> iclubPayments) {
		this.AId = AId;
		this.iclubAccountType = iclubAccountType;
		this.iclubBankMaster = iclubBankMaster;
		this.iclubOwnerType = iclubOwnerType;
		this.iclubPerson = iclubPerson;
		this.AAccNum = AAccNum;
		this.AOwnerId = AOwnerId;
		this.AStatus = AStatus;
		this.ACrtdDt = ACrtdDt;
		this.iclubPolicies = iclubPolicies;
		this.iclubPayments = iclubPayments;
	}

	// Property accessors
	@Id
	@Column(name = "a_id", unique = true, nullable = false, length = 36)
	public String getAId() {
		return this.AId;
	}

	public void setAId(String AId) {
		this.AId = AId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "a_acc_type_id")
	public IclubAccountType getIclubAccountType() {
		return this.iclubAccountType;
	}

	public void setIclubAccountType(IclubAccountType iclubAccountType) {
		this.iclubAccountType = iclubAccountType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "a_bm_id")
	public IclubBankMaster getIclubBankMaster() {
		return this.iclubBankMaster;
	}

	public void setIclubBankMaster(IclubBankMaster iclubBankMaster) {
		this.iclubBankMaster = iclubBankMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "a_owner_type")
	public IclubOwnerType getIclubOwnerType() {
		return this.iclubOwnerType;
	}

	public void setIclubOwnerType(IclubOwnerType iclubOwnerType) {
		this.iclubOwnerType = iclubOwnerType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "a_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "a_acc_num", length = 13)
	public String getAAccNum() {
		return this.AAccNum;
	}

	public void setAAccNum(String AAccNum) {
		this.AAccNum = AAccNum;
	}

	@Column(name = "a_owner_id", length = 36)
	public String getAOwnerId() {
		return this.AOwnerId;
	}

	public void setAOwnerId(String AOwnerId) {
		this.AOwnerId = AOwnerId;
	}

	@Column(name = "a_status", length = 45)
	public String getAStatus() {
		return this.AStatus;
	}

	public void setAStatus(String AStatus) {
		this.AStatus = AStatus;
	}

	@Column(name = "a_crtd_dt", length = 19)
	public Date getACrtdDt() {
		return this.ACrtdDt;
	}

	public void setACrtdDt(Date ACrtdDt) {
		this.ACrtdDt = ACrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccount")
	public Set<IclubPolicy> getIclubPolicies() {
		return this.iclubPolicies;
	}

	public void setIclubPolicies(Set<IclubPolicy> iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAccount")
	public Set<IclubPayment> getIclubPayments() {
		return this.iclubPayments;
	}

	public void setIclubPayments(Set<IclubPayment> iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

}