package com.noggin.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.noggin.models.File;

public class FileDao implements FileDaoInterface {
	
	private static  SessionFactory factory;
	
	public static void main(String[] args){
		
		FileDao fd = new FileDao();
		List<File> files = fd.getFilesByBook(1);
		for(File f : files){
			System.out.println(f.getFileName());
		}
		
	}
	
	public void addFile(File file){
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(file);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
	}

	public void removeFile(Integer id) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			File file = session.get(File.class, id);
			session.delete(file);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		
	}

	public void updateFile(File file) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(file);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
	}

	public File getFile(Integer id) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		File file = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			file = session.get(File.class, id);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		return file;
	}

	@Override
	public List<File> getFilesByBook(Integer id) {
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		List<File> files = null;
		Session session = factory.openSession();
		Transaction tx = null;
		String hql = "FROM File f WHERE f.book.id=" + id.toString();
		Query query = session.createQuery(hql);
		try {
			tx = session.beginTransaction();
			files = query.list();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		return files;
	}

	@Override
	public List<File> getAllFiles() {
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		List<File> files = null;
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			files = session.createCriteria(File.class).list();
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
		return files;
	}
	
	

}
