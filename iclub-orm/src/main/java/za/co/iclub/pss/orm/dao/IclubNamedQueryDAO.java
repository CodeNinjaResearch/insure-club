package za.co.iclub.pss.orm.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubClaim;
import za.co.iclub.pss.orm.bean.IclubConfig;
import za.co.iclub.pss.orm.bean.IclubDriver;
import za.co.iclub.pss.orm.bean.IclubGeoLoc;
import za.co.iclub.pss.orm.bean.IclubInsuranceItem;
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

	public IclubGeoLoc getIclubGeoLocByLatAndLong(Double geoLong, Double lat) {
		log.debug("Fetching all IclubGeoLoc by Query :: getAllBankNames");
		try {
			Query query = getCurrentSession().getNamedQuery("getIclubGeoLocByLatAndLong");
			query.setDouble("geoLong", geoLong);
			query.setDouble("lat", lat);
			Object[] ret = (Object[]) query.uniqueResult();

			if (ret != null && ret.length > 0) {
				IclubGeoLoc bean = new IclubGeoLoc();
				bean.setGlId(ret[0] != null ? (Long) ret[0] : null);
				bean.setIclubPerson(ret[1] != null ? (IclubPerson) ret[1] : null);
				bean.setGlKey(ret[0] != null ? (String) ret[2] : null);
				bean.setGlAddress(ret[0] != null ? (String) ret[3] : null);
				bean.setGlLat(ret[0] != null ? (Double) ret[4] : null);
				bean.setGlLong(ret[0] != null ? (Double) ret[5] : null);
				bean.setGlRate(ret[0] != null ? (Double) ret[6] : null);
				bean.setGlCrtdDt(ret[0] != null ? (Timestamp) ret[7] : null);

			}

			return new IclubGeoLoc();
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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubClaim instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getClaimByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubCountryCode instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getCountryCodeByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubCoverType instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getCoverTypeByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubDocument instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getDocumentByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubDriver instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getDriverByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubEvent instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getEventByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubExtras instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getExtrasByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubGeoLoc instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getGeoLocByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubInsuranceItem instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getInsuranceItemByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubInsurerMaster instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getInsurerMasterByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubLicenseCode instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getLicenseCodeByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubMbComment instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getMbCommentByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubMessageBoard instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getMessageBoardByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubMessage instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getMessageByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubNotif instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getNotifByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubOccupation instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getOccupationByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubPayment instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getPaymentByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubPerson instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getPersonByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubPolicy instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getPolicyByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }
	//
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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubProperty instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getPropertyByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubPurposeType instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getPurposeTypeByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	public List findByInsuranceItemType(String insurnceItemType) {
		log.debug("finding all IclubPurposeType instances by insurnceItemType");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getPurposeTypeByInsurnceItemType");
			queryObject.setString("iIType", insurnceItemType);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	// public List findByUser(String userId) {
	// log.debug("finding all IclubQuote instances by user");
	// try {
	// Query queryObject = getCurrentSession().getNamedQuery("getQuoteByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubRateEngine instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getRateEngineByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubSecurityDevice instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getSecurityDeviceByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubSecurityMaster instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getSecurityMasterByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubSupplMaster instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getSupplMasterByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	// public List findByUser(String userId) {
	// log.debug("finding all IclubSystemType instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getSystemTypeByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

	public List getThatchTypeBySD(String sd, Long id) {
		log.debug("Fetching all Thatch Type by Query :: getThatchTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getThatchTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Thatch Type", re);
			throw re;
		}
	}

	// public List findByUser(String userId) {
	// log.debug("finding all IclubTrackerMaster instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getTrackerMasterByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }
	//
	// public List findByUser(String userId) {
	// log.debug("finding all IclubVehicle instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getVehicleByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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

	// public List findByUser(String userId) {
	// log.debug("finding all IclubVehicleMaster instances by user");
	// try {
	// Query queryObject =
	// getCurrentSession().getNamedQuery("getVehicleMasterByUser");
	// queryObject.setString("id", userId);
	// return queryObject.list();
	// } catch (RuntimeException re) {
	// log.error("find all by user failed", re);
	// throw re;
	// }
	// }

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
}
