package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubPropertyType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_property_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getPropertyTypeBySD", query = "select * from iclub_property_type where lower(pt_short_desc) = lower(:sd) and pt_id <> :id", resultClass = IclubPropertyType.class) })
public class IclubPropertyType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226793033337122465L;
	private Long ptId;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubPropertyType() {
	}

	/** minimal constructor */
	public IclubPropertyType(Long ptId) {
		this.ptId = ptId;
	}

	/** full constructor */
	public IclubPropertyType(Long ptId, String ptShortDesc, String ptLongDesc, String ptStatus, Set<IclubProperty> iclubProperties) {
		this.ptId = ptId;
		this.ptShortDesc = ptShortDesc;
		this.ptLongDesc = ptLongDesc;
		this.ptStatus = ptStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "pt_id", unique = true, nullable = false)
	public Long getPtId() {
		return this.ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}

	@Column(name = "pt_short_desc", length = 4)
	public String getPtShortDesc() {
		return this.ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	@Column(name = "pt_long_desc", length = 500)
	public String getPtLongDesc() {
		return this.ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	@Column(name = "pt_status", length = 1)
	public String getPtStatus() {
		return this.ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPropertyType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}