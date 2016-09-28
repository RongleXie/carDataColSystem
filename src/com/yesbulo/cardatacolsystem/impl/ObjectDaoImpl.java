package com.yesbulo.cardatacolsystem.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.yesbulo.cardatacolsystem.server.HibernateSessionFactory;
import com.yesbulo.cardatacolsystem.server.ObjectDao;


/**
 * <p>@Title:ObjectDaoImpl</P>
 * <p>@Description:carDataColSystem</P>
 * <p>@Company: RongleXie </P>
 * <p>@author xieqingrong</p>
 * <p>@date 2016-9-2 下午03:07:24
 */
public class ObjectDaoImpl implements ObjectDao {

	private SessionFactory sessionFactory = HibernateSessionFactory
			.getSessionFactory();
	private Session session;
	private Transaction tran;

	// 通过id查找对象
	@SuppressWarnings("unchecked")
	public Object getObjectById(Class cls, int id) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Object obj = session.get(cls, id);
			tran.commit();
			return obj;
		} catch (Exception e) {
			return null;
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 保存或修改对象
	public void saveOrUpdate(Object obj) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			session.saveOrUpdate(obj);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 修改对象
	public void update(Object obj) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			session.update(obj);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据HQL执行增删改语句
	public void executeUpdate(String hql) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 添加对象
	public void save(Object obj) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			session.save(obj);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 删除对象
	public void delete(Object obj) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			session.delete(obj);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 删除对象通过id
	@SuppressWarnings("unchecked")
	public void delete(Class cls, int id) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			session.delete(session.load(cls, id));
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据一个字段获取对象列表
	public List<?> getObjectListByfield(String table, String dbfield,
			Object field) {
		System.out.println("ObjectDaoImpl.getObjectListByfield()");
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			String hql = "from " + table + " where " + dbfield + "=" + field;
			System.out.println(hql);
//			if (table.equals("Users")) {
//				hql += " and useIscompany<>2";
//			}
			System.out.println("ObjectDaoImpl.getObjectListByfield()");
			Query query = session.createQuery(hql);
			System.out.println("查询纪录条件："+field);
			//query.setParameter(0, field);
			System.out.println("hql查询语句："+hql);
			query.setCacheable(true);// 使用二级缓存
			List<?> list = query.list();
			System.out.println("查询纪录的条数："+list.size());
			tran.commit();
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据一个字段获取对象列表(激活使用)
	public List<?> getObjectListByfieldInActivate(String table, String dbfield,
			Object field) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			String hql = "from " + table + " where " + dbfield + "=?";

			Query query = session.createQuery(hql);
			query.setParameter(0, field);
			query.setCacheable(true);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据一组字段获取对象列表（参数数组应小于3，2以上为写相应代码）
	public List<?> getObjectListByfield(String table, String dbfields[],
			Object fields[]) {
		String hql = "from " + table + " where ";
		for (int i = 0; i < dbfields.length; i++) {
			if (i == fields.length - 1) {
				hql += dbfields[i] + "=?";
				continue;
			}
			hql += dbfields[i] + "=? and ";
		}
		if (table.equals("Users")) {
			hql += " and useIscompany<>2";
		}
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			for (int i = 0; i < dbfields.length; i++) {
				query.setParameter(i, fields[i]);
			}
			query.setCacheable(true);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据搜索条件获得对象数
	public int getObjectSizeBycond(String hql, List<?> list) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setParameterList("list", list);
			int i = ((Long) query.iterate().next()).intValue();
			tran.commit();
			return i;
		} catch (Exception e) {
			return 0;
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据搜索条件获得对象数
	public int getObjectSizeBycond(String hql) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			int i = (Long.valueOf(query.iterate().next().toString()))
					.intValue();
			tran.commit();
			return i;
		} catch (Exception e) {
			return 0;
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据搜索条件获得对象数量(给定返回个数)
	public List<?> getSomeObjectListBycond(String hql, int limit) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setMaxResults(limit);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 根据搜索条件获得对象列表
	public List<?> getObjectListBycond(String table, String cond) {
		String hql = "from " + table + " " + cond;
		if (table.equals("Users")) {
			hql += " and useIscompany<>2";
		}
		return getObjectListBycond(hql);

	}

	// 根据搜索条件获得对象列表
	public List<?> getObjectListBycond(String cond) {
		String hql = cond;
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setCacheable(true);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}
	
	
	
	
	// 登录验证
	public List<?> check4List(String table, String useName, String password) {
		String hql = "from " + table
				+ " where user_email = "+useName+" and user_pwd = "+password+" ";
		System.out.println(hql);
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			Query query = session.createQuery(hql);
			//query.setCacheable(true);
			List<?> list = query.list();
			tran.commit();
			System.out.println("size:"+list.size());
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 分页显示
	public List<?> pageList(String table, int first, int perPageRow) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			String hql = "from " + table;
			if (table.equals("Users")) {
				hql += "where useIscompany<>2";
			}
			Query query = session.createQuery(hql);
			query.setFirstResult(first * perPageRow);
			query.setMaxResults(perPageRow);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 分页显示(含条件，需含where关键字)
	public List<?> pageListWithCond(String table, int first, int perPageRow,
			String cond) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			String hql = "from " + table + " " + cond;
			if (table.equals("Users")) {
				hql += " and useIscompany<>2";
			}
			Query query = session.createQuery(hql);
			query.setFirstResult(first * perPageRow);
			query.setMaxResults(perPageRow);
			List<?> list = query.list();
			tran.commit();
			return list;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 分页显示(含条件，需含where关键字)
	public List<?> pageListWithCond(String table, int first, int perPageRow,
			String cond, List<?> list) {
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			String hql = "from " + table + " " + cond;
			if (table.equals("Users")) {
				hql += " and useIscompany<>2";
			}
			Query query = session.createQuery(hql);
			query.setFirstResult(first * perPageRow);
			query.setMaxResults(perPageRow);
			query.setParameterList("list", list);
			List<?> list1 = query.list();
			tran.commit();
			return list1;
		} catch (Exception e) {
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	//获取历史数据-月所需要的数据
	public List<?> getObjectListBycondCardataMonth() {
		// TODO Auto-generated method stub
		String hql = "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time between concat(substr(curdate(),1,8),'01 00:00:00') and concat(substr(curdate(),1,8),'07 23:59:59')"
			+ " union all "
			+ " select  ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time between concat(substr(curdate(),1,8),'08 00:00:00') and concat(substr(curdate(),1,8),'14 23:59:59')"
			+ " union all "
			+ " select  ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time between concat(substr(curdate(),1,8),'15 00:00:00') and concat(substr(curdate(),1,8),'21 23:59:59')"
			+ " union all "
			+ " select  ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time between concat(substr(curdate(),1,8),'22 00:00:00') and concat(substr(date_sub(curdate(),interval -1 month),1,8),'01')";
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			List<Object[]> results = session.createSQLQuery(hql).list();
			System.out.println(results.size());
			tran.commit();
			return results;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	// 获取历史数据-周所需要的数据
	public List<?> getObjectListBycondCardataWeek() {
		// String hql =
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(curdate(),'%')"
		// +
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 1 day),'%')"+
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 2 day),'%')"+
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 3 day),'%')"+
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 4 day),'%')"+
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 5 day),'%')"+
		// "union all "+
		// "select avg (cardata_speed),avg (cardata_acceleration),avg (cardata_longitude),avg (cardata_latitude),avg (cardata_altitude),avg (cardata_slope) from cardata where cardata_time like concat(date_sub(curdate(),interval 6 day),'%')";
		String hql = "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(curdate(),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 1 day),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 2 day),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 3 day),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 4 day),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 5 day),'%')"
				+ "union all "
				+ "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0) from cardata where cardata_time like concat(date_sub(curdate(),interval 6 day),'%')";
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			List<Object[]> results = session.createSQLQuery(hql).list();
			System.out.println(results.size());
			tran.commit();
			return results;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

	//获取历史数据-年所需要的数据
	public List<?> getObjectListBycondCardataYear() {
		// TODO Auto-generated method stub
		String hql = "select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-01%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-02%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-03%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-04%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-05%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-06%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-07%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-08%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-09%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-10%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-11%') "
				+ " union all "
				+ " select ifnull(avg (cardata_speed),0),ifnull(avg (cardata_acceleration),0),ifnull(avg (cardata_longitude),0),ifnull(avg (cardata_latitude),0),ifnull(avg (cardata_altitude),0),ifnull(avg (cardata_slope),0)  from cardata where cardata_time like concat(year(curdate()),'-12%')";
		try {
			session = sessionFactory.openSession();
			tran = session.beginTransaction();
			List<Object[]> results = session.createSQLQuery(hql).list();
			System.out.println(results.size());
			tran.commit();
			return results;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Object>();
		} finally {
			if (session.isOpen())
				session.close();
		}
	}

}
