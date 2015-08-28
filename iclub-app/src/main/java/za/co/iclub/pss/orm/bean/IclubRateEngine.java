package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubRateEngine entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_rate_engine", catalog = "iclubdb")
public class IclubRateEngine implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2108931839134152687L;
	private String reId;
	private IclubPerson iclubPerson;
	private IclubRateType iclubRateType;
	private String reBaseValue;
	private String reMaxValue;
	private Double reRate;
	private String reStatus;
	private Date reCrtdDt;

	// Constructors

	/** default constructor */
	public IclubRateEngine() {
	}

	/** minimal constructor */
	public IclubRateEngine(String reId) {
		this.reId = reId;
	}

	/** full constructor */
	public IclubRateEngine(String reId, IclubPerson iclubPerson,
			IclubRateType iclubRateType, String reBaseValue, String reMaxValue,
			Double reRate, String reStatus, Date reCrtdDt) {
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
	public Date getReCrtdDt() {
		return this.reCrtdDt;
	}

	public void setReCrtdDt(Date reCrtdDt) {
		this.reCrtdDt = reCrtdDt;
	}

}