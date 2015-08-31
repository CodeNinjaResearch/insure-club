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
 * IclubBarType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_bar_type", catalog = "iclubdb")
public class IclubBarType implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7312881882880072968L;
	private Long btId;
	private String btShortDesc;
	private String btLongDesc;
	private String btStatus;
	private Set<IclubProperty> iclubProperties = new HashSet<IclubProperty>(0);
	
	// Constructors
	
	/** default constructor */
	public IclubBarType() {
	}
	
	/** minimal constructor */
	public IclubBarType(Long btId) {
		this.btId = btId;
	}
	
	/** full constructor */
	public IclubBarType(Long btId, String btShortDesc, String btLongDesc, String btStatus, Set<IclubProperty> iclubProperties) {
		this.btId = btId;
		this.btShortDesc = btShortDesc;
		this.btLongDesc = btLongDesc;
		this.btStatus = btStatus;
		this.iclubProperties = iclubProperties;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubBarType")
	public Set<IclubProperty> getIclubProperties() {
		return this.iclubProperties;
	}
	
	public void setIclubProperties(Set<IclubProperty> iclubProperties) {
		this.iclubProperties = iclubProperties;
	}
	
}