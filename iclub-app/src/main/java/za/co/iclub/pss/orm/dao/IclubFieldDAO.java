package za.co.iclub.pss.orm.dao;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubField;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubField entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubField
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubFieldDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubFieldDAO.class);
	// property constants
	public static final String _FNAME = "FName";
	public static final String _FDESC = "FDesc";
	public static final String _FTYPE = "FType";
	public static final String _FLTBL_NAME = "FLTblName";
	public static final String _FRATE = "FRate";
	public static final String _FSTATUS = "FStatus";
	
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
	
	public void save(IclubField transientInstance) {
		log.debug("saving IclubField instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	
	public void delete(IclubField persistentInstance) {
		log.debug("deleting IclubField instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	public IclubField findById(java.lang.Long id) {
		log.debug("getting IclubField instance with id: " + id);
		try {
			IclubField instance = (IclubField) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubField", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<IclubField> findByExample(IclubField instance) {
		log.debug("finding IclubField instance by example");
		try {
			List<IclubField> results = (List<IclubField>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubField").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubField instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubField as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List<IclubField> findByFName(Object FName) {
		return findByProperty(_FNAME, FName);
	}
	
	public List<IclubField> findByFDesc(Object FDesc) {
		return findByProperty(_FDESC, FDesc);
	}
	
	public List<IclubField> findByFType(Object FType) {
		return findByProperty(_FTYPE, FType);
	}
	
	public List<IclubField> findByFLTblName(Object FLTblName) {
		return findByProperty(_FLTBL_NAME, FLTblName);
	}
	
	public List<IclubField> findByFRate(Object FRate) {
		return findByProperty(_FRATE, FRate);
	}
	
	public List<IclubField> findByFStatus(Object FStatus) {
		return findByProperty(_FSTATUS, FStatus);
	}
	
	public List findAll() {
		log.debug("finding all IclubField instances");
		try {
			String queryString = "from IclubField";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public IclubField merge(IclubField detachedInstance) {
		log.debug("merging IclubField instance");
		try {
			IclubField result = (IclubField) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	
	public void attachDirty(IclubField instance) {
		log.debug("attaching dirty IclubField instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public void attachClean(IclubField instance) {
		log.debug("attaching clean IclubField instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public static IclubFieldDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubFieldDAO) ctx.getBean("IclubFieldDAO");
	}
}