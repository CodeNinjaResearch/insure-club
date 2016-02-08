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
 * IclubMaritialStatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_maritial_status", catalog = "iclubdb")
public class IclubMaritialStatus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5565570144782231426L;
	private Long msId;
	private String msShortDesc;
	private String msLongDesc;
	private String msStatus;
	private Set<IclubDriver> iclubDrivers = new HashSet<IclubDriver>(0);
	private Set<IclubPerson> iclubPersons = new HashSet<IclubPerson>(0);

	// Constructors

	/** default constructor */
	public IclubMaritialStatus() {
	}

	/** minimal constructor */
	public IclubMaritialStatus(Long msId) {
		this.msId = msId;
	}

	/** full constructor */
	public IclubMaritialStatus(Long msId, String msShortDesc, String msLongDesc, String msStatus, Set<IclubDriver> iclubDrivers, Set<IclubPerson> iclubPersons) {
		this.msId = msId;
		this.msShortDesc = msShortDesc;
		this.msLongDesc = msLongDesc;
		this.msStatus = msStatus;
		this.iclubDrivers = iclubDrivers;
		this.iclubPersons = iclubPersons;
	}

	// Property accessors
	@Id
	@Column(name = "ms_id", unique = true, nullable = false)
	public Long getMsId() {
		return this.msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	@Column(name = "ms_short_desc", length = 4)
	public String getMsShortDesc() {
		return this.msShortDesc;
	}

	public void setMsShortDesc(String msShortDesc) {
		this.msShortDesc = msShortDesc;
	}

	@Column(name = "ms_long_desc", length = 500)
	public String getMsLongDesc() {
		return this.msLongDesc;
	}

	public void setMsLongDesc(String msLongDesc) {
		this.msLongDesc = msLongDesc;
	}

	@Column(name = "ms_status", length = 1)
	public String getMsStatus() {
		return this.msStatus;
	}

	public void setMsStatus(String msStatus) {
		this.msStatus = msStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubMaritialStatus")
	public Set<IclubDriver> getIclubDrivers() {
		return this.iclubDrivers;
	}

	public void setIclubDrivers(Set<IclubDriver> iclubDrivers) {
		this.iclubDrivers = iclubDrivers;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubMaritialStatus")
	public Set<IclubPerson> getIclubPersons() {
		return this.iclubPersons;
	}

	public void setIclubPersons(Set<IclubPerson> iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

}