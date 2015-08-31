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
 * IclubSystemType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_system_type", catalog = "iclubdb")
public class IclubSystemType implements java.io.Serializable {
	
	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4258413623987409025L;
	private Long stId;
	private String stShortDesc;
	private String stLongDesc;
	private String stStatus;
	private Set<IclubMessage> iclubMessagesForMFromSysId = new HashSet<IclubMessage>(0);
	private Set<IclubMessage> iclubMessagesForMToSysId = new HashSet<IclubMessage>(0);
	
	// Constructors
	
	/** default constructor */
	public IclubSystemType() {
	}
	
	/** minimal constructor */
	public IclubSystemType(Long stId) {
		this.stId = stId;
	}
	
	/** full constructor */
	public IclubSystemType(Long stId, String stShortDesc, String stLongDesc, String stStatus, Set<IclubMessage> iclubMessagesForMFromSysId, Set<IclubMessage> iclubMessagesForMToSysId) {
		this.stId = stId;
		this.stShortDesc = stShortDesc;
		this.stLongDesc = stLongDesc;
		this.stStatus = stStatus;
		this.iclubMessagesForMFromSysId = iclubMessagesForMFromSysId;
		this.iclubMessagesForMToSysId = iclubMessagesForMToSysId;
	}
	
	// Property accessors
	@Id
	@Column(name = "st_id", unique = true, nullable = false)
	public Long getStId() {
		return this.stId;
	}
	
	public void setStId(Long stId) {
		this.stId = stId;
	}
	
	@Column(name = "st_short_desc", length = 4)
	public String getStShortDesc() {
		return this.stShortDesc;
	}
	
	public void setStShortDesc(String stShortDesc) {
		this.stShortDesc = stShortDesc;
	}
	
	@Column(name = "st_long_desc", length = 500)
	public String getStLongDesc() {
		return this.stLongDesc;
	}
	
	public void setStLongDesc(String stLongDesc) {
		this.stLongDesc = stLongDesc;
	}
	
	@Column(name = "st_status", length = 1)
	public String getStStatus() {
		return this.stStatus;
	}
	
	public void setStStatus(String stStatus) {
		this.stStatus = stStatus;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSystemTypeByMFromSysId")
	public Set<IclubMessage> getIclubMessagesForMFromSysId() {
		return this.iclubMessagesForMFromSysId;
	}
	
	public void setIclubMessagesForMFromSysId(Set<IclubMessage> iclubMessagesForMFromSysId) {
		this.iclubMessagesForMFromSysId = iclubMessagesForMFromSysId;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubSystemTypeByMToSysId")
	public Set<IclubMessage> getIclubMessagesForMToSysId() {
		return this.iclubMessagesForMToSysId;
	}
	
	public void setIclubMessagesForMToSysId(Set<IclubMessage> iclubMessagesForMToSysId) {
		this.iclubMessagesForMToSysId = iclubMessagesForMToSysId;
	}
	
}