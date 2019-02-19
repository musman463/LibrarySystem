package com.app.lms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
@Table(name="book")
public class BookEntity {
	
	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	

	@Column(name="name")
	private String name;
	
	@Column(name="author_name")
	private String authorName;

	@OneToOne(cascade={ CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH,CascadeType.REFRESH })
	@JoinColumn(name="category_id")
	private CategoryEntity categoryEntity;
	
	public BookEntity() {
		
	}

	public BookEntity(String name, String authorName, CategoryEntity categoryEntity) {
		this.name = name;
		this.authorName = authorName;
		this.categoryEntity = categoryEntity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public CategoryEntity getCategory() {
		return categoryEntity;
	}

	public void setCategory(CategoryEntity categoryEntity) {
		this.categoryEntity = categoryEntity;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", name=" + name + ", authorName=" + authorName + ", categoryEntity=" + categoryEntity + "]";
	}
}
