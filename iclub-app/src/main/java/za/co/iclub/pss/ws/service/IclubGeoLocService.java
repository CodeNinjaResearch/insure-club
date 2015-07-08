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

import za.co.iclub.pss.model.ws.IclubGeoLocModel;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubGeoLocDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubGeoLocTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubGeoLocService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubGeoLocService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubGeoLocService.class);
	private IclubGeoLocDAO iclubGeoLocDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubGeoLocModel model) {
		try {
			
			IclubGeoLoc iCGl = IclubGeoLocTrans.fromWStoORM(model, iclubPersonDAO);
			
			iCGl.setGlId(iclubCommonDAO.getNextId(IclubGeoLoc.class));
			
			iclubGeoLocDAO.save(iCGl);
			
			LOGGER.info("Save Success with ID :: " + iCGl.getGlId().longValue());
			
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
	public ResponseModel mod(IclubGeoLocModel model) {
		try {
			IclubGeoLoc iCGl = IclubGeoLocTrans.fromWStoORM(model, iclubPersonDAO);
			
			iclubGeoLocDAO.merge(iCGl);
			
			LOGGER.info("Save Success with ID :: " + model.getGlId().longValue());
			
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
			iclubGeoLocDAO.delete(iclubGeoLocDAO.findById(id));
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
	public <T extends IclubGeoLocModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubGeoLocDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubGeoLoc bean = (IclubGeoLoc) object;
					IclubGeoLocModel iCGl = IclubGeoLocTrans.fromORMtoWS(bean);
					
					ret.add((T) iCGl);
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
	public <T extends IclubGeoLocModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();
		
		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubGeoLoc.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubGeoLoc bean = (IclubGeoLoc) object;
					IclubGeoLocModel iCGl = IclubGeoLocTrans.fromORMtoWS(bean);
					
					ret.add((T) iCGl);
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
	public IclubGeoLocModel getById(@PathParam("id") Long id) {
		IclubGeoLocModel model = new IclubGeoLocModel();
		try {
			IclubGeoLoc bean = iclubGeoLocDAO.findById(id);
			
			model = IclubGeoLocTrans.fromORMtoWS(bean);
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	@GET
	@Path("/get/{geoLat}/{geoLong}")
	@Produces("application/json")
	@Transactional
	public IclubGeoLocModel getByGeoLangAndLat(@PathParam("geoLat") Double geoLat, @PathParam("geoLong") Double geoLong) {
		IclubGeoLocModel model = new IclubGeoLocModel();
		try {
			Long glId = iclubNamedQueryDAO.getIclubGeoLocByLatAndLong(geoLong, geoLat);
			IclubGeoLoc bean = iclubGeoLocDAO.findById(glId);
			if (bean != null) {
				model = IclubGeoLocTrans.fromORMtoWS(bean);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}
	
	public IclubGeoLocDAO getIclubGeoLocDAO() {
		return iclubGeoLocDAO;
	}
	
	public void setIclubGeoLocDAO(IclubGeoLocDAO iclubGeoLocDAO) {
		this.iclubGeoLocDAO = iclubGeoLocDAO;
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
