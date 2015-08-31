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
 * IclubPaymentStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_payment_status", catalog = "iclubdb")
public class IclubPaymentStatus implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3334770448452915634L;
	private Long psId;
	private String psShortDesc;
	private String psLongDesc;
	private String psStatus;
	private Set<IclubPayment> iclubPayments = new HashSet<IclubPayment>(0);
	
	// Constructors
	
	/** default constructor */
	public IclubPaymentStatus() {
	}
	
	/** minimal constructor */
	public IclubPaymentStatus(Long psId) {
		this.psId = psId;
	}
	
	/** full constructor */
	public IclubPaymentStatus(Long psId, String psShortDesc, String psLongDesc, String psStatus, Set<IclubPayment> iclubPayments) {
		this.psId = psId;
		this.psShortDesc = psShortDesc;
		this.psLongDesc = psLongDesc;
		this.psStatus = psStatus;
		this.iclubPayments = iclubPayments;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubPaymentStatus")
	public Set<IclubPayment> getIclubPayments() {
		return this.iclubPayments;
	}
	
	public void setIclubPayments(Set<IclubPayment> iclubPayments) {
		this.iclubPayments = iclubPayments;
	}
	
}