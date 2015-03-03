package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubRoofType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_roof_type")
@NamedNativeQueries({ @NamedNativeQuery(name = "getRoofTypeBySD", query = "select * from iclub_roof_type where lower(rt_short_desc) = lower(:sd) and rt_id <> :id", resultClass = IclubRoofType.class) })
public class IclubRoofType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -950038305782479520L;
	private Long rtId;
	private String rtShortDesc;
	private String rtLongDesc;
	private String rtStatus;

	// Constructors

	/** default constructor */
	public IclubRoofType() {
	}

	/** minimal constructor */
	public IclubRoofType(Long rtId) {
		this.rtId = rtId;
	}

	/** full constructor */
	public IclubRoofType(Long rtId, String rtShortDesc, String rtLongDesc, String rtStatus) {
		this.rtId = rtId;
		this.rtShortDesc = rtShortDesc;
		this.rtLongDesc = rtLongDesc;
		this.rtStatus = rtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "rt_id", unique = true, nullable = false)
	public Long getRtId() {
		return this.rtId;
	}

	public void setRtId(Long rtId) {
		this.rtId = rtId;
	}

	@Column(name = "rt_short_desc", length = 4)
	public String getRtShortDesc() {
		return this.rtShortDesc;
	}

	public void setRtShortDesc(String rtShortDesc) {
		this.rtShortDesc = rtShortDesc;
	}

	@Column(name = "rt_long_desc", length = 500)
	public String getRtLongDesc() {
		return this.rtLongDesc;
	}

	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}

	@Column(name = "rt_status", length = 1)
	public String getRtStatus() {
		return this.rtStatus;
	}

	public void setRtStatus(String rtStatus) {
		this.rtStatus = rtStatus;
	}

}