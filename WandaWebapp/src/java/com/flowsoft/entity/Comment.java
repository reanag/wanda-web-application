package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "WComment", schema = "WANDA")
public class Comment extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	private WandaUser owner;
	@ManyToOne()
	private Article commentedArticle;
	private String commentContent;

	@Transient
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
}
