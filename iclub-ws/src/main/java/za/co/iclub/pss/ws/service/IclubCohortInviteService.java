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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubCohortInvite;
import za.co.iclub.pss.orm.dao.IclubCohortDAO;
import za.co.iclub.pss.orm.dao.IclubCohortInviteDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubCohortInviteModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubCohortInviteService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubCohortInviteService {

	private static final Logger LOGGER = Logger.getLogger(IclubCohortInviteService.class);
	private IclubCohortInviteDAO IclubCohortInviteDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubCohortDAO iclubCohortDAO;
	private IclubNotificationTypeDAO iclubNotificationTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubCohortInviteModel model) {
		try {

			IclubCohortInvite iCC = new IclubCohortInvite();

			iCC.setCiId(model.getCiId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setIclubNotificationType(iclubNotificationTypeDAO.findById(model.getIclubNotificationType()));
			iCC.setCiCrtdDt(model.getCiCrtdDt());
			iCC.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCC.setCiInviteAcceptYn(model.getCiInviteAcceptYn());
			iCC.setCiInviteUri(model.getCiInviteUri());
			iCC.setCiInviteFName(model.getCiInviteFName());
			iCC.setCiInviteLName(model.getCiInviteLName());
			IclubCohortInviteDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCiId());

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
	public ResponseModel addList(Collection<? extends IclubCohortInviteModel> models) {
		try {

			for (IclubCohortInviteModel model : models) {

				IclubCohortInvite iCC = new IclubCohortInvite();

				iCC.setCiId(model.getCiId());
				iCC.setIclubCohort(model.getIclubCohort() != null ? iclubCohortDAO.findById(model.getIclubCohort()) : null);
				iCC.setIclubNotificationType(model.getIclubNotificationType() != null ? iclubNotificationTypeDAO.findById(model.getIclubNotificationType()) : null);
				iCC.setCiCrtdDt(model.getCiCrtdDt());
				iCC.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
				iCC.setCiInviteAcceptYn(model.getCiInviteAcceptYn());
				iCC.setCiInviteUri(model.getCiInviteUri());
				iCC.setCiInviteFName(model.getCiInviteFName());
				iCC.setCiInviteLName(model.getCiInviteLName());
				IclubCohortInviteDAO.save(iCC);

				LOGGER.info("Save Success with ID :: " + iCC.getCiId());
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
	public ResponseModel mod(IclubCohortInviteModel model) {
		try {
			IclubCohortInvite iCC = new IclubCohortInvite();

			iCC.setCiId(model.getCiId());
			iCC.setIclubCohort(iclubCohortDAO.findById(model.getIclubCohort()));
			iCC.setCiCrtdDt(model.getCiCrtdDt());
			iCC.setCiInviteAcceptYn(model.getCiInviteAcceptYn());
			iCC.setCiInviteUri(model.getCiInviteUri());
			iCC.setIclubNotificationType(iclubNotificationTypeDAO.findById(model.getIclubNotificationType()));
			iCC.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCC.setCiInviteFName(model.getCiInviteFName());
			iCC.setCiInviteLName(model.getCiInviteLName());

			IclubCohortInviteDAO.merge(iCC);

			LOGGER.info("Save Success with ID :: " + model.getCiId());

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
			IclubCohortInviteDAO.delete(IclubCohortInviteDAO.findById(id));
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
	public <T extends IclubCohortInviteModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = IclubCohortInviteDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortInvite iclubC = (IclubCohortInvite) object;
					IclubCohortInviteModel iCC = new IclubCohortInviteModel();

					iCC.setCiId(iclubC.getCiId());
					iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
					iCC.setCiCrtdDt(iclubC.getCiCrtdDt());
					iCC.setIclubNotificationType((iclubC.getIclubNotificationType()) != null ? iclubC.getIclubNotificationType().getNtId() : null);
					iCC.setCiInviteAcceptYn(iclubC.getCiInviteAcceptYn());
					iCC.setCiInviteUri(iclubC.getCiInviteUri());
					iCC.setIclubPerson(iclubC.getIclubPerson() != null ? (iclubC.getIclubPerson()).getPId() : null);
					iCC.setCiInviteFName(iclubC.getCiInviteFName());
					iCC.setCiInviteLName(iclubC.getCiInviteLName());
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
	public <T extends IclubCohortInviteModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubCohortInvite.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubCohortInvite iclubC = (IclubCohortInvite) object;
					IclubCohortInviteModel iCC = new IclubCohortInviteModel();

					iCC.setCiId(iclubC.getCiId());
					iCC.setIclubCohort(iclubC.getIclubCohort() != null ? (iclubC.getIclubCohort()).getCId() : null);
					iCC.setCiCrtdDt(iclubC.getCiCrtdDt());
					iCC.setIclubNotificationType((iclubC.getIclubNotificationType()) != null ? iclubC.getIclubNotificationType().getNtId() : null);
					iCC.setCiInviteAcceptYn(iclubC.getCiInviteAcceptYn());
					iCC.setCiInviteUri(iclubC.getCiInviteUri());
					iCC.setIclubPerson(iclubC.getIclubPerson() != null ? (iclubC.getIclubPerson()).getPId() : null);
					iCC.setCiInviteFName(iclubC.getCiInviteFName());
					iCC.setCiInviteLName(iclubC.getCiInviteLName());
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
	public IclubCohortInviteModel getById(@PathParam("id") String id) {
		IclubCohortInviteModel model = new IclubCohortInviteModel();
		try {
			IclubCohortInvite bean = IclubCohortInviteDAO.findById(id);

			model.setCiId(bean.getCiId());
			model.setIclubCohort(bean.getIclubCohort() != null ? (bean.getIclubCohort()).getCId() : null);
			model.setCiCrtdDt(bean.getCiCrtdDt());
			model.setIclubNotificationType((bean.getIclubNotificationType()) != null ? bean.getIclubNotificationType().getNtId() : null);
			model.setCiInviteAcceptYn(bean.getCiInviteAcceptYn());
			model.setCiInviteUri(bean.getCiInviteUri());
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson()).getPId() : null);
			model.setCiInviteFName(bean.getCiInviteFName());
			model.setCiInviteLName(bean.getCiInviteLName());

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCohortInviteDAO getIclubCohortInviteDAO() {
		return IclubCohortInviteDAO;
	}

	public void setIclubCohortInviteDAO(IclubCohortInviteDAO IclubCohortInviteDAO) {
		this.IclubCohortInviteDAO = IclubCohortInviteDAO;
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

	public IclubNotificationTypeDAO getIclubNotificationTypeDAO() {
		return iclubNotificationTypeDAO;
	}

	public void setIclubNotificationTypeDAO(IclubNotificationTypeDAO iclubNotificationTypeDAO) {
		this.iclubNotificationTypeDAO = iclubNotificationTypeDAO;
	}

}
