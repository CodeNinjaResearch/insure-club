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

/**
 * IclubRateEngine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_rate_engine")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_rate_engine where re_crtd_by=:id", name = "getRateEngineByUser", resultClass = IclubRateEngine.class), @NamedNativeQuery(query = "select * from iclub_rate_engine where re_type_id=:id", name = "getRateEngineByRateType", resultClass = IclubRateEngine.class) })
public class IclubRateEngine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8486951601059001041L;
	private String reId;
	private IclubPerson iclubPerson;
	private IclubRateType iclubRateType;
	private String reBaseValue;
	private String reMaxValue;
	private Double reRate;
	private String reStatus;
	private Timestamp reCrtdDt;

	// Constructors

	/** default constructor */
	public IclubRateEngine() {
	}

	/** minimal constructor */
	public IclubRateEngine(String reId) {
		this.reId = reId;
	}

	/** full constructor */
	public IclubRateEngine(String reId, IclubPerson iclubPerson, IclubRateType iclubRateType, String reBaseValue, String reMaxValue, Double reRate, String reStatus, Timestamp reCrtdDt) {
		this.reId = reId;
		this.iclubPerson = iclubPerson;
		this.iclubRateType = iclubRateType;
		this.reBaseValue = reBaseValue;
		this.reMaxValue = reMaxValue;
		this.reRate = reRate;
		this.reStatus = reStatus;
		this.reCrtdDt = reCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "re_id", unique = true, nullable = false, length = 36)
	public String getReId() {
		return this.reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "re_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "re_type_id")
	public IclubRateType getIclubRateType() {
		return this.iclubRateType;
	}

	public void setIclubRateType(IclubRateType iclubRateType) {
		this.iclubRateType = iclubRateType;
	}

	@Column(name = "re_base_value", length = 999)
	public String getReBaseValue() {
		return this.reBaseValue;
	}

	public void setReBaseValue(String reBaseValue) {
		this.reBaseValue = reBaseValue;
	}

	@Column(name = "re_max_value", length = 999)
	public String getReMaxValue() {
		return this.reMaxValue;
	}

	public void setReMaxValue(String reMaxValue) {
		this.reMaxValue = reMaxValue;
	}

	@Column(name = "re_rate", precision = 15, scale = 5)
	public Double getReRate() {
		return this.reRate;
	}

	public void setReRate(Double reRate) {
		this.reRate = reRate;
	}

	@Column(name = "re_status", length = 1)
	public String getReStatus() {
		return this.reStatus;
	}

	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}

	@Column(name = "re_crtd_dt", length = 19)
	public Timestamp getReCrtdDt() {
		return this.reCrtdDt;
	}

	public void setReCrtdDt(Timestamp reCrtdDt) {
		this.reCrtdDt = reCrtdDt;
	}

}