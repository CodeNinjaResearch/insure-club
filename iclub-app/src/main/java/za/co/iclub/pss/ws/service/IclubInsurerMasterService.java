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

import za.co.iclub.pss.model.ws.IclubInsurerMasterModel;
import za.co.iclub.pss.orm.bean.IclubInsurerMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubInsurerMasterTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubInsurerMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubInsurerMasterService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubInsurerMasterService.class);
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubInsurerMasterModel model) {
		try {
			
			IclubInsurerMaster iCIm = IclubInsurerMasterTrans.fromWStoORM(model, iclubPersonDAO);
			
			iCIm.setImId(iclubCommonDAO.getNextId(IclubInsurerMaster.class));
			
			iclubInsurerMasterDAO.save(iCIm);
			
			LOGGER.info("Save Success with ID :: " + iCIm.getImId().longValue());
			
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
	public ResponseModel mod(IclubInsurerMasterModel model) {
		try {
			IclubInsurerMaster iCIm = IclubInsurerMasterTrans.fromWStoORM(model, iclubPersonDAO);
			
			iclubInsurerMasterDAO.merge(iCIm);
			
			LOGGER.info("Save Success with ID :: " + model.getImId().longValue());
			
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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubInsurerMasterDAO.delete(iclubInsurerMasterDAO.findById(id));
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
	public <T extends IclubInsurerMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubInsurerMasterDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubInsurerMaster bean = (IclubInsurerMaster) object;
					IclubInsurerMasterModel iCIm = IclubInsurerMasterTrans.fromORMtoWS(bean);
					
					ret.add((T) iCIm);
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
	public <T extends IclubInsurerMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubInsurerMaster.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubInsurerMaster bean = (IclubInsurerMaster) object;
					IclubInsurerMasterModel iCIm = IclubInsurerMasterTrans.fromORMtoWS(bean);
					
					ret.add((T) iCIm);
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
	public IclubInsurerMasterModel getById(@PathParam("id") Long id) {
		IclubInsurerMasterModel model = new IclubInsurerMasterModel();
		try {
			IclubInsurerMaster bean = iclubInsurerMasterDAO.findById(id);
			
			model = IclubInsurerMasterTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}
	
	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
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
	
}
