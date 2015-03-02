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

import za.co.iclub.pss.orm.bean.IclubDocumentType;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubDocumentType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubDocumentType
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubDocumentTypeDAO {
	private static final Logger log = Logger
			.getLogger(IclubDocumentTypeDAO.class);
	// property constants
	public static final String DT_SHORT_DESC = "dtShortDesc";
	public static final String DT_LONG_DESC = "dtLongDesc";
	public static final String DT_STATUS = "dtStatus";

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

	public void save(IclubDocumentType transientInstance) {
		log.debug("saving IclubDocumentType instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubDocumentType persistentInstance) {
		log.debug("deleting IclubDocumentType instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubDocumentType findById(java.lang.Long id) {
		log.debug("getting IclubDocumentType instance with id: " + id);
		try {
			IclubDocumentType instance = (IclubDocumentType) getCurrentSession()
					.get("za.co.iclub.pss.orm.bean.IclubDocumentType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubDocumentType> findByExample(IclubDocumentType instance) {
		log.debug("finding IclubDocumentType instance by example");
		try {
			List<IclubDocumentType> results = (List<IclubDocumentType>) getCurrentSession()
					.createCriteria(
							"za.co.iclub.pss.orm.bean.IclubDocumentType")
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
		log.debug("finding IclubDocumentType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from IclubDocumentType as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubDocumentType> findByDtShortDesc(Object dtShortDesc) {
		return findByProperty(DT_SHORT_DESC, dtShortDesc);
	}

	public List<IclubDocumentType> findByDtLongDesc(Object dtLongDesc) {
		return findByProperty(DT_LONG_DESC, dtLongDesc);
	}

	public List<IclubDocumentType> findByDtStatus(Object dtStatus) {
		return findByProperty(DT_STATUS, dtStatus);
	}

	public List findAll() {
		log.debug("finding all IclubDocumentType instances");
		try {
			String queryString = "from IclubDocumentType";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubDocumentType merge(IclubDocumentType detachedInstance) {
		log.debug("merging IclubDocumentType instance");
		try {
			IclubDocumentType result = (IclubDocumentType) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubDocumentType instance) {
		log.debug("attaching dirty IclubDocumentType instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubDocumentType instance) {
		log.debug("attaching clean IclubDocumentType instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getDocumentTypeBySD(String sd, Long id) {
		log.debug("Fetching all Document Type by Query :: getDocumentTypeySD");
		try {
			Query query = getCurrentSession().getNamedQuery("getDocumentTypeBySD");
			query.setString("sd", sd);
			query.setLong("id", id);
			List ret = query.list();
			return ret;
		} catch (RuntimeException re) {
			log.error("Document Type", re);
			throw re;
		}
	}

	public static IclubDocumentTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IclubDocumentTypeDAO) ctx.getBean("IclubDocumentTypeDAO");
	}
}