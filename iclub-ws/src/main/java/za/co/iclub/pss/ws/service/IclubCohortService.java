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

import za.co.iclub.pss.orm.bean.IclubCohort;
import za.co.iclub.pss.orm.bean.IclubCohortClaim;
import za.co.iclub.pss.orm.bean.IclubCohortPerson;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCohortModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortService {

	private static final Logger LOGGER = Logger.getLogger(IclubCohortService.class);
	private IclubCohortDAO iclubCohortDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortTypeDAO iclubCohortTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortModel model) {
		try {

			IclubCohort iCC = new IclubCohort();

			iCC.setCId(model.getCId());
			iCC.setCName(model.getCName());
			iCC.setCEmail(model.getCEmail());
			iCC.setCInitDt(model.getCInitDt());
			iCC.setCFinalizeDt(model.getCFinalizeDt());
			iCC.setCTotalContrib(model.getCTotalContrib());
			iCC.setCCollectedContrib(model.getCCollectedContrib());
			iCC.setCCurMemberCnt(model.getCCurMemberCnt());
			iCC.setIclubCohortType(iclubCohortTypeDAO.findById(model.getIclubCohortType()));
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setIclubPersonByCPrimaryUserId(iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()));
			iCC.setIclubPersonByCCrtdBy(iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()));

			iclubCohortDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCId());

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
	public ResponseModel mod(IclubCohortModel model) {
		try {
			IclubCohort iCC = new IclubCohort();

			iCC.setCId(model.getCId());
			iCC.setCName(model.getCName());
			iCC.setCEmail(model.getCEmail());
			iCC.setCInitDt(model.getCInitDt());
			iCC.setCFinalizeDt(model.getCFinalizeDt());
			iCC.setCTotalContrib(model.getCTotalContrib());
			iCC.setCCollectedContrib(model.getCCollectedContrib());
			iCC.setCCurMemberCnt(model.getCCurMemberCnt());
			iCC.setIclubCohortType(iclubCohortTypeDAO.findById(model.getIclubCohortType()));
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setIclubPersonByCPrimaryUserId(iclubPersonDAO.findById(model.getIclubPersonByCPrimaryUserId()));
			iCC.setIclubPersonByCCrtdBy(iclubPersonDAO.findById(model.getIclubPersonByCCrtdBy()));

			iclubCohortDAO.merge(iCC);

			LOGGER.info("Save Success with ID :: " + model.getCId());

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
			iclubCohortDAO.delete(iclubCohortDAO.findById(id));
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
	public <T extends IclubCohortModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubCohortDAO.findAll();
			if(batmod!=null&& batmod.size()>0){
			for (Object object : batmod) {
				IclubCohort iclubC = (IclubCohort) object;
				IclubCohortModel iCC = new IclubCohortModel();

				iCC.setCId(iclubC.getCId());
				iCC.setCName(iclubC.getCName());
				iCC.setCEmail(iclubC.getCEmail());
				iCC.setCInitDt(iclubC.getCInitDt());
				iCC.setCFinalizeDt(iclubC.getCFinalizeDt());
				iCC.setCTotalContrib(iclubC.getCTotalContrib());
				iCC.setCCollectedContrib(iclubC.getCCollectedContrib());
				iCC.setCCurMemberCnt(iclubC.getCCurMemberCnt());
				iCC.setCCrtdDt(iclubC.getCCrtdDt());
				iCC.setIclubCohortType(iclubC.getIclubCohortType() != null ? (iclubC.getIclubCohortType()).getCtId() : null);
				iCC.setCCrtdDt(iclubC.getCCrtdDt());
				iCC.setIclubPersonByCPrimaryUserId(iclubC.getIclubPersonByCPrimaryUserId() != null ? (iclubC.getIclubPersonByCPrimaryUserId()).getPId() : null);
				iCC.setIclubPersonByCCrtdBy(iclubC.getIclubPersonByCCrtdBy() != null ? (iclubC.getIclubPersonByCCrtdBy()).getPId() : null);

				if (iclubC.getIclubCohortClaims() != null && iclubC.getIclubCohortClaims().size() > 0) {
					String[] iclubCohortClaims = new String[iclubC.getIclubCohortClaims().size()];
					int i = 0;
					for (IclubCohortClaim iclubCohortClaim : iclubC.getIclubCohortClaims()) {
						iclubCohortClaims[i] = iclubCohortClaim.getCcId();
						i++;
					}
					iCC.setIclubCohortClaims(iclubCohortClaims);
				}

				if (iclubC.getIclubCohortPersons() != null && iclubC.getIclubCohortPersons().size() > 0) {
					String[] iclubCohortPersons = new String[iclubC.getIclubCohortPersons().size()];
					int i = 0;
					for (IclubCohortPerson iclubCohortPerson : iclubC.getIclubCohortPersons()) {
						iclubCohortPersons[i] = iclubCohortPerson.getCpId();
						i++;
					}
					iCC.setIclubCohortPersons(iclubCohortPersons);
				}

				ret.add((T) iCC);
			}}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/user/{user}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubCohortModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohort.class.getSimpleName());
			if(batmod!=null&& batmod.size()>0){
			for (Object object : batmod) {
				IclubCohort iclubC = (IclubCohort) object;
				IclubCohortModel iCC = new IclubCohortModel();

				iCC.setCId(iclubC.getCId());
				iCC.setCName(iclubC.getCName());
				iCC.setCEmail(iclubC.getCEmail());
				iCC.setCInitDt(iclubC.getCInitDt());
				iCC.setCFinalizeDt(iclubC.getCFinalizeDt());
				iCC.setCTotalContrib(iclubC.getCTotalContrib());
				iCC.setCCollectedContrib(iclubC.getCCollectedContrib());
				iCC.setCCurMemberCnt(iclubC.getCCurMemberCnt());
				iCC.setIclubCohortType(iclubC.getIclubCohortType() != null ? (iclubC.getIclubCohortType()).getCtId() : null);
				iCC.setCCrtdDt(iclubC.getCCrtdDt());
				iCC.setIclubPersonByCPrimaryUserId(iclubC.getIclubPersonByCPrimaryUserId() != null ? (iclubC.getIclubPersonByCPrimaryUserId()).getPId() : null);
				iCC.setIclubPersonByCCrtdBy(iclubC.getIclubPersonByCCrtdBy() != null ? (iclubC.getIclubPersonByCCrtdBy()).getPId() : null);

				if (iclubC.getIclubCohortClaims() != null && iclubC.getIclubCohortClaims().size() > 0) {
					String[] iclubCohortClaims = new String[iclubC.getIclubCohortClaims().size()];
					int i = 0;
					for (IclubCohortClaim iclubCohortClaim : iclubC.getIclubCohortClaims()) {
						iclubCohortClaims[i] = iclubCohortClaim.getCcId();
						i++;
					}
					iCC.setIclubCohortClaims(iclubCohortClaims);
				}

				if (iclubC.getIclubCohortPersons() != null && iclubC.getIclubCohortPersons().size() > 0) {
					String[] iclubCohortPersons = new String[iclubC.getIclubCohortPersons().size()];
					int i = 0;
					for (IclubCohortPerson iclubCohortPerson : iclubC.getIclubCohortPersons()) {
						iclubCohortPersons[i] = iclubCohortPerson.getCpId();
						i++;
					}
					iCC.setIclubCohortPersons(iclubCohortPersons);
				}

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
	public IclubCohortModel getById(@PathParam("id") String id) {
		IclubCohortModel model = new IclubCohortModel();
		try {
			IclubCohort bean = iclubCohortDAO.findById(id);

			model.setCId(bean.getCId());
			model.setCId(bean.getCId());
			model.setCName(bean.getCName());
			model.setCEmail(bean.getCEmail());
			model.setCInitDt(bean.getCInitDt());
			model.setCFinalizeDt(bean.getCFinalizeDt());
			model.setCTotalContrib(bean.getCTotalContrib());
			model.setCCollectedContrib(bean.getCCollectedContrib());
			model.setCCurMemberCnt(bean.getCCurMemberCnt());
			model.setIclubCohortType(bean.getIclubCohortType() != null ? (bean.getIclubCohortType()).getCtId() : null);
			model.setCCrtdDt(bean.getCCrtdDt());
			model.setIclubPersonByCPrimaryUserId(bean.getIclubPersonByCPrimaryUserId() != null ? (bean.getIclubPersonByCPrimaryUserId()).getPId() : null);
			model.setIclubPersonByCCrtdBy(bean.getIclubPersonByCCrtdBy() != null ? (bean.getIclubPersonByCCrtdBy()).getPId() : null);
			if (bean.getIclubCohortClaims() != null && bean.getIclubCohortClaims().size() > 0) {
				String[] iclubCohortClaims = new String[bean.getIclubCohortClaims().size()];
				int i = 0;
				for (IclubCohortClaim iclubCohortClaim : bean.getIclubCohortClaims()) {
					iclubCohortClaims[i] = iclubCohortClaim.getCcId();
					i++;
				}
				model.setIclubCohortClaims(iclubCohortClaims);
			}

			if (bean.getIclubCohortPersons() != null && bean.getIclubCohortPersons().size() > 0) {
				String[] iclubCohortPersons = new String[bean.getIclubCohortPersons().size()];
				int i = 0;
				for (IclubCohortPerson iclubCohortPerson : bean.getIclubCohortPersons()) {
					iclubCohortPersons[i] = iclubCohortPerson.getCpId();
					i++;
				}
				model.setIclubCohortPersons(iclubCohortPersons);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCohortDAO getIclubCohortTypeDAO() {
		return iclubCohortDAO;
	}

	public void setIclubCohortDAO(IclubCohortDAO iclubCohortDAO) {
		this.iclubCohortDAO = iclubCohortDAO;
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

	public IclubCohortTypeDAO getIclubCohortTypeTypeDAO() {
		return iclubCohortTypeDAO;
	}

	public void setIclubCohortTypeDAO(IclubCohortTypeDAO iclubCohortTypeDAO) {
		this.iclubCohortTypeDAO = iclubCohortTypeDAO;
	}

}
