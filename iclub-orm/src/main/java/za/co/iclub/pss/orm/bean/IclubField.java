package za.co.iclub.pss.orm.bean;

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

/**
 * IclubField entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_field")
public class IclubField implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6766278079672011994L;
	private Long FId;
	private IclubEntityType iclubEntityType;
	private String FName;
	private String FDesc;
	private String FType;
	private String FLTblName;
	private String FRate;
	private String FStatus;
	private Set<IclubRateType> iclubRateTypes = new HashSet<IclubRateType>(0);

	// Constructors

	/** default constructor */
	public IclubField() {
	}

	/** minimal constructor */
	public IclubField(Long FId) {
		this.FId = FId;
	}

	/** full constructor */
	public IclubField(Long FId, IclubEntityType iclubEntityType, String FName, String FDesc, String FType, String FLTblName, String FRate, String FStatus, Set<IclubRateType> iclubRateTypes) {
		this.FId = FId;
		this.iclubEntityType = iclubEntityType;
		this.FName = FName;
		this.FDesc = FDesc;
		this.FType = FType;
		this.FLTblName = FLTblName;
		this.FRate = FRate;
		this.FStatus = FStatus;
		this.iclubRateTypes = iclubRateTypes;
	}

	// Property accessors
	@Id
	@Column(name = "f_id", unique = true, nullable = false)
	public Long getFId() {
		return this.FId;
	}

	public void setFId(Long FId) {
		this.FId = FId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_entity_id")
	public IclubEntityType getIclubEntityType() {
		return this.iclubEntityType;
	}

	public void setIclubEntityType(IclubEntityType iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	@Column(name = "f_name", length = 450)
	public String getFName() {
		return this.FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	@Column(name = "f_desc", length = 450)
	public String getFDesc() {
		return this.FDesc;
	}

	public void setFDesc(String FDesc) {
		this.FDesc = FDesc;
	}

	@Column(name = "f_type", length = 450)
	public String getFType() {
		return this.FType;
	}

	public void setFType(String FType) {
		this.FType = FType;
	}

	@Column(name = "f_l_tbl_name", length = 450)
	public String getFLTblName() {
		return this.FLTblName;
	}

	public void setFLTblName(String FLTblName) {
		this.FLTblName = FLTblName;
	}

	@Column(name = "f_rate", length = 1)
	public String getFRate() {
		return this.FRate;
	}

	public void setFRate(String FRate) {
		this.FRate = FRate;
	}

	@Column(name = "f_status", length = 1)
	public String getFStatus() {
		return this.FStatus;
	}

	public void setFStatus(String FStatus) {
		this.FStatus = FStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubField")
	public Set<IclubRateType> getIclubRateTypes() {
		return this.iclubRateTypes;
	}

	public void setIclubRateTypes(Set<IclubRateType> iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

}