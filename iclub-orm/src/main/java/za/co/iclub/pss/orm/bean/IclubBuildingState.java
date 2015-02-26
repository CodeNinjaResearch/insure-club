package za.co.iclub.pss.orm.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IclubBuildingState entity. @author Venu Madhav Pattamatta
 */
@Entity
@Table(name = "iclub_building_state", catalog = "iclubdb")
public class IclubBuildingState implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5902524561919689858L;
	private Long bsId;
	private String bsShortDesc;
	private String bsLongDesc;
	private String bsStatus;

	// Constructors

	/** default constructor */
	public IclubBuildingState() {
	}

	/** minimal constructor */
	public IclubBuildingState(Long bsId) {
		this.bsId = bsId;
	}

	/** full constructor */
	public IclubBuildingState(Long bsId, String bsShortDesc, String bsLongDesc,
			String bsStatus) {
		this.bsId = bsId;
		this.bsShortDesc = bsShortDesc;
		this.bsLongDesc = bsLongDesc;
		this.bsStatus = bsStatus;
	}

	// Property accessors
	@Id
	@Column(name = "bs_id", unique = true, nullable = false)
	public Long getBsId() {
		return this.bsId;
	}

	public void setBsId(Long bsId) {
		this.bsId = bsId;
	}

	@Column(name = "bs_short_desc", length = 4)
	public String getBsShortDesc() {
		return this.bsShortDesc;
	}

	public void setBsShortDesc(String bsShortDesc) {
		this.bsShortDesc = bsShortDesc;
	}

	@Column(name = "bs_long_desc", length = 500)
	public String getBsLongDesc() {
		return this.bsLongDesc;
	}

	public void setBsLongDesc(String bsLongDesc) {
		this.bsLongDesc = bsLongDesc;
	}

	@Column(name = "bs_status", length = 1)
	public String getBsStatus() {
		return this.bsStatus;
	}

	public void setBsStatus(String bsStatus) {
		this.bsStatus = bsStatus;
	}

}