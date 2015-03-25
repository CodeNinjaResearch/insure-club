package za.co.iclub.pss.ws.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import za.co.iclub.pss.orm.bean.IclubAccountType;
import za.co.iclub.pss.orm.dao.IclubAccountTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.ws.model.IclubAccountTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubAccountTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubAccountTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubAccountTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubAccountTypeDAO iclubAccountTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubAccountTypeModel model) {
		try {
			IclubAccountType iAt = new IclubAccountType();

			iAt.setAtId(iclubCommonDAO.getNextId(IclubAccountType.class));
			iAt.setAtLongDesc(model.getAtLongDesc());
			iAt.setAtShortDesc(model.getAtShortDesc());
			iAt.setAtStatus(model.getAtStatus());

			iclubAccountTypeDAO.save(iAt);

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
	public ResponseModel mod(IclubAccountTypeModel model) {
		try {
			IclubAccountType iAt = new IclubAccountType();

			iAt.setAtId(model.getAtId());
			iAt.setAtLongDesc(model.getAtLongDesc());
			iAt.setAtShortDesc(model.getAtShortDesc());
			iAt.setAtStatus(model.getAtStatus());

			iclubAccountTypeDAO.merge(iAt);

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
			iclubAccountTypeDAO.delete(iclubAccountTypeDAO.findById(id));
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
	public <T extends IclubAccountTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubAccountTypeDAO.findAll();

			for (Object object : batmod) {
				IclubAccountType iAt = (IclubAccountType) object;

				IclubAccountTypeModel model = new IclubAccountTypeModel();

				model.setAtId(iAt.getAtId());
				model.setAtLongDesc(iAt.getAtLongDesc());
				model.setAtShortDesc(iAt.getAtShortDesc());
				model.setAtStatus(iAt.getAtStatus());

				if (iAt.getIclubAccounts() != null && iAt.getIclubAccounts().size() > 0) {
					Set<IclubAccount> iAcc = iAt.getIclubAccounts();
					String[] iclubAccounts = new String[iAcc.size()];

					int i = 0;
					for (IclubAccount iA : iAcc) {
						iclubAccounts[i] = iA.getAId();
						i++;
					}
					model.setIclubAccounts(iclubAccounts);
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
	public IclubAccountTypeModel getById(@PathParam("id") Long id) {
		IclubAccountTypeModel model = new IclubAccountTypeModel();
		try {
			IclubAccountType bean = iclubAccountTypeDAO.findById(id);

			model.setAtId(bean.getAtId());
			model.setAtLongDesc(bean.getAtLongDesc());
			model.setAtShortDesc(bean.getAtShortDesc());
			model.setAtStatus(bean.getAtStatus());

			if (bean.getIclubAccounts() != null && bean.getIclubAccounts().size() > 0) {
				Set<IclubAccount> iAcc = bean.getIclubAccounts();
				String[] iclubAccounts = new String[iAcc.size()];

				int i = 0;
				for (IclubAccount iA : iAcc) {
					iclubAccounts[i] = iA.getAId();
					i++;
				}
				model.setIclubAccounts(iclubAccounts);
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
			List data = iclubAccountTypeDAO.getAccountTypeBySD(val, id);
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

	public IclubAccountTypeDAO getIclubAccountTypeDAO() {
		return iclubAccountTypeDAO;
	}

	public void setIclubAccountTypeDAO(IclubAccountTypeDAO iclubAccountTypeDAO) {
		this.iclubAccountTypeDAO = iclubAccountTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

}
