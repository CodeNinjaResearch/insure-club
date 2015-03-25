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
import za.co.iclub.pss.orm.bean.IclubDocumentType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubDocumentTypeDAO;
import za.co.iclub.pss.ws.model.IclubDocumentTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubDocumentTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubDocumentTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubDocumentTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubDocumentTypeDAO iclubDocumentTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubDocumentTypeModel model) {
		try {
			IclubDocumentType iDt = new IclubDocumentType();

			iDt.setDtId(iclubCommonDAO.getNextId(IclubDocumentType.class));
			iDt.setDtLongDesc(model.getDtLongDesc());
			iDt.setDtShortDesc(model.getDtShortDesc());
			iDt.setDtStatus(model.getDtStatus());

			iclubDocumentTypeDAO.save(iDt);

			LOGGER.info("Save Success with ID :: " + iDt.getDtId());

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
	public ResponseModel mod(IclubDocumentTypeModel model) {
		try {
			IclubDocumentType iDt = new IclubDocumentType();

			iDt.setDtId(model.getDtId());
			iDt.setDtLongDesc(model.getDtLongDesc());
			iDt.setDtShortDesc(model.getDtShortDesc());
			iDt.setDtStatus(model.getDtStatus());

			iclubDocumentTypeDAO.merge(iDt);

			LOGGER.info("Merge Success with ID :: " + model.getDtId());

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
			iclubDocumentTypeDAO.delete(iclubDocumentTypeDAO.findById(id));
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
	public <T extends IclubDocumentTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubDocumentTypeDAO.findAll();

			for (Object object : batmod) {
				IclubDocumentType iDt = (IclubDocumentType) object;

				IclubDocumentTypeModel model = new IclubDocumentTypeModel();

				model.setDtId(iDt.getDtId());
				model.setDtLongDesc(iDt.getDtLongDesc());
				model.setDtShortDesc(iDt.getDtShortDesc());
				model.setDtStatus(iDt.getDtStatus());
				
				if (iDt.getIclubDocuments() != null && iDt.getIclubDocuments().size() > 0) {
					String[] iclubDocuments = new String[iDt.getIclubDocuments().size()];
					int i = 0;
					for (IclubDocument iclubDocument : iDt.getIclubDocuments()) {
						iclubDocuments[i] = iclubDocument.getDId();
						i++;
					}
					model.setIclubDocuments(iclubDocuments);
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
	public IclubDocumentTypeModel getById(@PathParam("id") Long id) {
		IclubDocumentTypeModel model = new IclubDocumentTypeModel();
		try {
			IclubDocumentType bean = iclubDocumentTypeDAO.findById(id);

			model.setDtId(bean.getDtId());
			model.setDtLongDesc(bean.getDtLongDesc());
			model.setDtShortDesc(bean.getDtShortDesc());
			model.setDtStatus(bean.getDtStatus());
			
			if (bean.getIclubDocuments() != null && bean.getIclubDocuments().size() > 0) {
				String[] iclubDocuments = new String[bean.getIclubDocuments().size()];
				int i = 0;
				for (IclubDocument iclubDocument : bean.getIclubDocuments()) {
					iclubDocuments[i] = iclubDocument.getDId();
					i++;
				}
				model.setIclubDocuments(iclubDocuments);
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
			List data = iclubDocumentTypeDAO.getDocumentTypeBySD(val, id);
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

	public IclubDocumentTypeDAO getIclubDocumentTypeDAO() {
		return iclubDocumentTypeDAO;
	}

	public void setIclubDocumentTypeDAO(IclubDocumentTypeDAO iclubDocumentTypeDAO) {
		this.iclubDocumentTypeDAO = iclubDocumentTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}
}
