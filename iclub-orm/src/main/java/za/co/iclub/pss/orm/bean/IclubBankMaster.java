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

/**
 * IclubBankMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "iclub_bank_master")
@NamedNativeQueries({ @NamedNativeQuery(query = "select * from iclub_bank_master where bm_crtd_by=:id", name = "getBankMasterByUser", resultClass = IclubBankMaster.class) })
public class IclubBankMaster implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -767683235432582948L;
	private Long bmId;
	private IclubPerson iclubPerson;
	private String bmBankName;
	private Integer bmBankCode;
	private String bmBranchName;
	private Integer bmBranchCode;
	private String bmBranchAddress;
	private Double bmBranchLat;
	private Double bmBranchLong;
	private Integer bmBranchZip;
	private Timestamp bmCrtdDt;
	private Set<IclubAccount> iclubAccounts = new HashSet<IclubAccount>(0);

	// Constructors

	/** default constructor */
	public IclubBankMaster() {
	}

	/** minimal constructor */
	public IclubBankMaster(Long bmId) {
		this.bmId = bmId;
	}

	/** full constructor */
	public IclubBankMaster(Long bmId, IclubPerson iclubPerson, String bmBankName, Integer bmBankCode, String bmBranchName, Integer bmBranchCode, String bmBranchAddress, Double bmBranchLat, Double bmBranchLong, Integer bmBranchZip, Timestamp bmCrtdDt, Set<IclubAccount> iclubAccounts) {
		this.bmId = bmId;
		this.iclubPerson = iclubPerson;
		this.bmBankName = bmBankName;
		this.bmBankCode = bmBankCode;
		this.bmBranchName = bmBranchName;
		this.bmBranchCode = bmBranchCode;
		this.bmBranchAddress = bmBranchAddress;
		this.bmBranchLat = bmBranchLat;
		this.bmBranchLong = bmBranchLong;
		this.bmBranchZip = bmBranchZip;
		this.bmCrtdDt = bmCrtdDt;
		this.iclubAccounts = iclubAccounts;
	}

	// Property accessors
	@Id
	@Column(name = "bm_id", unique = true, nullable = false)
	public Long getBmId() {
		return this.bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bm_crtd_by")
	public IclubPerson getIclubPerson() {
		return this.iclubPerson;
	}

	public void setIclubPerson(IclubPerson iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	@Column(name = "bm_bank_name", length = 999)
	public String getBmBankName() {
		return this.bmBankName;
	}

	public void setBmBankName(String bmBankName) {
		this.bmBankName = bmBankName;
	}

	@Column(name = "bm_bank_code")
	public Integer getBmBankCode() {
		return this.bmBankCode;
	}

	public void setBmBankCode(Integer bmBankCode) {
		this.bmBankCode = bmBankCode;
	}

	@Column(name = "bm_branch_name", length = 999)
	public String getBmBranchName() {
		return this.bmBranchName;
	}

	public void setBmBranchName(String bmBranchName) {
		this.bmBranchName = bmBranchName;
	}

	@Column(name = "bm_branch_code")
	public Integer getBmBranchCode() {
		return this.bmBranchCode;
	}

	public void setBmBranchCode(Integer bmBranchCode) {
		this.bmBranchCode = bmBranchCode;
	}

	@Column(name = "bm_branch_address", length = 399)
	public String getBmBranchAddress() {
		return this.bmBranchAddress;
	}

	public void setBmBranchAddress(String bmBranchAddress) {
		this.bmBranchAddress = bmBranchAddress;
	}

	@Column(name = "bm_branch_lat", precision = 10, scale = 7)
	public Double getBmBranchLat() {
		return this.bmBranchLat;
	}

	public void setBmBranchLat(Double bmBranchLat) {
		this.bmBranchLat = bmBranchLat;
	}

	@Column(name = "bm_branch_long", precision = 10, scale = 7)
	public Double getBmBranchLong() {
		return this.bmBranchLong;
	}

	public void setBmBranchLong(Double bmBranchLong) {
		this.bmBranchLong = bmBranchLong;
	}

	@Column(name = "bm_branch_zip")
	public Integer getBmBranchZip() {
		return this.bmBranchZip;
	}

	public void setBmBranchZip(Integer bmBranchZip) {
		this.bmBranchZip = bmBranchZip;
	}

	@Column(name = "bm_crtd_dt", length = 19)
	public Timestamp getBmCrtdDt() {
		return this.bmCrtdDt;
	}

	public void setBmCrtdDt(Timestamp bmCrtdDt) {
		this.bmCrtdDt = bmCrtdDt;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "iclubBankMaster")
	public Set<IclubAccount> getIclubAccounts() {
		return this.iclubAccounts;
	}

	public void setIclubAccounts(Set<IclubAccount> iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}

}