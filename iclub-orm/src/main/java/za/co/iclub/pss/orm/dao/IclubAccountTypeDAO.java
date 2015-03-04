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

import za.co.iclub.pss.orm.bean.IclubAccountType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubAccountType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubAccountType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubAccountTypeDAO {
	private static final Logger log = Logger.getLogger(IclubAccountTypeDAO.class);
	// property constants
	public static final String AT_SHORT_DESC = "atShortDesc";
	public static final String AT_LONG_DESC = "atLongDesc";
	public static final String AT_STATUS = "atStatus";

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

	public void save(IclubAccountType transientInstance) {
		log.debug("saving IclubAccountType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubAccountType persistentInstance) {
		log.debug("deleting IclubAccountType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubAccountType findById(java.lang.Long id) {
		log.debug("getting IclubAccountType instance with id: " + id);
		try {
			IclubAccountType instance = (IclubAccountType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubAccountType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubAccountType> findByExample(IclubAccountType instance) {
		log.debug("finding IclubAccountType instance by example");
		try {
			List<IclubAccountType> results = (List<IclubAccountType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubAccountType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubAccountType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubAccountType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubAccountType> findByAtShortDesc(Object atShortDesc) {
		return findByProperty(AT_SHORT_DESC, atShortDesc);
	}

	public List<IclubAccountType> findByAtLongDesc(Object atLongDesc) {
		return findByProperty(AT_LONG_DESC, atLongDesc);
	}

	public List<IclubAccountType> findByAtStatus(Object atStatus) {
		return findByProperty(AT_STATUS, atStatus);
	}

	public List findAll() {
		log.debug("finding all IclubAccountType instances");
		try {
			String queryString = "from IclubAccountType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubAccountType merge(IclubAccountType detachedInstance) {
		log.debug("merging IclubAccountType instance");
		try {
			IclubAccountType result = (IclubAccountType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubAccountType instance) {
		log.debug("attaching dirty IclubAccountType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubAccountType instance) {
		log.debug("attaching clean IclubAccountType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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

	public static IclubAccountTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubAccountTypeDAO) ctx.getBean("IclubAccountTypeDAO");
	}
}