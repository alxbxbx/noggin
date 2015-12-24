package com.noggin.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	public Category() {}
	
	public Category(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
