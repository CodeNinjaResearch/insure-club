package za.co.iclub.pss.orm.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SuppressWarnings({ "rawtypes" })
public class IclubCommonDAO {

	private static final Logger log = Logger.getLogger(IclubCommonDAO.class);

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

	public Long getNextId(Class clazz) {
		Long ret = -1l;
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) sessionFactory.getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret = Long.valueOf(res.get(0).toString());
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	public Long[] getNextIds(Class clazz, int num) {
		Long[] ret = new Long[num];
		try {
			AbstractEntityPersister persister = (AbstractEntityPersister) sessionFactory.getClassMetadata(clazz);
			String sql = "select ifnull(max(" + persister.getIdentifierColumnNames()[0] + "),0)+1 from " + persister.getTableName();
			SQLQuery query = getCurrentSession().createSQLQuery(sql);
			List res = query.list();
			if (res != null && res.size() > 0)
				ret[0] = Long.valueOf(res.get(0).toString());
			for (int i = 1; i < num; i++)
				ret[i] = ret[0] + i;
		} catch (Exception e) {
			log.error(e, e);
			throw new RuntimeException(e);
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List findAllLookValuesByTabelName(String tableName) {
		log.debug("finding all IclubVehicleMaster instances by vmMake");
		try {
			String colShrtNm = getColumFormat(tableName);
			String query = "select t." + colShrtNm + "_id, t." + colShrtNm + "_short_desc,t." + colShrtNm + "_long_desc from " + tableName + " t";
			Query queryObject = getCurrentSession().createSQLQuery(query);
			List<Object[]> objectArray = (List<Object[]>) queryObject.list();
			List lookDetails = new ArrayList<String>();
			for (Object[] objs : objectArray) {

				String details = objs[0] + "-" + objs[1] + "-" + objs[2];
				lookDetails.add(details);
			}
			return lookDetails;
		} catch (RuntimeException re) {
			log.error("find all by vmMake failed", re);
			throw re;
		}
	}

	public String findAllLookValuesByTabelName(String tableName, String id) {
		log.debug("finding all IclubVehicleMaster instances by vmMake");
		try {
			String colShrtNm = getColumFormat(tableName);
			String query = "select t." + colShrtNm + "_id, t." + colShrtNm + "_short_desc,t." + colShrtNm + "_long_desc from " + tableName + " t where " + colShrtNm + "_id=" + id;
			Query queryObject = getCurrentSession().createSQLQuery(query);
			Object[] obj = (Object[]) queryObject.uniqueResult();

			String lookDetails = obj[0] + "-" + obj[1] + "-" + obj[2];

			return lookDetails;
		} catch (RuntimeException re) {
			log.error("find all by vmMake failed", re);
			throw re;
		}
	}

	public Object getFieldValueByFieldNameAndId(String fieldName, String tableName, String recordId) {

		try {
			String colShrtNm = getColumFormat(tableName);
			String query = "select  " + fieldName + " from " + tableName + " where " + colShrtNm + "_id like " + "'" + recordId + "'";

			Query queryObject = getCurrentSession().createSQLQuery(query);

			return queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find FieldValue", re);
			throw re;
		}

	}

	public String getColumFormat(String tableName) {
		String colShrtNm = "";
		for (int i = 1; i < tableName.split("_").length; i++) {
			colShrtNm += tableName.split("_")[i].substring(0, 1);
		}
		return colShrtNm;
	}

	public static void main(String[] args) {
		String tableName = "iclub_rate_type";
		String colShrtNm = "";
		String tableNames[] = tableName.split("_");
		for (int i = 1; i < tableNames.length; i++) {
			colShrtNm += tableNames[i].substring(0, 1);
		}
		System.out.println(colShrtNm);

	}

	public static IclubCommonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IclubCommonDAO) ctx.getBean("IclubCommonDAO");
	}

}
