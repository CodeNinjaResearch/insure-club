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

import za.co.iclub.pss.orm.bean.IclubSecurityMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubSecurityMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubSecurityMaster
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubSecurityMasterDAO {
	private static final Logger log = Logger
			.getLogger(IclubSecurityMasterDAO.class);
	// property constants
	public static final String SM_DESC = "smDesc";
	public static final String SM_STATUS = "smStatus";

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

	public void save(IclubSecurityMaster transientInstance) {
		log.debug("saving IclubSecurityMaster instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubSecurityMaster persistentInstance) {
		log.debug("deleting IclubSecurityMaster instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubSecurityMaster findById(java.lang.String id) {
		log.debug("getting IclubSecurityMaster instance with id: " + id);
		try {
			IclubSecurityMaster instance = (IclubSecurityMaster) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubSecurityMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubSecurityMaster> findByExample(IclubSecurityMaster instance) {
		log.debug("finding IclubSecurityMaster instance by example");
		try {
			List<IclubSecurityMaster> results = (List<IclubSecurityMaster>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubSecurityMaster")
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
		log.debug("finding IclubSecurityMaster instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubSecurityMaster as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubSecurityMaster> findBySmDesc(Object smDesc) {
		return findByProperty(SM_DESC, smDesc);
	}

	public List<IclubSecurityMaster> findBySmStatus(Object smStatus) {
		return findByProperty(SM_STATUS, smStatus);
	}

	public List findAll() {
		log.debug("finding all IclubSecurityMaster instances");
		try {
			String queryString = "from IclubSecurityMaster";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubSecurityMaster merge(IclubSecurityMaster detachedInstance) {
		log.debug("merging IclubSecurityMaster instance");
		try {
			IclubSecurityMaster result = (IclubSecurityMaster) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubSecurityMaster instance) {
		log.debug("attaching dirty IclubSecurityMaster instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubSecurityMaster instance) {
		log.debug("attaching clean IclubSecurityMaster instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubSecurityMasterDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubSecurityMasterDAO) ctx.getBean("IclubSecurityMasterDAO");
	}
}