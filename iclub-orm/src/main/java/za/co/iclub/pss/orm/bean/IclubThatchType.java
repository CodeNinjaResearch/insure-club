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
 * IclubThatchType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_thatch_type")
public class IclubThatchType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594352503825157164L;
	private Long ttId;
	private String ttShortDesc;
	private String ttLongDesc;
	private String ttStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubThatchType() {
	}

	/** minimal constructor */
	public IclubThatchType(Long ttId) {
		this.ttId = ttId;
	}

	/** full constructor */
	public IclubThatchType(Long ttId, String ttShortDesc, String ttLongDesc, String ttStatus, Set<IclubProperty> iclubProperties) {
		this.ttId = ttId;
		this.ttShortDesc = ttShortDesc;
		this.ttLongDesc = ttLongDesc;
		this.ttStatus = ttStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "tt_id", unique = true, nullable = false)
	public Long getTtId() {
		return this.ttId;
	}

	public void setTtId(Long ttId) {
		this.ttId = ttId;
	}

	@Column(name = "tt_short_desc", length = 4)
	public String getTtShortDesc() {
		return this.ttShortDesc;
	}

	public void setTtShortDesc(String ttShortDesc) {
		this.ttShortDesc = ttShortDesc;
	}

	@Column(name = "tt_long_desc", length = 500)
	public String getTtLongDesc() {
		return this.ttLongDesc;
	}

	public void setTtLongDesc(String ttLongDesc) {
		this.ttLongDesc = ttLongDesc;
	}

	@Column(name = "tt_status", length = 1)
	public String getTtStatus() {
		return this.ttStatus;
	}

	public void setTtStatus(String ttStatus) {
		this.ttStatus = ttStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubThatchType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}