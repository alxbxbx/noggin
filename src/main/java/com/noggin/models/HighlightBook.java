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
