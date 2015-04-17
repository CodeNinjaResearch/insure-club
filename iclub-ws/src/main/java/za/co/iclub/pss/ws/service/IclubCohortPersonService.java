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

import za.co.iclub.pss.orm.bean.IclubCohortPerson;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortPersonDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCohortPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortPersonService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortPersonService {

	private static final Logger LOGGER = Logger.getLogger(IclubCohortPersonService.class);
	private IclubCohortPersonDAO iclubCohortPersonDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortDAO iclubCohortDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortPersonModel model) {
		try {

			IclubCohortPerson iCC = new IclubCohortPerson();

			iCC.setCpId(model.getCpId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setCpCrtdDt(model.getCpCrtdDt());
			iCC.setCpContrib(model.getCpContrib());
			iCC.setIclubPersonByCpPersonId(iclubPersonDAO.findById(model.getIclubPersonByCpPersonId()));
			iCC.setIclubPersonByCpCrtdBy(iclubPersonDAO.findById(model.getIclubPersonByCpCrtdBy()));

			iclubCohortPersonDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCpId());

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
	public ResponseModel mod(IclubCohortPersonModel model) {
		try {
			IclubCohortPerson iCC = new IclubCohortPerson();

			iCC.setCpId(model.getCpId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setCpCrtdDt(model.getCpCrtdDt());
			iCC.setCpContrib(model.getCpContrib());
			iCC.setIclubPersonByCpPersonId(iclubPersonDAO.findById(model.getIclubPersonByCpPersonId()));
			iCC.setIclubPersonByCpCrtdBy(iclubPersonDAO.findById(model.getIclubPersonByCpCrtdBy()));

			iclubCohortPersonDAO.merge(iCC);

			LOGGER.info("Save Success with ID :: " + model.getCpId());

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
			iclubCohortPersonDAO.delete(iclubCohortPersonDAO.findById(id));
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
	public <T extends IclubCohortPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCohortPersonDAO.findAll();

			for (Object object : batmod) {
				IclubCohortPerson iclubC = (IclubCohortPerson) object;
				IclubCohortPersonModel iCC = new IclubCohortPersonModel();

				iCC.setCpId(iclubC.getCpId());
				iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
				iCC.setCpCrtdDt(iclubC.getCpCrtdDt());
				iCC.setCpContrib(iclubC.getCpContrib());
				iCC.setIclubPersonByCpPersonId(iclubC.getIclubPersonByCpPersonId() != null ? (iclubC.getIclubPersonByCpPersonId()).getPId() : null);
				iCC.setIclubPersonByCpCrtdBy(iclubC.getIclubPersonByCpCrtdBy() != null ? (iclubC.getIclubPersonByCpCrtdBy()).getPId() : null);

				ret.add((T) iCC);
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
	public <T extends IclubCohortPersonModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohortPerson.class.getSimpleName());

			for (Object object : batmod) {
				IclubCohortPerson iclubC = (IclubCohortPerson) object;
				IclubCohortPersonModel iCC = new IclubCohortPersonModel();

				iCC.setCpId(iclubC.getCpId());
				iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
				iCC.setCpCrtdDt(iclubC.getCpCrtdDt());
				iCC.setCpContrib(iclubC.getCpContrib());
				iCC.setIclubPersonByCpPersonId(iclubC.getIclubPersonByCpPersonId() != null ? (iclubC.getIclubPersonByCpPersonId()).getPId() : null);
				iCC.setIclubPersonByCpCrtdBy(iclubC.getIclubPersonByCpCrtdBy() != null ? (iclubC.getIclubPersonByCpCrtdBy()).getPId() : null);

				ret.add((T) iCC);
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
	public IclubCohortPersonModel getById(@PathParam("id") String id) {
		IclubCohortPersonModel model = new IclubCohortPersonModel();
		try {
			IclubCohortPerson bean = iclubCohortPersonDAO.findById(id);

			model.setCpId(bean.getCpId());
			model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
			model.setCpCrtdDt(bean.getCpCrtdDt());
			model.setCpContrib(bean.getCpContrib());
			model.setIclubPersonByCpPersonId(bean.getIclubPersonByCpPersonId() != null ? (bean.getIclubPersonByCpPersonId()).getPId() : null);
			model.setIclubPersonByCpCrtdBy(bean.getIclubPersonByCpCrtdBy() != null ? (bean.getIclubPersonByCpCrtdBy()).getPId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCohortPersonDAO getIclubCohortPersonDAO() {
		return iclubCohortPersonDAO;
	}

	public void setIclubCohortPersonDAO(IclubCohortPersonDAO iclubCohortPersonDAO) {
		this.iclubCohortPersonDAO = iclubCohortPersonDAO;
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

}
