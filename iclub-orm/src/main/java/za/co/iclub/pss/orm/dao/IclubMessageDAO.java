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

import za.co.iclub.pss.orm.bean.IclubMessage;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMessage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMessage
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubMessageDAO {
	private static final Logger log = Logger
			.getLogger(IclubMessageDAO.class);
	// property constants
	public static final String _MTRAN_ID = "MTranId";
	public static final String _MCONTENT = "MContent";

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

	public void save(IclubMessage transientInstance) {
		log.debug("saving IclubMessage instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMessage persistentInstance) {
		log.debug("deleting IclubMessage instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMessage findById(java.lang.String id) {
		log.debug("getting IclubMessage instance with id: " + id);
		try {
			IclubMessage instance = (IclubMessage) getCurrentSession().get(
					"za.co.iclub.pss.orm.bean.IclubMessage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMessage> findByExample(IclubMessage instance) {
		log.debug("finding IclubMessage instance by example");
		try {
			List<IclubMessage> results = (List<IclubMessage>) getCurrentSession()
					.createCriteria("za.co.iclub.pss.orm.bean.IclubMessage")
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
		log.debug("finding IclubMessage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMessage as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMessage> findByMTranId(Object MTranId) {
		return findByProperty(_MTRAN_ID, MTranId);
	}

	public List<IclubMessage> findByMContent(Object MContent) {
		return findByProperty(_MCONTENT, MContent);
	}

	public List findAll() {
		log.debug("finding all IclubMessage instances");
		try {
			String queryString = "from IclubMessage";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMessage merge(IclubMessage detachedInstance) {
		log.debug("merging IclubMessage instance");
		try {
			IclubMessage result = (IclubMessage) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMessage instance) {
		log.debug("attaching dirty IclubMessage instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMessage instance) {
		log.debug("attaching clean IclubMessage instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubMessageDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubMessageDAO) ctx.getBean("IclubMessageDAO");
	}
}