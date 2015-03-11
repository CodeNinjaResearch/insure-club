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
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubInsuranceItemModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubInsuranceItemService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubInsuranceItemService {

	protected static final Logger LOGGER = Logger.getLogger(IclubInsuranceItemService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubInsuranceItemDAO iclubInsuranceItemDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubInsuranceItemModel model) {
		try {
			IclubInsuranceItem iCTt = new IclubInsuranceItem();

			iCTt.setIiId(model.getIiId());
			iCTt.setIiItemId(model.getIiItemId());
			iCTt.setIiQuoteId(model.getIiQuoteId());
			iCTt.setIiCrtdDt(model.getIiCrtdDt());
			iCTt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iCTt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iclubInsuranceItemDAO.save(iCTt);

			LOGGER.info("Save Success with ID :: " + iCTt.getIiId());

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
	public ResponseModel mod(IclubInsuranceItemModel model) {
		try {
			IclubInsuranceItem iCTt = new IclubInsuranceItem();

			iCTt.setIiId(model.getIiId());
			iCTt.setIiItemId(model.getIiItemId());
			iCTt.setIiQuoteId(model.getIiQuoteId());
			iCTt.setIiCrtdDt(model.getIiCrtdDt());
			iCTt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iCTt.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubInsuranceItemDAO.merge(iCTt);

			LOGGER.info("Merge Success with ID :: " + model.getIiId());

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
			iclubInsuranceItemDAO.delete(iclubInsuranceItemDAO.findById(id));
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
	public <T extends IclubInsuranceItemModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsuranceItemDAO.findAll();

			for (Object object : batmod) {
				IclubInsuranceItem iCTt = (IclubInsuranceItem) object;

				IclubInsuranceItemModel model = new IclubInsuranceItemModel();

				model.setIiId(iCTt.getIiId());
				model.setIiItemId(iCTt.getIiItemId());
				model.setIiQuoteId(iCTt.getIiQuoteId());
				model.setIiCrtdDt(iCTt.getIiCrtdDt());
				model.setIclubInsuranceItemType(iCTt.getIclubInsuranceItemType() != null ? (iCTt.getIclubInsuranceItemType().getIitId()) : null);
				model.setIclubPerson(iCTt.getIclubPerson() != null ? (iCTt.getIclubPerson().getPId()) : null);

				if (iCTt.getIclubClaimItems() != null && iCTt.getIclubClaimItems().size() > 0) {
					String[] claimItems = new String[iCTt.getIclubClaimItems().size()];
					int i = 0;
					for (IclubClaimItem claimItem : iCTt.getIclubClaimItems()) {
						claimItems[i] = claimItem.getCiId();
						i++;
					}
					model.setIclubClaimItems(claimItems);
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
	public <T extends IclubInsuranceItemModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsuranceItemDAO.findByUser(user);

			for (Object object : batmod) {
				IclubInsuranceItem iCTt = (IclubInsuranceItem) object;

				IclubInsuranceItemModel model = new IclubInsuranceItemModel();

				model.setIiId(iCTt.getIiId());
				model.setIiItemId(iCTt.getIiItemId());
				model.setIiQuoteId(iCTt.getIiQuoteId());
				model.setIiCrtdDt(iCTt.getIiCrtdDt());
				model.setIclubInsuranceItemType(iCTt.getIclubInsuranceItemType() != null ? (iCTt.getIclubInsuranceItemType().getIitId()) : null);
				model.setIclubPerson(iCTt.getIclubPerson() != null ? (iCTt.getIclubPerson().getPId()) : null);

				if (iCTt.getIclubClaimItems() != null && iCTt.getIclubClaimItems().size() > 0) {
					String[] claimItems = new String[iCTt.getIclubClaimItems().size()];
					int i = 0;
					for (IclubClaimItem claimItem : iCTt.getIclubClaimItems()) {
						claimItems[i] = claimItem.getCiId();
						i++;
					}
					model.setIclubClaimItems(claimItems);
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
	public IclubInsuranceItemModel getById(@PathParam("id") String id) {
		IclubInsuranceItemModel model = new IclubInsuranceItemModel();
		try {
			IclubInsuranceItem bean = iclubInsuranceItemDAO.findById(id);

			model.setIiId(bean.getIiId());
			model.setIiItemId(bean.getIiItemId());
			model.setIiQuoteId(bean.getIiQuoteId());
			model.setIiCrtdDt(bean.getIiCrtdDt());
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? (bean.getIclubInsuranceItemType().getIitId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

			if (bean.getIclubClaimItems() != null && bean.getIclubClaimItems().size() > 0) {
				String[] claimItems = new String[bean.getIclubClaimItems().size()];
				int i = 0;
				for (IclubClaimItem claimItem : bean.getIclubClaimItems()) {
					claimItems[i] = claimItem.getCiId();
					i++;
				}
				model.setIclubClaimItems(claimItems);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubInsuranceItemDAO getIclubInsuranceItemDAO() {
		return iclubInsuranceItemDAO;
	}

	public void setIclubInsuranceItemDAO(IclubInsuranceItemDAO iclubInsuranceItemDAO) {
		this.iclubInsuranceItemDAO = iclubInsuranceItemDAO;
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