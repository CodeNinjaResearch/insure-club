package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubMbCommentBean;
import za.co.iclub.pss.model.ws.IclubMbCommentModel;
import za.co.iclub.pss.orm.bean.IclubMbComment;
import za.co.iclub.pss.orm.dao.IclubMessageBoardDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;

public class IclubMbCommentTrans {
	
	public static IclubMbCommentBean fromWStoUI(IclubMbCommentModel model) {
		
		IclubMbCommentBean bean = new IclubMbCommentBean();
		
		bean.setMbcId(model.getMbcId());
		bean.setMbcCrtdDt(model.getMbcCrtdDt());
		bean.setMbcDesc(model.getMbcDesc());
		bean.setIclubMessageBoard(model.getIclubMessageBoard());
		bean.setIclubPerson(model.getIclubPerson());
		bean.setPFNameAndLName(model.getPFNameAndLName());
		
		return bean;
	}
	
	public static IclubMbCommentModel fromUItoWS(IclubMbCommentBean bean) {
		
		IclubMbCommentModel model = new IclubMbCommentModel();
		
		model.setMbcId(bean.getMbcId());
		model.setMbcCrtdDt(bean.getMbcCrtdDt());
		model.setMbcDesc(bean.getMbcDesc());
		model.setIclubMessageBoard(bean.getIclubMessageBoard());
		model.setIclubPerson(bean.getIclubPerson());
		model.setPFNameAndLName(bean.getPFNameAndLName());
		
		return model;
	}
	
	public static IclubMbCommentModel fromORMtoWS(IclubMbComment bean) {
		
		IclubMbCommentModel model = new IclubMbCommentModel();
		
		model.setMbcId(bean.getMbcId());
		model.setMbcCrtdDt(bean.getMbcCrtdDt());
		model.setMbcDesc(bean.getMbcDesc());
		model.setIclubMessageBoard(bean.getIclubMessageBoard() != null ? bean.getIclubMessageBoard().getMbId() : null);
		model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
		model.setPFNameAndLName(bean.getIclubPerson() != null ? bean.getIclubPerson().getPFName() + " " + bean.getIclubPerson().getPLName() != null ? bean.getIclubPerson().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubMbComment fromWStoORM(IclubMbCommentModel model, IclubPersonDAO iclubPersonDAO, IclubMessageBoardDAO iclubMessageBoardDAO) {
		
		IclubMbComment bean = new IclubMbComment();
		
		bean.setMbcId(model.getMbcId());
		bean.setMbcDesc(model.getMbcDesc());
		bean.setMbcCrtdDt(model.getMbcCrtdDt());
		bean.setIclubMessageBoard(model.getIclubMessageBoard() != null && !model.getIclubMessageBoard().trim().equalsIgnoreCase("") ? iclubMessageBoardDAO.findById(model.getIclubMessageBoard().trim()) : null);
		bean.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
		
		return bean;
	}
}
