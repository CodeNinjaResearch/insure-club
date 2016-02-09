package za.co.iclub.pss.orm.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubEventType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_event_type", catalog = "iclubdb")
public class IclubEventType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -658849497021815449L;
	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etStatus;
	private Set<IclubEvent> iclubEvents = new HashSet<IclubEvent>(0);

	// Constructors

	/** default constructor */
	public IclubEventType() {
	}

	/** minimal constructor */
	public IclubEventType(Long etId) {
		this.etId = etId;
	}

	/** full constructor */
	public IclubEventType(Long etId, String etShortDesc, String etLongDesc, String etStatus, Set<IclubEvent> iclubEvents) {
		this.etId = etId;
		this.etShortDesc = etShortDesc;
		this.etLongDesc = etLongDesc;
		this.etStatus = etStatus;
		this.iclubEvents = iclubEvents;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubEventType")
	public Set<IclubEvent> getIclubEvents() {
		return this.iclubEvents;
	}

	public void setIclubEvents(Set<IclubEvent> iclubEvents) {
		this.iclubEvents = iclubEvents;
	}

}