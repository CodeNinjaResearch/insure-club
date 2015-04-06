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

import za.co.iclub.pss.orm.bean.IclubMbComment;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubMbCommentDAO;
import za.co.iclub.pss.orm.dao.IclubMessageBoardDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubMbCommentModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubMbCommentService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubMbCommentService {

	protected static final Logger LOGGER = Logger.getLogger(IclubMbCommentService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubMbCommentDAO iclubMbCommentDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubMessageBoardDAO iclubMessageBoardDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubMbCommentModel model) {
		try {
			IclubMbComment iCMbc = new IclubMbComment();

			iCMbc.setMbcId(model.getMbcId());
			iCMbc.setMbcDesc(model.getMbcDesc());
			iCMbc.setMbcCrtdDt(model.getMbcCrtdDt());
			iCMbc.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iCMbc.setIclubMessageBoard(model.getIclubMessageBoard() != null && !model.getIclubMessageBoard().trim().equalsIgnoreCase("") ? iclubMessageBoardDAO.findById(model.getIclubMessageBoard().trim()) : null);

			iclubMbCommentDAO.save(iCMbc);

			LOGGER.info("Save Success with ID :: " + iCMbc.getMbcId());

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
	public ResponseModel mod(IclubMbCommentModel model) {
		try {
			IclubMbComment iCMbc = new IclubMbComment();

			iCMbc.setMbcId(model.getMbcId());
			iCMbc.setMbcDesc(model.getMbcDesc());
			iCMbc.setMbcCrtdDt(model.getMbcCrtdDt());
			iCMbc.setIclubMessageBoard(model.getIclubMessageBoard() != null && !model.getIclubMessageBoard().trim().equalsIgnoreCase("") ? iclubMessageBoardDAO.findById(model.getIclubMessageBoard().trim()) : null);
			iCMbc.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubMbCommentDAO.merge(iCMbc);

			LOGGER.info("Merge Success with ID :: " + model.getMbcId());

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
			iclubMbCommentDAO.delete(iclubMbCommentDAO.findById(id));
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
	public <T extends IclubMbCommentModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMbCommentDAO.findAll();

			for (Object object : batmod) {
				IclubMbComment iCMbc = (IclubMbComment) object;

				IclubMbCommentModel model = new IclubMbCommentModel();

				model.setMbcId(iCMbc.getMbcId());
				model.setMbcCrtdDt(iCMbc.getMbcCrtdDt());
				model.setMbcDesc(iCMbc.getMbcDesc());
				model.setIclubMessageBoard(iCMbc.getIclubMessageBoard() != null ? iCMbc.getIclubMessageBoard().getMbId() : null);
				model.setIclubPerson(iCMbc.getIclubPerson() != null ? (iCMbc.getIclubPerson().getPId()) : null);

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
	public <T extends IclubMbCommentModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.findByUser(user, IclubMbComment.class.getSimpleName());

			for (Object object : batmod) {
				IclubMbComment iCMbc = (IclubMbComment) object;

				IclubMbCommentModel model = new IclubMbCommentModel();

				model.setMbcId(iCMbc.getMbcId());
				model.setMbcCrtdDt(iCMbc.getMbcCrtdDt());
				model.setMbcDesc(iCMbc.getMbcDesc());
				model.setIclubMessageBoard(iCMbc.getIclubMessageBoard() != null ? iCMbc.getIclubMessageBoard().getMbId() : null);
				model.setIclubPerson(iCMbc.getIclubPerson() != null ? (iCMbc.getIclubPerson().getPId()) : null);

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
	public IclubMbCommentModel getById(@PathParam("id") String id) {
		IclubMbCommentModel model = new IclubMbCommentModel();
		try {
			IclubMbComment bean = iclubMbCommentDAO.findById(id);

			model.setMbcId(bean.getMbcId());
			model.setMbcCrtdDt(bean.getMbcCrtdDt());
			model.setMbcDesc(bean.getMbcDesc());
			model.setIclubMessageBoard(bean.getIclubMessageBoard() != null ? bean.getIclubMessageBoard().getMbId() : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	@GET
	@Path("/get/messageboard/{mbId}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public <T extends IclubMbCommentModel> List<T> getByMbId(@PathParam("mbId") String mbId) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubNamedQueryDAO.getMbCommentsByMbId(mbId);

			for (Object object : batmod) {
				IclubMbComment iCMbc = (IclubMbComment) object;

				IclubMbCommentModel model = new IclubMbCommentModel();

				model.setMbcId(iCMbc.getMbcId());
				model.setMbcCrtdDt(iCMbc.getMbcCrtdDt());
				model.setMbcDesc(iCMbc.getMbcDesc());
				model.setIclubMessageBoard(iCMbc.getIclubMessageBoard() != null ? iCMbc.getIclubMessageBoard().getMbId() : null);
				model.setIclubPerson(iCMbc.getIclubPerson() != null ? (iCMbc.getIclubPerson().getPId()) : null);

				ret.add((T) model);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}

		return ret;
	}

	public IclubMbCommentDAO getIclubMbCommentDAO() {
		return iclubMbCommentDAO;
	}

	public void setIclubMbCommentDAO(IclubMbCommentDAO iclubMbCommentDAO) {
		this.iclubMbCommentDAO = iclubMbCommentDAO;
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

	public IclubMessageBoardDAO getIclubMessageBoardDAO() {
		return iclubMessageBoardDAO;
	}

	public void setIclubMessageBoardDAO(IclubMessageBoardDAO iclubMessageBoardDAO) {
		this.iclubMessageBoardDAO = iclubMessageBoardDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}

}
