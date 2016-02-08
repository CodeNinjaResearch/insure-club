package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubOccupationBean;
import za.co.iclub.pss.model.ws.IclubOccupationModel;
import za.co.iclub.pss.orm.bean.IclubOccupation;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubOccupationTrans {

	public static IclubOccupationBean fromWStoUI(IclubOccupationModel model) {

		IclubOccupationBean bean = new IclubOccupationBean();

		bean.setOId(model.getOId());
		bean.setODesc(model.getODesc());
		bean.setOCrtdDt(model.getOCrtdDt());
		bean.setOStatus(model.getOStatus());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubOccupationModel fromUItoWS(IclubOccupationBean bean) {

		IclubOccupationModel model = new IclubOccupationModel();

		model.setOId(bean.getOId());
		model.setODesc(bean.getODesc());
		model.setOCrtdDt(bean.getOCrtdDt());
		model.setOStatus(bean.getOStatus());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubOccupationModel fromORMtoWS(IclubOccupation bean) {

		IclubOccupationModel model = new IclubOccupationModel();

		model.setOId(bean.getOId());
		model.setODesc(bean.getODesc());
		model.setOCrtdDt(bean.getOCrtdDt());
		model.setOStatus(bean.getOStatus());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubOccupation fromWStoORM(IclubOccupationModel model, IclubPersonDAO iclubPersonDAO) {

		IclubOccupation bean = new IclubOccupation();

		bean.setOId(model.getOId());
		bean.setODesc(model.getODesc());
		bean.setOCrtdDt(model.getOCrtdDt());
		bean.setOStatus(model.getOStatus());
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

		return bean;
	}
}
