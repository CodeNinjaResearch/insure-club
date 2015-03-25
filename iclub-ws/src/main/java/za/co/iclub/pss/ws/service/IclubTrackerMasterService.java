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

import za.co.iclub.pss.orm.bean.IclubSecurityDevice;
import za.co.iclub.pss.orm.bean.IclubTrackerMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubTrackerMasterDAO;
import za.co.iclub.pss.ws.model.IclubTrackerMasterModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubTrackerMasterService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubTrackerMasterService {

	private static final Logger LOGGER = Logger.getLogger(IclubTrackerMasterService.class);
	private IclubTrackerMasterDAO iclubTrackerMasterDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public ResponseModel add(IclubTrackerMasterModel model) {
		try {

			IclubTrackerMaster iCTm = new IclubTrackerMaster();

			iCTm.setTmId(iclubCommonDAO.getNextId(IclubTrackerMaster.class));
			iCTm.setTmName(model.getTmName());
			iCTm.setTmLat(model.getTmLat());
			iCTm.setTmLocation(model.getTmLocation());
			iCTm.setTmLong(model.getTmLong());
			iCTm.setTmTradeName(model.getTmTradeName());
			iCTm.setTmRegNum(model.getTmRegNum());
			iCTm.setTmCrtdDt(model.getTmCrtdDt());

			iCTm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubTrackerMasterDAO.save(iCTm);

			LOGGER.info("Save Success with ID :: " + iCTm.getTmId().longValue());

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
	public ResponseModel mod(IclubTrackerMasterModel model) {
		try {
			IclubTrackerMaster iCTm = new IclubTrackerMaster();

			iCTm.setTmId(model.getTmId());
			iCTm.setTmName(model.getTmName());
			iCTm.setTmLat(model.getTmLat());
			iCTm.setTmLocation(model.getTmLocation());
			iCTm.setTmLong(model.getTmLong());
			iCTm.setTmTradeName(model.getTmTradeName());
			iCTm.setTmRegNum(model.getTmRegNum());
			iCTm.setTmCrtdDt(model.getTmCrtdDt());

			iCTm.setIclubPerson(iclubPersonDAO.findById(model.getIclubPerson()));

			iclubTrackerMasterDAO.merge(iCTm);

			LOGGER.info("Save Success with ID :: " + model.getTmId().longValue());

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
			iclubTrackerMasterDAO.delete(iclubTrackerMasterDAO.findById(id));
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
	public <T extends IclubTrackerMasterModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubTrackerMasterDAO.findAll();

			for (Object object : batmod) {
				IclubTrackerMaster iclubTMaster = (IclubTrackerMaster) object;
				IclubTrackerMasterModel iCTm = new IclubTrackerMasterModel();

				iCTm.setTmId(iclubTMaster.getTmId());
				iCTm.setTmName(iclubTMaster.getTmName());
				iCTm.setTmLat(iclubTMaster.getTmLat());
				iCTm.setTmLocation(iclubTMaster.getTmLocation());
				iCTm.setTmLong(iclubTMaster.getTmLong());
				iCTm.setTmTradeName(iclubTMaster.getTmTradeName());
				iCTm.setTmRegNum(iclubTMaster.getTmRegNum());
				iCTm.setTmCrtdDt(iclubTMaster.getTmCrtdDt());
				iCTm.setIclubPerson(iclubTMaster.getIclubPerson() != null ? iclubTMaster.getIclubPerson().getPId() : null);


				if (iclubTMaster.getIclubSecurityDevices() != null && iclubTMaster.getIclubSecurityDevices().size() > 0) {
					String[] securityDevices = new String[iclubTMaster.getIclubSecurityDevices().size()];
					int i = 0;
					for (IclubSecurityDevice securityDevice : iclubTMaster.getIclubSecurityDevices()) {
						securityDevices[i] = securityDevice.getSdId();
						i++;
					}
					iCTm.setIclubSecurityDevices(securityDevices);
				}

				ret.add((T) iCTm);
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
	public <T extends IclubTrackerMasterModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubTrackerMasterDAO.findByUser(user);

			for (Object object : batmod) {
				IclubTrackerMaster iclubTMaster = (IclubTrackerMaster) object;
				IclubTrackerMasterModel iCTm = new IclubTrackerMasterModel();

				iCTm.setTmId(iclubTMaster.getTmId());
				iCTm.setTmName(iclubTMaster.getTmName());
				iCTm.setTmLat(iclubTMaster.getTmLat());
				iCTm.setTmLocation(iclubTMaster.getTmLocation());
				iCTm.setTmLong(iclubTMaster.getTmLong());
				iCTm.setTmTradeName(iclubTMaster.getTmTradeName());
				iCTm.setTmRegNum(iclubTMaster.getTmRegNum());
				iCTm.setTmCrtdDt(iclubTMaster.getTmCrtdDt());
				iCTm.setIclubPerson(iclubTMaster.getIclubPerson() != null ? iclubTMaster.getIclubPerson().getPId() : null);

				if (iclubTMaster.getIclubSecurityDevices() != null && iclubTMaster.getIclubSecurityDevices().size() > 0) {
					String[] securityDevices = new String[iclubTMaster.getIclubSecurityDevices().size()];
					int i = 0;
					for (IclubSecurityDevice securityDevice : iclubTMaster.getIclubSecurityDevices()) {
						securityDevices[i] = securityDevice.getSdId();
						i++;
					}
					iCTm.setIclubSecurityDevices(securityDevices);
				}
				
				ret.add((T) iCTm);
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
	public IclubTrackerMasterModel getById(@PathParam("id") Long id) {
		IclubTrackerMasterModel model = new IclubTrackerMasterModel();
		try {
			IclubTrackerMaster bean = iclubTrackerMasterDAO.findById(id);

			model.setTmId(bean.getTmId());
			model.setTmName(bean.getTmName());
			model.setTmLat(bean.getTmLat());
			model.setTmLocation(bean.getTmLocation());
			model.setTmLong(bean.getTmLong());
			model.setTmTradeName(bean.getTmTradeName());
			model.setTmRegNum(bean.getTmRegNum());
			model.setTmCrtdDt(bean.getTmCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);

			if (bean.getIclubSecurityDevices() != null && bean.getIclubSecurityDevices().size() > 0) {
				String[] securityDevices = new String[bean.getIclubSecurityDevices().size()];
				int i = 0;
				for (IclubSecurityDevice securityDevice : bean.getIclubSecurityDevices()) {
					securityDevices[i] = securityDevice.getSdId();
					i++;
				}
				model.setIclubSecurityDevices(securityDevices);
			}
			
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubTrackerMasterDAO getIclubTrackerMasterDAO() {
		return iclubTrackerMasterDAO;
	}

	public void setIclubTrackerMasterDAO(IclubTrackerMasterDAO iclubTrackerMasterDAO) {
		this.iclubTrackerMasterDAO = iclubTrackerMasterDAO;
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

}
