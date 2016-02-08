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
 * IclubSupplierType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_supplier_type", catalog = "iclubdb")
public class IclubSupplierType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1613754131810757273L;
	private Long stId;
	private String stShortDesc;
	private String stLongDesc;
	private String stStatus;
	private Set<IclubSupplMaster> iclubSupplMasters = new HashSet<IclubSupplMaster>(0);

	// Constructors

	/** default constructor */
	public IclubSupplierType() {
	}

	/** minimal constructor */
	public IclubSupplierType(Long stId) {
		this.stId = stId;
	}

	/** full constructor */
	public IclubSupplierType(Long stId, String stShortDesc, String stLongDesc, String stStatus, Set<IclubSupplMaster> iclubSupplMasters) {
		this.stId = stId;
		this.stShortDesc = stShortDesc;
		this.stLongDesc = stLongDesc;
		this.stStatus = stStatus;
		this.iclubSupplMasters = iclubSupplMasters;
	}

	// Property accessors
	@Id
	@Column(name = "st_id", unique = true, nullable = false)
	public Long getStId() {
		return this.stId;
	}

	public void setStId(Long stId) {
		this.stId = stId;
	}

	@Column(name = "st_short_desc", length = 4)
	public String getStShortDesc() {
		return this.stShortDesc;
	}

	public void setStShortDesc(String stShortDesc) {
		this.stShortDesc = stShortDesc;
	}

	@Column(name = "st_long_desc", length = 500)
	public String getStLongDesc() {
		return this.stLongDesc;
	}

	public void setStLongDesc(String stLongDesc) {
		this.stLongDesc = stLongDesc;
	}

	@Column(name = "st_status", length = 1)
	public String getStStatus() {
		return this.stStatus;
	}

	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSupplierType")
	public Set<IclubSupplMaster> getIclubSupplMasters() {
		return this.iclubSupplMasters;
	}

	public void setIclubSupplMasters(Set<IclubSupplMaster> iclubSupplMasters) {
		this.iclubSupplMasters = iclubSupplMasters;
	}

}