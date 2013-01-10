package com.flowsoft.domain;

import java.io.Serializable;
import java.util.Date;

abstract public class AbstractEntity implements Serializable {

	protected Date createdTS;
	protected Date modifiedTS;
	protected Integer id;

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
