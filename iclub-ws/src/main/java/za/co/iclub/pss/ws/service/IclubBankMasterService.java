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
import za.co.iclub.pss.orm.bean.IclubBankMaster;
import za.co.iclub.pss.orm.dao.IclubAccountDAO;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubBankMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubBankMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubBankMasterService {

	private static final Logger LOGGER = Logger.getLogger(IclubBankMasterService.class);
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubAccountDAO iclubAccountDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubBankMasterModel model) {
		try {

			IclubBankMaster iCBm = new IclubBankMaster();

			iCBm.setBmId(iclubCommonDAO.getNextId(IclubBankMaster.class));
			iCBm.setBmBankName(model.getBmBankName());
			iCBm.setBmBankCode(model.getBmBankCode());
			iCBm.setBmBranchName(model.getBmBranchName());
			iCBm.setBmBranchCode(model.getBmBranchCode());
			iCBm.setBmBranchAddress(model.getBmBranchAddress());
			iCBm.setBmBranchLat(model.getBmBranchLat());
			iCBm.setBmBranchLong(model.getBmBranchLong());
			iCBm.setBmCrtdDt(model.getBmCrtdDt());
			iCBm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubBankMasterDAO.save(iCBm);

			LOGGER.info("Save Success with ID :: " + iCBm.getBmId().longValue());

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
	public ResponseModel mod(IclubBankMasterModel model) {
		try {
			IclubBankMaster iCBm = new IclubBankMaster();

			iCBm.setBmId(model.getBmId());
			iCBm.setBmBankName(model.getBmBankName());
			iCBm.setBmBankCode(model.getBmBankCode());
			iCBm.setBmBranchName(model.getBmBranchName());
			iCBm.setBmBranchCode(model.getBmBranchCode());
			iCBm.setBmBranchAddress(model.getBmBranchAddress());
			iCBm.setBmBranchLat(model.getBmBranchLat());
			iCBm.setBmBranchLong(model.getBmBranchLong());
			iCBm.setBmCrtdDt(model.getBmCrtdDt());
			iCBm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubBankMasterDAO.merge(iCBm);

			LOGGER.info("Save Success with ID :: " + model.getBmId().longValue());

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
	public Response del(@PathParam("id") Long id) {
		try {
			iclubBankMasterDAO.delete(iclubBankMasterDAO.findById(id));
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
	public <T extends IclubBankMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubBankMasterDAO.findAll();

			for (Object object : batmod) {
				IclubBankMaster iclubBMaster = (IclubBankMaster) object;
				IclubBankMasterModel iCBm = new IclubBankMasterModel();

				iCBm.setBmId(iclubBMaster.getBmId());
				iCBm.setBmBankName(iclubBMaster.getBmBankName());
				iCBm.setBmBankCode(iclubBMaster.getBmBankCode());
				iCBm.setBmBranchName(iclubBMaster.getBmBranchName());
				iCBm.setBmBranchCode(iclubBMaster.getBmBranchCode());
				iCBm.setBmBranchAddress(iclubBMaster.getBmBranchAddress());
				iCBm.setBmBranchLat(iclubBMaster.getBmBranchLat());
				iCBm.setBmBranchLong(iclubBMaster.getBmBranchLong());
				iCBm.setBmCrtdDt(iclubBMaster.getBmCrtdDt());
				iCBm.setIclubPerson(iclubBMaster.getIclubPerson() != null ? iclubBMaster.getIclubPerson().getPId() : null);
				if (iclubBMaster.getIclubAccounts() != null && iclubBMaster.getIclubAccounts().size() > 0) {

					String[] accounts = new String[iclubBMaster.getIclubAccounts().size()];

					int i = 0;
					for (IclubAccount account : iclubBMaster.getIclubAccounts()) {
						accounts[i] = account.getAId();
					}
					iCBm.setIclubAccounts(accounts);
				}

				ret.add((T) iCBm);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/list/banknames")
	@Produces("application/json")
	@Transactional
	public <T extends String> List<T> listAllMake() {
		List<T> ret = new ArrayList<T>();
		try {
			List batmod = iclubNamedQueryDAO.getAllBankNames();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					String reDetails = (String) object;
					ret.add((T) reDetails);
				}
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/get/bankName/{bankName}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubBankMasterModel> List<T> getByBankName(@PathParam("bankName") String bankName) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.getIclubBankMastersByBankName(bankName);

			for (Object object : batmod) {
				IclubBankMaster iclubBMaster = (IclubBankMaster) object;
				IclubBankMasterModel iCBm = new IclubBankMasterModel();

				iCBm.setBmId(iclubBMaster.getBmId());
				iCBm.setBmBankName(iclubBMaster.getBmBankName());
				iCBm.setBmBankCode(iclubBMaster.getBmBankCode());
				iCBm.setBmBranchName(iclubBMaster.getBmBranchName());
				iCBm.setBmBranchCode(iclubBMaster.getBmBranchCode());
				iCBm.setBmBranchAddress(iclubBMaster.getBmBranchAddress());
				iCBm.setBmBranchLat(iclubBMaster.getBmBranchLat());
				iCBm.setBmBranchLong(iclubBMaster.getBmBranchLong());
				iCBm.setBmCrtdDt(iclubBMaster.getBmCrtdDt());
				iCBm.setIclubPerson(iclubBMaster.getIclubPerson() != null ? iclubBMaster.getIclubPerson().getPId() : null);
				if (iclubBMaster.getIclubAccounts() != null && iclubBMaster.getIclubAccounts().size() > 0) {

					String[] accounts = new String[iclubBMaster.getIclubAccounts().size()];

					int i = 0;
					for (IclubAccount account : iclubBMaster.getIclubAccounts()) {
						accounts[i] = account.getAId();
					}
					iCBm.setIclubAccounts(accounts);
				}

				ret.add((T) iCBm);
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
	public <T extends IclubBankMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubBankMaster.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubBankMaster iclubBMaster = (IclubBankMaster) object;
					IclubBankMasterModel iCBm = new IclubBankMasterModel();

					iCBm.setBmId(iclubBMaster.getBmId());
					iCBm.setBmBankName(iclubBMaster.getBmBankName());
					iCBm.setBmBankCode(iclubBMaster.getBmBankCode());
					iCBm.setBmBranchName(iclubBMaster.getBmBranchName());
					iCBm.setBmBranchCode(iclubBMaster.getBmBranchCode());
					iCBm.setBmBranchAddress(iclubBMaster.getBmBranchAddress());
					iCBm.setBmBranchLat(iclubBMaster.getBmBranchLat());
					iCBm.setBmBranchLong(iclubBMaster.getBmBranchLong());
					iCBm.setBmCrtdDt(iclubBMaster.getBmCrtdDt());
					iCBm.setIclubPerson(iclubBMaster.getIclubPerson() != null ? iclubBMaster.getIclubPerson().getPId() : null);
					if (iclubBMaster.getIclubAccounts() != null && iclubBMaster.getIclubAccounts().size() > 0) {

						String[] accounts = new String[iclubBMaster.getIclubAccounts().size()];

						int i = 0;
						for (IclubAccount account : iclubBMaster.getIclubAccounts()) {
							accounts[i] = account.getAId();
						}
						iCBm.setIclubAccounts(accounts);
					}

					ret.add((T) iCBm);
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
	@Transactional
	public IclubBankMasterModel getById(@PathParam("id") Long id) {
		IclubBankMasterModel model = new IclubBankMasterModel();
		try {
			IclubBankMaster bean = iclubBankMasterDAO.findById(id);

			model.setBmId(bean.getBmId());
			model.setBmBankName(bean.getBmBankName());
			model.setBmBankCode(bean.getBmBankCode());
			model.setBmBranchName(bean.getBmBranchName());
			model.setBmBranchCode(bean.getBmBranchCode());
			model.setBmBranchAddress(bean.getBmBranchAddress());
			model.setBmBranchLat(bean.getBmBranchLat());
			model.setBmBranchLong(bean.getBmBranchLong());
			model.setBmCrtdDt(bean.getBmCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			if (bean.getIclubAccounts() != null && bean.getIclubAccounts().size() > 0) {

				String[] accounts = new String[bean.getIclubAccounts().size()];

				int i = 0;
				for (IclubAccount account : bean.getIclubAccounts()) {
					accounts[i] = account.getAId();
				}
				model.setIclubAccounts(accounts);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubBankMasterDAO getIclubBankMasterDAO() {
		return iclubBankMasterDAO;
	}

	public void setIclubBankMasterDAO(IclubBankMasterDAO iclubBankMasterDAO) {
		this.iclubBankMasterDAO = iclubBankMasterDAO;
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

	public IclubAccountDAO getIclubAccountDAO() {
		return iclubAccountDAO;
	}

	public void setIclubAccountDAO(IclubAccountDAO iclubAccountDAO) {
		this.iclubAccountDAO = iclubAccountDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
