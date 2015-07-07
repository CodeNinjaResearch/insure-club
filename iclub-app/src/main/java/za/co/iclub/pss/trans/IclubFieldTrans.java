package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubFieldBean;
import za.co.iclub.pss.model.ws.IclubFieldModel;
import za.co.iclub.pss.orm.bean.IclubField;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;

public class IclubFieldTrans {
	
	public static IclubFieldBean fromWStoUI(IclubFieldModel model) {
		
		IclubFieldBean bean = new IclubFieldBean();
		
		bean.setFId(model.getFId());
		bean.setFName(model.getFName());
		bean.setFDesc(model.getFDesc());
		bean.setFStatus(model.getFStatus());
		bean.setFLTblName(model.getFLTblName());
		bean.setFRate(model.getFRate());
		bean.setIclubEntityType(model.getIclubEntityType());
		bean.setEtLongDesc(model.getEtLongDesc());
		
		return bean;
	}
	
	public static IclubFieldModel fromUItoWS(IclubFieldBean bean) {
		
		IclubFieldModel model = new IclubFieldModel();
		
		model.setFId(bean.getFId());
		model.setFName(bean.getFName());
		model.setFDesc(bean.getFDesc());
		model.setFStatus(bean.getFStatus());
		model.setFLTblName(bean.getFLTblName());
		model.setFRate(bean.getFRate());
		model.setIclubEntityType(bean.getIclubEntityType());
		model.setEtLongDesc(bean.getEtLongDesc());
		
		return model;
	}
	
	public static IclubFieldModel fromORMtoWS(IclubField bean) {
		
		IclubFieldModel model = new IclubFieldModel();
		
		model.setFId(bean.getFId());
		model.setFName(bean.getFName());
		model.setFDesc(bean.getFDesc());
		model.setFStatus(bean.getFStatus());
		model.setFLTblName(bean.getFLTblName());
		model.setFRate(bean.getFRate());
		model.setIclubEntityType(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtId() : null);
		model.setEtLongDesc(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtLongDesc() : null);
		
		return model;
	}
	
	public static IclubField fromWStoORM(IclubFieldModel model, IclubEntityTypeDAO iclubEntityTypeDAO) {
		
		IclubField bean = new IclubField();
		
		bean.setFId(model.getFId());
		bean.setFName(model.getFName());
		bean.setFDesc(model.getFDesc());
		bean.setFStatus(model.getFStatus());
		bean.setFLTblName(model.getFLTblName());
		bean.setFRate(model.getFRate());
		bean.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
		
		return bean;
	}
}
