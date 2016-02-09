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
 * IclubAssessmentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_assessment_type", catalog = "iclubdb")
public class IclubAssessmentType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 9118731819873465781L;
	private Long atId;
	private String atLongDesc;
	private String atShortDesc;
	private String atStatus;
	private Set<IclubSupplItem> iclubSupplItems = new HashSet<IclubSupplItem>(0);

	// Constructors

	/** default constructor */
	public IclubAssessmentType() {
	}

	/** minimal constructor */
	public IclubAssessmentType(Long atId) {
		this.atId = atId;
	}

	/** full constructor */
	public IclubAssessmentType(Long atId, String atLongDesc, String atShortDesc, String atStatus, Set<IclubSupplItem> iclubSupplItems) {
		this.atId = atId;
		this.atLongDesc = atLongDesc;
		this.atShortDesc = atShortDesc;
		this.atStatus = atStatus;
		this.iclubSupplItems = iclubSupplItems;
	}

	// Property accessors
	@Id
	@Column(name = "at_id", unique = true, nullable = false)
	public Long getAtId() {
		return this.atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	@Column(name = "at_long_desc")
	public String getAtLongDesc() {
		return this.atLongDesc;
	}

	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}

	@Column(name = "at_short_desc", length = 4)
	public String getAtShortDesc() {
		return this.atShortDesc;
	}

	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}

	@Column(name = "at_status", length = 1)
	public String getAtStatus() {
		return this.atStatus;
	}

	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubAssessmentType")
	public Set<IclubSupplItem> getIclubSupplItems() {
		return this.iclubSupplItems;
	}

	public void setIclubSupplItems(Set<IclubSupplItem> iclubSupplItems) {
		this.iclubSupplItems = iclubSupplItems;
	}

}