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
 * IclubConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_config", catalog = "iclubdb", uniqueConstraints = @UniqueConstraint(columnNames = "c_key"))
public class IclubConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5809985454234777830L;
	private Long CId;
	private IclubPerson iclubPerson;
	private String CKey;
	private String CValue;
	private String CStatus;
	private Date CCrtdDt;

	// Constructors

	/** default constructor */
	public IclubConfig() {
	}

	/** minimal constructor */
	public IclubConfig(Long CId) {
		this.CId = CId;
	}

	/** full constructor */
	public IclubConfig(Long CId, IclubPerson iclubPerson, String CKey, String CValue, String CStatus, Date CCrtdDt) {
		this.CId = CId;
		this.iclubPerson = iclubPerson;
		this.CKey = CKey;
		this.CValue = CValue;
		this.CStatus = CStatus;
		this.CCrtdDt = CCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "c_id", unique = true, nullable = false)
	public Long getCId() {
		return this.CId;
	}

	public void setCId(Long CId) {
		this.CId = CId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "c_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "c_key", unique = true, length = 250)
	public String getCKey() {
		return this.CKey;
	}

	public void setCKey(String CKey) {
		this.CKey = CKey;
	}

	@Column(name = "c_value", length = 999)
	public String getCValue() {
		return this.CValue;
	}

	public void setCValue(String CValue) {
		this.CValue = CValue;
	}

	@Column(name = "c_status", length = 1)
	public String getCStatus() {
		return this.CStatus;
	}

	public void setCStatus(String CStatus) {
		this.CStatus = CStatus;
	}

	@Column(name = "c_crtd_dt", length = 19)
	public Date getCCrtdDt() {
		return this.CCrtdDt;
	}

	public void setCCrtdDt(Date CCrtdDt) {
		this.CCrtdDt = CCrtdDt;
	}

}