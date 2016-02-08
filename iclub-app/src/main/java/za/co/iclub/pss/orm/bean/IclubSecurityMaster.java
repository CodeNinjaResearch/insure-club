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
 * IclubSecurityMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_security_master", catalog = "iclubdb")
public class IclubSecurityMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2045333679117468684L;
	private String smId;
	private IclubPerson iclubPerson;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private String smDesc;
	private String smStatus;
	private Date smCrtdDt;

	// Constructors

	/** default constructor */
	public IclubSecurityMaster() {
	}

	/** minimal constructor */
	public IclubSecurityMaster(String smId) {
		this.smId = smId;
	}

	/** full constructor */
	public IclubSecurityMaster(String smId, IclubPerson iclubPerson, IclubInsuranceItemType iclubInsuranceItemType, String smDesc, String smStatus, Date smCrtdDt) {
		this.smId = smId;
		this.iclubPerson = iclubPerson;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
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
	@JoinColumn(name = "sm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
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
	public Date getSmCrtdDt() {
		return this.smCrtdDt;
	}

	public void setSmCrtdDt(Date smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

}