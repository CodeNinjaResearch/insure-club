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

import za.co.iclub.pss.orm.bean.IclubMaritialStatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubMaritialStatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubMaritialStatus
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubMaritialStatusDAO {
	private static final Logger log = Logger
			.getLogger(IclubMaritialStatusDAO.class);
	// property constants
	public static final String MS_SHORT_DESC = "msShortDesc";
	public static final String MS_LONG_DESC = "msLongDesc";
	public static final String MS_STATUS = "msStatus";

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

	public void save(IclubMaritialStatus transientInstance) {
		log.debug("saving IclubMaritialStatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubMaritialStatus persistentInstance) {
		log.debug("deleting IclubMaritialStatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubMaritialStatus findById(java.lang.Long id) {
		log.debug("getting IclubMaritialStatus instance with id: " + id);
		try {
			IclubMaritialStatus instance = (IclubMaritialStatus) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubMaritialStatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubMaritialStatus> findByExample(IclubMaritialStatus instance) {
		log.debug("finding IclubMaritialStatus instance by example");
		try {
			List<IclubMaritialStatus> results = (List<IclubMaritialStatus>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubMaritialStatus")
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
		log.debug("finding IclubMaritialStatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubMaritialStatus as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubMaritialStatus> findByMsShortDesc(Object msShortDesc) {
		return findByProperty(MS_SHORT_DESC, msShortDesc);
	}

	public List<IclubMaritialStatus> findByMsLongDesc(Object msLongDesc) {
		return findByProperty(MS_LONG_DESC, msLongDesc);
	}

	public List<IclubMaritialStatus> findByMsStatus(Object msStatus) {
		return findByProperty(MS_STATUS, msStatus);
	}

	public List findAll() {
		log.debug("finding all IclubMaritialStatus instances");
		try {
			String queryString = "from IclubMaritialStatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubMaritialStatus merge(IclubMaritialStatus detachedInstance) {
		log.debug("merging IclubMaritialStatus instance");
		try {
			IclubMaritialStatus result = (IclubMaritialStatus) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubMaritialStatus instance) {
		log.debug("attaching dirty IclubMaritialStatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubMaritialStatus instance) {
		log.debug("attaching clean IclubMaritialStatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getMaritialStatusBySD(String sd, Long id) {
		log.debug("Fetching all Maritial Status by Query :: getMaritialStatusBySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getMaritialStatusBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Maritial Status", re);
			throw re;
		}
	}

	public static IclubMaritialStatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubMaritialStatusDAO) ctx.getBean("IclubMaritialStatusDAO");
	}
}