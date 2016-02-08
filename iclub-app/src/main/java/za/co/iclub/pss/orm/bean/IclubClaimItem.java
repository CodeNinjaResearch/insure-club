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
	private static final long serialVersionUID = -7200001278962990541L;
	private String ciId;
	private IclubClaimStatus iclubClaimStatus;
	private IclubSupplMaster iclubSupplMasterByCiAssesorId;
	private IclubInsuranceItem iclubInsuranceItem;
	private IclubSupplMaster iclubSupplMasterByCiHandlerId;
	private IclubClaim iclubClaim;
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
	public IclubClaimItem(String ciId, IclubClaimStatus iclubClaimStatus, IclubSupplMaster iclubSupplMasterByCiAssesorId, IclubInsuranceItem iclubInsuranceItem, IclubSupplMaster iclubSupplMasterByCiHandlerId, IclubClaim iclubClaim, Double ciValue, String ciCrtdBy, Date ciCrtdDt) {
		this.ciId = ciId;
		this.iclubClaimStatus = iclubClaimStatus;
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
		this.iclubInsuranceItem = iclubInsuranceItem;
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
		this.iclubClaim = iclubClaim;
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
	@JoinColumn(name = "ci_status_id")
	public IclubClaimStatus getIclubClaimStatus() {
		return this.iclubClaimStatus;
	}

	public void setIclubClaimStatus(IclubClaimStatus iclubClaimStatus) {
		this.iclubClaimStatus = iclubClaimStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_assesor_id")
	public IclubSupplMaster getIclubSupplMasterByCiAssesorId() {
		return this.iclubSupplMasterByCiAssesorId;
	}

	public void setIclubSupplMasterByCiAssesorId(IclubSupplMaster iclubSupplMasterByCiAssesorId) {
		this.iclubSupplMasterByCiAssesorId = iclubSupplMasterByCiAssesorId;
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
	@JoinColumn(name = "ci_handler_id")
	public IclubSupplMaster getIclubSupplMasterByCiHandlerId() {
		return this.iclubSupplMasterByCiHandlerId;
	}

	public void setIclubSupplMasterByCiHandlerId(IclubSupplMaster iclubSupplMasterByCiHandlerId) {
		this.iclubSupplMasterByCiHandlerId = iclubSupplMasterByCiHandlerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ci_claim_id")
	public IclubClaim getIclubClaim() {
		return this.iclubClaim;
	}

	public void setIclubClaim(IclubClaim iclubClaim) {
		this.iclubClaim = iclubClaim;
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