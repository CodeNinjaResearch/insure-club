package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubEvent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_event")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_event where e_crtd_by=:id", name = "getEventByUser", resultClass = IclubEvent.class) })
public class IclubEvent implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 526416131177256558L;
	private String EId;
	private IclubEventType iclubEventType;
	private IclubPerson iclubPerson;
	private String EDesc;
	private Timestamp ECrtdDt;

	// Constructors

	/** default constructor */
	public IclubEvent() {
	}

	/** minimal constructor */
	public IclubEvent(String EId) {
		this.EId = EId;
	}

	/** full constructor */
	public IclubEvent(String EId, IclubEventType iclubEventType, IclubPerson iclubPerson, String EDesc, Timestamp ECrtdDt) {
		this.EId = EId;
		this.iclubEventType = iclubEventType;
		this.iclubPerson = iclubPerson;
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
	@JoinColumn(name = "e_type_id")
	public IclubEventType getIclubEventType() {
		return this.iclubEventType;
	}

	public void setIclubEventType(IclubEventType iclubEventType) {
		this.iclubEventType = iclubEventType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "e_desc", length = 450)
	public String getEDesc() {
		return this.EDesc;
	}

	public void setEDesc(String EDesc) {
		this.EDesc = EDesc;
	}

	@Column(name = "e_crtd_dt", length = 19)
	public Timestamp getECrtdDt() {
		return this.ECrtdDt;
	}

	public void setECrtdDt(Timestamp ECrtdDt) {
		this.ECrtdDt = ECrtdDt;
	}

}