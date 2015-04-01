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
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMessageDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubMessageModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubMessageService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubMessageService {

	protected static final Logger LOGGER = Logger.getLogger(IclubMessageService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubMessageDAO iclubMessageDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubMessageModel model) {
		try {
			IclubMessage iCM = new IclubMessage();

			model.setMId(iCM.getMId());
			model.setMContent(iCM.getMContent());
			model.setMTranId(iCM.getMTranId());
			model.setMCrtdDt(iCM.getMCrtdDt());
			model.setMSentDt(iCM.getMSentDt());
			model.setIclubPerson(iCM.getIclubPerson() != null ? iCM.getIclubPerson().getPId() : null);
			model.setIclubMessageType(iCM.getIclubMessageType() != null ? iCM.getIclubMessageType().getMtId() : null);
			model.setIclubSystemTypeByMFromSysId(iCM.getIclubSystemTypeByMFromSysId() != null ? iCM.getIclubSystemTypeByMFromSysId().getStId() : null);
			model.setIclubSystemTypeByMToSysId(iCM.getIclubSystemTypeByMToSysId() != null ? iCM.getIclubSystemTypeByMToSysId().getStId() : null);

			iclubMessageDAO.save(iCM);

			LOGGER.info("Save Success with ID :: " + iCM.getMId());

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
	public ResponseModel mod(IclubMessageModel model) {
		try {
			IclubMessage iCM = new IclubMessage();

			model.setMId(iCM.getMId());
			model.setMContent(iCM.getMContent());
			model.setMTranId(iCM.getMTranId());
			model.setMCrtdDt(iCM.getMCrtdDt());
			model.setMSentDt(iCM.getMSentDt());
			model.setIclubPerson(iCM.getIclubPerson() != null ? iCM.getIclubPerson().getPId() : null);
			model.setIclubMessageType(iCM.getIclubMessageType() != null ? iCM.getIclubMessageType().getMtId() : null);
			model.setIclubSystemTypeByMFromSysId(iCM.getIclubSystemTypeByMFromSysId() != null ? iCM.getIclubSystemTypeByMFromSysId().getStId() : null);
			model.setIclubSystemTypeByMToSysId(iCM.getIclubSystemTypeByMToSysId() != null ? iCM.getIclubSystemTypeByMToSysId().getStId() : null);

			iclubMessageDAO.merge(iCM);

			LOGGER.info("Merge Success with ID :: " + model.getMId());

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
	public Response del(@PathParam("id") String id) {
		try {
			iclubMessageDAO.delete(iclubMessageDAO.findById(id));
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
	public <T extends IclubMessageModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMessageDAO.findAll();

			for (Object object : batmod) {
				IclubMessage iCM = (IclubMessage) object;

				IclubMessageModel model = new IclubMessageModel();

				model.setMId(iCM.getMId());
				model.setMContent(iCM.getMContent());
				model.setMTranId(iCM.getMTranId());
				model.setMCrtdDt(iCM.getMCrtdDt());
				model.setMSentDt(iCM.getMSentDt());
				model.setIclubPerson(iCM.getIclubPerson() != null ? iCM.getIclubPerson().getPId() : null);
				model.setIclubMessageType(iCM.getIclubMessageType() != null ? iCM.getIclubMessageType().getMtId() : null);
				model.setIclubSystemTypeByMFromSysId(iCM.getIclubSystemTypeByMFromSysId() != null ? iCM.getIclubSystemTypeByMFromSysId().getStId() : null);
				model.setIclubSystemTypeByMToSysId(iCM.getIclubSystemTypeByMToSysId() != null ? iCM.getIclubSystemTypeByMToSysId().getStId() : null);

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
	public <T extends IclubMessageModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubMessage.class.getSimpleName());

			for (Object object : batmod) {
				IclubMessage iCM = (IclubMessage) object;

				IclubMessageModel model = new IclubMessageModel();

				model.setMId(iCM.getMId());
				model.setMContent(iCM.getMContent());
				model.setMTranId(iCM.getMTranId());
				model.setMCrtdDt(iCM.getMCrtdDt());
				model.setMSentDt(iCM.getMSentDt());
				model.setIclubPerson(iCM.getIclubPerson() != null ? iCM.getIclubPerson().getPId() : null);
				model.setIclubMessageType(iCM.getIclubMessageType() != null ? iCM.getIclubMessageType().getMtId() : null);
				model.setIclubSystemTypeByMFromSysId(iCM.getIclubSystemTypeByMFromSysId() != null ? iCM.getIclubSystemTypeByMFromSysId().getStId() : null);
				model.setIclubSystemTypeByMToSysId(iCM.getIclubSystemTypeByMToSysId() != null ? iCM.getIclubSystemTypeByMToSysId().getStId() : null);

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
	public IclubMessageModel getById(@PathParam("id") String id) {
		IclubMessageModel model = new IclubMessageModel();
		try {
			IclubMessage bean = iclubMessageDAO.findById(id);

			model.setMId(bean.getMId());
			model.setMContent(bean.getMContent());
			model.setMTranId(bean.getMTranId());
			model.setMCrtdDt(bean.getMCrtdDt());
			model.setMSentDt(bean.getMSentDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubMessageType(bean.getIclubMessageType() != null ? bean.getIclubMessageType().getMtId() : null);
			model.setIclubSystemTypeByMFromSysId(bean.getIclubSystemTypeByMFromSysId() != null ? bean.getIclubSystemTypeByMFromSysId().getStId() : null);
			model.setIclubSystemTypeByMToSysId(bean.getIclubSystemTypeByMToSysId() != null ? bean.getIclubSystemTypeByMToSysId().getStId() : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubMessageDAO getIclubMessageDAO() {
		return iclubMessageDAO;
	}

	public void setIclubMessageDAO(IclubMessageDAO iclubMessageDAO) {
		this.iclubMessageDAO = iclubMessageDAO;
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

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
