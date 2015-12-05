package com.noggin.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.noggin.models.Book;
import com.noggin.models.File;

public class FileDao implements FileDaoInterface {
	
	private static  SessionFactory factory;
	
	public static void main(String[] args){
		try{
			factory = new Configuration().configure().addAnnotatedClass(File.class).buildSessionFactory();
			
		}catch(Throwable ex){
			System.err.println("Failed to create Session Factory object. "+ex);
			throw new ExceptionInInitializerError(ex);
		}
		Book book = new Book();
		book.setId(1);
		FileDao fd = new FileDao();
		File file = new File("Third file", "Something is about to happen", book);
		fd.addFile(file);
		
	}
	
	public void addFile(File file){
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
		// TODO Auto-generated method stub
		
	}

	public void updateFile(File file) {
		// TODO Auto-generated method stub
		
	}

	public File getFile(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
