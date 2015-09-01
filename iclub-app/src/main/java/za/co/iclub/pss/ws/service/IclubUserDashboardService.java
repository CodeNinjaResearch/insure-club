package za.co.iclub.pss.ws.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.model.ws.IclubUserDashboardModel;
import za.co.iclub.pss.orm.dao.IclubNamedQueryDAO;

@Path(value = "/IclubUserDashboardService")
@SuppressWarnings({ "rawtypes" })
public class IclubUserDashboardService {
	
	private static final Logger LOGGER = Logger.getLogger(IclubUserDashboardService.class);
	private IclubNamedQueryDAO iclubNamedQueryDAO;
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/get/{userId}")
	@Consumes("application/json")
	@Produces("application/json")
	@Transactional
	public IclubUserDashboardModel validateSd(@PathParam("userId") String userId) {
		try {
			IclubUserDashboardModel model = new IclubUserDashboardModel();
			String quoteIds = "";
			Double insuredValue = 0.0;
			String policyIds = "";
			List<String> policyIdsList = new ArrayList<String>();
			String cliamIds = "";
			List dataQuotes = null;
			
			try {
				List data = iclubNamedQueryDAO.getQuoteDetailsByUserId(userId, 1l);
				
				if (data != null && data.size() > 0) {
					Object[] objects = (Object[]) data.get(0);
					model.setTotalQuoteCnt(objects[0] != null ? new Long(objects[0].toString()) : 0);
					model.setTotalQPremium(objects[1] != null ? new Double(objects[1].toString()) : 0);
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			try {
				List data = iclubNamedQueryDAO.getQuoteDetailsIdByUserId(userId, 1l);
				
				if (data != null && data.size() > 0) {
					
					for (int i = 0; i < data.size(); i++) {
						quoteIds = quoteIds.trim().equalsIgnoreCase("") ? data.get(i).toString() : quoteIds + "," + data.get(i).toString();
					}
					dataQuotes = data;
					data = iclubNamedQueryDAO.getIclubQuoteIdByUserId(data);
					
					String vehicleIds = "";
					String propertyIds = "";
					
					if (data != null && data.size() > 0) {
						for (int i = 0; i < data.size(); i++) {
							Object[] object = (Object[]) data.get(i);
							
							if (object[0] != null && object[0].toString().equalsIgnoreCase("1")) {
								vehicleIds = vehicleIds.trim().equalsIgnoreCase("") ? object[1].toString() + "" : vehicleIds + "," + object[1].toString() + "";
							} else if (object[0] != null && object[0].toString().equalsIgnoreCase("2")) {
								propertyIds = propertyIds.trim().equalsIgnoreCase("") ? object[1].toString() + "" : propertyIds + "," + object[1].toString() + "";
							}
							
						}
						
						data = iclubNamedQueryDAO.getIclubPropertyIValueByPIds(propertyIds);
						if (data != null && data.size() > 0) {
							Object[] object = (Object[]) data.get(0);
							insuredValue = object[0] != null ? insuredValue + ((Double) object[0]) : insuredValue;
							insuredValue = object[1] != null ? insuredValue + ((Double) object[1]) : insuredValue;
						}
						
						data = iclubNamedQueryDAO.getIclubVehicleIValueByVIds(vehicleIds);
						if (data != null && data.size() > 0) {
							insuredValue = data.get(0) != null ? insuredValue + ((BigDecimal) data.get(0)).doubleValue() : insuredValue;
						}
						model.setTotalQEstValue(insuredValue);
					}
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			try {
				Object object = iclubNamedQueryDAO.getQuoteDetailsValDateByUserId(userId, 1l);
				
				if (object != null) {
					model.setEarliestQExpiry((Date) object);
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			try {
				Object object = iclubNamedQueryDAO.getQuoteDetailsValDateByUserId(userId, 1l);
				
				if (object != null) {
					model.setEarliestQExpiry((Date) object);
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			try {
				List data = iclubNamedQueryDAO.getIclubPolicIdsByQuotes(userId);
				dataQuotes = new ArrayList<String>();
				if (data != null && data.size() > 0) {
					quoteIds = "";
					Double prorataPremium = 0.0;
					insuredValue = 0.0;
					for (int i = 0; i < data.size(); i++) {
						Object[] object = (Object[]) data.get(i);
						
						if (model.getNextPremiumDate() == null && object[3] != null) {
							model.setNextPremiumDate(new Long(object[3].toString()));
						} else if (model.getNextPremiumDate() != null && object[3] != null && model.getNextPremiumDate().intValue() < new Integer(object[3].toString())) {
							model.setNextPremiumDate(new Long(object[3].toString()));
						}
						if (object[2].toString() != null) {
							dataQuotes.add(object[2].toString());
						}
						quoteIds = quoteIds.trim().equalsIgnoreCase("") ? object[2].toString().toString() + "" : quoteIds + "," + object[2].toString().toString() + "";
						if (object[0].toString() != null) {
							policyIdsList.add(object[0].toString());
						}
						
						policyIds = policyIds.trim().equalsIgnoreCase("") ? object[0].toString() + "" : policyIds + "," + object[0].toString().toString() + "";
						prorataPremium = object != null && object[1] != null ? new Double(object[1].toString()) + prorataPremium : prorataPremium;
						insuredValue = object != null && object[4] != null ? new Double(object[4].toString()) + insuredValue : insuredValue;
					}
					model.setTotalPolicyCnt(new Long(data.size()));
					
					data = iclubNamedQueryDAO.getIclubQuoteIdByUserId(dataQuotes);
					String vehicleIds = "";
					String propertyIds = "";
					insuredValue = prorataPremium + insuredValue;
					int noOfVehicles = 0;
					int noOfProperties = 0;
					
					if (data != null && data.size() > 0) {
						for (int i = 0; i < data.size(); i++) {
							Object[] object = (Object[]) data.get(i);
							
							if (object[0] != null && object[0].toString().equalsIgnoreCase("1")) {
								vehicleIds = vehicleIds.trim().equalsIgnoreCase("") ? object[1].toString() + "" : vehicleIds + "," + object[1].toString() + "";
								noOfVehicles++;
							} else if (object[0] != null && object[0].toString().equalsIgnoreCase("2")) {
								propertyIds = propertyIds.trim().equalsIgnoreCase("") ? object[1].toString() + "" : propertyIds + "," + object[1].toString() + "";
								noOfProperties++;
							}
							
						}
						model.setNoOfProperties(new Long(noOfProperties));
						model.setNoOfVehicles(new Long(noOfVehicles));
						
						model.setTotalPPremium(insuredValue);
					}
					
				}
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			
			try {
				List data = iclubNamedQueryDAO.getIclubClaimValueByPolicyIds(policyIdsList);
				
				if (data != null && data.size() > 0) {
					
					Double claimValue = 0.0;
					Double rejectedClaimValue = 0.0;
					Double approvedClaimValue = 0.0;
					int claimCount = 0;
					for (int i = 0; i < data.size(); i++) {
						Object[] object = (Object[]) data.get(i);
						claimCount++;
						cliamIds = cliamIds.trim().equalsIgnoreCase("") ? object[0].toString().toString() + "" : cliamIds + "," + object[0].toString().toString() + "";
						claimValue = object != null && object[1] != null ? new Double(object[1].toString()) + claimValue : claimValue;
						
						if (object[2] != null && object[2].toString().equalsIgnoreCase("5")) {
							approvedClaimValue = object != null && object[1] != null ? new Double(object[1].toString()) + approvedClaimValue : approvedClaimValue;
						}
						if (object[2] != null && object[2].toString().equalsIgnoreCase("9")) {
							rejectedClaimValue = object != null && object[1] != null ? new Double(object[1].toString()) + rejectedClaimValue : rejectedClaimValue;
						}
					}
					
					model.setTotalClaimCnt(new Long(claimCount));
					model.setApprovedClaimValue(approvedClaimValue);
					model.setRejectedClaimValue(rejectedClaimValue);
					model.setTotalClaimValue(claimValue);
				}
				
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			try {
				int policycount = 0;
				List data = iclubNamedQueryDAO.getIclubPaymentsByClaimIds(cliamIds);
				
				if (data != null && data.size() > 0) {
					Object[] object = (Object[]) data.get(0);
					policycount = object != null && object[0] != null ? new Integer(object[0].toString()) : 0;
					model.setTotalCPremium(object != null && object[1] != null ? new Double(object[1].toString()) : 0.0);
				}
				data = iclubNamedQueryDAO.getIclubPaymentByPolicyIds(policyIds);
				
				if (data != null && data.size() > 0) {
					Object[] object = (Object[]) data.get(0);
					policycount = object != null && object[0] != null ? new Integer(object[0].toString()) + policycount : policycount;
					model.setTotalPyPremium(object != null && object[1] != null ? new Double(object[1].toString()) : 0.0);
				}
				
				model.setTotalPaymentsCnt(new Long(policycount));
				
			} catch (Exception e) {
				LOGGER.error(e, e);
			}
			return model;
		} catch (Exception e) {
			LOGGER.error(e, e);
			throw e;
		}
		
	}
	
	public IclubNamedQueryDAO getIclubNamedQueryDAO() {
		return iclubNamedQueryDAO;
	}
	
	public void setIclubNamedQueryDAO(IclubNamedQueryDAO iclubNamedQueryDAO) {
		this.iclubNamedQueryDAO = iclubNamedQueryDAO;
	}
	
}
