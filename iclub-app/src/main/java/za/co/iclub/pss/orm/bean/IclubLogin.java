package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IclubLogin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_login", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "l_name"))
public class IclubLogin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6226744067183612039L;
	private String LId;
	private IclubSecurityQuestion iclubSecurityQuestion;
	private IclubPerson iclubPersonByLCrtdBy;
	private IclubPerson iclubPersonByLPersonId;
	private IclubRoleType iclubRoleType;
	private String LName;
	private String LPasswd;
	private Date LLastDate;
	private String LSecAns;
	private Date LCrtdDt;
	private String LProviderCd;
	private String LProviderId;

	// Constructors

	/** default constructor */
	public IclubLogin() {
	}

	/** minimal constructor */
	public IclubLogin(String LId) {
		this.LId = LId;
	}

	/** full constructor */
	public IclubLogin(String LId, IclubSecurityQuestion iclubSecurityQuestion,
			IclubPerson iclubPersonByLCrtdBy,
			IclubPerson iclubPersonByLPersonId, IclubRoleType iclubRoleType,
			String LName, String LPasswd, Date LLastDate, String LSecAns,
			Date LCrtdDt, String LProviderCd, String LProviderId) {
		this.LId = LId;
		this.iclubSecurityQuestion = iclubSecurityQuestion;
		this.iclubPersonByLCrtdBy = iclubPersonByLCrtdBy;
		this.iclubPersonByLPersonId = iclubPersonByLPersonId;
		this.iclubRoleType = iclubRoleType;
		this.LName = LName;
		this.LPasswd = LPasswd;
		this.LLastDate = LLastDate;
		this.LSecAns = LSecAns;
		this.LCrtdDt = LCrtdDt;
		this.LProviderCd = LProviderCd;
		this.LProviderId = LProviderId;
	}

	// Property accessors
	@Id
	@Column(name = "l_id", unique = true, nullable = false, length = 36)
	public String getLId() {
		return this.LId;
	}

	public void setLId(String LId) {
		this.LId = LId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "l_sec_ques_id")
	public IclubSecurityQuestion getIclubSecurityQuestion() {
		return this.iclubSecurityQuestion;
	}

	public void setIclubSecurityQuestion(
			IclubSecurityQuestion iclubSecurityQuestion) {
		this.iclubSecurityQuestion = iclubSecurityQuestion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "l_crtd_by")
	public IclubPerson getIclubPersonByLCrtdBy() {
		return this.iclubPersonByLCrtdBy;
	}

	public void setIclubPersonByLCrtdBy(IclubPerson iclubPersonByLCrtdBy) {
		this.iclubPersonByLCrtdBy = iclubPersonByLCrtdBy;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "l_person_id")
	public IclubPerson getIclubPersonByLPersonId() {
		return this.iclubPersonByLPersonId;
	}

	public void setIclubPersonByLPersonId(IclubPerson iclubPersonByLPersonId) {
		this.iclubPersonByLPersonId = iclubPersonByLPersonId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "l_role_id")
	public IclubRoleType getIclubRoleType() {
		return this.iclubRoleType;
	}

	public void setIclubRoleType(IclubRoleType iclubRoleType) {
		this.iclubRoleType = iclubRoleType;
	}

	@Column(name = "l_name", unique = true, length = 70)
	public String getLName() {
		return this.LName;
	}

	public void setLName(String LName) {
		this.LName = LName;
	}

	@Column(name = "l_passwd", length = 999)
	public String getLPasswd() {
		return this.LPasswd;
	}

	public void setLPasswd(String LPasswd) {
		this.LPasswd = LPasswd;
	}

	@Column(name = "l_last_date", length = 19)
	public Date getLLastDate() {
		return this.LLastDate;
	}

	public void setLLastDate(Date LLastDate) {
		this.LLastDate = LLastDate;
	}

	@Column(name = "l_sec_ans", length = 45)
	public String getLSecAns() {
		return this.LSecAns;
	}

	public void setLSecAns(String LSecAns) {
		this.LSecAns = LSecAns;
	}

	@Column(name = "l_crtd_dt", length = 19)
	public Date getLCrtdDt() {
		return this.LCrtdDt;
	}

	public void setLCrtdDt(Date LCrtdDt) {
		this.LCrtdDt = LCrtdDt;
	}

	@Column(name = "l_provider_cd", length = 50)
	public String getLProviderCd() {
		return this.LProviderCd;
	}

	public void setLProviderCd(String LProviderCd) {
		this.LProviderCd = LProviderCd;
	}

	@Column(name = "l_provider_id", length = 500)
	public String getLProviderId() {
		return this.LProviderId;
	}

	public void setLProviderId(String LProviderId) {
		this.LProviderId = LProviderId;
	}

}