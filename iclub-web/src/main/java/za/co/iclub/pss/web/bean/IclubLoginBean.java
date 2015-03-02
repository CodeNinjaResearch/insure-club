package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubLoginBean {

	private String LId;
	private Long iclubSecurityQuestion;
	private Long iclubPersonByLCrtdBy;
	private Long iclubPersonByLPersonId;
	private Long iclubRoleType;
	private String LName;
	private String LPasswd;
	private Timestamp LLastDate;
	private String LSecAns;
	private Timestamp LCrtdDt;

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

	public Long getIclubPersonByLCrtdBy() {
		return iclubPersonByLCrtdBy;
	}

	public void setIclubPersonByLCrtdBy(Long iclubPersonByLCrtdBy) {
		this.iclubPersonByLCrtdBy = iclubPersonByLCrtdBy;
	}

	public Long getIclubPersonByLPersonId() {
		return iclubPersonByLPersonId;
	}

	public void setIclubPersonByLPersonId(Long iclubPersonByLPersonId) {
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

	public Timestamp getLLastDate() {
		return LLastDate;
	}

	public void setLLastDate(Timestamp lLastDate) {
		LLastDate = lLastDate;
	}

	public String getLSecAns() {
		return LSecAns;
	}

	public void setLSecAns(String lSecAns) {
		LSecAns = lSecAns;
	}

	public Timestamp getLCrtdDt() {
		return LCrtdDt;
	}

	public void setLCrtdDt(Timestamp lCrtdDt) {
		LCrtdDt = lCrtdDt;
	}

}
