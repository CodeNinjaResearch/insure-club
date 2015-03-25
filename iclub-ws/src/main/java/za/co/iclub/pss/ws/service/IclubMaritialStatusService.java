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

import za.co.iclub.pss.orm.bean.IclubMaritialStatus;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.ws.model.IclubMaritialStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubMaritialStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubMaritialStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubMaritialStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubMaritialStatusModel model) {
		try {
			IclubMaritialStatus iMs = new IclubMaritialStatus();

			iMs.setMsId(iclubCommonDAO.getNextId(IclubMaritialStatus.class));
			iMs.setMsLongDesc(model.getMsLongDesc());
			iMs.setMsShortDesc(model.getMsShortDesc());
			iMs.setMsStatus(model.getMsStatus());

			iclubMaritialStatusDAO.save(iMs);

			LOGGER.info("Save Success with ID :: " + iMs.getMsId());

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
	public ResponseModel mod(IclubMaritialStatusModel model) {
		try {
			IclubMaritialStatus iMs = new IclubMaritialStatus();

			iMs.setMsId(model.getMsId());
			iMs.setMsLongDesc(model.getMsLongDesc());
			iMs.setMsShortDesc(model.getMsShortDesc());
			iMs.setMsStatus(model.getMsStatus());

			iclubMaritialStatusDAO.merge(iMs);

			LOGGER.info("Merge Success with ID :: " + model.getMsId());

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
			iclubMaritialStatusDAO.delete(iclubMaritialStatusDAO.findById(id));
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
	public <T extends IclubMaritialStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMaritialStatusDAO.findAll();

			for (Object object : batmod) {
				IclubMaritialStatus iMs = (IclubMaritialStatus) object;

				IclubMaritialStatusModel model = new IclubMaritialStatusModel();

				model.setMsId(iMs.getMsId());
				model.setMsLongDesc(iMs.getMsLongDesc());
				model.setMsShortDesc(iMs.getMsShortDesc());
				model.setMsStatus(iMs.getMsStatus());

				if (iMs.getIclubPersons() != null && iMs.getIclubPersons().size() > 0) {
					String[] iclubPersons = new String[iMs.getIclubPersons().size()];
					int i = 0;
					for (IclubPerson iclubPerson : iMs.getIclubPersons()) {
						iclubPersons[i] = iclubPerson.getPId();
						i++;
					}
					model.setIclubPersons(iclubPersons);
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
	public IclubMaritialStatusModel getById(@PathParam("id") Long id) {
		IclubMaritialStatusModel model = new IclubMaritialStatusModel();
		try {
			IclubMaritialStatus bean = iclubMaritialStatusDAO.findById(id);

			model.setMsId(bean.getMsId());
			model.setMsLongDesc(bean.getMsLongDesc());
			model.setMsShortDesc(bean.getMsShortDesc());
			model.setMsStatus(bean.getMsStatus());
			
			if (bean.getIclubPersons() != null && bean.getIclubPersons().size() > 0) {
				String[] iclubPersons = new String[bean.getIclubPersons().size()];
				int i = 0;
				for (IclubPerson iclubPerson : bean.getIclubPersons()) {
					iclubPersons[i] = iclubPerson.getPId();
					i++;
				}
				model.setIclubPersons(iclubPersons);
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
			List data = iclubMaritialStatusDAO.getMaritialStatusBySD(val, id);
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

	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}

	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
