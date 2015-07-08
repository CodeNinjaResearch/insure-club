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

import za.co.iclub.pss.model.ws.IclubPolicyModel;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyStatusDAO;
import za.co.iclub.pss.orm.dao.IclubQuoteDAO;
import za.co.iclub.pss.trans.IclubPolicyTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPolicyService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPolicyService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPolicyDAO iclubPolicyDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubPolicyStatusDAO iclubPolicyStatusDAO;
	private IclubQuoteDAO iclubQuoteDAO;
	private IclubAccountDAO iclubAccountDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPolicyModel model) {
		try {
			IclubPolicy iCP = IclubPolicyTrans.fromWStoORM(model, iclubPersonDAO, iclubPolicyStatusDAO, iclubQuoteDAO, iclubAccountDAO);
			
			iclubPolicyDAO.save(iCP);
			
			LOGGER.info("Save Success with ID :: " + iCP.getPId());
			
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
	public ResponseModel mod(IclubPolicyModel model) {
		try {
			IclubPolicy iCP = IclubPolicyTrans.fromWStoORM(model, iclubPersonDAO, iclubPolicyStatusDAO, iclubQuoteDAO, iclubAccountDAO);
			
			iclubPolicyDAO.merge(iCP);
			
			LOGGER.info("Merge Success with ID :: " + model.getPId());
			
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
			iclubPolicyDAO.delete(iclubPolicyDAO.findById(id));
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
	public <T extends IclubPolicyModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubPolicyDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy bean = (IclubPolicy) object;
					
					IclubPolicyModel model = IclubPolicyTrans.fromORMtoWS(bean);
					
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
	public <T extends IclubPolicyModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPolicy.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy bean = (IclubPolicy) object;
					
					IclubPolicyModel model = IclubPolicyTrans.fromORMtoWS(bean);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/policyStauts/{statusId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPolicyModel> List<T> getByPolicyStatus(@PathParam("statusId") String statusId) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.getIclubPolicyByPolicyStatus(statusId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy bean = (IclubPolicy) object;
					
					IclubPolicyModel model = IclubPolicyTrans.fromORMtoWS(bean);
					
					ret.add((T) model);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/get/createdate")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPolicyModel> List<T> getOrderByCreatedDate() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.getIclubPolicies();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPolicy bean = (IclubPolicy) object;
					
					IclubPolicyModel model = IclubPolicyTrans.fromORMtoWS(bean);
					
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
	public IclubPolicyModel getById(@PathParam("id") String id) {
		IclubPolicyModel model = new IclubPolicyModel();
		try {
			IclubPolicy bean = iclubPolicyDAO.findById(id);
			
			model = IclubPolicyTrans.fromORMtoWS(bean);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@GET
	@Path("/getByQuoteId/{quoteId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPolicyModel getByQuoteIdId(@PathParam("quoteId") String quoteId) {
		IclubPolicyModel model = new IclubPolicyModel();
		try {
			IclubPolicy bean = iclubNamedQueryDAO.findIclubPlolicyByQuoteId(quoteId);
			
			if (bean != null) {
				model = IclubPolicyTrans.fromORMtoWS(bean);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubPolicyDAO getIclubPolicyDAO() {
		return iclubPolicyDAO;
	}
	
	public void setIclubPolicyDAO(IclubPolicyDAO iclubPolicyDAO) {
		this.iclubPolicyDAO = iclubPolicyDAO;
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
	
	public IclubPolicyStatusDAO getIclubPolicyStatusDAO() {
		return iclubPolicyStatusDAO;
	}
	
	public void setIclubPolicyStatusDAO(IclubPolicyStatusDAO iclubPolicyStatusDAO) {
		this.iclubPolicyStatusDAO = iclubPolicyStatusDAO;
	}
	
	public IclubQuoteDAO getIclubQuoteDAO() {
		return iclubQuoteDAO;
	}
	
	public void setIclubQuoteDAO(IclubQuoteDAO iclubQuoteDAO) {
		this.iclubQuoteDAO = iclubQuoteDAO;
	}
	
	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}
	
	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
