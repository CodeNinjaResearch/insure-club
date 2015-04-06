package za.co.iclub.pss.ws.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubDocument;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubDocumentDAO;
import za.co.iclub.pss.orm.dao.IclubDocumentTypeDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubDocumentModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubDocumentService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubDocumentService {

	protected static final Logger LOGGER = Logger.getLogger(IclubDocumentService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubDocumentDAO iclubDocumentDAO;
	private IclubDocumentTypeDAO iclubDocumentTypeDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubDocumentModel model) {
		try {
			IclubDocument iCD = new IclubDocument();

			iCD.setDId(model.getDId());
			iCD.setDContent(model.getDContent());
			iCD.setDEntityId(model.getDEntityId());
			iCD.setDSize(model.getDSize());
			iCD.setDMimeType(model.getDMimeType());
			iCD.setDName(model.getDName());
			iCD.setDCrtdDt(model.getDCrtdDt());
			iCD.setIclubDocumentType(model.getIclubDocumentType() != null ? iclubDocumentTypeDAO.findById(model.getIclubDocumentType()) : null);
			iCD.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
			iCD.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubDocumentDAO.save(iCD);

			LOGGER.info("Save Success with ID :: " + iCD.getDId());

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
	public ResponseModel mod(IclubDocumentModel model) {
		try {
			IclubDocument iCD = new IclubDocument();

			iCD.setDId(model.getDId());
			iCD.setDContent(model.getDContent());
			iCD.setDEntityId(model.getDEntityId());
			iCD.setDSize(model.getDSize());
			iCD.setDMimeType(model.getDMimeType());
			iCD.setDName(model.getDName());
			iCD.setDCrtdDt(model.getDCrtdDt());
			iCD.setIclubDocumentType(model.getIclubDocumentType() != null ? iclubDocumentTypeDAO.findById(model.getIclubDocumentType()) : null);
			iCD.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
			iCD.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubDocumentDAO.merge(iCD);

			LOGGER.info("Merge Success with ID :: " + model.getDId());

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
	public Response del(@PathParam("id") String id) {
		try {
			iclubDocumentDAO.delete(iclubDocumentDAO.findById(id));
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
	public <T extends IclubDocumentModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubDocumentDAO.findAll();

			for (Object object : batmod) {
				IclubDocument iCD = (IclubDocument) object;

				IclubDocumentModel model = new IclubDocumentModel();

				model.setDId(iCD.getDId());
				model.setDContent(iCD.getDContent());
				model.setDEntityId(iCD.getDEntityId());
				model.setDSize(iCD.getDSize());
				model.setDMimeType(iCD.getDMimeType());
				model.setDName(iCD.getDName());
				model.setDCrtdDt(iCD.getDCrtdDt());
				model.setIclubDocumentType(iCD.getIclubDocumentType() != null ? (iCD.getIclubDocumentType().getDtId()) : null);
				model.setIclubEntityType(iCD.getIclubEntityType() != null ? (iCD.getIclubEntityType().getEtId()) : null);
				model.setIclubPerson(iCD.getIclubPerson() != null ? (iCD.getIclubPerson().getPId()) : null);

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
	public <T extends IclubDocumentModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubDocument.class.getSimpleName());

			for (Object object : batmod) {
				IclubDocument iCD = (IclubDocument) object;

				IclubDocumentModel model = new IclubDocumentModel();

				model.setDId(iCD.getDId());
				model.setDContent(iCD.getDContent());
				model.setDEntityId(iCD.getDEntityId());
				model.setDSize(iCD.getDSize());
				model.setDMimeType(iCD.getDMimeType());
				model.setDName(iCD.getDName());
				model.setDCrtdDt(iCD.getDCrtdDt());
				model.setIclubDocumentType(iCD.getIclubDocumentType() != null ? (iCD.getIclubDocumentType().getDtId()) : null);
				model.setIclubEntityType(iCD.getIclubEntityType() != null ? (iCD.getIclubEntityType().getEtId()) : null);
				model.setIclubPerson(iCD.getIclubPerson() != null ? (iCD.getIclubPerson().getPId()) : null);

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
	public IclubDocumentModel getById(@PathParam("id") String id) {
		IclubDocumentModel model = new IclubDocumentModel();
		try {
			IclubDocument bean = iclubDocumentDAO.findById(id);

			model.setDId(bean.getDId());
			model.setDContent(bean.getDContent());
			model.setDEntityId(bean.getDEntityId());
			model.setDSize(bean.getDSize());
			model.setDMimeType(bean.getDMimeType());
			model.setDName(bean.getDName());
			model.setDCrtdDt(bean.getDCrtdDt());
			model.setIclubDocumentType(bean.getIclubDocumentType() != null ? (bean.getIclubDocumentType().getDtId()) : null);
			model.setIclubEntityType(bean.getIclubEntityType() != null ? (bean.getIclubEntityType().getEtId()) : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Transactional(propagation = Propagation.REQUIRED)
	public Response upload(MultipartBody body) {
		try {

			List<Attachment> attachments = body.getAllAttachments();
			for (Attachment attachment : attachments) {
				String docId = attachment.getContentId();
				DataHandler handler = attachment.getDataHandler();
				InputStream stream = handler.getInputStream();
				byte[] bytes = IOUtils.toByteArray(stream);
				IclubDocument iDocument = iclubDocumentDAO.findById(docId);
				iDocument.setDBlob(bytes);
				iclubDocumentDAO.merge(iDocument);
				LOGGER.info("Save Success with ID :: " + iDocument.getDId());
			}

			Response response = Response.ok("SUCCESS").build();
			return response;
		} catch (Exception e) {
			LOGGER.error(e, e);
			Response response = Response.ok(e.getMessage()).build();
			return response;
		}

	}

	@GET
	@Path("/download/{docId}")
	@Produces(MediaType.MULTIPART_FORM_DATA)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED)
	public List<Attachment> downloadFile(@PathParam("docId") String docId) {
		List<Attachment> attachments = new ArrayList<Attachment>();
		try {
			IclubDocument document = iclubDocumentDAO.findById(docId);
			ContentDisposition cd = new ContentDisposition("attachment;filename=" + document.getDName() + ";filetype=" + document.getDMimeType());
			InputStream in = new ByteArrayInputStream(document.getDBlob());
			Attachment attachment = new Attachment("id", in, cd);
			attachments.add(attachment);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return attachments;
	}

	@GET
	@Path("/del/entity/{id}/{typeid}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public Response delByEntity(@PathParam("id") String id, @PathParam("typeid") Long typeId) {
		try {
			List batmod = iclubNamedQueryDAO.getDocumentByEntity(id, typeId);

			for (Object object : batmod) {
				IclubDocument iDocument = (IclubDocument) object;
				iclubDocumentDAO.delete(iDocument);
			}
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.error(e, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Path("/get/entity/{id}/{typeid}")
	@Produces("application/json")
	@Consumes("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubDocumentModel> List<T> getByEntity(@PathParam("id") String id, @PathParam("typeid") Long typeId) {
		List<T> ret = new ArrayList<T>();
		try {
			List batmod = iclubNamedQueryDAO.getDocumentByEntity(id, typeId);

			for (Object object : batmod) {
				IclubDocument iDocument = (IclubDocument) object;

				IclubDocumentModel model = new IclubDocumentModel();

				model.setDId(iDocument.getDId());
				model.setDContent(iDocument.getDContent());
				model.setDEntityId(iDocument.getDEntityId());
				model.setDSize(iDocument.getDSize());
				model.setDMimeType(iDocument.getDMimeType());
				model.setDName(iDocument.getDName());
				model.setDCrtdDt(iDocument.getDCrtdDt());
				model.setIclubDocumentType(iDocument.getIclubDocumentType() != null ? (iDocument.getIclubDocumentType().getDtId()) : null);
				model.setIclubEntityType(iDocument.getIclubEntityType() != null ? (iDocument.getIclubEntityType().getEtId()) : null);
				model.setIclubPerson(iDocument.getIclubPerson() != null ? (iDocument.getIclubPerson().getPId()) : null);

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubDocumentDAO getIclubDocumentDAO() {
		return iclubDocumentDAO;
	}

	public void setIclubDocumentDAO(IclubDocumentDAO iclubDocumentDAO) {
		this.iclubDocumentDAO = iclubDocumentDAO;
	}

	public IclubDocumentTypeDAO getIclubDocumentTypeDAO() {
		return iclubDocumentTypeDAO;
	}

	public void setIclubDocumentTypeDAO(IclubDocumentTypeDAO iclubDocumentTypeDAO) {
		this.iclubDocumentTypeDAO = iclubDocumentTypeDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubEntityTypeDAO getIclubEntityTypeDAO() {
		return iclubEntityTypeDAO;
	}

	public void setIclubEntityTypeDAO(IclubEntityTypeDAO iclubEntityTypeDAO) {
		this.iclubEntityTypeDAO = iclubEntityTypeDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
