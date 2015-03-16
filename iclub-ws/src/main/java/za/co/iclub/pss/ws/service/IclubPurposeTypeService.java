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
import za.co.iclub.pss.orm.bean.IclubPurposeType;
import za.co.iclub.pss.orm.bean.IclubVehicle;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPurposeTypeDAO;
import za.co.iclub.pss.ws.model.IclubPurposeTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubPurposeTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubPurposeTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubPurposeTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubPurposeTypeDAO iclubPurposeTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPurposeTypeModel model) {
		try {
			IclubPurposeType iCPt = new IclubPurposeType();

			iCPt.setPtId(iclubCommonDAO.getNextId(IclubPurposeType.class));
			iCPt.setPtLongDesc(model.getPtLongDesc());
			iCPt.setPtShortDesc(model.getPtShortDesc());
			iCPt.setPtStatus(model.getPtStatus());
			iCPt.setPtCrtdDt(model.getPtCrtdDt());
			iCPt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCPt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);

			iclubPurposeTypeDAO.save(iCPt);

			LOGGER.info("Save Success with ID :: " + iCPt.getPtId());

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
	public ResponseModel mod(IclubPurposeTypeModel model) {
		try {
			IclubPurposeType iCPt = new IclubPurposeType();

			iCPt.setPtId(model.getPtId());
			iCPt.setPtLongDesc(model.getPtLongDesc());
			iCPt.setPtShortDesc(model.getPtShortDesc());
			iCPt.setPtStatus(model.getPtStatus());
			iCPt.setPtCrtdDt(model.getPtCrtdDt());
			iCPt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCPt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);

			iclubPurposeTypeDAO.merge(iCPt);

			LOGGER.info("Merge Success with ID :: " + model.getPtId());

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
			iclubPurposeTypeDAO.delete(iclubPurposeTypeDAO.findById(id));
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
	public <T extends IclubPurposeTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPurposeTypeDAO.findAll();

			for (Object object : batmod) {
				IclubPurposeType iCPt = (IclubPurposeType) object;

				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

				model.setPtId(iCPt.getPtId());
				model.setPtLongDesc(iCPt.getPtLongDesc());
				model.setPtShortDesc(iCPt.getPtShortDesc());
				model.setPtStatus(iCPt.getPtStatus());
				model.setPtCrtdDt(iCPt.getPtCrtdDt());
				model.setIclubPerson(iCPt.getIclubPerson() != null ? iCPt.getIclubPerson().getPId() : null);
				model.setIclubInsuranceItemType(iCPt.getIclubInsuranceItemType() != null ? iCPt.getIclubInsuranceItemType().getIitId() : null);

				if (iCPt.getIclubProperties() != null && iCPt.getIclubProperties().size() > 0) {
					String[] properties = new String[iCPt.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iCPt.getIclubProperties()) {
						properties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(properties);
				}

				if (iCPt.getIclubVehicles() != null && iCPt.getIclubVehicles().size() > 0) {
					String[] vehicles = new String[iCPt.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle iclubVehicle : iCPt.getIclubVehicles()) {
						vehicles[i] = iclubVehicle.getVId();
						i++;
					}
					model.setIclubVehicles(vehicles);
				}

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	
	@GET
	@Path("/get/insurnceitemtype/{insurnceItemType}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubPurposeTypeModel> List<T> getByItemType(@PathParam("insurnceItemType") String insurnceItemType) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPurposeTypeDAO.findByInsuranceItemType(insurnceItemType);

			for (Object object : batmod) {
				IclubPurposeType iCPt = (IclubPurposeType) object;

				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

				model.setPtId(iCPt.getPtId());
				model.setPtLongDesc(iCPt.getPtLongDesc());
				model.setPtShortDesc(iCPt.getPtShortDesc());
				model.setPtStatus(iCPt.getPtStatus());
				model.setPtCrtdDt(iCPt.getPtCrtdDt());
				model.setIclubPerson(iCPt.getIclubPerson() != null ? iCPt.getIclubPerson().getPId() : null);
				model.setIclubInsuranceItemType(iCPt.getIclubInsuranceItemType() != null ? iCPt.getIclubInsuranceItemType().getIitId() : null);

				if (iCPt.getIclubProperties() != null && iCPt.getIclubProperties().size() > 0) {
					String[] properties = new String[iCPt.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iCPt.getIclubProperties()) {
						properties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(properties);
				}

				if (iCPt.getIclubVehicles() != null && iCPt.getIclubVehicles().size() > 0) {
					String[] vehicles = new String[iCPt.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle iclubVehicle : iCPt.getIclubVehicles()) {
						vehicles[i] = iclubVehicle.getVId();
						i++;
					}
					model.setIclubVehicles(vehicles);
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
	public <T extends IclubPurposeTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPurposeTypeDAO.findByUser(user);

			for (Object object : batmod) {
				IclubPurposeType iCPt = (IclubPurposeType) object;

				IclubPurposeTypeModel model = new IclubPurposeTypeModel();

				model.setPtId(iCPt.getPtId());
				model.setPtLongDesc(iCPt.getPtLongDesc());
				model.setPtShortDesc(iCPt.getPtShortDesc());
				model.setPtStatus(iCPt.getPtStatus());
				model.setPtCrtdDt(iCPt.getPtCrtdDt());
				model.setIclubPerson(iCPt.getIclubPerson() != null ? iCPt.getIclubPerson().getPId() : null);
				model.setIclubInsuranceItemType(iCPt.getIclubInsuranceItemType() != null ? iCPt.getIclubInsuranceItemType().getIitId() : null);

				if (iCPt.getIclubProperties() != null && iCPt.getIclubProperties().size() > 0) {
					String[] properties = new String[iCPt.getIclubProperties().size()];
					int i = 0;
					for (IclubProperty iclubProperty : iCPt.getIclubProperties()) {
						properties[i] = iclubProperty.getPId();
						i++;
					}
					model.setIclubProperties(properties);
				}

				if (iCPt.getIclubVehicles() != null && iCPt.getIclubVehicles().size() > 0) {
					String[] vehicles = new String[iCPt.getIclubVehicles().size()];
					int i = 0;
					for (IclubVehicle iclubVehicle : iCPt.getIclubVehicles()) {
						vehicles[i] = iclubVehicle.getVId();
						i++;
					}
					model.setIclubVehicles(vehicles);
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
	public IclubPurposeTypeModel getById(@PathParam("id") Long id) {
		IclubPurposeTypeModel model = new IclubPurposeTypeModel();
		try {
			IclubPurposeType bean = iclubPurposeTypeDAO.findById(id);

			model.setPtId(bean.getPtId());
			model.setPtLongDesc(bean.getPtLongDesc());
			model.setPtShortDesc(bean.getPtShortDesc());
			model.setPtStatus(bean.getPtStatus());
			model.setPtCrtdDt(bean.getPtCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);

			if (bean.getIclubProperties() != null && bean.getIclubProperties().size() > 0) {
				String[] properties = new String[bean.getIclubProperties().size()];
				int i = 0;
				for (IclubProperty iclubProperty : bean.getIclubProperties()) {
					properties[i] = iclubProperty.getPId();
					i++;
				}
				model.setIclubProperties(properties);
			}

			if (bean.getIclubVehicles() != null && bean.getIclubVehicles().size() > 0) {
				String[] vehicles = new String[bean.getIclubVehicles().size()];
				int i = 0;
				for (IclubVehicle iclubVehicle : bean.getIclubVehicles()) {
					vehicles[i] = iclubVehicle.getVId();
					i++;
				}
				model.setIclubVehicles(vehicles);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPurposeTypeDAO getIclubPurposeTypeDAO() {
		return iclubPurposeTypeDAO;
	}

	public void setIclubPurposeTypeDAO(IclubPurposeTypeDAO iclubPurposeTypeDAO) {
		this.iclubPurposeTypeDAO = iclubPurposeTypeDAO;
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
