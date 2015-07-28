package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubPersonModel;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubPersonTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path("/IclubPersonService")
public class IclubPersonService {
	protected static final Logger LOGGER = Logger.getLogger(IclubPersonService.class);
	private IclubPersonDAO iclubPersonDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubIdTypeDAO iclubIdTypeDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortDAO iclubCohortDAO;
	
	@POST
	@Path("/add")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPersonModel model) {
		try {
			IclubPerson person = IclubPersonTrans.fromWStoORM(model, iclubIdTypeDAO, iclubPersonDAO, iclubMaritialStatusDAO, iclubCohortDAO);
			
			iclubPersonDAO.save(person);
			
			LOGGER.info("Save Success with ID :: " + person.getPId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}
	
	@PUT
	@Path("/mod")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPersonModel model) {
		try {
			IclubPerson person = IclubPersonTrans.fromWStoORM(model, iclubIdTypeDAO, iclubPersonDAO, iclubMaritialStatusDAO, iclubCohortDAO);
			iclubPersonDAO.merge(person);
			
			LOGGER.info("Merge Success with ID :: " + model.getPId());
			
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}
	
	@GET
	@Path("/del/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPersonDAO.delete(iclubPersonDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubPersonDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPerson bean = (IclubPerson) object;
					
					IclubPersonModel model = IclubPersonTrans.fromORMtoWS(bean);
					
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubPerson.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubPerson bean = (IclubPerson) object;
					
					IclubPersonModel model = IclubPersonTrans.fromORMtoWS(bean);
					
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
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPersonModel getById(@PathParam("id") String id) {
		IclubPersonModel model = new IclubPersonModel();
		try {
			IclubPerson bean = iclubPersonDAO.findById(id);
			
			model = IclubPersonTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("/getMNumberList")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> getAllExistingMobileNmbers(Collection<? extends String> models) {
		List<T> ret = new ArrayList<T>();
		try {
			
			List batmod = iclubNamedQueryDAO.getIclubPersonNumbersList(models);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					String reDetails = (String) object;
					ret.add((T) reDetails);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@POST
	@Path("/getEmailsList")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> getAllEmails(Collection<? extends String> models) {
		List<T> ret = new ArrayList<T>();
		try {
			
			List batmod = iclubNamedQueryDAO.getIclubPersonEmailsList(models);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					String reDetails = (String) object;
					ret.add((T) reDetails);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}
	
	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}
	
	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubIdTypeDAO getIclubIdTypeDAO() {
		return iclubIdTypeDAO;
	}
	
	public void setIclubIdTypeDAO(IclubIdTypeDAO iclubIdTypeDAO) {
		this.iclubIdTypeDAO = iclubIdTypeDAO;
	}
	
	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}
	
	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
	public IclubCohortDAO getIclubCohortDAO() {
		return iclubCohortDAO;
	}
	
	public void setIclubCohortDAO(IclubCohortDAO iclubCohortDAO) {
		this.iclubCohortDAO = iclubCohortDAO;
	}
	
}
