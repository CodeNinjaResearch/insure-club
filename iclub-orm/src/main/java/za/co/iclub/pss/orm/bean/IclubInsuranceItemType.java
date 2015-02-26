package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubInsuranceItemType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_insurance_item_type", catalog = "iclubdb")
public class IclubInsuranceItemType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7094918716190785324L;
	private Long iitId;
	private String iitShortDesc;
	private String iitLongDesc;
	private String iitStatus;

	// Constructors

	/** default constructor */
	public IclubInsuranceItemType() {
	}

	/** minimal constructor */
	public IclubInsuranceItemType(Long iitId) {
		this.iitId = iitId;
	}

	/** full constructor */
	public IclubInsuranceItemType(Long iitId, String iitShortDesc,
			String iitLongDesc, String iitStatus) {
		this.iitId = iitId;
		this.iitShortDesc = iitShortDesc;
		this.iitLongDesc = iitLongDesc;
		this.iitStatus = iitStatus;
	}

	// Property accessors
	@Id
	@Column(name = "iit_id", unique = true, nullable = false)
	public Long getIitId() {
		return this.iitId;
	}

	public void setIitId(Long iitId) {
		this.iitId = iitId;
	}

	@Column(name = "iit_short_desc", length = 4)
	public String getIitShortDesc() {
		return this.iitShortDesc;
	}

	public void setIitShortDesc(String iitShortDesc) {
		this.iitShortDesc = iitShortDesc;
	}

	@Column(name = "iit_long_desc", length = 500)
	public String getIitLongDesc() {
		return this.iitLongDesc;
	}

	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}

	@Column(name = "iit_status", length = 1)
	public String getIitStatus() {
		return this.iitStatus;
	}

	public void setIitStatus(String iitStatus) {
		this.iitStatus = iitStatus;
	}

}