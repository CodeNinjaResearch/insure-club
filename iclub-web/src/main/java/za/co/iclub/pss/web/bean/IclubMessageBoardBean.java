package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubMessageBoardBean {

	private String mbId;
	private String iclubPerson;
	private String mbTitle;
	private String mbContent;
	private String mbTag;
	private Timestamp mbCrtdDt;
	private String[] iclubMbComments;

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

	public Timestamp getMbCrtdDt() {
		return mbCrtdDt;
	}

	public void setMbCrtdDt(Timestamp mbCrtdDt) {
		this.mbCrtdDt = mbCrtdDt;
	}

	public String[] getIclubMbComments() {
		return iclubMbComments;
	}

	public void setIclubMbComments(String[] iclubMbComments) {
		this.iclubMbComments = iclubMbComments;
	}

}
