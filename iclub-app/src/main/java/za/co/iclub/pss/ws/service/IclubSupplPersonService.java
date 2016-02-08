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

import za.co.iclub.pss.model.ws.IclubSupplPersonModel;
import za.co.iclub.pss.orm.bean.IclubSupplPerson;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;
import za.co.iclub.pss.orm.dao.IclubSupplPersonDAO;
import za.co.iclub.pss.trans.IclubSupplPersonTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSupplPersonService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSupplPersonService {

	private static final Logger LOGGER = Logger.getLogger(IclubSupplPersonService.class);
	private IclubSupplPersonDAO iclubSupplPersonDAO;
	private IclubSupplMasterDAO iclubSupplMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubSupplPersonModel model) {
		try {

			IclubSupplPerson bean = IclubSupplPersonTrans.fromWStoORM(model, iclubPersonDAO, iclubSupplMasterDAO);

			iclubSupplPersonDAO.save(bean);

			LOGGER.info("Save Success with ID :: " + bean.getSpId());

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
	public ResponseModel mod(IclubSupplPersonModel model) {
		try {
			IclubSupplPerson iCSp = IclubSupplPersonTrans.fromWStoORM(model, iclubPersonDAO, iclubSupplMasterDAO);

			iclubSupplPersonDAO.merge(iCSp);

			LOGGER.info("Save Success with ID :: " + model.getSpId());

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
			iclubSupplPersonDAO.delete(iclubSupplPersonDAO.findById(id));
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
	public <T extends IclubSupplPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSupplPersonDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplPerson bean = (IclubSupplPerson) object;
					IclubSupplPersonModel iCSp = IclubSupplPersonTrans.fromORMtoWS(bean);

					ret.add((T) iCSp);
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
	public <T extends IclubSupplPersonModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubSupplPerson.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplPerson bean = (IclubSupplPerson) object;
					IclubSupplPersonModel iCSp = IclubSupplPersonTrans.fromORMtoWS(bean);

					ret.add((T) iCSp);
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
	public IclubSupplPersonModel getById(@PathParam("id") String id) {
		IclubSupplPersonModel model = new IclubSupplPersonModel();
		try {
			IclubSupplPerson bean = iclubSupplPersonDAO.findById(id);

			model = IclubSupplPersonTrans.fromORMtoWS(bean);
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/get/supplMaster/{id}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubSupplPersonModel> List<T> getBySupplMaster(@PathParam("id") String smId) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findIclubSupplPersonBySmId(smId);
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplPerson bean = (IclubSupplPerson) object;
					IclubSupplPersonModel iCSp = IclubSupplPersonTrans.fromORMtoWS(bean);
					ret.add((T) iCSp);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	public IclubSupplPersonDAO getIclubSupplPersonDAO() {
		return iclubSupplPersonDAO;
	}

	public void setIclubSupplPersonDAO(IclubSupplPersonDAO iclubSupplPersonDAO) {
		this.iclubSupplPersonDAO = iclubSupplPersonDAO;
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

	public IclubSupplMasterDAO getIclubSupplMasterDAO() {
		return iclubSupplMasterDAO;
	}

	public void setIclubSupplMasterDAO(IclubSupplMasterDAO iclubSupplMasterDAO) {
		this.iclubSupplMasterDAO = iclubSupplMasterDAO;
	}

}
