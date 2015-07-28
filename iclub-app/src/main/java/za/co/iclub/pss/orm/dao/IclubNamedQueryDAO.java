package za.co.iclub.pss.orm.dao;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
import za.co.iclub.pss.orm.bean.IclubPayment;
import za.co.iclub.pss.orm.bean.IclubPerson;
import za.co.iclub.pss.orm.bean.IclubPolicy;

@Transactional
@SuppressWarnings("rawtypes")
public class IclubNamedQueryDAO {
	private static final Logger log = Logger.getLogger(IclubNamedQueryDAO.class);
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected void initDao() {
		// do nothing
	}
	
	public static void main(String[] args) {
		System.out.println();
	}
	
	public List findByUser(String userId, String className) {
		log.debug("finding all " + className + " instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("get" + className + "ByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List findIclubQuotesByUserAndStatusId(String userId, Long statusId) {
		log.debug("finding all IclubQuote instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubQuoteByUserAndStatus");
			queryObject.setString("id", userId);
			queryObject.setLong("statusId", statusId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all IclubQuote by user failed", re);
			throw re;
		}
	}
	
	public List findIclubSupplPersonBySmId(String smId) {
		log.debug("finding all IclubSupplPerson instances by smId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubSupplPersonBySmId");
			queryObject.setString("id", smId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all IclubSupplPerson by smId failed", re);
			throw re;
		}
	}
	
	public List getBySD(String sd, Long id, String className) {
		log.debug("Fetching all " + className + " by Query :: get" + className + "SD");
		try {
			Query query = getCurrentSession().getNamedQuery("get" + className + "BySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error(className, re);
			throw re;
		}
	}
	
	public List getIclubFieldByFieldStatus(String fieldStatus) {
		log.debug("Fetching all IclubBankMaster by Query :: getAllBankNames");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubFieldByFieldStatus");
			query.setString("fieldStatus", fieldStatus);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("BankNames", re);
			throw re;
		}
	}
	
	public Long getIclubGeoLocByLatAndLong(Double gLong, Double gLat) {
		log.debug("Fetching all IclubGeoLoc by Query :: getIclubGeoLocByLatAndLong");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubGeoLocByLatAndLong");
			query.setDouble("gLong", gLong);
			query.setDouble("gLat", gLat);
			Object[] res = (Object[]) query.uniqueResult();
			Long ret = -999l;
			if (res != null && res.length > 0) {
				ret = ((BigInteger) res[0]).longValue();
				
			}
			return ret;
			
		} catch (RuntimeException re) {
			log.error("BankNames", re);
			throw re;
		}
	}
	
	public List getIclubSupplMasterByLatAndLong(Double smLong, Double smLat) {
		log.debug("Fetching all IclubSupplMaster by Query :: getIclubSupplMasterByLatAndLong");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubSupplMasterByLatAndLong");
			query.setDouble("smLong", smLong);
			query.setDouble("smLat", smLat);
			List res = query.list();
			
			return res;
			
		} catch (RuntimeException re) {
			log.error("BankNames", re);
			throw re;
		}
	}
	
	public List getAllBankNames() {
		log.debug("Fetching all IclubBankMaster by Query :: getAllBankNames");
		try {
			Query query = getCurrentSession().createQuery("select distinct e.bmBankName from IclubBankMaster e");
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("BankNames", re);
			throw re;
		}
	}
	
