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
@Table(name = "iclub_prop_usage_type")
public class IclubPropUsageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6404622528271045343L;
	private Long puId;
	private String puShortDesc;
	private String puLongDesc;
	private String puStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubPropUsageType() {
	}

	/** minimal constructor */
	public IclubPropUsageType(Long puId) {
		this.puId = puId;
	}

	/** full constructor */
	public IclubPropUsageType(Long puId, String puShortDesc, String puLongDesc, String puStatus, Set<IclubProperty> iclubProperties) {
		this.puId = puId;
		this.puShortDesc = puShortDesc;
		this.puLongDesc = puLongDesc;
		this.puStatus = puStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "pu_id", unique = true, nullable = false)
	public Long getPuId() {
		return this.puId;
	}

	public void setPuId(Long puId) {
		this.puId = puId;
	}

	@Column(name = "pu_short_desc", length = 4)
	public String getPuShortDesc() {
		return this.puShortDesc;
	}

	public void setPuShortDesc(String puShortDesc) {
		this.puShortDesc = puShortDesc;
	}

	@Column(name = "pu_long_desc", length = 500)
	public String getPuLongDesc() {
		return this.puLongDesc;
	}

	public void setPuLongDesc(String puLongDesc) {
		this.puLongDesc = puLongDesc;
	}

	@Column(name = "pu_status", length = 1)
	public String getPuStatus() {
		return this.puStatus;
	}

	public void setPuStatus(String puStatus) {
		this.puStatus = puStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPropUsageType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}