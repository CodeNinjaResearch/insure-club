package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubMbCommentBean {

	private String mbcId;
	private String iclubPerson;
	private String iclubMessageBoard;
	private String mbcDesc;
	private Timestamp mbcCrtdDt;

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

	public Timestamp getMbcCrtdDt() {
		return mbcCrtdDt;
	}

	public void setMbcCrtdDt(Timestamp mbcCrtdDt) {
		this.mbcCrtdDt = mbcCrtdDt;
	}

}
