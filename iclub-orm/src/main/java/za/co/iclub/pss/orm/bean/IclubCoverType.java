package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubCoverType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_cover_type", catalog = "iclubdb")
public class IclubCoverType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2744257241077599763L;
	private Long ctId;
	private String ctShortDesc;
	private String ctLongDesc;
	private String ctStatus;

	// Constructors

	/** default constructor */
	public IclubCoverType() {
	}

	/** minimal constructor */
	public IclubCoverType(Long ctId) {
		this.ctId = ctId;
	}

	/** full constructor */
	public IclubCoverType(Long ctId, String ctShortDesc, String ctLongDesc,
			String ctStatus) {
		this.ctId = ctId;
		this.ctShortDesc = ctShortDesc;
		this.ctLongDesc = ctLongDesc;
		this.ctStatus = ctStatus;
	}

	// Property accessors
	@Id
	@Column(name = "ct_id", unique = true, nullable = false)
	public Long getCtId() {
		return this.ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	@Column(name = "ct_short_desc", length = 4)
	public String getCtShortDesc() {
		return this.ctShortDesc;
	}

	public void setCtShortDesc(String ctShortDesc) {
		this.ctShortDesc = ctShortDesc;
	}

	@Column(name = "ct_long_desc", length = 500)
	public String getCtLongDesc() {
		return this.ctLongDesc;
	}

	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}

	@Column(name = "ct_status", length = 1)
	public String getCtStatus() {
		return this.ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

}