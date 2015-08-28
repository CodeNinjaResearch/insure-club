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
 * IclubEntityType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_entity_type", catalog = "iclubdb")
public class IclubEntityType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4809949485408370000L;
	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etTblNm;
	private String etStatus;
	private Set<IclubRateType> iclubRateTypes = new HashSet<IclubRateType>(0);
	private Set<IclubField> iclubFields = new HashSet<IclubField>(0);
	private Set<IclubDocument> iclubDocuments = new HashSet<IclubDocument>(0);

	// Constructors

	/** default constructor */
	public IclubEntityType() {
	}

	/** minimal constructor */
	public IclubEntityType(Long etId) {
		this.etId = etId;
	}

	/** full constructor */
	public IclubEntityType(Long etId, String etShortDesc, String etLongDesc,
			String etTblNm, String etStatus, Set<IclubRateType> iclubRateTypes,
			Set<IclubField> iclubFields, Set<IclubDocument> iclubDocuments) {
		this.etId = etId;
		this.etShortDesc = etShortDesc;
		this.etLongDesc = etLongDesc;
		this.etTblNm = etTblNm;
		this.etStatus = etStatus;
		this.iclubRateTypes = iclubRateTypes;
		this.iclubFields = iclubFields;
		this.iclubDocuments = iclubDocuments;
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

	@Column(name = "et_tbl_nm", length = 450)
	public String getEtTblNm() {
		return this.etTblNm;
	}

	public void setEtTblNm(String etTblNm) {
		this.etTblNm = etTblNm;
	}

	@Column(name = "et_status", length = 1)
	public String getEtStatus() {
		return this.etStatus;
	}

	public void setEtStatus(String etStatus) {
		this.etStatus = etStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubEntityType")
	public Set<IclubRateType> getIclubRateTypes() {
		return this.iclubRateTypes;
	}

	public void setIclubRateTypes(Set<IclubRateType> iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubEntityType")
	public Set<IclubField> getIclubFields() {
		return this.iclubFields;
	}

	public void setIclubFields(Set<IclubField> iclubFields) {
		this.iclubFields = iclubFields;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubEntityType")
	public Set<IclubDocument> getIclubDocuments() {
		return this.iclubDocuments;
	}

	public void setIclubDocuments(Set<IclubDocument> iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}

}