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
 * IclubPropertyItem entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_property_item", catalog = "iclubdb")
public class IclubPropertyItem implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3185399206688535083L;
	private String piId;
	private IclubProperty iclubProperty;
	private IclubPerson iclubPerson;
	private Date piCrtdDate;
	private String piDescripton;
	private Double piValue;

	// Constructors

	/** default constructor */
	public IclubPropertyItem() {
	}

	/** minimal constructor */
	public IclubPropertyItem(String piId) {
		this.piId = piId;
	}

	/** full constructor */
	public IclubPropertyItem(String piId, IclubProperty iclubProperty, IclubPerson iclubPerson, Date piCrtdDate, String piDescripton, Double piValue) {
		this.piId = piId;
		this.iclubProperty = iclubProperty;
		this.iclubPerson = iclubPerson;
		this.piCrtdDate = piCrtdDate;
		this.piDescripton = piDescripton;
		this.piValue = piValue;
	}

	// Property accessors
	@Id
	@Column(name = "pi_id", unique = true, nullable = false, length = 36)
	public String getPiId() {
		return this.piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pi_property_id")
	public IclubProperty getIclubProperty() {
		return this.iclubProperty;
	}

	public void setIclubProperty(IclubProperty iclubProperty) {
		this.iclubProperty = iclubProperty;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pi_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "pi_crtd_date", length = 19)
	public Date getPiCrtdDate() {
		return this.piCrtdDate;
	}

	public void setPiCrtdDate(Date piCrtdDate) {
		this.piCrtdDate = piCrtdDate;
	}

	@Column(name = "pi_descripton")
	public String getPiDescripton() {
		return this.piDescripton;
	}

	public void setPiDescripton(String piDescripton) {
		this.piDescripton = piDescripton;
	}

	@Column(name = "pi_value", precision = 22, scale = 0)
	public Double getPiValue() {
		return this.piValue;
	}

	public void setPiValue(Double piValue) {
		this.piValue = piValue;
	}

}