package za.co.iclub.pss.web.bean;

import java.sql.Timestamp;

public class IclubMessageBean {

	private String MId;
	private String iclubPerson;
	private Long iclubSystemTypeByMToSysId;
	private String iclubMessageType;
	private String iclubSystemTypeByMFromSysId;
	private Timestamp MSentDt;
	private String MTranId;
	private String MContent;
	private Timestamp MCrtdDt;

	public String getMId() {
		return MId;
	}

	public void setMId(String mId) {
		MId = mId;
	}

	public String getIclubPerson() {
		return iclubPerson;
	}

	public void setIclubPerson(String iclubPerson) {
		this.iclubPerson = iclubPerson;
	}

	public Long getIclubSystemTypeByMToSysId() {
		return iclubSystemTypeByMToSysId;
	}

	public void setIclubSystemTypeByMToSysId(Long iclubSystemTypeByMToSysId) {
		this.iclubSystemTypeByMToSysId = iclubSystemTypeByMToSysId;
	}

	public String getIclubMessageType() {
		return iclubMessageType;
	}

	public void setIclubMessageType(String iclubMessageType) {
		this.iclubMessageType = iclubMessageType;
	}

	public String getIclubSystemTypeByMFromSysId() {
		return iclubSystemTypeByMFromSysId;
	}

	public void setIclubSystemTypeByMFromSysId(String iclubSystemTypeByMFromSysId) {
		this.iclubSystemTypeByMFromSysId = iclubSystemTypeByMFromSysId;
	}

	public Timestamp getMSentDt() {
		return MSentDt;
	}

	public void setMSentDt(Timestamp mSentDt) {
		MSentDt = mSentDt;
	}

	public String getMTranId() {
		return MTranId;
	}

	public void setMTranId(String mTranId) {
		MTranId = mTranId;
	}

	public String getMContent() {
		return MContent;
	}

	public void setMContent(String mContent) {
		MContent = mContent;
	}

	public Timestamp getMCrtdDt() {
		return MCrtdDt;
	}

	public void setMCrtdDt(Timestamp mCrtdDt) {
		MCrtdDt = mCrtdDt;
	}

}
