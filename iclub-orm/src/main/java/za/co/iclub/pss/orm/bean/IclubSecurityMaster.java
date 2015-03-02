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
 * IclubSecurityMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_security_master", catalog = "iclubdb")
public class IclubSecurityMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6304141126750022549L;
	private String smId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private String smDesc;
	private String smStatus;
	private Timestamp smCrtdDt;

	// Constructors

	/** default constructor */
	public IclubSecurityMaster() {
	}

	/** minimal constructor */
	public IclubSecurityMaster(String smId) {
		this.smId = smId;
	}

	/** full constructor */
	public IclubSecurityMaster(String smId,
			IclubInsuranceItemType iclubInsuranceItemType,
			IclubPerson iclubPerson, String smDesc, String smStatus,
			Timestamp smCrtdDt) {
		this.smId = smId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.smDesc = smDesc;
		this.smStatus = smStatus;
		this.smCrtdDt = smCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "sm_id", unique = true, nullable = false, length = 36)
	public String getSmId() {
		return this.smId;
	}

	public void setSmId(String smId) {
		this.smId = smId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(
			IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "sm_desc", length = 250)
	public String getSmDesc() {
		return this.smDesc;
	}

	public void setSmDesc(String smDesc) {
		this.smDesc = smDesc;
	}

	@Column(name = "sm_status", length = 1)
	public String getSmStatus() {
		return this.smStatus;
	}

	public void setSmStatus(String smStatus) {
		this.smStatus = smStatus;
	}

	@Column(name = "sm_crtd_dt", length = 19)
	public Timestamp getSmCrtdDt() {
		return this.smCrtdDt;
	}

	public void setSmCrtdDt(Timestamp smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

}