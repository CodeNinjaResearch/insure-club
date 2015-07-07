package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubRateTypeBean;
import za.co.iclub.pss.model.ws.IclubRateTypeModel;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubFieldDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubRateTypeTrans {
	
	public static IclubRateTypeBean fromWStoUI(IclubRateTypeModel model) {
		
		IclubRateTypeBean bean = new IclubRateTypeBean();
		
		bean.setRtId(model.getRtId());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setRtShortDesc(model.getRtShortDesc());
		bean.setRtStatus(model.getRtStatus());
		bean.setRtQuoteType(model.getRtQuoteType());
		bean.setIclubField(model.getIclubField());
		bean.setFDesc(model.getFDesc());
		bean.setFName(model.getFName());
		bean.setIclubEntityType(model.getIclubEntityType());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType());
		bean.setRtCrtdDt(model.getRtCrtdDt());
		bean.setRtType(model.getRtType());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubRateTypeModel fromUItoWS(IclubRateTypeBean bean) {
		
		IclubRateTypeModel model = new IclubRateTypeModel();
		
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		model.setRtQuoteType(bean.getRtQuoteType());
		model.setIclubField(bean.getIclubField());
		model.setFDesc(bean.getFDesc());
		model.setFName(bean.getFName());
		model.setIclubEntityType(bean.getIclubEntityType());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType());
		model.setRtCrtdDt(bean.getRtCrtdDt());
		model.setRtType(bean.getRtType());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubRateTypeModel fromORMtoWS(IclubRateType bean) {
		
		IclubRateTypeModel model = new IclubRateTypeModel();
		
		model.setRtId(bean.getRtId());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setRtShortDesc(bean.getRtShortDesc());
		model.setRtStatus(bean.getRtStatus());
		model.setRtQuoteType(bean.getRtQuoteType());
		model.setIclubField(bean.getIclubField() != null ? bean.getIclubField().getFId() : null);
		model.setFDesc(bean.getIclubField() != null ? bean.getIclubField().getFDesc() : null);
		model.setFName(bean.getIclubField() != null ? bean.getIclubField().getFName() : null);
		model.setIclubEntityType(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtId() : null);
		model.setEtLongDesc(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtLongDesc() : null);
		model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setRtCrtdDt(bean.getRtCrtdDt());
		model.setRtType(bean.getRtType());
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubRateType fromWStoORM(IclubRateTypeModel model, IclubFieldDAO iclubFieldDAO, IclubPersonDAO iclubPersonDAO, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO, IclubEntityTypeDAO iclubEntityTypeDAO) {
		
		IclubRateType bean = new IclubRateType();
		
		bean.setRtId(model.getRtId());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setRtShortDesc(model.getRtShortDesc());
		bean.setRtStatus(model.getRtStatus());
		bean.setIclubField(model.getIclubField() != null ? iclubFieldDAO.findById(model.getIclubField()) : null);
		bean.setRtQuoteType(model.getRtQuoteType());
		bean.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
		bean.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setRtCrtdDt(model.getRtCrtdDt());
		bean.setRtType(model.getRtType());
		
		return bean;
	}
	
}
