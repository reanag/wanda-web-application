package com.flowsoft.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(schema = "WANDA")
public class Category extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private WandaUser owner;

	@Transient
	Logger logger = LoggerFactory.getLogger(Category.class);

	public Category() {
		initDefaults();
	}

	public Category(WandaUser owner, String name) {
		this.owner = owner;
		this.categoryName = name;
		initDefaults();
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
