package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_event", catalog = "iclubdb")
public class IclubEvent implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4063032964216319772L;
	private String EId;
	private IclubPerson iclubPerson;
	private IclubEventType iclubEventType;
	private String EDesc;
	private Date ECrtdDt;
	
	// Constructors
	
	/** default constructor */
	public IclubEvent() {
	}
	
	/** minimal constructor */
	public IclubEvent(String EId) {
		this.EId = EId;
	}
	
	/** full constructor */
	public IclubEvent(String EId, IclubPerson iclubPerson, IclubEventType iclubEventType, String EDesc, Date ECrtdDt) {
		this.EId = EId;
		this.iclubPerson = iclubPerson;
		this.iclubEventType = iclubEventType;
		this.EDesc = EDesc;
		this.ECrtdDt = ECrtdDt;
	}
	
	// Property accessors
	@Id
	@Column(name = "e_id", unique = true, nullable = false, length = 36)
	public String getEId() {
		return this.EId;
	}
	
	public void setEId(String EId) {
		this.EId = EId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}
	
	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_type_id")
	public IclubEventType getIclubEventType() {
		return this.iclubEventType;
	}
	
	public void setIclubEventType(IclubEventType iclubEventType) {
		this.iclubEventType = iclubEventType;
	}
	
	@Column(name = "e_desc", length = 450)
	public String getEDesc() {
		return this.EDesc;
	}
	
	public void setEDesc(String EDesc) {
		this.EDesc = EDesc;
	}
	
	@Column(name = "e_crtd_dt", length = 19)
	public Date getECrtdDt() {
		return this.ECrtdDt;
	}
	
	public void setECrtdDt(Date ECrtdDt) {
		this.ECrtdDt = ECrtdDt;
	}
	
}