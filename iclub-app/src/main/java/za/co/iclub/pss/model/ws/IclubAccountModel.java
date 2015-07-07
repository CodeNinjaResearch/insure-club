package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubAccountModel")
public class IclubAccountModel {
	
	private String AId;
	private Long iclubAccountType;
	private String atLongDesc;
	private Long iclubBankMaster;
	private String bmBankName;
	private String bmCode;
	private Long iclubOwnerType;
	private String otLongDesc;
	private String iclubPerson;
	private String PFNameAndLName;
	private String AAccNum;
	private String AOwnerId;
	private String AStatus;
	private Date ACrtdDt;
	
	public String getAId() {
		return AId;
	}
	
	public void setAId(String aId) {
		AId = aId;
	}
	
	public Long getIclubAccountType() {
		return iclubAccountType;
	}
	
	public void setIclubAccountType(Long iclubAccountType) {
		this.iclubAccountType = iclubAccountType;
	}
	
	public String getAtLongDesc() {
		return atLongDesc;
	}
	
	public void setAtLongDesc(String atLongDesc) {
		this.atLongDesc = atLongDesc;
	}
	
	public Long getIclubBankMaster() {
		return iclubBankMaster;
	}
	
	public void setIclubBankMaster(Long iclubBankMaster) {
		this.iclubBankMaster = iclubBankMaster;
	}
	
	public String getBmBankName() {
		return bmBankName;
	}
	
	public void setBmBankName(String bmBankName) {
		this.bmBankName = bmBankName;
	}
	
	public String getBmCode() {
		return bmCode;
	}
	
	public void setBmCode(String bmCode) {
		this.bmCode = bmCode;
	}
	
	public Long getIclubOwnerType() {
		return iclubOwnerType;
	}
	
	public void setIclubOwnerType(Long iclubOwnerType) {
		this.iclubOwnerType = iclubOwnerType;
	}
	
	public String getOtLongDesc() {
		return otLongDesc;
	}
	
	public void setOtLongDesc(String otLongDesc) {
		this.otLongDesc = otLongDesc;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getAAccNum() {
		return AAccNum;
	}
	
	public void setAAccNum(String aAccNum) {
		AAccNum = aAccNum;
	}
	
	public String getAOwnerId() {
		return AOwnerId;
	}
	
	public void setAOwnerId(String aOwnerId) {
		AOwnerId = aOwnerId;
	}
	
	public String getAStatus() {
		return AStatus;
	}
	
	public void setAStatus(String aStatus) {
		AStatus = aStatus;
	}
	
	public Date getACrtdDt() {
		return ACrtdDt;
	}
	
	public void setACrtdDt(Date aCrtdDt) {
		ACrtdDt = aCrtdDt;
	}
	
}
