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

import za.co.iclub.pss.model.ws.IclubConfigModel;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubConfigDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.trans.IclubConfigTrans;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubConfigService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubConfigService {

	private static final Logger LOGGER = Logger.getLogger(IclubConfigService.class);
	private IclubConfigDAO iclubConfigDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubConfigModel model) {
		try {

			IclubConfig iCC = IclubConfigTrans.fromWStoORM(model, iclubPersonDAO);

			iCC.setCId(iclubCommonDAO.getNextId(IclubConfig.class));

			iclubConfigDAO.save(iCC);

			LOGGER.info("Save Success with ID :: " + iCC.getCId().longValue());

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
	public ResponseModel mod(IclubConfigModel model) {
		try {
			IclubConfig iCC = IclubConfigTrans.fromWStoORM(model, iclubPersonDAO);

			iclubConfigDAO.merge(iCC);

			LOGGER.info("Save Success with ID :: " + model.getCId().longValue());

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
			iclubConfigDAO.delete(iclubConfigDAO.findById(id));
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
	public <T extends IclubConfigModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubConfigDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubConfig bean = (IclubConfig) object;
					IclubConfigModel iCC = IclubConfigTrans.fromORMtoWS(bean);

					ret.add((T) iCC);
				}
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
	public <T extends IclubConfigModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubConfig.class.getSimpleName());
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubConfig bean = (IclubConfig) object;
					IclubConfigModel iCC = IclubConfigTrans.fromORMtoWS(bean);

					ret.add((T) iCC);
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
	public IclubConfigModel getById(@PathParam("id") Long id) {
		IclubConfigModel model = new IclubConfigModel();
		try {
			IclubConfig bean = iclubConfigDAO.findById(id);

			model = IclubConfigTrans.fromORMtoWS(bean);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/getByKey/{key}")
	@Produces("application/json")
	@Transactional
	public IclubConfigModel getByKey(@PathParam("key") String key) {
		IclubConfigModel model = new IclubConfigModel();
		try {
			IclubConfig bean = iclubNamedQueryDAO.getIclubConfigByKey(key);

			model = IclubConfigTrans.fromORMtoWS(bean);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubConfigDAO getIclubConfigDAO() {
		return iclubConfigDAO;
	}

	public void setIclubConfigDAO(IclubConfigDAO iclubConfigDAO) {
		this.iclubConfigDAO = iclubConfigDAO;
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

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
