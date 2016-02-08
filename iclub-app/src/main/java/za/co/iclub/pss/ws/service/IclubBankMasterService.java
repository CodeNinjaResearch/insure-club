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

import za.co.iclub.pss.model.ws.IclubBankMasterModel;
import za.co.iclub.pss.orm.bean.IclubBankMaster;
import za.co.iclub.pss.orm.dao.IclubBankMasterDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubBankMasterTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubBankMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubBankMasterService {

	private static final Logger LOGGER = Logger.getLogger(IclubBankMasterService.class);
	private IclubBankMasterDAO iclubBankMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubBankMasterModel model) {
		try {

			IclubBankMaster iCBm = IclubBankMasterTrans.fromWStoORM(model, iclubPersonDAO);

			iCBm.setBmId(iclubCommonDAO.getNextId(IclubBankMaster.class));

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
			IclubBankMaster iCBm = IclubBankMasterTrans.fromWStoORM(model, iclubPersonDAO);

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
				IclubBankMaster bean = (IclubBankMaster) object;
				IclubBankMasterModel iCBm = IclubBankMasterTrans.fromORMtoWS(bean);

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
				IclubBankMaster bean = (IclubBankMaster) object;
				IclubBankMasterModel iCBm = IclubBankMasterTrans.fromORMtoWS(bean);

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
					IclubBankMaster bean = (IclubBankMaster) object;
					IclubBankMasterModel iCBm = IclubBankMasterTrans.fromORMtoWS(bean);

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

			model = IclubBankMasterTrans.fromORMtoWS(bean);

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

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

}
