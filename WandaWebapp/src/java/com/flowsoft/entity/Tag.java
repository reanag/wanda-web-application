package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Tag extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(unique = true)
	private String tagName;

	private Integer rank;

	@Transient
	Logger logger = LoggerFactory.getLogger(Tag.class);

	public Tag() {
	}

	public Tag(String name) {
		logger.debug("Create Tag with id: " + name);
		this.tagName = name;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
