package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubDocumentTypeBean;
import za.co.iclub.pss.model.ws.IclubDocumentTypeModel;
import za.co.iclub.pss.orm.bean.IclubDocumentType;

public class IclubDocumentTypeTrans {
	
	public static IclubDocumentTypeBean fromWStoUI(IclubDocumentTypeModel model) {
		IclubDocumentTypeBean bean = new IclubDocumentTypeBean();
		bean.setDtId(model.getDtId());
		bean.setDtLongDesc(model.getDtLongDesc());
		bean.setDtShortDesc(model.getDtShortDesc());
		bean.setDtStatus(model.getDtStatus());
		return bean;
	}
	
	public static IclubDocumentTypeModel fromUItoWS(IclubDocumentTypeBean bean) {
		IclubDocumentTypeModel model = new IclubDocumentTypeModel();
		model.setDtId(bean.getDtId());
		model.setDtLongDesc(bean.getDtLongDesc());
		model.setDtShortDesc(bean.getDtShortDesc());
		model.setDtStatus(bean.getDtStatus());
		return model;
	}
	
	public static IclubDocumentTypeModel fromORMtoWS(IclubDocumentType bean) {
		IclubDocumentTypeModel model = new IclubDocumentTypeModel();
		model.setDtId(bean.getDtId());
		model.setDtLongDesc(bean.getDtLongDesc());
		model.setDtShortDesc(bean.getDtShortDesc());
		model.setDtStatus(bean.getDtStatus());
		return model;
	}
	
	public static IclubDocumentType fromWStoORM(IclubDocumentTypeModel model) {
		IclubDocumentType acctype = new IclubDocumentType();
		
		acctype.setDtId(model.getDtId());
		acctype.setDtLongDesc(model.getDtLongDesc());
		acctype.setDtShortDesc(model.getDtShortDesc());
		acctype.setDtStatus(model.getDtStatus());
		
		return acctype;
	}
}
