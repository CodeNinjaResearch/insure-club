package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubThatchType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_thatch_type", catalog = "iclubdb")
public class IclubThatchType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5101746394011736278L;
	private Long ttId;
	private String ttShortDesc;
	private String ttLongDesc;
	private String ttStatus;

	// Constructors

	/** default constructor */
	public IclubThatchType() {
	}

	/** minimal constructor */
	public IclubThatchType(Long ttId) {
		this.ttId = ttId;
	}

	/** full constructor */
	public IclubThatchType(Long ttId, String ttShortDesc, String ttLongDesc,
			String ttStatus) {
		this.ttId = ttId;
		this.ttShortDesc = ttShortDesc;
		this.ttLongDesc = ttLongDesc;
		this.ttStatus = ttStatus;
	}

	// Property accessors
	@Id
	@Column(name = "tt_id", unique = true, nullable = false)
	public Long getTtId() {
		return this.ttId;
	}

	public void setTtId(Long ttId) {
		this.ttId = ttId;
	}

	@Column(name = "tt_short_desc", length = 4)
	public String getTtShortDesc() {
		return this.ttShortDesc;
	}

	public void setTtShortDesc(String ttShortDesc) {
		this.ttShortDesc = ttShortDesc;
	}

	@Column(name = "tt_long_desc", length = 500)
	public String getTtLongDesc() {
		return this.ttLongDesc;
	}

	public void setTtLongDesc(String ttLongDesc) {
		this.ttLongDesc = ttLongDesc;
	}

	@Column(name = "tt_status", length = 1)
	public String getTtStatus() {
		return this.ttStatus;
	}

	public void setTtStatus(String ttStatus) {
		this.ttStatus = ttStatus;
	}

}