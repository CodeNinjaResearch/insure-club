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

import za.co.iclub.pss.orm.bean.IclubQuote;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubQuote entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubQuote
 * @author MyEclipse Persistence Tools
 */
@Transactional@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubQuoteDAO {
	private static final Logger log = Logger.getLogger(IclubQuoteDAO.class);
	// property constants
	public static final String _QNUMBER = "QNumber";
	public static final String _QNUM_ITEMS = "QNumItems";
	public static final String _QGEN_PREMIUM = "QGenPremium";
	public static final String _QEMAIL = "QEmail";
	public static final String _QMOBILE = "QMobile";
	public static final String _QPREV_PREMIUM = "QPrevPremium";
	public static final String _QIS_MATCHED = "QIsMatched";
	public static final String _QCLAIM_YN = "QClaimYn";

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

	public void save(IclubQuote transientInstance) {
		log.debug("saving IclubQuote instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubQuote persistentInstance) {
		log.debug("deleting IclubQuote instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubQuote findById(java.lang.String id) {
		log.debug("getting IclubQuote instance with id: " + id);
		try {
			IclubQuote instance = (IclubQuote) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubQuote", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubQuote> findByExample(IclubQuote instance) {
		log.debug("finding IclubQuote instance by example");
		try {
			List<IclubQuote> results = (List<IclubQuote>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubQuote").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubQuote instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubQuote as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubQuote> findByQNumber(Object QNumber) {
		return findByProperty(_QNUMBER, QNumber);
	}

	public List<IclubQuote> findByQNumItems(Object QNumItems) {
		return findByProperty(_QNUM_ITEMS, QNumItems);
	}

	public List<IclubQuote> findByQGenPremium(Object QGenPremium) {
		return findByProperty(_QGEN_PREMIUM, QGenPremium);
	}

	public List<IclubQuote> findByQEmail(Object QEmail) {
		return findByProperty(_QEMAIL, QEmail);
	}

	public List<IclubQuote> findByQMobile(Object QMobile) {
		return findByProperty(_QMOBILE, QMobile);
	}

	public List<IclubQuote> findByQPrevPremium(Object QPrevPremium) {
		return findByProperty(_QPREV_PREMIUM, QPrevPremium);
	}

	public List<IclubQuote> findByQIsMatched(Object QIsMatched) {
		return findByProperty(_QIS_MATCHED, QIsMatched);
	}

	public List<IclubQuote> findByQClaimYn(Object QClaimYn) {
		return findByProperty(_QCLAIM_YN, QClaimYn);
	}

	public List findAll() {
		log.debug("finding all IclubQuote instances");
		try {
			String queryString = "from IclubQuote";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubQuote merge(IclubQuote detachedInstance) {
		log.debug("merging IclubQuote instance");
		try {
			IclubQuote result = (IclubQuote) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubQuote instance) {
		log.debug("attaching dirty IclubQuote instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubQuote instance) {
		log.debug("attaching clean IclubQuote instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubQuoteDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubQuoteDAO) ctx.getBean("IclubQuoteDAO");
	}
}