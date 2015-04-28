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

import za.co.iclub.pss.orm.bean.IclubOccupation;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubOccupationDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubOccupationModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubOccupationService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubOccupationService {

	protected static final Logger LOGGER = Logger.getLogger(IclubOccupationService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubOccupationDAO iclubOccupationDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubOccupationModel model) {
		try {
			IclubOccupation iCO = new IclubOccupation();

			iCO.setOId(iclubCommonDAO.getNextId(IclubOccupation.class));
			iCO.setODesc(model.getODesc());
			iCO.setOCrtdDt(model.getOCrtdDt());
			iCO.setOStatus(model.getOStatus());
			iCO.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubOccupationDAO.save(iCO);

			LOGGER.info("Save Success with ID :: " + iCO.getOId());

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
	public ResponseModel mod(IclubOccupationModel model) {
		try {
			IclubOccupation iCO = new IclubOccupation();

			iCO.setOId(model.getOId());
			iCO.setODesc(model.getODesc());
			iCO.setOCrtdDt(model.getOCrtdDt());
			iCO.setOStatus(model.getOStatus());
			iCO.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubOccupationDAO.merge(iCO);

			LOGGER.info("Merge Success with ID :: " + model.getOId());

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
			iclubOccupationDAO.delete(iclubOccupationDAO.findById(id));
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
	public <T extends IclubOccupationModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubOccupationDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubOccupation iCO = (IclubOccupation) object;

					IclubOccupationModel model = new IclubOccupationModel();

					model.setOId(iCO.getOId());
					model.setODesc(iCO.getODesc());
					model.setOCrtdDt(iCO.getOCrtdDt());
					model.setOStatus(iCO.getOStatus());
					model.setIclubPerson(iCO.getIclubPerson() != null ? iCO.getIclubPerson().getPId() : null);

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
	public <T extends IclubOccupationModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubOccupation.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubOccupation iCO = (IclubOccupation) object;

					IclubOccupationModel model = new IclubOccupationModel();

					model.setOId(iCO.getOId());
					model.setODesc(iCO.getODesc());
					model.setOCrtdDt(iCO.getOCrtdDt());
					model.setOStatus(iCO.getOStatus());
					model.setIclubPerson(iCO.getIclubPerson() != null ? iCO.getIclubPerson().getPId() : null);

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
	public IclubOccupationModel getById(@PathParam("id") Long id) {
		IclubOccupationModel model = new IclubOccupationModel();
		try {
			IclubOccupation bean = iclubOccupationDAO.findById(id);

			model.setOId(bean.getOId());
			model.setODesc(bean.getODesc());
			model.setOCrtdDt(bean.getOCrtdDt());
			model.setOStatus(bean.getOStatus());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubOccupationDAO getIclubOccupationDAO() {
		return iclubOccupationDAO;
	}

	public void setIclubOccupationDAO(IclubOccupationDAO iclubOccupationDAO) {
		this.iclubOccupationDAO = iclubOccupationDAO;
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
