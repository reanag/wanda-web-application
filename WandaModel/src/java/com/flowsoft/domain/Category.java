package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Category extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 3, max = 25)
	private String categoryName;
	@NotEmpty
	private String description;
	private WandaUser owner;
	// private Date createdTS;
	// private Date modifiedTS;
	// private Integer id;
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

	public Category(WandaUser owner, String name, String description) {
		this.owner = owner;
		this.categoryName = name;
		this.description = description;
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

	// public Integer getId() {
	// return id;
	// }
	//
	// public void setId(Integer id) {
	// this.id = id;
	// }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		logger.debug("New description for category " + categoryName
				+ " is added: " + description);
		this.description = description;
	}

}
