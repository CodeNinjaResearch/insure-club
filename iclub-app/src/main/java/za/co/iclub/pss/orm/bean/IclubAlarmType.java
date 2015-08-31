package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubAlarmType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_alarm_type", catalog = "iclubdb")
public class IclubAlarmType implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -231833432740669604L;
	private Long atId;
	private String atShortDesc;
	private String atLongDesc;
	private String atStatus;
	
	// Constructors
	
	/** default constructor */
	public IclubAlarmType() {
	}
	
	/** minimal constructor */
	public IclubAlarmType(Long atId) {
		this.atId = atId;
	}
	
	/** full constructor */
	public IclubAlarmType(Long atId, String atShortDesc, String atLongDesc, String atStatus) {
		this.atId = atId;
		this.atShortDesc = atShortDesc;
		this.atLongDesc = atLongDesc;
		this.atStatus = atStatus;
	}
	
	// Property accessors
	@Id
	@Column(name = "at_id", unique = true, nullable = false)
	public Long getAtId() {
		return this.atId;
	}
	
	public void setAtId(Long atId) {
		this.atId = atId;
	}
	
	@Column(name = "at_short_desc", length = 4)
	public String getAtShortDesc() {
		return this.atShortDesc;
	}
	
	public void setAtShortDesc(String atShortDesc) {
		this.atShortDesc = atShortDesc;
	}
	
	@Column(name = "at_long_desc", length = 500)
	public String getAtLongDesc() {
		return this.atLongDesc;
	}
	
	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}
	
	@Column(name = "at_status", length = 1)
	public String getAtStatus() {
		return this.atStatus;
	}
	
	public void setAtStatus(String atStatus) {
		this.atStatus = atStatus;
	}
	
}