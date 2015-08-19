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
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubCohortInviteModel;
import za.co.iclub.pss.orm.bean.IclubCohortInvite;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInviteStatusDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubCohortInviteTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortInviteService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortInviteService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubCohortInviteService.class);
	private IclubCohortInviteDAO iclubCohortInviteDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortDAO iclubCohortDAO;
	private IclubNotificationTypeDAO iclubNotificationTypeDAO;
	private IclubInviteStatusDAO iclubInviteStatusDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortInviteModel model) {
		try {
			
			IclubCohortInvite iCC = IclubCohortInviteTrans.fromWStoORM(model, iclubNotificationTypeDAO, iclubCohortDAO, iclubPersonDAO, iclubInviteStatusDAO);
			iclubCohortInviteDAO.save(iCC);
			
			LOGGER.info("Save Success with ID :: " + iCC.getCiId());
			
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
	
	@POST
	@Path("/addList")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel addList(Collection<? extends IclubCohortInviteModel> models) {
		try {
			
			for (IclubCohortInviteModel model : models) {
				
				IclubCohortInvite iCC = IclubCohortInviteTrans.fromWStoORM(model, iclubNotificationTypeDAO, iclubCohortDAO, iclubPersonDAO, iclubInviteStatusDAO);
				iclubCohortInviteDAO.save(iCC);
				
				LOGGER.info("Save Success with ID :: " + iCC.getCiId());
			}
			
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
	
	@POST
	@Path("/modList")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel modList(Collection<? extends IclubCohortInviteModel> models) {
		try {
			
			for (IclubCohortInviteModel model : models) {
				
				IclubCohortInvite iCC = IclubCohortInviteTrans.fromWStoORM(model, iclubNotificationTypeDAO, iclubCohortDAO, iclubPersonDAO, iclubInviteStatusDAO);
				iclubCohortInviteDAO.merge(iCC);
				
				LOGGER.info("Save Success with ID :: " + model.getCiId());
			}
			
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
	public ResponseModel mod(IclubCohortInviteModel model) {
		try {
			IclubCohortInvite iCC = IclubCohortInviteTrans.fromWStoORM(model, iclubNotificationTypeDAO, iclubCohortDAO, iclubPersonDAO, iclubInviteStatusDAO);
			
			iclubCohortInviteDAO.merge(iCC);
			
			LOGGER.info("Save Success with ID :: " + model.getCiId());
			
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
			iclubCohortInviteDAO.delete(iclubCohortInviteDAO.findById(id));
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
	public <T extends IclubCohortInviteModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubCohortInviteDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortInvite bean = (IclubCohortInvite) object;
					IclubCohortInviteModel iCC = IclubCohortInviteTrans.fromORMtoWS(bean);
					
					ret.add((T) iCC);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/admin/list/{user}")
	@Produces("application/json")
	@Transactional
	public <T extends IclubCohortInviteModel> List<T> adminList(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List<Long> inviteIds = new ArrayList<Long>(2);
			inviteIds.add(1l);
			inviteIds.add(2l);
			List batmod = iclubNamedQueryDAO.getIclubCohortInvitesByinviteStatusIds(inviteIds, user);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortInvite bean = (IclubCohortInvite) object;
					IclubCohortInviteModel iCC = IclubCohortInviteTrans.fromORMtoWS(bean);
					
					ret.add((T) iCC);
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
	public <T extends IclubCohortInviteModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohortInvite.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortInvite bean = (IclubCohortInvite) object;
					IclubCohortInviteModel iCC = IclubCohortInviteTrans.fromORMtoWS(bean);
					ret.add((T) iCC);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}
	
	@POST
	@Path("/getInvitiesList")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> getIclubCohoretInvitesEmailsList(Collection<? extends String> models) {
		List<T> ret = new ArrayList<T>();
		try {
			
			List batmod = iclubNamedQueryDAO.getIclubCohoretInvitesEmailsList(models);
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
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional
	public IclubCohortInviteModel getById(@PathParam("id") String id) {
		IclubCohortInviteModel model = new IclubCohortInviteModel();
		try {
			IclubCohortInvite bean = iclubCohortInviteDAO.findById(id);
			
			model = IclubCohortInviteTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubCohortInviteDAO getIclubCohortInviteDAO() {
		return iclubCohortInviteDAO;
	}
	
	public void setIclubCohortInviteDAO(IclubCohortInviteDAO iclubCohortInviteDAO) {
		this.iclubCohortInviteDAO = iclubCohortInviteDAO;
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
	
	public IclubNotificationTypeDAO getIclubNotificationTypeDAO() {
		return iclubNotificationTypeDAO;
	}
	
	public void setIclubNotificationTypeDAO(IclubNotificationTypeDAO iclubNotificationTypeDAO) {
		this.iclubNotificationTypeDAO = iclubNotificationTypeDAO;
	}
	
	public IclubInviteStatusDAO getIclubInviteStatusDAO() {
		return iclubInviteStatusDAO;
	}
	
	public void setIclubInviteStatusDAO(IclubInviteStatusDAO iclubInviteStatusDAO) {
		this.iclubInviteStatusDAO = iclubInviteStatusDAO;
	}
	
}
