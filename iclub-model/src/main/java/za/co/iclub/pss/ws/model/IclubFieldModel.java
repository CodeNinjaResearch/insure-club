package za.co.iclub.pss.ws.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubFieldModel")
public class IclubFieldModel {

	private Long FId;
	private Long iclubEntityType;
	private String FName;
	private String FDesc;
	private String FType;
	private String FStatus;

	public Long getFId() {
		return FId;
	}

	public void setFId(Long fId) {
		FId = fId;
	}

	public Long getIclubEntityType() {
		return iclubEntityType;
	}

	public void setIclubEntityType(Long iclubEntityType) {
		this.iclubEntityType = iclubEntityType;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String fName) {
		FName = fName;
	}

	public String getFDesc() {
		return FDesc;
	}

	public void setFDesc(String fDesc) {
		FDesc = fDesc;
	}

	public String getFType() {
		return FType;
	}

	public void setFType(String fType) {
		FType = fType;
	}

	public String getFStatus() {
		return FStatus;
	}

	public void setFStatus(String fStatus) {
		FStatus = fStatus;
	}

}
