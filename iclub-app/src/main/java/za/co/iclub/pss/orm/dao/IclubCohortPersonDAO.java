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

import za.co.iclub.pss.orm.bean.IclubCohortPerson;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohortPerson entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohortPerson
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortPersonDAO {
	private static final Logger log = Logger.getLogger(IclubCohortPersonDAO.class);
	// property constants
	public static final String CP_CONTRIB = "cpContrib";

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

	public void save(IclubCohortPerson transientInstance) {
		log.debug("saving IclubCohortPerson instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohortPerson persistentInstance) {
		log.debug("deleting IclubCohortPerson instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohortPerson findById(java.lang.String id) {
		log.debug("getting IclubCohortPerson instance with id: " + id);
		try {
			IclubCohortPerson instance = (IclubCohortPerson) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohortPerson", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohortPerson> findByExample(IclubCohortPerson instance) {
		log.debug("finding IclubCohortPerson instance by example");
		try {
			List<IclubCohortPerson> results = (List<IclubCohortPerson>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohortPerson").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohortPerson instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohortPerson as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCohortPerson> findByCpContrib(Object cpContrib) {
		return findByProperty(CP_CONTRIB, cpContrib);
	}

	public List findAll() {
		log.debug("finding all IclubCohortPerson instances");
		try {
			String queryString = "from IclubCohortPerson";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohortPerson merge(IclubCohortPerson detachedInstance) {
		log.debug("merging IclubCohortPerson instance");
		try {
			IclubCohortPerson result = (IclubCohortPerson) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohortPerson instance) {
		log.debug("attaching dirty IclubCohortPerson instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohortPerson instance) {
		log.debug("attaching clean IclubCohortPerson instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortPersonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortPersonDAO) ctx.getBean("IclubCohortPersonDAO");
	}
}