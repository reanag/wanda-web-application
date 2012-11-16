package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Tag implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tagName;
	private Date createdTS;
	private Date modifiedTS;
	private Article taggedArticle;

	Logger logger = LoggerFactory.getLogger(Tag.class);

	public Tag() {
	}

	public Tag(String name) {
		logger.debug("Create Tag with id: " + name);
		this.tagName = name;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	@Id
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@ManyToOne()
	@JoinColumn(name = "taggedArticle")
	public Article getTaggedArticle() {
		return taggedArticle;
	}

	public void setTaggedArticle(Article taggedArticle) {
		this.taggedArticle = taggedArticle;
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