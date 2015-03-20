package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubInsuranceItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurance_item")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_insurance_item where ii_quote_id=:quoteId ", name = "getInsuranceItemByQuoteId", resultClass = IclubInsuranceItem.class), @NamedNativeQuery(query = "select * from iclub_insurance_item where ii_type_id=:itemTypeId and ii_quote_id =:quoteId ", name = "getInsuranceItemByQuoteIdAndItemTypeId", resultClass = IclubInsuranceItem.class),@NamedNativeQuery(query = "select * from iclub_insurance_item where a_crtd_by=:id", name = "getInsuranceItemByUser", resultClass = IclubInsuranceItem.class) })
public class IclubInsuranceItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529374939703795240L;
	private String iiId;
	private IclubPerson iclubPerson;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private String iiQuoteId;
	private String iiItemId;
	private Timestamp iiCrtdDt;
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
	public IclubInsuranceItem(String iiId, IclubPerson iclubPerson, IclubInsuranceItemType iclubInsuranceItemType, String iiQuoteId, String iiItemId, Timestamp iiCrtdDt, Set<IclubClaimItem> iclubClaimItems) {
		this.iiId = iiId;
		this.iclubPerson = iclubPerson;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iiQuoteId = iiQuoteId;
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
	@JoinColumn(name = "ii_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ii_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@Column(name = "ii_quote_id", length = 36)
	public String getIiQuoteId() {
		return this.iiQuoteId;
	}

	public void setIiQuoteId(String iiQuoteId) {
		this.iiQuoteId = iiQuoteId;
	}

	@Column(name = "ii_item_id", length = 36)
	public String getIiItemId() {
		return this.iiItemId;
	}

	public void setIiItemId(String iiItemId) {
		this.iiItemId = iiItemId;
	}

	@Column(name = "ii_crtd_dt", length = 19)
	public Timestamp getIiCrtdDt() {
		return this.iiCrtdDt;
	}

	public void setIiCrtdDt(Timestamp iiCrtdDt) {
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