package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryName;
	private WandaUser owner;
	private Date createdTS;
	private Date modifiedTS;

	public Category() {
	}

	public Category(WandaUser owner, String name) {
		this.owner = owner;
		this.categoryName = name;
		this.createdTS = new Date(System.currentTimeMillis());
		this.modifiedTS = (Date) createdTS.clone();
	}

	@Id
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String name) {
		this.categoryName = name;
	}

	@ManyToOne()
	@JoinColumn(name = "username")
	public WandaUser getOwner() {
		return owner;
	}

	public void setOwner(WandaUser owner) {
		this.owner = owner;
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
