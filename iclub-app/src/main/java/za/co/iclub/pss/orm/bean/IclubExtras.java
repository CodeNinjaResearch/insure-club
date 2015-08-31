package za.co.iclub.pss.orm.bean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * IclubExtras entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_extras", catalog = "iclubdb")
public class IclubExtras implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4961264413399472751L;
	private Long EId;
	private IclubPerson iclubPerson;
	private String EDesc;
	private String EStatus;
	private Date ECrtdDt;
	
	// Constructors
	
	/** default constructor */
	public IclubExtras() {
	}
	
	/** minimal constructor */
	public IclubExtras(Long EId) {
		this.EId = EId;
	}
	
	/** full constructor */
	public IclubExtras(Long EId, IclubPerson iclubPerson, String EDesc, String EStatus, Date ECrtdDt) {
		this.EId = EId;
		this.iclubPerson = iclubPerson;
		this.EDesc = EDesc;
		this.EStatus = EStatus;
		this.ECrtdDt = ECrtdDt;
	}
	
	// Property accessors
	@Id
	@Column(name = "e_id", unique = true, nullable = false)
	public Long getEId() {
		return this.EId;
	}
	
	public void setEId(Long EId) {
		this.EId = EId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "e_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}
	
	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	@Column(name = "e_desc", length = 450)
	public String getEDesc() {
		return this.EDesc;
	}
	
	public void setEDesc(String EDesc) {
		this.EDesc = EDesc;
	}
	
	@Column(name = "e_status", length = 1)
	public String getEStatus() {
		return this.EStatus;
	}
	
	public void setEStatus(String EStatus) {
		this.EStatus = EStatus;
	}
	
	@Column(name = "e_crtd_dt", length = 19)
	public Date getECrtdDt() {
		return this.ECrtdDt;
	}
	
	public void setECrtdDt(Date ECrtdDt) {
		this.ECrtdDt = ECrtdDt;
	}
	
}