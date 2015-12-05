package com.noggin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.noggin.models.User;

public class UserDao implements UserDaoInterface{
	
	private static SessionFactory factory;
	
	public static void main(String[] args){
		try{
			factory = new Configuration().configure().addAnnotatedClass(User.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		User user = new User();
		user.setFirstName("Milutin");
		user.setLastName("Milankovic");
		user.setPassword("Nekipass");
		user.setUsername("Miki");
		user.setType("Admin");
		
		UserDao ud = new UserDao();
		ud.addUser(user);
		
		
		
	}

	@Override
	public void addUser(User user) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		
	}

	@Override
	public void removeUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
