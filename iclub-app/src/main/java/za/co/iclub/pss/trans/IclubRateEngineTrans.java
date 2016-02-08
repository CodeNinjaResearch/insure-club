package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubRateEngineBean;
import za.co.iclub.pss.model.ws.IclubRateEngineModel;
import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubRateTypeDAO;

public class IclubRateEngineTrans {

	public static IclubRateEngineBean fromWStoUI(IclubRateEngineModel model) {

		IclubRateEngineBean bean = new IclubRateEngineBean();

		bean.setReId(model.getReId());
		bean.setReRate(model.getReRate());
		bean.setReCrtdDt(model.getReCrtdDt());
		bean.setReStatus(model.getReStatus());
		bean.setReMaxValue(model.getReMaxValue());
		bean.setReBaseValue(model.getReBaseValue());
		bean.setIclubRateType(model.getIclubRateType());
		bean.setRtLongDesc(model.getRtLongDesc());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubRateEngineModel fromUItoWS(IclubRateEngineBean bean) {

		IclubRateEngineModel model = new IclubRateEngineModel();

		model.setReId(bean.getReId());
		model.setReRate(bean.getReRate());
		model.setReCrtdDt(bean.getReCrtdDt());
		model.setReStatus(bean.getReStatus());
		model.setReMaxValue(bean.getReMaxValue());
		model.setReBaseValue(bean.getReBaseValue());
		model.setIclubRateType(bean.getIclubRateType());
		model.setRtLongDesc(bean.getRtLongDesc());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubRateEngineModel fromORMtoWS(IclubRateEngine bean) {

		IclubRateEngineModel model = new IclubRateEngineModel();
		model.setReId(bean.getReId());
		model.setReRate(bean.getReRate());
		model.setReCrtdDt(bean.getReCrtdDt());
		model.setReStatus(bean.getReStatus());
		model.setReMaxValue(bean.getReMaxValue());
		model.setReBaseValue(bean.getReBaseValue());
		model.setIclubRateType(bean.getIclubRateType() != null ? bean.getIclubRateType().getRtId() : null);
		model.setRtLongDesc(bean.getIclubRateType() != null ? bean.getIclubRateType().getRtLongDesc() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubRateEngine fromWStoORM(IclubRateEngineModel model, IclubRateTypeDAO iclubRateTypeDAO, IclubPersonDAO iclubPersonDAO, IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {

		IclubRateEngine bean = new IclubRateEngine();

		bean.setReId(model.getReId());
		bean.setReRate(model.getReRate());
		bean.setReCrtdDt(model.getReCrtdDt());
		bean.setReStatus(model.getReStatus());
		bean.setReMaxValue(model.getReMaxValue());
		bean.setReBaseValue(model.getReBaseValue());
		bean.setIclubRateType(model.getIclubRateType() != null ? iclubRateTypeDAO.findById(model.getIclubRateType()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		return bean;
	}
}
