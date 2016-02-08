package za.co.iclub.pss.model.ws;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubPropSecTypeModel")
public class IclubPropSecTypeModel {

	private Long pstId;
	private String pstShortDesc;
	private String pstLongDesc;
	private String pstStatus;

	public Long getPstId() {
		return pstId;
	}

	public void setPstId(Long pstId) {
		this.pstId = pstId;
	}

	public String getPstShortDesc() {
		return pstShortDesc;
	}

	public void setPstShortDesc(String pstShortDesc) {
		this.pstShortDesc = pstShortDesc;
	}

	public String getPstLongDesc() {
		return pstLongDesc;
	}

	public void setPstLongDesc(String pstLongDesc) {
		this.pstLongDesc = pstLongDesc;
	}

	public String getPstStatus() {
		return pstStatus;
	}

	public void setPstStatus(String pstStatus) {
		this.pstStatus = pstStatus;
	}

}
