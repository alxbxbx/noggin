package com.noggin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.noggin.models.Category;


public class CategoryDao implements CategoryDaoInterface{
	
	private static  SessionFactory factory;
	
	public static void main(String[] args){
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Category category = new Category();
		category.setName("Akcija");
		CategoryDao cd = new CategoryDao();
		cd.addCategory(category);
		
	}

	@Override
	public void addCategory(Category category) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(category);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		
	}

	@Override
	public void removeCategory(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getCategory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
