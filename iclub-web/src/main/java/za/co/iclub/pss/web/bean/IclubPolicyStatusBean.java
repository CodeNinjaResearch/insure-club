package za.co.iclub.pss.web.bean;

public class IclubPolicyStatusBean {

	private Long psId;
	private String psShortDesc;
	private String psLongDesc;
	private String psStatus;
	private String[] iclubPolicies;

	public Long getPsId() {
		return psId;
	}

	public void setPsId(Long psId) {
		this.psId = psId;
	}

	public String getPsShortDesc() {
		return psShortDesc;
	}

	public void setPsShortDesc(String psShortDesc) {
		this.psShortDesc = psShortDesc;
	}

	public String getPsLongDesc() {
		return psLongDesc;
	}

	public void setPsLongDesc(String psLongDesc) {
		this.psLongDesc = psLongDesc;
	}

	public String getPsStatus() {
		return psStatus;
	}

	public void setPsStatus(String psStatus) {
		this.psStatus = psStatus;
	}

	public String[] getIclubPolicies() {
		return iclubPolicies;
	}

	public void setIclubPolicies(String[] iclubPolicies) {
		this.iclubPolicies = iclubPolicies;
	}

}
