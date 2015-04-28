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

import za.co.iclub.pss.orm.bean.IclubEvent;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEventDAO;
import za.co.iclub.pss.orm.dao.IclubEventTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubEventModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubEventService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubEventService {

	protected static final Logger LOGGER = Logger.getLogger(IclubEventService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubEventDAO iclubEventDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubEventTypeDAO iclubEventTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubEventModel model) {
		try {
			IclubEvent iCE = new IclubEvent();

			iCE.setEId(model.getEId());
			iCE.setECrtdDt(model.getECrtdDt());
			iCE.setEDesc(model.getEDesc());
			iCE.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCE.setIclubEventType(model.getIclubEventType() != null ? iclubEventTypeDAO.findById(model.getIclubEventType()) : null);

			iclubEventDAO.save(iCE);

			LOGGER.info("Save Success with ID :: " + iCE.getEId());

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
	public ResponseModel mod(IclubEventModel model) {
		try {
			IclubEvent iCE = new IclubEvent();

			model.setEId(iCE.getEId());
			model.setECrtdDt(iCE.getECrtdDt());
			model.setEDesc(iCE.getEDesc());
			model.setIclubPerson(iCE.getIclubPerson() != null ? iCE.getIclubPerson().getPId() : null);
			model.setIclubEventType(iCE.getIclubEventType() != null ? iCE.getIclubEventType().getEtId() : null);

			iclubEventDAO.merge(iCE);

			LOGGER.info("Merge Success with ID :: " + model.getEId());

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
			iclubEventDAO.delete(iclubEventDAO.findById(id));
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
	public <T extends IclubEventModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubEventDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubEvent iCE = (IclubEvent) object;

					IclubEventModel model = new IclubEventModel();

					model.setEId(iCE.getEId());
					model.setECrtdDt(iCE.getECrtdDt());
					model.setEDesc(iCE.getEDesc());
					model.setIclubPerson(iCE.getIclubPerson() != null ? iCE.getIclubPerson().getPId() : null);
					model.setIclubEventType(iCE.getIclubEventType() != null ? iCE.getIclubEventType().getEtId() : null);

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
	public <T extends IclubEventModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubEvent.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubEvent iCE = (IclubEvent) object;

					IclubEventModel model = new IclubEventModel();

					model.setEId(iCE.getEId());
					model.setECrtdDt(iCE.getECrtdDt());
					model.setEDesc(iCE.getEDesc());
					model.setIclubPerson(iCE.getIclubPerson() != null ? iCE.getIclubPerson().getPId() : null);
					model.setIclubEventType(iCE.getIclubEventType() != null ? iCE.getIclubEventType().getEtId() : null);

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
	public IclubEventModel getById(@PathParam("id") String id) {
		IclubEventModel model = new IclubEventModel();
		try {
			IclubEvent bean = iclubEventDAO.findById(id);

			model.setEId(bean.getEId());
			model.setECrtdDt(bean.getECrtdDt());
			model.setEDesc(bean.getEDesc());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubEventType(bean.getIclubEventType() != null ? bean.getIclubEventType().getEtId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubEventDAO getIclubEventDAO() {
		return iclubEventDAO;
	}

	public void setIclubEventDAO(IclubEventDAO iclubEventDAO) {
		this.iclubEventDAO = iclubEventDAO;
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

	public IclubEventTypeDAO getIclubEventTypeDAO() {
		return iclubEventTypeDAO;
	}

	public void setIclubEventTypeDAO(IclubEventTypeDAO iclubEventTypeDAO) {
		this.iclubEventTypeDAO = iclubEventTypeDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
