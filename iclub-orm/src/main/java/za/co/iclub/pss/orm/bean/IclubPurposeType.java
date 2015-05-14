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
 * IclubPurposeType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_purpose_type", catalog = "iclubdb")
public class IclubPurposeType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 174942980029437864L;
	private Long ptId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Date ptCrtdDt;

	// Constructors

	/** default constructor */
	public IclubPurposeType() {
	}

	/** minimal constructor */
	public IclubPurposeType(Long ptId) {
		this.ptId = ptId;
	}

	/** full constructor */
	public IclubPurposeType(Long ptId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPerson, String ptShortDesc, String ptLongDesc, String ptStatus, Date ptCrtdDt) {
		this.ptId = ptId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.ptShortDesc = ptShortDesc;
		this.ptLongDesc = ptLongDesc;
		this.ptStatus = ptStatus;
		this.ptCrtdDt = ptCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "pt_id", unique = true, nullable = false)
	public Long getPtId() {
		return this.ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pt_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pt_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "pt_short_desc", length = 4)
	public String getPtShortDesc() {
		return this.ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	@Column(name = "pt_long_desc", length = 450)
	public String getPtLongDesc() {
		return this.ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	@Column(name = "pt_status", length = 1)
	public String getPtStatus() {
		return this.ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

	@Column(name = "pt_crtd_dt", length = 19)
	public Date getPtCrtdDt() {
		return this.ptCrtdDt;
	}

	public void setPtCrtdDt(Date ptCrtdDt) {
		this.ptCrtdDt = ptCrtdDt;
	}

}