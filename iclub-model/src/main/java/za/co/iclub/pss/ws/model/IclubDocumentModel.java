package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubDocumentModel")
public class IclubDocumentModel {

	private String DId;
	private Long iclubDocumentType;
	private String iclubPerson;
	private Long iclubEntityType;
	private String DName;
	private String DMimeType;
	private Long DSize;
	private String DEntityId;
	private String DContent;
	private Date DCrtdDt;
//	private byte[] DBlob;

	public String getDId() {
		return DId;
	}

	public void setDId(String dId) {
		DId = dId;
	}

	public Long getIclubDocumentType() {
		return iclubDocumentType;
	}

	public void setIclubDocumentType(Long iclubDocumentType) {
		this.iclubDocumentType = iclubDocumentType;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubEntityType() {
		return iclubEntityType;
	}

	public void setIclubEntityType(Long iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	public String getDName() {
		return DName;
	}

	public void setDName(String dName) {
		DName = dName;
	}

	public String getDMimeType() {
		return DMimeType;
	}

	public void setDMimeType(String dMimeType) {
		DMimeType = dMimeType;
	}

	public Long getDSize() {
		return DSize;
	}

	public void setDSize(Long dSize) {
		DSize = dSize;
	}

	public String getDEntityId() {
		return DEntityId;
	}

	public void setDEntityId(String dEntityId) {
		DEntityId = dEntityId;
	}

	public String getDContent() {
		return DContent;
	}

	public void setDContent(String dContent) {
		DContent = dContent;
	}

	public Date getDCrtdDt() {
		return DCrtdDt;
	}

	public void setDCrtdDt(Date dCrtdDt) {
		DCrtdDt = dCrtdDt;
	}

}
