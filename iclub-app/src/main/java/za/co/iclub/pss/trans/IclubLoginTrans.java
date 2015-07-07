package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubLoginBean;
import za.co.iclub.pss.model.ws.IclubLoginModel;
import za.co.iclub.pss.orm.bean.IclubLogin;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubRoleTypeDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityQuestionDAO;

public class IclubLoginTrans {
	
	private IclubPersonDAO iclubPersonDAO;
	private IclubSecurityQuestionDAO iclubSecurityQuestionDAO;
	private IclubRoleTypeDAO iclubRoleTypeDAO;
	
	public IclubLoginBean fromWStoUI(IclubLoginModel model) {
		IclubLoginBean bean = new IclubLoginBean();
		
		bean.setLId(model.getLId());
		bean.setLCrtdDt(model.getLCrtdDt());
		bean.setLLastDate(model.getLLastDate());
		bean.setLName(model.getLName());
		bean.setLPasswd(model.getLPasswd());
		bean.setLSecAns(model.getLSecAns());
		bean.setLSecAns(model.getLSecAns());
		bean.setIclubRoleType(model.getIclubRoleType());
		bean.setIclubSecurityQuestion(model.getIclubSecurityQuestion());
		bean.setIclubPersonAByLCrtdBy(model.getIclubPersonAByLCrtdBy());
		bean.setPAFNameAndLName(model.getPAFNameAndLName());
		bean.setIclubPersonBByLPersonId(model.getIclubPersonBByLPersonId());
		bean.setPBFNameAndLName(model.getPBFNameAndLName());
		
		return bean;
	}
	
	public IclubLoginModel fromUItoWS(IclubLoginBean bean) {
		IclubLoginModel model = new IclubLoginModel();
		
		model.setLId(bean.getLId());
		model.setLCrtdDt(bean.getLCrtdDt());
		model.setLLastDate(bean.getLLastDate());
		model.setLName(bean.getLName());
		model.setLPasswd(bean.getLPasswd());
		model.setLSecAns(bean.getLSecAns());
		model.setLSecAns(bean.getLSecAns());
		model.setIclubRoleType(bean.getIclubRoleType());
		model.setIclubSecurityQuestion(bean.getIclubSecurityQuestion());
		model.setIclubPersonAByLCrtdBy(bean.getIclubPersonAByLCrtdBy());
		model.setPAFNameAndLName(bean.getPAFNameAndLName());
		model.setIclubPersonBByLPersonId(bean.getIclubPersonBByLPersonId());
		model.setPBFNameAndLName(bean.getPBFNameAndLName());
		
		return model;
	}
	
	public IclubLoginModel fromORMtoWS(IclubLogin bean) {
		IclubLoginModel model = new IclubLoginModel();
		
		model.setLId(bean.getLId());
		model.setLCrtdDt(bean.getLCrtdDt());
		model.setLLastDate(bean.getLLastDate());
		model.setLName(bean.getLName());
		model.setLPasswd(bean.getLPasswd());
		model.setLSecAns(bean.getLSecAns());
		model.setLSecAns(bean.getLSecAns());
		model.setIclubRoleType(bean.getIclubRoleType() != null ? bean.getIclubRoleType().getRtId() : null);
		model.setIclubSecurityQuestion(bean.getIclubSecurityQuestion() != null ? bean.getIclubSecurityQuestion().getSqId() : null);
		model.setIclubPersonAByLCrtdBy(bean.getIclubPersonByLCrtdBy() != null ? bean.getIclubPersonByLCrtdBy().getPId() : null);
		model.setPAFNameAndLName(bean.getIclubPersonByLCrtdBy() != null ? bean.getIclubPersonByLCrtdBy().getPFName() + " " + bean.getIclubPersonByLCrtdBy().getPLName() != null ? bean.getIclubPersonByLCrtdBy().getPLName() : "" : "");
		model.setIclubPersonBByLPersonId(bean.getIclubPersonByLPersonId() != null ? bean.getIclubPersonByLPersonId().getPId() : null);
		model.setPBFNameAndLName(bean.getIclubPersonByLPersonId() != null ? bean.getIclubPersonByLPersonId().getPFName() + " " + bean.getIclubPersonByLPersonId().getPLName() != null ? bean.getIclubPersonByLPersonId().getPLName() : "" : "");
		
		return model;
	}
	
	public IclubLogin fromWStoORM(IclubLoginModel model) {
		
		IclubLogin bean = new IclubLogin();
		
		bean.setLId(model.getLId());
		bean.setLCrtdDt(model.getLCrtdDt());
		bean.setLLastDate(model.getLLastDate());
		bean.setLName(model.getLName());
		bean.setLPasswd(model.getLPasswd());
		bean.setLSecAns(model.getLSecAns());
		bean.setIclubPersonByLCrtdBy(model.getIclubPersonAByLCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonAByLCrtdBy()) : null);
		bean.setIclubPersonByLPersonId(model.getIclubPersonBByLPersonId() != null ? iclubPersonDAO.findById(model.getIclubPersonBByLPersonId()) : null);
		bean.setIclubRoleType(model.getIclubRoleType() != null ? iclubRoleTypeDAO.findById(model.getIclubRoleType()) : null);
		bean.setIclubSecurityQuestion(model.getIclubSecurityQuestion() != null ? iclubSecurityQuestionDAO.findById(model.getIclubSecurityQuestion()) : null);
		
		return bean;
	}
}
