package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubLoginBean")
public class IclubLoginModel {

	private String LId;
	private Long iclubSecurityQuestion;
	private String iclubPersonByLCrtdBy;
	private String iclubPersonByLPersonId;
	private Long iclubRoleType;
	private String LName;
	private String LPasswd;
	private Date LLastDate;
	private String LSecAns;
	private Date LCrtdDt;

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

	public String getIclubPersonByLCrtdBy() {
		return iclubPersonByLCrtdBy;
	}

	public void setIclubPersonByLCrtdBy(String iclubPersonByLCrtdBy) {
		this.iclubPersonByLCrtdBy = iclubPersonByLCrtdBy;
	}

	public String getIclubPersonByLPersonId() {
		return iclubPersonByLPersonId;
	}

	public void setIclubPersonByLPersonId(String iclubPersonByLPersonId) {
		this.iclubPersonByLPersonId = iclubPersonByLPersonId;
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

}
