package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubEventType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_event_type", catalog = "iclubdb")
public class IclubEventType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5314591037572508497L;
	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etStatus;

	// Constructors

	/** default constructor */
	public IclubEventType() {
	}

	/** minimal constructor */
	public IclubEventType(Long etId) {
		this.etId = etId;
	}

	/** full constructor */
	public IclubEventType(Long etId, String etShortDesc, String etLongDesc,
			String etStatus) {
		this.etId = etId;
		this.etShortDesc = etShortDesc;
		this.etLongDesc = etLongDesc;
		this.etStatus = etStatus;
	}

	// Property accessors
	@Id
	@Column(name = "et_id", unique = true, nullable = false)
	public Long getEtId() {
		return this.etId;
	}

	public void setEtId(Long etId) {
		this.etId = etId;
	}

	@Column(name = "et_short_desc", length = 4)
	public String getEtShortDesc() {
		return this.etShortDesc;
	}

	public void setEtShortDesc(String etShortDesc) {
		this.etShortDesc = etShortDesc;
	}

	@Column(name = "et_long_desc", length = 500)
	public String getEtLongDesc() {
		return this.etLongDesc;
	}

	public void setEtLongDesc(String etLongDesc) {
		this.etLongDesc = etLongDesc;
	}

	@Column(name = "et_status", length = 1)
	public String getEtStatus() {
		return this.etStatus;
	}

	public void setEtStatus(String etStatus) {
		this.etStatus = etStatus;
	}

}