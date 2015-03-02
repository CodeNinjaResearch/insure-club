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

import za.co.iclub.pss.orm.bean.IclubThatchType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubThatchType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubThatchType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubThatchTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubThatchTypeDAO.class);
	// property constants
	public static final String TT_SHORT_DESC = "ttShortDesc";
	public static final String TT_LONG_DESC = "ttLongDesc";
	public static final String TT_STATUS = "ttStatus";

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

	public void save(IclubThatchType transientInstance) {
		log.debug("saving IclubThatchType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubThatchType persistentInstance) {
		log.debug("deleting IclubThatchType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubThatchType findById(java.lang.Long id) {
		log.debug("getting IclubThatchType instance with id: " + id);
		try {
			IclubThatchType instance = (IclubThatchType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubThatchType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubThatchType> findByExample(IclubThatchType instance) {
		log.debug("finding IclubThatchType instance by example");
		try {
			List<IclubThatchType> results = (List<IclubThatchType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubThatchType")
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
		log.debug("finding IclubThatchType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubThatchType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubThatchType> findByTtShortDesc(Object ttShortDesc) {
		return findByProperty(TT_SHORT_DESC, ttShortDesc);
	}

	public List<IclubThatchType> findByTtLongDesc(Object ttLongDesc) {
		return findByProperty(TT_LONG_DESC, ttLongDesc);
	}

	public List<IclubThatchType> findByTtStatus(Object ttStatus) {
		return findByProperty(TT_STATUS, ttStatus);
	}

	public List findAll() {
		log.debug("finding all IclubThatchType instances");
		try {
			String queryString = "from IclubThatchType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubThatchType merge(IclubThatchType detachedInstance) {
		log.debug("merging IclubThatchType instance");
		try {
			IclubThatchType result = (IclubThatchType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubThatchType instance) {
		log.debug("attaching dirty IclubThatchType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubThatchType instance) {
		log.debug("attaching clean IclubThatchType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getThatchTypeBySD(String sd, Long id) {
		log.debug("Fetching all Thatch Type by Query :: getThatchTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getThatchTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Thatch Type", re);
			throw re;
		}
	}

	public static IclubThatchTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubThatchTypeDAO) ctx.getBean("IclubThatchTypeDAO");
	}
}