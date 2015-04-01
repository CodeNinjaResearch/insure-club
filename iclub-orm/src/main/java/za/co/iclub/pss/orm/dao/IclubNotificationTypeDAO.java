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

import za.co.iclub.pss.orm.bean.IclubNotificationType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubNotificationType entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubNotificationType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubNotificationTypeDAO {
	private static final Logger log = Logger.getLogger(IclubNotificationTypeDAO.class);
	// property constants
	public static final String NT_SHORT_DESC = "ntShortDesc";
	public static final String NT_LONG_DESC = "ntLongDesc";
	public static final String NT_STATUS = "ntStatus";

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

	public void save(IclubNotificationType transientInstance) {
		log.debug("saving IclubNotificationType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubNotificationType persistentInstance) {
		log.debug("deleting IclubNotificationType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubNotificationType findById(java.lang.Long id) {
		log.debug("getting IclubNotificationType instance with id: " + id);
		try {
			IclubNotificationType instance = (IclubNotificationType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubNotificationType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubNotificationType> findByExample(IclubNotificationType instance) {
		log.debug("finding IclubNotificationType instance by example");
		try {
			List<IclubNotificationType> results = (List<IclubNotificationType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubNotificationType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubNotificationType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubNotificationType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubNotificationType> findByNtShortDesc(Object ntShortDesc) {
		return findByProperty(NT_SHORT_DESC, ntShortDesc);
	}

	public List<IclubNotificationType> findByNtLongDesc(Object ntLongDesc) {
		return findByProperty(NT_LONG_DESC, ntLongDesc);
	}

	public List<IclubNotificationType> findByNtStatus(Object ntStatus) {
		return findByProperty(NT_STATUS, ntStatus);
	}

	public List findAll() {
		log.debug("finding all IclubNotificationType instances");
		try {
			String queryString = "from IclubNotificationType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubNotificationType merge(IclubNotificationType detachedInstance) {
		log.debug("merging IclubNotificationType instance");
		try {
			IclubNotificationType result = (IclubNotificationType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubNotificationType instance) {
		log.debug("attaching dirty IclubNotificationType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubNotificationType instance) {
		log.debug("attaching clean IclubNotificationType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubNotificationTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubNotificationTypeDAO) ctx.getBean("IclubNotificationTypeDAO");
	}
}