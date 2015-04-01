package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import za.co.iclub.pss.orm.bean.IclubLogin;
import za.co.iclub.pss.orm.bean.IclubSecurityQuestion;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityQuestionDAO;
import za.co.iclub.pss.ws.model.IclubSecurityQuestionModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSecurityQuestionService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSecurityQuestionService {

	protected static final Logger LOGGER = Logger.getLogger(IclubSecurityQuestionService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSecurityQuestionDAO iclubSecurityQuestionDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubSecurityQuestionModel model) {
		try {
			IclubSecurityQuestion iSq = new IclubSecurityQuestion();

			iSq.setSqId(iclubCommonDAO.getNextId(IclubSecurityQuestion.class));
			iSq.setSqLongDesc(model.getSqLongDesc());
			iSq.setSqShortDesc(model.getSqShortDesc());
			iSq.setSqStatus(model.getSqStatus());

			iclubSecurityQuestionDAO.save(iSq);

			LOGGER.info("Save Success with ID :: " + iSq.getSqId());

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
	public ResponseModel mod(IclubSecurityQuestionModel model) {
		try {
			IclubSecurityQuestion iSq = new IclubSecurityQuestion();

			iSq.setSqId(model.getSqId());
			iSq.setSqLongDesc(model.getSqLongDesc());
			iSq.setSqShortDesc(model.getSqShortDesc());
			iSq.setSqStatus(model.getSqStatus());

			iclubSecurityQuestionDAO.merge(iSq);

			LOGGER.info("Merge Success with ID :: " + model.getSqId());

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
			iclubSecurityQuestionDAO.delete(iclubSecurityQuestionDAO.findById(id));
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
	public <T extends IclubSecurityQuestionModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSecurityQuestionDAO.findAll();

			for (Object object : batmod) {
				IclubSecurityQuestion iSq = (IclubSecurityQuestion) object;

				IclubSecurityQuestionModel model = new IclubSecurityQuestionModel();

				model.setSqId(iSq.getSqId());
				model.setSqLongDesc(iSq.getSqLongDesc());
				model.setSqShortDesc(iSq.getSqShortDesc());
				model.setSqStatus(iSq.getSqStatus());

				if (iSq.getIclubLogins() != null && iSq.getIclubLogins().size() > 0) {
					Set<IclubLogin> iLog = iSq.getIclubLogins();
					String[] iclubLogins = new String[iLog.size()];

					int i = 0;
					for (IclubLogin iL : iLog) {
						iclubLogins[i] = iL.getLId();
						i++;
					}
					model.setIclubLogins(iclubLogins);
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
	public IclubSecurityQuestionModel getById(@PathParam("id") Long id) {
		IclubSecurityQuestionModel model = new IclubSecurityQuestionModel();
		try {
			IclubSecurityQuestion bean = iclubSecurityQuestionDAO.findById(id);

			model.setSqId(bean.getSqId());
			model.setSqLongDesc(bean.getSqLongDesc());
			model.setSqShortDesc(bean.getSqShortDesc());
			model.setSqStatus(bean.getSqStatus());

			if (bean.getIclubLogins() != null && bean.getIclubLogins().size() > 0) {
				Set<IclubLogin> iLog = bean.getIclubLogins();
				String[] iclubLogins = new String[iLog.size()];

				int i = 0;
				for (IclubLogin iL : iLog) {
					iclubLogins[i] = iL.getLId();
					i++;
				}
				model.setIclubLogins(iclubLogins);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubSecurityQuestion.class.getSimpleName());
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

	public IclubSecurityQuestionDAO getIclubSecurityQuestionDAO() {
		return iclubSecurityQuestionDAO;
	}

	public void setIclubSecurityQuestionDAO(IclubSecurityQuestionDAO iclubSecurityQuestionDAO) {
		this.iclubSecurityQuestionDAO = iclubSecurityQuestionDAO;
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
