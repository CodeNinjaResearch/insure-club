package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubPropUsageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_prop_usage_type", catalog = "iclubdb")
public class IclubPropUsageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4468070018265244995L;
	private Long putId;
	private String putLongDesc;
	private String putShortDesc;
	private String putStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubPropUsageType() {
	}

	/** minimal constructor */
	public IclubPropUsageType(Long putId) {
		this.putId = putId;
	}

	/** full constructor */
	public IclubPropUsageType(Long putId, String putLongDesc, String putShortDesc, String putStatus, Set<IclubProperty> iclubProperties) {
		this.putId = putId;
		this.putLongDesc = putLongDesc;
		this.putShortDesc = putShortDesc;
		this.putStatus = putStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "put_id", unique = true, nullable = false)
	public Long getPutId() {
		return this.putId;
	}

	public void setPutId(Long putId) {
		this.putId = putId;
	}

	@Column(name = "put_long_desc")
	public String getPutLongDesc() {
		return this.putLongDesc;
	}

	public void setPutLongDesc(String putLongDesc) {
		this.putLongDesc = putLongDesc;
	}

	@Column(name = "put_short_desc", length = 4)
	public String getPutShortDesc() {
		return this.putShortDesc;
	}

	public void setPutShortDesc(String putShortDesc) {
		this.putShortDesc = putShortDesc;
	}

	@Column(name = "put_status", length = 1)
	public String getPutStatus() {
		return this.putStatus;
	}

	public void setPutStatus(String putStatus) {
		this.putStatus = putStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPropUsageType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}