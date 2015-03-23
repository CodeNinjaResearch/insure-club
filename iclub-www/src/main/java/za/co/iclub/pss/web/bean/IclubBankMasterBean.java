package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubBankMasterBean {

	private Long bmId;
	private String iclubPerson;
	private String bmBankName;
	private Integer bmBankCode;
	private String bmBranchName;
	private Integer bmBranchCode;
	private String bmBranchAddress;
	private Long bmBranchLat;
	private Long bmBranchLong;
	private Integer bmBranchZip;
	private Timestamp bmCrtdDt;
	private String[] iclubAccounts;

	public Long getBmId() {
		return bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getBmBankName() {
		return bmBankName;
	}

	public void setBmBankName(String bmBankName) {
		this.bmBankName = bmBankName;
	}

	public Integer getBmBankCode() {
		return bmBankCode;
	}

	public void setBmBankCode(Integer bmBankCode) {
		this.bmBankCode = bmBankCode;
	}

	public String getBmBranchName() {
		return bmBranchName;
	}

	public void setBmBranchName(String bmBranchName) {
		this.bmBranchName = bmBranchName;
	}

	public Integer getBmBranchCode() {
		return bmBranchCode;
	}

	public void setBmBranchCode(Integer bmBranchCode) {
		this.bmBranchCode = bmBranchCode;
	}

	public String getBmBranchAddress() {
		return bmBranchAddress;
	}

	public void setBmBranchAddress(String bmBranchAddress) {
		this.bmBranchAddress = bmBranchAddress;
	}

	public Long getBmBranchLat() {
		return bmBranchLat;
	}

	public void setBmBranchLat(Long bmBranchLat) {
		this.bmBranchLat = bmBranchLat;
	}

	public Long getBmBranchLong() {
		return bmBranchLong;
	}

	public void setBmBranchLong(Long bmBranchLong) {
		this.bmBranchLong = bmBranchLong;
	}

	public Integer getBmBranchZip() {
		return bmBranchZip;
	}

	public void setBmBranchZip(Integer bmBranchZip) {
		this.bmBranchZip = bmBranchZip;
	}

	public Timestamp getBmCrtdDt() {
		return bmCrtdDt;
	}

	public void setBmCrtdDt(Timestamp bmCrtdDt) {
		this.bmCrtdDt = bmCrtdDt;
	}

	public String[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(String[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}
}
