package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private WandaUser owner;
	private Article commentedArticle;
	private String commentContent;
	private Date createdTS;
	private Date modifiedTS;
	Logger logger = LoggerFactory.getLogger(Comment.class);

	public Comment() {
	}

	public Comment(WandaUser owner, Article article, String content) {
		this.owner = owner;
		this.commentedArticle = article;
		this.commentContent = content;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
		logger.debug("Create Comment with id: " + this.getId() + " by :"
				+ owner.getUsername());
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
	@JoinColumn(name = "title")
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

	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
