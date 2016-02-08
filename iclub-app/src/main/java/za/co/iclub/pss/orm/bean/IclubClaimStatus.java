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
 * IclubClaimStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_claim_status", catalog = "iclubdb")
public class IclubClaimStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3916175446938471932L;
	private Long csId;
	private String csShortDesc;
	private String csLongDesc;
	private String csStatus;
	private Set<IclubClaimItem> iclubClaimItems = new HashSet<IclubClaimItem>(0);
	private Set<IclubClaim> iclubClaims = new HashSet<IclubClaim>(0);

	// Constructors

	/** default constructor */
	public IclubClaimStatus() {
	}

	/** minimal constructor */
	public IclubClaimStatus(Long csId) {
		this.csId = csId;
	}

	/** full constructor */
	public IclubClaimStatus(Long csId, String csShortDesc, String csLongDesc, String csStatus, Set<IclubClaimItem> iclubClaimItems, Set<IclubClaim> iclubClaims) {
		this.csId = csId;
		this.csShortDesc = csShortDesc;
		this.csLongDesc = csLongDesc;
		this.csStatus = csStatus;
		this.iclubClaimItems = iclubClaimItems;
		this.iclubClaims = iclubClaims;
	}

	// Property accessors
	@Id
	@Column(name = "cs_id", unique = true, nullable = false)
	public Long getCsId() {
		return this.csId;
	}

	public void setCsId(Long csId) {
		this.csId = csId;
	}

	@Column(name = "cs_short_desc", length = 4)
	public String getCsShortDesc() {
		return this.csShortDesc;
	}

	public void setCsShortDesc(String csShortDesc) {
		this.csShortDesc = csShortDesc;
	}

	@Column(name = "cs_long_desc", length = 500)
	public String getCsLongDesc() {
		return this.csLongDesc;
	}

	public void setCsLongDesc(String csLongDesc) {
		this.csLongDesc = csLongDesc;
	}

	@Column(name = "cs_status", length = 1)
	public String getCsStatus() {
		return this.csStatus;
	}

	public void setCsStatus(String csStatus) {
		this.csStatus = csStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubClaimStatus")
	public Set<IclubClaimItem> getIclubClaimItems() {
		return this.iclubClaimItems;
	}

	public void setIclubClaimItems(Set<IclubClaimItem> iclubClaimItems) {
		this.iclubClaimItems = iclubClaimItems;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubClaimStatus")
	public Set<IclubClaim> getIclubClaims() {
		return this.iclubClaims;
	}

	public void setIclubClaims(Set<IclubClaim> iclubClaims) {
		this.iclubClaims = iclubClaims;
	}

}