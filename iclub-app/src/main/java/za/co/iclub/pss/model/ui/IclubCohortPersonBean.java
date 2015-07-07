package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubCohortPersonBean {
	
	private String cpId;
	private String iclubPersonAByCpPersonId;
	private String PAFNameAndLName;
	private String iclubCohort;
	private String CEmail;
	private String iclubPersonBByCpCrtdBy;
	private String PBFNameAndLName;
	private Double cpContrib;
	private Date cpCrtdDt;
	
	public String getCpId() {
		return cpId;
	}
	
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
	
	public String getIclubPersonAByCpPersonId() {
		return iclubPersonAByCpPersonId;
	}
	
	public void setIclubPersonAByCpPersonId(String iclubPersonAByCpPersonId) {
		this.iclubPersonAByCpPersonId = iclubPersonAByCpPersonId;
	}
	
	public String getPAFNameAndLName() {
		return PAFNameAndLName;
	}
	
	public void setPAFNameAndLName(String pAFNameAndLName) {
		PAFNameAndLName = pAFNameAndLName;
	}
	
	public String getIclubCohort() {
		return iclubCohort;
	}
	
	public void setIclubCohort(String iclubCohort) {
		this.iclubCohort = iclubCohort;
	}
	
	public String getCEmail() {
		return CEmail;
	}
	
	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}
	
	public String getIclubPersonBByCpCrtdBy() {
		return iclubPersonBByCpCrtdBy;
	}
	
	public void setIclubPersonBByCpCrtdBy(String iclubPersonBByCpCrtdBy) {
		this.iclubPersonBByCpCrtdBy = iclubPersonBByCpCrtdBy;
	}
	
	public String getPBFNameAndLName() {
		return PBFNameAndLName;
	}
	
	public void setPBFNameAndLName(String pBFNameAndLName) {
		PBFNameAndLName = pBFNameAndLName;
	}
	
	public Double getCpContrib() {
		return cpContrib;
	}
	
	public void setCpContrib(Double cpContrib) {
		this.cpContrib = cpContrib;
	}
	
	public Date getCpCrtdDt() {
		return cpCrtdDt;
	}
	
	public void setCpCrtdDt(Date cpCrtdDt) {
		this.cpCrtdDt = cpCrtdDt;
	}
	
}
