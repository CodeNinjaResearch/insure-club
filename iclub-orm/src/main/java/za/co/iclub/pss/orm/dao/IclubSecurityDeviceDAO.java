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

import za.co.iclub.pss.orm.bean.IclubSecurityDevice;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSecurityDevice entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSecurityDevice
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSecurityDeviceDAO {
	private static final Logger log = Logger.getLogger(IclubSecurityDeviceDAO.class);
	// property constants
	public static final String SD_ITEM_ID = "sdItemId";
	public static final String SD_SER_NUM = "sdSerNum";
	public static final String SD_CONTRACT_NUM = "sdContractNum";

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

	public void save(IclubSecurityDevice transientInstance) {
		log.debug("saving IclubSecurityDevice instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSecurityDevice persistentInstance) {
		log.debug("deleting IclubSecurityDevice instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSecurityDevice findById(java.lang.String id) {
		log.debug("getting IclubSecurityDevice instance with id: " + id);
		try {
			IclubSecurityDevice instance = (IclubSecurityDevice) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubSecurityDevice", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSecurityDevice> findByExample(IclubSecurityDevice instance) {
		log.debug("finding IclubSecurityDevice instance by example");
		try {
			List<IclubSecurityDevice> results = (List<IclubSecurityDevice>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubSecurityDevice").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubSecurityDevice instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSecurityDevice as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSecurityDevice> findBySdItemId(Object sdItemId) {
		return findByProperty(SD_ITEM_ID, sdItemId);
	}

	public List<IclubSecurityDevice> findBySdSerNum(Object sdSerNum) {
		return findByProperty(SD_SER_NUM, sdSerNum);
	}

	public List<IclubSecurityDevice> findBySdContractNum(Object sdContractNum) {
		return findByProperty(SD_CONTRACT_NUM, sdContractNum);
	}

	public List findAll() {
		log.debug("finding all IclubSecurityDevice instances");
		try {
			String queryString = "from IclubSecurityDevice";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSecurityDevice merge(IclubSecurityDevice detachedInstance) {
		log.debug("merging IclubSecurityDevice instance");
		try {
			IclubSecurityDevice result = (IclubSecurityDevice) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSecurityDevice instance) {
		log.debug("attaching dirty IclubSecurityDevice instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSecurityDevice instance) {
		log.debug("attaching clean IclubSecurityDevice instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List findByUser(String userId) {
		log.debug("finding all IclubSecurityDevice instances by user");
		try {
			Query queryObject = getCurrentSession().getNamedQuery("getSecurityDeviceByUser");
			queryObject.setString("id", userId);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all by user failed", re);
			throw re;
		}
	}
	

	public static IclubSecurityDeviceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubSecurityDeviceDAO) ctx.getBean("IclubSecurityDeviceDAO");
	}
}