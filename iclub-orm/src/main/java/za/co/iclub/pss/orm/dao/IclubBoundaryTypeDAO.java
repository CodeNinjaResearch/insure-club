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

import za.co.iclub.pss.orm.bean.IclubBoundaryType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubBoundaryType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubBoundaryType
 * @author Venu Madhav Pattamatta
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubBoundaryTypeDAO {
	private static final Logger log = Logger.getLogger(IclubBoundaryTypeDAO.class);
	// property constants
	public static final String BT_SHORT_DESC = "btShortDesc";
	public static final String BT_LONG_DESC = "btLongDesc";
	public static final String BT_STATUS = "btStatus";

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

	public void save(IclubBoundaryType transientInstance) {
		log.debug("saving IclubBoundaryType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubBoundaryType persistentInstance) {
		log.debug("deleting IclubBoundaryType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubBoundaryType findById(java.lang.Long id) {
		log.debug("getting IclubBoundaryType instance with id: " + id);
		try {
			IclubBoundaryType instance = (IclubBoundaryType) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubBoundaryType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubBoundaryType> findByExample(IclubBoundaryType instance) {
		log.debug("finding IclubBoundaryType instance by example");
		try {
			List<IclubBoundaryType> results = (List<IclubBoundaryType>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubBoundaryType").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubBoundaryType instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubBoundaryType as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubBoundaryType> findByBtShortDesc(Object btShortDesc) {
		return findByProperty(BT_SHORT_DESC, btShortDesc);
	}

	public List<IclubBoundaryType> findByBtLongDesc(Object btLongDesc) {
		return findByProperty(BT_LONG_DESC, btLongDesc);
	}

	public List<IclubBoundaryType> findByBtStatus(Object btStatus) {
		return findByProperty(BT_STATUS, btStatus);
	}

	public List findAll() {
		log.debug("finding all IclubBoundaryType instances");
		try {
			String queryString = "from IclubBoundaryType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubBoundaryType merge(IclubBoundaryType detachedInstance) {
		log.debug("merging IclubBoundaryType instance");
		try {
			IclubBoundaryType result = (IclubBoundaryType) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubBoundaryType instance) {
		log.debug("attaching dirty IclubBoundaryType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubBoundaryType instance) {
		log.debug("attaching clean IclubBoundaryType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubBoundaryTypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubBoundaryTypeDAO) ctx.getBean("IclubBoundaryTypeDAO");
	}

}