package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubSupplierType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_supplier_type", catalog = "iclubdb")
public class IclubSupplierType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5650824419552056263L;
	private Long stId;
	private String stShortDesc;
	private String stLongDesc;
	private String stStatus;

	// Constructors

	/** default constructor */
	public IclubSupplierType() {
	}

	/** minimal constructor */
	public IclubSupplierType(Long stId) {
		this.stId = stId;
	}

	/** full constructor */
	public IclubSupplierType(Long stId, String stShortDesc, String stLongDesc,
			String stStatus) {
		this.stId = stId;
		this.stShortDesc = stShortDesc;
		this.stLongDesc = stLongDesc;
		this.stStatus = stStatus;
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

}