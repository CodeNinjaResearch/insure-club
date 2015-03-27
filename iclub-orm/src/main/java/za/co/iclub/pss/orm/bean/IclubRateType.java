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
 * IclubRateType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_rate_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getRateTypeBySD", query = "select * from iclub_rate_type where lower(rt_short_desc) = lower(:sd) and rt_id <> :id", resultClass = IclubRateType.class) })
public class IclubRateType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1222778689466584778L;
	private Long rtId;
	private IclubInsuranceItemType iclubInsuranceItemType;
	private IclubPerson iclubPerson;
	private IclubEntityType iclubEntityType;
	private String rtShortDesc;
	private String rtLongDesc;
	private Long rtFieldNm;
	private String rtStatus;
	private Timestamp rtCrtdDt;
	private String rtType;
	private String rtQuoteType;
	private Set<IclubRateEngine> iclubRateEngines = new HashSet<IclubRateEngine>(0);

	// Constructors

	/** default constructor */
	public IclubRateType() {
	}

	/** minimal constructor */
	public IclubRateType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRateType(Long rtId, IclubInsuranceItemType iclubInsuranceItemType, IclubPerson iclubPerson, IclubEntityType iclubEntityType, String rtShortDesc, String rtLongDesc, Long rtFieldNm, String rtStatus, Timestamp rtCrtdDt, String rtType, String rtQuoteType, Set<IclubRateEngine> iclubRateEngines) {
		this.rtId = rtId;
		this.iclubInsuranceItemType = iclubInsuranceItemType;
		this.iclubPerson = iclubPerson;
		this.iclubEntityType = iclubEntityType;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtFieldNm = rtFieldNm;
		this.rtStatus = rtStatus;
		this.rtCrtdDt = rtCrtdDt;
		this.rtType = rtType;
		this.rtQuoteType = rtQuoteType;
		this.iclubRateEngines = iclubRateEngines;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_item_type_id")
	public IclubInsuranceItemType getIclubInsuranceItemType() {
		return this.iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(IclubInsuranceItemType iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_entity_type_id")
	public IclubEntityType getIclubEntityType() {
		return this.iclubEntityType;
	}

	public void setIclubEntityType(IclubEntityType iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_field_nm", length = 450)
	public Long getRtFieldNm() {
		return this.rtFieldNm;
	}

	public void setRtFieldNm(Long rtFieldNm) {
		this.rtFieldNm = rtFieldNm;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	@Column(name = "rt_crtd_dt", length = 19)
	public Timestamp getRtCrtdDt() {
		return this.rtCrtdDt;
	}

	public void setRtCrtdDt(Timestamp rtCrtdDt) {
		this.rtCrtdDt = rtCrtdDt;
	}

	@Column(name = "rt_type", length = 1)
	public String getRtType() {
		return rtType;
	}

	public void setRtType(String rtType) {
		this.rtType = rtType;
	}

	@Column(name = "rt_quote_type", length = 1)
	public String getRtQuoteType() {
		return rtQuoteType;
	}

	public void setRtQuoteType(String rtQuoteType) {
		this.rtQuoteType = rtQuoteType;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubRateType")
	public Set<IclubRateEngine> getIclubRateEngines() {
		return this.iclubRateEngines;
	}

	public void setIclubRateEngines(Set<IclubRateEngine> iclubRateEngines) {
		this.iclubRateEngines = iclubRateEngines;
	}

}