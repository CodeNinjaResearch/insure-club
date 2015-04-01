package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

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
@Table(name = "iclub_login", uniqueConstraints = @UniqueConstraint(columnNames = "l_name"))
public class IclubLogin implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6283011740585731902L;
	private String LId;
	private IclubSecurityQuestion iclubSecurityQuestion;
	private IclubPerson iclubPersonByLCrtdBy;
	private IclubPerson iclubPersonByLPersonId;
	private IclubRoleType iclubRoleType;
	private String LName;
	private String LPasswd;
	private Timestamp LLastDate;
	private String LSecAns;
	private Timestamp LCrtdDt;

	// Constructors

	/** default constructor */
	public IclubLogin() {
	}

	/** minimal constructor */
	public IclubLogin(String LId) {
		this.LId = LId;
	}

	/** full constructor */
	public IclubLogin(String LId, IclubSecurityQuestion iclubSecurityQuestion, IclubPerson iclubPersonByLCrtdBy, IclubPerson iclubPersonByLPersonId, IclubRoleType iclubRoleType, String LName, String LPasswd, Timestamp LLastDate, String LSecAns, Timestamp LCrtdDt) {
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

	public void setIclubSecurityQuestion(IclubSecurityQuestion iclubSecurityQuestion) {
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
	public Timestamp getLLastDate() {
		return this.LLastDate;
	}

	public void setLLastDate(Timestamp LLastDate) {
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
	public Timestamp getLCrtdDt() {
		return this.LCrtdDt;
	}

	public void setLCrtdDt(Timestamp LCrtdDt) {
		this.LCrtdDt = LCrtdDt;
	}

}