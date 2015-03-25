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
 * IclubPolicyStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_policy_status")
@NamedNativeQueries({ @NamedNativeQuery(name = "getPolicyStatusBySD", query = "select * from iclub_policy_status where lower(ps_short_desc) = lower(:sd) and ps_id <> :id", resultClass = IclubPolicyStatus.class) })
public class IclubPolicyStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7359840649862662567L;
	private Long psId;
	private String psShortDesc;
	private String psLongDesc;
	private String psStatus;
	private Set<IclubPolicy> iclubPolicies = new HashSet<IclubPolicy>(0);

	// Constructors

	/** default constructor */
	public IclubPolicyStatus() {
	}

	/** minimal constructor */
	public IclubPolicyStatus(Long psId) {
		this.psId = psId;
	}

	/** full constructor */
	public IclubPolicyStatus(Long psId, String psShortDesc, String psLongDesc, String psStatus, Set<IclubPolicy> iclubPolicies) {
		this.psId = psId;
		this.psShortDesc = psShortDesc;
		this.psLongDesc = psLongDesc;
		this.psStatus = psStatus;
		this.iclubPolicies = iclubPolicies;
	}

	// Property accessors
	@Id
	@Column(name = "ps_id", unique = true, nullable = false)
	public Long getPsId() {
		return this.psId;
	}

	public void setPsId(Long psId) {
		this.psId = psId;
	}

	@Column(name = "ps_short_desc", length = 4)
	public String getPsShortDesc() {
		return this.psShortDesc;
	}

	public void setPsShortDesc(String psShortDesc) {
		this.psShortDesc = psShortDesc;
	}

	@Column(name = "ps_long_desc", length = 500)
	public String getPsLongDesc() {
		return this.psLongDesc;
	}

	public void setPsLongDesc(String psLongDesc) {
		this.psLongDesc = psLongDesc;
	}

	@Column(name = "ps_status", length = 1)
	public String getPsStatus() {
		return this.psStatus;
	}

	public void setPsStatus(String psStatus) {
		this.psStatus = psStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPolicyStatus")
	public Set<IclubPolicy> getIclubPolicies() {
		return this.iclubPolicies;
	}

	public void setIclubPolicies(Set<IclubPolicy> iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

}