package com.noggin.models;
import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.Proxy;

/*
 * temp meaning:
 * 0 - is not temp
 * 1 - is temp
 * 
 * */

@Entity
@Table(name="books")
@Proxy(lazy = false)
public class Book implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="author")
	private String author;
	
	@Column(name="keywords")
	private String keywords;
	
	@Column(name="publication_year")
	private Integer publicationYear;
	
	@Column(name="filename")
	private String filename;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "temp")
	private Integer temp;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="category_id", referencedColumnName="id", nullable = false)
	private Category category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="language_id", referencedColumnName="id", nullable = false)
	private Language language;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", referencedColumnName="id", nullable = false)
	private User user;
	
	public Book(){}
	
	public Book(Integer id, String title, String author, String keywords, Integer publicationYear, String filename,
			Category category, Language language, User user, String path, Integer temp, String createdAt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.filename = filename;
		this.category = category;
		this.language = language;
		this.user = user;
		this.path = path;
		this.temp = temp;
		this.createdAt = createdAt;
	}
	
	
	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}
	

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
