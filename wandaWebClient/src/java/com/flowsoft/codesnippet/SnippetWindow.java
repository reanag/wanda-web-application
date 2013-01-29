package com.flowsoft.codesnippet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class SnippetWindow extends Window {
	static Logger logger = LoggerFactory.getLogger(SnippetWindow.class);
	private static final long serialVersionUID = 1L;

	public SnippetWindow(String source) {
		this.setWidth("600px");
		this.setHeight("400px");
		this.setStyleName("black");
		Label c = new CodeLabel(source);

		// c.setStyleName("black");
		addComponent(c);

	}
}
