package za.co.iclub.pss.ws.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IclubCohortPersonModel")
public class IclubCohortPersonModel {

	private String cpId;
	private String iclubPersonByCpPersonId;
	private String iclubCohort;
	private String iclubPersonByCpCrtdBy;
	private Double cpContrib;
	private Date cpCrtdDt;

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getIclubPersonByCpPersonId() {
		return iclubPersonByCpPersonId;
	}

	public void setIclubPersonByCpPersonId(String iclubPersonByCpPersonId) {
		this.iclubPersonByCpPersonId = iclubPersonByCpPersonId;
	}

	public String getIclubCohort() {
		return iclubCohort;
	}

	public void setIclubCohort(String iclubCohort) {
		this.iclubCohort = iclubCohort;
	}

	public String getIclubPersonByCpCrtdBy() {
		return iclubPersonByCpCrtdBy;
	}

	public void setIclubPersonByCpCrtdBy(String iclubPersonByCpCrtdBy) {
		this.iclubPersonByCpCrtdBy = iclubPersonByCpCrtdBy;
	}

	public Double getCpContrib() {
		return cpContrib;
	}

	public void setCpContrib(Double cpContrib) {
		this.cpContrib = cpContrib;
	}

	public Date getCpCrtdDt() {
		return cpCrtdDt;
	}

	public void setCpCrtdDt(Date cpCrtdDt) {
		this.cpCrtdDt = cpCrtdDt;
	}

}
