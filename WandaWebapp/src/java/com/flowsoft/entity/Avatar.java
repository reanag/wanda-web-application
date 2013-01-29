package com.flowsoft.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Embeddable
public class Avatar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Lob
	@Size(max = 15000)
	private byte[] image;

	public Avatar() {
	}

	public Avatar(byte[] im) {

		this.image = im;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
