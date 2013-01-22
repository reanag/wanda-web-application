package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Comment extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// private Integer id;
	private WandaUser owner;
	private Article commentedArticle;
	private String commentContent;
	// private Date createdTS;
	// private Date modifiedTS;
	Logger logger = LoggerFactory.getLogger(Comment.class);

	public Comment() {
	}

	public Comment(WandaUser owner, Article article, String content) {
		this.owner = owner;
		this.commentedArticle = article;
		this.commentContent = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();

	}

	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
	}

	public Article getCommentedArticle() {
		return commentedArticle;
	}

	public void setCommentedArticle(Article commentedArticle) {
		this.commentedArticle = commentedArticle;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	// public Date getCreatedTS() {
	// return createdTS;
	// }
	//
	// public void setCreatedTS(Date createdTS) {
	// this.createdTS = createdTS;
	// }

	// public Date getModifiedTS() {
	// return modifiedTS;
	// }
	//
	// public void setModifiedTS(Date modifiedTS) {
	// this.modifiedTS = modifiedTS;
	// }
	//
	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }

}
