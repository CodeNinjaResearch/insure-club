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

import za.co.iclub.pss.orm.bean.IclubCohortClaim;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubCohortClaimDAO;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCohortClaimModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortClaimService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortClaimService {

	private static final Logger LOGGER = Logger.getLogger(IclubCohortClaimService.class);
	private IclubCohortClaimDAO iclubCohortClaimDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortDAO iclubCohortDAO;
	private IclubClaimDAO iclubClaimDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortClaimModel model) {
		try {

			IclubCohortClaim iCC = new IclubCohortClaim();

			iCC.setCcId(model.getCcId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setIclubClaim(iclubClaimDAO.findById(model.getIclubClaim()));
			iCC.setCcCrtdDt(model.getCcCrtdDt());
			iCC.setCcClaimAmt(model.getCcClaimAmt());
			iCC.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubCohortClaimDAO.save(iCC);

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

	@PUT
	@Path("/mod")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel mod(IclubCohortClaimModel model) {
		try {
			IclubCohortClaim iCC = new IclubCohortClaim();

			iCC.setCcId(model.getCcId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setCcCrtdDt(model.getCcCrtdDt());
			iCC.setCcClaimAmt(model.getCcClaimAmt());
			iCC.setIclubClaim(iclubClaimDAO.findById(model.getIclubClaim()));
			iCC.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubCohortClaimDAO.merge(iCC);

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
			iclubCohortClaimDAO.delete(iclubCohortClaimDAO.findById(id));
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
	public <T extends IclubCohortClaimModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCohortClaimDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortClaim iclubC = (IclubCohortClaim) object;
					IclubCohortClaimModel iCC = new IclubCohortClaimModel();

					iCC.setCcId(iclubC.getCcId());
					iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
					iCC.setCcCrtdDt(iclubC.getCcCrtdDt());
					iCC.setIclubClaim((iclubC.getIclubClaim()) != null ? iclubC.getIclubClaim().getCId() : null);
					iCC.setCcClaimAmt(iclubC.getCcClaimAmt());
					iCC.setIclubPerson(iclubC.getIclubPerson() != null ? (iclubC.getIclubPerson()).getPId() : null);

					ret.add((T) iCC);
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
	public <T extends IclubCohortClaimModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohortClaim.class.getSimpleName());
			if(batmod!=null&& batmod.size()>0){
			for (Object object : batmod) {
				IclubCohortClaim iclubC = (IclubCohortClaim) object;
				IclubCohortClaimModel iCC = new IclubCohortClaimModel();

				iCC.setCcId(iclubC.getCcId());
				iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
				iCC.setCcCrtdDt(iclubC.getCcCrtdDt());
				iCC.setIclubClaim((iclubC.getIclubClaim()) != null ? iclubC.getIclubClaim().getCId() : null);
				iCC.setCcClaimAmt(iclubC.getCcClaimAmt());
				iCC.setIclubPerson(iclubC.getIclubPerson() != null ? (iclubC.getIclubPerson()).getPId() : null);

				ret.add((T) iCC);
			}}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional
	public IclubCohortClaimModel getById(@PathParam("id") String id) {
		IclubCohortClaimModel model = new IclubCohortClaimModel();
		try {
			IclubCohortClaim bean = iclubCohortClaimDAO.findById(id);

			model.setCcId(bean.getCcId());
			model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
			model.setCcCrtdDt(bean.getCcCrtdDt());
			model.setIclubClaim((bean.getIclubClaim()) != null ? bean.getIclubClaim().getCId() : null);
			model.setCcClaimAmt(bean.getCcClaimAmt());
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson()).getPId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCohortClaimDAO getIclubCohortClaimDAO() {
		return iclubCohortClaimDAO;
	}

	public void setIclubCohortClaimDAO(IclubCohortClaimDAO iclubCohortClaimDAO) {
		this.iclubCohortClaimDAO = iclubCohortClaimDAO;
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

	public IclubClaimDAO getIclubClaimDAO() {
		return iclubClaimDAO;
	}

	public void setIclubClaimDAO(IclubClaimDAO iclubClaimDAO) {
		this.iclubClaimDAO = iclubClaimDAO;
	}

}
