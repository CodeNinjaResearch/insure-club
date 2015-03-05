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

import za.co.iclub.pss.orm.bean.IclubLogin;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubLoginDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubSecurityQuestionDAO;
import za.co.iclub.pss.ws.model.IclubLoginModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubLoginService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubLoginService {

	protected static final Logger LOGGER = Logger.getLogger(IclubLoginService.class);
	private IclubLoginDAO iclubLoginDAO;
	private IclubPersonDAO iclubPersonDAO;
	private IclubSecurityQuestionDAO iclubSecurityQuestionDAO;
	private IclubCommonDAO iclubCommonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubLoginModel model) {
		try {
			IclubLogin login = new IclubLogin();

			login.setLId(model.getLId());
			login.setLCrtdDt(model.getLCrtdDt());
			login.setLLastDate(model.getLLastDate());
			login.setLName(model.getLName());
			login.setLPasswd(model.getLPasswd());
			login.setLSecAns(model.getLSecAns());
			login.setLSecAns(model.getLSecAns());

			iclubLoginDAO.save(login);

			LOGGER.info("Save Success with ID :: " + login.getLId());

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
	public ResponseModel mod(IclubLoginModel model) {
		try {
			IclubLogin login = new IclubLogin();

			login.setLId(model.getLId());
			login.setLCrtdDt(model.getLCrtdDt());
			login.setLLastDate(model.getLLastDate());
			login.setLName(model.getLName());
			login.setLPasswd(model.getLPasswd());
			login.setLSecAns(model.getLSecAns());
			login.setLSecAns(model.getLSecAns());

			iclubLoginDAO.merge(login);

			LOGGER.info("Merge Success with ID :: " + model.getLId());

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
			iclubLoginDAO.delete(iclubLoginDAO.findById(id));
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
	public <T extends IclubLoginModel> List<T> list() {
		List<T> ret = new ArrayList<T>();
		try {
			List loginmod = iclubLoginDAO.findAll();

			for (Object object : loginmod) {
				IclubLogin iclubLogin = (IclubLogin) object;
				IclubLoginModel ibm = new IclubLoginModel();

				ibm.setLId(iclubLogin.getLId());
				ibm.setLCrtdDt(iclubLogin.getLCrtdDt());
				ibm.setLLastDate(iclubLogin.getLLastDate());
				ibm.setLName(iclubLogin.getLName());
				ibm.setLPasswd(iclubLogin.getLPasswd());
				ibm.setLSecAns(iclubLogin.getLSecAns());
				ibm.setLSecAns(iclubLogin.getLSecAns());

				ret.add((T) ibm);
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return ret;
	}

	@GET
	@Path("/person/login/{name}/{pwd}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel login(@PathParam("name") String name, @PathParam("pwd") String pwd) {
		try {
			List logins = iclubLoginDAO.verifyLogin(name, pwd);
			ResponseModel message = new ResponseModel();
			if (logins.size() == 0 || logins.get(0) == null) {
				message.setStatusCode(-1);
				message.setStatusDesc("Invalid PersonName or Password");
			} /*
			 * else if (!(((IclubLogin)
			 * logins.get(0)).getIclubPersonByLCrtdBy().
			 * getIclubEntityStatus().getEntityStatusStatus
			 * ().equalsIgnoreCase("y"))) { message.setStatusCode(1);
			 * message.setStatusDesc("Inactive Person"); }
			 */else {
				message.setStatusCode(0);
				message.setStatusDesc("Login Success");
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
	@Path("/person/{name}")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public IclubLoginModel getByPersonName(@PathParam("name") String name) {
		IclubLoginModel message = new IclubLoginModel();
		try {
			List logins = iclubLoginDAO.findByProperty("LName", name);
			if (logins.size() != 0 || logins.get(0) != null) {
				IclubLogin login = (IclubLogin) logins.get(0);

				message.setLId(login.getLId());
				message.setLCrtdDt(login.getLCrtdDt());
				message.setLLastDate(login.getLLastDate());
				message.setLName(login.getLName());
				message.setLPasswd(login.getLPasswd());
				message.setLSecAns(login.getLSecAns());
				message.setLSecAns(login.getLSecAns());
				message.setIclubPersonByLPersonId(login.getIclubPersonByLPersonId().getPId());
			}
		} catch (Exception e) {
			LOGGER.error(e, e);
		}
		return message;
	}

	public IclubLoginDAO getIclubLoginDAO() {
		return iclubLoginDAO;
	}

	public void setIclubLoginDAO(IclubLoginDAO iclubLoginDAO) {
		this.iclubLoginDAO = iclubLoginDAO;
	}

	public IclubPersonDAO getIclubPersonDAO() {
		return iclubPersonDAO;
	}

	public void setIclubPersonDAO(IclubPersonDAO iclubPersonDAO) {
		this.iclubPersonDAO = iclubPersonDAO;
	}

	public IclubSecurityQuestionDAO getIclubSecurityQuestionDAO() {
		return iclubSecurityQuestionDAO;
	}

	public void setIclubSecurityQuestionDAO(IclubSecurityQuestionDAO iclubSecurityQuestionDAO) {
		this.iclubSecurityQuestionDAO = iclubSecurityQuestionDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

}
