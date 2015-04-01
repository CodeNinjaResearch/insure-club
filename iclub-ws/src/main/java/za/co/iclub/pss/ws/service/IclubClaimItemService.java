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

import za.co.iclub.pss.orm.bean.IclubClaimItem;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubClaimItemDAO;
import za.co.iclub.pss.orm.dao.IclubClaimStatusDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubSupplMasterDAO;
import za.co.iclub.pss.ws.model.IclubClaimItemModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubClaimItemService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubClaimItemService {

	protected static final Logger LOGGER = Logger.getLogger(IclubClaimItemService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubSupplMasterDAO iclubSupplMasterDAO;
	private IclubInsuranceItemDAO iclubInsuranceItemDAO;
	private IclubClaimDAO iclubClaimDAO;
	private IclubClaimStatusDAO iclubClaimStatusDAO;
	private IclubClaimItemDAO iclubClaimItemDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubClaimItemModel model) {
		try {
			IclubClaimItem iCCI = new IclubClaimItem();

			iCCI.setCiId(model.getCiId());
			iCCI.setCiCrtdBy(model.getCiCrtdBy());
			iCCI.setCiCrtdDt(model.getCiCrtdDt());
			iCCI.setCiValue(model.getCiValue());
			iCCI.setIclubSupplMasterByCiHandlerId(model.getIclubSupplMasterByCiHandlerId() != null && !model.getIclubSupplMasterByCiHandlerId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiHandlerId()) : null);
			iCCI.setIclubInsuranceItem(model.getIclubInsuranceItem() != null && !model.getIclubInsuranceItem().trim().equalsIgnoreCase("") ? iclubInsuranceItemDAO.findById(model.getIclubInsuranceItem()) : null);
			iCCI.setIclubSupplMasterByCiAssesorId(model.getIclubSupplMasterByCiAssesorId() != null && !model.getIclubSupplMasterByCiAssesorId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiAssesorId()) : null);
			iCCI.setIclubClaim(model.getIclubClaim() != null && !model.getIclubClaim().trim().equalsIgnoreCase("") ? iclubClaimDAO.findById(model.getIclubClaim()) : null);
			iCCI.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);

			iclubClaimItemDAO.save(iCCI);

			LOGGER.info("Save Success with ID :: " + iCCI.getCiId());

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
	public ResponseModel mod(IclubClaimItemModel model) {
		try {
			IclubClaimItem iCCI = new IclubClaimItem();

			iCCI.setCiId(model.getCiId());
			iCCI.setCiCrtdBy(model.getCiCrtdBy());
			iCCI.setCiCrtdDt(model.getCiCrtdDt());
			iCCI.setCiValue(model.getCiValue());
			iCCI.setIclubSupplMasterByCiHandlerId(model.getIclubSupplMasterByCiHandlerId() != null && !model.getIclubSupplMasterByCiHandlerId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiHandlerId()) : null);
			iCCI.setIclubInsuranceItem(model.getIclubInsuranceItem() != null && !model.getIclubInsuranceItem().trim().equalsIgnoreCase("") ? iclubInsuranceItemDAO.findById(model.getIclubInsuranceItem()) : null);
			iCCI.setIclubSupplMasterByCiAssesorId(model.getIclubSupplMasterByCiAssesorId() != null && !model.getIclubSupplMasterByCiAssesorId().trim().equalsIgnoreCase("") ? iclubSupplMasterDAO.findById(model.getIclubSupplMasterByCiAssesorId()) : null);
			iCCI.setIclubClaim(model.getIclubClaim() != null && !model.getIclubClaim().trim().equalsIgnoreCase("") ? iclubClaimDAO.findById(model.getIclubClaim()) : null);
			iCCI.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);

			iclubClaimItemDAO.merge(iCCI);

			LOGGER.info("Merge Success with ID :: " + model.getCiId());

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
			iclubClaimItemDAO.delete(iclubClaimItemDAO.findById(id));
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
	public <T extends IclubClaimItemModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubClaimItemDAO.findAll();

			for (Object object : batmod) {
				IclubClaimItem iCCI = (IclubClaimItem) object;

				IclubClaimItemModel model = new IclubClaimItemModel();

				model.setCiId(iCCI.getCiId());
				model.setCiCrtdBy(iCCI.getCiCrtdBy());
				model.setCiCrtdDt(iCCI.getCiCrtdDt());
				model.setCiValue(iCCI.getCiValue());
				model.setIclubClaimStatus(iCCI.getIclubClaimStatus() != null ? (iCCI.getIclubClaimStatus().getCsId()) : null);
				model.setIclubClaim(iCCI.getIclubClaim() != null ? (iCCI.getIclubClaim().getCId()) : null);
				model.setIclubSupplMasterByCiAssesorId(iCCI.getIclubSupplMasterByCiAssesorId() != null ? (iCCI.getIclubSupplMasterByCiAssesorId().getSmId()) : null);
				model.setIclubInsuranceItem(iCCI.getIclubInsuranceItem() != null ? (iCCI.getIclubInsuranceItem().getIiId()) : null);
				model.setIclubSupplMasterByCiHandlerId(iCCI.getIclubSupplMasterByCiHandlerId() != null ? (iCCI.getIclubSupplMasterByCiHandlerId().getSmId()) : null);

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
	public <T extends IclubClaimItemModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubClaimItem.class.getSimpleName());

			for (Object object : batmod) {
				IclubClaimItem iCCI = (IclubClaimItem) object;

				IclubClaimItemModel model = new IclubClaimItemModel();

				model.setCiId(iCCI.getCiId());
				model.setCiCrtdBy(iCCI.getCiCrtdBy());
				model.setCiCrtdDt(iCCI.getCiCrtdDt());
				model.setCiValue(iCCI.getCiValue());
				model.setIclubClaimStatus(iCCI.getIclubClaimStatus() != null ? (iCCI.getIclubClaimStatus().getCsId()) : null);
				model.setIclubClaim(iCCI.getIclubClaim() != null ? (iCCI.getIclubClaim().getCId()) : null);
				model.setIclubSupplMasterByCiAssesorId(iCCI.getIclubSupplMasterByCiAssesorId() != null ? (iCCI.getIclubSupplMasterByCiAssesorId().getSmId()) : null);
				model.setIclubInsuranceItem(iCCI.getIclubInsuranceItem() != null ? (iCCI.getIclubInsuranceItem().getIiId()) : null);
				model.setIclubSupplMasterByCiHandlerId(iCCI.getIclubSupplMasterByCiHandlerId() != null ? (iCCI.getIclubSupplMasterByCiHandlerId().getSmId()) : null);

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
	public IclubClaimItemModel getById(@PathParam("id") String id) {
		IclubClaimItemModel model = new IclubClaimItemModel();
		try {
			IclubClaimItem bean = iclubClaimItemDAO.findById(id);

			model.setCiId(bean.getCiId());
			model.setCiCrtdBy(bean.getCiCrtdBy());
			model.setCiCrtdDt(bean.getCiCrtdDt());
			model.setCiValue(bean.getCiValue());
			model.setIclubClaimStatus(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsId()) : null);
			model.setIclubClaim(bean.getIclubClaim() != null ? (bean.getIclubClaim().getCId()) : null);
			model.setIclubSupplMasterByCiAssesorId(bean.getIclubSupplMasterByCiAssesorId() != null ? (bean.getIclubSupplMasterByCiAssesorId().getSmId()) : null);
			model.setIclubInsuranceItem(bean.getIclubInsuranceItem() != null ? (bean.getIclubInsuranceItem().getIiId()) : null);
			model.setIclubSupplMasterByCiHandlerId(bean.getIclubSupplMasterByCiHandlerId() != null ? (bean.getIclubSupplMasterByCiHandlerId().getSmId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubSupplMasterDAO getIclubSupplMasterDAO() {
		return iclubSupplMasterDAO;
	}

	public void setIclubSupplMasterDAO(IclubSupplMasterDAO iclubSupplMasterDAO) {
		this.iclubSupplMasterDAO = iclubSupplMasterDAO;
	}

	public IclubInsuranceItemDAO getIclubInsuranceItemDAO() {
		return iclubInsuranceItemDAO;
	}

	public void setIclubInsuranceItemDAO(IclubInsuranceItemDAO iclubInsuranceItemDAO) {
		this.iclubInsuranceItemDAO = iclubInsuranceItemDAO;
	}

	public IclubClaimDAO getIclubClaimDAO() {
		return iclubClaimDAO;
	}

	public void setIclubClaimDAO(IclubClaimDAO iclubClaimDAO) {
		this.iclubClaimDAO = iclubClaimDAO;
	}

	public IclubClaimStatusDAO getIclubClaimStatusDAO() {
		return iclubClaimStatusDAO;
	}

	public void setIclubClaimStatusDAO(IclubClaimStatusDAO iclubClaimStatusDAO) {
		this.iclubClaimStatusDAO = iclubClaimStatusDAO;
	}

	public IclubClaimItemDAO getIclubClaimItemDAO() {
		return iclubClaimItemDAO;
	}

	public void setIclubClaimItemDAO(IclubClaimItemDAO iclubClaimItemDAO) {
		this.iclubClaimItemDAO = iclubClaimItemDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
