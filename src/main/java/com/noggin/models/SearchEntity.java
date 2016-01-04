package com.noggin.models;

import java.io.Serializable;

import javax.persistence.Entity;

/* ST in attributes is for Search Type and it can be:

	"Regular",
	"Fuzzy",
	"Phrase",
	"Range",
	"Prefix"
 */

/* SC in attributes is for Search Condition and it can be:
 	"MUST",
 	"MUST_NOT",
 	"SHOULD"

 * */

@Entity
public class SearchEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	private String textST;
	private String textSC;
	private String keywords;
	private String keywordsST;
	private String keywordsSC;
	private String author;
	private String authorST;
	private String authorSC;
	private String title;
	private String titleST;
	private String titleSC;
	
	
	public SearchEntity(){
		
	}
	
	public SearchEntity(String text, String textST, String textSC, String keywords, String keywordsST,
			String keywordsSC, String author, String authorST, String authorSC, String title, String titleST,
			String titleSC) {
		super();
		this.text = text;
		this.textST = textST;
		this.textSC = textSC;
		this.keywords = keywords;
		this.keywordsST = keywordsST;
		this.keywordsSC = keywordsSC;
		this.author = author;
		this.authorST = authorST;
		this.authorSC = authorSC;
		this.title = title;
		this.titleST = titleST;
		this.titleSC = titleSC;
	}

	public String getTextSC() {
		return textSC;
	}

	public void setTextSC(String textSC) {
		this.textSC = textSC;
	}

	public String getKeywordsSC() {
		return keywordsSC;
	}

	public void setKeywordsSC(String keywordsSC) {
		this.keywordsSC = keywordsSC;
	}

	public String getAuthorSC() {
		return authorSC;
	}

	public void setAuthorSC(String authorSC) {
		this.authorSC = authorSC;
	}

	public String getTitleSC() {
		return titleSC;
	}

	public void setTitleSC(String titleSC) {
		this.titleSC = titleSC;
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextST() {
		return textST;
	}
	public void setTextST(String textST) {
		this.textST = textST;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getKeywordsST() {
		return keywordsST;
	}
	public void setKeywordsST(String keywordsST) {
		this.keywordsST = keywordsST;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorST() {
		return authorST;
	}
	public void setAuthorST(String authorST) {
		this.authorST = authorST;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleST() {
		return titleST;
	}
	public void setTitleST(String titleST) {
		this.titleST = titleST;
	}
	
	
	

}
