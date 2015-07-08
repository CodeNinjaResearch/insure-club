package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubQuoteModel;
import za.co.iclub.pss.orm.bean.IclubQuote;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubCoverTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteStatusDAO;
import za.co.iclub.pss.trans.IclubQuoteTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubQuoteService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubQuoteService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubQuoteService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCoverTypeDAO iclubCoverTypeDAO;
	private IclubQuoteStatusDAO iclubQuoteStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = IclubQuoteTrans.fromWStoORM(model, iclubQuoteStatusDAO, iclubCoverTypeDAO, iclubInsurerMasterDAO, iclubProductTypeDAO, iclubPersonDAO);
			
			iclubQuoteDAO.save(iCQ);
			
			LOGGER.info("Save Success with ID :: " + iCQ.getQId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubQuoteModel model) {
		try {
			IclubQuote iCQ = IclubQuoteTrans.fromWStoORM(model, iclubQuoteStatusDAO, iclubCoverTypeDAO, iclubInsurerMasterDAO, iclubProductTypeDAO, iclubPersonDAO);
			iclubQuoteDAO.merge(iCQ);
			
			LOGGER.info("Merge Success with ID :: " + model.getQId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(0);
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(1);
			message.setStatusDesc(e.getMessage());
			return message;
		}
		
	}
	
	@GET
	@Path("/del/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubQuoteDAO.delete(iclubQuoteDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubQuoteDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote bean = (IclubQuote) object;
					
					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/user/{user}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubQuote.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote bean = (IclubQuote) object;
					
					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/userstatusId/{user}/{statusId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubQuoteModel> List<T> getByUserAndStatus(@PathParam("user") String user, @PathParam("statusId") Long statusId) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findIclubQuotesByUserAndStatusId(user, statusId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubQuote bean = (IclubQuote) object;
					
					IclubQuoteModel model = IclubQuoteTrans.fromORMtoWS(bean);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubQuoteModel getById(@PathParam("id") String id) {
		IclubQuoteModel model = new IclubQuoteModel();
		try {
			IclubQuote bean = iclubQuoteDAO.findById(id);
			
			model = IclubQuoteTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
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
