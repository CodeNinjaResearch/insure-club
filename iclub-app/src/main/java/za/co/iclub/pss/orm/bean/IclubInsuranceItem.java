package za.co.iclub.pss.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubInsuranceItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurance_item", catalog = "iclubdb")
public class IclubInsuranceItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7188242062725333479L;
	private String iiId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private String iiQuoteId;
	private Double iiInsureValue;
	private Double iiActualValue;
	private String iiItemId;
	private Date iiCrtdDt;
	private Set<IclubClaimItem> iclubClaimItems = new HashSet<IclubClaimItem>(0);

	// Constructors

	/** default constructor */
	public IclubInsuranceItem() {
	}

	/** minimal constructor */
	public IclubInsuranceItem(String iiId) {
		this.iiId = iiId;
	}

	/** full constructor */
	public IclubInsuranceItem(String iiId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPerson, String iiQuoteId, Double iiInsureValue, Double iiActualValue, String iiItemId, Date iiCrtdDt, Set<IclubClaimItem> iclubClaimItems) {
		this.iiId = iiId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.iiQuoteId = iiQuoteId;
		this.iiInsureValue = iiInsureValue;
		this.iiActualValue = iiActualValue;
		this.iiItemId = iiItemId;
		this.iiCrtdDt = iiCrtdDt;
		this.iclubClaimItems = iclubClaimItems;
	}

	// Property accessors
	@Id
	@Column(name = "ii_id", unique = true, nullable = false, length = 36)
	public String getIiId() {
		return this.iiId;
	}

	public void setIiId(String iiId) {
		this.iiId = iiId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ii_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ii_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "ii_quote_id", length = 36)
	public String getIiQuoteId() {
		return this.iiQuoteId;
	}

	public void setIiQuoteId(String iiQuoteId) {
		this.iiQuoteId = iiQuoteId;
	}

	@Column(name = "ii_insure_value", precision = 15, scale = 5)
	public Double getIiInsureValue() {
		return this.iiInsureValue;
	}

	public void setIiInsureValue(Double iiInsureValue) {
		this.iiInsureValue = iiInsureValue;
	}

	@Column(name = "ii_actual_value", precision = 15, scale = 5)
	public Double getIiActualValue() {
		return this.iiActualValue;
	}

	public void setIiActualValue(Double iiActualValue) {
		this.iiActualValue = iiActualValue;
	}

	@Column(name = "ii_item_id", length = 36)
	public String getIiItemId() {
		return this.iiItemId;
	}

	public void setIiItemId(String iiItemId) {
		this.iiItemId = iiItemId;
	}

	@Column(name = "ii_crtd_dt", length = 19)
	public Date getIiCrtdDt() {
		return this.iiCrtdDt;
	}

	public void setIiCrtdDt(Date iiCrtdDt) {
		this.iiCrtdDt = iiCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsuranceItem")
	public Set<IclubClaimItem> getIclubClaimItems() {
		return this.iclubClaimItems;
	}

	public void setIclubClaimItems(Set<IclubClaimItem> iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
	}

}