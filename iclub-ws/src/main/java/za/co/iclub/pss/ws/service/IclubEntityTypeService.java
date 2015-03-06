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

import za.co.iclub.pss.orm.bean.IclubDocument;
import za.co.iclub.pss.orm.bean.IclubEntityType;
import za.co.iclub.pss.orm.bean.IclubMessage;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubEntityTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubEntityTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubEntityTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubEntityTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubEntityTypeModel model) {
		try {
			IclubEntityType iCEt = new IclubEntityType();

			iCEt.setEtId(iclubCommonDAO.getNextId(IclubEntityType.class));
			iCEt.setEtLongDesc(model.getEtLongDesc());
			iCEt.setEtShortDesc(model.getEtShortDesc());
			iCEt.setEtStatus(model.getEtStatus());

			iclubEntityTypeDAO.save(iCEt);

			LOGGER.info("Save Success with ID :: " + iCEt.getEtId());

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
	public ResponseModel mod(IclubEntityTypeModel model) {
		try {
			IclubEntityType iCEt = new IclubEntityType();

			iCEt.setEtId(model.getEtId());
			iCEt.setEtLongDesc(model.getEtLongDesc());
			iCEt.setEtShortDesc(model.getEtShortDesc());
			iCEt.setEtStatus(model.getEtStatus());

			iclubEntityTypeDAO.merge(iCEt);

			LOGGER.info("Merge Success with ID :: " + model.getEtId());

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
			iclubEntityTypeDAO.delete(iclubEntityTypeDAO.findById(id));
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
	public <T extends IclubEntityTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubEntityTypeDAO.findAll();

			for (Object object : batmod) {
				IclubEntityType iCEt = (IclubEntityType) object;

				IclubEntityTypeModel model = new IclubEntityTypeModel();

				model.setEtId(iCEt.getEtId());
				model.setEtLongDesc(iCEt.getEtLongDesc());
				model.setEtShortDesc(iCEt.getEtShortDesc());
				model.setEtStatus(iCEt.getEtStatus());
				
				if (iCEt.getIclubDocuments() != null && iCEt.getIclubDocuments().size() > 0) {
					String[] documents = new String[iCEt.getIclubDocuments().size()];
					int i = 0;
					for (IclubDocument iclubDocument : iCEt.getIclubDocuments()) {
						documents[i] = iclubDocument.getDId();
						i++;
					}
					model.setIclubDocuments(documents);
				}

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
	public <T extends IclubEntityTypeModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubEntityTypeDAO.findAll();

			for (Object object : batmod) {
				IclubEntityType iCEt = (IclubEntityType) object;

				IclubEntityTypeModel model = new IclubEntityTypeModel();

				model.setEtId(iCEt.getEtId());
				model.setEtLongDesc(iCEt.getEtLongDesc());
				model.setEtShortDesc(iCEt.getEtShortDesc());
				model.setEtStatus(iCEt.getEtStatus());
				
				if (iCEt.getIclubDocuments() != null && iCEt.getIclubDocuments().size() > 0) {
					String[] documents = new String[iCEt.getIclubDocuments().size()];
					int i = 0;
					for (IclubDocument iclubDocument : iCEt.getIclubDocuments()) {
						documents[i] = iclubDocument.getDId();
						i++;
					}
					model.setIclubDocuments(documents);
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
	public IclubEntityTypeModel getById(@PathParam("id") Long id) {
		IclubEntityTypeModel model = new IclubEntityTypeModel();
		try {
			IclubEntityType bean = iclubEntityTypeDAO.findById(id);

			model.setEtId(bean.getEtId());
			model.setEtLongDesc(bean.getEtLongDesc());
			model.setEtShortDesc(bean.getEtShortDesc());
			model.setEtStatus(bean.getEtStatus());
			
			if (bean.getIclubDocuments() != null && bean.getIclubDocuments().size() > 0) {
				String[] documents = new String[bean.getIclubDocuments().size()];
				int i = 0;
				for (IclubDocument iclubDocument : bean.getIclubDocuments()) {
					documents[i] = iclubDocument.getDId();
					i++;
				}
				model.setIclubDocuments(documents);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubEntityTypeDAO getIclubEntityTypeDAO() {
		return iclubEntityTypeDAO;
	}

	public void setIclubEntityTypeDAO(IclubEntityTypeDAO iclubEntityTypeDAO) {
		this.iclubEntityTypeDAO = iclubEntityTypeDAO;
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

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}
}
