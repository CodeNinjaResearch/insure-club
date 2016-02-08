package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubMessageBoardBean {

	private String mbId;
	private String iclubPerson;
	private String PFNameAndLName;
	private String mbTitle;
	private String mbContent;
	private String mbTag;
	private Date mbCrtdDt;

	public String getMbId() {
		return mbId;
	}

	public void setMbId(String mbId) {
		this.mbId = mbId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public String getMbTitle() {
		return mbTitle;
	}

	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}

	public String getMbContent() {
		return mbContent;
	}

	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}

	public String getMbTag() {
		return mbTag;
	}

	public void setMbTag(String mbTag) {
		this.mbTag = mbTag;
	}

	public Date getMbCrtdDt() {
		return mbCrtdDt;
	}

	public void setMbCrtdDt(Date mbCrtdDt) {
		this.mbCrtdDt = mbCrtdDt;
	}

	public String getPFNameAndLName() {
		return PFNameAndLName;
	}

	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}

}
