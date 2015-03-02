package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubDocumentTypeModel")
public class IclubDocumentTypeModel {

	private Long dtId;
	private String dtShortDesc;
	private String dtLongDesc;
	private String dtStatus;

	public Long getDtId() {
		return dtId;
	}

	public void setDtId(Long dtId) {
		this.dtId = dtId;
	}

	public String getDtShortDesc() {
		return dtShortDesc;
	}

	public void setDtShortDesc(String dtShortDesc) {
		this.dtShortDesc = dtShortDesc;
	}

	public String getDtLongDesc() {
		return dtLongDesc;
	}

	public void setDtLongDesc(String dtLongDesc) {
		this.dtLongDesc = dtLongDesc;
	}

	public String getDtStatus() {
		return dtStatus;
	}

	public void setDtStatus(String dtStatus) {
		this.dtStatus = dtStatus;
	}

}
