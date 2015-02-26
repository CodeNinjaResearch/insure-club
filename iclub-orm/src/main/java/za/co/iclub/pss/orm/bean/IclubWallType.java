package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubWallType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_wall_type", catalog = "iclubdb")
public class IclubWallType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7087677394103289393L;
	private Long wtId;
	private String wtShortDesc;
	private String wtLongDesc;
	private String wtStatus;

	// Constructors

	/** default constructor */
	public IclubWallType() {
	}

	/** minimal constructor */
	public IclubWallType(Long wtId) {
		this.wtId = wtId;
	}

	/** full constructor */
	public IclubWallType(Long wtId, String wtShortDesc, String wtLongDesc,
			String wtStatus) {
		this.wtId = wtId;
		this.wtShortDesc = wtShortDesc;
		this.wtLongDesc = wtLongDesc;
		this.wtStatus = wtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "wt_id", unique = true, nullable = false)
	public Long getWtId() {
		return this.wtId;
	}

	public void setWtId(Long wtId) {
		this.wtId = wtId;
	}

	@Column(name = "wt_short_desc", length = 4)
	public String getWtShortDesc() {
		return this.wtShortDesc;
	}

	public void setWtShortDesc(String wtShortDesc) {
		this.wtShortDesc = wtShortDesc;
	}

	@Column(name = "wt_long_desc", length = 500)
	public String getWtLongDesc() {
		return this.wtLongDesc;
	}

	public void setWtLongDesc(String wtLongDesc) {
		this.wtLongDesc = wtLongDesc;
	}

	@Column(name = "wt_status", length = 1)
	public String getWtStatus() {
		return this.wtStatus;
	}

	public void setWtStatus(String wtStatus) {
		this.wtStatus = wtStatus;
	}

}