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

import za.co.iclub.pss.model.ws.IclubNotificationTypeModel;
import za.co.iclub.pss.orm.bean.IclubNotificationType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.trans.IclubNotificationTypeTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubNotificationTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubNotificationTypeService {
	
	protected static final Logger LOGGER = Logger.getLogger(IclubNotificationTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubNotificationTypeDAO iclubNotificationTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubNotificationTypeModel model) {
		try {
			IclubNotificationType iNt = IclubNotificationTypeTrans.fromWStoORM(model);
			
			iNt.setNtId(iclubCommonDAO.getNextId(IclubNotificationType.class));
			
			iclubNotificationTypeDAO.save(iNt);
			
			LOGGER.info("Save Success with ID :: " + iNt.getNtId());
			
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
	public ResponseModel mod(IclubNotificationTypeModel model) {
		try {
			IclubNotificationType iNt = IclubNotificationTypeTrans.fromWStoORM(model);
			
			iclubNotificationTypeDAO.merge(iNt);
			
			LOGGER.info("Merge Success with ID :: " + model.getNtId());
			
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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubNotificationTypeDAO.delete(iclubNotificationTypeDAO.findById(id));
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
	public <T extends IclubNotificationTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNotificationTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubNotificationType bean = (IclubNotificationType) object;
					
					IclubNotificationTypeModel model = IclubNotificationTypeTrans.fromORMtoWS(bean);
					
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
	public IclubNotificationTypeModel getById(@PathParam("id") Long id) {
		IclubNotificationTypeModel model = new IclubNotificationTypeModel();
		try {
			IclubNotificationType bean = iclubNotificationTypeDAO.findById(id);
			
			model = IclubNotificationTypeTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubNotificationType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}
	
	public IclubNotificationTypeDAO getIclubNotificationTypeDAO() {
		return iclubNotificationTypeDAO;
	}
	
	public void setIclubNotificationTypeDAO(IclubNotificationTypeDAO iclubNotificationTypeDAO) {
		this.iclubNotificationTypeDAO = iclubNotificationTypeDAO;
	}
	
	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}
	
	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
