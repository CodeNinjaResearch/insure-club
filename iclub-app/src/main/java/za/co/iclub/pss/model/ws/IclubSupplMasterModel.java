package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubSupplMasterModel")
public class IclubSupplMasterModel {
	
	private String smId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubSupplierType;
	private String stLongDesc;
	private String smName;
	private String smTradeName;
	private String smRegNum;
	private String smAddress;
	private Double smLat;
	private Double smLong;
	private Double smCrLimit;
	private Date srActionDt;
	private Integer smRating;
	private Date smCrtdDt;
	
	public String getSmId() {
		return smId;
	}
	
	public void setSmId(String smId) {
		this.smId = smId;
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
	
	public Long getIclubSupplierType() {
		return iclubSupplierType;
	}
	
	public void setIclubSupplierType(Long iclubSupplierType) {
		this.iclubSupplierType = iclubSupplierType;
	}
	
	public String getStLongDesc() {
		return stLongDesc;
	}
	
	public void setStLongDesc(String stLongDesc) {
		this.stLongDesc = stLongDesc;
	}
	
	public String getSmName() {
		return smName;
	}
	
	public void setSmName(String smName) {
		this.smName = smName;
	}
	
	public String getSmTradeName() {
		return smTradeName;
	}
	
	public void setSmTradeName(String smTradeName) {
		this.smTradeName = smTradeName;
	}
	
	public String getSmRegNum() {
		return smRegNum;
	}
	
	public void setSmRegNum(String smRegNum) {
		this.smRegNum = smRegNum;
	}
	
	public String getSmAddress() {
		return smAddress;
	}
	
	public void setSmAddress(String smAddress) {
		this.smAddress = smAddress;
	}
	
	public Double getSmLat() {
		return smLat;
	}
	
	public void setSmLat(Double smLat) {
		this.smLat = smLat;
	}
	
	public Double getSmLong() {
		return smLong;
	}
	
	public void setSmLong(Double smLong) {
		this.smLong = smLong;
	}
	
	public Double getSmCrLimit() {
		return smCrLimit;
	}
	
	public void setSmCrLimit(Double smCrLimit) {
		this.smCrLimit = smCrLimit;
	}
	
	public Date getSrActionDt() {
		return srActionDt;
	}
	
	public void setSrActionDt(Date srActionDt) {
		this.srActionDt = srActionDt;
	}
	
	public Integer getSmRating() {
		return smRating;
	}
	
	public void setSmRating(Integer smRating) {
		this.smRating = smRating;
	}
	
	public Date getSmCrtdDt() {
		return smCrtdDt;
	}
	
	public void setSmCrtdDt(Date smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}
	
}
