package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Article implements Serializable {

	protected static final long serialVersionUID = 1L;
	protected WandaUser owner;
	@NotNull
	protected Category category;
	protected Double rank;
	protected Integer rankCount;

	@NotNull
	@Size(max = 100, min = 3)
	protected String title;
	protected Set<Tag> tagList = new HashSet<Tag>();
	@NotEmpty
	protected String content;
	protected Date createdTS;
	protected Date modifiedTS;
	protected Integer id;

	Logger logger = LoggerFactory.getLogger(Article.class);

	public Article() {
	}

	public Article(WandaUser owner, String title, String content) {
		this.owner = owner;
		this.title = title;
		this.content = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();

		// logger.debug("Create Article with title: {} by: {}", getTitle(),
		// owner.getUsername());
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
		// logger.debug("Edit Article with title: {} by: {} new title: {} "
		// + title, getTitle(), owner.getUsername());
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

	public Set<Tag> getTagList() {
		return tagList;
	}

	public void setTagList(Set<Tag> tagList) {
		this.tagList = tagList;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	public Integer getRankCount() {
		return rankCount;
	}

	public void setRankCount(Integer rankCount) {
		this.rankCount = rankCount;
	}

	public String getAuthor() {
		if (owner != null) {
			return owner.getUsername();
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "Article [owner=" + owner + ", category=" + category + ", rank="
				+ rank + ", rankCount=" + rankCount + ", title=" + title
				+ ", tagList=" + tagList + ", content=" + content
				+ ", createdTS=" + createdTS + ", modifiedTS=" + modifiedTS
				+ ", id=" + id + "]";
	}

}
