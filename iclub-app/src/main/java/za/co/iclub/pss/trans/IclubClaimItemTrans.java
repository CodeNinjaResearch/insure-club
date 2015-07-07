package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubClaimItemBean;
import za.co.iclub.pss.model.ws.IclubClaimItemModel;
import za.co.iclub.pss.orm.bean.IclubClaimItem;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubClaimStatusDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;

public class IclubClaimItemTrans {
	
	public static IclubClaimItemBean fromWStoUI(IclubClaimItemModel model) {
		IclubClaimItemBean bean = new IclubClaimItemBean();
		bean.setCiId(model.getCiId());
		bean.setCiCrtdBy(model.getCiCrtdBy());
		bean.setCiCrtdDt(model.getCiCrtdDt());
		bean.setCiValue(model.getCiValue());
		bean.setIclubClaimStatus(model.getIclubClaimStatus());
		bean.setCsLongDesc(model.getCsLongDesc());
		bean.setIclubClaim(model.getIclubClaim());
		bean.setCValue(model.getCValue());
		bean.setCNumber(model.getCNumber());
		bean.setIclubSupplMasterByCiAssesorId(model.getIclubSupplMasterByCiAssesorId());
		bean.setSmARegNum(model.getSmARegNum());
		bean.setIclubInsuranceItem(model.getIclubInsuranceItem());
		bean.setIiQuoteId(model.getIiQuoteId());
		bean.setIiItemId(model.getIclubInsuranceItem());
		bean.setIclubSupplMasterByCiHandlerId(model.getIclubSupplMasterByCiHandlerId());
		bean.setSmBRegNum(model.getSmBRegNum());
		
		return bean;
	}
	
	public static IclubClaimItemModel fromUItoWS(IclubClaimItemBean bean) {
		IclubClaimItemModel model = new IclubClaimItemModel();
		model.setCiId(bean.getCiId());
		model.setCiCrtdBy(bean.getCiCrtdBy());
		model.setCiCrtdDt(bean.getCiCrtdDt());
		model.setCiValue(bean.getCiValue());
		model.setIclubClaimStatus(bean.getIclubClaimStatus());
		model.setCsLongDesc(bean.getCsLongDesc());
		model.setIclubClaim(bean.getIclubClaim());
		model.setCValue(bean.getCValue());
		model.setCNumber(bean.getCNumber());
		model.setIclubSupplMasterByCiAssesorId(bean.getIclubSupplMasterByCiAssesorId());
		model.setSmARegNum(bean.getSmARegNum());
		model.setIclubInsuranceItem(bean.getIclubInsuranceItem());
		model.setIiQuoteId(bean.getIiQuoteId());
		model.setIiItemId(bean.getIclubInsuranceItem());
		model.setIclubSupplMasterByCiHandlerId(bean.getIclubSupplMasterByCiHandlerId());
		model.setSmBRegNum(bean.getSmBRegNum());
		return model;
	}
	
	public static IclubClaimItemModel fromORMtoWS(IclubClaimItem bean) {
		IclubClaimItemModel model = new IclubClaimItemModel();
		
		model.setCiId(bean.getCiId());
		model.setCiCrtdBy(bean.getCiCrtdBy());
		model.setCiCrtdDt(bean.getCiCrtdDt());
		model.setCiValue(bean.getCiValue());
		model.setIclubClaimStatus(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsId()) : null);
		model.setCsLongDesc(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsLongDesc()) : null);
		model.setIclubClaim(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCId()) : null);
		model.setCValue(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCValue()) : null);
		model.setCNumber(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCNumber()) : null);
		model.setIclubSupplMasterByCiAssesorId(bean.getIclubSupplMasterByCiAssesorId() != null ? (bean.getIclubSupplMasterByCiAssesorId().getSmId()) : null);
		model.setSmARegNum(bean.getIclubSupplMasterByCiAssesorId() != null ? (bean.getIclubSupplMasterByCiAssesorId().getSmRegNum()) : null);
		model.setIclubInsuranceItem(bean.getIclubInsuranceItem() != null ? (bean.getIclubInsuranceItem().getIiId()) : null);
		model.setIiQuoteId(bean.getIclubInsuranceItem() != null ? (bean.getIclubInsuranceItem().getIiQuoteId()) : null);
		model.setIiItemId(bean.getIclubInsuranceItem() != null ? (bean.getIclubInsuranceItem().getIiItemId()) : null);
		model.setIclubSupplMasterByCiHandlerId(bean.getIclubSupplMasterByCiHandlerId() != null ? (bean.getIclubSupplMasterByCiHandlerId().getSmId()) : null);
		model.setSmBRegNum(bean.getIclubSupplMasterByCiHandlerId() != null ? (bean.getIclubSupplMasterByCiHandlerId().getSmRegNum()) : null);
		
		return model;
	}
	
	public static IclubClaimItem fromWStoORM(IclubClaimItemModel model, IclubClaimStatusDAO iclubClaimStatusDAO, IclubClaimDAO iclubClaimDAO, IclubInsuranceItemDAO iclubInsuranceItemDAO, IclubSupplMasterDAO iclubSupplMasterDAO) {
		
		IclubClaimItem bean = new IclubClaimItem();
		bean.setCiId(model.getCiId());
		bean.setCiCrtdBy(model.getCiCrtdBy());
		bean.setCiCrtdDt(model.getCiCrtdDt());
		bean.setCiValue(model.getCiValue());
		bean.setIclubSupplMasterByCiHandlerId(model.getIclubSupplMasterByCiHandlerId() != null && !model.getIclubSupplMasterByCiHandlerId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiHandlerId()) : null);
		bean.setIclubInsuranceItem(model.getIclubInsuranceItem() != null && !model.getIclubInsuranceItem().trim().equalsIgnoreCase("") ? iclubInsuranceItemDAO.findById(model.getIclubInsuranceItem()) : null);
		bean.setIclubSupplMasterByCiAssesorId(model.getIclubSupplMasterByCiAssesorId() != null && !model.getIclubSupplMasterByCiAssesorId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiAssesorId()) : null);
		bean.setIclubClaim(model.getIclubClaim() != null && !model.getIclubClaim().trim().equalsIgnoreCase("") ? iclubClaimDAO.findById(model.getIclubClaim()) : null);
		bean.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);
		
		return bean;
	}
	
}
