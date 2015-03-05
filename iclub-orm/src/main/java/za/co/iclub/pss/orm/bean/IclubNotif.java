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
 * IclubNotif entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_notif")
public class IclubNotif implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1315751866686368608L;
	private String NId;
	private IclubPerson iclubPerson;
	private IclubNotificationType iclubNotificationType;
	private String NTitle;
	private String NBody;
	private String NFromAddr;
	private String NToList;
	private String NStatus;
	private Timestamp NCrtdDt;

	// Constructors

	/** default constructor */
	public IclubNotif() {
	}

	/** minimal constructor */
	public IclubNotif(String NId) {
		this.NId = NId;
	}

	/** full constructor */
	public IclubNotif(String NId, IclubPerson iclubPerson, IclubNotificationType iclubNotificationType, String NTitle, String NBody, String NFromAddr, String NToList, String NStatus, Timestamp NCrtdDt) {
		this.NId = NId;
		this.iclubPerson = iclubPerson;
		this.iclubNotificationType = iclubNotificationType;
		this.NTitle = NTitle;
		this.NBody = NBody;
		this.NFromAddr = NFromAddr;
		this.NToList = NToList;
		this.NStatus = NStatus;
		this.NCrtdDt = NCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "n_id", unique = true, nullable = false, length = 36)
	public String getNId() {
		return this.NId;
	}

	public void setNId(String NId) {
		this.NId = NId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "n_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "n_type_id")
	public IclubNotificationType getIclubNotificationType() {
		return this.iclubNotificationType;
	}

	public void setIclubNotificationType(IclubNotificationType iclubNotificationType) {
		this.iclubNotificationType = iclubNotificationType;
	}

	@Column(name = "n_title", length = 500)
	public String getNTitle() {
		return this.NTitle;
	}

	public void setNTitle(String NTitle) {
		this.NTitle = NTitle;
	}

	@Column(name = "n_body")
	public String getNBody() {
		return this.NBody;
	}

	public void setNBody(String NBody) {
		this.NBody = NBody;
	}

	@Column(name = "n_from_addr", length = 450)
	public String getNFromAddr() {
		return this.NFromAddr;
	}

	public void setNFromAddr(String NFromAddr) {
		this.NFromAddr = NFromAddr;
	}

	@Column(name = "n_to_list", length = 9999)
	public String getNToList() {
		return this.NToList;
	}

	public void setNToList(String NToList) {
		this.NToList = NToList;
	}

	@Column(name = "n_status", length = 45)
	public String getNStatus() {
		return this.NStatus;
	}

	public void setNStatus(String NStatus) {
		this.NStatus = NStatus;
	}

	@Column(name = "n_crtd_dt", length = 19)
	public Timestamp getNCrtdDt() {
		return this.NCrtdDt;
	}

	public void setNCrtdDt(Timestamp NCrtdDt) {
		this.NCrtdDt = NCrtdDt;
	}

}