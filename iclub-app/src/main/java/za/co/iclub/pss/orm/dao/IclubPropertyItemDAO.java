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

import za.co.iclub.pss.orm.bean.IclubPropertyItem;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubPropertyItem entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubPropertyItem
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubPropertyItemDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubPropertyItemDAO.class);
	// property constants
	public static final String PI_DESCRIPTON = "piDescripton";
	public static final String PI_VALUE = "piValue";
	
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
	
	public void save(IclubPropertyItem transientInstance) {
		log.debug("saving IclubPropertyItem instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(IclubPropertyItem persistentInstance) {
		log.debug("deleting IclubPropertyItem instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public IclubPropertyItem findById(java.lang.String id) {
		log.debug("getting IclubPropertyItem instance with id: " + id);
		try {
			IclubPropertyItem instance = (IclubPropertyItem) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubPropertyItem", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<IclubPropertyItem> findByExample(IclubPropertyItem instance) {
		log.debug("finding IclubPropertyItem instance by example");
		try {
			List<IclubPropertyItem> results = (List<IclubPropertyItem>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubPropertyItem").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubPropertyItem instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubPropertyItem as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<IclubPropertyItem> findByPiDescripton(Object piDescripton) {
		return findByProperty(PI_DESCRIPTON, piDescripton);
	}
	
	public List<IclubPropertyItem> findByPiValue(Object piValue) {
		return findByProperty(PI_VALUE, piValue);
	}
	
	public List findAll() {
		log.debug("finding all IclubPropertyItem instances");
		try {
			String queryString = "from IclubPropertyItem";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public IclubPropertyItem merge(IclubPropertyItem detachedInstance) {
		log.debug("merging IclubPropertyItem instance");
		try {
			IclubPropertyItem result = (IclubPropertyItem) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(IclubPropertyItem instance) {
		log.debug("attaching dirty IclubPropertyItem instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(IclubPropertyItem instance) {
		log.debug("attaching clean IclubPropertyItem instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static IclubPropertyItemDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubPropertyItemDAO) ctx.getBean("IclubPropertyItemDAO");
	}
}