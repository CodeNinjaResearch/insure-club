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

import za.co.iclub.pss.orm.bean.IclubOccupiedStatus;
import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubOccupiedStatusDAO;
import za.co.iclub.pss.ws.model.IclubOccupiedStatusModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubOccupiedStatusService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubOccupiedStatusService {

	protected static final Logger LOGGER = Logger.getLogger(IclubOccupiedStatusService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubOccupiedStatusDAO iclubOccupiedStatusDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubOccupiedStatusModel model) {
		try {
			IclubOccupiedStatus iOs = new IclubOccupiedStatus();

			iOs.setOsId(iclubCommonDAO.getNextId(IclubOccupiedStatus.class));
			iOs.setOsLongDesc(model.getOsLongDesc());
			iOs.setOsShortDesc(model.getOsShortDesc());
			iOs.setOsStatus(model.getOsStatus());

			iclubOccupiedStatusDAO.save(iOs);

			LOGGER.info("Save Success with ID :: " + iOs.getOsId());

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
	public ResponseModel mod(IclubOccupiedStatusModel model) {
		try {
			IclubOccupiedStatus iOs = new IclubOccupiedStatus();

			iOs.setOsId(model.getOsId());
			iOs.setOsLongDesc(model.getOsLongDesc());
			iOs.setOsShortDesc(model.getOsShortDesc());
			iOs.setOsStatus(model.getOsStatus());

			iclubOccupiedStatusDAO.merge(iOs);

			LOGGER.info("Merge Success with ID :: " + model.getOsId());

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
			iclubOccupiedStatusDAO.delete(iclubOccupiedStatusDAO.findById(id));
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
	public <T extends IclubOccupiedStatusModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubOccupiedStatusDAO.findAll();

			for (Object object : batmod) {
				IclubOccupiedStatus iOs = (IclubOccupiedStatus) object;

				IclubOccupiedStatusModel model = new IclubOccupiedStatusModel();

				model.setOsId(iOs.getOsId());
				model.setOsLongDesc(iOs.getOsLongDesc());
				model.setOsShortDesc(iOs.getOsShortDesc());
				model.setOsStatus(iOs.getOsStatus());
				
				if (iOs.getIclubProperties() != null && iOs.getIclubProperties().size() > 0) {
					String[] iclubProperties = new String[iOs.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iOs.getIclubProperties()) {
						iclubProperties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(iclubProperties);
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
	public IclubOccupiedStatusModel getById(@PathParam("id") Long id) {
		IclubOccupiedStatusModel model = new IclubOccupiedStatusModel();
		try {
			IclubOccupiedStatus bean = iclubOccupiedStatusDAO.findById(id);

			model.setOsId(bean.getOsId());
			model.setOsLongDesc(bean.getOsLongDesc());
			model.setOsShortDesc(bean.getOsShortDesc());
			model.setOsStatus(bean.getOsStatus());
			
			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] iclubProperties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty iclubProperty : bean.getIclubProperties()) {
					iclubProperties[i] = iclubProperty.getPId();
					i++;
				}
				model.setIclubProperties(iclubProperties);
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
			List data = iclubOccupiedStatusDAO.getOccupiedStatusBySD(val, id);
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

	public IclubOccupiedStatusDAO getIclubOccupiedStatusDAO() {
		return iclubOccupiedStatusDAO;
	}

	public void setIclubOccupiedStatusDAO(IclubOccupiedStatusDAO iclubOccupiedStatusDAO) {
		this.iclubOccupiedStatusDAO = iclubOccupiedStatusDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
