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

import za.co.iclub.pss.orm.bean.IclubNotif;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNotifDAO;
import za.co.iclub.pss.orm.dao.IclubNotificationTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubNotifModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubNotifService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubNotifService {

	private static final Logger LOGGER = Logger.getLogger(IclubNotifService.class);
	private IclubNotifDAO iclubNotifDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubNotificationTypeDAO iclubNotificationTypeDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubNotifModel model) {
		try {

			IclubNotif iCN = new IclubNotif();

			iCN.setNId(model.getNId());
			iCN.setNTitle(model.getNTitle());
			iCN.setNBody(model.getNBody());
			iCN.setNFromAddr(model.getNFromAddr());
			iCN.setNToList(model.getNToList());
			iCN.setNCrtdDt(model.getNCrtdDt());
			iCN.setIclubNotificationType(iclubNotificationTypeDAO.findById(model.getIclubNotificationType()));
			iCN.setNStatus(model.getNStatus());
			iCN.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubNotifDAO.save(iCN);

			LOGGER.info("Save Success with ID :: " + iCN.getNId());

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
	public ResponseModel mod(IclubNotifModel model) {
		try {
			IclubNotif iCN = new IclubNotif();

			iCN.setNId(model.getNId());
			iCN.setNTitle(model.getNTitle());
			iCN.setNBody(model.getNBody());
			iCN.setNFromAddr(model.getNFromAddr());
			iCN.setNToList(model.getNToList());
			iCN.setNCrtdDt(model.getNCrtdDt());
			iCN.setIclubNotificationType(iclubNotificationTypeDAO.findById(model.getIclubNotificationType()));
			iCN.setNStatus(model.getNStatus());
			iCN.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubNotifDAO.merge(iCN);

			LOGGER.info("Save Success with ID :: " + model.getNId());

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
			iclubNotifDAO.delete(iclubNotifDAO.findById(id));
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
	public <T extends IclubNotifModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNotifDAO.findAll();

			for (Object object : batmod) {
				IclubNotif iClubNotf = (IclubNotif) object;
				IclubNotifModel iCN = new IclubNotifModel();

				iCN.setNId(iClubNotf.getNId());
				iCN.setNTitle(iClubNotf.getNTitle());
				iCN.setNBody(iClubNotf.getNBody());
				iCN.setNFromAddr(iClubNotf.getNFromAddr());
				iCN.setNToList(iClubNotf.getNToList());
				iCN.setNCrtdDt(iClubNotf.getNCrtdDt());
				iCN.setIclubPerson(iClubNotf.getIclubPerson() != null ? iClubNotf.getIclubPerson().getPId() : null);
				iCN.setIclubNotificationType(iClubNotf.getIclubNotificationType() != null ? iClubNotf.getIclubNotificationType().getNtId() : null);
				iCN.setNStatus(iClubNotf.getNStatus());

				ret.add((T) iCN);
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
	public <T extends IclubNotifModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNotifDAO.findByUser(user);

			for (Object object : batmod) {
				IclubNotif iClubNotf = (IclubNotif) object;
				IclubNotifModel iCN = new IclubNotifModel();

				iCN.setNId(iClubNotf.getNId());
				iCN.setNTitle(iClubNotf.getNTitle());
				iCN.setNBody(iClubNotf.getNBody());
				iCN.setNFromAddr(iClubNotf.getNFromAddr());
				iCN.setNToList(iClubNotf.getNToList());
				iCN.setNCrtdDt(iClubNotf.getNCrtdDt());
				iCN.setIclubPerson(iClubNotf.getIclubPerson() != null ? iClubNotf.getIclubPerson().getPId() : null);
				iCN.setIclubNotificationType(iClubNotf.getIclubNotificationType() != null ? iClubNotf.getIclubNotificationType().getNtId() : null);
				iCN.setNStatus(iClubNotf.getNStatus());

				ret.add((T) iCN);
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
	public IclubNotifModel getById(@PathParam("id") String id) {
		IclubNotifModel model = new IclubNotifModel();
		try {
			IclubNotif bean = iclubNotifDAO.findById(id);

			model.setNId(bean.getNId());
			model.setNTitle(bean.getNTitle());
			model.setNBody(bean.getNBody());
			model.setNFromAddr(bean.getNFromAddr());
			model.setNToList(bean.getNToList());
			model.setNCrtdDt(bean.getNCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubNotificationType(bean.getIclubNotificationType() != null ? bean.getIclubNotificationType().getNtId() : null);
			model.setNStatus(bean.getNStatus());

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubNotifDAO getIclubNotifDAO() {
		return iclubNotifDAO;
	}

	public void setIclubNotifDAO(IclubNotifDAO iclubNotifDAO) {
		this.iclubNotifDAO = iclubNotifDAO;
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

	public IclubNotificationTypeDAO getIclubNotificationTypeDAO() {
		return iclubNotificationTypeDAO;
	}

	public void setIclubNotificationTypeDAO(IclubNotificationTypeDAO iclubNotificationTypeDAO) {
		this.iclubNotificationTypeDAO = iclubNotificationTypeDAO;
	}

}
