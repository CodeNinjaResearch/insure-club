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

import za.co.iclub.pss.orm.bean.IclubEntityType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubEntityType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubEntityType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubEntityTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubEntityTypeDAO.class);
	// property constants
	public static final String ET_SHORT_DESC = "etShortDesc";
	public static final String ET_LONG_DESC = "etLongDesc";
	public static final String ET_STATUS = "etStatus";

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

	public void save(IclubEntityType transientInstance) {
		log.debug("saving IclubEntityType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubEntityType persistentInstance) {
		log.debug("deleting IclubEntityType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubEntityType findById(java.lang.Long id) {
		log.debug("getting IclubEntityType instance with id: " + id);
		try {
			IclubEntityType instance = (IclubEntityType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubEntityType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubEntityType> findByExample(IclubEntityType instance) {
		log.debug("finding IclubEntityType instance by example");
		try {
			List<IclubEntityType> results = (List<IclubEntityType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubEntityType")
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
		log.debug("finding IclubEntityType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubEntityType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubEntityType> findByEtShortDesc(Object etShortDesc) {
		return findByProperty(ET_SHORT_DESC, etShortDesc);
	}

	public List<IclubEntityType> findByEtLongDesc(Object etLongDesc) {
		return findByProperty(ET_LONG_DESC, etLongDesc);
	}

	public List<IclubEntityType> findByEtStatus(Object etStatus) {
		return findByProperty(ET_STATUS, etStatus);
	}

	public List findAll() {
		log.debug("finding all IclubEntityType instances");
		try {
			String queryString = "from IclubEntityType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubEntityType merge(IclubEntityType detachedInstance) {
		log.debug("merging IclubEntityType instance");
		try {
			IclubEntityType result = (IclubEntityType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubEntityType instance) {
		log.debug("attaching dirty IclubEntityType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubEntityType instance) {
		log.debug("attaching clean IclubEntityType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubEntityTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubEntityTypeDAO) ctx.getBean("IclubEntityTypeDAO");
	}
}