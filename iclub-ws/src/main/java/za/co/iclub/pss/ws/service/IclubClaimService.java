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

import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubClaimItem;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.dao.IclubClaimDAO;
import za.co.iclub.pss.orm.dao.IclubClaimStatusDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsurerMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubPolicyDAO;
import za.co.iclub.pss.orm.dao.IclubProductTypeDAO;
import za.co.iclub.pss.ws.model.IclubClaimModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubClaimService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubClaimService {

	protected static final Logger LOGGER = Logger.getLogger(IclubClaimService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubClaimDAO iclubClaimDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubProductTypeDAO iclubProductTypeDAO;
	private IclubInsurerMasterDAO iclubInsurerMasterDAO;
	private IclubPolicyDAO iclubPolicyDAO;
	private IclubClaimStatusDAO iclubClaimStatusDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubClaimModel model) {
		try {
			IclubClaim iCC = new IclubClaim();

			iCC.setCId(model.getCId());
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setCValue(model.getCValue());
			iCC.setCNumItems(model.getCNumItems());
			iCC.setCNumber(model.getCNumber());

			iCC.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);
			iCC.setIclubPolicy(model.getIclubPolicy() != null ? iclubPolicyDAO.findById(model.getIclubPolicy()) : null);
			iCC.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubClaimDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCId());

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
	public ResponseModel mod(IclubClaimModel model) {
		try {
			IclubClaim iCC = new IclubClaim();

			iCC.setCId(model.getCId());
			iCC.setCCrtdDt(model.getCCrtdDt());
			iCC.setCValue(model.getCValue());
			iCC.setCNumItems(model.getCNumItems());
			iCC.setCNumber(model.getCNumber());

			iCC.setIclubClaimStatus(model.getIclubClaimStatus() != null ? iclubClaimStatusDAO.findById(model.getIclubClaimStatus()) : null);
			iCC.setIclubPolicy(model.getIclubPolicy() != null ? iclubPolicyDAO.findById(model.getIclubPolicy()) : null);
			iCC.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubClaimDAO.merge(iCC);

			LOGGER.info("Merge Success with ID :: " + model.getCId());

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
			iclubClaimDAO.delete(iclubClaimDAO.findById(id));
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
	public <T extends IclubClaimModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubClaimDAO.findAll();

			for (Object object : batmod) {
				IclubClaim iCC = (IclubClaim) object;

				IclubClaimModel model = new IclubClaimModel();

				model.setCId(iCC.getCId());
				model.setCCrtdDt(iCC.getCCrtdDt());
				model.setCValue(iCC.getCValue());
				model.setCNumItems(iCC.getCNumItems());
				model.setCNumber(iCC.getCNumber());
				model.setIclubPolicy(iCC.getIclubPolicy() != null ? (iCC.getIclubPolicy().getPId()) : null);
				model.setIclubClaimStatus(iCC.getIclubClaimStatus() != null ? (iCC.getIclubClaimStatus().getCsId()) : null);
				model.setIclubPerson(iCC.getIclubPerson() != null ? (iCC.getIclubPerson().getPId()) : null);

				if (iCC.getIclubPayments() != null && iCC.getIclubPayments().size() > 0) {
					String[] payments = new String[iCC.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment payment : iCC.getIclubPayments()) {
						payments[i] = payment.getPId();
						i++;
					}
					model.setIclubPayments(payments);
				}

				if (iCC.getIclubClaimItems() != null && iCC.getIclubClaimItems().size() > 0) {
					String[] claimItems = new String[iCC.getIclubClaimItems().size()];
					int i = 0;
					for (IclubClaimItem claimItem : iCC.getIclubClaimItems()) {
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
	public <T extends IclubClaimModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubClaim.class.getSimpleName());

			for (Object object : batmod) {
				IclubClaim iCC = (IclubClaim) object;

				IclubClaimModel model = new IclubClaimModel();

				model.setCId(iCC.getCId());
				model.setCCrtdDt(iCC.getCCrtdDt());
				model.setCValue(iCC.getCValue());
				model.setCNumItems(iCC.getCNumItems());
				model.setCNumber(iCC.getCNumber());
				model.setIclubPolicy(iCC.getIclubPolicy() != null ? (iCC.getIclubPolicy().getPId()) : null);
				model.setIclubClaimStatus(iCC.getIclubClaimStatus() != null ? (iCC.getIclubClaimStatus().getCsId()) : null);
				model.setIclubPerson(iCC.getIclubPerson() != null ? (iCC.getIclubPerson().getPId()) : null);

				if (iCC.getIclubPayments() != null && iCC.getIclubPayments().size() > 0) {
					String[] payments = new String[iCC.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment payment : iCC.getIclubPayments()) {
						payments[i] = payment.getPId();
						i++;
					}
					model.setIclubPayments(payments);
				}

				if (iCC.getIclubClaimItems() != null && iCC.getIclubClaimItems().size() > 0) {
					String[] claimItems = new String[iCC.getIclubClaimItems().size()];
					int i = 0;
					for (IclubClaimItem claimItem : iCC.getIclubClaimItems()) {
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
	public IclubClaimModel getById(@PathParam("id") String id) {
		IclubClaimModel model = new IclubClaimModel();
		try {
			IclubClaim bean = iclubClaimDAO.findById(id);

			model.setCId(bean.getCId());
			model.setCCrtdDt(bean.getCCrtdDt());
			model.setCValue(bean.getCValue());
			model.setCNumItems(bean.getCNumItems());
			model.setCNumber(bean.getCNumber());
			model.setIclubPolicy(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPId()) : null);
			model.setIclubClaimStatus(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

			if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
				String[] payments = new String[bean.getIclubPayments().size()];
				int i = 0;
				for (IclubPayment payment : bean.getIclubPayments()) {
					payments[i] = payment.getPId();
					i++;
				}
				
				model.setIclubPayments(payments);
			}

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
	
	@GET
	@Path("/getByPolicyId/{policyId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubClaimModel getByPolicyId(@PathParam("policyId") String policyId) {
		IclubClaimModel model = new IclubClaimModel();
		try {
			IclubClaim bean = iclubNamedQueryDAO.findByPolicyId(policyId);

			model.setCId(bean.getCId());
			model.setCCrtdDt(bean.getCCrtdDt());
			model.setCValue(bean.getCValue());
			model.setCNumItems(bean.getCNumItems());
			model.setCNumber(bean.getCNumber());
			model.setIclubPolicy(bean.getIclubPolicy() != null ? (bean.getIclubPolicy().getPId()) : null);
			model.setIclubClaimStatus(bean.getIclubClaimStatus() != null ? (bean.getIclubClaimStatus().getCsId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

			if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
				String[] payments = new String[bean.getIclubPayments().size()];
				int i = 0;
				for (IclubPayment payment : bean.getIclubPayments()) {
					payments[i] = payment.getPId();
					i++;
				}
				
				model.setIclubPayments(payments);
			}

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


	public IclubClaimDAO getIclubClaimDAO() {
		return iclubClaimDAO;
	}

	public void setIclubClaimDAO(IclubClaimDAO iclubClaimDAO) {
		this.iclubClaimDAO = iclubClaimDAO;
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

	public IclubProductTypeDAO getIclubProductTypeDAO() {
		return iclubProductTypeDAO;
	}

	public void setIclubProductTypeDAO(IclubProductTypeDAO iclubProductTypeDAO) {
		this.iclubProductTypeDAO = iclubProductTypeDAO;
	}

	public IclubInsurerMasterDAO getIclubInsurerMasterDAO() {
		return iclubInsurerMasterDAO;
	}

	public void setIclubInsurerMasterDAO(IclubInsurerMasterDAO iclubInsurerMasterDAO) {
		this.iclubInsurerMasterDAO = iclubInsurerMasterDAO;
	}

	public IclubPolicyDAO getIclubPolicyDAO() {
		return iclubPolicyDAO;
	}

	public void setIclubPolicyDAO(IclubPolicyDAO iclubPolicyDAO) {
		this.iclubPolicyDAO = iclubPolicyDAO;
	}

	public IclubClaimStatusDAO getIclubClaimStatusDAO() {
		return iclubClaimStatusDAO;
	}

	public void setIclubClaimStatusDAO(IclubClaimStatusDAO iclubClaimStatusDAO) {
		this.iclubClaimStatusDAO = iclubClaimStatusDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
