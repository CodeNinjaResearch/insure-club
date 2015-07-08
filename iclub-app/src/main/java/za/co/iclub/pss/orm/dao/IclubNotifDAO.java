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

import za.co.iclub.pss.orm.bean.IclubNotif;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubNotif entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubNotif
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubNotifDAO {
	private static final Logger log = Logger.getLogger(IclubNotifDAO.class);
	// property constants
	public static final String _NTITLE = "NTitle";
	public static final String _NBODY = "NBody";
	public static final String _NFROM_ADDR = "NFromAddr";
	public static final String _NTO_LIST = "NToList";
	public static final String _NSTATUS = "NStatus";

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

	public void save(IclubNotif transientInstance) {
		log.debug("saving IclubNotif instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubNotif persistentInstance) {
		log.debug("deleting IclubNotif instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubNotif findById(java.lang.String id) {
		log.debug("getting IclubNotif instance with id: " + id);
		try {
			IclubNotif instance = (IclubNotif) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubNotif", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubNotif> findByExample(IclubNotif instance) {
		log.debug("finding IclubNotif instance by example");
		try {
			List<IclubNotif> results = (List<IclubNotif>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubNotif").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubNotif instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubNotif as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubNotif> findByNTitle(Object NTitle) {
		return findByProperty(_NTITLE, NTitle);
	}

	public List<IclubNotif> findByNBody(Object NBody) {
		return findByProperty(_NBODY, NBody);
	}

	public List<IclubNotif> findByNFromAddr(Object NFromAddr) {
		return findByProperty(_NFROM_ADDR, NFromAddr);
	}

	public List<IclubNotif> findByNToList(Object NToList) {
		return findByProperty(_NTO_LIST, NToList);
	}

	public List<IclubNotif> findByNStatus(Object NStatus) {
		return findByProperty(_NSTATUS, NStatus);
	}

	public List findAll() {
		log.debug("finding all IclubNotif instances");
		try {
			String queryString = "from IclubNotif";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubNotif merge(IclubNotif detachedInstance) {
		log.debug("merging IclubNotif instance");
		try {
			IclubNotif result = (IclubNotif) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubNotif instance) {
		log.debug("attaching dirty IclubNotif instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubNotif instance) {
		log.debug("attaching clean IclubNotif instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubNotifDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubNotifDAO) ctx.getBean("IclubNotifDAO");
	}
}