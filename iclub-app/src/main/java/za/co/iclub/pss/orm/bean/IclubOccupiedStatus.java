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
 * IclubOccupiedStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_occupied_status", catalog = "iclubdb")
public class IclubOccupiedStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1494952670198297922L;
	private Long osId;
	private String osShortDesc;
	private String osLongDesc;
	private String osStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubOccupiedStatus() {
	}

	/** minimal constructor */
	public IclubOccupiedStatus(Long osId) {
		this.osId = osId;
	}

	/** full constructor */
	public IclubOccupiedStatus(Long osId, String osShortDesc,
			String osLongDesc, String osStatus,
			Set<IclubProperty> iclubProperties) {
		this.osId = osId;
		this.osShortDesc = osShortDesc;
		this.osLongDesc = osLongDesc;
		this.osStatus = osStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "os_id", unique = true, nullable = false)
	public Long getOsId() {
		return this.osId;
	}

	public void setOsId(Long osId) {
		this.osId = osId;
	}

	@Column(name = "os_short_desc", length = 4)
	public String getOsShortDesc() {
		return this.osShortDesc;
	}

	public void setOsShortDesc(String osShortDesc) {
		this.osShortDesc = osShortDesc;
	}

	@Column(name = "os_long_desc", length = 500)
	public String getOsLongDesc() {
		return this.osLongDesc;
	}

	public void setOsLongDesc(String osLongDesc) {
		this.osLongDesc = osLongDesc;
	}

	@Column(name = "os_status", length = 1)
	public String getOsStatus() {
		return this.osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubOccupiedStatus")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}