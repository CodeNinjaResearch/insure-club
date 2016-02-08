package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubPropertyItemBean;
import za.co.iclub.pss.model.ws.IclubPropertyItemModel;
import za.co.iclub.pss.orm.bean.IclubPropertyItem;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPropertyDAO;

public class IclubPropertyItemTrans {

	public static IclubPropertyItemBean fromWStoUI(IclubPropertyItemModel model) {

		IclubPropertyItemBean bean = new IclubPropertyItemBean();

		bean.setPiId(model.getPiId());
		bean.setPiCrtdDate(model.getPiCrtdDate());
		bean.setPiDescripton(model.getPiDescripton());
		bean.setPiValue(model.getPiValue());
		bean.setIclubProperty(model.getIclubProperty());
		bean.setPRegNum(model.getPRegNum());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());

		return bean;
	}

	public static IclubPropertyItemModel fromUItoWS(IclubPropertyItemBean bean) {

		IclubPropertyItemModel model = new IclubPropertyItemModel();

		model.setPiId(bean.getPiId());
		model.setPiCrtdDate(bean.getPiCrtdDate());
		model.setPiDescripton(bean.getPiDescripton());
		model.setPiValue(bean.getPiValue());
		model.setIclubProperty(bean.getIclubProperty());
		model.setPRegNum(bean.getPRegNum());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());

		return model;
	}

	public static IclubPropertyItemModel fromORMtoWS(IclubPropertyItem bean) {

		IclubPropertyItemModel model = new IclubPropertyItemModel();

		model.setPiId(bean.getPiId());
		model.setPiCrtdDate(bean.getPiCrtdDate());
		model.setPiDescripton(bean.getPiDescripton());
		model.setPiValue(bean.getPiValue());
		model.setIclubProperty(bean.getIclubProperty() != null ? bean.getIclubProperty().getPId() : null);
		model.setPRegNum(bean.getIclubProperty() != null ? bean.getIclubProperty().getPRegNum() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");

		return model;
	}

	public static IclubPropertyItem fromWStoORM(IclubPropertyItemModel model, IclubPersonDAO iclubPersonDAO, IclubPropertyDAO iclubPropertyDAO) {

		IclubPropertyItem bean = new IclubPropertyItem();

		bean.setPiId(model.getPiId());
		bean.setPiCrtdDate(model.getPiCrtdDate());
		bean.setPiDescripton(model.getPiDescripton());
		bean.setPiValue(model.getPiValue());
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubProperty(model.getIclubProperty() != null ? iclubPropertyDAO.findById(model.getIclubProperty()) : null);

		return bean;
	}
}
