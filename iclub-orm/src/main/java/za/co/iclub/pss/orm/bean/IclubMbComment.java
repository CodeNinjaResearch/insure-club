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
 * IclubMbComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_mb_comment", catalog = "iclubdb")
public class IclubMbComment implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1843535524700319345L;
	private String mbcId;
	private IclubPerson iclubPerson;
	private IclubMessageBoard iclubMessageBoard;
	private String mbcDesc;
	private Date mbcCrtdDt;

	// Constructors

	/** default constructor */
	public IclubMbComment() {
	}

	/** minimal constructor */
	public IclubMbComment(String mbcId) {
		this.mbcId = mbcId;
	}

	/** full constructor */
	public IclubMbComment(String mbcId, IclubPerson iclubPerson, IclubMessageBoard iclubMessageBoard, String mbcDesc, Date mbcCrtdDt) {
		this.mbcId = mbcId;
		this.iclubPerson = iclubPerson;
		this.iclubMessageBoard = iclubMessageBoard;
		this.mbcDesc = mbcDesc;
		this.mbcCrtdDt = mbcCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "mbc_id", unique = true, nullable = false, length = 36)
	public String getMbcId() {
		return this.mbcId;
	}

	public void setMbcId(String mbcId) {
		this.mbcId = mbcId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mbc_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mbc_mb_id")
	public IclubMessageBoard getIclubMessageBoard() {
		return this.iclubMessageBoard;
	}

	public void setIclubMessageBoard(IclubMessageBoard iclubMessageBoard) {
		this.iclubMessageBoard = iclubMessageBoard;
	}

	@Column(name = "mbc_desc", length = 999)
	public String getMbcDesc() {
		return this.mbcDesc;
	}

	public void setMbcDesc(String mbcDesc) {
		this.mbcDesc = mbcDesc;
	}

	@Column(name = "mbc_crtd_dt", length = 19)
	public Date getMbcCrtdDt() {
		return this.mbcCrtdDt;
	}

	public void setMbcCrtdDt(Date mbcCrtdDt) {
		this.mbcCrtdDt = mbcCrtdDt;
	}

}