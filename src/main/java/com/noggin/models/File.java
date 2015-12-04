package com.noggin.models;
import javax.persistence.*;
@Entity
@Table(name = "file")
public class File {
	
	@Id @GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name="filename")
	private String fileName;
	
	@Column(name="mime")
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
