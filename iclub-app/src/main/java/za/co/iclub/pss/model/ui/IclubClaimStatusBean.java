package za.co.iclub.pss.model.ui;

public class IclubClaimStatusBean {

	private Long csId;
	private String csShortDesc;
	private String csLongDesc;
	private String csStatus;

	public Long getCsId() {
		return csId;
	}

	public void setCsId(Long csId) {
		this.csId = csId;
	}

	public String getCsShortDesc() {
		return csShortDesc;
	}

	public void setCsShortDesc(String csShortDesc) {
		this.csShortDesc = csShortDesc;
	}

	public String getCsLongDesc() {
		return csLongDesc;
	}

	public void setCsLongDesc(String csLongDesc) {
		this.csLongDesc = csLongDesc;
	}

	public String getCsStatus() {
		return csStatus;
	}

	public void setCsStatus(String csStatus) {
		this.csStatus = csStatus;
	}

}
