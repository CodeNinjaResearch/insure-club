package za.co.iclub.pss.orm.dao;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import za.co.iclub.pss.orm.bean.IclubBankMaster;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubBankMaster entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubBankMaster
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubBankMasterDAO {
	private static final Logger log = LoggerFactory.getLogger(IclubBankMasterDAO.class);
	// property constants
	public static final String BM_BANK_NAME = "bmBankName";
	public static final String BM_BANK_CODE = "bmBankCode";
	public static final String BM_BRANCH_NAME = "bmBranchName";
	public static final String BM_BRANCH_CODE = "bmBranchCode";
	public static final String BM_BRANCH_ADDRESS = "bmBranchAddress";
	public static final String BM_BRANCH_LAT = "bmBranchLat";
	public static final String BM_BRANCH_LONG = "bmBranchLong";
	public static final String BM_BRANCH_ZIP = "bmBranchZip";

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

	public void save(IclubBankMaster transientInstance) {
		log.debug("saving IclubBankMaster instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubBankMaster persistentInstance) {
		log.debug("deleting IclubBankMaster instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubBankMaster findById(java.lang.Long id) {
		log.debug("getting IclubBankMaster instance with id: " + id);
		try {
			IclubBankMaster instance = (IclubBankMaster) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubBankMaster", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubBankMaster> findByExample(IclubBankMaster instance) {
		log.debug("finding IclubBankMaster instance by example");
		try {
			List<IclubBankMaster> results = (List<IclubBankMaster>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubBankMaster").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubBankMaster instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubBankMaster as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubBankMaster> findByBmBankName(Object bmBankName) {
		return findByProperty(BM_BANK_NAME, bmBankName);
	}

	public List<IclubBankMaster> findByBmBankCode(Object bmBankCode) {
		return findByProperty(BM_BANK_CODE, bmBankCode);
	}

	public List<IclubBankMaster> findByBmBranchName(Object bmBranchName) {
		return findByProperty(BM_BRANCH_NAME, bmBranchName);
	}

	public List<IclubBankMaster> findByBmBranchCode(Object bmBranchCode) {
		return findByProperty(BM_BRANCH_CODE, bmBranchCode);
	}

	public List<IclubBankMaster> findByBmBranchAddress(Object bmBranchAddress) {
		return findByProperty(BM_BRANCH_ADDRESS, bmBranchAddress);
	}

	public List<IclubBankMaster> findByBmBranchLat(Object bmBranchLat) {
		return findByProperty(BM_BRANCH_LAT, bmBranchLat);
	}

	public List<IclubBankMaster> findByBmBranchLong(Object bmBranchLong) {
		return findByProperty(BM_BRANCH_LONG, bmBranchLong);
	}

	public List<IclubBankMaster> findByBmBranchZip(Object bmBranchZip) {
		return findByProperty(BM_BRANCH_ZIP, bmBranchZip);
	}

	public List findAll() {
		log.debug("finding all IclubBankMaster instances");
		try {
			String queryString = "from IclubBankMaster";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubBankMaster merge(IclubBankMaster detachedInstance) {
		log.debug("merging IclubBankMaster instance");
		try {
			IclubBankMaster result = (IclubBankMaster) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubBankMaster instance) {
		log.debug("attaching dirty IclubBankMaster instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubBankMaster instance) {
		log.debug("attaching clean IclubBankMaster instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubBankMasterDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubBankMasterDAO) ctx.getBean("IclubBankMasterDAO");
	}
}