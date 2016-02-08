package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubPayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_payment", catalog = "iclubdb")
public class IclubPayment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6892310805591087557L;
	private String PId;
	private IclubAccount iclubAccount;
	private IclubPaymentStatus iclubPaymentStatus;
	private IclubPerson iclubPerson;
	private IclubClaim iclubClaim;
	private IclubPolicy iclubPolicy;
	private Double PValue;
	private String PDrCrInd;
	private Date PGenDt;
	private Date PCrtdDt;

	// Constructors

	/** default constructor */
	public IclubPayment() {
	}

	/** minimal constructor */
	public IclubPayment(String PId) {
		this.PId = PId;
	}

	/** full constructor */
	public IclubPayment(String PId, IclubAccount iclubAccount, IclubPaymentStatus iclubPaymentStatus, IclubPerson iclubPerson, IclubClaim iclubClaim, IclubPolicy iclubPolicy, Double PValue, String PDrCrInd, Date PGenDt, Date PCrtdDt) {
		this.PId = PId;
		this.iclubAccount = iclubAccount;
		this.iclubPaymentStatus = iclubPaymentStatus;
		this.iclubPerson = iclubPerson;
		this.iclubClaim = iclubClaim;
		this.iclubPolicy = iclubPolicy;
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
	@JoinColumn(name = "p_policy_id")
	public IclubPolicy getIclubPolicy() {
		return this.iclubPolicy;
	}

	public void setIclubPolicy(IclubPolicy iclubPolicy) {
		this.iclubPolicy = iclubPolicy;
	}

	@Column(name = "p_value", precision = 15, scale = 5)
	public Double getPValue() {
		return this.PValue;
	}

	public void setPValue(Double PValue) {
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
	public Date getPGenDt() {
		return this.PGenDt;
	}

	public void setPGenDt(Date PGenDt) {
		this.PGenDt = PGenDt;
	}

	@Column(name = "p_crtd_dt", length = 19)
	public Date getPCrtdDt() {
		return this.PCrtdDt;
	}

	public void setPCrtdDt(Date PCrtdDt) {
		this.PCrtdDt = PCrtdDt;
	}

}