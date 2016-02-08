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

import za.co.iclub.pss.orm.bean.IclubCohortActivity;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohortActivity entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohortActivity
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortActivityDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubCohortActivityDAO.class);
	// property constants

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

	public void save(IclubCohortActivity transientInstance) {
		log.debug("saving IclubCohortActivity instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohortActivity persistentInstance) {
		log.debug("deleting IclubCohortActivity instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohortActivity findById(java.lang.String id) {
		log.debug("getting IclubCohortActivity instance with id: " + id);
		try {
			IclubCohortActivity instance = (IclubCohortActivity) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohortActivity", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohortActivity> findByExample(IclubCohortActivity instance) {
		log.debug("finding IclubCohortActivity instance by example");
		try {
			List<IclubCohortActivity> results = (List<IclubCohortActivity>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohortActivity").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohortActivity instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohortActivity as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all IclubCohortActivity instances");
		try {
			String queryString = "from IclubCohortActivity";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohortActivity merge(IclubCohortActivity detachedInstance) {
		log.debug("merging IclubCohortActivity instance");
		try {
			IclubCohortActivity result = (IclubCohortActivity) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohortActivity instance) {
		log.debug("attaching dirty IclubCohortActivity instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohortActivity instance) {
		log.debug("attaching clean IclubCohortActivity instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortActivityDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortActivityDAO) ctx.getBean("IclubCohortActivityDAO");
	}
}