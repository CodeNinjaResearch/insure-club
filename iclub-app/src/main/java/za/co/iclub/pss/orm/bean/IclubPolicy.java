package za.co.iclub.pss.orm.bean;

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
import javax.persistence.UniqueConstraint;

/**
 * IclubPolicy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_policy", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "p_number"))
public class IclubPolicy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4175017855380226152L;
	private String PId;
	private IclubAccount iclubAccount;
	private IclubQuote iclubQuote;
	private IclubPolicyStatus iclubPolicyStatus;
	private IclubPerson iclubPerson;
	private Long PNumber;
	private Double PPremium;
	private Double PProrataPrm;
	private Integer PDebitDt;
	private String PCrtdDt;
	private Set<IclubClaim> iclubClaims = new HashSet<IclubClaim>(0);
	private Set<IclubPayment> iclubPayments = new HashSet<IclubPayment>(0);

	// Constructors

	/** default constructor */
	public IclubPolicy() {
	}

	/** minimal constructor */
	public IclubPolicy(String PId, Long PNumber) {
		this.PId = PId;
		this.PNumber = PNumber;
	}

	/** full constructor */
	public IclubPolicy(String PId, IclubAccount iclubAccount, IclubQuote iclubQuote, IclubPolicyStatus iclubPolicyStatus, IclubPerson iclubPerson, Long PNumber, Double PPremium, Double PProrataPrm, Integer PDebitDt, String PCrtdDt, Set<IclubClaim> iclubClaims, Set<IclubPayment> iclubPayments) {
		this.PId = PId;
		this.iclubAccount = iclubAccount;
		this.iclubQuote = iclubQuote;
		this.iclubPolicyStatus = iclubPolicyStatus;
		this.iclubPerson = iclubPerson;
		this.PNumber = PNumber;
		this.PPremium = PPremium;
		this.PProrataPrm = PProrataPrm;
		this.PDebitDt = PDebitDt;
		this.PCrtdDt = PCrtdDt;
		this.iclubClaims = iclubClaims;
		this.iclubPayments = iclubPayments;
	}

	// Property accessors
	@Id
	@Column(name = "p_id", unique = true, nullable = false, length = 36)
	public String getPId() {
		return this.PId;
	}

	public void setPId(String PId) {
		this.PId = PId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_account_id")
	public IclubAccount getIclubAccount() {
		return this.iclubAccount;
	}

	public void setIclubAccount(IclubAccount iclubAccount) {
		this.iclubAccount = iclubAccount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_quote_id")
	public IclubQuote getIclubQuote() {
		return this.iclubQuote;
	}

	public void setIclubQuote(IclubQuote iclubQuote) {
		this.iclubQuote = iclubQuote;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_status_id")
	public IclubPolicyStatus getIclubPolicyStatus() {
		return this.iclubPolicyStatus;
	}

	public void setIclubPolicyStatus(IclubPolicyStatus iclubPolicyStatus) {
		this.iclubPolicyStatus = iclubPolicyStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "p_number", unique = true, nullable = false)
	public Long getPNumber() {
		return this.PNumber;
	}

	public void setPNumber(Long PNumber) {
		this.PNumber = PNumber;
	}

	@Column(name = "p_premium", precision = 15, scale = 5)
	public Double getPPremium() {
		return this.PPremium;
	}

	public void setPPremium(Double PPremium) {
		this.PPremium = PPremium;
	}

	@Column(name = "p_prorata_prm", precision = 15, scale = 5)
	public Double getPProrataPrm() {
		return this.PProrataPrm;
	}

	public void setPProrataPrm(Double PProrataPrm) {
		this.PProrataPrm = PProrataPrm;
	}

	@Column(name = "p_debit_dt")
	public Integer getPDebitDt() {
		return this.PDebitDt;
	}

	public void setPDebitDt(Integer PDebitDt) {
		this.PDebitDt = PDebitDt;
	}

	@Column(name = "p_crtd_dt", length = 45)
	public String getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(String PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPolicy")
	public Set<IclubClaim> getIclubClaims() {
		return this.iclubClaims;
	}

	public void setIclubClaims(Set<IclubClaim> iclubClaims) {
		this.iclubClaims = iclubClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPolicy")
	public Set<IclubPayment> getIclubPayments() {
		return this.iclubPayments;
	}

	public void setIclubPayments(Set<IclubPayment> iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

}