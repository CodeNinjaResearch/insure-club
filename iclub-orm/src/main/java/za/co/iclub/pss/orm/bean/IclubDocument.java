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
 * IclubDocument entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_document")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_document where d_crtd_by=:id", name = "getDocumentByUser", resultClass = IclubDocument.class) })
public class IclubDocument implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595608254237216420L;
	private String DId;
	private IclubDocumentType iclubDocumentType;
	private IclubPerson iclubPerson;
	private IclubEntityType iclubEntityType;
	private String DName;
	private String DMimeType;
	private Long DSize;
	private String DEntityId;
	private String DContent;
	private Timestamp DCrtdDt;
	private byte[] DBlob;

	// Constructors

	/** default constructor */
	public IclubDocument() {
	}

	/** minimal constructor */
	public IclubDocument(String DId) {
		this.DId = DId;
	}

	/** full constructor */
	public IclubDocument(String DId, IclubDocumentType iclubDocumentType, IclubPerson iclubPerson, IclubEntityType iclubEntityType, String DName, String DMimeType, Long DSize, String DEntityId, String DContent, Timestamp DCrtdDt) {
		this.DId = DId;
		this.iclubDocumentType = iclubDocumentType;
		this.iclubPerson = iclubPerson;
		this.iclubEntityType = iclubEntityType;
		this.DName = DName;
		this.DMimeType = DMimeType;
		this.DSize = DSize;
		this.DEntityId = DEntityId;
		this.DContent = DContent;
		this.DCrtdDt = DCrtdDt;
	}

	// Property accessors
	@Id
	@Column(name = "d_id", unique = true, nullable = false, length = 36)
	public String getDId() {
		return this.DId;
	}

	public void setDId(String DId) {
		this.DId = DId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_type_id")
	public IclubDocumentType getIclubDocumentType() {
		return this.iclubDocumentType;
	}

	public void setIclubDocumentType(IclubDocumentType iclubDocumentType) {
		this.iclubDocumentType = iclubDocumentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_entity_type_id")
	public IclubEntityType getIclubEntityType() {
		return this.iclubEntityType;
	}

	public void setIclubEntityType(IclubEntityType iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	@Column(name = "d_name", length = 999)
	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	@Column(name = "d_mime_type", length = 999)
	public String getDMimeType() {
		return this.DMimeType;
	}

	public void setDMimeType(String DMimeType) {
		this.DMimeType = DMimeType;
	}

	@Column(name = "d_size")
	public Long getDSize() {
		return this.DSize;
	}

	public void setDSize(Long DSize) {
		this.DSize = DSize;
	}

	@Column(name = "d_entity_id", length = 36)
	public String getDEntityId() {
		return this.DEntityId;
	}

	public void setDEntityId(String DEntityId) {
		this.DEntityId = DEntityId;
	}

	@Column(name = "d_content")
	public String getDContent() {
		return this.DContent;
	}

	public void setDContent(String DContent) {
		this.DContent = DContent;
	}

	@Column(name = "d_crtd_dt", length = 19)
	public Timestamp getDCrtdDt() {
		return this.DCrtdDt;
	}

	public void setDCrtdDt(Timestamp DCrtdDt) {
		this.DCrtdDt = DCrtdDt;
	}

	@Column(name = "d_blob")
	public byte[] getDBlob() {
		return DBlob;
	}

	public void setDBlob(byte[] dBlob) {
		DBlob = dBlob;
	}

}