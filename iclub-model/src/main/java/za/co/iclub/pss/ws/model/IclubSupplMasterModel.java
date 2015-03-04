package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubSupplMasterModel")
public class IclubSupplMasterModel {

	private String smId;
	private String iclubPerson;
	private Long iclubSupplierType;
	private String smName;
	private String smTradeName;
	private String smRegNum;
	private String smAddress;
	private Long smLat;
	private Long smLong;
	private Long smCrLimit;
	private Date srActionDt;
	private Integer smRating;
	private Timestamp smCrtdDt;

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

	public Long getIclubSupplierType() {
		return iclubSupplierType;
	}

	public void setIclubSupplierType(Long iclubSupplierType) {
		this.iclubSupplierType = iclubSupplierType;
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

	public Long getSmLat() {
		return smLat;
	}

	public void setSmLat(Long smLat) {
		this.smLat = smLat;
	}

	public Long getSmLong() {
		return smLong;
	}

	public void setSmLong(Long smLong) {
		this.smLong = smLong;
	}

	public Long getSmCrLimit() {
		return smCrLimit;
	}

	public void setSmCrLimit(Long smCrLimit) {
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

	public Timestamp getSmCrtdDt() {
		return smCrtdDt;
	}

	public void setSmCrtdDt(Timestamp smCrtdDt) {
		this.smCrtdDt = smCrtdDt;
	}

}
