package za.co.iclub.pss.trans;

import java.util.Date;

public class IclubMessageTrans {
	
	private String MId;
	private String iclubPerson;
	private String PFNameAndLName;
	private Long iclubSystemTypeByMToSysId;
	private String toStLongDesc;
	private Long iclubMessageType;
	private String mtLongDesc;
	private Long iclubSystemTypeByMFromSysId;
	private String fromStLongDesc;
	private Date MSentDt;
	private String MTranId;
	private String MContent;
	private Date MCrtdDt;
	
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
	
	public Date getMSentDt() {
		return MSentDt;
	}
	
	public void setMSentDt(Date mSentDt) {
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
	
	public Date getMCrtdDt() {
		return MCrtdDt;
	}
	
	public void setMCrtdDt(Date mCrtdDt) {
		MCrtdDt = mCrtdDt;
	}
	
	public String getPFNameAndLName() {
		return PFNameAndLName;
	}
	
	public void setPFNameAndLName(String pFNameAndLName) {
		PFNameAndLName = pFNameAndLName;
	}
	
	public String getToStLongDesc() {
		return toStLongDesc;
	}
	
	public void setToStLongDesc(String toStLongDesc) {
		this.toStLongDesc = toStLongDesc;
	}
	
	public String getMtLongDesc() {
		return mtLongDesc;
	}
	
	public void setMtLongDesc(String mtLongDesc) {
		this.mtLongDesc = mtLongDesc;
	}
	
	public String getFromStLongDesc() {
		return fromStLongDesc;
	}
	
	public void setFromStLongDesc(String fromStLongDesc) {
		this.fromStLongDesc = fromStLongDesc;
	}
	
}
