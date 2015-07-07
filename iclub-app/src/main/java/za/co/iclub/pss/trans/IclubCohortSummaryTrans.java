package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCohortSummaryBean;
import za.co.iclub.pss.model.ws.IclubCohortSummaryModel;

public class IclubCohortSummaryTrans {
	
	public static IclubCohortSummaryBean fromWStoUI(IclubCohortSummaryModel model) {
		
		IclubCohortSummaryBean bean = new IclubCohortSummaryBean();
		
		bean.setClaimSinceI(model.getClaimSinceI());
		bean.setClaimsInYear(model.getClaimsInYear());
		bean.setPremiumForYear(model.getPremiumForYear());
		bean.setPremiumPaidInYear(model.getPremiumPaidInYear());
		bean.setPrimumSinceI(model.getPrimumSinceI());
		
		return bean;
	}
	
	public static IclubCohortSummaryModel fromUItoWS(IclubCohortSummaryBean bean) {
		
		IclubCohortSummaryModel model = new IclubCohortSummaryModel();
		
		model.setClaimSinceI(bean.getClaimSinceI());
		model.setClaimsInYear(bean.getClaimsInYear());
		model.setPremiumForYear(bean.getPremiumForYear());
		model.setPremiumPaidInYear(bean.getPremiumPaidInYear());
		model.setPrimumSinceI(bean.getPrimumSinceI());
		
		return model;
	}
	
}
