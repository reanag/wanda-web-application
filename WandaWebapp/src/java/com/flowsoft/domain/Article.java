package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	private WandaUser owner;
	private Category category;
	private String title;
	private String content;
	private Date createdTS;
	private Date modifiedTS;

	public Article() {
	}

	public Article(WandaUser owner, String title, String content) {
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	@ManyToOne()
	@JoinColumn(name = "username")
	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
	}

	@ManyToOne()
	@JoinColumn(name = "category")
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.modifiedTS = new Date(System.currentTimeMillis());
		this.category = category;
	}

	@Id
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.modifiedTS = new Date(System.currentTimeMillis());
		this.content = content;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedTS() {
		return modifiedTS;
	}

	public void setModifiedTS(Date modifiedTS) {
		this.modifiedTS = modifiedTS;
	}

}
