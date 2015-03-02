package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubBankMasterModel")
public class IclubBankMasterModel {

	private Long bmId;
	private Long iclubPerson;
	private String bmBankName;
	private Integer bmBankCode;
	private String bmBranchName;
	private Integer bmBranchCode;
	private String bmBranchAddress;
	private Long bmBranchLat;
	private Long bmBranchLong;
	private Integer bmBranchZip;
	private Timestamp bmCrtdDt;
	private Long[] iclubAccounts;

	public Long getBmId() {
		return bmId;
	}

	public void setBmId(Long bmId) {
		this.bmId = bmId;
	}

	public Long getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(Long iclubPerson) {
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

	public Long[] getIclubAccounts() {
		return iclubAccounts;
	}

	public void setIclubAccounts(Long[] iclubAccounts) {
		this.iclubAccounts = iclubAccounts;
	}
}
