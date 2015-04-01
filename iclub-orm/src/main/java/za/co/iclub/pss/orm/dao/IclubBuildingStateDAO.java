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

import za.co.iclub.pss.orm.bean.IclubBuildingState;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubBuildingState entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubBuildingState
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubBuildingStateDAO {
	private static final Logger log = Logger.getLogger(IclubBuildingStateDAO.class);
	// property constants
	public static final String BS_SHORT_DESC = "bsShortDesc";
	public static final String BS_LONG_DESC = "bsLongDesc";
	public static final String BS_STATUS = "bsStatus";

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

	public void save(IclubBuildingState transientInstance) {
		log.debug("saving IclubBuildingState instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubBuildingState persistentInstance) {
		log.debug("deleting IclubBuildingState instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubBuildingState findById(java.lang.Long id) {
		log.debug("getting IclubBuildingState instance with id: " + id);
		try {
			IclubBuildingState instance = (IclubBuildingState) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubBuildingState", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubBuildingState> findByExample(IclubBuildingState instance) {
		log.debug("finding IclubBuildingState instance by example");
		try {
			List<IclubBuildingState> results = (List<IclubBuildingState>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubBuildingState").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubBuildingState instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubBuildingState as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubBuildingState> findByBsShortDesc(Object bsShortDesc) {
		return findByProperty(BS_SHORT_DESC, bsShortDesc);
	}

	public List<IclubBuildingState> findByBsLongDesc(Object bsLongDesc) {
		return findByProperty(BS_LONG_DESC, bsLongDesc);
	}

	public List<IclubBuildingState> findByBsStatus(Object bsStatus) {
		return findByProperty(BS_STATUS, bsStatus);
	}

	public List findAll() {
		log.debug("finding all IclubBuildingState instances");
		try {
			String queryString = "from IclubBuildingState";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubBuildingState merge(IclubBuildingState detachedInstance) {
		log.debug("merging IclubBuildingState instance");
		try {
			IclubBuildingState result = (IclubBuildingState) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubBuildingState instance) {
		log.debug("attaching dirty IclubBuildingState instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubBuildingState instance) {
		log.debug("attaching clean IclubBuildingState instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubBuildingStateDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubBuildingStateDAO) ctx.getBean("IclubBuildingStateDAO");
	}

}