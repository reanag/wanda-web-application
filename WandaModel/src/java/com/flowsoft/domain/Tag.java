package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tag extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String tagName;
	private Integer rank;
	// private Date createdTS;
	// private Date modifiedTS;
	// private Integer id;

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

	// public Date getCreatedTS() {
	// return createdTS;
	// }
	//
	// public void setCreatedTS(Date createdTS) {
	// this.createdTS = createdTS;
	// }
	//
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

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
