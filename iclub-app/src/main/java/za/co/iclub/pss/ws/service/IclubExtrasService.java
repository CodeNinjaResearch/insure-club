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

import za.co.iclub.pss.orm.bean.IclubExtras;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubExtrasDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubExtrasModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubExtrasService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubExtrasService {

	protected static final Logger LOGGER = Logger.getLogger(IclubExtrasService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubExtrasDAO iclubExtrasDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubExtrasModel model) {
		try {
			IclubExtras iCt = new IclubExtras();

			iCt.setEId(iclubCommonDAO.getNextId(IclubExtras.class));
			iCt.setEDesc(model.getEDesc());
			iCt.setECrtdDt(model.getECrtdDt());
			iCt.setEStatus(model.getEStatus());
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubExtrasDAO.save(iCt);

			LOGGER.info("Save Success with ID :: " + iCt.getEId());

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
	public ResponseModel mod(IclubExtrasModel model) {
		try {
			IclubExtras iCt = new IclubExtras();

			iCt.setEId(model.getEId());
			iCt.setEDesc(model.getEDesc());
			iCt.setECrtdDt(model.getECrtdDt());
			iCt.setEStatus(model.getEStatus());
			iCt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubExtrasDAO.merge(iCt);

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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubExtrasDAO.delete(iclubExtrasDAO.findById(id));
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
	public <T extends IclubExtrasModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubExtrasDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubExtras iCt = (IclubExtras) object;

					IclubExtrasModel model = new IclubExtrasModel();

					model.setEId(iCt.getEId());
					model.setECrtdDt(iCt.getECrtdDt());
					model.setEDesc(iCt.getEDesc());
					model.setEStatus(iCt.getEStatus());
					model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);

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
	public <T extends IclubExtrasModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubExtras.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubExtras iCt = (IclubExtras) object;

					IclubExtrasModel model = new IclubExtrasModel();

					model.setEId(iCt.getEId());
					model.setECrtdDt(iCt.getECrtdDt());
					model.setEDesc(iCt.getEDesc());
					model.setEStatus(iCt.getEStatus());
					model.setIclubPerson(iCt.getIclubPerson() != null ? (iCt.getIclubPerson().getPId()) : null);

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
	public IclubExtrasModel getById(@PathParam("id") Long id) {
		IclubExtrasModel model = new IclubExtrasModel();
		try {
			IclubExtras bean = iclubExtrasDAO.findById(id);

			model.setEId(bean.getEId());
			model.setECrtdDt(bean.getECrtdDt());
			model.setEDesc(bean.getEDesc());
			model.setEStatus(bean.getEStatus());
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubExtrasDAO getIclubExtrasDAO() {
		return iclubExtrasDAO;
	}

	public void setIclubExtrasDAO(IclubExtrasDAO iclubExtrasDAO) {
		this.iclubExtrasDAO = iclubExtrasDAO;
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
