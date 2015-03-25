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

import za.co.iclub.pss.orm.bean.IclubRoleType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubRoleType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubRoleType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubRoleTypeDAO {
	private static final Logger log = Logger.getLogger(IclubRoleTypeDAO.class);
	// property constants
	public static final String RT_SHORT_DESC = "rtShortDesc";
	public static final String RT_LONG_DESC = "rtLongDesc";
	public static final String RT_STATUS = "rtStatus";

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

	public void save(IclubRoleType transientInstance) {
		log.debug("saving IclubRoleType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubRoleType persistentInstance) {
		log.debug("deleting IclubRoleType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubRoleType findById(java.lang.Long id) {
		log.debug("getting IclubRoleType instance with id: " + id);
		try {
			IclubRoleType instance = (IclubRoleType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubRoleType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubRoleType> findByExample(IclubRoleType instance) {
		log.debug("finding IclubRoleType instance by example");
		try {
			List<IclubRoleType> results = (List<IclubRoleType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubRoleType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubRoleType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubRoleType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubRoleType> findByRtShortDesc(Object rtShortDesc) {
		return findByProperty(RT_SHORT_DESC, rtShortDesc);
	}

	public List<IclubRoleType> findByRtLongDesc(Object rtLongDesc) {
		return findByProperty(RT_LONG_DESC, rtLongDesc);
	}

	public List<IclubRoleType> findByRtStatus(Object rtStatus) {
		return findByProperty(RT_STATUS, rtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubRoleType instances");
		try {
			String queryString = "from IclubRoleType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubRoleType merge(IclubRoleType detachedInstance) {
		log.debug("merging IclubRoleType instance");
		try {
			IclubRoleType result = (IclubRoleType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubRoleType instance) {
		log.debug("attaching dirty IclubRoleType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubRoleType instance) {
		log.debug("attaching clean IclubRoleType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
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

	public static IclubRoleTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubRoleTypeDAO) ctx.getBean("IclubRoleTypeDAO");
	}
}