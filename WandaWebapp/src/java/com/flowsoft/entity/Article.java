package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Article extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	private WandaUser owner;

	@ManyToMany
	private Map<Integer, Tag> tagList = new HashMap<Integer, Tag>();

	@ManyToOne()
	private Category category;
	private String title;
	@Size(max = 5000)
	private String content;

	@Transient
	Logger logger = LoggerFactory.getLogger(Article.class);

	public Article() {
	}

	public Article(WandaUser owner, String title, String content) {
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
		logger.debug("Create Article with id: " + this.title + " by: "
				+ owner.getUsername());
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

	public Map<Integer, Tag> getTagList() {
		return tagList;
	}

	public void setTagList(Map<Integer, Tag> tagList2) {
		tagList = tagList2;

	}

}
