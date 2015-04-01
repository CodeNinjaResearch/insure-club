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

import za.co.iclub.pss.orm.bean.IclubDocument;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubDocument entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubDocument
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubDocumentDAO {
	private static final Logger log = Logger.getLogger(IclubDocumentDAO.class);
	// property constants
	public static final String _DNAME = "DName";
	public static final String _DMIME_TYPE = "DMimeType";
	public static final String _DSIZE = "DSize";
	public static final String _DENTITY_ID = "DEntityId";
	public static final String _DCONTENT = "DContent";

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

	public void save(IclubDocument transientInstance) {
		log.debug("saving IclubDocument instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubDocument persistentInstance) {
		log.debug("deleting IclubDocument instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubDocument findById(java.lang.String id) {
		log.debug("getting IclubDocument instance with id: " + id);
		try {
			IclubDocument instance = (IclubDocument) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubDocument", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubDocument> findByExample(IclubDocument instance) {
		log.debug("finding IclubDocument instance by example");
		try {
			List<IclubDocument> results = (List<IclubDocument>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubDocument").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubDocument instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubDocument as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubDocument> findByDName(Object DName) {
		return findByProperty(_DNAME, DName);
	}

	public List<IclubDocument> findByDMimeType(Object DMimeType) {
		return findByProperty(_DMIME_TYPE, DMimeType);
	}

	public List<IclubDocument> findByDSize(Object DSize) {
		return findByProperty(_DSIZE, DSize);
	}

	public List<IclubDocument> findByDEntityId(Object DEntityId) {
		return findByProperty(_DENTITY_ID, DEntityId);
	}

	public List<IclubDocument> findByDContent(Object DContent) {
		return findByProperty(_DCONTENT, DContent);
	}

	public List findAll() {
		log.debug("finding all IclubDocument instances");
		try {
			String queryString = "from IclubDocument";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubDocument merge(IclubDocument detachedInstance) {
		log.debug("merging IclubDocument instance");
		try {
			IclubDocument result = (IclubDocument) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubDocument instance) {
		log.debug("attaching dirty IclubDocument instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubDocument instance) {
		log.debug("attaching clean IclubDocument instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubDocumentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubDocumentDAO) ctx.getBean("IclubDocumentDAO");
	}
}