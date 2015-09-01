package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortCriteriaBean;
import za.co.iclub.pss.model.ws.IclubCohortCriteriaModel;
import za.co.iclub.pss.orm.bean.IclubCohortCriteria;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;

public class IclubCohortCriteriaTrans {
	
	public static IclubCohortCriteriaBean fromWStoUI(IclubCohortCriteriaModel model) {
		IclubCohortCriteriaBean bean = new IclubCohortCriteriaBean();
		
		bean.setCcId(model.getCcId());
		bean.setCcClaimLastTwYrs(model.getCcClaimLastTwYrs());
		bean.setCcAge(model.getCcAge());
		bean.setCcGender(model.getCcGender());
		bean.setCcClaimLastYr(model.getCcClaimLastYr());
		bean.setIclubCohortInvite(model.getIclubCohortInvite());
		bean.setCcInsuredValue(model.getCcInsuredValue());
		bean.setCcMaritialStatus(model.getCcMaritialStatus());
		
		return bean;
	}
	
	public static IclubCohortCriteriaModel fromUItoWS(IclubCohortCriteriaBean bean) {
		IclubCohortCriteriaModel model = new IclubCohortCriteriaModel();
		
		model.setCcId(bean.getCcId());
		model.setCcClaimLastTwYrs(bean.getCcClaimLastTwYrs());
		model.setCcAge(bean.getCcAge());
		model.setCcGender(bean.getCcGender());
		model.setCcClaimLastYr(bean.getCcClaimLastYr());
		model.setIclubCohortInvite(bean.getIclubCohortInvite());
		model.setCcInsuredValue(bean.getCcInsuredValue());
		model.setCcMaritialStatus(bean.getCcMaritialStatus());
		
		return model;
	}
	
	public static IclubCohortCriteriaModel fromORMtoWS(IclubCohortCriteria bean) {
		
		IclubCohortCriteriaModel model = new IclubCohortCriteriaModel();
		model.setCcId(bean.getCcId());
		model.setCcClaimLastTwYrs(bean.getCcClaimLastTwYrs());
		model.setCcAge(bean.getCcAge());
		model.setCcGender(bean.getCcGender());
		model.setCcClaimLastYr(bean.getCcClaimLastYr());
		model.setCcInsuredValue(bean.getCcInsuredValue());
		model.setCcMaritialStatus(bean.getCcMaritialStatus());
		model.setIclubCohortInvite(bean.getIclubCohortInvite() != null ? bean.getIclubCohortInvite().getCiId() : null);
		
		return model;
	}
	
	public static IclubCohortCriteria fromWStoORM(IclubCohortCriteriaModel model, IclubCohortInviteDAO iclubCohortInviteDAO) {
		
		IclubCohortCriteria bean = new IclubCohortCriteria();
		
		bean.setCcId(model.getCcId());
		bean.setCcClaimLastTwYrs(model.getCcClaimLastTwYrs());
		bean.setCcAge(model.getCcAge());
		bean.setCcGender(model.getCcGender());
		bean.setCcClaimLastYr(model.getCcClaimLastYr());
		bean.setCcInsuredValue(model.getCcInsuredValue());
		bean.setCcMaritialStatus(model.getCcMaritialStatus());
		bean.setIclubCohortInvite(model.getIclubCohortInvite() != null ? iclubCohortInviteDAO.findById(model.getIclubCohortInvite()) : null);
		
		return bean;
	}
}
