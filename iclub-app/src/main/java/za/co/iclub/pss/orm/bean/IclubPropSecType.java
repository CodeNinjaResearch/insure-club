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
 * IclubPropSecType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_prop_sec_type", catalog = "iclubdb")
public class IclubPropSecType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -24079831302171688L;
	private Long pstId;
	private String pstLongDesc;
	private String pstShortDesc;
	private String pstStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);

	// Constructors

	/** default constructor */
	public IclubPropSecType() {
	}

	/** minimal constructor */
	public IclubPropSecType(Long pstId) {
		this.pstId = pstId;
	}

	/** full constructor */
	public IclubPropSecType(Long pstId, String pstLongDesc, String pstShortDesc, String pstStatus, Set<IclubProperty> iclubProperties) {
		this.pstId = pstId;
		this.pstLongDesc = pstLongDesc;
		this.pstShortDesc = pstShortDesc;
		this.pstStatus = pstStatus;
		this.iclubProperties = iclubProperties;
	}

	// Property accessors
	@Id
	@Column(name = "pst_id", unique = true, nullable = false)
	public Long getPstId() {
		return this.pstId;
	}

	public void setPstId(Long pstId) {
		this.pstId = pstId;
	}

	@Column(name = "pst_long_desc")
	public String getPstLongDesc() {
		return this.pstLongDesc;
	}

	public void setPstLongDesc(String pstLongDesc) {
		this.pstLongDesc = pstLongDesc;
	}

	@Column(name = "pst_short_desc", length = 4)
	public String getPstShortDesc() {
		return this.pstShortDesc;
	}

	public void setPstShortDesc(String pstShortDesc) {
		this.pstShortDesc = pstShortDesc;
	}

	@Column(name = "pst_status", length = 1)
	public String getPstStatus() {
		return this.pstStatus;
	}

	public void setPstStatus(String pstStatus) {
		this.pstStatus = pstStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPropSecType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}

	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}

}