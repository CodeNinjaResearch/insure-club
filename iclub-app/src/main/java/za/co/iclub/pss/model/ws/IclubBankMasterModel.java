package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubBankMasterModel")
public class IclubBankMasterModel {

	private Long bmId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String bmBankName;
	private Integer bmBankCode;
	private String bmBranchName;
	private Integer bmBranchCode;
	private String bmBranchAddress;
	private Double bmBranchLat;
	private Double bmBranchLong;
	private Integer bmBranchZip;
	private Date bmCrtdDt;

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

	public Double getBmBranchLat() {
		return bmBranchLat;
	}

	public void setBmBranchLat(Double bmBranchLat) {
		this.bmBranchLat = bmBranchLat;
	}

	public Double getBmBranchLong() {
		return bmBranchLong;
	}

	public void setBmBranchLong(Double bmBranchLong) {
		this.bmBranchLong = bmBranchLong;
	}

	public Integer getBmBranchZip() {
		return bmBranchZip;
	}

	public void setBmBranchZip(Integer bmBranchZip) {
		this.bmBranchZip = bmBranchZip;
	}

	public Date getBmCrtdDt() {
		return bmCrtdDt;
	}

	public void setBmCrtdDt(Date bmCrtdDt) {
		this.bmCrtdDt = bmCrtdDt;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
