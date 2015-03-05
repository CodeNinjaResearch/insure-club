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
@Table(name = "iclub_policy", uniqueConstraints = @UniqueConstraint(columnNames = "p_number"))
public class IclubPolicy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1465948584010792421L;
	private String PId;
	private IclubPolicyStatus iclubPolicyStatus;
	private IclubQuote iclubQuote;
	private IclubPerson iclubPerson;
	private IclubAccount iclubAccount;
	private Long PNumber;
	private Long PPremium;
	private Long PProrataPrm;
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
	public IclubPolicy(String PId, IclubPolicyStatus iclubPolicyStatus, IclubQuote iclubQuote, IclubPerson iclubPerson, IclubAccount iclubAccount, Long PNumber, Long PPremium, Long PProrataPrm, Integer PDebitDt, String PCrtdDt, Set<IclubClaim> iclubClaims, Set<IclubPayment> iclubPayments) {
		this.PId = PId;
		this.iclubPolicyStatus = iclubPolicyStatus;
		this.iclubQuote = iclubQuote;
		this.iclubPerson = iclubPerson;
		this.iclubAccount = iclubAccount;
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
	@JoinColumn(name = "p_status_id")
	public IclubPolicyStatus getIclubPolicyStatus() {
		return this.iclubPolicyStatus;
	}

	public void setIclubPolicyStatus(IclubPolicyStatus iclubPolicyStatus) {
		this.iclubPolicyStatus = iclubPolicyStatus;
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
	@JoinColumn(name = "p_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_account_id")
	public IclubAccount getIclubAccount() {
		return this.iclubAccount;
	}

	public void setIclubAccount(IclubAccount iclubAccount) {
		this.iclubAccount = iclubAccount;
	}

	@Column(name = "p_number", unique = true, nullable = false)
	public Long getPNumber() {
		return this.PNumber;
	}

	public void setPNumber(Long PNumber) {
		this.PNumber = PNumber;
	}

	@Column(name = "p_premium", precision = 15, scale = 5)
	public Long getPPremium() {
		return this.PPremium;
	}

	public void setPPremium(Long PPremium) {
		this.PPremium = PPremium;
	}

	@Column(name = "p_prorata_prm", precision = 15, scale = 5)
	public Long getPProrataPrm() {
		return this.PProrataPrm;
	}

	public void setPProrataPrm(Long PProrataPrm) {
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