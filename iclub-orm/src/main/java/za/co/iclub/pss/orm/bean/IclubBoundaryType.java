package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubBoundaryType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_boundary_type", catalog = "iclubdb")
public class IclubBoundaryType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4674760845840674739L;
	private Long btId;
	private String btShortDesc;
	private String btLongDesc;
	private String btStatus;

	// Constructors

	/** default constructor */
	public IclubBoundaryType() {
	}

	/** minimal constructor */
	public IclubBoundaryType(Long btId) {
		this.btId = btId;
	}

	/** full constructor */
	public IclubBoundaryType(Long btId, String btShortDesc, String btLongDesc, String btStatus) {
		this.btId = btId;
		this.btShortDesc = btShortDesc;
		this.btLongDesc = btLongDesc;
		this.btStatus = btStatus;
	}

	// Property accessors
	@Id
	@Column(name = "bt_id", unique = true, nullable = false)
	public Long getBtId() {
		return this.btId;
	}

	public void setBtId(Long btId) {
		this.btId = btId;
	}

	@Column(name = "bt_short_desc", length = 4)
	public String getBtShortDesc() {
		return this.btShortDesc;
	}

	public void setBtShortDesc(String btShortDesc) {
		this.btShortDesc = btShortDesc;
	}

	@Column(name = "bt_long_desc", length = 500)
	public String getBtLongDesc() {
		return this.btLongDesc;
	}

	public void setBtLongDesc(String btLongDesc) {
		this.btLongDesc = btLongDesc;
	}

	@Column(name = "bt_status", length = 1)
	public String getBtStatus() {
		return this.btStatus;
	}

	public void setBtStatus(String btStatus) {
		this.btStatus = btStatus;
	}

}