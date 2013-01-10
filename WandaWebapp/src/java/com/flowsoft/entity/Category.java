package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Category extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private WandaUser owner;

	@Transient
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
