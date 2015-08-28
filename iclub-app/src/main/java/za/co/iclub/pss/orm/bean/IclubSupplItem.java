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
 * IclubSupplItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_suppl_item", catalog = "iclubdb")
public class IclubSupplItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -811229621375112939L;
	private String siId;
	private IclubSupplMaster iclubSupplMaster;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private IclubAssessmentType iclubAssessmentType;
	private Long siAssessNumber;
	private Date siCrtdDt;
	private String siItemId;

	// Constructors

	/** default constructor */
	public IclubSupplItem() {
	}

	/** minimal constructor */
	public IclubSupplItem(String siId) {
		this.siId = siId;
	}

	/** full constructor */
	public IclubSupplItem(String siId, IclubSupplMaster iclubSupplMaster,
			IclubInsuranceItemType iclubInsuranceItemType,
			IclubPerson iclubPerson, IclubAssessmentType iclubAssessmentType,
			Long siAssessNumber, Date siCrtdDt, String siItemId) {
		this.siId = siId;
		this.iclubSupplMaster = iclubSupplMaster;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.iclubAssessmentType = iclubAssessmentType;
		this.siAssessNumber = siAssessNumber;
		this.siCrtdDt = siCrtdDt;
		this.siItemId = siItemId;
	}

	// Property accessors
	@Id
	@Column(name = "si_id", unique = true, nullable = false, length = 36)
	public String getSiId() {
		return this.siId;
	}

	public void setSiId(String siId) {
		this.siId = siId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "si_suppl_id")
	public IclubSupplMaster getIclubSupplMaster() {
		return this.iclubSupplMaster;
	}

	public void setIclubSupplMaster(IclubSupplMaster iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "si_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(
			IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "si_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "si_assess_type_id")
	public IclubAssessmentType getIclubAssessmentType() {
		return this.iclubAssessmentType;
	}

	public void setIclubAssessmentType(IclubAssessmentType iclubAssessmentType) {
		this.iclubAssessmentType = iclubAssessmentType;
	}

	@Column(name = "si_assess_number")
	public Long getSiAssessNumber() {
		return this.siAssessNumber;
	}

	public void setSiAssessNumber(Long siAssessNumber) {
		this.siAssessNumber = siAssessNumber;
	}

	@Column(name = "si_crtd_dt", length = 19)
	public Date getSiCrtdDt() {
		return this.siCrtdDt;
	}

	public void setSiCrtdDt(Date siCrtdDt) {
		this.siCrtdDt = siCrtdDt;
	}

	@Column(name = "si_item_id", length = 36)
	public String getSiItemId() {
		return this.siItemId;
	}

	public void setSiItemId(String siItemId) {
		this.siItemId = siItemId;
	}

}