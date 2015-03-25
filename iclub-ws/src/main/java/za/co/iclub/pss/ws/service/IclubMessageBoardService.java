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
import za.co.iclub.pss.orm.bean.IclubMessageBoard;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubMessageBoardDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.ws.model.IclubMessageBoardModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubMessageBoardService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubMessageBoardService {

	protected static final Logger LOGGER = Logger.getLogger(IclubMessageBoardService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubMessageBoardDAO iclubMessageBoardDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubMessageBoardModel model) {
		try {
			IclubMessageBoard iCMb = new IclubMessageBoard();

			iCMb.setMbId(model.getMbId());
			iCMb.setMbContent(model.getMbContent());
			iCMb.setMbContent(model.getMbContent());
			iCMb.setMbTag(model.getMbTag());
			iCMb.setMbTitle(model.getMbTitle());
			iCMb.setMbCrtdDt(model.getMbCrtdDt());
			iCMb.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubMessageBoardDAO.save(iCMb);

			LOGGER.info("Save Success with ID :: " + iCMb.getMbId());

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
	public ResponseModel mod(IclubMessageBoardModel model) {
		try {
			IclubMessageBoard iCMb = new IclubMessageBoard();

			iCMb.setMbId(model.getMbId());
			iCMb.setMbContent(model.getMbContent());
			iCMb.setMbContent(model.getMbContent());
			iCMb.setMbTag(model.getMbTag());
			iCMb.setMbTitle(model.getMbTitle());
			iCMb.setMbCrtdDt(model.getMbCrtdDt());
			iCMb.setIclubPerson(model.getIclubPerson() != null && !model.getIclubPerson().trim().equalsIgnoreCase("") ? iclubPersonDAO.findById(model.getIclubPerson()) : null);

			iclubMessageBoardDAO.merge(iCMb);

			LOGGER.info("Merge Success with ID :: " + model.getMbId());

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
			iclubMessageBoardDAO.delete(iclubMessageBoardDAO.findById(id));
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
	public <T extends IclubMessageBoardModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMessageBoardDAO.findAll();

			for (Object object : batmod) {
				IclubMessageBoard iCMb = (IclubMessageBoard) object;

				IclubMessageBoardModel model = new IclubMessageBoardModel();

				model.setMbId(iCMb.getMbId());
				model.setMbContent(iCMb.getMbContent());
				model.setMbContent(iCMb.getMbContent());
				model.setMbTag(iCMb.getMbTag());
				model.setMbTitle(iCMb.getMbTitle());
				model.setMbCrtdDt(iCMb.getMbCrtdDt());
				model.setIclubPerson(iCMb.getIclubPerson() != null ? (iCMb.getIclubPerson().getPId()) : null);
				if (iCMb.getIclubMbComments() != null && iCMb.getIclubMbComments().size() > 0) {
					String[] iclubMbComments = new String[iCMb.getIclubMbComments().size()];
					int i = 0;
					for (IclubMbComment iclubMbComment : iCMb.getIclubMbComments()) {
						iclubMbComments[i] = iclubMbComment.getMbcId();
						i++;
					}
					model.setIclubMbComments(iclubMbComments);
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
	public <T extends IclubMessageBoardModel> List<T> getByUser(@PathParam("user") String user) {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubMessageBoardDAO.findByUser(user);

			for (Object object : batmod) {
				IclubMessageBoard iCMb = (IclubMessageBoard) object;

				IclubMessageBoardModel model = new IclubMessageBoardModel();

				model.setMbId(iCMb.getMbId());
				model.setMbContent(iCMb.getMbContent());
				model.setMbContent(iCMb.getMbContent());
				model.setMbTag(iCMb.getMbTag());
				model.setMbTitle(iCMb.getMbTitle());
				model.setMbCrtdDt(iCMb.getMbCrtdDt());
				model.setIclubPerson(iCMb.getIclubPerson() != null ? (iCMb.getIclubPerson().getPId()) : null);
				if (iCMb.getIclubMbComments() != null && iCMb.getIclubMbComments().size() > 0) {
					String[] iclubMbComments = new String[iCMb.getIclubMbComments().size()];
					int i = 0;
					for (IclubMbComment iclubMbComment : iCMb.getIclubMbComments()) {
						iclubMbComments[i] = iclubMbComment.getMbcId();
						i++;
					}
					model.setIclubMbComments(iclubMbComments);
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
	public IclubMessageBoardModel getById(@PathParam("id") String id) {
		IclubMessageBoardModel model = new IclubMessageBoardModel();
		try {
			IclubMessageBoard bean = iclubMessageBoardDAO.findById(id);

			model.setMbId(bean.getMbId());
			model.setMbContent(bean.getMbContent());
			model.setMbContent(bean.getMbContent());
			model.setMbTag(bean.getMbTag());
			model.setMbTitle(bean.getMbTitle());
			model.setMbCrtdDt(bean.getMbCrtdDt());
			model.setIclubPerson(bean.getIclubPerson() != null ? (bean.getIclubPerson().getPId()) : null);
			if (bean.getIclubMbComments() != null && bean.getIclubMbComments().size() > 0) {
				String[] iclubMbComments = new String[bean.getIclubMbComments().size()];
				int i = 0;
				for (IclubMbComment iclubMbComment : bean.getIclubMbComments()) {
					iclubMbComments[i] = iclubMbComment.getMbcId();
					i++;
				}
				model.setIclubMbComments(iclubMbComments);
			}

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return model;
	}

	public IclubMessageBoardDAO getIclubMessageBoardDAO() {
		return iclubMessageBoardDAO;
	}

	public void setIclubMessageBoardDAO(IclubMessageBoardDAO iclubMessageBoardDAO) {
		this.iclubMessageBoardDAO = iclubMessageBoardDAO;
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
