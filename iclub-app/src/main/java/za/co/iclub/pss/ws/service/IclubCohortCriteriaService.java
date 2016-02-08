package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.Collection;
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
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubCohortCriteriaModel;
import za.co.iclub.pss.orm.bean.IclubCohortCriteria;
import za.co.iclub.pss.orm.dao.IclubCohortCriteriaDAO;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.trans.IclubCohortCriteriaTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortCriteriaService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortCriteriaService {

	private static final Logger LOGGER = Logger.getLogger(IclubCohortCriteriaService.class);
	private IclubCohortCriteriaDAO iclubCohortCriteriaDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortInviteDAO iclubCohortInviteDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortCriteriaModel model) {
		try {

			IclubCohortCriteria iCC = IclubCohortCriteriaTrans.fromWStoORM(model, iclubCohortInviteDAO);
			iclubCohortCriteriaDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCcId());

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

	@POST
	@Path("/addList")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel addList(Collection<? extends IclubCohortCriteriaModel> models) {
		try {

			for (IclubCohortCriteriaModel model : models) {

				IclubCohortCriteria iCC = IclubCohortCriteriaTrans.fromWStoORM(model, iclubCohortInviteDAO);
				iclubCohortCriteriaDAO.save(iCC);

				LOGGER.info("Save Success with ID :: " + iCC.getCcId());
			}

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

	@POST
	@Path("/modList")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel modList(Collection<? extends IclubCohortCriteriaModel> models) {
		try {

			for (IclubCohortCriteriaModel model : models) {

				IclubCohortCriteria iCC = IclubCohortCriteriaTrans.fromWStoORM(model, iclubCohortInviteDAO);
				iclubCohortCriteriaDAO.merge(iCC);

				LOGGER.info("Save Success with ID :: " + model.getCcId());
			}

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
	public ResponseModel mod(IclubCohortCriteriaModel model) {
		try {
			IclubCohortCriteria iCC = IclubCohortCriteriaTrans.fromWStoORM(model, iclubCohortInviteDAO);

			iclubCohortCriteriaDAO.merge(iCC);

			LOGGER.info("Save Success with ID :: " + model.getCcId());

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
			iclubCohortCriteriaDAO.delete(iclubCohortCriteriaDAO.findById(id));
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
	public <T extends IclubCohortCriteriaModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCohortCriteriaDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortCriteria bean = (IclubCohortCriteria) object;
					IclubCohortCriteriaModel iCC = IclubCohortCriteriaTrans.fromORMtoWS(bean);

					ret.add((T) iCC);
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
	public IclubCohortCriteriaModel getById(@PathParam("id") String id) {
		IclubCohortCriteriaModel model = new IclubCohortCriteriaModel();
		try {
			IclubCohortCriteria bean = iclubCohortCriteriaDAO.findById(id);

			model = IclubCohortCriteriaTrans.fromORMtoWS(bean);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCohortCriteriaDAO getIclubCohortCriteriaDAO() {
		return iclubCohortCriteriaDAO;
	}

	public void setIclubCohortCriteriaDAO(IclubCohortCriteriaDAO iclubCohortCriteriaDAO) {
		this.iclubCohortCriteriaDAO = iclubCohortCriteriaDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

	public IclubCohortInviteDAO getIclubCohortInviteDAO() {
		return iclubCohortInviteDAO;
	}

	public void setIclubCohortInviteDAO(IclubCohortInviteDAO iclubCohortInviteDAO) {
		this.iclubCohortInviteDAO = iclubCohortInviteDAO;
	}

}
