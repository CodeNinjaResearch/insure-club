package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_message")
public class IclubMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1556044231597466645L;
	private String MId;
	private IclubPerson iclubPerson;
	private IclubSystemType iclubSystemTypeByMToSysId;
	private IclubMessageType iclubMessageType;
	private IclubSystemType iclubSystemTypeByMFromSysId;
	private Timestamp MSentDt;
	private String MTranId;
	private String MContent;
	private Timestamp MCrtdDt;

	// Constructors

	/** default constructor */
	public IclubMessage() {
	}

	/** minimal constructor */
	public IclubMessage(String MId) {
		this.MId = MId;
	}

	/** full constructor */
	public IclubMessage(String MId, IclubPerson iclubPerson, IclubSystemType iclubSystemTypeByMToSysId, IclubMessageType iclubMessageType, IclubSystemType iclubSystemTypeByMFromSysId, Timestamp MSentDt, String MTranId, String MContent, Timestamp MCrtdDt) {
		this.MId = MId;
		this.iclubPerson = iclubPerson;
		this.iclubSystemTypeByMToSysId = iclubSystemTypeByMToSysId;
		this.iclubMessageType = iclubMessageType;
		this.iclubSystemTypeByMFromSysId = iclubSystemTypeByMFromSysId;
		this.MSentDt = MSentDt;
		this.MTranId = MTranId;
		this.MContent = MContent;
		this.MCrtdDt = MCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "m_id", unique = true, nullable = false, length = 36)
	public String getMId() {
		return this.MId;
	}

	public void setMId(String MId) {
		this.MId = MId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_to_sys_id")
	public IclubSystemType getIclubSystemTypeByMToSysId() {
		return this.iclubSystemTypeByMToSysId;
	}

	public void setIclubSystemTypeByMToSysId(IclubSystemType iclubSystemTypeByMToSysId) {
		this.iclubSystemTypeByMToSysId = iclubSystemTypeByMToSysId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_type_id")
	public IclubMessageType getIclubMessageType() {
		return this.iclubMessageType;
	}

	public void setIclubMessageType(IclubMessageType iclubMessageType) {
		this.iclubMessageType = iclubMessageType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "m_from_sys_id")
	public IclubSystemType getIclubSystemTypeByMFromSysId() {
		return this.iclubSystemTypeByMFromSysId;
	}

	public void setIclubSystemTypeByMFromSysId(IclubSystemType iclubSystemTypeByMFromSysId) {
		this.iclubSystemTypeByMFromSysId = iclubSystemTypeByMFromSysId;
	}

	@Column(name = "m_sent_dt", length = 19)
	public Timestamp getMSentDt() {
		return this.MSentDt;
	}

	public void setMSentDt(Timestamp MSentDt) {
		this.MSentDt = MSentDt;
	}

	@Column(name = "m_tran_id", length = 36)
	public String getMTranId() {
		return this.MTranId;
	}

	public void setMTranId(String MTranId) {
		this.MTranId = MTranId;
	}

	@Column(name = "m_content")
	public String getMContent() {
		return this.MContent;
	}

	public void setMContent(String MContent) {
		this.MContent = MContent;
	}

	@Column(name = "m_crtd_dt", length = 19)
	public Timestamp getMCrtdDt() {
		return this.MCrtdDt;
	}

	public void setMCrtdDt(Timestamp MCrtdDt) {
		this.MCrtdDt = MCrtdDt;
	}

}