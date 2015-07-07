package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubAccountBean;
import za.co.iclub.pss.model.ws.IclubAccountModel;
import za.co.iclub.pss.orm.bean.IclubAccount;
import za.co.iclub.pss.orm.dao.IclubAccountTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubOwnerTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubAccountTrans {
	
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubAccountTypeDAO iclubAccountTypeDAO;
	private IclubOwnerTypeDAO iclubOwnerTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	
	public IclubAccountBean fromWStoUI(IclubAccountModel model) {
		IclubAccountBean bean = new IclubAccountBean();
		bean.setAId(model.getAId());
		bean.setAAccNum(model.getAAccNum());
		bean.setACrtdDt(model.getACrtdDt());
		bean.setAOwnerId(model.getAOwnerId());
		bean.setIclubBankMaster(model.getIclubBankMaster());
		bean.setBmBankName(model.getBmBankName());
		bean.setBmCode(model.getBmCode());
		bean.setIclubAccountType(model.getIclubAccountType());
		bean.setAtLongDesc(model.getAtLongDesc());
		bean.setIclubOwnerType(model.getIclubOwnerType());
		bean.setOtLongDesc(model.getOtLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setAStatus(model.getAStatus());
		return bean;
	}
	
	public IclubAccountModel fromUItoWS(IclubAccountBean bean) {
		IclubAccountModel model = new IclubAccountModel();
		model.setAId(model.getAId());
		model.setAAccNum(model.getAAccNum());
		model.setACrtdDt(model.getACrtdDt());
		model.setAOwnerId(model.getAOwnerId());
		model.setIclubBankMaster(bean.getIclubBankMaster());
		model.setBmBankName(bean.getBmBankName());
		model.setBmCode(bean.getBmCode());
		model.setIclubAccountType(bean.getIclubAccountType());
		model.setAtLongDesc(bean.getAtLongDesc());
		model.setIclubOwnerType(bean.getIclubOwnerType());
		model.setOtLongDesc(bean.getOtLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setAStatus(model.getAStatus());
		return model;
	}
	
	public IclubAccountModel fromORMtoWS(IclubAccount bean) {
		IclubAccountModel model = new IclubAccountModel();
		model.setAId(model.getAId());
		model.setAAccNum(model.getAAccNum());
		model.setACrtdDt(model.getACrtdDt());
		model.setAOwnerId(model.getAOwnerId());
		model.setIclubBankMaster(bean.getIclubBankMaster() != null ? bean.getIclubBankMaster().getBmId() : null);
		model.setBmBankName(bean.getIclubBankMaster() != null ? bean.getIclubBankMaster().getBmBankName() : null);
		model.setIclubAccountType(bean.getIclubAccountType() != null ? bean.getIclubAccountType().getAtId() : null);
		model.setAtLongDesc(bean.getIclubAccountType() != null ? bean.getIclubAccountType().getAtLongDesc() : null);
		model.setIclubOwnerType(bean.getIclubOwnerType() != null ? bean.getIclubOwnerType().getOtId() : null);
		model.setOtLongDesc(bean.getIclubOwnerType() != null ? bean.getIclubOwnerType().getOtLongDesc() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() : null);
		model.setAStatus(model.getAStatus());
		return model;
	}
	
	public IclubAccount fromWStoORM(IclubAccountModel model) {
		IclubAccount bean = new IclubAccount();
		bean.setAId(model.getAId());
		bean.setAAccNum(model.getAAccNum());
		bean.setACrtdDt(model.getACrtdDt());
		bean.setAOwnerId(model.getAOwnerId());
		bean.setIclubBankMaster(model.getIclubBankMaster() != null ? iclubBankMasterDAO.findById(model.getIclubBankMaster()) : null);
		bean.setIclubAccountType(model.getIclubAccountType() != null ? iclubAccountTypeDAO.findById(model.getIclubAccountType()) : null);
		bean.setIclubOwnerType(model.getIclubOwnerType() != null ? iclubOwnerTypeDAO.findById(model.getIclubOwnerType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setAStatus(model.getAStatus());
		return bean;
	}
	
}
