package za.co.iclub.pss.orm.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubOccupiedStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubOccupiedStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubOccupiedStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubOccupiedStatusDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubOccupiedStatusDAO.class);
	// property constants
	public static final String OS_SHORT_DESC = "osShortDesc";
	public static final String OS_LONG_DESC = "osLongDesc";
	public static final String OS_STATUS = "osStatus";
	
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
	
	public void save(IclubOccupiedStatus transientInstance) {
		log.debug("saving IclubOccupiedStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(IclubOccupiedStatus persistentInstance) {
		log.debug("deleting IclubOccupiedStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public IclubOccupiedStatus findById(java.lang.Long id) {
		log.debug("getting IclubOccupiedStatus instance with id: " + id);
		try {
			IclubOccupiedStatus instance = (IclubOccupiedStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubOccupiedStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<IclubOccupiedStatus> findByExample(IclubOccupiedStatus instance) {
		log.debug("finding IclubOccupiedStatus instance by example");
		try {
			List<IclubOccupiedStatus> results = (List<IclubOccupiedStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubOccupiedStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubOccupiedStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubOccupiedStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<IclubOccupiedStatus> findByOsShortDesc(Object osShortDesc) {
		return findByProperty(OS_SHORT_DESC, osShortDesc);
	}
	
	public List<IclubOccupiedStatus> findByOsLongDesc(Object osLongDesc) {
		return findByProperty(OS_LONG_DESC, osLongDesc);
	}
	
	public List<IclubOccupiedStatus> findByOsStatus(Object osStatus) {
		return findByProperty(OS_STATUS, osStatus);
	}
	
	public List findAll() {
		log.debug("finding all IclubOccupiedStatus instances");
		try {
			String queryString = "from IclubOccupiedStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public IclubOccupiedStatus merge(IclubOccupiedStatus detachedInstance) {
		log.debug("merging IclubOccupiedStatus instance");
		try {
			IclubOccupiedStatus result = (IclubOccupiedStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(IclubOccupiedStatus instance) {
		log.debug("attaching dirty IclubOccupiedStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(IclubOccupiedStatus instance) {
		log.debug("attaching clean IclubOccupiedStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static IclubOccupiedStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubOccupiedStatusDAO) ctx.getBean("IclubOccupiedStatusDAO");
	}
}