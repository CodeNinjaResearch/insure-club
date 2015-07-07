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
import za.co.iclub.pss.orm.bean.IclubEventType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEventTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.IclubEventTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubEventTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubEventTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubEventTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubEventTypeDAO iclubEventTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubEventTypeModel model) {
		try {
			IclubEventType iEt = new IclubEventType();

			iEt.setEtId(iclubCommonDAO.getNextId(IclubEventType.class));
			iEt.setEtLongDesc(model.getEtLongDesc());
			iEt.setEtShortDesc(model.getEtShortDesc());
			iEt.setEtStatus(model.getEtStatus());

			iclubEventTypeDAO.save(iEt);

			LOGGER.info("Save Success with ID :: " + iEt.getEtId());

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
	public ResponseModel mod(IclubEventTypeModel model) {
		try {
			IclubEventType iEt = new IclubEventType();

			iEt.setEtId(model.getEtId());
			iEt.setEtLongDesc(model.getEtLongDesc());
			iEt.setEtShortDesc(model.getEtShortDesc());
			iEt.setEtStatus(model.getEtStatus());

			iclubEventTypeDAO.merge(iEt);

			LOGGER.info("Merge Success with ID :: " + model.getEtId());

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
			iclubEventTypeDAO.delete(iclubEventTypeDAO.findById(id));
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
	public <T extends IclubEventTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubEventTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubEventType iEt = (IclubEventType) object;

					IclubEventTypeModel model = new IclubEventTypeModel();

					model.setEtId(iEt.getEtId());
					model.setEtLongDesc(iEt.getEtLongDesc());
					model.setEtShortDesc(iEt.getEtShortDesc());
					model.setEtStatus(iEt.getEtStatus());

					if (iEt.getIclubEvents() != null && iEt.getIclubEvents().size() > 0) {
						String[] iclubEvents = new String[iEt.getIclubEvents().size()];
						int i = 0;
						for (IclubEvent iclubEvent : iEt.getIclubEvents()) {
							iclubEvents[i] = iclubEvent.getEId();
							i++;
						}
						model.setIclubEvents(iclubEvents);
					}

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
	public IclubEventTypeModel getById(@PathParam("id") Long id) {
		IclubEventTypeModel model = new IclubEventTypeModel();
		try {
			IclubEventType bean = iclubEventTypeDAO.findById(id);

			model.setEtId(bean.getEtId());
			model.setEtLongDesc(bean.getEtLongDesc());
			model.setEtShortDesc(bean.getEtShortDesc());
			model.setEtStatus(bean.getEtStatus());

			if (bean.getIclubEvents() != null && bean.getIclubEvents().size() > 0) {
				String[] iclubEvents = new String[bean.getIclubEvents().size()];
				int i = 0;
				for (IclubEvent iclubEvent : bean.getIclubEvents()) {
					iclubEvents[i] = iclubEvent.getEId();
					i++;
				}
				model.setIclubEvents(iclubEvents);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubEventType.class.getSimpleName());
			ResponseModel message = new ResponseModel();
			if ((data != null) && (data.size() > 0)) {
				message.setStatusCode(Integer.valueOf(1));
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(Integer.valueOf(0));
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(2));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	public IclubEventTypeDAO getIclubEventTypeDAO() {
		return iclubEventTypeDAO;
	}

	public void setIclubEventTypeDAO(IclubEventTypeDAO iclubEventTypeDAO) {
		this.iclubEventTypeDAO = iclubEventTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
