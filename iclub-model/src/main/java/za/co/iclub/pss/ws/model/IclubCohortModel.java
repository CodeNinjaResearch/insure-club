package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortModel")
public class IclubCohortModel {

	private String CId;
	private String iclubPersonByCPrimaryUserId;
	private String iclubPersonByCCrtdBy;
	private Long iclubCohortType;
	private String CName;
	private String CEmail;
	private Date CInitDt;
	private Date CFinalizeDt;
	private Double CTotalContrib;
	private Double CCollectedContrib;
	private Integer CCurMemberCnt;
	private Date CCrtdDt;
	private String[] iclubCohortClaims;
	private String[] iclubCohortPersons;
	private String[] iclubPersons;
	private String[] iclubCohortInvites;

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

	public String getIclubPersonByCCrtdBy() {
		return iclubPersonByCCrtdBy;
	}

	public void setIclubPersonByCCrtdBy(String iclubPersonByCCrtdBy) {
		this.iclubPersonByCCrtdBy = iclubPersonByCCrtdBy;
	}

	public Long getIclubCohortType() {
		return iclubCohortType;
	}

	public void setIclubCohortType(Long iclubCohortType) {
		this.iclubCohortType = iclubCohortType;
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

	public String[] getIclubCohortClaims() {
		return iclubCohortClaims;
	}

	public void setIclubCohortClaims(String[] iclubCohortClaims) {
		this.iclubCohortClaims = iclubCohortClaims;
	}

	public String[] getIclubCohortPersons() {
		return iclubCohortPersons;
	}

	public void setIclubCohortPersons(String[] iclubCohortPersons) {
		this.iclubCohortPersons = iclubCohortPersons;
	}

	public String[] getIclubPersons() {
		return iclubPersons;
	}

	public void setIclubPersons(String[] iclubPersons) {
		this.iclubPersons = iclubPersons;
	}

	public String[] getIclubCohortInvites() {
		return iclubCohortInvites;
	}

	public void setIclubCohortInvites(String[] iclubCohortInvites) {
		this.iclubCohortInvites = iclubCohortInvites;
	}
}
