package com.flowsoft.component;

import java.io.Serializable;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

public class LinkLabel extends Label implements Serializable {

	private static final long serialVersionUID = 1L;
	private String content;
	private String linkText;
	private ExternalResource resource;

	public LinkLabel() {
	}

	public LinkLabel(String content, String linkText, ExternalResource resource) {

		this.content = content;
		this.linkText = linkText;
		this.resource = resource;

		setContentMode(ContentMode.HTML);
		setValue(content + "<a href=\"" + resource.getURL() + " \">" + linkText
				+ "</a> ");

	}

	public String getContent() {
		return content;
	}

	public String getLinkText() {
		return linkText;
	}

	public ExternalResource getResource() {
		return resource;
	}

	public void setLinkText(String string) {
		this.linkText = string;
		setContentMode(ContentMode.HTML);
		setValue(content + "<a href=\"" + resource.getURL() + " \">" + linkText
				+ "</a> ");

	}

}
