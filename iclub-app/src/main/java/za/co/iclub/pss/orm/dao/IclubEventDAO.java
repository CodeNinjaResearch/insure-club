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

import za.co.iclub.pss.orm.bean.IclubEvent;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubEvent entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubEvent
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({"unchecked","rawtypes"})
public class IclubEventDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IclubEventDAO.class);
	// property constants
	public static final String _EDESC = "EDesc";

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

	public void save(IclubEvent transientInstance) {
		log.debug("saving IclubEvent instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubEvent persistentInstance) {
		log.debug("deleting IclubEvent instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubEvent findById(java.lang.String id) {
		log.debug("getting IclubEvent instance with id: " + id);
		try {
			IclubEvent instance = (IclubEvent) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubEvent", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubEvent> findByExample(IclubEvent instance) {
		log.debug("finding IclubEvent instance by example");
		try {
			List<IclubEvent> results = (List<IclubEvent>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubEvent")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubEvent instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from IclubEvent as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubEvent> findByEDesc(Object EDesc) {
		return findByProperty(_EDESC, EDesc);
	}

	public List findAll() {
		log.debug("finding all IclubEvent instances");
		try {
			String queryString = "from IclubEvent";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubEvent merge(IclubEvent detachedInstance) {
		log.debug("merging IclubEvent instance");
		try {
			IclubEvent result = (IclubEvent) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubEvent instance) {
		log.debug("attaching dirty IclubEvent instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubEvent instance) {
		log.debug("attaching clean IclubEvent instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubEventDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubEventDAO) ctx.getBean("IclubEventDAO");
	}
}