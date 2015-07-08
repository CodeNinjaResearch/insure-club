package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubLoginBean")
public class IclubLoginModel {
	
	private String LId;
	private Long iclubSecurityQuestion;
	private String sqLongDesc;
	private String iclubPersonAByLCrtdBy;
	private String PAFNameAndLName;
	private String iclubPersonBByLPersonId;
	private String PBFNameAndLName;
	private Long iclubRoleType;
	private String rtLongDesc;
	private String LName;
	private String LPasswd;
	private Date LLastDate;
	private String LSecAns;
	private Date LCrtdDt;
	private String LProviderCd;
	private String LProviderId;
	
	public String getLId() {
		return LId;
	}
	
	public void setLId(String lId) {
		LId = lId;
	}
	
	public Long getIclubSecurityQuestion() {
		return iclubSecurityQuestion;
	}
	
	public void setIclubSecurityQuestion(Long iclubSecurityQuestion) {
		this.iclubSecurityQuestion = iclubSecurityQuestion;
	}
	
	public Long getIclubRoleType() {
		return iclubRoleType;
	}
	
	public void setIclubRoleType(Long iclubRoleType) {
		this.iclubRoleType = iclubRoleType;
	}
	
	public String getLName() {
		return LName;
	}
	
	public void setLName(String lName) {
		LName = lName;
	}
	
	public String getLPasswd() {
		return LPasswd;
	}
	
	public void setLPasswd(String lPasswd) {
		LPasswd = lPasswd;
	}
	
	public Date getLLastDate() {
		return LLastDate;
	}
	
	public void setLLastDate(Date lLastDate) {
		LLastDate = lLastDate;
	}
	
	public String getLSecAns() {
		return LSecAns;
	}
	
	public void setLSecAns(String lSecAns) {
		LSecAns = lSecAns;
	}
	
	public Date getLCrtdDt() {
		return LCrtdDt;
	}
	
	public void setLCrtdDt(Date lCrtdDt) {
		LCrtdDt = lCrtdDt;
	}
	
	public String getSqLongDesc() {
		return sqLongDesc;
	}
	
	public void setSqLongDesc(String sqLongDesc) {
		this.sqLongDesc = sqLongDesc;
	}
	
	public String getIclubPersonAByLCrtdBy() {
		return iclubPersonAByLCrtdBy;
	}
	
	public void setIclubPersonAByLCrtdBy(String iclubPersonAByLCrtdBy) {
		this.iclubPersonAByLCrtdBy = iclubPersonAByLCrtdBy;
	}
	
	public String getPAFNameAndLName() {
		return PAFNameAndLName;
	}
	
	public void setPAFNameAndLName(String pAFNameAndLName) {
		PAFNameAndLName = pAFNameAndLName;
	}
	
	public String getIclubPersonBByLPersonId() {
		return iclubPersonBByLPersonId;
	}
	
	public void setIclubPersonBByLPersonId(String iclubPersonBByLPersonId) {
		this.iclubPersonBByLPersonId = iclubPersonBByLPersonId;
	}
	
	public String getPBFNameAndLName() {
		return PBFNameAndLName;
	}
	
	public void setPBFNameAndLName(String pBFNameAndLName) {
		PBFNameAndLName = pBFNameAndLName;
	}
	
	public String getRtLongDesc() {
		return rtLongDesc;
	}
	
	public void setRtLongDesc(String rtLongDesc) {
		this.rtLongDesc = rtLongDesc;
	}
	
	public String getLProviderCd() {
		return LProviderCd;
	}
	
	public void setLProviderCd(String lProviderCd) {
		LProviderCd = lProviderCd;
	}
	
	public String getLProviderId() {
		return LProviderId;
	}
	
	public void setLProviderId(String lProviderId) {
		LProviderId = lProviderId;
	}
	
}
