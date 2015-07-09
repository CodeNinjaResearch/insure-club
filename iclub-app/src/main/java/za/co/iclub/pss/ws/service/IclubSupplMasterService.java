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

import za.co.iclub.pss.model.ws.IclubSupplMasterModel;
import za.co.iclub.pss.orm.bean.IclubSupplMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;
import za.co.iclub.pss.orm.dao.IclubSupplierTypeDAO;
import za.co.iclub.pss.trans.IclubSupplMasterTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSupplMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSupplMasterService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubSupplMasterService.class);
	private IclubSupplMasterDAO iclubSupplMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubSupplierTypeDAO iclubSupplierTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubSupplMasterModel model) {
		try {
			
			IclubSupplMaster iCSm = IclubSupplMasterTrans.fromWStoORM(model, iclubPersonDAO, iclubSupplierTypeDAO);
			
			iclubSupplMasterDAO.save(iCSm);
			
			LOGGER.info("Save Success with ID :: " + iCSm.getSmId());
			
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
	public ResponseModel mod(IclubSupplMasterModel model) {
		try {
			IclubSupplMaster iCSm = IclubSupplMasterTrans.fromWStoORM(model, iclubPersonDAO, iclubSupplierTypeDAO);
			
			iclubSupplMasterDAO.merge(iCSm);
			
			LOGGER.info("Save Success with ID :: " + model.getSmId());
			
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
			iclubSupplMasterDAO.delete(iclubSupplMasterDAO.findById(id));
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
	public <T extends IclubSupplMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubSupplMasterDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplMaster bean = (IclubSupplMaster) object;
					IclubSupplMasterModel iCSm = IclubSupplMasterTrans.fromORMtoWS(bean);
					
					ret.add((T) iCSm);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		
		return ret;
	}
	
	@GET
	@Path("/getByLongAndLat/{smLat}/{smLong}")
	@Produces("application/json")
	@Transactional
	public <T extends IclubSupplMasterModel> List<T> getByLongAndLat(@PathParam("smLat") Double smLat, @PathParam("smLong") Double smLong) {
		List<T> ret = new ArrayList<T>();
		try {
			List smIds = iclubNamedQueryDAO.getIclubSupplMasterByLatAndLong(smLong, smLat);
			if (smIds != null && smIds.size() > 0) {
				for (Object obj : smIds) {
					String smId = (String) ((Object[]) obj)[0];
					
					IclubSupplMaster bean = iclubSupplMasterDAO.findById(smId);
					
					IclubSupplMasterModel model = IclubSupplMasterTrans.fromORMtoWS(bean);
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
	public <T extends IclubSupplMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubSupplMaster.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplMaster bean = (IclubSupplMaster) object;
					IclubSupplMasterModel iCSm = IclubSupplMasterTrans.fromORMtoWS(bean);
					
					ret.add((T) iCSm);
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
	public IclubSupplMasterModel getById(@PathParam("id") String id) {
		IclubSupplMasterModel model = new IclubSupplMasterModel();
		try {
			IclubSupplMaster bean = iclubSupplMasterDAO.findById(id);
			
			model = IclubSupplMasterTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubSupplMasterDAO getIclubSupplMasterDAO() {
		return iclubSupplMasterDAO;
	}
	
	public void setIclubSupplMasterDAO(IclubSupplMasterDAO iclubSupplMasterDAO) {
		this.iclubSupplMasterDAO = iclubSupplMasterDAO;
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
	
	public IclubSupplierTypeDAO getIclubSupplierTypeDAO() {
		return iclubSupplierTypeDAO;
	}
	
	public void setIclubSupplierTypeDAO(IclubSupplierTypeDAO iclubSupplierTypeDAO) {
		this.iclubSupplierTypeDAO = iclubSupplierTypeDAO;
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
