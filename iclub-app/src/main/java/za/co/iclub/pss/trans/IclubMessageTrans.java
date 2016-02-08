package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMessageBean;
import za.co.iclub.pss.model.ws.IclubMessageModel;
import za.co.iclub.pss.orm.bean.IclubMessage;
import za.co.iclub.pss.orm.dao.IclubMessageTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSystemTypeDAO;

public class IclubMessageTrans {

	public static IclubMessageBean fromWStoUI(IclubMessageModel model) {
		IclubMessageBean bean = new IclubMessageBean();

		bean.setMId(model.getMId());
		bean.setMContent(model.getMContent());
		bean.setMTranId(model.getMTranId());
		bean.setMCrtdDt(model.getMCrtdDt());
		bean.setMSentDt(model.getMSentDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setIclubMessageType(model.getIclubMessageType());
		bean.setMtLongDesc(model.getMtLongDesc());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		bean.setFromStLongDesc(model.getFromStLongDesc());
		bean.setToStLongDesc(model.getToStLongDesc());
		bean.setIclubSystemTypeByMFromSysId(model.getIclubSystemTypeByMFromSysId());
		bean.setIclubSystemTypeByMToSysId(model.getIclubSystemTypeByMToSysId());

		return bean;
	}

	public static IclubMessageModel fromUItoWS(IclubMessageBean bean) {

		IclubMessageModel model = new IclubMessageModel();

		model.setMId(bean.getMId());
		model.setMContent(bean.getMContent());
		model.setMTranId(bean.getMTranId());
		model.setMCrtdDt(bean.getMCrtdDt());
		model.setMSentDt(bean.getMSentDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		model.setIclubMessageType(bean.getIclubMessageType());
		model.setMtLongDesc(bean.getMtLongDesc());
		model.setFromStLongDesc(bean.getFromStLongDesc());
		model.setToStLongDesc(bean.getToStLongDesc());
		model.setIclubSystemTypeByMFromSysId(bean.getIclubSystemTypeByMFromSysId());
		model.setIclubSystemTypeByMToSysId(bean.getIclubSystemTypeByMToSysId());

		return model;
	}

	public static IclubMessageModel fromORMtoWS(IclubMessage bean) {
		IclubMessageModel model = new IclubMessageModel();

		model.setMId(bean.getMId());
		model.setMContent(bean.getMContent());
		model.setMTranId(bean.getMTranId());
		model.setMCrtdDt(bean.getMCrtdDt());
		model.setMSentDt(bean.getMSentDt());
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() : null);
		model.setMtLongDesc(bean.getIclubMessageType() != null ? bean.getIclubMessageType().getMtLongDesc() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setIclubMessageType(bean.getIclubMessageType() != null ? bean.getIclubMessageType().getMtId() : null);
		model.setIclubSystemTypeByMFromSysId(bean.getIclubSystemTypeByMFromSysId() != null ? bean.getIclubSystemTypeByMFromSysId().getStId() : null);
		model.setIclubSystemTypeByMToSysId(bean.getIclubSystemTypeByMToSysId() != null ? bean.getIclubSystemTypeByMToSysId().getStId() : null);
		model.setFromStLongDesc(bean.getIclubSystemTypeByMToSysId() != null ? bean.getIclubSystemTypeByMToSysId().getStLongDesc() : null);
		model.setToStLongDesc(bean.getIclubSystemTypeByMToSysId() != null ? bean.getIclubSystemTypeByMToSysId().getStLongDesc() : null);

		return model;
	}

	public static IclubMessage fromWStoORM(IclubMessageModel model, IclubPersonDAO iclubPersonDAO, IclubMessageTypeDAO iclubMessageTypeDAO, IclubSystemTypeDAO iclubSystemTypeDAO) {

		IclubMessage bean = new IclubMessage();

		bean.setMId(model.getMId());
		bean.setMContent(model.getMContent());
		bean.setMTranId(model.getMTranId());
		bean.setMCrtdDt(model.getMCrtdDt());
		bean.setMSentDt(model.getMSentDt());
		bean.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		bean.setIclubMessageType(model.getIclubMessageType() != null ? iclubMessageTypeDAO.findById(model.getIclubMessageType()) : null);
		bean.setIclubSystemTypeByMFromSysId(model.getIclubSystemTypeByMFromSysId() != null ? iclubSystemTypeDAO.findById(model.getIclubSystemTypeByMFromSysId()) : null);
		bean.setIclubSystemTypeByMToSysId(model.getIclubSystemTypeByMToSysId() != null ? iclubSystemTypeDAO.findById(model.getIclubSystemTypeByMToSysId()) : null);

		return bean;
	}
}
