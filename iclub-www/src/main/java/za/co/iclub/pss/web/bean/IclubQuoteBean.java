package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubQuoteBean {

	private String QId;
	private String iclubPersonByQCrtdBy;
	private String iclubPersonByQPersonId;
	private Long iclubProductType;
	private Long iclubInsurerMaster;
	private Long iclubCoverType;
	private Long iclubQuoteStatus;
	private Long QNumber;
	private Timestamp QGenDt;
	private Integer QNumItems;
	private Double QGenPremium;
	private String QEmail;
	private String QMobile;
	private Timestamp QValidUntil;
	private Double QPrevPremium;
	private String QIsMatched;
	private Timestamp QCrtdDt;
	private String[] iclubPolicies;

	public String getQId() {
		return QId;
	}

	public void setQId(String qId) {
		QId = qId;
	}

	public String getIclubPersonByQCrtdBy() {
		return iclubPersonByQCrtdBy;
	}

	public void setIclubPersonByQCrtdBy(String iclubPersonByQCrtdBy) {
		this.iclubPersonByQCrtdBy = iclubPersonByQCrtdBy;
	}

	public String getIclubPersonByQPersonId() {
		return iclubPersonByQPersonId;
	}

	public void setIclubPersonByQPersonId(String iclubPersonByQPersonId) {
		this.iclubPersonByQPersonId = iclubPersonByQPersonId;
	}

	public Long getIclubProductType() {
		return iclubProductType;
	}

	public void setIclubProductType(Long iclubProductType) {
		this.iclubProductType = iclubProductType;
	}

	public Long getIclubInsurerMaster() {
		return iclubInsurerMaster;
	}

	public void setIclubInsurerMaster(Long iclubInsurerMaster) {
		this.iclubInsurerMaster = iclubInsurerMaster;
	}

	public Long getIclubCoverType() {
		return iclubCoverType;
	}

	public void setIclubCoverType(Long iclubCoverType) {
		this.iclubCoverType = iclubCoverType;
	}

	public Long getIclubQuoteStatus() {
		return iclubQuoteStatus;
	}

	public void setIclubQuoteStatus(Long iclubQuoteStatus) {
		this.iclubQuoteStatus = iclubQuoteStatus;
	}

	public Long getQNumber() {
		return QNumber;
	}

	public void setQNumber(Long qNumber) {
		QNumber = qNumber;
	}

	public Timestamp getQGenDt() {
		return QGenDt;
	}

	public void setQGenDt(Timestamp qGenDt) {
		QGenDt = qGenDt;
	}

	public Integer getQNumItems() {
		return QNumItems;
	}

	public void setQNumItems(Integer qNumItems) {
		QNumItems = qNumItems;
	}

	public Double getQGenPremium() {
		return QGenPremium;
	}

	public void setQGenPremium(Double qGenPremium) {
		QGenPremium = qGenPremium;
	}

	public String getQEmail() {
		return QEmail;
	}

	public void setQEmail(String qEmail) {
		QEmail = qEmail;
	}

	public String getQMobile() {
		return QMobile;
	}

	public void setQMobile(String qMobile) {
		QMobile = qMobile;
	}

	public Timestamp getQValidUntil() {
		return QValidUntil;
	}

	public void setQValidUntil(Timestamp qValidUntil) {
		QValidUntil = qValidUntil;
	}

	public Double getQPrevPremium() {
		return QPrevPremium;
	}

	public void setQPrevPremium(Double qPrevPremium) {
		QPrevPremium = qPrevPremium;
	}

	public String getQIsMatched() {
		return QIsMatched;
	}

	public void setQIsMatched(String qIsMatched) {
		QIsMatched = qIsMatched;
	}

	public Timestamp getQCrtdDt() {
		return QCrtdDt;
	}

	public void setQCrtdDt(Timestamp qCrtdDt) {
		QCrtdDt = qCrtdDt;
	}

	public String[] getIclubPolicies() {
		return iclubPolicies;
	}

	public void setIclubPolicies(String[] iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

}
