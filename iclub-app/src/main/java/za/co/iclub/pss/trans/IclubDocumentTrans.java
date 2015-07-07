package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubDocumentBean;
import za.co.iclub.pss.model.ws.IclubDocumentModel;
import za.co.iclub.pss.orm.bean.IclubDocument;
import za.co.iclub.pss.orm.dao.IclubDocumentTypeDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubDocumentTrans {
	
	private IclubDocumentTypeDAO iclubDocumentTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;
	
	public IclubDocumentBean fromWStoUI(IclubDocumentModel model) {
		
		IclubDocumentBean bean = new IclubDocumentBean();
		
		bean.setDId(model.getDId());
		bean.setDContent(model.getDContent());
		bean.setDEntityId(model.getDEntityId());
		bean.setDSize(model.getDSize());
		bean.setDMimeType(model.getDMimeType());
		bean.setDName(model.getDName());
		bean.setDCrtdDt(model.getDCrtdDt());
		bean.setIclubDocumentType(model.getIclubDocumentType());
		bean.setIclubEntityType(model.getIclubEntityType());
		bean.setDtLongDesc(model.getDtLongDesc());
		bean.setEtLongDesc(model.getEtLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubDocumentModel fromUItoWS(IclubDocumentBean bean) {
		
		IclubDocumentModel model = new IclubDocumentModel();
		
		model.setDId(bean.getDId());
		model.setDContent(bean.getDContent());
		model.setDEntityId(bean.getDEntityId());
		model.setDSize(bean.getDSize());
		model.setDMimeType(bean.getDMimeType());
		model.setDName(bean.getDName());
		model.setDCrtdDt(bean.getDCrtdDt());
		model.setIclubDocumentType(bean.getIclubDocumentType());
		model.setIclubEntityType(bean.getIclubEntityType());
		model.setDtLongDesc(bean.getDtLongDesc());
		model.setEtLongDesc(bean.getEtLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubDocumentModel fromORMtoWS(IclubDocument bean) {
		
		IclubDocumentModel model = new IclubDocumentModel();
		
		model.setDId(bean.getDId());
		model.setDContent(bean.getDContent());
		model.setDEntityId(bean.getDEntityId());
		model.setDSize(bean.getDSize());
		model.setDMimeType(bean.getDMimeType());
		model.setDName(bean.getDName());
		model.setDCrtdDt(bean.getDCrtdDt());
		model.setIclubDocumentType(bean.getIclubDocumentType() != null ? (bean.getIclubDocumentType().getDtId()) : null);
		model.setIclubEntityType(bean.getIclubEntityType() != null ? (bean.getIclubEntityType().getEtId()) : null);
		model.setDtLongDesc(bean.getIclubDocumentType() != null ? (bean.getIclubDocumentType().getDtLongDesc()) : null);
		model.setEtLongDesc(bean.getIclubEntityType() != null ? (bean.getIclubEntityType().getEtLongDesc()) : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubDocument fromWStoORM(IclubDocumentModel model) {
		
		IclubDocument bean = new IclubDocument();
		
		bean.setDId(model.getDId());
		bean.setDContent(model.getDContent());
		bean.setDEntityId(model.getDEntityId());
		bean.setDSize(model.getDSize());
		bean.setDMimeType(model.getDMimeType());
		bean.setDName(model.getDName());
		bean.setDCrtdDt(model.getDCrtdDt());
		bean.setIclubDocumentType(model.getIclubDocumentType() != null ? iclubDocumentTypeDAO.findById(model.getIclubDocumentType()) : null);
		bean.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
	
}
