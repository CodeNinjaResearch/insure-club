package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * IclubVehicleType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_vehicle_type", catalog = "iclubdb")
@NamedNativeQueries({ @NamedNativeQuery(name = "getVehicleTypeBySD", query = "select * from iclub_vehicle_type where lower(vt_short_desc) = lower(:sd) and vt_id <> :id", resultClass = IclubVehicleType.class) })
public class IclubVehicleType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6053822260895260708L;
	private Long vtId;
	private String vtShortDesc;
	private String vtLongDesc;
	private String vtStatus;

	// Constructors

	/** default constructor */
	public IclubVehicleType() {
	}

	/** minimal constructor */
	public IclubVehicleType(Long vtId) {
		this.vtId = vtId;
	}

	/** full constructor */
	public IclubVehicleType(Long vtId, String vtShortDesc, String vtLongDesc, String vtStatus) {
		this.vtId = vtId;
		this.vtShortDesc = vtShortDesc;
		this.vtLongDesc = vtLongDesc;
		this.vtStatus = vtStatus;
	}

	// Property accessors
	@Id
	@Column(name = "vt_id", unique = true, nullable = false)
	public Long getVtId() {
		return this.vtId;
	}

	public void setVtId(Long vtId) {
		this.vtId = vtId;
	}

	@Column(name = "vt_short_desc", length = 4)
	public String getVtShortDesc() {
		return this.vtShortDesc;
	}

	public void setVtShortDesc(String vtShortDesc) {
		this.vtShortDesc = vtShortDesc;
	}

	@Column(name = "vt_long_desc", length = 500)
	public String getVtLongDesc() {
		return this.vtLongDesc;
	}

	public void setVtLongDesc(String vtLongDesc) {
		this.vtLongDesc = vtLongDesc;
	}

	@Column(name = "vt_status", length = 1)
	public String getVtStatus() {
		return this.vtStatus;
	}

	public void setVtStatus(String vtStatus) {
		this.vtStatus = vtStatus;
	}

}