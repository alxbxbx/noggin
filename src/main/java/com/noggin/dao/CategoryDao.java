package com.noggin.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.noggin.models.Category;


public class CategoryDao implements CategoryDaoInterface{
	
	private static  SessionFactory factory;
	
	
	public static void main(String[] args){
		


	}

	@Override
	public void addCategory(Category category) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
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
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Category category = session.get(Category.class, id);
			session.delete(category);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
	}

	@Override
	public void updateCategory(Category category) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(category);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
	}

	@Override
	public Category getCategory(Integer id) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Category category = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			category = session.get(Category.class, id);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		try{
			factory = new Configuration().configure().addAnnotatedClass(Category.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		List<Category> categories = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			categories = session.createCriteria(Category.class).list();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		return categories;
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInBatch(Iterable<Category> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll(Sort arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll(Iterable<Integer> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> List<S> save(Iterable<S> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> S saveAndFlush(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Category> findAll(Pageable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Integer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Category arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends Category> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exists(Integer arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category findOne(Integer arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Category> S save(S arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
