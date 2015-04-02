package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubEntityTypeModel")
public class IclubEntityTypeModel {

	private Long etId;
	private String etShortDesc;
	private String etLongDesc;
	private String etStatus;
	private String etTblNm;
	private String[] iclubDocuments;
	private Long[] iclubRateTypes;
	private Long[] iclubFields;

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

	public String[] getIclubDocuments() {
		return iclubDocuments;
	}

	public void setIclubDocuments(String[] iclubDocuments) {
		this.iclubDocuments = iclubDocuments;
	}

	public String getEtTblNm() {
		return etTblNm;
	}

	public void setEtTblNm(String etTblNm) {
		this.etTblNm = etTblNm;
	}

	public Long[] getIclubRateTypes() {
		return iclubRateTypes;
	}

	public void setIclubRateTypes(Long[] iclubRateTypes) {
		this.iclubRateTypes = iclubRateTypes;
	}

	public Long[] getIclubFields() {
		return iclubFields;
	}

	public void setIclubFields(Long[] iclubFields) {
		this.iclubFields = iclubFields;
	}

}
