package com.noggin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.noggin.models.File;

public class FileDao {
	
	private static  SessionFactory factory;
	
	public static void main(String[] args){
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		FileDao fd = new FileDao();
		fd.addFile("NewFile", "Something is about to happen");
		
	}
	
	public void addFile(String filename, String mime){
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			File file = new File();
			file.setFileName(filename);
			file.setMIME(mime);
			session.save(file);
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
         session.close(); 
		}
	}

}
