package za.co.iclub.pss.ws.model.common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ResponseModel")
public class ResponseModel {
	private Integer statusCode;
	private String statusDesc;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	

}
