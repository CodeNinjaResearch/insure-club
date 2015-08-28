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
 * IclubClaimItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_claim_item", catalog = "iclubdb")
public class IclubClaimItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5879678092631584840L;
	private String ciId;
	private IclubSupplMaster iclubSupplMasterByCiHandlerId;
	private IclubInsuranceItem iclubInsuranceItem;
	private IclubSupplMaster iclubSupplMasterByCiAssesorId;
	private IclubClaim iclubClaim;
	private IclubClaimStatus iclubClaimStatus;
	private Double ciValue;
	private String ciCrtdBy;
	private Date ciCrtdDt;

	// Constructors

	/** default constructor */
	public IclubClaimItem() {
	}

	/** minimal constructor */
	public IclubClaimItem(String ciId) {
		this.ciId = ciId;
	}

	/** full constructor */
	public IclubClaimItem(String ciId,
			IclubSupplMaster iclubSupplMasterByCiHandlerId,
			IclubInsuranceItem iclubInsuranceItem,
			IclubSupplMaster iclubSupplMasterByCiAssesorId,
			IclubClaim iclubClaim, IclubClaimStatus iclubClaimStatus,
			Double ciValue, String ciCrtdBy, Date ciCrtdDt) {
		this.ciId = ciId;
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
		this.iclubInsuranceItem = iclubInsuranceItem;
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
		this.iclubClaim = iclubClaim;
		this.iclubClaimStatus = iclubClaimStatus;
		this.ciValue = ciValue;
		this.ciCrtdBy = ciCrtdBy;
		this.ciCrtdDt = ciCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "ci_id", unique = true, nullable = false, length = 36)
	public String getCiId() {
		return this.ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_handler_id")
	public IclubSupplMaster getIclubSupplMasterByCiHandlerId() {
		return this.iclubSupplMasterByCiHandlerId;
	}

	public void setIclubSupplMasterByCiHandlerId(
			IclubSupplMaster iclubSupplMasterByCiHandlerId) {
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_item_id")
	public IclubInsuranceItem getIclubInsuranceItem() {
		return this.iclubInsuranceItem;
	}

	public void setIclubInsuranceItem(IclubInsuranceItem iclubInsuranceItem) {
		this.iclubInsuranceItem = iclubInsuranceItem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_assesor_id")
	public IclubSupplMaster getIclubSupplMasterByCiAssesorId() {
		return this.iclubSupplMasterByCiAssesorId;
	}

	public void setIclubSupplMasterByCiAssesorId(
			IclubSupplMaster iclubSupplMasterByCiAssesorId) {
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_claim_id")
	public IclubClaim getIclubClaim() {
		return this.iclubClaim;
	}

	public void setIclubClaim(IclubClaim iclubClaim) {
		this.iclubClaim = iclubClaim;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_status_id")
	public IclubClaimStatus getIclubClaimStatus() {
		return this.iclubClaimStatus;
	}

	public void setIclubClaimStatus(IclubClaimStatus iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	@Column(name = "ci_value", precision = 15, scale = 5)
	public Double getCiValue() {
		return this.ciValue;
	}

	public void setCiValue(Double ciValue) {
		this.ciValue = ciValue;
	}

	@Column(name = "ci_crtd_by", length = 36)
	public String getCiCrtdBy() {
		return this.ciCrtdBy;
	}

	public void setCiCrtdBy(String ciCrtdBy) {
		this.ciCrtdBy = ciCrtdBy;
	}

	@Column(name = "ci_crtd_dt", length = 19)
	public Date getCiCrtdDt() {
		return this.ciCrtdDt;
	}

	public void setCiCrtdDt(Date ciCrtdDt) {
		this.ciCrtdDt = ciCrtdDt;
	}

}