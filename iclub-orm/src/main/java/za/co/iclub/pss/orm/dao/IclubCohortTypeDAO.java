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

import za.co.iclub.pss.orm.bean.IclubCohortType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubCohortType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubCohortType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubCohortTypeDAO {
	private static final Logger log = Logger.getLogger(IclubCohortTypeDAO.class);
	// property constants
	public static final String CT_SHORT_DESC = "ctShortDesc";
	public static final String CT_LONG_DESC = "ctLongDesc";
	public static final String CT_STATUS = "ctStatus";

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

	public void save(IclubCohortType transientInstance) {
		log.debug("saving IclubCohortType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubCohortType persistentInstance) {
		log.debug("deleting IclubCohortType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubCohortType findById(java.lang.Long id) {
		log.debug("getting IclubCohortType instance with id: " + id);
		try {
			IclubCohortType instance = (IclubCohortType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubCohortType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubCohortType> findByExample(IclubCohortType instance) {
		log.debug("finding IclubCohortType instance by example");
		try {
			List<IclubCohortType> results = (List<IclubCohortType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubCohortType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubCohortType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubCohortType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubCohortType> findByCtShortDesc(Object ctShortDesc) {
		return findByProperty(CT_SHORT_DESC, ctShortDesc);
	}

	public List<IclubCohortType> findByCtLongDesc(Object ctLongDesc) {
		return findByProperty(CT_LONG_DESC, ctLongDesc);
	}

	public List<IclubCohortType> findByCtStatus(Object ctStatus) {
		return findByProperty(CT_STATUS, ctStatus);
	}

	public List findAll() {
		log.debug("finding all IclubCohortType instances");
		try {
			String queryString = "from IclubCohortType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubCohortType merge(IclubCohortType detachedInstance) {
		log.debug("merging IclubCohortType instance");
		try {
			IclubCohortType result = (IclubCohortType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubCohortType instance) {
		log.debug("attaching dirty IclubCohortType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubCohortType instance) {
		log.debug("attaching clean IclubCohortType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubCohortTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCohortTypeDAO) ctx.getBean("IclubCohortTypeDAO");
	}
}