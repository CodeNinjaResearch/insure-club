package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubVehicleModel")
public class IclubVehicleModel {

	private String VId;
	private Long iclubPurposeType;
	private String iclubSecurityMaster;
	private Long iclubAccessTypeByVOnAccessTypeId;
	private Long iclubAccessTypeByVDdAccessTypeId;
	private String iclubSecurityDevice;
	private Long iclubVehicleMaster;
	private String iclubPerson;
	private String iclubDriver;
	private Long VOdometer;
	private String VOnArea;
	private Long VOnLat;
	private Long VOnLong;
	private String VDdArea;
	private Long VDdLat;
	private Long VDdLong;
	private Integer VYear;
	private Long VInsuredValue;
	private Long VConcessPrct;
	private String VConcessReason;
	private String VImmYn;
	private String VGearLockYn;
	private String VOwner;
	private Integer VNoclaimYrs;
	private String VVin;
	private String VEngineNr;
	private String VRegNum;
	private Timestamp VCrtdDt;

	public String getVId() {
		return VId;
	}

	public void setVId(String vId) {
		VId = vId;
	}

	public Long getIclubPurposeType() {
		return iclubPurposeType;
	}

	public void setIclubPurposeType(Long iclubPurposeType) {
		this.iclubPurposeType = iclubPurposeType;
	}

	public String getIclubSecurityMaster() {
		return iclubSecurityMaster;
	}

	public void setIclubSecurityMaster(String iclubSecurityMaster) {
		this.iclubSecurityMaster = iclubSecurityMaster;
	}

	public Long getIclubAccessTypeByVOnAccessTypeId() {
		return iclubAccessTypeByVOnAccessTypeId;
	}

	public void setIclubAccessTypeByVOnAccessTypeId(Long iclubAccessTypeByVOnAccessTypeId) {
		this.iclubAccessTypeByVOnAccessTypeId = iclubAccessTypeByVOnAccessTypeId;
	}

	public Long getIclubAccessTypeByVDdAccessTypeId() {
		return iclubAccessTypeByVDdAccessTypeId;
	}

	public void setIclubAccessTypeByVDdAccessTypeId(Long iclubAccessTypeByVDdAccessTypeId) {
		this.iclubAccessTypeByVDdAccessTypeId = iclubAccessTypeByVDdAccessTypeId;
	}

	public String getIclubSecurityDevice() {
		return iclubSecurityDevice;
	}

	public void setIclubSecurityDevice(String iclubSecurityDevice) {
		this.iclubSecurityDevice = iclubSecurityDevice;
	}

	public Long getIclubVehicleMaster() {
		return iclubVehicleMaster;
	}

	public void setIclubVehicleMaster(Long iclubVehicleMaster) {
		this.iclubVehicleMaster = iclubVehicleMaster;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getIclubDriver() {
		return iclubDriver;
	}

	public void setIclubDriver(String iclubDriver) {
		this.iclubDriver = iclubDriver;
	}

	public Long getVOdometer() {
		return VOdometer;
	}

	public void setVOdometer(Long vOdometer) {
		VOdometer = vOdometer;
	}

	public String getVOnArea() {
		return VOnArea;
	}

	public void setVOnArea(String vOnArea) {
		VOnArea = vOnArea;
	}

	public Long getVOnLat() {
		return VOnLat;
	}

	public void setVOnLat(Long vOnLat) {
		VOnLat = vOnLat;
	}

	public Long getVOnLong() {
		return VOnLong;
	}

	public void setVOnLong(Long vOnLong) {
		VOnLong = vOnLong;
	}

	public String getVDdArea() {
		return VDdArea;
	}

	public void setVDdArea(String vDdArea) {
		VDdArea = vDdArea;
	}

	public Long getVDdLat() {
		return VDdLat;
	}

	public void setVDdLat(Long vDdLat) {
		VDdLat = vDdLat;
	}

	public Long getVDdLong() {
		return VDdLong;
	}

	public void setVDdLong(Long vDdLong) {
		VDdLong = vDdLong;
	}

	public Integer getVYear() {
		return VYear;
	}

	public void setVYear(Integer vYear) {
		VYear = vYear;
	}

	public Long getVInsuredValue() {
		return VInsuredValue;
	}

	public void setVInsuredValue(Long vInsuredValue) {
		VInsuredValue = vInsuredValue;
	}

	public Long getVConcessPrct() {
		return VConcessPrct;
	}

	public void setVConcessPrct(Long vConcessPrct) {
		VConcessPrct = vConcessPrct;
	}

	public String getVConcessReason() {
		return VConcessReason;
	}

	public void setVConcessReason(String vConcessReason) {
		VConcessReason = vConcessReason;
	}

	public String getVImmYn() {
		return VImmYn;
	}

	public void setVImmYn(String vImmYn) {
		VImmYn = vImmYn;
	}

	public String getVGearLockYn() {
		return VGearLockYn;
	}

	public void setVGearLockYn(String vGearLockYn) {
		VGearLockYn = vGearLockYn;
	}

	public String getVOwner() {
		return VOwner;
	}

	public void setVOwner(String vOwner) {
		VOwner = vOwner;
	}

	public Integer getVNoclaimYrs() {
		return VNoclaimYrs;
	}

	public void setVNoclaimYrs(Integer vNoclaimYrs) {
		VNoclaimYrs = vNoclaimYrs;
	}

	public String getVVin() {
		return VVin;
	}

	public void setVVin(String vVin) {
		VVin = vVin;
	}

	public String getVEngineNr() {
		return VEngineNr;
	}

	public void setVEngineNr(String vEngineNr) {
		VEngineNr = vEngineNr;
	}

	public String getVRegNum() {
		return VRegNum;
	}

	public void setVRegNum(String vRegNum) {
		VRegNum = vRegNum;
	}

	public Timestamp getVCrtdDt() {
		return VCrtdDt;
	}

	public void setVCrtdDt(Timestamp vCrtdDt) {
		VCrtdDt = vCrtdDt;
	}

}
