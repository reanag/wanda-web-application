package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	private WandaUser owner;
	private Category category;
	private String title;

	private String content;
	private Date createdTS;
	private Date modifiedTS;
	private Integer id;

	Logger logger = LoggerFactory.getLogger(Article.class);

	public Article() {
	}

	public Article(WandaUser owner, String title, String content) {
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
		logger.debug("Create Article with title: {} by: {}", getTitle(),
				owner.getUsername());
	}

	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.modifiedTS = new Date(System.currentTimeMillis());
		this.category = category;
	}

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

	public Date getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}

	public Date getModifiedTS() {
		return modifiedTS;
	}

	public void setModifiedTS(Date modifiedTS) {
		this.modifiedTS = modifiedTS;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
