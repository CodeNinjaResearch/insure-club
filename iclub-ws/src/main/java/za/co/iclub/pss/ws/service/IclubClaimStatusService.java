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

import za.co.iclub.pss.orm.bean.IclubClaimStatus;
import za.co.iclub.pss.orm.dao.IclubClaimStatusDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.ws.model.IclubClaimStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubClaimStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubClaimStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubClaimStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubClaimStatusDAO iclubClaimStatusDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubClaimStatusModel model) {
		try {
			IclubClaimStatus iCS = new IclubClaimStatus();

			iCS.setCsId(iclubCommonDAO.getNextId(IclubClaimStatus.class));
			iCS.setCsLongDesc(model.getCsLongDesc());
			iCS.setCsShortDesc(model.getCsShortDesc());
			iCS.setCsStatus(model.getCsStatus());

			iclubClaimStatusDAO.save(iCS);

			LOGGER.info("Save Success with ID :: " + iCS.getCsId());

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
	public ResponseModel mod(IclubClaimStatusModel model) {
		try {
			IclubClaimStatus iCS = new IclubClaimStatus();

			iCS.setCsId(model.getCsId());
			iCS.setCsLongDesc(model.getCsLongDesc());
			iCS.setCsShortDesc(model.getCsShortDesc());
			iCS.setCsStatus(model.getCsStatus());

			iclubClaimStatusDAO.merge(iCS);

			LOGGER.info("Merge Success with ID :: " + model.getCsId());

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
			iclubClaimStatusDAO.delete(iclubClaimStatusDAO.findById(id));
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
	public <T extends IclubClaimStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubClaimStatusDAO.findAll();

			for (Object object : batmod) {
				IclubClaimStatus iCS = (IclubClaimStatus) object;

				IclubClaimStatusModel model = new IclubClaimStatusModel();

				model.setCsId(iCS.getCsId());
				model.setCsLongDesc(iCS.getCsLongDesc());
				model.setCsShortDesc(iCS.getCsShortDesc());
				model.setCsStatus(iCS.getCsStatus());

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
	public IclubClaimStatusModel getById(@PathParam("id") Long id) {
		IclubClaimStatusModel model = new IclubClaimStatusModel();
		try {
			IclubClaimStatus bean = iclubClaimStatusDAO.findById(id);

			model.setCsId(bean.getCsId());
			model.setCsLongDesc(bean.getCsLongDesc());
			model.setCsShortDesc(bean.getCsShortDesc());
			model.setCsStatus(bean.getCsStatus());

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
			List data = iclubClaimStatusDAO.getClaimStatusBySD(val, id);
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

	public IclubClaimStatusDAO getIclubClaimStatusDAO() {
		return iclubClaimStatusDAO;
	}

	public void setIclubClaimStatusDAO(IclubClaimStatusDAO iclubClaimStatusDAO) {
		this.iclubClaimStatusDAO = iclubClaimStatusDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
