package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubInsuranceItemType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurance_item_type", catalog = "iclubdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getInsuranceItemTypeBySD", query = "select * from iclub_insurance_item_type where lower(iit_short_desc) = lower(:sd) and iit_id <> :id", resultClass = IclubInsuranceItemType.class) })
public class IclubInsuranceItemType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8538428444190363704L;
	private Long iitId;
	private String iitShortDesc;
	private String iitLongDesc;
	private String iitStatus;
	private Set<IclubSecurityMaster> iclubSecurityMasters = new HashSet<IclubSecurityMaster>(
			0);
	private Set<IclubPurposeType> iclubPurposeTypes = new HashSet<IclubPurposeType>(
			0);
	private Set<IclubCoverType> iclubCoverTypes = new HashSet<IclubCoverType>(0);

	// Constructors

	/** default constructor */
	public IclubInsuranceItemType() {
	}

	/** minimal constructor */
	public IclubInsuranceItemType(Long iitId) {
		this.iitId = iitId;
	}

	/** full constructor */
	public IclubInsuranceItemType(Long iitId, String iitShortDesc,
			String iitLongDesc, String iitStatus,
			Set<IclubSecurityMaster> iclubSecurityMasters,
			Set<IclubPurposeType> iclubPurposeTypes,
			Set<IclubCoverType> iclubCoverTypes) {
		this.iitId = iitId;
		this.iitShortDesc = iitShortDesc;
		this.iitLongDesc = iitLongDesc;
		this.iitStatus = iitStatus;
		this.iclubSecurityMasters = iclubSecurityMasters;
		this.iclubPurposeTypes = iclubPurposeTypes;
		this.iclubCoverTypes = iclubCoverTypes;
	}

	// Property accessors
	@Id
	@Column(name = "iit_id", unique = true, nullable = false)
	public Long getIitId() {
		return this.iitId;
	}

	public void setIitId(Long iitId) {
		this.iitId = iitId;
	}

	@Column(name = "iit_short_desc", length = 4)
	public String getIitShortDesc() {
		return this.iitShortDesc;
	}

	public void setIitShortDesc(String iitShortDesc) {
		this.iitShortDesc = iitShortDesc;
	}

	@Column(name = "iit_long_desc", length = 500)
	public String getIitLongDesc() {
		return this.iitLongDesc;
	}

	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}

	@Column(name = "iit_status", length = 1)
	public String getIitStatus() {
		return this.iitStatus;
	}

	public void setIitStatus(String iitStatus) {
		this.iitStatus = iitStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubSecurityMaster> getIclubSecurityMasters() {
		return this.iclubSecurityMasters;
	}

	public void setIclubSecurityMasters(
			Set<IclubSecurityMaster> iclubSecurityMasters) {
		this.iclubSecurityMasters = iclubSecurityMasters;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubPurposeType> getIclubPurposeTypes() {
		return this.iclubPurposeTypes;
	}

	public void setIclubPurposeTypes(Set<IclubPurposeType> iclubPurposeTypes) {
		this.iclubPurposeTypes = iclubPurposeTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubCoverType> getIclubCoverTypes() {
		return this.iclubCoverTypes;
	}

	public void setIclubCoverTypes(Set<IclubCoverType> iclubCoverTypes) {
		this.iclubCoverTypes = iclubCoverTypes;
	}

}