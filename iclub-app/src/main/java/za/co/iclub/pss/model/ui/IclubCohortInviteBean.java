package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubCohortInviteBean {

	private String ciId;
	private String iclubCohort;
	private String CName;
	private String CEmail;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubNotificationType;
	private String ntLongDesc;
	private String ciInviteUri;
	private String ciInviteAcceptYn;
	private Date ciCrtdDt;
	private String ciInviteFName;
	private String ciInviteLName;
	private String ciInviteSentStatus;
	private Long iclubInviteStatus;
	private String isLongDesc;
	private boolean criteria;
	private String cohortCriteriaId;

	public String getCiId() {
		return ciId;
	}

	public void setCiId(String ciId) {
		this.ciId = ciId;
	}

	public String getIclubCohort() {
		return iclubCohort;
	}

	public void setIclubCohort(String iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubNotificationType() {
		return iclubNotificationType;
	}

	public void setIclubNotificationType(Long iclubNotificationType) {
		this.iclubNotificationType = iclubNotificationType;
	}

	public String getCiInviteUri() {
		return ciInviteUri;
	}

	public void setCiInviteUri(String ciInviteUri) {
		this.ciInviteUri = ciInviteUri;
	}

	public String getCiInviteAcceptYn() {
		return ciInviteAcceptYn;
	}

	public void setCiInviteAcceptYn(String ciInviteAcceptYn) {
		this.ciInviteAcceptYn = ciInviteAcceptYn;
	}

	public Date getCiCrtdDt() {
		return ciCrtdDt;
	}

	public void setCiCrtdDt(Date ciCrtdDt) {
		this.ciCrtdDt = ciCrtdDt;
	}

	public String getCiInviteFName() {
		return ciInviteFName;
	}

	public void setCiInviteFName(String ciInviteFName) {
		this.ciInviteFName = ciInviteFName;
	}

	public String getCiInviteLName() {
		return ciInviteLName;
	}

	public void setCiInviteLName(String ciInviteLName) {
		this.ciInviteLName = ciInviteLName;
	}

	public String getCEmail() {
		return CEmail;
	}

	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

	public String getNtLongDesc() {
		return ntLongDesc;
	}

	public void setNtLongDesc(String ntLongDesc) {
		this.ntLongDesc = ntLongDesc;
	}

	public String getCiInviteSentStatus() {
		return ciInviteSentStatus;
	}

	public void setCiInviteSentStatus(String ciInviteSentStatus) {
		this.ciInviteSentStatus = ciInviteSentStatus;
	}

	public Long getIclubInviteStatus() {
		return iclubInviteStatus;
	}

	public void setIclubInviteStatus(Long iclubInviteStatus) {
		this.iclubInviteStatus = iclubInviteStatus;
	}

	public String getIsLongDesc() {
		return isLongDesc;
	}

	public void setIsLongDesc(String isLongDesc) {
		this.isLongDesc = isLongDesc;
	}

	public String getCName() {
		return CName;
	}

	public void setCName(String cName) {
		CName = cName;
	}

	public boolean isCriteria() {
		return criteria;
	}

	public void setCriteria(boolean criteria) {
		this.criteria = criteria;
	}

	public String getCohortCriteriaId() {
		return cohortCriteriaId;
	}

	public void setCohortCriteriaId(String cohortCriteriaId) {
		this.cohortCriteriaId = cohortCriteriaId;
	}
}
