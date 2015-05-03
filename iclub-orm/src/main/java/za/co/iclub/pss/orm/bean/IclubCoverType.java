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
 * IclubCoverType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cover_type")
public class IclubCoverType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 596072198822224792L;
	private Long ctId;
	private IclubPerson iclubPerson;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;
	private Date ctCrtdDt;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);
	private Set<IclubQuote> iclubQuotes = new HashSet<IclubQuote>(0);

	// Constructors

	/** default constructor */
	public IclubCoverType() {
	}

	/** minimal constructor */
	public IclubCoverType(Long ctId) {
		this.ctId = ctId;
	}

	/** full constructor */
	public IclubCoverType(Long ctId, IclubPerson iclubPerson, IclubInsuranceItemType iclubInsuranceItemType, String ctShortDesc, String ctLongDesc, String ctStatus, Date ctCrtdDt, Set<IclubProperty> iclubProperties, Set<IclubQuote> iclubQuotes) {
		this.ctId = ctId;
		this.iclubPerson = iclubPerson;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.ctShortDesc = ctShortDesc;
		this.ctLongDesc = ctLongDesc;
		this.ctStatus = ctStatus;
		this.ctCrtdDt = ctCrtdDt;
		this.iclubProperties = iclubProperties;
		this.iclubQuotes = iclubQuotes;
	}

	// Property accessors
	@Id
	@Column(name = "ct_id", unique = true, nullable = false)
	public Long getCtId() {
		return this.ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ct_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@Column(name = "ct_short_desc", length = 4)
	public String getCtShortDesc() {
		return this.ctShortDesc;
	}

	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}

	@Column(name = "ct_long_desc", length = 450)
	public String getCtLongDesc() {
		return this.ctLongDesc;
	}

	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}

	@Column(name = "ct_status", length = 1)
	public String getCtStatus() {
		return this.ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

	@Column(name = "ct_crtd_dt", length = 19)
	public Date getCtCrtdDt() {
		return this.ctCrtdDt;
	}

	public void setCtCrtdDt(Date ctCrtdDt) {
		this.ctCrtdDt = ctCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCoverType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubCoverType")
	public Set<IclubQuote> getIclubQuotes() {
		return this.iclubQuotes;
	}

	public void setIclubQuotes(Set<IclubQuote> iclubQuotes) {
		this.iclubQuotes = iclubQuotes;
	}

}