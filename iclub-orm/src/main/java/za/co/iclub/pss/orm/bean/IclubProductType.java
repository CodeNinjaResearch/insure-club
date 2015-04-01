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
 * IclubProductType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_product_type")
public class IclubProductType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1696659249106486941L;
	private Long ptId;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;
	private Set<IclubQuote> iclubQuotes = new HashSet<IclubQuote>(0);

	// Constructors

	/** default constructor */
	public IclubProductType() {
	}

	/** minimal constructor */
	public IclubProductType(Long ptId) {
		this.ptId = ptId;
	}

	/** full constructor */
	public IclubProductType(Long ptId, String ptShortDesc, String ptLongDesc, String ptStatus, Set<IclubQuote> iclubQuotes) {
		this.ptId = ptId;
		this.ptShortDesc = ptShortDesc;
		this.ptLongDesc = ptLongDesc;
		this.ptStatus = ptStatus;
		this.iclubQuotes = iclubQuotes;
	}

	// Property accessors
	@Id
	@Column(name = "pt_id", unique = true, nullable = false)
	public Long getPtId() {
		return this.ptId;
	}

	public void setPtId(Long ptId) {
		this.ptId = ptId;
	}

	@Column(name = "pt_short_desc", length = 4)
	public String getPtShortDesc() {
		return this.ptShortDesc;
	}

	public void setPtShortDesc(String ptShortDesc) {
		this.ptShortDesc = ptShortDesc;
	}

	@Column(name = "pt_long_desc", length = 500)
	public String getPtLongDesc() {
		return this.ptLongDesc;
	}

	public void setPtLongDesc(String ptLongDesc) {
		this.ptLongDesc = ptLongDesc;
	}

	@Column(name = "pt_status", length = 1)
	public String getPtStatus() {
		return this.ptStatus;
	}

	public void setPtStatus(String ptStatus) {
		this.ptStatus = ptStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubProductType")
	public Set<IclubQuote> getIclubQuotes() {
		return this.iclubQuotes;
	}

	public void setIclubQuotes(Set<IclubQuote> iclubQuotes) {
		this.iclubQuotes = iclubQuotes;
	}

}