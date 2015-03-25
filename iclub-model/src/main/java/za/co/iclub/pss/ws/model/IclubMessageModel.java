package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubMessageModel")
public class IclubMessageModel {

	private String MId;
	private String iclubPerson;
	private Long iclubSystemTypeByMToSysId;
	private Long iclubMessageType;
	private Long iclubSystemTypeByMFromSysId;
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

	public Long getIclubMessageType() {
		return iclubMessageType;
	}

	public void setIclubMessageType(Long iclubMessageType) {
		this.iclubMessageType = iclubMessageType;
	}

	public Long getIclubSystemTypeByMFromSysId() {
		return iclubSystemTypeByMFromSysId;
	}

	public void setIclubSystemTypeByMFromSysId(Long iclubSystemTypeByMFromSysId) {
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
