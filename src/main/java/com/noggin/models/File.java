package com.noggin.models;

public class File {
	
	private Integer id;
	private String fileName;
	private String MIME;
	
	public File(){}

	public File(Integer id, String fileName, String mIME) {
		super();
		this.id = id;
		this.fileName = fileName;
		MIME = mIME;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMIME() {
		return MIME;
	}

	public void setMIME(String mIME) {
		MIME = mIME;
	}
	
	
	
}
