package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubVehicleMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubVehicleMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubVehicleMaster
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubVehicleMasterDAO {
	private static final Logger log = Logger.getLogger(IclubVehicleMasterDAO.class);
	// property constants
	public static final String VM_MAKE = "vmMake";
	public static final String VM_MODEL = "vmModel";
	public static final String VM_ORIG_RATE = "vmOrigRate";
	public static final String VM_MRKT_RATE = "vmMrktRate";
	public static final String VM_RET_RATE = "vmRetRate";

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

	public void save(IclubVehicleMaster transientInstance) {
		log.debug("saving IclubVehicleMaster instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubVehicleMaster persistentInstance) {
		log.debug("deleting IclubVehicleMaster instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubVehicleMaster findById(java.lang.Long id) {
		log.debug("getting IclubVehicleMaster instance with id: " + id);
		try {
			IclubVehicleMaster instance = (IclubVehicleMaster) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubVehicleMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubVehicleMaster> findByExample(IclubVehicleMaster instance) {
		log.debug("finding IclubVehicleMaster instance by example");
		try {
			List<IclubVehicleMaster> results = (List<IclubVehicleMaster>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubVehicleMaster").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubVehicleMaster instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubVehicleMaster as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubVehicleMaster> findByVmMake(Object vmMake) {
		return findByProperty(VM_MAKE, vmMake);
	}

	public List<IclubVehicleMaster> findByVmModel(Object vmModel) {
		return findByProperty(VM_MODEL, vmModel);
	}

	public List<IclubVehicleMaster> findByVmOrigRate(Object vmOrigRate) {
		return findByProperty(VM_ORIG_RATE, vmOrigRate);
	}

	public List<IclubVehicleMaster> findByVmMrktRate(Object vmMrktRate) {
		return findByProperty(VM_MRKT_RATE, vmMrktRate);
	}

	public List<IclubVehicleMaster> findByVmRetRate(Object vmRetRate) {
		return findByProperty(VM_RET_RATE, vmRetRate);
	}

	public List findAll() {
		log.debug("finding all IclubVehicleMaster instances");
		try {
			String queryString = "from IclubVehicleMaster";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubVehicleMaster merge(IclubVehicleMaster detachedInstance) {
		log.debug("merging IclubVehicleMaster instance");
		try {
			IclubVehicleMaster result = (IclubVehicleMaster) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubVehicleMaster instance) {
		log.debug("attaching dirty IclubVehicleMaster instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubVehicleMaster instance) {
		log.debug("attaching clean IclubVehicleMaster instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public List findByUser(String userId) {
		log.debug("finding all IclubVehicleMaster instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getVehicleMasterByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}

	public static IclubVehicleMasterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubVehicleMasterDAO) ctx.getBean("IclubVehicleMasterDAO");
	}
}