	public List getIclubBankMastersByBankName(String bankName) {
		log.debug("Fetching all IclubBankMaster by Query :: getAllBankNames");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubBankMastersByBankName");
			query.setString("bankName", bankName);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("MbComment", re);
			throw re;
		}
	}
	
	public List findIclubRateTypeByQuoteTypeAndFieldId(Long fieldId, String quoteType) {
		log.debug("Fetching all IclubMbCommnet by Query :: findIclubRateTypeByQuoteTypeAndFieldId");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubRateTypeByQuoteTypeAndFieldId");
			query.setLong("id", fieldId);
			query.setString("quoteType", quoteType);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("MbComment", re);
			throw re;
		}
	}
	
	public List getMbCommentsByMbId(String mbId) {
		log.debug("Fetching all IclubMbCommnet by Query :: getIclubMbCommnetByMbId");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubMbCommnetByMbId");
			query.setString("id", mbId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("MbComment", re);
			throw re;
		}
	}
	
	public List getAccountTypeBySD(String sd, Long id) {
		log.debug("Fetching all Account Type by Query :: getAccountTypeySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getAccountTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Account Type", re);
			throw re;
		}
	}
	
	public List getAlarmTypeBySD(String sd, Long id) {
		log.debug("Fetching all Alarm Type by Query :: getAlarmTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getAlarmTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Alarm Type", re);
			throw re;
		}
	}
	
	public List findBankMasterByUser(String userId) {
		log.debug("finding all IclubBankMaster instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getBankMasterByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List getBarTypeBySD(String sd, Long id) {
		log.debug("Fetching all Bar Type by Query :: getBarTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getBarTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Bar Type", re);
			throw re;
		}
	}
	
	public List getBoundaryTypeBySD(String sd, Long id) {
		log.debug("Fetching all Boundary Type by Query :: getBoundaryTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getBoundaryTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Boundary Type", re);
			throw re;
		}
	}
	
	public List getBuildingStateBySD(String sd, Long id) {
		log.debug("Fetching all Building State by Query :: getBuildingStateBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getBuildingStateBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Building State", re);
			throw re;
		}
	}
	
	public IclubClaim findByPolicyId(String policyId) {
		log.debug("finding all IclubClaim instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getClaimByPolicyId");
			queryObject.setString("policyId", policyId);
			return (IclubClaim) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public IclubConfig getIclubConfigByKey(String key) {
		log.debug("finding IclubConfig instance by config key");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubConfigByKey");
			queryObject.setString("key", key);
			return (IclubConfig) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("get IclubConfig failed", re);
			throw re;
		}
	}
	
	public List getDocumentByEntity(String id, Long typeId) {
		log.debug("Fetching all Batch by Query :: getDocumentByEntity");
		try {
			Query query = getCurrentSession().getNamedQuery("getDocumentByEntity");
			query.setString("id", id);
			query.setLong("typeId", typeId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Document", re);
			throw re;
		}
	}
	
	public List findClaimItemByUser(String userId) {
		log.debug("finding all IclubClaimItem instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getClaimItemByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List getClaimStatusBySD(String sd, Long id) {
		log.debug("Fetching all Claim Status by Query :: getClaimStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getClaimStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Claim Status", re);
			throw re;
		}
	}
	
	public List findConfigByUser(String userId) {
		log.debug("finding all IclubConfig instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getConfigByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List getDocumentTypeBySD(String sd, Long id) {
		log.debug("Fetching all Document Type by Query :: getDocumentTypeySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getDocumentTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Document Type", re);
			throw re;
		}
	}
	
	public IclubDriver findByPersonId(String personId) {
		log.debug("finding all IclubDriver instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getDriverByPersonId");
			queryObject.setString("id", personId);
			return (IclubDriver) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List getEntityTypeBySD(String sd, Long id) {
		log.debug("Fetching all Entity Type by Query :: getEntityTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getEntityTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Event Type", re);
			throw re;
		}
	}
	
	public List getEventTypeBySD(String sd, Long id) {
		log.debug("Fetching all Event Type by Query :: getEventTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getEventTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Event Type", re);
			throw re;
		}
	}
	
	public List getFieldBySD(String sd, Long id) {
		log.debug("Fetching all Field by Query :: getFieldBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getFieldBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Field", re);
			throw re;
		}
	}
	
	public List getIdTypeBySD(String sd, Long id) {
		log.debug("Fetching all Id Type by Query :: getIdTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getIdTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Id Type", re);
			throw re;
		}
	}
	
	public List getPropertyItemByProperty(String propertyId) {
		log.debug("Fetching all Id Type by Query :: getIdTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getIdTypeBySD");
			query.setString("propertyId", propertyId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Property Item", re);
			throw re;
		}
	}
	
	public IclubInsuranceItem findByQuoteIdAndItemTypeId(String quoteId, Long itemTypeId) {
		log.debug("finding all IclubInsuranceItem instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getInsuranceItemByQuoteIdAndItemTypeId");
			queryObject.setString("quoteId", quoteId);
			queryObject.setLong("itemTypeId", itemTypeId);
			List ret = queryObject.list();
			
			return (IclubInsuranceItem) ret.get(0);
			
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List findIclubInsuranceItemsByQuoteIdAndItemTypeId(String quoteId, Long itemTypeId) {
		log.debug("finding all IclubInsuranceItem instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getInsuranceItemByQuoteIdAndItemTypeId");
			queryObject.setString("quoteId", quoteId);
			queryObject.setLong("itemTypeId", itemTypeId);
			List ret = queryObject.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List findByQuoteId(String quoteId) {
		log.debug("finding all IclubInsuranceItem instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getInsuranceItemByQuoteId");
			queryObject.setString("quoteId", quoteId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public IclubPolicy findIclubPlolicyByQuoteId(String quoteId) {
		log.debug("finding IclubPolicy instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getPolicyByQuoteId");
			queryObject.setString("quoteId", quoteId);
			return (IclubPolicy) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by user failed", re);
			throw re;
		}
	}
	
	public List getInsuranceItemTypeBySD(String sd, Long id) {
		log.debug("Fetching all Insurance Item Type by Query :: getInsuranceItemTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getInsuranceItemTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Insurance Item Type", re);
			throw re;
		}
	}
	
	public List verifyLogin(String name, String pwd) {
		log.debug("Fetching Login Query :: verify Login");
		try {
			Query query = getCurrentSession().getNamedQuery("verifyLogin");
			query.setString("name", name);
			query.setString("pwd", pwd);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Login failed", re);
			throw re;
		}
	}
	
	public List getMaritialStatusBySD(String sd, Long id) {
		log.debug("Fetching all Maritial Status by Query :: getMaritialStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getMaritialStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Maritial Status", re);
			throw re;
		}
	}
	
	public List getMessageTypeBySD(String sd, Long id) {
		log.debug("Fetching all Message Type by Query :: getMessageTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getMessageTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Message Type", re);
			throw re;
		}
	}
	
	public List getAccessTypeBySD(String sd, Long id) {
		log.debug("Fetching all Access Type by Query :: getAccessTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getAccessTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Access Type", re);
			throw re;
		}
	}
	
	public List getNotificationTypeBySD(String sd, Long id) {
		log.debug("Fetching all Notification Type by Query :: getNotificationTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getNotificationTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Notification Type", re);
			throw re;
		}
	}
	
	public List getOccupiedStatusBySD(String sd, Long id) {
		log.debug("Fetching all Occupied Status by Query :: getOccupiedStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getOccupiedStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Occupied Status", re);
			throw re;
		}
	}
	
	public List getOwnerTypeBySD(String sd, Long id) {
		log.debug("Fetching all Owner Type by Query :: getOwnerTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getOwnerTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Owner Type", re);
			throw re;
		}
	}
	
	public List getPaymentStatusBySD(String sd, Long id) {
		log.debug("Fetching all Payment Status by Query :: getPaymentStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getPaymentStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Payment Status", re);
			throw re;
		}
	}
	
	public List getPaymentTypeBySD(String sd, Long id) {
		log.debug("Fetching all Payment Type by Query :: getPaymentTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getPaymentTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Payment Type", re);
			throw re;
		}
	}
	
	public IclubPolicy findPolicyByQuoteId(String quoteId) {
		log.debug("finding   IclubPolicy instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getPolicyByQuoteId");
			queryObject.setString("quoteId", quoteId);
			return (IclubPolicy) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find   by user failed", re);
			throw re;
		}
	}
	
	public List getPolicyStatusBySD(String sd, Long id) {
		log.debug("Fetching all Policy Status by Query :: getPolicyStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getPolicyStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Policy Status", re);
			throw re;
		}
	}
	
	public List getProductTypeBySD(String sd, Long id) {
		log.debug("Fetching all Product Type by Query :: getProductTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getProductTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Product Type", re);
			throw re;
		}
	}
	
	public List getPropertyTypeBySD(String sd, Long id) {
		log.debug("Fetching all Property Type by Query :: getPropertyTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getPropertyTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Property Type", re);
			throw re;
		}
	}
	
	public List findAllVmMakes() {
		log.debug("finding all IclubVehicleMaster instances by vmMake");
		try {
			String queryString = "select  distinct model.vmMake from IclubVehicleMaster as model ";
			Query queryObject = getCurrentSession().createQuery(queryString);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by vmMake failed", re);
			throw re;
		}
	}
	
	public List getQuoteStatusBySD(String sd, Long id) {
		log.debug("Fetching all Quote Status by Query :: getQuoteStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getQuoteStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Quote Status", re);
			throw re;
		}
	}
	
	public List findByRateType(String rateType) {
		log.debug("finding all IclubRateEngine instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getRateEngineByRateType");
			queryObject.setString("id", rateType);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	
	public List getRateTypeBySD(String sd, Long id) {
		log.debug("Fetching all Rate Type by Query :: getRateTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getRateTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Rate Type", re);
			throw re;
		}
	}
	
	public List getRoleTypeBySD(String sd, Long id) {
		log.debug("Fetching all Role Type by Query :: getRoleTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getRoleTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Role Type", re);
			throw re;
		}
	}
	
	public List getRoofTypeBySD(String sd, Long id) {
		log.debug("Fetching all Roof Type by Query :: getRoofTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getRoofTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Roof Type", re);
			throw re;
		}
	}
	
	public List getSecurityQuestionBySD(String sd, Long id) {
		log.debug("Fetching all Security Question by Query :: getSecurityQuestionBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getSecurityQuestionBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Security Question", re);
			throw re;
		}
	}
	
	public List getSupplierTypeBySD(String sd, Long id) {
		log.debug("Fetching all Supplier Type by Query :: getSupplierTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getSupplierTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Supplier Type", re);
			throw re;
		}
	}
	
	public List findByDriverId(String driverId) {
		log.debug("finding  IclubVehicle instance by getVehicleByDriverId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getVehicleByDriverId");
			queryObject.setString("driverId", driverId);
			List ret = queryObject.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("find IclubVehicle failed", re);
			throw re;
		}
	}
	
	public List findAllByMake(String vmMake) {
		log.debug("finding all IclubVehicleMaster instances by vmMake");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getVehicleMasterByMake");
			queryObject.setString("vmMake", vmMake);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by vmMake failed", re);
			throw re;
		}
	}
	
	public List getVehicleTypeBySD(String sd, Long id) {
		log.debug("Fetching all VehicleType by Query :: getVehicleTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getVehicleTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("VehicleType", re);
			throw re;
		}
	}
	
	public List getWallTypeBySD(String sd, Long id) {
		log.debug("Fetching all Wall Type by Query :: getWallTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getWallTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Wall Type", re);
			throw re;
		}
	}
	
	public List getIclubLoginByPersonId(String personId) {
		log.debug("finding IclubLogin instance by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubLoginByPersonId");
			queryObject.setString("personId", personId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubLogin failed", re);
			throw re;
		}
	}
	
	public List getIclubRateEngineByBaseValueAndRateTypeId(String baseValue, Long rateTypeId, String reId) {
		log.debug("Fetching all IclubRateEngine by Query :: getIclubRateEngineByBaseValueAndRateTypeId");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubRateEngineByBaseValueAndRateTypeId");
			query.setString("baseValue", baseValue);
			query.setString("reId", reId);
			query.setLong("rateTypeId", rateTypeId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("IclubRateEngine", re);
			throw re;
		}
	}
	
	public List getIclubRateEngineByBaseMaxValueAndRateTypeId(String baseValue, String maxValue, Long rateTypeId, String reId) {
		log.debug("Fetching all IclubRateEngine by Query :: getIclubRateEngineByBaseValueAndRateTypeId");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubRateEngineByBaseMaxValueAndRateTypeId");
			/*
			 * String queryString =
			 * "select e from IclubRateEngine e where e.reId is not null ";
			 * 
			 * if (reId != null && !reId.trim().equalsIgnoreCase("null") &&
			 * !reId.trim().equalsIgnoreCase("")) { queryString +=
			 * " and e.reId <> :reId"; } if (baseValue != null) { queryString +=
			 * " and (( e.reBaseValue <= :baseValue and :baseValue <= e.reMaxValue) or ( e.reBaseValue <= :maxValue and :maxValue <= e.reMaxValue) )"
			 * ; }
			 * 
			 * if (rateTypeId != null) { queryString +=
			 * " and e.iclubRateType.rtId =:rateTypeId"; } Query query =
			 * getCurrentSession().createQuery(queryString);
			 * 
			 * query.setParameter("baseValue", baseValue);
			 * query.setParameter("maxValue", maxValue); if (reId != null &&
			 * !reId.trim().equalsIgnoreCase("null") &&
			 * !reId.trim().equalsIgnoreCase("")) { query.setParameter("reId",
			 * reId); } if (rateTypeId != null) {
			 * query.setParameter("rateTypeId", rateTypeId); }
			 */
			
			query.setDouble("baseValue", new Double(baseValue));
			query.setDouble("maxValue", new Double(maxValue));
			query.setString("reId", reId);
			query.setLong("rateTypeId", rateTypeId);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("IclubRateEngine", re);
			throw re;
		}
	}
	
	public List getIclubPolicyByPolicyStatus(String policyStatus) {
		log.debug("finding IclubPolicy instance by policyStatus");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPolicyByPolicyStatus");
			queryObject.setString("policyStatus", policyStatus);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubPolicy failed", re);
			throw re;
		}
	}
	
	public List getIclubPolicies() {
		log.debug("finding IclubPolicy instance");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPolicies");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubPolicy failed", re);
			throw re;
		}
	}
	
	public List getIclubClaimByCrtDt() {
		log.debug("finding IclubCalim instance");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubClaimByCrtDt");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubPolicy failed", re);
			throw re;
		}
	}
	
	public List getIclubCalimByClaimStatus(String claimStatus) {
		log.debug("finding IclubCalim  instance by CalimStatus");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubCalimByClaimStatus");
			queryObject.setString("claimStatus", claimStatus);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubClaim failed", re);
			throw re;
		}
	}
	
	public List getIclubPersonNumbersList(Collection<? extends String> numbers) {
		log.debug("finding IclubPerson  instances by getIclubPersonNumbersList");
		try {
			Criteria criteria = getCurrentSession().createCriteria(IclubPerson.class);
			criteria.add(Restrictions.in("PMobile", numbers));
			criteria.setProjection(Projections.property("PMobile"));
			List personNames = criteria.list();
			return personNames;
		} catch (RuntimeException re) {
			log.error("get IclubPersonList failed", re);
			throw re;
		}
		
	}
	
	public List getIclubPersonEmailsList(Collection<? extends String> emails) {
		log.debug("finding IclubPerson  instances by getIclubPersonEmailsList");
		try {
			Criteria criteria = getCurrentSession().createCriteria(IclubPerson.class);
			criteria.add(Restrictions.in("PEmail", emails));
			criteria.setProjection(Projections.property("PEmail"));
			List personNames = criteria.list();
			return personNames;
		} catch (RuntimeException re) {
			log.error("get IclubPersonList failed", re);
			throw re;
		}
		
	}
	
	public List getIclubPaymentsByCohortId(String cohortId, String userId) {
		log.debug("finding IclubPayment  instances by getIclubPaymentsByCohortId");
		try {
			Criteria criteria = getCurrentSession().createCriteria(IclubPayment.class);
			// To Restrict the data for two columns
			// ProjectionList p1 = Projections.projectionList();
			// p1.add(Projections.property("PValue"));
			// p1.add(Projections.property("PCrtdDt"));
			// criteria.setProjection(p1);
			criteria.createAlias("iclubPerson", "person");
			criteria.createAlias("person.iclubCohort", "cohort");
			if (cohortId != null && !cohortId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("cohort.CId", cohortId).ignoreCase());
			}
			if (userId != null && !userId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("person.PId", userId).ignoreCase());
			}
			List paymentList = criteria.list();
			return paymentList;
		} catch (RuntimeException re) {
			log.error("get IclubPayment failed", re);
			throw re;
		}
		
	}
	
	public List getIclubPoliciesByCohortId(String cohortId, String userId) {
		log.debug("finding IclubPolicy  instances by getIclubPoliciesByCohortId");
		try {
			Criteria criteria = getCurrentSession().createCriteria(IclubPolicy.class);
			criteria.createAlias("iclubPerson", "person");
			criteria.createAlias("person.iclubCohort", "cohort");
			if (cohortId != null && !cohortId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("cohort.CId", cohortId).ignoreCase());
			}
			if (userId != null && !userId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("person.PId", userId).ignoreCase());
			}
			List paymentList = criteria.list();
			return paymentList;
		} catch (RuntimeException re) {
			log.error("get IclubPolicy failed", re);
			throw re;
		}
		
	}
	
	public List getIclubClaimsByCohortId(String cohortId, String userId) {
		log.debug("finding IclubClaim  instances by getIclubClaimsByCohortId");
		try {
			Criteria criteria = getCurrentSession().createCriteria(IclubClaim.class);
			criteria.createAlias("iclubPerson", "person");
			criteria.createAlias("person.iclubCohort", "cohort");
			if (cohortId != null && !cohortId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("cohort.CId", cohortId).ignoreCase());
			}
			if (userId != null && !userId.trim().equalsIgnoreCase("")) {
				criteria.add(Restrictions.eq("person.PId", userId).ignoreCase());
			}
			List paymentList = criteria.list();
			return paymentList;
		} catch (RuntimeException re) {
			log.error("get IclubClaim failed", re);
			throw re;
		}
		
	}
	
	public List getIclubPropertyItemByProperty(String propertyId) {
		log.debug("finding IclubPropertyItem  instance by ProeprtyItemStatus");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPropertyItemByProperty");
			queryObject.setString("id", propertyId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubPropertyItem failed", re);
			throw re;
		}
	}
	
	public List getIclubLoginByIdAndProviderId(String loginId, String providerId, String providerCd) {
		log.debug("finding IclubLogin instance by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubLoginByIdOrProviderId");
			queryObject.setString("lpId", providerId);
			queryObject.setString("lpCd", providerCd);
			queryObject.setString("lId", loginId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("get IclubLogin failed", re);
			throw re;
		}
	}
	
	public List getQuoteDetailsByUserId(String userId, Long quoteStatus) {
		log.debug("finding IclubQuote instance by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubQuoteCntByUserId");
			
			queryObject.setString("userId", userId);
			queryObject.setLong("quoteStatus", quoteStatus);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubQuote failed", re);
			throw re;
		}
	}
	
	public List getQuoteDetailsIdByUserId(String userId, Long quoteStatus) {
		log.debug("finding IclubQuote IDs instance by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubQuoteIdByUserId");
			
			queryObject.setString("userId", userId);
			queryObject.setLong("quoteStatus", quoteStatus);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubQuote Ids failed", re);
			throw re;
		}
	}
	
	public List getIclubQuoteIdByUserId(String quoteIds) {
		log.debug("finding IclubInsuranceItem fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubItemIdsByUserId");
			
			queryObject.setString("quoteIds", quoteIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubPolicIdsByQuotes(String userIds) {
		log.debug("finding IclubPolicy fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPolicIdsByQuotes");
			
			queryObject.setString("userIds", userIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubClaimValueByPolicyIds(String policyIds) {
		log.debug("finding IclubPolicy fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubClaimValueByPolicyIds");
			
			queryObject.setString("policyIds", policyIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubPaymentByPolicyIds(String policyIds) {
		log.debug("finding IclubPolicy fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPaymentByPolicyIds");
			
			queryObject.setString("policyIds", policyIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubPaymentLeastDbDtByPolicyIds(String policyIds) {
		log.debug("finding IclubPolicy fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPaymentByPolicyIds");
			
			queryObject.setString("policyIds", policyIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubPaymentsByClaimIds(String claimIds) {
		log.debug("finding IclubPolicy fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPaymentsByClaimIds");
			
			queryObject.setString("claimIds", claimIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubVehicleIValueByVIds(String vehicleIds) {
		log.debug("finding IclubInsuranceItem fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubVehicleIValueByVIds");
			
			queryObject.setString("vehicleIds", vehicleIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public List getIclubPropertyIValueByPIds(String propertyIds) {
		log.debug("finding IclubInsuranceItem fields  by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPropertyIValueByPIds");
			
			queryObject.setString("propertyIds", propertyIds);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubInsuranceItem failed", re);
			throw re;
		}
	}
	
	public Object getQuoteDetailsValDateByUserId(String userId, Long quoteStatus) {
		log.debug("finding IclubQuote instance by PersonId");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubQuoteValDtByUserId");
			
			queryObject.setString("userId", userId);
			queryObject.setLong("quoteStatus", quoteStatus);
			Object object = queryObject.uniqueResult();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubQuote failed", re);
			throw re;
		}
	}
	
	public List getIclubCohortInvitesByNotSentStatus() {
		log.debug("finding IclubCohortInvite   by Not  Sent Status");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubCohortInviteByNotSentStatus");
			
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubCohortInvite  ", re);
			throw re;
		}
	}
	
	public List getIclubClaimPaymentsByUserId(String userId, Long claimStausId) {
		log.debug("finding IclubPayment   by Not  Sent Status");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubClaimPaymentsByUserId");
			queryObject.setString("userId", userId);
			queryObject.setLong("claimStausId", claimStausId);
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubPayment  ", re);
			throw re;
		}
	}
	
	public List getIclubPolicyPaymentsByUserId(String userId) {
		log.debug("finding IclubPayment   by Not  Sent Status");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getIclubPolicyPaymentsByUserId");
			queryObject.setString("userId", userId);
			
			List object = queryObject.list();
			return object;
		} catch (RuntimeException re) {
			log.error("get IclubPayment  ", re);
			throw re;
		}
	}
	
}