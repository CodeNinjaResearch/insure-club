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

import za.co.iclub.pss.orm.bean.IclubMessageType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMessageType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMessageType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubMessageTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubMessageTypeDAO.class);
	// property constants
	public static final String MT_SHORT_DESC = "mtShortDesc";
	public static final String MT_LONG_DESC = "mtLongDesc";
	public static final String MT_STATUS = "mtStatus";

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

	public void save(IclubMessageType transientInstance) {
		log.debug("saving IclubMessageType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMessageType persistentInstance) {
		log.debug("deleting IclubMessageType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMessageType findById(java.lang.Long id) {
		log.debug("getting IclubMessageType instance with id: " + id);
		try {
			IclubMessageType instance = (IclubMessageType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubMessageType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMessageType> findByExample(IclubMessageType instance) {
		log.debug("finding IclubMessageType instance by example");
		try {
			List<IclubMessageType> results = (List<IclubMessageType>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubMessageType")
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
		log.debug("finding IclubMessageType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMessageType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMessageType> findByMtShortDesc(Object mtShortDesc) {
		return findByProperty(MT_SHORT_DESC, mtShortDesc);
	}

	public List<IclubMessageType> findByMtLongDesc(Object mtLongDesc) {
		return findByProperty(MT_LONG_DESC, mtLongDesc);
	}

	public List<IclubMessageType> findByMtStatus(Object mtStatus) {
		return findByProperty(MT_STATUS, mtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubMessageType instances");
		try {
			String queryString = "from IclubMessageType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMessageType merge(IclubMessageType detachedInstance) {
		log.debug("merging IclubMessageType instance");
		try {
			IclubMessageType result = (IclubMessageType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMessageType instance) {
		log.debug("attaching dirty IclubMessageType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMessageType instance) {
		log.debug("attaching clean IclubMessageType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getWallTypeBySD(String sd, Long id) {
		log.debug("Fetching all Batch by Query :: getWallTypeBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getWallTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Entity Cat", re);
			throw re;
		}
	}

	public static IclubMessageTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubMessageTypeDAO) ctx.getBean("IclubMessageTypeDAO");
	}
}