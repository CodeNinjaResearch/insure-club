package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortInviteModel")
public class IclubCohortInviteModel {
	
	private String ciId;
	private String iclubCohort;
	private String iclubPerson;
	private Long iclubNotificationType;
	private String ciInviteUri;
	private String ciInviteAcceptYn;
	private Date ciCrtdDt;
	private String ciInviteFName;
	private String ciInviteLName;
	
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
}
