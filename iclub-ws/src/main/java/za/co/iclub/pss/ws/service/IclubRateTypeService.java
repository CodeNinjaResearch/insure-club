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

import za.co.iclub.pss.orm.bean.IclubRateEngine;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubEntityTypeDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubPersonDAO;
import za.co.iclub.pss.orm.dao.IclubRateTypeDAO;
import za.co.iclub.pss.ws.model.IclubRateTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubRateTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubRateTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubRateTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubRateTypeDAO iclubRateTypeDAO;
	private IclubEntityTypeDAO iclubEntityTypeDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubPersonDAO iclubPersonDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubRateTypeModel model) {
		try {
			IclubRateType iRt = new IclubRateType();

			iRt.setRtId(iclubCommonDAO.getNextId(IclubRateType.class));
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());
			iRt.setRtLookupTblNm(model.getRtLookupTblNm());
			iRt.setRtQuoteType(model.getRtQuoteType());
			iRt.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
			iRt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iRt.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iRt.setRtCrtdDt(model.getRtCrtdDt());
			iRt.setRtType(model.getRtType());
			iRt.setRtFieldNm(model.getRtFieldNm());

			iclubRateTypeDAO.save(iRt);

			LOGGER.info("Save Success with ID :: " + iRt.getRtId());

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
	public ResponseModel mod(IclubRateTypeModel model) {
		try {
			IclubRateType iRt = new IclubRateType();

			iRt.setRtId(model.getRtId());
			iRt.setRtLongDesc(model.getRtLongDesc());
			iRt.setRtShortDesc(model.getRtShortDesc());
			iRt.setRtStatus(model.getRtStatus());
			iRt.setRtQuoteType(model.getRtQuoteType());
			iRt.setRtLookupTblNm(model.getRtLookupTblNm());
			iRt.setIclubEntityType(model.getIclubEntityType() != null ? iclubEntityTypeDAO.findById(model.getIclubEntityType()) : null);
			iRt.setIclubInsuranceItemType(model.getIclubInsuranceItemType() != null ? iclubInsuranceItemTypeDAO.findById(model.getIclubInsuranceItemType()) : null);
			iRt.setIclubPerson(model.getIclubPerson() != null ? iclubPersonDAO.findById(model.getIclubPerson()) : null);
			iRt.setRtCrtdDt(model.getRtCrtdDt());
			iRt.setRtType(model.getRtType());
			iRt.setRtFieldNm(model.getRtFieldNm());

			iclubRateTypeDAO.merge(iRt);

			LOGGER.info("Merge Success with ID :: " + model.getRtId());

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
			iclubRateTypeDAO.delete(iclubRateTypeDAO.findById(id));
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
	public <T extends IclubRateTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubRateTypeDAO.findAll();

			for (Object object : batmod) {
				IclubRateType iRt = (IclubRateType) object;

				IclubRateTypeModel model = new IclubRateTypeModel();

				model.setRtId(iRt.getRtId());
				model.setRtLongDesc(iRt.getRtLongDesc());
				model.setRtShortDesc(iRt.getRtShortDesc());
				model.setRtStatus(iRt.getRtStatus());
				model.setRtLookupTblNm(iRt.getRtLookupTblNm());
				model.setRtQuoteType(iRt.getRtQuoteType());
				model.setIclubEntityType(iRt.getIclubEntityType() != null ? iRt.getIclubEntityType().getEtId() : null);
				model.setIclubInsuranceItemType(iRt.getIclubInsuranceItemType() != null ? iRt.getIclubInsuranceItemType().getIitId() : null);
				model.setIclubPerson(iRt.getIclubPerson() != null ? iRt.getIclubPerson().getPId() : null);
				model.setRtCrtdDt(iRt.getRtCrtdDt());
				model.setRtType(iRt.getRtType());
				model.setRtFieldNm(iRt.getRtFieldNm());

				if (iRt.getIclubRateEngines() != null && iRt.getIclubRateEngines().size() > 0) {
					String[] rateEngines = new String[iRt.getIclubRateEngines().size()];
					int i = 0;
					for (IclubRateEngine rateEngine : iRt.getIclubRateEngines()) {
						rateEngines[i] = rateEngine.getReId();
						i++;
					}

					model.setIclubRateEngines(rateEngines);
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
	public IclubRateTypeModel getById(@PathParam("id") Long id) {
		IclubRateTypeModel model = new IclubRateTypeModel();
		try {
			IclubRateType bean = iclubRateTypeDAO.findById(id);

			model.setRtId(bean.getRtId());
			model.setRtLongDesc(bean.getRtLongDesc());
			model.setRtShortDesc(bean.getRtShortDesc());
			model.setRtStatus(bean.getRtStatus());
			model.setRtQuoteType(bean.getRtQuoteType());
			model.setRtLookupTblNm(bean.getRtLookupTblNm());
			model.setIclubEntityType(bean.getIclubEntityType() != null ? bean.getIclubEntityType().getEtId() : null);
			model.setIclubInsuranceItemType(bean.getIclubInsuranceItemType() != null ? bean.getIclubInsuranceItemType().getIitId() : null);
			model.setIclubPerson(bean.getIclubPerson() != null ? bean.getIclubPerson().getPId() : null);
			model.setRtCrtdDt(bean.getRtCrtdDt());
			model.setRtType(bean.getRtType());
			model.setRtFieldNm(bean.getRtFieldNm());

			if (bean.getIclubRateEngines() != null && bean.getIclubRateEngines().size() > 0) {
				String[] rateEngines = new String[bean.getIclubRateEngines().size()];
				int i = 0;
				for (IclubRateEngine rateEngine : bean.getIclubRateEngines()) {
					rateEngines[i] = rateEngine.getReId();
					i++;
				}

				model.setIclubRateEngines(rateEngines);
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
			List data = iclubRateTypeDAO.getRateTypeBySD(val, id);
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

	public IclubRateTypeDAO getIclubRateTypeDAO() {
		return iclubRateTypeDAO;
	}

	public void setIclubRateTypeDAO(IclubRateTypeDAO iclubRateTypeDAO) {
		this.iclubRateTypeDAO = iclubRateTypeDAO;
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
