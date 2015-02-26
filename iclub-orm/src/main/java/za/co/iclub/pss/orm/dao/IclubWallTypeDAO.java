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

import za.co.iclub.pss.orm.bean.IclubWallType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubWallType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubWallType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubWallTypeDAO {
	private static final Logger log = Logger.getLogger(IclubWallTypeDAO.class);
	// property constants
	public static final String WT_SHORT_DESC = "wtShortDesc";
	public static final String WT_LONG_DESC = "wtLongDesc";
	public static final String WT_STATUS = "wtStatus";

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

	public void save(IclubWallType transientInstance) {
		log.debug("saving IclubWallType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubWallType persistentInstance) {
		log.debug("deleting IclubWallType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubWallType findById(java.lang.Long id) {
		log.debug("getting IclubWallType instance with id: " + id);
		try {
			IclubWallType instance = (IclubWallType) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubWallType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubWallType> findByExample(IclubWallType instance) {
		log.debug("finding IclubWallType instance by example");
		try {
			List<IclubWallType> results = (List<IclubWallType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubWallType")
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
		log.debug("finding IclubWallType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubWallType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubWallType> findByWtShortDesc(Object wtShortDesc) {
		return findByProperty(WT_SHORT_DESC, wtShortDesc);
	}

	public List<IclubWallType> findByWtLongDesc(Object wtLongDesc) {
		return findByProperty(WT_LONG_DESC, wtLongDesc);
	}

	public List<IclubWallType> findByWtStatus(Object wtStatus) {
		return findByProperty(WT_STATUS, wtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubWallType instances");
		try {
			String queryString = "from IclubWallType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubWallType merge(IclubWallType detachedInstance) {
		log.debug("merging IclubWallType instance");
		try {
			IclubWallType result = (IclubWallType) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubWallType instance) {
		log.debug("attaching dirty IclubWallType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubWallType instance) {
		log.debug("attaching clean IclubWallType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubWallTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubWallTypeDAO) ctx.getBean("IclubWallTypeDAO");
	}
}