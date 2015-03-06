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
import za.co.iclub.pss.orm.bean.IclubMessage;
import za.co.iclub.pss.orm.bean.IclubSystemType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSystemTypeDAO;
import za.co.iclub.pss.ws.model.IclubSystemTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSystemTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSystemTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubSystemTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSystemTypeDAO iclubSystemTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubSystemTypeModel model) {
		try {
			IclubSystemType iCSt = new IclubSystemType();

			iCSt.setStId(iclubCommonDAO.getNextId(IclubSystemType.class));
			iCSt.setStLongDesc(model.getStLongDesc());
			iCSt.setStShortDesc(model.getStShortDesc());
			iCSt.setStStatus(model.getStStatus());

			iclubSystemTypeDAO.save(iCSt);

			LOGGER.info("Save Success with ID :: " + iCSt.getStId());

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
	public ResponseModel mod(IclubSystemTypeModel model) {
		try {
			IclubSystemType iCSt = new IclubSystemType();

			iCSt.setStId(model.getStId());
			iCSt.setStLongDesc(model.getStLongDesc());
			iCSt.setStShortDesc(model.getStShortDesc());
			iCSt.setStStatus(model.getStStatus());

			iclubSystemTypeDAO.merge(iCSt);

			LOGGER.info("Merge Success with ID :: " + model.getStId());

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
			iclubSystemTypeDAO.delete(iclubSystemTypeDAO.findById(id));
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
	public <T extends IclubSystemTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSystemTypeDAO.findAll();

			for (Object object : batmod) {
				IclubSystemType iCSt = (IclubSystemType) object;

				IclubSystemTypeModel model = new IclubSystemTypeModel();

				model.setStId(iCSt.getStId());
				model.setStLongDesc(iCSt.getStLongDesc());
				model.setStShortDesc(iCSt.getStShortDesc());
				model.setStStatus(iCSt.getStStatus());

				if (iCSt.getIclubMessagesForMFromSysId() != null && iCSt.getIclubMessagesForMFromSysId().size() > 0) {
					String[] messagesForMFromSysId = new String[iCSt.getIclubMessagesForMFromSysId().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iCSt.getIclubMessagesForMFromSysId()) {
						messagesForMFromSysId[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessagesForMFromSysId(messagesForMFromSysId);
				}

				if (iCSt.getIclubMessagesForMToSysId() != null && iCSt.getIclubMessagesForMToSysId().size() > 0) {
					String[] messagesForMToSysId = new String[iCSt.getIclubMessagesForMToSysId().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iCSt.getIclubMessagesForMToSysId()) {
						messagesForMToSysId[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessagesForMToSysId(messagesForMToSysId);
				}

				ret.add((T) model);
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
	public <T extends IclubSystemTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSystemTypeDAO.findAll();

			for (Object object : batmod) {
				IclubSystemType iCSt = (IclubSystemType) object;

				IclubSystemTypeModel model = new IclubSystemTypeModel();

				model.setStId(iCSt.getStId());
				model.setStLongDesc(iCSt.getStLongDesc());
				model.setStShortDesc(iCSt.getStShortDesc());
				model.setStStatus(iCSt.getStStatus());
				if (iCSt.getIclubMessagesForMFromSysId() != null && iCSt.getIclubMessagesForMFromSysId().size() > 0) {
					String[] messagesForMFromSysId = new String[iCSt.getIclubMessagesForMFromSysId().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iCSt.getIclubMessagesForMFromSysId()) {
						messagesForMFromSysId[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessagesForMFromSysId(messagesForMFromSysId);
				}

				if (iCSt.getIclubMessagesForMToSysId() != null && iCSt.getIclubMessagesForMToSysId().size() > 0) {
					String[] messagesForMToSysId = new String[iCSt.getIclubMessagesForMToSysId().size()];
					int i = 0;
					for (IclubMessage iclubMessage : iCSt.getIclubMessagesForMToSysId()) {
						messagesForMToSysId[i] = iclubMessage.getMId();
						i++;
					}
					model.setIclubMessagesForMToSysId(messagesForMToSysId);
				}

				ret.add((T) model);
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
	public IclubSystemTypeModel getById(@PathParam("id") Long id) {
		IclubSystemTypeModel model = new IclubSystemTypeModel();
		try {
			IclubSystemType bean = iclubSystemTypeDAO.findById(id);

			model.setStId(bean.getStId());
			model.setStLongDesc(bean.getStLongDesc());
			model.setStShortDesc(bean.getStShortDesc());
			model.setStStatus(bean.getStStatus());

			if (bean.getIclubMessagesForMFromSysId() != null && bean.getIclubMessagesForMFromSysId().size() > 0) {
				String[] messagesForMFromSysId = new String[bean.getIclubMessagesForMFromSysId().size()];
				int i = 0;
				for (IclubMessage iclubMessage : bean.getIclubMessagesForMFromSysId()) {
					messagesForMFromSysId[i] = iclubMessage.getMId();
					i++;
				}
				model.setIclubMessagesForMFromSysId(messagesForMFromSysId);
			}

			if (bean.getIclubMessagesForMToSysId() != null && bean.getIclubMessagesForMToSysId().size() > 0) {
				String[] messagesForMToSysId = new String[bean.getIclubMessagesForMToSysId().size()];
				int i = 0;
				for (IclubMessage iclubMessage : bean.getIclubMessagesForMToSysId()) {
					messagesForMToSysId[i] = iclubMessage.getMId();
					i++;
				}
				model.setIclubMessagesForMToSysId(messagesForMToSysId);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubSystemTypeDAO getIclubSystemTypeDAO() {
		return iclubSystemTypeDAO;
	}

	public void setIclubSystemTypeDAO(IclubSystemTypeDAO iclubSystemTypeDAO) {
		this.iclubSystemTypeDAO = iclubSystemTypeDAO;
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

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}
}
