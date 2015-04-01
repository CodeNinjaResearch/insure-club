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
import za.co.iclub.pss.orm.bean.IclubRoleType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubRoleTypeDAO;
import za.co.iclub.pss.ws.model.IclubRoleTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubRoleTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubRoleTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubRoleTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubRoleTypeDAO iclubRoleTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;


	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubRoleTypeModel model) {
		try {
			IclubRoleType iRt = new IclubRoleType();

			iRt.setRtId(iclubCommonDAO.getNextId(IclubRoleType.class));
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());

			iclubRoleTypeDAO.save(iRt);

			LOGGER.info("Save Success with ID :: " + iRt.getRtId());

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
	public ResponseModel mod(IclubRoleTypeModel model) {
		try {
			IclubRoleType iRt = new IclubRoleType();

			iRt.setRtId(model.getRtId());
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());

			iclubRoleTypeDAO.merge(iRt);

			LOGGER.info("Merge Success with ID :: " + model.getRtId());

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
			iclubRoleTypeDAO.delete(iclubRoleTypeDAO.findById(id));
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
	public <T extends IclubRoleTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubRoleTypeDAO.findAll();

			for (Object object : batmod) {
				IclubRoleType iRt = (IclubRoleType) object;

				IclubRoleTypeModel model = new IclubRoleTypeModel();

				model.setRtId(iRt.getRtId());
				model.setRtLongDesc(iRt.getRtLongDesc());
				model.setRtShortDesc(iRt.getRtShortDesc());
				model.setRtStatus(iRt.getRtStatus());

				if (iRt.getIclubLogins() != null && iRt.getIclubLogins().size() > 0) {
					Set<IclubLogin> iLog = iRt.getIclubLogins();
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
	public IclubRoleTypeModel getById(@PathParam("id") Long id) {
		IclubRoleTypeModel model = new IclubRoleTypeModel();
		try {
			IclubRoleType bean = iclubRoleTypeDAO.findById(id);

			model.setRtId(bean.getRtId());
			model.setRtLongDesc(bean.getRtLongDesc());
			model.setRtShortDesc(bean.getRtShortDesc());
			model.setRtStatus(bean.getRtStatus());

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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubRoleType.class.getSimpleName());
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

	public IclubRoleTypeDAO getIclubRoleTypeDAO() {
		return iclubRoleTypeDAO;
	}

	public void setIclubRoleTypeDAO(IclubRoleTypeDAO iclubRoleTypeDAO) {
		this.iclubRoleTypeDAO = iclubRoleTypeDAO;
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
