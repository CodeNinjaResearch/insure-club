package za.co.iclub.pss.ws.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;
import za.co.iclub.pss.ws.model.IclubFullQuoteRequest;
import za.co.iclub.pss.ws.model.IclubFullQuoteResponse;

@Path(value = "/IclubFullQuoteService")
public class IclubFullQuoteService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubFullQuoteService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubQuoteStatusDAO iclubQuoteStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/createQuote")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubFullQuoteResponse createQuote(IclubFullQuoteRequest request) {
		// try {
		//
		// Double generatedPremium = 0.0;
		// String quoteNumber = "";
		// IclubQuote iCQ = new IclubQuote();
		//
		// iCQ.setQId(model.getQId());
		// iCQ.setQCrtdDt(model.getQCrtdDt());
		// iCQ.setQIsMatched(model.getQIsMatched());
		// iCQ.setQPrevPremium(model.getQPrevPremium());
		// iCQ.setQValidUntil(model.getQValidUntil());
		// iCQ.setQMobile(model.getQMobile());
		// iCQ.setQEmail(model.getQEmail());
		// iCQ.setQGenPremium(model.getQGenPremium());
		// iCQ.setQNumItems(model.getQNumItems());
		// iCQ.setQGenDt(model.getQGenDt());
		// iCQ.setQNumber(model.getQNumber());
		// iCQ.setIclubInsurerMaster(model.getIclubInsurerMaster() != null ?
		// iclubInsurerMasterDAO.findById(model.getIclubInsurerMaster()) :
		// null);
		// iCQ.setIclubCoverType(model.getIclubCoverType() != null ?
		// iclubCoverTypeDAO.findById(model.getIclubCoverType()) : null);
		// iCQ.setIclubQuoteStatus(model.getIclubQuoteStatus() != null ?
		// iclubQuoteStatusDAO.findById(model.getIclubQuoteStatus()) : null);
		// iCQ.setIclubProductType(model.getIclubProductType() != null ?
		// iclubProductTypeDAO.findById(model.getIclubProductType()) : null);
		// iCQ.setIclubPersonByQPersonId(model.getIclubPersonByQPersonId() !=
		// null &&
		// !model.getIclubPersonByQPersonId().trim().equalsIgnoreCase("") ?
		// iclubPersonDAO.findById(model.getIclubPersonByQPersonId()) : null);
		// iCQ.setIclubPersonByQCrtdBy(model.getIclubPersonByQCrtdBy() != null
		// && !model.getIclubPersonByQCrtdBy().trim().equalsIgnoreCase("") ?
		// iclubPersonDAO.findById(model.getIclubPersonByQCrtdBy()) : null);
		//
		// iclubQuoteDAO.save(iCQ);
		//
		// LOGGER.info("Save Success with ID :: " + iCQ.getQId());
		//
		// IclubQuickQuoteResponse message = new IclubQuickQuoteResponse();
		// message.setGeneratedPremium(generatedPremium);
		// message.setQuoteNumber(quoteNumber);
		// return message;
		// } catch (Exception e) {
		// LOGGER.error(e, e);
		// ResponseModel message = new ResponseModel();
		// message.setStatusCode(1);
		// message.setStatusDesc(e.getMessage());
		// return message;
		// }
		return new IclubFullQuoteResponse();
	}
	
	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}
	
	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubProductTypeDAO getIclubProductTypeDAO() {
		return iclubProductTypeDAO;
	}
	
	public void setIclubProductTypeDAO(IclubProductTypeDAO iclubProductTypeDAO) {
		this.iclubProductTypeDAO = iclubProductTypeDAO;
	}
	
	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}
	
	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
	}
	
	public IclubCoverTypeDAO getIclubCoverTypeDAO() {
		return iclubCoverTypeDAO;
	}
	
	public void setIclubCoverTypeDAO(IclubCoverTypeDAO iclubCoverTypeDAO) {
		this.iclubCoverTypeDAO = iclubCoverTypeDAO;
	}
	
	public IclubQuoteStatusDAO getIclubQuoteStatusDAO() {
		return iclubQuoteStatusDAO;
	}
	
	public void setIclubQuoteStatusDAO(IclubQuoteStatusDAO iclubQuoteStatusDAO) {
		this.iclubQuoteStatusDAO = iclubQuoteStatusDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
