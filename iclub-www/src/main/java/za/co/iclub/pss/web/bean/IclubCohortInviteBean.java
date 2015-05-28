package za.co.iclub.pss.web.bean;

import java.util.Date;

public class IclubCohortInviteBean {
	
	private String ciId;
	private String iclubCohort;
	private String iclubPerson;
	private Long iclubNotificationType;
	private String ciInviteUri;
	private String ciInviteAcceptYn;
	private Date ciCrtdDt;
	
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
}
