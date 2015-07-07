package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubSupplPersonBean;
import za.co.iclub.pss.model.ws.IclubSupplPersonModel;
import za.co.iclub.pss.orm.bean.IclubSupplPerson;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;

public class IclubSupplPersonTrans {
	
	public static IclubSupplPersonBean fromWStoUI(IclubSupplPersonModel model) {
		
		IclubSupplPersonBean bean = new IclubSupplPersonBean();
		
		bean.setSpId(model.getSpId());
		bean.setSpCrtdDt(model.getSpCrtdDt());
		bean.setSmRegNum(model.getSmRegNum());
		bean.setIclubSupplMaster(model.getIclubSupplMaster());
		bean.setIclubPersonABySpCrtdBy(model.getIclubPersonABySpCrtdBy());
		bean.setPAFNameAndLName(model.getPAFNameAndLName());
		bean.setIclubPersonBBySpPersonId(model.getIclubPersonBBySpPersonId());
		bean.setPBFNameAndLName(model.getPBFNameAndLName());
		
		return bean;
	}
	
	public static IclubSupplPersonModel fromUItoWS(IclubSupplPersonBean bean) {
		IclubSupplPersonModel model = new IclubSupplPersonModel();
		
		model.setSpId(bean.getSpId());
		model.setSpCrtdDt(bean.getSpCrtdDt());
		model.setSmRegNum(bean.getSmRegNum());
		model.setIclubSupplMaster(bean.getIclubSupplMaster());
		model.setIclubPersonABySpCrtdBy(bean.getIclubPersonABySpCrtdBy());
		model.setPAFNameAndLName(bean.getPAFNameAndLName());
		model.setIclubPersonBBySpPersonId(bean.getIclubPersonBBySpPersonId());
		model.setPBFNameAndLName(bean.getPBFNameAndLName());
		
		return model;
	}
	
	public static IclubSupplPersonModel fromORMtoWS(IclubSupplPerson bean) {
		
		IclubSupplPersonModel model = new IclubSupplPersonModel();
		
		model.setSpId(bean.getSpId());
		model.setSpCrtdDt(bean.getSpCrtdDt());
		model.setIclubSupplMaster(bean.getIclubSupplMaster() != null ? bean.getIclubSupplMaster().getSmId() : null);
		model.setSmRegNum(bean.getIclubSupplMaster() != null ? bean.getIclubSupplMaster().getSmRegNum() : null);
		model.setIclubPersonABySpCrtdBy(bean.getIclubPersonBySpCrtdBy() != null ? bean.getIclubPersonBySpCrtdBy().getPId() : null);
		model.setPAFNameAndLName(bean.getIclubPersonBySpCrtdBy() != null ? bean.getIclubPersonBySpCrtdBy().getPFName() + " " + bean.getIclubPersonBySpCrtdBy().getPLName() != null ? bean.getIclubPersonBySpCrtdBy().getPLName() : "" : "");
		model.setIclubPersonBBySpPersonId(bean.getIclubPersonBySpPersonId() != null ? bean.getIclubPersonBySpPersonId().getPId() : null);
		model.setPBFNameAndLName(bean.getIclubPersonBySpPersonId() != null ? bean.getIclubPersonBySpPersonId().getPFName() + " " + bean.getIclubPersonBySpPersonId().getPLName() != null ? bean.getIclubPersonBySpPersonId().getPLName() : "" : "");
		
		return model;
	}
	
	public static IclubSupplPerson fromWStoORM(IclubSupplPersonModel model, IclubPersonDAO iclubPersonDAO, IclubSupplMasterDAO iclubSupplMasterDAO) {
		
		IclubSupplPerson bean = new IclubSupplPerson();
		
		bean.setSpId(model.getSpId());
		bean.setSpCrtdDt(model.getSpCrtdDt());
		bean.setIclubSupplMaster(model.getIclubSupplMaster() != null ? iclubSupplMasterDAO.findById(model.getIclubSupplMaster()) : null);
		bean.setIclubPersonBySpCrtdBy(model.getIclubPersonABySpCrtdBy() != null ? iclubPersonDAO.findById(model.getIclubPersonABySpCrtdBy()) : null);
		bean.setIclubPersonBySpPersonId(model.getIclubPersonBBySpPersonId() != null ? iclubPersonDAO.findById(model.getIclubPersonBBySpPersonId()) : null);
		
		return bean;
	}
}
