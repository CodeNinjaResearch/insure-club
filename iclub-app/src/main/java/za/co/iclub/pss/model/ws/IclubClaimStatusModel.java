package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubClaimStatusModel")
public class IclubClaimStatusModel {

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
