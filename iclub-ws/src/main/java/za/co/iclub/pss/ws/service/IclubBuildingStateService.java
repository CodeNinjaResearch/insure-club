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
import za.co.iclub.pss.orm.bean.IclubBuildingState;
import za.co.iclub.pss.orm.dao.IclubBuildingStateDAO;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.ws.model.IclubBuildingStateModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubBuildingStateService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubBuildingStateService {

	private static final Logger LOGGER = Logger.getLogger(IclubBuildingStateService.class);
	private IclubBuildingStateDAO iclubBuildingStateDAO;
	private IclubCommonDAO iclubCommonDAO;
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubBuildingStateModel model) {
		try {

			IclubBuildingState buildType = new IclubBuildingState();
			
			buildType.setBsId(iclubCommonDAO.getNextId(IclubBuildingState.class));
			buildType.setBsLongDesc(model.getBsLongDesc());
			buildType.setBsShortDesc(model.getBsShortDesc());
			buildType.setBsStatus(model.getBsStatus());

			iclubBuildingStateDAO.save(buildType);

			LOGGER.info("Save Success with ID :: " + buildType.getBsId().longValue());

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
	public ResponseModel mod(IclubBuildingStateModel model) {
		try {
			IclubBuildingState buildType = new IclubBuildingState();
			
			buildType.setBsId(model.getBsId());
			buildType.setBsLongDesc(model.getBsLongDesc());
			buildType.setBsShortDesc(model.getBsShortDesc());
			buildType.setBsStatus(model.getBsStatus());

			iclubBuildingStateDAO.merge(buildType);

			LOGGER.info("Save Success with ID :: " + model.getBsId().longValue());

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
			iclubBuildingStateDAO.delete(iclubBuildingStateDAO.findById(id));
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
			List data = iclubBuildingStateDAO.getBuildingStateBySD(val,id);
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
	public <T extends IclubBuildingStateModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubBuildingStateDAO.findAll();

			for (Object object : batmod) {
				IclubBuildingState iclubBsype = (IclubBuildingState) object;
				IclubBuildingStateModel iCB = new IclubBuildingStateModel();
				
				iCB.setBsId(iclubBsype.getBsId().longValue());
				iCB.setBsLongDesc(iclubBsype.getBsLongDesc());
				iCB.setBsShortDesc(iclubBsype.getBsShortDesc());
				iCB.setBsStatus(iclubBsype.getBsStatus());
				
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
	public IclubBuildingStateModel getById(@PathParam("id") Long id) {
		IclubBuildingStateModel model = new IclubBuildingStateModel();
		try {
			IclubBuildingState bean = iclubBuildingStateDAO.findById(id);
			
			model.setBsId(bean.getBsId().longValue());
			model.setBsLongDesc(bean.getBsLongDesc());
			model.setBsShortDesc(bean.getBsShortDesc());
			model.setBsStatus(bean.getBsStatus());
			
		} catch (Exception e) {
			LOGGER.error(e, e );
		}
		return model;
	}

	public IclubBuildingStateDAO getIclubBuildingStateDAO() {
		return iclubBuildingStateDAO;
	}

	public void setIclubBuildingStateDAO(IclubBuildingStateDAO iclubBuildingStateDAO) {
		this.iclubBuildingStateDAO = iclubBuildingStateDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
	
}

