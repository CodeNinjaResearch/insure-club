package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubBankMasterBean;
import za.co.iclub.pss.model.ws.IclubBankMasterModel;
import za.co.iclub.pss.orm.bean.IclubBankMaster;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubBankMasterTrans {
	
	public static IclubBankMasterBean fromWStoUI(IclubBankMasterModel model) {
		IclubBankMasterBean bean = new IclubBankMasterBean();
		bean.setBmId(model.getBmId());
		bean.setBmBankName(model.getBmBankName());
		bean.setBmBankCode(model.getBmBankCode());
		bean.setBmBranchName(model.getBmBranchName());
		bean.setBmBranchCode(model.getBmBranchCode());
		bean.setBmBranchAddress(model.getBmBranchAddress());
		bean.setBmBranchLat(model.getBmBranchLat());
		bean.setBmBranchLong(model.getBmBranchLong());
		bean.setBmCrtdDt(model.getBmCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		return bean;
	}
	
	public static IclubBankMasterModel fromUItoWS(IclubBankMasterBean bean) {
		IclubBankMasterModel model = new IclubBankMasterModel();
		model.setBmId(bean.getBmId());
		model.setBmBankName(bean.getBmBankName());
		model.setBmBankCode(bean.getBmBankCode());
		model.setBmBranchName(bean.getBmBranchName());
		model.setBmBranchCode(bean.getBmBranchCode());
		model.setBmBranchAddress(bean.getBmBranchAddress());
		model.setBmBranchLat(bean.getBmBranchLat());
		model.setBmBranchLong(bean.getBmBranchLong());
		model.setBmCrtdDt(bean.getBmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		return model;
	}
	
	public static IclubBankMasterModel fromORMtoWS(IclubBankMaster bean) {
		IclubBankMasterModel model = new IclubBankMasterModel();
		model.setBmId(bean.getBmId());
		model.setBmBankName(bean.getBmBankName());
		model.setBmBankCode(bean.getBmBankCode());
		model.setBmBranchName(bean.getBmBranchName());
		model.setBmBranchCode(bean.getBmBranchCode());
		model.setBmBranchAddress(bean.getBmBranchAddress());
		model.setBmBranchLat(bean.getBmBranchLat());
		model.setBmBranchLong(bean.getBmBranchLong());
		model.setBmCrtdDt(bean.getBmCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		return model;
	}
	
	public static IclubBankMaster fromWStoORM(IclubBankMasterModel model, IclubPersonDAO iclubPersonDAO) {
		IclubBankMaster bean = new IclubBankMaster();
		bean.setBmId(model.getBmId());
		bean.setBmBankName(model.getBmBankName());
		bean.setBmBankCode(model.getBmBankCode());
		bean.setBmBranchName(model.getBmBranchName());
		bean.setBmBranchCode(model.getBmBranchCode());
		bean.setBmBranchAddress(model.getBmBranchAddress());
		bean.setBmBranchLat(model.getBmBranchLat());
		bean.setBmBranchLong(model.getBmBranchLong());
		bean.setBmCrtdDt(model.getBmCrtdDt());
		bean.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
		return bean;
	}
	
}
