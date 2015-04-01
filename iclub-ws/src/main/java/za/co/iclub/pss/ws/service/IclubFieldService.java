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

import za.co.iclub.pss.orm.bean.IclubField;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubFieldDAO;
import za.co.iclub.pss.ws.model.IclubFieldModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubFieldService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubFieldService {

	protected static final Logger LOGGER = Logger.getLogger(IclubFieldService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubFieldDAO iclubFieldDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubFieldModel model) {
		try {
			IclubField iF = new IclubField();

			iF.setFId(iclubCommonDAO.getNextId(IclubField.class));
			iF.setFName(model.getFName());
			iF.setFDesc(model.getFDesc());
			iF.setFStatus(model.getFStatus());
			iF.setFLTblName(model.getFLTblName());
			iF.setFRate(model.getFRate());
			iF.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);

			iclubFieldDAO.save(iF);

			LOGGER.info("Save Success with ID :: " + iF.getFId());

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
	public ResponseModel mod(IclubFieldModel model) {
		try {
			IclubField iF = new IclubField();

			iF.setFId(model.getFId());
			iF.setFName(model.getFName());
			iF.setFDesc(model.getFDesc());
			iF.setFStatus(model.getFStatus());
			iF.setFLTblName(model.getFLTblName());
			iF.setFRate(model.getFRate());
			iF.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);

			iclubFieldDAO.merge(iF);

			LOGGER.info("Merge Success with ID :: " + model.getFId());

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
			iclubFieldDAO.delete(iclubFieldDAO.findById(id));
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
	public <T extends IclubFieldModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubFieldDAO.findAll();

			for (Object object : batmod) {
				IclubField iF = (IclubField) object;

				IclubFieldModel model = new IclubFieldModel();

				model.setFId(iF.getFId());
				model.setFName(iF.getFName());
				model.setFDesc(iF.getFDesc());
				model.setFStatus(iF.getFStatus());
				model.setFLTblName(iF.getFLTblName());
				model.setFRate(iF.getFRate());
				model.setIclubEntityType(iF.getIclubEntityType() != null ? iF.getIclubEntityType().getEtId() : null);
				if (iF.getIclubRateTypes() != null && iF.getIclubRateTypes().size() > 0) {
					Long[] rateTypes = new Long[iF.getIclubRateTypes().size()];
					int i = 0;
					for (IclubRateType rateType : iF.getIclubRateTypes()) {
						rateTypes[i] = rateType.getRtId();
						i++;
					}

					model.setIclubRateTypes(rateTypes);
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
	public IclubFieldModel getById(@PathParam("id") Long id) {
		IclubFieldModel model = new IclubFieldModel();
		try {
			IclubField bean = iclubFieldDAO.findById(id);

			model.setFId(bean.getFId());
			model.setFName(bean.getFName());
			model.setFDesc(bean.getFDesc());
			model.setFStatus(bean.getFStatus());
			model.setFLTblName(bean.getFLTblName());
			model.setFRate(bean.getFRate());
			model.setIclubEntityType(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtId() : null);
			if (bean.getIclubRateTypes() != null && bean.getIclubRateTypes().size() > 0) {
				Long[] rateTypes = new Long[bean.getIclubRateTypes().size()];
				int i = 0;
				for (IclubRateType rateType : bean.getIclubRateTypes()) {
					rateTypes[i] = rateType.getRtId();
					i++;
				}

				model.setIclubRateTypes(rateTypes);
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
			List data = iclubFieldDAO.getFieldBySD(val, id);
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

	public IclubFieldDAO getIclubFieldDAO() {
		return iclubFieldDAO;
	}

	public void setIclubFieldDAO(IclubFieldDAO iclubFieldDAO) {
		this.iclubFieldDAO = iclubFieldDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubEntityTypeDAO getIclubEntityTypeDAO() {
		return iclubEntityTypeDAO;
	}

	public void setIclubEntityTypeDAO(IclubEntityTypeDAO iclubEntityTypeDAO) {
		this.iclubEntityTypeDAO = iclubEntityTypeDAO;
	}
}
