package za.co.iclub.pss.model.ui;

public class IclubUserDashboardBean {

	private Long totalQuoteCnt;
	private Double totalQPremium;
	private Double totalQEstValue;
	private String earliestQExpiry;

	private Long totalPaymentsCnt;
	private Double totalPyPremium;
	private Double totalCPremium;
	private String nextPremiumDate;

	private Long totalPolicyCnt;
	private Double totalPPremium;
	private Long noOfVehicles;
	private Long noOfProperties;

	private Long totalClaimCnt;
	private Double totalClaimValue;
	private Double rejectedClaimValue;
	private Double approvedClaimValue;

	public Long getTotalQuoteCnt() {
		return totalQuoteCnt;
	}

	public void setTotalQuoteCnt(Long totalQuoteCnt) {
		this.totalQuoteCnt = totalQuoteCnt;
	}

	public Double getTotalQPremium() {
		return totalQPremium;
	}

	public void setTotalQPremium(Double totalQPremium) {
		this.totalQPremium = totalQPremium;
	}

	public Double getTotalQEstValue() {
		return totalQEstValue;
	}

	public void setTotalQEstValue(Double totalQEstValue) {
		this.totalQEstValue = totalQEstValue;
	}

	public String getEarliestQExpiry() {
		return earliestQExpiry;
	}

	public void setEarliestQExpiry(String earliestQExpiry) {
		this.earliestQExpiry = earliestQExpiry;
	}

	public Long getTotalPaymentsCnt() {
		return totalPaymentsCnt;
	}

	public void setTotalPaymentsCnt(Long totalPaymentsCnt) {
		this.totalPaymentsCnt = totalPaymentsCnt;
	}

	public Double getTotalPyPremium() {
		return totalPyPremium;
	}

	public void setTotalPyPremium(Double totalPyPremium) {
		this.totalPyPremium = totalPyPremium;
	}

	public Double getTotalCPremium() {
		return totalCPremium;
	}

	public void setTotalCPremium(Double totalCPremium) {
		this.totalCPremium = totalCPremium;
	}

	public String getNextPremiumDate() {
		return nextPremiumDate;
	}

	public void setNextPremiumDate(String nextPremiumDate) {
		this.nextPremiumDate = nextPremiumDate;
	}

	public Long getTotalPolicyCnt() {
		return totalPolicyCnt;
	}

	public void setTotalPolicyCnt(Long totalPolicyCnt) {
		this.totalPolicyCnt = totalPolicyCnt;
	}

	public Double getTotalPPremium() {
		return totalPPremium;
	}

	public void setTotalPPremium(Double totalPPremium) {
		this.totalPPremium = totalPPremium;
	}

	public Long getNoOfVehicles() {
		return noOfVehicles;
	}

	public void setNoOfVehicles(Long noOfVehicles) {
		this.noOfVehicles = noOfVehicles;
	}

	public Long getNoOfProperties() {
		return noOfProperties;
	}

	public void setNoOfProperties(Long noOfProperties) {
		this.noOfProperties = noOfProperties;
	}

	public Long getTotalClaimCnt() {
		return totalClaimCnt;
	}

	public void setTotalClaimCnt(Long totalClaimCnt) {
		this.totalClaimCnt = totalClaimCnt;
	}

	public Double getTotalClaimValue() {
		return totalClaimValue;
	}

	public void setTotalClaimValue(Double totalClaimValue) {
		this.totalClaimValue = totalClaimValue;
	}

	public Double getRejectedClaimValue() {
		return rejectedClaimValue;
	}

	public void setRejectedClaimValue(Double rejectedClaimValue) {
		this.rejectedClaimValue = rejectedClaimValue;
	}

	public Double getApprovedClaimValue() {
		return approvedClaimValue;
	}

	public void setApprovedClaimValue(Double approvedClaimValue) {
		this.approvedClaimValue = approvedClaimValue;
	}
}
