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
 * IclubCountryCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_country_code", catalog = "iclubdb", uniqueConstraints = {
		@UniqueConstraint(columnNames = "cc_short_id"),
		@UniqueConstraint(columnNames = "cc_iso_id") })
public class IclubCountryCode implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9122366570281322313L;
	private Integer ccId;
	private IclubPerson iclubPerson;
	private String ccShortId;
	private String ccIsoId;
	private String ccName;
	private Timestamp ccCrtdDt;

	// Constructors

	/** default constructor */
	public IclubCountryCode() {
	}

	/** minimal constructor */
	public IclubCountryCode(Integer ccId) {
		this.ccId = ccId;
	}

	/** full constructor */
	public IclubCountryCode(Integer ccId, IclubPerson iclubPerson,
			String ccShortId, String ccIsoId, String ccName, Timestamp ccCrtdDt) {
		this.ccId = ccId;
		this.iclubPerson = iclubPerson;
		this.ccShortId = ccShortId;
		this.ccIsoId = ccIsoId;
		this.ccName = ccName;
		this.ccCrtdDt = ccCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "cc_id", unique = true, nullable = false)
	public Integer getCcId() {
		return this.ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cc_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "cc_short_id", unique = true, length = 2)
	public String getCcShortId() {
		return this.ccShortId;
	}

	public void setCcShortId(String ccShortId) {
		this.ccShortId = ccShortId;
	}

	@Column(name = "cc_iso_id", unique = true, length = 3)
	public String getCcIsoId() {
		return this.ccIsoId;
	}

	public void setCcIsoId(String ccIsoId) {
		this.ccIsoId = ccIsoId;
	}

	@Column(name = "cc_name", length = 9999)
	public String getCcName() {
		return this.ccName;
	}

	public void setCcName(String ccName) {
		this.ccName = ccName;
	}

	@Column(name = "cc_crtd_dt", length = 19)
	public Timestamp getCcCrtdDt() {
		return this.ccCrtdDt;
	}

	public void setCcCrtdDt(Timestamp ccCrtdDt) {
		this.ccCrtdDt = ccCrtdDt;
	}

}