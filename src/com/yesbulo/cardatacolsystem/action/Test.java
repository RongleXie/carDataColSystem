package com.yesbulo.cardatacolsystem.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.yesbulo.cardatacolsystem.impl.ObjectDaoImpl;
import com.yesbulo.cardatacolsystem.server.HibernateSessionFactory;

public class Test {
	public static void main(String[] args) {
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction tx = session.beginTransaction();
			//List list = session.createQuery("select * from Users").list();
			
			String hqlString = "select sum(totalpoints) from Users";
			Query query = session.createQuery(hqlString);
			query.setCacheable(true);
			List<Object[]> list = query.list();
			System.out.print(list.get(0));
			tx.commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<?> list1 = new ObjectDaoImpl().getObjectListByfield("Users", "user_email",
				"'362929422@qq.com'");
		System.out.println(list1.size());
		//new CardataAction().getCardataOfWeek();
		//new CardataAction().getCardataOfDay();
	}
}
