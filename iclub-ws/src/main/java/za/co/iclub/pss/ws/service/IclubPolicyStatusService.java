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

import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.bean.IclubPolicyStatus;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyStatusDAO;
import za.co.iclub.pss.ws.model.IclubPolicyStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPolicyStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPolicyStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPolicyStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPolicyStatusDAO iclubPolicyStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPolicyStatusModel model) {
		try {
			IclubPolicyStatus iPs = new IclubPolicyStatus();

			iPs.setPsId(iclubCommonDAO.getNextId(IclubPolicyStatus.class));
			iPs.setPsLongDesc(model.getPsLongDesc());
			iPs.setPsShortDesc(model.getPsShortDesc());
			iPs.setPsStatus(model.getPsStatus());

			iclubPolicyStatusDAO.save(iPs);

			LOGGER.info("Save Success with ID :: " + iPs.getPsId());

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
	public ResponseModel mod(IclubPolicyStatusModel model) {
		try {
			IclubPolicyStatus iPs = new IclubPolicyStatus();

			iPs.setPsId(model.getPsId());
			iPs.setPsLongDesc(model.getPsLongDesc());
			iPs.setPsShortDesc(model.getPsShortDesc());
			iPs.setPsStatus(model.getPsStatus());

			iclubPolicyStatusDAO.merge(iPs);

			LOGGER.info("Merge Success with ID :: " + model.getPsId());

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
			iclubPolicyStatusDAO.delete(iclubPolicyStatusDAO.findById(id));
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
	public <T extends IclubPolicyStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPolicyStatusDAO.findAll();

			for (Object object : batmod) {
				IclubPolicyStatus iPs = (IclubPolicyStatus) object;

				IclubPolicyStatusModel model = new IclubPolicyStatusModel();

				model.setPsId(iPs.getPsId());
				model.setPsLongDesc(iPs.getPsLongDesc());
				model.setPsShortDesc(iPs.getPsShortDesc());
				model.setPsStatus(iPs.getPsStatus());

				if (iPs.getIclubPolicies() != null && iPs.getIclubPolicies().size() > 0) {
					String[] iclubPolicies = new String[iPs.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy iclubPolicy : iPs.getIclubPolicies()) {
						iclubPolicies[i] = iclubPolicy.getPId();
						i++;
					}
					model.setIclubPolicies(iclubPolicies);
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
	public IclubPolicyStatusModel getById(@PathParam("id") Long id) {
		IclubPolicyStatusModel model = new IclubPolicyStatusModel();
		try {
			IclubPolicyStatus bean = iclubPolicyStatusDAO.findById(id);

			model.setPsId(bean.getPsId());
			model.setPsLongDesc(bean.getPsLongDesc());
			model.setPsShortDesc(bean.getPsShortDesc());
			model.setPsStatus(bean.getPsStatus());

			if (bean.getIclubPolicies() != null && bean.getIclubPolicies().size() > 0) {
				String[] iclubPolicies = new String[bean.getIclubPolicies().size()];
				int i = 0;
				for (IclubPolicy iclubPolicy : bean.getIclubPolicies()) {
					iclubPolicies[i] = iclubPolicy.getPId();
					i++;
				}
				model.setIclubPolicies(iclubPolicies);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubPolicyStatus.class.getSimpleName());
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

	public IclubPolicyStatusDAO getIclubPolicyStatusDAO() {
		return iclubPolicyStatusDAO;
	}

	public void setIclubPolicyStatusDAO(IclubPolicyStatusDAO iclubPolicyStatusDAO) {
		this.iclubPolicyStatusDAO = iclubPolicyStatusDAO;
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
