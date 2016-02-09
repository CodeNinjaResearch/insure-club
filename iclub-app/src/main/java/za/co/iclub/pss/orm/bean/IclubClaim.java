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
 * IclubClaim entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_claim", catalog = "iclubdb")
public class IclubClaim implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606012449479952321L;
	private String CId;
	private IclubClaimStatus iclubClaimStatus;
	private IclubPolicy iclubPolicy;
	private IclubPerson iclubPerson;
	private Long CNumber;
	private Integer CNumItems;
	private Double CValue;
	private Date CCrtdDt;
	private Set<IclubCohortClaim> iclubCohortClaims = new HashSet<IclubCohortClaim>(0);
	private Set<IclubClaimItem> iclubClaimItems = new HashSet<IclubClaimItem>(0);
	private Set<IclubPayment> iclubPayments = new HashSet<IclubPayment>(0);

	// Constructors

	/** default constructor */
	public IclubClaim() {
	}

	/** minimal constructor */
	public IclubClaim(String CId) {
		this.CId = CId;
	}

	/** full constructor */
	public IclubClaim(String CId, IclubClaimStatus iclubClaimStatus, IclubPolicy iclubPolicy, IclubPerson iclubPerson, Long CNumber, Integer CNumItems, Double CValue, Date CCrtdDt, Set<IclubCohortClaim> iclubCohortClaims, Set<IclubClaimItem> iclubClaimItems, Set<IclubPayment> iclubPayments) {
		this.CId = CId;
		this.iclubClaimStatus = iclubClaimStatus;
		this.iclubPolicy = iclubPolicy;
		this.iclubPerson = iclubPerson;
		this.CNumber = CNumber;
		this.CNumItems = CNumItems;
		this.CValue = CValue;
		this.CCrtdDt = CCrtdDt;
		this.iclubCohortClaims = iclubCohortClaims;
		this.iclubClaimItems = iclubClaimItems;
		this.iclubPayments = iclubPayments;
	}

	// Property accessors
	@Id
	@Column(name = "c_id", unique = true, nullable = false, length = 36)
	public String getCId() {
		return this.CId;
	}

	public void setCId(String CId) {
		this.CId = CId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_status_id")
	public IclubClaimStatus getIclubClaimStatus() {
		return this.iclubClaimStatus;
	}

	public void setIclubClaimStatus(IclubClaimStatus iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_policy_id")
	public IclubPolicy getIclubPolicy() {
		return this.iclubPolicy;
	}

	public void setIclubPolicy(IclubPolicy iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "c_number")
	public Long getCNumber() {
		return this.CNumber;
	}

	public void setCNumber(Long CNumber) {
		this.CNumber = CNumber;
	}

	@Column(name = "c_num_items")
	public Integer getCNumItems() {
		return this.CNumItems;
	}

	public void setCNumItems(Integer CNumItems) {
		this.CNumItems = CNumItems;
	}

	@Column(name = "c_value", precision = 15, scale = 5)
	public Double getCValue() {
		return this.CValue;
	}

	public void setCValue(Double CValue) {
		this.CValue = CValue;
	}

	@Column(name = "c_crtd_dt", length = 19)
	public Date getCCrtdDt() {
		return this.CCrtdDt;
	}

	public void setCCrtdDt(Date CCrtdDt) {
		this.CCrtdDt = CCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubClaim")
	public Set<IclubCohortClaim> getIclubCohortClaims() {
		return this.iclubCohortClaims;
	}

	public void setIclubCohortClaims(Set<IclubCohortClaim> iclubCohortClaims) {
		this.iclubCohortClaims = iclubCohortClaims;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubClaim")
	public Set<IclubClaimItem> getIclubClaimItems() {
		return this.iclubClaimItems;
	}

	public void setIclubClaimItems(Set<IclubClaimItem> iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubClaim")
	public Set<IclubPayment> getIclubPayments() {
		return this.iclubPayments;
	}

	public void setIclubPayments(Set<IclubPayment> iclubPayments) {
		this.iclubPayments = iclubPayments;
	}

}