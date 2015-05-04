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

import za.co.iclub.pss.orm.bean.IclubAssessmentType;
import za.co.iclub.pss.orm.bean.IclubSupplItem;
import za.co.iclub.pss.orm.dao.IclubAssessmentTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.IclubAssessmentTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAssessmentTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAssessmentTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubAssessmentTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubAssessmentTypeDAO iclubAssessmentTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubAssessmentTypeModel model) {
		try {
			IclubAssessmentType iAt = new IclubAssessmentType();

			iAt.setAtId(iclubCommonDAO.getNextId(IclubAssessmentType.class));
			iAt.setAtLongDesc(model.getAtLongDesc());
			iAt.setAtShortDesc(model.getAtShortDesc());
			iAt.setAtStatus(model.getAtStatus());

			iclubAssessmentTypeDAO.save(iAt);

			LOGGER.info("Save Success with ID :: " + iAt.getAtId());

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
	public ResponseModel mod(IclubAssessmentTypeModel model) {
		try {
			IclubAssessmentType iAt = new IclubAssessmentType();

			iAt.setAtId(model.getAtId());
			iAt.setAtLongDesc(model.getAtLongDesc());
			iAt.setAtShortDesc(model.getAtShortDesc());
			iAt.setAtStatus(model.getAtStatus());

			iclubAssessmentTypeDAO.merge(iAt);

			LOGGER.info("Merge Success with ID :: " + model.getAtId());

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
			iclubAssessmentTypeDAO.delete(iclubAssessmentTypeDAO.findById(id));
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
	public <T extends IclubAssessmentTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubAssessmentTypeDAO.findAll();

			for (Object object : batmod) {
				IclubAssessmentType iAt = (IclubAssessmentType) object;

				IclubAssessmentTypeModel model = new IclubAssessmentTypeModel();

				model.setAtId(iAt.getAtId());
				model.setAtLongDesc(iAt.getAtLongDesc());
				model.setAtShortDesc(iAt.getAtShortDesc());
				model.setAtStatus(iAt.getAtStatus());

				if (iAt.getIclubSupplItems() != null && iAt.getIclubSupplItems().size() > 0) {
					String[] supplItems = new String[iAt.getIclubSupplItems().size()];
					int i = 0;
					for (IclubSupplItem supplItem : iAt.getIclubSupplItems()) {
						supplItems[i] = supplItem.getSiId();
						i++;
					}
					model.setIclubSupplItems(supplItems);
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
	public IclubAssessmentTypeModel getById(@PathParam("id") Long id) {
		IclubAssessmentTypeModel model = new IclubAssessmentTypeModel();
		try {
			IclubAssessmentType bean = iclubAssessmentTypeDAO.findById(id);

			model.setAtId(bean.getAtId());
			model.setAtLongDesc(bean.getAtLongDesc());
			model.setAtShortDesc(bean.getAtShortDesc());
			model.setAtStatus(bean.getAtStatus());

			if (bean.getIclubSupplItems() != null && bean.getIclubSupplItems().size() > 0) {
				String[] supplItems = new String[bean.getIclubSupplItems().size()];
				int i = 0;
				for (IclubSupplItem supplItem : bean.getIclubSupplItems()) {
					supplItems[i] = supplItem.getSiId();
					i++;
				}
				model.setIclubSupplItems(supplItems);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubAssessmentType.class.getSimpleName());
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

	public IclubAssessmentTypeDAO getIclubAssessmentTypeDAO() {
		return iclubAssessmentTypeDAO;
	}

	public void setIclubAssessmentTypeDAO(IclubAssessmentTypeDAO iclubAssessmentTypeDAO) {
		this.iclubAssessmentTypeDAO = iclubAssessmentTypeDAO;
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
