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

import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubGeoLocDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubGeoLocModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubGeoLocService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubGeoLocService {

	private static final Logger LOGGER = Logger.getLogger(IclubGeoLocService.class);
	private IclubGeoLocDAO iclubGeoLocDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubGeoLocModel model) {
		try {

			IclubGeoLoc iCGl = new IclubGeoLoc();

			iCGl.setGlId(iclubCommonDAO.getNextId(IclubGeoLoc.class));
			iCGl.setGlAddress(model.getGlAddress());
			iCGl.setGlLat(model.getGlLat());
			iCGl.setGlLong(model.getGlLong());
			iCGl.setGlRate(model.getGlRate());
			iCGl.setGlCrtdDt(model.getGlCrtdDt());
			iCGl.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

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
			IclubGeoLoc iCGl = new IclubGeoLoc();

			iCGl.setGlId(model.getGlId());
			iCGl.setGlAddress(model.getGlAddress());
			iCGl.setGlLat(model.getGlLat());
			iCGl.setGlLong(model.getGlLong());
			iCGl.setGlRate(model.getGlRate());
			iCGl.setGlCrtdDt(model.getGlCrtdDt());
			;
			iCGl.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

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

			for (Object object : batmod) {
				IclubGeoLoc iclubGLoc = (IclubGeoLoc) object;
				IclubGeoLocModel iCGl = new IclubGeoLocModel();

				iCGl.setGlId(iclubGLoc.getGlId());
				iCGl.setGlAddress(iclubGLoc.getGlAddress());
				iCGl.setGlLat(iclubGLoc.getGlLat());
				iCGl.setGlLong(iclubGLoc.getGlLong());
				iCGl.setIclubPerson(iclubGLoc.getIclubPerson() != null ? iclubGLoc.getIclubPerson().getPId() : null);
				iCGl.setGlRate(iclubGLoc.getGlRate());
				iCGl.setGlCrtdDt(iclubGLoc.getGlCrtdDt());
				;

				ret.add((T) iCGl);
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
			List batmod = iclubGeoLocDAO.findByUser(user);

			for (Object object : batmod) {
				IclubGeoLoc iclubGLoc = (IclubGeoLoc) object;
				IclubGeoLocModel iCGl = new IclubGeoLocModel();

				iCGl.setGlId(iclubGLoc.getGlId());
				iCGl.setGlAddress(iclubGLoc.getGlAddress());
				iCGl.setGlLat(iclubGLoc.getGlLat());
				iCGl.setGlLong(iclubGLoc.getGlLong());
				iCGl.setIclubPerson(iclubGLoc.getIclubPerson() != null ? iclubGLoc.getIclubPerson().getPId() : null);
				iCGl.setGlRate(iclubGLoc.getGlRate());
				iCGl.setGlCrtdDt(iclubGLoc.getGlCrtdDt());
				;

				ret.add((T) iCGl);
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

			model.setGlId(bean.getGlId());
			model.setGlAddress(bean.getGlAddress());
			model.setGlLat(bean.getGlLat());
			model.setGlLong(bean.getGlLong());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setGlRate(bean.getGlRate());
			model.setGlCrtdDt(bean.getGlCrtdDt());

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

}
