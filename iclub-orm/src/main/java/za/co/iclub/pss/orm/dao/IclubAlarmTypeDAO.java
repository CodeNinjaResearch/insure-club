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

import za.co.iclub.pss.orm.bean.IclubAlarmType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubAlarmType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubAlarmType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubAlarmTypeDAO {
	private static final Logger log = Logger.getLogger(IclubAlarmTypeDAO.class);
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

	public void save(IclubAlarmType transientInstance) {
		log.debug("saving IclubAlarmType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubAlarmType persistentInstance) {
		log.debug("deleting IclubAlarmType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubAlarmType findById(java.lang.Long id) {
		log.debug("getting IclubAlarmType instance with id: " + id);
		try {
			IclubAlarmType instance = (IclubAlarmType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubAlarmType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubAlarmType> findByExample(IclubAlarmType instance) {
		log.debug("finding IclubAlarmType instance by example");
		try {
			List<IclubAlarmType> results = (List<IclubAlarmType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubAlarmType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubAlarmType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubAlarmType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubAlarmType> findByAtShortDesc(Object atShortDesc) {
		return findByProperty(AT_SHORT_DESC, atShortDesc);
	}

	public List<IclubAlarmType> findByAtLongDesc(Object atLongDesc) {
		return findByProperty(AT_LONG_DESC, atLongDesc);
	}

	public List<IclubAlarmType> findByAtStatus(Object atStatus) {
		return findByProperty(AT_STATUS, atStatus);
	}

	public List findAll() {
		log.debug("finding all IclubAlarmType instances");
		try {
			String queryString = "from IclubAlarmType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubAlarmType merge(IclubAlarmType detachedInstance) {
		log.debug("merging IclubAlarmType instance");
		try {
			IclubAlarmType result = (IclubAlarmType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubAlarmType instance) {
		log.debug("attaching dirty IclubAlarmType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubAlarmType instance) {
		log.debug("attaching clean IclubAlarmType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubAlarmTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubAlarmTypeDAO) ctx.getBean("IclubAlarmTypeDAO");
	}

	public List getAlarmTypeBySD(String sd, Long id) {
		log.debug("Fetching all Alarm Type by Query :: getAlarmTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getAlarmTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Alarm Type", re);
			throw re;
		}
	}
}