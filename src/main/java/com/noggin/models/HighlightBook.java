package com.noggin.models;

public class HighlightBook extends Book{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String highlight;

	public HighlightBook(){
		super();
	}
	public HighlightBook(Book book){
		super();
		this.setAuthor(book.getAuthor());
		this.setCategory(book.getCategory());
		this.setCreatedAt(book.getCreatedAt());
		this.setFilename(book.getFilename());
		this.setId(book.getId());
		this.setKeywords(book.getKeywords());
		this.setLanguage(book.getLanguage());
		this.setPath(book.getPath());
		this.setPublicationYear(book.getPublicationYear());
		this.setTemp(book.getTemp());
		this.setTitle(book.getTitle());
		this.setUser(book.getUser());
	}
	public HighlightBook(String highlight) {
		super();
		this.highlight = highlight;
	}

	public String getHighlight() {
		return highlight;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	
	

}
