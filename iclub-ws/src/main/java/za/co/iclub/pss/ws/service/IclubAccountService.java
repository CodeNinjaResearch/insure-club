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

import za.co.iclub.pss.orm.bean.IclubAccount;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPolicy;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubAccountTypeDAO;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubOwnerTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubAccountModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAccountService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAccountService {

	private static final Logger LOGGER = Logger.getLogger(IclubAccountService.class);
	private IclubAccountDAO iclubAccountDAO;
	private IclubOwnerTypeDAO iclubOwnerTypeDAO;
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubAccountTypeDAO iclubAccountTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubAccountModel model) {
		try {

			IclubAccount iCA = new IclubAccount();

			iCA.setAId(model.getAId());
			iCA.setAAccNum(model.getAAccNum());
			iCA.setACrtdDt(model.getACrtdDt());
			iCA.setAOwnerId(model.getAOwnerId());
			iCA.setIclubBankMaster(iclubBankMasterDAO.findById(model.getIclubBankMaster()));
			iCA.setIclubAccountType(iclubAccountTypeDAO.findById(model.getIclubAccountType()));
			iCA.setIclubOwnerType(iclubOwnerTypeDAO.findById(model.getIclubOwnerType()));
			iCA.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCA.setAStatus(model.getAStatus());

			iclubAccountDAO.save(iCA);

			LOGGER.info("Save Success with ID :: " + iCA.getAId());

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
	@Transactional
	public ResponseModel mod(IclubAccountModel model) {
		try {
			IclubAccount iCA = new IclubAccount();

			iCA.setAId(model.getAId());
			iCA.setAAccNum(model.getAAccNum());
			iCA.setACrtdDt(model.getACrtdDt());
			iCA.setAOwnerId(model.getAOwnerId());
			iCA.setIclubBankMaster(iclubBankMasterDAO.findById(model.getIclubBankMaster()));
			iCA.setIclubAccountType(iclubAccountTypeDAO.findById(model.getIclubAccountType()));
			iCA.setIclubOwnerType(iclubOwnerTypeDAO.findById(model.getIclubOwnerType()));
			iCA.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));
			iCA.setAStatus(model.getAStatus());

			iclubAccountDAO.merge(iCA);

			LOGGER.info("Save Success with ID :: " + model.getAId());

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
	@Transactional
	public Response del(@PathParam("id") String id) {
		try {
			iclubAccountDAO.delete(iclubAccountDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubAccountModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubAccountDAO.findAll();

			for (Object object : batmod) {
				IclubAccount iclubAtype = (IclubAccount) object;
				IclubAccountModel iCA = new IclubAccountModel();

				iCA.setAId(iclubAtype.getAId());
				iCA.setAAccNum(iclubAtype.getAAccNum());
				iCA.setACrtdDt(iclubAtype.getACrtdDt());
				iCA.setAOwnerId(iclubAtype.getAOwnerId());
				iCA.setIclubBankMaster(iclubAtype.getIclubBankMaster() != null ? iclubAtype.getIclubBankMaster().getBmId() : null);
				iCA.setIclubAccountType(iclubAtype.getIclubAccountType() != null ? iclubAtype.getIclubAccountType().getAtId() : null);
				iCA.setIclubOwnerType(iclubAtype.getIclubOwnerType() != null ? iclubAtype.getIclubOwnerType().getOtId() : null);
				iCA.setIclubPerson(iclubAtype.getIclubPerson() != null ? iclubAtype.getIclubPerson().getPId() : null);
				iCA.setAStatus(iclubAtype.getAStatus());

				if (iclubAtype.getIclubPolicies() != null && iclubAtype.getIclubPolicies().size() > 0) {
					String[] policies = new String[iclubAtype.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy policy : iclubAtype.getIclubPolicies()) {

						policies[i] = policy.getPId();
						i++;
					}
					iCA.setIclubPolicies(policies);
				}

				if (iclubAtype.getIclubPayments() != null && iclubAtype.getIclubPayments().size() > 0) {
					String[] payments = new String[iclubAtype.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment payment : iclubAtype.getIclubPayments()) {

						payments[i] = payment.getPId();
						i++;
					}
					iCA.setIclubPayments(payments);
				}

				ret.add((T) iCA);
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
	public <T extends IclubAccountModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubAccount.class.getSimpleName());

			for (Object object : batmod) {
				IclubAccount iclubAtype = (IclubAccount) object;
				IclubAccountModel iCA = new IclubAccountModel();

				iCA.setAId(iclubAtype.getAId());
				iCA.setAAccNum(iclubAtype.getAAccNum());
				iCA.setACrtdDt(iclubAtype.getACrtdDt());
				iCA.setAOwnerId(iclubAtype.getAOwnerId());
				iCA.setIclubBankMaster(iclubAtype.getIclubBankMaster() != null ? iclubAtype.getIclubBankMaster().getBmId() : null);
				iCA.setIclubAccountType(iclubAtype.getIclubAccountType() != null ? iclubAtype.getIclubAccountType().getAtId() : null);
				iCA.setIclubOwnerType(iclubAtype.getIclubOwnerType() != null ? iclubAtype.getIclubOwnerType().getOtId() : null);
				iCA.setIclubPerson(iclubAtype.getIclubPerson() != null ? iclubAtype.getIclubPerson().getPId() : null);
				iCA.setAStatus(iclubAtype.getAStatus());

				if (iclubAtype.getIclubPolicies() != null && iclubAtype.getIclubPolicies().size() > 0) {
					String[] policies = new String[iclubAtype.getIclubPolicies().size()];
					int i = 0;
					for (IclubPolicy policy : iclubAtype.getIclubPolicies()) {

						policies[i] = policy.getPId();
						i++;
					}
					iCA.setIclubPolicies(policies);
				}

				if (iclubAtype.getIclubPayments() != null && iclubAtype.getIclubPayments().size() > 0) {
					String[] payments = new String[iclubAtype.getIclubPayments().size()];
					int i = 0;
					for (IclubPayment payment : iclubAtype.getIclubPayments()) {

						payments[i] = payment.getPId();
						i++;
					}
					iCA.setIclubPayments(payments);
				}

				ret.add((T) iCA);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	@Transactional
	public IclubAccountModel getById(@PathParam("id") String id) {
		IclubAccountModel model = new IclubAccountModel();
		try {
			IclubAccount bean = iclubAccountDAO.findById(id);

			model.setAId(bean.getAId());

			model.setAAccNum(bean.getAAccNum());
			model.setACrtdDt(bean.getACrtdDt());
			model.setAOwnerId(bean.getAOwnerId());
			model.setIclubBankMaster(bean.getIclubBankMaster() != null ? bean.getIclubBankMaster().getBmId() : null);
			model.setIclubAccountType(bean.getIclubAccountType() != null ? bean.getIclubAccountType().getAtId() : null);
			model.setIclubOwnerType(bean.getIclubOwnerType() != null ? bean.getIclubOwnerType().getOtId() : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setAStatus(bean.getAStatus());

			if (bean.getIclubPolicies() != null && bean.getIclubPolicies().size() > 0) {
				String[] policies = new String[bean.getIclubPolicies().size()];
				int i = 0;
				for (IclubPolicy policy : bean.getIclubPolicies()) {

					policies[i] = policy.getPId();
					i++;
				}
				model.setIclubPolicies(policies);
			}

			if (bean.getIclubPayments() != null && bean.getIclubPayments().size() > 0) {
				String[] payments = new String[bean.getIclubPayments().size()];
				int i = 0;
				for (IclubPayment payment : bean.getIclubPayments()) {

					payments[i] = payment.getPId();
					i++;
				}
				model.setIclubPayments(payments);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}

	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}

	public IclubOwnerTypeDAO getIclubOwnerTypeDAO() {
		return iclubOwnerTypeDAO;
	}

	public void setIclubOwnerTypeDAO(IclubOwnerTypeDAO iclubOwnerTypeDAO) {
		this.iclubOwnerTypeDAO = iclubOwnerTypeDAO;
	}

	public IclubBankMasterDAO getIclubBankMasterDAO() {
		return iclubBankMasterDAO;
	}

	public void setIclubBankMasterDAO(IclubBankMasterDAO iclubBankMasterDAO) {
		this.iclubBankMasterDAO = iclubBankMasterDAO;
	}

	public IclubAccountTypeDAO getIclubAccountTypeDAO() {
		return iclubAccountTypeDAO;
	}

	public void setIclubAccountTypeDAO(IclubAccountTypeDAO iclubAccountTypeDAO) {
		this.iclubAccountTypeDAO = iclubAccountTypeDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
