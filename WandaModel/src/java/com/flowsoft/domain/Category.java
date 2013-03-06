package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO - BACSOG - CODE SNIPPET ****
public class Category extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	@Size(min = 3, max = 25)
	private String categoryName;
	@NotEmpty
	private String description;
	private WandaUser owner;
	@Transient
	Logger logger = LoggerFactory.getLogger(Category.class);

	public Category() {
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	public Category(WandaUser owner, String name) {
		this.owner = owner;
		this.categoryName = name;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	// TODO - BACSOG - **** CODE SNIPPET
	public Category(WandaUser owner, String name, String description) {
		this.owner = owner;
		this.categoryName = name;
		this.description = description;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
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
