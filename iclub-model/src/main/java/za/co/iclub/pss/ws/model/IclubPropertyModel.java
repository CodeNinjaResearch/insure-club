package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropertyModel")
public class IclubPropertyModel {

	private String PId;
	private Long iclubCoverType;
	private Long iclubPropUsageType;
	private Long iclubOccupiedStatus;
	private Long iclubPropertyType;
	private Long iclubWallType;
	private Long iclubAccessType;
	private String iclubPerson;
	private Long iclubBarType;
	private String PThatchType;
	private Long iclubRoofType;
	private String PRegNum;
	private String PAddress;
	private Double PLat;
	private Double PLong;
	private Integer PPostalCd;
	private Integer PNoclaimYrs;
	private String PRentFurYn;
	private String PCompYn;
	private String PNorobberyYn;
	private String PSecGatesYn;
	private Double PEstValue;
	private Date PCrtdDt;
	private Long iclubPropSecType;
	private String[] iclubPropertyItems;

	public String getPId() {
		return PId;
	}

	public void setPId(String pId) {
		PId = pId;
	}

	public Long getIclubCoverType() {
		return iclubCoverType;
	}

	public void setIclubCoverType(Long iclubCoverType) {
		this.iclubCoverType = iclubCoverType;
	}

	public Long getIclubPropUsageType() {
		return iclubPropUsageType;
	}

	public void setIclubPropUsageType(Long iclubPropUsageType) {
		this.iclubPropUsageType = iclubPropUsageType;
	}

	public Long getIclubOccupiedStatus() {
		return iclubOccupiedStatus;
	}

	public void setIclubOccupiedStatus(Long iclubOccupiedStatus) {
		this.iclubOccupiedStatus = iclubOccupiedStatus;
	}

	public Long getIclubPropertyType() {
		return iclubPropertyType;
	}

	public void setIclubPropertyType(Long iclubPropertyType) {
		this.iclubPropertyType = iclubPropertyType;
	}

	public Long getIclubWallType() {
		return iclubWallType;
	}

	public void setIclubWallType(Long iclubWallType) {
		this.iclubWallType = iclubWallType;
	}

	public Long getIclubAccessType() {
		return iclubAccessType;
	}

	public void setIclubAccessType(Long iclubAccessType) {
		this.iclubAccessType = iclubAccessType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubBarType() {
		return iclubBarType;
	}

	public void setIclubBarType(Long iclubBarType) {
		this.iclubBarType = iclubBarType;
	}

	public String getPThatchType() {
		return PThatchType;
	}

	public void setPThatchType(String pThatchType) {
		PThatchType = pThatchType;
	}

	public Long getIclubRoofType() {
		return iclubRoofType;
	}

	public void setIclubRoofType(Long iclubRoofType) {
		this.iclubRoofType = iclubRoofType;
	}

	public String getPRegNum() {
		return PRegNum;
	}

	public void setPRegNum(String pRegNum) {
		PRegNum = pRegNum;
	}

	public String getPAddress() {
		return PAddress;
	}

	public void setPAddress(String pAddress) {
		PAddress = pAddress;
	}

	public Double getPLat() {
		return PLat;
	}

	public void setPLat(Double pLat) {
		PLat = pLat;
	}

	public Double getPLong() {
		return PLong;
	}

	public void setPLong(Double pLong) {
		PLong = pLong;
	}

	public Integer getPPostalCd() {
		return PPostalCd;
	}

	public void setPPostalCd(Integer pPostalCd) {
		PPostalCd = pPostalCd;
	}

	public Integer getPNoclaimYrs() {
		return PNoclaimYrs;
	}

	public void setPNoclaimYrs(Integer pNoclaimYrs) {
		PNoclaimYrs = pNoclaimYrs;
	}

	public String getPRentFurYn() {
		return PRentFurYn;
	}

	public void setPRentFurYn(String pRentFurYn) {
		PRentFurYn = pRentFurYn;
	}

	public String getPCompYn() {
		return PCompYn;
	}

	public void setPCompYn(String pCompYn) {
		PCompYn = pCompYn;
	}

	public String getPNorobberyYn() {
		return PNorobberyYn;
	}

	public void setPNorobberyYn(String pNorobberyYn) {
		PNorobberyYn = pNorobberyYn;
	}

	public String getPSecGatesYn() {
		return PSecGatesYn;
	}

	public void setPSecGatesYn(String pSecGatesYn) {
		PSecGatesYn = pSecGatesYn;
	}

	public Double getPEstValue() {
		return PEstValue;
	}

	public void setPEstValue(Double pEstValue) {
		PEstValue = pEstValue;
	}

	public Date getPCrtdDt() {
		return PCrtdDt;
	}

	public void setPCrtdDt(Date pCrtdDt) {
		PCrtdDt = pCrtdDt;
	}

	public Long getIclubPropSecType() {
		return iclubPropSecType;
	}

	public void setIclubPropSecType(Long iclubPropSecType) {
		this.iclubPropSecType = iclubPropSecType;
	}

	public String[] getIclubPropertyItems() {
		return iclubPropertyItems;
	}

	public void setIclubPropertyItems(String[] iclubPropertyItems) {
		this.iclubPropertyItems = iclubPropertyItems;
	}

}
