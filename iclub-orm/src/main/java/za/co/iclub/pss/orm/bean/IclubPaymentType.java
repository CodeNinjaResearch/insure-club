package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubPaymentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_payment_type", catalog = "iclubdb")
public class IclubPaymentType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4363809389473514342L;
	private Long ptId;
	private String ptShortDesc;
	private String ptLongDesc;
	private String ptStatus;

	// Constructors

	/** default constructor */
	public IclubPaymentType() {
	}

	/** minimal constructor */
	public IclubPaymentType(Long ptId) {
		this.ptId = ptId;
	}

	/** full constructor */
	public IclubPaymentType(Long ptId, String ptShortDesc, String ptLongDesc, String ptStatus) {
		this.ptId = ptId;
		this.ptShortDesc = ptShortDesc;
		this.ptLongDesc = ptLongDesc;
		this.ptStatus = ptStatus;
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

}