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
 * IclubRoofType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_roof_type", catalog = "iclubdb")
public class IclubRoofType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8909752921246786306L;
	private Long rtId;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubRoofType() {
	}

	/** minimal constructor */
	public IclubRoofType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRoofType(Long rtId, String rtShortDesc, String rtLongDesc, String rtStatus, Set<IclubProperty> iclubProperties) {
		this.rtId = rtId;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtStatus = rtStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubRoofType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}