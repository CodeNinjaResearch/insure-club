package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_payment")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_payment where p_crtd_by=:id", name = "getPaymentByUser", resultClass = IclubPayment.class) })
public class IclubPayment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7471030628228469297L;
	private String PId;
	private IclubPolicy iclubPolicy;
	private IclubPerson iclubPerson;
	private IclubClaim iclubClaim;
	private IclubAccount iclubAccount;
	private IclubPaymentStatus iclubPaymentStatus;
	private Long PValue;
	private String PDrCrInd;
	private Timestamp PGenDt;
	private Timestamp PCrtdDt;

	// Constructors

	/** default constructor */
	public IclubPayment() {
	}

	/** minimal constructor */
	public IclubPayment(String PId) {
		this.PId = PId;
	}

	/** full constructor */
	public IclubPayment(String PId, IclubPolicy iclubPolicy, IclubPerson iclubPerson, IclubClaim iclubClaim, IclubAccount iclubAccount, IclubPaymentStatus iclubPaymentStatus, Long PValue, String PDrCrInd, Timestamp PGenDt, Timestamp PCrtdDt) {
		this.PId = PId;
		this.iclubPolicy = iclubPolicy;
		this.iclubPerson = iclubPerson;
		this.iclubClaim = iclubClaim;
		this.iclubAccount = iclubAccount;
		this.iclubPaymentStatus = iclubPaymentStatus;
		this.PValue = PValue;
		this.PDrCrInd = PDrCrInd;
		this.PGenDt = PGenDt;
		this.PCrtdDt = PCrtdDt;
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
	@JoinColumn(name = "p_policy_id")
	public IclubPolicy getIclubPolicy() {
		return this.iclubPolicy;
	}

	public void setIclubPolicy(IclubPolicy iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
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
	@JoinColumn(name = "p_claim_id")
	public IclubClaim getIclubClaim() {
		return this.iclubClaim;
	}

	public void setIclubClaim(IclubClaim iclubClaim) {
		this.iclubClaim = iclubClaim;
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
	@JoinColumn(name = "p_status")
	public IclubPaymentStatus getIclubPaymentStatus() {
		return this.iclubPaymentStatus;
	}

	public void setIclubPaymentStatus(IclubPaymentStatus iclubPaymentStatus) {
		this.iclubPaymentStatus = iclubPaymentStatus;
	}

	@Column(name = "p_value", precision = 15, scale = 5)
	public Long getPValue() {
		return this.PValue;
	}

	public void setPValue(Long PValue) {
		this.PValue = PValue;
	}

	@Column(name = "p_dr_cr_ind", length = 1)
	public String getPDrCrInd() {
		return this.PDrCrInd;
	}

	public void setPDrCrInd(String PDrCrInd) {
		this.PDrCrInd = PDrCrInd;
	}

	@Column(name = "p_gen_dt", length = 19)
	public Timestamp getPGenDt() {
		return this.PGenDt;
	}

	public void setPGenDt(Timestamp PGenDt) {
		this.PGenDt = PGenDt;
	}

	@Column(name = "p_crtd_dt", length = 19)
	public Timestamp getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(Timestamp PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

}