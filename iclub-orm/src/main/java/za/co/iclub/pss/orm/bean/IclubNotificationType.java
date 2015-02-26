package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubNotificationType entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_notification_type", catalog = "iclubdb")
public class IclubNotificationType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 424241877222722469L;
	private Long ntId;
	private String ntShortDesc;
	private String ntLongDesc;
	private String ntStatus;

	// Constructors

	/** default constructor */
	public IclubNotificationType() {
	}

	/** minimal constructor */
	public IclubNotificationType(Long ntId) {
		this.ntId = ntId;
	}

	/** full constructor */
	public IclubNotificationType(Long ntId, String ntShortDesc,
			String ntLongDesc, String ntStatus) {
		this.ntId = ntId;
		this.ntShortDesc = ntShortDesc;
		this.ntLongDesc = ntLongDesc;
		this.ntStatus = ntStatus;
	}

	// Property accessors
	@Id
	@Column(name = "nt_id", unique = true, nullable = false)
	public Long getNtId() {
		return this.ntId;
	}

	public void setNtId(Long ntId) {
		this.ntId = ntId;
	}

	@Column(name = "nt_short_desc", length = 4)
	public String getNtShortDesc() {
		return this.ntShortDesc;
	}

	public void setNtShortDesc(String ntShortDesc) {
		this.ntShortDesc = ntShortDesc;
	}

	@Column(name = "nt_long_desc", length = 500)
	public String getNtLongDesc() {
		return this.ntLongDesc;
	}

	public void setNtLongDesc(String ntLongDesc) {
		this.ntLongDesc = ntLongDesc;
	}

	@Column(name = "nt_status", length = 1)
	public String getNtStatus() {
		return this.ntStatus;
	}

	public void setNtStatus(String ntStatus) {
		this.ntStatus = ntStatus;
	}

}