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

import za.co.iclub.pss.orm.bean.IclubSupplItem;
import za.co.iclub.pss.orm.dao.IclubAssessmentTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSupplItemDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;
import za.co.iclub.pss.ws.model.IclubSupplItemModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubSupplItemService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubSupplItemService {

	protected static final Logger LOGGER = Logger.getLogger(IclubSupplItemService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSupplItemDAO iclubSupplItemDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubSupplMasterDAO iclubSupplMasterDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubAssessmentTypeDAO iclubAssessmentTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubSupplItemModel model) {
		try {
			IclubSupplItem iSt = new IclubSupplItem();

			iSt.setSiId(model.getSiId());
			iSt.setIclubAssessmentType(model.getIclubAssessmentType() != null ? iclubAssessmentTypeDAO.findById(model.getIclubAssessmentType()) : null);
			iSt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iSt.setIclubSupplMaster(model.getIclubSupplMaster() != null ? iclubSupplMasterDAO.findById(model.getIclubSupplMaster()) : null);
			iSt.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iSt.setSiCrtdDt(model.getSiCrtdDt());
			iSt.setSiAssessNumber(model.getSiAssessNumber());
			iSt.setSiItemId(model.getSiItemId());

			iclubSupplItemDAO.save(iSt);

			LOGGER.info("Save Success with ID :: " + iSt.getSiId());

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
	public ResponseModel mod(IclubSupplItemModel model) {
		try {
			IclubSupplItem iSt = new IclubSupplItem();

			iSt.setSiId(model.getSiId());
			iSt.setIclubAssessmentType(model.getIclubAssessmentType() != null ? iclubAssessmentTypeDAO.findById(model.getIclubAssessmentType()) : null);
			iSt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iSt.setIclubSupplMaster(model.getIclubSupplMaster() != null ? iclubSupplMasterDAO.findById(model.getIclubSupplMaster()) : null);
			iSt.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iSt.setSiCrtdDt(model.getSiCrtdDt());
			iSt.setSiAssessNumber(model.getSiAssessNumber());
			iSt.setSiItemId(model.getSiItemId());
			iclubSupplItemDAO.merge(iSt);

			LOGGER.info("Merge Success with ID :: " + model.getSiId());

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
			iclubSupplItemDAO.delete(iclubSupplItemDAO.findById(id));
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
	public <T extends IclubSupplItemModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubSupplItemDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubSupplItem iSt = (IclubSupplItem) object;

					IclubSupplItemModel model = new IclubSupplItemModel();

					model.setSiId(iSt.getSiId());
					model.setIclubAssessmentType(iSt.getIclubAssessmentType() != null ? iSt.getIclubAssessmentType().getAtId() : null);
					model.setIclubInsuranceItemType(iSt.getIclubInsuranceItemType() != null ? iSt.getIclubInsuranceItemType().getIitId() : null);
					model.setIclubSupplMaster(iSt.getIclubSupplMaster() != null ? iSt.getIclubSupplMaster().getSmId() : null);
					model.setIclubPerson(iSt.getIclubPerson() != null ? iSt.getIclubPerson().getPId() : null);
					model.setSiCrtdDt(iSt.getSiCrtdDt());
					model.setSiAssessNumber(iSt.getSiAssessNumber());
					model.setSiItemId(iSt.getSiItemId());

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
	public IclubSupplItemModel getById(@PathParam("id") String id) {
		IclubSupplItemModel model = new IclubSupplItemModel();
		try {
			IclubSupplItem bean = iclubSupplItemDAO.findById(id);

			model.setSiId(bean.getSiId());
			model.setIclubAssessmentType(bean.getIclubAssessmentType() != null ? bean.getIclubAssessmentType().getAtId() : null);
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
			model.setIclubSupplMaster(bean.getIclubSupplMaster() != null ? bean.getIclubSupplMaster().getSmId() : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setSiCrtdDt(bean.getSiCrtdDt());
			model.setSiAssessNumber(bean.getSiAssessNumber());
			model.setSiItemId(bean.getSiItemId());

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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubSupplItem.class.getSimpleName());
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

	public IclubSupplItemDAO getIclubSupplItemDAO() {
		return iclubSupplItemDAO;
	}

	public void setIclubSupplItemDAO(IclubSupplItemDAO iclubSupplItemDAO) {
		this.iclubSupplItemDAO = iclubSupplItemDAO;
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

	public IclubSupplMasterDAO getIclubSupplMasterDAO() {
		return iclubSupplMasterDAO;
	}

	public void setIclubSupplMasterDAO(IclubSupplMasterDAO iclubSupplMasterDAO) {
		this.iclubSupplMasterDAO = iclubSupplMasterDAO;
	}

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubAssessmentTypeDAO getIclubAssessmentTypeDAO() {
		return iclubAssessmentTypeDAO;
	}

	public void setIclubAssessmentTypeDAO(IclubAssessmentTypeDAO iclubAssessmentTypeDAO) {
		this.iclubAssessmentTypeDAO = iclubAssessmentTypeDAO;
	}
}
