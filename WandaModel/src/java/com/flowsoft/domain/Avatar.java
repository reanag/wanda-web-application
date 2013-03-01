package com.flowsoft.domain;

import java.io.Serializable;

public class Avatar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private byte[] image;

	public Avatar() {
	}

	public Avatar(byte[] im) {

		this.image = im;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] bs) {
		this.image = bs;
	}

}
