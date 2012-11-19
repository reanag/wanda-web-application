package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private WandaUser owner;
	private Date createdTS;
	private Date modifiedTS;
	private Integer id;
	Logger logger = LoggerFactory.getLogger(Category.class);

	public Category() {
	}

	public Category(WandaUser owner, String name) {
		this.owner = owner;
		this.categoryName = name;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
		logger.debug("Create Category with id: " + categoryName + " by : "
				+ owner.getUsername());
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
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
