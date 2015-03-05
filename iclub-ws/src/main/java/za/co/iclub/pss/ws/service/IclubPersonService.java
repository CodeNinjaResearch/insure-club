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

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubIdTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMaritialStatusDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubPersonModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path("/IclubPersonService")
public class IclubPersonService {
	protected static final Logger LOGGER = Logger.getLogger(IclubPersonService.class);
	private IclubPersonDAO iclubPersonDAO;
	private IclubCommonDAO iclubCommonDAO;
	private IclubIdTypeDAO iclubIdTypeDAO;
	private IclubMaritialStatusDAO iclubMaritialStatusDAO;

	@POST
	@Path("/add")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubPersonModel model) {
		try {
			IclubPerson person = new IclubPerson();

			person.setPId(model.getPId());
			person.setPCrtdDt(model.getPCrtdDt());
			person.setPDob(model.getPDob());
			person.setPEmail(model.getPEmail());
			person.setPFName(model.getPFName());
			person.setPIdNum(model.getPIdNum());
			person.setPLName(model.getPLName());
			person.setPMobile(model.getPMobile());
			person.setPAddress(model.getPAddress());
			person.setPContactPref(model.getPContactPref());
			person.setPGender(model.getPGender());
			person.setPIdNum(model.getPIdNum());
			person.setPContactPref(model.getPContactPref());
			person.setPIdExpiryDt(model.getPIdExpiryDt());
			person.setPInitials(model.getPInitials());
			person.setPIsPensioner(model.getPIsPensioner());
			person.setPIdIssueCntry(model.getPIdIssueCntry());
			person.setPLat(model.getPLat());
			person.setPLong(model.getPLong());
			person.setPOccupation(model.getPOccupation());
			person.setPTitle(model.getPTitle());
			person.setPZipCd(model.getPZipCd());
			person.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
			person.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			person.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);
			iclubPersonDAO.save(person);

			LOGGER.info("Save Success with ID :: " + person.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@PUT
	@Path("/mod")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel mod(IclubPersonModel model) {
		try {
			IclubPerson person = new IclubPerson();

			person.setPId(model.getPId());
			person.setPCrtdDt(model.getPCrtdDt());
			person.setPDob(model.getPDob());
			person.setPEmail(model.getPEmail());
			person.setPFName(model.getPFName());
			person.setPIdNum(model.getPIdNum());
			person.setPLName(model.getPLName());
			person.setPMobile(model.getPMobile());
			person.setPAddress(model.getPAddress());
			person.setPContactPref(model.getPContactPref());
			person.setPGender(model.getPGender());
			person.setPIdNum(model.getPIdNum());
			person.setPContactPref(model.getPContactPref());
			person.setPIdExpiryDt(model.getPIdExpiryDt());
			person.setPInitials(model.getPInitials());
			person.setPIsPensioner(model.getPIsPensioner());
			person.setPIdIssueCntry(model.getPIdIssueCntry());
			person.setPLat(model.getPLat());
			person.setPLong(model.getPLong());
			person.setPOccupation(model.getPOccupation());
			person.setPTitle(model.getPTitle());
			person.setPZipCd(model.getPZipCd());
			person.setIclubIdType(model.getIclubIdType() != null ? iclubIdTypeDAO.findById(model.getIclubIdType()) : null);
			person.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			person.setIclubMaritialStatus(model.getIclubMaritialStatus() != null ? iclubMaritialStatusDAO.findById(model.getIclubMaritialStatus()) : null);

			iclubPersonDAO.merge(person);

			LOGGER.info("Merge Success with ID :: " + model.getPId());

			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(0));
			message.setStatusDesc("Success");
			return message;
		} catch (Exception e) {
			LOGGER.error(e, e);
			ResponseModel message = new ResponseModel();
			message.setStatusCode(Integer.valueOf(1));
			message.setStatusDesc(e.getMessage());
			return message;
		}
	}

	@GET
	@Path("/del/{id}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public Response del(@PathParam("id") String id) {
		try {
			iclubPersonDAO.delete(iclubPersonDAO.findById(id));
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPersonDAO.findAll();

			for (Object object : batmod) {
				IclubPerson iPerson = (IclubPerson) object;

				IclubPersonModel model = new IclubPersonModel();
				model.setPId(iPerson.getPId());
				model.setPCrtdDt(iPerson.getPCrtdDt());
				model.setPDob(iPerson.getPDob());
				model.setPEmail(iPerson.getPEmail());
				model.setPFName(iPerson.getPFName());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPLName(iPerson.getPLName());
				model.setPMobile(iPerson.getPMobile());
				model.setPAddress(iPerson.getPAddress());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPGender(iPerson.getPGender());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPIdExpiryDt(iPerson.getPIdExpiryDt());
				model.setPInitials(iPerson.getPInitials());
				model.setPIsPensioner(iPerson.getPIsPensioner());
				model.setPIdIssueCntry(iPerson.getPIdIssueCntry());
				model.setPLat(iPerson.getPLat());
				model.setPLong(iPerson.getPLong());
				model.setPOccupation(iPerson.getPOccupation());
				model.setPTitle(iPerson.getPTitle());
				model.setPZipCd(iPerson.getPZipCd());
				model.setIclubIdType(iPerson.getIclubIdType() != null ? (iPerson.getIclubIdType().getItId()) : null);
				model.setIclubMaritialStatus(iPerson.getIclubMaritialStatus() != null ? (iPerson.getIclubMaritialStatus().getMsId()) : null);
				model.setIclubPerson(iPerson.getIclubPerson() != null ? iPerson.getIclubPerson().getPId() : null);

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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T extends IclubPersonModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubPersonDAO.findByUser(user);

			for (Object object : batmod) {
				IclubPerson iPerson = (IclubPerson) object;

				IclubPersonModel model = new IclubPersonModel();
				model.setPId(iPerson.getPId());
				model.setPCrtdDt(iPerson.getPCrtdDt());
				model.setPDob(iPerson.getPDob());
				model.setPEmail(iPerson.getPEmail());
				model.setPFName(iPerson.getPFName());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPLName(iPerson.getPLName());
				model.setPMobile(iPerson.getPMobile());
				model.setPAddress(iPerson.getPAddress());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPGender(iPerson.getPGender());
				model.setPIdNum(iPerson.getPIdNum());
				model.setPContactPref(iPerson.getPContactPref());
				model.setPIdExpiryDt(iPerson.getPIdExpiryDt());
				model.setPInitials(iPerson.getPInitials());
				model.setPIsPensioner(iPerson.getPIsPensioner());
				model.setPIdIssueCntry(iPerson.getPIdIssueCntry());
				model.setPLat(iPerson.getPLat());
				model.setPLong(iPerson.getPLong());
				model.setPOccupation(iPerson.getPOccupation());
				model.setPTitle(iPerson.getPTitle());
				model.setPZipCd(iPerson.getPZipCd());
				model.setIclubIdType(iPerson.getIclubIdType() != null ? (iPerson.getIclubIdType().getItId()) : null);
				model.setIclubPerson(iPerson.getIclubPerson() != null ? iPerson.getIclubPerson().getPId() : null);
				model.setIclubMaritialStatus(iPerson.getIclubMaritialStatus() != null ? (iPerson.getIclubMaritialStatus().getMsId()) : null);

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	@GET
	@Path("/get/{id}")
	@Produces({ "application/json" })
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubPersonModel getById(@PathParam("id") String id) {
		IclubPersonModel model = new IclubPersonModel();
		try {
			IclubPerson bean = iclubPersonDAO.findById(id);

			model.setPId(bean.getPId());
			model.setPCrtdDt(bean.getPCrtdDt());
			model.setPDob(bean.getPDob());
			model.setPEmail(bean.getPEmail());
			model.setPFName(bean.getPFName());
			model.setPIdNum(bean.getPIdNum());
			model.setPLName(bean.getPLName());
			model.setPMobile(bean.getPMobile());
			model.setPAddress(bean.getPAddress());
			model.setPContactPref(bean.getPContactPref());
			model.setPGender(bean.getPGender());
			model.setPIdNum(bean.getPIdNum());
			model.setPContactPref(bean.getPContactPref());
			model.setPIdExpiryDt(bean.getPIdExpiryDt());
			model.setPInitials(bean.getPInitials());
			model.setPIsPensioner(bean.getPIsPensioner());
			model.setPIdIssueCntry(bean.getPIdIssueCntry());
			model.setPLat(bean.getPLat());
			model.setPLong(bean.getPLong());
			model.setPOccupation(bean.getPOccupation());
			model.setPTitle(bean.getPTitle());
			model.setPZipCd(bean.getPZipCd());
			model.setIclubIdType(bean.getIclubIdType() != null ? (bean.getIclubIdType().getItId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setIclubMaritialStatus(bean.getIclubMaritialStatus() != null ? (bean.getIclubMaritialStatus().getMsId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubIdTypeDAO getIclubIdTypeDAO() {
		return iclubIdTypeDAO;
	}

	public void setIclubIdTypeDAO(IclubIdTypeDAO iclubIdTypeDAO) {
		this.iclubIdTypeDAO = iclubIdTypeDAO;
	}

	public IclubMaritialStatusDAO getIclubMaritialStatusDAO() {
		return iclubMaritialStatusDAO;
	}

	public void setIclubMaritialStatusDAO(IclubMaritialStatusDAO iclubMaritialStatusDAO) {
		this.iclubMaritialStatusDAO = iclubMaritialStatusDAO;
	}

}
