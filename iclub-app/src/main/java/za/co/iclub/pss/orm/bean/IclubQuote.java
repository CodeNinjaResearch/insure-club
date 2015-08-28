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
import javax.persistence.UniqueConstraint;

/**
 * IclubQuote entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_quote", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "q_number"))
public class IclubQuote implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7468725703537241288L;
	private String QId;
	private IclubPerson iclubPersonByQCrtdBy;
	private IclubPerson iclubPersonByQPersonId;
	private IclubProductType iclubProductType;
	private IclubInsurerMaster iclubInsurerMaster;
	private IclubCoverType iclubCoverType;
	private IclubQuoteStatus iclubQuoteStatus;
	private Long QNumber;
	private Date QGenDt;
	private Integer QNumItems;
	private Double QGenPremium;
	private String QEmail;
	private String QMobile;
	private Date QValidUntil;
	private Double QPrevPremium;
	private String QIsMatched;
	private Date QCrtdDt;
	private String QClaimYn;
	private Set<IclubPolicy> iclubPolicies = new HashSet<IclubPolicy>(0);

	// Constructors

	/** default constructor */
	public IclubQuote() {
	}

	/** minimal constructor */
	public IclubQuote(String QId, Long QNumber) {
		this.QId = QId;
		this.QNumber = QNumber;
	}

	/** full constructor */
	public IclubQuote(String QId, IclubPerson iclubPersonByQCrtdBy,
			IclubPerson iclubPersonByQPersonId,
			IclubProductType iclubProductType,
			IclubInsurerMaster iclubInsurerMaster,
			IclubCoverType iclubCoverType, IclubQuoteStatus iclubQuoteStatus,
			Long QNumber, Date QGenDt, Integer QNumItems,
			Double QGenPremium, String QEmail, String QMobile,
			Date QValidUntil, Double QPrevPremium, String QIsMatched,
			Date QCrtdDt, String QClaimYn, Set<IclubPolicy> iclubPolicies) {
		this.QId = QId;
		this.iclubPersonByQCrtdBy = iclubPersonByQCrtdBy;
		this.iclubPersonByQPersonId = iclubPersonByQPersonId;
		this.iclubProductType = iclubProductType;
		this.iclubInsurerMaster = iclubInsurerMaster;
		this.iclubCoverType = iclubCoverType;
		this.iclubQuoteStatus = iclubQuoteStatus;
		this.QNumber = QNumber;
		this.QGenDt = QGenDt;
		this.QNumItems = QNumItems;
		this.QGenPremium = QGenPremium;
		this.QEmail = QEmail;
		this.QMobile = QMobile;
		this.QValidUntil = QValidUntil;
		this.QPrevPremium = QPrevPremium;
		this.QIsMatched = QIsMatched;
		this.QCrtdDt = QCrtdDt;
		this.QClaimYn = QClaimYn;
		this.iclubPolicies = iclubPolicies;
	}

	// Property accessors
	@Id
	@Column(name = "q_id", unique = true, nullable = false, length = 36)
	public String getQId() {
		return this.QId;
	}

	public void setQId(String QId) {
		this.QId = QId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_crtd_by")
	public IclubPerson getIclubPersonByQCrtdBy() {
		return this.iclubPersonByQCrtdBy;
	}

	public void setIclubPersonByQCrtdBy(IclubPerson iclubPersonByQCrtdBy) {
		this.iclubPersonByQCrtdBy = iclubPersonByQCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_person_id")
	public IclubPerson getIclubPersonByQPersonId() {
		return this.iclubPersonByQPersonId;
	}

	public void setIclubPersonByQPersonId(IclubPerson iclubPersonByQPersonId) {
		this.iclubPersonByQPersonId = iclubPersonByQPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_product_type")
	public IclubProductType getIclubProductType() {
		return this.iclubProductType;
	}

	public void setIclubProductType(IclubProductType iclubProductType) {
		this.iclubProductType = iclubProductType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_insurer_id")
	public IclubInsurerMaster getIclubInsurerMaster() {
		return this.iclubInsurerMaster;
	}

	public void setIclubInsurerMaster(IclubInsurerMaster iclubInsurerMaster) {
		this.iclubInsurerMaster = iclubInsurerMaster;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_cover_type_id")
	public IclubCoverType getIclubCoverType() {
		return this.iclubCoverType;
	}

	public void setIclubCoverType(IclubCoverType iclubCoverType) {
		this.iclubCoverType = iclubCoverType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "q_status_id")
	public IclubQuoteStatus getIclubQuoteStatus() {
		return this.iclubQuoteStatus;
	}

	public void setIclubQuoteStatus(IclubQuoteStatus iclubQuoteStatus) {
		this.iclubQuoteStatus = iclubQuoteStatus;
	}

	@Column(name = "q_number", unique = true, nullable = false)
	public Long getQNumber() {
		return this.QNumber;
	}

	public void setQNumber(Long QNumber) {
		this.QNumber = QNumber;
	}

	@Column(name = "q_gen_dt", length = 19)
	public Date getQGenDt() {
		return this.QGenDt;
	}

	public void setQGenDt(Date QGenDt) {
		this.QGenDt = QGenDt;
	}

	@Column(name = "q_num_items")
	public Integer getQNumItems() {
		return this.QNumItems;
	}

	public void setQNumItems(Integer QNumItems) {
		this.QNumItems = QNumItems;
	}

	@Column(name = "q_gen_premium", precision = 15, scale = 5)
	public Double getQGenPremium() {
		return this.QGenPremium;
	}

	public void setQGenPremium(Double QGenPremium) {
		this.QGenPremium = QGenPremium;
	}

	@Column(name = "q_email", length = 999)
	public String getQEmail() {
		return this.QEmail;
	}

	public void setQEmail(String QEmail) {
		this.QEmail = QEmail;
	}

	@Column(name = "q_mobile", length = 13)
	public String getQMobile() {
		return this.QMobile;
	}

	public void setQMobile(String QMobile) {
		this.QMobile = QMobile;
	}

	@Column(name = "q_valid_until", length = 19)
	public Date getQValidUntil() {
		return this.QValidUntil;
	}

	public void setQValidUntil(Date QValidUntil) {
		this.QValidUntil = QValidUntil;
	}

	@Column(name = "q_prev_premium", precision = 15, scale = 5)
	public Double getQPrevPremium() {
		return this.QPrevPremium;
	}

	public void setQPrevPremium(Double QPrevPremium) {
		this.QPrevPremium = QPrevPremium;
	}

	@Column(name = "q_is_matched", length = 1)
	public String getQIsMatched() {
		return this.QIsMatched;
	}

	public void setQIsMatched(String QIsMatched) {
		this.QIsMatched = QIsMatched;
	}

	@Column(name = "q_crtd_dt", length = 19)
	public Date getQCrtdDt() {
		return this.QCrtdDt;
	}

	public void setQCrtdDt(Date QCrtdDt) {
		this.QCrtdDt = QCrtdDt;
	}

	@Column(name = "q_claim_yn", length = 1)
	public String getQClaimYn() {
		return this.QClaimYn;
	}

	public void setQClaimYn(String QClaimYn) {
		this.QClaimYn = QClaimYn;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubQuote")
	public Set<IclubPolicy> getIclubPolicies() {
		return this.iclubPolicies;
	}

	public void setIclubPolicies(Set<IclubPolicy> iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

}