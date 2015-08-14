package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortModel")
public class IclubCohortModel {
	
	private String CId;
	private String iclubPersonByCPrimaryUserId;
	private String PAFNameAndLName;
	private String iclubPersonByCCrtdBy;
	private String PBFNameAndLName;
	private Long iclubCohortType;
	private String ctLongDesc;
	private String CName;
	private String CEmail;
	private Date CInitDt;
	private Date CFinalizeDt;
	private Double CTotalContrib;
	private Double CCollectedContrib;
	private Integer CCurMemberCnt;
	private Date CCrtdDt;
	private Long iclubInsuranceItemType;
	private String iitLongDesc;
	
	public String getCId() {
		return CId;
	}
	
	public void setCId(String cId) {
		CId = cId;
	}
	
	public String getIclubPersonByCPrimaryUserId() {
		return iclubPersonByCPrimaryUserId;
	}
	
	public void setIclubPersonByCPrimaryUserId(String iclubPersonByCPrimaryUserId) {
		this.iclubPersonByCPrimaryUserId = iclubPersonByCPrimaryUserId;
	}
	
	public String getPAFNameAndLName() {
		return PAFNameAndLName;
	}
	
	public void setPAFNameAndLName(String pAFNameAndLName) {
		PAFNameAndLName = pAFNameAndLName;
	}
	
	public String getIclubPersonByCCrtdBy() {
		return iclubPersonByCCrtdBy;
	}
	
	public void setIclubPersonByCCrtdBy(String iclubPersonByCCrtdBy) {
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
	}
	
	public String getPBFNameAndLName() {
		return PBFNameAndLName;
	}
	
	public void setPBFNameAndLName(String pBFNameAndLName) {
		PBFNameAndLName = pBFNameAndLName;
	}
	
	public Long getIclubCohortType() {
		return iclubCohortType;
	}
	
	public void setIclubCohortType(Long iclubCohortType) {
		this.iclubCohortType = iclubCohortType;
	}
	
	public String getCtLongDesc() {
		return ctLongDesc;
	}
	
	public void setCtLongDesc(String ctLongDesc) {
		this.ctLongDesc = ctLongDesc;
	}
	
	public String getCName() {
		return CName;
	}
	
	public void setCName(String cName) {
		CName = cName;
	}
	
	public String getCEmail() {
		return CEmail;
	}
	
	public void setCEmail(String cEmail) {
		CEmail = cEmail;
	}
	
	public Date getCInitDt() {
		return CInitDt;
	}
	
	public void setCInitDt(Date cInitDt) {
		CInitDt = cInitDt;
	}
	
	public Date getCFinalizeDt() {
		return CFinalizeDt;
	}
	
	public void setCFinalizeDt(Date cFinalizeDt) {
		CFinalizeDt = cFinalizeDt;
	}
	
	public Double getCTotalContrib() {
		return CTotalContrib;
	}
	
	public void setCTotalContrib(Double cTotalContrib) {
		CTotalContrib = cTotalContrib;
	}
	
	public Double getCCollectedContrib() {
		return CCollectedContrib;
	}
	
	public void setCCollectedContrib(Double cCollectedContrib) {
		CCollectedContrib = cCollectedContrib;
	}
	
	public Integer getCCurMemberCnt() {
		return CCurMemberCnt;
	}
	
	public void setCCurMemberCnt(Integer cCurMemberCnt) {
		CCurMemberCnt = cCurMemberCnt;
	}
	
	public Date getCCrtdDt() {
		return CCrtdDt;
	}
	
	public void setCCrtdDt(Date cCrtdDt) {
		CCrtdDt = cCrtdDt;
	}
	
	public Long getIclubInsuranceItemType() {
		return iclubInsuranceItemType;
	}
	
	public void setIclubInsuranceItemType(Long iclubInsuranceItemType) {
		this.iclubInsuranceItemType = iclubInsuranceItemType;
	}
	
	public String getIitLongDesc() {
		return iitLongDesc;
	}
	
	public void setIitLongDesc(String iitLongDesc) {
		this.iitLongDesc = iitLongDesc;
	}
	
}
