package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubAccountModel")
public class IclubAccountModel {

	private Integer AId;
	private Long iclubAccountType;
	private Long iclubBankMaster;
	private Long iclubOwnerType;
	private String iclubPerson;
	private String AAccNum;
	private String AOwnerId;
	private String AStatus;
	private Timestamp ACrtdDt;

	public Integer getAId() {
		return AId;
	}

	public void setAId(Integer aId) {
		AId = aId;
	}

	public Long getIclubAccountType() {
		return iclubAccountType;
	}

	public void setIclubAccountType(Long iclubAccountType) {
		this.iclubAccountType = iclubAccountType;
	}

	public Long getIclubBankMaster() {
		return iclubBankMaster;
	}

	public void setIclubBankMaster(Long iclubBankMaster) {
		this.iclubBankMaster = iclubBankMaster;
	}

	public Long getIclubOwnerType() {
		return iclubOwnerType;
	}

	public void setIclubOwnerType(Long iclubOwnerType) {
		this.iclubOwnerType = iclubOwnerType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
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

	public Timestamp getACrtdDt() {
		return ACrtdDt;
	}

	public void setACrtdDt(Timestamp aCrtdDt) {
		ACrtdDt = aCrtdDt;
	}

}
