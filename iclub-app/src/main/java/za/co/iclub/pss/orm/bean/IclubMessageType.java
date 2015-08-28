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
 * IclubMessageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_message_type", catalog = "iclubdb")
public class IclubMessageType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8755866992996462950L;
	private Long mtId;
	private String mtShortDesc;
	private String mtLongDesc;
	private String mtStatus;
	private Set<IclubMessage> iclubMessages = new HashSet<IclubMessage>(0);

	// Constructors

	/** default constructor */
	public IclubMessageType() {
	}

	/** minimal constructor */
	public IclubMessageType(Long mtId) {
		this.mtId = mtId;
	}

	/** full constructor */
	public IclubMessageType(Long mtId, String mtShortDesc, String mtLongDesc,
			String mtStatus, Set<IclubMessage> iclubMessages) {
		this.mtId = mtId;
		this.mtShortDesc = mtShortDesc;
		this.mtLongDesc = mtLongDesc;
		this.mtStatus = mtStatus;
		this.iclubMessages = iclubMessages;
	}

	// Property accessors
	@Id
	@Column(name = "mt_id", unique = true, nullable = false)
	public Long getMtId() {
		return this.mtId;
	}

	public void setMtId(Long mtId) {
		this.mtId = mtId;
	}

	@Column(name = "mt_short_desc", length = 4)
	public String getMtShortDesc() {
		return this.mtShortDesc;
	}

	public void setMtShortDesc(String mtShortDesc) {
		this.mtShortDesc = mtShortDesc;
	}

	@Column(name = "mt_long_desc", length = 500)
	public String getMtLongDesc() {
		return this.mtLongDesc;
	}

	public void setMtLongDesc(String mtLongDesc) {
		this.mtLongDesc = mtLongDesc;
	}

	@Column(name = "mt_status", length = 1)
	public String getMtStatus() {
		return this.mtStatus;
	}

	public void setMtStatus(String mtStatus) {
		this.mtStatus = mtStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubMessageType")
	public Set<IclubMessage> getIclubMessages() {
		return this.iclubMessages;
	}

	public void setIclubMessages(Set<IclubMessage> iclubMessages) {
		this.iclubMessages = iclubMessages;
	}

}