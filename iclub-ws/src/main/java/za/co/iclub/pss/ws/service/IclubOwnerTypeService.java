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
import za.co.iclub.pss.orm.bean.IclubOwnerType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubOwnerTypeDAO;
import za.co.iclub.pss.ws.model.IclubOwnerTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubOwnerTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubOwnerTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubOwnerTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubOwnerTypeDAO iclubOwnerTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubOwnerTypeModel model) {
		try {
			IclubOwnerType iOt = new IclubOwnerType();

			iOt.setOtId(iclubCommonDAO.getNextId(IclubOwnerType.class));
			iOt.setOtLongDesc(model.getOtLongDesc());
			iOt.setOtShortDesc(model.getOtShortDesc());
			iOt.setOtStatus(model.getOtStatus());

			iclubOwnerTypeDAO.save(iOt);

			LOGGER.info("Save Success with ID :: " + iOt.getOtId());

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
	public ResponseModel mod(IclubOwnerTypeModel model) {
		try {
			IclubOwnerType iOt = new IclubOwnerType();

			iOt.setOtId(model.getOtId());
			iOt.setOtLongDesc(model.getOtLongDesc());
			iOt.setOtShortDesc(model.getOtShortDesc());
			iOt.setOtStatus(model.getOtStatus());

			iclubOwnerTypeDAO.merge(iOt);

			LOGGER.info("Merge Success with ID :: " + model.getOtId());

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
			iclubOwnerTypeDAO.delete(iclubOwnerTypeDAO.findById(id));
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
	public <T extends IclubOwnerTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubOwnerTypeDAO.findAll();

			for (Object object : batmod) {
				IclubOwnerType iOt = (IclubOwnerType) object;

				IclubOwnerTypeModel model = new IclubOwnerTypeModel();

				model.setOtId(iOt.getOtId());
				model.setOtLongDesc(iOt.getOtLongDesc());
				model.setOtShortDesc(iOt.getOtShortDesc());
				model.setOtStatus(iOt.getOtStatus());

				if (iOt.getIclubAccounts() != null && iOt.getIclubAccounts().size() > 0) {
					Set<IclubAccount> iAcc = iOt.getIclubAccounts();
					Integer[] iclubAccounts = new Integer[iAcc.size()];

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
	public IclubOwnerTypeModel getById(@PathParam("id") Long id) {
		IclubOwnerTypeModel model = new IclubOwnerTypeModel();
		try {
			IclubOwnerType bean = iclubOwnerTypeDAO.findById(id);

			model.setOtId(bean.getOtId());
			model.setOtLongDesc(bean.getOtLongDesc());
			model.setOtShortDesc(bean.getOtShortDesc());
			model.setOtStatus(bean.getOtStatus());

			if (bean.getIclubAccounts() != null && bean.getIclubAccounts().size() > 0) {
				Set<IclubAccount> iAcc = bean.getIclubAccounts();
				Integer[] iclubAccounts = new Integer[iAcc.size()];

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
			List data = iclubOwnerTypeDAO.getOwnerTypeBySD(val, id);
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

	public IclubOwnerTypeDAO getIclubOwnerTypeDAO() {
		return iclubOwnerTypeDAO;
	}

	public void setIclubOwnerTypeDAO(IclubOwnerTypeDAO iclubOwnerTypeDAO) {
		this.iclubOwnerTypeDAO = iclubOwnerTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

}
