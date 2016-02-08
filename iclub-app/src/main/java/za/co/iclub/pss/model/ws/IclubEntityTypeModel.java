package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubEntityTypeModel")
public class IclubEntityTypeModel {

	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etStatus;
	private String etTblNm;

	public Long getEtId() {
		return etId;
	}

	public void setEtId(Long etId) {
		this.etId = etId;
	}

	public String getEtShortDesc() {
		return etShortDesc;
	}

	public void setEtShortDesc(String etShortDesc) {
		this.etShortDesc = etShortDesc;
	}

	public String getEtLongDesc() {
		return etLongDesc;
	}

	public void setEtLongDesc(String etLongDesc) {
		this.etLongDesc = etLongDesc;
	}

	public String getEtStatus() {
		return etStatus;
	}

	public void setEtStatus(String etStatus) {
		this.etStatus = etStatus;
	}

	public String getEtTblNm() {
		return etTblNm;
	}

	public void setEtTblNm(String etTblNm) {
		this.etTblNm = etTblNm;
	}

}
