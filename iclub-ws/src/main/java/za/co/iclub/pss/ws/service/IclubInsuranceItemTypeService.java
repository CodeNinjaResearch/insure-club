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

import za.co.iclub.pss.orm.bean.IclubCoverType;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.bean.IclubInsuranceItemType;
import za.co.iclub.pss.orm.bean.IclubRateType;
import za.co.iclub.pss.orm.bean.IclubSecurityDevice;
import za.co.iclub.pss.orm.bean.IclubSecurityMaster;
import za.co.iclub.pss.orm.dao.IclubCommonDAO;
import za.co.iclub.pss.orm.dao.IclubInsuranceItemTypeDAO;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;
import za.co.iclub.pss.ws.model.IclubInsuranceItemTypeModel;
import za.co.iclub.pss.ws.model.common.ResponseModel;

@Path(value = "/IclubInsuranceItemTypeService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IclubInsuranceItemTypeService {

	protected static final Logger LOGGER = Logger.getLogger(IclubInsuranceItemTypeService.class);
	private IclubCommonDAO iclubCommonDAO;
	private IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO;
	private IclubNamedQueryDAO iclubNamedQueryDAO;

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional(propagation = Propagation.REQUIRED)
	public ResponseModel add(IclubInsuranceItemTypeModel model) {
		try {
			IclubInsuranceItemType iIit = new IclubInsuranceItemType();

			iIit.setIitId(iclubCommonDAO.getNextId(IclubInsuranceItemType.class));
			iIit.setIitLongDesc(model.getIitLongDesc());
			iIit.setIitShortDesc(model.getIitShortDesc());
			iIit.setIitStatus(model.getIitStatus());

			iclubInsuranceItemTypeDAO.save(iIit);

			LOGGER.info("Save Success with ID :: " + iIit.getIitId());

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
	public ResponseModel mod(IclubInsuranceItemTypeModel model) {
		try {
			IclubInsuranceItemType iIit = new IclubInsuranceItemType();

			iIit.setIitId(model.getIitId());
			iIit.setIitLongDesc(model.getIitLongDesc());
			iIit.setIitShortDesc(model.getIitShortDesc());
			iIit.setIitStatus(model.getIitStatus());

			iclubInsuranceItemTypeDAO.merge(iIit);

			LOGGER.info("Merge Success with ID :: " + model.getIitId());

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
			iclubInsuranceItemTypeDAO.delete(iclubInsuranceItemTypeDAO.findById(id));
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
	public <T extends IclubInsuranceItemTypeModel> List<T> list() {
		List<T> ret = new ArrayList<T>();

		try {
			List batmod = iclubInsuranceItemTypeDAO.findAll();
			if (batmod != null && batmod.size() > 0) {
				for (Object object : batmod) {
					IclubInsuranceItemType iIit = (IclubInsuranceItemType) object;

					IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();

					model.setIitId(iIit.getIitId());
					model.setIitLongDesc(iIit.getIitLongDesc());
					model.setIitShortDesc(iIit.getIitShortDesc());
					model.setIitStatus(iIit.getIitStatus());

					if (iIit.getIclubSecurityMasters() != null && iIit.getIclubSecurityMasters().size() > 0) {
						String[] iclubSecurityMasters = new String[iIit.getIclubSecurityMasters().size()];
						int i = 0;
						for (IclubSecurityMaster iclubSecurityMaster : iIit.getIclubSecurityMasters()) {
							iclubSecurityMasters[i] = iclubSecurityMaster.getSmId();
							i++;
						}
						model.setIclubSecurityMasters(iclubSecurityMasters);
					}

					if (iIit.getIclubRateTypes() != null && iIit.getIclubRateTypes().size() > 0) {
						Long[] iclubRateTypes = new Long[iIit.getIclubRateTypes().size()];
						int i = 0;
						for (IclubRateType iclubRateType : iIit.getIclubRateTypes()) {
							iclubRateTypes[i] = iclubRateType.getRtId();
							i++;
						}
						model.setIclubRateTypes(iclubRateTypes);
					}

					if (iIit.getIclubSecurityDevices() != null && iIit.getIclubSecurityDevices().size() > 0) {
						String[] iclubSecurityDevices = new String[iIit.getIclubSecurityDevices().size()];
						int i = 0;
						for (IclubSecurityDevice iclubSecurityDevice : iIit.getIclubSecurityDevices()) {
							iclubSecurityDevices[i] = iclubSecurityDevice.getSdId();
							i++;
						}
						model.setIclubSecurityDevices(iclubSecurityDevices);
					}
					if (iIit.getIclubInsuranceItems() != null && iIit.getIclubInsuranceItems().size() > 0) {
						String[] insuranceItems = new String[iIit.getIclubInsuranceItems().size()];
						int i = 0;
						for (IclubInsuranceItem iclubInsuranceItem : iIit.getIclubInsuranceItems()) {
							insuranceItems[i] = iclubInsuranceItem.getIiId();
							i++;
						}
						model.setIclubInsuranceItems(insuranceItems);
					}
					if (iIit.getIclubInsuranceItems_1() != null && iIit.getIclubInsuranceItems_1().size() > 0) {
						String[] insuranceItems = new String[iIit.getIclubInsuranceItems_1().size()];
						int i = 0;
						for (IclubInsuranceItem iclubInsuranceItem : iIit.getIclubInsuranceItems_1()) {
							insuranceItems[i] = iclubInsuranceItem.getIiId();
							i++;
						}
						model.setIclubInsuranceItems_1(insuranceItems);
					}

					if (iIit.getIclubCoverTypes() != null && iIit.getIclubCoverTypes().size() > 0) {
						Long[] iclubCoverTypes = new Long[iIit.getIclubCoverTypes().size()];
						int i = 0;
						for (IclubCoverType iclubCoverType : iIit.getIclubCoverTypes()) {
							iclubCoverTypes[i] = iclubCoverType.getCtId();
							i++;
						}
						model.setIclubCoverTypes(iclubCoverTypes);
					}

					ret.add((T) model);
				}
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
	public IclubInsuranceItemTypeModel getById(@PathParam("id") Long id) {
		IclubInsuranceItemTypeModel model = new IclubInsuranceItemTypeModel();
		try {
			IclubInsuranceItemType bean = iclubInsuranceItemTypeDAO.findById(id);

			model.setIitId(bean.getIitId());
			model.setIitLongDesc(bean.getIitLongDesc());
			model.setIitShortDesc(bean.getIitShortDesc());
			model.setIitStatus(bean.getIitStatus());

			if (bean.getIclubSecurityMasters() != null && bean.getIclubSecurityMasters().size() > 0) {
				String[] iclubSecurityMasters = new String[bean.getIclubSecurityMasters().size()];
				int i = 0;
				for (IclubSecurityMaster iclubSecurityMaster : bean.getIclubSecurityMasters()) {
					iclubSecurityMasters[i] = iclubSecurityMaster.getSmId();
					i++;
				}
				model.setIclubSecurityMasters(iclubSecurityMasters);
			}

			if (bean.getIclubRateTypes() != null && bean.getIclubRateTypes().size() > 0) {
				Long[] iclubRateTypes = new Long[bean.getIclubRateTypes().size()];
				int i = 0;
				for (IclubRateType iclubRateType : bean.getIclubRateTypes()) {
					iclubRateTypes[i] = iclubRateType.getRtId();
					i++;
				}
				model.setIclubRateTypes(iclubRateTypes);
			}

			if (bean.getIclubSecurityDevices() != null && bean.getIclubSecurityDevices().size() > 0) {
				String[] iclubSecurityDevices = new String[bean.getIclubSecurityDevices().size()];
				int i = 0;
				for (IclubSecurityDevice iclubSecurityDevice : bean.getIclubSecurityDevices()) {
					iclubSecurityDevices[i] = iclubSecurityDevice.getSdId();
					i++;
				}
				model.setIclubSecurityDevices(iclubSecurityDevices);
			}
			if (bean.getIclubInsuranceItems() != null && bean.getIclubInsuranceItems().size() > 0) {
				String[] insuranceItems = new String[bean.getIclubInsuranceItems().size()];
				int i = 0;
				for (IclubInsuranceItem iclubInsuranceItem : bean.getIclubInsuranceItems()) {
					insuranceItems[i] = iclubInsuranceItem.getIiId();
					i++;
				}
				model.setIclubInsuranceItems(insuranceItems);
			}
			if (bean.getIclubInsuranceItems_1() != null && bean.getIclubInsuranceItems_1().size() > 0) {
				String[] insuranceItems = new String[bean.getIclubInsuranceItems_1().size()];
				int i = 0;
				for (IclubInsuranceItem iclubInsuranceItem : bean.getIclubInsuranceItems_1()) {
					insuranceItems[i] = iclubInsuranceItem.getIiId();
					i++;
				}
				model.setIclubInsuranceItems_1(insuranceItems);
			}

			if (bean.getIclubCoverTypes() != null && bean.getIclubCoverTypes().size() > 0) {
				Long[] iclubCoverTypes = new Long[bean.getIclubCoverTypes().size()];
				int i = 0;
				for (IclubCoverType iclubCoverType : bean.getIclubCoverTypes()) {
					iclubCoverTypes[i] = iclubCoverType.getCtId();
					i++;
				}
				model.setIclubCoverTypes(iclubCoverTypes);
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
			List data = iclubNamedQueryDAO.getBySD(val, id, IclubInsuranceItemType.class.getSimpleName());
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

	public IclubInsuranceItemTypeDAO getIclubInsuranceItemTypeDAO() {
		return iclubInsuranceItemTypeDAO;
	}

	public void setIclubInsuranceItemTypeDAO(IclubInsuranceItemTypeDAO iclubInsuranceItemTypeDAO) {
		this.iclubInsuranceItemTypeDAO = iclubInsuranceItemTypeDAO;
	}

	public IclubCommonDAO getIclubCommonDAO() {
		return iclubCommonDAO;
	}

	public void setIclubCommonDAO(IclubCommonDAO iclubCommonDAO) {
		this.iclubCommonDAO = iclubCommonDAO;
	}

	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}

	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
}
