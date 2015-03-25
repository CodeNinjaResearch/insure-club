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
import org.springframework.transaction.annotation.Transactional;
import za.co.iclub.pss.orm.bean.IclubBoundaryType;
import za.co.iclub.pss.orm.dao.IclubBoundaryTypeDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.ws.model.IclubBoundaryTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubBoundaryTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubBoundaryTypeService {

	private static final Logger LOGGER = Logger.getLogger(IclubBoundaryTypeService.class);
	private IclubBoundaryTypeDAO iclubBoundaryTypeDAO;
	private IclubCommonDAO iclubCommonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubBoundaryTypeModel model) {
		try {

			IclubBoundaryType bouType = new IclubBoundaryType();

			bouType.setBtId(iclubCommonDAO.getNextId(IclubBoundaryType.class));
			bouType.setBtLongDesc(model.getBtLongDesc());
			bouType.setBtShortDesc(model.getBtShortDesc());
			bouType.setBtStatus(model.getBtStatus());

			iclubBoundaryTypeDAO.save(bouType);

			LOGGER.info("Save Success with ID :: " + bouType.getBtId().longValue());

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
	public ResponseModel mod(IclubBoundaryTypeModel model) {
		try {
			IclubBoundaryType bouType = new IclubBoundaryType();

			bouType.setBtId(model.getBtId());
			bouType.setBtLongDesc(model.getBtLongDesc());
			bouType.setBtShortDesc(model.getBtShortDesc());
			bouType.setBtStatus(model.getBtStatus());

			iclubBoundaryTypeDAO.merge(bouType);

			LOGGER.info("Save Success with ID :: " + model.getBtId().longValue());

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
			iclubBoundaryTypeDAO.delete(iclubBoundaryTypeDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/validate/sd/{val}/{id}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel validateSd(@PathParam("val") String val, @PathParam("id") Long id) {
		try {
			List data = iclubBoundaryTypeDAO.getBoundaryTypeBySD(val, id);
			ResponseModel message = new ResponseModel();
			if (data != null && data.size() > 0) {
				message.setStatusCode(1);
				message.setStatusDesc("Duplicate Value");
			} else {
				message.setStatusCode(0);
				message.setStatusDesc("Success");
			}
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(2);
			message.setStatusDesc(e.getMessage());
			return message;
		}

	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional
	public <T extends IclubBoundaryTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubBoundaryTypeDAO.findAll();

			for (Object object : batmod) {
				IclubBoundaryType iclubBoutype = (IclubBoundaryType) object;
				IclubBoundaryTypeModel iCB = new IclubBoundaryTypeModel();

				iCB.setBtId(iclubBoutype.getBtId().longValue());
				iCB.setBtLongDesc(iclubBoutype.getBtLongDesc());
				iCB.setBtShortDesc(iclubBoutype.getBtShortDesc());
				iCB.setBtStatus(iclubBoutype.getBtStatus());

				ret.add((T) iCB);
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
	public IclubBoundaryTypeModel getById(@PathParam("id") Long id) {
		IclubBoundaryTypeModel model = new IclubBoundaryTypeModel();
		try {
			IclubBoundaryType bean = iclubBoundaryTypeDAO.findById(id);

			model.setBtId(bean.getBtId().longValue());
			model.setBtLongDesc(bean.getBtLongDesc());
			model.setBtShortDesc(bean.getBtShortDesc());
			model.setBtStatus(bean.getBtStatus());

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubBoundaryTypeDAO getIclubBoundaryTypeDAO() {
		return iclubBoundaryTypeDAO;
	}

	public void setIclubBoundaryTypeDAO(IclubBoundaryTypeDAO iclubBoundaryTypeDAO) {
		this.iclubBoundaryTypeDAO = iclubBoundaryTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

}
