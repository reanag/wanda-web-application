package com.flowsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
abstract public class AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdTS;
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedTS;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(unique = true, nullable = false)
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
