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
 * IclubWallType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_wall_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getWallTypeBySD", query = "select * from iclub_wall_type where lower(wt_short_desc) = lower(:sd) and wt_id <> :id", resultClass = IclubWallType.class) })
public class IclubWallType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606196015877472290L;
	private Long wtId;
	private String wtShortDesc;
	private String wtLongDesc;
	private String wtStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubWallType() {
	}

	/** minimal constructor */
	public IclubWallType(Long wtId) {
		this.wtId = wtId;
	}

	/** full constructor */
	public IclubWallType(Long wtId, String wtShortDesc, String wtLongDesc, String wtStatus, Set<IclubProperty> iclubProperties) {
		this.wtId = wtId;
		this.wtShortDesc = wtShortDesc;
		this.wtLongDesc = wtLongDesc;
		this.wtStatus = wtStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "wt_id", unique = true, nullable = false)
	public Long getWtId() {
		return this.wtId;
	}

	public void setWtId(Long wtId) {
		this.wtId = wtId;
	}

	@Column(name = "wt_short_desc", length = 4)
	public String getWtShortDesc() {
		return this.wtShortDesc;
	}

	public void setWtShortDesc(String wtShortDesc) {
		this.wtShortDesc = wtShortDesc;
	}

	@Column(name = "wt_long_desc", length = 500)
	public String getWtLongDesc() {
		return this.wtLongDesc;
	}

	public void setWtLongDesc(String wtLongDesc) {
		this.wtLongDesc = wtLongDesc;
	}

	@Column(name = "wt_status", length = 1)
	public String getWtStatus() {
		return this.wtStatus;
	}

	public void setWtStatus(String wtStatus) {
		this.wtStatus = wtStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubWallType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}