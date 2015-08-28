package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubInsuranceItemType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurance_item_type", catalog = "iclubdb")
public class IclubInsuranceItemType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4264917161249887899L;
	private Long iitId;
	private String iitShortDesc;
	private String iitLongDesc;
	private String iitStatus;
	private Set<IclubRateType> iclubRateTypes = new HashSet<IclubRateType>(0);
	private Set<IclubSecurityDevice> iclubSecurityDevices = new HashSet<IclubSecurityDevice>(
			0);
	private Set<IclubSupplItem> iclubSupplItems = new HashSet<IclubSupplItem>(0);
	private Set<IclubSecurityMaster> iclubSecurityMasters = new HashSet<IclubSecurityMaster>(
			0);
	private Set<IclubPurposeType> iclubPurposeTypes = new HashSet<IclubPurposeType>(
			0);
	private Set<IclubInsuranceItem> iclubInsuranceItems = new HashSet<IclubInsuranceItem>(
			0);
	private Set<IclubInsuranceItem> iclubInsuranceItems_1 = new HashSet<IclubInsuranceItem>(
			0);
	private Set<IclubCoverType> iclubCoverTypes = new HashSet<IclubCoverType>(0);
	private Set<IclubCohort> iclubCohorts = new HashSet<IclubCohort>(0);

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
			Set<IclubRateType> iclubRateTypes,
			Set<IclubSecurityDevice> iclubSecurityDevices,
			Set<IclubSupplItem> iclubSupplItems,
			Set<IclubSecurityMaster> iclubSecurityMasters,
			Set<IclubPurposeType> iclubPurposeTypes,
			Set<IclubInsuranceItem> iclubInsuranceItems,
			Set<IclubInsuranceItem> iclubInsuranceItems_1,
			Set<IclubCoverType> iclubCoverTypes, Set<IclubCohort> iclubCohorts) {
		this.iitId = iitId;
		this.iitShortDesc = iitShortDesc;
		this.iitLongDesc = iitLongDesc;
		this.iitStatus = iitStatus;
		this.iclubRateTypes = iclubRateTypes;
		this.iclubSecurityDevices = iclubSecurityDevices;
		this.iclubSupplItems = iclubSupplItems;
		this.iclubSecurityMasters = iclubSecurityMasters;
		this.iclubPurposeTypes = iclubPurposeTypes;
		this.iclubInsuranceItems = iclubInsuranceItems;
		this.iclubInsuranceItems_1 = iclubInsuranceItems_1;
		this.iclubCoverTypes = iclubCoverTypes;
		this.iclubCohorts = iclubCohorts;
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
	public Set<IclubRateType> getIclubRateTypes() {
		return this.iclubRateTypes;
	}

	public void setIclubRateTypes(Set<IclubRateType> iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubSecurityDevice> getIclubSecurityDevices() {
		return this.iclubSecurityDevices;
	}

	public void setIclubSecurityDevices(
			Set<IclubSecurityDevice> iclubSecurityDevices) {
		this.iclubSecurityDevices = iclubSecurityDevices;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubSupplItem> getIclubSupplItems() {
		return this.iclubSupplItems;
	}

	public void setIclubSupplItems(Set<IclubSupplItem> iclubSupplItems) {
		this.iclubSupplItems = iclubSupplItems;
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
	public Set<IclubInsuranceItem> getIclubInsuranceItems() {
		return this.iclubInsuranceItems;
	}

	public void setIclubInsuranceItems(
			Set<IclubInsuranceItem> iclubInsuranceItems) {
		this.iclubInsuranceItems = iclubInsuranceItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubInsuranceItem> getIclubInsuranceItems_1() {
		return this.iclubInsuranceItems_1;
	}

	public void setIclubInsuranceItems_1(
			Set<IclubInsuranceItem> iclubInsuranceItems_1) {
		this.iclubInsuranceItems_1 = iclubInsuranceItems_1;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubCoverType> getIclubCoverTypes() {
		return this.iclubCoverTypes;
	}

	public void setIclubCoverTypes(Set<IclubCoverType> iclubCoverTypes) {
		this.iclubCoverTypes = iclubCoverTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItemType")
	public Set<IclubCohort> getIclubCohorts() {
		return this.iclubCohorts;
	}

	public void setIclubCohorts(Set<IclubCohort> iclubCohorts) {
		this.iclubCohorts = iclubCohorts;
	}

}