package com.flowsoft.domain;

import java.io.Serializable;

public class Avatar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Byte[] image;

	public Avatar() {
	}

	public Avatar(Integer i, Byte[] im) {
		this.id = i;
		this.image = im;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

}
