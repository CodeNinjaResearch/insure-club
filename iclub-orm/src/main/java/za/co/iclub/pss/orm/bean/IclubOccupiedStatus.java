package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubOccupiedStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_occupied_status")
@NamedNativeQueries({ @NamedNativeQuery(name = "getOccupiedStatusBySD", query = "select * from iclub_occupied_status where lower(os_short_desc) = lower(:sd) and os_id <> :id", resultClass = IclubOccupiedStatus.class) })
public class IclubOccupiedStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8103033165228040040L;
	private Long osId;
	private String osShortDesc;
	private String osLongDesc;
	private String osStatus;

	// Constructors

	/** default constructor */
	public IclubOccupiedStatus() {
	}

	/** minimal constructor */
	public IclubOccupiedStatus(Long osId) {
		this.osId = osId;
	}

	/** full constructor */
	public IclubOccupiedStatus(Long osId, String osShortDesc, String osLongDesc, String osStatus) {
		this.osId = osId;
		this.osShortDesc = osShortDesc;
		this.osLongDesc = osLongDesc;
		this.osStatus = osStatus;
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

}