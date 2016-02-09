package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubMaritalStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMaritalStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMaritalStatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubMaritalStatusDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubMaritalStatusDAO.class);
	// property constants
	public static final String MS_SHORT_DESC = "msShortDesc";
	public static final String MS_LONG_DESC = "msLongDesc";
	public static final String MS_STATUS = "msStatus";

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

	public void save(IclubMaritalStatus transientInstance) {
		log.debug("saving IclubMaritalStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMaritalStatus persistentInstance) {
		log.debug("deleting IclubMaritalStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMaritalStatus findById(java.lang.Long id) {
		log.debug("getting IclubMaritalStatus instance with id: " + id);
		try {
			IclubMaritalStatus instance = (IclubMaritalStatus) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubMaritalStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMaritalStatus> findByExample(IclubMaritalStatus instance) {
		log.debug("finding IclubMaritalStatus instance by example");
		try {
			List<IclubMaritalStatus> results = (List<IclubMaritalStatus>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubMaritalStatus").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubMaritalStatus instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMaritalStatus as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMaritalStatus> findByMsShortDesc(Object msShortDesc) {
		return findByProperty(MS_SHORT_DESC, msShortDesc);
	}

	public List<IclubMaritalStatus> findByMsLongDesc(Object msLongDesc) {
		return findByProperty(MS_LONG_DESC, msLongDesc);
	}

	public List<IclubMaritalStatus> findByMsStatus(Object msStatus) {
		return findByProperty(MS_STATUS, msStatus);
	}

	public List findAll() {
		log.debug("finding all IclubMaritalStatus instances");
		try {
			String queryString = "from IclubMaritalStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMaritalStatus merge(IclubMaritalStatus detachedInstance) {
		log.debug("merging IclubMaritalStatus instance");
		try {
			IclubMaritalStatus result = (IclubMaritalStatus) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMaritalStatus instance) {
		log.debug("attaching dirty IclubMaritalStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMaritalStatus instance) {
		log.debug("attaching clean IclubMaritalStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubMaritalStatusDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubMaritalStatusDAO) ctx.getBean("IclubMaritalStatusDAO");
	}
}