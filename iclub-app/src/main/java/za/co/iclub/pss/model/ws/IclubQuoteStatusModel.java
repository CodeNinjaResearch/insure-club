package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubQuoteStatusModel")
public class IclubQuoteStatusModel {

	private Long qsId;
	private String qsShortDesc;
	private String qsLongDesc;
	private String qsStatus;

	public Long getQsId() {
		return qsId;
	}

	public void setQsId(Long qsId) {
		this.qsId = qsId;
	}

	public String getQsShortDesc() {
		return qsShortDesc;
	}

	public void setQsShortDesc(String qsShortDesc) {
		this.qsShortDesc = qsShortDesc;
	}

	public String getQsLongDesc() {
		return qsLongDesc;
	}

	public void setQsLongDesc(String qsLongDesc) {
		this.qsLongDesc = qsLongDesc;
	}

	public String getQsStatus() {
		return qsStatus;
	}

	public void setQsStatus(String qsStatus) {
		this.qsStatus = qsStatus;
	}

}
