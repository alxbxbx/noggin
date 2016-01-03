package com.noggin.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.annotation.MultipartConfig;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.document.Field.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.noggin.dao.repositories.IBook;
import com.noggin.dao.repositories.ICategory;
import com.noggin.dao.repositories.ILanguage;
import com.noggin.dao.repositories.IUser;
import com.noggin.lucene.handlers.PDFHandler;
import com.noggin.lucene.indexing.Indexer;
import com.noggin.models.Book;
import com.noggin.models.Category;
import com.noggin.models.Language;
import com.noggin.models.User;

@RestController
@RequestMapping(value = "book")
@MultipartConfig(fileSizeThreshold = 20971520)
public class BookController {

	@Autowired
	private IBook ib;
	
	@Autowired
	private ICategory ic;
	
	@Autowired
	private ILanguage il;
	
	@Autowired
	private IUser iu;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public List<Book> getAll() {
		List<Book> allBooks = ib.findAll();
		List<Book> books = new ArrayList<Book>();
		for(Book b : allBooks){
			if(b.getTemp().equals(0))
				books.add(b);
		}
		return books;
	}
	@RequestMapping(value="/category/{id}")
	public List<Book> getByCategory(@PathVariable String id) {
		Integer intId = null;
		Category cat = null;
		try{
			intId = Integer.parseInt(id);
			cat = ic.findOne(intId);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Book> books = ib.findByCategory(cat);
		return books;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Book> get(@PathVariable String id) {
		Integer intId = null;
		Book book = null;
		try {
			intId = Integer.parseInt(id);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		book = ib.findOne(intId);
		if(book.getTemp().equals(1))
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Book>(book, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Book> returnKeywords(@RequestParam("file") MultipartFile uploadedPDF,
			@RequestParam("languageId") String lanId, @RequestParam("categoryId") String catId,
			@RequestParam("userId") String userId) {

		String storagePath = ResourceBundle.getBundle("application").getString("temp");
		String fileName = uploadedPDF.getOriginalFilename();
		fileName = System.currentTimeMillis() + ".pdf";
		storagePath += fileName;

		byte[] buffer = new byte[1000];

		File outputFile = new File(storagePath);

		FileInputStream reader = null;
		FileOutputStream writer = null;
		int totalBytes = 0;

		try {
			reader = (FileInputStream) uploadedPDF.getInputStream();
			writer = new FileOutputStream(outputFile);
			int bytesRead = 0;

			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer);
				totalBytes += bytesRead;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		PDFHandler handler = new PDFHandler();
		Document doc = handler.getDocument(outputFile);
		Indexer.getInstance().index(outputFile);

		Book book = new Book();
		book.setFilename(fileName);
		book.setPath(storagePath);
		book.setTitle(doc.get("title"));
		book.setKeywords(doc.get("keyword"));
		Long a = System.currentTimeMillis();
		book.setCreatedAt(a.toString());
		Integer categoryId = Integer.parseInt(catId);
		Integer languageId = Integer.parseInt(lanId);
		Integer userID = Integer.parseInt(userId);
		Category c = ic.findOne(categoryId);
		Language l = il.findOne(languageId);
		User u = iu.findOne(userID); 
		book.setCategory(c);
		book.setLanguage(l);
		book.setUser(u);
		book.setTemp(1);

		return new ResponseEntity<Book>(ib.save(book), HttpStatus.OK);

	}

	@RequestMapping(value = "/permanent", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Book> storeBook(@RequestBody Book book) {
		Book b = ib.findOne(book.getId());
		
		b.setAuthor(book.getAuthor());
		b.setCategory(book.getCategory());
		b.setKeywords(book.getKeywords());
		b.setLanguage(book.getLanguage());
		b.setPublicationYear(book.getPublicationYear());
		b.setTitle(book.getTitle());
		b.setUser(book.getUser());
		b.setTemp(0);
		
		String storagePath = ResourceBundle.getBundle("application").getString("storage");
		storagePath += b.getFilename();

		FileInputStream reader = null;
		FileOutputStream writer = null;

		try {
			File tempFile = new File(b.getPath());
			File storedFile = new File(storagePath);

			reader = new FileInputStream(tempFile);
			writer = new FileOutputStream(storedFile);

			byte[] buffer = new byte[1024];

			int length;

			while ((length = reader.read(buffer)) > 0) {
				writer.write(buffer, 0, length);
			}
			reader.close();
			writer.close();

			Indexer.getInstance().index(storedFile);

			tempFile.delete();
		} catch (IOException e) {
			e.printStackTrace();
			//return new ResponseEntity<Book>(HttpStatus.CONFLICT);
		}
		b.setPath(storagePath);

		// Update PDF info
		TextField keywords = new TextField("keywords", b.getKeywords(), Store.YES);
		TextField author = new TextField("author", b.getAuthor(), Store.YES);
		TextField title = new TextField("title", b.getTitle(), Store.YES);
		TextField filename = new TextField("filename", b.getFilename(), Store.YES);
		List<IndexableField> fields = new ArrayList<IndexableField>();
		fields.add(keywords);
		fields.add(author);
		fields.add(title);
		fields.add(filename);
		Indexer.getInstance().updateDocument(b.getFilename(), fields);

		return new ResponseEntity<Book>(ib.save(b), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Book> book(@PathVariable String id) {
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
			Book book = new Book();
			book = ib.findOne(intId);

			// File system delete
			File bookFile = new File(book.getPath());
			bookFile.delete();

			// Database file delete
			ib.delete(book);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Book>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<Book> book(@PathVariable String id, @RequestBody Book book) {

		Book b = new Book();
		Integer intId = null;
		try {
			intId = Integer.parseInt(id);
			b = ib.findOne(intId);
		} catch (Exception e) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}

		// Update book
		b.setAuthor(book.getAuthor());
		b.setCategory(book.getCategory());
		b.setKeywords(book.getKeywords());
		b.setLanguage(book.getLanguage());
		b.setPublicationYear(book.getPublicationYear());
		b.setTitle(book.getTitle());

		// Update PDF
		TextField keywords = new TextField("keywords", b.getKeywords(), Store.YES);
		TextField author = new TextField("author", b.getAuthor(), Store.YES);
		TextField title = new TextField("title", b.getTitle(), Store.YES);
		TextField filename = new TextField("filename", b.getFilename(), Store.YES);
		List<IndexableField> fields = new ArrayList<IndexableField>();
		fields.add(keywords);
		fields.add(author);
		fields.add(title);
		fields.add(filename);
		Indexer.getInstance().updateDocument(b.getFilename(), fields);

		return new ResponseEntity<Book>(ib.save(b), HttpStatus.OK);
	}

}
