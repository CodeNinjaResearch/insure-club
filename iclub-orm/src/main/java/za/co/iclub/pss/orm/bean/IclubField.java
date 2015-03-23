package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubField entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_field")
public class IclubField implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1910592171729543072L;
	private Long FId;
	private IclubEntityType iclubEntityType;
	private String FName;
	private String FDesc;
	private String FType;
	private String FStatus;

	// Constructors

	/** default constructor */
	public IclubField() {
	}

	/** minimal constructor */
	public IclubField(Long FId) {
		this.FId = FId;
	}

	/** full constructor */
	public IclubField(Long FId, IclubEntityType iclubEntityType, String FName, String FDesc, String FType, String FStatus) {
		this.FId = FId;
		this.iclubEntityType = iclubEntityType;
		this.FName = FName;
		this.FDesc = FDesc;
		this.FType = FType;
		this.FStatus = FStatus;
	}

	// Property accessors
	@Id
	@Column(name = "f_id", unique = true, nullable = false)
	public Long getFId() {
		return this.FId;
	}

	public void setFId(Long FId) {
		this.FId = FId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "f_entity_id")
	public IclubEntityType getIclubEntityType() {
		return this.iclubEntityType;
	}

	public void setIclubEntityType(IclubEntityType iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	@Column(name = "f_name", length = 450)
	public String getFName() {
		return this.FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	@Column(name = "f_desc", length = 450)
	public String getFDesc() {
		return this.FDesc;
	}

	public void setFDesc(String FDesc) {
		this.FDesc = FDesc;
	}

	@Column(name = "f_type", length = 450)
	public String getFType() {
		return this.FType;
	}

	public void setFType(String FType) {
		this.FType = FType;
	}

	@Column(name = "f_status", length = 1)
	public String getFStatus() {
		return this.FStatus;
	}

	public void setFStatus(String FStatus) {
		this.FStatus = FStatus;
	}

}