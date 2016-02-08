package za.co.iclub.pss.trans;

import za.co.iclub.pss.model.ui.IclubQuoteBean;
import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;

public class IclubQuoteTrans {

	public static IclubQuoteBean fromWStoUI(IclubQuoteModel model) {

		IclubQuoteBean bean = new IclubQuoteBean();

		bean.setQId(model.getQId());
		bean.setQCrtdDt(model.getQCrtdDt());
		bean.setQIsMatched(model.getQIsMatched());
		bean.setQPrevPremium(model.getQPrevPremium());
		bean.setQValidUntil(model.getQValidUntil());
		bean.setQMobile(model.getQMobile());
		bean.setQEmail(model.getQEmail());
		bean.setQGenPremium(model.getQGenPremium());
		bean.setQNumItems(model.getQNumItems());
		bean.setQGenDt(model.getQGenDt());
		bean.setQNumber(model.getQNumber());
		bean.setIclubPersonAByQCrtdBy(model.getIclubPersonAByQCrtdBy());
		bean.setIclubProductType(model.getIclubProductType());
		bean.setPtLongDesc(model.getPtLongDesc());
		bean.setIclubInsurerMaster(model.getIclubInsurerMaster());
		bean.setImRegNum(model.getImRegNum());
		bean.setIclubCoverType(model.getIclubCoverType());
		bean.setCtLongDesc(model.getCtLongDesc());
		bean.setIclubQuoteStatus(model.getIclubQuoteStatus());
		bean.setQsLongDesc(model.getQsLongDesc());
		bean.setIclubPersonBByQPersonId(model.getIclubPersonBByQPersonId());
		bean.setPBFNameAndLName(model.getPAFNameAndLName());
		bean.setPAFNameAndLName(model.getPBFNameAndLName());

		return bean;
	}

	public static IclubQuoteModel fromUItoWS(IclubQuoteBean bean) {

		IclubQuoteModel model = new IclubQuoteModel();

		model.setQId(bean.getQId());
		model.setQCrtdDt(bean.getQCrtdDt());
		model.setQIsMatched(bean.getQIsMatched());
		model.setQPrevPremium(bean.getQPrevPremium());
		model.setQValidUntil(bean.getQValidUntil());
		model.setQMobile(bean.getQMobile());
		model.setQEmail(bean.getQEmail());
		model.setQGenPremium(bean.getQGenPremium());
		model.setQNumItems(bean.getQNumItems());
		model.setQGenDt(bean.getQGenDt());
		model.setQNumber(bean.getQNumber());
		model.setIclubPersonAByQCrtdBy(bean.getIclubPersonAByQCrtdBy());
		model.setIclubProductType(bean.getIclubProductType());
		model.setPtLongDesc(bean.getPtLongDesc());
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster());
		model.setImRegNum(bean.getImRegNum());
		model.setIclubCoverType(bean.getIclubCoverType());
		model.setCtLongDesc(bean.getCtLongDesc());
		model.setIclubQuoteStatus(bean.getIclubQuoteStatus());
		model.setQsLongDesc(bean.getQsLongDesc());
		model.setIclubPersonBByQPersonId(bean.getIclubPersonBByQPersonId());
		model.setPBFNameAndLName(bean.getPAFNameAndLName());
		model.setPAFNameAndLName(bean.getPBFNameAndLName());

		return model;
	}

	public static IclubQuoteModel fromORMtoWS(IclubQuote bean) {

		IclubQuoteModel model = new IclubQuoteModel();

		model.setQId(bean.getQId());
		model.setQCrtdDt(bean.getQCrtdDt());
		model.setQIsMatched(bean.getQIsMatched());
		model.setQPrevPremium(bean.getQPrevPremium());
		model.setQValidUntil(bean.getQValidUntil());
		model.setQMobile(bean.getQMobile());
		model.setQEmail(bean.getQEmail());
		model.setQGenPremium(bean.getQGenPremium());
		model.setQNumItems(bean.getQNumItems());
		model.setQGenDt(bean.getQGenDt());
		model.setQNumber(bean.getQNumber());
		model.setIclubPersonAByQCrtdBy(bean.getIclubPersonByQCrtdBy() != null ? (bean.getIclubPersonByQCrtdBy().getPId()) : null);
		model.setIclubProductType(bean.getIclubProductType() != null ? (bean.getIclubProductType().getPtId()) : null);
		model.setIclubInsurerMaster(bean.getIclubInsurerMaster() != null ? (bean.getIclubInsurerMaster().getImId()) : null);
		model.setIclubCoverType(bean.getIclubCoverType() != null ? (bean.getIclubCoverType().getCtId()) : null);
		model.setIclubQuoteStatus(bean.getIclubQuoteStatus() != null ? (bean.getIclubQuoteStatus().getQsId()) : null);
		model.setIclubPersonBByQPersonId(bean.getIclubPersonByQPersonId() != null ? (bean.getIclubPersonByQPersonId().getPId()) : null);

		model.setPBFNameAndLName(bean.getIclubPersonByQPersonId() != null ? bean.getIclubPersonByQPersonId().getPFName() + " " + bean.getIclubPersonByQPersonId().getPLName() != null ? bean.getIclubPersonByQPersonId().getPLName() : "" : "");
		model.setPAFNameAndLName(bean.getIclubPersonByQCrtdBy() != null ? bean.getIclubPersonByQCrtdBy().getPFName() + " " + bean.getIclubPersonByQCrtdBy().getPLName() != null ? bean.getIclubPersonByQCrtdBy().getPLName() : "" : "");

		return model;
	}

	public static IclubQuote fromWStoORM(IclubQuoteModel model, IclubQuoteStatusDAO iclubQuoteStatusDAO, IclubCoverTypeDAO iclubCoverTypeDAO, IclubInsurerMasterDAO iclubInsurerMasterDAO, IclubProductTypeDAO iclubProductTypeDAO, IclubPersonDAO iclubPersonDAO) {

		IclubQuote bean = new IclubQuote();

		bean.setQId(model.getQId());
		bean.setQCrtdDt(model.getQCrtdDt());
		bean.setQIsMatched(model.getQIsMatched());
		bean.setQPrevPremium(model.getQPrevPremium());
		bean.setQValidUntil(model.getQValidUntil());
		bean.setQMobile(model.getQMobile());
		bean.setQEmail(model.getQEmail());
		bean.setQGenPremium(model.getQGenPremium());
		bean.setQNumItems(model.getQNumItems());
		bean.setQGenDt(model.getQGenDt());
		bean.setQNumber(model.getQNumber());
		bean.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ? iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) : null);
		bean.setIclubCoverType(model.getIclubCoverType() != null ? iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
		bean.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ? iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
		bean.setIclubProductType(model.getIclubProductType() != null ? iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
		bean.setIclubPersonByQPersonId(model.getIclubPersonBByQPersonId() != null && !model.getIclubPersonBByQPersonId().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonBByQPersonId()) : null);
		bean.setIclubPersonByQCrtdBy(model.getIclubPersonAByQCrtdBy() != null && !model.getIclubPersonAByQCrtdBy().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPersonAByQCrtdBy()) : null);

		return bean;
	}
}
