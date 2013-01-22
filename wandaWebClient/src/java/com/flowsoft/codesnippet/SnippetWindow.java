package com.flowsoft.codesnippet;

import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class SnippetWindow extends Window {
	static Logger logger = LoggerFactory.getLogger(SnippetWindow.class);
	private static final long serialVersionUID = 1L;

	public SnippetWindow(Integer id) {
		this.setWidth("400px");
		this.setHeight("600px");

		ResourceBundle s = ResourceBundle.getBundle("source", new Locale("HU"));

		Label cl = new Label();
		cl.setWidth("390px");
		cl.setHeight("580px");
		cl.setContentMode(ContentMode.HTML);
	}
}
