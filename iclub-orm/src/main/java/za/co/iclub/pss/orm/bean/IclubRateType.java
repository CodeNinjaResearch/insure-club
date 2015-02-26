package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubRateType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_rate_type", catalog = "iclubdb")
public class IclubRateType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2636062017875435455L;
	private Long rtId;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;

	// Constructors

	/** default constructor */
	public IclubRateType() {
	}

	/** minimal constructor */
	public IclubRateType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRateType(Long rtId, String rtShortDesc, String rtLongDesc,
			String rtStatus) {
		this.rtId = rtId;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtStatus = rtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

}