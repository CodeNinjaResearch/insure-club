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

import za.co.iclub.pss.orm.bean.IclubProperty;
import za.co.iclub.pss.orm.bean.IclubRoofType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubRoofTypeDAO;
import za.co.iclub.pss.ws.model.IclubRoofTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubRoofTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubRoofTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubRoofTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubRoofTypeDAO iclubRoofTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubRoofTypeModel model) {
		try {
			IclubRoofType iRt = new IclubRoofType();

			iRt.setRtId(iclubCommonDAO.getNextId(IclubRoofType.class));
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());

			iclubRoofTypeDAO.save(iRt);

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
	public ResponseModel mod(IclubRoofTypeModel model) {
		try {
			IclubRoofType iRt = new IclubRoofType();

			iRt.setRtId(model.getRtId());
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());

			iclubRoofTypeDAO.merge(iRt);

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
			iclubRoofTypeDAO.delete(iclubRoofTypeDAO.findById(id));
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
	public <T extends IclubRoofTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubRoofTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubRoofType iRt = (IclubRoofType) object;

					IclubRoofTypeModel model = new IclubRoofTypeModel();

					model.setRtId(iRt.getRtId());
					model.setRtLongDesc(iRt.getRtLongDesc());
					model.setRtShortDesc(iRt.getRtShortDesc());
					model.setRtStatus(iRt.getRtStatus());

					if (iRt.getIclubProperties() != null && iRt.getIclubProperties().size() > 0) {
						String[] properties = new String[iRt.getIclubProperties().size()];
						int i = 0;
						for (IclubProperty property : iRt.getIclubProperties()) {
							properties[i] = property.getPId();
							i++;
						}

						model.setIclubProperties(properties);
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
	public IclubRoofTypeModel getById(@PathParam("id") Long id) {
		IclubRoofTypeModel model = new IclubRoofTypeModel();
		try {
			IclubRoofType bean = iclubRoofTypeDAO.findById(id);

			model.setRtId(bean.getRtId());
			model.setRtLongDesc(bean.getRtLongDesc());
			model.setRtShortDesc(bean.getRtShortDesc());
			model.setRtStatus(bean.getRtStatus());

			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] properties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty property : bean.getIclubProperties()) {
					properties[i] = property.getPId();
					i++;
				}
				model.setIclubProperties(properties);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubRoofType.class.getSimpleName());
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

	public IclubRoofTypeDAO getIclubRoofTypeDAO() {
		return iclubRoofTypeDAO;
	}

	public void setIclubRoofTypeDAO(IclubRoofTypeDAO iclubRoofTypeDAO) {
		this.iclubRoofTypeDAO = iclubRoofTypeDAO;
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
