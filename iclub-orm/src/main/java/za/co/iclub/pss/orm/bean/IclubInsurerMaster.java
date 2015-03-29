package za.co.iclub.pss.orm.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * IclubInsurerMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_insurer_master", uniqueConstraints = @UniqueConstraint(columnNames = "im_name"))
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_insurer_master where aim_crtd_by=:id", name = "getInsurerMasterByUser", resultClass = IclubInsurerMaster.class) })
public class IclubInsurerMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4137448027884551871L;
	private Long imId;
	private IclubPerson iclubPerson;
	private String imName;
	private String imTradeName;
	private String imLocation;
	private Double imLat;
	private Double imLong;
	private String imRegNum;
	private Timestamp imCrtdDt;
	private Set<IclubQuote> iclubQuotes = new HashSet<IclubQuote>(0);

	// Constructors

	/** default constructor */
	public IclubInsurerMaster() {
	}

	/** minimal constructor */
	public IclubInsurerMaster(Long imId) {
		this.imId = imId;
	}

	/** full constructor */
	public IclubInsurerMaster(Long imId, IclubPerson iclubPerson, String imName, String imTradeName, String imLocation, Double imLat, Double imLong, String imRegNum, Timestamp imCrtdDt, Set<IclubQuote> iclubQuotes) {
		this.imId = imId;
		this.iclubPerson = iclubPerson;
		this.imName = imName;
		this.imTradeName = imTradeName;
		this.imLocation = imLocation;
		this.imLat = imLat;
		this.imLong = imLong;
		this.imRegNum = imRegNum;
		this.imCrtdDt = imCrtdDt;
		this.iclubQuotes = iclubQuotes;
	}

	// Property accessors
	@Id
	@Column(name = "im_id", unique = true, nullable = false)
	public Long getImId() {
		return this.imId;
	}

	public void setImId(Long imId) {
		this.imId = imId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "im_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "im_name", unique = true, length = 250)
	public String getImName() {
		return this.imName;
	}

	public void setImName(String imName) {
		this.imName = imName;
	}

	@Column(name = "im_trade_name", length = 999)
	public String getImTradeName() {
		return this.imTradeName;
	}

	public void setImTradeName(String imTradeName) {
		this.imTradeName = imTradeName;
	}

	@Column(name = "im_location", length = 999)
	public String getImLocation() {
		return this.imLocation;
	}

	public void setImLocation(String imLocation) {
		this.imLocation = imLocation;
	}

	@Column(name = "im_lat", precision = 10, scale = 7)
	public Double getImLat() {
		return this.imLat;
	}

	public void setImLat(Double imLat) {
		this.imLat = imLat;
	}

	@Column(name = "im_long", precision = 10, scale = 7)
	public Double getImLong() {
		return this.imLong;
	}

	public void setImLong(Double imLong) {
		this.imLong = imLong;
	}

	@Column(name = "im_reg_num", length = 25)
	public String getImRegNum() {
		return this.imRegNum;
	}

	public void setImRegNum(String imRegNum) {
		this.imRegNum = imRegNum;
	}

	@Column(name = "im_crtd_dt", length = 19)
	public Timestamp getImCrtdDt() {
		return this.imCrtdDt;
	}

	public void setImCrtdDt(Timestamp imCrtdDt) {
		this.imCrtdDt = imCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubInsurerMaster")
	public Set<IclubQuote> getIclubQuotes() {
		return this.iclubQuotes;
	}

	public void setIclubQuotes(Set<IclubQuote> iclubQuotes) {
		this.iclubQuotes = iclubQuotes;
	}

}