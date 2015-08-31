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
 * IclubDocumentType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_document_type", catalog = "iclubdb")
public class IclubDocumentType implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1206735450588464611L;
	private Long dtId;
	private String dtShortDesc;
	private String dtLongDesc;
	private String dtStatus;
	private Set<IclubDocument> iclubDocuments = new HashSet<IclubDocument>(0);
	
	// Constructors
	
	/** default constructor */
	public IclubDocumentType() {
	}
	
	/** minimal constructor */
	public IclubDocumentType(Long dtId) {
		this.dtId = dtId;
	}
	
	/** full constructor */
	public IclubDocumentType(Long dtId, String dtShortDesc, String dtLongDesc, String dtStatus, Set<IclubDocument> iclubDocuments) {
		this.dtId = dtId;
		this.dtShortDesc = dtShortDesc;
		this.dtLongDesc = dtLongDesc;
		this.dtStatus = dtStatus;
		this.iclubDocuments = iclubDocuments;
	}
	
	// Property accessors
	@Id
	@Column(name = "dt_id", unique = true, nullable = false)
	public Long getDtId() {
		return this.dtId;
	}
	
	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}
	
	@Column(name = "dt_short_desc", length = 4)
	public String getDtShortDesc() {
		return this.dtShortDesc;
	}
	
	public void setDtShortDesc(String dtShortDesc) {
		this.dtShortDesc = dtShortDesc;
	}
	
	@Column(name = "dt_long_desc", length = 500)
	public String getDtLongDesc() {
		return this.dtLongDesc;
	}
	
	public void setDtLongDesc(String dtLongDesc) {
		this.dtLongDesc = dtLongDesc;
	}
	
	@Column(name = "dt_status", length = 1)
	public String getDtStatus() {
		return this.dtStatus;
	}
	
	public void setDtStatus(String dtStatus) {
		this.dtStatus = dtStatus;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubDocumentType")
	public Set<IclubDocument> getIclubDocuments() {
		return this.iclubDocuments;
	}
	
	public void setIclubDocuments(Set<IclubDocument> iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}
	
}