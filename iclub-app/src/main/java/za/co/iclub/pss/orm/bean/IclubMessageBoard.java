package za.co.iclub.pss.orm.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * IclubMessageBoard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_message_board", catalog = "iclubdb")
public class IclubMessageBoard implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2771572034240238846L;
	private String mbId;
	private IclubPerson iclubPerson;
	private String mbTitle;
	private String mbContent;
	private String mbTag;
	private Date mbCrtdDt;
	private Set<IclubMbComment> iclubMbComments = new HashSet<IclubMbComment>(0);

	// Constructors

	/** default constructor */
	public IclubMessageBoard() {
	}

	/** minimal constructor */
	public IclubMessageBoard(String mbId) {
		this.mbId = mbId;
	}

	/** full constructor */
	public IclubMessageBoard(String mbId, IclubPerson iclubPerson,
			String mbTitle, String mbContent, String mbTag, Date mbCrtdDt,
			Set<IclubMbComment> iclubMbComments) {
		this.mbId = mbId;
		this.iclubPerson = iclubPerson;
		this.mbTitle = mbTitle;
		this.mbContent = mbContent;
		this.mbTag = mbTag;
		this.mbCrtdDt = mbCrtdDt;
		this.iclubMbComments = iclubMbComments;
	}

	// Property accessors
	@Id
	@Column(name = "mb_id", unique = true, nullable = false, length = 36)
	public String getMbId() {
		return this.mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mb_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "mb_title", length = 450)
	public String getMbTitle() {
		return this.mbTitle;
	}

	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}

	@Column(name = "mb_content")
	public String getMbContent() {
		return this.mbContent;
	}

	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}

	@Column(name = "mb_tag", length = 450)
	public String getMbTag() {
		return this.mbTag;
	}

	public void setMbTag(String mbTag) {
		this.mbTag = mbTag;
	}

	@Column(name = "mb_crtd_dt", length = 19)
	public Date getMbCrtdDt() {
		return this.mbCrtdDt;
	}

	public void setMbCrtdDt(Date mbCrtdDt) {
		this.mbCrtdDt = mbCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubMessageBoard")
	public Set<IclubMbComment> getIclubMbComments() {
		return this.iclubMbComments;
	}

	public void setIclubMbComments(Set<IclubMbComment> iclubMbComments) {
		this.iclubMbComments = iclubMbComments;
	}

}