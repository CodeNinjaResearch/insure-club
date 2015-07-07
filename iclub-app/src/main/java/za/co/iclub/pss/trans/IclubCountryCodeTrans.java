package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubCountryCodeBean;
import za.co.iclub.pss.model.ws.IclubCountryCodeModel;
import za.co.iclub.pss.orm.bean.IclubCountryCode;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubCountryCodeTrans {
	
	private IclubPersonDAO iclubPersonDAO;
	
	public IclubCountryCodeBean fromWStoUI(IclubCountryCodeModel model) {
		
		IclubCountryCodeBean bean = new IclubCountryCodeBean();
		
		bean.setCcId(bean.getCcId());
		bean.setCcIsoId(bean.getCcIsoId());
		bean.setCcShortId(bean.getCcShortId());
		bean.setCcCrtdDt(bean.getCcCrtdDt());
		bean.setCcName(bean.getCcName());
		bean.setIclubPerson(bean.getIclubPerson());
		bean.setPFNameAndLName(bean.getPFNameAndLName());
		
		return bean;
	}
	
	public IclubCountryCodeModel fromUItoWS(IclubCountryCodeBean bean) {
		
		IclubCountryCodeModel model = new IclubCountryCodeModel();
		
		model.setCcId(bean.getCcId());
		model.setCcIsoId(bean.getCcIsoId());
		model.setCcShortId(bean.getCcShortId());
		model.setCcCrtdDt(bean.getCcCrtdDt());
		model.setCcName(bean.getCcName());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public IclubCountryCodeModel fromORMtoWS(IclubCountryCode bean) {
		
		IclubCountryCodeModel model = new IclubCountryCodeModel();
		
		model.setCcId(bean.getCcId());
		model.setCcIsoId(bean.getCcIsoId());
		model.setCcShortId(bean.getCcShortId());
		model.setCcCrtdDt(bean.getCcCrtdDt());
		model.setCcName(bean.getCcName());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubCountryCode fromWStoORM(IclubCountryCodeModel model) {
		IclubCountryCode bean = new IclubCountryCode();
		
		bean.setCcId(model.getCcId());
		bean.setCcId(model.getCcId());
		bean.setCcIsoId(model.getCcIsoId());
		bean.setCcShortId(model.getCcShortId());
		bean.setCcCrtdDt(model.getCcCrtdDt());
		bean.setCcName(model.getCcName());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
		
		return bean;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
}
