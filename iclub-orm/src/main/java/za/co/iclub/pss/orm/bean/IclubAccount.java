package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	private static final long serialVersionUID = 5082887782355260582L;
	private Integer AId;
	private IclubAccountType iclubAccountType;
	private IclubBankMaster iclubBankMaster;
	private IclubOwnerType iclubOwnerType;
	private IclubPerson iclubPerson;
	private String AAccNum;
	private String AOwnerId;
	private String AStatus;
	private Timestamp ACrtdDt;

	// Constructors

	/** default constructor */
	public IclubAccount() {
	}

	/** minimal constructor */
	public IclubAccount(Integer AId) {
		this.AId = AId;
	}

	/** full constructor */
	public IclubAccount(Integer AId, IclubAccountType iclubAccountType,
			IclubBankMaster iclubBankMaster, IclubOwnerType iclubOwnerType,
			IclubPerson iclubPerson, String AAccNum, String AOwnerId,
			String AStatus, Timestamp ACrtdDt) {
		this.AId = AId;
		this.iclubAccountType = iclubAccountType;
		this.iclubBankMaster = iclubBankMaster;
		this.iclubOwnerType = iclubOwnerType;
		this.iclubPerson = iclubPerson;
		this.AAccNum = AAccNum;
		this.AOwnerId = AOwnerId;
		this.AStatus = AStatus;
		this.ACrtdDt = ACrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "a_id", unique = true, nullable = false)
	public Integer getAId() {
		return this.AId;
	}

	public void setAId(Integer AId) {
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
	public Timestamp getACrtdDt() {
		return this.ACrtdDt;
	}

	public void setACrtdDt(Timestamp ACrtdDt) {
		this.ACrtdDt = ACrtdDt;
	}

}