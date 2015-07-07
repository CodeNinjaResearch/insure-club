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

import za.co.iclub.pss.model.ws.IclubAccountModel;
import za.co.iclub.pss.orm.bean.IclubAccount;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubAccountTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubOwnerTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubAccountTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAccountService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAccountService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubAccountService.class);
	private IclubAccountDAO iclubAccountDAO;
	private IclubOwnerTypeDAO iclubOwnerTypeDAO;
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubAccountTypeDAO iclubAccountTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubAccountModel model) {
		try {
			
			IclubAccount iCA = IclubAccountTrans.fromWStoORM(model, iclubBankMasterDAO, iclubAccountTypeDAO, iclubOwnerTypeDAO, iclubPersonDAO);
			
			iclubAccountDAO.save(iCA);
			
			LOGGER.info("Save Success with ID :: " + iCA.getAId());
			
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
	@Transactional
	public ResponseModel mod(IclubAccountModel model) {
		try {
			IclubAccount iCA = IclubAccountTrans.fromWStoORM(model, iclubBankMasterDAO, iclubAccountTypeDAO, iclubOwnerTypeDAO, iclubPersonDAO);
			
			iclubAccountDAO.merge(iCA);
			
			LOGGER.info("Save Success with ID :: " + model.getAId());
			
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
	@Transactional
	public Response del(@PathParam("id") String id) {
		try {
			iclubAccountDAO.delete(iclubAccountDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubAccountModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubAccountDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubAccount bean = (IclubAccount) object;
					IclubAccountModel iCA = IclubAccountTrans.fromORMtoWS(bean);
					ret.add((T) iCA);
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
	public <T extends IclubAccountModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubAccount.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubAccount bean = (IclubAccount) object;
					IclubAccountModel iCA = IclubAccountTrans.fromORMtoWS(bean);
					ret.add((T) iCA);
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
	@Transactional
	public IclubAccountModel getById(@PathParam("id") String id) {
		IclubAccountModel model = new IclubAccountModel();
		try {
			IclubAccount bean = iclubAccountDAO.findById(id);
			
			model = IclubAccountTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}
	
	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}
	
	public IclubOwnerTypeDAO getIclubOwnerTypeDAO() {
		return iclubOwnerTypeDAO;
	}
	
	public void setIclubOwnerTypeDAO(IclubOwnerTypeDAO iclubOwnerTypeDAO) {
		this.iclubOwnerTypeDAO = iclubOwnerTypeDAO;
	}
	
	public IclubBankMasterDAO getIclubBankMasterDAO() {
		return iclubBankMasterDAO;
	}
	
	public void setIclubBankMasterDAO(IclubBankMasterDAO iclubBankMasterDAO) {
		this.iclubBankMasterDAO = iclubBankMasterDAO;
	}
	
	public IclubAccountTypeDAO getIclubAccountTypeDAO() {
		return iclubAccountTypeDAO;
	}
	
	public void setIclubAccountTypeDAO(IclubAccountTypeDAO iclubAccountTypeDAO) {
		this.iclubAccountTypeDAO = iclubAccountTypeDAO;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
