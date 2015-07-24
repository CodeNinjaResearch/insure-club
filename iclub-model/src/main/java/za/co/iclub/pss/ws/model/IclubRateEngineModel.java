package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubRateEngineModel")
public class IclubRateEngineModel {

	private String reId;
	private Long iclubInsuranceItemType;
	private String iclubPerson;
	private Long iclubRateType;
	private String reFieldName;
	private String reBaseValue;
	private String reMaxValue;
	private Double reRate;
	private String reStatus;
	private Date reCrtdDt;

	public String getReId() {
		return reId;
	}

	public void setReId(String reId) {
		this.reId = reId;
	}

	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}

	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubRateType() {
		return iclubRateType;
	}

	public void setIclubRateType(Long iclubRateType) {
		this.iclubRateType = iclubRateType;
	}

	public String getReFieldName() {
		return reFieldName;
	}

	public void setReFieldName(String reFieldName) {
		this.reFieldName = reFieldName;
	}

	public String getReBaseValue() {
		return reBaseValue;
	}

	public void setReBaseValue(String reBaseValue) {
		this.reBaseValue = reBaseValue;
	}

	public String getReMaxValue() {
		return reMaxValue;
	}

	public void setReMaxValue(String reMaxValue) {
		this.reMaxValue = reMaxValue;
	}

	public Double getReRate() {
		return reRate;
	}

	public void setReRate(Double reRate) {
		this.reRate = reRate;
	}

	public String getReStatus() {
		return reStatus;
	}

	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}

	public Date getReCrtdDt() {
		return reCrtdDt;
	}

	public void setReCrtdDt(Date reCrtdDt) {
		this.reCrtdDt = reCrtdDt;
	}

}
