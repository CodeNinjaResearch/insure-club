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

import za.co.iclub.pss.orm.bean.IclubVehicle;

/**
 * A data access object (DAO) providing persistence and search support for
 * IclubVehicle entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see za.co.iclub.pss.orm.bean.IclubVehicle
 * @author MyEclipse Persistence Tools
 */
@Transactional
@SuppressWarnings({ "unchecked", "rawtypes" })
public class IclubVehicleDAO {
	private static final Logger log = Logger.getLogger(IclubVehicleDAO.class);
	// property constants
	public static final String _VODOMETER = "VOdometer";
	public static final String _VON_AREA = "VOnArea";
	public static final String _VON_LAT = "VOnLat";
	public static final String _VON_LONG = "VOnLong";
	public static final String _VDD_AREA = "VDdArea";
	public static final String _VDD_LAT = "VDdLat";
	public static final String _VDD_LONG = "VDdLong";
	public static final String _VYEAR = "VYear";
	public static final String _VINSURED_VALUE = "VInsuredValue";
	public static final String _VCONCESS_PRCT = "VConcessPrct";
	public static final String _VCONCESS_REASON = "VConcessReason";
	public static final String _VIMM_YN = "VImmYn";
	public static final String _VGEAR_LOCK_YN = "VGearLockYn";
	public static final String _VOWNER = "VOwner";
	public static final String _VNOCLAIM_YRS = "VNoclaimYrs";
	public static final String _VCOMP_YRS = "VCompYrs";
	public static final String _VVIN = "VVin";
	public static final String _VENGINE_NR = "VEngineNr";
	public static final String _VREG_NUM = "VRegNum";
	public static final String _VMODIFIED_YN = "VModifiedYn";

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

	public void save(IclubVehicle transientInstance) {
		log.debug("saving IclubVehicle instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(IclubVehicle persistentInstance) {
		log.debug("deleting IclubVehicle instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public IclubVehicle findById(java.lang.String id) {
		log.debug("getting IclubVehicle instance with id: " + id);
		try {
			IclubVehicle instance = (IclubVehicle) getCurrentSession().get("za.co.iclub.pss.orm.bean.IclubVehicle", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<IclubVehicle> findByExample(IclubVehicle instance) {
		log.debug("finding IclubVehicle instance by example");
		try {
			List<IclubVehicle> results = (List<IclubVehicle>) getCurrentSession().createCriteria("za.co.iclub.pss.orm.bean.IclubVehicle").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding IclubVehicle instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from IclubVehicle as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<IclubVehicle> findByVOdometer(Object VOdometer) {
		return findByProperty(_VODOMETER, VOdometer);
	}

	public List<IclubVehicle> findByVOnArea(Object VOnArea) {
		return findByProperty(_VON_AREA, VOnArea);
	}

	public List<IclubVehicle> findByVOnLat(Object VOnLat) {
		return findByProperty(_VON_LAT, VOnLat);
	}

	public List<IclubVehicle> findByVOnLong(Object VOnLong) {
		return findByProperty(_VON_LONG, VOnLong);
	}

	public List<IclubVehicle> findByVDdArea(Object VDdArea) {
		return findByProperty(_VDD_AREA, VDdArea);
	}

	public List<IclubVehicle> findByVDdLat(Object VDdLat) {
		return findByProperty(_VDD_LAT, VDdLat);
	}

	public List<IclubVehicle> findByVDdLong(Object VDdLong) {
		return findByProperty(_VDD_LONG, VDdLong);
	}

	public List<IclubVehicle> findByVYear(Object VYear) {
		return findByProperty(_VYEAR, VYear);
	}

	public List<IclubVehicle> findByVInsuredValue(Object VInsuredValue) {
		return findByProperty(_VINSURED_VALUE, VInsuredValue);
	}

	public List<IclubVehicle> findByVConcessPrct(Object VConcessPrct) {
		return findByProperty(_VCONCESS_PRCT, VConcessPrct);
	}

	public List<IclubVehicle> findByVConcessReason(Object VConcessReason) {
		return findByProperty(_VCONCESS_REASON, VConcessReason);
	}

	public List<IclubVehicle> findByVImmYn(Object VImmYn) {
		return findByProperty(_VIMM_YN, VImmYn);
	}

	public List<IclubVehicle> findByVGearLockYn(Object VGearLockYn) {
		return findByProperty(_VGEAR_LOCK_YN, VGearLockYn);
	}

	public List<IclubVehicle> findByVOwner(Object VOwner) {
		return findByProperty(_VOWNER, VOwner);
	}

	public List<IclubVehicle> findByVNoclaimYrs(Object VNoclaimYrs) {
		return findByProperty(_VNOCLAIM_YRS, VNoclaimYrs);
	}

	public List<IclubVehicle> findByVCompYrs(Object VCompYrs) {
		return findByProperty(_VCOMP_YRS, VCompYrs);
	}

	public List<IclubVehicle> findByVVin(Object VVin) {
		return findByProperty(_VVIN, VVin);
	}

	public List<IclubVehicle> findByVEngineNr(Object VEngineNr) {
		return findByProperty(_VENGINE_NR, VEngineNr);
	}

	public List<IclubVehicle> findByVRegNum(Object VRegNum) {
		return findByProperty(_VREG_NUM, VRegNum);
	}

	public List<IclubVehicle> findByVModifiedYn(Object VModifiedYn) {
		return findByProperty(_VMODIFIED_YN, VModifiedYn);
	}

	public List findAll() {
		log.debug("finding all IclubVehicle instances");
		try {
			String queryString = "from IclubVehicle";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public IclubVehicle merge(IclubVehicle detachedInstance) {
		log.debug("merging IclubVehicle instance");
		try {
			IclubVehicle result = (IclubVehicle) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(IclubVehicle instance) {
		log.debug("attaching dirty IclubVehicle instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(IclubVehicle instance) {
		log.debug("attaching clean IclubVehicle instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IclubVehicleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubVehicleDAO) ctx.getBean("IclubVehicleDAO");
	}
}