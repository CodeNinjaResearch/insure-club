package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubMaritialStatus entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_maritial_status", catalog = "iclubdb")
public class IclubMaritialStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1611703298795919365L;
	private Long msId;
	private String msShortDesc;
	private String msLongDesc;
	private String msStatus;

	// Constructors

	/** default constructor */
	public IclubMaritialStatus() {
	}

	/** minimal constructor */
	public IclubMaritialStatus(Long msId) {
		this.msId = msId;
	}

	/** full constructor */
	public IclubMaritialStatus(Long msId, String msShortDesc,
			String msLongDesc, String msStatus) {
		this.msId = msId;
		this.msShortDesc = msShortDesc;
		this.msLongDesc = msLongDesc;
		this.msStatus = msStatus;
	}

	// Property accessors
	@Id
	@Column(name = "ms_id", unique = true, nullable = false)
	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	@Column(name = "ms_short_desc", length = 4)
	public String getMsShortDesc() {
		return this.msShortDesc;
	}

	public void setMsShortDesc(String msShortDesc) {
		this.msShortDesc = msShortDesc;
	}

	@Column(name = "ms_long_desc", length = 500)
	public String getMsLongDesc() {
		return this.msLongDesc;
	}

	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}

	@Column(name = "ms_status", length = 1)
	public String getMsStatus() {
		return this.msStatus;
	}

	public void setMsStatus(String msStatus) {
		this.msStatus = msStatus;
	}

}