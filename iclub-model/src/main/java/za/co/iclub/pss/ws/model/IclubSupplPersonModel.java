package za.co.iclub.pss.ws.model;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubSupplPersonModel")
public class IclubSupplPersonModel {

	private String spId;
	private String iclubPersonBySpCrtdBy;
	private String iclubPersonBySpPersonId;
	private String iclubSupplMaster;
	private Timestamp spCrtdDt;

	public String getSpId() {
		return spId;
	}

	public void setSpId(String spId) {
		this.spId = spId;
	}

	public String getIclubPersonBySpCrtdBy() {
		return iclubPersonBySpCrtdBy;
	}

	public void setIclubPersonBySpCrtdBy(String iclubPersonBySpCrtdBy) {
		this.iclubPersonBySpCrtdBy = iclubPersonBySpCrtdBy;
	}

	public String getIclubPersonBySpPersonId() {
		return iclubPersonBySpPersonId;
	}

	public void setIclubPersonBySpPersonId(String iclubPersonBySpPersonId) {
		this.iclubPersonBySpPersonId = iclubPersonBySpPersonId;
	}

	public String getIclubSupplMaster() {
		return iclubSupplMaster;
	}

	public void setIclubSupplMaster(String iclubSupplMaster) {
		this.iclubSupplMaster = iclubSupplMaster;
	}

	public Timestamp getSpCrtdDt() {
		return spCrtdDt;
	}

	public void setSpCrtdDt(Timestamp spCrtdDt) {
		this.spCrtdDt = spCrtdDt;
	}
}
