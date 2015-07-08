package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMessageBoardBean;
import za.co.iclub.pss.model.ws.IclubMessageBoardModel;
import za.co.iclub.pss.orm.bean.IclubMessageBoard;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubMessageBoardTrans {
	
	public static IclubMessageBoardBean fromWStoUI(IclubMessageBoardModel model) {
		
		IclubMessageBoardBean bean = new IclubMessageBoardBean();
		
		model.setMbId(bean.getMbId());
		model.setMbContent(bean.getMbContent());
		model.setMbContent(bean.getMbContent());
		model.setMbTag(bean.getMbTag());
		model.setMbTitle(bean.getMbTitle());
		model.setMbCrtdDt(bean.getMbCrtdDt());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubMessageBoardModel fromUItoWS(IclubMessageBoardBean bean) {
		
		IclubMessageBoardModel model = new IclubMessageBoardModel();
		
		model.setMbId(bean.getMbId());
		model.setMbContent(bean.getMbContent());
		model.setMbContent(bean.getMbContent());
		model.setMbTag(bean.getMbTag());
		model.setMbTitle(bean.getMbTitle());
		model.setMbCrtdDt(bean.getMbCrtdDt());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubMessageBoardModel fromORMtoWS(IclubMessageBoard bean) {
		
		IclubMessageBoardModel model = new IclubMessageBoardModel();
		
		model.setMbId(bean.getMbId());
		model.setMbContent(bean.getMbContent());
		model.setMbContent(bean.getMbContent());
		model.setMbTag(bean.getMbTag());
		model.setMbTitle(bean.getMbTitle());
		model.setMbCrtdDt(bean.getMbCrtdDt());
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubMessageBoard fromWStoORM(IclubMessageBoardModel model, IclubPersonDAO iclubPersonDAO) {
		
		IclubMessageBoard bean = new IclubMessageBoard();
		
		bean.setMbId(model.getMbId());
		bean.setMbContent(model.getMbContent());
		bean.setMbContent(model.getMbContent());
		bean.setMbTag(model.getMbTag());
		bean.setMbTitle(model.getMbTitle());
		bean.setMbCrtdDt(model.getMbCrtdDt());
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
}
