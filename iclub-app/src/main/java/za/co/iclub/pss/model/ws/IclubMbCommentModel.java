package za.co.iclub.pss.model.ws;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubMbCommentModel")
public class IclubMbCommentModel {
	
	private String mbcId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String iclubMessageBoard;
	private String mbContent;
	private String mbcDesc;
	private Date mbcCrtdDt;
	
	public String getMbcId() {
		return mbcId;
	}
	
	public void setMbcId(String mbcId) {
		this.mbcId = mbcId;
	}
	
	public String getIclubPerson() {
		return iclubPerson;
	}
	
	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}
	
	public String getIclubMessageBoard() {
		return iclubMessageBoard;
	}
	
	public void setIclubMessageBoard(String iclubMessageBoard) {
		this.iclubMessageBoard = iclubMessageBoard;
	}
	
	public String getMbcDesc() {
		return mbcDesc;
	}
	
	public void setMbcDesc(String mbcDesc) {
		this.mbcDesc = mbcDesc;
	}
	
	public Date getMbcCrtdDt() {
		return mbcCrtdDt;
	}
	
	public void setMbcCrtdDt(Date mbcCrtdDt) {
		this.mbcCrtdDt = mbcCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getMbContent() {
		return mbContent;
	}
	
	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}
	
}
