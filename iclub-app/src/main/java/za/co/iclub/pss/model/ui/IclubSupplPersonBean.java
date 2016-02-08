package za.co.iclub.pss.model.ui;

import java.util.Date;

public class IclubSupplPersonBean {

	private String spId;
	private String iclubPersonABySpCrtdBy;
	private String PAFNameAndLName;
	private String iclubPersonBBySpPersonId;
	private String PBFNameAndLName;
	private String iclubSupplMaster;
	private String smRegNum;
	private Date spCrtdDt;

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getIclubPersonABySpCrtdBy() {
		return iclubPersonABySpCrtdBy;
	}

	public void setIclubPersonABySpCrtdBy(String iclubPersonABySpCrtdBy) {
		this.iclubPersonABySpCrtdBy = iclubPersonABySpCrtdBy;
	}

	public String getPAFNameAndLName() {
		return PAFNameAndLName;
	}

	public void setPAFNameAndLName(String pAFNameAndLName) {
		PAFNameAndLName = pAFNameAndLName;
	}

	public String getIclubPersonBBySpPersonId() {
		return iclubPersonBBySpPersonId;
	}

	public void setIclubPersonBBySpPersonId(String iclubPersonBBySpPersonId) {
		this.iclubPersonBBySpPersonId = iclubPersonBBySpPersonId;
	}

	public String getPBFNameAndLName() {
		return PBFNameAndLName;
	}

	public void setPBFNameAndLName(String pBFNameAndLName) {
		PBFNameAndLName = pBFNameAndLName;
	}

	public String getIclubSupplMaster() {
		return iclubSupplMaster;
	}

	public void setIclubSupplMaster(String iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
	}

	public String getSmRegNum() {
		return smRegNum;
	}

	public void setSmRegNum(String smRegNum) {
		this.smRegNum = smRegNum;
	}

	public Date getSpCrtdDt() {
		return spCrtdDt;
	}

	public void setSpCrtdDt(Date spCrtdDt) {
		this.spCrtdDt = spCrtdDt;
	}

}
