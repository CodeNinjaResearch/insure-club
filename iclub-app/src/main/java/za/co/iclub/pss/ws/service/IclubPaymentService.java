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

import za.co.iclub.pss.model.ws.IclubPaymentModel;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPaymentDAO;
import za.co.iclub.pss.orm.dao.IclubPaymentStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;
import za.co.iclub.pss.trans.IclubPaymentTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPaymentService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPaymentService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubPaymentService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPolicyDAO iclubPolicyDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubClaimDAO iclubClaimDAO;
	private IclubAccountDAO iclubAccountDAO;
	private IclubPaymentStatusDAO iclubPaymentStatusDAO;
	private IclubPaymentDAO iclubPaymentDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPaymentModel model) {
		try {
			IclubPayment iCP = IclubPaymentTrans.fromWStoORM(model, iclubPolicyDAO, iclubPersonDAO, iclubClaimDAO, iclubAccountDAO, iclubPaymentStatusDAO);
			
			iclubPaymentDAO.save(iCP);
			
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
	public ResponseModel mod(IclubPaymentModel model) {
		try {
			
			IclubPayment iCP = IclubPaymentTrans.fromWStoORM(model, iclubPolicyDAO, iclubPersonDAO, iclubClaimDAO, iclubAccountDAO, iclubPaymentStatusDAO);
			
			iclubPaymentDAO.merge(iCP);
			
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
			iclubPaymentDAO.delete(iclubPaymentDAO.findById(id));
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
	public <T extends IclubPaymentModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubPaymentDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPayment bean = (IclubPayment) object;
					
					IclubPaymentModel model = IclubPaymentTrans.fromORMtoWS(bean);
					
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
	public <T extends IclubPaymentModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPayment.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPayment bean = (IclubPayment) object;
					
					IclubPaymentModel model = IclubPaymentTrans.fromORMtoWS(bean);
					
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
	public IclubPaymentModel getById(@PathParam("id") String id) {
		IclubPaymentModel model = new IclubPaymentModel();
		try {
			IclubPayment bean = iclubPaymentDAO.findById(id);
			
			model = IclubPaymentTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubPolicyDAO getIclubPolicyDAO() {
		return iclubPolicyDAO;
	}
	
	public void setIclubPolicyDAO(IclubPolicyDAO iclubPolicyDAO) {
		this.iclubPolicyDAO = iclubPolicyDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubClaimDAO getIclubClaimDAO() {
		return iclubClaimDAO;
	}
	
	public void setIclubClaimDAO(IclubClaimDAO iclubClaimDAO) {
		this.iclubClaimDAO = iclubClaimDAO;
	}
	
	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}
	
	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}
	
	public IclubPaymentStatusDAO getIclubPaymentStatusDAO() {
		return iclubPaymentStatusDAO;
	}
	
	public void setIclubPaymentStatusDAO(IclubPaymentStatusDAO iclubPaymentStatusDAO) {
		this.iclubPaymentStatusDAO = iclubPaymentStatusDAO;
	}
	
	public IclubPaymentDAO getIclubPaymentDAO() {
		return iclubPaymentDAO;
	}
	
	public void setIclubPaymentDAO(IclubPaymentDAO iclubPaymentDAO) {
		this.iclubPaymentDAO = iclubPaymentDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
