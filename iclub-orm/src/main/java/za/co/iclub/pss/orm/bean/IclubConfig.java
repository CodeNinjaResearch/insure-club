package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IclubConfig entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_config", uniqueConstraints = @UniqueConstraint(columnNames = "c_key"))
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_config where c_crtd_by=:id", name = "getConfigByUser", resultClass = IclubConfig.class) })
public class IclubConfig implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7385655058179300040L;
	private Long CId;
	private IclubPerson iclubPerson;
	private String CKey;
	private String CValue;
	private String CStatus;
	private Timestamp CCrtdDt;

	// Constructors

	/** default constructor */
	public IclubConfig() {
	}

	/** minimal constructor */
	public IclubConfig(Long CId) {
		this.CId = CId;
	}

	/** full constructor */
	public IclubConfig(Long CId, IclubPerson iclubPerson, String CKey, String CValue, String CStatus, Timestamp CCrtdDt) {
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
	public Timestamp getCCrtdDt() {
		return this.CCrtdDt;
	}

	public void setCCrtdDt(Timestamp CCrtdDt) {
		this.CCrtdDt = CCrtdDt;
	}

}