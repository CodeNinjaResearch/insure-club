package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortPersonBean;
import za.co.iclub.pss.model.ws.IclubCohortPersonModel;
import za.co.iclub.pss.orm.bean.IclubCohortPerson;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCohortPersonTrans {
	
	public static IclubCohortPersonBean fromWStoUI(IclubCohortPersonModel model) {
		
		IclubCohortPersonBean bean = new IclubCohortPersonBean();
		
		model.setCpId(bean.getCpId());
		model.setIclubCohort(bean.getIclubCohort());
		model.setCEmail(bean.getCEmail());
		model.setCpCrtdDt(bean.getCpCrtdDt());
		model.setCpContrib(bean.getCpContrib());
		model.setIclubPersonAByCpPersonId(bean.getIclubPersonAByCpPersonId());
		model.setPAFNameAndLName(bean.getPAFNameAndLName());
		model.setIclubPersonBByCpCrtdBy(bean.getIclubPersonBByCpCrtdBy());
		model.setPBFNameAndLName(bean.getPBFNameAndLName());
		
		return bean;
	}
	
	public static IclubCohortPersonModel fromUItoWS(IclubCohortPersonBean bean) {
		IclubCohortPersonModel model = new IclubCohortPersonModel();
		
		return model;
	}
	
	public static IclubCohortPersonModel fromORMtoWS(IclubCohortPerson bean) {
		
		IclubCohortPersonModel model = new IclubCohortPersonModel();
		
		model.setCpId(bean.getCpId());
		model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
		model.setCEmail(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCEmail() : null);
		model.setCpCrtdDt(bean.getCpCrtdDt());
		model.setCpContrib(bean.getCpContrib());
		model.setIclubPersonAByCpPersonId(bean.getIclubPersonByCpPersonId() != null ? (bean.getIclubPersonByCpPersonId()).getPId() : null);
		model.setPAFNameAndLName(bean.getIclubPersonByCpPersonId() != null ? (bean.getIclubPersonByCpPersonId()).getPFName() + "" + bean.getIclubPersonByCpPersonId().getPLName() : null);
		model.setIclubPersonBByCpCrtdBy(bean.getIclubPersonByCpCrtdBy() != null ? (bean.getIclubPersonByCpCrtdBy()).getPId() : null);
		model.setPBFNameAndLName(bean.getIclubPersonByCpCrtdBy() != null ? (bean.getIclubPersonByCpCrtdBy()).getPFName() + "" + bean.getIclubPersonByCpCrtdBy().getPLName() : null);
		
		return model;
	}
	
	public static IclubCohortPerson fromWStoORM(IclubCohortPersonModel model, IclubCohortDAO iclubCohortDAO, IclubPersonDAO iclubPersonDAO) {
		
		IclubCohortPerson bean = new IclubCohortPerson();
		
		bean.setCpId(model.getCpId());
		bean.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
		bean.setCpCrtdDt(model.getCpCrtdDt());
		bean.setCpContrib(model.getCpContrib());
		bean.setIclubPersonByCpPersonId(iclubPersonDAO.findById(model.getIclubPersonAByCpPersonId()));
		bean.setIclubPersonByCpCrtdBy(iclubPersonDAO.findById(model.getIclubPersonBByCpCrtdBy()));
		
		return bean;
	}
}
